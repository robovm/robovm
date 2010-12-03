#include <nullvm.h>
#include <unwind.h>

typedef struct StackEntry StackEntry;
struct StackEntry {
    StackEntry* next;
    Method* method;
    jint offset;
};

static _Unwind_Reason_Code trace(struct _Unwind_Context* ctx, void* d) {
    void* address = (void*) _Unwind_GetIP(ctx);
    void* func = _Unwind_FindEnclosingFunction(address);
    Method* method = nvmFindMethodAtAddress((Env*) d, func);
    if (method) {
        printf("    %s/%s%s (+%x, %p, %p)\n", method->clazz->name, method->name, method->desc, (unsigned int) (address - method->impl), method->impl, func);
    } else {
        printf("    Unknown function (%p, %p)\n", address, func);
    }
    return _URC_NO_REASON;
}

Object* Java_java_lang_Throwable_fillInStackTrace(JNIEnv* _env, Object* thiz) {
    printf("*** fillInStackTrace\n");
    _Unwind_Backtrace(trace, (Env*) _env);
    return NULL;
}

ObjectArray* Java_java_lang_Throwable_getStackTraceImpl(JNIEnv* _env, Object* thiz) {
    Env* env = (Env*) _env;
    Class* java_lang_StackTraceElement = nvmFindClass(env, "java/lang/StackTraceElement");
    if (!java_lang_StackTraceElement) return NULL;
    return nvmNewObjectArray(env, 0, java_lang_StackTraceElement, NULL, NULL);
}

