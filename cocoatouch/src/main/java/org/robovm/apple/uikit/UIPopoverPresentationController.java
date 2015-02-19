/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPopoverPresentationController/*</name>*/ 
    extends /*<extends>*/UIPresentationController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPopoverPresentationControllerPtr extends Ptr<UIPopoverPresentationController, UIPopoverPresentationControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPopoverPresentationController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPopoverPresentationController() {}
    protected UIPopoverPresentationController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIPopoverPresentationControllerDelegate getPopoverPresentationDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setPopoverPresentationDelegate(UIPopoverPresentationControllerDelegate v);
    @Property(selector = "permittedArrowDirections")
    public native UIPopoverArrowDirection getPermittedArrowDirections();
    @Property(selector = "setPermittedArrowDirections:")
    public native void setPermittedArrowDirections(UIPopoverArrowDirection v);
    @Property(selector = "sourceView")
    public native UIView getSourceView();
    @Property(selector = "setSourceView:")
    public native void setSourceView(UIView v);
    @Property(selector = "sourceRect")
    public native @ByVal CGRect getSourceRect();
    @Property(selector = "setSourceRect:")
    public native void setSourceRect(@ByVal CGRect v);
    @Property(selector = "barButtonItem")
    public native UIBarButtonItem getBarButtonItem();
    @Property(selector = "setBarButtonItem:")
    public native void setBarButtonItem(UIBarButtonItem v);
    @Property(selector = "arrowDirection")
    public native UIPopoverArrowDirection getArrowDirection();
    @Property(selector = "passthroughViews")
    public native NSArray<UIView> getPassthroughViews();
    @Property(selector = "setPassthroughViews:")
    public native void setPassthroughViews(NSArray<UIView> v);
    @Property(selector = "backgroundColor")
    public native UIColor getBackgroundColor();
    @Property(selector = "setBackgroundColor:")
    public native void setBackgroundColor(UIColor v);
    @Property(selector = "popoverLayoutMargins")
    public native @ByVal UIEdgeInsets getPopoverLayoutMargins();
    @Property(selector = "setPopoverLayoutMargins:")
    public native void setPopoverLayoutMargins(@ByVal UIEdgeInsets v);
    @Property(selector = "popoverBackgroundViewClass")
    public native Class<?> getPopoverBackgroundViewClass();
    @Property(selector = "setPopoverBackgroundViewClass:")
    public native void setPopoverBackgroundViewClass(Class<?> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
