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
public enum /*<name>*/AUSelector/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Initialize(1L),
    Uninitialize(2L),
    GetPropertyInfo(3L),
    GetProperty(4L),
    SetProperty(5L),
    AddPropertyListener(10L),
    RemovePropertyListener(11L),
    RemovePropertyListenerWithUserData(18L),
    AddRenderNotify(15L),
    RemoveRenderNotify(16L),
    GetParameter(6L),
    SetParameter(7L),
    ScheduleParameters(17L),
    Render(14L),
    Reset(9L),
    ComplexRender(19L),
    Process(20L),
    ProcessMultiple(21L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AUSelector/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AUSelector/*</name>*/ valueOf(long n) {
        for (/*<name>*/AUSelector/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AUSelector/*</name>*/.class.getName());
    }
}
