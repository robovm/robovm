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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLConnection/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLConnectionPtr extends Ptr<NSURLConnection, NSURLConnectionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLConnection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLConnection() {}
    protected NSURLConnection(SkipInit skipInit) { super(skipInit); }
    public NSURLConnection(NSURLRequest request, NSURLConnection delegate, boolean startImmediately) { super((SkipInit) null); initObject(initWithRequest$delegate$startImmediately$(request, delegate, startImmediately)); }
    public NSURLConnection(NSURLRequest request, NSURLConnection delegate) { super((SkipInit) null); initObject(initWithRequest$delegate$(request, delegate)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRequest:delegate:startImmediately:")
    protected native @Pointer long initWithRequest$delegate$startImmediately$(NSURLRequest request, NSURLConnection delegate, boolean startImmediately);
    @Method(selector = "initWithRequest:delegate:")
    protected native @Pointer long initWithRequest$delegate$(NSURLRequest request, NSURLConnection delegate);
    @Method(selector = "originalRequest")
    public native NSURLRequest originalRequest();
    @Method(selector = "currentRequest")
    public native NSURLRequest currentRequest();
    @Method(selector = "start")
    public native void start();
    @Method(selector = "cancel")
    public native void cancel();
    @Method(selector = "scheduleInRunLoop:forMode:")
    public native void scheduleInRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "unscheduleFromRunLoop:forMode:")
    public native void unscheduleFromRunLoop$forMode$(NSRunLoop aRunLoop, String mode);
    @Method(selector = "setDelegateQueue:")
    public native void setDelegateQueue$(NSOperationQueue queue);
    @Method(selector = "connectionWithRequest:delegate:")
    public static native NSURLConnection connectionWithRequest$delegate$(NSURLRequest request, NSObject delegate);
    @Method(selector = "canHandleRequest:")
    public static native boolean canHandleRequest$(NSURLRequest request);
    @Method(selector = "sendSynchronousRequest:returningResponse:error:")
    public static native NSData sendSynchronousRequest$returningResponse$error$(NSURLRequest request, NSURLResponse.NSURLResponsePtr response, NSError.NSErrorPtr error);
    @Method(selector = "sendAsynchronousRequest:queue:completionHandler:")
    public static native void sendAsynchronousRequest$queue$completionHandler$(NSURLRequest request, NSOperationQueue queue, ObjCBlock handler);
    /*</methods>*/
}
