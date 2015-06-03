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
@Marshaler(NSStreamSOCKSProxyConfiguration.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamSOCKSProxyConfiguration/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSStreamSOCKSProxyConfiguration toObject(Class<NSStreamSOCKSProxyConfiguration> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSStreamSOCKSProxyConfiguration(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSStreamSOCKSProxyConfiguration() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSStreamSOCKSProxyConfiguration.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getHost() {
        if (data.containsKey(HostKey())) {
            NSString val = (NSString)data.get(HostKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setHost(String host) {
        data.put(HostKey(), new NSString(host));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public int getPort() {
        if (data.containsKey(PortKey())) {
            NSNumber val = (NSNumber)data.get(PortKey());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setPort(int port) {
        data.put(PortKey(), NSNumber.valueOf(port));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyVersion getVersion() {
        if (data.containsKey(VersionKey())) {
            NSString val = (NSString)data.get(VersionKey());
            return NSStreamSOCKSProxyVersion.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setVersion(NSStreamSOCKSProxyVersion version) {
        data.put(VersionKey(), version.value());
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getUser() {
        if (data.containsKey(UserKey())) {
            NSString val = (NSString)data.get(UserKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setUser(String host) {
        data.put(UserKey(), new NSString(host));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getPassword() {
        if (data.containsKey(PasswordKey())) {
            NSString val = (NSString)data.get(PasswordKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSStreamSOCKSProxyConfiguration setPassword(String host) {
        data.put(PasswordKey(), new NSString(host));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyHostKey", optional=true)
    protected static native NSString HostKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPortKey", optional=true)
    protected static native NSString PortKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey", optional=true)
    protected static native NSString VersionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyUserKey", optional=true)
    protected static native NSString UserKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey", optional=true)
    protected static native NSString PasswordKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
