/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIBezierPath /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBezierPath /*</name>*/.class);
    }

    /*<constructors>*/
    public UIBezierPath() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("CGPath") public native @Type("CGPathRef") CGPath getCGPath();
    @Bind("setCGPath:") public native void setCGPath(@Type("CGPathRef") CGPath v);
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    @Bind("currentPoint") public native @Type("CGPoint") CGPoint getCurrentPoint();
    @Bind("isEmpty") public native @Type("BOOL") boolean isEmpty();
    @Bind("flatness") public native @Type("CGFloat") float getFlatness();
    @Bind("setFlatness:") public native void setFlatness(@Type("CGFloat") float v);
    @Bind("lineCapStyle") public native @Type("CGLineCap") CGLineCap getLineCapStyle();
    @Bind("setLineCapStyle:") public native void setLineCapStyle(@Type("CGLineCap") CGLineCap v);
    @Bind("lineJoinStyle") public native @Type("CGLineJoin") CGLineJoin getLineJoinStyle();
    @Bind("setLineJoinStyle:") public native void setLineJoinStyle(@Type("CGLineJoin") CGLineJoin v);
    @Bind("lineWidth") public native @Type("CGFloat") float getLineWidth();
    @Bind("setLineWidth:") public native void setLineWidth(@Type("CGFloat") float v);
    @Bind("miterLimit") public native @Type("CGFloat") float getMiterLimit();
    @Bind("setMiterLimit:") public native void setMiterLimit(@Type("CGFloat") float v);
    @Bind("usesEvenOddFillRule") public native @Type("BOOL") boolean isUsesEvenOddFillRule();
    @Bind("setUsesEvenOddFillRule:") public native void setUsesEvenOddFillRule(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("bezierPath") public native static @Type("UIBezierPath *") UIBezierPath create();
    @Bind("bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:") public native static @Type("UIBezierPath *") UIBezierPath fromArc(@Type("CGPoint") CGPoint center, @Type("CGFloat") float radius, @Type("CGFloat") float startAngle, @Type("CGFloat") float endAngle, @Type("BOOL") boolean clockwise);
    @Bind("bezierPathWithOvalInRect:") public native static @Type("UIBezierPath *") UIBezierPath fromOval(@Type("CGRect") CGRect rect);
    @Bind("bezierPathWithCGPath:") public native static @Type("UIBezierPath *") UIBezierPath fromPath(@Type("CGPathRef") CGPath CGPath);
    @Bind("bezierPathWithRect:") public native static @Type("UIBezierPath *") UIBezierPath fromRect(@Type("CGRect") CGRect rect);
    @Bind("bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:") public native static @Type("UIBezierPath *") UIBezierPath fromRoundedRect(@Type("CGRect") CGRect rect, @Type("UIRectCorner") EnumSet<UIRectCorner> corners, @Type("CGSize") CGSize cornerRadii);
    @Bind("bezierPathWithRoundedRect:cornerRadius:") public native static @Type("UIBezierPath *") UIBezierPath fromRoundedRect(@Type("CGRect") CGRect rect, @Type("CGFloat") float cornerRadius);
    @Bind("addArcWithCenter:radius:startAngle:endAngle:clockwise:") public native @Type("void") void addArc(@Type("CGPoint") CGPoint center, @Type("CGFloat") float radius, @Type("CGFloat") float startAngle, @Type("CGFloat") float endAngle, @Type("BOOL") boolean clockwise);
    @Bind("addClip") public native @Type("void") void addClip();
    @Bind("addCurveToPoint:controlPoint1:controlPoint2:") public native @Type("void") void addCurve(@Type("CGPoint") CGPoint endPoint, @Type("CGPoint") CGPoint controlPoint1, @Type("CGPoint") CGPoint controlPoint2);
    @Bind("addLineToPoint:") public native @Type("void") void addLine(@Type("CGPoint") CGPoint point);
    @Bind("addQuadCurveToPoint:controlPoint:") public native @Type("void") void addQuadCurve(@Type("CGPoint") CGPoint endPoint, @Type("CGPoint") CGPoint controlPoint);
    @Bind("appendPath:") public native @Type("void") void appendPath(@Type("UIBezierPath *") UIBezierPath bezierPath);
    @Bind("applyTransform:") public native @Type("void") void applyTransform(@Type("CGAffineTransform") CGAffineTransform transform);
    @Bind("bezierPathByReversingPath") public native @Type("UIBezierPath *") UIBezierPath bezierPathByReversingPath();
    @Bind("closePath") public native @Type("void") void closePath();
    @Bind("containsPoint:") public native @Type("BOOL") boolean containsPoint(@Type("CGPoint") CGPoint point);
    @Bind("fill") public native @Type("void") void fill();
    @Bind("fillWithBlendMode:alpha:") public native @Type("void") void fill(@Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    @Bind("getLineDash:count:phase:") private native @Type("void") void getLineDash(@Type("CGFloat *") @Pointer long pattern, @Type("NSInteger *") IntPtr count, @Type("CGFloat *") FloatPtr phase);
    @Bind("moveToPoint:") public native @Type("void") void move(@Type("CGPoint") CGPoint point);
    @Bind("removeAllPoints") public native @Type("void") void removeAllPoints();
    @Bind("setLineDash:count:phase:") private native @Type("void") void setLineDash(@Type("CGFloat *") FloatPtr pattern, @Type("NSInteger") int count, @Type("CGFloat") float phase);
    @Bind("stroke") public native @Type("void") void stroke();
    @Bind("strokeWithBlendMode:alpha:") public native @Type("void") void stroke(@Type("CGBlendMode") CGBlendMode blendMode, @Type("CGFloat") float alpha);
    /*</methods>*/

}
