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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLCache/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLCachePtr extends Ptr<NSURLCache, NSURLCachePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLCache.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLCache() {}
    protected NSURLCache(SkipInit skipInit) { super(skipInit); }
    public NSURLCache(@MachineSizedUInt long memoryCapacity, @MachineSizedUInt long diskCapacity, String path) { super((SkipInit) null); initObject(initWithMemoryCapacity$diskCapacity$diskPath$(memoryCapacity, diskCapacity, path)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithMemoryCapacity:diskCapacity:diskPath:")
    protected native @Pointer long initWithMemoryCapacity$diskCapacity$diskPath$(@MachineSizedUInt long memoryCapacity, @MachineSizedUInt long diskCapacity, String path);
    @Method(selector = "cachedResponseForRequest:")
    public native NSCachedURLResponse cachedResponseForRequest$(NSURLRequest request);
    @Method(selector = "storeCachedResponse:forRequest:")
    public native void storeCachedResponse$forRequest$(NSCachedURLResponse cachedResponse, NSURLRequest request);
    @Method(selector = "removeCachedResponseForRequest:")
    public native void removeCachedResponseForRequest$(NSURLRequest request);
    @Method(selector = "removeAllCachedResponses")
    public native void removeAllCachedResponses();
    @Method(selector = "memoryCapacity")
    public native @MachineSizedUInt long memoryCapacity();
    @Method(selector = "diskCapacity")
    public native @MachineSizedUInt long diskCapacity();
    @Method(selector = "setMemoryCapacity:")
    public native void setMemoryCapacity(@MachineSizedUInt long memoryCapacity);
    @Method(selector = "setDiskCapacity:")
    public native void setDiskCapacity(@MachineSizedUInt long diskCapacity);
    @Method(selector = "currentMemoryUsage")
    public native @MachineSizedUInt long currentMemoryUsage();
    @Method(selector = "currentDiskUsage")
    public native @MachineSizedUInt long currentDiskUsage();
    @Method(selector = "sharedURLCache")
    public static native NSURLCache sharedURLCache();
    @Method(selector = "setSharedURLCache:")
    public static native void setSharedURLCache(NSURLCache cache);
    /*</methods>*/
}
