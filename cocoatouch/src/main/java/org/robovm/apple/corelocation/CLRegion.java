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
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreLocation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CLRegion/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CLRegionPtr extends Ptr<CLRegion, CLRegionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CLRegion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CLRegion() {}
    protected CLRegion(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public CLRegion(@ByVal CLLocationCoordinate2D center, double radius, String identifier) { super((SkipInit) null); initObject(initCircularRegionWithCenter$radius$identifier$(center, radius, identifier)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "center")
    public native @ByVal CLLocationCoordinate2D getCenter();
    @Property(selector = "radius")
    public native double getRadius();
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "notifyOnEntry")
    public native boolean isNotifyOnEntry();
    @Property(selector = "setNotifyOnEntry:")
    public native void setNotifyOnEntry(boolean v);
    @Property(selector = "notifyOnExit")
    public native boolean isNotifyOnExit();
    @Property(selector = "setNotifyOnExit:")
    public native void setNotifyOnExit(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "initCircularRegionWithCenter:radius:identifier:")
    protected native @Pointer long initCircularRegionWithCenter$radius$identifier$(@ByVal CLLocationCoordinate2D center, double radius, String identifier);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "containsCoordinate:")
    public native boolean containsCoordinate(@ByVal CLLocationCoordinate2D coordinate);
    /*</methods>*/
}
