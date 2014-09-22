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
@Marshaler(CGPDFBoxOptions.Marshaler.class)
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGPDFBoxOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    private CGPDFBoxOptions(CFDictionary data) {
        this.data = data;
    }
    public CGPDFBoxOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGPDFBoxOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @ByVal CGRect getMediaBox() {
        if (data.containsKey(MediaBoxKey())) {
            NSData val = data.get(MediaBoxKey(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setMediaBox(@ByVal CGRect box) {
        NSData val = new NSData(box);
        data.put(MediaBoxKey(), val.as(CFData.class));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @ByVal CGRect getCropBox() {
        if (data.containsKey(CropBoxKey())) {
            NSData val = data.get(CropBoxKey(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setCropBox(@ByVal CGRect box) {
        NSData val = new NSData(box);
        data.put(CropBoxKey(), val.as(CFData.class));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @ByVal CGRect getBleedBox() {
        if (data.containsKey(BleedBoxKey())) {
            NSData val = data.get(BleedBoxKey(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setBleedBox(@ByVal CGRect box) {
        NSData val = new NSData(box);
        data.put(BleedBoxKey(), val.as(CFData.class));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @ByVal CGRect getTrimBox() {
        if (data.containsKey(TrimBoxKey())) {
            NSData val = data.get(TrimBoxKey(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setTrimBox(@ByVal CGRect box) {
        NSData val = new NSData(box);
        data.put(TrimBoxKey(), val.as(CFData.class));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @ByVal CGRect getArtBox() {
        if (data.containsKey(ArtBoxKey())) {
            NSData val = data.get(ArtBoxKey(), NSData.class);
            return val.getStructData(CGRect.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGPDFBoxOptions setArtBox(@ByVal CGRect box) {
        NSData val = new NSData(box);
        data.put(ArtBoxKey(), val.as(CFData.class));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextMediaBox", optional=true)
    protected static native CFString MediaBoxKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextCropBox", optional=true)
    protected static native CFString CropBoxKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextBleedBox", optional=true)
    protected static native CFString BleedBoxKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextTrimBox", optional=true)
    protected static native CFString TrimBoxKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCGPDFContextArtBox", optional=true)
    protected static native CFString ArtBoxKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
