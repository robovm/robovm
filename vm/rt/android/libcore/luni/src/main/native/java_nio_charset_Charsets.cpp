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

#define LOG_TAG "String"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedPrimitiveArray.h"
#include "jni.h"
#include "unicode/utf16.h"

#include <string.h>

/**
 * Approximates java.lang.UnsafeByteSequence so we don't have to pay the cost of calling back into
 * Java when converting a char[] to a UTF-8 byte[]. This lets us have UTF-8 conversions slightly
 * faster than ICU for large char[]s without paying for the NIO overhead with small char[]s.
 *
 * We could avoid this by keeping the UTF-8 bytes on the native heap until we're done and only
 * creating a byte[] on the Java heap when we know how big it needs to be, but one shouldn't lie
 * to the garbage collector (nor hide potentially large allocations from it).
 *
 * Because a call to append might require an allocation, it might fail. Callers should always
 * check the return value of append.
 */
class NativeUnsafeByteSequence {
public:
    NativeUnsafeByteSequence(JNIEnv* env)
        : mEnv(env), mJavaArray(NULL), mRawArray(NULL), mSize(-1), mOffset(0)
    {
    }

    ~NativeUnsafeByteSequence() {
        // Release our pointer to the raw array, copying changes back to the Java heap.
        if (mRawArray != NULL) {
            mEnv->ReleaseByteArrayElements(mJavaArray, mRawArray, 0);
        }
    }

    bool append(jbyte b) {
        if (mOffset == mSize && !resize(mSize * 2)) {
            return false;
        }
        mRawArray[mOffset++] = b;
        return true;
    }

    bool resize(int newSize) {
        if (newSize == mSize) {
            return true;
        }

        // Allocate a new array.
        jbyteArray newJavaArray = mEnv->NewByteArray(newSize);
        if (newJavaArray == NULL) {
            return false;
        }
        jbyte* newRawArray = mEnv->GetByteArrayElements(newJavaArray, NULL);
        if (newRawArray == NULL) {
            return false;
        }

        // Copy data out of the old array and then let go of it.
        // Note that we may be trimming the array.
        if (mRawArray != NULL) {
            memcpy(newRawArray, mRawArray, mOffset);
            mEnv->ReleaseByteArrayElements(mJavaArray, mRawArray, JNI_ABORT);
            mEnv->DeleteLocalRef(mJavaArray);
        }

        // Point ourselves at the new array.
        mJavaArray = newJavaArray;
        mRawArray = newRawArray;
        mSize = newSize;
        return true;
    }

    jbyteArray toByteArray() {
        // Trim any unused space, if necessary.
        bool okay = resize(mOffset);
        return okay ? mJavaArray : NULL;
    }

private:
    JNIEnv* mEnv;
    jbyteArray mJavaArray;
    jbyte* mRawArray;
    jint mSize;
    jint mOffset;

    // Disallow copy and assignment.
    NativeUnsafeByteSequence(const NativeUnsafeByteSequence&);
    void operator=(const NativeUnsafeByteSequence&);
};

extern "C" void Java_java_nio_charset_Charsets_asciiBytesToChars(JNIEnv* env, jclass, jbyteArray javaBytes, jint offset, jint length, jcharArray javaChars) {
    ScopedByteArrayRO bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        return;
    }
    ScopedCharArrayRW chars(env, javaChars);
    if (chars.get() == NULL) {
        return;
    }

    const jbyte* src = &bytes[offset];
    jchar* dst = &chars[0];
    static const jchar REPLACEMENT_CHAR = 0xfffd;
    for (int i = length - 1; i >= 0; --i) {
        jchar ch = static_cast<jchar>(*src++ & 0xff);
        *dst++ = (ch <= 0x7f) ? ch : REPLACEMENT_CHAR;
    }
}

extern "C" void Java_java_nio_charset_Charsets_isoLatin1BytesToChars(JNIEnv* env, jclass, jbyteArray javaBytes, jint offset, jint length, jcharArray javaChars) {
    ScopedByteArrayRO bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        return;
    }
    ScopedCharArrayRW chars(env, javaChars);
    if (chars.get() == NULL) {
        return;
    }

    const jbyte* src = &bytes[offset];
    jchar* dst = &chars[0];
    for (int i = length - 1; i >= 0; --i) {
        *dst++ = static_cast<jchar>(*src++ & 0xff);
    }
}

/**
 * Translates the given characters to US-ASCII or ISO-8859-1 bytes, using the fact that
 * Unicode code points between U+0000 and U+007f inclusive are identical to US-ASCII, while
 * U+0000 to U+00ff inclusive are identical to ISO-8859-1.
 */
static jbyteArray charsToBytes(JNIEnv* env, jcharArray javaChars, jint offset, jint length, jchar maxValidChar) {
    ScopedCharArrayRO chars(env, javaChars);
    if (chars.get() == NULL) {
        return NULL;
    }

    jbyteArray javaBytes = env->NewByteArray(length);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        return NULL;
    }

    const jchar* src = &chars[offset];
    jbyte* dst = &bytes[0];
    for (int i = length - 1; i >= 0; --i) {
        jchar ch = *src++;
        if (ch > maxValidChar) {
            ch = '?';
        }
        *dst++ = static_cast<jbyte>(ch);
    }

    return javaBytes;
}

extern "C" jbyteArray Java_java_nio_charset_Charsets_toAsciiBytes(JNIEnv* env, jclass, jcharArray javaChars, jint offset, jint length) {
    return charsToBytes(env, javaChars, offset, length, 0x7f);
}

extern "C" jbyteArray Java_java_nio_charset_Charsets_toIsoLatin1Bytes(JNIEnv* env, jclass, jcharArray javaChars, jint offset, jint length) {
    return charsToBytes(env, javaChars, offset, length, 0xff);
}

extern "C" jbyteArray Java_java_nio_charset_Charsets_toUtf8Bytes(JNIEnv* env, jclass, jcharArray javaChars, jint offset, jint length) {
    ScopedCharArrayRO chars(env, javaChars);
    if (chars.get() == NULL) {
        return NULL;
    }

    NativeUnsafeByteSequence out(env);
    if (!out.resize(length)) {
        return NULL;
    }

    const int end = offset + length;
    for (int i = offset; i < end; ++i) {
        jint ch = chars[i];
        if (ch < 0x80) {
            // One byte.
            if (!out.append(ch)) {
                return NULL;
            }
        } else if (ch < 0x800) {
            // Two bytes.
            if (!out.append((ch >> 6) | 0xc0) || !out.append((ch & 0x3f) | 0x80)) {
                return NULL;
            }
        } else if (U16_IS_SURROGATE(ch)) {
            // A supplementary character.
            jchar high = (jchar) ch;
            jchar low = (i + 1 != end) ? chars[i + 1] : 0;
            if (!U16_IS_SURROGATE_LEAD(high) || !U16_IS_SURROGATE_TRAIL(low)) {
                if (!out.append('?')) {
                    return NULL;
                }
                continue;
            }
            // Now we know we have a *valid* surrogate pair, we can consume the low surrogate.
            ++i;
            ch = U16_GET_SUPPLEMENTARY(high, low);
            // Four bytes.
            jbyte b1 = (ch >> 18) | 0xf0;
            jbyte b2 = ((ch >> 12) & 0x3f) | 0x80;
            jbyte b3 = ((ch >> 6) & 0x3f) | 0x80;
            jbyte b4 = (ch & 0x3f) | 0x80;
            if (!out.append(b1) || !out.append(b2) || !out.append(b3) || !out.append(b4)) {
                return NULL;
            }
        } else {
            // Three bytes.
            jbyte b1 = (ch >> 12) | 0xe0;
            jbyte b2 = ((ch >> 6) & 0x3f) | 0x80;
            jbyte b3 = (ch & 0x3f) | 0x80;
            if (!out.append(b1) || !out.append(b2) || !out.append(b3)) {
                return NULL;
            }
        }
    }
    return out.toByteArray();
}

