#include <string.h>
#include <nullvm.h>

#define LOG_BUF_SIZE 1024
#define IS_ENABLED(level) (_logLevel < LOG_LEVEL_SILENT && _logLevel <= level)

jint _logLevel = LOG_LEVEL_ERROR;

static const char* levels[] = {
  "TRACE",
  "DEBUG",
  "INFO",
  "WARN",
  "ERROR",
  "FATAL"
};

static const char* level2String(int level) {
    if (level < LOG_LEVEL_TRACE) level = LOG_LEVEL_TRACE;
    return levels[level - LOG_LEVEL_TRACE];
}

static inline int logwrite(int level, const char* tag, const char* text) {
    return fprintf(stderr, "[%s] %s: %s\n", level2String(level), tag, text); \
}

jboolean nvmInitLog(Env* env) {
    _logLevel = env->vm->options->logLevel == 0 ? LOG_LEVEL_ERROR : env->vm->options->logLevel;
    return TRUE;
}

int nvmLog(int level, const char* tag, const char* text) {
    if (IS_ENABLED(level)) {
        return logwrite(level, tag, text);
    }
    return 0;
}

int nvmLogf(int level, const char* tag, const char* format, ...) {
    va_list ap;
    char buf[LOG_BUF_SIZE];
    if (IS_ENABLED(level)) {
        va_start(ap, format);
        vsnprintf(buf, LOG_BUF_SIZE, format, ap);
        va_end(ap);
        return logwrite(level, tag, buf);
    }
    return 0;
}

int nvmLogfv(int level, const char* tag, const char* format, va_list ap) {
    char buf[LOG_BUF_SIZE];
    if (IS_ENABLED(level)) {
        vsnprintf(buf, LOG_BUF_SIZE, format, ap);
        return logwrite(level, tag, buf);
    }
    return 0;
}

/*
 * Implementations of log functions used by the Android code. These just forward to 
 * the NullVM log functions.
 */
int __android_log_write(int prio, const char* tag, const char* text) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    return nvmLog(prio, realtag, text);
}

int __android_log_print(int prio, const char* tag,  const char* fmt, ...) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    va_list ap;
    va_start(ap, fmt);
    int n = nvmLogfv(prio, realtag, fmt, ap);
    va_end(ap);
    return n;
}
void __android_log_assert(const char *cond, const char *tag,
			  const char *fmt, ...) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    va_list ap;
    va_start(ap, fmt);
    nvmLogfv(LOG_LEVEL_FATAL, realtag, fmt, ap);
    va_end(ap);
    nvmAbort(NULL);
}

