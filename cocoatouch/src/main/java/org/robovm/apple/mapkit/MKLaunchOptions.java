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
/*<annotations>*/@Library("MapKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKLaunchOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    NSMutableDictionary<NSString, NSObject> options;
    
    public MKLaunchOptions() {
        this.options = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(MKLaunchOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKDirectionsMode getDirectionsMode() {
        if (options.containsKey(DirectionsModeKey())) {
            NSString val = (NSString)options.get(DirectionsModeKey());
            return MKDirectionsMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setDirectionsMode(MKDirectionsMode mode) {
        options.put(DirectionsModeKey(), mode.value());
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKMapType getMapType() {
        if (options.containsKey(MapTypeKey())) {
            NSNumber val = (NSNumber)options.get(MapTypeKey());
            return MKMapType.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setMapType(MKMapType type) {
        options.put(MapTypeKey(), NSNumber.valueOf((int)type.value()));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isShowingTraffic() {
        if (options.containsKey(ShowsTrafficKey())) {
            NSNumber val = (NSNumber)options.get(ShowsTrafficKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setShowsTraffic(boolean showsTraffic) {
        options.put(ShowsTrafficKey(), NSNumber.valueOf(showsTraffic));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public @ByVal CLLocationCoordinate2D getMapCenter() {
        if (options.containsKey(MapCenterKey())) {
            NSValue val = (NSValue)options.get(MapCenterKey());
            return null; // TODO get value from nsvalue
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setMapCenter(@ByVal CLLocationCoordinate2D center) {
//        options.put(MapCenterKey(), ); TODO put center in nsvalue
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public @ByVal MKCoordinateSpan getMapSpan() {
        if (options.containsKey(MapSpanKey())) {
            NSValue val = (NSValue)options.get(MapSpanKey());
            return null; // TODO get value from nsvalue
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setMapSpan(@ByVal MKCoordinateSpan span) {
//        options.put(MapSpanKey(), ); TODO put span in nsvalue
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public MKMapCamera getCamera() {
        if (options.containsKey(CameraKey())) {
            MKMapCamera val = (MKMapCamera)options.get(CameraKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setCamera(MKMapCamera camera) {
        options.put(CameraKey(), camera);
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
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsCameraKey", optional=true)
    protected static native NSString CameraKey();
    /*</methods>*/
}
