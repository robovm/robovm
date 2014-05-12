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
package org.robovm.apple.corelocation;

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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/CLError/*</name>*/ implements ValuedEnum {
    /*<values>*/
    LocationUnknown(0L),
    Denied(1L),
    Network(2L),
    HeadingFailure(3L),
    RegionMonitoringDenied(4L),
    RegionMonitoringFailure(5L),
    RegionMonitoringSetupDelayed(6L),
    RegionMonitoringResponseDelayed(7L),
    GeocodeFoundNoResult(8L),
    GeocodeFoundPartialResult(9L),
    GeocodeCanceled(10L),
    DeferredFailed(11L),
    DeferredNotUpdatingLocation(12L),
    DeferredAccuracyTooLow(13L),
    DeferredDistanceFiltered(14L),
    DeferredCanceled(15L),
    RangingUnavailable(16L),
    RangingFailure(17L);
    /*</values>*/

    /*<bind>*/static { Bro.bind(CLError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CLError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CLError/*</name>*/ valueOf(long n) {
        for (/*<name>*/CLError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CLError/*</name>*/.class.getName());
    }
}
