/*
 * Copyright (C) 2007 The Android Open Source Project
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

#define LOG_TAG "Memory"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "Portability.h"
#include "ScopedBytes.h"
#include "ScopedPrimitiveArray.h"
#include "UniquePtr.h"

#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>

#if defined(__arm__)
// 32-bit ARM has load/store alignment restrictions for longs.
#define LONG_ALIGNMENT_MASK 0x3
#define INT_ALIGNMENT_MASK 0x0
#define SHORT_ALIGNMENT_MASK 0x0
#elif defined(__mips__)
// MIPS has load/store alignment restrictions for longs, ints and shorts.
#define LONG_ALIGNMENT_MASK 0x7
#define INT_ALIGNMENT_MASK 0x3
#define SHORT_ALIGNMENT_MASK 0x1
#elif  defined(__aarch64__) || defined(__i386__) || defined(__x86_64__)
// These architectures can load anything at any alignment.
#define LONG_ALIGNMENT_MASK 0x0
#define INT_ALIGNMENT_MASK 0x0
#define SHORT_ALIGNMENT_MASK 0x0
#else
#error unknown load/store alignment restrictions for this architecture
#endif

// Use packed structures for access to unaligned data on targets with alignment restrictions.
// The compiler will generate appropriate code to access these structures without
// generating alignment exceptions.
template <typename T> static inline T get_unaligned(const T* address) {
    struct unaligned { T v; } __attribute__ ((packed));
    const unaligned* p = reinterpret_cast<const unaligned*>(address);
    return p->v;
}

template <typename T> static inline void put_unaligned(T* address, T v) {
    struct unaligned { T v; } __attribute__ ((packed));
    unaligned* p = reinterpret_cast<unaligned*>(address);
    p->v = v;
}

template <typename T> static T cast(jlong address) {
    return reinterpret_cast<T>(static_cast<uintptr_t>(address));
}

// Byte-swap 2 jshort values packed in a jint.
static inline jint bswap_2x16(jint v) {
    // v is initially ABCD
#if defined(__mips__) && defined(__mips_isa_rev) && (__mips_isa_rev >= 2)
    __asm__ volatile ("wsbh %0, %0" : "+r" (v));  // v=BADC
#else
    v = bswap_32(v);                              // v=DCBA
    v = (v << 16) | ((v >> 16) & 0xffff);         // v=BADC
#endif
    return v;
}

static inline void swapShorts(jshort* dstShorts, const jshort* srcShorts, size_t count) {
    // Do 32-bit swaps as long as possible...
    jint* dst = reinterpret_cast<jint*>(dstShorts);
    const jint* src = reinterpret_cast<const jint*>(srcShorts);

    if ((reinterpret_cast<uintptr_t>(dst) & INT_ALIGNMENT_MASK) == 0 &&
        (reinterpret_cast<uintptr_t>(src) & INT_ALIGNMENT_MASK) == 0) {
        for (size_t i = 0; i < count / 2; ++i) {
            jint v = *src++;
            *dst++ = bswap_2x16(v);
        }
        // ...with one last 16-bit swap if necessary.
        if ((count % 2) != 0) {
            jshort v = *reinterpret_cast<const jshort*>(src);
            *reinterpret_cast<jshort*>(dst) = bswap_16(v);
        }
    } else {
        for (size_t i = 0; i < count / 2; ++i) {
            jint v = get_unaligned<jint>(src++);
            put_unaligned<jint>(dst++, bswap_2x16(v));
        }
        if ((count % 2) != 0) {
          jshort v = get_unaligned<jshort>(reinterpret_cast<const jshort*>(src));
          put_unaligned<jshort>(reinterpret_cast<jshort*>(dst), bswap_16(v));
        }
    }
}

static inline void swapInts(jint* dstInts, const jint* srcInts, size_t count) {
    if ((reinterpret_cast<uintptr_t>(dstInts) & INT_ALIGNMENT_MASK) == 0 &&
        (reinterpret_cast<uintptr_t>(srcInts) & INT_ALIGNMENT_MASK) == 0) {
        for (size_t i = 0; i < count; ++i) {
            jint v = *srcInts++;
            *dstInts++ = bswap_32(v);
        }
    } else {
        for (size_t i = 0; i < count; ++i) {
            jint v = get_unaligned<int>(srcInts++);
            put_unaligned<jint>(dstInts++, bswap_32(v));
        }
    }
}

static inline void swapLongs(jlong* dstLongs, const jlong* srcLongs, size_t count) {
    jint* dst = reinterpret_cast<jint*>(dstLongs);
    const jint* src = reinterpret_cast<const jint*>(srcLongs);
    if ((reinterpret_cast<uintptr_t>(dstLongs) & INT_ALIGNMENT_MASK) == 0 &&
        (reinterpret_cast<uintptr_t>(srcLongs) & INT_ALIGNMENT_MASK) == 0) {
        for (size_t i = 0; i < count; ++i) {
          jint v1 = *src++;
          jint v2 = *src++;
          *dst++ = bswap_32(v2);
          *dst++ = bswap_32(v1);
        }
    } else {
        for (size_t i = 0; i < count; ++i) {
            jint v1 = get_unaligned<jint>(src++);
            jint v2 = get_unaligned<jint>(src++);
            put_unaligned<jint>(dst++, bswap_32(v2));
            put_unaligned<jint>(dst++, bswap_32(v1));
        }
    }
}

extern "C" void Java_libcore_io_Memory_memmove(JNIEnv* env, jclass, jobject dstObject, jint dstOffset, jobject srcObject, jint srcOffset, jlong length) {
    ScopedBytesRW dstBytes(env, dstObject);
    if (dstBytes.get() == NULL) {
        return;
    }
    ScopedBytesRO srcBytes(env, srcObject);
    if (srcBytes.get() == NULL) {
        return;
    }
    memmove(dstBytes.get() + dstOffset, srcBytes.get() + srcOffset, length);
}

extern "C" jbyte Java_libcore_io_Memory_peekByte(JNIEnv*, jclass, jlong srcAddress) {
    return *cast<const jbyte*>(srcAddress);
}

extern "C" void Java_libcore_io_Memory_peekByteArray(JNIEnv* env, jclass, jlong srcAddress, jbyteArray dst, jint dstOffset, jint byteCount) {
    env->SetByteArrayRegion(dst, dstOffset, byteCount, cast<const jbyte*>(srcAddress));
}

// Implements the peekXArray methods:
// - For unswapped access, we just use the JNI SetXArrayRegion functions.
// - For swapped access, we use GetXArrayElements and our own copy-and-swap routines.
//   GetXArrayElements is disproportionately cheap on Dalvik because it doesn't copy (as opposed
//   to Hotspot, which always copies). The SWAP_FN copies and swaps in one pass, which is cheaper
//   than copying and then swapping in a second pass. Depending on future VM/GC changes, the
//   swapped case might need to be revisited.
#define PEEKER(SCALAR_TYPE, JNI_NAME, SWAP_TYPE, SWAP_FN) { \
    if (swap) { \
        Scoped ## JNI_NAME ## ArrayRW elements(env, dst); \
        if (elements.get() == NULL) { \
            return; \
        } \
        const SWAP_TYPE* src = cast<const SWAP_TYPE*>(srcAddress); \
        SWAP_FN(reinterpret_cast<SWAP_TYPE*>(elements.get()) + dstOffset, src, count); \
    } else { \
        const SCALAR_TYPE* src = cast<const SCALAR_TYPE*>(srcAddress); \
        env->Set ## JNI_NAME ## ArrayRegion(dst, dstOffset, count, src); \
    } \
}

extern "C" void Java_libcore_io_Memory_peekCharArray(JNIEnv* env, jclass, jlong srcAddress, jcharArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jchar, Char, jshort, swapShorts);
}

extern "C" void Java_libcore_io_Memory_peekDoubleArray(JNIEnv* env, jclass, jlong srcAddress, jdoubleArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jdouble, Double, jlong, swapLongs);
}

extern "C" void Java_libcore_io_Memory_peekFloatArray(JNIEnv* env, jclass, jlong srcAddress, jfloatArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jfloat, Float, jint, swapInts);
}

extern "C" void Java_libcore_io_Memory_peekIntArray(JNIEnv* env, jclass, jlong srcAddress, jintArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jint, Int, jint, swapInts);
}

extern "C" void Java_libcore_io_Memory_peekLongArray(JNIEnv* env, jclass, jlong srcAddress, jlongArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jlong, Long, jlong, swapLongs);
}

extern "C" void Java_libcore_io_Memory_peekShortArray(JNIEnv* env, jclass, jlong srcAddress, jshortArray dst, jint dstOffset, jint count, jboolean swap) {
    PEEKER(jshort, Short, jshort, swapShorts);
}

extern "C" void Java_libcore_io_Memory_pokeByte(JNIEnv*, jclass, jlong dstAddress, jbyte value) {
    *cast<jbyte*>(dstAddress) = value;
}

extern "C" void Java_libcore_io_Memory_pokeByteArray(JNIEnv* env, jclass, jlong dstAddress, jbyteArray src, jint offset, jint length) {
    env->GetByteArrayRegion(src, offset, length, cast<jbyte*>(dstAddress));
}

// Implements the pokeXArray methods:
// - For unswapped access, we just use the JNI GetXArrayRegion functions.
// - For swapped access, we use GetXArrayElements and our own copy-and-swap routines.
//   GetXArrayElements is disproportionately cheap on Dalvik because it doesn't copy (as opposed
//   to Hotspot, which always copies). The SWAP_FN copies and swaps in one pass, which is cheaper
//   than copying and then swapping in a second pass. Depending on future VM/GC changes, the
//   swapped case might need to be revisited.
#define POKER(SCALAR_TYPE, JNI_NAME, SWAP_TYPE, SWAP_FN) { \
    if (swap) { \
        Scoped ## JNI_NAME ## ArrayRO elements(env, src); \
        if (elements.get() == NULL) { \
            return; \
        } \
        const SWAP_TYPE* src = reinterpret_cast<const SWAP_TYPE*>(elements.get()) + srcOffset; \
        SWAP_FN(cast<SWAP_TYPE*>(dstAddress), src, count); \
    } else { \
        env->Get ## JNI_NAME ## ArrayRegion(src, srcOffset, count, cast<SCALAR_TYPE*>(dstAddress)); \
    } \
}

extern "C" void Java_libcore_io_Memory_pokeCharArray(JNIEnv* env, jclass, jlong dstAddress, jcharArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jchar, Char, jshort, swapShorts);
}

extern "C" void Java_libcore_io_Memory_pokeDoubleArray(JNIEnv* env, jclass, jlong dstAddress, jdoubleArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jdouble, Double, jlong, swapLongs);
}

extern "C" void Java_libcore_io_Memory_pokeFloatArray(JNIEnv* env, jclass, jlong dstAddress, jfloatArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jfloat, Float, jint, swapInts);
}

extern "C" void Java_libcore_io_Memory_pokeIntArray(JNIEnv* env, jclass, jlong dstAddress, jintArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jint, Int, jint, swapInts);
}

extern "C" void Java_libcore_io_Memory_pokeLongArray(JNIEnv* env, jclass, jlong dstAddress, jlongArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jlong, Long, jlong, swapLongs);
}

extern "C" void Java_libcore_io_Memory_pokeShortArray(JNIEnv* env, jclass, jlong dstAddress, jshortArray src, jint srcOffset, jint count, jboolean swap) {
    POKER(jshort, Short, jshort, swapShorts);
}

extern "C" jshort Java_libcore_io_Memory_peekShort(JNIEnv*, jclass, jlong srcAddress, jboolean swap) {
    jshort result = *cast<const jshort*>(srcAddress);
    if (swap) {
        result = bswap_16(result);
    }
    return result;
}

extern "C" void Java_libcore_io_Memory_pokeShort(JNIEnv*, jclass, jlong dstAddress, jshort value, jboolean swap) {
    if (swap) {
        value = bswap_16(value);
    }
    *cast<jshort*>(dstAddress) = value;
}

extern "C" jint Java_libcore_io_Memory_peekInt(JNIEnv*, jclass, jlong srcAddress, jboolean swap) {
    jint result = *cast<const jint*>(srcAddress);
    if (swap) {
        result = bswap_32(result);
    }
    return result;
}

extern "C" void Java_libcore_io_Memory_pokeInt(JNIEnv*, jclass, jlong dstAddress, jint value, jboolean swap) {
    if (swap) {
        value = bswap_32(value);
    }
    *cast<jint*>(dstAddress) = value;
}

extern "C" jlong Java_libcore_io_Memory_peekLong(JNIEnv*, jclass, jlong srcAddress, jboolean swap) {
    jlong result;
    const jlong* src = cast<const jlong*>(srcAddress);
    if ((srcAddress & LONG_ALIGNMENT_MASK) == 0) {
        result = *src;
    } else {
        result = get_unaligned<jlong>(src);
    }
    if (swap) {
        result = bswap_64(result);
    }
    return result;
}

extern "C" void Java_libcore_io_Memory_pokeLong(JNIEnv*, jclass, jlong dstAddress, jlong value, jboolean swap) {
    jlong* dst = cast<jlong*>(dstAddress);
    if (swap) {
        value = bswap_64(value);
    }
    if ((dstAddress & LONG_ALIGNMENT_MASK) == 0) {
        *dst = value;
    } else {
        put_unaligned<jlong>(dst, value);
    }
}

static void unsafeBulkCopy(jbyte* dst, const jbyte* src, jint byteCount,
        jint sizeofElement, jboolean swap) {
    if (!swap) {
        memcpy(dst, src, byteCount);
        return;
    }

    if (sizeofElement == 2) {
        jshort* dstShorts = reinterpret_cast<jshort*>(dst);
        const jshort* srcShorts = reinterpret_cast<const jshort*>(src);
        swapShorts(dstShorts, srcShorts, byteCount / 2);
    } else if (sizeofElement == 4) {
        jint* dstInts = reinterpret_cast<jint*>(dst);
        const jint* srcInts = reinterpret_cast<const jint*>(src);
        swapInts(dstInts, srcInts, byteCount / 4);
    } else if (sizeofElement == 8) {
        jlong* dstLongs = reinterpret_cast<jlong*>(dst);
        const jlong* srcLongs = reinterpret_cast<const jlong*>(src);
        swapLongs(dstLongs, srcLongs, byteCount / 8);
    }
}

extern "C" void Java_libcore_io_Memory_unsafeBulkGet(JNIEnv* env, jclass, jobject dstObject, jint dstOffset,
        jint byteCount, jbyteArray srcArray, jint srcOffset, jint sizeofElement, jboolean swap) {
    ScopedByteArrayRO srcBytes(env, srcArray);
    if (srcBytes.get() == NULL) {
        return;
    }
    jarray dstArray = reinterpret_cast<jarray>(dstObject);
    jbyte* dstBytes = reinterpret_cast<jbyte*>(env->GetPrimitiveArrayCritical(dstArray, NULL));
    if (dstBytes == NULL) {
        return;
    }
    jbyte* dst = dstBytes + dstOffset*sizeofElement;
    const jbyte* src = srcBytes.get() + srcOffset;
    unsafeBulkCopy(dst, src, byteCount, sizeofElement, swap);
    env->ReleasePrimitiveArrayCritical(dstArray, dstBytes, 0);
}

extern "C" void Java_libcore_io_Memory_unsafeBulkPut(JNIEnv* env, jclass, jbyteArray dstArray, jint dstOffset,
        jint byteCount, jobject srcObject, jint srcOffset, jint sizeofElement, jboolean swap) {
    ScopedByteArrayRW dstBytes(env, dstArray);
    if (dstBytes.get() == NULL) {
        return;
    }
    jarray srcArray = reinterpret_cast<jarray>(srcObject);
    jbyte* srcBytes = reinterpret_cast<jbyte*>(env->GetPrimitiveArrayCritical(srcArray, NULL));
    if (srcBytes == NULL) {
        return;
    }
    jbyte* dst = dstBytes.get() + dstOffset;
    const jbyte* src = srcBytes + srcOffset*sizeofElement;
    unsafeBulkCopy(dst, src, byteCount, sizeofElement, swap);
    env->ReleasePrimitiveArrayCritical(srcArray, srcBytes, 0);
}

