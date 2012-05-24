/*
 * Copyright (C) 2012 RoboVM
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
#ifndef ROBOVM_LOG_H
#define ROBOVM_LOG_H

#define LOG(level, text) rvmLog(level, LOG_TAG, text)
#define LOGF(level, format, ...) rvmLogf(level, LOG_TAG, format, __VA_ARGS__)
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
#define IS_TRACE_ENABLED (rvmLogIsTraceEnabled())
#define IS_DEBUG_ENABLED (rvmLogIsDebugEnabled())
#define IS_INFO_ENABLED (rvmLogIsInfoEnabled())
#define IS_WARN_ENABLED (rvmLogIsWarnEnabled())
#define IS_ERROR_ENABLED (rvmLogIsErrorEnabled())
#define IS_FATAL_ENABLED (rvmLogIsFatalEnabled())

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

extern jboolean rvmInitLog(Env* env);
extern jboolean rvmLogIsTraceEnabled();
extern jboolean rvmLogIsDebugEnabled();
extern jboolean rvmLogIsInfoEnabled();
extern jboolean rvmLogIsWarnEnabled();
extern jboolean rvmLogIsErrorEnabled();
extern jboolean rvmLogIsFatalEnabled();
extern int rvmLog(int level, const char* tag, const char* text);
extern int rvmLogf(int level, const char* tag, const char* format, ...);
extern int rvmLogfv(int level, const char* tag, const char* format, va_list ap);

#endif

