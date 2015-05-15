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
@Marshaler(/*<name>*/CVPixelFormatDescription/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelFormatDescription/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVPixelFormatDescription toObject(Class<CVPixelFormatDescription> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CVPixelFormatDescription(o);
        }
        @MarshalsPointer
        public static long toNative(CVPixelFormatDescription o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVPixelFormatDescription> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVPixelFormatDescription> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CVPixelFormatDescription(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVPixelFormatDescription> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVPixelFormatDescription i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    public interface FillExtendedPixelsCallback {
        boolean fillExtendedPixels(CVPixelBuffer pixelBuffer);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private static LongMap<FillExtendedPixelsCallback> callbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbFillExtendedPixels;
    
    static {
        try {
            cbFillExtendedPixels = CVPixelFormatDescription.class.getDeclaredMethod("cbFillExtendedPixels", CVPixelBuffer.class, long.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    
    @Callback
    private static boolean cbFillExtendedPixels(CVPixelBuffer pixelBuffer, long refcon) {
        FillExtendedPixelsCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        return callback.fillExtendedPixels(pixelBuffer);
    }
    
    /*<constructors>*/
    CVPixelFormatDescription(CFDictionary data) {
        super(data);
    }
    public CVPixelFormatDescription() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public CVPixelFormatDescription set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getName() {
        if (has(Keys.Name())) {
            CFString val = get(Keys.Name(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setName(String name) {
        set(Keys.Name(), new CFString(name));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getConstant() {
        if (has(Keys.Constant())) {
            CFNumber val = get(Keys.Constant(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setConstant(long constant) {
        set(Keys.Constant(), CFNumber.valueOf(constant));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getCodecType() {
        if (has(Keys.CodecType())) {
            CFString val = get(Keys.CodecType(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCodecType(String codecType) {
        set(Keys.CodecType(), new CFString(codecType));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getFourCC() {
        if (has(Keys.FourCC())) {
            CFString val = get(Keys.FourCC(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setFourCC(String fourCC) {
        set(Keys.FourCC(), new CFString(fourCC));
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public boolean containsAlpha() {
        if (has(Keys.ContainsAlpha())) {
            CFBoolean val = get(Keys.ContainsAlpha(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CVPixelFormatDescription setContainsAlpha(boolean containsAlpha) {
        set(Keys.ContainsAlpha(), CFBoolean.valueOf(containsAlpha));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean containsYCbCr() {
        if (has(Keys.ContainsYCbCr())) {
            CFBoolean val = get(Keys.ContainsYCbCr(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CVPixelFormatDescription setContainsYCbCr(boolean containsYCbCr) {
        set(Keys.ContainsYCbCr(), CFBoolean.valueOf(containsYCbCr));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean containsRGB() {
        if (has(Keys.ContainsRGB())) {
            CFBoolean val = get(Keys.ContainsRGB(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CVPixelFormatDescription setContainsRGB(boolean containsRGB) {
        set(Keys.ContainsRGB(), CFBoolean.valueOf(containsRGB));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getPlanes() {
        if (has(Keys.Planes())) {
            CFNumber val = get(Keys.Planes(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setPlanes(int planes) {
        set(Keys.Planes(), CFNumber.valueOf(planes));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBlockWidth() {
        if (has(Keys.BlockWidth())) {
            CFNumber val = get(Keys.BlockWidth(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBlockWidth(int blockWidth) {
        set(Keys.BlockWidth(), CFNumber.valueOf(blockWidth));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBlockHeight() {
        if (has(Keys.BlockHeight())) {
            CFNumber val = get(Keys.BlockHeight(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBlockHeight(int blockHeight) {
        set(Keys.BlockHeight(), CFNumber.valueOf(blockHeight));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBitsPerBlock() {
        if (has(Keys.BitsPerBlock())) {
            CFNumber val = get(Keys.BitsPerBlock(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBitsPerBlock(int bitsPerBlock) {
        set(Keys.BitsPerBlock(), CFNumber.valueOf(bitsPerBlock));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBlockHorizontalAlignment() {
        if (has(Keys.BlockHorizontalAlignment())) {
            CFNumber val = get(Keys.BlockHorizontalAlignment(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBlockHorizontalAlignment(int blockHorizontalAlignment) {
        set(Keys.BlockHorizontalAlignment(), CFNumber.valueOf(blockHorizontalAlignment));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBlockVerticalAlignment() {
        if (has(Keys.BlockVerticalAlignment())) {
            CFNumber val = get(Keys.BlockVerticalAlignment(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBlockVerticalAlignment(int blockVerticalAlignment) {
        set(Keys.BlockVerticalAlignment(), CFNumber.valueOf(blockVerticalAlignment));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSData getBlackBlock() {
        if (has(Keys.BlackBlock())) {
            NSData val = get(Keys.BlackBlock(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setBlackBlock(NSData blackBlock) {
        set(Keys.BlackBlock(), blackBlock);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getHorizontalSubsampling() {
        if (has(Keys.HorizontalSubsampling())) {
            CFNumber val = get(Keys.HorizontalSubsampling(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setHorizontalSubsampling(int horizontalSubsampling) {
        set(Keys.HorizontalSubsampling(), CFNumber.valueOf(horizontalSubsampling));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getVerticalSubsampling() {
        if (has(Keys.VerticalSubsampling())) {
            CFNumber val = get(Keys.VerticalSubsampling(), CFNumber.class);
            return val.intValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setVerticalSubsampling(int verticalSubsampling) {
        set(Keys.VerticalSubsampling(), CFNumber.valueOf(verticalSubsampling));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getOpenGLFormat() {
        if (has(Keys.OpenGLFormat())) {
            CFNumber val = get(Keys.OpenGLFormat(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setOpenGLFormat(int openGLFormat) {
        set(Keys.OpenGLFormat(), CFNumber.valueOf(openGLFormat));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getOpenGLType() {
        if (has(Keys.OpenGLType())) {
            CFNumber val = get(Keys.OpenGLType(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setOpenGLType(int openGLType) {
        set(Keys.OpenGLType(), CFNumber.valueOf(openGLType));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getOpenGLInternalFormat() {
        if (has(Keys.OpenGLInternalFormat())) {
            CFNumber val = get(Keys.OpenGLInternalFormat(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setOpenGLInternalFormat(int openGLInternalFormat) {
        set(Keys.OpenGLInternalFormat(), CFNumber.valueOf(openGLInternalFormat));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGBitmapInfo getCGBitmapInfo() {
        if (has(Keys.CGBitmapInfo())) {
            CFNumber val = get(Keys.CGBitmapInfo(), CFNumber.class);
            return new CGBitmapInfo(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCGBitmapInfo(CGBitmapInfo cGBitmapInfo) {
        set(Keys.CGBitmapInfo(), CFNumber.valueOf(cGBitmapInfo.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithQD() {
        if (has(Keys.QDCompatibility())) {
            CFBoolean val = get(Keys.QDCompatibility(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCompatibleWithQD(boolean compatibleWithQD) {
        set(Keys.QDCompatibility(), CFBoolean.valueOf(compatibleWithQD));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithCGBitmapContext() {
        if (has(Keys.CGBitmapContextCompatibility())) {
            CFBoolean val = get(Keys.CGBitmapContextCompatibility(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCompatibleWithCGBitmapContext(boolean compatibleWithCGBitmapContext) {
        set(Keys.CGBitmapContextCompatibility(), CFBoolean.valueOf(compatibleWithCGBitmapContext));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithCGImage() {
        if (has(Keys.CGImageCompatibility())) {
            CFBoolean val = get(Keys.CGImageCompatibility(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCompatibleWithCGImage(boolean compatibleWithCGImage) {
        set(Keys.CGImageCompatibility(), CFBoolean.valueOf(compatibleWithCGImage));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isCompatibleWithOpenGL() {
        if (has(Keys.OpenGLCompatibility())) {
            CFBoolean val = get(Keys.OpenGLCompatibility(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVPixelFormatDescription setCompatibleWithOpenGL(boolean compatibleWithOpenGL) {
        set(Keys.OpenGLCompatibility(), CFBoolean.valueOf(compatibleWithOpenGL));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isCompatibleWithOpenGLES() {
        if (has(Keys.OpenGLESCompatibility())) {
            CFBoolean val = get(Keys.OpenGLESCompatibility(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CVPixelFormatDescription setCompatibleWithOpenGLES(boolean compatibleWithOpenGLES) {
        set(Keys.OpenGLESCompatibility(), CFBoolean.valueOf(compatibleWithOpenGLES));
        return this;
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelFormatDescriptionCreateWithPixelFormatType", optional=true)
    public static native CVPixelFormatDescription create(CFAllocator allocator, CVPixelFormatType pixelFormat);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelFormatDescriptionArrayCreateWithAllPixelFormatTypes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CVPixelFormatType.AsListMarshaler.class) List<CVPixelFormatType> getAllPixelFormatTypes(CFAllocator allocator);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelFormatDescriptionRegisterDescriptionWithPixelFormatType", optional=true)
    public static native void registerDescription(CVPixelFormatDescription description, CVPixelFormatType pixelFormat);
    /*</methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CVPixelFormatDescription create(CVPixelFormatType pixelFormat) {
        return create(null, pixelFormat);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static List<CVPixelFormatType> getAllPixelFormatTypes() {
        return getAllPixelFormatTypes(null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void register(CVPixelFormatType pixelFormat) {
        registerDescription(this, pixelFormat);
    }
    
    private long refcon = -1;
    
    public CVPixelFormatDescription setFillExtendedPixelsCallback(FillExtendedPixelsCallback callback) {
        if (refcon == -1) {
            refcon = CVPixelFormatDescription.refconId.getAndIncrement();
        }
        CVFillExtendedPixelsCallBackData data = new CVFillExtendedPixelsCallBackData(1, new FunctionPtr(cbFillExtendedPixels), refcon);
        NSData val = new NSData(data);
        set(Keys.FillExtendedPixelsCallback(), val);
        synchronized (callbacks) {
            callbacks.put(refcon, callback);
        }
        
        return this;
    }
    
    /*<keys>*/
    @Library("CoreVideo")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatName", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatConstant", optional=true)
        public static native CFString Constant();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatCodecType", optional=true)
        public static native CFString CodecType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatFourCC", optional=true)
        public static native CFString FourCC();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatContainsAlpha", optional=true)
        public static native CFString ContainsAlpha();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatContainsYCbCr", optional=true)
        public static native CFString ContainsYCbCr();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatContainsRGB", optional=true)
        public static native CFString ContainsRGB();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatPlanes", optional=true)
        public static native CFString Planes();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBlockWidth", optional=true)
        public static native CFString BlockWidth();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBlockHeight", optional=true)
        public static native CFString BlockHeight();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBitsPerBlock", optional=true)
        public static native CFString BitsPerBlock();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBlockHorizontalAlignment", optional=true)
        public static native CFString BlockHorizontalAlignment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBlockVerticalAlignment", optional=true)
        public static native CFString BlockVerticalAlignment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatBlackBlock", optional=true)
        public static native CFString BlackBlock();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatHorizontalSubsampling", optional=true)
        public static native CFString HorizontalSubsampling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatVerticalSubsampling", optional=true)
        public static native CFString VerticalSubsampling();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatOpenGLFormat", optional=true)
        public static native CFString OpenGLFormat();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatOpenGLType", optional=true)
        public static native CFString OpenGLType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatOpenGLInternalFormat", optional=true)
        public static native CFString OpenGLInternalFormat();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatCGBitmapInfo", optional=true)
        public static native CFString CGBitmapInfo();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatQDCompatibility", optional=true)
        public static native CFString QDCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatCGBitmapContextCompatibility", optional=true)
        public static native CFString CGBitmapContextCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatCGImageCompatibility", optional=true)
        public static native CFString CGImageCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatOpenGLCompatibility", optional=true)
        public static native CFString OpenGLCompatibility();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatOpenGLESCompatibility", optional=true)
        public static native CFString OpenGLESCompatibility();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVPixelFormatFillExtendedPixelsCallback", optional=true)
        public static native CFString FillExtendedPixelsCallback();
    }
    /*</keys>*/
}
