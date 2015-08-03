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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
@Marshaler(/*<name>*/CBPeripheralManagerOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBPeripheralManagerOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CBPeripheralManagerOptions toObject(Class<CBPeripheralManagerOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CBPeripheralManagerOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CBPeripheralManagerOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CBPeripheralManagerOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CBPeripheralManagerOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CBPeripheralManagerOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CBPeripheralManagerOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (CBPeripheralManagerOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CBPeripheralManagerOptions(NSDictionary data) {
        super(data);
    }
    public CBPeripheralManagerOptions() {}
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
    public CBPeripheralManagerOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean showsPowerAlert() {
        if (has(Keys.ShowPowerAlert())) {
            NSNumber val = (NSNumber) get(Keys.ShowPowerAlert());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CBPeripheralManagerOptions setShowsPowerAlert(boolean showsPowerAlert) {
        set(Keys.ShowPowerAlert(), NSNumber.valueOf(showsPowerAlert));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getRestoreIdentifier() {
        if (has(Keys.RestoreIdentifier())) {
            NSString val = (NSString) get(Keys.RestoreIdentifier());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CBPeripheralManagerOptions setRestoreIdentifier(String restoreIdentifier) {
        set(Keys.RestoreIdentifier(), new NSString(restoreIdentifier));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreBluetooth")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CBPeripheralManagerOptionShowPowerAlertKey", optional=true)
        public static native NSString ShowPowerAlert();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CBPeripheralManagerOptionRestoreIdentifierKey", optional=true)
        public static native NSString RestoreIdentifier();
    }
    /*</keys>*/
}
