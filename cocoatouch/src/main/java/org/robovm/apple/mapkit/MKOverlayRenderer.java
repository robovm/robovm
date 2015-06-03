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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MapKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKOverlayRenderer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKOverlayRendererPtr extends Ptr<MKOverlayRenderer, MKOverlayRendererPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKOverlayRenderer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKOverlayRenderer() {}
    protected MKOverlayRenderer(SkipInit skipInit) { super(skipInit); }
    public MKOverlayRenderer(MKOverlay overlay) { super((SkipInit) null); initObject(init(overlay)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "overlay")
    public native MKOverlay getOverlay();
    @Property(selector = "alpha")
    public native @MachineSizedFloat double getAlpha();
    @Property(selector = "setAlpha:")
    public native void setAlpha(@MachineSizedFloat double v);
    @Property(selector = "contentScaleFactor")
    public native @MachineSizedFloat double getContentScaleFactor();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="MKRoadWidthAtZoomScale", optional=true)
    public static native @MachineSizedFloat double getRoadWidth(@MachineSizedFloat double zoomScale);
    
    @Method(selector = "initWithOverlay:")
    protected native @Pointer long init(MKOverlay overlay);
    @Method(selector = "pointForMapPoint:")
    public native @ByVal CGPoint getPoint(@ByVal MKMapPoint mapPoint);
    @Method(selector = "mapPointForPoint:")
    public native @ByVal MKMapPoint getMapPoint(@ByVal CGPoint point);
    @Method(selector = "rectForMapRect:")
    public native @ByVal CGRect getRect(@ByVal MKMapRect mapRect);
    @Method(selector = "mapRectForRect:")
    public native @ByVal MKMapRect getMapRect(@ByVal CGRect rect);
    @Method(selector = "canDrawMapRect:zoomScale:")
    public native boolean canDraw(@ByVal MKMapRect mapRect, @MachineSizedFloat double zoomScale);
    @Method(selector = "drawMapRect:zoomScale:inContext:")
    public native void draw(@ByVal MKMapRect mapRect, @MachineSizedFloat double zoomScale, CGContext context);
    @Method(selector = "setNeedsDisplay")
    public native void setNeedsDisplay();
    @Method(selector = "setNeedsDisplayInMapRect:")
    public native void setNeedsDisplay(@ByVal MKMapRect mapRect);
    @Method(selector = "setNeedsDisplayInMapRect:zoomScale:")
    public native void setNeedsDisplay(@ByVal MKMapRect mapRect, @MachineSizedFloat double zoomScale);
    /*</methods>*/
}
