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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CMVideoCodecType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    _422YpCbCr8(846624121L),
    Animation(1919706400L),
    Cinepak(1668704612L),
    JPEG(1785750887L),
    JPEG_OpenDML(1684890161L),
    SorensonVideo(1398165809L),
    SorensonVideo3(1398165811L),
    H263(1748121139L),
    H264(1635148593L),
    MPEG4Video(1836070006L),
    MPEG2Video(1836069494L),
    MPEG1Video(1836069238L),
    DVCNTSC(1685480224L),
    DVCPAL(1685480304L),
    DVCProPAL(1685483632L),
    DVCPro50NTSC(1685468526L),
    DVCPro50PAL(1685468528L),
    DVCPROHD720p60(1685481584L),
    DVCPROHD720p50(1685481585L),
    DVCPROHD1080i60(1685481526L),
    DVCPROHD1080i50(1685481525L),
    DVCPROHD1080p30(1685481523L),
    DVCPROHD1080p25(1685481522L),
    AppleProRes4444(1634743400L),
    AppleProRes422HQ(1634755432L),
    AppleProRes422(1634755438L),
    AppleProRes422LT(1634755443L),
    AppleProRes422Proxy(1634755439L);
    /*</values>*/

    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CMVideoCodecType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMVideoCodecType> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(CMVideoCodecType.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMVideoCodecType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (CMVideoCodecType i : l) {
                array.add(new NSString(i.asFourCharCode()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CMVideoCodecType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    
    public String asFourCharCode() {
        byte[] b = new byte[8];
        long value = value();
        for (int i = 0; i < 8; ++i) {
            b[i] = (byte)(value >> (8 - i - 1 << 3));
        }
        return new String(b);
    }
    
    public static /*<name>*/CMVideoCodecType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CMVideoCodecType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMVideoCodecType/*</name>*/.class.getName());
    }
    
    public static /*<name>*/CMVideoCodecType/*</name>*/ valueOf(NSString n) {
        byte[] by = n.toString().getBytes();
        long value = 0;
        for (int i = 0; i < by.length; i++) {
            value = (value << 8) + (by[i] & 0xff);
        }
        
        for (/*<name>*/CMVideoCodecType/*</name>*/ v : values()) {
            if (v.n == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMVideoCodecType/*</name>*/.class.getName());
    }
}
