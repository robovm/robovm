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
    @GlobalValue(symbol="NSHTTPCookieName", optional=true)
    public static native NSString KeyName();
    @GlobalValue(symbol="NSHTTPCookieValue", optional=true)
    public static native NSString KeyValue();
    @GlobalValue(symbol="NSHTTPCookieOriginURL", optional=true)
    public static native NSString KeyOriginURL();
    @GlobalValue(symbol="NSHTTPCookieVersion", optional=true)
    public static native NSString KeyVersion();
    @GlobalValue(symbol="NSHTTPCookieDomain", optional=true)
    public static native NSString KeyDomain();
    @GlobalValue(symbol="NSHTTPCookiePath", optional=true)
    public static native NSString KeyPath();
    @GlobalValue(symbol="NSHTTPCookieSecure", optional=true)
    public static native NSString KeySecure();
    @GlobalValue(symbol="NSHTTPCookieExpires", optional=true)
    public static native NSString KeyExpires();
    @GlobalValue(symbol="NSHTTPCookieComment", optional=true)
    public static native NSString KeyComment();
    @GlobalValue(symbol="NSHTTPCookieCommentURL", optional=true)
    public static native NSString KeyCommentURL();
    @GlobalValue(symbol="NSHTTPCookieDiscard", optional=true)
    public static native NSString KeyDiscard();
    @GlobalValue(symbol="NSHTTPCookieMaximumAge", optional=true)
    public static native NSString KeyMaximumAge();
    @GlobalValue(symbol="NSHTTPCookiePort", optional=true)
    public static native NSString KeyPort();
    
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
