/*
 * Copyright (C) 2012 RoboVM
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
@Marshaler(ValuedEnum.AsIntMarshaler.class)
public interface ValuedEnum {

    /**
     * Returns the integer value of this {@link Enum} item.
     * 
     * @return the value.
     */
    int value();
    
    public static class AsIntMarshaler {
        
        public static Enum<?> toObject(Enum<?>[] values, int value) {
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
                    + value + " in " + enumType.getName());
        }
        
        public static int toNative(Enum<?> v) {
            return ((ValuedEnum) v).value();
        }
    }
}
