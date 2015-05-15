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
package org.robovm.apple.imageio;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyColorModel.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyColorModel/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyColorModel toObject(Class<CGImagePropertyColorModel> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyColorModel.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyColorModel o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyColorModel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyColorModel RGB = new CGImagePropertyColorModel("RGBValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyColorModel Gray = new CGImagePropertyColorModel("GrayValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyColorModel CMYK = new CGImagePropertyColorModel("CMYKValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyColorModel Lab = new CGImagePropertyColorModel("LabValue");
    
    private static CGImagePropertyColorModel[] values = new CGImagePropertyColorModel[] {RGB, Gray, CMYK, Lab};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyColorModel(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyColorModel valueOf(CFString value) {
        for (CGImagePropertyColorModel v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyColorModel/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyColorModelRGB", optional=true)
    protected static native CFString RGBValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyColorModelGray", optional=true)
    protected static native CFString GrayValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyColorModelCMYK", optional=true)
    protected static native CFString CMYKValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyColorModelLab", optional=true)
    protected static native CFString LabValue();
    /*</methods>*/
}
