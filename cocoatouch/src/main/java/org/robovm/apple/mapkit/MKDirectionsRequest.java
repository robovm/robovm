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
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKDirectionsRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKDirectionsRequestPtr extends Ptr<MKDirectionsRequest, MKDirectionsRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKDirectionsRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKDirectionsRequest() {}
    protected MKDirectionsRequest(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public MKDirectionsRequest(NSURL url) { super((SkipInit) null); initObject(init(url)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "transportType")
    public native MKDirectionsTransportType getTransportType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTransportType:")
    public native void setTransportType(MKDirectionsTransportType v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "requestsAlternateRoutes")
    public native boolean requestsAlternateRoutes();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setRequestsAlternateRoutes:")
    public native void setRequestsAlternateRoutes(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "departureDate")
    public native NSDate getDepartureDate();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setDepartureDate:")
    public native void setDepartureDate(NSDate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "arrivalDate")
    public native NSDate getArrivalDate();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setArrivalDate:")
    public native void setArrivalDate(NSDate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "source")
    public native MKMapItem getSource();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setSource:")
    public native void setSource(MKMapItem source);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "destination")
    public native MKMapItem getDestination();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setDestination:")
    public native void setDestination(MKMapItem destination);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithContentsOfURL:")
    protected native @Pointer long init(NSURL url);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "isDirectionsRequestURL:")
    public static native boolean isDirectionsRequestURL(NSURL url);
    /*</methods>*/
}
