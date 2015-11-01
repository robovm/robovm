/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.modelio;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/MDLVertexFormat/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Invalid(0L),
    PackedBit(4096L),
    UCharBits(65536L),
    CharBits(131072L),
    UCharNormalizedBits(196608L),
    CharNormalizedBits(262144L),
    UShortBits(327680L),
    ShortBits(393216L),
    UShortNormalizedBits(458752L),
    ShortNormalizedBits(524288L),
    UIntBits(589824L),
    IntBits(655360L),
    HalfBits(720896L),
    FloatBits(786432L),
    UChar(65537L),
    UChar2(65538L),
    UChar3(65539L),
    UChar4(65540L),
    Char(131073L),
    Char2(131074L),
    Char3(131075L),
    Char4(131076L),
    UCharNormalized(196609L),
    UChar2Normalized(196610L),
    UChar3Normalized(196611L),
    UChar4Normalized(196612L),
    CharNormalized(262145L),
    Char2Normalized(262146L),
    Char3Normalized(262147L),
    Char4Normalized(262148L),
    UShort(327681L),
    UShort2(327682L),
    UShort3(327683L),
    UShort4(327684L),
    Short(393217L),
    Short2(393218L),
    Short3(393219L),
    Short4(393220L),
    UShortNormalized(458753L),
    UShort2Normalized(458754L),
    UShort3Normalized(458755L),
    UShort4Normalized(458756L),
    ShortNormalized(524289L),
    Short2Normalized(524290L),
    Short3Normalized(524291L),
    Short4Normalized(524292L),
    UInt(589825L),
    UInt2(589826L),
    UInt3(589827L),
    UInt4(589828L),
    Int(655361L),
    Int2(655362L),
    Int3(655363L),
    Int4(655364L),
    Half(720897L),
    Half2(720898L),
    Half3(720899L),
    Half4(720900L),
    Float(786433L),
    Float2(786434L),
    Float3(786435L),
    Float4(786436L),
    Int1010102Normalized(659460L),
    UInt1010102Normalized(593924L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MDLVertexFormat/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MDLVertexFormat/*</name>*/ valueOf(long n) {
        for (/*<name>*/MDLVertexFormat/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MDLVertexFormat/*</name>*/.class.getName());
    }
}
