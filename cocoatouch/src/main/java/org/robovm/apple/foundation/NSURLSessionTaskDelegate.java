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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSURLSessionTaskDelegate/*</name>*/ 
    /*<implements>*/extends NSURLSessionDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "URLSession:task:willPerformHTTPRedirection:newRequest:completionHandler:")
    void URLSession$task$willPerformHTTPRedirection$newRequest$completionHandler$(NSURLSession session, NSURLSessionTask task, NSHTTPURLResponse response, NSURLRequest request, ObjCBlock completionHandler);
    @Method(selector = "URLSession:task:didReceiveChallenge:completionHandler:")
    void URLSession$task$didReceiveChallenge$completionHandler$(NSURLSession session, NSURLSessionTask task, NSURLAuthenticationChallenge challenge, ObjCBlock completionHandler);
    @Method(selector = "URLSession:task:needNewBodyStream:")
    void URLSession$task$needNewBodyStream$(NSURLSession session, NSURLSessionTask task, ObjCBlock completionHandler);
    @Method(selector = "URLSession:task:didSendBodyData:totalBytesSent:totalBytesExpectedToSend:")
    void URLSession$task$didSendBodyData$totalBytesSent$totalBytesExpectedToSend$(NSURLSession session, NSURLSessionTask task, long bytesSent, long totalBytesSent, long totalBytesExpectedToSend);
    @Method(selector = "URLSession:task:didCompleteWithError:")
    void URLSession$task$didCompleteWithError$(NSURLSession session, NSURLSessionTask task, NSError error);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
