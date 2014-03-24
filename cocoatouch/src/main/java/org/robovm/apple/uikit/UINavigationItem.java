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

/**
 *
 * <div class="javadoc"></div>
 */
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
    public UINavigationItem(String title) { super((SkipInit) null); initObject(initWithTitle$(title)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "backBarButtonItem")
    public native UIBarButtonItem getBackBarButtonItem();
    @Property(selector = "setBackBarButtonItem:")
    public native void setBackBarButtonItem(UIBarButtonItem v);
    @Property(selector = "titleView")
    public native UIView getTitleView();
    @Property(selector = "setTitleView:")
    public native void setTitleView(UIView v);
    @Property(selector = "prompt")
    public native String getPrompt();
    @Property(selector = "setPrompt:")
    public native void setPrompt(String v);
    @Property(selector = "hidesBackButton")
    public native boolean isHidesBackButton();
    @Property(selector = "setHidesBackButton:")
    public native void setHidesBackButton(boolean v);
    @Property(selector = "leftBarButtonItems")
    public native NSArray<?> getLeftBarButtonItems();
    @Property(selector = "setLeftBarButtonItems:")
    public native void setLeftBarButtonItems(NSArray<?> v);
    @Property(selector = "rightBarButtonItems")
    public native NSArray<?> getRightBarButtonItems();
    @Property(selector = "setRightBarButtonItems:")
    public native void setRightBarButtonItems(NSArray<?> v);
    @Property(selector = "leftItemsSupplementBackButton")
    public native boolean isLeftItemsSupplementBackButton();
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
    protected native @Pointer long initWithTitle$(String title);
    @Method(selector = "setHidesBackButton:animated:")
    public native void setHidesBackButton$animated$(boolean hidesBackButton, boolean animated);
    @Method(selector = "setLeftBarButtonItems:animated:")
    public native void setLeftBarButtonItems$animated$(NSArray<?> items, boolean animated);
    @Method(selector = "setRightBarButtonItems:animated:")
    public native void setRightBarButtonItems$animated$(NSArray<?> items, boolean animated);
    @Method(selector = "setLeftBarButtonItem:animated:")
    public native void setLeftBarButtonItem$animated$(UIBarButtonItem item, boolean animated);
    @Method(selector = "setRightBarButtonItem:animated:")
    public native void setRightBarButtonItem$animated$(UIBarButtonItem item, boolean animated);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
