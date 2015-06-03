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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MKOverlayPathRenderer/*</name>*/ 
    extends /*<extends>*/MKOverlayRenderer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MKOverlayPathRendererPtr extends Ptr<MKOverlayPathRenderer, MKOverlayPathRendererPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MKOverlayPathRenderer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MKOverlayPathRenderer() {}
    protected MKOverlayPathRenderer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public MKOverlayPathRenderer(MKOverlay overlay) {
    	super(overlay);
    }
    /*<properties>*/
    @Property(selector = "fillColor")
    public native UIColor getFillColor();
    @Property(selector = "setFillColor:")
    public native void setFillColor(UIColor v);
    @Property(selector = "strokeColor")
    public native UIColor getStrokeColor();
    @Property(selector = "setStrokeColor:")
    public native void setStrokeColor(UIColor v);
    @Property(selector = "lineWidth")
    public native @MachineSizedFloat double getLineWidth();
    @Property(selector = "setLineWidth:")
    public native void setLineWidth(@MachineSizedFloat double v);
    @Property(selector = "lineJoin")
    public native CGLineJoin getLineJoin();
    @Property(selector = "setLineJoin:")
    public native void setLineJoin(CGLineJoin v);
    @Property(selector = "lineCap")
    public native CGLineCap getLineCap();
    @Property(selector = "setLineCap:")
    public native void setLineCap(CGLineCap v);
    @Property(selector = "miterLimit")
    public native @MachineSizedFloat double getMiterLimit();
    @Property(selector = "setMiterLimit:")
    public native void setMiterLimit(@MachineSizedFloat double v);
    @Property(selector = "lineDashPhase")
    public native @MachineSizedFloat double getLineDashPhase();
    @Property(selector = "setLineDashPhase:")
    public native void setLineDashPhase(@MachineSizedFloat double v);
    @Property(selector = "lineDashPattern")
    public native NSArray<NSNumber> getLineDashPattern();
    @Property(selector = "setLineDashPattern:")
    public native void setLineDashPattern(NSArray<NSNumber> v);
    @Property(selector = "path")
    public native CGPath getPath();
    @Property(selector = "setPath:")
    public native void setPath(CGPath v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "createPath")
    public native void createPath();
    @Method(selector = "invalidatePath")
    public native void invalidatePath();
    @Method(selector = "applyStrokePropertiesToContext:atZoomScale:")
    public native void applyStrokeProperties(CGContext context, @MachineSizedFloat double zoomScale);
    @Method(selector = "applyFillPropertiesToContext:atZoomScale:")
    public native void applyFillProperties(CGContext context, @MachineSizedFloat double zoomScale);
    @Method(selector = "strokePath:inContext:")
    public native void strokePath(CGPath path, CGContext context);
    @Method(selector = "fillPath:inContext:")
    public native void fillPath(CGPath path, CGContext context);
    /*</methods>*/
}
