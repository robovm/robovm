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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITabBarItem/*</name>*/ 
    extends /*<extends>*/UIBarItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UITabBarItemPtr extends Ptr<UITabBarItem, UITabBarItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITabBarItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITabBarItem() {}
    protected UITabBarItem(SkipInit skipInit) { super(skipInit); }
    public UITabBarItem(String title, UIImage image, @MachineSizedSInt long tag) { super((SkipInit) null); initObject(initWithTitle$image$tag$(title, image, tag)); }
    public UITabBarItem(String title, UIImage image, UIImage selectedImage) { super((SkipInit) null); initObject(initWithTitle$image$selectedImage$(title, image, selectedImage)); }
    public UITabBarItem(UITabBarSystemItem systemItem, @MachineSizedSInt long tag) { super((SkipInit) null); initObject(initWithTabBarSystemItem$tag$(systemItem, tag)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "selectedImage")
    public native UIImage getSelectedImage();
    @Property(selector = "setSelectedImage:")
    public native void setSelectedImage(UIImage v);
    @Property(selector = "badgeValue")
    public native String getBadgeValue();
    @Property(selector = "setBadgeValue:")
    public native void setBadgeValue(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTitle:image:tag:")
    protected native @Pointer long initWithTitle$image$tag$(String title, UIImage image, @MachineSizedSInt long tag);
    @Method(selector = "initWithTitle:image:selectedImage:")
    protected native @Pointer long initWithTitle$image$selectedImage$(String title, UIImage image, UIImage selectedImage);
    @Method(selector = "initWithTabBarSystemItem:tag:")
    protected native @Pointer long initWithTabBarSystemItem$tag$(UITabBarSystemItem systemItem, @MachineSizedSInt long tag);
    @Method(selector = "setFinishedSelectedImage:withFinishedUnselectedImage:")
    public native void setFinishedImages(UIImage selectedImage, UIImage unselectedImage);
    @Method(selector = "finishedSelectedImage")
    public native UIImage getFinishedSelectedImage();
    @Method(selector = "finishedUnselectedImage")
    public native UIImage getFinishedUnselectedImage();
    @Method(selector = "setTitlePositionAdjustment:")
    public native void setTitlePositionAdjustment$(@ByVal UIOffset adjustment);
    @Method(selector = "titlePositionAdjustment")
    public native @ByVal UIOffset getTitlePositionAdjustment();
    /*</methods>*/
}
