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
import org.robovm.apple.mediaplayer.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.avkit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/ADInterstitialAdDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "interstitialAdDidUnload:")
    void didUnload(ADInterstitialAd interstitialAd);
    @Method(selector = "interstitialAd:didFailWithError:")
    void didFail(ADInterstitialAd interstitialAd, NSError error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "interstitialAdWillLoad:")
    void willLoad(ADInterstitialAd interstitialAd);
    @Method(selector = "interstitialAdDidLoad:")
    void didLoad(ADInterstitialAd interstitialAd);
    @Method(selector = "interstitialAdActionShouldBegin:willLeaveApplication:")
    boolean actionShouldBegin(ADInterstitialAd interstitialAd, boolean willLeave);
    @Method(selector = "interstitialAdActionDidFinish:")
    void actionDidFinish(ADInterstitialAd interstitialAd);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
