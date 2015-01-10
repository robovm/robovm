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
@ForceLinkClass(EKError.class)
/*<annotations>*//*</annotations>*/
public enum /*<name>*/EKErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    EventNotMutable(0L),
    NoCalendar(1L),
    NoStartDate(2L),
    NoEndDate(3L),
    DatesInverted(4L),
    InternalFailure(5L),
    CalendarReadOnly(6L),
    DurationGreaterThanRecurrence(7L),
    AlarmGreaterThanRecurrence(8L),
    StartDateTooFarInFuture(9L),
    StartDateCollidesWithOtherOccurrence(10L),
    ObjectBelongsToDifferentStore(11L),
    InvitesCannotBeMoved(12L),
    InvalidSpan(13L),
    CalendarHasNoSource(14L),
    CalendarSourceCannotBeModified(15L),
    CalendarIsImmutable(16L),
    SourceDoesNotAllowCalendarAddDelete(17L),
    RecurringReminderRequiresDueDate(18L),
    StructuredLocationsNotSupported(19L),
    ReminderLocationsNotSupported(20L),
    AlarmProximityNotSupported(21L),
    CalendarDoesNotAllowEvents(22L),
    CalendarDoesNotAllowReminders(23L),
    SourceDoesNotAllowReminders(24L),
    PriorityIsInvalid(25L),
    InvalidEntityType(26L),
    Last(27L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/EKErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/EKErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/EKErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/EKErrorCode/*</name>*/.class.getName());
    }
}
