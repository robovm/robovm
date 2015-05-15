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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CVPixelFormatType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    _1Monochrome(1L),
    _2Indexed(2L),
    _4Indexed(4L),
    _8Indexed(8L),
    _1IndexedGray_WhiteIsZero(33L),
    _2IndexedGray_WhiteIsZero(34L),
    _4IndexedGray_WhiteIsZero(36L),
    _8IndexedGray_WhiteIsZero(40L),
    _16BE555(16L),
    _16LE555(1278555445L),
    _16LE5551(892679473L),
    _16BE565(1110783541L),
    _16LE565(1278555701L),
    _24RGB(24L),
    _24BGR(842285639L),
    _32ARGB(32L),
    _32BGRA(1111970369L),
    _32ABGR(1094862674L),
    _32RGBA(1380401729L),
    _64ARGB(1647719521L),
    _48RGB(1647589490L),
    _32AlphaGray(1647522401L),
    _16Gray(1647392359L),
    _30RGB(1378955371L),
    _422YpCbCr8(846624121L),
    _4444YpCbCrA8(1983131704L),
    _4444YpCbCrA8R(1916022840L),
    _4444AYpCbCr8(2033463352L),
    _4444AYpCbCr16(2033463606L),
    _444YpCbCr8(1983066168L),
    _422YpCbCr16(1983000886L),
    _422YpCbCr10(1983000880L),
    _444YpCbCr10(1983131952L),
    _420YpCbCr8Planar(2033463856L),
    _420YpCbCr8PlanarFullRange(1714696752L),
    _422YpCbCr_4A_8BiPlanar(1630697081L),
    _420YpCbCr8BiPlanarVideoRange(875704438L),
    _420YpCbCr8BiPlanarFullRange(875704422L),
    _422YpCbCr8_yuvs(2037741171L),
    _422YpCbCr8FullRange(2037741158L),
    OneComponent8(1278226488L),
    TwoComponent8(843264056L),
    OneComponent16Half(1278226536L),
    OneComponent32Float(1278226534L),
    TwoComponent16Half(843264104L),
    TwoComponent32Float(843264102L),
    _64RGBAHalf(1380411457L),
    _128RGBAFloat(1380410945L);
    /*</values>*/

    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CVPixelFormatType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVPixelFormatType> list = new ArrayList<>();
            for (NSNumber n : o) {
                list.add(CVPixelFormatType.valueOf(n.longValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVPixelFormatType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSNumber> array = new NSMutableArray<>();
            for (CVPixelFormatType i : l) {
                array.add(NSNumber.valueOf(i.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CVPixelFormatType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CVPixelFormatType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CVPixelFormatType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CVPixelFormatType/*</name>*/.class.getName());
    }
}
