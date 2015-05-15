/*
 * Copyright (C) 2012 RoboVM AB
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

import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsValue;

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
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, byte value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        @MarshalsValue
        public static byte toNative(Enum<?> v, long flags) {
            return (byte) ((ValuedEnum) v).value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as an 8-bit unsigned value.
     */
    public static class AsUnsignedByteMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, byte value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffL, flags);
        }
        @MarshalsValue
        public static byte toNative(ValuedEnum v, long flags) {
            return (byte) v.value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 16-bit signed value.
     */
    public static class AsSignedShortMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, short value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        @MarshalsValue
        public static short toNative(ValuedEnum v, long flags) {
            return (short) v.value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 16-bit unsigned value.
     */
    public static class AsUnsignedShortMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, short value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffffL, flags);
        }
        @MarshalsValue
        public static short toNative(ValuedEnum v, long flags) {
            return (short) v.value();
        }
    }

    /**
     * Marshals a {@link ValuedEnum} as a 32-bit signed value. This is the
     * default marshaler for {@link ValuedEnum}s.
     */
    public static class AsSignedIntMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, int value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        @MarshalsValue
        public static int toNative(ValuedEnum v, long flags) {
            return (int) v.value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 32-bit unsigned value.
     */
    public static class AsUnsignedIntMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, int value, long flags) {
            return AsLongMarshaler.toObject(cls, value & 0xffffffffL, flags);
        }
        @MarshalsValue
        public static int toNative(ValuedEnum v, long flags) {
            return (int) v.value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 64-bit value.
     */
    public static class AsLongMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, long value, long flags) {
            T[] values = Enum.getSharedConstants(cls);
            int length = values.length;
            if (length == 0) {
                throw new AssertionError("Enum class has no values!");
            }
            for (int i = 0; i < length; i++) {
                Enum<?> e = values[i];
                ValuedEnum v = (ValuedEnum) e;
                if (v.value() == value) {
                    return v;
                }
            }
            Class<?> enumType = values[0].getClass();
            throw new IllegalArgumentException("No constant with value " 
                    + value + " (0x" + Long.toHexString(value) + ") found in " 
                    + enumType.getName());
        }
        
        public static long toNative(ValuedEnum v, long flags) {
            return v.value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 32-bit or 64-bit signed value 
     * depending on the machine word size.
     */
    public static class AsMachineSizedSIntMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, @MachineSizedSInt long value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        
        public static @MachineSizedSInt long toNative(ValuedEnum v, long flags) {
            return v.value();
        }
    }
    
    /**
     * Marshals a {@link ValuedEnum} as a 32-bit or 64-bit unsigned value 
     * depending on the machine word size.
     */
    public static class AsMachineSizedUIntMarshaler {
        @MarshalsValue
        public static <T extends Enum<T> & ValuedEnum> ValuedEnum toObject(Class<T> cls, @MachineSizedUInt long value, long flags) {
            return AsLongMarshaler.toObject(cls, value, flags);
        }
        
        public static @MachineSizedUInt long toNative(ValuedEnum v, long flags) {
            return v.value();
        }
    }
}
