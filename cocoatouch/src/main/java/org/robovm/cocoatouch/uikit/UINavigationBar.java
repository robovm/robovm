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
public class /*<name>*/ UINavigationBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationBar /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("backItem") public native @Type("UINavigationItem *") UINavigationItem getBackItem();
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    @Bind("delegate") public native @Type("id") NSObject getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id") NSObject v);
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    @Bind("shadowImage") public native @Type("UIImage *") UIImage getShadowImage();
    @Bind("setShadowImage:") public native void setShadowImage(@Type("UIImage *") UIImage v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("titleTextAttributes") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes();
    @Bind("setTitleTextAttributes:") public native void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary v);
    @Bind("topItem") public native @Type("UINavigationItem *") UINavigationItem getTopItem();
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("backgroundImageForBarMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("titleVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getTitleVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("popNavigationItemAnimated:") public native @Type("UINavigationItem *") UINavigationItem popNavigationItem(@Type("BOOL") boolean animated);
    @Bind("pushNavigationItem:animated:") public native @Type("void") void pushNavigationItem(@Type("UINavigationItem *") UINavigationItem item, @Type("BOOL") boolean animated);
    @Bind("setBackgroundImage:forBarMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    @Bind("setTitleVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setTitleVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /*</methods>*/

}
