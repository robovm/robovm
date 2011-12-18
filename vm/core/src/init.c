#include <nullvm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>
#include <dlfcn.h>
#include "log.h"
#include "hyport.h"
#include "utlist.h"

#define RT_DSO "libnullvm-rt.so"
#ifdef DARWIN
  #undef RT_DSO
  #define RT_DSO "libnullvm-rt.dylib"
#endif

#define INITIAL_NATIVE_FRAMES_SIZE 8
#define INITIAL_PROXY_FRAMES_SIZE 2

HyPortLibraryVersion portLibraryVersion;
HyPortLibrary portLibrary;

static inline jint startsWith(char* s, char* prefix) {
    return s && strncmp(s, prefix, strlen(prefix)) == 0;
}

static char* absolutize(char* basePath, char* rel, char* dest) {
    if (rel[0] == '/') {
        strcpy(dest, rel);
    } else {
        strcpy(dest, basePath);
        strcat(dest, "/");
        strcat(dest, rel);
    }
    return dest;
}

static jboolean initClasspathEntries(Env* env, char* basePath, char** raw, ClasspathEntry** first) {
    jint i = 0;
    while (raw[i]) {
        ClasspathEntry* entry = nvmAllocateMemory(env, sizeof(ClasspathEntry));
        if (!entry) return FALSE;
        absolutize(basePath, raw[i], entry->jarPath);
        LL_APPEND(*first, entry);
        i++;
    }

    return TRUE;
}

static ClassLoader* getSystemClassLoader(Env* env) {
    Class* holder = nvmFindClass(env, "java/lang/ClassLoader$SystemClassLoaderHolder");
    if (!holder) return NULL;
    ClassField* field = nvmGetClassField(env, holder, "loader", "Ljava/lang/ClassLoader;");
    if (!field) return NULL;
    return (ClassLoader*) nvmGetObjectClassFieldValue(env, holder, field);
}

jboolean nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs) {
    char path[PATH_MAX];
    if (!realpath(argv[0], path)) {
        return FALSE;
    }

    strcpy(options->executablePath, path);

    jint i = strlen(path);
    while (i >= 0 && path[i] != '/') {
        path[i--] = '\0';
    }
    if (i >= 0 && path[i] == '/') {
        path[i] = '\0';
    }

    strcpy(options->basePath, path);

    jint firstJavaArg = 1;
    for (i = 1; i < argc; i++) {
        if (startsWith(argv[i], "-nvm:")) {
            if (!ignoreNvmArgs) {
                char* arg = &argv[i][5];
                if (startsWith(arg, "log=trace")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_TRACE;
                } else if (startsWith(arg, "log=warn")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_WARN;
                } else if (startsWith(arg, "log=error")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_ERROR;
                } else if (startsWith(arg, "log=none")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_NONE;
                } else if (startsWith(arg, "MainClass=")) {
                    if (!options->mainClass) {
                        char* s = strdup(&arg[10]);
                        jint j;
                        for (j = 0; s[j] != 0; j++) {
                            if (s[j] == '.') s[j] = '/';
                        }
                        options->mainClass = s;
                    }
                }
            }
            firstJavaArg++;
        } else {
            break;
        }
    }

    options->commandLineArgs = NULL;
    options->commandLineArgsCount = argc - firstJavaArg;
    if (options->commandLineArgsCount > 0) {
        options->commandLineArgs = &argv[firstJavaArg];
    }

    return options->mainClass != NULL;
}

VM* nvmCreateVM(Options* options) {
    VM* vm = GC_MALLOC(sizeof(VM));
    if (!vm) return NULL;
    vm->options = options;
    nvmInitJavaVM(vm);
    return vm;
}

Env* nvmCreateEnv(VM* vm) {
    Env* env = GC_MALLOC(sizeof(Env) + sizeof(void*) * INITIAL_NATIVE_FRAMES_SIZE + sizeof(ProxyMethod*) * INITIAL_PROXY_FRAMES_SIZE);
    if (!env) return NULL;
    env->vm = vm;
    env->nativeFrames.size = INITIAL_NATIVE_FRAMES_SIZE;
    env->nativeFrames.base = (void**) ((void*) env + sizeof(Env));
    env->nativeFrames.top = env->nativeFrames.base;
    env->proxyFrames.size = INITIAL_PROXY_FRAMES_SIZE;
    env->proxyFrames.base = (ProxyMethod**) ((void*) env + sizeof(Env) + sizeof(void*) * INITIAL_NATIVE_FRAMES_SIZE);
    env->proxyFrames.top = env->proxyFrames.base;
    nvmInitJNIEnv(env);
    return env;
}

Env* nvmStartup(Options* options) {
    GC_INIT();
    // TODO: Handle args like -Xmx?

    VM* vm = nvmCreateVM(options);
    if (!vm) return NULL;

    Env* env = nvmCreateEnv(vm);
    if (!env) return NULL;
    // TODO: What if we can't allocate Env?

    if (!initClasspathEntries(env, options->basePath, options->rawBootclasspath, &options->bootclasspath)) return NULL;
    if (!initClasspathEntries(env, options->basePath, options->rawClasspath, &options->classpath)) return NULL;

    HYPORT_SET_VERSION(&portLibraryVersion, HYPORT_CAPABILITY_MASK);
    if (hyport_init_library(&portLibrary, &portLibraryVersion, sizeof(HyPortLibrary))) return NULL;

    // Call init on modules
    if (!nvmInitLog(env)) return NULL;
    if (!nvmInitClasses(env)) return NULL;
    if (!nvmInitMethods(env)) return NULL;
    if (!nvmInitStrings(env)) return NULL;
    if (!nvmInitVMI(env)) return NULL;
    if (!nvmInitThreads(env)) return NULL;
    if (!nvmInitAttributes(env)) return NULL;

    // Load nullvm-rt
    // Try first in same dir as the executable
    char path[PATH_MAX];
    strcpy(path, options->basePath);
    strcat(path, "/" RT_DSO);
    if (!nvmLoadNativeLibrary(env, path, NULL)) {
        // Try with no path. Maybe (DY)LD_LIBRARY_PATH has been set?
        if (!nvmLoadNativeLibrary(env, RT_DSO, NULL)) {
            nvmAbort("Fatal error: Failed to load " RT_DSO);
        }
    }

    // This has to be called after nullvm-rt has been loaded
    if (!nvmInitPrimitiveWrapperClasses(env)) return NULL;

    env->currentThread->contextClassLoader = getSystemClassLoader(env);
    if (nvmExceptionOccurred(env)) return NULL;

    return env;
}

jboolean nvmRun(Env* env) {
    Options* options = env->vm->options;
    ClassLoader* systemClassLoader = getSystemClassLoader(env);
    Class* clazz = NULL;
    if (systemClassLoader) {
        clazz = nvmFindClassUsingLoader(env, options->mainClass, systemClassLoader);
        if (clazz) {
            Method* method = nvmGetClassMethod(env, clazz, "main", "([Ljava/lang/String;)V");
            if (method) {
                ObjectArray* args = nvmNewObjectArray(env, options->commandLineArgsCount, java_lang_String, NULL, NULL);
                if (args) {
                    jint i = 0;
                    for (i = 0; i < args->length; i++) {
                        // TODO: Don't assume modified UTF-8
                        args->values[i] = nvmNewStringUTF(env, options->commandLineArgs[i], -1);
                        if (!args->values[i]) {
                            args = NULL;
                            break;
                        }
                    }
                    if (args) nvmCallVoidClassMethod(env, clazz, method, args);
                }
            }
        }
    }
    Object* throwable = nvmExceptionOccurred(env);
    if (throwable) {
        // TODO: Handle when the call to printStackTrace fails with an exception
        nvmExceptionClear(env);
        Method* printStackTrace = nvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
        if (printStackTrace) {
            jvalue args[1];
            args[0].l = (jobject) throwable;
            nvmCallVoidInstanceMethodA(env, (Object*) env->currentThread, printStackTrace, args);
        }
        nvmThrow(env, throwable);
        // TODO: Wait for other threads to finish?
    }
    if (!clazz) {
        fprintf(stderr, "Main class %s not found.\n", options->mainClass);
    }
    return !nvmExceptionCheck(env);
}

void nvmShutdown(Env* env, jint code) {
    // TODO: Cleanup, stop threads.
    exit(code);
}

void nvmAbort(char* format, ...) {
    va_list args;
    va_start(args, format);
    vfprintf(stderr, format, args);
    va_end(args);
    fprintf(stderr, "\n");
    abort();
}

DynamicLib* nvmOpenDynamicLib(Env* env, char* file) {
    DynamicLib* dlib = NULL;

    void* handle = dlopen(file, RTLD_LOCAL | RTLD_LAZY);
    if (!handle) {
        TRACE("Failed to load dynamic library '%s': %s\n", file, dlerror());
        return NULL;
    }

    TRACE("Opening dynamic library '%s'\n", file);

    dlib = nvmAllocateMemory(env, sizeof(DynamicLib));
    if (!dlib) {
        dlclose(handle);
        return NULL;
    }

    dlib->handle = handle;

    return dlib;
}

void nvmCloseDynamicLib(Env* env, DynamicLib* lib) {
    dlclose(lib->handle);
}

jboolean nvmHasDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs) {
    DynamicLib* dlib = NULL;
    LL_FOREACH(libs, dlib) {
        if (dlib->handle == lib->handle) {
            return TRUE;
        }
    }
    return FALSE;
}

void nvmAddDynamicLib(Env* env, DynamicLib* lib, DynamicLib** libs) {
    LL_APPEND(*libs, lib);
}

void nvmRemoveDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs) {
    LL_DELETE(libs, lib);
}

void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* libs, char* symbol, jboolean searchAll) {
    TRACE("Searching for symbol '%s'\n", symbol);

    DynamicLib* dlib = NULL;
    LL_FOREACH(libs, dlib) {
        void* v = dlsym(dlib->handle, symbol);
        if (v) return v;
        if (!searchAll) return NULL;
    }
    return NULL;
}

