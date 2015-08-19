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
package org.robovm.apple.corelocation;

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
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corebluetooth.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLLocationManagerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements CLLocationManagerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @NotImplemented("locationManager:didUpdateToLocation:fromLocation:")
    public void didUpdateToLocation(CLLocationManager manager, CLLocation newLocation, CLLocation oldLocation) {}
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("locationManager:didUpdateLocations:")
    public void didUpdateLocations(CLLocationManager manager, NSArray<CLLocation> locations) {}
    /**
     * @since Available in iOS 3.0 and later.
     */
    @NotImplemented("locationManager:didUpdateHeading:")
    public void didUpdateHeading(CLLocationManager manager, CLHeading newHeading) {}
    /**
     * @since Available in iOS 3.0 and later.
     */
    @NotImplemented("locationManagerShouldDisplayHeadingCalibration:")
    public boolean shouldDisplayHeadingCalibration(CLLocationManager manager) { return false; }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("locationManager:didDetermineState:forRegion:")
    public void didDetermineState(CLLocationManager manager, CLRegionState state, CLRegion region) {}
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("locationManager:didRangeBeacons:inRegion:")
    public void didRangeBeacons(CLLocationManager manager, NSArray<?> beacons, CLBeaconRegion region) {}
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("locationManager:rangingBeaconsDidFailForRegion:withError:")
    public void rangingBeaconsDidFail(CLLocationManager manager, CLBeaconRegion region, NSError error) {}
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("locationManager:didEnterRegion:")
    public void didEnterRegion(CLLocationManager manager, CLRegion region) {}
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("locationManager:didExitRegion:")
    public void didExitRegion(CLLocationManager manager, CLRegion region) {}
    @NotImplemented("locationManager:didFailWithError:")
    public void didFail(CLLocationManager manager, NSError error) {}
    /**
     * @since Available in iOS 4.0 and later.
     */
    @NotImplemented("locationManager:monitoringDidFailForRegion:withError:")
    public void monitoringDidFail(CLLocationManager manager, CLRegion region, NSError error) {}
    /**
     * @since Available in iOS 4.2 and later.
     */
    @NotImplemented("locationManager:didChangeAuthorizationStatus:")
    public void didChangeAuthorizationStatus(CLLocationManager manager, CLAuthorizationStatus status) {}
    /**
     * @since Available in iOS 5.0 and later.
     */
    @NotImplemented("locationManager:didStartMonitoringForRegion:")
    public void didStartMonitoring(CLLocationManager manager, CLRegion region) {}
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("locationManagerDidPauseLocationUpdates:")
    public void didPauseLocationUpdates(CLLocationManager manager) {}
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("locationManagerDidResumeLocationUpdates:")
    public void didResumeLocationUpdates(CLLocationManager manager) {}
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("locationManager:didFinishDeferredUpdatesWithError:")
    public void didFinishDeferredUpdates(CLLocationManager manager, NSError error) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("locationManager:didVisit:")
    public void didVisit(CLLocationManager manager, CLVisit visit) {}
    /*</methods>*/
}
