/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.rt.bro;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.BaseType;

/**
 * Contains marshalers for {@link String} values.
 */
public class StringMarshalers {

    public static class EightBitZeroTerminatedStringMarshaler {
        private static final String EMPTY_STRING = "";
        private final Charset charset;
        
        public EightBitZeroTerminatedStringMarshaler(String charsetName) {
            charset = Charset.forName(charsetName);
        }
        
        public final Object toObject(Class<?> cls, long handle, long flags, int d1) {
            int length = 0;
            while (length < d1 && VM.getByte(handle + length) != 0) {
                length++;
            }
            if (length == 0) {
                return EMPTY_STRING;
            }
            return charset.decode(VM.newDirectByteBuffer(handle, length)).toString();
        }
        
        public final void toNative(Object object, long handle, long flags, int d1) {
            String s = (String) object;
            int length = Math.min(d1, s.length());
            ByteBuffer bb = VM.newDirectByteBuffer(handle, d1);
            bb.put(s.substring(0, length).getBytes(charset));
            if (bb.hasRemaining()) {
                bb.put((byte) 0);
            }
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * default character encoding (assuming it's an 8-bit encoding).
     */
    public static class AsDefaultCharsetZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER = 
                new EightBitZeroTerminatedStringMarshaler(Charset.defaultCharset().name());
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        public static @BaseType(byte.class) void toNative(Object object, long handle, long flags, int d1) {
            MARSHALER.toNative(object, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code ASCII} character encoding.
     */
    public static class AsAsciiZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("ascii");
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        public static @BaseType(byte.class) void toNative(Object object, long handle, long flags, int d1) {
            MARSHALER.toNative(object, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code UTF-8} character encoding.
     */
    public static class AsUtf8ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("utf-8");
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        public static @BaseType(byte.class) void toNative(Object object, long handle, long flags, int d1) {
            MARSHALER.toNative(object, handle, flags, d1);
        }
    }
    
    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code iso8859-1} (aka {@code latin1}) character encoding.
     */
    public static class AsLatin1ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("8859-1");
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        public static @BaseType(byte.class) void toNative(Object object, long handle, long flags, int d1) {
            MARSHALER.toNative(object, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code windows-1252} character encoding.
     */
    public static class AsWindow1252ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("windows-1252");
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        public static @BaseType(byte.class) void toNative(Object object, long handle, long flags, int d1) {
            MARSHALER.toNative(object, handle, flags, d1);
        }
    }
}
