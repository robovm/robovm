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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVTrackAssociationType.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVTrackAssociationType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVTrackAssociationType> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVTrackAssociationType.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVTrackAssociationType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVTrackAssociationType i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVTrackAssociationType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType AudioFallback = new AVTrackAssociationType("AudioFallbackValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType ChapterList = new AVTrackAssociationType("ChapterListValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType ForcedSubtitlesOnly = new AVTrackAssociationType("ForcedSubtitlesOnlyValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType SelectionFollower = new AVTrackAssociationType("SelectionFollowerValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVTrackAssociationType Timecode = new AVTrackAssociationType("TimecodeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVTrackAssociationType MetadataReferent = new AVTrackAssociationType("MetadataReferentValue");
    
    private static AVTrackAssociationType[] values = new AVTrackAssociationType[] {AudioFallback, ChapterList, ForcedSubtitlesOnly, 
        SelectionFollower, Timecode, MetadataReferent};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVTrackAssociationType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVTrackAssociationType valueOf(NSString value) {
        for (AVTrackAssociationType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVTrackAssociationType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeAudioFallback", optional=true)
    protected static native NSString AudioFallbackValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeChapterList", optional=true)
    protected static native NSString ChapterListValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeForcedSubtitlesOnly", optional=true)
    protected static native NSString ForcedSubtitlesOnlyValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeSelectionFollower", optional=true)
    protected static native NSString SelectionFollowerValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeTimecode", optional=true)
    protected static native NSString TimecodeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeMetadataReferent", optional=true)
    protected static native NSString MetadataReferentValue();
    /*</methods>*/
}
