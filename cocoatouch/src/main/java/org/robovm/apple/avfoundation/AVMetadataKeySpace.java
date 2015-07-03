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
@Marshaler(/*<name>*/AVMetadataKeySpace/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeySpace/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataKeySpace/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeySpace toObject(Class<AVMetadataKeySpace> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeySpace.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeySpace o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataKeySpace> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataKeySpace> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataKeySpace.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataKeySpace> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataKeySpace o : l) {
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
    public static final AVMetadataKeySpace Common = new AVMetadataKeySpace("Common");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace QuickTimeUserData = new AVMetadataKeySpace("QuickTimeUserData");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKeySpace ISOUserData = new AVMetadataKeySpace("ISOUserData");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace QuickTimeMetadata = new AVMetadataKeySpace("QuickTimeMetadata");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace iTunes = new AVMetadataKeySpace("iTunes");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeySpace ID3 = new AVMetadataKeySpace("ID3");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataKeySpace Icy = new AVMetadataKeySpace("Icy");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataKeySpace/*</name>*/[] values = new /*<name>*/AVMetadataKeySpace/*</name>*/[] {/*<value_list>*/Common, QuickTimeUserData, ISOUserData, QuickTimeMetadata, iTunes, ID3, Icy/*</value_list>*/};
    
    /*<name>*/AVMetadataKeySpace/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataKeySpace/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataKeySpace/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeySpace/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceCommon", optional=true)
        public static native NSString Common();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeUserData", optional=true)
        public static native NSString QuickTimeUserData();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceISOUserData", optional=true)
        public static native NSString ISOUserData();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeMetadata", optional=true)
        public static native NSString QuickTimeMetadata();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceiTunes", optional=true)
        public static native NSString iTunes();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceID3", optional=true)
        public static native NSString ID3();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataKeySpaceIcy", optional=true)
        public static native NSString Icy();
        /*</values>*/
    }
}
