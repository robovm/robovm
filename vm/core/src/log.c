/*
 * Copyright (C) 2012 Trillian Mobile AB
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
#include <string.h>
#include <robovm.h>

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

jboolean rvmInitLog(Options* options) {
    _logLevel = options->logLevel == 0 ? LOG_LEVEL_ERROR : options->logLevel;
    return TRUE;
}

jboolean rvmLogIsTraceEnabled() {
    return IS_ENABLED(LOG_LEVEL_TRACE) ? TRUE : FALSE;
}

jboolean rvmLogIsDebugEnabled() {
    return IS_ENABLED(LOG_LEVEL_DEBUG) ? TRUE : FALSE;
}

jboolean rvmLogIsInfoEnabled() {
    return IS_ENABLED(LOG_LEVEL_INFO) ? TRUE : FALSE;
}

jboolean rvmLogIsWarnEnabled() {
    return IS_ENABLED(LOG_LEVEL_WARN) ? TRUE : FALSE;
}

jboolean rvmLogIsErrorEnabled() {
    return IS_ENABLED(LOG_LEVEL_ERROR) ? TRUE : FALSE;
}

jboolean rvmLogIsFatalEnabled() {
    return IS_ENABLED(LOG_LEVEL_FATAL) ? TRUE : FALSE;
}

int rvmLog(int level, const char* tag, const char* text) {
    if (IS_ENABLED(level)) {
        return logwrite(level, tag, text);
    }
    return 0;
}

int rvmLogf(int level, const char* tag, const char* format, ...) {
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

int rvmLogfv(int level, const char* tag, const char* format, va_list ap) {
    char buf[LOG_BUF_SIZE];
    if (IS_ENABLED(level)) {
        vsnprintf(buf, LOG_BUF_SIZE, format, ap);
        return logwrite(level, tag, buf);
    }
    return 0;
}

/*
 * Implementations of log functions used by the Android code. These just forward to 
 * the RoboVM log functions.
 */
int __android_log_write(int prio, const char* tag, const char* text) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    return rvmLog(prio, realtag, text);
}

int __android_log_print(int prio, const char* tag,  const char* fmt, ...) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    va_list ap;
    va_start(ap, fmt);
    int n = rvmLogfv(prio, realtag, fmt, ap);
    va_end(ap);
    return n;
}
void __android_log_assert(const char *cond, const char *tag,
			  const char *fmt, ...) {
    char realtag[128] = "android.";
    strcat(realtag, tag);
    va_list ap;
    va_start(ap, fmt);
    rvmLogfv(LOG_LEVEL_FATAL, realtag, fmt, ap);
    va_end(ap);
    rvmAbort(NULL);
}

