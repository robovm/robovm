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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHTTPCookie/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSHTTPCookiePtr extends Ptr<NSHTTPCookie, NSHTTPCookiePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSHTTPCookie.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSHTTPCookie() {}
    protected NSHTTPCookie(SkipInit skipInit) { super(skipInit); }
    public NSHTTPCookie(NSHTTPCookieAttributes properties) { super((SkipInit) null); initObject(init(properties)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithProperties:")
    protected native @Pointer long init(NSHTTPCookieAttributes properties);
    @Method(selector = "properties")
    public native NSHTTPCookieAttributes getProperties();
    @Method(selector = "version")
    public native @MachineSizedUInt long getVersion();
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "value")
    public native String getValue();
    @Method(selector = "expiresDate")
    public native NSDate getExpiresDate();
    @Method(selector = "isSessionOnly")
    public native boolean isSessionOnly();
    @Method(selector = "domain")
    public native String getDomain();
    @Method(selector = "path")
    public native String getPath();
    @Method(selector = "isSecure")
    public native boolean isSecure();
    @Method(selector = "isHTTPOnly")
    public native boolean isHTTPOnly();
    @Method(selector = "comment")
    public native String getComment();
    @Method(selector = "commentURL")
    public native NSURL getCommentURL();
    @Method(selector = "portList")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsIntegerListMarshaler.class) List<Integer> getPortList();
    @Method(selector = "requestHeaderFieldsWithCookies:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringStringMapMarshaler.class) Map<String, String> getRequestHeaderFieldsWithCookies(NSArray<NSHTTPCookie> cookies);
    @Method(selector = "cookiesWithResponseHeaderFields:forURL:")
    public static native NSArray<NSHTTPCookie> getCookiesWithResponseHeaderFields(@org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringStringMapMarshaler.class) Map<String, String> headerFields, NSURL url);
    /*</methods>*/
}
