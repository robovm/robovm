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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html">UIDatePicker Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/calendar">@property(nonatomic, copy) NSCalendar *calendar</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("calendar") public native @Type("NSCalendar *") NSCalendar getCalendar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/calendar">@property(nonatomic, copy) NSCalendar *calendar</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCalendar:") public native void setCalendar(@Type("NSCalendar *") NSCalendar v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/countDownDuration">@property(nonatomic) NSTimeInterval countDownDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("countDownDuration") public native @Type("NSTimeInterval") double getCountDownDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/countDownDuration">@property(nonatomic) NSTimeInterval countDownDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCountDownDuration:") public native void setCountDownDuration(@Type("NSTimeInterval") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/date">@property(nonatomic, retain) NSDate *date</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("date") public native @Type("NSDate *") NSDate getDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/date">@property(nonatomic, retain) NSDate *date</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDate:") public native void setDate(@Type("NSDate *") NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/datePickerMode">@property(nonatomic) UIDatePickerMode datePickerMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("datePickerMode") public native @Type("UIDatePickerMode") UIDatePickerMode getDatePickerMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/datePickerMode">@property(nonatomic) UIDatePickerMode datePickerMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDatePickerMode:") public native void setDatePickerMode(@Type("UIDatePickerMode") UIDatePickerMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/locale">@property(nonatomic, retain) NSLocale *locale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("locale") public native @Type("NSLocale *") NSLocale getLocale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/locale">@property(nonatomic, retain) NSLocale *locale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLocale:") public native void setLocale(@Type("NSLocale *") NSLocale v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/maximumDate">@property(nonatomic, retain) NSDate *maximumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumDate") public native @Type("NSDate *") NSDate getMaximumDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/maximumDate">@property(nonatomic, retain) NSDate *maximumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumDate:") public native void setMaximumDate(@Type("NSDate *") NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minimumDate">@property(nonatomic, retain) NSDate *minimumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumDate") public native @Type("NSDate *") NSDate getMinimumDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minimumDate">@property(nonatomic, retain) NSDate *minimumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumDate:") public native void setMinimumDate(@Type("NSDate *") NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minuteInterval">@property(nonatomic) NSInteger minuteInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minuteInterval") public native @Type("NSInteger") int getMinuteInterval();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minuteInterval">@property(nonatomic) NSInteger minuteInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinuteInterval:") public native void setMinuteInterval(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/timeZone">@property(nonatomic, retain) NSTimeZone *timeZone</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("timeZone") public native @Type("NSTimeZone *") NSTimeZone getTimeZone();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/timeZone">@property(nonatomic, retain) NSTimeZone *timeZone</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTimeZone:") public native void setTimeZone(@Type("NSTimeZone *") NSTimeZone v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instm/UIDatePicker/setDate:animated:">- (void)setDate:(NSDate *)date animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDate:animated:") public native @Type("void") void setDate(@Type("NSDate *") NSDate date, @Type("BOOL") boolean animated);
    /*</methods>*/

}
