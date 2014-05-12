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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/CLLocationManagerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "locationManager:didUpdateToLocation:fromLocation:")
    void didUpdateToLocation(CLLocationManager manager, CLLocation newLocation, CLLocation oldLocation);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "locationManager:didUpdateLocations:")
    void didUpdateLocations(CLLocationManager manager, NSArray<?> locations);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "locationManager:didUpdateHeading:")
    void didUpdateHeading(CLLocationManager manager, CLHeading newHeading);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "locationManagerShouldDisplayHeadingCalibration:")
    boolean shouldDisplayHeadingCalibration(CLLocationManager manager);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "locationManager:didDetermineState:forRegion:")
    void didDetermineState(CLLocationManager manager, CLRegionState state, CLRegion region);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "locationManager:didRangeBeacons:inRegion:")
    void didRangeBeacons(CLLocationManager manager, NSArray<?> beacons, CLBeaconRegion region);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "locationManager:rangingBeaconsDidFailForRegion:withError:")
    void rangingBeaconsDidFail(CLLocationManager manager, CLBeaconRegion region, NSError error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "locationManager:didEnterRegion:")
    void didEnterRegion(CLLocationManager manager, CLRegion region);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "locationManager:didExitRegion:")
    void didExitRegion(CLLocationManager manager, CLRegion region);
    @Method(selector = "locationManager:didFailWithError:")
    void didFail(CLLocationManager manager, NSError error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "locationManager:monitoringDidFailForRegion:withError:")
    void monitoringDidFail(CLLocationManager manager, CLRegion region, NSError error);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "locationManager:didChangeAuthorizationStatus:")
    void didChangeAuthorizationStatus(CLLocationManager manager, CLAuthorizationStatus status);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "locationManager:didStartMonitoringForRegion:")
    void didStartMonitoring(CLLocationManager manager, CLRegion region);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "locationManagerDidPauseLocationUpdates:")
    void didPauseLocationUpdates(CLLocationManager manager);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "locationManagerDidResumeLocationUpdates:")
    void didResumeLocationUpdates(CLLocationManager manager);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "locationManager:didFinishDeferredUpdatesWithError:")
    void didFinishDeferredUpdates(CLLocationManager manager, NSError error);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
