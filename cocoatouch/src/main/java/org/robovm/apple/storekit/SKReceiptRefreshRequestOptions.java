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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("StoreKit")/*</annotations>*/
@Marshaler(/*<name>*/SKReceiptRefreshRequestOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKReceiptRefreshRequestOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SKReceiptRefreshRequestOptions toObject(Class<SKReceiptRefreshRequestOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new SKReceiptRefreshRequestOptions(o);
        }
        @MarshalsPointer
        public static long toNative(SKReceiptRefreshRequestOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SKReceiptRefreshRequestOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SKReceiptRefreshRequestOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new SKReceiptRefreshRequestOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SKReceiptRefreshRequestOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (SKReceiptRefreshRequestOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    SKReceiptRefreshRequestOptions(NSDictionary data) {
        super(data);
    }
    public SKReceiptRefreshRequestOptions() {}
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
    public SKReceiptRefreshRequestOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isExpired() {
        if (has(Keys.IsExpired())) {
            NSNumber val = (NSNumber) get(Keys.IsExpired());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setExpired(boolean expired) {
        set(Keys.IsExpired(), NSNumber.valueOf(expired));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isRevoked() {
        if (has(Keys.IsRevoked())) {
            NSNumber val = (NSNumber) get(Keys.IsRevoked());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setRevoked(boolean revoked) {
        set(Keys.IsRevoked(), NSNumber.valueOf(revoked));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isVolumePurchase() {
        if (has(Keys.IsVolumePurchase())) {
            NSNumber val = (NSNumber) get(Keys.IsVolumePurchase());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setVolumePurchase(boolean volumePurchase) {
        set(Keys.IsVolumePurchase(), NSNumber.valueOf(volumePurchase));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("StoreKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="SKReceiptPropertyIsExpired", optional=true)
        public static native NSString IsExpired();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="SKReceiptPropertyIsRevoked", optional=true)
        public static native NSString IsRevoked();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="SKReceiptPropertyIsVolumePurchase", optional=true)
        public static native NSString IsVolumePurchase();
    }
    /*</keys>*/
}
