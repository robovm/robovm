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
import org.robovm.rt.annotation.*;
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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UINavigationItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UINavigationItemPtr extends Ptr<UINavigationItem, UINavigationItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UINavigationItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UINavigationItem() {}
    protected UINavigationItem(SkipInit skipInit) { super(skipInit); }
    public UINavigationItem(String title) { super((SkipInit) null); initObject(init(title)); }
    public UINavigationItem(NSCoder coder) { super((SkipInit) null); initObject(init(coder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "titleView")
    public native UIView getTitleView();
    @Property(selector = "setTitleView:")
    public native void setTitleView(UIView v);
    @Property(selector = "prompt")
    public native String getPrompt();
    @Property(selector = "setPrompt:")
    public native void setPrompt(String v);
    @Property(selector = "backBarButtonItem")
    public native UIBarButtonItem getBackBarButtonItem();
    @Property(selector = "setBackBarButtonItem:")
    public native void setBackBarButtonItem(UIBarButtonItem v);
    @Property(selector = "hidesBackButton")
    public native boolean hidesBackButton();
    @Property(selector = "setHidesBackButton:")
    public native void setHidesBackButton(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "leftBarButtonItems")
    public native NSArray<UIBarButtonItem> getLeftBarButtonItems();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setLeftBarButtonItems:")
    public native void setLeftBarButtonItems(NSArray<UIBarButtonItem> v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "rightBarButtonItems")
    public native NSArray<UIBarButtonItem> getRightBarButtonItems();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setRightBarButtonItems:")
    public native void setRightBarButtonItems(NSArray<UIBarButtonItem> v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "leftItemsSupplementBackButton")
    public native boolean leftItemsSupplementBackButton();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setLeftItemsSupplementBackButton:")
    public native void setLeftItemsSupplementBackButton(boolean v);
    @Property(selector = "leftBarButtonItem")
    public native UIBarButtonItem getLeftBarButtonItem();
    @Property(selector = "setLeftBarButtonItem:")
    public native void setLeftBarButtonItem(UIBarButtonItem v);
    @Property(selector = "rightBarButtonItem")
    public native UIBarButtonItem getRightBarButtonItem();
    @Property(selector = "setRightBarButtonItem:")
    public native void setRightBarButtonItem(UIBarButtonItem v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTitle:")
    protected native @Pointer long init(String title);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder coder);
    @Method(selector = "setHidesBackButton:animated:")
    public native void setHidesBackButton(boolean hidesBackButton, boolean animated);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setLeftBarButtonItems:animated:")
    public native void setLeftBarButtonItems(NSArray<UIBarButtonItem> items, boolean animated);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setRightBarButtonItems:animated:")
    public native void setRightBarButtonItems(NSArray<UIBarButtonItem> items, boolean animated);
    @Method(selector = "setLeftBarButtonItem:animated:")
    public native void setLeftBarButtonItem(UIBarButtonItem item, boolean animated);
    @Method(selector = "setRightBarButtonItem:animated:")
    public native void setRightBarButtonItem(UIBarButtonItem item, boolean animated);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder coder);
    /*</methods>*/
}
