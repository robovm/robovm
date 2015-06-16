/*
 * Copyright (C) 2012 RoboVM AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include <robovm.h>
#include <string.h>
#include <stdio.h>
#include <stdarg.h>
#include <ctype.h>
#include <dlfcn.h>
#include <signal.h>
#if defined(IOS) && (defined(RVM_ARMV7) || defined(RVM_THUMBV7))
#   include <fenv.h>
#endif
#include "private.h"
#include "utlist.h"
#include <unistd.h>

#define LOG_TAG "core.init"
ClassLoader* systemClassLoader = NULL;
static Class* java_lang_Daemons = NULL;
static Method* java_lang_Daemons_start = NULL;

extern int registerCoreLibrariesJni(JNIEnv* env);
#ifdef DARWIN
  extern void registerJARURLProtocol(void);
#endif

static inline jint startsWith(char* s, char* prefix) {
    return s && strncasecmp(s, prefix, strlen(prefix)) == 0;
}

static char* trim(char* str) {
    char* orig = str;

    // Trim leading space
    while (isspace(*str)) str++;

    if (*str != 0) {
        // Trim trailing space
        char* end = str + strlen(str) - 1;
        while (end > str && isspace(*end)) end--;
        // Write new null terminator
        *(end + 1) = 0;
    }

    if (str != orig) {
        memmove(orig, str, strlen(str) + 1);
    }
    return orig;
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

static jboolean ignoreSignal(int signo) {
    if (signal(signo, SIG_IGN) == SIG_ERR) {
        return FALSE;
    }
    return TRUE;
}

static jboolean initClasspathEntries(Env* env, char* basePath, char** raw, ClasspathEntry** first) {
    jint i = 0;
    while (raw[i]) {
        ClasspathEntry* entry = rvmAllocateMemoryAtomicUncollectable(env, sizeof(ClasspathEntry));
        if (!entry) return FALSE;
        absolutize(basePath, raw[i], entry->jarPath);
        LL_APPEND(*first, entry);
        i++;
    }

    return TRUE;
}

static void parseArg(char* arg, Options* options) {
    if (startsWith(arg, "log=trace")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_TRACE;
    } else if (startsWith(arg, "log=debug")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_DEBUG;
    } else if (startsWith(arg, "log=info")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_INFO;
    } else if (startsWith(arg, "log=warn")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_WARN;
    } else if (startsWith(arg, "log=error")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_ERROR;
    } else if (startsWith(arg, "log=fatal")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_FATAL;
    } else if (startsWith(arg, "log=silent")) {
        if (options->logLevel == 0) options->logLevel = LOG_LEVEL_SILENT;
    } else if (startsWith(arg, "mx") || startsWith(arg, "ms")) {
        char* unit;
        jlong n = strtol(&arg[2], &unit, 10);
        if (n > 0) {
            if (unit[0] != '\0') {
                switch (unit[0]) {
                case 'g':
                case 'G':
                    n *= 1024 * 1024 * 1024;
                    break;
                case 'm':
                case 'M':
                    n *= 1024 * 1024;
                    break;
                case 'k':
                case 'K':
                    n *= 1024;
                    break;
                }
            }
        }
        if (startsWith(arg, "mx")) {
            options->maxHeapSize = n;
        } else {
            options->initialHeapSize = n;
        }
    } else if (startsWith(arg, "MainClass=")) {
        if (!options->mainClass) {
            char* s = strdup(&arg[10]);
            jint j;
            for (j = 0; s[j] != 0; j++) {
                if (s[j] == '.') s[j] = '/';
            }
            options->mainClass = s;
        }
    } else if (startsWith(arg, "EnableGCHeapStats")) {
        options->enableGCHeapStats = TRUE;
    } else if (startsWith(arg, "EnableHooks")) {
        options->enableHooks = TRUE;
    } else if (startsWith(arg, "WaitForResume")) {
        options->waitForResume = TRUE;
        options->enableHooks = TRUE; // WaitForResume also enables hooks
    } else if (startsWith(arg, "PrintPID=")) {
        options->printPID = TRUE;
        if (!options->pidFile) {
            char* s = strdup(&arg[9]);
            options->pidFile = s;
        }
    } else if (startsWith(arg, "PrintPID")) {
        options->printPID = TRUE;
    } else if (startsWith(arg, "PrintDebugPort=")) {
        options->printDebugPort = TRUE;
        if (!options->debugPortFile) {
            char* s = strdup(&arg[15]);
            options->debugPortFile = s;
        }
    } else if (startsWith(arg, "PrintDebugPort")) {
        options->printDebugPort = TRUE;
    } else if (startsWith(arg, "D")) {
        char* s = strdup(&arg[1]);
        // Split the arg string on the '='. 'key' will have the
        // part on the left and 's' will have the value on the right
        char* key = strsep(&s, "=");

        SystemProperty* property = calloc(1, sizeof(SystemProperty));
        property->key = key;
        property->value = s;
        DL_APPEND(options->properties, property);
    } else if (startsWith(arg, "exepath=")) {
        fprintf(stderr, "processing executable path.");
        strncpy( options->executablePath, &arg[8], PATH_MAX);
    }
}

jboolean rvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreRvmArgs) {
    char path[PATH_MAX];
    if (!realpath(argv[0], path)) {
        return FALSE;
    }

    strncpy(options->executablePath, path, PATH_MAX);

    jint i = strlen(path);
    while (i >= 0 && path[i] != '/') {
        path[i--] = '\0';
    }
    if (i >= 0 && path[i] == '/') {
        path[i] = '\0';
    }

    strcpy(options->basePath, path);

    // Look for a robovm.ini next to the executable
    strcat(path, "/robovm.ini");
    FILE* f = fopen(path, "r");
    if (f) {
        char* line = NULL;
        size_t linecap = 0;
        ssize_t linelen;
        while ((linelen = getline(&line, &linecap, f)) > 0) {
            line = trim(line);
            if (strlen(line) > 0 && line[0] != '#') {
                parseArg(line, options);
            }
        }
        if (line) {
            free(line);
        }
        fclose(f);
    }

    jint firstJavaArg = 1;
    for (i = 1; i < argc; i++) {
        if (startsWith(argv[i], "-rvm:")) {
            if (!ignoreRvmArgs) {
                char* arg = &argv[i][5];
                parseArg(arg, options);
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

VM* rvmCreateVM(Options* options) {
    VM* vm = gcAllocate(sizeof(VM));
    if (!vm) return NULL;
    vm->options = options;
    rvmInitJavaVM(vm);
    return vm;
}

Env* rvmCreateEnv(VM* vm) {
    Env* env = gcAllocateUncollectable(vm->options->enableHooks ? sizeof(DebugEnv) : sizeof(Env));
    if (!env) return NULL;
    env->vm = vm;
    if(vm->options->enableHooks) {
        DebugEnv* debugEnv = (DebugEnv*)env;
        debugEnv->reqId = 0;
        debugEnv->suspended = FALSE;
    }
    rvmInitJNIEnv(env);
    return env;
}

Env* rvmStartup(Options* options) {
    // TODO: Error handling

    TRACE("Initializing logging");
    if (!rvmInitLog(options)) return NULL;

#if defined(IOS) && (defined(RVM_ARMV7) || defined(RVM_THUMBV7))
    // Enable IEEE-754 denormal support.
    // Without this the VFP treats numbers that are close to zero as zero itself.
    // See http://developer.apple.com/library/ios/#technotes/tn2293/_index.html.
    fenv_t fenv;
    fegetenv(&fenv);
    fenv.__fpscr &= ~__fpscr_flush_to_zero;
    fesetenv(&fenv);
#endif
    // print PID if it was requested
    if(options->printPID) {
        pid_t pid = getpid();
        if(options->pidFile) {
            FILE* f = fopen(options->pidFile, "w");
            if (!f) return NULL;
            fprintf(f, "%d", pid);
            fclose(f);
        } else {
            fprintf(stderr, "[DEBUG] %s: pid=%d\n", LOG_TAG, pid);
        }
    }

    // setup the TCP channel socket and wait
    // for the debugger to connect
    if(options->enableHooks) {
        if(!rvmHookSetupTCPChannel(options)) return NULL;
        if(!rvmHookHandshake(options)) return NULL;
    }

    TRACE("Initializing GC");
    if (!initGC(options)) return NULL;

    // Ignore SIGPIPE signals. SIGPIPE interrupts write() calls which we don't
    // want. Dalvik does this too in dalvikvm/Main.cpp.
    if (!ignoreSignal(SIGPIPE)) return NULL;

    // Ignore SIGXFSZ signals. SIGXFSZ is raised when writing beyond the RLIMIT_FSIZE
    // of the current process (at least on Darwin) using pwrite().
    if (!ignoreSignal(SIGXFSZ)) return NULL;

    VM* vm = rvmCreateVM(options);
    if (!vm) return NULL;

    Env* env = rvmCreateEnv(vm);
    if (!env) return NULL;
    // TODO: What if we can't allocate Env?

    if (!initClasspathEntries(env, options->basePath, options->rawBootclasspath, &options->bootclasspath)) return NULL;
    if (!initClasspathEntries(env, options->basePath, options->rawClasspath, &options->classpath)) return NULL;

    // Call init on modules
    TRACE("Initializing classes");
    if (!rvmInitClasses(env)) return NULL;
    TRACE("Initializing memory");
    if (!rvmInitMemory(env)) return NULL;
    TRACE("Initializing methods");
    if (!rvmInitMethods(env)) return NULL;
    TRACE("Initializing strings");
    if (!rvmInitStrings(env)) return NULL;
    TRACE("Initializing monitors");
    if (!rvmInitMonitors(env)) return NULL;
    TRACE("Initializing proxy");
    if (!rvmInitProxy(env)) return NULL;
    TRACE("Initializing threads");
    if (!rvmInitThreads(env)) return NULL;
    TRACE("Initializing attributes");
    if (!rvmInitAttributes(env)) return NULL;
    TRACE("Initializing primitive wrapper classes");
    if (!rvmInitPrimitiveWrapperClasses(env)) return NULL;
    TRACE("Initializing exceptions");
    if (!rvmInitExceptions(env)) return NULL;
    TRACE("Initializing signals");
    if (!rvmInitSignals(env)) return NULL;
    TRACE("Initializing JNI");
    if (!rvmInitJNI(env)) return NULL;

    // Initialize the RoboVM rt JNI code
//    RT_JNI_OnLoad(&vm->javaVM, NULL);
    // Initialize the dalvik rt JNI code
    TRACE("Initializing dalvik's runtime JNI code");
    registerCoreLibrariesJni((JNIEnv*) env);

#ifdef DARWIN
    TRACE("Initializing JAR NSURLProtocol");
    registerJARURLProtocol();
#endif

    TRACE("Creating system ClassLoader");
    systemClassLoader = rvmGetSystemClassLoader(env);
    if (rvmExceptionOccurred(env)) goto error_system_ClassLoader;
    env->currentThread->threadObj->contextClassLoader = systemClassLoader;

    TRACE("Initialization done");
    env->vm->initialized = TRUE;
    
    // Start Daemons
    TRACE("Starting Daemons");
    java_lang_Daemons = rvmFindClassUsingLoader(env, "java/lang/Daemons", NULL);
    if (!java_lang_Daemons) goto error_daemons;
    java_lang_Daemons_start = rvmGetClassMethod(env, java_lang_Daemons, "start", "()V");
    if (!java_lang_Daemons_start) goto error_daemons;
    rvmCallVoidClassMethod(env, java_lang_Daemons, java_lang_Daemons_start);
    if (rvmExceptionCheck(env)) goto error_daemons;
    TRACE("Daemons started");

    jboolean errorDuringSetup = FALSE;

    //If our options has any properties, let's set them before we call our main.
    if (options->properties) {
        //First, find java.lang.System, which has the setProperty method.
        Class* clazz = rvmFindClassUsingLoader(env, "java/lang/System", NULL);
        if (clazz) {
            //Get the setProperty method.
            Method* method = rvmGetClassMethod(env, clazz, "setProperty", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");
            if (method) {
                SystemProperty* property = options->properties;

                //Go through all of our properties and add each one in turn by calling setProperty.
                while (property != NULL) {
                    Object* key = NULL;
                    Object* value = NULL;
                    //The key is not allowed to be an empty string, so don't set it if we don't get a key.
                    if(property->key && strlen(property->key) > 0) {
                        key = rvmNewStringUTF(env, property->key, -1);
                    } else {
                        FATAL("Cannot have empty key in system property.");
                        errorDuringSetup = TRUE;
                        break;
                    }
                    if (property->value) {
                        value = rvmNewStringUTF(env, property->value, -1);
                    } else {
                        value = rvmNewStringUTF(env, "", -1);
                    }

                    if (key && value) {
                        rvmCallObjectClassMethod(env, clazz, method, key, value);
                    } else {
                        if (!key) {
                            FATALF("Error creating string from system property key: %s", property->key);
                        }
                        if (!value) {
                            FATALF("Error creating string from system property value: %s", property->value);
                        }
                        errorDuringSetup = TRUE;
                        break;
                    }
                    property = property->next; //Advance to the next property.
                }
            }
        }
    }

    return (errorDuringSetup) ? NULL : env;

error_daemons:
error_system_ClassLoader:
    rvmDetachCurrentThread(env->vm, TRUE, FALSE);

    return NULL;
}

jboolean rvmRun(Env* env) {
    Options* options = env->vm->options;
    Class* clazz = NULL;

    rvmHookBeforeAppEntryPoint(env, options->mainClass);
    clazz = rvmFindClassUsingLoader(env, options->mainClass, systemClassLoader);
    if (clazz) {
        Method* method = rvmGetClassMethod(env, clazz, "main", "([Ljava/lang/String;)V");
        if (method) {
            ObjectArray* args = rvmNewObjectArray(env, options->commandLineArgsCount, java_lang_String, NULL, NULL);
            if (args) {
                jint i = 0;
                for (i = 0; i < args->length; i++) {
                    // TODO: Don't assume modified UTF-8
                    args->values[i] = rvmNewStringUTF(env, options->commandLineArgs[i], -1);
                    if (!args->values[i]) {
                        args = NULL;
                        break;
                    }
                }
                if (args) {
                    rvmCallVoidClassMethod(env, clazz, method, args);
                }
            }
        }
    }

    return rvmDestroy(env->vm);
}

jboolean rvmDestroy(VM * vm) {
    Env * env;
    if (JNI_OK != rvmAttachCurrentThread(vm, &env, NULL, NULL) ) {
        WARN("rvmDestroy() failed to attach current thread.");
        return FALSE;
    }
    Object* throwable = rvmExceptionOccurred(env);
    rvmDetachCurrentThread(env->vm, TRUE, FALSE);

    rvmJoinNonDaemonThreads(env);

    return throwable == NULL ? TRUE : FALSE;
}

void rvmShutdown(Env* env, jint code) {
    // TODO: Cleanup, stop threads.
    exit(code);
}

void rvmAbort(char* format, ...) {
    va_list args;
    if (format) {
        va_start(args, format);
        vfprintf(stderr, format, args);
        va_end(args);
        fprintf(stderr, "\n");
    }
    abort();
}

DynamicLib* rvmOpenDynamicLib(Env* env, const char* file, char** errorMsg) {
    *errorMsg = NULL;
    DynamicLib* dlib = NULL;

    void* handle = dlopen(file, RTLD_LOCAL | RTLD_LAZY);
    if (!handle) {
        *errorMsg = dlerror();
        TRACEF("Failed to load dynamic library '%s': %s", file, *errorMsg);
        return NULL;
    }

    TRACEF("Opening dynamic library '%s'", file);

    dlib = rvmAllocateMemoryAtomicUncollectable(env, sizeof(DynamicLib));
    if (!dlib) {
        dlclose(handle);
        return NULL;
    }

    dlib->handle = handle;

    return dlib;
}

void rvmCloseDynamicLib(Env* env, DynamicLib* lib) {
    dlclose(lib->handle);
    rvmFreeMemoryUncollectable(env, lib);
}

jboolean rvmHasDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs) {
    DynamicLib* dlib = NULL;
    LL_FOREACH(libs, dlib) {
        if (dlib->handle == lib->handle) {
            return TRUE;
        }
    }
    return FALSE;
}

void rvmAddDynamicLib(Env* env, DynamicLib* lib, DynamicLib** libs) {
    LL_APPEND(*libs, lib);
}

void* rvmFindDynamicLibSymbol(Env* env, DynamicLib* libs, const char* symbol, jboolean searchAll) {
    TRACEF("Searching for symbol '%s'", symbol);

    DynamicLib* dlib = NULL;
    LL_FOREACH(libs, dlib) {
        void* v = dlsym(dlib->handle, symbol);
        if (v) return v;
        if (!searchAll) return NULL;
    }
    return NULL;
}

