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
 * Contains {@link Marshaler}s for {@link Enum}s using {@link Enum#ordinal()} to marshal
 * {@link Enum}s to/from the primitive types.
 */
public class EnumMarshalers {

    /**
     * Default {@link Marshaler} for {@link Enum}s used by the compiler if no {@link Marshaler} can be found.
     */
    public static class AsIntMarshaler {
        public static Enum<?> toObject(Enum<?>[] values, int ordinal) {
            if (values.length == 0) {
                throw new AssertionError("Enum class has no values!");
            }
            if (ordinal < 0 || ordinal >= values.length) {
                Class<?> enumType = values[0].getClass();
                throw new IllegalArgumentException("No constant with ordinal " 
                        + ordinal + " in " + enumType.getName());
            }
            return values[ordinal];
        }
        
        public static int toNative(Enum<?> v) {
            return v.ordinal();
        }
    }
    
}
