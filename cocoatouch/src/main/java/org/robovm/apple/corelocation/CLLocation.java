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
package org.robovm.apple.corelocation;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLLocation/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLLocationPtr extends Ptr<CLLocation, CLLocationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CLLocation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CLLocation() {}
    protected CLLocation(SkipInit skipInit) { super(skipInit); }
    public CLLocation(double latitude, double longitude) { super((SkipInit) null); initObject(initWithLatitude$longitude$(latitude, longitude)); }
    public CLLocation(@ByVal CLLocationCoordinate2D coordinate, double altitude, double hAccuracy, double vAccuracy, NSDate timestamp) { super((SkipInit) null); initObject(initWithCoordinate$altitude$horizontalAccuracy$verticalAccuracy$timestamp$(coordinate, altitude, hAccuracy, vAccuracy, timestamp)); }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public CLLocation(@ByVal CLLocationCoordinate2D coordinate, double altitude, double hAccuracy, double vAccuracy, double course, double speed, NSDate timestamp) { super((SkipInit) null); initObject(initWithCoordinate$altitude$horizontalAccuracy$verticalAccuracy$course$speed$timestamp$(coordinate, altitude, hAccuracy, vAccuracy, course, speed, timestamp)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "coordinate")
    public native @ByVal CLLocationCoordinate2D getCoordinate();
    @Property(selector = "altitude")
    public native double getAltitude();
    @Property(selector = "horizontalAccuracy")
    public native double getHorizontalAccuracy();
    @Property(selector = "verticalAccuracy")
    public native double getVerticalAccuracy();
    @Property(selector = "course")
    public native double getCourse();
    @Property(selector = "speed")
    public native double getSpeed();
    @Property(selector = "timestamp")
    public native NSDate getTimestamp();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithLatitude:longitude:")
    protected native @Pointer long initWithLatitude$longitude$(double latitude, double longitude);
    @Method(selector = "initWithCoordinate:altitude:horizontalAccuracy:verticalAccuracy:timestamp:")
    protected native @Pointer long initWithCoordinate$altitude$horizontalAccuracy$verticalAccuracy$timestamp$(@ByVal CLLocationCoordinate2D coordinate, double altitude, double hAccuracy, double vAccuracy, NSDate timestamp);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "initWithCoordinate:altitude:horizontalAccuracy:verticalAccuracy:course:speed:timestamp:")
    protected native @Pointer long initWithCoordinate$altitude$horizontalAccuracy$verticalAccuracy$course$speed$timestamp$(@ByVal CLLocationCoordinate2D coordinate, double altitude, double hAccuracy, double vAccuracy, double course, double speed, NSDate timestamp);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "distanceFromLocation:")
    public native double calculateDistanceFrom(CLLocation location);
    /*</methods>*/
}
