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
import org.robovm.apple.addressbook.*;
import org.robovm.apple.corebluetooth.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLLocationManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLLocationManagerPtr extends Ptr<CLLocationManager, CLLocationManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CLLocationManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CLLocationManager() {}
    protected CLLocationManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native CLLocationManagerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(CLLocationManagerDelegate v);
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "purpose")
    public native String getPurpose();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setPurpose:")
    public native void setPurpose(String v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "activityType")
    public native CLActivityType getActivityType();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setActivityType:")
    public native void setActivityType(CLActivityType v);
    @Property(selector = "distanceFilter")
    public native double getDistanceFilter();
    @Property(selector = "setDistanceFilter:")
    public native void setDistanceFilter(double v);
    @Property(selector = "desiredAccuracy")
    public native double getDesiredAccuracy();
    @Property(selector = "setDesiredAccuracy:")
    public native void setDesiredAccuracy(double v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "pausesLocationUpdatesAutomatically")
    public native boolean isPausesLocationUpdatesAutomatically();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setPausesLocationUpdatesAutomatically:")
    public native void setPausesLocationUpdatesAutomatically(boolean v);
    @Property(selector = "location")
    public native CLLocation getLocation();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "headingFilter")
    public native double getHeadingFilter();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setHeadingFilter:")
    public native void setHeadingFilter(double v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "headingOrientation")
    public native CLDeviceOrientation getHeadingOrientation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setHeadingOrientation:")
    public native void setHeadingOrientation(CLDeviceOrientation v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "heading")
    public native CLHeading getHeading();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "maximumRegionMonitoringDistance")
    public native double getMaximumRegionMonitoringDistance();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "monitoredRegions")
    public native NSSet<CLRegion> getMonitoredRegions();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "rangedRegions")
    public native NSSet<CLBeaconRegion> getRangedRegions();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCLDistanceFilterNone", optional=true)
    public static native double DistanceFilterNone();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CLTimeIntervalMax", optional=true)
    public static native double TimeIntervalMax();
    @GlobalValue(symbol="kCLHeadingFilterNone", optional=true)
    public static native double HeadingFilterNone();
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "requestWhenInUseAuthorization")
    public native void requestWhenInUseAuthorization();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "requestAlwaysAuthorization")
    public native void requestAlwaysAuthorization();
    @Method(selector = "startUpdatingLocation")
    public native void startUpdatingLocation();
    @Method(selector = "stopUpdatingLocation")
    public native void stopUpdatingLocation();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "startUpdatingHeading")
    public native void startUpdatingHeading();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "stopUpdatingHeading")
    public native void stopUpdatingHeading();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "dismissHeadingCalibrationDisplay")
    public native void dismissHeadingCalibrationDisplay();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "startMonitoringSignificantLocationChanges")
    public native void startMonitoringSignificantLocationChanges();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "stopMonitoringSignificantLocationChanges")
    public native void stopMonitoringSignificantLocationChanges();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "startMonitoringForRegion:desiredAccuracy:")
    public native void startMonitoring(CLRegion region, double accuracy);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "stopMonitoringForRegion:")
    public native void stopMonitoring(CLRegion region);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startMonitoringForRegion:")
    public native void startMonitoring(CLRegion region);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "requestStateForRegion:")
    public native void requestState(CLRegion region);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "startRangingBeaconsInRegion:")
    public native void startRangingBeacons(CLBeaconRegion region);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "stopRangingBeaconsInRegion:")
    public native void stopRangingBeacons(CLBeaconRegion region);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "allowDeferredLocationUpdatesUntilTraveled:timeout:")
    public native void allowDeferredLocationUpdatesUntil(double distance, double timeout);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "disallowDeferredLocationUpdates")
    public native void disallowDeferredLocationUpdates();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "locationServicesEnabled")
    public static native boolean isLocationServicesEnabled();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "headingAvailable")
    public static native boolean isHeadingAvailable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "significantLocationChangeMonitoringAvailable")
    public static native boolean isSignificantLocationChangeMonitoringAvailable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isMonitoringAvailableForClass:")
    public static native boolean isMonitoringAvailable(Class<? extends CLRegion> regionClass);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "regionMonitoringAvailable")
    public static native boolean isRegionMonitoringAvailable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isRangingAvailable")
    public static native boolean isRangingAvailable();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "authorizationStatus")
    public static native CLAuthorizationStatus getAuthorizationStatus();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "deferredLocationUpdatesAvailable")
    public static native boolean isDeferredLocationUpdatesAvailable();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "startMonitoringVisits")
    public native void startMonitoringVisits();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "stopMonitoringVisits")
    public native void stopMonitoringVisits();
    /*</methods>*/
}
