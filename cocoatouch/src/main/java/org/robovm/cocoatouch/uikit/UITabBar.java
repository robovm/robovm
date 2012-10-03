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
public class /*<name>*/ UITabBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBar /*</name>*/.class);
    }

    /*<constructors>*/
    public UITabBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("backgroundImage") public native @Type("UIImage *") UIImage getBackgroundImage();
    @Bind("setBackgroundImage:") public native void setBackgroundImage(@Type("UIImage *") UIImage v);
    @Bind("delegate") public native @Type("id<UITabBarDelegate>") UITabBarDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITabBarDelegate>") UITabBarDelegate v);
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    @Bind("selectedImageTintColor") public native @Type("UIColor *") UIColor getSelectedImageTintColor();
    @Bind("setSelectedImageTintColor:") public native void setSelectedImageTintColor(@Type("UIColor *") UIColor v);
    @Bind("selectedItem") public native @Type("UITabBarItem *") UITabBarItem getSelectedItem();
    @Bind("setSelectedItem:") public native void setSelectedItem(@Type("UITabBarItem *") UITabBarItem v);
    @Bind("selectionIndicatorImage") public native @Type("UIImage *") UIImage getSelectionIndicatorImage();
    @Bind("setSelectionIndicatorImage:") public native void setSelectionIndicatorImage(@Type("UIImage *") UIImage v);
    @Bind("shadowImage") public native @Type("UIImage *") UIImage getShadowImage();
    @Bind("setShadowImage:") public native void setShadowImage(@Type("UIImage *") UIImage v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    @Bind("beginCustomizingItems:") public native @Type("void") void beginCustomizing(@Type("NSArray *") NSArray items);
    @Bind("endCustomizingAnimated:") public native @Type("BOOL") boolean endCustomizing(@Type("BOOL") boolean animated);
    @Bind("isCustomizing") public native @Type("BOOL") boolean isCustomizing();
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /*</methods>*/

}
