#include <nullvm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>
#include <dlfcn.h>
#include "log.h"

DynamicLib* nativeLibs = NULL;
jboolean _logLevel;

static inline jint startsWith(char* s, char* prefix) {
    return s && strncmp(s, prefix, strlen(prefix)) == 0;
}

jint nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs) {
    char path[PATH_MAX];
    if (!realpath(argv[0], path)) {
        return 1;
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
    strcpy(options->bootLibPath, path);
    strcat(options->bootLibPath, "/lib/boot");
    strcpy(options->mainLibPath, path);
    strcat(options->mainLibPath, "/lib/main");

    jint firstJavaArg = 1;
    for (i = 1; i < argc; i++) {
        if (startsWith(argv[i], "-nvm:")) {
            if (!ignoreNvmArgs) {
                char* arg = &argv[i][5];
                if (startsWith(arg, "Xlog:trace")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_TRACE;
                } else if (startsWith(arg, "Xlog:warn")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_WARN;
                } else if (startsWith(arg, "Xlog:error")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_ERROR;
                } else if (startsWith(arg, "Xlog:none")) {
                    if (options->logLevel == 0) options->logLevel = LOG_LEVEL_NONE;
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
        options->commandLineArgs = &argv[options->commandLineArgsCount];
    }

    _logLevel = options->logLevel == 0 ? LOG_LEVEL_ERROR : options->logLevel;

    return 0;
}

Env* nvmStartup(Options* options) {
    GC_INIT();
    // TODO: Handle args like -Xmx?

    Env* env = nvmCreateEnv(options);
    if (!env) return NULL;
    // TODO: What if we can't allocate Env?

    // Call init on modules
    if (!nvmInitClasses(env)) return NULL;
    if (!nvmInitStrings(env)) return NULL;
    if (!nvmInitThreads(env)) return NULL;
    if (!nvmInitVMI(env)) return NULL;

    return env;
}

jboolean nvmRun(Env* env) {
    Class* clazz = nvmFindClass(env, env->options->mainClass);
    if (!clazz) return FALSE;
    Method* method = nvmGetClassMethod(env, clazz, "main", "([Ljava/lang/String;)V");
    if (!method) return FALSE;
    // TODO: Create args array
    jvalue args[1];
    args[0].l = (jobject) NULL;
    nvmCallVoidClassMethodA(env, clazz, method, args);
    return !nvmExceptionCheck(env);
}

void nvmShutdown(void) {
}

// TODO: Move this to a more appropriate file
void* nvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        nvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void nvmAbort(char* format, ...) {
    va_list args;
    va_start(args, format);
    vfprintf(stderr, format, args);
    va_end(args);
    fprintf(stderr, "\n");
    abort();
}

DynamicLib** nvmGetNativeLibs(Env* env) {
    return &nativeLibs;
}

DynamicLib* nvmInitDynamicLib(Env* env, char* basePath, char* baseName, DynamicLib** first) {
    while (*first != NULL) first = &((*first)->next);

    DynamicLib* dlib = nvmAllocateMemory(env, sizeof(DynamicLib));
    if (!dlib) return NULL;

    strcpy(dlib->path, basePath);
    strcat(dlib->path, "/");
    strcat(dlib->path, baseName);
    strcat(dlib->path, ".so");

    *first = dlib;

    TRACE("Initialized dynamic library '%s'\n", dlib->path);

    return dlib;
}

jboolean nvmLoadDynamicLib(Env* env, DynamicLib* dlib) {
    TRACE("Loading dynamic library '%s'\n", dlib->path);

    if (!dlib->handle) {
        dlib->handle = dlopen(dlib->path, RTLD_LOCAL | RTLD_LAZY);
        if (!dlib->handle) return FALSE;
    }

    return TRUE;
}

void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* first, DynamicLib* last, char* symbol) {
    TRACE("Searching for symbol '%s'", symbol);

    DynamicLib* dlib = first;
    while (dlib && dlib != last) {
        if (!dlib->handle) {
            nvmLoadDynamicLib(env, dlib);
        }
        if (dlib->handle) {
            void* v = dlsym(dlib->handle, symbol);
            if (v) return v;
        }
        dlib = dlib->next;
    }

    return NULL;
}

