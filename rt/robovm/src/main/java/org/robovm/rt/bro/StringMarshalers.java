/*
 * Copyright (C) 2013 RoboVM AB
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
import org.robovm.rt.bro.annotation.MarshalsArray;
import org.robovm.rt.bro.annotation.MarshalsPointer;

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
        
        public final String toObject(Class<?> cls, long handle, long flags) {
            if (handle == 0L) {
                return null;
            }
            int length = 0;
            while (VM.getByte(handle + length) != 0) {
                length++;
            }
            if (length == 0) {
                return EMPTY_STRING;
            }
            return charset.decode(VM.newDirectByteBuffer(handle, length)).toString();
        }
        
        public final long toNative(String s, long flags) {
            long callType = flags & MarshalerFlags.CALL_TYPE_MASK;
            if (callType != MarshalerFlags.CALL_TYPE_BRIDGE) {
                // Struct member setter values and @Callback return values can not be marshaled
                // since we don't know how to allocate the native memory for it.
                if (callType == MarshalerFlags.CALL_TYPE_CALLBACK) {
                    throw new UnsupportedOperationException("Marshaling String to pointer " 
                            + "for callback return values is not supported");
                }
                if (callType == MarshalerFlags.CALL_TYPE_STRUCT_MEMBER) {
                    throw new UnsupportedOperationException("Marshaling String to pointer " 
                            + "for struct member setter values is not supported");
                }
                throw new UnsupportedOperationException();
            }

            // Must be a @Bridge method argument. Allocate native string on the heap.

            if (s == null) {
                return 0L;
            }

            byte[] bytes = s.getBytes(charset);
            long handle = VM.allocateMemoryAtomic(bytes.length + 1);
            VM.memcpy(handle, VM.getArrayValuesAddress(bytes), bytes.length);
            return handle;
        }
        
        public final String toObject(Class<?> cls, long handle, long flags, int d1) {
            int length = 0;
            while (length < d1 && VM.getByte(handle + length) != 0) {
                length++;
            }
            if (length == 0) {
                return EMPTY_STRING;
            }
            return charset.decode(VM.newDirectByteBuffer(handle, length)).toString();
        }
        
        public final void toNative(String s, long handle, long flags, int d1) {
            byte[] bytes = s.getBytes(charset);
            int length = Math.min(d1, bytes.length);
            ByteBuffer bb = VM.newDirectByteBuffer(handle, d1);
            bb.put(bytes, 0, length);
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
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            return MARSHALER.toObject(cls, handle, flags);
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            return MARSHALER.toNative(s, flags);
        }
        @MarshalsArray(baseType = byte.class)
        public static String toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        @MarshalsArray(baseType = byte.class)
        public static void toNative(String s, long handle, long flags, int d1) {
            MARSHALER.toNative(s, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code ASCII} character encoding.
     */
    public static class AsAsciiZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("ascii");
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            return MARSHALER.toObject(cls, handle, flags);
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            return MARSHALER.toNative(s, flags);
        }
        @MarshalsArray(baseType = byte.class)
        public static String toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        @MarshalsArray(baseType = byte.class)
        public static void toNative(String s, long handle, long flags, int d1) {
            MARSHALER.toNative(s, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code UTF-8} character encoding.
     */
    public static class AsUtf8ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("utf-8");
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            return MARSHALER.toObject(cls, handle, flags);
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            return MARSHALER.toNative(s, flags);
        }
        @MarshalsArray(baseType = byte.class)
        public static String toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        @MarshalsArray(baseType = byte.class)
        public static void toNative(String s, long handle, long flags, int d1) {
            MARSHALER.toNative(s, handle, flags, d1);
        }
    }
    
    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code iso8859-1} (aka {@code latin1}) character encoding.
     */
    public static class AsLatin1ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("8859-1");
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            return MARSHALER.toObject(cls, handle, flags);
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            return MARSHALER.toNative(s, flags);
        }
        @MarshalsArray(baseType = byte.class)
        public static String toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        @MarshalsArray(baseType = byte.class)
        public static void toNative(String s, long handle, long flags, int d1) {
            MARSHALER.toNative(s, handle, flags, d1);
        }
    }

    /**
     * Marshals {@link String}s to/from zero-terminated bytes using the 
     * {@code windows-1252} character encoding.
     */
    public static class AsWindow1252ZMarshaler {
        private static final EightBitZeroTerminatedStringMarshaler MARSHALER 
            = new EightBitZeroTerminatedStringMarshaler("windows-1252");
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            return MARSHALER.toObject(cls, handle, flags);
        }
        @MarshalsPointer
        public static long toNative(String s, long flags) {
            return MARSHALER.toNative(s, flags);
        }
        @MarshalsArray(baseType = byte.class)
        public static String toObject(Class<?> cls, long handle, long flags, int d1) {
            return MARSHALER.toObject(cls, handle, flags, d1);
        }
        @MarshalsArray(baseType = byte.class)
        public static void toNative(String s, long handle, long flags, int d1) {
            MARSHALER.toNative(s, handle, flags, d1);
        }
    }
}
