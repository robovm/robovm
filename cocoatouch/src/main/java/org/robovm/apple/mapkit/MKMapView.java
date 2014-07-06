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
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKMapView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class MKMapViewPtr extends Ptr<MKMapView, MKMapViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKMapView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKMapView() {}
    protected MKMapView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native MKMapViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MKMapViewDelegate v);
    @Property(selector = "mapType")
    public native MKMapType getMapType();
    @Property(selector = "setMapType:")
    public native void setMapType(MKMapType v);
    @Property(selector = "region")
    public native @ByVal MKCoordinateRegion getRegion();
    @Property(selector = "setRegion:")
    public native void setRegion(@ByVal MKCoordinateRegion v);
    @Property(selector = "centerCoordinate")
    public native @ByVal CLLocationCoordinate2D getCenterCoordinate();
    @Property(selector = "setCenterCoordinate:")
    public native void setCenterCoordinate(@ByVal CLLocationCoordinate2D v);
    @Property(selector = "visibleMapRect")
    public native @ByVal MKMapRect getVisibleMapRect();
    @Property(selector = "setVisibleMapRect:")
    public native void setVisibleMapRect(@ByVal MKMapRect v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "camera")
    public native MKMapCamera getCamera();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setCamera:")
    public native void setCamera(MKMapCamera v);
    @Property(selector = "isZoomEnabled")
    public native boolean isZoomEnabled();
    @Property(selector = "setZoomEnabled:")
    public native void setZoomEnabled(boolean v);
    @Property(selector = "isScrollEnabled")
    public native boolean isScrollEnabled();
    @Property(selector = "setScrollEnabled:")
    public native void setScrollEnabled(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isRotateEnabled")
    public native boolean isRotateEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setRotateEnabled:")
    public native void setRotateEnabled(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isPitchEnabled")
    public native boolean isPitchEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPitchEnabled:")
    public native void setPitchEnabled(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "showsPointsOfInterest")
    public native boolean isShowsPointsOfInterest();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setShowsPointsOfInterest:")
    public native void setShowsPointsOfInterest(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "showsBuildings")
    public native boolean isShowsBuildings();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setShowsBuildings:")
    public native void setShowsBuildings(boolean v);
    @Property(selector = "showsUserLocation")
    public native boolean isShowsUserLocation();
    @Property(selector = "setShowsUserLocation:")
    public native void setShowsUserLocation(boolean v);
    @Property(selector = "userLocation")
    public native MKUserLocation getUserLocation();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "userTrackingMode")
    public native MKUserTrackingMode getUserTrackingMode();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setUserTrackingMode:")
    public native void setUserTrackingMode(MKUserTrackingMode v);
    @Property(selector = "isUserLocationVisible")
    public native boolean isUserLocationVisible();
    @Property(selector = "annotations")
    public native NSArray<NSObject> getAnnotations();
    @Property(selector = "selectedAnnotations")
    public native NSArray<NSObject> getSelectedAnnotations();
    @Property(selector = "setSelectedAnnotations:")
    public native void setSelectedAnnotations(NSArray<NSObject> v);
    @Property(selector = "annotationVisibleRect")
    public native @ByVal CGRect getAnnotationVisibleRect();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "overlays")
    public native NSArray<NSObject> getOverlays();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setRegion:animated:")
    public native void setRegion(@ByVal MKCoordinateRegion region, boolean animated);
    @Method(selector = "setCenterCoordinate:animated:")
    public native void setCenterCoordinate(@ByVal CLLocationCoordinate2D coordinate, boolean animated);
    @Method(selector = "regionThatFits:")
    public native @ByVal MKCoordinateRegion getRegionThatFits(@ByVal MKCoordinateRegion region);
    @Method(selector = "setVisibleMapRect:animated:")
    public native void setVisibleMapRect(@ByVal MKMapRect mapRect, boolean animate);
    @Method(selector = "mapRectThatFits:")
    public native @ByVal MKMapRect getMapRectThatFits(@ByVal MKMapRect mapRect);
    @Method(selector = "setVisibleMapRect:edgePadding:animated:")
    public native void setVisibleMapRect(@ByVal MKMapRect mapRect, @ByVal UIEdgeInsets insets, boolean animate);
    @Method(selector = "mapRectThatFits:edgePadding:")
    public native @ByVal MKMapRect getMapRectThatFits(@ByVal MKMapRect mapRect, @ByVal UIEdgeInsets insets);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setCamera:animated:")
    public native void setCamera(MKMapCamera camera, boolean animated);
    @Method(selector = "convertCoordinate:toPointToView:")
    public native @ByVal CGPoint convertCoordinate(@ByVal CLLocationCoordinate2D coordinate, UIView view);
    @Method(selector = "convertPoint:toCoordinateFromView:")
    public native @ByVal CLLocationCoordinate2D convertPoint(@ByVal CGPoint point, UIView view);
    @Method(selector = "convertRegion:toRectToView:")
    public native @ByVal CGRect convertRegion(@ByVal MKCoordinateRegion region, UIView view);
    @Method(selector = "convertRect:toRegionFromView:")
    public native @ByVal MKCoordinateRegion convertRect(@ByVal CGRect rect, UIView view);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setUserTrackingMode:animated:")
    public native void setUserTrackingMode(MKUserTrackingMode mode, boolean animated);
    @Method(selector = "addAnnotation:")
    public native void addAnnotation(MKAnnotation annotation);
    @Method(selector = "addAnnotations:")
    public native void addAnnotations(NSArray<NSObject> annotations);
    @Method(selector = "removeAnnotation:")
    public native void removeAnnotation(MKAnnotation annotation);
    @Method(selector = "removeAnnotations:")
    public native void removeAnnotations(NSArray<NSObject> annotations);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "annotationsInMapRect:")
    public native NSSet<NSObject> getAnnotations(@ByVal MKMapRect mapRect);
    @Method(selector = "viewForAnnotation:")
    public native MKAnnotationView getView(MKAnnotation annotation);
    @Method(selector = "dequeueReusableAnnotationViewWithIdentifier:")
    public native MKAnnotationView dequeueReusableAnnotationView(String identifier);
    @Method(selector = "selectAnnotation:animated:")
    public native void selectAnnotation(MKAnnotation annotation, boolean animated);
    @Method(selector = "deselectAnnotation:animated:")
    public native void deselectAnnotation(MKAnnotation annotation, boolean animated);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "showAnnotations:animated:")
    public native void showAnnotations(NSArray<NSObject> annotations, boolean animated);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addOverlay:level:")
    public native void addOverlay(MKOverlay overlay, MKOverlayLevel level);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addOverlays:level:")
    public native void addOverlays(NSArray<NSObject> overlays, MKOverlayLevel level);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "removeOverlay:")
    public native void removeOverlay(MKOverlay overlay);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "removeOverlays:")
    public native void removeOverlays(NSArray<NSObject> overlays);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "insertOverlay:atIndex:level:")
    public native void insertOverlay(MKOverlay overlay, @MachineSizedUInt long index, MKOverlayLevel level);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "insertOverlay:aboveOverlay:")
    public native void insertOverlayAbove(MKOverlay overlay, MKOverlay sibling);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "insertOverlay:belowOverlay:")
    public native void insertOverlayBelow(MKOverlay overlay, MKOverlay sibling);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "exchangeOverlay:withOverlay:")
    public native void exchangeOverlay(MKOverlay overlay1, MKOverlay overlay2);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "overlaysInLevel:")
    public native NSArray<NSObject> getOverlays(MKOverlayLevel level);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "rendererForOverlay:")
    public native MKOverlayRenderer getOverlayRenderer(MKOverlay overlay);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "viewForOverlay:")
    public native MKOverlayView getOverlayView(MKOverlay overlay);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addOverlay:")
    public native void addOverlay(MKOverlay overlay);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addOverlays:")
    public native void addOverlays(NSArray<NSObject> overlays);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "insertOverlay:atIndex:")
    public native void insertOverlay(MKOverlay overlay, @MachineSizedUInt long index);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "exchangeOverlayAtIndex:withOverlayAtIndex:")
    public native void exchangeOverlay(@MachineSizedUInt long index1, @MachineSizedUInt long index2);
    /*</methods>*/
}
