#include <stdio.h>

extern jboolean _logLevel;

#define TRACE(format, args...)  \
    if (_logLevel <= LOG_LEVEL_TRACE) { \
        fprintf(stderr, "[TRACE] "); \
        fprintf(stderr, format , ## args); \
    }

#define WARN(format, args...)  \
    if (_logLevel <= LOG_LEVEL_WARN) { \
        fprintf(stderr, "[WARN] "); \
        fprintf(stderr, format , ## args); \
    }

#define ERROR(format, args...)  \
    if (_logLevel <= LOG_LEVEL_ERROR) { \
        fprintf(stderr, "[ERROR] "); \
        fprintf(stderr, format , ## args); \
    }

