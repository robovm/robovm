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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIWindow/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIWindowPtr extends Ptr<UIWindow, UIWindowPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIWindow.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIWindow() {}
    protected UIWindow(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIWindow(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "screen")
    public native UIScreen getScreen();
    @Property(selector = "setScreen:")
    public native void setScreen(UIScreen v);
    @Property(selector = "windowLevel")
    public native @MachineSizedFloat double getWindowLevel();
    @Property(selector = "setWindowLevel:")
    public native void setWindowLevel(@MachineSizedFloat double v);
    @Property(selector = "isKeyWindow")
    public native boolean isKeyWindow();
    @Property(selector = "rootViewController")
    public native UIViewController getRootViewController();
    @Property(selector = "setRootViewController:")
    public native void setRootViewController(UIViewController v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UIWindowLevelNormal", optional=true)
    public static native @MachineSizedFloat double WindowLevelNormal();
    @GlobalValue(symbol="UIWindowLevelAlert", optional=true)
    public static native @MachineSizedFloat double WindowLevelAlert();
    @GlobalValue(symbol="UIWindowLevelStatusBar", optional=true)
    public static native @MachineSizedFloat double WindowLevelStatusBar();
    @GlobalValue(symbol="UIWindowDidBecomeVisibleNotification", optional=true)
    public static native String NotificationWindowDidBecomeVisible();
    @GlobalValue(symbol="UIWindowDidBecomeHiddenNotification", optional=true)
    public static native String NotificationWindowDidBecomeHidden();
    @GlobalValue(symbol="UIWindowDidBecomeKeyNotification", optional=true)
    public static native String NotificationWindowDidBecomeKey();
    @GlobalValue(symbol="UIWindowDidResignKeyNotification", optional=true)
    public static native String NotificationWindowDidResignKey();
    @GlobalValue(symbol="UIKeyboardWillShowNotification", optional=true)
    public static native String NotificationKeyboardWillShow();
    @GlobalValue(symbol="UIKeyboardDidShowNotification", optional=true)
    public static native String NotificationKeyboardDidShow();
    @GlobalValue(symbol="UIKeyboardWillHideNotification", optional=true)
    public static native String NotificationKeyboardWillHide();
    @GlobalValue(symbol="UIKeyboardDidHideNotification", optional=true)
    public static native String NotificationKeyboardDidHide();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameBeginUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardFrameBegin();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIKeyboardFrameEndUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardFrameEnd();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationDurationUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardAnimationDuration();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardAnimationCurveUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardAnimationCurve();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardWillChangeFrameNotification", optional=true)
    public static native String NotificationKeyboardWillChangeFrame();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIKeyboardDidChangeFrameNotification", optional=true)
    public static native String NotificationKeyboardDidChangeFrame();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterBeginUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardCenterBegin();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardCenterEndUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardCenterEnd();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="UIKeyboardBoundsUserInfoKey", optional=true)
    public static native NSString UserInfoKeyKeyboardBounds();
    
    @Method(selector = "becomeKeyWindow")
    public native void becomeKeyWindow();
    @Method(selector = "resignKeyWindow")
    public native void resignKeyWindow();
    @Method(selector = "makeKeyWindow")
    public native void makeKeyWindow();
    @Method(selector = "makeKeyAndVisible")
    public native void makeKeyAndVisible();
    @Method(selector = "sendEvent:")
    public native void sendEvent(UIEvent event);
    @Method(selector = "convertPoint:toWindow:")
    public native @ByVal CGPoint convertPointToWindow(@ByVal CGPoint point, UIWindow window);
    @Method(selector = "convertPoint:fromWindow:")
    public native @ByVal CGPoint convertPointFromWindow(@ByVal CGPoint point, UIWindow window);
    @Method(selector = "convertRect:toWindow:")
    public native @ByVal CGRect convertRectToWindow(@ByVal CGRect rect, UIWindow window);
    @Method(selector = "convertRect:fromWindow:")
    public native @ByVal CGRect convertRectFromWindow(@ByVal CGRect rect, UIWindow window);
    /*</methods>*/
}
