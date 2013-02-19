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

/*
 * In contrast to BigIntegers this class doesn't fake two's complement representation.
 * Any Bit-Operations, including Shifting, solely regard the unsigned magnitude.
 * Moreover BigInt objects are mutable and offer efficient in-place-operations.
 */
final class BigInt {

    /* Fields used for the internal representation. */
    transient int bignum = 0;

    @Override protected void finalize() throws Throwable {
        try {
            if (this.bignum != 0) {
                NativeBN.BN_free(this.bignum);
                this.bignum = 0;
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public String toString() {
        return this.decString();
    }

    int getNativeBIGNUM() {
        return this.bignum;
    }

    static int consumeErrors(StringBuilder sb) {
        int cnt = 0;
        int e, reason;
        while ((e = NativeBN.ERR_get_error()) != 0) {
            reason = e & 255;
            if (reason == 103) {
                throw new ArithmeticException("BigInteger division by zero");
            }
            if (reason == 108) {
                throw new ArithmeticException("BigInteger not invertible");
            }
            if (reason == 65) {
                throw new OutOfMemoryError();
            }
            sb.append(e).append(": ");
            String s = NativeBN.ERR_error_string(e);
            sb.append(s);
            cnt++;
        }
        return cnt;
    }

    private static void Check(boolean success) {
        if (!success) {
            StringBuilder sb = new StringBuilder("(openssl)ERR: ");
            int cnt = consumeErrors(sb);
            if (cnt > 0)
                throw new ArithmeticException(sb.toString());
        }
    }


    private void makeValid() {
        if (this.bignum == 0) {
            this.bignum = NativeBN.BN_new();
            Check(this.bignum != 0);
        }
    }

    private static BigInt newBigInt() {
        BigInt bi = new BigInt();
        bi.bignum = NativeBN.BN_new();
        Check(bi.bignum != 0);
        return bi;
    }


    static int cmp(BigInt a, BigInt b) {
        return NativeBN.BN_cmp(a.bignum, b.bignum);
    }


    void putCopy(BigInt from) {
        this.makeValid();
        Check(NativeBN.BN_copy(this.bignum, from.bignum));
    }

    BigInt copy() {
        BigInt bi = new BigInt();
        bi.putCopy(this);
        return bi;
    }


    void putLongInt(long val) {
        this.makeValid();
        Check(NativeBN.putLongInt(this.bignum, val));
    }

    void putULongInt(long val, boolean neg) {
        this.makeValid();
        Check(NativeBN.putULongInt(this.bignum, val, neg));
    }

    private NumberFormatException invalidBigInteger(String s) {
        throw new NumberFormatException("Invalid BigInteger: " + s);
    }

    void putDecString(String original) {
        String s = checkString(original, 10);
        this.makeValid();
        int usedLen = NativeBN.BN_dec2bn(this.bignum, s);
        Check((usedLen > 0));
        if (usedLen < s.length()) {
            throw invalidBigInteger(original);
        }
    }

    void putHexString(String original) {
        String s = checkString(original, 16);
        this.makeValid();
        int usedLen = NativeBN.BN_hex2bn(this.bignum, s);
        Check((usedLen > 0));
        if (usedLen < s.length()) {
            throw invalidBigInteger(original);
        }
    }

    /**
     * Returns a string suitable for passing to OpenSSL.
     * Throws if 's' doesn't match Java's rules for valid BigInteger strings.
     * BN_dec2bn and BN_hex2bn do very little checking, so we need to manually
     * ensure we comply with Java's rules.
     * http://code.google.com/p/android/issues/detail?id=7036
     */
    String checkString(String s, int base) {
        if (s == null) {
            throw new NullPointerException();
        }
        // A valid big integer consists of an optional '-' or '+' followed by
        // one or more digit characters appropriate to the given base,
        // and no other characters.
        int charCount = s.length();
        int i = 0;
        if (charCount > 0) {
            char ch = s.charAt(0);
            if (ch == '+') {
                // Java supports leading +, but OpenSSL doesn't, so we need to strip it.
                s = s.substring(1);
                --charCount;
            } else if (ch == '-') {
                ++i;
            }
        }
        if (charCount - i == 0) {
            throw invalidBigInteger(s);
        }
        boolean nonAscii = false;
        for (; i < charCount; ++i) {
            char ch = s.charAt(i);
            if (Character.digit(ch, base) == -1) {
                throw invalidBigInteger(s);
            }
            if (ch > 128) {
                nonAscii = true;
            }
        }
        return nonAscii ? toAscii(s, base) : s;
    }

    // Java supports non-ASCII decimal digits, but OpenSSL doesn't.
    // We need to translate the decimal digits but leave any other characters alone.
    // This method assumes it's being called on a string that has already been validated.
    private static String toAscii(String s, int base) {
        int length = s.length();
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            char ch = s.charAt(i);
            int value = Character.digit(ch, base);
            if (value >= 0 && value <= 9) {
                ch = (char) ('0' + value);
            }
            result.append(ch);
        }
        return result.toString();
    }

    void putBigEndian(byte[] a, boolean neg) {
        this.makeValid();
        Check(NativeBN.BN_bin2bn(a, a.length, neg, this.bignum));
    }

    void putLittleEndianInts(int[] a, boolean neg) {
        this.makeValid();
        Check(NativeBN.litEndInts2bn(a, a.length, neg, this.bignum));
    }

    void putBigEndianTwosComplement(byte[] a) {
        this.makeValid();
        Check(NativeBN.twosComp2bn(a, a.length, this.bignum));
    }


    long longInt() {
        return NativeBN.longInt(this.bignum);
    }

    String decString() {
        return NativeBN.BN_bn2dec(this.bignum);
    }

    String hexString() {
        return NativeBN.BN_bn2hex(this.bignum);
    }

    byte[] bigEndianMagnitude() {
        return NativeBN.BN_bn2bin(this.bignum);
    }

    int[] littleEndianIntsMagnitude() {
        return NativeBN.bn2litEndInts(this.bignum);
    }

    int sign() {
        return NativeBN.sign(this.bignum);
    }

    void setSign(int val) {
        if (val > 0) {
            NativeBN.BN_set_negative(this.bignum, 0);
        } else {
            if (val < 0) NativeBN.BN_set_negative(this.bignum, 1);
        }
    }

    boolean twosCompFitsIntoBytes(int desiredByteCount) {
        int actualByteCount = (NativeBN.bitLength(this.bignum) + 7) / 8;
        return actualByteCount <= desiredByteCount;
    }

    int bitLength() {
        return NativeBN.bitLength(this.bignum);
    }

    boolean isBitSet(int n) {
        return NativeBN.BN_is_bit_set(this.bignum, n);
    }

    // n > 0: shift left (multiply)
    static BigInt shift(BigInt a, int n) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_shift(r.bignum, a.bignum, n));
        return r;
    }

    void shift(int n) {
        Check(NativeBN.BN_shift(this.bignum, this.bignum, n));
    }

    void addPositiveInt(int w) {
        Check(NativeBN.BN_add_word(this.bignum, w));
    }

    void multiplyByPositiveInt(int w) {
        Check(NativeBN.BN_mul_word(this.bignum, w));
    }

    static int remainderByPositiveInt(BigInt a, int w) {
        int rem = NativeBN.BN_mod_word(a.bignum, w);
        Check(rem != -1);
        return rem;
    }

    static BigInt addition(BigInt a, BigInt b) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_add(r.bignum, a.bignum, b.bignum));
        return r;
    }

    void add(BigInt a) {
        Check(NativeBN.BN_add(this.bignum, this.bignum, a.bignum));
    }

    static BigInt subtraction(BigInt a, BigInt b) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_sub(r.bignum, a.bignum, b.bignum));
        return r;
    }


    static BigInt gcd(BigInt a, BigInt b) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_gcd(r.bignum, a.bignum, b.bignum));
        return r;
    }

    static BigInt product(BigInt a, BigInt b) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_mul(r.bignum, a.bignum, b.bignum));
        return r;
    }

    static BigInt bigExp(BigInt a, BigInt p) {
        // Sign of p is ignored!
        BigInt r = newBigInt();
        Check(NativeBN.BN_exp(r.bignum, a.bignum, p.bignum));
        return r;
    }

    static BigInt exp(BigInt a, int p) {
        // Sign of p is ignored!
        BigInt power = new BigInt();
        power.putLongInt(p);
        return bigExp(a, power);
        // OPTIONAL:
        // int BN_sqr(BigInteger r, BigInteger a, BN_CTX ctx);
        // int BN_sqr(BIGNUM *r, const BIGNUM *a,BN_CTX *ctx);
    }

    static void division(BigInt dividend, BigInt divisor,
            BigInt quotient, BigInt remainder) {
        int quot, rem;
        if (quotient != null) {
            quotient.makeValid();
            quot = quotient.bignum;
        } else {
            quot = 0;
        }
        if (remainder != null) {
            remainder.makeValid();
            rem = remainder.bignum;
        } else {
            rem = 0;
        }
        Check(NativeBN.BN_div(quot, rem, dividend.bignum, divisor.bignum));
    }

    static BigInt modulus(BigInt a, BigInt m) {
        // Sign of p is ignored! ?
        BigInt r = newBigInt();
        Check(NativeBN.BN_nnmod(r.bignum, a.bignum, m.bignum));
        return r;
    }

    static BigInt modExp(BigInt a, BigInt p, BigInt m) {
        // Sign of p is ignored!
        BigInt r = newBigInt();
        Check(NativeBN.BN_mod_exp(r.bignum, a.bignum, p.bignum, m.bignum));

        // OPTIONAL:
        // int BN_mod_sqr(BIGNUM *r, const BIGNUM *a, const BIGNUM *m, BN_CTX *ctx);
        return r;
    }


    static BigInt modInverse(BigInt a, BigInt m) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_mod_inverse(r.bignum, a.bignum, m.bignum));
        return r;
    }


    static BigInt generatePrimeDefault(int bitLength) {
        BigInt r = newBigInt();
        Check(NativeBN.BN_generate_prime_ex(r.bignum, bitLength, false, 0, 0, 0));
        return r;
    }

    boolean isPrime(int certainty) {
        return NativeBN.BN_is_prime_ex(bignum, certainty, 0);
    }
}
