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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPresentationController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAppearanceContainer, UITraitEnvironment/*</implements>*/ {

    /*<ptr>*/public static class UIPresentationControllerPtr extends Ptr<UIPresentationController, UIPresentationControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPresentationController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPresentationController() {}
    protected UIPresentationController(SkipInit skipInit) { super(skipInit); }
    public UIPresentationController(UIViewController presentedViewController, UIViewController presentingViewController) { super((SkipInit) null); initObject(init(presentedViewController, presentingViewController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "presentingViewController")
    public native UIViewController getPresentingViewController();
    @Property(selector = "presentedViewController")
    public native UIViewController getPresentedViewController();
    @Property(selector = "presentationStyle")
    public native UIModalPresentationStyle getPresentationStyle();
    @Property(selector = "containerView")
    public native UIView getContainerView();
    @Property(selector = "delegate")
    public native UIAdaptivePresentationControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIAdaptivePresentationControllerDelegate v);
    @Property(selector = "overrideTraitCollection")
    public native UITraitCollection getOverrideTraitCollection();
    @Property(selector = "setOverrideTraitCollection:")
    public native void setOverrideTraitCollection(UITraitCollection v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "traitCollection")
    public native UITraitCollection getTraitCollection();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPresentedViewController:presentingViewController:")
    protected native @Pointer long init(UIViewController presentedViewController, UIViewController presentingViewController);
    @Method(selector = "adaptivePresentationStyle")
    public native UIModalPresentationStyle getAdaptivePresentationStyle();
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Method(selector = "adaptivePresentationStyleForTraitCollection:")
    public native UIModalPresentationStyle getAdaptivePresentationStyleForTraitCollection(UITraitCollection traitCollection);
    @Method(selector = "containerViewWillLayoutSubviews")
    public native void containerViewWillLayoutSubviews();
    @Method(selector = "containerViewDidLayoutSubviews")
    public native void containerViewDidLayoutSubviews();
    @Method(selector = "presentedView")
    public native UIView getPresentedView();
    @Method(selector = "frameOfPresentedViewInContainerView")
    public native @ByVal CGRect getFrameOfPresentedViewInContainerView();
    @Method(selector = "shouldPresentInFullscreen")
    public native boolean shouldPresentInFullscreen();
    @Method(selector = "shouldRemovePresentersView")
    public native boolean shouldRemovePresentersView();
    @Method(selector = "presentationTransitionWillBegin")
    public native void presentationTransitionWillBegin();
    @Method(selector = "presentationTransitionDidEnd:")
    public native void presentationTransitionDidEnd(boolean completed);
    @Method(selector = "dismissalTransitionWillBegin")
    public native void dismissalTransitionWillBegin();
    @Method(selector = "dismissalTransitionDidEnd:")
    public native void dismissalTransitionDidEnd(boolean completed);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "traitCollectionDidChange:")
    public native void traitCollectionDidChange(UITraitCollection previousTraitCollection);
    /*</methods>*/
}
