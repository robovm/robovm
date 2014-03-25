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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActionSheet/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIActionSheetPtr extends Ptr<UIActionSheet, UIActionSheetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIActionSheet.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIActionSheet() {}
    protected UIActionSheet(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIActionSheet(CGRect frame) {
        super(frame);
    }

    /*<properties>*/
    @Property(selector = "delegate")
    public native UIActionSheetDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIActionSheetDelegate v);
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "actionSheetStyle")
    public native UIActionSheetStyle getActionSheetStyle();
    @Property(selector = "setActionSheetStyle:")
    public native void setActionSheetStyle(UIActionSheetStyle v);
    @Property(selector = "numberOfButtons")
    public native @MachineSizedSInt long getNumberOfButtons();
    @Property(selector = "cancelButtonIndex")
    public native @MachineSizedSInt long getCancelButtonIndex();
    @Property(selector = "setCancelButtonIndex:")
    public native void setCancelButtonIndex(@MachineSizedSInt long v);
    @Property(selector = "destructiveButtonIndex")
    public native @MachineSizedSInt long getDestructiveButtonIndex();
    @Property(selector = "setDestructiveButtonIndex:")
    public native void setDestructiveButtonIndex(@MachineSizedSInt long v);
    @Property(selector = "firstOtherButtonIndex")
    public native @MachineSizedSInt long getFirstOtherButtonIndex();
    @Property(selector = "isVisible")
    public native boolean isVisible();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addButtonWithTitle:")
    public native @MachineSizedSInt long addButton(String title);
    @Method(selector = "buttonTitleAtIndex:")
    public native String getButtonTitle(@MachineSizedSInt long buttonIndex);
    @Method(selector = "showFromToolbar:")
    public native void showFrom(UIToolbar view);
    @Method(selector = "showFromTabBar:")
    public native void showFrom(UITabBar view);
    @Method(selector = "showFromBarButtonItem:animated:")
    public native void showFrom(UIBarButtonItem item, boolean animated);
    @Method(selector = "showFromRect:inView:animated:")
    public native void showFrom(@ByVal CGRect rect, UIView view, boolean animated);
    @Method(selector = "showInView:")
    public native void showInView$(UIView view);
    @Method(selector = "dismissWithClickedButtonIndex:animated:")
    public native void dismiss(@MachineSizedSInt long buttonIndex, boolean animated);
    /*</methods>*/
}
