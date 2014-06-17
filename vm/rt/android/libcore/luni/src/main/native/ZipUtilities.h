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

#ifndef ZIP_UTILITIES_H_included
#define ZIP_UTILITIES_H_included

#include "UniquePtr.h"
#include "jni.h"
#include "zlib.h"

class NativeZipStream {
public:
    UniquePtr<jbyte[]> input;
    int inCap;
    z_stream stream;

    NativeZipStream();
    ~NativeZipStream();
    void setDictionary(JNIEnv* env, jbyteArray javaDictionary, int off, int len, bool inflate);
    void setInput(JNIEnv* env, jbyteArray buf, jint off, jint len);

private:
    UniquePtr<jbyte[]> mDict;

    // Disallow copy and assignment.
    NativeZipStream(const NativeZipStream&);
    void operator=(const NativeZipStream&);
};

NativeZipStream* toNativeZipStream(jlong address);

void throwExceptionForZlibError(JNIEnv* env, const char* exceptionClassName, int error,
        NativeZipStream* stream);

#endif  // ZIP_UTILITIES_H_included
