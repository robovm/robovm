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
import org.robovm.rt.annotation.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGColorSpace/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGColorSpacePtr extends Ptr<CGColorSpace, CGColorSpacePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGColorSpace.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGColorSpace() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public static CGColorSpace createCalibratedGray(double[] whitePoint, double[] blackPoint, double gamma) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        return createCalibratedGray(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                gamma);
    }

    public static CGColorSpace createCalibratedGray(float[] whitePoint, float[] blackPoint, double gamma) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        return createCalibratedGray(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                gamma);
    }

    public static CGColorSpace createCalibratedRGB(double[] whitePoint, double[] blackPoint, double[] gamma, double[] matrix) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (gamma == null) {
            throw new NullPointerException("gamma");
        }
        if (matrix == null) {
            throw new NullPointerException("matrix");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        if (gamma.length != 3) {
            throw new IllegalArgumentException("gamma.length != 3 (" + gamma.length + ")");
        }
        if (matrix.length != 9) {
            throw new IllegalArgumentException("matrix.length != 9 (" + matrix.length + ")");
        }
        return createCalibratedRGB(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(gamma)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(matrix)));
    }

    public static CGColorSpace createCalibratedRGB(float[] whitePoint, float[] blackPoint, float[] gamma, float[] matrix) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (gamma == null) {
            throw new NullPointerException("gamma");
        }
        if (matrix == null) {
            throw new NullPointerException("matrix");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        if (gamma.length != 3) {
            throw new IllegalArgumentException("gamma.length != 3 (" + gamma.length + ")");
        }
        if (matrix.length != 9) {
            throw new IllegalArgumentException("matrix.length != 9 (" + matrix.length + ")");
        }
        return createCalibratedRGB(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(gamma)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(matrix)));
    }
    
    public static CGColorSpace createICCBased(long nComponents, double[] range, CGDataProvider profile, CGColorSpace alternate) {
        if (range == null) {
            throw new NullPointerException("range");
        }
        if (range.length != nComponents * 2) {
            throw new IllegalArgumentException("range.length != " + nComponents * 2 + " (" + range.length + ")");
        }
        return createICCBased(nComponents, 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(range)), 
                profile, alternate);
    }

    public static CGColorSpace createICCBased(long nComponents, float[] range, CGDataProvider profile, CGColorSpace alternate) {
        if (range == null) {
            throw new NullPointerException("range");
        }
        if (range.length != nComponents * 2) {
            throw new IllegalArgumentException("range.length != " + nComponents * 2 + " (" + range.length + ")");
        }
        return createICCBased(nComponents, 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(range)), 
                profile, alternate);
    }

    public static CGColorSpace createLab(double[] whitePoint, double[] blackPoint, double[] range) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (range == null) {
            throw new NullPointerException("range");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        if (range.length != 4) {
            throw new IllegalArgumentException("range.length != 4 (" + range.length + ")");
        }
        return createLab(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(range)));
    }
    
    public static CGColorSpace createLab(float[] whitePoint, float[] blackPoint, float[] range) {
        if (whitePoint == null) {
            throw new NullPointerException("whitePoint");
        }
        if (blackPoint == null) {
            throw new NullPointerException("blackPoint");
        }
        if (range == null) {
            throw new NullPointerException("range");
        }
        if (whitePoint.length != 3) {
            throw new IllegalArgumentException("whitePoint.length != 3 (" + whitePoint.length + ")");
        }
        if (blackPoint.length != 3) {
            throw new IllegalArgumentException("blackPoint.length != 3 (" + blackPoint.length + ")");
        }
        if (range.length != 4) {
            throw new IllegalArgumentException("range.length != 4 (" + range.length + ")");
        }
        return createLab(
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(whitePoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(blackPoint)), 
                VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(range)));
    }
    
    public static CGColorSpace createIndexed(CGColorSpace baseSpace, long lastIndex, byte[] colorTable) {
        if (baseSpace == null) {
            throw new NullPointerException("baseSpace");
        }
        if (colorTable == null) {
            throw new NullPointerException("colorTable");
        }
        long m = baseSpace.getNumberOfComponents();
        if (colorTable.length != m * (lastIndex + 1)) {
            throw new IllegalArgumentException("colorTable.length != " + (m * (lastIndex + 1)) 
                    + " (" + colorTable.length + ")");
        }
        return createIndexed(baseSpace, lastIndex, VM.getArrayValuesAddress(colorTable));
    }
    
    public byte[] getColorTable() {
        long len = getColorTableCount();
        if (len == 0) {
            throw new IllegalStateException("Not an indexed color space");
        }
        byte[] table = new byte[(int) len];
        getColorTable(VM.getArrayValuesAddress(table));
        return table;
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateDeviceGray", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createDeviceGray();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateDeviceRGB", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createDeviceRGB();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateDeviceCMYK", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createDeviceCMYK();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateCalibratedGray", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createCalibratedGray(@Pointer long whitePoint, @Pointer long blackPoint, @MachineSizedFloat double gamma);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateCalibratedRGB", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createCalibratedRGB(@Pointer long whitePoint, @Pointer long blackPoint, @Pointer long gamma, @Pointer long matrix);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateLab", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createLab(@Pointer long whitePoint, @Pointer long blackPoint, @Pointer long range);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateWithICCProfile", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createWithICCProfile(NSData data);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateICCBased", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createICCBased(@MachineSizedUInt long nComponents, @Pointer long range, CGDataProvider profile, CGColorSpace alternate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateIndexed", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createIndexed(CGColorSpace baseSpace, @MachineSizedUInt long lastIndex, @Pointer long colorTable);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreatePattern", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace createPattern(CGColorSpace baseSpace);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCreateWithName", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColorSpace create(String name);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetNumberOfComponents", optional=true)
    public native @MachineSizedUInt long getNumberOfComponents();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetModel", optional=true)
    public native CGColorSpaceModel getModel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetBaseColorSpace", optional=true)
    public native CGColorSpace getBaseColorSpace();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetColorTableCount", optional=true)
    public native @MachineSizedUInt long getColorTableCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGColorSpaceGetColorTable", optional=true)
    private native void getColorTable(@Pointer long table);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CGColorSpaceCopyICCProfile", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getICCProfile();
    /*</methods>*/
}
