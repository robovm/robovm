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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AudioSessionProperty/*</name>*/ implements ValuedEnum {
    /*<values>*/
    PreferredHardwareSampleRate(1752658802L),
    PreferredHardwareIOBufferDuration(1768907364L),
    AudioCategory(1633902964L),
    AudioRouteChange(1919902568L),
    CurrentHardwareSampleRate(1667789682L),
    CurrentHardwareInputNumberChannels(1667787107L),
    CurrentHardwareOutputNumberChannels(1667788643L),
    CurrentHardwareOutputVolume(1667788662L),
    CurrentHardwareInputLatency(1667853428L),
    CurrentHardwareOutputLatency(1668246644L),
    CurrentHardwareIOBufferDuration(1667785316L),
    OtherAudioIsPlaying(1869899890L),
    OverrideAudioRoute(1870033508L),
    AudioInputAvailable(1634296182L),
    ServerDied(1684628836L),
    OtherMixableAudioShouldDuck(1685414763L),
    OverrideCategoryMixWithOthers(1668114808L),
    OverrideCategoryDefaultToSpeaker(1668509803L),
    OverrideCategoryEnableBluetoothInput(1667394677L),
    InterruptionType(1954115685L),
    Mode(1836016741L),
    InputSources(1936876403L),
    OutputDestinations(1685288051L),
    InputSource(1769173603L),
    OutputDestination(1868854132L),
    InputGainAvailable(1768382838L),
    InputGainScalar(1768387427L),
    AudioRouteDescription(1668440434L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioSessionProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioSessionProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioSessionProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioSessionProperty/*</name>*/.class.getName());
    }
}
