/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.metal;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/MTLVertexFormat/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Invalid(0L),
    UChar2(1L),
    UChar3(2L),
    UChar4(3L),
    Char2(4L),
    Char3(5L),
    Char4(6L),
    UChar2Normalized(7L),
    UChar3Normalized(8L),
    UChar4Normalized(9L),
    Char2Normalized(10L),
    Char3Normalized(11L),
    Char4Normalized(12L),
    UShort2(13L),
    UShort3(14L),
    UShort4(15L),
    Short2(16L),
    Short3(17L),
    Short4(18L),
    UShort2Normalized(19L),
    UShort3Normalized(20L),
    UShort4Normalized(21L),
    Short2Normalized(22L),
    Short3Normalized(23L),
    Short4Normalized(24L),
    Half2(25L),
    Half3(26L),
    Half4(27L),
    Float(28L),
    Float2(29L),
    Float3(30L),
    Float4(31L),
    Int(32L),
    Int2(33L),
    Int3(34L),
    Int4(35L),
    UInt(36L),
    UInt2(37L),
    UInt3(38L),
    UInt4(39L),
    Int1010102Normalized(40L),
    UInt1010102Normalized(41L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MTLVertexFormat/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MTLVertexFormat/*</name>*/ valueOf(long n) {
        for (/*<name>*/MTLVertexFormat/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MTLVertexFormat/*</name>*/.class.getName());
    }
}
