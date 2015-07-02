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
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSHTTPCookieAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHTTPCookieAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSHTTPCookieAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSHTTPCookieAttribute toObject(Class<NSHTTPCookieAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSHTTPCookieAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSHTTPCookieAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSHTTPCookieAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSHTTPCookieAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSHTTPCookieAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSHTTPCookieAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSHTTPCookieAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSHTTPCookieAttribute Name = new NSHTTPCookieAttribute("Name");
    public static final NSHTTPCookieAttribute Value = new NSHTTPCookieAttribute("Value");
    public static final NSHTTPCookieAttribute OriginURL = new NSHTTPCookieAttribute("OriginURL");
    public static final NSHTTPCookieAttribute Version = new NSHTTPCookieAttribute("Version");
    public static final NSHTTPCookieAttribute Domain = new NSHTTPCookieAttribute("Domain");
    public static final NSHTTPCookieAttribute Path = new NSHTTPCookieAttribute("Path");
    public static final NSHTTPCookieAttribute Secure = new NSHTTPCookieAttribute("Secure");
    public static final NSHTTPCookieAttribute Expires = new NSHTTPCookieAttribute("Expires");
    public static final NSHTTPCookieAttribute Comment = new NSHTTPCookieAttribute("Comment");
    public static final NSHTTPCookieAttribute CommentURL = new NSHTTPCookieAttribute("CommentURL");
    public static final NSHTTPCookieAttribute Discard = new NSHTTPCookieAttribute("Discard");
    public static final NSHTTPCookieAttribute MaximumAge = new NSHTTPCookieAttribute("MaximumAge");
    public static final NSHTTPCookieAttribute Port = new NSHTTPCookieAttribute("Port");
    /*</constants>*/
    
    private static /*<name>*/NSHTTPCookieAttribute/*</name>*/[] values = new /*<name>*/NSHTTPCookieAttribute/*</name>*/[] {/*<value_list>*/Name, Value, OriginURL, Version, Domain, Path, Secure, Expires, Comment, CommentURL, Discard, MaximumAge, Port/*</value_list>*/};
    
    /*<name>*/NSHTTPCookieAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSHTTPCookieAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSHTTPCookieAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSHTTPCookieAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSHTTPCookieName", optional=true)
        public static native NSString Name();
        @GlobalValue(symbol="NSHTTPCookieValue", optional=true)
        public static native NSString Value();
        @GlobalValue(symbol="NSHTTPCookieOriginURL", optional=true)
        public static native NSString OriginURL();
        @GlobalValue(symbol="NSHTTPCookieVersion", optional=true)
        public static native NSString Version();
        @GlobalValue(symbol="NSHTTPCookieDomain", optional=true)
        public static native NSString Domain();
        @GlobalValue(symbol="NSHTTPCookiePath", optional=true)
        public static native NSString Path();
        @GlobalValue(symbol="NSHTTPCookieSecure", optional=true)
        public static native NSString Secure();
        @GlobalValue(symbol="NSHTTPCookieExpires", optional=true)
        public static native NSString Expires();
        @GlobalValue(symbol="NSHTTPCookieComment", optional=true)
        public static native NSString Comment();
        @GlobalValue(symbol="NSHTTPCookieCommentURL", optional=true)
        public static native NSString CommentURL();
        @GlobalValue(symbol="NSHTTPCookieDiscard", optional=true)
        public static native NSString Discard();
        @GlobalValue(symbol="NSHTTPCookieMaximumAge", optional=true)
        public static native NSString MaximumAge();
        @GlobalValue(symbol="NSHTTPCookiePort", optional=true)
        public static native NSString Port();
        /*</values>*/
    }
}
