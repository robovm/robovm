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
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CMMPEG2VideoProfile/*</name>*/ implements ValuedEnum {
    /*<values>*/
    HDV_720p30(1751414321L),
    HDV_1080i60(1751414322L),
    HDV_1080i50(1751414323L),
    HDV_720p24(1751414324L),
    HDV_720p25(1751414325L),
    HDV_1080p24(1751414326L),
    HDV_1080p25(1751414327L),
    HDV_1080p30(1751414328L),
    HDV_720p60(1751414329L),
    HDV_720p50(1751414369L),
    XDCAM_HD_1080i60_VBR35(2019849778L),
    XDCAM_HD_1080i50_VBR35(2019849779L),
    XDCAM_HD_1080p24_VBR35(2019849782L),
    XDCAM_HD_1080p25_VBR35(2019849783L),
    XDCAM_HD_1080p30_VBR35(2019849784L),
    XDCAM_EX_720p24_VBR35(2019849780L),
    XDCAM_EX_720p25_VBR35(2019849781L),
    XDCAM_EX_720p30_VBR35(2019849777L),
    XDCAM_EX_720p50_VBR35(2019849825L),
    XDCAM_EX_720p60_VBR35(2019849785L),
    XDCAM_EX_1080i60_VBR35(2019849826L),
    XDCAM_EX_1080i50_VBR35(2019849827L),
    XDCAM_EX_1080p24_VBR35(2019849828L),
    XDCAM_EX_1080p25_VBR35(2019849829L),
    XDCAM_EX_1080p30_VBR35(2019849830L),
    XDCAM_HD422_720p50_CBR50(2019833185L),
    XDCAM_HD422_720p60_CBR50(2019833145L),
    XDCAM_HD422_1080i60_CBR50(2019833186L),
    XDCAM_HD422_1080i50_CBR50(2019833187L),
    XDCAM_HD422_1080p24_CBR50(2019833188L),
    XDCAM_HD422_1080p25_CBR50(2019833189L),
    XDCAM_HD422_1080p30_CBR50(2019833190L),
    XDCAM_HD_540p(2019846244L),
    XDCAM_HD422_540p(2019846194L),
    XDCAM_HD422_720p24_CBR50(2019833140L),
    XDCAM_HD422_720p25_CBR50(2019833141L),
    XDCAM_HD422_720p30_CBR50(2019833137L),
    XF(2019981873L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CMMPEG2VideoProfile/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CMMPEG2VideoProfile/*</name>*/ valueOf(long n) {
        for (/*<name>*/CMMPEG2VideoProfile/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CMMPEG2VideoProfile/*</name>*/.class.getName());
    }
}
