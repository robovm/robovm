#include <dlfcn.h>
#include <nullvm.h>

jlong Java_org_nullvm_rt_bro_Dl_open(Env* env, Class* c, Object* name) {
    char* cName = NULL;
    if (name) {
        cName = nvmGetStringUTFChars(env, name);
        if (!cName) return 0;
    }

    void* handle = dlopen(cName, RTLD_LOCAL | RTLD_LAZY);
    if (!handle) {
        return 0;
    }
    return (jlong) handle;
}

jlong Java_org_nullvm_rt_bro_Dl_resolve(Env* env, Class* c, jlong handle, Object* name) {
    char* cName = nvmGetStringUTFChars(env, name);
    if (!cName) return 0;
    return (jlong) dlsym((void*) handle, cName);
}

void Java_org_nullvm_rt_bro_Dl_close(Env* env, Class* c, jlong handle) {
    dlclose((void*) handle);
}

Object* Java_org_nullvm_rt_bro_Dl_lastError(Env* env, Class* c) {
    char* error = dlerror();
    if (!error) return NULL;
    return nvmNewStringUTF(env, error, -1);
}

