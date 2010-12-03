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

package org.apache.harmony.archive.util;

/**
 * Helpers for the archive module.
 */
public class Util {

    /**
     * Returns whether the given source string ends with the suffix, ignoring
     * case and assuming that the strings are ascii encoded.
     * 
     * @param source
     *            the string to match.
     * @param suffix
     *            the suffix to test.
     * @return {@code true} if the source does end with the given suffix, or
     *         {@code false} if not.
     */
    public static boolean asciiEndsWithIgnoreCase(String source, String suffix) {
        int length = suffix.length();
        if (length > source.length()) {
            return false;
        }
        int offset = source.length() - length;
        for (int i = 0; i < length; i++) {
            char c1 = source.charAt(i + offset);
            char c2 = suffix.charAt(i);
            if (c1 != c2 && toASCIIUpperCase(c1) != toASCIIUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares the given byte arrays and returns whether they are equal,
     * ignoring case differences and assuming they are ascii-encoded strings.
     * 
     * @param buf1
     *            first byte array to compare.
     * @param buf2
     *            second byte array to compare.
     * @return the result of the comparison.
     */
    public static boolean asciiEqualsIgnoreCase(byte[] buf1, byte[] buf2) {
        if (buf1 == null || buf2 == null) {
            return false;
        }
        if (buf1 == buf2) {
            return true;
        }
        if (buf1.length != buf2.length) {
            return false;
        }

        for (int i = 0; i < buf1.length; i++) {
            byte b1 = buf1[i];
            byte b2 = buf2[i];
            if (b1 != b2 && toASCIIUpperCase(b1) != toASCIIUpperCase(b2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares the given strings and returns whether they are equal, ignoring
     * case differences and assuming they are ascii-encoded strings.
     * 
     * @param s1
     *            first string to compare.
     * @param s2
     *            second string to compare.
     * @return the result of the comparison.
     */
    public static boolean asciiEqualsIgnoreCase(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1 == s2) {
            return true;
        }

        int length = s1.length();
        if (length != s2.length()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2 && toASCIIUpperCase(c1) != toASCIIUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }

    private static final byte toASCIIUpperCase(byte b) {
        if ('a' <= b && b <= 'z') {
            return (byte) (b - ('a' - 'A'));
        }
        return b;
    }

    private static final char toASCIIUpperCase(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) (c - ('a' - 'A'));
        }
        return c;
    }
}
