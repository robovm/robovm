#include <robovm.h>

void Java_java_lang_Runtime_nativeExit(Env* env, Class* clazz, jint code, jboolean isExit) {
    // TODO: What about other threads? Should we stop them before shutting down?
    rvmShutdown(env, code);
}

void Java_java_lang_Runtime_nativeLoad(Env* env, Class* clazz, Object* filename, ClassLoader* classLoader) {
    char* path = rvmGetStringUTFChars(env, filename);
    if (!path) return;
    rvmLoadNativeLibrary(env, path, classLoader);
}

