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

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * Contains marshalers for {@link Buffer} subclasses.
 */
public class BufferMarshalers {

    /**
     * Marshals to/from {@link ByteBuffer}, {@link ShortBuffer}, 
     * {@link CharBuffer}, {@link IntBuffer}, {@link LongBuffer}, 
     * {@link FloatBuffer} and {@link DoubleBuffer}.
     */
    public static class BufferMarshaler {
        private static final int EFFECTIVE_DIRECT_ADDRESS_OFFSET;
        private static final int _ELEMENT_SIZE_SHIFT_OFFSET;

        static {
            try {
                Field f1 = Buffer.class.getDeclaredField("effectiveDirectAddress");
                if (f1.getType() != int.class) {
                    // Make sure we don't mess up here when we start using a 64-bit capable Android class lib.
                    throw new Error("java.nio.Buffer.effectiveDirectAddress should be an int");
                }
                EFFECTIVE_DIRECT_ADDRESS_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f1));
                Field f2 = Buffer.class.getDeclaredField("_elementSizeShift");
                _ELEMENT_SIZE_SHIFT_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f2));
            } catch (NoSuchFieldException e) {
                throw new Error(e);
            }
        }
        
        public static Object toObject(Class<?> cls, long handle, long flags) {
            throw new UnsupportedOperationException("Marshaling " 
                    + cls.getName() + " to pointer  is not supported");
        }
        
        public static @Pointer long toNative(Object object, long flags) {
            long callType = flags & MarshalerFlags.CALL_TYPE_MASK;
            if (callType != MarshalerFlags.CALL_TYPE_BRIDGE) {
                // Struct member setter values and @Callback return values can not be marshaled
                // since we don't know how to allocate the native memory for it.
                if (callType == MarshalerFlags.CALL_TYPE_CALLBACK) {
                    throw new UnsupportedOperationException("Marshaling java.nio.Buffer to pointer " 
                            + "for callback return values is not supported");
                }
                if (callType == MarshalerFlags.CALL_TYPE_STRUCT_MEMBER) {
                    throw new UnsupportedOperationException("Marshaling java.nio.Buffer to pointer " 
                            + "for struct member setter values is not supported");
                }
                throw new UnsupportedOperationException();
            }

            // Must be a @Bridge method argument.

            if (object == null) {
                return 0L;
            }

            Buffer buffer = (Buffer) object;
            if (!buffer.isDirect() && !buffer.hasArray()) {
                // Non-direct buffers must be backed by an array to be supported.
                // We could have made a copy of the buffer contents and returned
                // a pointer to that but then changes made to the contents by
                // native code wouldn't be visible in the original buffer and
                // the semantics would be different depending on the type of
                // the buffer.
                throw new IllegalArgumentException("Only direct and array-backed " 
                        + "java.nio.Buffers can be marshaled to pointers.");
            }

            if (buffer.isDirect()) {
                return VM.getInt(VM.getObjectAddress(buffer) + EFFECTIVE_DIRECT_ADDRESS_OFFSET);
            } else {
                Object array = buffer.array();
                int offset = buffer.arrayOffset();
                int shift = VM.getInt(VM.getObjectAddress(buffer) + _ELEMENT_SIZE_SHIFT_OFFSET);
                return VM.getArrayValuesAddress(array) + (offset << shift);
            }
        }
        
        public static Object toObject(Class<?> cls, long handle, long flags, int d1) {
            if (cls == ByteBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1);
            }
            ByteOrder byteOrder = ByteOrder.nativeOrder();
            if (cls == ShortBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 1).order(byteOrder).asShortBuffer();
            }
            if (cls == CharBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 1).order(byteOrder).asCharBuffer();
            }
            if (cls == IntBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 2).order(byteOrder).asIntBuffer();
            }
            if (cls == LongBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 3).order(byteOrder).asLongBuffer();
            }
            if (cls == FloatBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 2).order(byteOrder).asFloatBuffer();
            }
            if (cls == DoubleBuffer.class) {
                return VM.newDirectByteBuffer(handle, d1 << 3).order(byteOrder).asDoubleBuffer();
            }
            throw new IllegalArgumentException("Unsupported class: " + cls.getName());
        }
        public static void toNative(Object object, long handle, long flags, int d1) {
            Buffer buffer = (Buffer) object;
            
            if (d1 != buffer.capacity()) {
                Class<?> cls = null;
                if (buffer instanceof ByteBuffer) {
                    cls = ByteBuffer.class;
                } else if (buffer instanceof ShortBuffer) {
                    cls = ShortBuffer.class;
                } else if (buffer instanceof CharBuffer) {
                    cls = CharBuffer.class;
                } else if (buffer instanceof IntBuffer) {
                    cls = IntBuffer.class;
                } else if (buffer instanceof LongBuffer) {
                    cls = LongBuffer.class;
                } else if (buffer instanceof FloatBuffer) {
                    cls = FloatBuffer.class;
                } else if (buffer instanceof DoubleBuffer) {
                    cls = DoubleBuffer.class;
                }
                throw new IllegalArgumentException("Expected " + 
                        cls.getName() + " of capacity " + d1 
                        + ". Got " + cls.getName() + " of capacity " 
                        + buffer.capacity());
            }

            long src = 0L;
            Object array = null;
            int offset = 0;
            int shift = VM.getInt(VM.getObjectAddress(buffer) + _ELEMENT_SIZE_SHIFT_OFFSET);
            if (buffer.isDirect()) {
                src = VM.getInt(VM.getObjectAddress(buffer) + EFFECTIVE_DIRECT_ADDRESS_OFFSET);
            } else {
                if (buffer.hasArray()) {
                    array = buffer.array();
                    offset = buffer.arrayOffset();
                } else {
                    int pos = buffer.position();
                    int limit = buffer.limit();
                    buffer.position(0);
                    buffer.limit(buffer.capacity());
                    if (buffer instanceof ByteBuffer) {
                        array = new byte[d1];
                        ((ByteBuffer) buffer).get((byte[]) array);
                    } else if (buffer instanceof ShortBuffer) {
                        array = new short[d1];
                        ((ShortBuffer) buffer).get((short[]) array);
                    } else if (buffer instanceof CharBuffer) {
                        array = new char[d1];
                        ((CharBuffer) buffer).get((char[]) array);
                    } else if (buffer instanceof IntBuffer) {
                        array = new int[d1];
                        ((IntBuffer) buffer).get((int[]) array);
                    } else if (buffer instanceof LongBuffer) {
                        array = new long[d1];
                        ((LongBuffer) buffer).get((long[]) array);
                    } else if (buffer instanceof FloatBuffer) {
                        array = new float[d1];
                        ((FloatBuffer) buffer).get((float[]) array);
                    } else if (buffer instanceof DoubleBuffer) {
                        array = new double[d1];
                        ((DoubleBuffer) buffer).get((double[]) array);
                    }
                    buffer.position(pos);
                    buffer.limit(limit);
                }
                
                src = VM.getArrayValuesAddress(array);
            }
            
            VM.memcpy(handle, src + (offset << shift), d1 << shift);
        }
        public static Object toObject(Class<?> cls, long handle, long flags, int d1, int d2) {
            return toObject(cls, handle, flags, d1 * d2);
        }
        public static void toNative(Object object, long handle, long flags, int d1, int d2) {
            toNative(object, handle, flags, d1 * d2);
        }
        public static Object toObject(Class<?> cls, long handle, long flags, int d1, int d2, int d3) {
            return toObject(cls, handle, flags, d1 * d2 * d3);
        }
        public static void toNative(Object object, long handle, long flags, int d1, int d2, int d3) {
            toNative(object, handle, flags, d1 * d2 * d3);
        }
    }

}
