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
package org.robovm.apple.healthkit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/HKBodyTemperatureSensorLocation/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Other(0L),
    Armpit(1L),
    Body(2L),
    Ear(3L),
    Finger(4L),
    GastroIntestinal(5L),
    Mouth(6L),
    Rectum(7L),
    Toe(8L),
    EarDrum(9L),
    TemporalArtery(10L),
    Forehead(11L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/HKBodyTemperatureSensorLocation/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/HKBodyTemperatureSensorLocation/*</name>*/ valueOf(long n) {
        for (/*<name>*/HKBodyTemperatureSensorLocation/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/HKBodyTemperatureSensorLocation/*</name>*/.class.getName());
    }
}
