#include <nullvm.h>
#include <stddef.h>
#include <unwind.h>

#define EXCEPTION_CLASS 0x4A4A4A4A4A4A4A4A // "JJJJJJJJ"

typedef struct UnwindInfo {
    struct _Unwind_Exception exception_info;
    Object* throwable;
    _Unwind_Ptr landing_pad;
} UnwindInfo;

extern _Unwind_Reason_Code __gcc_personality_v0(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

_Unwind_Reason_Code _nvmPersonality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {

    UnwindInfo* info = (UnwindInfo*) exception_info;
    if (actions & _UA_SEARCH_PHASE) {
        _Unwind_Ptr saved_ip = _Unwind_GetIP(context);
        _Unwind_Reason_Code urc = __gcc_personality_v0(version, _UA_CLEANUP_PHASE, exception_class, exception_info, context);
        if (urc == _URC_INSTALL_CONTEXT) {
            info->landing_pad = _Unwind_GetIP(context);
            _Unwind_SetIP(context, saved_ip);
            return _URC_HANDLER_FOUND;
        }
        return urc;
    } else if (actions & _UA_HANDLER_FRAME) {
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (0), (_Unwind_Ptr) exception_info);
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (1), 0);
        _Unwind_SetIP(context, info->landing_pad); 
        return _URC_INSTALL_CONTEXT;
    }

    return _URC_CONTINUE_UNWIND;
}

void nvmRaiseException(Env* env, Object* e) {
    UnwindInfo* u = nvmAllocateMemory(env, sizeof(UnwindInfo));
    u->exception_info.exception_class = EXCEPTION_CLASS;
    u->throwable = e;
    nvmThrow(env, e);
    _Unwind_Reason_Code urc = _Unwind_RaiseException(&u->exception_info);
    if (urc == _URC_END_OF_STACK) {
        nvmAbort("Unhandled exception: %s", e->clazz->name);
    }
    nvmAbort("Fatal error in exception handler: %d", urc);
}

jboolean nvmExceptionCheck(Env* env) {
    return env->throwable ? TRUE : FALSE;
}

Object* nvmExceptionOccurred(Env* env) {
    return env->throwable;
}

void nvmExceptionPrintStackTrace(Env* env, Object* e, FILE* f) {
    // TODO: Write the stack trace to the FILE*
    fprintf(stderr, "Exception occurred: %s\n", e->clazz->name);
}

Object* nvmExceptionClear(Env* env) {
    Object* e = env->throwable;
    env->throwable = NULL;
    return e;
}

jint nvmThrow(Env* env, Object* e) {
    // TODO: Check that e != NULL?
    env->throwable = e;
    return 0;
}

jint nvmThrowNew(Env* env, Class* clazz, char* message) {
    Method* constructor = nvmGetInstanceMethod(env, clazz, "<init>", "(Ljava/lang/String;)V");
    if (!constructor) return 1;
    Object* string = NULL;
    // TODO: Check that clazz != NULL?
    if (message) {
        string = nvmNewStringUTF(env, message, -1);
        if (!string) return 2;
    }
    Object* e = nvmNewObject(env, clazz, constructor, string);
    if (!e) return 3;
    return nvmThrow(env, e);
}

jint nvmThrowOutOfMemoryError(Env* env) {
    return nvmThrowNew(env, java_lang_OutOfMemoryError, "");
}

jint nvmThrowNoClassDefFoundError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoClassDefFoundError: a/C"
    return nvmThrowNew(env, java_lang_NoClassDefFoundError, "");
}

jint nvmThrowIllegalAccessError(Env* env) {
    return nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint nvmThrowIllegalAccessErrorField(Env* env, Class* clazz, char* name, char* desc, Class* caller) {
    // TODO: Message should look like "java.lang.IllegalAccessError: tried to access field a.A.x from class b.B"
    return nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint nvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, char* name, char* desc, Class* caller) {
    // TODO: Message should look like ?
    return nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

jint nvmThrowNoSuchFieldError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoSuchFieldError: x"
    // TODO: Cache java.lang.NoSuchFieldError at startup
    return nvmThrowNew(env, java_lang_NoSuchFieldError, "");
}

jint nvmThrowNoSuchMethodError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoSuchMethodError: x"
    return nvmThrowNew(env, java_lang_NoSuchMethodError, "");
}

jint nvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected static field a.C.x"
    return nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint nvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected non-static field a.C.x"
    return nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint nvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like ?
    return nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

jint nvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass) {
    // TODO: Message should look like "java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String"
    // TODO: Cache java.lang.ClassCastException at startup
    return nvmThrowNew(env, java_lang_ClassCastException, "");
}

jint nvmThrowNullPointerException(Env* env) {
    return nvmThrowNew(env, java_lang_NullPointerException, "");
}

jint nvmThrowAbstractMethodError(Env* env) {
    return nvmThrowNew(env, java_lang_AbstractMethodError, "");
}

jint nvmThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    // TODO: Set index on exception
    return nvmThrowNew(env, java_lang_ArrayIndexOutOfBoundsException, "");
}

jint nvmThrowArrayStoreException(Env* env) {
    return nvmThrowNew(env, java_lang_ArrayStoreException, "");
}

jint nvmThrowClassNotFoundException(Env* env, char* className) {
    // TODO: Message should look like "java.lang.ClassNotFoundException: a.C"
    return nvmThrowNew(env, java_lang_ClassNotFoundException, "");
}

jint nvmThrowNegativeArraySizeException(Env* env) {
    return nvmThrowNew(env, java_lang_NegativeArraySizeException, "");
}

jint nvmThrowUnsatisfiedLinkError(Env* env) {
    // TODO: Message should look like "java.lang.UnsatisfiedLinkError: Foo.nativeFunction()V"
    return nvmThrowNew(env, java_lang_UnsatisfiedLinkError, "");
}

jint nvmThrowIllegalArgumentException(Env* env, char* message) {
    return nvmThrowNew(env, java_lang_IllegalArgumentException, message);
}

jint nvmThrowVerifyError(Env* env, char* msg) {
    return nvmThrowNew(env, java_lang_VerifyError, msg);
}

jint nvmThrowArithmeticException(Env* env) {
    return nvmThrowNew(env, java_lang_ArithmeticException, NULL);
}

