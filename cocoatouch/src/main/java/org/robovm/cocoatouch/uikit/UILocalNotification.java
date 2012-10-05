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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertAction">@property(nonatomic, copy) NSString *alertAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("alertAction") public native String getAlertAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertAction">@property(nonatomic, copy) NSString *alertAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setAlertAction:") public native void setAlertAction(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertBody">@property(nonatomic, copy) NSString *alertBody</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("alertBody") public native String getAlertBody();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertBody">@property(nonatomic, copy) NSString *alertBody</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setAlertBody:") public native void setAlertBody(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertLaunchImage">@property(nonatomic,copy) NSString *alertLaunchImage</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("alertLaunchImage") public native String getAlertLaunchImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/alertLaunchImage">@property(nonatomic,copy) NSString *alertLaunchImage</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setAlertLaunchImage:") public native void setAlertLaunchImage(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("applicationIconBadgeNumber") public native int getApplicationIconBadgeNumber();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/applicationIconBadgeNumber">@property(nonatomic) NSInteger applicationIconBadgeNumber</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setApplicationIconBadgeNumber:") public native void setApplicationIconBadgeNumber(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/fireDate">@property(nonatomic, copy) NSDate *fireDate</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("fireDate") public native NSDate getFireDate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/fireDate">@property(nonatomic, copy) NSDate *fireDate</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setFireDate:") public native void setFireDate(NSDate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/hasAction">@property(nonatomic) BOOL hasAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("hasAction") public native boolean isHasAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/hasAction">@property(nonatomic) BOOL hasAction</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setHasAction:") public native void setHasAction(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatCalendar">@property(nonatomic, copy) NSCalendar *repeatCalendar</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("repeatCalendar") public native NSCalendar getRepeatCalendar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatCalendar">@property(nonatomic, copy) NSCalendar *repeatCalendar</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setRepeatCalendar:") public native void setRepeatCalendar(NSCalendar v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatInterval">@property(nonatomic) NSCalendarUnit repeatInterval</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("repeatInterval") public native NSCalendarUnit getRepeatInterval();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/repeatInterval">@property(nonatomic) NSCalendarUnit repeatInterval</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setRepeatInterval:") public native void setRepeatInterval(NSCalendarUnit v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/soundName">@property(nonatomic, copy) NSString *soundName</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("soundName") public native String getSoundName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/soundName">@property(nonatomic, copy) NSString *soundName</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setSoundName:") public native void setSoundName(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/timeZone">@property(nonatomic, copy) NSTimeZone *timeZone</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("timeZone") public native NSTimeZone getTimeZone();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/timeZone">@property(nonatomic, copy) NSTimeZone *timeZone</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setTimeZone:") public native void setTimeZone(NSTimeZone v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/userInfo">@property(nonatomic, copy) NSDictionary *userInfo</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("userInfo") public native NSDictionary getUserInfo();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UILocalNotification_Class/Reference/Reference.html#//apple_ref/occ/instp/UILocalNotification/userInfo">@property(nonatomic, copy) NSDictionary *userInfo</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setUserInfo:") public native void setUserInfo(NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
