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
public class /*<name>*/ UITabBarItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBarItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UITabBarItem() {}
    @Bind("initWithTabBarSystemItem:tag:") public UITabBarItem(@Type("UITabBarSystemItem") UITabBarSystemItem systemItem, @Type("NSInteger") int tag) {}
    @Bind("initWithTitle:image:tag:") public UITabBarItem(@Type("NSString *") String title, @Type("UIImage *") UIImage image, @Type("NSInteger") int tag) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("badgeValue") public native @Type("NSString *") String getBadgeValue();
    @Bind("setBadgeValue:") public native void setBadgeValue(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    @Bind("finishedSelectedImage") public native @Type("UIImage *") UIImage getFinishedSelectedImage();
    @Bind("finishedUnselectedImage") public native @Type("UIImage *") UIImage getFinishedUnselectedImage();
    @Bind("titlePositionAdjustment") public native @Type("UIOffset") UIOffset getTitlePositionAdjustment();
    @Bind("setFinishedSelectedImage:withFinishedUnselectedImage:") public native @Type("void") void setFinishedImages(@Type("UIImage *") UIImage selectedImage, @Type("UIImage *") UIImage unselectedImage);
    @Bind("setTitlePositionAdjustment:") public native @Type("void") void setTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment);
    /*</methods>*/

}
