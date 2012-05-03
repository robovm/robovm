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

package java.math;

final class NativeBN {

    public static native int ERR_get_error();
    // unsigned long ERR_get_error(void);

    public static native String ERR_error_string(int e);
    // char *ERR_error_string(unsigned long e, char *buf);

    public static native int BN_new();
    // BIGNUM *BN_new(void);

    public static native void BN_free(int a);
    // void BN_free(BIGNUM *a);

    public static native int BN_cmp(int a, int b);
    // int BN_cmp(const BIGNUM *a, const BIGNUM *b);

    public static native boolean BN_copy(int to, int from);
    // Returns boolean success AND NOT result BIGNUM handle!
    // BIGNUM *BN_copy(BIGNUM *to, const BIGNUM *from);
//    public static native int BN_dup(int from);
    // BIGNUM *BN_dup(const BIGNUM *a);


    public static native boolean putLongInt(int a, long dw);

    public static native boolean putULongInt(int a, long dw, boolean neg);

    public static native int BN_dec2bn(int a, String str);
    // int BN_dec2bn(BIGNUM **a, const char *str);

    public static native int BN_hex2bn(int a, String str);
    // int BN_hex2bn(BIGNUM **a, const char *str);

    public static native boolean BN_bin2bn(byte[] s, int len, boolean neg, int ret);
    // Returns boolean success AND NOT result BIGNUM handle!
    // BIGNUM * BN_bin2bn(const unsigned char *s, int len, BIGNUM *ret);
    // BN-Docu: s is taken as unsigned big endian;
    // Additional parameter: neg.

    public static native boolean litEndInts2bn(int[] ints, int len, boolean neg, int ret);

    public static native boolean twosComp2bn(byte[] s, int len, int ret);


    public static native long longInt(int a);
    // unsigned long BN_get_word(BIGNUM *a);

    public static native String BN_bn2dec(int a);
    // char * BN_bn2dec(const BIGNUM *a);

    public static native String BN_bn2hex(int a);
    // char * BN_bn2hex(const BIGNUM *a);

    public static native byte[] BN_bn2bin(int a);
    // Returns result byte[] AND NOT length.
    // int BN_bn2bin(const BIGNUM *a, unsigned char *to);

    public static native int[] bn2litEndInts(int a);

    public static native int sign(int a);
    // Returns -1, 0, 1 AND NOT boolean.
    // #define BN_is_negative(a) ((a)->neg != 0)

    public static native void BN_set_negative(int b, int n);
    // void BN_set_negative(BIGNUM *b, int n);

    public static native int bitLength(int a);

    public static native boolean BN_is_bit_set(int a, int n);
    // int BN_is_bit_set(const BIGNUM *a, int n);

    public static native boolean modifyBit(int a, int n, int op);
    // Returns boolean success.
    // op: 0 = reset; 1 = set; -1 = flip
    // uses BN_set_bit(), BN_clear_bit() and BN_is_bit_set()

    public static native boolean BN_shift(int r, int a, int n);
    // int BN_shift(BIGNUM *r, const BIGNUM *a, int n);

    public static native boolean BN_add_word(int a, int w);
    // ATTENTION: w is treated as unsigned.
    // int BN_add_word(BIGNUM *a, BN_ULONG w);

    public static native boolean BN_sub_word(int a, int w);
    // ATTENTION: w is treated as unsigned.
    // int BN_sub_word(BIGNUM *a, BN_ULONG w);

    public static native boolean BN_mul_word(int a, int w);
    // ATTENTION: w is treated as unsigned.
    // int BN_mul_word(BIGNUM *a, BN_ULONG w);

    public static native int BN_div_word(int a, int w);
    // ATTENTION: w is treated as unsigned.
    // BN_ULONG BN_div_word(BIGNUM *a, BN_ULONG w);

    public static native int BN_mod_word(int a, int w);
    // ATTENTION: w is treated as unsigned.
    // BN_ULONG BN_mod_word(BIGNUM *a, BN_ULONG w);

    public static native boolean BN_add(int r, int a, int b);
    // int BN_add(BIGNUM *r, const BIGNUM *a, const BIGNUM *b);

    public static native boolean BN_sub(int r, int a, int b);
    // int BN_sub(BIGNUM *r, const BIGNUM *a, const BIGNUM *b);

    public static native boolean BN_gcd(int r, int a, int b);
    // int BN_gcd(BIGNUM *r, const BIGNUM *a, const BIGNUM *b, BN_CTX *ctx);

    public static native boolean BN_mul(int r, int a, int b);
    // int BN_mul(BIGNUM *r, const BIGNUM *a, const BIGNUM *b, BN_CTX *ctx);

    public static native boolean BN_exp(int r, int a, int p);
    // int BN_exp(BIGNUM *r, const BIGNUM *a, const BIGNUM *p, BN_CTX *ctx);

    // OPTIONAL:
//    public static native int BN_sqr(BigInteger r, BigInteger a, BN_CTX ctx);
    // int BN_sqr(BIGNUM *r, const BIGNUM *a,BN_CTX *ctx);

    public static native boolean BN_div(int dv, int rem, int m, int d);
    // int BN_div(BIGNUM *dv, BIGNUM *rem, const BIGNUM *m, const BIGNUM *d, BN_CTX *ctx);

    public static native boolean BN_nnmod(int r, int a, int m);
    // int BN_nnmod(BIGNUM *r, const BIGNUM *a, const BIGNUM *m, BN_CTX *ctx);

    public static native boolean BN_mod_exp(int r, int a, int p, int m);
    // int BN_mod_exp(BIGNUM *r, const BIGNUM *a, const BIGNUM *p, const BIGNUM *m, BN_CTX *ctx);

    // OPTIONAL:
//    public static native boolean BN_mod_sqr(BigInteger r, BigInteger a, BigInteger m, BN_CTX ctx);
    // int BN_mod_sqr(BIGNUM *r, const BIGNUM *a, const BIGNUM *m, BN_CTX *ctx);


    public static native boolean BN_mod_inverse(int ret, int a, int n);
    // Returns boolean success AND NOT result BIGNUM handle!
    // BIGNUM * BN_mod_inverse(BIGNUM *ret, const BIGNUM *a, const BIGNUM *n, BN_CTX *ctx);


    public static native boolean BN_generate_prime_ex(int ret, int bits, boolean safe,
            int add, int rem, int cb);
    // int BN_generate_prime_ex(BIGNUM *ret, int bits, int safe,
    //         const BIGNUM *add, const BIGNUM *rem, BN_GENCB *cb);

    public static native boolean BN_is_prime_ex(int p, int nchecks, int cb);
    // int BN_is_prime_ex(const BIGNUM *p, int nchecks, BN_CTX *ctx, BN_GENCB *cb);

}
