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
#include <stdint.h>
#include <gc/gc_mark.h>
#include "private.h"
#include "uthash.h"
#include "utlist.h"

#define LOG_TAG "core.memory"

#define OBJECTS_GC_ROOTS_INITIAL_SIZE 2048
#define INTERNAL_GC_ROOTS_INITIAL_SIZE 1024

static Class* java_nio_ReadWriteDirectByteBuffer = NULL;
static Method* java_nio_ReadWriteDirectByteBuffer_init = NULL;
static InstanceField* java_nio_Buffer_effectiveDirectAddress = NULL;
static InstanceField* java_nio_Buffer_capacity = NULL;
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
static Class* java_nio_MemoryBlock = NULL;
static InstanceField* java_nio_MemoryBlock_address = NULL;
static VM* vm = NULL;

// A shared OutOfMemoryError instance with an empty stack trace that we will use
// when memory is so low that there's not even enough left to allocate a new 
// OutOfMemoryError.
static Object* criticalOutOfMemoryError = NULL;

// GC descriptor specifying which words in a ReferentEntry that should be scanned 
// for heap pointers. The hh.hashv value in particular must not be scanned since
// it often can be mistaken for a pointer.
#define REFERENT_ENTRY_GC_BITMAP (MAKE_GC_BITMAP(offsetof(ReferentEntry, references)) \
                                 |MAKE_GC_BITMAP(offsetof(ReferentEntry, cleanupHandlers)) \
                                 |MAKE_GC_BITMAP(offsetof(ReferentEntry, hh.next)))

typedef struct CleanupHandlerList {
    struct CleanupHandlerList* next;
    CleanupHandler handler;
} CleanupHandlerList;
typedef struct ReferenceList {
    struct ReferenceList* next;
    Object* reference;
} ReferenceList;
typedef struct ReferentEntry {
    void* key;
    ReferenceList* references;
    CleanupHandlerList* cleanupHandlers;
    UT_hash_handle hh;
} ReferentEntry;
static ReferentEntry* referents = NULL;
static uint32_t referentEntryGCKind;

static Object** objectGCRoots = NULL;
static jint objectGCRootsCount = 0;
static jint objectGCRootsSize = 0;

static Mutex referentsLock;
static Mutex gcRootsLock;

// The GC kind used when allocating Objects (and Classes which are also Objects)
static uint32_t objectGCKind;
// The GC kind used when allocating large arrays
static uint32_t largeArrayGCKind;
// The GC kind used when allocating primitive arrays and Objects containing no references
static uint32_t atomicObjectGCKind;

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
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->typeInfo, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->vitable, mark_stack_ptr, mark_stack_limit, NULL);
        mark_stack_ptr = GC_MARK_AND_PUSH(clazz->itables, mark_stack_ptr, mark_stack_limit, NULL);
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
        // Only small arrays of objects are allocated using this mark proc. Mark all values in the array.
        ObjectArray* array = (ObjectArray*) obj;
        void** start = (void**) (((char*) array) + offsetof(ObjectArray, values));
        void** end = (void**) (((char*) start) + sizeof(Object*) * array->length);
        mark_stack_ptr = markRegion(start, end, mark_stack_ptr, mark_stack_limit);
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
            } else if (clazz == java_nio_MemoryBlock) {
                // The 'address' field in java.nio.MemoryBlock is an int but contains a pointer.
                // Possibly to an address on the GCed heap.
                void** field_start = (void**) (((char*) obj) + java_nio_MemoryBlock_address->offset);
                void** field_end = (void**) (((char*) field_start) + sizeof(jint));
                mark_stack_ptr = markRegion(field_start, field_end, mark_stack_ptr, mark_stack_limit);
            }
            clazz = clazz->superclass;
        }
    }

    return mark_stack_ptr;
}

static void gcWarnProc(char* msg, GC_word arg) {
    WARNF(msg, arg);
}

static void dumpRefs(void** start, void** end) {
    void** p = start;
    while (p < end) {
        if (*p) {
            fprintf(stderr, "\t%p\n", *p);
        }
        p++;
    }
}

static void heapDumpCallback(void* ptr, unsigned char kind, size_t sz, void* data) {
    if (kind == objectGCKind || kind == largeArrayGCKind || kind == atomicObjectGCKind) {
        Object* obj = (Object*) ptr;
        if (obj->clazz == java_lang_Class) {
            Class* clazz = (Class*) obj;
            fprintf(stderr, "%p (class %s of size %d bytes)\n", clazz, clazz->name, clazz->classDataSize);
            if (clazz->_data) {
                fprintf(stderr, "\t%p\n", clazz->_data);
            }
            void** start = (void**) (((char*) clazz) + offsetof(Class, data));
            void** end = (void**) (((char*) start) + clazz->classRefCount * sizeof(Object*));
            dumpRefs(start, end);
        } else if (CLASS_IS_ARRAY(obj->clazz)) {
            fprintf(stderr, "%p (array of type %s of length %d elements)\n", obj, obj->clazz->name, ((Array*) obj)->length);
            if (!CLASS_IS_PRIMITIVE(obj->clazz->componentType)) {
                ObjectArray* array = (ObjectArray*) obj;
                void** start = (void**) (((char*) array) + offsetof(ObjectArray, values));
                void** end = (void**) (((char*) start) + sizeof(Object*) * array->length);
                dumpRefs(start, end);
            }
        } else {
            Class* clazz = obj->clazz;
            fprintf(stderr, "%p (object of type %s of size %d bytes)\n", obj, clazz->name, clazz->instanceDataSize);
            while (clazz != NULL) {
                void** start = (void**) (((char*) obj) + clazz->instanceDataOffset);
                void** end = (void**) (((char*) start) + clazz->instanceRefCount * sizeof(Object*));
                if (clazz == java_lang_ref_Reference) {
                    void** referent_start = (void**) (((char*) obj) + java_lang_ref_Reference_referent->offset);
                    void** referent_end = (void**) (((char*) referent_start) + sizeof(Object*));
                    if (*referent_start) {
                        fprintf(stderr, "\t%p (weak)\n", *referent_start);
                    }
                    dumpRefs(start, referent_start);
                    dumpRefs(referent_end, end);
                } else {
                    dumpRefs(start, end);
                }
                clazz = clazz->superclass;
            }
        }
    }
}

void gcHeapDump() {
    GC_apply_to_each_live_object(heapDumpCallback, NULL);
}

jboolean initGC(Options* options) {
    GC_set_no_dls(1);
    GC_set_java_finalization(1);
    GC_INIT();
    if (options->maxHeapSize > 0) {
        GC_set_max_heap_size(options->maxHeapSize);
    }
    if (options->initialHeapSize > 0) {
        size_t now = GC_get_heap_size();
        if (options->initialHeapSize > now) {
            GC_expand_hp(options->initialHeapSize - now);
        }
    }

    objectGCKind = GC_new_kind(GC_new_free_list(), GC_MAKE_PROC(GC_new_proc(markObject), 0), 0, 1);
    largeArrayGCKind = GC_new_kind(GC_new_free_list(), GC_DS_LENGTH, 1, 1);
    atomicObjectGCKind = GC_new_kind(GC_new_free_list(), GC_DS_LENGTH, 0, 1);
    referentEntryGCKind = gcNewDirectBitmapKind(REFERENT_ENTRY_GC_BITMAP);

    if (rvmInitMutex(&referentsLock) != 0) {
        return FALSE;
    }
    if (rvmInitMutex(&gcRootsLock) != 0) {
        return FALSE;
    }

    GC_set_warn_proc(gcWarnProc);
    GC_allow_register_threads();

    return TRUE;
}

void gcRegisterCurrentThread() {
    struct GC_stack_base stackBase;
    if (!GC_thread_is_registered()) {
        assert(GC_get_stack_base(&stackBase) == GC_SUCCESS);
        assert(GC_register_my_thread(&stackBase) == GC_SUCCESS);
    }
}

void gcUnregisterCurrentThread() {
    if (GC_thread_is_registered()) {
        GC_unregister_my_thread();
    }
}

void gcAddRoot(void* ptr) {
    GC_add_roots(ptr, ptr + sizeof(void*));
}

uint32_t gcNewDirectBitmapKind(uint32_t bitmap) {
    assert((bitmap & GC_DS_TAGS) == 0);
    return GC_new_kind(GC_new_free_list(), bitmap | GC_DS_BITMAP, 0, 1);
}

static void* gcAllocateKind(size_t size, uint32_t kind) {
    void* m = GC_generic_malloc(size, kind);
    if (!m) {
        // Force GC and try again
        GC_gcollect();
        m = GC_generic_malloc(size, kind);
    }
    return m;
}
void* gcAllocate(size_t size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        // Force GC and try again
        GC_gcollect();
        m = GC_MALLOC(size);
    }
    return m;
}
void* gcAllocateUncollectable(size_t size) {
    void* m = GC_MALLOC_UNCOLLECTABLE(size);
    if (!m) {
        // Force GC and try again
        GC_gcollect();
        m = GC_MALLOC_UNCOLLECTABLE(size);
    }
    return m;
}
void* gcAllocateAtomic(size_t size) {
    void* m = GC_MALLOC_ATOMIC(size);
    if (!m) {
        // Force GC and try again
        GC_gcollect();
        m = GC_MALLOC_ATOMIC(size);
    }
    if (m) {
        memset(m, 0, size);
    }
    return m;
}
void* gcAllocateAtomicUncollectable(size_t size) {
    void* m = GC_MALLOC_ATOMIC_UNCOLLECTABLE(size);
    if (!m) {
        // Force GC and try again
        GC_gcollect();
        m = GC_MALLOC_ATOMIC_UNCOLLECTABLE(size);
    }
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
        // Run all cleanup handlers registered for the object
        CleanupHandlerList* l = referentEntry->cleanupHandlers;
        while (l) {
            l->handler(env, obj);
            // Discard any exception thrown by the cleanup handler
            rvmExceptionClear(env);
            l = l->next;
        }
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
    // When attaching a thread (except the main thread) there's a slight chance that the call to rvmCreateEnv()
    // first triggers a GC. If there are finalize objects this function will be called with no Env associated 
    // with the current thread. In such cases we reregister the object for finalization and it will be finalized later.
    if (env) {
        finalizeObject(env, obj);
    } else {
        GC_REGISTER_FINALIZER_NO_ORDER(obj, _finalizeObject, NULL, NULL, NULL);
    }
}

void rvmRegisterFinalizer(Env* env, Object* obj) {
    // Call java.lang.FinalizerReference.add(obj)
    // A FinalizerReference will be created for obj and that reference will be registered using rvmRegisterReference().
    rvmCallVoidClassMethod(env, java_lang_ref_FinalizerReference, java_lang_ref_FinalizerReference_add, obj);
}

/**
 * Returns the ReferentEntry for the specified object or creates one and adds
 * it to the referents hash if none exists. referentsLock MUST be held.
 */
static ReferentEntry* getReferentEntryForObject(Env* env, Object* o) {
    void* key = (void*) GC_HIDE_POINTER(o); // Hide the pointer from the GC so that the key doesn't prevent the object from being GCed.
    ReferentEntry* referentEntry;
    HASH_FIND_PTR(referents, &key, referentEntry);
    if (!referentEntry) {
        // Object is not in the hashtable. Add it.
        referentEntry = allocateMemoryOfKind(env, sizeof(ReferentEntry), referentEntryGCKind);
        if (!referentEntry) return NULL; // OOM thrown
        referentEntry->key = key;
        HASH_ADD_PTR(referents, key, referentEntry);
    }
    return referentEntry;
}

void registerCleanupHandler(Env* env, Object* object, CleanupHandler handler) {
    rvmLockMutex(&referentsLock);
    CleanupHandlerList* l = rvmAllocateMemory(env, sizeof(CleanupHandlerList));
    if (!l) goto done; // OOM thrown
    l->handler = handler;
    ReferentEntry* referentEntry = getReferentEntryForObject(env, object);
    if (!referentEntry) goto done;
    // Add the handler to the object's list of cleanup handlers
    LL_PREPEND(referentEntry->cleanupHandlers, l);
    // Register the referent for finalization
    GC_REGISTER_FINALIZER_NO_ORDER(object, _finalizeObject, NULL, NULL, NULL);

done:
    rvmUnlockMutex(&referentsLock);
}

void rvmRegisterReference(Env* env, Object* reference, Object* referent) {
    if (referent) {
        // Add 'reference' to the references list for 'referent' in the referents hashtable
        rvmLockMutex(&referentsLock);

        ReferenceList* l = rvmAllocateMemory(env, sizeof(ReferenceList));
        if (!l) goto done; // OOM thrown
        l->reference = reference;
        ReferentEntry* referentEntry = getReferentEntryForObject(env, referent);
        if (!referentEntry) goto done;
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

    gcAddRoot(&referents);

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
    java_nio_MemoryBlock = rvmFindClassUsingLoader(env, "java/nio/MemoryBlock", NULL);
    if (!java_nio_MemoryBlock) return FALSE;
    java_nio_MemoryBlock_address = rvmGetInstanceField(env, java_nio_MemoryBlock, "address", "I");
    if (!java_nio_MemoryBlock_address) return FALSE;

    criticalOutOfMemoryError = rvmAllocateMemoryForObject(env, java_lang_OutOfMemoryError);
    if (!criticalOutOfMemoryError) return FALSE;
    criticalOutOfMemoryError->clazz = java_lang_OutOfMemoryError;
    if (!rvmAddObjectGCRoot(env, criticalOutOfMemoryError)) return FALSE;

    return TRUE;
}

Class* rvmAllocateMemoryForClass(Env* env, jint classDataSize) {
    Class* m = (Class*) gcAllocateKind(classDataSize, objectGCKind);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

Object* rvmAllocateMemoryForObject(Env* env, Class* clazz) {
    Object* m = NULL;
    if (CLASS_IS_FINALIZABLE(clazz) || CLASS_IS_REFERENCE(clazz) 
        || (clazz->superclass && clazz->superclass == org_robovm_rt_bro_Struct)
        || (clazz->superclass && clazz->superclass == java_nio_MemoryBlock)
        || (clazz == java_nio_MemoryBlock)) {

        // These types of objects must be marked specially. We could probably
        // do this using GC bitmap descriptors instead. Also instances of
        // java.lang.Throwable must be marked specially but it has at least 1
        // reference field and will thus not be allocated atomically.

        m = (Object*) gcAllocateKind(clazz->instanceDataSize, objectGCKind);
    } else if (CLASS_IS_REF_FREE(clazz)) {
        // Objects with 0 instance reference fields contain no pointers except for the Class
        // pointer and possibly a fat monitor. Those are allocated uncollectably
        // and will be reachable even if we alocate this atomically.
        m = (Object*) gcAllocateKind(clazz->instanceDataSize, atomicObjectGCKind);
    } else {
        // TODO: Use GC bitmap descriptors for small Objects.
        m = (Object*) gcAllocateKind(clazz->instanceDataSize, objectGCKind);
    }
    if (!m) {
        if (clazz == java_lang_OutOfMemoryError) {
            // We can't even allocate an OutOfMemoryError object. Prevent
            // infinite recursion by returning the shared criticalOutOfMemoryError
            // object.
            return criticalOutOfMemoryError;
        }
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

Array* rvmAllocateMemoryForArray(Env* env, Class* arrayClass, jint length) {
    jint elementSize = rvmGetArrayElementSize(env, arrayClass);
    if (elementSize == 0) {
        return NULL;
    }
    jlong size = (jlong) sizeof(Array) + (jlong) length * (jlong) elementSize;
    if (size > (jlong) (size_t) -1) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    Array* m = NULL;
    if (CLASS_IS_PRIMITIVE(arrayClass->componentType)) {
        // Primitive array objects contain no pointers except for the Class
        // pointer and possibly a fat monitor. Those are allocated uncollectably
        // and will be reachable even if we alocate this atomically.
        m = (Array*) gcAllocateKind((size_t) size, atomicObjectGCKind);
    } else if (length < 30) {
        // TODO: Use GC bitmap descriptor for small Object arrays.
        m = (Array*) gcAllocateKind((size_t) size, objectGCKind);
    } else {
        // Large Object array. Conservatively scanned. Only the lock (if thin) 
        // and the length fields could become a problem if they look like 
        // pointers into the heap.
        m = (Array*) gcAllocateKind((size_t) size, largeArrayGCKind);
    }
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* allocateMemoryOfKind(Env* env, size_t size, uint32_t kind) {
    void* m = gcAllocateKind(size, kind);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
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

void* rvmAllocateMemoryAtomicUncollectable(Env* env, size_t size) {
    void* m = gcAllocateAtomicUncollectable(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

static jboolean addObjectGCRoot(Env* env, void* ptr, void*** roots, jint* count, jint* size, 
        jint initialSize) {

    jint index = *count;
    if (index >= *size) {
        jint newSize = *size > 0 ? (*size << 1) : initialSize;
        void** tmp = rvmAllocateMemoryUncollectable(env, newSize * sizeof(void*));
        if (!tmp) {
            return FALSE;
        }
        TRACEF("Object GC roots grown from %d to %d total entries", *size, newSize);
        if (*roots) {
            memcpy(tmp, *roots, *size * sizeof(void*));
            rvmFreeMemoryUncollectable(env, *roots);
        }
        *roots = tmp;
        *size = newSize;
    }
    (*roots)[index] = ptr;
    *count = index + 1;

    return TRUE;
}

jboolean rvmAddObjectGCRoot(Env* env, Object* object) {
    rvmLockMutex(&gcRootsLock);
    jboolean r = addObjectGCRoot(env, object, (void***) &objectGCRoots, &objectGCRootsCount, 
                    &objectGCRootsSize, OBJECTS_GC_ROOTS_INITIAL_SIZE);
    rvmUnlockMutex(&gcRootsLock);
    return r;
}

jboolean rvmIsCriticalOutOfMemoryError(Env* env, Object* throwable) {
    return throwable == criticalOutOfMemoryError;
}

void rvmFreeMemoryUncollectable(Env* env, void* m) {
    GC_FREE(m);
}

void rvmGCCollect(Env* env) {
    GC_gcollect();
}

jlong rvmGetFreeMemory(Env* env) {
    GC_word pfree_bytes;
    GC_CALL GC_get_heap_usage_safe(NULL, &pfree_bytes, NULL, NULL, NULL);
    return (jlong) pfree_bytes;
}

jlong rvmGetTotalMemory(Env* env) {
    GC_word pheap_size;
    GC_CALL GC_get_heap_usage_safe(&pheap_size, NULL, NULL, NULL, NULL);
    return (jlong) pheap_size;
}

jlong rvmGetMaxMemory(Env* env) {
    if (env->vm->options->maxHeapSize > 0) {
        return env->vm->options->maxHeapSize;
    }
    // No limit. Return Long.MAX_VALUE as specified by java.lang.Runtime.maxMemory().
    return 0x7fffffffffffffffLL;
}

void* rvmCopyMemoryAtomic(Env* env, const void* src, size_t size) {
    void* dest = rvmAllocateMemoryAtomic(env, size);
    if (!dest) return NULL;
    memcpy(dest, src, size);
    return dest;
}

void* rvmCopyMemoryAtomicZ(Env* env, const char* src) {
    return rvmCopyMemoryAtomic(env, src, strlen(src) + 1);
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
