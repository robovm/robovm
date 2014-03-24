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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
            cbApplier = CGFunction.class.getDeclaredMethod("cbApplier", long.class, CGPathElement.class);
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
    @Bridge(symbol="CGPathGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGPathCreateCopy")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopy(CGPath path);
    @Bridge(symbol="CGPathCreateCopyByTransformingPath")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByTransformingPath(CGPath path, CGAffineTransform transform);
    @Bridge(symbol="CGPathCreateWithRect")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithRect(@ByVal CGRect rect, CGAffineTransform transform);
    @Bridge(symbol="CGPathCreateWithEllipseInRect")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithEllipseInRect(@ByVal CGRect rect, CGAffineTransform transform);
    @Bridge(symbol="CGPathCreateWithRoundedRect")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createWithRoundedRect(@ByVal CGRect rect, @MachineSizedFloat double cornerWidth, @MachineSizedFloat double cornerHeight, CGAffineTransform transform);
    @Bridge(symbol="CGPathCreateCopyByDashingPath")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByDashingPath(CGPath path, CGAffineTransform transform, @MachineSizedFloat double phase, @Pointer long lengths, @MachineSizedUInt long count);
    @Bridge(symbol="CGPathCreateCopyByStrokingPath")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGPath createCopyByStrokingPath(CGPath path, CGAffineTransform transform, @MachineSizedFloat double lineWidth, CGLineCap lineCap, CGLineJoin lineJoin, @MachineSizedFloat double miterLimit);
    @Bridge(symbol="CGPathEqualToPath")
    public native boolean equalToPath(CGPath path2);
    @Bridge(symbol="CGPathIsEmpty")
    public native boolean isEmpty();
    @Bridge(symbol="CGPathIsRect")
    public native boolean isRect(CGRect rect);
    @Bridge(symbol="CGPathGetCurrentPoint")
    public native @ByVal CGPoint getCurrentPoint();
    @Bridge(symbol="CGPathGetBoundingBox")
    public native @ByVal CGRect getBoundingBox();
    @Bridge(symbol="CGPathGetPathBoundingBox")
    public native @ByVal CGRect getPathBoundingBox();
    @Bridge(symbol="CGPathContainsPoint")
    public native boolean containsPoint(CGAffineTransform m, @ByVal CGPoint point, boolean eoFill);
    @Bridge(symbol="CGPathApply")
    protected native void apply(@Pointer long info, @Pointer long function);
    /*</methods>*/
}
