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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLRequestPtr extends Ptr<NSURLRequest, NSURLRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLRequest() {}
    protected NSURLRequest(SkipInit skipInit) { super(skipInit); }
    public NSURLRequest(NSURL URL) { super((SkipInit) null); initObject(initWithURL$(URL)); }
    public NSURLRequest(NSURL URL, NSURLRequestCachePolicy cachePolicy, double timeoutInterval) { super((SkipInit) null); initObject(initWithURL$cachePolicy$timeoutInterval$(URL, cachePolicy, timeoutInterval)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:")
    protected native @Pointer long initWithURL$(NSURL URL);
    @Method(selector = "initWithURL:cachePolicy:timeoutInterval:")
    protected native @Pointer long initWithURL$cachePolicy$timeoutInterval$(NSURL URL, NSURLRequestCachePolicy cachePolicy, double timeoutInterval);
    @Method(selector = "URL")
    public native NSURL URL();
    @Method(selector = "cachePolicy")
    public native NSURLRequestCachePolicy cachePolicy();
    @Method(selector = "timeoutInterval")
    public native double timeoutInterval();
    @Method(selector = "mainDocumentURL")
    public native NSURL mainDocumentURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "networkServiceType")
    public native NSURLRequestNetworkServiceType networkServiceType();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "allowsCellularAccess")
    public native boolean allowsCellularAccess();
    @Method(selector = "requestWithURL:")
    public static native NSObject requestWithURL$(NSURL URL);
    @Method(selector = "supportsSecureCoding")
    public static native boolean supportsSecureCoding();
    @Method(selector = "requestWithURL:cachePolicy:timeoutInterval:")
    public static native NSObject requestWithURL$cachePolicy$timeoutInterval$(NSURL URL, NSURLRequestCachePolicy cachePolicy, double timeoutInterval);
    @Method(selector = "HTTPMethod")
    public native String HTTPMethod();
    @Method(selector = "allHTTPHeaderFields")
    public native NSDictionary<?, ?> allHTTPHeaderFields();
    @Method(selector = "valueForHTTPHeaderField:")
    public native String valueForHTTPHeaderField$(String field);
    @Method(selector = "HTTPBody")
    public native NSData HTTPBody();
    @Method(selector = "HTTPBodyStream")
    public native NSInputStream HTTPBodyStream();
    @Method(selector = "HTTPShouldHandleCookies")
    public native boolean HTTPShouldHandleCookies();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "HTTPShouldUsePipelining")
    public native boolean HTTPShouldUsePipelining();
    /*</methods>*/
}
