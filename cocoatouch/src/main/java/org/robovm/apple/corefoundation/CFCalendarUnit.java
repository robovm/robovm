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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFCalendarUnit/*</name>*/ extends Bits</*<name>*/CFCalendarUnit/*</name>*/> {
    /*<values>*/
    public static final CFCalendarUnit Era = new CFCalendarUnit(2L);
    public static final CFCalendarUnit Year = new CFCalendarUnit(4L);
    public static final CFCalendarUnit Month = new CFCalendarUnit(8L);
    public static final CFCalendarUnit Day = new CFCalendarUnit(16L);
    public static final CFCalendarUnit Hour = new CFCalendarUnit(32L);
    public static final CFCalendarUnit Minute = new CFCalendarUnit(64L);
    public static final CFCalendarUnit Second = new CFCalendarUnit(128L);
    public static final CFCalendarUnit Week = new CFCalendarUnit(256L);
    public static final CFCalendarUnit Weekday = new CFCalendarUnit(512L);
    public static final CFCalendarUnit WeekdayOrdinal = new CFCalendarUnit(1024L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFCalendarUnit Quarter = new CFCalendarUnit(2048L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFCalendarUnit WeekOfMonth = new CFCalendarUnit(4096L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFCalendarUnit WeekOfYear = new CFCalendarUnit(8192L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CFCalendarUnit YearForWeekOfYear = new CFCalendarUnit(16384L);
    /*</values>*/

    private static final /*<name>*/CFCalendarUnit/*</name>*/[] values = _values(/*<name>*/CFCalendarUnit/*</name>*/.class);

    public /*<name>*/CFCalendarUnit/*</name>*/(long value) { super(value); }
    private /*<name>*/CFCalendarUnit/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFCalendarUnit/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFCalendarUnit/*</name>*/(value, mask);
    }
    protected /*<name>*/CFCalendarUnit/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFCalendarUnit/*</name>*/[] values() {
        return values.clone();
    }
}
