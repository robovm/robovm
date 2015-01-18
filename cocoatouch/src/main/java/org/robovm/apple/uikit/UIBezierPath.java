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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIBezierPath/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIBezierPathPtr extends Ptr<UIBezierPath, UIBezierPathPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIBezierPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIBezierPath() {}
    protected UIBezierPath(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "CGPath")
    public native CGPath getCGPath();
    @Property(selector = "setCGPath:")
    public native void setCGPath(CGPath v);
    @Property(selector = "isEmpty")
    public native boolean isEmpty();
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "currentPoint")
    public native @ByVal CGPoint getCurrentPoint();
    @Property(selector = "lineWidth")
    public native @MachineSizedFloat double getLineWidth();
    @Property(selector = "setLineWidth:")
    public native void setLineWidth(@MachineSizedFloat double v);
    @Property(selector = "lineCapStyle")
    public native CGLineCap getLineCapStyle();
    @Property(selector = "setLineCapStyle:")
    public native void setLineCapStyle(CGLineCap v);
    @Property(selector = "lineJoinStyle")
    public native CGLineJoin getLineJoinStyle();
    @Property(selector = "setLineJoinStyle:")
    public native void setLineJoinStyle(CGLineJoin v);
    @Property(selector = "miterLimit")
    public native @MachineSizedFloat double getMiterLimit();
    @Property(selector = "setMiterLimit:")
    public native void setMiterLimit(@MachineSizedFloat double v);
    @Property(selector = "flatness")
    public native @MachineSizedFloat double getFlatness();
    @Property(selector = "setFlatness:")
    public native void setFlatness(@MachineSizedFloat double v);
    @Property(selector = "usesEvenOddFillRule")
    public native boolean usesEvenOddFillRule();
    @Property(selector = "setUsesEvenOddFillRule:")
    public native void setUsesEvenOddFillRule(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public void setLineDash(double[] pattern, double phase) {
        if (pattern == null) {
            throw new NullPointerException("pattern");
        }
        MachineSizedFloatPtr patternPtr = Struct.allocate(MachineSizedFloatPtr.class, pattern.length);
        patternPtr.set(pattern);
        setLineDash(patternPtr, pattern.length, phase);
    }

    public void setLineDash(float[] pattern, double phase) {
        if (pattern == null) {
            throw new NullPointerException("pattern");
        }
        MachineSizedFloatPtr patternPtr = Struct.allocate(MachineSizedFloatPtr.class, pattern.length);
        patternPtr.set(pattern);
        setLineDash(patternPtr, pattern.length, phase);
    }

    public double[] getLineDashD() {
        MachineSizedSIntPtr countPtr = new MachineSizedSIntPtr();
        getLineDash(null, countPtr, null);
        int count = (int) countPtr.get();
        MachineSizedFloatPtr patternPtr = Struct.allocate(MachineSizedFloatPtr.class, count);
        getLineDash(patternPtr, null, null);
        return patternPtr.toDoubleArray(count);
    }
    
    public float[] getLineDashF() {
        MachineSizedSIntPtr countPtr = new MachineSizedSIntPtr();
        getLineDash(null, countPtr, null);
        int count = (int) countPtr.get();
        MachineSizedFloatPtr patternPtr = Struct.allocate(MachineSizedFloatPtr.class, count);
        getLineDash(patternPtr, null, null);
        return patternPtr.toFloatArray(count);
    }

    public double getLineDashPhase() {
        MachineSizedFloatPtr phasePtr = new MachineSizedFloatPtr();
        getLineDash(null, null, phasePtr);
        return phasePtr.get();
    }

    /*<methods>*/
    @Method(selector = "moveToPoint:")
    public native void move(@ByVal CGPoint point);
    @Method(selector = "addLineToPoint:")
    public native void addLine(@ByVal CGPoint point);
    @Method(selector = "addCurveToPoint:controlPoint1:controlPoint2:")
    public native void addCurve(@ByVal CGPoint endPoint, @ByVal CGPoint controlPoint1, @ByVal CGPoint controlPoint2);
    @Method(selector = "addQuadCurveToPoint:controlPoint:")
    public native void addQuadCurve(@ByVal CGPoint endPoint, @ByVal CGPoint controlPoint);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addArcWithCenter:radius:startAngle:endAngle:clockwise:")
    public native void addArc(@ByVal CGPoint center, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, boolean clockwise);
    @Method(selector = "closePath")
    public native void closePath();
    @Method(selector = "removeAllPoints")
    public native void removeAllPoints();
    @Method(selector = "appendPath:")
    public native void appendPath(UIBezierPath bezierPath);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "bezierPathByReversingPath")
    public native UIBezierPath reverse();
    @Method(selector = "applyTransform:")
    public native void applyTransform(@ByVal CGAffineTransform transform);
    @Method(selector = "containsPoint:")
    public native boolean containsPoint(@ByVal CGPoint point);
    @Method(selector = "setLineDash:count:phase:")
    protected native void setLineDash(MachineSizedFloatPtr pattern, @MachineSizedSInt long count, @MachineSizedFloat double phase);
    @Method(selector = "getLineDash:count:phase:")
    protected native void getLineDash(MachineSizedFloatPtr pattern, MachineSizedSIntPtr count, MachineSizedFloatPtr phase);
    @Method(selector = "fill")
    public native void fill();
    @Method(selector = "stroke")
    public native void stroke();
    @Method(selector = "fillWithBlendMode:alpha:")
    public native void fill(CGBlendMode blendMode, @MachineSizedFloat double alpha);
    @Method(selector = "strokeWithBlendMode:alpha:")
    public native void stroke(CGBlendMode blendMode, @MachineSizedFloat double alpha);
    @Method(selector = "addClip")
    public native void addClip();
    @Method(selector = "bezierPathWithRect:")
    public static native UIBezierPath createFromRect(@ByVal CGRect rect);
    @Method(selector = "bezierPathWithOvalInRect:")
    public static native UIBezierPath createFromOval(@ByVal CGRect rect);
    @Method(selector = "bezierPathWithRoundedRect:cornerRadius:")
    public static native UIBezierPath createFromRoundedRect(@ByVal CGRect rect, @MachineSizedFloat double cornerRadius);
    @Method(selector = "bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:")
    public static native UIBezierPath createFromRoundedRect(@ByVal CGRect rect, UIRectCorner corners, @ByVal CGSize cornerRadii);
    @Method(selector = "bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:")
    public static native UIBezierPath createFromArc(@ByVal CGPoint center, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, boolean clockwise);
    @Method(selector = "bezierPathWithCGPath:")
    public static native UIBezierPath createFromPath(CGPath CGPath);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
