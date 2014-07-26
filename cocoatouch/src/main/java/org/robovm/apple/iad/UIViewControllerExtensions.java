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
package org.robovm.apple.iad;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("iAd")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/UIViewControllerExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIViewControllerExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private UIViewControllerExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "interstitialPresentationPolicy")
    public static native ADInterstitialPresentationPolicy getInterstitialPresentationPolicy(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setInterstitialPresentationPolicy:")
    public static native void setInterstitialPresentationPolicy(UIViewController thiz, ADInterstitialPresentationPolicy v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "canDisplayBannerAds")
    public static native boolean isCanDisplayBannerAds(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setCanDisplayBannerAds:")
    public static native void setCanDisplayBannerAds(UIViewController thiz, boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "originalContentView")
    public static native UIView getOriginalContentView(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isPresentingFullScreenAd")
    public static native boolean isPresentingFullScreenAd(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isDisplayingBannerAd")
    public static native boolean isDisplayingBannerAd(UIViewController thiz);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "requestInterstitialAdPresentation")
    public static native boolean requestInterstitialAdPresentation(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "shouldPresentInterstitialAd")
    public static native boolean shouldPresentInterstitialAd(UIViewController thiz);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "prepareInterstitialAds")
    protected static native void prepareInterstitialAds(ObjCClass clazz);
    public static void prepareInterstitialAds() { prepareInterstitialAds(ObjCClass.getByType(UIViewController.class)); }
    /*</methods>*/
}
