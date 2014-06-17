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

#define LOG_TAG "Inflater"

#include "JniConstants.h"
#include "JniException.h"
#include "ScopedPrimitiveArray.h"
#include "ZipUtilities.h"
#include "zutil.h" // For DEF_WBITS and DEF_MEM_LEVEL.
#include <errno.h>

extern "C" jlong Java_java_util_zip_Inflater_createStream(JNIEnv* env, jobject, jboolean noHeader) {
    UniquePtr<NativeZipStream> jstream(new NativeZipStream);
    if (jstream.get() == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return -1;
    }
    jstream->stream.adler = 1;

    /*
     * See zlib.h for documentation of the inflateInit2 windowBits parameter.
     *
     * zconf.h says the "requirements for inflate are (in bytes) 1 << windowBits
     * that is, 32K for windowBits=15 (default value) plus a few kilobytes
     * for small objects." This means that we can happily use the default
     * here without worrying about memory consumption.
     */
    int err = inflateInit2(&jstream->stream, noHeader ? -DEF_WBITS : DEF_WBITS);
    if (err != Z_OK) {
        throwExceptionForZlibError(env, "java/lang/IllegalArgumentException", err, jstream.get());
        return -1;
    }
    return reinterpret_cast<uintptr_t>(jstream.release());
}

extern "C" void Java_java_util_zip_Inflater_setInputImpl(JNIEnv* env, jobject, jbyteArray buf, jint off, jint len, jlong handle) {
    toNativeZipStream(handle)->setInput(env, buf, off, len);
}

extern "C" jint Java_java_util_zip_Inflater_setFileInputImpl(JNIEnv* env, jobject, jobject javaFileDescriptor, jlong off, jint len, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);

    // We reuse the existing native buffer if it's large enough.
    // TODO: benchmark.
    if (stream->inCap < len) {
        stream->setInput(env, NULL, 0, len);
    } else {
        stream->stream.next_in = reinterpret_cast<Bytef*>(&stream->input[0]);
        stream->stream.avail_in = len;
    }

    // As an Android-specific optimization, we read directly onto the native heap.
    // The original code used Java to read onto the Java heap and then called setInput(byte[]).
    // TODO: benchmark.
    int fd = jniGetFDFromFileDescriptor(env, javaFileDescriptor);
    int rc = TEMP_FAILURE_RETRY(lseek(fd, off, SEEK_SET));
    if (rc == -1) {
        jniThrowIOException(env, errno);
        return 0;
    }
    jint totalByteCount = 0;
    Bytef* dst = reinterpret_cast<Bytef*>(&stream->input[0]);
    ssize_t byteCount;
    while ((byteCount = TEMP_FAILURE_RETRY(read(fd, dst, len))) > 0) {
        dst += byteCount;
        len -= byteCount;
        totalByteCount += byteCount;
    }
    if (byteCount == -1) {
        jniThrowIOException(env, errno);
        return 0;
    }
    return totalByteCount;
}

extern "C" jint Java_java_util_zip_Inflater_inflateImpl(JNIEnv* env, jobject recv, jbyteArray buf, int off, int len, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    ScopedByteArrayRW out(env, buf);
    if (out.get() == NULL) {
        return -1;
    }
    stream->stream.next_out = reinterpret_cast<Bytef*>(out.get() + off);
    stream->stream.avail_out = len;

    Bytef* initialNextIn = stream->stream.next_in;
    Bytef* initialNextOut = stream->stream.next_out;

    int err = inflate(&stream->stream, Z_SYNC_FLUSH);
    switch (err) {
    case Z_OK:
        break;
    case Z_NEED_DICT:
        static jfieldID needsDictionary = env->GetFieldID(JniConstants::inflaterClass, "needsDictionary", "Z");
        env->SetBooleanField(recv, needsDictionary, JNI_TRUE);
        break;
    case Z_STREAM_END:
        static jfieldID finished = env->GetFieldID(JniConstants::inflaterClass, "finished", "Z");
        env->SetBooleanField(recv, finished, JNI_TRUE);
        break;
    case Z_STREAM_ERROR:
        return 0;
    default:
        throwExceptionForZlibError(env, "java/util/zip/DataFormatException", err, stream);
        return -1;
    }

    jint bytesRead = stream->stream.next_in - initialNextIn;
    jint bytesWritten = stream->stream.next_out - initialNextOut;

    static jfieldID inReadField = env->GetFieldID(JniConstants::inflaterClass, "inRead", "I");
    jint inReadValue = env->GetIntField(recv, inReadField);
    inReadValue += bytesRead;
    env->SetIntField(recv, inReadField, inReadValue);
    return bytesWritten;
}

extern "C" jint Java_java_util_zip_Inflater_getAdlerImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.adler;
}

extern "C" void Java_java_util_zip_Inflater_endImpl(JNIEnv*, jobject, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    inflateEnd(&stream->stream);
    delete stream;
}

extern "C" void Java_java_util_zip_Inflater_setDictionaryImpl(JNIEnv* env, jobject, jbyteArray dict, int off, int len, jlong handle) {
    toNativeZipStream(handle)->setDictionary(env, dict, off, len, true);
}

extern "C" void Java_java_util_zip_Inflater_resetImpl(JNIEnv* env, jobject, jlong handle) {
    NativeZipStream* stream = toNativeZipStream(handle);
    int err = inflateReset(&stream->stream);
    if (err != Z_OK) {
        throwExceptionForZlibError(env, "java/lang/IllegalArgumentException", err, stream);
    }
}

extern "C" jlong Java_java_util_zip_Inflater_getTotalOutImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.total_out;
}

extern "C" jlong Java_java_util_zip_Inflater_getTotalInImpl(JNIEnv*, jobject, jlong handle) {
    return toNativeZipStream(handle)->stream.total_in;
}

