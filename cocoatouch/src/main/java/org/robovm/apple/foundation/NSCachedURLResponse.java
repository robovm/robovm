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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCachedURLResponse/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSCachedURLResponsePtr extends Ptr<NSCachedURLResponse, NSCachedURLResponsePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCachedURLResponse.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCachedURLResponse() {}
    protected NSCachedURLResponse(SkipInit skipInit) { super(skipInit); }
    public NSCachedURLResponse(NSURLResponse response, NSData data) { super((SkipInit) null); initObject(initWithResponse$data$(response, data)); }
    public NSCachedURLResponse(NSURLResponse response, NSData data, NSDictionary<?, ?> userInfo, NSURLCacheStoragePolicy storagePolicy) { super((SkipInit) null); initObject(initWithResponse$data$userInfo$storagePolicy$(response, data, userInfo, storagePolicy)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithResponse:data:")
    protected native @Pointer long initWithResponse$data$(NSURLResponse response, NSData data);
    @Method(selector = "initWithResponse:data:userInfo:storagePolicy:")
    protected native @Pointer long initWithResponse$data$userInfo$storagePolicy$(NSURLResponse response, NSData data, NSDictionary<?, ?> userInfo, NSURLCacheStoragePolicy storagePolicy);
    @Method(selector = "response")
    public native NSURLResponse response();
    @Method(selector = "data")
    public native NSData data();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "storagePolicy")
    public native NSURLCacheStoragePolicy storagePolicy();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
