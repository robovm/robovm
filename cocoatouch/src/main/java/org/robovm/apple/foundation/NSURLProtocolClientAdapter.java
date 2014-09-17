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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtocolClientAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSURLProtocolClient/*</implements>*/ {

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
    @NotImplemented("URLProtocol:wasRedirectedToRequest:redirectResponse:")
    public void URLProtocol$wasRedirectedToRequest$redirectResponse$(NSURLProtocol protocol, NSURLRequest request, NSURLResponse redirectResponse) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:cachedResponseIsValid:")
    public void URLProtocol$cachedResponseIsValid$(NSURLProtocol protocol, NSCachedURLResponse cachedResponse) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:didReceiveResponse:cacheStoragePolicy:")
    public void URLProtocol$didReceiveResponse$cacheStoragePolicy$(NSURLProtocol protocol, NSURLResponse response, NSURLCacheStoragePolicy policy) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:didLoadData:")
    public void URLProtocol$didLoadData$(NSURLProtocol protocol, NSData data) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocolDidFinishLoading:")
    public void URLProtocolDidFinishLoading$(NSURLProtocol protocol) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:didFailWithError:")
    public void URLProtocol$didFailWithError$(NSURLProtocol protocol, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:didReceiveAuthenticationChallenge:")
    public void URLProtocol$didReceiveAuthenticationChallenge$(NSURLProtocol protocol, NSURLAuthenticationChallenge challenge) { throw new UnsupportedOperationException(); }
    @NotImplemented("URLProtocol:didCancelAuthenticationChallenge:")
    public void URLProtocol$didCancelAuthenticationChallenge$(NSURLProtocol protocol, NSURLAuthenticationChallenge challenge) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
