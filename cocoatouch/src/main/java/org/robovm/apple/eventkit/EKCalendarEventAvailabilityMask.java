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
package org.robovm.apple.eventkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/ extends Bits</*<name>*/EKCalendarEventAvailabilityMask/*</name>*/> {
    /*<values>*/
    public static final EKCalendarEventAvailabilityMask None = new EKCalendarEventAvailabilityMask(0L);
    public static final EKCalendarEventAvailabilityMask Busy = new EKCalendarEventAvailabilityMask(1L);
    public static final EKCalendarEventAvailabilityMask Free = new EKCalendarEventAvailabilityMask(2L);
    public static final EKCalendarEventAvailabilityMask Tentative = new EKCalendarEventAvailabilityMask(4L);
    public static final EKCalendarEventAvailabilityMask Unavailable = new EKCalendarEventAvailabilityMask(8L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/[] values = _values(/*<name>*/EKCalendarEventAvailabilityMask/*</name>*/.class);

    public /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/(long value) { super(value); }
    private /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/(value, mask);
    }
    protected /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/EKCalendarEventAvailabilityMask/*</name>*/[] values() {
        return values.clone();
    }
}
