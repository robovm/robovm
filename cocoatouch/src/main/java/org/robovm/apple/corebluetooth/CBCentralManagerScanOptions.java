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
@Marshaler(CBCentralManagerScanOptions.Marshaler.class)
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBCentralManagerScanOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CBCentralManagerScanOptions toObject(Class<CBCentralManagerScanOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CBCentralManagerScanOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CBCentralManagerScanOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CBCentralManagerScanOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public CBCentralManagerScanOptions() {
    	this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(CBCentralManagerScanOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public boolean isAllowingDuplicates() {
        if (data.containsKey(AllowDuplicatesKey())) {
            NSNumber val = (NSNumber)data.get(AllowDuplicatesKey());
            return val.booleanValue();
        }
        return false;
    }
    public CBCentralManagerScanOptions setAllowsDuplicates(boolean allowDuplicates) {
        data.put(AllowDuplicatesKey(), NSNumber.valueOf(allowDuplicates));
        return this;
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
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CBCentralManagerScanOptions setSolicitedServiceUUIDs(NSArray<CBUUID> uuids) {
        data.put(SolicitedServiceUUIDsKey(), uuids);
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="CBCentralManagerScanOptionAllowDuplicatesKey", optional=true)
    protected static native NSString AllowDuplicatesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerScanOptionSolicitedServiceUUIDsKey", optional=true)
    protected static native NSString SolicitedServiceUUIDsKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
