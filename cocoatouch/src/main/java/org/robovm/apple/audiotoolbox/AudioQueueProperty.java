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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AudioQueueProperty/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Property_IsRunning(1634824814L),
    DeviceProperty_SampleRate(1634825074L),
    DeviceProperty_NumberChannels(1634821219L),
    Property_CurrentDevice(1634820964L),
    Property_MagicCookie(1634823523L),
    Property_MaximumOutputPacketSize(2020569203L),
    Property_StreamDescription(1634821748L),
    Property_ChannelLayout(1634820972L),
    Property_EnableLevelMetering(1634823525L),
    Property_CurrentLevelMeter(1634823542L),
    Property_CurrentLevelMeterDB(1634823524L),
    Property_DecodeBufferSizeFrames(1684234854L),
    Property_ConverterError(1902343781L),
    Property_EnableTimePitch(1902081136L),
    Property_TimePitchAlgorithm(1903456353L),
    Property_TimePitchBypass(1903456354L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioQueueProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioQueueProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioQueueProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioQueueProperty/*</name>*/.class.getName());
    }
}
