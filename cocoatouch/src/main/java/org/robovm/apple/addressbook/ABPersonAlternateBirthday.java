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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonAlternateBirthday/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    public ABPersonAlternateBirthday() {
        this.data = CFMutableDictionary.create();
    }
    protected ABPersonAlternateBirthday(CFDictionary data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(ABPersonAlternateBirthday.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSCalendarIdentifier getCalendarIdentifier() {
        if (data.containsKey(CalendarIdentifierKey())) {
            NSString val = data.get(CalendarIdentifierKey(), NSString.class);
            return NSCalendarIdentifier.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setCalendarIdentifier(NSCalendarIdentifier identifier) {
        data.put(CalendarIdentifierKey(), identifier.value());
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getEra() {
        if (data.containsKey(EraKey())) {
            NSNumber val = data.get(EraKey(), NSNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setEra(long era) {
        data.put(EraKey(), NSNumber.valueOf(era));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getYear() {
        if (data.containsKey(YearKey())) {
            NSNumber val = data.get(YearKey(), NSNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setYear(long year) {
        data.put(YearKey(), NSNumber.valueOf(year));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getMonth() {
        if (data.containsKey(MonthKey())) {
            NSNumber val = data.get(MonthKey(), NSNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setMonth(long month) {
        data.put(MonthKey(), NSNumber.valueOf(month));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isLeapMonth() {
        if (data.containsKey(IsLeapMonthKey())) {
            CFNumber val = data.get(IsLeapMonthKey(), CFNumber.class);
            return val.charValue() != 0;
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setLeapMonth(boolean leapMonth) {
        data.put(IsLeapMonthKey(), CFNumber.valueOf(leapMonth ? 1 : 0));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getDay() {
        if (data.containsKey(DayKey())) {
            NSNumber val = data.get(DayKey(), NSNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public ABPersonAlternateBirthday setDay(long day) {
        data.put(DayKey(), NSNumber.valueOf(day));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayCalendarIdentifierKey", optional=true)
    protected static native CFString CalendarIdentifierKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayEraKey", optional=true)
    protected static native CFString EraKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayYearKey", optional=true)
    protected static native CFString YearKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayMonthKey", optional=true)
    protected static native CFString MonthKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayIsLeapMonthKey", optional=true)
    protected static native CFString IsLeapMonthKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kABPersonAlternateBirthdayDayKey", optional=true)
    protected static native CFString DayKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
