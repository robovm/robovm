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

class ArgsTest{
    public ArgsTest() {

    }

    private long mLongArray[] = new long[] {
        0x1122334455667788L, 0x9887766554433221L };

    /**
     *
     * @param a
     * @param c
     * @param d
     * @param j
     * @param f
     */
    void argTest(int a, char c, double d, long j, float f) {
        System.out.println("VALUES: " + Long.toHexString(mLongArray[0]) + " "
            + Long.toHexString(mLongArray[1]) + " " + Long.toHexString(j));
        System.out.println("VALUES: " + mLongArray[0] + " "
            + mLongArray[1] + " " + j);

        System.out.println(j);
        System.out.println("j = " + j);
        System.out.println("a=" + a + " c=" + c + " d=" + d
            + " j=" + j + " f=" + f);
    }
}
