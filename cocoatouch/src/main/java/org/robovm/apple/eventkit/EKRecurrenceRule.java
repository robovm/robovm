/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("EventKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/EKRecurrenceRule/*</name>*/ 
    extends /*<extends>*/EKObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class EKRecurrenceRulePtr extends Ptr<EKRecurrenceRule, EKRecurrenceRulePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(EKRecurrenceRule.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public EKRecurrenceRule() {}
    protected EKRecurrenceRule(SkipInit skipInit) { super(skipInit); }
    public EKRecurrenceRule(EKRecurrenceFrequency type, @MachineSizedSInt long interval, EKRecurrenceEnd end) { super((SkipInit) null); initObject(init(type, interval, end)); }
    public EKRecurrenceRule(EKRecurrenceFrequency type, @MachineSizedSInt long interval, NSArray<EKRecurrenceDayOfWeek> days, NSArray<NSNumber> monthDays, NSArray<NSNumber> months, NSArray<NSNumber> weeksOfTheYear, NSArray<NSNumber> daysOfTheYear, NSArray<NSNumber> setPositions, EKRecurrenceEnd end) { super((SkipInit) null); initObject(init(type, interval, days, monthDays, months, weeksOfTheYear, daysOfTheYear, setPositions, end)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "calendarIdentifier")
    public native String getCalendarIdentifier();
    @Property(selector = "recurrenceEnd")
    public native EKRecurrenceEnd getRecurrenceEnd();
    @Property(selector = "setRecurrenceEnd:")
    public native void setRecurrenceEnd(EKRecurrenceEnd v);
    @Property(selector = "frequency")
    public native EKRecurrenceFrequency getFrequency();
    @Property(selector = "interval")
    public native @MachineSizedSInt long getInterval();
    @Property(selector = "firstDayOfTheWeek")
    public native @MachineSizedSInt long getFirstDayOfTheWeek();
    @Property(selector = "daysOfTheWeek")
    public native NSArray<EKRecurrenceDayOfWeek> getDaysOfTheWeek();
    @Property(selector = "daysOfTheMonth")
    public native NSArray<NSNumber> getDaysOfTheMonth();
    @Property(selector = "daysOfTheYear")
    public native NSArray<NSNumber> getDaysOfTheYear();
    @Property(selector = "weeksOfTheYear")
    public native NSArray<NSNumber> getWeeksOfTheYear();
    @Property(selector = "monthsOfTheYear")
    public native NSArray<NSNumber> getMonthsOfTheYear();
    @Property(selector = "setPositions")
    public native NSArray<NSNumber> getSetPositions();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initRecurrenceWithFrequency:interval:end:")
    protected native @Pointer long init(EKRecurrenceFrequency type, @MachineSizedSInt long interval, EKRecurrenceEnd end);
    @Method(selector = "initRecurrenceWithFrequency:interval:daysOfTheWeek:daysOfTheMonth:monthsOfTheYear:weeksOfTheYear:daysOfTheYear:setPositions:end:")
    protected native @Pointer long init(EKRecurrenceFrequency type, @MachineSizedSInt long interval, NSArray<EKRecurrenceDayOfWeek> days, NSArray<NSNumber> monthDays, NSArray<NSNumber> months, NSArray<NSNumber> weeksOfTheYear, NSArray<NSNumber> daysOfTheYear, NSArray<NSNumber> setPositions, EKRecurrenceEnd end);
    /*</methods>*/
}
