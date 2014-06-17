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

package libcore.icu;

public final class NativeIDN {
    public static String toASCII(String s, int flags) {
        return convert(s, flags, true);
    }

    public static String toUnicode(String s, int flags) {
        try {
            return convert(s, flags, false);
        } catch (IllegalArgumentException ex) {
            // The RI documentation explicitly states that this method can't fail.
            // ICU4C disagrees, as does the RI in practice.
            // The RI just returns the input string if it can't
            return s;
        }
    }

    private static String convert(String s, int flags, boolean toAscii) {
        if (s == null) {
            throw new NullPointerException("s == null");
        }
        return convertImpl(s, flags, toAscii);
    }
    private static native String convertImpl(String s, int flags, boolean toAscii);

    private NativeIDN() {}
}
