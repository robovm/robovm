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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMapPoint/*</name>*/ 
    extends /*<extends>*/Struct<MKMapPoint>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMapPointPtr extends Ptr<MKMapPoint, MKMapPointPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MKMapPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMapPoint() {}
    public MKMapPoint(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getX();
    @StructMember(0) public native MKMapPoint setX(double x);
    @StructMember(1) public native double getY();
    @StructMember(1) public native MKMapPoint setY(double y);
    /*</members>*/
    public boolean equalsTo(MKMapPoint other) {
        return getX() == other.getX() && getY() == other.getY();
    }
    public boolean equals(Object obj) {
        return obj instanceof MKMapPoint && equalsTo((MKMapPoint)obj);
    }
    
    public String toString() {
        return String.format("{%.1f, %.1f}", getX(), getY());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapPointForCoordinate", optional=true)
    public static native @ByVal MKMapPoint create(@ByVal CLLocationCoordinate2D coordinate);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CLLocationCoordinate2D toCoordinate() { return toCoordinate(this); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKCoordinateForMapPoint", optional=true)
    private static native @ByVal CLLocationCoordinate2D toCoordinate(@ByVal MKMapPoint mapPoint);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMetersPerMapPointAtLatitude", optional=true)
    public static native double getMetersPerMapPoint(double latitude);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMapPointsPerMeterAtLatitude", optional=true)
    public static native double getMapPointsPerMeter(double latitude);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKMetersBetweenMapPoints", optional=true)
    public static native double getMetersBetween(@ByVal MKMapPoint a, @ByVal MKMapPoint b);
    /*</methods>*/
}
