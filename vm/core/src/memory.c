/*
 * Copyright (C) 2012 Trillian AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include <robovm.h>
#include <string.h>
#include <gc/gc_mark.h>
#include "uthash.h"
#include "utlist.h"

static Class* java_nio_ReadWriteDirectByteBuffer = NULL;
static Method* java_nio_ReadWriteDirectByteBuffer_init = NULL;
static InstanceField* java_nio_Buffer_effectiveDirectAddress = NULL;
static InstanceField* java_nio_Buffer_capacity = NULL;
static Class* java_lang_ref_Reference = NULL;
static InstanceField* java_lang_ref_Reference_referent = NULL;
static InstanceField* java_lang_ref_Reference_pendingNext = NULL;
static InstanceField* java_lang_ref_Reference_queue = NULL;
static InstanceField* java_lang_ref_Reference_queueNext = NULL;
static Class* java_lang_ref_PhantomReference = NULL;
static Class* java_lang_ref_WeakReference = NULL;
static Class* java_lang_ref_SoftReference = NULL;
static Class* java_lang_ref_FinalizerReference = NULL;
static Method* java_lang_ref_FinalizerReference_add = NULL;
static InstanceField* java_lang_ref_FinalizerReference_zombie = NULL;
static Class* java_lang_ref_ReferenceQueue = NULL;
static Method* java_lang_ref_ReferenceQueue_add = NULL;
static InstanceField* java_lang_Throwable_stackState = NULL;
static Class* org_robovm_rt_bro_Struct = NULL;
static InstanceField* org_robovm_rt_bro_Struct_handle = NULL;
static VM* vm = NULL;

typedef struct ReferenceList {
    struct ReferenceList* next;
    Object* reference;
} ReferenceList;
typedef struct ReferentEntry {
    void* key;
    ReferenceList* references;
    UT_hash_handle hh;
} ReferentEntry;
static ReferentEntry* referents = NULL;

static Mutex referentsLock;

// The GC kind used when allocating Objects (and arrays and Classes which are also Objects)
static int object_gc_kind;

static inline struct GC_ms_entry* markRegion(void** start, void** end, struct GC_ms_entry* mark_stack_ptr, struct GC_ms_entry* mark_stack_limit) {
    void** p = start;
    while (p < end) {
        mark_stack_ptr = GC_MARK_AND_PUSH(*p, mark_stack_ptr, mark_stack_limit, NULL);
        p++;
    }
    return mark_stack_ptr;
}

static struct GC_ms_entry* markObject(GC_word* addr, struct GC_ms_entry* mark_stack_ptr, struct GC_ms_entry* mark_stack_limit, GC_word env) {
    Object* obj = (Object*) addr;

    if (obj == NULL || obj->clazz == NULL || obj->clazz->object.clazz != java_lang_Class) {
        // According to the comments in gc_mark.h the GC sometimes calls the mark_proc with unused objects.
        // Such objects have been cleared except for the first word which points to a free list link field.
        // A valid RovoVM Object must point to a Class and the Class of the Object's Class must be java.lang.Class.
        return mark_stack_ptr;
    }

    mark_stack_ptr = GC_MARK_AND_PUSH(obj->clazz, mark_stack_ptr, mark_stack_limit, NULL);

    if (obj->clazz == java_lang_Class) {
        // Class*
        Class* clazz = (Class*) obj;
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->_data, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH((void*) clazz->name, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->classLoader, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->superclass, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->componentType, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->_interfaces, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->_fields, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->_methods, mark_stack_ptr, mark_stack_limit, NULL);
        void** start = (void**) (((char*) clazz) + offsetof(Class, data));
        void** end = (void**) (((char*) start) + clazz->classRefCount * sizeof(Object*));
        mark_stack_ptr = markRegion(start, end, mark_stack_ptr, mark_stack_limit);
    } else if (CLASS_IS_ARRAY(obj->clazz)) {
        if (!CLASS_IS_PRIMITIVE(obj->clazz->componentType)) {
            // Array of objects. Mark all values in the array.
            ObjectArray* array = (ObjectArray*) obj;
            void** start = (void**) (((char*) array) + offsetof(ObjectArray, values));
            void** end = (void**) (((char*) start) + sizeof(Object*) * array->length);
            mark_stack_ptr = markRegion(start, end, mark_stack_ptr, mark_stack_limit);
        }
    } else {
        // Object* - for each Class in the hierarchy of obj's Class we mark the first instanceRefCount*sizeof(Object*) bytes
        Class* clazz = obj->clazz;
        while (clazz != NULL) {
            void** start = (void**) (((char*) obj) + clazz->instanceDataOffset);
            void** end = (void**) (((char*) start) + clazz->instanceRefCount * sizeof(Object*));
            if (clazz == java_lang_ref_Reference) {
                // Don't mark the referent field
                void** referent_start = (void**) (((char*) obj) + java_lang_ref_Reference_referent->offset);
                void** referent_end = (void**) (((char*) referent_start) + sizeof(Object*));
                mark_stack_ptr = markRegion(start, referent_start, mark_stack_ptr, mark_stack_limit);
                mark_stack_ptr = markRegion(referent_end, end, mark_stack_ptr, mark_stack_limit);
            } else {
                mark_stack_ptr = markRegion(start, end, mark_stack_ptr, mark_stack_limit);
            }

            // Some classes use longs and ints to store pointers to GC allocated memory.
            // For each such class we need to mark those fields here.
            // Note: java.lang.Thread, java.lang.reflect.Constructor, java.lang.reflect.Method, 
            // java.lang.reflect.Field also contain such fields but we don't have
            // to mark those because the Thread, Method and Field C structures those
            // point to are also referenced by other roots (the threads list, Class structures)
            // that prevent GCing.
            if (clazz == java_lang_Throwable) {
                // The 'stackState' field in java.lang.Throwable is a long but contains
                // a pointer to an address on the GCed heap.
                void** field_start = (void**) (((char*) obj) + java_lang_Throwable_stackState->offset);
                void** field_end = (void**) (((char*) field_start) + sizeof(jlong));
                mark_stack_ptr = markRegion(field_start, field_end, mark_stack_ptr, mark_stack_limit);
            } else if (clazz == org_robovm_rt_bro_Struct) {
                // The 'handle' field in org.robovm.rt.bro.Struct (actually in its
                // superclass NativeObject) is a long but contains a pointer.
                // Possibly to an address on the GCed heap.
                void** field_start = (void**) (((char*) obj) + org_robovm_rt_bro_Struct_handle->offset);
                void** field_end = (void**) (((char*) field_start) + sizeof(jlong));
                mark_stack_ptr = markRegion(field_start, field_end, mark_stack_ptr, mark_stack_limit);
            }
            clazz = clazz->superclass;
        }
    }

    return mark_stack_ptr;
}

jboolean initGC(Options* options) {
    GC_INIT();
    GC_set_java_finalization(1);
    if (options->maxHeapSize > 0) {
        GC_set_max_heap_size(options->maxHeapSize);
    }
    if (options->initialHeapSize > 0) {
        size_t now = GC_get_heap_size();
        if (options->initialHeapSize > now) {
            GC_expand_hp(options->initialHeapSize - now);
        }
    }

    object_gc_kind = GC_new_kind(GC_new_free_list(), GC_MAKE_PROC(GC_new_proc(markObject), 0), 0, 1);

    if (rvmInitMutex(&referentsLock) != 0) {
        return FALSE;
    }

    return TRUE;
}

static void* gcAllocateKind(size_t size, jint kind) {
    return GC_generic_malloc(size, kind);
}
void* gcAllocate(size_t size) {
    return GC_MALLOC(size);
}
void* gcAllocateUncollectable(size_t size) {
    return GC_MALLOC_UNCOLLECTABLE(size);
}
void* gcAllocateAtomic(size_t size) {
    void* m = GC_MALLOC_ATOMIC(size);
    if (m) {
        memset(m, 0, size);
    }
    return m;
}

/*
 * Adds a reference to the tail of a circular queue of references.
 */
static void enqueuePendingReference(Env* env, Object* ref, Object** list) {
    // This code is a port of the enqueuePendingReference() function in Android's MarkSweep.cpp
    if (*list == NULL) {
        // ref.pendingNext = ref
        rvmSetObjectInstanceFieldValue(env, ref, java_lang_ref_Reference_pendingNext, ref);
        *list = ref;
    } else {
        // head = list.pendingNext
        Object* head = rvmGetObjectInstanceFieldValue(env, *list, java_lang_ref_Reference_pendingNext);
        // ref.pendingNext = head
        rvmSetObjectInstanceFieldValue(env, ref, java_lang_ref_Reference_pendingNext, head);
        // list.pendingNext = ref
        rvmSetObjectInstanceFieldValue(env, *list, java_lang_ref_Reference_pendingNext, ref);
    }
}

/*
 * Removes the reference at the head of a circular queue of
 * references.
 */
static Object* dequeuePendingReference(Env* env, Object** list) {
    // This code is a port of the dequeuePendingReference() function in Android's MarkSweep.cpp
    assert(list != NULL);
    assert(*list != NULL);
    // head = list.pendingNext
    Object* head = rvmGetObjectInstanceFieldValue(env, *list, java_lang_ref_Reference_pendingNext);
    Object* ref;
    if (*list == head) {
        ref = *list;
        *list = NULL;
    } else {
        // next = head.pendingNext
        Object* next = rvmGetObjectInstanceFieldValue(env, head, java_lang_ref_Reference_pendingNext);
        // list.pendingNext = next
        rvmSetObjectInstanceFieldValue(env, *list, java_lang_ref_Reference_pendingNext, next);
        ref = head;
    }
    rvmSetObjectInstanceFieldValue(env, ref, java_lang_ref_Reference_pendingNext, NULL);
    return ref;
}

/*
 * Clear the referent field.
 */
static void clearReference(Env* env, Object* reference) {
    // This code is a port of the clearReference() function in Android's MarkSweep.cpp
    rvmSetObjectInstanceFieldValue(env, reference, java_lang_ref_Reference_referent, NULL);
}

/*
 * Returns true if the reference was registered with a reference queue
 * and has not yet been enqueued.
 */
static jboolean isEnqueuable(Env* env, Object* reference) {
    // This code is a port of the isEnqueuable() function in Android's MarkSweep.cpp
    assert(reference != NULL);
    Object* queue = rvmGetObjectInstanceFieldValue(env, reference, java_lang_ref_Reference_queue);
    Object* queueNext = rvmGetObjectInstanceFieldValue(env, reference, java_lang_ref_Reference_queueNext);
    return (queue != NULL && queueNext == NULL) ? TRUE : FALSE;
}

/*
 * Schedules a reference to be appended to its reference queue.
 */
static void enqueueReference(Env* env, Object* ref, Object** cleared) {
    // This code is a port of the enqueueReference() function in Android's MarkSweep.cpp
    assert(ref != NULL);
    assert(isEnqueuable(env, ref));
    enqueuePendingReference(env, ref, cleared);
}

/*
 * Unlink the reference list clearing references objects with unreachable
 * referents.  Cleared references registered to a reference queue are
 * enqueued on the cleared list.
 */
static void clearAndEnqueueReferences(Env* env, Object** list, Object** cleared) {
    // This code is a port of the clearWhiteReferences() function in Android's MarkSweep.cpp
    assert(list != NULL);
    assert(cleared != NULL);
    while (*list != NULL) {
        Object* ref = dequeuePendingReference(env, list);
        Object* referent = rvmGetObjectInstanceFieldValue(env, ref, java_lang_ref_Reference_referent);
        if (referent != NULL) {
            clearReference(env, ref);
            if (isEnqueuable(env, ref)) {
                enqueueReference(env, ref, cleared);
            }
        }
    }
    assert(*list == NULL);
}

/*
 * Enqueues finalizer references with unreachable referents.  The
 * referent is moved to the zombie field (which makes it reachable again), 
 * and the referent field is cleared.
 */
static void enqueueFinalizerReferences(Env* env, Object** list, Object** cleared) {
    // This code is a port of the enqueueFinalizerReferences() function in Android's MarkSweep.cpp
    assert(list != NULL);
    while (*list != NULL) {
        Object* ref = dequeuePendingReference(env, list);
        Object* referent = rvmGetObjectInstanceFieldValue(env, ref, java_lang_ref_Reference_referent);
        if (referent != NULL) {
            /* If the referent is non-null the reference must queuable. */
            assert(isEnqueuable(env, ref));
            // Copy the referent to the zombie field
            rvmSetObjectInstanceFieldValue(env, ref, java_lang_ref_FinalizerReference_zombie, referent);
            // Clear the referent
            clearReference(env, ref);
            enqueueReference(env, ref, cleared);
        }
    }
    assert(*list == NULL);
}

static void _finalizeObject(GC_PTR addr, GC_PTR client_data);

static void finalizeObject(Env* env, Object* obj) {
//    TRACEF("finalizeObject: %p (%s)\n", obj, obj->clazz->name);

    rvmLockMutex(&referentsLock);
    void* key = (void*) GC_HIDE_POINTER(obj);
    ReferentEntry* referentEntry;
    HASH_FIND_PTR(referents, &key, referentEntry);

    assert(referentEntry != NULL);

    if (referentEntry->references == NULL) {
        // The object is not referenced by any type of reference and can never be resurrected.
        HASH_DEL(referents, referentEntry);
        rvmUnlockMutex(&referentsLock);
        return;
    }

    Object* softReferences = NULL;
    Object* weakReferences = NULL;
    Object* finalizerReferences = NULL;
    Object* phantomReferences = NULL;
    Object* clearedReferences = NULL;

    ReferenceList* refNode;
    while (referentEntry->references != NULL) {
        refNode = referentEntry->references;
        LL_DELETE(referentEntry->references, refNode);
        Object** list = NULL;
        Object* reference = refNode->reference;
        if (rvmIsSubClass(java_lang_ref_SoftReference, reference->clazz)) {
            list = &softReferences;
        } else if (rvmIsSubClass(java_lang_ref_WeakReference, reference->clazz)) {
            list = &weakReferences;
        } else if (rvmIsSubClass(java_lang_ref_FinalizerReference, reference->clazz)) {
            list = &finalizerReferences;
        } else if (rvmIsSubClass(java_lang_ref_PhantomReference, reference->clazz)) {
            list = &phantomReferences;
        }
        enqueuePendingReference(env, reference, list);
    }
    assert(referentEntry->references == NULL);

    clearAndEnqueueReferences(env, &softReferences, &clearedReferences);
    clearAndEnqueueReferences(env, &weakReferences, &clearedReferences);
    enqueueFinalizerReferences(env, &finalizerReferences, &clearedReferences);
    clearAndEnqueueReferences(env, &phantomReferences, &clearedReferences);

    // Reregister for finalization. If no new references have been added to the list of references for the referent the
    // next time it gets finalized we know it will never be resurrected.
    GC_REGISTER_FINALIZER_NO_ORDER(obj, _finalizeObject, NULL, NULL, NULL);

    rvmUnlockMutex(&referentsLock);

    if (clearedReferences != NULL) {
        rvmCallVoidClassMethod(env, java_lang_ref_ReferenceQueue, java_lang_ref_ReferenceQueue_add, clearedReferences);
        assert(rvmExceptionOccurred(env) == NULL);
    }
}

static void _finalizeObject(GC_PTR addr, GC_PTR client_data) {
    Object* obj = (Object*) addr;
    Env* env = rvmGetEnv();
    assert(env != NULL);
    finalizeObject(env, obj);
}

void rvmRegisterFinalizer(Env* env, Object* obj) {
    // Call java.lang.FinalizerReference.add(obj)
    // A FinalizerReference will be created for obj and that reference will be registered using rvmRegisterReference().
    rvmCallVoidClassMethod(env, java_lang_ref_FinalizerReference, java_lang_ref_FinalizerReference_add, obj);
}

void rvmRegisterReference(Env* env, Object* reference, Object* referent) {
    if (referent) {
        // Add 'reference' to the references list for 'referent' in the referents hashtable
        rvmLockMutex(&referentsLock);

        ReferenceList* l = rvmAllocateMemory(env, sizeof(ReferenceList));
        if (!l) goto done; // OOM thrown
        l->reference = reference;

        void* key = (void*) GC_HIDE_POINTER(referent); // Hide the pointer from the GC so that it doesn't prevent the referent from being GCed.
        ReferentEntry* referentEntry;
        HASH_FIND_PTR(referents, &key, referentEntry);
        if (!referentEntry) {
            // referent is not in the hashtable. Add it.
            referentEntry = rvmAllocateMemory(env, sizeof(ReferentEntry));
            if (!referentEntry) goto done; // OOM thrown
            referentEntry->key = key;
            HASH_ADD_PTR(referents, key, referentEntry);
        }

        // Add the reference to the referent's list of references
        LL_PREPEND(referentEntry->references, l);

        // Register the referent for finalization
        GC_REGISTER_FINALIZER_NO_ORDER(referent, _finalizeObject, NULL, NULL, NULL);

done:
        rvmUnlockMutex(&referentsLock);
    }
}

void rvmRegisterDisappearingLink(Env* env, void** address, Object* obj) {
    GC_GENERAL_REGISTER_DISAPPEARING_LINK(address, obj);
}

void rvmUnregisterDisappearingLink(Env* env, void** address) {
    GC_unregister_disappearing_link(address);
}

jboolean rvmInitMemory(Env* env) {
    vm = env->vm;
    java_lang_ref_Reference = rvmFindClassUsingLoader(env, "java/lang/ref/Reference", NULL);
    if (!java_lang_ref_Reference) return FALSE;
    java_lang_ref_Reference_referent = rvmGetInstanceField(env, java_lang_ref_Reference, "referent", "Ljava/lang/Object;");
    if (!java_lang_ref_Reference_referent) return FALSE;
    java_lang_ref_Reference_pendingNext = rvmGetInstanceField(env, java_lang_ref_Reference, "pendingNext", "Ljava/lang/ref/Reference;");
    if (!java_lang_ref_Reference_pendingNext) return FALSE;
    java_lang_ref_Reference_queue = rvmGetInstanceField(env, java_lang_ref_Reference, "queue", "Ljava/lang/ref/ReferenceQueue;");
    if (!java_lang_ref_Reference_queue) return FALSE;
    java_lang_ref_Reference_queueNext = rvmGetInstanceField(env, java_lang_ref_Reference, "queueNext", "Ljava/lang/ref/Reference;");
    if (!java_lang_ref_Reference_queueNext) return FALSE;
    java_lang_ref_PhantomReference = rvmFindClassUsingLoader(env, "java/lang/ref/PhantomReference", NULL);
    if (!java_lang_ref_PhantomReference) return FALSE;
    java_lang_ref_WeakReference = rvmFindClassUsingLoader(env, "java/lang/ref/WeakReference", NULL);
    if (!java_lang_ref_WeakReference) return FALSE;
    java_lang_ref_SoftReference = rvmFindClassUsingLoader(env, "java/lang/ref/SoftReference", NULL);
    if (!java_lang_ref_SoftReference) return FALSE;
    java_lang_ref_FinalizerReference = rvmFindClassUsingLoader(env, "java/lang/ref/FinalizerReference", NULL);
    if (!java_lang_ref_FinalizerReference) return FALSE;
    java_lang_ref_FinalizerReference_add = rvmGetClassMethod(env, java_lang_ref_FinalizerReference, "add", "(Ljava/lang/Object;)V");
    if (!java_lang_ref_FinalizerReference_add) return FALSE;
    java_lang_ref_FinalizerReference_zombie = rvmGetInstanceField(env, java_lang_ref_FinalizerReference, "zombie", "Ljava/lang/Object;");
    if (!java_lang_ref_FinalizerReference_zombie) return FALSE;
    java_lang_ref_ReferenceQueue = rvmFindClassUsingLoader(env, "java/lang/ref/ReferenceQueue", NULL);
    if (!java_lang_ref_ReferenceQueue) return FALSE;
    java_lang_ref_ReferenceQueue_add = rvmGetClassMethod(env, java_lang_ref_ReferenceQueue, "add", "(Ljava/lang/ref/Reference;)V");
    if (!java_lang_ref_ReferenceQueue_add) return FALSE;
    java_nio_ReadWriteDirectByteBuffer = rvmFindClassUsingLoader(env, "java/nio/ReadWriteDirectByteBuffer", NULL);
    if (!java_nio_ReadWriteDirectByteBuffer) return FALSE;
    java_nio_ReadWriteDirectByteBuffer_init = rvmGetInstanceMethod(env, java_nio_ReadWriteDirectByteBuffer, "<init>", "(II)V");
    if (!java_nio_ReadWriteDirectByteBuffer_init) return FALSE;
    Class* java_nio_Buffer = rvmFindClassUsingLoader(env, "java/nio/Buffer", NULL);
    if (!java_nio_Buffer) return FALSE;
    java_nio_Buffer_effectiveDirectAddress = rvmGetInstanceField(env, java_nio_Buffer, "effectiveDirectAddress", "I");
    if (!java_nio_Buffer_effectiveDirectAddress) return FALSE;
    java_nio_Buffer_capacity = rvmGetInstanceField(env, java_nio_Buffer, "capacity", "I");
    if (!java_nio_Buffer_capacity) return FALSE;
    java_lang_Throwable_stackState = rvmGetInstanceField(env, java_lang_Throwable, "stackState", "J");
    if (!java_lang_Throwable_stackState) return FALSE;
    org_robovm_rt_bro_Struct = rvmFindClassUsingLoader(env, "org/robovm/rt/bro/Struct", NULL);
    if (!org_robovm_rt_bro_Struct) {
        // We don't need Struct if it hasn't been compiled in
        rvmExceptionClear(env);
    } else {
        org_robovm_rt_bro_Struct_handle = rvmGetInstanceField(env, org_robovm_rt_bro_Struct, "handle", "J");
        if (!org_robovm_rt_bro_Struct_handle) return FALSE;
    }

    // Make sure that java.lang.ReferenceQueue is initialized now to prevent deadlocks during finalization
    // when both holding the referentsLock and the classLock.
    rvmInitialize(env, java_lang_ref_ReferenceQueue);
    if (rvmExceptionOccurred(env)) return FALSE;

    return TRUE;
}

Class* rvmAllocateMemoryForClass(Env* env, jint classDataSize) {
    return gcAllocateKind(classDataSize, object_gc_kind);
}

Object* rvmAllocateMemoryForObject(Env* env, Class* clazz) {
    return gcAllocateKind(clazz->instanceDataSize, object_gc_kind);
}

Array* rvmAllocateMemoryForArray(Env* env, jint length, jint elementSize) {
    jlong size = (jlong) sizeof(Array) + (jlong) length * (jlong) elementSize;
    if (size > (jlong) (size_t) -1) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return gcAllocateKind((size_t) size, object_gc_kind);
}

void* rvmAllocateMemory(Env* env, size_t size) {
    void* m = gcAllocate(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryUncollectable(Env* env, size_t size) {
    void* m = gcAllocateUncollectable(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryAtomic(Env* env, size_t size) {
    void* m = gcAllocateAtomic(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void rvmFreeUncollectable(Env* env, void* m) {
    GC_FREE(m);
}

void rvmGCCollect(Env* env) {
    GC_gcollect();
}

void* rvmCopyMemory(Env* env, const void* src, size_t size) {
    void* dest = rvmAllocateMemory(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* rvmCopyMemoryZ(Env* env, const char* src) {
    return rvmCopyMemory(env, src, strlen(src) + 1);
}

Object* rvmNewDirectByteBuffer(Env* env, void* address, jlong capacity) {
    jvalue args[2];
    args[0].i = (jint) address;
    args[1].i = (jint) capacity;
    return rvmNewObjectA(env, java_nio_ReadWriteDirectByteBuffer, java_nio_ReadWriteDirectByteBuffer_init, args);
}

void* rvmGetDirectBufferAddress(Env* env, Object* buf) {
    jint effectiveDirectAddress = rvmGetIntInstanceFieldValue(env, buf, java_nio_Buffer_effectiveDirectAddress);
    return (void*) (intptr_t) effectiveDirectAddress;
}

jlong rvmGetDirectBufferCapacity(Env* env, Object* buf) {
    jlong capacity = rvmGetIntInstanceFieldValue(env, buf, java_nio_Buffer_capacity);
    return capacity & 0x00000000ffffffffULL;
}
