/*
 * Copyright (C) 2014 Trillian Mobile AB
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
@Marshaler(CBCentralManagerRestoredState.Marshaler.class)
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBCentralManagerRestoredState/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CBCentralManagerRestoredState toObject(Class<CBCentralManagerRestoredState> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CBCentralManagerRestoredState(o);
        }
        @MarshalsPointer
        public static long toNative(CBCentralManagerRestoredState o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CBCentralManagerRestoredState(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(CBCentralManagerRestoredState.class); }/*</bind>*/
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
    @SuppressWarnings("unchecked")
    public NSArray<CBPeripheral> getPeripherals() {
        if (data.containsKey(PeripheralsKey())) {
            NSArray<CBPeripheral> val = (NSArray<CBPeripheral>)data.get(PeripheralsKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<CBUUID> getScanServices() {
        if (data.containsKey(ScanServicesKey())) {
            NSArray<CBUUID> val = (NSArray<CBUUID>)data.get(ScanServicesKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public CBCentralManagerScanOptions getScanOptions() {
        if (data.containsKey(ScanOptionsKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>)data.get(ScanOptionsKey());
            return new CBCentralManagerScanOptions(val);
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStatePeripheralsKey", optional=true)
    protected static native NSString PeripheralsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStateScanServicesKey", optional=true)
    protected static native NSString ScanServicesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStateScanOptionsKey", optional=true)
    protected static native NSString ScanOptionsKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
