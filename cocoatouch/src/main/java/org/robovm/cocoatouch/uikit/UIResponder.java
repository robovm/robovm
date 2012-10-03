/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIResponder /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIResponder /*</name>*/.class);
    }

    /*<constructors>*/
    public UIResponder() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    @Bind("inputView") public native @Type("UIView *") UIView getInputView();
    @Bind("undoManager") public native @Type("NSUndoManager *") NSUndoManager getUndoManager();
    /*</properties>*/
    /*<methods>*/
    @Bind("becomeFirstResponder") public native @Type("BOOL") boolean becomeFirstResponder();
    @Bind("canBecomeFirstResponder") public native @Type("BOOL") boolean canBecomeFirstResponder();
    @Bind("canPerformAction:withSender:") public native @Type("BOOL") boolean canPerformAction(@Type("SEL") Selector action, @Type("id") NSObject sender);
    @Bind("canResignFirstResponder") public native @Type("BOOL") boolean canResignFirstResponder();
    @Bind("nextResponder") public native @Type("UIResponder *") UIResponder getNextResponder();
    @Bind("isFirstResponder") public native @Type("BOOL") boolean isFirstResponder();
    @Bind("motionBegan:withEvent:") public native @Type("void") void motionBegan(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    @Bind("motionCancelled:withEvent:") public native @Type("void") void motionCancelled(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    @Bind("motionEnded:withEvent:") public native @Type("void") void motionEnded(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    @Bind("reloadInputViews") public native @Type("void") void reloadInputViews();
    @Bind("remoteControlReceivedWithEvent:") public native @Type("void") void remoteControlReceived(@Type("UIEvent *") UIEvent event);
    @Bind("resignFirstResponder") public native @Type("BOOL") boolean resignFirstResponder();
    @Bind("touchesBegan:withEvent:") public native @Type("void") void touchesBegan(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesCancelled:withEvent:") public native @Type("void") void touchesCancelled(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesEnded:withEvent:") public native @Type("void") void touchesEnded(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesMoved:withEvent:") public native @Type("void") void touchesMoved(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
