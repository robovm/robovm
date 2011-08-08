#include <nullvm.h>
#include <sys/time.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <limits.h>
#include <pwd.h>
#include <sys/utsname.h>
#include <string.h>

jint Java_java_lang_System_identityHashCode(JNIEnv* env, jclass clazz, jobject o) {
    return (jint) o;
}

jlong Java_java_lang_System_currentTimeMillis(JNIEnv* env, jclass clazz) {
    struct timeval tv;
    gettimeofday(&tv, (struct timezone *) NULL);
    jlong millis = tv.tv_sec * 1000LL + tv.tv_usec / 1000;
    return millis;
}

jstring Java_java_lang_System_getEncoding(JNIEnv* env, jclass clazz, jint type) {
    if (type == 1) {
        return (*env)->NewStringUTF(env, "UTF-8");
    }
    return NULL;
}

static jboolean setProperty(Env* env, ObjectArray** props, jint index, char* name, char* value) {
    if (*props == NULL || index >= (*props)->length / 2) {
        jint length = *props == NULL ? 10 : (*props)->length * 2;
        ObjectArray* newProps = nvmNewObjectArray(env, length, java_lang_String, NULL, NULL);
        if (!newProps) return FALSE;
        if (*props) {
            jint i;
            for (i = 0; i < (*props)->length; i++) {
                newProps->values[i] = (*props)->values[i];
            }
        }
        *props = newProps;
    }
    Object* n = nvmNewStringUTF(env, name, -1);
    if (!n) return FALSE;
    Object* v = nvmNewStringUTF(env, value, -1);
    if (!v) return FALSE;
    index <<= 1;
    (*props)->values[index++] = n;
    (*props)->values[index++] = v;
    return TRUE;
}

static char* createClasspathFromClasspathEntries(Env* env, ClasspathEntry* first) {
    jint length = 0;
    ClasspathEntry* entry = first;
    while (entry) {
        length += strlen(entry->jarPath);
        entry = entry->next;
        if (entry) length++; // Make room for the :
    }

    char* p = nvmAllocateMemory(env, length + 1);
    if (!p) return NULL;

    entry = first;
    while (entry) {
        strcat(p, entry->jarPath);
        entry = entry->next;
        if (entry) strcat(p, ":");
    }

    return p;
}

ObjectArray* Java_java_lang_System_getPropertyList(Env* env, Class* clazz) {
    struct passwd* pwd = getpwuid(getuid());
    if (!pwd) return NULL; // TODO: Throw exception?
    struct utsname os;
    if (uname(&os) == -1) return NULL; // TODO: Throw exception?

    char* bootclasspath = createClasspathFromClasspathEntries(env, env->vm->options->bootclasspath);
    if (!bootclasspath) return NULL;
    char* classpath = createClasspathFromClasspathEntries(env, env->vm->options->classpath);
    if (!classpath) return NULL;

    ObjectArray* props = NULL;
    jint i = 0;
    if (!setProperty(env, &props, i++, "java.boot.class.path", bootclasspath)) return NULL;
    if (!setProperty(env, &props, i++, "java.class.path", classpath)) return NULL;
    if (!setProperty(env, &props, i++, "java.class.version", "46.0")) return NULL;
    if (!setProperty(env, &props, i++, "java.compiler", "")) return NULL;
    if (!setProperty(env, &props, i++, "java.ext.dirs", "")) return NULL;
    if (!setProperty(env, &props, i++, "java.home", "")) return NULL;
    if (!setProperty(env, &props, i++, "java.io.tmpdir", "/tmp")) return NULL;
    if (getenv("LD_LIBRARY_PATH")) {
        if (!setProperty(env, &props, i++, "java.library.path", getenv("LD_LIBRARY_PATH"))) return NULL;
    } else {
        if (!setProperty(env, &props, i++, "java.library.path", "")) return NULL;
    }

    if (!setProperty(env, &props, i++, "java.net.preferIPv6Addresses", "true")) return NULL;

    if (!setProperty(env, &props, i++, "java.vendor", "NullVM")) return NULL;
    if (!setProperty(env, &props, i++, "java.vendor.url", "http://www.nullvm.org/")) return NULL;
    if (!setProperty(env, &props, i++, "java.version", "0")) return NULL;

    // TODO: Set java.vm.* and java.specification.*

    if (!setProperty(env, &props, i++, "os.arch", os.machine)) return NULL;
    if (!setProperty(env, &props, i++, "os.name", os.sysname)) return NULL;
    if (!setProperty(env, &props, i++, "os.version", os.release)) return NULL;
    if (!setProperty(env, &props, i++, "user.home", pwd->pw_dir)) return NULL;
    if (!setProperty(env, &props, i++, "user.name", pwd->pw_name)) return NULL;
    char path[PATH_MAX];
    if (!setProperty(env, &props, i++, "user.dir", getcwd(path, sizeof(path)))) return NULL;

    // TODO: Don't hard code these
    if (!setProperty(env, &props, i++, "file.separator", "/")) return NULL;
    if (!setProperty(env, &props, i++, "line.separator", "\n")) return NULL;
    if (!setProperty(env, &props, i++, "path.separator", ":")) return NULL;
    if (!setProperty(env, &props, i++, "file.encoding", "UTF-8")) return NULL;
    if (!setProperty(env, &props, i++, "user.language", "en")) return NULL;
    if (!setProperty(env, &props, i++, "user.region", "US")) return NULL;
    // TODO: user.timezone

    return props;
}

Object* Java_java_lang_System_mapLibraryName(Env* env, Class* c, Object* userLibName) {
    if (!userLibName) return NULL;
    char* libName = nvmGetStringUTFChars(env, userLibName);
    char* result = nvmAllocateMemory(env, strlen(libName) + 6 + 1);
    if (!result) return NULL;
    strcpy(result, "lib");
    strcat(result, libName);
    strcat(result, ".so");
    return nvmNewStringUTF(env, result, -1);
}

