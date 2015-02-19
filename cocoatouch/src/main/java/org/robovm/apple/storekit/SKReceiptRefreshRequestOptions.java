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
@Marshaler(SKReceiptRefreshRequestOptions.Marshaler.class)
/*<annotations>*/@Library("StoreKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKReceiptRefreshRequestOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static SKReceiptRefreshRequestOptions toObject(Class<SKReceiptRefreshRequestOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected SKReceiptRefreshRequestOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public SKReceiptRefreshRequestOptions() {
        data = new NSMutableDictionary<NSString, NSObject>();
    }
    /*<bind>*/static { Bro.bind(SKReceiptRefreshRequestOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isExpired() {
        if (data.containsKey(IsExpiredKey())) {
            NSNumber val = (NSNumber)data.get(IsExpiredKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setExpired(boolean expired) {
        data.put(IsExpiredKey(), NSNumber.valueOf(expired));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isRevoked() {
        if (data.containsKey(IsRevokedKey())) {
            NSNumber val = (NSNumber)data.get(IsRevokedKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setRevoked(boolean revoked) {
        data.put(IsRevokedKey(), NSNumber.valueOf(revoked));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isVolumePurchase() {
        if (data.containsKey(IsVolumePurchaseKey())) {
            NSNumber val = (NSNumber)data.get(IsVolumePurchaseKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public SKReceiptRefreshRequestOptions setVolumePurchase(boolean volumePurchase) {
        data.put(IsVolumePurchaseKey(), NSNumber.valueOf(volumePurchase));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsExpired", optional=true)
    protected static native NSString IsExpiredKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsRevoked", optional=true)
    protected static native NSString IsRevokedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsVolumePurchase", optional=true)
    protected static native NSString IsVolumePurchaseKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
