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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/AVAssetResourceLoaderDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "resourceLoader:shouldWaitForLoadingOfRequestedResource:")
    boolean shouldWait(AVAssetResourceLoader resourceLoader, AVAssetResourceLoadingRequest loadingRequest);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "resourceLoader:shouldWaitForRenewalOfRequestedResource:")
    boolean resourceLoader$shouldWaitForRenewalOfRequestedResource$(AVAssetResourceLoader resourceLoader, AVAssetResourceRenewalRequest renewalRequest);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "resourceLoader:didCancelLoadingRequest:")
    void didCancel(AVAssetResourceLoader resourceLoader, AVAssetResourceLoadingRequest loadingRequest);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "resourceLoader:shouldWaitForResponseToAuthenticationChallenge:")
    boolean resourceLoader$shouldWaitForResponseToAuthenticationChallenge$(AVAssetResourceLoader resourceLoader, NSURLAuthenticationChallenge authenticationChallenge);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "resourceLoader:didCancelAuthenticationChallenge:")
    void resourceLoader$didCancelAuthenticationChallenge$(AVAssetResourceLoader resourceLoader, NSURLAuthenticationChallenge authenticationChallenge);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
