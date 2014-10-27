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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLCircularRegion/*</name>*/ 
    extends /*<extends>*/CLRegion/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLCircularRegionPtr extends Ptr<CLCircularRegion, CLCircularRegionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CLCircularRegion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CLCircularRegion() {}
    protected CLCircularRegion(SkipInit skipInit) { super(skipInit); }
    public CLCircularRegion(@ByVal CLLocationCoordinate2D center, double radius, String identifier) { super((SkipInit) null); initObject(initWithCenter$radius$identifier$(center, radius, identifier)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "center")
    public native @ByVal CLLocationCoordinate2D getCenter();
    @Property(selector = "radius")
    public native double getRadius();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCenter:radius:identifier:")
    protected native @Pointer long initWithCenter$radius$identifier$(@ByVal CLLocationCoordinate2D center, double radius, String identifier);
    /*</methods>*/
}
