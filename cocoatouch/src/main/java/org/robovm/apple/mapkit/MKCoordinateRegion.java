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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKCoordinateRegion/*</name>*/ 
    extends /*<extends>*/Struct<MKCoordinateRegion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKCoordinateRegionPtr extends Ptr<MKCoordinateRegion, MKCoordinateRegionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MKCoordinateRegion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKCoordinateRegion() {}
    public MKCoordinateRegion(CLLocationCoordinate2D center, MKCoordinateSpan span) {
        this.center(center);
        this.span(span);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CLLocationCoordinate2D center();
    @StructMember(0) public native MKCoordinateRegion center(@ByVal CLLocationCoordinate2D center);
    @StructMember(1) public native @ByVal MKCoordinateSpan span();
    @StructMember(1) public native MKCoordinateRegion span(@ByVal MKCoordinateSpan span);
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="MKCoordinateRegionMakeWithDistance", optional=true)
    public static native @ByVal MKCoordinateRegion create(@ByVal CLLocationCoordinate2D centerCoordinate, double latitudinalMeters, double longitudinalMeters);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKCoordinateRegionForMapRect", optional=true)
    public static native @ByVal MKCoordinateRegion create(@ByVal MKMapRect rect);
    /*</methods>*/
}
