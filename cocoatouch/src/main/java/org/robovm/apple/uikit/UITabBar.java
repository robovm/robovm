/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
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
    
    public UITabBar(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "delegate")
    public native UITabBarDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UITabBarDelegate v);
    @Property(selector = "items")
    public native NSArray<UITabBarItem> getItems();
    @Property(selector = "setItems:")
    public native void setItems(NSArray<UITabBarItem> v);
    @Property(selector = "selectedItem")
    public native UITabBarItem getSelectedItem();
    @Property(selector = "setSelectedItem:", strongRef = true)
    public native void setSelectedItem(UITabBarItem v);
    @Property(selector = "tintColor")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setBarTintColor(UIColor v);
    @Property(selector = "selectedImageTintColor")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIColor getSelectedImageTintColor();
    @Property(selector = "setSelectedImageTintColor:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setSelectedImageTintColor(UIColor v);
    @Property(selector = "backgroundImage")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIImage getBackgroundImage();
    @Property(selector = "setBackgroundImage:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setBackgroundImage(UIImage v);
    @Property(selector = "selectionIndicatorImage")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIImage getSelectionIndicatorImage();
    @Property(selector = "setSelectionIndicatorImage:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setSelectionIndicatorImage(UIImage v);
    @Property(selector = "shadowImage")
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native UIImage getShadowImage();
    @Property(selector = "setShadowImage:")
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native void setShadowImage(UIImage v);
    @Property(selector = "itemPositioning")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UITabBarItemPositioning getItemPositioning();
    @Property(selector = "setItemPositioning:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setItemPositioning(UITabBarItemPositioning v);
    @Property(selector = "itemWidth")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native @MachineSizedFloat double getItemWidth();
    @Property(selector = "setItemWidth:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setItemWidth(@MachineSizedFloat double v);
    @Property(selector = "itemSpacing")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native @MachineSizedFloat double getItemSpacing();
    @Property(selector = "setItemSpacing:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setItemSpacing(@MachineSizedFloat double v);
    @Property(selector = "barStyle")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIBarStyle getBarStyle();
    @Property(selector = "setBarStyle:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setBarStyle(UIBarStyle v);
    @Property(selector = "isTranslucent")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setTranslucent(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setItems:animated:")
    public native void setItems(NSArray<UITabBarItem> items, boolean animated);
    @Method(selector = "beginCustomizingItems:")
    public native void beginCustomizing(NSArray<UITabBarItem> items);
    @Method(selector = "endCustomizingAnimated:")
    public native boolean endCustomizing(boolean animated);
    @Method(selector = "isCustomizing")
    public native boolean isCustomizing();
    /*</methods>*/
}
