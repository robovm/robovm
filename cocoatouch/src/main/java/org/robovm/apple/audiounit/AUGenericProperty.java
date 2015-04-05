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
package org.robovm.apple.audiounit;

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
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AUGenericProperty/*</name>*/ implements AUPropertyID {
    /*<values>*/
    ClassInfo(0L),
    MakeConnection(1L),
    SampleRate(2L),
    ParameterList(3L),
    ParameterInfo(4L),
    CPULoad(6L),
    StreamFormat(8L),
    ElementCount(11L),
    Latency(12L),
    SupportedNumChannels(13L),
    MaximumFramesPerSlice(14L),
    ParameterValueStrings(16L),
    AudioChannelLayout(19L),
    TailTime(20L),
    BypassEffect(21L),
    LastRenderError(22L),
    SetRenderCallback(23L),
    FactoryPresets(24L),
    RenderQuality(26L),
    HostCallbacks(27L),
    InPlaceProcessing(29L),
    ElementName(30L),
    SupportedChannelLayoutTags(32L),
    PresentPreset(36L),
    DependentParameters(45L),
    InputSamplesInOutput(49L),
    ShouldAllocateBuffer(51L),
    FrequencyResponse(52L),
    ParameterHistoryInfo(53L),
    NickName(54L),
    OfflineRender(37L),
    ParameterIDName(34L),
    ParameterStringFromValue(33L),
    ParameterValueFromString(38L),
    RemoteControlEventListener(100L),
    IsInterAppConnected(101L),
    PeerURL(102L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUGenericProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUGenericProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUGenericProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUGenericProperty/*</name>*/.class.getName());
    }
}
