/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UINavigationBar/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIBarPositioning/*</implements>*/ {

    /*<ptr>*/public static class UINavigationBarPtr extends Ptr<UINavigationBar, UINavigationBarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UINavigationBar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UINavigationBar() {}
    protected UINavigationBar(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "barStyle")
    public native UIBarStyle getBarStyle();
    @Property(selector = "setBarStyle:")
    public native void setBarStyle(UIBarStyle v);
    @Property(selector = "delegate")
    public native NSObject getDelegate();
    @Property(selector = "setDelegate:")
    public native void setDelegate(NSObject v);
    @Property(selector = "isTranslucent")
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    public native void setTranslucent(boolean v);
    @Property(selector = "topItem")
    public native UINavigationItem getTopItem();
    @Property(selector = "backItem")
    public native UINavigationItem getBackItem();
    @Property(selector = "items")
    public native NSArray<?> getItems();
    @Property(selector = "setItems:")
    public native void setItems(NSArray<?> v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    public native void setBarTintColor(UIColor v);
    @Property(selector = "shadowImage")
    public native UIImage getShadowImage();
    @Property(selector = "setShadowImage:")
    public native void setShadowImage(UIImage v);
    @Property(selector = "titleTextAttributes")
    public native NSDictionary<?, ?> getTitleTextAttributes();
    @Property(selector = "setTitleTextAttributes:")
    public native void setTitleTextAttributes(NSDictionary<?, ?> v);
    @Property(selector = "backIndicatorImage")
    public native UIImage getBackIndicatorImage();
    @Property(selector = "setBackIndicatorImage:")
    public native void setBackIndicatorImage(UIImage v);
    @Property(selector = "backIndicatorTransitionMaskImage")
    public native UIImage getBackIndicatorTransitionMaskImage();
    @Property(selector = "setBackIndicatorTransitionMaskImage:")
    public native void setBackIndicatorTransitionMaskImage(UIImage v);
    @Property(selector = "barPosition")
    public native UIBarPosition getBarPosition();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "pushNavigationItem:animated:")
    public native void pushNavigationItem$animated$(UINavigationItem item, boolean animated);
    @Method(selector = "popNavigationItemAnimated:")
    public native UINavigationItem popNavigationItem(boolean animated);
    @Method(selector = "setItems:animated:")
    public native void setItems$animated$(NSArray<?> items, boolean animated);
    @Method(selector = "setBackgroundImage:forBarPosition:barMetrics:")
    public native void setBackgroundImage$forBarPosition$barMetrics$(UIImage backgroundImage, UIBarPosition barPosition, UIBarMetrics barMetrics);
    @Method(selector = "backgroundImageForBarPosition:barMetrics:")
    public native UIImage backgroundImageForBarPosition$barMetrics$(UIBarPosition barPosition, UIBarMetrics barMetrics);
    @Method(selector = "setBackgroundImage:forBarMetrics:")
    public native void setBackgroundImage$forBarMetrics$(UIImage backgroundImage, UIBarMetrics barMetrics);
    @Method(selector = "backgroundImageForBarMetrics:")
    public native UIImage getBackgroundImage(UIBarMetrics barMetrics);
    @Method(selector = "setTitleVerticalPositionAdjustment:forBarMetrics:")
    public native void setTitleVerticalPositionAdjustment$forBarMetrics$(@MachineSizedFloat double adjustment, UIBarMetrics barMetrics);
    @Method(selector = "titleVerticalPositionAdjustmentForBarMetrics:")
    public native @MachineSizedFloat double getTitleVerticalPositionAdjustment(UIBarMetrics barMetrics);
    /*</methods>*/
}
