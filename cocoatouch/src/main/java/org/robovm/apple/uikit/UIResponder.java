/*
 * Copyright (C) 2014 Trillian AB
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
    @Property(selector = "undoManager")
    public native NSUndoManager getUndoManager();
    @Property(selector = "keyCommands")
    public native NSArray<?> getKeyCommands();
    @Property(selector = "inputView")
    public native UIView getInputView();
    @Property(selector = "inputAccessoryView")
    public native UIView getInputAccessoryView();
    @Property(selector = "textInputMode")
    public native UITextInputMode getTextInputMode();
    @Property(selector = "textInputContextIdentifier")
    public native String getTextInputContextIdentifier();
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
    public native void touchesBegan$withEvent$(NSSet<?> touches, UIEvent event);
    @Method(selector = "touchesMoved:withEvent:")
    public native void touchesMoved$withEvent$(NSSet<?> touches, UIEvent event);
    @Method(selector = "touchesEnded:withEvent:")
    public native void touchesEnded$withEvent$(NSSet<?> touches, UIEvent event);
    @Method(selector = "touchesCancelled:withEvent:")
    public native void touchesCancelled$withEvent$(NSSet<?> touches, UIEvent event);
    @Method(selector = "motionBegan:withEvent:")
    public native void motionBegan$withEvent$(UIEventSubtype motion, UIEvent event);
    @Method(selector = "motionEnded:withEvent:")
    public native void motionEnded$withEvent$(UIEventSubtype motion, UIEvent event);
    @Method(selector = "motionCancelled:withEvent:")
    public native void motionCancelled$withEvent$(UIEventSubtype motion, UIEvent event);
    @Method(selector = "remoteControlReceivedWithEvent:")
    public native void remoteControlReceived(UIEvent event);
    @Method(selector = "canPerformAction:withSender:")
    public native boolean canPerformAction$withSender$(Selector action, NSObject sender);
    @Method(selector = "targetForAction:withSender:")
    public native NSObject targetForAction$withSender$(Selector action, NSObject sender);
    @Method(selector = "reloadInputViews")
    public native void reloadInputViews();
    @Method(selector = "clearTextInputContextIdentifier:")
    public static native void clearTextInputContextIdentifier$(String identifier);
    /*</methods>*/
}
