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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
@Marshaler(/*<name>*/CVPixelBufferAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelBufferAttribute/*</name>*/ 
    extends /*<extends>*/CVBufferAttribute/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVPixelBufferAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVPixelBufferAttribute toObject(Class<CVPixelBufferAttribute> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVPixelBufferAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVPixelBufferAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVPixelBufferAttribute> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVPixelBufferAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVPixelBufferAttribute.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVPixelBufferAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVPixelBufferAttribute i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute PixelFormatType = new CVPixelBufferAttribute("PixelFormatType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute MemoryAllocator = new CVPixelBufferAttribute("MemoryAllocator");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute Width = new CVPixelBufferAttribute("Width");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute Height = new CVPixelBufferAttribute("Height");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute ExtendedPixelsLeft = new CVPixelBufferAttribute("ExtendedPixelsLeft");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute ExtendedPixelsTop = new CVPixelBufferAttribute("ExtendedPixelsTop");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute ExtendedPixelsRight = new CVPixelBufferAttribute("ExtendedPixelsRight");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute ExtendedPixelsBottom = new CVPixelBufferAttribute("ExtendedPixelsBottom");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute BytesPerRowAlignment = new CVPixelBufferAttribute("BytesPerRowAlignment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute CGBitmapContextCompatibility = new CVPixelBufferAttribute("CGBitmapContextCompatibility");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute CGImageCompatibility = new CVPixelBufferAttribute("CGImageCompatibility");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute OpenGLCompatibility = new CVPixelBufferAttribute("OpenGLCompatibility");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute PlaneAlignment = new CVPixelBufferAttribute("PlaneAlignment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVPixelBufferAttribute IOSurfaceProperties = new CVPixelBufferAttribute("IOSurfaceProperties");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CVPixelBufferAttribute OpenGLESCompatibility = new CVPixelBufferAttribute("OpenGLESCompatibility");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CVPixelBufferAttribute MetalCompatibility = new CVPixelBufferAttribute("MetalCompatibility");
    /*</constants>*/
    
    private static /*<name>*/CVPixelBufferAttribute/*</name>*/[] values = new /*<name>*/CVPixelBufferAttribute/*</name>*/[] {/*<value_list>*/PixelFormatType, MemoryAllocator, Width, Height, ExtendedPixelsLeft, ExtendedPixelsTop, ExtendedPixelsRight, ExtendedPixelsBottom, BytesPerRowAlignment, CGBitmapContextCompatibility, CGImageCompatibility, OpenGLCompatibility, PlaneAlignment, IOSurfaceProperties, OpenGLESCompatibility, MetalCompatibility/*</value_list>*/};
    
    /*<name>*/CVPixelBufferAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CVPixelBufferAttribute/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVPixelBufferAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVPixelBufferAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferPixelFormatTypeKey", optional=true)
        public static native CFString PixelFormatType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferMemoryAllocatorKey", optional=true)
        public static native CFString MemoryAllocator();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferWidthKey", optional=true)
        public static native CFString Width();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferHeightKey", optional=true)
        public static native CFString Height();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferExtendedPixelsLeftKey", optional=true)
        public static native CFString ExtendedPixelsLeft();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferExtendedPixelsTopKey", optional=true)
        public static native CFString ExtendedPixelsTop();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferExtendedPixelsRightKey", optional=true)
        public static native CFString ExtendedPixelsRight();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferExtendedPixelsBottomKey", optional=true)
        public static native CFString ExtendedPixelsBottom();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferBytesPerRowAlignmentKey", optional=true)
        public static native CFString BytesPerRowAlignment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferCGBitmapContextCompatibilityKey", optional=true)
        public static native CFString CGBitmapContextCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferCGImageCompatibilityKey", optional=true)
        public static native CFString CGImageCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferOpenGLCompatibilityKey", optional=true)
        public static native CFString OpenGLCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferPlaneAlignmentKey", optional=true)
        public static native CFString PlaneAlignment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferIOSurfacePropertiesKey", optional=true)
        public static native CFString IOSurfaceProperties();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferOpenGLESCompatibilityKey", optional=true)
        public static native CFString OpenGLESCompatibility();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCVPixelBufferMetalCompatibilityKey", optional=true)
        public static native CFString MetalCompatibility();
        /*</values>*/
    }
}
