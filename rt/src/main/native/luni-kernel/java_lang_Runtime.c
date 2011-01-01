#include <nullvm.h>

void Java_java_lang_Runtime_nativeExit(Env* env, Class* clazz, jint code, jboolean isExit) {
    // TODO: What about other threads? Should we stop them before shutting down?
    nvmShutdown(env, code);
}

