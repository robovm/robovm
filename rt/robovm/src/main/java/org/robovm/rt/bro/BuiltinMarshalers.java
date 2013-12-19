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
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Marshalers;

/**
 * Defines builtin marshalers. This is the final fallback when searching for
 * a marshaler.
 */
@Marshalers({ 
    @Marshaler(type = byte[].class, value = ArrayMarshalers.ByteArrayMarshaler.class),
    @Marshaler(type = byte[][].class, value = ArrayMarshalers.ByteArrayMarshaler.class),
    @Marshaler(type = byte[][][].class, value = ArrayMarshalers.ByteArrayMarshaler.class),
    @Marshaler(type = short[].class, value = ArrayMarshalers.ShortArrayMarshaler.class),
    @Marshaler(type = short[][].class, value = ArrayMarshalers.ShortArrayMarshaler.class),
    @Marshaler(type = short[][][].class, value = ArrayMarshalers.ShortArrayMarshaler.class),
    @Marshaler(type = char[].class, value = ArrayMarshalers.CharArrayMarshaler.class),
    @Marshaler(type = char[][].class, value = ArrayMarshalers.CharArrayMarshaler.class),
    @Marshaler(type = char[][][].class, value = ArrayMarshalers.CharArrayMarshaler.class),
    @Marshaler(type = int[].class, value = ArrayMarshalers.IntArrayMarshaler.class),
    @Marshaler(type = int[][].class, value = ArrayMarshalers.IntArrayMarshaler.class),
    @Marshaler(type = int[][][].class, value = ArrayMarshalers.IntArrayMarshaler.class),
    @Marshaler(type = long[].class, value = ArrayMarshalers.LongArrayMarshaler.class),
    @Marshaler(type = long[][].class, value = ArrayMarshalers.LongArrayMarshaler.class),
    @Marshaler(type = long[][][].class, value = ArrayMarshalers.LongArrayMarshaler.class),
    @Marshaler(type = float[].class, value = ArrayMarshalers.FloatArrayMarshaler.class),
    @Marshaler(type = float[][].class, value = ArrayMarshalers.FloatArrayMarshaler.class),
    @Marshaler(type = float[][][].class, value = ArrayMarshalers.FloatArrayMarshaler.class),
    @Marshaler(type = double[].class, value = ArrayMarshalers.DoubleArrayMarshaler.class),
    @Marshaler(type = double[][].class, value = ArrayMarshalers.DoubleArrayMarshaler.class),
    @Marshaler(type = double[][][].class, value = ArrayMarshalers.DoubleArrayMarshaler.class),
    @Marshaler(type = Struct[].class, value = Struct.StructArrayMarshaler.class),
    @Marshaler(type = Struct[][].class, value = Struct.StructArrayMarshaler.class),
    @Marshaler(type = Struct[][][].class, value = Struct.StructArrayMarshaler.class),
    @Marshaler(type = ByteBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = ShortBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = CharBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = IntBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = LongBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = FloatBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = DoubleBuffer.class, value = BufferMarshalers.BufferMarshaler.class),
    @Marshaler(type = String.class, value = StringMarshalers.AsDefaultCharsetZMarshaler.class),
    @Marshaler(type = Enum.class, value = EnumMarshalers.AsIntMarshaler.class),
})
public class BuiltinMarshalers {
}
