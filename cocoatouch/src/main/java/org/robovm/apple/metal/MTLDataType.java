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
public enum /*<name>*/MTLDataType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    None(0L),
    Struct(1L),
    Array(2L),
    Float(3L),
    Float2(4L),
    Float3(5L),
    Float4(6L),
    Float2x2(7L),
    Float2x3(8L),
    Float2x4(9L),
    Float3x2(10L),
    Float3x3(11L),
    Float3x4(12L),
    Float4x2(13L),
    Float4x3(14L),
    Float4x4(15L),
    Half(16L),
    Half2(17L),
    Half3(18L),
    Half4(19L),
    Half2x2(20L),
    Half2x3(21L),
    Half2x4(22L),
    Half3x2(23L),
    Half3x3(24L),
    Half3x4(25L),
    Half4x2(26L),
    Half4x3(27L),
    Half4x4(28L),
    Int(29L),
    Int2(30L),
    Int3(31L),
    Int4(32L),
    UInt(33L),
    UInt2(34L),
    UInt3(35L),
    UInt4(36L),
    Short(37L),
    Short2(38L),
    Short3(39L),
    Short4(40L),
    UShort(41L),
    UShort2(42L),
    UShort3(43L),
    UShort4(44L),
    Char(45L),
    Char2(46L),
    Char3(47L),
    Char4(48L),
    UChar(49L),
    UChar2(50L),
    UChar3(51L),
    UChar4(52L),
    Bool(53L),
    Bool2(54L),
    Bool3(55L),
    Bool4(56L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MTLDataType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MTLDataType/*</name>*/ valueOf(long n) {
        for (/*<name>*/MTLDataType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MTLDataType/*</name>*/.class.getName());
    }
}
