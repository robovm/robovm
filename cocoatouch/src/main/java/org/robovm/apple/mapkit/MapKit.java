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
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MapKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MapKit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="MKAnnotationCalloutInfoDidChangeNotification", optional=true)
    public static native NSString AnnotationCalloutInfoDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MKMapSizeWorld", optional=true)
    public static native @ByVal MKMapSize MapSizeWorld();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MKMapRectWorld", optional=true)
    public static native @ByVal MKMapRect MapRectWorld();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MKMapRectNull", optional=true)
    public static native @ByVal MKMapRect MapRectNull();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeKey", optional=true)
    public static native NSString LaunchOptionsDirectionsModeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapTypeKey", optional=true)
    public static native NSString LaunchOptionsMapTypeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsShowsTrafficKey", optional=true)
    public static native NSString LaunchOptionsShowsTrafficKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeDriving", optional=true)
    public static native NSString LaunchOptionsDirectionsModeDriving();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsDirectionsModeWalking", optional=true)
    public static native NSString LaunchOptionsDirectionsModeWalking();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapCenterKey", optional=true)
    public static native NSString LaunchOptionsMapCenterKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsMapSpanKey", optional=true)
    public static native NSString LaunchOptionsMapSpanKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="MKLaunchOptionsCameraKey", optional=true)
    public static native NSString LaunchOptionsCameraKey();
    @GlobalValue(symbol="MKErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKRoadWidthAtZoomScale", optional=true)
    public static native @MachineSizedFloat double getRoadWidth(@MachineSizedFloat double zoomScale);
    /*</methods>*/
}
