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

#ifndef NET_FD_H_included
#define NET_FD_H_included

/**
 * Wraps access to the int inside a java.io.FileDescriptor, taking care of throwing exceptions.
 */
class NetFd {
public:
    NetFd(JNIEnv* env, jobject fileDescriptor)
        : mEnv(env), mFileDescriptor(fileDescriptor), mFd(-1)
    {
    }

    bool isClosed() {
        mFd = jniGetFDFromFileDescriptor(mEnv, mFileDescriptor);
        bool closed = (mFd == -1);
        if (closed) {
            jniThrowException(mEnv, "java/net/SocketException", "Socket closed");
        }
        return closed;
    }

    int get() const {
        return mFd;
    }

private:
    JNIEnv* mEnv;
    jobject mFileDescriptor;
    int mFd;

    // Disallow copy and assignment.
    NetFd(const NetFd&);
    void operator=(const NetFd&);
};

/**
 * Used to retry syscalls that can return EINTR. This differs from TEMP_FAILURE_RETRY in that
 * it also considers the case where the reason for failure is that another thread called
 * Socket.close.
 */
#define NET_FAILURE_RETRY(fd, exp) ({               \
    typeof (exp) _rc;                               \
    do {                                            \
        _rc = (exp);                                \
        if (_rc == -1) {                            \
            if (fd.isClosed() || errno != EINTR) {  \
                break;                              \
            }                                       \
        }                                           \
    } while (_rc == -1);                            \
    _rc; })

#endif // NET_FD_H_included
