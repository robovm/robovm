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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSCalendarUnit/*</name>*/ extends Bits</*<name>*/NSCalendarUnit/*</name>*/> {
    /*<values>*/
    public static final NSCalendarUnit None = new NSCalendarUnit(0L);
    public static final NSCalendarUnit Era = new NSCalendarUnit(2L);
    public static final NSCalendarUnit Year = new NSCalendarUnit(4L);
    public static final NSCalendarUnit Month = new NSCalendarUnit(8L);
    public static final NSCalendarUnit Day = new NSCalendarUnit(16L);
    public static final NSCalendarUnit Hour = new NSCalendarUnit(32L);
    public static final NSCalendarUnit Minute = new NSCalendarUnit(64L);
    public static final NSCalendarUnit Second = new NSCalendarUnit(128L);
    public static final NSCalendarUnit Weekday = new NSCalendarUnit(512L);
    public static final NSCalendarUnit WeekdayOrdinal = new NSCalendarUnit(1024L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarUnit Quarter = new NSCalendarUnit(2048L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCalendarUnit WeekOfMonth = new NSCalendarUnit(4096L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCalendarUnit WeekOfYear = new NSCalendarUnit(8192L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCalendarUnit YearForWeekOfYear = new NSCalendarUnit(16384L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSCalendarUnit Nanosecond = new NSCalendarUnit(32768L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarUnit Calendar = new NSCalendarUnit(1048576L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSCalendarUnit TimeZone = new NSCalendarUnit(2097152L);
    /*</values>*/

    private static final /*<name>*/NSCalendarUnit/*</name>*/[] values = _values(/*<name>*/NSCalendarUnit/*</name>*/.class);

    public /*<name>*/NSCalendarUnit/*</name>*/(long value) { super(value); }
    private /*<name>*/NSCalendarUnit/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSCalendarUnit/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSCalendarUnit/*</name>*/(value, mask);
    }
    protected /*<name>*/NSCalendarUnit/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSCalendarUnit/*</name>*/[] values() {
        return values.clone();
    }
}
