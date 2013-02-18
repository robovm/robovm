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

public class InstField {
    public boolean mBoolean1, mBoolean2;
    public byte mByte1, mByte2;
    public char mChar1, mChar2;
    public short mShort1, mShort2;
    public int mInt1, mInt2;
    public float mFloat1, mFloat2;
    public long mLong1, mLong2;
    public double mDouble1, mDouble2;
    public volatile long mVolatileLong1, mVolatileLong2;

    public void run() {
        assignFields();
        checkFields();
        InstField.nullCheck(null);
    }

    /*
     * Check access to instance fields through a null pointer.
     */
    static public void nullCheck(InstField nully) {
        System.out.println("InstField.nullCheck");
        try {
            int x = nully.mInt1;
            assert(false);
        } catch (NullPointerException npe) {
            // good
        }
        try {
            long l = nully.mLong1;
            assert(false);
        } catch (NullPointerException npe) {
            // good
        }
        try {
            nully.mInt1 = 5;
            assert(false);
        } catch (NullPointerException npe) {
            // good
        }
        try {
            nully.mLong1 = 17L;
            assert(false);
        } catch (NullPointerException npe) {
            // good
        }
    }

    public void assignFields() {
        System.out.println("InstField assign...");
        mBoolean1 = true;
        mBoolean2 = false;
        mByte1 = 127;
        mByte2 = -128;
        mChar1 = 32767;
        mChar2 = 65535;
        mShort1 = 32767;
        mShort2 = -32768;
        mInt1 = 65537;
        mInt2 = -65537;
        mFloat1 = 3.1415f;
        mFloat2 = -1.0f / 0.0f;                // -inf
        mLong1 = 1234605616436508552L;     // 0x1122334455667788
        mLong2 = -1234605616436508552L;
        mDouble1 = 3.1415926535;
        mDouble2 = 1.0 / 0.0;               // +inf
        mVolatileLong1 = mLong1 - 1;
        mVolatileLong2 = mLong2 + 1;
    }

    public void checkFields() {
        System.out.println("InstField check...");
        assert(mBoolean1);
        assert(!mBoolean2);
        assert(mByte1 == 127);
        assert(mByte2 == -128);
        assert(mChar1 == 32767);
        assert(mChar2 == 65535);
        assert(mShort1 == 32767);
        assert(mShort2 == -32768);
        assert(mInt1 == 65537);
        assert(mInt2 == -65537);
        assert(mFloat1 > 3.141f && mFloat1 < 3.142f);
        assert(mFloat2 < mFloat1);
        assert(mLong1 == 1234605616436508552L);
        assert(mLong2 == -1234605616436508552L);
        assert(mDouble1 > 3.141592653 && mDouble1 < 3.141592654);
        assert(mDouble2 > mDouble1);
        assert(mVolatileLong1 == 1234605616436508551L);
        assert(mVolatileLong2 == -1234605616436508551L);
    }
}
