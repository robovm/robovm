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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPath/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGPathPtr extends Ptr<CGPath, CGPathPtr> {}/*</ptr>*/
    
    public interface Applier {
        void apply(CGPathElementType type, CGPoint point1, CGPoint point2, CGPoint point3);
    }
    
    /*<bind>*/static { Bro.bind(CGPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    private static final java.lang.reflect.Method cbApplier;
    
    static {
        try {
            cbApplier = CGPath.class.getDeclaredMethod("cbApplier", long.class, CGPathElement.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    /*<constructors>*/
    protected CGPath() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public static CGPath createCopyByDashingPath(CGPath path, CGAffineTransform transform, double phase, double[] lengths) {
        return createCopyByDashingPath(path, transform, phase, 
                lengths != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(lengths)) : 0, 
                lengths != null ? lengths.length : 0);
    }

    public static CGPath createCopyByDashingPath(CGPath path, CGAffineTransform transform, double phase, float[] lengths) {
        return createCopyByDashingPath(path, transform, phase, 
                lengths != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(lengths)) : 0, 
                lengths != null ? lengths.length : 0);
    }
    
    public void apply(Applier applier) {
        if (applier == null) {
            throw new NullPointerException("applier");
        }
        apply(VM.getObjectAddress(applier), VM.getCallbackMethodImpl(cbApplier));
    }

    @Callback
    private static void cbApplier(@Pointer long applierPtr, CGPathElement element) {
        Applier applier = (Applier) VM.castAddressToObject(applierPtr);
        CGPoint point1 = null;
        CGPoint point2 = null;
        CGPoint point3 = null;
        switch (element.type()) {
        case AddLineToPoint:
        case MoveToPoint:
            point1 = element.points();
            break;
        case AddQuadCurveToPoint:
            point1 = element.points();
            point2 = point1.next();
            break;
        case AddCurveToPoint:
            point1 = element.points();
            point2 = point1.next();
            point3 = point2.next();
            break;
        case CloseSubpath:
            break;
        }
        applier.apply(element.type(), point1, point2, point3);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopy(CGPath path);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathCreateCopyByTransformingPath", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByTransformingPath(CGPath path, CGAffineTransform transform);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGPathCreateWithRect", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithRect(@ByVal CGRect rect, CGAffineTransform transform);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathCreateWithEllipseInRect", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithEllipseInRect(@ByVal CGRect rect, CGAffineTransform transform);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGPathCreateWithRoundedRect", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithRoundedRect(@ByVal CGRect rect, @MachineSizedFloat double cornerWidth, @MachineSizedFloat double cornerHeight, CGAffineTransform transform);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathCreateCopyByDashingPath", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByDashingPath(CGPath path, CGAffineTransform transform, @MachineSizedFloat double phase, @Pointer long lengths, @MachineSizedUInt long count);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CGPathCreateCopyByStrokingPath", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByStrokingPath(CGPath path, CGAffineTransform transform, @MachineSizedFloat double lineWidth, CGLineCap lineCap, CGLineJoin lineJoin, @MachineSizedFloat double miterLimit);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathEqualToPath", optional=true)
    public native boolean equalToPath(CGPath path2);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathIsEmpty", optional=true)
    public native boolean isEmpty();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathIsRect", optional=true)
    public native boolean isRect(CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathGetCurrentPoint", optional=true)
    public native @ByVal CGPoint getCurrentPoint();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathGetBoundingBox", optional=true)
    public native @ByVal CGRect getBoundingBox();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGPathGetPathBoundingBox", optional=true)
    public native @ByVal CGRect getPathBoundingBox();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathContainsPoint", optional=true)
    public native boolean containsPoint(CGAffineTransform m, @ByVal CGPoint point, boolean eoFill);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGPathApply", optional=true)
    protected native void apply(@Pointer long info, @Pointer long function);
    /*</methods>*/
}
