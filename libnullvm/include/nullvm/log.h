#ifndef NULLVM_LOG_H
#define NULLVM_LOG_H

#define LOG_LEVEL_TRACE 1
#define LOG_LEVEL_DEBUG 2
#define LOG_LEVEL_INFO 3
#define LOG_LEVEL_WARN 4
#define LOG_LEVEL_ERROR 5
#define LOG_LEVEL_NONE 6

extern jboolean nvmInitLog(Env* env);
extern void nvmLogTrace(Env* env, char* format, ...);
extern void nvmLogDebug(Env* env, char* format, ...);
extern void nvmLogInfo(Env* env, char* format, ...);
extern void nvmLogWarn(Env* env, char* format, ...);
extern void nvmLogError(Env* env, char* format, ...);

#endif

