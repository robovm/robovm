#include <nullvm.h>

extern jint JNI_OnLoad_LUNI(JavaVM* vm, void *reserved);
extern jint JNI_OnLoad_Archive(JavaVM* vm, void *reserved);

jint JNI_OnLoad(JavaVM* vm, void *reserved) {
    if (!JNI_OnLoad_LUNI(vm, reserved)) return 0;
    if (!JNI_OnLoad_Archive(vm, reserved)) return 0;
    return JNI_VERSION_1_2;
}

