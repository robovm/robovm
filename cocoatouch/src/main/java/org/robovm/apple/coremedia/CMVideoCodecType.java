/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CMVideoCodecType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    _422YpCbCr8(846624121L),
    _Animation(1919706400L),
    _Cinepak(1668704612L),
    _JPEG(1785750887L),
    _JPEG_OpenDML(1684890161L),
    _SorensonVideo(1398165809L),
    _SorensonVideo3(1398165811L),
    _H263(1748121139L),
    _H264(1635148593L),
    _MPEG4Video(1836070006L),
    _MPEG2Video(1836069494L),
    _MPEG1Video(1836069238L),
    _DVCNTSC(1685480224L),
    _DVCPAL(1685480304L),
    _DVCProPAL(1685483632L),
    _DVCPro50NTSC(1685468526L),
    _DVCPro50PAL(1685468528L),
    _DVCPROHD720p60(1685481584L),
    _DVCPROHD720p50(1685481585L),
    _DVCPROHD1080i60(1685481526L),
    _DVCPROHD1080i50(1685481525L),
    _DVCPROHD1080p30(1685481523L),
    _DVCPROHD1080p25(1685481522L),
    _AppleProRes4444(1634743400L),
    _AppleProRes422HQ(1634755432L),
    _AppleProRes422(1634755438L),
    _AppleProRes422LT(1634755443L),
    _AppleProRes422Proxy(1634755439L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CMVideoCodecType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CMVideoCodecType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CMVideoCodecType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMVideoCodecType/*</name>*/.class.getName());
    }
}
