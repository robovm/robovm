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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMultiPoint/*</name>*/ 
    extends /*<extends>*/MKShape/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKMultiPointPtr extends Ptr<MKMultiPoint, MKMultiPointPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKMultiPoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMultiPoint() {}
    protected MKMultiPoint(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "pointCount")
    public native @MachineSizedUInt long getPointCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    public MKMapPoint[] getPoints() {
        return getPoints0().toArray((int) getPointCount());
    }
    public CLLocationCoordinate2D[] getCoordinates(@ByVal NSRange range) {
        CLLocationCoordinate2D coords = Struct.allocate(CLLocationCoordinate2D.class, (int)range.getLength());
        getCoordinates0(coords, range);
        return coords.toArray((int) range.getLength());
    }
    /*<methods>*/
    @Method(selector = "points")
    protected native MKMapPoint getPoints0();
    @Method(selector = "getCoordinates:range:")
    protected native void getCoordinates0(CLLocationCoordinate2D coords, @ByVal NSRange range);
    /*</methods>*/
}
