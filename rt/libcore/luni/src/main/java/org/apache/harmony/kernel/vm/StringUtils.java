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

package org.apache.harmony.kernel.vm;

/**
 * String utility functions.
 */
public final class StringUtils {
    /**
     * This class is uninstantiable.
     */
    private StringUtils() {
        // This space intentionally left blank.
    }

    /**
     * Combine a list of strings in an <code>Object[]</code> into a single
     * string.
     *
     * @param list non-null; the strings to combine
     * @return non-null; the combined form
     */
    public static String combineStrings(Object[] list) {
        int listLength = list.length;

        switch (listLength) {
            case 0: {
                return "";
            }
            case 1: {
                return (String) list[0];
            }
        }

        int strLength = 0;

        for (int i = 0; i < listLength; i++) {
            strLength += ((String) list[i]).length();
        }

        StringBuilder sb = new StringBuilder(strLength);

        for (int i = 0; i < listLength; i++) {
            sb.append(list[i]);
        }

        return sb.toString();
    }
}
