/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.harmony.luni.platform;


/**
 * Defines some C scalar types.
 * 
 */

public interface ICommonDataTypes {

    // Nothing
    public static final int SIZEOF_NO_BYTES = 0;

    public static final int SIZEOF_VOID = SIZEOF_NO_BYTES;

    // One byte
    public static final int SIZEOF_ONE_BYTE = 1;

    public static final int SIZEOF_UNSIGNED_CHAR = SIZEOF_ONE_BYTE; // ANSI C

    public static final int SIZEOF_JBOOLEAN = SIZEOF_ONE_BYTE; // Java language

    public static final int SIZEOF_BOOLEAN = SIZEOF_ONE_BYTE;

    public static final int SIZEOF_BOOL = SIZEOF_ONE_BYTE;

    public static final int SIZEOF_SIGNED_8_BITS = SIZEOF_ONE_BYTE;

    public static final int SIZEOF_SIGNED_CHAR = SIZEOF_ONE_BYTE; // ANSI C

    public static final int SIZEOF_JBYTE = SIZEOF_ONE_BYTE; // Java language

    // Two bytes
    public static final int SIZEOF_TWO_BYTES = 2;

    public static final int SIZEOF_UNSIGNED_SHORT = SIZEOF_TWO_BYTES; // ANSI
                                                                        // C

    public static final int SIZEOF_JCHAR = SIZEOF_TWO_BYTES; // Java language

    public static final int SIZEOF_UINT16 = SIZEOF_TWO_BYTES; // Common
                                                                // abbreviation

    public static final int SIZEOF_SIGNED_16_BITS = SIZEOF_TWO_BYTES;

    public static final int SIZEOF_SIGNED_SHORT = SIZEOF_TWO_BYTES; // ANSI C

    public static final int SIZEOF_JSHORT = SIZEOF_TWO_BYTES; // Java language

    public static final int SIZEOF_SHORT = SIZEOF_TWO_BYTES; // Common
                                                                // convention

    public static final int SIZEOF_INT16 = SIZEOF_TWO_BYTES; // Common
                                                                // abbreviation

    // Four bytes
    public static final int SIZE_OF_FOUR_BYTES = 4;

    public static final int SIZEOF_UNSIGNED_32_BITS = SIZE_OF_FOUR_BYTES;

    public static final int SIZEOF_UINT32 = SIZE_OF_FOUR_BYTES; // Common
                                                                // abbreviation

    public static final int SIZEOF_UNSIGNED_LONG = SIZE_OF_FOUR_BYTES; // Common
                                                                        // convention

    public static final int SIZEOF_SIGNED_32_BITS = SIZE_OF_FOUR_BYTES;

    public static final int SIZEOF_SIGNED_LONG = SIZE_OF_FOUR_BYTES; // ANSI
                                                                        // C

    public static final int SIZEOF_JINT = SIZE_OF_FOUR_BYTES; // Java language

    public static final int SIZEOF_JSIZE = SIZE_OF_FOUR_BYTES; // Java language

    public static final int SIZEOF_LONG = SIZE_OF_FOUR_BYTES; // Common
                                                                // abbreviation

    public static final int SIZEOF_INT32 = SIZE_OF_FOUR_BYTES; // Common
                                                                // abbreviation

    public static final int SIZEOF_IEEE754_32_BITS = SIZE_OF_FOUR_BYTES;

    public static final int SIZEOF_JFLOAT = SIZE_OF_FOUR_BYTES; // Java language

    public static final int SIZEOF_FLOAT = SIZE_OF_FOUR_BYTES; // Common
                                                                // convention

    // Eight bytes
    public static final int SIZEOF_EIGHT_BYTES = 8;

    public static final int SIZEOF_UNSIGNED_64_BITS = SIZEOF_EIGHT_BYTES;

    public static final int SIZEOF_SIGNED_64_BITS = SIZEOF_EIGHT_BYTES;

    public static final int SIZEOF_JLONG = SIZEOF_EIGHT_BYTES; // Java language

    public static final int SIZEOF_IEEE754_64_BITS = SIZEOF_EIGHT_BYTES;

    public static final int SIZEOF_JDOUBLE = SIZEOF_EIGHT_BYTES; // Java
                                                                    // language

    public static final int SIZEOF_DOUBLE = SIZEOF_EIGHT_BYTES; // Common
                                                                // convention
}
