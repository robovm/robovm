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
import org.robovm.apple.security.*;
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
    public NSURLRequest connection$willSendRequest$redirectResponse$(NSURLConnection connection, NSURLRequest request, NSURLResponse response) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didReceiveResponse:")
    public void connection$didReceiveResponse$(NSURLConnection connection, NSURLResponse response) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didReceiveData:")
    public void connection$didReceiveData$(NSURLConnection connection, NSData data) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:needNewBodyStream:")
    public NSInputStream connection$needNewBodyStream$(NSURLConnection connection, NSURLRequest request) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:didSendBodyData:totalBytesWritten:totalBytesExpectedToWrite:")
    public void connection$didSendBodyData$totalBytesWritten$totalBytesExpectedToWrite$(NSURLConnection connection, @MachineSizedSInt long bytesWritten, @MachineSizedSInt long totalBytesWritten, @MachineSizedSInt long totalBytesExpectedToWrite) { throw new UnsupportedOperationException(); }
    @NotImplemented("connection:willCacheResponse:")
    public NSCachedURLResponse connection$willCacheResponse$(NSURLConnection connection, NSCachedURLResponse cachedResponse) { throw new UnsupportedOperationException(); }
    @NotImplemented("connectionDidFinishLoading:")
    public void connectionDidFinishLoading$(NSURLConnection connection) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
