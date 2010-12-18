#include <nullvm.h>
#include <vmi.h>
#include "log.h"

// Defined in init.c
extern DynamicLib* nativeLibs;

extern struct VMInterfaceFunctions_ vmiImpl;
VMInterface vmi = &vmiImpl;
HyPortLibraryVersion portLibraryVersion;
HyPortLibrary portLibrary;

static UDATA JNICALL _HyVMLSAllocKeys(JNIEnv* env, UDATA* pInitCount, ...) {
    return 0;
}
static void JNICALL _HyVMLSFreeKeys(JNIEnv* env, UDATA* pInitCount, ...) {
}
static void* JNICALL _HyVMLSGet(JNIEnv* env, void *key) {
    return key;
}
static void* JNICALL _HyVMLSSet(JNIEnv* env, void **pKey, void *value) {
    *pKey = value;
    return value;
}

HyVMLSFunctionTable vmls = {
    &_HyVMLSAllocKeys,
    &_HyVMLSFreeKeys,
    &_HyVMLSGet,
    &_HyVMLSSet
};

static vmiError JNICALL _CheckVersion(VMInterface* vmi, vmiVersion* version) {
    return VMI_ERROR_UNIMPLEMENTED;
}
static JavaVM* JNICALL _GetJavaVM(VMInterface* vmi) {
    return NULL;
}
static HyPortLibrary* JNICALL _GetPortLibrary(VMInterface* vmi) {
    return &portLibrary;
}
static HyVMLSFunctionTable* JNICALL _GetVMLSFunctions(VMInterface* vmi) {
    return &vmls;
}
static HyZipCachePool* JNICALL _GetZipCachePool(VMInterface* vmi) {
}
static JavaVMInitArgs* JNICALL _GetInitArgs(VMInterface* vmi) {
}
static vmiError JNICALL _GetSystemProperty(VMInterface* vmi, char* key, char** valuePtr) {
}
static vmiError JNICALL _SetSystemProperty(VMInterface* vmi, char* key, char* value) {
}
static vmiError JNICALL _CountSystemProperties(VMInterface* vmi, int* countPtr) {
}
static vmiError JNICALL _IterateSystemProperties(VMInterface* vmi, vmiSystemPropertyIterator iterator, void* userData) {
}

struct VMInterfaceFunctions_ vmiImpl = {
    &_CheckVersion,
    &_GetJavaVM,
    &_GetPortLibrary,
    &_GetVMLSFunctions,
    &_GetZipCachePool,
    &_GetInitArgs,
    &_GetSystemProperty,
    &_SetSystemProperty,
    &_CountSystemProperties,
    &_IterateSystemProperties
};

struct JavaVMProxy {
    JavaVM javaVM;
    Env* env;
};

static jint _GetEnv(JavaVM *_vm, void **env, jint version) {
    struct JavaVMProxy* vm = (struct JavaVMProxy*) _vm;
    *env = vm->env;
    return JNI_OK;
}

jboolean nvmInitVMI(Env* env) {
    HYPORT_SET_VERSION(&portLibraryVersion, HYPORT_CAPABILITY_MASK);
    if (hyport_init_library(&portLibrary, &portLibraryVersion, sizeof(HyPortLibrary))) return FALSE;

    // Setup a JavaVM struct which satisfies the JNI_OnLoad_* functions
    struct JNIInvokeInterface_ javaVM;
    javaVM.GetEnv = _GetEnv;
    struct JavaVMProxy javaVMProxy = {&javaVM, env};

    DynamicLib* dlib = nvmInitDynamicLib(env, env->options->basePath, "libnullvm-rt", &nativeLibs);
    if (!dlib) return FALSE;
    if (!nvmLoadDynamicLib(env, dlib)) {
        nvmAbort("Fatal error: Failed to load %s", dlib->path);
    }

    jint (*JNI_OnLoad_LUNI)(JavaVM* vm, void* reserved) = nvmFindDynamicLibSymbol(env, dlib, NULL, "JNI_OnLoad_LUNI");
    jint (*JNI_OnLoad_Archive)(JavaVM* vm, void* reserved) = nvmFindDynamicLibSymbol(env, dlib, NULL, "JNI_OnLoad_Archive");

    if (!JNI_OnLoad_LUNI || !JNI_OnLoad_Archive) return FALSE;

    if (!JNI_OnLoad_LUNI((JavaVM*) &javaVMProxy, NULL)) return FALSE;
    if (!JNI_OnLoad_Archive((JavaVM*) &javaVMProxy, NULL)) return FALSE;

    // TODO: Call onUnload when shutting down

    return TRUE;
}

VMInterface* JNICALL VMI_GetVMIFromJavaVM(JavaVM* vm) {
    return &vmi;
}    

VMInterface* JNICALL VMI_GetVMIFromJNIEnv(JNIEnv* env) {
    return &vmi;
}

