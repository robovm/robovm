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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITabBar/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UITabBarPtr extends Ptr<UITabBar, UITabBarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITabBar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITabBar() {}
    protected UITabBar(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UITabBarDelegate getDelegate();
    @Property(selector = "setDelegate:")
    public native void setDelegate(UITabBarDelegate v);
    @Property(selector = "items")
    public native NSArray<?> getItems();
    @Property(selector = "setItems:")
    public native void setItems(NSArray<?> v);
    @Property(selector = "selectedItem")
    public native UITabBarItem getSelectedItem();
    @Property(selector = "setSelectedItem:")
    public native void setSelectedItem(UITabBarItem v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    public native void setBarTintColor(UIColor v);
    @Property(selector = "selectedImageTintColor")
    public native UIColor getSelectedImageTintColor();
    @Property(selector = "setSelectedImageTintColor:")
    public native void setSelectedImageTintColor(UIColor v);
    @Property(selector = "backgroundImage")
    public native UIImage getBackgroundImage();
    @Property(selector = "setBackgroundImage:")
    public native void setBackgroundImage(UIImage v);
    @Property(selector = "selectionIndicatorImage")
    public native UIImage getSelectionIndicatorImage();
    @Property(selector = "setSelectionIndicatorImage:")
    public native void setSelectionIndicatorImage(UIImage v);
    @Property(selector = "shadowImage")
    public native UIImage getShadowImage();
    @Property(selector = "setShadowImage:")
    public native void setShadowImage(UIImage v);
    @Property(selector = "itemPositioning")
    public native UITabBarItemPositioning getItemPositioning();
    @Property(selector = "setItemPositioning:")
    public native void setItemPositioning(UITabBarItemPositioning v);
    @Property(selector = "itemWidth")
    public native @MachineSizedFloat double getItemWidth();
    @Property(selector = "setItemWidth:")
    public native void setItemWidth(@MachineSizedFloat double v);
    @Property(selector = "itemSpacing")
    public native @MachineSizedFloat double getItemSpacing();
    @Property(selector = "setItemSpacing:")
    public native void setItemSpacing(@MachineSizedFloat double v);
    @Property(selector = "barStyle")
    public native UIBarStyle getBarStyle();
    @Property(selector = "setBarStyle:")
    public native void setBarStyle(UIBarStyle v);
    @Property(selector = "isTranslucent")
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    public native void setTranslucent(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setItems:animated:")
    public native void setItems$animated$(NSArray<?> items, boolean animated);
    @Method(selector = "beginCustomizingItems:")
    public native void beginCustomizing(NSArray<?> items);
    @Method(selector = "endCustomizingAnimated:")
    public native boolean endCustomizing(boolean animated);
    @Method(selector = "isCustomizing")
    public native boolean isCustomizing();
    /*</methods>*/
}
