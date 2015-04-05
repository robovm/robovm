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
public enum /*<name>*/AUSubType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    GenericOutput(1734700658L),
    RemoteIO(1919512419L),
    VoiceProcessingIO(1987078511L),
    Sampler(1935764848L),
    MIDISynth(1836284270L),
    AUConverter(1668247158L),
    Varispeed(1986097769L),
    DeferredRenderer(1684366962L),
    Splitter(1936747636L),
    Merger(1835364967L),
    NewTimePitch(1853191280L),
    AUiPodTimeOther(1768977519L),
    RoundTripAAC(1918984547L),
    AUiPodTime(1768977517L),
    PeakLimiter(1819112562L),
    DynamicsProcessor(1684237680L),
    LowPassFilter(1819304307L),
    HighPassFilter(1752195443L),
    BandPassFilter(1651532147L),
    HighShelfFilter(1752393830L),
    LowShelfFilter(1819502694L),
    ParametricEQ(1886217585L),
    Distortion(1684632436L),
    Delay(1684368505L),
    SampleDelay(1935961209L),
    Reverb2(1920361010L),
    AUiPodEQ(1768973681L),
    NBandEQ(1851942257L),
    MultiChannelMixer(1835232632L),
    MatrixMixer(1836608888L),
    SpatialMixer(862217581L),
    AU3DMixerEmbedded(862217581L),
    ScheduledSoundPlayer(1936945260L),
    AudioFilePlayer(1634103404L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUSubType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUSubType/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUSubType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUSubType/*</name>*/.class.getName());
    }
}
