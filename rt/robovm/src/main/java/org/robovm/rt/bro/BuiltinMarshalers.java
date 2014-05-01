/*
 * Copyright (C) 2013 Trillian Mobile AB
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
import org.robovm.rt.bro.annotation.Marshalers;

/**
 * Defines builtin marshalers. This is the final fallback when searching for
 * a marshaler.
 */
@Marshalers({ 
    @Marshaler(ArrayMarshalers.ByteArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.ShortArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.CharArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.IntArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.LongArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.FloatArrayMarshaler.class),
    @Marshaler(ArrayMarshalers.DoubleArrayMarshaler.class),
    @Marshaler(BufferMarshalers.BufferMarshaler.class),
    @Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class),
    @Marshaler(EnumMarshalers.AsIntMarshaler.class),
})
public class BuiltinMarshalers {
}
