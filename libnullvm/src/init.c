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

static jboolean loadClasspathEntries(Env* env, char* basePath, char* entriesFile, ClasspathEntry** first) {
    // TODO: Handle escaped = characters?
    // TODO: Encoding
    FILE* f = fopen(entriesFile, "r");
    if (!f) return FALSE;
    fseek(f, 0, SEEK_END);
    jint length = ftell(f);
    fseek(f, 0, SEEK_SET);
    char* files = nvmAllocateMemory(env, length + 1);
    if (!files || fread(files, length, 1, f) == 0) {
        fclose(f);
        return FALSE;
    }
    fclose(f);
    f = NULL;
    files[length] = '\0';

    TRACE("Contents of file '%s':\n %s\n", entriesFile, files);

    char jarPath[PATH_MAX];
    char soPath[PATH_MAX];

    char* p = files;
    char* line = NULL;
    char* lasts = NULL;
    while (1) {
        line = strtok_r(p, "\r\n", &lasts);
        p = NULL;
        if (!line) break;
        if (line[0] == '#') continue;

        char* lasts2 = NULL;
        char* left = strtok_r(line, "=", &lasts2);
        if (!left) nvmAbort("Failed to parse line '%s' in classpath file '%s'", line, entriesFile);
        char* right = strtok_r(NULL, "\r\n", &lasts2);
        if (!right) nvmAbort("Failed to parse line '%s' in classpath file '%s'", line, entriesFile);
        ClasspathEntry* entry = nvmAllocateMemory(env, sizeof(ClasspathEntry));
        if (!entry) return FALSE;
        absolutize(basePath, left, entry->jarPath);
        absolutize(basePath, right, entry->soPath);
        *first = entry;
        first = &entry->next;
    }

    return TRUE;
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

    char cpFile[PATH_MAX];
    strcpy(cpFile, options->basePath);
    strcat(cpFile, "/bootclasspath");
    if (!loadClasspathEntries(env, options->basePath, cpFile, &options->bootclasspath)) return NULL;
    strcpy(cpFile, options->basePath);
    strcat(cpFile, "/classpath");
    if (!loadClasspathEntries(env, options->basePath, cpFile, &options->classpath)) return NULL;

    // Call init on modules
    if (!nvmInitClasses(env)) return NULL;
    if (!nvmInitStrings(env)) return NULL;
    if (!nvmInitVMI(env)) return NULL;
    if (!nvmInitThreads(env)) return NULL;

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
    Object* throwable = nvmExceptionOccurred(env);
    if (throwable) {
        // TODO: Handle when the call to printStackTrace fails with an exception
        nvmExceptionClear(env);
        Method* printStackTrace = nvmGetInstanceMethod(env, java_lang_Thread, "printStackTrace", "(Ljava/lang/Throwable;)V");
        if (printStackTrace) {
            args[0].l = (jobject) throwable;
            nvmCallVoidInstanceMethodA(env, env->currentThread->threadObj, printStackTrace, args);
        }
        nvmThrow(env, throwable);
        // TODO: Wait for other threads to finish?
    }
    return !nvmExceptionCheck(env);
}

void nvmShutdown(Env* env, jint code) {
    // TODO: Cleanup, stop threads.
    exit(code);
}

// TODO: Move this to a more appropriate file
void* nvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
//    void* m = calloc(1, size);
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

DynamicLib* nvmInitDynamicLib(Env* env, char* file, DynamicLib** first) {
    while (*first != NULL) first = &((*first)->next);

    DynamicLib* dlib = nvmAllocateMemory(env, sizeof(DynamicLib));
    if (!dlib) return NULL;

    strcpy(dlib->path, file);

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
    TRACE("Searching for symbol '%s'\n", symbol);

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

