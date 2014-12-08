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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMetadataFormat.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataFormat/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataFormat toObject(Class<AVMetadataFormat> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataFormat.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataFormat o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataFormat> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataFormat> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVMetadataFormat.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataFormat> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataFormat i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataFormat.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat QuickTimeUserData = new AVMetadataFormat("QuickTimeUserDataValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat QuickTimeMetadata = new AVMetadataFormat("QuickTimeMetadataValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat iTunesMetadata = new AVMetadataFormat("iTunesMetadataValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat ID3Metadata = new AVMetadataFormat("ID3MetadataValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataFormat ISOUserData = new AVMetadataFormat("ISOUserDataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataFormat HLSMetadata = new AVMetadataFormat("HLSMetadataValue");
    
    private static AVMetadataFormat[] values = new AVMetadataFormat[] {QuickTimeUserData, QuickTimeMetadata, 
        iTunesMetadata, ID3Metadata, ISOUserData, HLSMetadata};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataFormat(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataFormat valueOf(NSString value) {
        for (AVMetadataFormat v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataFormat/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatQuickTimeUserData", optional=true)
    protected static native NSString QuickTimeUserDataValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatISOUserData", optional=true)
    protected static native NSString ISOUserDataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatQuickTimeMetadata", optional=true)
    protected static native NSString QuickTimeMetadataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatiTunesMetadata", optional=true)
    protected static native NSString iTunesMetadataValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatID3Metadata", optional=true)
    protected static native NSString ID3MetadataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatHLSMetadata", optional=true)
    protected static native NSString HLSMetadataValue();
    /*</methods>*/
}
