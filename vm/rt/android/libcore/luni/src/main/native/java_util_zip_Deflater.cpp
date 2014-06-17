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

#define LOG_TAG "Deflater"

#include "JniConstants.h"
#include "JniException.h"
#include "ScopedPrimitiveArray.h"
#include "ZipUtilities.h"
#include "zutil.h" // For DEF_WBITS and DEF_MEM_LEVEL.

extern "C" void Java_java_util_zip_Deflater_setDictionaryImpl(JNIEnv* env, jobject, jbyteArray dict, int off, int len, jlong handle) {
    toNativeZipStream(handle)->setDictionary(env, dict, off, len, false);
}

extern "C" jlong Java_java_util_zip_Deflater_getTotalInImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.total_in;
}

extern "C" jlong Java_java_util_zip_Deflater_getTotalOutImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.total_out;
}

extern "C" jint Java_java_util_zip_Deflater_getAdlerImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.adler;
}

extern "C" jlong Java_java_util_zip_Deflater_createStream(JNIEnv * env, jobject, jint level, jint strategy, jboolean noHeader) {
    UniquePtr<NativeZipStream> jstream(new NativeZipStream);
    if (jstream.get() == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return -1;
    }

    /*
     * See zlib.h for documentation of the deflateInit2 windowBits and memLevel parameters.
     *
     * zconf.h says the "requirements for deflate are (in bytes):
     *         (1 << (windowBits+2)) +  (1 << (memLevel+9))
     * that is: 128K for windowBits=15  +  128K for memLevel = 8  (default values)
     * plus a few kilobytes for small objects."
     */
    int windowBits = noHeader ? -DEF_WBITS : DEF_WBITS;
    int memLevel = DEF_MEM_LEVEL;
    int err = deflateInit2(&jstream->stream, level, Z_DEFLATED, windowBits, memLevel, strategy);
    if (err != Z_OK) {
        throwExceptionForZlibError(env, "java/lang/IllegalArgumentException", err, jstream.get());
        return -1;
    }
    return reinterpret_cast<uintptr_t>(jstream.release());
}

extern "C" void Java_java_util_zip_Deflater_setInputImpl(JNIEnv* env, jobject, jbyteArray buf, jint off, jint len, jlong handle) {
    toNativeZipStream(handle)->setInput(env, buf, off, len);
}

extern "C" jint Java_java_util_zip_Deflater_deflateImpl(JNIEnv* env, jobject recv, jbyteArray buf, int off, int len, jlong handle, int flushStyle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    ScopedByteArrayRW out(env, buf);
    if (out.get() == NULL) {
        return -1;
    }
    stream->stream.next_out = reinterpret_cast<Bytef*>(out.get() + off);
    stream->stream.avail_out = len;

    Bytef* initialNextIn = stream->stream.next_in;
    Bytef* initialNextOut = stream->stream.next_out;

    int err = deflate(&stream->stream, flushStyle);
    switch (err) {
    case Z_OK:
        break;
    case Z_STREAM_END:
        static jfieldID finished = env->GetFieldID(JniConstants::deflaterClass, "finished", "Z");
        env->SetBooleanField(recv, finished, JNI_TRUE);
        break;
    case Z_BUF_ERROR:
        // zlib reports this "if no progress is possible (for example avail_in or avail_out was
        // zero) ... Z_BUF_ERROR is not fatal, and deflate() can be called again with more
        // input and more output space to continue compressing".
        break;
    default:
        throwExceptionForZlibError(env, "java/util/zip/DataFormatException", err, stream);
        return -1;
    }

    jint bytesRead = stream->stream.next_in - initialNextIn;
    jint bytesWritten = stream->stream.next_out - initialNextOut;

    static jfieldID inReadField = env->GetFieldID(JniConstants::deflaterClass, "inRead", "I");
    jint inReadValue = env->GetIntField(recv, inReadField);
    inReadValue += bytesRead;
    env->SetIntField(recv, inReadField, inReadValue);
    return bytesWritten;
}

extern "C" void Java_java_util_zip_Deflater_endImpl(JNIEnv*, jobject, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    deflateEnd(&stream->stream);
    delete stream;
}

extern "C" void Java_java_util_zip_Deflater_resetImpl(JNIEnv* env, jobject, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    int err = deflateReset(&stream->stream);
    if (err != Z_OK) {
        throwExceptionForZlibError(env, "java/lang/IllegalArgumentException", err, stream);
    }
}

extern "C" void Java_java_util_zip_Deflater_setLevelsImpl(JNIEnv* env, jobject, int level, int strategy, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    // The deflateParams documentation says that avail_out must never be 0 because it may be
    // necessary to flush, but the Java API ensures that we only get here if there's nothing
    // to flush. To be on the safe side, make sure that we're not pointing to a no longer valid
    // buffer.
    stream->stream.next_out = reinterpret_cast<Bytef*>(NULL);
    stream->stream.avail_out = 0;
    int err = deflateParams(&stream->stream, level, strategy);
    if (err != Z_OK) {
        throwExceptionForZlibError(env, "java/lang/IllegalStateException", err, stream);
    }
}

