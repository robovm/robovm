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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIToolbar/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements UIBarPositioning/*</implements>*/ {

    /*<ptr>*/public static class UIToolbarPtr extends Ptr<UIToolbar, UIToolbarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIToolbar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIToolbar() {}
    protected UIToolbar(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIToolbar(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "barStyle")
    public native UIBarStyle getBarStyle();
    @Property(selector = "setBarStyle:")
    public native void setBarStyle(UIBarStyle v);
    @Property(selector = "items")
    public native NSArray<?> getItems();
    @Property(selector = "setItems:")
    public native void setItems(NSArray<?> v);
    @Property(selector = "isTranslucent")
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    public native void setTranslucent(boolean v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    public native void setBarTintColor(UIColor v);
    @Property(selector = "delegate")
    public native UIToolbarDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIToolbarDelegate v);
    @Property(selector = "barPosition")
    public native UIBarPosition getBarPosition();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setItems:animated:")
    public native void setItems$animated$(NSArray<?> items, boolean animated);
    @Method(selector = "setBackgroundImage:forToolbarPosition:barMetrics:")
    public native void setBackgroundImage$forToolbarPosition$barMetrics$(UIImage backgroundImage, UIBarPosition topOrBottom, UIBarMetrics barMetrics);
    @Method(selector = "backgroundImageForToolbarPosition:barMetrics:")
    public native UIImage getBackgroundImage(UIBarPosition topOrBottom, UIBarMetrics barMetrics);
    @Method(selector = "setShadowImage:forToolbarPosition:")
    public native void setShadowImage$forToolbarPosition$(UIImage shadowImage, UIBarPosition topOrBottom);
    @Method(selector = "shadowImageForToolbarPosition:")
    public native UIImage getShadowImage(UIBarPosition topOrBottom);
    /*</methods>*/
}
