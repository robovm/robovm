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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UIApplicationLaunchOptions.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIApplicationLaunchOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIApplicationLaunchOptions toObject(Class<UIApplicationLaunchOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIApplicationLaunchOptions(o);
        }
        @MarshalsPointer
        public static long toNative(UIApplicationLaunchOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected UIApplicationLaunchOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(UIApplicationLaunchOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSURL getURL() {
        if (data.containsKey(URLKey())) {
            NSURL val = (NSURL) data.get(URLKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getSourceApplication() {
        if (data.containsKey(SourceApplicationKey())) {
            NSString val = (NSString) data.get(SourceApplicationKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public UIRemoteNotification getRemoteNotication() {
        if (data.containsKey(RemoteNotificationKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(RemoteNotificationKey());
            return new UIRemoteNotification(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public UILocalNotification getLocalNotification() {
        if (data.containsKey(LocalNotificationKey())) {
            UILocalNotification val = (UILocalNotification) data.get(LocalNotificationKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSPropertyList getAnnotation() {
        if (data.containsKey(AnnotationKey())) {
            NSPropertyList val = (NSPropertyList) data.get(AnnotationKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isLocationStart() {
        if (data.containsKey(LocationKey())) {
            NSNumber val = (NSNumber) data.get(LocationKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getNewsstandDownloadIdentifiers() {
        if (data.containsKey(NewsstandDownloadsKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(NewsstandDownloadsKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getBluetoothCentralIdentifiers() {
        if (data.containsKey(BluetoothCentralsKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(BluetoothCentralsKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getBluetoothPeripheralIdentifiers() {
        if (data.containsKey(BluetoothPeripheralsKey())) {
            NSArray<NSString> val = (NSArray<NSString>) data.get(BluetoothPeripheralsKey());
            return val.asStringList();
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsURLKey", optional=true)
    public static native NSString URLKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsSourceApplicationKey", optional=true)
    public static native NSString SourceApplicationKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsRemoteNotificationKey", optional=true)
    public static native NSString RemoteNotificationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocalNotificationKey", optional=true)
    public static native NSString LocalNotificationKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsAnnotationKey", optional=true)
    public static native NSString AnnotationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsLocationKey", optional=true)
    public static native NSString LocationKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsNewsstandDownloadsKey", optional=true)
    public static native NSString NewsstandDownloadsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothCentralsKey", optional=true)
    public static native NSString BluetoothCentralsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothPeripheralsKey", optional=true)
    public static native NSString BluetoothPeripheralsKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
