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

#define LOG_TAG "Bidi"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "ScopedPrimitiveArray.h"
#include "UniquePtr.h"
#include "unicode/ubidi.h"

#include <stdlib.h>
#include <string.h>

struct BiDiData {
    BiDiData(UBiDi* biDi) : mBiDi(biDi), mEmbeddingLevels(NULL) {
    }

    ~BiDiData() {
        ubidi_close(mBiDi);
    }

    UBiDiLevel* embeddingLevels() {
        return reinterpret_cast<UBiDiLevel*>(&mEmbeddingLevels[0]);
    }

    void setEmbeddingLevels(jbyte* newEmbeddingLevels) {
        mEmbeddingLevels.reset(newEmbeddingLevels);
    }

    UBiDi* uBiDi() {
        return mBiDi;
    }

private:
    UBiDi* mBiDi;
    UniquePtr<jbyte[]> mEmbeddingLevels;

    // Disallow copy and assignment.
    BiDiData(const BiDiData&);
    void operator=(const BiDiData&);
};

static BiDiData* biDiData(jlong ptr) {
    return reinterpret_cast<BiDiData*>(static_cast<uintptr_t>(ptr));
}

static UBiDi* uBiDi(jlong ptr) {
    return reinterpret_cast<BiDiData*>(static_cast<uintptr_t>(ptr))->uBiDi();
}

extern "C" jlong Java_java_text_Bidi_ubidi_1open(JNIEnv*, jclass) {
    return reinterpret_cast<uintptr_t>(new BiDiData(ubidi_open()));
}

extern "C" void Java_java_text_Bidi_ubidi_1close(JNIEnv*, jclass, jlong ptr) {
    delete biDiData(ptr);
}

extern "C" void Java_java_text_Bidi_ubidi_1setPara(JNIEnv* env, jclass, jlong ptr, jcharArray text, jint length, jint paraLevel, jbyteArray newEmbeddingLevels) {
    BiDiData* data = biDiData(ptr);
    // Copy the new embedding levels from the Java heap to the native heap.
    if (newEmbeddingLevels != NULL) {
        jbyte* dst;
        data->setEmbeddingLevels(dst = new jbyte[length]);
        env->GetByteArrayRegion(newEmbeddingLevels, 0, length, dst);
    } else {
        data->setEmbeddingLevels(NULL);
    }
    ScopedCharArrayRO chars(env, text);
    if (chars.get() == NULL) {
        return;
    }
    UErrorCode err = U_ZERO_ERROR;
    ubidi_setPara(data->uBiDi(), chars.get(), length, paraLevel, data->embeddingLevels(), &err);
    maybeThrowIcuException(env, err);
}

extern "C" jlong Java_java_text_Bidi_ubidi_1setLine(JNIEnv* env, jclass, jlong ptr, jint start, jint limit) {
    UErrorCode status = U_ZERO_ERROR;
    UBiDi* sized = ubidi_openSized(limit - start, 0, &status);
    if (maybeThrowIcuException(env, status)) {
        return 0;
    }
    UniquePtr<BiDiData> lineData(new BiDiData(sized));
    ubidi_setLine(uBiDi(ptr), start, limit, lineData->uBiDi(), &status);
    maybeThrowIcuException(env, status);
    return reinterpret_cast<uintptr_t>(lineData.release());
}

extern "C" jint Java_java_text_Bidi_ubidi_1getDirection(JNIEnv*, jclass, jlong ptr) {
    return ubidi_getDirection(uBiDi(ptr));
}

extern "C" jint Java_java_text_Bidi_ubidi_1getLength(JNIEnv*, jclass, jlong ptr) {
    return ubidi_getLength(uBiDi(ptr));
}

extern "C" jbyte Java_java_text_Bidi_ubidi_1getParaLevel(JNIEnv*, jclass, jlong ptr) {
    return ubidi_getParaLevel(uBiDi(ptr));
}

extern "C" jbyteArray Java_java_text_Bidi_ubidi_1getLevels(JNIEnv* env, jclass, jlong ptr) {
    UErrorCode status = U_ZERO_ERROR;
    const UBiDiLevel* levels = ubidi_getLevels(uBiDi(ptr), &status);
    if (maybeThrowIcuException(env, status)) {
        return NULL;
    }
    int len = ubidi_getLength(uBiDi(ptr));
    jbyteArray result = env->NewByteArray(len);
    env->SetByteArrayRegion(result, 0, len, reinterpret_cast<const jbyte*>(levels));
    return result;
}

extern "C" jint Java_java_text_Bidi_ubidi_1countRuns(JNIEnv* env, jclass, jlong ptr) {
    UErrorCode status = U_ZERO_ERROR;
    int count = ubidi_countRuns(uBiDi(ptr), &status);
    maybeThrowIcuException(env, status);
    return count;
}

/**
 * TODO: if we care about performance, we might just want to use an int[] instead of a Run[].
 */
extern "C" jobjectArray Java_java_text_Bidi_ubidi_1getRuns(JNIEnv* env, jclass, jlong ptr) {
    UBiDi* ubidi = uBiDi(ptr);
    UErrorCode status = U_ZERO_ERROR;
    int runCount = ubidi_countRuns(ubidi, &status);
    if (maybeThrowIcuException(env, status)) {
        return NULL;
    }
    jmethodID bidiRunConstructor = env->GetMethodID(JniConstants::bidiRunClass, "<init>", "(III)V");
    jobjectArray runs = env->NewObjectArray(runCount, JniConstants::bidiRunClass, NULL);
    UBiDiLevel level = 0;
    int start = 0;
    int limit = 0;
    for (int i = 0; i < runCount; ++i) {
        ubidi_getLogicalRun(ubidi, start, &limit, &level);
        jobject run = env->NewObject(JniConstants::bidiRunClass, bidiRunConstructor, start, limit, level);
        env->SetObjectArrayElement(runs, i, run);
        start = limit;
    }
    return runs;
}

extern "C" jintArray Java_java_text_Bidi_ubidi_1reorderVisual(JNIEnv* env, jclass, jbyteArray javaLevels, jint length) {
    ScopedByteArrayRO levelBytes(env, javaLevels);
    if (levelBytes.get() == NULL) {
        return NULL;
    }

    const UBiDiLevel* levels = reinterpret_cast<const UBiDiLevel*>(levelBytes.get());

    UniquePtr<int[]> indexMap(new int[length]);
    ubidi_reorderVisual(levels, length, &indexMap[0]);

    jintArray result = env->NewIntArray(length);
    env->SetIntArrayRegion(result, 0, length, &indexMap[0]);
    return result;
}

