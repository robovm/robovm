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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(MKLaunchOptions.Marshaler.class)
/*<annotations>*/@Library("MapKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKLaunchOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
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

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public MKLaunchOptions() {
        this.data = new NSMutableDictionary<>();
    }
    protected MKLaunchOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(MKLaunchOptions.class); }/*</bind>*/
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
    public MKDirectionsMode getDirectionsMode() {
        if (data.containsKey(DirectionsModeKey())) {
            NSString val = (NSString)data.get(DirectionsModeKey());
            return MKDirectionsMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setDirectionsMode(MKDirectionsMode mode) {
        data.put(DirectionsModeKey(), mode.value());
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKMapType getMapType() {
        if (data.containsKey(MapTypeKey())) {
            NSNumber val = (NSNumber)data.get(MapTypeKey());
            return MKMapType.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapType(MKMapType type) {
        data.put(MapTypeKey(), NSNumber.valueOf((int)type.value()));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isShowingTraffic() {
        if (data.containsKey(ShowsTrafficKey())) {
            NSNumber val = (NSNumber)data.get(ShowsTrafficKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setShowsTraffic(boolean showsTraffic) {
        data.put(ShowsTrafficKey(), NSNumber.valueOf(showsTraffic));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public @ByVal CLLocationCoordinate2D getMapCenter() {
        if (data.containsKey(MapCenterKey())) {
            NSValue val = (NSValue)data.get(MapCenterKey());
            return NSValueExtensions.getMKCoordinateValue(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapCenter(@ByVal CLLocationCoordinate2D center) {
        data.put(MapCenterKey(), NSValueExtensions.create(center));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public @ByVal MKCoordinateSpan getMapSpan() {
        if (data.containsKey(MapSpanKey())) {
            NSValue val = (NSValue)data.get(MapSpanKey());
            return NSValueExtensions.getMKCoordinateSpanValue(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKLaunchOptions setMapSpan(@ByVal MKCoordinateSpan span) {
        data.put(MapSpanKey(), NSValueExtensions.create(span));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public MKMapCamera getCamera() {
        if (data.containsKey(CameraKey())) {
            MKMapCamera val = (MKMapCamera)data.get(CameraKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public MKLaunchOptions setCamera(MKMapCamera camera) {
        data.put(CameraKey(), camera);
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeKey", optional=true)
    protected static native NSString DirectionsModeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapTypeKey", optional=true)
    protected static native NSString MapTypeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsShowsTrafficKey", optional=true)
    protected static native NSString ShowsTrafficKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapCenterKey", optional=true)
    protected static native NSString MapCenterKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapSpanKey", optional=true)
    protected static native NSString MapSpanKey();
    /**
     * @since Available in iOS 7.1 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsCameraKey", optional=true)
    protected static native NSString CameraKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
