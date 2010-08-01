#include <nullvm.h>
#include <stddef.h>
#include <unwind.h>

#define EXCEPTION_CLASS 0x4A4A4A4A4A4A4A4A // "JJJJJJJJ"

typedef struct _junwind_info {
  jobject* throwable;
  _Unwind_Ptr landing_pad;
  struct _Unwind_Exception exception_info;
} junwind_info;

static is_instance_of_throwable(jobject* throwable, jclass* clazz) {
    return clazz != NULL && (throwable->clazz == clazz || is_instance_of_throwable(throwable, clazz->superclass));
}

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

void j_eh_resume_unwind(struct _Unwind_Exception* exception_info) {
    _Unwind_Resume(exception_info);
}

void nvmThrow(jobject* e) {
    junwind_info* u = GC_MALLOC(sizeof(junwind_info));
    u->exception_info.exception_class = EXCEPTION_CLASS;
    u->throwable = e;
    _Unwind_Reason_Code urc = _Unwind_RaiseException(&u->exception_info);
    if (urc == _URC_END_OF_STACK) {
        nvmAbort("Unhandled exception: %s", e->clazz->name);
    }
    nvmAbort("Fatal error in exception handler: %d", urc);
}

jobject* j_get_throwable(struct _Unwind_Exception* exception_info) {
    junwind_info* info = (junwind_info*) (((char*) exception_info) - offsetof(junwind_info, exception_info));
    return info->throwable;
}

jint j_eh_match_throwable(jobject* throwable, jclass* clazz) {
    jclass* c = throwable->clazz;
    while (c && c != clazz) {
        c = c->superclass;
    }
    return c == clazz ? 1 : 0;
}

void nvmThrowNoClassDefFoundError(char* name) {
    // TODO: Message should look like "java.lang.NoClassDefFoundError: a/C"
    // TODO: Cache java.lang.NoClassDefFoundError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/NoClassDefFoundError", "java_lang_NoClassDefFoundError", NULL)));
}

void nvmThrowIllegalAccessError(void) {
    // TODO: Cache java.lang.IllegalAccessError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/IllegalAccessError", "java_lang_IllegalAccessError", NULL)));
}

void nvmThrowIllegalAccessErrorField(jclass* clazz, char* name, char* desc, jclass* caller) {
    // TODO: Message should look like "java.lang.IllegalAccessError: tried to access field a.A.x from class b.B"
    // TODO: Cache java.lang.IllegalAccessError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/IllegalAccessError", "java_lang_IllegalAccessError", NULL)));
}

void nvmThrowNoSuchFieldError(char* name) {
    // TODO: Message should look like "java.lang.NoSuchFieldError: x"
    // TODO: Cache java.lang.NoSuchFieldError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/NoSuchFieldError", "java_lang_NoSuchFieldError", NULL)));
}

void nvmThrowIncompatibleClassChangeErrorClassField(jclass* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected static field a.C.x"
    // TODO: Cache java.lang.IncompatibleClassChangeError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/IncompatibleClassChangeError", "java_lang_IncompatibleClassChangeError", NULL)));
}

void nvmThrowIncompatibleClassChangeErrorInstanceField(jclass* clazz, char* name, char* desc) {
    // TODO: Message should look like "java.lang.ThrowIncompatibleClassChangeError: Expected non-static field a.C.x"
    // TODO: Cache java.lang.IncompatibleClassChangeError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/IncompatibleClassChangeError", "java_lang_IncompatibleClassChangeError", NULL)));
}

void nvmThrowClassCastException(jclass* expectedClass, jclass* actualClass) {
    // TODO: Message should look like "java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String"
    // TODO: Cache java.lang.ClassCastException at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/ClassCastException", "java_lang_ClassCastException", NULL)));
}

void nvmThrowNullPointerException(void) {
    // TODO: Cache java.lang.NullPointerException at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/NullPointerException", "java_lang_NullPointerException", NULL)));
}

void nvmThrowAbstractMethodError(void) {
    // TODO: Cache java.lang.AbstractMethodError at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/AbstractMethodError", "java_lang_AbstractMethodError", NULL)));
}

void nvmThrowArrayIndexOutOfBoundsException(jint index) {
    // TODO: Set index on exception
    // TODO: Cache java.lang.ArrayIndexOutOfBoundsException at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/ArrayIndexOutOfBoundsException", "java_lang_ArrayIndexOutOfBoundsException", NULL)));
}

void nvmThrowClassNotFoundException(char* className) {
    // TODO: Message should look like "java.lang.ClassNotFoundException: a.C"
    // TODO: Cache java.lang.ClassNotFoundException at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/ClassNotFoundException", "java_lang_ClassNotFoundException", NULL)));
}

void nvmThrowNegativeArraySizeException(void) {
    // TODO: Cache java.lang.NegativeArraySizeException at startup
    nvmThrow(nvmNewInstance(nvmGetClass("java/lang/NegativeArraySizeException", "java_lang_NegativeArraySizeException", NULL)));
}

