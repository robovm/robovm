/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHTTPCookieAttribute/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSHTTPCookieAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSHTTPCookieAttribute Name = new NSHTTPCookieAttribute("NameAttribute");
    public static final NSHTTPCookieAttribute Value = new NSHTTPCookieAttribute("ValueAttribute");
    public static final NSHTTPCookieAttribute OriginURL = new NSHTTPCookieAttribute("OriginURLAttribute");
    public static final NSHTTPCookieAttribute Version = new NSHTTPCookieAttribute("VersionAttribute");
    public static final NSHTTPCookieAttribute Domain = new NSHTTPCookieAttribute("DomainAttribute");
    public static final NSHTTPCookieAttribute Path = new NSHTTPCookieAttribute("PathAttribute");
    public static final NSHTTPCookieAttribute Secure = new NSHTTPCookieAttribute("SecureAttribute");
    public static final NSHTTPCookieAttribute Expires = new NSHTTPCookieAttribute("ExpiresAttribute");
    public static final NSHTTPCookieAttribute Comment = new NSHTTPCookieAttribute("CommentAttribute");
    public static final NSHTTPCookieAttribute CommentURL = new NSHTTPCookieAttribute("CommentURLAttribute");
    public static final NSHTTPCookieAttribute Discard = new NSHTTPCookieAttribute("DiscardAttribute");
    public static final NSHTTPCookieAttribute MaximumAge = new NSHTTPCookieAttribute("MaximumAgeAttribute");
    public static final NSHTTPCookieAttribute Port = new NSHTTPCookieAttribute("PortAttribute");
    private static NSHTTPCookieAttribute[] values = new NSHTTPCookieAttribute[] {Name, Value, OriginURL, Version, Domain, Path, Secure, Expires, 
        Comment, CommentURL, Discard, MaximumAge, Port};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSHTTPCookieAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSHTTPCookieAttribute valueOf(NSString value) {
        for (NSHTTPCookieAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSHTTPCookieAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSHTTPCookieName", optional=true)
    protected static native NSString NameAttribute();
    @GlobalValue(symbol="NSHTTPCookieValue", optional=true)
    protected static native NSString ValueAttribute();
    @GlobalValue(symbol="NSHTTPCookieOriginURL", optional=true)
    protected static native NSString OriginURLAttribute();
    @GlobalValue(symbol="NSHTTPCookieVersion", optional=true)
    protected static native NSString VersionAttribute();
    @GlobalValue(symbol="NSHTTPCookieDomain", optional=true)
    protected static native NSString DomainAttribute();
    @GlobalValue(symbol="NSHTTPCookiePath", optional=true)
    protected static native NSString PathAttribute();
    @GlobalValue(symbol="NSHTTPCookieSecure", optional=true)
    protected static native NSString SecureAttribute();
    @GlobalValue(symbol="NSHTTPCookieExpires", optional=true)
    protected static native NSString ExpiresAttribute();
    @GlobalValue(symbol="NSHTTPCookieComment", optional=true)
    protected static native NSString CommentAttribute();
    @GlobalValue(symbol="NSHTTPCookieCommentURL", optional=true)
    protected static native NSString CommentURLAttribute();
    @GlobalValue(symbol="NSHTTPCookieDiscard", optional=true)
    protected static native NSString DiscardAttribute();
    @GlobalValue(symbol="NSHTTPCookieMaximumAge", optional=true)
    protected static native NSString MaximumAgeAttribute();
    @GlobalValue(symbol="NSHTTPCookiePort", optional=true)
    protected static native NSString PortAttribute();
    /*</methods>*/
}
