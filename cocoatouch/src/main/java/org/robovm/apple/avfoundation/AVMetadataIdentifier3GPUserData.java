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
@Marshaler(AVMetadataIdentifier3GPUserData.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class AVMetadataIdentifier3GPUserData 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifier3GPUserData toObject(Class<AVMetadataIdentifier3GPUserData> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifier3GPUserData.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifier3GPUserData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifier3GPUserData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Copyright = new AVMetadataIdentifier3GPUserData("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Author = new AVMetadataIdentifier3GPUserData("AuthorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Performer = new AVMetadataIdentifier3GPUserData("PerformerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Genre = new AVMetadataIdentifier3GPUserData("GenreValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData RecordingYear = new AVMetadataIdentifier3GPUserData("RecordingYearValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Location = new AVMetadataIdentifier3GPUserData("LocationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Title = new AVMetadataIdentifier3GPUserData("TitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Description = new AVMetadataIdentifier3GPUserData("DescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Collection = new AVMetadataIdentifier3GPUserData("CollectionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData UserRating = new AVMetadataIdentifier3GPUserData("UserRatingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData Thumbnail = new AVMetadataIdentifier3GPUserData("ThumbnailValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData AlbumAndTrack = new AVMetadataIdentifier3GPUserData("AlbumAndTrackValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData KeywordList = new AVMetadataIdentifier3GPUserData("KeywordListValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData MediaClassification = new AVMetadataIdentifier3GPUserData("MediaClassificationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifier3GPUserData MediaRating = new AVMetadataIdentifier3GPUserData("MediaRatingValue");
    
    private static AVMetadataIdentifier3GPUserData[] values = new AVMetadataIdentifier3GPUserData[] {Copyright, Author, Performer, Genre, 
        RecordingYear, Location, Title, Description, Collection, UserRating, Thumbnail, AlbumAndTrack, KeywordList, MediaClassification, 
        MediaRating};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifier3GPUserData(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifier3GPUserData valueOf(NSString value) {
        for (AVMetadataIdentifier3GPUserData v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifier3GPUserData/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataRecordingYear", optional=true)
    protected static native NSString RecordingYearValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataLocation", optional=true)
    protected static native NSString LocationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataCollection", optional=true)
    protected static native NSString CollectionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataUserRating", optional=true)
    protected static native NSString UserRatingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataThumbnail", optional=true)
    protected static native NSString ThumbnailValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataAlbumAndTrack", optional=true)
    protected static native NSString AlbumAndTrackValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataKeywordList", optional=true)
    protected static native NSString KeywordListValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataMediaClassification", optional=true)
    protected static native NSString MediaClassificationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataMediaRating", optional=true)
    protected static native NSString MediaRatingValue();
    /*</methods>*/
}
