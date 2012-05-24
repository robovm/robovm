#include <string.h>
#include <robovm.h>
#include <unwind.h>

jlong Java_java_lang_Throwable_nativeFillInStackTrace(Env* env, Object* thiz) {
    CallStackEntry* first = nvmGetCallStack(env);
    if (!first) return 0;
    first = first->next; // Skip Throwable.fillInStackTrace0()
    if (!first) return 0;
    first = first->next; // Skip Throwable.fillInStackTrace()
    if (!first) return 0;

    Class* clazz = first->method->clazz;
    if (clazz == java_lang_Throwable && METHOD_IS_CONSTRUCTOR(first->method)) {
        // fillInStackTrace() was called from the constructor of Throwable
        // Skip all constructors until the constructor of thiz->clazz
        Class* superclass = java_lang_Object;
        while (first && METHOD_IS_CONSTRUCTOR(first->method) && clazz != thiz->clazz && clazz->superclass == superclass) {
            first = first->next;
            if (first && first->method->clazz != clazz) {
                superclass = clazz;
                clazz = first->method->clazz;
            }
        }
        // We're now at the constructor of thiz->clazz which called super(). 
        // Skip all constructors belonging to thiz->clazz to get to the method which created the throwable
        while (first && METHOD_IS_CONSTRUCTOR(first->method) && clazz == thiz->clazz) {
            first = first->next;
            if (first) clazz = first->method->clazz;
        }
    }

    return PTR_TO_LONG(first);
}

ObjectArray* Java_java_lang_Throwable_nativeGetStackTrace(Env* env, Object* thiz, jlong stackState) {
    Class* java_lang_StackTraceElement = nvmFindClass(env, "java/lang/StackTraceElement");
    if (!java_lang_StackTraceElement) return NULL;

    jint length = 0;
    CallStackEntry* first = (CallStackEntry*) LONG_TO_PTR(stackState);
    while (first) {
        length++;
        first = first->next;
    }

    ObjectArray* array = nvmNewObjectArray(env, length, java_lang_StackTraceElement, NULL, NULL);
    if (!array) return NULL;

    if (length > 0) {
        Method* steConstructor = nvmGetInstanceMethod(env, java_lang_StackTraceElement, "<init>", 
                                      "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;I)V");
        if (!steConstructor) return NULL;

        jvalue args[4];
        CallStackEntry* first = (CallStackEntry*) LONG_TO_PTR(stackState);
        jint i = 0;
        for (i = 0; i < length; i++) {
            Method* m = first->method;
            args[0].l = (jobject) m->clazz;
            args[1].l = (jobject) nvmNewStringUTF(env, m->name, -1);
            if (!args[1].l) return NULL;
            args[2].l = NULL; // TODO: File names
            args[3].i = METHOD_IS_NATIVE(m) ? -2 : -1; // TODO: Line numbers
            array->values[i] = nvmNewObjectA(env, java_lang_StackTraceElement, steConstructor, args);
            if (!array->values[i]) return NULL;
            first = first->next;
        }
    }

    return array;
}

