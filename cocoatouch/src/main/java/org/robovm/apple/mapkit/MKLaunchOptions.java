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
package org.robovm.apple.mapkit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MapKit")/*</annotations>*/
@Marshaler(/*<name>*/MKLaunchOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKLaunchOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MKLaunchOptions toObject(Class<MKLaunchOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MKLaunchOptions(o);
        }
        @MarshalsPointer
        public static long toNative(MKLaunchOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<MKLaunchOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MKLaunchOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new MKLaunchOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MKLaunchOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (MKLaunchOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    MKLaunchOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public MKLaunchOptions() {}
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
    public MKLaunchOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKDirectionsMode getDirectionsMode() {
        if (has(Keys.DirectionsMode())) {
            NSString val = (NSString) get(Keys.DirectionsMode());
            return MKDirectionsMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setDirectionsMode(MKDirectionsMode directionsMode) {
        set(Keys.DirectionsMode(), directionsMode.value());
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKMapType getMapType() {
        if (has(Keys.MapType())) {
            NSNumber val = (NSNumber) get(Keys.MapType());
            return MKMapType.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapType(MKMapType mapType) {
        set(Keys.MapType(), NSNumber.valueOf(mapType.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean showsTraffic() {
        if (has(Keys.ShowsTraffic())) {
            NSNumber val = (NSNumber) get(Keys.ShowsTraffic());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setShowsTraffic(boolean showsTraffic) {
        set(Keys.ShowsTraffic(), NSNumber.valueOf(showsTraffic));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CLLocationCoordinate2D getMapCenter() {
        if (has(Keys.MapCenter())) {
            NSData val = (NSData) get(Keys.MapCenter());
            return val.getStructData(CLLocationCoordinate2D.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapCenter(CLLocationCoordinate2D mapCenter) {
        set(Keys.MapCenter(), new NSData(mapCenter));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKCoordinateSpan getMapSpan() {
        if (has(Keys.MapSpan())) {
            NSData val = (NSData) get(Keys.MapSpan());
            return val.getStructData(MKCoordinateSpan.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapSpan(MKCoordinateSpan mapSpan) {
        set(Keys.MapSpan(), new NSData(mapSpan));
        return this;
    }
    /**
     * @since Available in iOS 7.1 and later.
     */
    public MKMapCamera getCamera() {
        if (has(Keys.Camera())) {
            MKMapCamera val = (MKMapCamera) get(Keys.Camera());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.1 and later.
     */
    public MKLaunchOptions setCamera(MKMapCamera camera) {
        set(Keys.Camera(), camera);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("MapKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsDirectionsModeKey", optional=true)
        public static native NSString DirectionsMode();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsMapTypeKey", optional=true)
        public static native NSString MapType();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsShowsTrafficKey", optional=true)
        public static native NSString ShowsTraffic();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsMapCenterKey", optional=true)
        public static native NSString MapCenter();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsMapSpanKey", optional=true)
        public static native NSString MapSpan();
        /**
         * @since Available in iOS 7.1 and later.
         */
        @GlobalValue(symbol="MKLaunchOptionsCameraKey", optional=true)
        public static native NSString Camera();
    }
    /*</keys>*/
}
