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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGColor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGColorPtr extends Ptr<CGColor, CGColorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGColor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGColor() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    public static CGColor create(CGColorSpace space, float[] components) {
        return create(space, components, components != null ? components.length : 0);
    }

    public static CGColor create(CGColorSpace space, double[] components) {
        return create(space, components, components != null ? components.length : 0);
    }

    private static CGColor create(CGColorSpace space, Object components, int componentsLength) {
        if (space == null) {
            throw new NullPointerException("space == null");
        }
        if (components == null) {
            throw new NullPointerException("components == null");
        }
        if (componentsLength != space.getNumberOfComponents() + 1) {
            throw new IllegalArgumentException("components.length != space.numberOfComponents + 1");
        }
        return create(space, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    
    public double[] getComponentsD() {
        return getComponents(this).toDoubleArray((int) getNumberOfComponents());
    }

    public float[] getComponentsF() {
        return getComponents(this).toFloatArray((int) getNumberOfComponents());
    }

    /*<methods>*/
    @GlobalValue(symbol="kCGColorWhite")
    public static native String ColorWhite();
    @GlobalValue(symbol="kCGColorBlack")
    public static native String ColorBlack();
    @GlobalValue(symbol="kCGColorClear")
    public static native String ColorClear();
    
    @Bridge(symbol="CGColorCreate")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor create(CGColorSpace space, @Pointer long components);
    @Bridge(symbol="CGColorCreateGenericGray")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createGenericGray(@MachineSizedFloat double gray, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGColorCreateGenericRGB")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createGenericRGB(@MachineSizedFloat double red, @MachineSizedFloat double green, @MachineSizedFloat double blue, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGColorCreateGenericCMYK")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createGenericCMYK(@MachineSizedFloat double cyan, @MachineSizedFloat double magenta, @MachineSizedFloat double yellow, @MachineSizedFloat double black, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGColorGetConstantColor")
    public static native CGColor getConstantColor(String colorName);
    @Bridge(symbol="CGColorCreateWithPattern")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createWithPattern(CGColorSpace space, CGPattern pattern, @Pointer long components);
    @Bridge(symbol="CGColorCreateCopy")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createCopy(CGColor color);
    @Bridge(symbol="CGColorCreateCopyWithAlpha")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGColor createCopyWithAlpha(CGColor color, @MachineSizedFloat double alpha);
    @Bridge(symbol="CGColorEqualToColor")
    public native boolean equalToColor(CGColor color2);
    @Bridge(symbol="CGColorGetNumberOfComponents")
    public native @MachineSizedUInt long getNumberOfComponents();
    @Bridge(symbol="CGColorGetComponents")
    protected static native MachineSizedFloatPtr getComponents(CGColor color);
    @Bridge(symbol="CGColorGetAlpha")
    public native @MachineSizedFloat double getAlpha();
    @Bridge(symbol="CGColorGetColorSpace")
    public native CGColorSpace getColorSpace();
    @Bridge(symbol="CGColorGetPattern")
    public native CGPattern getPattern();
    @Bridge(symbol="CGColorGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    /*</methods>*/
}
