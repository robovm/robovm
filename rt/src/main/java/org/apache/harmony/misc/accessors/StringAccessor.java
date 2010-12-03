/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.misc.accessors;

import java.nio.ByteOrder;
import java.io.UnsupportedEncodingException;

import org.apache.harmony.misc.internal.nls.Messages;

/**
 * This class allows to copy data from String objects to native buffers, and
 * construct String objects from native strings. This allows to pass and receive
 * string arguments from native libraries.
 *
 * The main difference between modified UTF8 and UTF8 is two trailing 0 instead
 * of one (see JNI 5.0 spec on Modified UTF-8 Strings).
 */
public class StringAccessor {

    private static StringAccessor instance;

    static StringAccessor getInstance() {
        if (instance == null) {
            System.loadLibrary("accessors"); //$NON-NLS-1$
            instance = new StringAccessor();
        }
        return instance;
    }

    private StringAccessor() {
    }

    /**
     * Creates modified UTF8 encoded copy of String object in native heap.
     * Allocates buffer enough to represent 0 terminated string in native heap.
     * Adds 2 trailing 0 to the end of native string. The returned buffer should
     * be deallocated by {@link MemoryAccessor#free}
     * <p>
     * This method has an effect of GetStringUTFChars JNI call.
     *
     * @param str
     *            string to copy
     * @return buffer memory address
     */
    public native long getUTFChars(String str);

    /**
     * Copies len modified UTF8 encoded chars or buflen bytes whichever is
     * smaller from String to native buffer. If there is a room in buffer after
     * last copied string char, 2 trailing 0 are added. For ANSI chars buffer
     * should be at least len bytes, for international chars buffer should be at
     * least 3 * len bytes (see JNI 5.0 spec on Modified UTF-8 Strings) to
     * guaranty String chars will fully fit.
     * <p>
     * This method has an effect of GetStringUTFRegion JNI call.
     *
     * @param buf
     *            memory address of native buf
     * @param buflen
     *            length of buffer in bytes
     * @param str
     *            string to copy
     * @param start
     *            first char index
     * @param len
     *            number of chars to copy
     * @return buf
     */
    public long getUTFChars(long buf, long buflen, String str, int start,
            int len) {
        String substr = str.substring(start, start + len);
        long addr = getUTFChars(substr);
        Malloc.strncpy(buf, addr, buflen);
        Malloc.free(addr);
        return buf;
    }

    /**
     * Creates UTF16 (Unicode) copy of String object in native heap. Allocates
     * buffer enough to represent 0 terminated string in native heap. The
     * returned buffer should be deallocated by {@link MemoryAccessor#free}
     * <p>
     * This method has an effect of GetStringChars JNI call.
     *
     * @param str -
     *            string to copy
     * @return buffer memory address
     */
    public native long getChars(String str);

    /**
     * Copies len UTF16 (Unicode) encoded chars or buflen bytes whichever is
     * smaller from String to native buffer. If there is a room in buffer after
     * last copied string char, 2 trailing 0 are added. Buffer should be at
     * least 2 * len bytes to guaranty String chars will fully fit.
     * <p>
     * This method has an effect of GetStringRegion JNI call.
     *
     * @param buf
     *            memory address of native buf
     * @param buflen
     *            length of buffer in bytes
     * @param str
     *            string to copy
     * @param start
     *            first char index
     * @param len
     *            number of chars to copy
     * @return buf
     */
    public long getChars(long buf, long buflen, String str, int start, int len) {
        String substr = str.substring(start, start + len);
        long addr = getChars(substr);
        Malloc.memcpy(buf, addr, Math.min(buflen, (substr.length() + 1) * 2));
        Malloc.free(addr);
        return buf;
    }

    /**
     * Creates String from 0 terminated modified UTF8 native string
     * <p>
     * This method has an effect of NewStringUTF JNI call.
     *
     * @param str
     *            memory address of native
     * @return new String
     */
    public native String createStringUTF(long str);

    /**
     * Creates String from 0 terminated UTF16 (Unicode) native string
     * <p>
     * This method has an effect of NewString JNI call.
     *
     * @param wstr
     *            memory address of native
     * @return new String
     */
    public native String createString(long wstr);

    /**
     * Creates String from 0 terminated modified UTF8 native string of max
     * strlen length in bytes.
     * <p>
     * This method has an effect of NewStringUTF JNI call.
     *
     * @param str
     *            memory address of native
     * @param strlen
     *            max length of native string in bytes
     * @return new String
     */
    public native String createStringUTF(long str, long strlen);

    /**
     * Creates String from 0 terminated UTF16 (Unicode) native string of max
     * strlen length in bytes.
     * <p>
     * This method has an effect of NewString JNI call.
     *
     * @param wstr
     *            memory address of native
     * @param strlen
     *            max length of native string in bytes
     * @return new String
     */
    public native String createString(long wstr, long strlen);

    /**
     * Creates String from 0 terminated UTF16 (Unicode) native string of max
     * strlen length in bytes.
     * <p>
     * This method has an effect of NewString JNI call.
     *
     * @param wstr
     *            memory address of native
     * @param order
     *            byte order
     * @return new String
     */
    public native String createOrderedString(long wstr, int order);

    /**
     * Lexicographically compares Java string with native UTF-8 encoded string .
     * The comparison is based on the Unicode value of each character in the
     * strings. The character sequence represented by String object is compared
     * lexicographically to the character sequence represented by the native
     * UTF-8 string. The result is a negative integer if String object
     * lexicographically precedes the argument str. The result is a positive
     * integer if this String object lexicographically follows the argument str.
     * The result is zero if the strings are equal.
     * @param strObj Java string
     * @param str native string memory address
     * @return 0 if strings are equal,
     * &lt;0 if Java string is lexicographically less than the native string,
     * &gt;0 if Java string is lexicographically greater than the native string.
     */
    public int compareStringUTF(String strObj, long str) {
        return strObj.compareTo(createStringUTF(str));
    }

    /**
     * Lexicographically compares Java string with native UTF-16 encoded string
     * in byteOrder order.
     * @param strObj Java string
     * @param str native string memory address
     * @param len length of native string
     * @param byteOrder either ByteOrder.BIG_ENDIAN or ByteOrder.LITTLE_ENDIAN
     * @return 0 if strings are equal,
     * &lt;0 if Java string is lexicographically less than the native string,
     * &gt;0 if Java string is lexicographically greater than the native string.
     */
    public int compareString(String strObj, long str, int len, int byteOrder) {
        ByteOrder or = ByteOrder.nativeOrder();
        String cmpTo = createString(str, 2 * len);
        if ((byteOrder == 1 && or == ByteOrder.BIG_ENDIAN)
                || (byteOrder == 2 && or == ByteOrder.LITTLE_ENDIAN)) {
            return strObj.compareTo(cmpTo);
        }
        try {
            if (byteOrder == 1) {
                cmpTo = new String(cmpTo.getBytes("UTF-16BE"), "UTF-16BE"); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                cmpTo = new String(cmpTo.getBytes("UTF-16LE"), "UTF-16LE"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        } catch (UnsupportedEncodingException e) {
            throw new InternalError(
                    // misc.6=Failed to get UTF-16 support, this is a bug
                    Messages.getString("misc.6")); //$NON-NLS-1$
        }
        return strObj.compareTo(cmpTo);
    }

    /**
     * Lexicographically compares Java string with native UTF-16 encoded string
     * in platform default order.
     * @param strObj Java string
     * @param str native string memory address
     * @param len length of native string
     * @return 0 if strings are equal,
     * &lt;0 if Java string is lexicographically less than the native string,
     * &gt;0 if Java string is lexicographically greater than the native string.
     */
    public int compareString(String strObj, long str, int len) {
        return strObj.compareTo(createString(str, 2 * len));
    }

    /**
     * Lexicographically compares the Java string with the character sequence
     * represented by the part of char array str.
     * @param strObj Java string
     * @param str char array
     * @param offset offset within char array
     * @return 0 if strings are equal,
     * &lt;0 if Java string is lexicographically less than the native string,
     * &gt;0 if Java string is lexicographically greater than the native string.
     */
    public int compareStringAndChars(String strObj, char[] str, int offset,
            int len) {
        return strObj.compareTo(new String(str, offset, len));
    }

}
