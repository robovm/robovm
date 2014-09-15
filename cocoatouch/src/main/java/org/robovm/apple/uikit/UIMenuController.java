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
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIMenuController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeWillShowMenu(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillShowMenuNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeDidShowMenu(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidShowMenuNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeWillHideMenu(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillHideMenuNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeDidHideMenu(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidHideMenuNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
        public static NSObject observeMenuFrameDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(MenuFrameDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
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
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "arrowDirection")
    public native UIMenuControllerArrowDirection getArrowDirection();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setArrowDirection:")
    public native void setArrowDirection(UIMenuControllerArrowDirection v);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "menuItems")
    public native NSArray<UIMenuItem> getMenuItems();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setMenuItems:")
    public native void setMenuItems(NSArray<UIMenuItem> v);
    @Property(selector = "menuFrame")
    public native @ByVal CGRect getMenuFrame();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UIMenuControllerWillShowMenuNotification", optional=true)
    public static native NSString WillShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidShowMenuNotification", optional=true)
    public static native NSString DidShowMenuNotification();
    @GlobalValue(symbol="UIMenuControllerWillHideMenuNotification", optional=true)
    public static native NSString WillHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerDidHideMenuNotification", optional=true)
    public static native NSString DidHideMenuNotification();
    @GlobalValue(symbol="UIMenuControllerMenuFrameDidChangeNotification", optional=true)
    public static native NSString MenuFrameDidChangeNotification();
    
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
