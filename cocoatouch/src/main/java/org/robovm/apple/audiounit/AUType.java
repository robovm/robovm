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
import org.robovm.rt.annotation.*;
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
public enum /*<name>*/AUType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Output(1635086197L),
    MusicDevice(1635085685L),
    MusicEffect(1635085670L),
    FormatConverter(1635083875L),
    Effect(1635083896L),
    Mixer(1635085688L),
    Panner(1635086446L),
    Generator(1635084142L),
    OfflineEffect(1635086188L),
    MIDIProcessor(1635085673L),
    RemoteEffect(1635086968L),
    RemoteGenerator(1635086951L),
    RemoteInstrument(1635086953L),
    RemoteMusicEffect(1635086957L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private AUType(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUType/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUType/*</name>*/.class.getName());
    }
}
