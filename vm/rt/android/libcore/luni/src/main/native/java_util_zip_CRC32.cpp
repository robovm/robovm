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

#define LOG_TAG "CRC32"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedPrimitiveArray.h"
#include "jni.h"
#include "zlib.h"

extern "C" jlong Java_java_util_zip_CRC32_updateImpl(JNIEnv* env, jobject, jbyteArray byteArray, int off, int len, jlong crc) {
    ScopedByteArrayRO bytes(env, byteArray);
    if (bytes.get() == NULL) {
        return 0;
    }
    jlong result = crc32(crc, reinterpret_cast<const Bytef*>(bytes.get() + off), len);
    return result;
}

extern "C" jlong Java_java_util_zip_CRC32_updateByteImpl(JNIEnv*, jobject, jbyte val, jlong crc) {
    return crc32(crc, reinterpret_cast<const Bytef*>(&val), 1);
}

