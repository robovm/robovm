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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetResourceLoaderDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements AVAssetResourceLoaderDelegate/*</implements>*/ {

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
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("resourceLoader:shouldWaitForLoadingOfRequestedResource:")
    public boolean shouldWaitForLoadingOfRequestedResource(AVAssetResourceLoader resourceLoader, AVAssetResourceLoadingRequest loadingRequest) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("resourceLoader:shouldWaitForRenewalOfRequestedResource:")
    public boolean shouldWaitForRenewalOfRequestedResource(AVAssetResourceLoader resourceLoader, AVAssetResourceRenewalRequest renewalRequest) { return false; }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("resourceLoader:didCancelLoadingRequest:")
    public void didCancelLoadingRequest(AVAssetResourceLoader resourceLoader, AVAssetResourceLoadingRequest loadingRequest) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("resourceLoader:shouldWaitForResponseToAuthenticationChallenge:")
    public boolean shouldWaitForResponseToAuthenticationChallenge(AVAssetResourceLoader resourceLoader, NSURLAuthenticationChallenge authenticationChallenge) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("resourceLoader:didCancelAuthenticationChallenge:")
    public void didCancelAuthenticationChallenge(AVAssetResourceLoader resourceLoader, NSURLAuthenticationChallenge authenticationChallenge) {}
    /*</methods>*/
}
