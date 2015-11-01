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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.mapkit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKEvent/*</name>*/ 
    extends /*<extends>*/EKCalendarItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKEventPtr extends Ptr<EKEvent, EKEventPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKEvent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKEvent() {}
    protected EKEvent(SkipInit skipInit) { super(skipInit); }
    public EKEvent(EKEventStore eventStore) { super(create(eventStore)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "eventIdentifier")
    public native String getEventIdentifier();
    @Property(selector = "isAllDay")
    public native boolean isAllDay();
    @Property(selector = "setAllDay:")
    public native void setAllDay(boolean v);
    @Property(selector = "startDate")
    public native NSDate getStartDate();
    @Property(selector = "setStartDate:")
    public native void setStartDate(NSDate v);
    @Property(selector = "endDate")
    public native NSDate getEndDate();
    @Property(selector = "setEndDate:")
    public native void setEndDate(NSDate v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "structuredLocation")
    public native EKStructuredLocation getStructuredLocation();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setStructuredLocation:")
    public native void setStructuredLocation(EKStructuredLocation v);
    @Property(selector = "organizer")
    public native EKParticipant getOrganizer();
    @Property(selector = "availability")
    public native EKEventAvailability getAvailability();
    @Property(selector = "setAvailability:")
    public native void setAvailability(EKEventAvailability v);
    @Property(selector = "status")
    public native EKEventStatus getStatus();
    @Property(selector = "isDetached")
    public native boolean isDetached();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "occurrenceDate")
    public native NSDate getOccurrenceDate();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "birthdayContactIdentifier")
    public native String getBirthdayContactIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "birthdayPersonID")
    public native @MachineSizedSInt long getBirthdayPersonID();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "compareStartDateWithEvent:")
    public native NSComparisonResult compareStartDateWithEvent(EKEvent other);
    @Method(selector = "refresh")
    public native boolean refresh();
    @Method(selector = "eventWithEventStore:")
    private static native @Pointer long create(EKEventStore eventStore);
    /*</methods>*/
}
