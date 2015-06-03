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
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLPlacemark/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLPlacemarkPtr extends Ptr<CLPlacemark, CLPlacemarkPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CLPlacemark.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CLPlacemark(SkipInit skipInit) { super(skipInit); }
    public CLPlacemark(CLPlacemark placemark) { super((SkipInit) null); initObject(init(placemark)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "location")
    public native CLLocation getLocation();
    @Property(selector = "region")
    public native CLRegion getRegion();
    @WeaklyLinked
    @Property(selector = "addressDictionary")
    public native ABPersonAddress getAddress();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "thoroughfare")
    public native String getThoroughfare();
    @Property(selector = "subThoroughfare")
    public native String getSubThoroughfare();
    @Property(selector = "locality")
    public native String getLocality();
    @Property(selector = "subLocality")
    public native String getSubLocality();
    @Property(selector = "administrativeArea")
    public native String getAdministrativeArea();
    @Property(selector = "subAdministrativeArea")
    public native String getSubAdministrativeArea();
    @Property(selector = "postalCode")
    public native String getPostalCode();
    @Property(selector = "ISOcountryCode")
    public native String getISOcountryCode();
    @Property(selector = "country")
    public native String getCountry();
    @Property(selector = "inlandWater")
    public native String getInlandWater();
    @Property(selector = "ocean")
    public native String getOcean();
    @Property(selector = "areasOfInterest")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAreasOfInterest();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPlacemark:")
    protected native @Pointer long init(CLPlacemark placemark);
    /*</methods>*/
}
