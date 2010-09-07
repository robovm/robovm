#include <nullvm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>

static inline jint startsWith(char* s, char* prefix) {
    return s && strncmp(s, prefix, strlen(prefix)) == 0;
}

jint nvmParseArgs(int argc, char* argv[], Options* options) {
    jint i;

    for (i = 1; i < argc; i++) {
        if (startsWith(argv[i], "-nvm:")) {
            char* arg = &argv[i][5];
            if (startsWith(arg, "MainClass=")) {
                if (!options->mainClass) {
                    options->mainClass = &arg[strlen("MainClass=")];
                }
            }
        }
    }

    return 0;
}

Env* nvmStartup(Options* options) {
    GC_set_pages_executable(1);
    if (!GC_get_pages_executable()) {
        nvmAbort("Failed to set GC_set_pages_executable(1)");
    }
    GC_INIT();
    // TODO: Handle args like -Xmx?

    Env* env = nvmCreateEnv();
    if (!env) return NULL;
    // TODO: What if we can't allocate Env?

    // Call init on modules
    nvmInitClasses(env);

    return env;
}

jboolean nvmRun(Env* env, Options* options) {
    Class* clazz = nvmFindClass(env, options->mainClass);
    if (!clazz) return FALSE;
    Method* method = nvmGetClassMethod(env, clazz, "main", "([Ljava/lang/String;)V");
    if (!method) return FALSE;
    // TODO: Create args array
    void (*f)(void*, Env*, ObjectArray*) = method->impl;
    // TODO: Call through a trampoline which catches any exceptions and sets it on env.
    f(NULL, env, NULL);
    return nvmExceptionCheck(env);
}

void nvmShutdown(void) {
}

void* nvmAllocateMemory(Env* env, int size) {
    void* m = GC_MALLOC(size);
    if (!m) {
        nvmThrowOutOfMemoryError(env);
        return NULL;
    }
    return m;
}

void* nvmAllocateExecutableMemory(Env* env, int size) {
    return nvmAllocateMemory(env, size);
}

void nvmAbort(char* format, ...) {
    va_list args;
    va_start(args, format);
    vfprintf(stderr, format, args);
    va_end(args);
    fprintf(stderr, "\n");
    abort();
}


