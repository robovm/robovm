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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLConnectionDataDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSURLConnectionDelegateAdapter/*</extends>*/ 
    /*<implements>*/implements NSURLConnectionDataDelegate/*</implements>*/ {

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
    @NotImplemented("connection:willSendRequest:redirectResponse:")
    public NSURLRequest willSendRequest(NSURLConnection connection, NSURLRequest request, NSURLResponse response) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didReceiveResponse:")
    public void didReceiveResponse(NSURLConnection connection, NSURLResponse response) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didReceiveData:")
    public void didReceiveData(NSURLConnection connection, NSData data) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:needNewBodyStream:")
    public NSInputStream needNewBodyStream(NSURLConnection connection, NSURLRequest request) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didSendBodyData:totalBytesWritten:totalBytesExpectedToWrite:")
    public void didSendBodyData(NSURLConnection connection, @MachineSizedSInt long bytesWritten, @MachineSizedSInt long totalBytesWritten, @MachineSizedSInt long totalBytesExpectedToWrite) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:willCacheResponse:")
    public NSCachedURLResponse willCacheResponse(NSURLConnection connection, NSCachedURLResponse cachedResponse) { throw new UnsupportedOperationException(); }
    @NotImplemented("connectionDidFinishLoading:")
    public void didFinishLoading(NSURLConnection connection) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
