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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyPNG.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyPNG/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyPNG toObject(Class<CGImagePropertyPNG> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyPNG.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyPNG o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyPNG.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG Gamma = new CGImagePropertyPNG("GammaKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG InterlaceType = new CGImagePropertyPNG("InterlaceTypeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG XPixelsPerMeter = new CGImagePropertyPNG("XPixelsPerMeterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG YPixelsPerMeter = new CGImagePropertyPNG("YPixelsPerMeterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG sRGBIntent = new CGImagePropertyPNG("sRGBIntentKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG Chromaticities = new CGImagePropertyPNG("ChromaticitiesKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Author = new CGImagePropertyPNG("AuthorKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Copyright = new CGImagePropertyPNG("CopyrightKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG CreationTime = new CGImagePropertyPNG("CreationTimeKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Description = new CGImagePropertyPNG("DescriptionKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG ModificationTime = new CGImagePropertyPNG("ModificationTimeKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Software = new CGImagePropertyPNG("SoftwareKey");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Title = new CGImagePropertyPNG("TitleKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG LoopCount = new CGImagePropertyPNG("LoopCountKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG DelayTime = new CGImagePropertyPNG("DelayTimeKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG UnclampedDelayTime = new CGImagePropertyPNG("UnclampedDelayTimeKey");
    
    private static CGImagePropertyPNG[] values = new CGImagePropertyPNG[] {Gamma, InterlaceType, XPixelsPerMeter, YPixelsPerMeter, 
        sRGBIntent, Chromaticities, Author, Copyright, CreationTime, Description, ModificationTime, Software, Title, LoopCount, 
        DelayTime, UnclampedDelayTime};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyPNG(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyPNG valueOf(CFString value) {
        for (CGImagePropertyPNG v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyPNG/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGGamma", optional=true)
    protected static native CFString GammaKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGInterlaceType", optional=true)
    protected static native CFString InterlaceTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGXPixelsPerMeter", optional=true)
    protected static native CFString XPixelsPerMeterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGYPixelsPerMeter", optional=true)
    protected static native CFString YPixelsPerMeterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGsRGBIntent", optional=true)
    protected static native CFString sRGBIntentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGChromaticities", optional=true)
    protected static native CFString ChromaticitiesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGAuthor", optional=true)
    protected static native CFString AuthorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGCopyright", optional=true)
    protected static native CFString CopyrightKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGCreationTime", optional=true)
    protected static native CFString CreationTimeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGDescription", optional=true)
    protected static native CFString DescriptionKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGModificationTime", optional=true)
    protected static native CFString ModificationTimeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGSoftware", optional=true)
    protected static native CFString SoftwareKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGTitle", optional=true)
    protected static native CFString TitleKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyAPNGLoopCount", optional=true)
    protected static native CFString LoopCountKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyAPNGDelayTime", optional=true)
    protected static native CFString DelayTimeKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyAPNGUnclampedDelayTime", optional=true)
    protected static native CFString UnclampedDelayTimeKey();
    /*</methods>*/
}
