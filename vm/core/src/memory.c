/*
 * Copyright (C) 2012 RoboVM
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

static Class* java_nio_ReadWriteDirectByteBuffer = NULL;
static Method* java_nio_ReadWriteDirectByteBuffer_init = NULL;
static InstanceField* java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress = NULL;
static Class* java_lang_ref_Reference = NULL;
static InstanceField* java_lang_ref_Reference_referent = NULL;
static Class* java_lang_ref_FinalizerReference = NULL;
static Method* java_lang_ref_FinalizerReference_add = NULL;
static VM* vm = NULL;

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

    return TRUE;
}

static void* gcAllocateKind(jint size, jint kind) {
    return GC_generic_malloc(size, kind);
}
void* gcAllocate(jint size) {
    return GC_MALLOC(size);
}
void* gcAllocateUncollectable(jint size) {
    return GC_MALLOC_UNCOLLECTABLE(size);
}
void* gcAllocateAtomic(jint size) {
    void* m = GC_MALLOC_ATOMIC(size);
    if (m) {
        memset(m, 0, size);
    }
    return m;
}

static void enqueueObjectForFinalization(GC_PTR addr, GC_PTR client_data) {
    Object* obj = (Object*) addr;
    printf("Finalize: %p (%s)\n", obj, obj->clazz->name);

/*    Env* env = NULL;
    if (rvmAttachCurrentThread(vm, &env, "Temp GC finalizer enqueuer", NULL) != JNI_OK) {
        rvmAbort("Failed to attach current thread to enqueue Object to FinalizerReference queue");
    }

    Object* throwable = rvmExceptionClear(env);
    if (throwable) {
        rvmPrintStackTrace(env, throwable);
    }
    rvmDetachCurrentThread(vm, FALSE);
*/
}

void rvmRegisterFinalizer(Env* env, Object* obj) {
    rvmCallVoidClassMethod(env, java_lang_ref_FinalizerReference, java_lang_ref_FinalizerReference_add, obj);
    if (!rvmExceptionCheck(env)) {
        GC_REGISTER_FINALIZER_NO_ORDER(obj, enqueueObjectForFinalization, NULL, NULL, NULL);
    }
}

jboolean rvmInitMemory(Env* env) {
    vm = env->vm;
    java_lang_ref_Reference = rvmFindClassUsingLoader(env, "java/lang/ref/Reference", NULL);
    if (!java_lang_ref_Reference) return FALSE;
    java_lang_ref_Reference_referent = rvmGetInstanceField(env, java_lang_ref_Reference, "referent", "Ljava/lang/Object;");
    if (!java_lang_ref_Reference_referent) return FALSE;
    java_lang_ref_FinalizerReference = rvmFindClassUsingLoader(env, "java/lang/ref/FinalizerReference", NULL);
    if (!java_lang_ref_FinalizerReference) return FALSE;
    java_lang_ref_FinalizerReference_add = rvmGetClassMethod(env, java_lang_ref_FinalizerReference, "add", "(Ljava/lang/Object;)V");
    if (!java_lang_ref_FinalizerReference_add) return FALSE;
    java_nio_ReadWriteDirectByteBuffer = rvmFindClassUsingLoader(env, "java/nio/ReadWriteDirectByteBuffer", NULL);
    if (!java_nio_ReadWriteDirectByteBuffer) return FALSE;
    java_nio_ReadWriteDirectByteBuffer_init = rvmGetInstanceMethod(env, java_nio_ReadWriteDirectByteBuffer, "<init>", "(II)V");
    if (!java_nio_ReadWriteDirectByteBuffer_init) return FALSE;
    java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress = rvmGetInstanceField(env, java_nio_ReadWriteDirectByteBuffer, "effectiveDirectAddress", "I");
    if (!java_nio_ReadWriteDirectByteBuffer_effectiveDirectAddress) return FALSE;
    return TRUE;
}

Class* rvmAllocateMemoryForClass(Env* env, jint classDataSize) {
    return gcAllocateKind(classDataSize, object_gc_kind);
}

Object* rvmAllocateMemoryForObject(Env* env, Class* clazz) {
    return gcAllocateKind(clazz->instanceDataSize, object_gc_kind);
}

Array* rvmAllocateMemoryForArray(Env* env, jint length, jint elementSize) {
    return gcAllocateKind(sizeof(Array) + length * elementSize, object_gc_kind);
}

void* rvmAllocateMemory(Env* env, jint size) {
    void* m = gcAllocate(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryUncollectable(Env* env, jint size) {
    void* m = gcAllocateUncollectable(size);
    if (!m) {
        rvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* rvmAllocateMemoryAtomic(Env* env, jint size) {
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

void* rvmCopyMemory(Env* env, const void* src, jint size) {
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
