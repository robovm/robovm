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
public class /*<name>*/ UILocalNotification /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILocalNotification /*</name>*/.class);
    }

    /*<constructors>*/
    public UILocalNotification() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("alertAction") public native @Type("NSString *") String getAlertAction();
    @Bind("setAlertAction:") public native void setAlertAction(@Type("NSString *") String v);
    @Bind("alertBody") public native @Type("NSString *") String getAlertBody();
    @Bind("setAlertBody:") public native void setAlertBody(@Type("NSString *") String v);
    @Bind("alertLaunchImage") public native @Type("NSString *") String getAlertLaunchImage();
    @Bind("setAlertLaunchImage:") public native void setAlertLaunchImage(@Type("NSString *") String v);
    @Bind("applicationIconBadgeNumber") public native @Type("NSInteger") int getApplicationIconBadgeNumber();
    @Bind("setApplicationIconBadgeNumber:") public native void setApplicationIconBadgeNumber(@Type("NSInteger") int v);
    @Bind("fireDate") public native @Type("NSDate *") NSDate getFireDate();
    @Bind("setFireDate:") public native void setFireDate(@Type("NSDate *") NSDate v);
    @Bind("hasAction") public native @Type("BOOL") boolean isHasAction();
    @Bind("setHasAction:") public native void setHasAction(@Type("BOOL") boolean v);
    @Bind("repeatCalendar") public native @Type("NSCalendar *") NSCalendar getRepeatCalendar();
    @Bind("setRepeatCalendar:") public native void setRepeatCalendar(@Type("NSCalendar *") NSCalendar v);
    @Bind("repeatInterval") public native @Type("NSCalendarUnit") NSCalendarUnit getRepeatInterval();
    @Bind("setRepeatInterval:") public native void setRepeatInterval(@Type("NSCalendarUnit") NSCalendarUnit v);
    @Bind("soundName") public native @Type("NSString *") String getSoundName();
    @Bind("setSoundName:") public native void setSoundName(@Type("NSString *") String v);
    @Bind("timeZone") public native @Type("NSTimeZone *") NSTimeZone getTimeZone();
    @Bind("setTimeZone:") public native void setTimeZone(@Type("NSTimeZone *") NSTimeZone v);
    @Bind("userInfo") public native @Type("NSDictionary *") NSDictionary getUserInfo();
    @Bind("setUserInfo:") public native void setUserInfo(@Type("NSDictionary *") NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
