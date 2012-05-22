#ifndef NULLVM_LOG_H
#define NULLVM_LOG_H

#define LOG(level, text) nvmLog(level, LOG_TAG, text)
#define LOGF(level, format, ...) nvmLogf(level, LOG_TAG, format, __VA_ARGS__)
#define TRACE(text) LOG(LOG_LEVEL_TRACE, text)
#define TRACEF(format, ...) LOGF(LOG_LEVEL_TRACE, format, __VA_ARGS__)
#define DEBUG(text) LOG(LOG_LEVEL_DEBUG, text)
#define DEBUGF(format, ...) LOGF(LOG_LEVEL_DEBUG, format, __VA_ARGS__)
#define INFO(text) LOG(LOG_LEVEL_INFO, text)
#define INFOF(format, ...) LOGF(LOG_LEVEL_INFO, format, __VA_ARGS__)
#define WARN(text) LOG(LOG_LEVEL_WARN, text)
#define WARNF(format, ...) LOGF(LOG_LEVEL_WARN, format, __VA_ARGS__)
#define ERROR(text) LOG(LOG_LEVEL_ERROR, text)
#define ERRORF(format, ...) LOGF(LOG_LEVEL_ERROR, format, __VA_ARGS__)
#define FATAL(text) LOG(LOG_LEVEL_FATAL, text)
#define FATALF(format, ...) LOGF(LOG_LEVEL_FATAL, format, __VA_ARGS__)
#define IS_TRACE_ENABLED (nvmLogIsTraceEnabled())
#define IS_DEBUG_ENABLED (nvmLogIsDebugEnabled())
#define IS_INFO_ENABLED (nvmLogIsInfoEnabled())
#define IS_WARN_ENABLED (nvmLogIsWarnEnabled())
#define IS_ERROR_ENABLED (nvmLogIsErrorEnabled())
#define IS_FATAL_ENABLED (nvmLogIsFatalEnabled())

/*
 * Log levels. The values must match the values used 
 * by the logging functions in Android's liblog. This
 * is why we start at 2 for TRACE.
 */
typedef enum LogLevel {
    LOG_LEVEL_TRACE = 2,
    LOG_LEVEL_DEBUG,
    LOG_LEVEL_INFO,
    LOG_LEVEL_WARN,
    LOG_LEVEL_ERROR,
    LOG_LEVEL_FATAL,
    LOG_LEVEL_SILENT,
} LogLevel;

extern jboolean nvmInitLog(Env* env);
extern jboolean nvmLogIsTraceEnabled();
extern jboolean nvmLogIsDebugEnabled();
extern jboolean nvmLogIsInfoEnabled();
extern jboolean nvmLogIsWarnEnabled();
extern jboolean nvmLogIsErrorEnabled();
extern jboolean nvmLogIsFatalEnabled();
extern int nvmLog(int level, const char* tag, const char* text);
extern int nvmLogf(int level, const char* tag, const char* format, ...);
extern int nvmLogfv(int level, const char* tag, const char* format, va_list ap);

#endif

