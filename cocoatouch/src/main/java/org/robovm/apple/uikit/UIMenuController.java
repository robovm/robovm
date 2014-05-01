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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIMenuController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIMenuControllerPtr extends Ptr<UIMenuController, UIMenuControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIMenuController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIMenuController() {}
    protected UIMenuController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isMenuVisible")
    public native boolean isMenuVisible();
    @Property(selector = "setMenuVisible:")
    public native void setMenuVisible(boolean v);
    @Property(selector = "arrowDirection")
    public native UIMenuControllerArrowDirection getArrowDirection();
    @Property(selector = "setArrowDirection:")
    public native void setArrowDirection(UIMenuControllerArrowDirection v);
    @Property(selector = "menuItems")
    public native NSArray<UIMenuItem> getMenuItems();
    @Property(selector = "setMenuItems:")
    public native void setMenuItems(NSArray<UIMenuItem> v);
    @Property(selector = "menuFrame")
    public native @ByVal CGRect getMenuFrame();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UIMenuControllerWillShowMenuNotification", optional=true)
    public static native String NotificationMenuControllerWillShowMenu();
    @GlobalValue(symbol="UIMenuControllerDidShowMenuNotification", optional=true)
    public static native String NotificationMenuControllerDidShowMenu();
    @GlobalValue(symbol="UIMenuControllerWillHideMenuNotification", optional=true)
    public static native String NotificationMenuControllerWillHideMenu();
    @GlobalValue(symbol="UIMenuControllerDidHideMenuNotification", optional=true)
    public static native String NotificationMenuControllerDidHideMenu();
    @GlobalValue(symbol="UIMenuControllerMenuFrameDidChangeNotification", optional=true)
    public static native String NotificationMenuControllerMenuFrameDidChange();
    
    @Method(selector = "setMenuVisible:animated:")
    public native void setMenuVisible(boolean menuVisible, boolean animated);
    @Method(selector = "setTargetRect:inView:")
    public native void setTargetRect(@ByVal CGRect targetRect, UIView targetView);
    @Method(selector = "update")
    public native void update();
    @Method(selector = "sharedMenuController")
    public static native UIMenuController getSharedMenuController();
    /*</methods>*/
}
