/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#if !defined(zip_h)
#define zip_h

#include "JNIHelp.h"
#include "JniException.h"
#include "UniquePtr.h"
#include "jni.h"
#include "zlib.h"
#include "zutil.h"

static void throwExceptionForZlibError(JNIEnv* env, const char* exceptionClassName, int error) {
    if (error == Z_MEM_ERROR) {
        jniThrowOutOfMemoryError(env, NULL);
    } else {
        jniThrowException(env, exceptionClassName, zError(error));
    }
}

class NativeZipStream {
public:
    UniquePtr<jbyte[]> input;
    int inCap;
    z_stream stream;

    NativeZipStream() : input(NULL), inCap(0), mDict(NULL) {
        // Let zlib use its default allocator.
        stream.opaque = Z_NULL;
        stream.zalloc = Z_NULL;
        stream.zfree = Z_NULL;
    }

    ~NativeZipStream() {
    }

    void setDictionary(JNIEnv* env, jbyteArray javaDictionary, int off, int len, bool inflate) {
        UniquePtr<jbyte[]> dictionaryBytes(new jbyte[len]);
        if (dictionaryBytes.get() == NULL) {
            jniThrowOutOfMemoryError(env, NULL);
            return;
        }
        env->GetByteArrayRegion(javaDictionary, off, len, &dictionaryBytes[0]);
        const Bytef* dictionary = reinterpret_cast<const Bytef*>(&dictionaryBytes[0]);
        int err;
        if (inflate) {
            err = inflateSetDictionary(&stream, dictionary, len);
        } else {
            err = deflateSetDictionary(&stream, dictionary, len);
        }
        if (err != Z_OK) {
            throwExceptionForZlibError(env, "java/lang/IllegalArgumentException", err);
            return;
        }
        mDict.reset(dictionaryBytes.release());
    }

    void setInput(JNIEnv* env, jbyteArray buf, jint off, jint len) {
        input.reset(new jbyte[len]);
        if (input.get() == NULL) {
            inCap = 0;
            jniThrowOutOfMemoryError(env, NULL);
            return;
        }
        inCap = len;
        if (buf != NULL) {
            env->GetByteArrayRegion(buf, off, len, &input[0]);
        }
        stream.next_in = reinterpret_cast<Bytef*>(&input[0]);
        stream.avail_in = len;
    }

private:
    UniquePtr<jbyte[]> mDict;

    // Disallow copy and assignment.
    NativeZipStream(const NativeZipStream&);
    void operator=(const NativeZipStream&);
};

static NativeZipStream* toNativeZipStream(jlong address) {
    return reinterpret_cast<NativeZipStream*>(static_cast<uintptr_t>(address));
}

#endif /* zip_h */
