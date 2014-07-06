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
 * @since Available in iOS 3.0 and later.
 * @deprecated Deprecated in iOS 5.0.
 */
@Deprecated
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKReverseGeocoder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKReverseGeocoderPtr extends Ptr<MKReverseGeocoder, MKReverseGeocoderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKReverseGeocoder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKReverseGeocoder() {}
    protected MKReverseGeocoder(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    public MKReverseGeocoder(@ByVal CLLocationCoordinate2D coordinate) { super((SkipInit) null); initObject(init(coordinate)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Property(selector = "delegate")
    public native MKReverseGeocoderDelegate getDelegate();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MKReverseGeocoderDelegate v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Property(selector = "coordinate")
    public native @ByVal CLLocationCoordinate2D getCoordinate();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Property(selector = "placemark")
    public native MKPlacemark getPlacemark();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Property(selector = "isQuerying")
    public native boolean isQuerying();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "initWithCoordinate:")
    protected native @Pointer long init(@ByVal CLLocationCoordinate2D coordinate);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "start")
    public native void start();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "cancel")
    public native void cancel();
    /*</methods>*/
}
