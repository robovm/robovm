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
public enum /*<name>*/AUOutputProperty/*</name>*/ implements AUPropertyType {
    /*<values>*/
    CurrentDevice(2000L),
    ChannelMap(2002L),
    EnableIO(2003L),
    StartTime(2004L),
    SetInputCallback(2005L),
    HasIO(2006L),
    StartTimestampsAtZero(2007L),
    IsRunning(2001L),
    MIDICallbacks(2010L),
    HostReceivesRemoteControlEvents(2011L),
    RemoteControlToHost(2012L),
    HostTransportState(2013L),
    NodeComponentDescription(2014L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUOutputProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUOutputProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUOutputProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUOutputProperty/*</name>*/.class.getName());
    }
}
