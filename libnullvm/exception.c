#include <nullvm.h>
#include <stddef.h>
#include <unwind.h>

#define EXCEPTION_CLASS 0x4A4A4A4A4A4A4A4A // "JJJJJJJJ"

typedef struct _junwind_info {
  Object* throwable;
  _Unwind_Ptr landing_pad;
  struct _Unwind_Exception exception_info;
} junwind_info;

extern _Unwind_Reason_Code __gcc_personality_v0(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context);

_Unwind_Reason_Code j_eh_personality(int version, _Unwind_Action actions, _Unwind_Exception_Class exception_class, struct _Unwind_Exception* exception_info, struct _Unwind_Context* context) {

    if (actions & _UA_SEARCH_PHASE) {
        _Unwind_Ptr saved_ip = _Unwind_GetIP(context);
        _Unwind_Reason_Code urc = __gcc_personality_v0(version, _UA_CLEANUP_PHASE, exception_class, exception_info, context);
        if (urc == _URC_INSTALL_CONTEXT) {
            junwind_info* info = (junwind_info*) (((char*) exception_info) - offsetof(junwind_info, exception_info));
            info->landing_pad = _Unwind_GetIP(context);
            _Unwind_SetIP(context, saved_ip);
            return _URC_HANDLER_FOUND;
        }
        return urc;
    } else if (actions & _UA_HANDLER_FRAME) {
        junwind_info* info = (junwind_info*) (((char*) exception_info) - offsetof(junwind_info, exception_info));
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (0), (_Unwind_Ptr) exception_info);
        _Unwind_SetGR(context, __builtin_eh_return_data_regno (1), 0);
        _Unwind_SetIP(context, info->landing_pad); 
        return _URC_INSTALL_CONTEXT;
    }

    return _URC_CONTINUE_UNWIND;
}

void nvmRaiseException(Env* env, Object* e) {
    junwind_info* u = nvmAllocateMemory(env, sizeof(junwind_info));
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

Object* nvmExceptionClear(Env* env) {
    Object* e = env->throwable;
    env->throwable = NULL;
    return e;
}

void nvmThrow(Env* env, Object* e) {
    // TODO: Check that e != NULL?
    env->throwable = e;
}

void nvmThrowNew(Env* env, Class* clazz, char* message) {
    // TODO: Check that clazz != NULL?
    Object* e = nvmAllocateObject(env, clazz);
    if (!e) return;
    // TODO: Call constructor
    nvmThrow(env, e);
}

void nvmThrowOutOfMemoryError(Env* env) {
    nvmThrowNew(env, java_lang_OutOfMemoryError, "");
}

void nvmThrowNoClassDefFoundError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoClassDefFoundError: a/C"
    nvmThrowNew(env, java_lang_NoClassDefFoundError, "");
}

void nvmThrowIllegalAccessError(Env* env) {
    nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

void nvmThrowIllegalAccessErrorField(Env* env, Class* clazz, char* name, char* desc, Class* caller) {
    // TODO: Message should look like "java.lang.IllegalAccessError: tried to access field a.A.x from class b.B"
    nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

void nvmThrowIllegalAccessErrorMethod(Env* env, Class* clazz, char* name, char* desc, Class* caller) {
    // TODO: Message should look like ?
    nvmThrowNew(env, java_lang_IllegalAccessError, "");
}

void nvmThrowNoSuchFieldError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoSuchFieldError: x"
    // TODO: Cache java.lang.NoSuchFieldError at startup
    nvmThrowNew(env, java_lang_NoSuchFieldError, "");
}

void nvmThrowNoSuchMethodError(Env* env, char* name) {
    // TODO: Message should look like "java.lang.NoSuchMethodError: x"
    nvmThrowNew(env, java_lang_NoSuchMethodError, "");
}

void nvmThrowIncompatibleClassChangeErrorClassField(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected static field a.C.x"
    nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

void nvmThrowIncompatibleClassChangeErrorInstanceField(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected non-static field a.C.x"
    nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

void nvmThrowIncompatibleClassChangeErrorMethod(Env* env, Class* clazz, char* name, char* desc) {
    // TODO: Message should look like ?
    nvmThrowNew(env, java_lang_IncompatibleClassChangeError, "");
}

void nvmThrowClassCastException(Env* env, Class* expectedClass, Class* actualClass) {
    // TODO: Message should look like "java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String"
    // TODO: Cache java.lang.ClassCastException at startup
    nvmThrowNew(env, java_lang_ClassCastException, "");
}

void nvmThrowNullPointerException(Env* env) {
    nvmThrowNew(env, java_lang_NullPointerException, "");
}

void nvmThrowAbstractMethodError(Env* env) {
    nvmThrowNew(env, java_lang_AbstractMethodError, "");
}

void nvmThrowArrayIndexOutOfBoundsException(Env* env, jint index) {
    // TODO: Set index on exception
    nvmThrowNew(env, java_lang_ArrayIndexOutOfBoundsException, "");
}

void nvmThrowClassNotFoundException(Env* env, char* className) {
    // TODO: Message should look like "java.lang.ClassNotFoundException: a.C"
    nvmThrowNew(env, java_lang_ClassNotFoundException, "");
}

void nvmThrowNegativeArraySizeException(Env* env) {
    nvmThrowNew(env, java_lang_NegativeArraySizeException, "");
}

void nvmThrowUnsatisfiedLinkError(Env* env) {
    // TODO: Message should look like ?
    nvmThrowNew(env, java_lang_UnsatisfiedLinkError, "");
}

