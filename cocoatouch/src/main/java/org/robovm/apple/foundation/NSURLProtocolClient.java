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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSURLProtocolClient/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "URLProtocol:wasRedirectedToRequest:redirectResponse:")
    void URLProtocol$wasRedirectedToRequest$redirectResponse$(NSURLProtocol protocol, NSURLRequest request, NSURLResponse redirectResponse);
    @Method(selector = "URLProtocol:cachedResponseIsValid:")
    void URLProtocol$cachedResponseIsValid$(NSURLProtocol protocol, NSCachedURLResponse cachedResponse);
    @Method(selector = "URLProtocol:didReceiveResponse:cacheStoragePolicy:")
    void URLProtocol$didReceiveResponse$cacheStoragePolicy$(NSURLProtocol protocol, NSURLResponse response, NSURLCacheStoragePolicy policy);
    @Method(selector = "URLProtocol:didLoadData:")
    void URLProtocol$didLoadData$(NSURLProtocol protocol, NSData data);
    @Method(selector = "URLProtocolDidFinishLoading:")
    void URLProtocolDidFinishLoading$(NSURLProtocol protocol);
    @Method(selector = "URLProtocol:didFailWithError:")
    void URLProtocol$didFailWithError$(NSURLProtocol protocol, NSError error);
    @Method(selector = "URLProtocol:didReceiveAuthenticationChallenge:")
    void URLProtocol$didReceiveAuthenticationChallenge$(NSURLProtocol protocol, NSURLAuthenticationChallenge challenge);
    @Method(selector = "URLProtocol:didCancelAuthenticationChallenge:")
    void URLProtocol$didCancelAuthenticationChallenge$(NSURLProtocol protocol, NSURLAuthenticationChallenge challenge);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
