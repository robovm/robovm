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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIResponder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAccessibility/*</implements>*/ {

    /*<ptr>*/public static class UIResponderPtr extends Ptr<UIResponder, UIResponderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIResponder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIResponder() {}
    protected UIResponder(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "undoManager")
    public native NSUndoManager getUndoManager();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "keyCommands")
    public native NSArray<UIKeyCommand> getKeyCommands();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "inputView")
    public native UIView getInputView();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "inputAccessoryView")
    public native UIView getInputAccessoryView();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "inputViewController")
    public native UIInputViewController getInputViewController();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "inputAccessoryViewController")
    public native UIInputViewController getInputAccessoryViewController();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "textInputMode")
    public native UITextInputMode getTextInputMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "textInputContextIdentifier")
    public native String getTextInputContextIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "userActivity")
    public native NSUserActivity getUserActivity();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setUserActivity:")
    public native void setUserActivity(NSUserActivity v);
    @Property(selector = "isAccessibilityElement")
    public native boolean isAccessibilityElement();
    @Property(selector = "setIsAccessibilityElement:")
    public native void setAccessibilityElement(boolean v);
    @Property(selector = "accessibilityLabel")
    public native String getAccessibilityLabel();
    @Property(selector = "setAccessibilityLabel:")
    public native void setAccessibilityLabel(String v);
    @Property(selector = "accessibilityHint")
    public native String getAccessibilityHint();
    @Property(selector = "setAccessibilityHint:")
    public native void setAccessibilityHint(String v);
    @Property(selector = "accessibilityValue")
    public native String getAccessibilityValue();
    @Property(selector = "setAccessibilityValue:")
    public native void setAccessibilityValue(String v);
    @Property(selector = "accessibilityTraits")
    public native UIAccessibilityTraits getAccessibilityTraits();
    @Property(selector = "setAccessibilityTraits:")
    public native void setAccessibilityTraits(UIAccessibilityTraits v);
    @Property(selector = "accessibilityFrame")
    public native @ByVal CGRect getAccessibilityFrame();
    @Property(selector = "setAccessibilityFrame:")
    public native void setAccessibilityFrame(@ByVal CGRect v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "accessibilityPath")
    public native UIBezierPath getAccessibilityPath();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAccessibilityPath:")
    public native void setAccessibilityPath(UIBezierPath v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityActivationPoint")
    public native @ByVal CGPoint getAccessibilityActivationPoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityActivationPoint:")
    public native void setAccessibilityActivationPoint(@ByVal CGPoint v);
    @Property(selector = "accessibilityLanguage")
    public native String getAccessibilityLanguage();
    @Property(selector = "setAccessibilityLanguage:")
    public native void setAccessibilityLanguage(String v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityElementsHidden")
    public native boolean areAccessibilityElementsHidden();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityElementsHidden:")
    public native void setAccessibilityElementsHidden(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "accessibilityViewIsModal")
    public native boolean isAccessibilityViewModal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAccessibilityViewIsModal:")
    public native void setAccessibilityViewModal(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "shouldGroupAccessibilityChildren")
    public native boolean shouldGroupAccessibilityChildren();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setShouldGroupAccessibilityChildren:")
    public native void setShouldGroupAccessibilityChildren(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "accessibilityNavigationStyle")
    public native UIAccessibilityNavigationStyle getAccessibilityNavigationStyle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setAccessibilityNavigationStyle:")
    public native void setAccessibilityNavigationStyle(UIAccessibilityNavigationStyle v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "nextResponder")
    public native UIResponder getNextResponder();
    @Method(selector = "canBecomeFirstResponder")
    public native boolean canBecomeFirstResponder();
    @Method(selector = "becomeFirstResponder")
    public native boolean becomeFirstResponder();
    @Method(selector = "canResignFirstResponder")
    public native boolean canResignFirstResponder();
    @Method(selector = "resignFirstResponder")
    public native boolean resignFirstResponder();
    @Method(selector = "isFirstResponder")
    public native boolean isFirstResponder();
    @Method(selector = "touchesBegan:withEvent:")
    public native void touchesBegan(NSSet<UITouch> touches, UIEvent event);
    @Method(selector = "touchesMoved:withEvent:")
    public native void touchesMoved(NSSet<UITouch> touches, UIEvent event);
    @Method(selector = "touchesEnded:withEvent:")
    public native void touchesEnded(NSSet<UITouch> touches, UIEvent event);
    @Method(selector = "touchesCancelled:withEvent:")
    public native void touchesCancelled(NSSet<UITouch> touches, UIEvent event);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "motionBegan:withEvent:")
    public native void motionBegan(UIEventSubtype motion, UIEvent event);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "motionEnded:withEvent:")
    public native void motionEnded(UIEventSubtype motion, UIEvent event);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "motionCancelled:withEvent:")
    public native void motionCancelled(UIEventSubtype motion, UIEvent event);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "remoteControlReceivedWithEvent:")
    public native void remoteControlReceived(UIEvent event);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "canPerformAction:withSender:")
    public native boolean canPerformAction(Selector action, NSObject sender);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "targetForAction:withSender:")
    public native NSObject getActionTarget(Selector action, NSObject sender);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "reloadInputViews")
    public native void reloadInputViews();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "clearTextInputContextIdentifier:")
    public static native void clearTextInputContextIdentifier(String identifier);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "updateUserActivityState:")
    public native void updateUserActivityState(NSUserActivity activity);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "restoreUserActivityState:")
    public native void restoreUserActivityState(NSUserActivity activity);
    /*</methods>*/
}
