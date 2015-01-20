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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMVideoFormatDescriptionExtension/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMVideoFormatDescriptionExtension/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMVideoFormatDescriptionExtension toObject(Class<CMVideoFormatDescriptionExtension> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMVideoFormatDescriptionExtension(o);
        }
        @MarshalsPointer
        public static long toNative(CMVideoFormatDescriptionExtension o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMVideoFormatDescriptionExtension> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMVideoFormatDescriptionExtension> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMVideoFormatDescriptionExtension(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMVideoFormatDescriptionExtension> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMVideoFormatDescriptionExtension i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
     CMVideoFormatDescriptionExtension(CFDictionary data) {
        super(data);
    }
    public CMVideoFormatDescriptionExtension() {}
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
    public CMVideoFormatDescriptionExtension set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getFormatName() {
        if (has(Keys.FormatName())) {
            CFString val = get(Keys.FormatName(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setFormatName(String formatName) {
        set(Keys.FormatName(), new CFString(formatName));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getDepth() {
        if (has(Keys.Depth())) {
            CFNumber val = get(Keys.Depth(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setDepth(int depth) {
        set(Keys.Depth(), CFNumber.valueOf(depth));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSArray<NSNumber> getCleanApertureWidthRational() {
        if (has(Keys.CleanApertureWidthRational())) {
            NSArray<NSNumber> val = get(Keys.CleanApertureWidthRational(), NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setCleanApertureWidthRational(NSArray<NSNumber> cleanApertureWidthRational) {
        set(Keys.CleanApertureWidthRational(), cleanApertureWidthRational);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSArray<NSNumber> getCleanApertureHeightRational() {
        if (has(Keys.CleanApertureHeightRational())) {
            NSArray<NSNumber> val = get(Keys.CleanApertureHeightRational(), NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setCleanApertureHeightRational(NSArray<NSNumber> cleanApertureHeightRational) {
        set(Keys.CleanApertureHeightRational(), cleanApertureHeightRational);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSArray<NSNumber> getCleanApertureHorizontalOffsetRational() {
        if (has(Keys.CleanApertureHorizontalOffsetRational())) {
            NSArray<NSNumber> val = get(Keys.CleanApertureHorizontalOffsetRational(), NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setCleanApertureHorizontalOffsetRational(NSArray<NSNumber> cleanApertureHorizontalOffsetRational) {
        set(Keys.CleanApertureHorizontalOffsetRational(), cleanApertureHorizontalOffsetRational);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSArray<NSNumber> getCleanApertureVerticalOffsetRational() {
        if (has(Keys.CleanApertureVerticalOffsetRational())) {
            NSArray<NSNumber> val = get(Keys.CleanApertureVerticalOffsetRational(), NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setCleanApertureVerticalOffsetRational(NSArray<NSNumber> cleanApertureVerticalOffsetRational) {
        set(Keys.CleanApertureVerticalOffsetRational(), cleanApertureVerticalOffsetRational);
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public boolean isFullRangeVideo() {
        if (has(Keys.FullRangeVideo())) {
            CFBoolean val = get(Keys.FullRangeVideo(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CMVideoFormatDescriptionExtension setFullRangeVideo(boolean fullRangeVideo) {
        set(Keys.FullRangeVideo(), CFBoolean.valueOf(fullRangeVideo));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSData getICCProfile() {
        if (has(Keys.ICCProfile())) {
            NSData val = get(Keys.ICCProfile(), NSData.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setICCProfile(NSData iCCProfile) {
        set(Keys.ICCProfile(), iCCProfile);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getBytesPerRow() {
        if (has(Keys.BytesPerRow())) {
            CFNumber val = get(Keys.BytesPerRow(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setBytesPerRow(long bytesPerRow) {
        set(Keys.BytesPerRow(), CFNumber.valueOf(bytesPerRow));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMMPEG2VideoProfile getConformsToMPEG2VideoProfile() {
        if (has(Keys.ConformsToMPEG2VideoProfile())) {
            CFNumber val = get(Keys.ConformsToMPEG2VideoProfile(), CFNumber.class);
            return CMMPEG2VideoProfile.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setConformsToMPEG2VideoProfile(CMMPEG2VideoProfile conformsToMPEG2VideoProfile) {
        set(Keys.ConformsToMPEG2VideoProfile(), CFNumber.valueOf(conformsToMPEG2VideoProfile.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getTemporalQuality() {
        if (has(Keys.TemporalQuality())) {
            CFNumber val = get(Keys.TemporalQuality(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setTemporalQuality(int temporalQuality) {
        set(Keys.TemporalQuality(), CFNumber.valueOf(temporalQuality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getSpatialQuality() {
        if (has(Keys.SpatialQuality())) {
            CFNumber val = get(Keys.SpatialQuality(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setSpatialQuality(int spatialQuality) {
        set(Keys.SpatialQuality(), CFNumber.valueOf(spatialQuality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getVersion() {
        if (has(Keys.Version())) {
            CFNumber val = get(Keys.Version(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setVersion(int version) {
        set(Keys.Version(), CFNumber.valueOf(version));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getRevisionLevel() {
        if (has(Keys.RevisionLevel())) {
            CFNumber val = get(Keys.RevisionLevel(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setRevisionLevel(int revisionLevel) {
        set(Keys.RevisionLevel(), CFNumber.valueOf(revisionLevel));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMFormatDescriptionVendor getVendor() {
        if (has(Keys.Vendor())) {
            CFString val = get(Keys.Vendor(), CFString.class);
            return CMFormatDescriptionVendor.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMVideoFormatDescriptionExtension setVendor(CMFormatDescriptionVendor vendor) {
        set(Keys.Vendor(), vendor.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreMedia")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_FormatName", optional=true)
        public static native CFString FormatName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_Depth", optional=true)
        public static native CFString Depth();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureWidthRational", optional=true)
        public static native CFString CleanApertureWidthRational();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureHeightRational", optional=true)
        public static native CFString CleanApertureHeightRational();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureHorizontalOffsetRational", optional=true)
        public static native CFString CleanApertureHorizontalOffsetRational();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureVerticalOffsetRational", optional=true)
        public static native CFString CleanApertureVerticalOffsetRational();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_FullRangeVideo", optional=true)
        public static native CFString FullRangeVideo();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_ICCProfile", optional=true)
        public static native CFString ICCProfile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_BytesPerRow", optional=true)
        public static native CFString BytesPerRow();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionConformsToMPEG2VideoProfile", optional=true)
        public static native CFString ConformsToMPEG2VideoProfile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_TemporalQuality", optional=true)
        public static native CFString TemporalQuality();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_SpatialQuality", optional=true)
        public static native CFString SpatialQuality();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_Version", optional=true)
        public static native CFString Version();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_RevisionLevel", optional=true)
        public static native CFString RevisionLevel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMFormatDescriptionExtension_Vendor", optional=true)
        public static native CFString Vendor();
    }
    /*</keys>*/
}
