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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKPolygon/*</name>*/ 
    extends /*<extends>*/MKMultiPoint/*</extends>*/ 
    /*<implements>*/implements MKOverlay/*</implements>*/ {

    /*<ptr>*/public static class MKPolygonPtr extends Ptr<MKPolygon, MKPolygonPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKPolygon.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKPolygon() {}
    protected MKPolygon(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "interiorPolygons")
    public native NSArray<MKPolygon> getInteriorPolygons();
    @Property(selector = "coordinate")
    public native @ByVal CLLocationCoordinate2D getCoordinate();
    @Property(selector = "boundingMapRect")
    public native @ByVal MKMapRect getBoundingMapRect();
    /*</properties>*/
    /*<members>*//*</members>*/
    public static MKPolygon create(MKMapPoint[] points) {
        MKMapPoint first = Struct.allocate(MKMapPoint.class, points.length);
        first.update(points);
        return create(first, points.length);
    }
    public static MKPolygon create(MKMapPoint[] points, NSArray<MKPolygon> interiorPolygons) {
        MKMapPoint first = Struct.allocate(MKMapPoint.class, points.length);
        first.update(points);
        return create(first, points.length, interiorPolygons);
    }
    public static MKPolygon create(CLLocationCoordinate2D[] coords) {
        CLLocationCoordinate2D first = Struct.allocate(CLLocationCoordinate2D.class, coords.length);
        first.update(coords);
        return create(first, coords.length);
    }
    public static MKPolygon create(CLLocationCoordinate2D[] coords, NSArray<MKPolygon> interiorPolygons) {
        CLLocationCoordinate2D first = Struct.allocate(CLLocationCoordinate2D.class, coords.length);
        first.update(coords);
        return create(first, coords.length, interiorPolygons);
    }
    /*<methods>*/
    @Method(selector = "polygonWithPoints:count:")
    private static native MKPolygon create(MKMapPoint points, @MachineSizedUInt long count);
    @Method(selector = "polygonWithPoints:count:interiorPolygons:")
    private static native MKPolygon create(MKMapPoint points, @MachineSizedUInt long count, NSArray<MKPolygon> interiorPolygons);
    @Method(selector = "polygonWithCoordinates:count:")
    private static native MKPolygon create(CLLocationCoordinate2D coords, @MachineSizedUInt long count);
    @Method(selector = "polygonWithCoordinates:count:interiorPolygons:")
    private static native MKPolygon create(CLLocationCoordinate2D coords, @MachineSizedUInt long count, NSArray<MKPolygon> interiorPolygons);
    @Method(selector = "intersectsMapRect:")
    public native boolean intersects(@ByVal MKMapRect mapRect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "canReplaceMapContent")
    public native boolean canReplaceMapContent();
    /*</methods>*/
}
