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
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
@Marshaler(/*<name>*/CGImageDestinationCopySourceOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageDestinationCopySourceOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImageDestinationCopySourceOptions toObject(Class<CGImageDestinationCopySourceOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGImageDestinationCopySourceOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageDestinationCopySourceOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImageDestinationCopySourceOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImageDestinationCopySourceOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGImageDestinationCopySourceOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImageDestinationCopySourceOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImageDestinationCopySourceOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGImageDestinationCopySourceOptions(CFDictionary data) {
        super(data);
    }
    public CGImageDestinationCopySourceOptions() {}
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
    public CGImageDestinationCopySourceOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageMetadata getMetadata() {
        if (has(Keys.DestinationMetadata())) {
            CGImageMetadata val = get(Keys.DestinationMetadata(), CGImageMetadata.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setMetadata(CGImageMetadata metadata) {
        set(Keys.DestinationMetadata(), metadata);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean mergesMetadata() {
        if (has(Keys.DestinationMergeMetadata())) {
            CFBoolean val = get(Keys.DestinationMergeMetadata(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setMergesMetadata(boolean mergesMetadata) {
        set(Keys.DestinationMergeMetadata(), CFBoolean.valueOf(mergesMetadata));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldExcludeXMP() {
        if (has(Keys.MetadataShouldExcludeXMP())) {
            CFBoolean val = get(Keys.MetadataShouldExcludeXMP(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setShouldExcludeXMP(boolean shouldExcludeXMP) {
        set(Keys.MetadataShouldExcludeXMP(), CFBoolean.valueOf(shouldExcludeXMP));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean shouldExcludeGPS() {
        if (has(Keys.MetadataShouldExcludeGPS())) {
            CFBoolean val = get(Keys.MetadataShouldExcludeGPS(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationCopySourceOptions setShouldExcludeGPS(boolean shouldExcludeGPS) {
        set(Keys.MetadataShouldExcludeGPS(), CFBoolean.valueOf(shouldExcludeGPS));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getDateTime() {
        if (has(Keys.DestinationDateTime())) {
            CFString val = get(Keys.DestinationDateTime(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setDateTime(String dateTime) {
        set(Keys.DestinationDateTime(), new CFString(dateTime));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImagePropertyOrientation getOrientation() {
        if (has(Keys.DestinationOrientation())) {
            CFNumber val = get(Keys.DestinationOrientation(), CFNumber.class);
            return CGImagePropertyOrientation.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setOrientation(CGImagePropertyOrientation orientation) {
        set(Keys.DestinationOrientation(), CFNumber.valueOf(orientation.value()));
        return this;
    }
    /*</methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setDateTime(NSDate dateTime) {
        set(Keys.DestinationDateTime(), dateTime);
        return this;
    }
    
    /*<keys>*/
    @Library("ImageIO")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationMetadata", optional=true)
        public static native CFString DestinationMetadata();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationMergeMetadata", optional=true)
        public static native CFString DestinationMergeMetadata();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataShouldExcludeXMP", optional=true)
        public static native CFString MetadataShouldExcludeXMP();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataShouldExcludeGPS", optional=true)
        public static native CFString MetadataShouldExcludeGPS();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationDateTime", optional=true)
        public static native CFString DestinationDateTime();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationOrientation", optional=true)
        public static native CFString DestinationOrientation();
    }
    /*</keys>*/
}
