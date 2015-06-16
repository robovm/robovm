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
package org.robovm.apple.accounts;

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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Accounts")/*</annotations>*/
@Marshaler(/*<name>*/ACFacebookOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACFacebookOptions/*</name>*/ 
    extends /*<extends>*/ACAccountOptions/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ACFacebookOptions toObject(Class<ACFacebookOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new ACFacebookOptions(o);
        }
        @MarshalsPointer
        public static long toNative(ACFacebookOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ACFacebookOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ACFacebookOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new ACFacebookOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ACFacebookOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (ACFacebookOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    ACFacebookOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public ACFacebookOptions() {}
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
    public ACFacebookOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getAppId() {
        if (has(Keys.AppId())) {
            NSString val = (NSString) get(Keys.AppId());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public ACFacebookOptions setAppId(String appId) {
        set(Keys.AppId(), new NSString(appId));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public List<String> getPermissions() {
        if (has(Keys.Permissions())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.Permissions());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public ACFacebookOptions setPermissions(List<String> permissions) {
        set(Keys.Permissions(), NSArray.fromStrings(permissions));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public ACFacebookAudience getAudience() {
        if (has(Keys.Audience())) {
            NSString val = (NSString) get(Keys.Audience());
            return ACFacebookAudience.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public ACFacebookOptions setAudience(ACFacebookAudience audience) {
        set(Keys.Audience(), audience.value());
        return this;
    }
    /*</methods>*/
    
     /**
     * @since Available in iOS 6.0 and later.
     */
    public void setPermissions(String...permissions) {
        NSArray<NSString> p = NSArray.fromStrings(permissions);
        data.put(Keys.Permissions(), p);
    }
    
    /*<keys>*/
    @Library("Accounts")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookAppIdKey", optional=true)
        public static native NSString AppId();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookPermissionsKey", optional=true)
        public static native NSString Permissions();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="ACFacebookAudienceKey", optional=true)
        public static native NSString Audience();
    }
    /*</keys>*/
}
