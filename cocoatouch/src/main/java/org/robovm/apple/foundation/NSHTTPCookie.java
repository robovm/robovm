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
    public NSHTTPCookie(NSDictionary<?, ?> properties) { super((SkipInit) null); initObject(initWithProperties$(properties)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithProperties:")
    protected native @Pointer long initWithProperties$(NSDictionary<?, ?> properties);
    @Method(selector = "properties")
    public native NSDictionary<?, ?> properties();
    @Method(selector = "version")
    public native @MachineSizedUInt long version();
    @Method(selector = "name")
    public native String name();
    @Method(selector = "value")
    public native String value();
    @Method(selector = "expiresDate")
    public native NSDate expiresDate();
    @Method(selector = "isSessionOnly")
    public native boolean isSessionOnly();
    @Method(selector = "domain")
    public native String domain();
    @Method(selector = "path")
    public native String path();
    @Method(selector = "isSecure")
    public native boolean isSecure();
    @Method(selector = "isHTTPOnly")
    public native boolean isHTTPOnly();
    @Method(selector = "comment")
    public native String comment();
    @Method(selector = "commentURL")
    public native NSURL commentURL();
    @Method(selector = "portList")
    public native NSArray<?> portList();
    @Method(selector = "cookieWithProperties:")
    public static native NSObject cookieWithProperties$(NSDictionary<?, ?> properties);
    @Method(selector = "requestHeaderFieldsWithCookies:")
    public static native NSDictionary<?, ?> requestHeaderFieldsWithCookies$(NSArray<?> cookies);
    @Method(selector = "cookiesWithResponseHeaderFields:forURL:")
    public static native NSArray<?> cookiesWithResponseHeaderFields$forURL$(NSDictionary<?, ?> headerFields, NSURL URL);
    /*</methods>*/
}
