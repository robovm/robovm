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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVMetadataFormat/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataFormat/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataFormat/*</name>*/.class); }

    /*<marshalers>*/
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
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataFormat.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataFormat> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataFormat o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat QuickTimeUserData = new AVMetadataFormat("QuickTimeUserData");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataFormat ISOUserData = new AVMetadataFormat("ISOUserData");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat QuickTimeMetadata = new AVMetadataFormat("QuickTimeMetadata");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat iTunesMetadata = new AVMetadataFormat("iTunesMetadata");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataFormat ID3Metadata = new AVMetadataFormat("ID3Metadata");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataFormat HLSMetadata = new AVMetadataFormat("HLSMetadata");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataFormat/*</name>*/[] values = new /*<name>*/AVMetadataFormat/*</name>*/[] {/*<value_list>*/QuickTimeUserData, ISOUserData, QuickTimeMetadata, iTunesMetadata, ID3Metadata, HLSMetadata/*</value_list>*/};
    
    /*<name>*/AVMetadataFormat/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataFormat/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataFormat/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataFormat/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatQuickTimeUserData", optional=true)
        public static native NSString QuickTimeUserData();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatISOUserData", optional=true)
        public static native NSString ISOUserData();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatQuickTimeMetadata", optional=true)
        public static native NSString QuickTimeMetadata();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatiTunesMetadata", optional=true)
        public static native NSString iTunesMetadata();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatID3Metadata", optional=true)
        public static native NSString ID3Metadata();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataFormatHLSMetadata", optional=true)
        public static native NSString HLSMetadata();
        /*</values>*/
    }
}
