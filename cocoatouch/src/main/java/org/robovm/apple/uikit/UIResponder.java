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
    /*<implements>*//*</implements>*/ {

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
