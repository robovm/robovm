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
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLProtocol/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLProtocolPtr extends Ptr<NSURLProtocol, NSURLProtocolPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLProtocol.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLProtocol() {}
    protected NSURLProtocol(SkipInit skipInit) { super(skipInit); }
    public NSURLProtocol(NSURLRequest request, NSCachedURLResponse cachedResponse, NSURLProtocolClient client) { super((SkipInit) null); initObject(initWithRequest$cachedResponse$client$(request, cachedResponse, client)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRequest:cachedResponse:client:")
    protected native @Pointer long initWithRequest$cachedResponse$client$(NSURLRequest request, NSCachedURLResponse cachedResponse, NSURLProtocolClient client);
    @Method(selector = "client")
    public native NSURLProtocolClient client();
    @Method(selector = "request")
    public native NSURLRequest request();
    @Method(selector = "cachedResponse")
    public native NSCachedURLResponse cachedResponse();
    @Method(selector = "startLoading")
    public native void startLoading();
    @Method(selector = "stopLoading")
    public native void stopLoading();
    @Method(selector = "canInitWithRequest:")
    public static native boolean canInitWithRequest$(NSURLRequest request);
    @Method(selector = "canonicalRequestForRequest:")
    public static native NSURLRequest canonicalRequestForRequest$(NSURLRequest request);
    @Method(selector = "requestIsCacheEquivalent:toRequest:")
    public static native boolean requestIsCacheEquivalent$toRequest$(NSURLRequest a, NSURLRequest b);
    @Method(selector = "propertyForKey:inRequest:")
    public static native NSObject propertyForKey$inRequest$(String key, NSURLRequest request);
    @Method(selector = "setProperty:forKey:inRequest:")
    public static native void setProperty$forKey$inRequest$(NSObject value, String key, NSMutableURLRequest request);
    @Method(selector = "removePropertyForKey:inRequest:")
    public static native void removePropertyForKey$inRequest$(String key, NSMutableURLRequest request);
    @Method(selector = "registerClass:")
    public static native boolean registerClass$(ObjCClass protocolClass);
    @Method(selector = "unregisterClass:")
    public static native void unregisterClass$(ObjCClass protocolClass);
    /*</methods>*/
}
