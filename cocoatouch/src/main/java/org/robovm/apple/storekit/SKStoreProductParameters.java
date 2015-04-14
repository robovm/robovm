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
package org.robovm.apple.storekit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("StoreKit")/*</annotations>*/
@Marshaler(/*<name>*/SKStoreProductParameters/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKStoreProductParameters/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SKStoreProductParameters toObject(Class<SKStoreProductParameters> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SKStoreProductParameters(o);
        }
        @MarshalsPointer
        public static long toNative(SKStoreProductParameters o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SKStoreProductParameters> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SKStoreProductParameters> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SKStoreProductParameters(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SKStoreProductParameters> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (SKStoreProductParameters i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SKStoreProductParameters(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public SKStoreProductParameters() {}
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
    public SKStoreProductParameters set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public long getITunesItemIdentifier() {
        if (has(Keys.ITunesItemIdentifier())) {
            NSNumber val = (NSNumber) get(Keys.ITunesItemIdentifier());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public SKStoreProductParameters setITunesItemIdentifier(long iTunesItemIdentifier) {
        set(Keys.ITunesItemIdentifier(), NSNumber.valueOf(iTunesItemIdentifier));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getAffiliateToken() {
        if (has(Keys.AffiliateToken())) {
            NSString val = (NSString) get(Keys.AffiliateToken());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SKStoreProductParameters setAffiliateToken(String affiliateToken) {
        set(Keys.AffiliateToken(), new NSString(affiliateToken));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getCampaignToken() {
        if (has(Keys.CampaignToken())) {
            NSString val = (NSString) get(Keys.CampaignToken());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public SKStoreProductParameters setCampaignToken(String campaignToken) {
        set(Keys.CampaignToken(), new NSString(campaignToken));
        return this;
    }
    /**
     * @since Available in iOS 8.3 and later.
     */
    public String getProviderToken() {
        if (has(Keys.ProviderToken())) {
            NSString val = (NSString) get(Keys.ProviderToken());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.3 and later.
     */
    public SKStoreProductParameters setProviderToken(String providerToken) {
        set(Keys.ProviderToken(), new NSString(providerToken));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("StoreKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="SKStoreProductParameterITunesItemIdentifier", optional=true)
        public static native NSString ITunesItemIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SKStoreProductParameterAffiliateToken", optional=true)
        public static native NSString AffiliateToken();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="SKStoreProductParameterCampaignToken", optional=true)
        public static native NSString CampaignToken();
        /**
         * @since Available in iOS 8.3 and later.
         */
        @GlobalValue(symbol="SKStoreProductParameterProviderToken", optional=true)
        public static native NSString ProviderToken();
    }
    /*</keys>*/
}
