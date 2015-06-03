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
@Marshaler(CBConnectPeripheralOptions.Marshaler.class)
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBConnectPeripheralOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static CBConnectPeripheralOptions toObject(Class<CBConnectPeripheralOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected CBConnectPeripheralOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public CBConnectPeripheralOptions() {
    	this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(CBConnectPeripheralOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isNotifyingOnConnection() {
        if (data.containsKey(NotifyOnConnectionKey())) {
            NSNumber val = (NSNumber)data.get(NotifyOnConnectionKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CBConnectPeripheralOptions setNotifyOnConnection(boolean notify) {
        data.put(NotifyOnConnectionKey(), NSNumber.valueOf(notify));
        return this;
    }
    public boolean isNotifyingOnDisconnection() {
        if (data.containsKey(NotifyOnDisconnectionKey())) {
            NSNumber val = (NSNumber)data.get(NotifyOnDisconnectionKey());
            return val.booleanValue();
        }
        return false;
    }
    public CBConnectPeripheralOptions setNotifyOnDisconnection(boolean notify) {
        data.put(NotifyOnDisconnectionKey(), NSNumber.valueOf(notify));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isNotifyingOnNotification() {
        if (data.containsKey(NotifyOnNotificationKey())) {
            NSNumber val = (NSNumber)data.get(NotifyOnNotificationKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CBConnectPeripheralOptions setNotifyOnNotification(boolean notify) {
        data.put(NotifyOnNotificationKey(), NSNumber.valueOf(notify));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnConnectionKey", optional=true)
    protected static native NSString NotifyOnConnectionKey();
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnDisconnectionKey", optional=true)
    protected static native NSString NotifyOnDisconnectionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnNotificationKey", optional=true)
    protected static native NSString NotifyOnNotificationKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
