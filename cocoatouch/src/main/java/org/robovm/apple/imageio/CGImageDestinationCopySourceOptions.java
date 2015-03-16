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
@Marshaler(CGImageDestinationCopySourceOptions.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageDestinationCopySourceOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CGImageDestinationCopySourceOptions(CFDictionary data) {
        this.data = data;
    }
    public CGImageDestinationCopySourceOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGImageDestinationCopySourceOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageMetadata getMetadata() {
        if (data.containsKey(DestinationMetadataKey())) {
            CGImageMetadata val = data.get(DestinationMetadataKey(), CGImageMetadata.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setMetadata(CGImageMetadata metadata) {
        data.put(DestinationMetadataKey(), metadata);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isMergingMetadata() {
        if (data.containsKey(DestinationMergeMetadataKey())) {
            CFBoolean val = data.get(DestinationMergeMetadataKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setMergeMetadata(boolean merge) {
        data.put(DestinationMergeMetadataKey(), CFBoolean.valueOf(merge));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldExcludeXMP() {
        if (data.containsKey(MetadataShouldExcludeXMPKey())) {
            CFBoolean val = data.get(MetadataShouldExcludeXMPKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setShouldExcludeXMP(boolean exclude) {
        data.put(MetadataShouldExcludeXMPKey(), CFBoolean.valueOf(exclude));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean shouldExcludeGPS() {
        if (data.containsKey(MetadataShouldExcludeGPSKey())) {
            CFBoolean val = data.get(MetadataShouldExcludeGPSKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationCopySourceOptions setShouldExcludeGPS(boolean exclude) {
        data.put(MetadataShouldExcludeGPSKey(), CFBoolean.valueOf(exclude));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getDateTime() {
        if (data.containsKey(DestinationDateTimeKey())) {
            CFString val = data.get(DestinationDateTimeKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setDateTime(String dateTime) {
        data.put(DestinationDateTimeKey(), new CFString(dateTime));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setDateTime(NSDate dateTime) {
        data.put(DestinationDateTimeKey(), dateTime);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImagePropertyOrientation getOrientation() {
        if (data.containsKey(DestinationOrientationKey())) {
            CFNumber val = data.get(DestinationOrientationKey(), CFNumber.class);
            return CGImagePropertyOrientation.valueOf(val.intValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageDestinationCopySourceOptions setOrientation(CGImagePropertyOrientation orientation) {
        data.put(DestinationOrientationKey(), CFNumber.valueOf(orientation.value()));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationMetadata", optional=true)
    protected static native CFString DestinationMetadataKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationMergeMetadata", optional=true)
    protected static native CFString DestinationMergeMetadataKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataShouldExcludeXMP", optional=true)
    protected static native CFString MetadataShouldExcludeXMPKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataShouldExcludeGPS", optional=true)
    protected static native CFString MetadataShouldExcludeGPSKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationDateTime", optional=true)
    protected static native CFString DestinationDateTimeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationOrientation", optional=true)
    protected static native CFString DestinationOrientationKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
