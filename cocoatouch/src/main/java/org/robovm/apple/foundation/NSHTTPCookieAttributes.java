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
@Marshaler(/*<name>*/NSHTTPCookieAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHTTPCookieAttributes/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSHTTPCookieAttributes> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSHTTPCookieAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSHTTPCookieAttributes(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSHTTPCookieAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (NSHTTPCookieAttributes i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSHTTPCookieAttributes(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public NSHTTPCookieAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSHTTPCookieAttribute key) {
        return data.containsKey(key.value());
    }
    public NSObject get(NSHTTPCookieAttribute key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public NSHTTPCookieAttributes set(NSHTTPCookieAttribute key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    public String getName() {
        if (has(NSHTTPCookieAttribute.Name)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Name);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setName(String name) {
        set(NSHTTPCookieAttribute.Name, new NSString(name));
        return this;
    }
    public String getValue() {
        if (has(NSHTTPCookieAttribute.Value)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Value);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setValue(String value) {
        set(NSHTTPCookieAttribute.Value, new NSString(value));
        return this;
    }
    public NSURL getOriginURL() {
        if (has(NSHTTPCookieAttribute.OriginURL)) {
            NSURL val = (NSURL) get(NSHTTPCookieAttribute.OriginURL);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setOriginURL(NSURL originURL) {
        set(NSHTTPCookieAttribute.OriginURL, originURL);
        return this;
    }
    public int getVersion() {
        if (has(NSHTTPCookieAttribute.Version)) {
            NSNumber val = (NSNumber) get(NSHTTPCookieAttribute.Version);
            return val.intValue();
        }
        return 0;
    }
    public NSHTTPCookieAttributes setVersion(int version) {
        set(NSHTTPCookieAttribute.Version, NSNumber.valueOf(version));
        return this;
    }
    public String getDomain() {
        if (has(NSHTTPCookieAttribute.Domain)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Domain);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setDomain(String domain) {
        set(NSHTTPCookieAttribute.Domain, new NSString(domain));
        return this;
    }
    public String getPath() {
        if (has(NSHTTPCookieAttribute.Path)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Path);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setPath(String path) {
        set(NSHTTPCookieAttribute.Path, new NSString(path));
        return this;
    }
    public NSDate getExpireDate() {
        if (has(NSHTTPCookieAttribute.Expires)) {
            NSDate val = (NSDate) get(NSHTTPCookieAttribute.Expires);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setExpireDate(NSDate expireDate) {
        set(NSHTTPCookieAttribute.Expires, expireDate);
        return this;
    }
    public String getComment() {
        if (has(NSHTTPCookieAttribute.Comment)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Comment);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setComment(String comment) {
        set(NSHTTPCookieAttribute.Comment, new NSString(comment));
        return this;
    }
    public NSURL getCommentURL() {
        if (has(NSHTTPCookieAttribute.CommentURL)) {
            NSURL val = (NSURL) get(NSHTTPCookieAttribute.CommentURL);
            return val;
        }
        return null;
    }
    public NSHTTPCookieAttributes setCommentURL(NSURL commentURL) {
        set(NSHTTPCookieAttribute.CommentURL, commentURL);
        return this;
    }
    public int getMaximumAge() {
        if (has(NSHTTPCookieAttribute.MaximumAge)) {
            NSNumber val = (NSNumber) get(NSHTTPCookieAttribute.MaximumAge);
            return val.intValue();
        }
        return 0;
    }
    public NSHTTPCookieAttributes setMaximumAge(int maximumAge) {
        set(NSHTTPCookieAttribute.MaximumAge, NSNumber.valueOf(maximumAge));
        return this;
    }
    public String getPort() {
        if (has(NSHTTPCookieAttribute.Port)) {
            NSString val = (NSString) get(NSHTTPCookieAttribute.Port);
            return val.toString();
        }
        return null;
    }
    public NSHTTPCookieAttributes setPort(String port) {
        set(NSHTTPCookieAttribute.Port, new NSString(port));
        return this;
    }
    /*</methods>*/
    
    public boolean discards() {
        if (has(NSHTTPCookieAttribute.Discard)) {
            NSString val = (NSString)get(NSHTTPCookieAttribute.Discard);
            return val.toString().equals("TRUE");
        }
        
        if (getVersion() >= 1 && getMaximumAge() == 0) return true; 
        return false;
    }
    public NSHTTPCookieAttributes setDiscards(boolean discard) {
        set(NSHTTPCookieAttribute.Discard, discard ? new NSString("TRUE") : new NSString("FALSE"));
        return this;
    }
    public boolean isSecure() {
        if (has(NSHTTPCookieAttribute.Secure)) {
            return true;
        }
        return false;
    }
    public NSHTTPCookieAttributes setSecure(boolean secure) {
        set(NSHTTPCookieAttribute.Secure, secure ? new NSString("secure") : null);
        return this;
    }
    
    /*<keys>*/
    /*</keys>*/
}
