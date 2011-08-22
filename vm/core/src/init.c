#include <nullvm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>
#include <dlfcn.h>
#include "log.h"
#include "hyport.h"
#include "utlist.h"

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

    char* p = files;
    char* line = NULL;
    char* lasts = NULL;
    while (1) {
        line = strtok_r(p, "\r\n", &lasts);
        p = NULL;
        if (!line) break;
        if (line[0] == '#') continue;

        ClasspathEntry* entry = nvmAllocateMemory(env, sizeof(ClasspathEntry));
        if (!entry) return FALSE;
        absolutize(basePath, line, entry->jarPath);
        LL_APPEND(*first, entry);
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
    strcpy(options->bootLibPath, path);
    strcat(options->bootLibPath, "/lib/boot");
    strcpy(options->mainLibPath, path);
    strcat(options->mainLibPath, "/lib/main");

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
    return vm;
}

Env* nvmCreateEnv(VM* vm) {
    Env* env = GC_MALLOC(sizeof(Env));
    if (!env) return NULL;
    env->vm = vm;
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

    char cpFile[PATH_MAX];
    strcpy(cpFile, options->basePath);
    strcat(cpFile, "/bootclasspath");
    if (!loadClasspathEntries(env, options->basePath, cpFile, &options->bootclasspath)) return NULL;
    strcpy(cpFile, options->basePath);
    strcat(cpFile, "/classpath");
    if (!loadClasspathEntries(env, options->basePath, cpFile, &options->classpath)) return NULL;

    HYPORT_SET_VERSION(&portLibraryVersion, HYPORT_CAPABILITY_MASK);
    if (hyport_init_library(&portLibrary, &portLibraryVersion, sizeof(HyPortLibrary))) return NULL;

    // Call init on modules
    if (!nvmInitLog(env)) return NULL;
    if (!nvmInitClasses(env)) return NULL;
    if (!nvmInitStrings(env)) return NULL;
    if (!nvmInitVMI(env)) return NULL;
    if (!nvmInitThreads(env)) return NULL;
    if (!nvmInitPrimitiveWrapperClasses(env)) return NULL;
    if (!nvmInitAttributes(env)) return NULL;

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

DynamicLib* nvmLoadDynamicLib(Env* env, char* file, DynamicLib** first) {
    DynamicLib* dlib = NULL;

    void* handle = dlopen(file, RTLD_LOCAL | RTLD_LAZY);
    if (!handle) {
        TRACE("Failed to load dynamic library '%s': %s\n", file, dlerror());
        return NULL;
    }

    // Make sure we haven't already loaded this lib
    LL_FOREACH(*first, dlib) {
        if (dlib->handle == handle) {
            dlclose(handle); // Close to decrement ref count
            return dlib;
        }
    }

    TRACE("Loading dynamic library '%s'\n", file);

    dlib = nvmAllocateMemory(env, sizeof(DynamicLib));
    if (!dlib) {
        dlclose(handle);
        return NULL;
    }

    dlib->handle = handle;
    LL_APPEND(*first, dlib);

    return dlib;
}

void* nvmFindDynamicLibSymbol(Env* env, DynamicLib* first, char* symbol) {
    TRACE("Searching for symbol '%s'\n", symbol);

    DynamicLib* dlib = first;
    while (dlib) {
        void* v = dlsym(dlib->handle, symbol);
        if (v) return v;
        dlib = dlib->next;
    }

    return NULL;
}

