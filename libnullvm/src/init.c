#include <nullvm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>

static inline jint startsWith(char* s, char* prefix) {
    return s && strncmp(s, prefix, strlen(prefix)) == 0;
}

jint nvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreNvmArgs) {
    options->executablePath = realpath(argv[0], NULL);
    if (!options->executablePath) {
        return 1;
    }

    jint i;
    jint firstJavaArg = 1;
    for (i = 1; i < argc; i++) {
        if (startsWith(argv[i], "-nvm:")) {
            if (!ignoreNvmArgs) {
                char* arg = &argv[i][5];
                if (startsWith(arg, "MainClass=")) {
                    if (!options->mainClass) {
                        options->mainClass = &arg[strlen("MainClass=")];
                    }
                } else if (startsWith(arg, "ResourcesPath=")) {
                    if (!options->resourcesPath) {
                        options->resourcesPath = &arg[strlen("ResourcesPath=")];
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
        options->commandLineArgs = &argv[options->commandLineArgsCount];
    }

    if (!options->resourcesPath) {
        options->resourcesPath = malloc(strlen(options->executablePath) + strlen(".resources") + 1);
        strcpy(options->resourcesPath, options->executablePath);
        strcat(options->resourcesPath, ".resources");
    }

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


