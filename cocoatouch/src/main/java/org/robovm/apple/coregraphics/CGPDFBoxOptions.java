/*
 * Copyright (C) 2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CGPDFBoxOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFBoxOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGPDFBoxOptions toObject(Class<CGPDFBoxOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGPDFBoxOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CGPDFBoxOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGPDFBoxOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGPDFBoxOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGPDFBoxOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGPDFBoxOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGPDFBoxOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGPDFBoxOptions(CFDictionary data) {
        super(data);
    }
    public CGPDFBoxOptions() {}
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
    public CGPDFBoxOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getMediaBox() {
        if (has(Keys.MediaBox())) {
            NSData val = get(Keys.MediaBox(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setMediaBox(CGRect mediaBox) {
        set(Keys.MediaBox(), new NSData(mediaBox));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getCropBox() {
        if (has(Keys.CropBox())) {
            NSData val = get(Keys.CropBox(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setCropBox(CGRect cropBox) {
        set(Keys.CropBox(), new NSData(cropBox));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBleedBox() {
        if (has(Keys.BleedBox())) {
            NSData val = get(Keys.BleedBox(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setBleedBox(CGRect bleedBox) {
        set(Keys.BleedBox(), new NSData(bleedBox));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTrimBox() {
        if (has(Keys.TrimBox())) {
            NSData val = get(Keys.TrimBox(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setTrimBox(CGRect trimBox) {
        set(Keys.TrimBox(), new NSData(trimBox));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getArtBox() {
        if (has(Keys.ArtBox())) {
            NSData val = get(Keys.ArtBox(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setArtBox(CGRect artBox) {
        set(Keys.ArtBox(), new NSData(artBox));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreGraphics")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextMediaBox", optional=true)
        public static native CFString MediaBox();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextCropBox", optional=true)
        public static native CFString CropBox();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextBleedBox", optional=true)
        public static native CFString BleedBox();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextTrimBox", optional=true)
        public static native CFString TrimBox();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGPDFContextArtBox", optional=true)
        public static native CFString ArtBox();
    }
    /*</keys>*/
}
