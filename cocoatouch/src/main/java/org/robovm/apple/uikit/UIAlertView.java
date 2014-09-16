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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAlertView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIAlertViewPtr extends Ptr<UIAlertView, UIAlertViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIAlertView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIAlertView() {}
    protected UIAlertView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIAlertView(CGRect frame) {
        super(frame);
    }
    
    public UIAlertView(String title, String message, UIAlertViewDelegate delegate, String cancelButtonTitle, 
            String ... otherButtonTitles) {
        super((SkipInit) null);
        initObject(initWithTitle$message$delegate$cancelButtonTitle$otherButtonTitles$(title, message, delegate, cancelButtonTitle, 0));
        for (String otherButtonTitle : otherButtonTitles) {
            addButton(otherButtonTitle);
        }
        updateStrongRef(null, delegate);
    }
    
    @Method(selector = "initWithTitle:message:delegate:cancelButtonTitle:otherButtonTitles:")
    protected native @Pointer long initWithTitle$message$delegate$cancelButtonTitle$otherButtonTitles$(String title, String message, UIAlertViewDelegate delegate, String cancelButtonTitle, @Pointer long otherButtonTitles);
    
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIAlertViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIAlertViewDelegate v);
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "message")
    public native String getMessage();
    @Property(selector = "setMessage:")
    public native void setMessage(String v);
    @Property(selector = "numberOfButtons")
    public native @MachineSizedSInt long getNumberOfButtons();
    @Property(selector = "cancelButtonIndex")
    public native @MachineSizedSInt long getCancelButtonIndex();
    @Property(selector = "setCancelButtonIndex:")
    public native void setCancelButtonIndex(@MachineSizedSInt long v);
    @Property(selector = "firstOtherButtonIndex")
    public native @MachineSizedSInt long getFirstOtherButtonIndex();
    @Property(selector = "isVisible")
    public native boolean isVisible();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "alertViewStyle")
    public native UIAlertViewStyle getAlertViewStyle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setAlertViewStyle:")
    public native void setAlertViewStyle(UIAlertViewStyle v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addButtonWithTitle:")
    public native @MachineSizedSInt long addButton(String title);
    @Method(selector = "buttonTitleAtIndex:")
    public native String getButtonTitle(@MachineSizedSInt long buttonIndex);
    @Method(selector = "show")
    public native void show();
    @Method(selector = "dismissWithClickedButtonIndex:animated:")
    public native void dismiss(@MachineSizedSInt long buttonIndex, boolean animated);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "textFieldAtIndex:")
    public native UITextField getTextField(@MachineSizedSInt long textFieldIndex);
    /*</methods>*/
}
