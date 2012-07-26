/*
 * Copyright (C) 2008 The Android Open Source Project
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

/*
 * Native glue for Java class org.openssl.NativeBN
 */

#define LOG_TAG "NativeBN"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedUtfChars.h"
#include "StaticAssert.h"
#include "UniquePtr.h"
#include "jni.h"
#include <openssl/bn.h>
#include <openssl/crypto.h>
#include <openssl/err.h>
#include <stdio.h>

struct BN_CTX_Deleter {
    void operator()(BN_CTX* p) const {
        BN_CTX_free(p);
    }
};
typedef UniquePtr<BN_CTX, BN_CTX_Deleter> Unique_BN_CTX;

static int isValidHandle (JNIEnv* env, void* handle, const char* message) {
    if (handle == NULL) {
        jniThrowNullPointerException(env, message);
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static int oneValidHandle (JNIEnv* env, void* a)
{
    return isValidHandle(env, a, "Mandatory handle (first) passed as null");
}

static int twoValidHandles (JNIEnv* env, void* a, void* b)
{
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return isValidHandle(env, b, "Mandatory handle (second) passed as null");
}

static int threeValidHandles (JNIEnv* env, void* a, void* b, void* c)
{
    if (!twoValidHandles(env, a, b)) return JNI_FALSE;
    return isValidHandle(env, c, "Mandatory handle (third) passed as null");
}

static int fourValidHandles (JNIEnv* env, void* a, void* b, void* c, void* d)
{
    if (!threeValidHandles(env, a, b, c)) return JNI_FALSE;
    return isValidHandle(env, d, "Mandatory handle (fourth) passed as null");
}

extern "C" unsigned long Java_java_math_NativeBN_ERR_1get_1error(JNIEnv*, jclass) {
    return ERR_get_error();
}

extern "C" jstring Java_java_math_NativeBN_ERR_1error_1string(JNIEnv* env, jclass, unsigned long e) {
    char* errStr = ERR_error_string(e, NULL);
    return env->NewStringUTF(errStr);
}

extern "C" BIGNUM* Java_java_math_NativeBN_BN_1new(JNIEnv*, jclass) {
    return BN_new();
}

extern "C" void Java_java_math_NativeBN_BN_1free(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return;
    BN_free(a);
}

extern "C" int Java_java_math_NativeBN_BN_1cmp(JNIEnv* env, jclass, BIGNUM* a, BIGNUM* b) {
    if (!twoValidHandles(env, a, b)) return 1;
    return BN_cmp(a, b);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1copy(JNIEnv* env, jclass, BIGNUM* to, BIGNUM* from) {
    if (!twoValidHandles(env, to, from)) return JNI_FALSE;
    return (BN_copy(to, from) != NULL);
}

extern "C" jboolean Java_java_math_NativeBN_putULongInt(JNIEnv* env, jclass, BIGNUM* a, unsigned long long dw, jboolean neg) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    unsigned int hi = dw >> 32; // This shifts without sign extension.
    int lo = (int)dw; // This truncates implicitely.

    // cf. litEndInts2bn:
    bn_check_top(a);
        if (bn_wexpand(a, 2) != NULL) {
            a->d[0] = lo;
            a->d[1] = hi;
            a->top = 2;
            a->neg = neg;
            bn_correct_top(a);
            return JNI_TRUE;
        }
        else return JNI_FALSE;
}

extern "C" jboolean Java_java_math_NativeBN_putLongInt(JNIEnv* env, jclass cls, BIGNUM* a, long long dw) {
    if (dw >= 0) return Java_java_math_NativeBN_putULongInt(env, cls, a, dw, JNI_FALSE);
    else return Java_java_math_NativeBN_putULongInt(env, cls, a, -dw, JNI_TRUE);
}

extern "C" int Java_java_math_NativeBN_BN_1dec2bn(JNIEnv* env, jclass, BIGNUM* a, jstring str) {
    if (!oneValidHandle(env, a)) return -1;
    ScopedUtfChars chars(env, str);
    if (chars.c_str() == NULL) {
        return -1;
    }
    return BN_dec2bn(&a, chars.c_str());
}

extern "C" int Java_java_math_NativeBN_BN_1hex2bn(JNIEnv* env, jclass, BIGNUM* a, jstring str) {
    if (!oneValidHandle(env, a)) return -1;
    ScopedUtfChars chars(env, str);
    if (chars.c_str() == NULL) {
        return -1;
    }
    return BN_hex2bn(&a, chars.c_str());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1bin2bn(JNIEnv* env, jclass, jbyteArray arr, int len, jboolean neg, BIGNUM* ret) {
    if (!oneValidHandle(env, ret)) return JNI_FALSE;
    ScopedByteArrayRO bytes(env, arr);
    if (bytes.get() == NULL) {
        return -1;
    }
    jboolean success = (BN_bin2bn(reinterpret_cast<const unsigned char*>(bytes.get()), len, ret) != NULL);
    if (success && neg) {
        BN_set_negative(ret, 1);
    }
    return success;
}

/**
 * public static native boolean litEndInts2bn(int[], int, int, int)
 * Note:
 * This procedure directly writes the internal representation of BIGNUMs.
 * We do so as there is no direct interface based on Little Endian Integer Arrays.
 * Also note that the same representation is used in the Cordoba Java Implementation of BigIntegers,
 *        whereof certain functionality is still being used.
 */
extern "C" jboolean Java_java_math_NativeBN_litEndInts2bn(JNIEnv* env, jclass, jintArray arr, int len, jboolean neg, BIGNUM* ret) {
    if (!oneValidHandle(env, ret)) return JNI_FALSE;
    bn_check_top(ret);
    if (len > 0) {
        ScopedIntArrayRO scopedArray(env, arr);
        if (scopedArray.get() == NULL) {
            return JNI_FALSE;
        }

        STATIC_ASSERT(sizeof(BN_ULONG) == sizeof(jint), BN_ULONG_not_32_bit);
        const BN_ULONG* tmpInts = reinterpret_cast<const BN_ULONG*>(scopedArray.get());
        if ((tmpInts != NULL) && (bn_wexpand(ret, len) != NULL)) {
            int i = len; do { i--; ret->d[i] = tmpInts[i]; } while (i > 0);
            ret->top = len;
            ret->neg = neg;
            // need to call this due to clear byte at top if avoiding
            // having the top bit set (-ve number)
            // Basically get rid of top zero ints:
            bn_correct_top(ret);
            return JNI_TRUE;
        } else {
            return JNI_FALSE;
        }
    } else { // (len = 0) means value = 0 and sign will be 0, too.
        ret->top = 0;
        return JNI_TRUE;
    }
}


#define BYTES2INT(bytes, k) \
 (  (bytes[k + 3] & 0xFF) \
  | (bytes[k + 2] & 0xFF) << 8 \
  | (bytes[k + 1] & 0xFF) << 16 \
  | (bytes[k + 0] & 0xFF) << 24 )

static jboolean negBigEndianBytes2bn(JNIEnv*, jclass, const unsigned char* bytes, int bytesLen, BIGNUM* ret) {
    // We rely on: (BN_BITS2 == 32), i.e. BN_ULONG is unsigned int and has 4 bytes:
    bn_check_top(ret);
    // FIXME: assert bytesLen > 0
    int intLen = (bytesLen + 3) / 4;
    int firstNonzeroDigit = -2;
    if (bn_wexpand(ret, intLen) != NULL) {
        BN_ULONG* d = ret->d;
        BN_ULONG di;
        ret->top = intLen;
        int highBytes = bytesLen % 4;
        int k = bytesLen;
        // Put bytes to the int array starting from the end of the byte array
        int i = 0;
        while (k > highBytes) {
            k -= 4;
            di = BYTES2INT(bytes, k);
            if (di != 0) {
                d[i] = -di;
                firstNonzeroDigit = i;
                i++;
                while (k > highBytes) {
                    k -= 4;
                    d[i] = ~BYTES2INT(bytes, k);
                    i++;
                }
                break;
            } else {
                d[i] = 0;
                i++;
            }
        }
        if (highBytes != 0) {
            di = -1;
            // Put the first bytes in the highest element of the int array
            if (firstNonzeroDigit != -2) {
                for (k = 0; k < highBytes; k++) {
                    di = (di << 8) | (bytes[k] & 0xFF);
                }
                d[i] = ~di;
            } else {
                for (k = 0; k < highBytes; k++) {
                    di = (di << 8) | (bytes[k] & 0xFF);
                }
                d[i] = -di;
            }
        }
        return JNI_TRUE;
    }
    else return JNI_FALSE;
}

extern "C" jboolean Java_java_math_NativeBN_twosComp2bn(JNIEnv* env, jclass cls, jbyteArray arr, int bytesLen, BIGNUM* ret) {
    if (!oneValidHandle(env, ret)) return JNI_FALSE;
    ScopedByteArrayRO bytes(env, arr);
    if (bytes.get() == NULL) {
        return -1;
    }
    jboolean success;
    const unsigned char* s = reinterpret_cast<const unsigned char*>(bytes.get());
    if ((bytes[0] & 0X80) == 0) { // Positive value!
        //
        // We can use the existing BN implementation for unsigned big endian bytes:
        //
        success = (BN_bin2bn(s, bytesLen, ret) != NULL);
        BN_set_negative(ret, JNI_FALSE);
    } else { // Negative value!
        //
        // We need to apply two's complement:
        //
        success = negBigEndianBytes2bn(env, cls, s, bytesLen, ret);
        BN_set_negative(ret, JNI_TRUE);
    }
    return success;
}

extern "C" long long Java_java_math_NativeBN_longInt(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return -1;
    bn_check_top(a);
    int intLen = a->top;
    BN_ULONG* d = a->d;
    switch (intLen) {
    case 0:
        return 0;
    case 1:
        if (!a->neg) return d[0] & 0X00000000FFFFFFFFLL;
        else return -(d[0] & 0X00000000FFFFFFFFLL);
    default:
        if (!a->neg) return ((long long)d[1] << 32) | (d[0] & 0XFFFFFFFFLL);
        else return -(((long long)d[1] << 32) | (d[0] & 0XFFFFFFFFLL));
    }
}

static char* leadingZerosTrimmed(char* s) {
    char* p = s;
    if (*p == '-') {
        p++;
        while ((*p == '0') && (*(p + 1) != 0)) { p++; }
        p--;
        *p = '-';
    } else {
        while ((*p == '0') && (*(p + 1) != 0)) { p++; }
    }
    return p;
}

extern "C" jstring Java_java_math_NativeBN_BN_1bn2dec(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return NULL;
    char* tmpStr;
    char* retStr;
    tmpStr = BN_bn2dec(a);
    if (tmpStr != NULL) {
        retStr = leadingZerosTrimmed(tmpStr);
        jstring returnJString = env->NewStringUTF(retStr);
        OPENSSL_free(tmpStr);
        return returnJString;
    }
    else return NULL;
}

extern "C" jstring Java_java_math_NativeBN_BN_1bn2hex(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return NULL;
    char* tmpStr;
    char* retStr;
    tmpStr = BN_bn2hex(a);
    if (tmpStr != NULL) {
        retStr = leadingZerosTrimmed(tmpStr);
        jstring returnJString = env->NewStringUTF(retStr);
        OPENSSL_free(tmpStr);
        return returnJString;
    }
    else return NULL;
}

extern "C" jbyteArray Java_java_math_NativeBN_BN_1bn2bin(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return NULL;
    jbyteArray result = env->NewByteArray(BN_num_bytes(a));
    if (result == NULL) {
        return NULL;
    }
    ScopedByteArrayRW bytes(env, result);
    if (bytes.get() == NULL) {
        return NULL;
    }
    BN_bn2bin(a, reinterpret_cast<unsigned char*>(bytes.get()));
    return result;
}

extern "C" jintArray Java_java_math_NativeBN_bn2litEndInts(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return NULL;
    bn_check_top(a);
    int len = a->top;
    if (len == 0) {
        return NULL;
    }
    jintArray result = env->NewIntArray(len);
    if (result == NULL) {
        return NULL;
    }
    ScopedIntArrayRW ints(env, result);
    if (ints.get() == NULL) {
        return NULL;
    }
    BN_ULONG* ulongs = reinterpret_cast<BN_ULONG*>(ints.get());
    if (ulongs == NULL) {
        return NULL;
    }
    int i = len; do { i--; ulongs[i] = a->d[i]; } while (i > 0);
    return result;
}

extern "C" int Java_java_math_NativeBN_sign(JNIEnv* env, jclass, BIGNUM* a) {
    if (!oneValidHandle(env, a)) return -2;
    if (BN_is_zero(a)) return 0;
    else if (BN_is_negative(a)) return -1;
    else return 1;
}

extern "C" void Java_java_math_NativeBN_BN_1set_1negative(JNIEnv* env, jclass, BIGNUM* b, int n) {
    if (!oneValidHandle(env, b)) return;
    BN_set_negative(b, n);
}

extern "C" int Java_java_math_NativeBN_bitLength(JNIEnv* env, jclass, BIGNUM* a) {
// We rely on: (BN_BITS2 == 32), i.e. BN_ULONG is unsigned int and has 4 bytes:
//
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    bn_check_top(a);
    int intLen = a->top;
    if (intLen == 0) return 0;
    BN_ULONG* d = a->d;
    int i = intLen - 1;
    BN_ULONG msd = d[i]; // most significant digit
        if (a->neg) {
            // Handle negative values correctly:
            // i.e. decrement the msd if all other digits are 0:
            // while ((i > 0) && (d[i] != 0)) { i--; }
            do { i--; } while (!((i < 0) || (d[i] != 0)));
            if (i < 0) msd--; // Only if all lower significant digits are 0 we decrement the most significant one.
        }
        return (intLen - 1) * 32 + BN_num_bits_word(msd);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1is_1bit_1set(JNIEnv* env, jclass, BIGNUM* a, int n) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return (jboolean)BN_is_bit_set(a, n);
}

extern "C" jboolean Java_java_math_NativeBN_modifyBit(JNIEnv* env, jclass, BIGNUM* a, int n, int op) {
// ALOGD("NativeBN_BN_modifyBit");
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    switch (op) {
    case 1: return BN_set_bit(a, n);
    case 0: return BN_clear_bit(a, n);
    case -1:
        if (BN_is_bit_set(a, n)) return BN_clear_bit(a, n);
        else return BN_set_bit(a, n);
    }
    return JNI_FALSE;
}

extern "C" jboolean Java_java_math_NativeBN_BN_1shift(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, int n) {
    if (!twoValidHandles(env, r, a)) return JNI_FALSE;
    return (n >= 0) ? BN_lshift(r, a, n) : BN_rshift(r, a, -n);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1add_1word(JNIEnv* env, jclass, BIGNUM* a, BN_ULONG w) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return BN_add_word(a, w);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1sub_1word(JNIEnv* env, jclass, BIGNUM* a, BN_ULONG w) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return BN_sub_word(a, w);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1mul_1word(JNIEnv* env, jclass, BIGNUM* a, BN_ULONG w) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return BN_mul_word(a, w);
}

extern "C" BN_ULONG Java_java_math_NativeBN_BN_1div_1word(JNIEnv* env, jclass, BIGNUM* a, BN_ULONG w) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return BN_div_word(a, w);
}

extern "C" BN_ULONG Java_java_math_NativeBN_BN_1mod_1word(JNIEnv* env, jclass, BIGNUM* a, BN_ULONG w) {
    if (!oneValidHandle(env, a)) return JNI_FALSE;
    return BN_mod_word(a, w);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1add(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* b) {
    if (!threeValidHandles(env, r, a, b)) return JNI_FALSE;
    return BN_add(r, a, b);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1sub(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* b) {
    if (!threeValidHandles(env, r, a, b)) return JNI_FALSE;
    return BN_sub(r, a, b);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1gcd(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* b) {
    if (!threeValidHandles(env, r, a, b)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_gcd(r, a, b, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1mul(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* b) {
    if (!threeValidHandles(env, r, a, b)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_mul(r, a, b, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1exp(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* p) {
    if (!threeValidHandles(env, r, a, p)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_exp(r, a, p, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1div(JNIEnv* env, jclass, BIGNUM* dv, BIGNUM* rem, BIGNUM* m, BIGNUM* d) {
    if (!fourValidHandles(env, (rem ? rem : dv), (dv ? dv : rem), m, d)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_div(dv, rem, m, d, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1nnmod(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* m) {
    if (!threeValidHandles(env, r, a, m)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_nnmod(r, a, m, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1mod_1exp(JNIEnv* env, jclass, BIGNUM* r, BIGNUM* a, BIGNUM* p, BIGNUM* m) {
    if (!fourValidHandles(env, r, a, p, m)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_mod_exp(r, a, p, m, ctx.get());
}

extern "C" jboolean Java_java_math_NativeBN_BN_1mod_1inverse(JNIEnv* env, jclass, BIGNUM* ret, BIGNUM* a, BIGNUM* n) {
    if (!threeValidHandles(env, ret, a, n)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return (BN_mod_inverse(ret, a, n, ctx.get()) != NULL);
}

extern "C" jboolean Java_java_math_NativeBN_BN_1generate_1prime_1ex(JNIEnv* env, jclass, BIGNUM* ret, int bits, jboolean safe,
        BIGNUM* add, BIGNUM* rem, jint cb) {
    if (!oneValidHandle(env, ret)) return JNI_FALSE;
    return BN_generate_prime_ex(ret, bits, safe, add, rem, reinterpret_cast<BN_GENCB*>(cb));
}

extern "C" jboolean Java_java_math_NativeBN_BN_1is_1prime_1ex(JNIEnv* env, jclass, BIGNUM* p, int nchecks, jint cb) {
    if (!oneValidHandle(env, p)) return JNI_FALSE;
    Unique_BN_CTX ctx(BN_CTX_new());
    return BN_is_prime_ex(p, nchecks, ctx.get(), reinterpret_cast<BN_GENCB*>(cb));
}

