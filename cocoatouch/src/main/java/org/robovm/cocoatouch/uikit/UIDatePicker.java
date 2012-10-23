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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIDatePicker /*</name>*/.class);

    /*<constructors>*/
    protected UIDatePicker(SkipInit skipInit) { super(skipInit); }
    public UIDatePicker() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector calendar = Selector.register("calendar");
    @Bridge(symbol = "objc_msgSend") private native static NSCalendar objc_getCalendar(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSCalendar objc_getCalendarSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/calendar">@property(nonatomic, copy) NSCalendar *calendar</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSCalendar getCalendar() {
        if (customClass) { return objc_getCalendarSuper(getSuper(), this, calendar); } else { return objc_getCalendar(this, calendar); }
    }
    
    private static final Selector setCalendar$ = Selector.register("setCalendar:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setCalendar(UIDatePicker __self__, Selector __cmd__, NSCalendar calendar);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setCalendarSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSCalendar calendar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/calendar">@property(nonatomic, copy) NSCalendar *calendar</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCalendar(NSCalendar calendar) {
        if (customClass) { objc_setCalendarSuper(getSuper(), this, setCalendar$, calendar); } else { objc_setCalendar(this, setCalendar$, calendar); }
    }
    
    private static final Selector countDownDuration = Selector.register("countDownDuration");
    @Bridge(symbol = "objc_msgSend") private native static double objc_getCountDownDuration(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static double objc_getCountDownDurationSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/countDownDuration">@property(nonatomic) NSTimeInterval countDownDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    public double getCountDownDuration() {
        if (customClass) { return objc_getCountDownDurationSuper(getSuper(), this, countDownDuration); } else { return objc_getCountDownDuration(this, countDownDuration); }
    }
    
    private static final Selector setCountDownDuration$ = Selector.register("setCountDownDuration:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setCountDownDuration(UIDatePicker __self__, Selector __cmd__, double countDownDuration);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setCountDownDurationSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, double countDownDuration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/countDownDuration">@property(nonatomic) NSTimeInterval countDownDuration</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCountDownDuration(double countDownDuration) {
        if (customClass) { objc_setCountDownDurationSuper(getSuper(), this, setCountDownDuration$, countDownDuration); } else { objc_setCountDownDuration(this, setCountDownDuration$, countDownDuration); }
    }
    
    private static final Selector date = Selector.register("date");
    @Bridge(symbol = "objc_msgSend") private native static NSDate objc_getDate(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDate objc_getDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/date">@property(nonatomic, retain) NSDate *date</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getDate() {
        if (customClass) { return objc_getDateSuper(getSuper(), this, date); } else { return objc_getDate(this, date); }
    }
    
    private static final Selector setDate$ = Selector.register("setDate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDate(UIDatePicker __self__, Selector __cmd__, NSDate date);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSDate date);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/date">@property(nonatomic, retain) NSDate *date</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDate(NSDate date) {
        if (customClass) { objc_setDateSuper(getSuper(), this, setDate$, date); } else { objc_setDate(this, setDate$, date); }
    }
    
    private static final Selector datePickerMode = Selector.register("datePickerMode");
    @Bridge(symbol = "objc_msgSend") private native static UIDatePickerMode objc_getDatePickerMode(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIDatePickerMode objc_getDatePickerModeSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/datePickerMode">@property(nonatomic) UIDatePickerMode datePickerMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIDatePickerMode getDatePickerMode() {
        if (customClass) { return objc_getDatePickerModeSuper(getSuper(), this, datePickerMode); } else { return objc_getDatePickerMode(this, datePickerMode); }
    }
    
    private static final Selector setDatePickerMode$ = Selector.register("setDatePickerMode:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDatePickerMode(UIDatePicker __self__, Selector __cmd__, UIDatePickerMode datePickerMode);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDatePickerModeSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, UIDatePickerMode datePickerMode);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/datePickerMode">@property(nonatomic) UIDatePickerMode datePickerMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDatePickerMode(UIDatePickerMode datePickerMode) {
        if (customClass) { objc_setDatePickerModeSuper(getSuper(), this, setDatePickerMode$, datePickerMode); } else { objc_setDatePickerMode(this, setDatePickerMode$, datePickerMode); }
    }
    
    private static final Selector locale = Selector.register("locale");
    @Bridge(symbol = "objc_msgSend") private native static NSLocale objc_getLocale(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSLocale objc_getLocaleSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/locale">@property(nonatomic, retain) NSLocale *locale</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSLocale getLocale() {
        if (customClass) { return objc_getLocaleSuper(getSuper(), this, locale); } else { return objc_getLocale(this, locale); }
    }
    
    private static final Selector setLocale$ = Selector.register("setLocale:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLocale(UIDatePicker __self__, Selector __cmd__, NSLocale locale);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLocaleSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSLocale locale);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/locale">@property(nonatomic, retain) NSLocale *locale</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLocale(NSLocale locale) {
        if (customClass) { objc_setLocaleSuper(getSuper(), this, setLocale$, locale); } else { objc_setLocale(this, setLocale$, locale); }
    }
    
    private static final Selector maximumDate = Selector.register("maximumDate");
    @Bridge(symbol = "objc_msgSend") private native static NSDate objc_getMaximumDate(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDate objc_getMaximumDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/maximumDate">@property(nonatomic, retain) NSDate *maximumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getMaximumDate() {
        if (customClass) { return objc_getMaximumDateSuper(getSuper(), this, maximumDate); } else { return objc_getMaximumDate(this, maximumDate); }
    }
    
    private static final Selector setMaximumDate$ = Selector.register("setMaximumDate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumDate(UIDatePicker __self__, Selector __cmd__, NSDate maximumDate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSDate maximumDate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/maximumDate">@property(nonatomic, retain) NSDate *maximumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumDate(NSDate maximumDate) {
        if (customClass) { objc_setMaximumDateSuper(getSuper(), this, setMaximumDate$, maximumDate); } else { objc_setMaximumDate(this, setMaximumDate$, maximumDate); }
    }
    
    private static final Selector minimumDate = Selector.register("minimumDate");
    @Bridge(symbol = "objc_msgSend") private native static NSDate objc_getMinimumDate(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDate objc_getMinimumDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minimumDate">@property(nonatomic, retain) NSDate *minimumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getMinimumDate() {
        if (customClass) { return objc_getMinimumDateSuper(getSuper(), this, minimumDate); } else { return objc_getMinimumDate(this, minimumDate); }
    }
    
    private static final Selector setMinimumDate$ = Selector.register("setMinimumDate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumDate(UIDatePicker __self__, Selector __cmd__, NSDate minimumDate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSDate minimumDate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minimumDate">@property(nonatomic, retain) NSDate *minimumDate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumDate(NSDate minimumDate) {
        if (customClass) { objc_setMinimumDateSuper(getSuper(), this, setMinimumDate$, minimumDate); } else { objc_setMinimumDate(this, setMinimumDate$, minimumDate); }
    }
    
    private static final Selector minuteInterval = Selector.register("minuteInterval");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getMinuteInterval(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getMinuteIntervalSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minuteInterval">@property(nonatomic) NSInteger minuteInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getMinuteInterval() {
        if (customClass) { return objc_getMinuteIntervalSuper(getSuper(), this, minuteInterval); } else { return objc_getMinuteInterval(this, minuteInterval); }
    }
    
    private static final Selector setMinuteInterval$ = Selector.register("setMinuteInterval:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinuteInterval(UIDatePicker __self__, Selector __cmd__, int minuteInterval);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinuteIntervalSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, int minuteInterval);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/minuteInterval">@property(nonatomic) NSInteger minuteInterval</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinuteInterval(int minuteInterval) {
        if (customClass) { objc_setMinuteIntervalSuper(getSuper(), this, setMinuteInterval$, minuteInterval); } else { objc_setMinuteInterval(this, setMinuteInterval$, minuteInterval); }
    }
    
    private static final Selector timeZone = Selector.register("timeZone");
    @Bridge(symbol = "objc_msgSend") private native static NSTimeZone objc_getTimeZone(UIDatePicker __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSTimeZone objc_getTimeZoneSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/timeZone">@property(nonatomic, retain) NSTimeZone *timeZone</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSTimeZone getTimeZone() {
        if (customClass) { return objc_getTimeZoneSuper(getSuper(), this, timeZone); } else { return objc_getTimeZone(this, timeZone); }
    }
    
    private static final Selector setTimeZone$ = Selector.register("setTimeZone:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTimeZone(UIDatePicker __self__, Selector __cmd__, NSTimeZone timeZone);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTimeZoneSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSTimeZone timeZone);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instp/UIDatePicker/timeZone">@property(nonatomic, retain) NSTimeZone *timeZone</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTimeZone(NSTimeZone timeZone) {
        if (customClass) { objc_setTimeZoneSuper(getSuper(), this, setTimeZone$, timeZone); } else { objc_setTimeZone(this, setTimeZone$, timeZone); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setDate$animated$ = Selector.register("setDate:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDate(UIDatePicker __self__, Selector __cmd__, NSDate date, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDateSuper(ObjCSuper __super__, UIDatePicker __self__, Selector __cmd__, NSDate date, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDatePicker_Class/Reference/UIDatePicker.html#//apple_ref/occ/instm/UIDatePicker/setDate:animated:">- (void)setDate:(NSDate *)date animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDate(NSDate date, boolean animated) {
        if (customClass) { objc_setDateSuper(getSuper(), this, setDate$animated$, date, animated); } else { objc_setDate(this, setDate$animated$, date, animated); }
    }
    /*</methods>*/

}
