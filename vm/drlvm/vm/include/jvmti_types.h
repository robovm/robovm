/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements. See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file
 * Definition of JVMTI interface types
 *
 * See <a
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html">specification</a>
 * for details.
 */
#ifndef _JVMTI_TYPES_H_
#define _JVMTI_TYPES_H_

#include "jni_types.h"

#ifdef __cplusplus
extern "C"
{
#endif

    /**
     * Basic types
     */
    struct ti_interface;
    struct jvmtiEnv_struct;

#ifdef __cplusplus
    /**
     * JVMTI environment definition for use in C++ sources
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#environments">specification</a>
     * for details.
     */
    typedef jvmtiEnv_struct jvmtiEnv;
#else
    /**
     * JVMTI environment definition for use in C sources
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#environments">specification</a>
     * for details.
     */
    typedef const struct ti_interface *jvmtiEnv;
#endif

    /**
     * Reference type which describes a java.lang.Thread instance
     * object in native function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jthread">specification</a>
     * for details.
     */
    typedef jobject jthread;
    /**
     * 64-bit type which defines executable positon inside of method
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jlocation">specification</a>
     * for details.
     */
    typedef jlong jlocation;
    /**
     * Reference type which describes a java.lang.ThreadGroup instance
     * object in native function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jthreadGroup">specification</a>
     * for details.
     */
    typedef jobject jthreadGroup;
    /**
     * Raw monitor ID type
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jrawMonitorID">specification</a>
     * for details.
     */
    typedef int jrawMonitorID;
    /**
     * JNI API interface table type for usage in C
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jni/spec/design.html#wp16696">specification</a>
     * for details. */
    typedef struct JNINativeInterface_ jniNativeInterface;


    /**
     * Pointer to a function which could be launched as a separate
     * agent thread
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiStartFunction">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiStartFunction)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, void *arg);

    /**
     * Error codes which JVMTI API functions may return
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ErrorSection">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_ERROR_NONE = 0,
        JVMTI_ERROR_NULL_POINTER = 100,
        JVMTI_ERROR_OUT_OF_MEMORY = 110,
        JVMTI_ERROR_ACCESS_DENIED = 111,
        JVMTI_ERROR_UNATTACHED_THREAD = 115,
        JVMTI_ERROR_INVALID_ENVIRONMENT = 116,
        JVMTI_ERROR_WRONG_PHASE = 112,
        JVMTI_ERROR_INTERNAL = 113,
        JVMTI_ERROR_INVALID_PRIORITY = 12,
        JVMTI_ERROR_THREAD_NOT_SUSPENDED = 13,
        JVMTI_ERROR_THREAD_SUSPENDED = 14,
        JVMTI_ERROR_THREAD_NOT_ALIVE = 15,
        JVMTI_ERROR_CLASS_NOT_PREPARED = 22,
        JVMTI_ERROR_NO_MORE_FRAMES = 31,
        JVMTI_ERROR_OPAQUE_FRAME = 32,
        JVMTI_ERROR_DUPLICATE = 40,
        JVMTI_ERROR_NOT_FOUND = 41,
        JVMTI_ERROR_NOT_MONITOR_OWNER = 51,
        JVMTI_ERROR_INTERRUPT = 52,
        JVMTI_ERROR_UNMODIFIABLE_CLASS = 79,
        JVMTI_ERROR_NOT_AVAILABLE = 98,
        JVMTI_ERROR_ABSENT_INFORMATION = 101,
        JVMTI_ERROR_INVALID_EVENT_TYPE = 102,
        JVMTI_ERROR_NATIVE_METHOD = 104,
        JVMTI_ERROR_INVALID_THREAD = 10,
        JVMTI_ERROR_INVALID_FIELDID = 25,
        JVMTI_ERROR_INVALID_METHODID = 23,
        JVMTI_ERROR_INVALID_LOCATION = 24,
        JVMTI_ERROR_INVALID_OBJECT = 20,
        JVMTI_ERROR_INVALID_CLASS = 21,
        JVMTI_ERROR_TYPE_MISMATCH = 34,
        JVMTI_ERROR_INVALID_SLOT = 35,
        JVMTI_ERROR_MUST_POSSESS_CAPABILITY = 99,
        JVMTI_ERROR_INVALID_THREAD_GROUP = 11,
        JVMTI_ERROR_INVALID_MONITOR = 50,
        JVMTI_ERROR_ILLEGAL_ARGUMENT = 103,
        JVMTI_ERROR_INVALID_TYPESTATE = 65,
        JVMTI_ERROR_UNSUPPORTED_VERSION = 68,
        JVMTI_ERROR_INVALID_CLASS_FORMAT = 60,
        JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION = 61,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED = 63,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED = 64,
        JVMTI_ERROR_FAILS_VERIFICATION = 62,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED = 66,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED = 67,
        JVMTI_ERROR_NAMES_DONT_MATCH = 69,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED = 70,
        JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED = 71,
        JVMTI_NYI = 666
    } jvmtiError;

    /**
     * Class status flags which <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetClassStatus">GetClassStatus</a>
     * function may return
     */
    enum
    {
        /**
         * Class bytecodes have been verified
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_VERIFIED">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_VERIFIED = 1,
        /**
         * Class preparation is complete
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_PREPARED">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_PREPARED = 2,
        /**
         * Class initialization is complete. Static initializer has been run
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_INITIALIZED">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_INITIALIZED = 4,
        /**
         * Error during initialization makes class unusable
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_ERROR">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_ERROR = 8,
        /**
         * Class is an array. If set, all other bits are zero
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_ARRAY">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_ARRAY = 16,
        /**
         * Class is a primitive class (for example,
         * <code>java.lang.Integer.TYPE</code>).  If set, all other
         * bits are zero
         *
         * See <a
         * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#JVMTI_CLASS_STATUS_PRIMITIVE">specification</a>
         * for details.
         */
        JVMTI_CLASS_STATUS_PRIMITIVE = 32
    };

    /**
     * Thread states which may be returned by <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadState">GetThreadState</a>
     * function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadState">specification</a>
     * for details.
     */
    enum
    {
        JVMTI_THREAD_STATE_ALIVE = 0x0001,
        JVMTI_THREAD_STATE_TERMINATED = 0x0002,
        JVMTI_THREAD_STATE_RUNNABLE = 0x0004,
        JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER = 0x0400,
        JVMTI_THREAD_STATE_WAITING = 0x0080,
        JVMTI_THREAD_STATE_WAITING_INDEFINITELY = 0x0010,
        JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT = 0x0020,
        JVMTI_THREAD_STATE_SLEEPING = 0x0040,
        JVMTI_THREAD_STATE_IN_OBJECT_WAIT = 0x0100,
        JVMTI_THREAD_STATE_PARKED = 0x0200,
        JVMTI_THREAD_STATE_SUSPENDED = 0x100000,
        JVMTI_THREAD_STATE_INTERRUPTED = 0x200000,
        JVMTI_THREAD_STATE_IN_NATIVE = 0x400000,
        JVMTI_THREAD_STATE_VENDOR_1 = 0x10000000,
        JVMTI_THREAD_STATE_VENDOR_2 = 0x20000000,
        JVMTI_THREAD_STATE_VENDOR_3 = 0x40000000
    };

    /**
     * Thread states masks which may be used to filter out thread
     * states in bit mask returned by <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadState">specification</a>
     * function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadState">specification</a>
     * for details.
     */
    enum
    {
        JVMTI_JAVA_LANG_THREAD_STATE_MASK =
        JVMTI_THREAD_STATE_TERMINATED |
        JVMTI_THREAD_STATE_ALIVE |
        JVMTI_THREAD_STATE_RUNNABLE |
        JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER |
        JVMTI_THREAD_STATE_WAITING |
        JVMTI_THREAD_STATE_WAITING_INDEFINITELY |
        JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT,
        JVMTI_JAVA_LANG_THREAD_STATE_NEW = 0,
        JVMTI_JAVA_LANG_THREAD_STATE_TERMINATED = JVMTI_THREAD_STATE_TERMINATED,
        JVMTI_JAVA_LANG_THREAD_STATE_RUNNABLE =
        JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_RUNNABLE,
        JVMTI_JAVA_LANG_THREAD_STATE_BLOCKED =
        JVMTI_THREAD_STATE_ALIVE | JVMTI_THREAD_STATE_BLOCKED_ON_MONITOR_ENTER,
        JVMTI_JAVA_LANG_THREAD_STATE_WAITING =
        JVMTI_THREAD_STATE_ALIVE |
        JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_INDEFINITELY,
        JVMTI_JAVA_LANG_THREAD_STATE_TIMED_WAITING =
        JVMTI_THREAD_STATE_ALIVE |
        JVMTI_THREAD_STATE_WAITING | JVMTI_THREAD_STATE_WAITING_WITH_TIMEOUT
    };

    /**
     * Thread priorities
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiThreadPriority">specification</a>
     * for details.
     */
    enum
    {
        JVMTI_THREAD_MIN_PRIORITY = 1,
        JVMTI_THREAD_NORM_PRIORITY = 5,
        JVMTI_THREAD_MAX_PRIORITY = 10
    };

    /**
     * Thread information structure returned by <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadInfo">GetThreadInfo</a>
     * function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiThreadInfo">specification</a>
     * for details.
     */
    typedef struct
    {
        char *name;
        jint priority;
        jboolean is_daemon;
        jthreadGroup thread_group;
        jobject context_class_loader;
    } jvmtiThreadInfo;

    /**
     * Thread group information structure returned by <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GetThreadGroupInfo">GetThreadGroupInfo</a>
     * function
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiThreadGroupInfo">specification</a>
     * for details.
     */
    typedef struct
    {
        jthreadGroup parent;
        char *name;
        jint max_priority;
        jboolean is_daemon;
    } jvmtiThreadGroupInfo;

    /**
     * Single stack frame information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiFrameInfo">specification</a>
     * for details.
     */
    typedef struct
    {
        jmethodID method;
        jlocation location;
    } jvmtiFrameInfo;

    /**
     * Single thread stack information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiStackInfo">specification</a>
     * for details.
     */
    typedef struct
    {
        jthread thread;
        jint state;
        jvmtiFrameInfo *frame_buffer;
        jint frame_count;
    } jvmtiStackInfo;

    /**
     * Event numbers. Custom events could be added after
     * <code>JVMTI_MAX_EVENT_TYPE_VAL</code>.
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#EventIndex">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_MIN_EVENT_TYPE_VAL = 50,
        JVMTI_EVENT_VM_INIT = 50,
        JVMTI_EVENT_VM_DEATH = 51,
        JVMTI_EVENT_THREAD_START = 52,
        JVMTI_EVENT_THREAD_END = 53,
        JVMTI_EVENT_CLASS_FILE_LOAD_HOOK = 54,
        JVMTI_EVENT_CLASS_LOAD = 55,
        JVMTI_EVENT_CLASS_PREPARE = 56,
        JVMTI_EVENT_VM_START = 57,
        JVMTI_EVENT_EXCEPTION = 58,
        JVMTI_EVENT_EXCEPTION_CATCH = 59,
        JVMTI_EVENT_SINGLE_STEP = 60,
        JVMTI_EVENT_FRAME_POP = 61,
        JVMTI_EVENT_BREAKPOINT = 62,
        JVMTI_EVENT_FIELD_ACCESS = 63,
        JVMTI_EVENT_FIELD_MODIFICATION = 64,
        JVMTI_EVENT_METHOD_ENTRY = 65,
        JVMTI_EVENT_METHOD_EXIT = 66,
        JVMTI_EVENT_NATIVE_METHOD_BIND = 67,
        JVMTI_EVENT_COMPILED_METHOD_LOAD = 68,
        JVMTI_EVENT_COMPILED_METHOD_UNLOAD = 69,
        JVMTI_EVENT_DYNAMIC_CODE_GENERATED = 70,
        JVMTI_EVENT_DATA_DUMP_REQUEST = 71,
        JVMTI_EVENT_DATA_RESET_REQUEST = 72,
        JVMTI_EVENT_MONITOR_WAIT = 73,
        JVMTI_EVENT_MONITOR_WAITED = 74,
        JVMTI_EVENT_MONITOR_CONTENDED_ENTER = 75,
        JVMTI_EVENT_MONITOR_CONTENDED_ENTERED = 76,
        JVMTI_EVENT_GARBAGE_COLLECTION_START = 81,
        JVMTI_EVENT_GARBAGE_COLLECTION_FINISH = 82,
        JVMTI_EVENT_OBJECT_FREE = 83,
        JVMTI_EVENT_VM_OBJECT_ALLOC = 84,
        JVMTI_MAX_EVENT_TYPE_VAL = 84
    } jvmtiEvent;

    /**
     * Total number of events specified in JVMTI interface
     */
#define TOTAL_EVENT_TYPE_NUM (JVMTI_MAX_EVENT_TYPE_VAL - JVMTI_MIN_EVENT_TYPE_VAL + 1)

    /**
     * Root types used in JVMTI <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a>
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiHeapRootKind">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_HEAP_ROOT_JNI_GLOBAL = 1,
        JVMTI_HEAP_ROOT_SYSTEM_CLASS = 2,
        JVMTI_HEAP_ROOT_MONITOR = 3,
        JVMTI_HEAP_ROOT_STACK_LOCAL = 4,
        JVMTI_HEAP_ROOT_JNI_LOCAL = 5,
        JVMTI_HEAP_ROOT_THREAD = 6,
        JVMTI_HEAP_ROOT_OTHER = 7
    } jvmtiHeapRootKind;

    /**
     * Generic iteration control used in JVMTI <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a>
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiIterationControl">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_ITERATION_CONTINUE = 1,
        JVMTI_ITERATION_IGNORE = 2,
        JVMTI_ITERATION_ABORT = 0
    } jvmtiIterationControl;

    /**
     * Describes enumerated references in JVMTI <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a>
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiObjectReferenceKind">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_REFERENCE_CLASS = 1,
        JVMTI_REFERENCE_FIELD = 2,
        JVMTI_REFERENCE_ARRAY_ELEMENT = 3,
        JVMTI_REFERENCE_CLASS_LOADER = 4,
        JVMTI_REFERENCE_SIGNERS = 5,
        JVMTI_REFERENCE_PROTECTION_DOMAIN = 6,
        JVMTI_REFERENCE_INTERFACE = 7,
        JVMTI_REFERENCE_STATIC_FIELD = 8,
        JVMTI_REFERENCE_CONSTANT_POOL = 9
    } jvmtiObjectReferenceKind;

    /**
     * Tagged objects filters used in JVMTI <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a>
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiHeapObjectFilter">specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_HEAP_OBJECT_TAGGED = 1,
        JVMTI_HEAP_OBJECT_UNTAGGED = 2,
        JVMTI_HEAP_OBJECT_EITHER = 3
    } jvmtiHeapObjectFilter;

    /**
     * Monitor usage information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiMonitorUsage">specification</a>
     * for details.
     */
    typedef struct
    {
        jthread owner;
        jint entry_count;
        jint waiter_count;
        jthread *waiters;
        jint notify_waiter_count;
        jthread *notify_waiters;
    } jvmtiMonitorUsage;

    /**
     * Line numbers information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiLineNumberEntry">specification</a>
     * for details.
     */
    typedef struct
    {
        jlocation start_location;
        jint line_number;
    } jvmtiLineNumberEntry;

    /**
     * Local variables information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiLocalVariableEntry">specification</a>
     * for details.
     */
    typedef struct
    {
        jlocation start_location;
        jint length;
        char *name;
        char *signature;
        char *generic_signature;
        jint slot;
    } jvmtiLocalVariableEntry;

    /**
     * Callback function type for VMInit event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#VMInit">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventVMInit)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread);

    /**
     * Callback function type for SingleStep event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#SingleStep">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventSingleStep)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jlocation location);

    /**
     * Callback function type for Breakpoint event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Breakpoint">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventBreakpoint)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jlocation location);

    /**
     * Callback function type for FieldAccess event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#FieldAccess">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventFieldAccess)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jlocation location, jclass field_clazz,
        jobject object, jfieldID field);

    /**
     * Callback function type for FieldModification event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#FieldModification">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventFieldModification)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jlocation location, jclass field_clazz,
        jobject object, jfieldID field, char signature_type, jvalue new_value);

    /**
     * Callback function type for FramePop event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#FramePop">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventFramePop)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jboolean was_popped_by_exception);

    /**
     * Callback function type for MethodEntry event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MethodEntry">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMethodEntry)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method);

    /**
     * Callback function type for MethodExit event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MethodExit">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMethodExit)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jboolean was_popped_by_exception, jvalue return_value);

    /**
     * Callback function type for NativeMethodBind event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#NativeMethodBind">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventNativeMethodBind)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, void *address, void **new_address_ptr);

    /**
     * Callback function type for Exception event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Exception">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventException)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jmethodID method, jlocation location, jobject exception,
        jmethodID catch_method, jlocation catch_location);

    /**
     * Callback function type for ExceptionCatch event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ExceptionCatch">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventExceptionCatch)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env,
        jthread thread, jmethodID method, jlocation location, jobject exception);

    /**
     * Callback function type for ThreadStart event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ThreadStart">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventThreadStart)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread);

    /**
     * Callback function type for ThreadEnd event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ThreadEnd">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventThreadEnd)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread);

    /**
     * Callback function type for ClassLoad event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ClassLoad">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventClassLoad)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread, jclass clazz);

    /**
     * Callback function type for ClassPrepare event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ClassPrepare">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventClassPrepare)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread, jclass clazz);

    /**
     * Callback function type for ClassFileLoadHook event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ClassFileLoadHook">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventClassFileLoadHook)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env,
        jclass class_being_redefined, jobject loader,
        const char *name, jobject protection_domain,
        jint class_data_len, const unsigned char *class_data,
        jint * new_class_data_len, unsigned char **new_class_data);

    /**
     * Callback function type for VMStart event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#VMStart">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventVMStart)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env);

    /**
     * Callback function type for VMDeath event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#VMDeath">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventVMDeath)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env);

    /**
     * Native to bytecode location mapping information
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiAddrLocationMap>specification</a>
     * for details.
     */
    typedef struct
    {
        const void *start_address;
        jlocation location;
    } jvmtiAddrLocationMap;

    /**
     * Callback function type for CompiledMethodLoad event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#CompiledMethodLoad">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventCompiledMethodLoad)
        (jvmtiEnv * jvmti_env, jmethodID method, jint code_size,
        const void *code_addr, jint map_length,
        const jvmtiAddrLocationMap * almap, const void *compile_info);

    /**
     * Callback function type for CompiledMethodUnload event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#CompiledMethodUnload">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventCompiledMethodUnload)
        (jvmtiEnv * jvmti_env, jmethodID method, const void *code_addr);

    /**
     * Callback function type for DynamicCodeGenerated event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#DynamicCodeGenerated">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventDynamicCodeGenerated)
        (jvmtiEnv * jvmti_env,
        const char *name, const void *address, jint length);

    /**
     * Callback function type for DataDumpRequest event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#DataDumpRequest">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventDataDumpRequest) (jvmtiEnv * jvmti_env);

    /**
     * Callback function type for DataResetRequest event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#DataResetRequest">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventDataResetRequest) (jvmtiEnv * jvmti_env);

    /**
     * Callback function type for MonitorContendedEnter event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MonitorContendedEnter">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMonitorContendedEnter)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread, jobject object);

    /**
     * Callback function type for MonitorContendedEntered event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MonitorContendedEntered">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMonitorContendedEntered)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread, jobject object);

    /**
     * Callback function type for MonitorWait event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MonitorWait">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMonitorWait)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jobject object, jlong timeout);

    /**
     * Callback function type for MonitorWaited event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#MonitorWaited">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventMonitorWaited)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jobject object, jboolean timed_out);

    /**
     * Callback function type for VMObjectAlloc event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#VMObjectAlloc">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventVMObjectAlloc)
        (jvmtiEnv * jvmti_env, JNIEnv * jni_env, jthread thread,
        jobject object, jclass object_clazz, jlong size);

    /**
     * Callback function type for ObjectFree event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#ObjectFree">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventObjectFree)
        (jvmtiEnv * jvmti_env, jlong tag);

    /**
     * Callback function type for GarbageCollectionStart event
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GarbageCollectionStart">specification</a>
     * for details.
     */
    typedef void (JNICALL * jvmtiEventGarbageCollectionStart)
        (jvmtiEnv * jvmti_env);

    /**
     * Callback function type for GarbageCollectionFinish event
     *
     * See <a href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#GarbageCollectionFinish">specification</a> for details.
     */
    typedef void (JNICALL * jvmtiEventGarbageCollectionFinish)
        (jvmtiEnv * jvmti_env);

    /**
     * Callback type for reserved event types
     */
    typedef void *jvmtiEventReserved;

    /**
     * Event callbacks table
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiEventCallbacks>specification</a>
     * for details.
     */
    typedef struct
    {
        jvmtiEventVMInit VMInit;
        jvmtiEventVMDeath VMDeath;
        jvmtiEventThreadStart ThreadStart;
        jvmtiEventThreadEnd ThreadEnd;
        jvmtiEventClassFileLoadHook ClassFileLoadHook;
        jvmtiEventClassLoad ClassLoad;
        jvmtiEventClassPrepare ClassPrepare;
        jvmtiEventVMStart VMStart;
        jvmtiEventException Exception;
        jvmtiEventExceptionCatch ExceptionCatch;
        jvmtiEventSingleStep SingleStep;
        jvmtiEventFramePop FramePop;
        jvmtiEventBreakpoint Breakpoint;
        jvmtiEventFieldAccess FieldAccess;
        jvmtiEventFieldModification FieldModification;
        jvmtiEventMethodEntry MethodEntry;
        jvmtiEventMethodExit MethodExit;
        jvmtiEventNativeMethodBind NativeMethodBind;
        jvmtiEventCompiledMethodLoad CompiledMethodLoad;
        jvmtiEventCompiledMethodUnload CompiledMethodUnload;
        jvmtiEventDynamicCodeGenerated DynamicCodeGenerated;
        jvmtiEventDataDumpRequest DataDumpRequest;
        jvmtiEventDataResetRequest DataResetRequest;
        jvmtiEventMonitorWait MonitorWait;
        jvmtiEventMonitorWaited MonitorWaited;
        jvmtiEventMonitorContendedEnter MonitorContendedEnter;
        jvmtiEventMonitorContendedEntered MonitorContendedEntered;
        jvmtiEventReserved reserved77;
        jvmtiEventReserved reserved78;
        jvmtiEventReserved reserved79;
        jvmtiEventReserved reserved80;
        jvmtiEventGarbageCollectionStart GarbageCollectionStart;
        jvmtiEventGarbageCollectionFinish GarbageCollectionFinish;
        jvmtiEventObjectFree ObjectFree;
        jvmtiEventVMObjectAlloc VMObjectAlloc;
    } jvmtiEventCallbacks;


    /**
     * Arguments kinds for extension events callbacks
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiParamKind>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_KIND_IN = 91,
        JVMTI_KIND_IN_PTR = 92,
        JVMTI_KIND_IN_BUF = 93,
        JVMTI_KIND_ALLOC_BUF = 94,
        JVMTI_KIND_ALLOC_ALLOC_BUF = 95,
        JVMTI_KIND_OUT = 96,
        JVMTI_KIND_OUT_BUF = 97
    } jvmtiParamKind;

    /**
     * Arguments types for extension events callbacks
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiParamKind>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_TYPE_JBYTE = 101,
        JVMTI_TYPE_JCHAR = 102,
        JVMTI_TYPE_JSHORT = 103,
        JVMTI_TYPE_JINT = 104,
        JVMTI_TYPE_JLONG = 105,
        JVMTI_TYPE_JFLOAT = 106,
        JVMTI_TYPE_JDOUBLE = 107,
        JVMTI_TYPE_JBOOLEAN = 108,
        JVMTI_TYPE_JOBJECT = 109,
        JVMTI_TYPE_JTHREAD = 110,
        JVMTI_TYPE_JCLASS = 111,
        JVMTI_TYPE_JVALUE = 112,
        JVMTI_TYPE_JFIELDID = 113,
        JVMTI_TYPE_JMETHODID = 114,
        JVMTI_TYPE_CCHAR = 115,
        JVMTI_TYPE_CVOID = 116,
        JVMTI_TYPE_JNIENV = 117
    } jvmtiParamTypes;

    /**
     * Arguments information for extension events callbacks
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiParamInfo>specification</a>
     * for details.
     */
    typedef struct
    {
        char *name;
        jvmtiParamKind kind;
        jvmtiParamTypes base_type;
        jboolean null_ok;
    } jvmtiParamInfo;

    /**
     * Callback function type for extension events
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiExtensionFunction>specification</a>
     * for details.
     */
    typedef jvmtiError
        (JNICALL * jvmtiExtensionFunction) (jvmtiEnv * jvmti_env, ...);

    /**
     * Event generation mode
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiEventMode>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_ENABLE = 1,
        JVMTI_DISABLE = 0
    } jvmtiEventMode;

    /**
     * Extenstion functions information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiExtensionFunctionInfo>specification</a>
     * for details.
     */
    typedef struct
    {
        jvmtiExtensionFunction func;
        char *id;
        char *short_description;
        jint param_count;
        jvmtiParamInfo *params;
        jint error_count;
        jvmtiError *errors;
    } jvmtiExtensionFunctionInfo;

    /**
     * Extension events information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiExtensionEventInfo>specification</a>
     * for details.
     */
    typedef struct
    {
        jint extension_event_index;
        char *id;
        char *short_description;
        jint param_count;
        jvmtiParamInfo *params;
    } jvmtiExtensionEventInfo;

    /**
     * Bit table of JVMTI capabilities supported by VM
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiCapabilities>specification</a>
     * for details.
     */
    typedef struct
    {
        unsigned int can_tag_objects:1;
        unsigned int can_generate_field_modification_events:1;
        unsigned int can_generate_field_access_events:1;
        unsigned int can_get_bytecodes:1;
        unsigned int can_get_synthetic_attribute:1;
        unsigned int can_get_owned_monitor_info:1;
        unsigned int can_get_current_contended_monitor:1;
        unsigned int can_get_monitor_info:1;
        unsigned int can_pop_frame:1;
        unsigned int can_redefine_classes:1;
        unsigned int can_signal_thread:1;
        unsigned int can_get_source_file_name:1;
        unsigned int can_get_line_numbers:1;
        unsigned int can_get_source_debug_extension:1;
        unsigned int can_access_local_variables:1;
        unsigned int can_maintain_original_method_order:1;
        unsigned int can_generate_single_step_events:1;
        unsigned int can_generate_exception_events:1;
        unsigned int can_generate_frame_pop_events:1;
        unsigned int can_generate_breakpoint_events:1;
        unsigned int can_suspend:1;
        unsigned int can_redefine_any_class:1;
        unsigned int can_get_current_thread_cpu_time:1;
        unsigned int can_get_thread_cpu_time:1;
        unsigned int can_generate_method_entry_events:1;
        unsigned int can_generate_method_exit_events:1;
        unsigned int can_generate_all_class_hook_events:1;
        unsigned int can_generate_compiled_method_load_events:1;
        unsigned int can_generate_monitor_events:1;
        unsigned int can_generate_vm_object_alloc_events:1;
        unsigned int can_generate_native_method_bind_events:1;
        unsigned int can_generate_garbage_collection_events:1;
        unsigned int can_generate_object_free_events:1;
        unsigned int:15;
        unsigned int:16;
        unsigned int:16;
        unsigned int:16;
        unsigned int:16;
        unsigned int:16;
    } jvmtiCapabilities;

    /**
     * Timer type identifier
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiTimerKind>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_TIMER_USER_CPU = 30,
        JVMTI_TIMER_TOTAL_CPU = 31,
        JVMTI_TIMER_ELAPSED = 32
    } jvmtiTimerKind;

    /**
     * Timer information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiTimerInfo>specification</a>
     * for details.
     */
    typedef struct
    {
        jlong max_value;
        jboolean may_skip_forward;
        jboolean may_skip_backward;
        jvmtiTimerKind kind;
        jlong reserved1;
        jlong reserved2;
    } jvmtiTimerInfo;

    /**
     * VM operation phase identifier
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiPhase>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_PHASE_ONLOAD = 1,
        JVMTI_PHASE_PRIMORDIAL = 2,
        JVMTI_PHASE_START = 6,
        JVMTI_PHASE_LIVE = 4,
        JVMTI_PHASE_DEAD = 8
    } jvmtiPhase;

    /**
     * VM verbosity level
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiVerboseFlag>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_VERBOSE_OTHER = 0,
        JVMTI_VERBOSE_GC = 1,
        JVMTI_VERBOSE_CLASS = 2,
        JVMTI_VERBOSE_JNI = 4
    } jvmtiVerboseFlag;

    /**
     * JLocation format identifier
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiJlocationFormat>specification</a>
     * for details.
     */
    typedef enum
    {
        JVMTI_JLOCATION_JVMBCI = 1,
        JVMTI_JLOCATION_MACHINEPC = 2,
        JVMTI_JLOCATION_OTHER = 0
    } jvmtiJlocationFormat;

    /**
     * Callback function type for <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a> callback which is called for all objects
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiHeapObjectCallback>specification</a>
     * for details.
     */
    typedef jvmtiIterationControl
        (JNICALL * jvmtiHeapObjectCallback)
        (jlong class_tag, jlong size, jlong * tag_ptr, void *user_data);

    /**
     * Callback function type for <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a> callback which is called for all root set
     * references
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiHeapRootCallback>specification</a>
     * for details.
     */
    typedef jvmtiIterationControl
        (JNICALL * jvmtiHeapRootCallback)
        (jvmtiHeapRootKind root_kind, jlong class_tag, jlong size,
        jlong * tag_ptr, void *user_data);

    /**
     * Callback function type for <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a> callback which is called for objects referenced
     * from thread stacks
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiStackReferenceCallback>specification</a>
     * for details.
     */
    typedef jvmtiIterationControl
        (JNICALL * jvmtiStackReferenceCallback)
        (jvmtiHeapRootKind root_kind, jlong class_tag, jlong size,
        jlong * tag_ptr, jlong thread_tag, jint depth, jmethodID method,
        jint slot, void *user_data);

    /**
     * Callback function type for <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#Heap">heap
     * iteration</a> callback which is called for objects referenced
     * from object fields
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiObjectReferenceCallback>specification</a>
     * for details.
     */
    typedef jvmtiIterationControl
        (JNICALL * jvmtiObjectReferenceCallback)
        (jvmtiObjectReferenceKind reference_kind, jlong class_tag, jlong size,
        jlong * tag_ptr, jlong referrer_tag, jint referrer_index,
        void *user_data);

    /**
     * Class redefinition information structure
     *
     * See <a
     * href="http://java.sun.com/j2se/1.5.0/docs/guide/jvmti/jvmti.html#jvmtiClassDefinition>specification</a>
     * for details.
     */
    typedef struct
    {
        jclass klass;
        jint class_byte_count;
        const unsigned char *class_bytes;
    } jvmtiClassDefinition;

    typedef void (JNICALL * jvmtiExtensionEvent) (jvmtiEnv * jvmti_env, ...);

#ifdef __cplusplus
}               /* extern "C" */
#endif


#endif /* _JVMTI_TYPES_H_ */
