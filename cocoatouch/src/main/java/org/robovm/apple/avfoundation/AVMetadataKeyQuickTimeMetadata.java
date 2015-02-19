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
@Marshaler(AVMetadataKeyQuickTimeMetadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyQuickTimeMetadata toObject(Class<AVMetadataKeyQuickTimeMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyQuickTimeMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyQuickTimeMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeyQuickTimeMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Author = new AVMetadataKeyQuickTimeMetadata("AuthorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Comment = new AVMetadataKeyQuickTimeMetadata("CommentValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Copyright = new AVMetadataKeyQuickTimeMetadata("CopyrightValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CreationDate = new AVMetadataKeyQuickTimeMetadata("CreationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Director = new AVMetadataKeyQuickTimeMetadata("DirectorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DisplayName = new AVMetadataKeyQuickTimeMetadata("DisplayNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Information = new AVMetadataKeyQuickTimeMetadata("InformationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Keywords = new AVMetadataKeyQuickTimeMetadata("KeywordsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Producer = new AVMetadataKeyQuickTimeMetadata("ProducerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Publisher = new AVMetadataKeyQuickTimeMetadata("PublisherValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Album = new AVMetadataKeyQuickTimeMetadata("AlbumValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Artist = new AVMetadataKeyQuickTimeMetadata("ArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Artwork = new AVMetadataKeyQuickTimeMetadata("ArtworkValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Description = new AVMetadataKeyQuickTimeMetadata("DescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Software = new AVMetadataKeyQuickTimeMetadata("SoftwareValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Year = new AVMetadataKeyQuickTimeMetadata("YearValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Genre = new AVMetadataKeyQuickTimeMetadata("GenreValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata iXML = new AVMetadataKeyQuickTimeMetadata("iXMLValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationISO6709 = new AVMetadataKeyQuickTimeMetadata("LocationISO6709Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Make = new AVMetadataKeyQuickTimeMetadata("MakeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Model = new AVMetadataKeyQuickTimeMetadata("ModelValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Arranger = new AVMetadataKeyQuickTimeMetadata("ArrangerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata EncodedBy = new AVMetadataKeyQuickTimeMetadata("EncodedByValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata OriginalArtist = new AVMetadataKeyQuickTimeMetadata("OriginalArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Performer = new AVMetadataKeyQuickTimeMetadata("PerformerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Composer = new AVMetadataKeyQuickTimeMetadata("ComposerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Credits = new AVMetadataKeyQuickTimeMetadata("CreditsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata PhonogramRights = new AVMetadataKeyQuickTimeMetadata("PhonogramRightsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CameraIdentifier = new AVMetadataKeyQuickTimeMetadata("CameraIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CameraFrameReadoutTime = new AVMetadataKeyQuickTimeMetadata("CameraFrameReadoutTimeValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Title = new AVMetadataKeyQuickTimeMetadata("TitleValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CollectionUser = new AVMetadataKeyQuickTimeMetadata("CollectionUserValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata RatingUser = new AVMetadataKeyQuickTimeMetadata("RatingUserValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationName = new AVMetadataKeyQuickTimeMetadata("LocationNameValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationBody = new AVMetadataKeyQuickTimeMetadata("LocationBodyValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationNote = new AVMetadataKeyQuickTimeMetadata("LocationNoteValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationRole = new AVMetadataKeyQuickTimeMetadata("LocationRoleValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationDate = new AVMetadataKeyQuickTimeMetadata("LocationDateValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DirectionFacing = new AVMetadataKeyQuickTimeMetadata("DirectionFacingValue");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DirectionMotion = new AVMetadataKeyQuickTimeMetadata("DirectionMotionValue");
    
    private static AVMetadataKeyQuickTimeMetadata[] values = new AVMetadataKeyQuickTimeMetadata[] {Author, Comment, Copyright, CreationDate, Director, 
        DisplayName, Information, Keywords, Producer, Publisher, Album, Artist, Artwork, Description, Software, Year, Genre, iXML, LocationISO6709, 
        Make, Model, Arranger, EncodedBy, OriginalArtist, Performer, Composer, Credits, PhonogramRights, CameraIdentifier, CameraFrameReadoutTime, 
        Title, CollectionUser, RatingUser, LocationName, LocationBody, LocationNote, LocationRole, LocationDate, DirectionFacing, DirectionMotion};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKeyQuickTimeMetadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeyQuickTimeMetadata valueOf(NSString value) {
        for (AVMetadataKeyQuickTimeMetadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComment", optional=true)
    protected static native NSString CommentValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDisplayName", optional=true)
    protected static native NSString DisplayNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyInformation", optional=true)
    protected static native NSString InformationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyKeywords", optional=true)
    protected static native NSString KeywordsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtwork", optional=true)
    protected static native NSString ArtworkValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeySoftware", optional=true)
    protected static native NSString SoftwareValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyYear", optional=true)
    protected static native NSString YearValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyiXML", optional=true)
    protected static native NSString iXMLValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationISO6709", optional=true)
    protected static native NSString LocationISO6709Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraIdentifier", optional=true)
    protected static native NSString CameraIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime", optional=true)
    protected static native NSString CameraFrameReadoutTimeValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCollectionUser", optional=true)
    protected static native NSString CollectionUserValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyRatingUser", optional=true)
    protected static native NSString RatingUserValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationName", optional=true)
    protected static native NSString LocationNameValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationBody", optional=true)
    protected static native NSString LocationBodyValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationNote", optional=true)
    protected static native NSString LocationNoteValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationRole", optional=true)
    protected static native NSString LocationRoleValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationDate", optional=true)
    protected static native NSString LocationDateValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionFacing", optional=true)
    protected static native NSString DirectionFacingValue();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionMotion", optional=true)
    protected static native NSString DirectionMotionValue();
    /*</methods>*/
}
