/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIDatePicker /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDatePicker /*</name>*/.class);
    }

    /*<constructors>*/
    public UIDatePicker() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("calendar") public native @Type("NSCalendar *") NSCalendar getCalendar();
    @Bind("setCalendar:") public native void setCalendar(@Type("NSCalendar *") NSCalendar v);
    @Bind("countDownDuration") public native @Type("NSTimeInterval") double getCountDownDuration();
    @Bind("setCountDownDuration:") public native void setCountDownDuration(@Type("NSTimeInterval") double v);
    @Bind("date") public native @Type("NSDate *") NSDate getDate();
    @Bind("setDate:") public native void setDate(@Type("NSDate *") NSDate v);
    @Bind("datePickerMode") public native @Type("UIDatePickerMode") UIDatePickerMode getDatePickerMode();
    @Bind("setDatePickerMode:") public native void setDatePickerMode(@Type("UIDatePickerMode") UIDatePickerMode v);
    @Bind("locale") public native @Type("NSLocale *") NSLocale getLocale();
    @Bind("setLocale:") public native void setLocale(@Type("NSLocale *") NSLocale v);
    @Bind("maximumDate") public native @Type("NSDate *") NSDate getMaximumDate();
    @Bind("setMaximumDate:") public native void setMaximumDate(@Type("NSDate *") NSDate v);
    @Bind("minimumDate") public native @Type("NSDate *") NSDate getMinimumDate();
    @Bind("setMinimumDate:") public native void setMinimumDate(@Type("NSDate *") NSDate v);
    @Bind("minuteInterval") public native @Type("NSInteger") int getMinuteInterval();
    @Bind("setMinuteInterval:") public native void setMinuteInterval(@Type("NSInteger") int v);
    @Bind("timeZone") public native @Type("NSTimeZone *") NSTimeZone getTimeZone();
    @Bind("setTimeZone:") public native void setTimeZone(@Type("NSTimeZone *") NSTimeZone v);
    /*</properties>*/
    /*<methods>*/
    @Bind("setDate:animated:") public native @Type("void") void setDate(@Type("NSDate *") NSDate date, @Type("BOOL") boolean animated);
    /*</methods>*/

}
