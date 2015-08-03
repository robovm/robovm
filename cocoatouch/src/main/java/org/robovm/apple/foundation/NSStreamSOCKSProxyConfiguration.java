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
@Marshaler(/*<name>*/NSStreamSOCKSProxyConfiguration/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamSOCKSProxyConfiguration/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamSOCKSProxyConfiguration toObject(Class<NSStreamSOCKSProxyConfiguration> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSStreamSOCKSProxyConfiguration(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamSOCKSProxyConfiguration o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSStreamSOCKSProxyConfiguration> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSStreamSOCKSProxyConfiguration> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSStreamSOCKSProxyConfiguration(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSStreamSOCKSProxyConfiguration> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (NSStreamSOCKSProxyConfiguration i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSStreamSOCKSProxyConfiguration(NSDictionary data) {
        super(data);
    }
    public NSStreamSOCKSProxyConfiguration() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public NSStreamSOCKSProxyConfiguration set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHost() {
        if (has(Keys.Host())) {
            NSString val = (NSString) get(Keys.Host());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setHost(String host) {
        set(Keys.Host(), new NSString(host));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (has(Keys.Port())) {
            NSNumber val = (NSNumber) get(Keys.Port());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setPort(int port) {
        set(Keys.Port(), NSNumber.valueOf(port));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyVersion getVersion() {
        if (has(Keys.Version())) {
            NSString val = (NSString) get(Keys.Version());
            return NSStreamSOCKSProxyVersion.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setVersion(NSStreamSOCKSProxyVersion version) {
        set(Keys.Version(), version.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUser() {
        if (has(Keys.User())) {
            NSString val = (NSString) get(Keys.User());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setUser(String user) {
        set(Keys.User(), new NSString(user));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPassword() {
        if (has(Keys.Password())) {
            NSString val = (NSString) get(Keys.Password());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setPassword(String password) {
        set(Keys.Password(), new NSString(password));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("Foundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyHostKey", optional=true)
        public static native NSString Host();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyPortKey", optional=true)
        public static native NSString Port();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey", optional=true)
        public static native NSString Version();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyUserKey", optional=true)
        public static native NSString User();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey", optional=true)
        public static native NSString Password();
    }
    /*</keys>*/
}
