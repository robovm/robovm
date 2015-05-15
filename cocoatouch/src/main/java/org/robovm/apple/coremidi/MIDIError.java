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
package org.robovm.apple.coremidi;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/MIDIError/*</name>*/ implements ValuedEnum {
	No(0L),
    /*<values>*/
    InvalidClient(-10830L),
    InvalidPort(-10831L),
    WrongEndpointType(-10832L),
    NoConnection(-10833L),
    UnknownEndpoint(-10834L),
    UnknownProperty(-10835L),
    WrongPropertyType(-10836L),
    NoCurrentSetup(-10837L),
    MessageSendErr(-10838L),
    ServerStartErr(-10839L),
    SetupFormatErr(-10840L),
    WrongThread(-10841L),
    ObjectNotFound(-10842L),
    IDNotUnique(-10843L),
    NotPermitted(-10844L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MIDIError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MIDIError/*</name>*/ valueOf(long n) {
        for (/*<name>*/MIDIError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MIDIError/*</name>*/.class.getName());
    }
}
