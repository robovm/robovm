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
import org.robovm.apple.coreimage.*;
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
@Marshaler(/*<name>*/AVTrackAssociationType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVTrackAssociationType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVTrackAssociationType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVTrackAssociationType toObject(Class<AVTrackAssociationType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVTrackAssociationType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVTrackAssociationType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVTrackAssociationType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVTrackAssociationType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVTrackAssociationType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVTrackAssociationType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVTrackAssociationType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType AudioFallback = new AVTrackAssociationType("AudioFallback");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType ChapterList = new AVTrackAssociationType("ChapterList");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType ForcedSubtitlesOnly = new AVTrackAssociationType("ForcedSubtitlesOnly");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType SelectionFollower = new AVTrackAssociationType("SelectionFollower");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType Timecode = new AVTrackAssociationType("Timecode");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVTrackAssociationType MetadataReferent = new AVTrackAssociationType("MetadataReferent");
    /*</constants>*/
    
    private static /*<name>*/AVTrackAssociationType/*</name>*/[] values = new /*<name>*/AVTrackAssociationType/*</name>*/[] {/*<value_list>*/AudioFallback, ChapterList, ForcedSubtitlesOnly, SelectionFollower, Timecode, MetadataReferent/*</value_list>*/};
    
    /*<name>*/AVTrackAssociationType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVTrackAssociationType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVTrackAssociationType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVTrackAssociationType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeAudioFallback", optional=true)
        public static native NSString AudioFallback();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeChapterList", optional=true)
        public static native NSString ChapterList();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeForcedSubtitlesOnly", optional=true)
        public static native NSString ForcedSubtitlesOnly();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeSelectionFollower", optional=true)
        public static native NSString SelectionFollower();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeTimecode", optional=true)
        public static native NSString Timecode();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVTrackAssociationTypeMetadataReferent", optional=true)
        public static native NSString MetadataReferent();
        /*</values>*/
    }
}
