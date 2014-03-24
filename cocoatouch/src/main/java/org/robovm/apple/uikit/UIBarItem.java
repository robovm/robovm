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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIBarItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIBarItemPtr extends Ptr<UIBarItem, UIBarItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIBarItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIBarItem() {}
    protected UIBarItem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "image")
    public native UIImage getImage();
    @Property(selector = "setImage:")
    public native void setImage(UIImage v);
    @Property(selector = "landscapeImagePhone")
    public native UIImage getLandscapeImagePhone();
    @Property(selector = "setLandscapeImagePhone:")
    public native void setLandscapeImagePhone(UIImage v);
    @Property(selector = "imageInsets")
    public native @ByVal UIEdgeInsets getImageInsets();
    @Property(selector = "setImageInsets:")
    public native void setImageInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "landscapeImagePhoneInsets")
    public native @ByVal UIEdgeInsets getLandscapeImagePhoneInsets();
    @Property(selector = "setLandscapeImagePhoneInsets:")
    public native void setLandscapeImagePhoneInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "tag")
    public native @MachineSizedSInt long getTag();
    @Property(selector = "setTag:")
    public native void setTag(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setTitleTextAttributes:forState:")
    public native void setTitleTextAttributes(NSDictionary<?, ?> attributes, UIControlState state);
    @Method(selector = "titleTextAttributesForState:")
    public native NSDictionary<?, ?> getTitleTextAttributes(UIControlState state);
    /*</methods>*/
}
