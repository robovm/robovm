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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html">UILocalNotification Class Reference</a>
 *   @since Available in iOS 4.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UILocalNotification /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILocalNotification /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UILocalNotification /*</name>*/.class);

    /*<constructors>*/
    protected UILocalNotification(SkipInit skipInit) { super(skipInit); }
    public UILocalNotification() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alertAction = Selector.register("alertAction");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getAlertAction(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getAlertActionSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertAction">@property(nonatomic, copy) NSString *alertAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public String getAlertAction() {
        if (customClass) { return objc_getAlertActionSuper(getSuper(), this, alertAction); } else { return objc_getAlertAction(this, alertAction); }
    }
    
    private static final Selector setAlertAction$ = Selector.register("setAlertAction:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAlertAction(UILocalNotification __self__, Selector __cmd__, String alertAction);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAlertActionSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, String alertAction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertAction">@property(nonatomic, copy) NSString *alertAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setAlertAction(String alertAction) {
        if (customClass) { objc_setAlertActionSuper(getSuper(), this, setAlertAction$, alertAction); } else { objc_setAlertAction(this, setAlertAction$, alertAction); }
    }
    
    private static final Selector alertBody = Selector.register("alertBody");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getAlertBody(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getAlertBodySuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertBody">@property(nonatomic, copy) NSString *alertBody</a>
     * @since Available in iOS 4.0 and later.
     */
    public String getAlertBody() {
        if (customClass) { return objc_getAlertBodySuper(getSuper(), this, alertBody); } else { return objc_getAlertBody(this, alertBody); }
    }
    
    private static final Selector setAlertBody$ = Selector.register("setAlertBody:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAlertBody(UILocalNotification __self__, Selector __cmd__, String alertBody);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAlertBodySuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, String alertBody);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertBody">@property(nonatomic, copy) NSString *alertBody</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setAlertBody(String alertBody) {
        if (customClass) { objc_setAlertBodySuper(getSuper(), this, setAlertBody$, alertBody); } else { objc_setAlertBody(this, setAlertBody$, alertBody); }
    }
    
    private static final Selector alertLaunchImage = Selector.register("alertLaunchImage");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getAlertLaunchImage(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getAlertLaunchImageSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertLaunchImage">@property(nonatomic,copy) NSString *alertLaunchImage</a>
     * @since Available in iOS 4.0 and later.
     */
    public String getAlertLaunchImage() {
        if (customClass) { return objc_getAlertLaunchImageSuper(getSuper(), this, alertLaunchImage); } else { return objc_getAlertLaunchImage(this, alertLaunchImage); }
    }
    
    private static final Selector setAlertLaunchImage$ = Selector.register("setAlertLaunchImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAlertLaunchImage(UILocalNotification __self__, Selector __cmd__, String alertLaunchImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAlertLaunchImageSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, String alertLaunchImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertLaunchImage">@property(nonatomic,copy) NSString *alertLaunchImage</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setAlertLaunchImage(String alertLaunchImage) {
        if (customClass) { objc_setAlertLaunchImageSuper(getSuper(), this, setAlertLaunchImage$, alertLaunchImage); } else { objc_setAlertLaunchImage(this, setAlertLaunchImage$, alertLaunchImage); }
    }
    
    private static final Selector applicationIconBadgeNumber = Selector.register("applicationIconBadgeNumber");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getApplicationIconBadgeNumber(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getApplicationIconBadgeNumberSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 4.0 and later.
     */
    public int getApplicationIconBadgeNumber() {
        if (customClass) { return objc_getApplicationIconBadgeNumberSuper(getSuper(), this, applicationIconBadgeNumber); } else { return objc_getApplicationIconBadgeNumber(this, applicationIconBadgeNumber); }
    }
    
    private static final Selector setApplicationIconBadgeNumber$ = Selector.register("setApplicationIconBadgeNumber:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setApplicationIconBadgeNumber(UILocalNotification __self__, Selector __cmd__, int applicationIconBadgeNumber);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setApplicationIconBadgeNumberSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, int applicationIconBadgeNumber);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setApplicationIconBadgeNumber(int applicationIconBadgeNumber) {
        if (customClass) { objc_setApplicationIconBadgeNumberSuper(getSuper(), this, setApplicationIconBadgeNumber$, applicationIconBadgeNumber); } else { objc_setApplicationIconBadgeNumber(this, setApplicationIconBadgeNumber$, applicationIconBadgeNumber); }
    }
    
    private static final Selector fireDate = Selector.register("fireDate");
    @Bridge(symbol = "objc_msgSend") private native static NSDate objc_getFireDate(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDate objc_getFireDateSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/fireDate">@property(nonatomic, copy) NSDate *fireDate</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getFireDate() {
        if (customClass) { return objc_getFireDateSuper(getSuper(), this, fireDate); } else { return objc_getFireDate(this, fireDate); }
    }
    
    private static final Selector setFireDate$ = Selector.register("setFireDate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setFireDate(UILocalNotification __self__, Selector __cmd__, NSDate fireDate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setFireDateSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, NSDate fireDate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/fireDate">@property(nonatomic, copy) NSDate *fireDate</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setFireDate(NSDate fireDate) {
        if (customClass) { objc_setFireDateSuper(getSuper(), this, setFireDate$, fireDate); } else { objc_setFireDate(this, setFireDate$, fireDate); }
    }
    
    private static final Selector hasAction = Selector.register("hasAction");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isHasAction(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isHasActionSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/hasAction">@property(nonatomic) BOOL hasAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean isHasAction() {
        if (customClass) { return objc_isHasActionSuper(getSuper(), this, hasAction); } else { return objc_isHasAction(this, hasAction); }
    }
    
    private static final Selector setHasAction$ = Selector.register("setHasAction:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHasAction(UILocalNotification __self__, Selector __cmd__, boolean hasAction);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHasActionSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, boolean hasAction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/hasAction">@property(nonatomic) BOOL hasAction</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setHasAction(boolean hasAction) {
        if (customClass) { objc_setHasActionSuper(getSuper(), this, setHasAction$, hasAction); } else { objc_setHasAction(this, setHasAction$, hasAction); }
    }
    
    private static final Selector repeatCalendar = Selector.register("repeatCalendar");
    @Bridge(symbol = "objc_msgSend") private native static NSCalendar objc_getRepeatCalendar(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSCalendar objc_getRepeatCalendarSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatCalendar">@property(nonatomic, copy) NSCalendar *repeatCalendar</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSCalendar getRepeatCalendar() {
        if (customClass) { return objc_getRepeatCalendarSuper(getSuper(), this, repeatCalendar); } else { return objc_getRepeatCalendar(this, repeatCalendar); }
    }
    
    private static final Selector setRepeatCalendar$ = Selector.register("setRepeatCalendar:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRepeatCalendar(UILocalNotification __self__, Selector __cmd__, NSCalendar repeatCalendar);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRepeatCalendarSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, NSCalendar repeatCalendar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatCalendar">@property(nonatomic, copy) NSCalendar *repeatCalendar</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setRepeatCalendar(NSCalendar repeatCalendar) {
        if (customClass) { objc_setRepeatCalendarSuper(getSuper(), this, setRepeatCalendar$, repeatCalendar); } else { objc_setRepeatCalendar(this, setRepeatCalendar$, repeatCalendar); }
    }
    
    private static final Selector repeatInterval = Selector.register("repeatInterval");
    @Bridge(symbol = "objc_msgSend") private native static NSCalendarUnit objc_getRepeatInterval(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSCalendarUnit objc_getRepeatIntervalSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatInterval">@property(nonatomic) NSCalendarUnit repeatInterval</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSCalendarUnit getRepeatInterval() {
        if (customClass) { return objc_getRepeatIntervalSuper(getSuper(), this, repeatInterval); } else { return objc_getRepeatInterval(this, repeatInterval); }
    }
    
    private static final Selector setRepeatInterval$ = Selector.register("setRepeatInterval:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRepeatInterval(UILocalNotification __self__, Selector __cmd__, NSCalendarUnit repeatInterval);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRepeatIntervalSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, NSCalendarUnit repeatInterval);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatInterval">@property(nonatomic) NSCalendarUnit repeatInterval</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setRepeatInterval(NSCalendarUnit repeatInterval) {
        if (customClass) { objc_setRepeatIntervalSuper(getSuper(), this, setRepeatInterval$, repeatInterval); } else { objc_setRepeatInterval(this, setRepeatInterval$, repeatInterval); }
    }
    
    private static final Selector soundName = Selector.register("soundName");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getSoundName(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getSoundNameSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/soundName">@property(nonatomic, copy) NSString *soundName</a>
     * @since Available in iOS 4.0 and later.
     */
    public String getSoundName() {
        if (customClass) { return objc_getSoundNameSuper(getSuper(), this, soundName); } else { return objc_getSoundName(this, soundName); }
    }
    
    private static final Selector setSoundName$ = Selector.register("setSoundName:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSoundName(UILocalNotification __self__, Selector __cmd__, String soundName);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSoundNameSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, String soundName);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/soundName">@property(nonatomic, copy) NSString *soundName</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setSoundName(String soundName) {
        if (customClass) { objc_setSoundNameSuper(getSuper(), this, setSoundName$, soundName); } else { objc_setSoundName(this, setSoundName$, soundName); }
    }
    
    private static final Selector timeZone = Selector.register("timeZone");
    @Bridge(symbol = "objc_msgSend") private native static NSTimeZone objc_getTimeZone(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSTimeZone objc_getTimeZoneSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/timeZone">@property(nonatomic, copy) NSTimeZone *timeZone</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSTimeZone getTimeZone() {
        if (customClass) { return objc_getTimeZoneSuper(getSuper(), this, timeZone); } else { return objc_getTimeZone(this, timeZone); }
    }
    
    private static final Selector setTimeZone$ = Selector.register("setTimeZone:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTimeZone(UILocalNotification __self__, Selector __cmd__, NSTimeZone timeZone);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTimeZoneSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, NSTimeZone timeZone);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/timeZone">@property(nonatomic, copy) NSTimeZone *timeZone</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setTimeZone(NSTimeZone timeZone) {
        if (customClass) { objc_setTimeZoneSuper(getSuper(), this, setTimeZone$, timeZone); } else { objc_setTimeZone(this, setTimeZone$, timeZone); }
    }
    
    private static final Selector userInfo = Selector.register("userInfo");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getUserInfo(UILocalNotification __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getUserInfoSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/userInfo">@property(nonatomic, copy) NSDictionary *userInfo</a>
     * @since Available in iOS 4.0 and later.
     */
    public NSDictionary getUserInfo() {
        if (customClass) { return objc_getUserInfoSuper(getSuper(), this, userInfo); } else { return objc_getUserInfo(this, userInfo); }
    }
    
    private static final Selector setUserInfo$ = Selector.register("setUserInfo:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setUserInfo(UILocalNotification __self__, Selector __cmd__, NSDictionary userInfo);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setUserInfoSuper(ObjCSuper __super__, UILocalNotification __self__, Selector __cmd__, NSDictionary userInfo);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/userInfo">@property(nonatomic, copy) NSDictionary *userInfo</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setUserInfo(NSDictionary userInfo) {
        if (customClass) { objc_setUserInfoSuper(getSuper(), this, setUserInfo$, userInfo); } else { objc_setUserInfo(this, setUserInfo$, userInfo); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
