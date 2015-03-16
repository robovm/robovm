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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGMutablePath/*</name>*/ 
    extends /*<extends>*/CGPath/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGMutablePathPtr extends Ptr<CGMutablePath, CGMutablePathPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGMutablePath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGMutablePath() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public void addRects(CGAffineTransform m, CGRect[] rects) {
        if (rects == null) {
            throw new NullPointerException("rects");
        }
        CGRect first = Struct.allocate(CGRect.class, rects.length);
        first.update(rects);
        addRects(m, first, rects.length);
    }
    public void addLines(CGAffineTransform m, CGPoint[] points) {
        if (points == null) {
            throw new NullPointerException("points");
        }
        CGPoint first = Struct.allocate(CGPoint.class, points.length);
        first.update(points);
        addLines(m, first, points.length);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGMutablePath createMutable();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGMutablePath createMutableCopy(CGPath path);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathCreateMutableCopyByTransformingPath", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGMutablePath createMutableCopyByTransformingPath(CGPath path, CGAffineTransform transform);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGPathAddRoundedRect", optional=true)
    public native void addRoundedRect(CGAffineTransform transform, @ByVal CGRect rect, @MachineSizedFloat double cornerWidth, @MachineSizedFloat double cornerHeight);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathMoveToPoint", optional=true)
    public native void moveToPoint(CGAffineTransform m, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddLineToPoint", optional=true)
    public native void addLineToPoint(CGAffineTransform m, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddQuadCurveToPoint", optional=true)
    public native void addQuadCurveToPoint(CGAffineTransform m, @MachineSizedFloat double cpx, @MachineSizedFloat double cpy, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddCurveToPoint", optional=true)
    public native void addCurveToPoint(CGAffineTransform m, @MachineSizedFloat double cp1x, @MachineSizedFloat double cp1y, @MachineSizedFloat double cp2x, @MachineSizedFloat double cp2y, @MachineSizedFloat double x, @MachineSizedFloat double y);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathCloseSubpath", optional=true)
    public native void closeSubpath();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddRect", optional=true)
    public native void addRect(CGAffineTransform m, @ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddRects", optional=true)
    private native void addRects(CGAffineTransform m, CGRect rects, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddLines", optional=true)
    private native void addLines(CGAffineTransform m, CGPoint points, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddEllipseInRect", optional=true)
    public native void addEllipseInRect(CGAffineTransform m, @ByVal CGRect rect);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathAddRelativeArc", optional=true)
    public native void addRelativeArc(CGAffineTransform matrix, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double delta);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddArc", optional=true)
    public native void addArc(CGAffineTransform m, @MachineSizedFloat double x, @MachineSizedFloat double y, @MachineSizedFloat double radius, @MachineSizedFloat double startAngle, @MachineSizedFloat double endAngle, boolean clockwise);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddArcToPoint", optional=true)
    public native void addArcToPoint(CGAffineTransform m, @MachineSizedFloat double x1, @MachineSizedFloat double y1, @MachineSizedFloat double x2, @MachineSizedFloat double y2, @MachineSizedFloat double radius);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathAddPath", optional=true)
    public native void addPath(CGAffineTransform m, CGPath path2);
    /*</methods>*/
}
