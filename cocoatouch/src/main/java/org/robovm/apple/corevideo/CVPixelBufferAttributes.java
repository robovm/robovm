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
@Marshaler(CVPixelBufferAttributes.Marshaler.class)
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelBufferAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CVPixelBufferAttributes toObject(Class<CVPixelBufferAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CVPixelBufferAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CVPixelBufferAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    protected CFDictionary data;
    
    protected CVPixelBufferAttributes(CFDictionary data) {
        this.data = data;
    }
    public CVPixelBufferAttributes() {
        data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CVPixelBufferAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public List<CVPixelFormatType> getPixelFormatTypes() {
        if (data.containsKey(PixelFormatTypeKey())) {
            CFType val = data.get(PixelFormatTypeKey(), CFType.class);
            List<CVPixelFormatType> list = new ArrayList<>();
            if (val.getClass() == CFNumber.class || CFNumber.class.isAssignableFrom(val.getClass())) {
                CFNumber num = (CFNumber)val;
                list.add(CVPixelFormatType.valueOf(num.longValue()));
                return list;
            } else if (val.getClass() == CFArray.class || CFArray.class.isAssignableFrom(val.getClass())) {
                CFArray arr = (CFArray)val;
                for (int i = 0; i < arr.size(); i++) {
                    list.add(CVPixelFormatType.valueOf(arr.get(i, CFNumber.class).longValue()));
                }
                return list;
            }
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setPixelFormatType(CVPixelFormatType type) {
        data.put(PixelFormatTypeKey(), CFNumber.valueOf(type.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setPixelFormatTypes(List<CVPixelFormatType> types) {
        CFArray array = CFMutableArray.create();
        for (CVPixelFormatType i : types) {
            array.add(CFNumber.valueOf(i.value()));
        }
        data.put(PixelFormatTypeKey(), array);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CFAllocator getMemoryAllocator() {
        if (data.containsKey(MemoryAllocatorKey())) {
            CFAllocator val = data.get(MemoryAllocatorKey(), CFAllocator.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setMemoryAllocator(CFAllocator allocator) {
        data.put(MemoryAllocatorKey(), allocator);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getWidth() {
        if (data.containsKey(WidthKey())) {
            CFNumber val = data.get(WidthKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setWidth(long width) {
        data.put(WidthKey(), CFNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHeight() {
        if (data.containsKey(HeightKey())) {
            CFNumber val = data.get(HeightKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setHeight(long height) {
        data.put(HeightKey(), CFNumber.valueOf(height));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getExtendedPixelsLeft() {
        if (data.containsKey(ExtendedPixelsLeftKey())) {
            CFNumber val = data.get(ExtendedPixelsLeftKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setExtendedPixelsLeft(long pixels) {
        data.put(ExtendedPixelsLeftKey(), CFNumber.valueOf(pixels));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getExtendedPixelsTop() {
        if (data.containsKey(ExtendedPixelsTopKey())) {
            CFNumber val = data.get(ExtendedPixelsTopKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setExtendedPixelsTop(long pixels) {
        data.put(ExtendedPixelsTopKey(), CFNumber.valueOf(pixels));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getExtendedPixelsRight() {
        if (data.containsKey(ExtendedPixelsRightKey())) {
            CFNumber val = data.get(ExtendedPixelsRightKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setExtendedPixelsRight(long pixels) {
        data.put(ExtendedPixelsRightKey(), CFNumber.valueOf(pixels));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getExtendedPixelsBottom() {
        if (data.containsKey(ExtendedPixelsBottomKey())) {
            CFNumber val = data.get(ExtendedPixelsBottomKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setExtendedPixelsBottom(long pixels) {
        data.put(ExtendedPixelsBottomKey(), CFNumber.valueOf(pixels));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getBytesPerRowAlignment() {
        if (data.containsKey(BytesPerRowAlignmentKey())) {
            CFNumber val = data.get(BytesPerRowAlignmentKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setBytesPerRowAlignment(long alignment) {
        data.put(BytesPerRowAlignmentKey(), CFNumber.valueOf(alignment));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithCGBitmapContext() {
        if (data.containsKey(CGBitmapContextCompatibilityKey())) {
            CFBoolean val = data.get(CGBitmapContextCompatibilityKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setCompatibleWithCGBitmapContext(boolean compatible) {
        data.put(CGBitmapContextCompatibilityKey(), CFBoolean.valueOf(compatible));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithCGImage() {
        if (data.containsKey(CGImageCompatibilityKey())) {
            CFBoolean val = data.get(CGImageCompatibilityKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setCompatibleWithCGImage(boolean compatible) {
        data.put(CGImageCompatibilityKey(), CFBoolean.valueOf(compatible));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithOpenGL() {
        if (data.containsKey(OpenGLCompatibilityKey())) {
            CFBoolean val = data.get(OpenGLCompatibilityKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setCompatibleWithOpenGL(boolean compatible) {
        data.put(OpenGLCompatibilityKey(), CFBoolean.valueOf(compatible));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getPlaneAlignment() {
        if (data.containsKey(PlaneAlignmentKey())) {
            CFNumber val = data.get(PlaneAlignmentKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setPlaneAlignment(long alignment) {
        data.put(PlaneAlignmentKey(), CFNumber.valueOf(alignment));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CFDictionary getIOSurfaceProperties() {
        if (data.containsKey(IOSurfacePropertiesKey())) {
            CFDictionary val = data.get(IOSurfacePropertiesKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelBufferAttributes setIOSurfaceProperties(CFDictionary properties) {
        data.put(IOSurfacePropertiesKey(), properties);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isCompatibleWithOpenGLES() {
        if (data.containsKey(OpenGLESCompatibilityKey())) {
            CFBoolean val = data.get(OpenGLESCompatibilityKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CVPixelBufferAttributes setCompatibleWithOpenGLES(boolean compatible) {
        data.put(OpenGLESCompatibilityKey(), CFBoolean.valueOf(compatible));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isCompatibleWithMetal() {
        if (data.containsKey(MetalCompatibilityKey())) {
            CFBoolean val = data.get(MetalCompatibilityKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CVPixelBufferAttributes setCompatibleWithMetal(boolean compatible) {
        data.put(MetalCompatibilityKey(), CFBoolean.valueOf(compatible));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPixelFormatTypeKey", optional=true)
    protected static native CFString PixelFormatTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferMemoryAllocatorKey", optional=true)
    protected static native CFString MemoryAllocatorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferWidthKey", optional=true)
    protected static native CFString WidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferHeightKey", optional=true)
    protected static native CFString HeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsLeftKey", optional=true)
    protected static native CFString ExtendedPixelsLeftKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsTopKey", optional=true)
    protected static native CFString ExtendedPixelsTopKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsRightKey", optional=true)
    protected static native CFString ExtendedPixelsRightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferExtendedPixelsBottomKey", optional=true)
    protected static native CFString ExtendedPixelsBottomKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferBytesPerRowAlignmentKey", optional=true)
    protected static native CFString BytesPerRowAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGBitmapContextCompatibilityKey", optional=true)
    protected static native CFString CGBitmapContextCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferCGImageCompatibilityKey", optional=true)
    protected static native CFString CGImageCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLCompatibilityKey", optional=true)
    protected static native CFString OpenGLCompatibilityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferPlaneAlignmentKey", optional=true)
    protected static native CFString PlaneAlignmentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferIOSurfacePropertiesKey", optional=true)
    protected static native CFString IOSurfacePropertiesKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferOpenGLESCompatibilityKey", optional=true)
    protected static native CFString OpenGLESCompatibilityKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCVPixelBufferMetalCompatibilityKey", optional=true)
    protected static native CFString MetalCompatibilityKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
