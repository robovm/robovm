/*
 * Copyright (C) 2010 The Android Open Source Project
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

#ifndef ASYNCHRONOUS_SOCKET_CLOSE_MONITOR_H_included
#define ASYNCHRONOUS_SOCKET_CLOSE_MONITOR_H_included

#include "ScopedPthreadMutexLock.h"
#include <pthread.h>

/**
 * AsynchronousSocketCloseMonitor helps implement Java's asynchronous Socket.close semantics.
 *
 * AsynchronousSocketCloseMonitor::init must be called before anything else.
 *
 * Every blocking network I/O operation must be surrounded by an AsynchronousSocketCloseMonitor
 * instance. For example:
 *
 *   {
 *     AsynchronousSocketCloseMonitor monitor(fd);
 *     byteCount = ::read(fd, buf, sizeof(buf));
 *   }
 *
 * To interrupt all threads currently blocked on file descriptor 'fd', call signalBlockedThreads:
 *
 *   AsynchronousSocketCloseMonitor::signalBlockedThreads(fd);
 */
class AsynchronousSocketCloseMonitor {
public:
    AsynchronousSocketCloseMonitor(int fd);
    ~AsynchronousSocketCloseMonitor();

    static void init();

    static void signalBlockedThreads(int fd);

private:
    AsynchronousSocketCloseMonitor* mPrev;
    AsynchronousSocketCloseMonitor* mNext;
    pthread_t mThread;
    int mFd;

    // Disallow copy and assignment.
    AsynchronousSocketCloseMonitor(const AsynchronousSocketCloseMonitor&);
    void operator=(const AsynchronousSocketCloseMonitor&);
};

#endif  // ASYNCHRONOUS_SOCKET_CLOSE_MONITOR_H_included
