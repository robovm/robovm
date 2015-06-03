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
    
    private static final long UI_MENU_CONTROLLER = ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSObject"));
    private static final java.lang.reflect.Method cbForwarder;
    
    static {
        try {
            cbForwarder = UIMenuController.class.getDeclaredMethod("cbForwarder", long.class, long.class, UIMenuController.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    
    @Callback
    private static void cbForwarder(@Pointer long self, @Pointer long sel, UIMenuController menuController) {
        UIMenuItem item = UIMenuItem.items.get(sel);
        if (item != null && item.getActionListener() != null) {
            item.getActionListener().onAction(menuController, item);
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
    private native NSArray<UIMenuItem> getMenuItems0();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setMenuItems:")
    private native void setMenuItems0(NSArray<UIMenuItem> v);
    @Property(selector = "menuFrame")
    public native @ByVal CGRect getMenuFrame();
    /*</properties>*/
    /*<members>*//*</members>*/
    public NSArray<UIMenuItem> getMenuItems() {
        return getMenuItems0();
    }
    public void setMenuItems(NSArray<UIMenuItem> items) {
        updateMenu(items);
        setMenuItems0(items);
    }
    
    private void updateMenu(NSArray<UIMenuItem> items) {
        long imp = VM.getCallbackMethodImpl(cbForwarder);
        long typeEncodings = VM.getStringUTFChars("v@:@");
        
        for (UIMenuItem item : items) {
            Selector sel = item.getAction();
            ObjCRuntime.class_addMethod(UI_MENU_CONTROLLER, sel.getHandle(), imp, typeEncodings);
        }
    }
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
