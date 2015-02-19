/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(AVMetadataKey3GPUserData.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKey3GPUserData/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKey3GPUserData toObject(Class<AVMetadataKey3GPUserData> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKey3GPUserData.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKey3GPUserData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKey3GPUserData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Copyright = new AVMetadataKey3GPUserData("CopyrightValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Author = new AVMetadataKey3GPUserData("AuthorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Performer = new AVMetadataKey3GPUserData("PerformerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Genre = new AVMetadataKey3GPUserData("GenreValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData RecordingYear = new AVMetadataKey3GPUserData("RecordingYearValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Location = new AVMetadataKey3GPUserData("LocationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Title = new AVMetadataKey3GPUserData("TitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKey3GPUserData Description = new AVMetadataKey3GPUserData("DescriptionValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData Collection = new AVMetadataKey3GPUserData("CollectionValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData UserRating = new AVMetadataKey3GPUserData("UserRatingValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData Thumbnail = new AVMetadataKey3GPUserData("ThumbnailValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData AlbumAndTrack = new AVMetadataKey3GPUserData("AlbumAndTrackValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData KeywordList = new AVMetadataKey3GPUserData("KeywordListValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData MediaClassification = new AVMetadataKey3GPUserData("MediaClassificationValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataKey3GPUserData MediaRating = new AVMetadataKey3GPUserData("MediaRatingValue");
    
    private static AVMetadataKey3GPUserData[] values = new AVMetadataKey3GPUserData[] {Copyright, Author, Performer, Genre, 
        RecordingYear, Location, Title, Description, Collection, UserRating, Thumbnail, AlbumAndTrack, KeywordList, MediaClassification,
        MediaRating};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKey3GPUserData(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKey3GPUserData valueOf(NSString value) {
        for (AVMetadataKey3GPUserData v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKey3GPUserData/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyRecordingYear", optional=true)
    protected static native NSString RecordingYearValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyLocation", optional=true)
    protected static native NSString LocationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCollection", optional=true)
    protected static native NSString CollectionValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyUserRating", optional=true)
    protected static native NSString UserRatingValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyThumbnail", optional=true)
    protected static native NSString ThumbnailValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAlbumAndTrack", optional=true)
    protected static native NSString AlbumAndTrackValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyKeywordList", optional=true)
    protected static native NSString KeywordListValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaClassification", optional=true)
    protected static native NSString MediaClassificationValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaRating", optional=true)
    protected static native NSString MediaRatingValue();
    /*</methods>*/
}
