/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(NSHTTPCookieAttributes.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHTTPCookieAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSHTTPCookieAttributes toObject(Class<NSHTTPCookieAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSHTTPCookieAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSHTTPCookieAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSHTTPCookieAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSHTTPCookieAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSHTTPCookieAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSHTTPCookieAttributes set(NSHTTPCookieAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
        return this;
    }
    public NSObject get(NSHTTPCookieAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(NSHTTPCookieAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    public String getComment() {
        if (contains(NSHTTPCookieAttribute.Comment)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Comment);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setComment(String comment) {
        set(NSHTTPCookieAttribute.Comment, new NSString(comment));
        return this;
    }
    public NSURL getCommentURL() {
        if (contains(NSHTTPCookieAttribute.CommentURL)) {
            NSURL val = (NSURL)get(NSHTTPCookieAttribute.CommentURL);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setCommentURL(NSURL url) {
        set(NSHTTPCookieAttribute.CommentURL, url);
        return this;
    }
    public boolean isDiscard() {
        if (contains(NSHTTPCookieAttribute.Discard)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Discard);
            return val.toString().equals("TRUE");
        }
        
        if (getVersion() >= 1 && getMaximumAge() == 0) return true; 
        return false;
    }
    public NSHTTPCookieAttributes setDiscard(boolean discard) {
        set(NSHTTPCookieAttribute.Discard, discard ? new NSString("TRUE") : new NSString("FALSE"));
        return this;
    }
    public String getDomain() {
        if (contains(NSHTTPCookieAttribute.Domain)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Domain);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setDomain(String domain) {
        set(NSHTTPCookieAttribute.Domain, new NSString(domain));
        return this;
    }
    public NSDate getExpireDate() {
        if (contains(NSHTTPCookieAttribute.Expires)) {
            NSDate val = (NSDate)get(NSHTTPCookieAttribute.Expires);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setExpireDate(NSDate expires) {
        set(NSHTTPCookieAttribute.Expires, expires);
        return this;
    }
    public int getMaximumAge() {
        if (contains(NSHTTPCookieAttribute.MaximumAge)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.MaximumAge);
            return Integer.valueOf(val.toString());
        }
        return 0;
    }
    public NSHTTPCookieAttributes setMaximumAge(int age) {
        set(NSHTTPCookieAttribute.MaximumAge, new NSString(String.valueOf(age)));
        return this;
    }
    public String getName() {
        if (contains(NSHTTPCookieAttribute.Name)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Name);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setName(String name) {
        set(NSHTTPCookieAttribute.Name, new NSString(name));
        return this;
    }
    public NSURL getOriginURL() {
        if (contains(NSHTTPCookieAttribute.OriginURL)) {
            NSURL val = (NSURL)get(NSHTTPCookieAttribute.OriginURL);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setOriginURL(NSURL url) {
        set(NSHTTPCookieAttribute.OriginURL, url);
        return this;
    }
    public String getPath() {
        if (contains(NSHTTPCookieAttribute.Path)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Path);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setPath(String path) {
        set(NSHTTPCookieAttribute.Path, new NSString(path));
        return this;
    }
    public String getPort() {
        if (contains(NSHTTPCookieAttribute.Port)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Port);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setPort(String port) {
        set(NSHTTPCookieAttribute.Port, new NSString(port));
        return this;
    }
    public boolean isSecure() {
        if (contains(NSHTTPCookieAttribute.Secure)) {
            return true;
        }
        return false;
    }
    public NSHTTPCookieAttributes setSecure(boolean secure) {
        set(NSHTTPCookieAttribute.Secure, secure ? new NSString("secure") : null);
        return this;
    }
    public String getValue() {
        if (contains(NSHTTPCookieAttribute.Value)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Value);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setValue(String value) {
        set(NSHTTPCookieAttribute.Value, new NSString(value));
        return this;
    }
    public int getVersion() {
        if (contains(NSHTTPCookieAttribute.Version)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Version);
            return Integer.valueOf(val.toString());
        }
        return 0;
    }
    public NSHTTPCookieAttributes setVersion(int version) {
        set(NSHTTPCookieAttribute.Version, new NSString(String.valueOf(version)));
        return this;
    }
    /*<methods>*/
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
