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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UILocalNotification/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UILocalNotificationPtr extends Ptr<UILocalNotification, UILocalNotificationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UILocalNotification.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UILocalNotification() {}
    protected UILocalNotification(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "fireDate")
    public native NSDate getFireDate();
    @Property(selector = "setFireDate:")
    public native void setFireDate(NSDate v);
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "repeatInterval")
    public native NSCalendarUnit getRepeatInterval();
    @Property(selector = "setRepeatInterval:")
    public native void setRepeatInterval(NSCalendarUnit v);
    @Property(selector = "repeatCalendar")
    public native NSCalendar getRepeatCalendar();
    @Property(selector = "setRepeatCalendar:")
    public native void setRepeatCalendar(NSCalendar v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "region")
    public native CLRegion getRegion();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setRegion:")
    public native void setRegion(CLRegion v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "regionTriggersOnce")
    public native boolean regionTriggersOnce();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setRegionTriggersOnce:")
    public native void setRegionTriggersOnce(boolean v);
    @Property(selector = "alertBody")
    public native String getAlertBody();
    @Property(selector = "setAlertBody:")
    public native void setAlertBody(String v);
    @Property(selector = "hasAction")
    public native boolean hasAction();
    @Property(selector = "setHasAction:")
    public native void setHasAction(boolean v);
    @Property(selector = "alertAction")
    public native String getAlertAction();
    @Property(selector = "setAlertAction:")
    public native void setAlertAction(String v);
    @Property(selector = "alertLaunchImage")
    public native String getAlertLaunchImage();
    @Property(selector = "setAlertLaunchImage:")
    public native void setAlertLaunchImage(String v);
    /**
     * @since Available in iOS 8.2 and later.
     */
    @Property(selector = "alertTitle")
    public native String getAlertTitle();
    /**
     * @since Available in iOS 8.2 and later.
     */
    @Property(selector = "setAlertTitle:")
    public native void setAlertTitle(String v);
    @Property(selector = "soundName")
    public native String getSoundName();
    @Property(selector = "setSoundName:")
    public native void setSoundName(String v);
    @Property(selector = "applicationIconBadgeNumber")
    public native @MachineSizedSInt long getApplicationIconBadgeNumber();
    @Property(selector = "setApplicationIconBadgeNumber:")
    public native void setApplicationIconBadgeNumber(@MachineSizedSInt long v);
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "category")
    public native String getCategory();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCategory:")
    public native void setCategory(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UILocalNotificationDefaultSoundName", optional=true)
    public static native String getDefaultSoundName();
    
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
