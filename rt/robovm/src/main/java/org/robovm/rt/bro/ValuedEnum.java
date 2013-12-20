/*
 * Copyright (C) 2012 Trillian AB
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

import org.robovm.rt.bro.annotation.Marshaler;

/**
 * Implemented by {@link Enum} classes which have an explicit integer value.
 */
@Marshaler(ValuedEnum.AsSignedIntMarshaler.class)
public interface ValuedEnum {

    /**
     * Returns the integer value of this {@link Enum} item.
     * 
     * @return the value.
     */
    long value();
    
    /**
     * Marshals a {@link ValuedEnum} as an 8-bit signed value.
     */
    public static class AsSignedByteMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, byte value, long flags) {
            return AsLongMarshaler.toObject(values, value, flags);
        }
        public static byte toNative(Enum<?> v, long flags) {
            return (byte) ((ValuedEnum) v).value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as an 8-bit unsigned value.
     */
    public static class AsUnsignedByteMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, byte value, long flags) {
            return AsLongMarshaler.toObject(values, value & 0xffL, flags);
        }
        public static byte toNative(Enum<?> v, long flags) {
            return (byte) ((ValuedEnum) v).value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 16-bit signed value.
     */
    public static class AsSignedShortMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, short value, long flags) {
            return AsLongMarshaler.toObject(values, value, flags);
        }
        public static short toNative(Enum<?> v, long flags) {
            return (short) ((ValuedEnum) v).value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 16-bit unsigned value.
     */
    public static class AsUnsignedShortMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, short value, long flags) {
            return AsLongMarshaler.toObject(values, value & 0xffffL, flags);
        }
        public static short toNative(Enum<?> v, long flags) {
            return (short) ((ValuedEnum) v).value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 32-bit signed value. This is the
     * default marshaler for {@link ValuedEnum}s.
     */
    public static class AsSignedIntMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, int value, long flags) {
            return AsLongMarshaler.toObject(values, value, flags);
        }
        public static int toNative(Enum<?> v, long flags) {
            return (int) ((ValuedEnum) v).value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 32-bit unsigned value.
     */
    public static class AsUnsignedIntMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, int value, long flags) {
            return AsLongMarshaler.toObject(values, value & 0xffffffffL, flags);
        }
        public static int toNative(Enum<?> v, long flags) {
            return (int) ((ValuedEnum) v).value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 64-bit value.
     */
    public static class AsLongMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, long value, long flags) {
            int length = values.length;
            if (length == 0) {
                throw new AssertionError("Enum class has no values!");
            }
            for (int i = 0; i < length; i++) {
                Enum<?> e = values[i];
                ValuedEnum v = (ValuedEnum) e;
                if (v.value() == value) {
                    return e;
                }
            }
            Class<?> enumType = values[0].getClass();
            throw new IllegalArgumentException("No constant with value " 
                    + value + " (0x" + Long.toHexString(value) + ") found in " 
                    + enumType.getName());
        }
        
        public static long toNative(Enum<?> v, long flags) {
            return ((ValuedEnum) v).value();
        }
    }
}
