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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSURLConnectionDataDelegate/*</name>*/ 
    /*<implements>*/extends NSURLConnectionDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "connection:willSendRequest:redirectResponse:")
    NSURLRequest connection$willSendRequest$redirectResponse$(NSURLConnection connection, NSURLRequest request, NSURLResponse response);
    @Method(selector = "connection:didReceiveResponse:")
    void connection$didReceiveResponse$(NSURLConnection connection, NSURLResponse response);
    @Method(selector = "connection:didReceiveData:")
    void connection$didReceiveData$(NSURLConnection connection, NSData data);
    @Method(selector = "connection:needNewBodyStream:")
    NSInputStream connection$needNewBodyStream$(NSURLConnection connection, NSURLRequest request);
    @Method(selector = "connection:didSendBodyData:totalBytesWritten:totalBytesExpectedToWrite:")
    void connection$didSendBodyData$totalBytesWritten$totalBytesExpectedToWrite$(NSURLConnection connection, @MachineSizedSInt long bytesWritten, @MachineSizedSInt long totalBytesWritten, @MachineSizedSInt long totalBytesExpectedToWrite);
    @Method(selector = "connection:willCacheResponse:")
    NSCachedURLResponse connection$willCacheResponse$(NSURLConnection connection, NSCachedURLResponse cachedResponse);
    @Method(selector = "connectionDidFinishLoading:")
    void connectionDidFinishLoading$(NSURLConnection connection);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
