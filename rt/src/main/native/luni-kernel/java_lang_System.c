#include <nullvm.h>
#include <sys/time.h>
#include <unistd.h>
#include <stdlib.h>
#include <limits.h>

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

static jboolean setProperty(Env* env, ObjectArray* props, jint index, char* name, char* value) {
    index <<= 1;
    props->values[index] = nvmNewStringUTF(env, name, -1);
    if (!props->values[index]) return FALSE;
    index++;
    props->values[index] = nvmNewStringUTF(env, value, -1);
    if (!props->values[index]) return FALSE;
    return TRUE;
}

ObjectArray* Java_java_lang_System_getPropertyList(Env* env, Class* clazz) {
    ObjectArray* props = nvmNewObjectArray(env, 24 * 2, java_lang_String, NULL, NULL);
    if (!props) return NULL;

    jint i = 0;
    if (!setProperty(env, props, i++, "java.boot.class.path", "")) return NULL;
    if (!setProperty(env, props, i++, "java.class.path", "")) return NULL;
    if (!setProperty(env, props, i++, "java.class.version", "46.0")) return NULL;
    if (!setProperty(env, props, i++, "java.compiler", "")) return NULL;
    if (!setProperty(env, props, i++, "java.ext.dirs", "")) return NULL;
    if (!setProperty(env, props, i++, "java.home", "")) return NULL;
    if (!setProperty(env, props, i++, "java.io.tmpdir", "/tmp")) return;
    if (getenv("LD_LIBRARY_PATH")) {
        if (!setProperty(env, props, i++, "java.library.path", getenv("LD_LIBRARY_PATH"))) return;
    } else {
        if (!setProperty(env, props, i++, "java.library.path", "")) return;
    }

    if (!setProperty(env, props, i++, "java.net.preferIPv6Addresses", "true")) return;

    if (!setProperty(env, props, i++, "java.vendor", "NullVM")) return;
    if (!setProperty(env, props, i++, "java.vendor.url", "http://www.nullvm.org/")) return;
    if (!setProperty(env, props, i++, "java.version", "0")) return;

    // TODO: Set java.vm.* and java.specification.*

    // TODO: Don't hard code these
    if (!setProperty(env, props, i++, "os.arch", "amd64")) return;
    if (!setProperty(env, props, i++, "os.name", "Linux")) return;
    if (!setProperty(env, props, i++, "os.version", "2.6.32-24-generic")) return;
    if (!setProperty(env, props, i++, "user.home", getenv("HOME"))) return;
    if (!setProperty(env, props, i++, "user.name", getenv("USER"))) return;
    char path[PATH_MAX];
    if (!setProperty(env, props, i++, "user.dir", getcwd(path, sizeof(path)))) return;
    if (!setProperty(env, props, i++, "file.separator", "/")) return;
    if (!setProperty(env, props, i++, "line.separator", "\n")) return;
    if (!setProperty(env, props, i++, "path.separator", ":")) return;
    if (!setProperty(env, props, i++, "file.encoding", "UTF-8")) return;
    if (!setProperty(env, props, i++, "user.language", "en")) return;
    if (!setProperty(env, props, i++, "user.region", "US")) return;
    // TODO: user.timezone

    return props;
}

