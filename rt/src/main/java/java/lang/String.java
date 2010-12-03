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

package java.lang;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Locale;

import java.util.regex.Pattern;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.AccessController;
import java.util.regex.PatternSyntaxException;

import org.apache.harmony.kernel.vm.VM;
import org.apache.harmony.luni.util.PriviAction;

/**
 * An immutable sequence of characters/code units ({@code char}s). A
 * {@code String} is represented by array of UTF-16 values, such that
 * Unicode supplementary characters (code points) are stored/encoded as
 * surrogate pairs via Unicode code units ({@code char}).
 *
 * @see StringBuffer
 * @see StringBuilder
 * @see Charset
 * @since 1.0
 */
public final class String implements Serializable, Comparable<String>,
        CharSequence {

    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * An PrintStream used for System.out which performs the correct character
     * conversion for the console, since the console may use a different
     * conversion than the default file.encoding.
     */
    static class ConsolePrintStream extends java.io.PrintStream {
        private static String charset;

        static {
            charset = AccessController.doPrivileged(new PriviAction<String>(
                    "console.encoding", "ISO8859_1")); //$NON-NLS-1$ //$NON-NLS-2$
            if (!Charset.isSupported(charset)) {
                charset = "ISO-8859-1"; //$NON-NLS-1$
            }
        }

        /**
         * Create a ConsolePrintStream on the specified OutputStream, usually
         * System.out.
         * 
         * @param out
         *            the console OutputStream
         */
        public ConsolePrintStream(java.io.OutputStream out) {
            super(out, true);

        }

        /**
         * Override the print(String) method from PrintStream to perform the
         * character conversion using the console character converter.
         *
         * @param str
         *            the string to convert
         */
        @Override
        public void print(String str) {
            if (str == null) {
                str = "null"; //$NON-NLS-1$
            }

            try {
                write(str.getBytes(charset));
            } catch (java.io.IOException e) {
                setError();
            }
        }
    }

    /**
     * CaseInsensitiveComparator compares Strings ignoring the case of the
     * characters.
     */
    private static final class CaseInsensitiveComparator implements
            Comparator<String>, Serializable {
        private static final long serialVersionUID = 8575799808933029326L;

        /**
         * Compare the two objects to determine the relative ordering.
         *
         * @param o1
         *            an Object to compare
         * @param o2
         *            an Object to compare
         * @return an int < 0 if object1 is less than object2, 0 if they are
         *         equal, and > 0 if object1 is greater
         *
         * @exception ClassCastException
         *                if objects are not the correct type
         */
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    /**
     * A comparator ignoring the case of the characters.
     */
    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();

    private static final char[] ascii;

    private final char[] value;

    private final int offset;

    private final int count;

    private int hashCode;

    private static Charset DefaultCharset;

    private static Charset lastCharset;

    static {
        ascii = new char[128];
        for (int i = 0; i < ascii.length; i++) {
            ascii[i] = (char) i;
        }
    }

    /**
     * Creates an empty string.
     */
    public String() {
        value = new char[0];
        offset = 0;
        count = 0;
    }

    /*
     * Private constructor used for JIT optimization.
     */
    @SuppressWarnings("unused")
    private String(String s, char c) {
        offset = 0;
        value = new char[s.count + 1];
        count = s.count + 1;
        System.arraycopy(s.value, s.offset, value, 0, s.count);
        value[s.count] = c;
    }

    /**
     * Converts the byte array to a string using the default encoding as
     * specified by the file.encoding system property. If the system property is
     * not defined, the default encoding is ISO8859_1 (ISO-Latin-1). If 8859-1
     * is not available, an ASCII encoding is used.
     * 
     * @param data
     *            the byte array to convert to a string.
     */
    public String(byte[] data) {
        this(data, 0, data.length);
    }

    /**
     * Converts the byte array to a string, setting the high byte of every
     * character to the specified value.
     * 
     * @param data
     *            the byte array to convert to a string.
     * @param high
     *            the high byte to use.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @deprecated Use {@link #String(byte[])} or
     *             {@link #String(byte[], String)} instead.
     */
    @Deprecated
    public String(byte[] data, int high) {
        this(data, high, 0, data.length);
    }

    /**
     * Converts the byte array to a string using the default encoding as
     * specified by the file.encoding system property. If the system property is
     * not defined, the default encoding is ISO8859_1 (ISO-Latin-1). If 8859-1
     * is not available, an ASCII encoding is used.
     * 
     * @param data
     *            the byte array to convert to a string.
     * @param start
     *            the starting offset in the byte array.
     * @param length
     *            the number of bytes to convert.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0, start < 0} or {@code start + length >
     *             data.length}.
     */
    public String(byte[] data, int start, int length) {
        // start + length could overflow, start/length maybe MaxInt
        if (start >= 0 && 0 <= length && length <= data.length - start) {
            offset = 0;
            Charset charset = defaultCharset();
            int result;
            CharBuffer cb = charset
                    .decode(ByteBuffer.wrap(data, start, length));
            if ((result = cb.length()) > 0) {
                value = cb.array();
                count = result;
            } else {
                count = 0;
                value = new char[0];
            }
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /**
     * Converts the byte array to a string, setting the high byte of every
     * character to the specified value.
     *
     * @param data
     *            the byte array to convert to a string.
     * @param high
     *            the high byte to use.
     * @param start
     *            the starting offset in the byte array.
     * @param length
     *            the number of bytes to convert.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0, start < 0} or
     *             {@code start + length > data.length}
     *
     * @deprecated Use {@link #String(byte[], int, int)} instead.
     */
    @Deprecated
    public String(byte[] data, int high, int start, int length) {
        if (data != null) {
            // start + length could overflow, start/length maybe MaxInt
            if (start >= 0 && 0 <= length && length <= data.length - start) {
                offset = 0;
                value = new char[length];
                count = length;
                high <<= 8;
                for (int i = 0; i < count; i++) {
                    value[i] = (char) (high + (data[start++] & 0xff));
                }
            } else {
                throw new StringIndexOutOfBoundsException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Converts the byte array to a string using the specified encoding.
     * 
     * @param data
     *            the byte array to convert to a string.
     * @param start
     *            the starting offset in the byte array.
     * @param length
     *            the number of bytes to convert.
     * @param encoding
     *            the encoding.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0, start < 0} or {@code start + length >
     *             data.length}.
     * @throws UnsupportedEncodingException
     *             if {@code encoding} is not supported.
     */
    public String(byte[] data, int start, int length, final String encoding)
            throws UnsupportedEncodingException {
        if (encoding == null) {
            throw new NullPointerException();
        }
        // start + length could overflow, start/length maybe MaxInt
        if (start >= 0 && 0 <= length && length <= data.length - start) {
            offset = 0;
            Charset charset = getCharset(encoding);

            int result;
        	CharBuffer cb;
            try {
            	cb = charset.decode(ByteBuffer.wrap(data, start, length));
            } catch (Exception e) {
            	// do nothing. according to spec: 
            	// behavior is unspecified for invalid array
            	cb = CharBuffer.wrap("\u003f".toCharArray()); //$NON-NLS-1$
            }
            if ((result = cb.length()) > 0) {
                value = cb.array();
                count = result;
            } else {
                count = 0;
                value = new char[0];
            }
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /**
     * Converts the byte array to a string using the specified encoding.
     * 
     * @param data
     *            the byte array to convert to a string.
     * @param encoding
     *            the encoding.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @throws UnsupportedEncodingException
     *             if {@code encoding} is not supported.
     */
    public String(byte[] data, String encoding) throws UnsupportedEncodingException {
        this(data, 0, data.length, encoding);
    }
    
    /**
     * Converts the byte array to a String using the specified encoding.
     * 
     * @param data
     *            the byte array to convert to a String
     * @param start
     *            the starting offset in the byte array
     * @param length
     *            the number of bytes to convert
     * @param encoding
     *            the encoding
     * 
     * @throws IndexOutOfBoundsException
     *             when <code>length < 0, start < 0</code> or
     *             <code>start + length > data.length</code>
     * @throws NullPointerException
     *             when data is null
     * 
     * @see #getBytes()
     * @see #getBytes(int, int, byte[], int)
     * @see #getBytes(String)
     * @see #valueOf(boolean)
     * @see #valueOf(char)
     * @see #valueOf(char[])
     * @see #valueOf(char[], int, int)
     * @see #valueOf(double)
     * @see #valueOf(float)
     * @see #valueOf(int)
     * @see #valueOf(long)
     * @see #valueOf(Object)
     * @since 1.6
     */
    public String(byte[] data, int start, int length, final Charset encoding) {
        if (encoding == null) {
            throw new NullPointerException();
        }
        // start + length could overflow, start/length maybe MaxInt
        if (start >= 0 && 0 <= length && length <= data.length - start) {
            offset = 0;
            lastCharset = encoding;
            
            CharBuffer cb = encoding
                    .decode(ByteBuffer.wrap(data, start, length));
            value = cb.array();
            count = cb.length();
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }
    
    /**
     * Converts the byte array to a String using the specified encoding.
     * 
     * @param data
     *            the byte array to convert to a String
     * @param encoding
     *            the encoding
     * 
     * @throws NullPointerException
     *             when data is null
     * 
     * @see #getBytes()
     * @see #getBytes(int, int, byte[], int)
     * @see #getBytes(String)
     * @see #valueOf(boolean)
     * @see #valueOf(char)
     * @see #valueOf(char[])
     * @see #valueOf(char[], int, int)
     * @see #valueOf(double)
     * @see #valueOf(float)
     * @see #valueOf(int)
     * @see #valueOf(long)
     * @see #valueOf(Object)
     * @since 1.6
     */
    public String(byte[] data, Charset encoding) {
        this(data, 0, data.length, encoding);
    }

    /**
     * Initializes this string to contain the characters in the specified
     * character array. Modifying the character array after creating the string
     * has no effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     */
    public String(char[] data) {
        this(data, 0, data.length);
    }

    /**
     * Initializes this string to contain the specified characters in the
     * character array. Modifying the character array after creating the string
     * has no effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @param start
     *            the starting offset in the character array.
     * @param length
     *            the number of characters to use.
     * @throws NullPointerException
     *             when {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0, start < 0} or {@code start + length >
     *             data.length}
     */
    public String(char[] data, int start, int length) {
        // range check everything so a new char[] is not created
        // start + length could overflow, start/length maybe MaxInt
        if (start >= 0 && 0 <= length && length <= data.length - start) {
            offset = 0;
            value = new char[length];
            count = length;
            System.arraycopy(data, start, value, 0, count);
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /*
     * Internal version of string constructor. Does not range check, null check,
     * or copy the character array.
     */
    String(int start, int length, char[] data) {
        value = data;
        offset = start;
        count = length;
    }

    /**
     * Creates a {@code String} that is a copy of the specified string.
     * 
     * @param string
     *            the string to copy.
     */
    public String(String string) {
        value = string.value;
        offset = string.offset;
        count = string.count;
    }

    /*
     * Private constructor useful for JIT optimization.
     */
    @SuppressWarnings( { "unused", "nls" })
    private String(String s1, String s2) {
        if (s1 == null) {
            s1 = "null";
        }
        if (s2 == null) {
            s2 = "null";
        }
        count = s1.count + s2.count;
        value = new char[count];
        offset = 0;
        System.arraycopy(s1.value, s1.offset, value, 0, s1.count);
        System.arraycopy(s2.value, s2.offset, value, s1.count, s2.count);
    }

    /*
     * Private constructor useful for JIT optimization.
     */
    @SuppressWarnings( { "unused", "nls" })
    private String(String s1, String s2, String s3) {
        if (s1 == null) {
            s1 = "null";
        }
        if (s2 == null) {
            s2 = "null";
        }
        if (s3 == null) {
            s3 = "null";
        }
        count = s1.count + s2.count + s3.count;
        value = new char[count];
        offset = 0;
        System.arraycopy(s1.value, s1.offset, value, 0, s1.count);
        System.arraycopy(s2.value, s2.offset, value, s1.count, s2.count);
        System.arraycopy(s3.value, s3.offset, value, s1.count + s2.count,
                s3.count);
    }

    /**
     * Creates a {@code String} from the contents of the specified
     * {@code StringBuffer}.
     * 
     * @param stringbuffer
     *            the buffer to get the contents from.
     */
    public String(StringBuffer stringbuffer) {
        offset = 0;
        synchronized (stringbuffer) {
            value = stringbuffer.shareValue();
            count = stringbuffer.length();
        }
    }

    /**
     * Creates a {@code String} from the sub-array of Unicode code points.
     *
     * @param codePoints
     *            the array of Unicode code points to convert.
     * @param offset
     *            the inclusive index into {@code codePoints} to begin
     *            converting from.
     * @param count
     *            the number of elements in {@code codePoints} to copy.
     * @throws NullPointerException
     *             if {@code codePoints} is {@code null}.
     * @throws IllegalArgumentException
     *             if any of the elements of {@code codePoints} are not valid
     *             Unicode code points.
     * @throws IndexOutOfBoundsException
     *             if {@code offset} or {@code count} are not within the bounds
     *             of {@code codePoints}.
     * @since 1.5
     */
    public String(int[] codePoints, int offset, int count) {
        super();
        if (codePoints == null) {
            throw new NullPointerException();
        }
        if (offset < 0 || count < 0
                || (long) offset + (long) count > codePoints.length) {
            throw new IndexOutOfBoundsException();
        }
        this.offset = 0;
        this.value = new char[count * 2];
        int end = offset + count;
        int c = 0;
        for (int i = offset; i < end; i++) {
            c += Character.toChars(codePoints[i], this.value, c);
        }
        this.count = c;
    }

    /**
     * Creates a {@code String} from the contents of the specified {@code
     * StringBuilder}.
     * 
     * @param sb
     *            the {@code StringBuilder} to copy the contents from.
     * @throws NullPointerException
     *             if {@code sb} is {@code null}.
     * @since 1.5
     */
    public String(StringBuilder sb) {
        if (sb == null) {
            throw new NullPointerException();
        }
        this.offset = 0;
        this.count = sb.length();
        this.value = new char[this.count];
        sb.getChars(0, this.count, this.value, 0);
    }

    /*
     * Creates a {@code String} that is s1 + v1. May be used by JIT code.
     */
    @SuppressWarnings("unused")
    private String(String s1, int v1) {
        if (s1 == null) {
            s1 = "null"; //$NON-NLS-1$
        }
        String s2 = String.valueOf(v1);
        int len = s1.count + s2.count;
        value = new char[len];
        offset = 0;
        System.arraycopy(s1.value, s1.offset, value, 0, s1.count);
        System.arraycopy(s2.value, s2.offset, value, s1.count, s2.count);
        count = len;
    }

    /**
     * Returns the character at the specified offset in this string.
     * 
     * @param index
     *            the zero-based index in this string.
     * @return the character at the index.
     * @throws IndexOutOfBoundsException
     *             if {@code index < 0} or {@code index >= length()}.
     */
    public char charAt(int index) {
        if (0 <= index && index < count) {
            return value[offset + index];
        }
        throw new StringIndexOutOfBoundsException();
    }

    // Optimized for ASCII
    private char compareValue(char ch) {
        if (ch < 128) {
            if ('A' <= ch && ch <= 'Z') {
                return (char) (ch + ('a' - 'A'));
            }
            return ch;
        }
        return Character.toLowerCase(Character.toUpperCase(ch));
    }

    // Optimized for ASCII
    private char toLowerCase(char ch) {
        if (ch < 128) {
            if ('A' <= ch && ch <= 'Z') {
                return (char) (ch + ('a' - 'A'));
            }
            return ch;
        }
        return Character.toLowerCase(ch);
    }

    // Optimized for ASCII
    private char toUpperCase(char ch) {
        if (ch < 128) {
            if ('a' <= ch && ch <= 'z') {
                return (char) (ch - ('a' - 'A'));
            }
            return ch;
        }
        return Character.toUpperCase(ch);
    }

    /**
     * Compares the specified string to this string using the Unicode values of
     * the characters. Returns 0 if the strings contain the same characters in
     * the same order. Returns a negative integer if the first non-equal
     * character in this string has a Unicode value which is less than the
     * Unicode value of the character at the same position in the specified
     * string, or if this string is a prefix of the specified string. Returns a
     * positive integer if the first non-equal character in this string has a
     * Unicode value which is greater than the Unicode value of the character at
     * the same position in the specified string, or if the specified string is
     * a prefix of this string.
     * 
     * @param string
     *            the string to compare.
     * @return 0 if the strings are equal, a negative integer if this string is
     *         before the specified string, or a positive integer if this string
     *         is after the specified string.
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public int compareTo(String string) {
        // Code adapted from K&R, pg 101
        int o1 = offset, o2 = string.offset, result;
        int end = offset + (count < string.count ? count : string.count);
        char[] target = string.value;
        while (o1 < end) {
            if ((result = value[o1++] - target[o2++]) != 0) {
                return result;
            }
        }
        return count - string.count;
    }

    /**
     * Compares the specified string to this string using the Unicode values of
     * the characters, ignoring case differences. Returns 0 if the strings
     * contain the same characters in the same order. Returns a negative integer
     * if the first non-equal character in this string has a Unicode value which
     * is less than the Unicode value of the character at the same position in
     * the specified string, or if this string is a prefix of the specified
     * string. Returns a positive integer if the first non-equal character in
     * this string has a Unicode value which is greater than the Unicode value
     * of the character at the same position in the specified string, or if the
     * specified string is a prefix of this string.
     * 
     * @param string
     *            the string to compare.
     * @return 0 if the strings are equal, a negative integer if this string is
     *         before the specified string, or a positive integer if this string
     *         is after the specified string.
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public int compareToIgnoreCase(String string) {
        int o1 = offset, o2 = string.offset, result;
        int end = offset + (count < string.count ? count : string.count);
        char c1, c2;
        char[] target = string.value;
        while (o1 < end) {
            if ((c1 = value[o1++]) == (c2 = target[o2++])) {
                continue;
            }
            c1 = compareValue(c1);
            c2 = compareValue(c2);
            if ((result = c1 - c2) != 0) {
                return result;
            }
        }
        return count - string.count;
    }

    /**
     * Concatenates this string and the specified string.
     * 
     * @param string
     *            the string to concatenate
     * @return a new string which is the concatenation of this string and the
     *         specified string.
     */
    public String concat(String string) {
        if (string.count > 0 && count > 0) {
            char[] buffer = new char[count + string.count];
            System.arraycopy(value, offset, buffer, 0, count);
            System.arraycopy(string.value, string.offset, buffer, count,
                    string.count);
            return new String(0, buffer.length, buffer);
        }
        return count == 0 ? string : this;
    }

    /**
     * Creates a new string containing the characters in the specified character
     * array. Modifying the character array after creating the string has no
     * effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @return the new string.
     * @throws NullPointerException
     *             if {@code data} is {@code null}.
     */
    public static String copyValueOf(char[] data) {
        return new String(data, 0, data.length);
    }

    /**
     * Creates a new string containing the specified characters in the character
     * array. Modifying the character array after creating the string has no
     * effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @param start
     *            the starting offset in the character array.
     * @param length
     *            the number of characters to use.
     * @return the new string.
     * @throws NullPointerException
     *             if {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0, start < 0} or {@code start + length >
     *             data.length}.
     */
    public static String copyValueOf(char[] data, int start, int length) {
        return new String(data, start, length);
    }

    private Charset defaultCharset() {
        if (DefaultCharset == null) {
            String encoding = AccessController
                    .doPrivileged(new PriviAction<String>(
                            "file.encoding", "ISO8859_1")); //$NON-NLS-1$ //$NON-NLS-2$
            // calling System.getProperty() may cause DefaultCharset to be
            // initialized
            try {
                DefaultCharset = Charset.forName(encoding);
            } catch (IllegalCharsetNameException e) {
                // Ignored
            } catch (UnsupportedCharsetException e) {
                // Ignored
            }

            if (DefaultCharset == null) {
                DefaultCharset = Charset.forName("ISO-8859-1"); //$NON-NLS-1$
            }
        }
        return DefaultCharset;
    }

    /**
     * Compares the specified string to this string to determine if the
     * specified string is a suffix.
     * 
     * @param suffix
     *            the suffix to look for.
     * @return {@code true} if the specified string is a suffix of this string,
     *         {@code false} otherwise.
     * @throws NullPointerException
     *             if {@code suffix} is {@code null}.
     */
    public boolean endsWith(String suffix) {
        return regionMatches(count - suffix.count, suffix, 0, suffix.count);
    }

    /**
     * Compares the specified object to this string and returns true if they are
     * equal. The object must be an instance of string with the same characters
     * in the same order.
     * 
     * @param object
     *            the object to compare.
     * @return {@code true} if the specified object is equal to this string,
     *         {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof String) {
            String s = (String) object;
            int hash = hashCode;  // Single read on hashCodes as they may change
            int shash = s.hashCode;
            if (count != s.count || (hash != shash && hash != 0 && shash != 0)) {
                return false;
            }
            for (int i = 0; i < count; ++i) {
                if (value[offset + i] != s.value[s.offset + i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Compares the specified string to this string ignoring the case of the
     * characters and returns true if they are equal.
     * 
     * @param string
     *            the string to compare.
     * @return {@code true} if the specified string is equal to this string,
     *         {@code false} otherwise.
     */
    public boolean equalsIgnoreCase(String string) {
        if (string == this) {
            return true;
        }
        if (string == null || count != string.count) {
            return false;
        }

        int o1 = offset, o2 = string.offset;
        int end = offset + count;
        char c1, c2;
        char[] target = string.value;
        while (o1 < end) {
            if ((c1 = value[o1++]) != (c2 = target[o2++])
                    && toUpperCase(c1) != toUpperCase(c2)
                    // Required for unicode that we test both cases
                    && toLowerCase(c1) != toLowerCase(c2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Converts this string to a byte array using the default encoding as
     * specified by the file.encoding system property. If the system property is
     * not defined, the default encoding is ISO8859_1 (ISO-Latin-1). If 8859-1
     * is not available, an ASCII encoding is used.
     * 
     * @return the byte array encoding of this string.
     */
    public byte[] getBytes() {
        ByteBuffer buffer = defaultCharset().encode(
                CharBuffer.wrap(this.value, this.offset, this.count));
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }

    /**
     * Converts this string to a byte array, ignoring the high order bits of
     * each character.
     * 
     * @param start
     *            the starting offset of characters to copy.
     * @param end
     *            the ending offset of characters to copy.
     * @param data
     *            the destination byte array.
     * @param index
     *            the starting offset in the destination byte array.
     * @throws NullPointerException
     *             if {@code data} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0}, {@code end > length()}, {@code index <
     *             0} or {@code end - start > data.length - index}.
     * @deprecated Use {@link #getBytes()} or {@link #getBytes(String)}
     */
    @Deprecated
    public void getBytes(int start, int end, byte[] data, int index) {
        if (0 <= start && start <= end && end <= count) {
            end += offset;
            try {
                for (int i = offset + start; i < end; i++) {
                    data[index++] = (byte) value[i];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException();
            }
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /**
     * Converts this string to a byte array using the specified encoding.
     * 
     * @param encoding
     *            the encoding to use.
     * @return the encoded byte array of this string.
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported.
     */
    public byte[] getBytes(String encoding) throws UnsupportedEncodingException {
        ByteBuffer buffer = getCharset(encoding).encode(
                CharBuffer.wrap(this.value, this.offset, this.count));
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }

    private Charset getCharset(final String encoding)
            throws UnsupportedEncodingException {
        Charset charset = lastCharset;
        if (charset == null || !encoding.equalsIgnoreCase(charset.name())) {
            try {
                charset = Charset.forName(encoding);
            } catch (IllegalCharsetNameException e) {
                throw (UnsupportedEncodingException) (new UnsupportedEncodingException(
                        encoding).initCause(e));
            } catch (UnsupportedCharsetException e) {
                throw (UnsupportedEncodingException) (new UnsupportedEncodingException(
                        encoding).initCause(e));
            }
            lastCharset = charset;
        }
        return charset;
    }
    
    /**
     * Converts this String to a byte encoding using the specified encoding.
     * 
     * @param encoding
     *            the encoding
     * @return the byte array encoding of this String
     * 
     * @see String
     * @since 1.6
     */
    public byte[] getBytes(Charset encoding) {
        ByteBuffer buffer = encoding.encode(CharBuffer.wrap(this.value,
                this.offset, this.count));
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        return bytes;
    }

    /**
     * Copies the specified characters in this string to the character array
     * starting at the specified offset in the character array.
     * 
     * @param start
     *            the starting offset of characters to copy.
     * @param end
     *            the ending offset of characters to copy.
     * @param buffer
     *            the destination character array.
     * @param index
     *            the starting offset in the character array.
     * @throws NullPointerException
     *             if {@code buffer} is {@code null}.
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0}, {@code end > length()}, {@code start >
     *             end}, {@code index < 0}, {@code end - start > buffer.length -
     *             index}
     */
    public void getChars(int start, int end, char[] buffer, int index) {
        // NOTE last character not copied!
        // Fast range check.
        if (0 <= start && start <= end && end <= count) {
            System.arraycopy(value, start + offset, buffer, index, end - start);
        } else {
            throw new StringIndexOutOfBoundsException();
        }
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            if (count == 0) {
                return 0;
            }
            int hash = 0;
            for (int i = offset; i < count + offset; i++) {
                hash = value[i] + ((hash << 5) - hash);
            }
            hashCode = hash;
        }
        return hashCode;
    }

    /**
     * Searches in this string for the first index of the specified character.
     * The search for the character starts at the beginning and moves towards
     * the end of this string.
     * 
     * @param c
     *            the character to find.
     * @return the index in this string of the specified character, -1 if the
     *         character isn't found.
     */
    public int indexOf(int c) {
        return indexOf(c, 0);
    }

    /**
     * Searches in this string for the index of the specified character. The
     * search for the character starts at the specified offset and moves towards
     * the end of this string.
     * 
     * @param c
     *            the character to find.
     * @param start
     *            the starting offset.
     * @return the index in this string of the specified character, -1 if the
     *         character isn't found.
     */
    public int indexOf(int c, int start) {
        if (start < count) {
            if (start < 0) {
                start = 0;
            }
            if (c >= 0 && c <= Character.MAX_VALUE) {
                for (int i = offset + start; i < offset + count; i++) {
                    if (value[i] == c) {
                        return i - offset;
                    }
                }
            } else if (c > Character.MAX_VALUE && c <= Character.MAX_CODE_POINT) {
                for (int i = start; i < count; i++) {
                    int codePoint = codePointAt(i);
                    if (codePoint == c) {
                        return i;
                    } else if (codePoint >= Character.MIN_SUPPLEMENTARY_CODE_POINT) {
                        i++;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Searches in this string for the first index of the specified string. The
     * search for the string starts at the beginning and moves towards the end
     * of this string.
     * 
     * @param string
     *            the string to find.
     * @return the index of the first character of the specified string in this
     *         string, -1 if the specified string is not a substring.
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public int indexOf(String string) {
        return indexOf(string, 0);
    }

    /**
     * Searches in this string for the index of the specified string. The search
     * for the string starts at the specified offset and moves towards the end
     * of this string.
     * 
     * @param subString
     *            the string to find.
     * @param start
     *            the starting offset.
     * @return the index of the first character of the specified string in this
     *         string, -1 if the specified string is not a substring.
     * @throws NullPointerException
     *             if {@code subString} is {@code null}.
     */
    public int indexOf(String subString, int start) {
        if (start < 0) {
            start = 0;
        }
        int subCount = subString.count;
        if (subCount > 0) {
            if (subCount + start > count) {
                return -1;
            }
            char[] target = subString.value;
            int subOffset = subString.offset;
            char firstChar = target[subOffset];
            int end = subOffset + subCount;
            while (true) {
                int i = indexOf(firstChar, start);
                if (i == -1 || subCount + i > count) {
                    return -1; // handles subCount > count || start >= count
                }
                int o1 = offset + i, o2 = subOffset;
                while (++o2 < end && value[++o1] == target[o2]) {
                    // Intentionally empty
                }
                if (o2 == end) {
                    return i;
                }
                start = i + 1;
            }
        }
        return start < count ? start : count;
    }

    /**
     * Searches an internal table of strings for a string equal to this string.
     * If the string is not in the table, it is added. Returns the string
     * contained in the table which is equal to this string. The same string
     * object is always returned for strings which are equal.
     * 
     * @return the interned string equal to this string.
     */
    public String intern() {
        return VM.intern(this);
    }

    /**
     * Searches in this string for the last index of the specified character.
     * The search for the character starts at the end and moves towards the
     * beginning of this string.
     * 
     * @param c
     *            the character to find.
     * @return the index in this string of the specified character, -1 if the
     *         character isn't found.
     */
    public int lastIndexOf(int c) {
        return lastIndexOf(c, count - 1);
    }

    /**
     * Searches in this string for the index of the specified character. The
     * search for the character starts at the specified offset and moves towards
     * the beginning of this string.
     * 
     * @param c
     *            the character to find.
     * @param start
     *            the starting offset.
     * @return the index in this string of the specified character, -1 if the
     *         character isn't found.
     */
    public int lastIndexOf(int c, int start) {
        if (start >= 0) {
            if (start >= count) {
                start = count - 1;
            }
            if (c >= 0 && c <= Character.MAX_VALUE) {
                for (int i = offset + start; i >= offset; --i) {
                    if (value[i] == c) {
                        return i - offset;
                    }
                }
            } else if (c > Character.MAX_VALUE && c <= Character.MAX_CODE_POINT) {
                for (int i = start; i >= 0; --i) {
                    int codePoint = codePointAt(i);
                    if (codePoint == c) {
                        return i;
                    } else if (codePoint >= Character.MIN_SUPPLEMENTARY_CODE_POINT) {
                        --i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Searches in this string for the last index of the specified string. The
     * search for the string starts at the end and moves towards the beginning
     * of this string.
     * 
     * @param string
     *            the string to find.
     * @return the index of the first character of the specified string in this
     *         string, -1 if the specified string is not a substring.
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public int lastIndexOf(String string) {
        // Use count instead of count - 1 so lastIndexOf("") answers count
        return lastIndexOf(string, count);
    }

    /**
     * Searches in this string for the index of the specified string. The search
     * for the string starts at the specified offset and moves towards the
     * beginning of this string.
     * 
     * @param subString
     *            the string to find.
     * @param start
     *            the starting offset.
     * @return the index of the first character of the specified string in this
     *         string , -1 if the specified string is not a substring.
     * @throws NullPointerException
     *             if {@code subString} is {@code null}.
     */
    public int lastIndexOf(String subString, int start) {
        int subCount = subString.count;
        if (subCount <= count && start >= 0) {
            if (subCount > 0) {
                if (start > count - subCount) {
                    start = count - subCount;
                }
                // count and subCount are both >= 1
                char[] target = subString.value;
                int subOffset = subString.offset;
                char firstChar = target[subOffset];
                int end = subOffset + subCount;
                while (true) {
                    int i = lastIndexOf(firstChar, start);
                    if (i == -1) {
                        return -1;
                    }
                    int o1 = offset + i, o2 = subOffset;
                    while (++o2 < end && value[++o1] == target[o2]) {
                        // Intentionally empty
                    }
                    if (o2 == end) {
                        return i;
                    }
                    start = i - 1;
                }
            }
            return start < count ? start : count;
        }
        return -1;
    }

    /**
     * Returns the size of this string.
     * 
     * @return the number of characters in this string.
     */
    public int length() {
        return count;
    }
    
    /**
     * Answers if the size of this String is zero.
     * 
     * @return true if the size of this String is zero, false otherwise
     * @since 1.6
     */
    public boolean isEmpty() {
        return 0 == count;
    }

    /**
     * Compares the specified string to this string and compares the specified
     * range of characters to determine if they are the same.
     * 
     * @param thisStart
     *            the starting offset in this string.
     * @param string
     *            the string to compare.
     * @param start
     *            the starting offset in the specified string.
     * @param length
     *            the number of characters to compare.
     * @return {@code true} if the ranges of characters are equal, {@code false}
     *         otherwise
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public boolean regionMatches(int thisStart, String string, int start,
            int length) {
        if (string == null) {
            throw new NullPointerException();
        }
        if (start < 0 || string.count - start < length) {
            return false;
        }
        if (thisStart < 0 || count - thisStart < length) {
            return false;
        }
        if (length <= 0) {
            return true;
        }
        int o1 = offset + thisStart, o2 = string.offset + start;
        for (int i = 0; i < length; ++i) {
            if (value[o1 + i] != string.value[o2 + i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares the specified string to this string and compares the specified
     * range of characters to determine if they are the same. When ignoreCase is
     * true, the case of the characters is ignored during the comparison.
     * 
     * @param ignoreCase
     *            specifies if case should be ignored.
     * @param thisStart
     *            the starting offset in this string.
     * @param string
     *            the string to compare.
     * @param start
     *            the starting offset in the specified string.
     * @param length
     *            the number of characters to compare.
     * @return {@code true} if the ranges of characters are equal, {@code false}
     *         otherwise.
     * @throws NullPointerException
     *             if {@code string} is {@code null}.
     */
    public boolean regionMatches(boolean ignoreCase, int thisStart,
            String string, int start, int length) {
        if (!ignoreCase) {
            return regionMatches(thisStart, string, start, length);
        }

        if (string != null) {
            if (thisStart < 0 || length > count - thisStart) {
                return false;
            }
            if (start < 0 || length > string.count - start) {
                return false;
            }

            thisStart += offset;
            start += string.offset;
            int end = thisStart + length;
            char c1, c2;
            char[] target = string.value;
            while (thisStart < end) {
                if ((c1 = value[thisStart++]) != (c2 = target[start++])
                        && toUpperCase(c1) != toUpperCase(c2)
                        // Required for unicode that we test both cases
                        && toLowerCase(c1) != toLowerCase(c2)) {
                    return false;
                }
            }
            return true;
        }
        throw new NullPointerException();
    }

    /**
     * Copies this string replacing occurrences of the specified character with
     * another character.
     * 
     * @param oldChar
     *            the character to replace.
     * @param newChar
     *            the replacement character.
     * @return a new string with occurrences of oldChar replaced by newChar.
     */
    public String replace(char oldChar, char newChar) {
        int index = indexOf(oldChar, 0);
        if (index == -1) {
            return this;
        }

        char[] buffer = new char[count];
        System.arraycopy(value, offset, buffer, 0, count);
        do {
            buffer[index++] = newChar;
        } while ((index = indexOf(oldChar, index)) != -1);
        return new String(0, count, buffer);
    }
    
    /**
     * Copies this string replacing occurrences of the specified target sequence
     * with another sequence. The string is processed from the beginning to the
     * end.
     * 
     * @param target
     *            the sequence to replace.
     * @param replacement
     *            the replacement sequence.
     * @return the resulting string.
     * @throws NullPointerException
     *             if {@code target} or {@code replacement} is {@code null}.
     */
    public String replace(CharSequence target, CharSequence replacement) {
        if (target == null) {
            throw new NullPointerException("target should not be null");
        }
        if (replacement == null) {
            throw new NullPointerException("replacement should not be null");
        }
        String ts = target.toString();
        int index = indexOf(ts, 0);

        if (index == -1)
            return this;

        String rs = replacement.toString();

        // special case if the string to match is empty then
        // match at the start, inbetween each character and at the end
        if ("".equals(ts)) {
            StringBuilder buffer = new StringBuilder(count + (rs.length() * (count + 1)));
            buffer.append(rs);
            for(int i=0; i<count; i++) {
                buffer.append(value[offset + i]);
                buffer.append(rs);
            }
            return buffer.toString();
        }

        StringBuilder buffer = new StringBuilder(count + rs.length());
        int tl = target.length();
        int tail = 0;
        do {
            buffer.append(value, offset + tail, index - tail);
            buffer.append(rs);
            tail = index + tl;
        } while ((index = indexOf(ts, tail)) != -1);
        //append trailing chars 
        buffer.append(value, offset + tail, count - tail);

        return buffer.toString();
    }

    /**
     * Compares the specified string to this string to determine if the
     * specified string is a prefix.
     * 
     * @param prefix
     *            the string to look for.
     * @return {@code true} if the specified string is a prefix of this string,
     *         {@code false} otherwise
     * @throws NullPointerException
     *             if {@code prefix} is {@code null}.
     */
    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    /**
     * Compares the specified string to this string, starting at the specified
     * offset, to determine if the specified string is a prefix.
     * 
     * @param prefix
     *            the string to look for.
     * @param start
     *            the starting offset.
     * @return {@code true} if the specified string occurs in this string at the
     *         specified offset, {@code false} otherwise.
     * @throws NullPointerException
     *             if {@code prefix} is {@code null}.
     */
    public boolean startsWith(String prefix, int start) {
        return regionMatches(start, prefix, 0, prefix.count);
    }

    /**
     * Copies a range of characters into a new string.
     * 
     * @param start
     *            the offset of the first character.
     * @return a new string containing the characters from start to the end of
     *         the string.
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0} or {@code start > length()}.
     */
    public String substring(int start) {
        if (start == 0) {
            return this;
        }
        if (0 <= start && start <= count) {
            return new String(offset + start, count - start, value);
        }
        throw new StringIndexOutOfBoundsException(start);
    }

    /**
     * Copies a range of characters into a new string.
     * 
     * @param start
     *            the offset of the first character.
     * @param end
     *            the offset one past the last character.
     * @return a new string containing the characters from start to end - 1
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0}, {@code start > end} or {@code end >
     *             length()}.
     */
    public String substring(int start, int end) {
        if (start == 0 && end == count) {
            return this;
        }
        if (start < 0) {
            throw new StringIndexOutOfBoundsException(start);
        } else if (start > end) {
            throw new StringIndexOutOfBoundsException(end - start);
        } else if (end > count) {
            throw new StringIndexOutOfBoundsException(end);
        }
        // NOTE last character not copied!
        return new String(offset + start, end - start, value);
    }

    /**
     * Copies the characters in this string to a character array.
     * 
     * @return a character array containing the characters of this string.
     */
    public char[] toCharArray() {
        char[] buffer = new char[count];
        System.arraycopy(value, offset, buffer, 0, count);
        return buffer;
    }

    /**
     * Converts the characters in this string to lowercase, using the default
     * Locale.
     * 
     * @return a new string containing the lowercase characters equivalent to
     *         the characters in this string.
     */
    public String toLowerCase() {
        return toLowerCase(Locale.getDefault());
    }

    /**
     * Converts the characters in this string to lowercase, using the specified
     * Locale.
     * 
     * @param locale
     *            the Locale to use.
     * @return a new string containing the lowercase characters equivalent to
     *         the characters in this string.
     */
    public String toLowerCase(Locale locale) {
        for (int o = offset, end = offset + count; o < end; o++) {
            char ch = value[o];
            if (ch != toLowerCase(ch)) {
                char[] buffer = new char[count];
                int i = o - offset;
                // Not worth checking for i == 0 case
                System.arraycopy(value, offset, buffer, 0, i);
                // Turkish
                if (!"tr".equals(locale.getLanguage())) { //$NON-NLS-1$
                    while (i < count) {
                        buffer[i++] = toLowerCase(value[o++]);
                    }
                } else {
                    while (i < count) {
                        buffer[i++] = (ch = value[o++]) != 0x49 ? toLowerCase(ch)
                                : (char) 0x131;
                    }
                }
                return new String(0, count, buffer);
            }
        }
        return this;
    }

    /**
     * Returns this string.
     *
     * @return this string.
     */
    @Override
    public String toString() {
        return this;
    }

    /**
     * Converts the characters in this string to uppercase, using the default
     * Locale.
     * 
     * @return a new string containing the uppercase characters equivalent to
     *         the characters in this string.
     */
    public String toUpperCase() {
        return toUpperCase(Locale.getDefault());
    }

    private static final char[] upperValues = "SS\u0000\u02bcN\u0000J\u030c\u0000\u0399\u0308\u0301\u03a5\u0308\u0301\u0535\u0552\u0000H\u0331\u0000T\u0308\u0000W\u030a\u0000Y\u030a\u0000A\u02be\u0000\u03a5\u0313\u0000\u03a5\u0313\u0300\u03a5\u0313\u0301\u03a5\u0313\u0342\u1f08\u0399\u0000\u1f09\u0399\u0000\u1f0a\u0399\u0000\u1f0b\u0399\u0000\u1f0c\u0399\u0000\u1f0d\u0399\u0000\u1f0e\u0399\u0000\u1f0f\u0399\u0000\u1f08\u0399\u0000\u1f09\u0399\u0000\u1f0a\u0399\u0000\u1f0b\u0399\u0000\u1f0c\u0399\u0000\u1f0d\u0399\u0000\u1f0e\u0399\u0000\u1f0f\u0399\u0000\u1f28\u0399\u0000\u1f29\u0399\u0000\u1f2a\u0399\u0000\u1f2b\u0399\u0000\u1f2c\u0399\u0000\u1f2d\u0399\u0000\u1f2e\u0399\u0000\u1f2f\u0399\u0000\u1f28\u0399\u0000\u1f29\u0399\u0000\u1f2a\u0399\u0000\u1f2b\u0399\u0000\u1f2c\u0399\u0000\u1f2d\u0399\u0000\u1f2e\u0399\u0000\u1f2f\u0399\u0000\u1f68\u0399\u0000\u1f69\u0399\u0000\u1f6a\u0399\u0000\u1f6b\u0399\u0000\u1f6c\u0399\u0000\u1f6d\u0399\u0000\u1f6e\u0399\u0000\u1f6f\u0399\u0000\u1f68\u0399\u0000\u1f69\u0399\u0000\u1f6a\u0399\u0000\u1f6b\u0399\u0000\u1f6c\u0399\u0000\u1f6d\u0399\u0000\u1f6e\u0399\u0000\u1f6f\u0399\u0000\u1fba\u0399\u0000\u0391\u0399\u0000\u0386\u0399\u0000\u0391\u0342\u0000\u0391\u0342\u0399\u0391\u0399\u0000\u1fca\u0399\u0000\u0397\u0399\u0000\u0389\u0399\u0000\u0397\u0342\u0000\u0397\u0342\u0399\u0397\u0399\u0000\u0399\u0308\u0300\u0399\u0308\u0301\u0399\u0342\u0000\u0399\u0308\u0342\u03a5\u0308\u0300\u03a5\u0308\u0301\u03a1\u0313\u0000\u03a5\u0342\u0000\u03a5\u0308\u0342\u1ffa\u0399\u0000\u03a9\u0399\u0000\u038f\u0399\u0000\u03a9\u0342\u0000\u03a9\u0342\u0399\u03a9\u0399\u0000FF\u0000FI\u0000FL\u0000FFIFFLST\u0000ST\u0000\u0544\u0546\u0000\u0544\u0535\u0000\u0544\u053b\u0000\u054e\u0546\u0000\u0544\u053d\u0000".value; //$NON-NLS-1$

    /**
     * Return the index of the specified character into the upperValues table.
     * The upperValues table contains three entries at each position. These
     * three characters are the upper case conversion. If only two characters
     * are used, the third character in the table is \u0000.
     *
     * @param ch
     *            the char being converted to upper case
     *
     * @return the index into the upperValues table, or -1
     */
    private int upperIndex(int ch) {
        int index = -1;
        if (ch >= 0xdf) {
            if (ch <= 0x587) {
                if (ch == 0xdf) {
                    index = 0;
                } else if (ch <= 0x149) {
                    if (ch == 0x149) {
                        index = 1;
                    }
                } else if (ch <= 0x1f0) {
                    if (ch == 0x1f0) {
                        index = 2;
                    }
                } else if (ch <= 0x390) {
                    if (ch == 0x390) {
                        index = 3;
                    }
                } else if (ch <= 0x3b0) {
                    if (ch == 0x3b0) {
                        index = 4;
                    }
                } else if (ch <= 0x587) {
                    if (ch == 0x587) {
                        index = 5;
                    }
                }
            } else if (ch >= 0x1e96) {
                if (ch <= 0x1e9a) {
                    index = 6 + ch - 0x1e96;
                } else if (ch >= 0x1f50 && ch <= 0x1ffc) {
                    index = "\u000b\u0000\f\u0000\r\u0000\u000e\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>\u0000\u0000?@A\u0000BC\u0000\u0000\u0000\u0000D\u0000\u0000\u0000\u0000\u0000EFG\u0000HI\u0000\u0000\u0000\u0000J\u0000\u0000\u0000\u0000\u0000KL\u0000\u0000MN\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000OPQ\u0000RS\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000TUV\u0000WX\u0000\u0000\u0000\u0000Y".value[ch - 0x1f50]; //$NON-NLS-1$
                    if (index == 0) {
                        index = -1;
                    }
                } else if (ch >= 0xfb00) {
                    if (ch <= 0xfb06) {
                        index = 90 + ch - 0xfb00;
                    } else if (ch >= 0xfb13 && ch <= 0xfb17) {
                        index = 97 + ch - 0xfb13;
                    }
                }
            }
        }
        return index;
    }

    /**
     * Converts the characters in this string to uppercase, using the specified
     * Locale.
     * 
     * @param locale
     *            the Locale to use.
     * @return a new string containing the uppercase characters equivalent to
     *         the characters in this string.
     */
    public String toUpperCase(Locale locale) {
        boolean turkish = "tr".equals(locale.getLanguage()); //$NON-NLS-1$
        char[] output = null;
        int i = 0;
        for (int o = offset, end = offset + count; o < end; o++) {
            char ch = value[o];
            int index = upperIndex(ch);
            if (index == -1) {
                if (output != null && i >= output.length) {
                    char[] newoutput = new char[output.length + (count / 6) + 2];
                    System.arraycopy(output, 0, newoutput, 0, output.length);
                    output = newoutput;
                }
                char upch = !turkish ? toUpperCase(ch)
                        : (ch != 0x69 ? toUpperCase(ch)
                                : (char) 0x130);
                if (ch != upch) {
                    if (output == null) {
                        output = new char[count];
                        i = o - offset;
                        System.arraycopy(value, offset, output, 0, i);

                    }
                    output[i++] = upch;
                } else if (output != null) {
                    output[i++] = ch;
                }
            } else {
                int target = index * 3;
                char val3 = upperValues[target + 2];
                if (output == null) {
                    output = new char[count + (count / 6) + 2];
                    i = o - offset;
                    System.arraycopy(value, offset, output, 0, i);
                } else if (i + (val3 == 0 ? 1 : 2) >= output.length) {
                    char[] newoutput = new char[output.length + (count / 6) + 3];
                    System.arraycopy(output, 0, newoutput, 0, output.length);
                    output = newoutput;
                }

                char val = upperValues[target];
                output[i++] = val;
                val = upperValues[target + 1];
                output[i++] = val;
                if (val3 != 0) {
                    output[i++] = val3;
                }
            }
        }
        if (output == null) {
            return this;
        }
        return output.length == i || output.length - i < 8 ? new String(0, i,
                output) : new String(output, 0, i);
    }

    /**
     * Copies this string removing white space characters from the beginning and
     * end of the string.
     * 
     * @return a new string with characters <code><= \\u0020</code> removed from
     *         the beginning and the end.
     */
    public String trim() {
        int start = offset, last = offset + count - 1;
        int end = last;
        while ((start <= end) && (value[start] <= ' ')) {
            start++;
        }
        while ((end >= start) && (value[end] <= ' ')) {
            end--;
        }
        if (start == offset && end == last) {
            return this;
        }
        return new String(start, end - start + 1, value);
    }

    /**
     * Creates a new string containing the characters in the specified character
     * array. Modifying the character array after creating the string has no
     * effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @return the new string.
     * @throws NullPointerException
     *             if {@code data} is {@code null}.
     */
    public static String valueOf(char[] data) {
        return new String(data, 0, data.length);
    }

    /**
     * Creates a new string containing the specified characters in the character
     * array. Modifying the character array after creating the string has no
     * effect on the string.
     * 
     * @param data
     *            the array of characters.
     * @param start
     *            the starting offset in the character array.
     * @param length
     *            the number of characters to use.
     * @return the new string.
     * @throws IndexOutOfBoundsException
     *             if {@code length < 0}, {@code start < 0} or {@code start +
     *             length > data.length}
     * @throws NullPointerException
     *             if {@code data} is {@code null}.
     */
    public static String valueOf(char[] data, int start, int length) {
        return new String(data, start, length);
    }

    /**
     * Converts the specified character to its string representation.
     * 
     * @param value
     *            the character.
     * @return the character converted to a string.
     */
    public static String valueOf(char value) {
        String s;
        if (value < 128) {
            s = new String(value, 1, ascii);
        } else {
            s = new String(0, 1, new char[] { value });
        }
        s.hashCode = value;
        return s;
    }

    /**
     * Converts the specified double to its string representation.
     * 
     * @param value
     *            the double.
     * @return the double converted to a string.
     */
    public static String valueOf(double value) {
        return Double.toString(value);
    }

    /**
     * Converts the specified float to its string representation.
     * 
     * @param value
     *            the float.
     * @return the float converted to a string.
     */
    public static String valueOf(float value) {
        return Float.toString(value);
    }

    /**
     * Converts the specified integer to its string representation.
     * 
     * @param value
     *            the integer.
     * @return the integer converted to a string.
     */
    public static String valueOf(int value) {
        return Integer.toString(value);
    }

    /**
     * Converts the specified long to its string representation.
     * 
     * @param value
     *            the long.
     * @return the long converted to a string.
     */
    public static String valueOf(long value) {
        return Long.toString(value);
    }

    /**
     * Converts the specified object to its string representation. If the object
     * is null return the string {@code "null"}, otherwise use {@code
     * toString()} to get the string representation.
     * 
     * @param value
     *            the object.
     * @return the object converted to a string, or the string {@code "null"}.
     */
    public static String valueOf(Object value) {
        return value != null ? value.toString() : "null"; //$NON-NLS-1$
    }

    /**
     * Converts the specified boolean to its string representation. When the
     * boolean is {@code true} return {@code "true"}, otherwise return {@code
     * "false"}.
     * 
     * @param value
     *            the boolean.
     * @return the boolean converted to a string.
     */
    public static String valueOf(boolean value) {
        return value ? "true" : "false"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Returns whether the characters in the StringBuffer {@code strbuf} are the
     * same as those in this string.
     * 
     * @param strbuf
     *            the StringBuffer to compare this string to.
     * @return {@code true} if the characters in {@code strbuf} are identical to
     *         those in this string. If they are not, {@code false} will be
     *         returned.
     * @throws NullPointerException
     *             if {@code strbuf} is {@code null}.
     * @since 1.4
     */
    public boolean contentEquals(StringBuffer strbuf) {
        synchronized (strbuf) {
            int size = strbuf.length();
            if (count != size) {
                return false;
            }
            return regionMatches(0, new String(0, size, strbuf.getValue()), 0,
                    size);
        }
    }

    /**
     * Compares a {@code CharSequence} to this {@code String} to determine if
     * their contents are equal.
     *
     * @param cs
     *            the character sequence to compare to.
     * @return {@code true} if equal, otherwise {@code false}
     * @since 1.5
     */
    public boolean contentEquals(CharSequence cs) {
        if (cs == null) {
            throw new NullPointerException();
        }

        int len = cs.length();

        if (len != count) {
            return false;
        }

        if (len == 0 && count == 0) {
            return true; // since both are empty strings
        }

        return regionMatches(0, cs.toString(), 0, len);
    }

    /**
     * Determines whether this string matches a given regular expression.
     * 
     * @param expr
     *            the regular expression to be matched.
     * @return {@code true} if the expression matches, otherwise {@code false}.
     * @throws PatternSyntaxException
     *             if the syntax of the supplied regular expression is not
     *             valid.
     * @throws NullPointerException
     *             if {@code expr} is {@code null}.
     * @since 1.4
     */
    public boolean matches(String expr) {
        return Pattern.matches(expr, this);
    }

    /**
     * Replace any substrings within this string that match the supplied regular
     * expression {@code expr}, with the string {@code substitute}.
     * 
     * @param expr
     *            the regular expression to match.
     * @param substitute
     *            the string to replace the matching substring with.
     * @return the new string.
     * @throws PatternSyntaxException
     *             if the syntax of the supplied regular expression is not
     *             valid.
     * @see Pattern
     * @since 1.4
     */
    public String replaceAll(String expr, String substitute) {
        return Pattern.compile(expr).matcher(this).replaceAll(substitute);
    }

    /**
     * Replace the first substring within this string that matches the supplied
     * regular expression {@code expr}, with the string {@code substitute}.
     * 
     * @param expr
     *            the regular expression to match.
     * @param substitute
     *            the string to replace the matching substring with.
     * @return the new string.
     * @throws PatternSyntaxException
     *             if the syntax of the supplied regular expression is not
     *             valid.
     * @throws NullPointerException
     *             if {@code strbuf} is {@code null}.
     * @see Pattern
     * @since 1.4
     */
    public String replaceFirst(String expr, String substitute) {
        return Pattern.compile(expr).matcher(this).replaceFirst(substitute);
    }

    /**
     * Splits this string using the supplied regular expression {@code expr}.
     * 
     * @param expr
     *            the regular expression used to divide the string.
     * @return an array of Strings created by separating the string along
     *         matches of the regular expression.
     * @throws NullPointerException
     *             if {@code expr} is {@code null}.
     * @throws PatternSyntaxException
     *             if the syntax of the supplied regular expression is not
     *             valid.
     * @see Pattern
     * @since 1.4
     */
    public String[] split(String expr) {
        return Pattern.compile(expr).split(this);
    }

    /**
     * Splits this string using the supplied regular expression {@code expr}.
     * The parameter {@code max} controls the behavior how many times the
     * pattern is applied to the string.
     * 
     * @param expr
     *            the regular expression used to divide the string.
     * @param max
     *            the number of entries in the resulting array.
     * @return an array of Strings created by separating the string along
     *         matches of the regular expression.
     * @throws NullPointerException
     *             if {@code expr} is {@code null}.
     * @throws PatternSyntaxException
     *             if the syntax of the supplied regular expression is not
     *             valid.
     * @see Pattern#split(CharSequence, int)
     * @since 1.4
     */
    public String[] split(String expr, int max) {
        return Pattern.compile(expr).split(this, max);
    }

    /**
     * Has the same result as the substring function, but is present so that
     * string may implement the CharSequence interface.
     * 
     * @param start
     *            the offset the first character.
     * @param end
     *            the offset of one past the last character to include.
     * @return the subsequence requested.
     * @throws IndexOutOfBoundsException
     *             if {@code start < 0}, {@code end < 0}, {@code start > end} or
     *             {@code end > length()}.
     * @see java.lang.CharSequence#subSequence(int, int)
     * @since 1.4
     */
    public CharSequence subSequence(int start, int end) {
        return substring(start, end);
    }

    /**
     * Retrieves the Unicode code point (character) value at the specified
     * {@code index}.
     * 
     * @param index
     *            the index to the {@code char} code unit within this string.
     * @return the Unicode code point value.
     * @throws IndexOutOfBoundsException
     *             if {@code index} is negative or greater than or equal to
     *             {@code length()}.
     * @see Character#codePointAt(char[], int, int)
     * @since 1.5
     */
    public int codePointAt(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        int s = index + offset;
        return Character.codePointAt(value, s, offset + count);
    }

    /**
     * Retrieves the Unicode code point value that precedes the specified
     * {@code index}.
     * 
     * @param index
     *            the index to the {@code char} code unit within this string.
     * @return the Unicode code point value.
     * @throws IndexOutOfBoundsException
     *             if {@code index} is less than 1 or greater than
     *             {@code length()}.
     * @see Character#codePointBefore(char[], int, int)
     * @since 1.5
     */
    public int codePointBefore(int index) {
        if (index < 1 || index > count) {
            throw new IndexOutOfBoundsException();
        }
        int s = index + offset;
        return Character.codePointBefore(value, s);
    }

    /**
     * Calculates the number of Unicode code points between {@code beginIndex}
     * and {@code endIndex}.
     * 
     * @param beginIndex
     *            the inclusive beginning index of the subsequence.
     * @param endIndex
     *            the exclusive end index of the subsequence.
     * @return the number of Unicode code points in the subsequence.
     * @throws IndexOutOfBoundsException
     *             if {@code beginIndex} is negative or greater than {@code
     *             endIndex} or {@code endIndex} is greater than {@code
     *             length()}.
     * @see Character#codePointCount(CharSequence, int, int)
     * @since 1.5
     */
    public int codePointCount(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > count || beginIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }
        int s = beginIndex + offset;
        return Character.codePointCount(value, s, endIndex - beginIndex);
    }

    /**
     * Determines if this {@code String} contains the sequence of characters in
     * the {@code CharSequence} passed.
     *
     * @param cs
     *            the character sequence to search for.
     * @return {@code true} if the sequence of characters are contained in this
     *         string, otherwise {@code false}.
     * @since 1.5
     */
    public boolean contains(CharSequence cs) {
        if (cs == null) {
            throw new NullPointerException();
        }
        return indexOf(cs.toString()) >= 0;
    }

    /**
     * Returns the index within this object that is offset from {@code index} by
     * {@code codePointOffset} code points.
     *
     * @param index
     *            the index within this object to calculate the offset from.
     * @param codePointOffset
     *            the number of code points to count.
     * @return the index within this object that is the offset.
     * @throws IndexOutOfBoundsException
     *             if {@code index} is negative or greater than {@code length()}
     *             or if there aren't enough code points before or after {@code
     *             index} to match {@code codePointOffset}.
     * @since 1.5
     */
    public int offsetByCodePoints(int index, int codePointOffset) {
        int s = index + offset;
        int r = Character.offsetByCodePoints(value, offset, count, s,
                codePointOffset);
        return r - offset;
    }

    /**
     * Returns a formatted string, using the supplied format and arguments,
     * using the default locale.
     * 
     * @param format
     *            a format string.
     * @param args
     *            arguments to replace format specifiers (may be none).
     * @return the formatted string.
     * @throws NullPointerException
     *             if {@code format} is {@code null}.
     * @throws java.util.IllegalFormatException
     *             if the format is invalid.
     * @see java.util.Formatter
     * @since 1.5
     */
    public static String format(String format, Object... args) {
        return format(Locale.getDefault(), format, args);
    }

    /**
     * Returns a formatted string, using the supplied format and arguments,
     * accordingly to the specified locale.
     * <p>
     * Note that this is a convenience method. Using it involves creating an
     * internal {@link java.util.Formatter} instance on-the-fly, which is
     * somewhat costly in terms of memory and time. This is probably acceptable
     * if you use the method only rarely, but if you rely on it for formatting a
     * large number of strings, consider creating and reusing your own
     * {@link java.util.Formatter} instance instead.
     *
     * @param loc
     *            the locale to apply; {@code null} value means no localization.
     * @param format
     *            a format string.
     * @param args
     *            arguments to replace format specifiers (may be none).
     * @return the formatted string.
     * @throws NullPointerException
     *             if {@code format} is {@code null}.
     * @throws java.util.IllegalFormatException
     *             if the format is invalid.
     * @see java.util.Formatter
     * @since 1.5
     */
    public static String format(Locale loc, String format, Object... args) {
        if (format == null) {
            throw new NullPointerException("null format argument");
        }
        int bufferSize = format.length()
                + (args == null ? 0 : args.length * 10);
        Formatter f = new Formatter(new StringBuilder(bufferSize), loc);
        return f.format(format, args).toString();
    }

    /*
     * An implementation of a String.indexOf that is supposed to perform
     * substantially better than the default algorithm if the "needle" (the
     * subString being searched for) is a constant string.
     *
     * For example, a JIT, upon encoutering a call to String.indexOf(String),
     * where the needle is a constant string, may compute the values cache, md2
     * and lastChar, and change the call to the following method.
     */
    @SuppressWarnings("unused")
    private static int indexOf(String haystackString, String needleString,
            int cache, int md2, char lastChar) {
        char[] haystack = haystackString.value;
        int haystackOffset = haystackString.offset;
        int haystackLength = haystackString.count;
        char[] needle = needleString.value;
        int needleOffset = needleString.offset;
        int needleLength = needleString.count;
        int needleLengthMinus1 = needleLength - 1;
        int haystackEnd = haystackOffset + haystackLength;
        outer_loop: for (int i = haystackOffset + needleLengthMinus1; i < haystackEnd;) {
            if (lastChar == haystack[i]) {
                for (int j = 0; j < needleLengthMinus1; ++j) {
                    if (needle[j + needleOffset] != haystack[i + j
                            - needleLengthMinus1]) {
                        int skip = 1;
                        if ((cache & (1 << haystack[i])) == 0) {
                            skip += j;
                        }
                        i += Math.max(md2, skip);
                        continue outer_loop;
                    }
                }
                return i - needleLengthMinus1 - haystackOffset;
            }

            if ((cache & (1 << haystack[i])) == 0) {
                i += needleLengthMinus1;
            }
            i++;
        }
        return -1;
    }

    /*
     * Returns the character array for this string.
     */
    char[] getValue() {
        return value;
    }
}
