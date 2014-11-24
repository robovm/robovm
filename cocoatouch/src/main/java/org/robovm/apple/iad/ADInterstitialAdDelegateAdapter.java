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
import org.robovm.apple.mediaplayer.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.avkit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ADInterstitialAdDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements ADInterstitialAdDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("interstitialAdDidUnload:")
    public void didUnload(ADInterstitialAd interstitialAd) { throw new UnsupportedOperationException(); }
    @NotImplemented("interstitialAd:didFailWithError:")
    public void didFail(ADInterstitialAd interstitialAd, NSError error) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("interstitialAdWillLoad:")
    public void willLoad(ADInterstitialAd interstitialAd) { throw new UnsupportedOperationException(); }
    @NotImplemented("interstitialAdDidLoad:")
    public void didLoad(ADInterstitialAd interstitialAd) { throw new UnsupportedOperationException(); }
    @NotImplemented("interstitialAdActionShouldBegin:willLeaveApplication:")
    public boolean actionShouldBegin(ADInterstitialAd interstitialAd, boolean willLeave) { throw new UnsupportedOperationException(); }
    @NotImplemented("interstitialAdActionDidFinish:")
    public void actionDidFinish(ADInterstitialAd interstitialAd) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
