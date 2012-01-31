#include <nullvm.h>

#define LOG(level, format)  \
    va_list args; \
    va_start(args, format); \
    if (_logLevel <= LOG_LEVEL_ ## level) { \
        fprintf(stderr, "[" #level "] "); \
        vfprintf(stderr, format , args); \
    }


jint _logLevel = LOG_LEVEL_ERROR;

jboolean nvmInitLog(Env* env) {
    _logLevel = env->vm->options->logLevel == 0 ? LOG_LEVEL_ERROR : env->vm->options->logLevel;
    return TRUE;
}

void nvmLogTrace(Env* env, char* format, ...) {
    LOG(TRACE, format);
}

void nvmLogDebug(Env* env, char* format, ...) {
    LOG(DEBUG, format);
}

void nvmLogInfo(Env* env, char* format, ...) {
    LOG(INFO, format);
}

void nvmLogWarn(Env* env, char* format, ...) {
    LOG(WARN, format);
}

void nvmLogError(Env* env, char* format, ...) {
    LOG(ERROR, format);
}

