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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CBAdvertisementData.Marshaler.class)
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBAdvertisementData/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CBAdvertisementData toObject(Class<CBAdvertisementData> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CBAdvertisementData(o);
        }
        @MarshalsPointer
        public static long toNative(CBAdvertisementData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CBAdvertisementData(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CBAdvertisementData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    public String getLocalName() {
        if (data.containsKey(LocalNameKey())) {
            NSString val = (NSString)data.get(LocalNameKey());
            return val.toString();
        }
        return null;
    }
    public double getTxPowerLevel() {
        if (data.containsKey(TxPowerLevelKey())) {
            NSNumber val = (NSNumber)data.get(TxPowerLevelKey());
            return val.doubleValue();
        }
        return 0;
    }
    @SuppressWarnings("unchecked")
    public NSArray<CBUUID> getServiceUUIDs() {
        if (data.containsKey(ServiceUUIDsKey())) {
            NSArray<CBUUID> val = (NSArray<CBUUID>)data.get(ServiceUUIDsKey());
            return val;
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public NSDictionary<CBUUID, NSData> getServiceData() {
        if (data.containsKey(ServiceDataKey())) {
            NSDictionary<CBUUID, NSData> val = (NSDictionary<CBUUID, NSData>)data.get(ServiceDataKey());
            return val;
        }
        return null;
    }
    public NSData getManufacturerData() {
        if (data.containsKey(ManufacturerDataKey())) {
            NSData val = (NSData)data.get(ManufacturerDataKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<CBUUID> getOverflowServiceUUIDs() {
        if (data.containsKey(OverflowServiceUUIDsKey())) {
            NSArray<CBUUID> val = (NSArray<CBUUID>)data.get(OverflowServiceUUIDsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isConnectable() {
        if (data.containsKey(IsConnectable())) {
            NSNumber val = (NSNumber)data.get(IsConnectable());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<CBUUID> getSolicitedServiceUUIDs() {
        if (data.containsKey(SolicitedServiceUUIDsKey())) {
            NSArray<CBUUID> val = (NSArray<CBUUID>)data.get(SolicitedServiceUUIDsKey());
            return val;
        }
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="CBAdvertisementDataLocalNameKey", optional=true)
    protected static native NSString LocalNameKey();
    @GlobalValue(symbol="CBAdvertisementDataTxPowerLevelKey", optional=true)
    protected static native NSString TxPowerLevelKey();
    @GlobalValue(symbol="CBAdvertisementDataServiceUUIDsKey", optional=true)
    protected static native NSString ServiceUUIDsKey();
    @GlobalValue(symbol="CBAdvertisementDataServiceDataKey", optional=true)
    protected static native NSString ServiceDataKey();
    @GlobalValue(symbol="CBAdvertisementDataManufacturerDataKey", optional=true)
    protected static native NSString ManufacturerDataKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataOverflowServiceUUIDsKey", optional=true)
    protected static native NSString OverflowServiceUUIDsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataIsConnectable", optional=true)
    protected static native NSString IsConnectable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataSolicitedServiceUUIDsKey", optional=true)
    protected static native NSString SolicitedServiceUUIDsKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
