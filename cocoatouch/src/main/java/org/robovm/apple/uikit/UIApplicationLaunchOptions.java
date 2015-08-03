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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(/*<name>*/UIApplicationLaunchOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIApplicationLaunchOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static UIApplicationLaunchOptions toObject(Class<UIApplicationLaunchOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<UIApplicationLaunchOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<UIApplicationLaunchOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new UIApplicationLaunchOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<UIApplicationLaunchOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (UIApplicationLaunchOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    UIApplicationLaunchOptions(NSDictionary data) {
        super(data);
    }
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
    

    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSURL getURL() {
        if (has(Keys.URL())) {
            NSURL val = (NSURL) get(Keys.URL());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getSourceApplication() {
        if (has(Keys.SourceApplication())) {
            NSString val = (NSString) get(Keys.SourceApplication());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public UILocalNotification getLocalNotification() {
        if (has(Keys.LocalNotification())) {
            UILocalNotification val = (UILocalNotification) get(Keys.LocalNotification());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public NSPropertyList getAnnotation() {
        if (has(Keys.Annotation())) {
            NSPropertyList val = (NSPropertyList) get(Keys.Annotation());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isLocationStart() {
        if (has(Keys.Location())) {
            NSNumber val = (NSNumber) get(Keys.Location());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public List<String> getNewsstandDownloadIdentifiers() {
        if (has(Keys.NewsstandDownloads())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.NewsstandDownloads());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public List<String> getBluetoothCentralIdentifiers() {
        if (has(Keys.BluetoothCentrals())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.BluetoothCentrals());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public List<String> getBluetoothPeripheralIdentifiers() {
        if (has(Keys.BluetoothPeripherals())) {
            NSArray<NSString> val = (NSArray<NSString>) get(Keys.BluetoothPeripherals());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public UIApplicationLaunchOptionsUserActivityInfo getUserActivityInfo() {
        if (has(Keys.UserActivityDictionary())) {
            NSDictionary val = (NSDictionary) get(Keys.UserActivityDictionary());
            return new UIApplicationLaunchOptionsUserActivityInfo(val);
        }
        return null;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public UIRemoteNotification getRemoteNotification() {
        if (has(Keys.RemoteNotification())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) get(Keys.RemoteNotification());
            return new UIRemoteNotification(val);
        }
        return null;
    }
    
    /*<keys>*/
    @Library("UIKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsURLKey", optional=true)
        public static native NSString URL();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsSourceApplicationKey", optional=true)
        public static native NSString SourceApplication();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsRemoteNotificationKey", optional=true)
        public static native NSString RemoteNotification();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsLocalNotificationKey", optional=true)
        public static native NSString LocalNotification();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsAnnotationKey", optional=true)
        public static native NSString Annotation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsLocationKey", optional=true)
        public static native NSString Location();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsNewsstandDownloadsKey", optional=true)
        public static native NSString NewsstandDownloads();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothCentralsKey", optional=true)
        public static native NSString BluetoothCentrals();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsBluetoothPeripheralsKey", optional=true)
        public static native NSString BluetoothPeripherals();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="UIApplicationLaunchOptionsUserActivityDictionaryKey", optional=true)
        public static native NSString UserActivityDictionary();
    }
    /*</keys>*/
}
