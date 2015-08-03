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
@Marshaler(/*<name>*/CBConnectPeripheralOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBConnectPeripheralOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CBConnectPeripheralOptions toObject(Class<CBConnectPeripheralOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CBConnectPeripheralOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CBConnectPeripheralOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CBConnectPeripheralOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CBConnectPeripheralOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CBConnectPeripheralOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CBConnectPeripheralOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (CBConnectPeripheralOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CBConnectPeripheralOptions(NSDictionary data) {
        super(data);
    }
    public CBConnectPeripheralOptions() {}
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
    public CBConnectPeripheralOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean notifiesOnConnection() {
        if (has(Keys.NotifyOnConnection())) {
            NSNumber val = (NSNumber) get(Keys.NotifyOnConnection());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CBConnectPeripheralOptions setNotifiesOnConnection(boolean notifiesOnConnection) {
        set(Keys.NotifyOnConnection(), NSNumber.valueOf(notifiesOnConnection));
        return this;
    }
    public boolean notifiesOnDisconnection() {
        if (has(Keys.NotifyOnDisconnection())) {
            NSNumber val = (NSNumber) get(Keys.NotifyOnDisconnection());
            return val.booleanValue();
        }
        return false;
    }
    public CBConnectPeripheralOptions setNotifiesOnDisconnection(boolean notifiesOnDisconnection) {
        set(Keys.NotifyOnDisconnection(), NSNumber.valueOf(notifiesOnDisconnection));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean notifiesOnNotification() {
        if (has(Keys.NotifyOnNotification())) {
            NSNumber val = (NSNumber) get(Keys.NotifyOnNotification());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CBConnectPeripheralOptions setNotifiesOnNotification(boolean notifiesOnNotification) {
        set(Keys.NotifyOnNotification(), NSNumber.valueOf(notifiesOnNotification));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreBluetooth")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnConnectionKey", optional=true)
        public static native NSString NotifyOnConnection();
        @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnDisconnectionKey", optional=true)
        public static native NSString NotifyOnDisconnection();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnNotificationKey", optional=true)
        public static native NSString NotifyOnNotification();
    }
    /*</keys>*/
}
