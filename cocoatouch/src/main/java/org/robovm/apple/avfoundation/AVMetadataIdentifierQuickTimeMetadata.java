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
@Marshaler(AVMetadataIdentifierQuickTimeMetadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifierQuickTimeMetadata toObject(Class<AVMetadataIdentifierQuickTimeMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifierQuickTimeMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifierQuickTimeMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifierQuickTimeMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Author = new AVMetadataIdentifierQuickTimeMetadata("AuthorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Comment = new AVMetadataIdentifierQuickTimeMetadata("CommentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Copyright = new AVMetadataIdentifierQuickTimeMetadata("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CreationDate = new AVMetadataIdentifierQuickTimeMetadata("CreationDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Director = new AVMetadataIdentifierQuickTimeMetadata("DirectorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DisplayName = new AVMetadataIdentifierQuickTimeMetadata("DisplayNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Information = new AVMetadataIdentifierQuickTimeMetadata("InformationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Keywords = new AVMetadataIdentifierQuickTimeMetadata("KeywordsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Producer = new AVMetadataIdentifierQuickTimeMetadata("ProducerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Publisher = new AVMetadataIdentifierQuickTimeMetadata("PublisherValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Album = new AVMetadataIdentifierQuickTimeMetadata("AlbumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Artist = new AVMetadataIdentifierQuickTimeMetadata("ArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Artwork = new AVMetadataIdentifierQuickTimeMetadata("ArtworkValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Description = new AVMetadataIdentifierQuickTimeMetadata("DescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Software = new AVMetadataIdentifierQuickTimeMetadata("SoftwareValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Year = new AVMetadataIdentifierQuickTimeMetadata("YearValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Genre = new AVMetadataIdentifierQuickTimeMetadata("GenreValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata iXML = new AVMetadataIdentifierQuickTimeMetadata("iXMLValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationISO6709 = new AVMetadataIdentifierQuickTimeMetadata("LocationISO6709Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Make = new AVMetadataIdentifierQuickTimeMetadata("MakeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Model = new AVMetadataIdentifierQuickTimeMetadata("ModelValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Arranger = new AVMetadataIdentifierQuickTimeMetadata("ArrangerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata EncodedBy = new AVMetadataIdentifierQuickTimeMetadata("EncodedByValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata OriginalArtist = new AVMetadataIdentifierQuickTimeMetadata("OriginalArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Performer = new AVMetadataIdentifierQuickTimeMetadata("PerformerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Composer = new AVMetadataIdentifierQuickTimeMetadata("ComposerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Credits = new AVMetadataIdentifierQuickTimeMetadata("CreditsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata PhonogramRights = new AVMetadataIdentifierQuickTimeMetadata("PhonogramRightsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CameraIdentifier = new AVMetadataIdentifierQuickTimeMetadata("CameraIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CameraFrameReadoutTime = new AVMetadataIdentifierQuickTimeMetadata("CameraFrameReadoutTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Title = new AVMetadataIdentifierQuickTimeMetadata("TitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CollectionUser = new AVMetadataIdentifierQuickTimeMetadata("CollectionUserValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata RatingUser = new AVMetadataIdentifierQuickTimeMetadata("RatingUserValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationName = new AVMetadataIdentifierQuickTimeMetadata("LocationNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationBody = new AVMetadataIdentifierQuickTimeMetadata("LocationBodyValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationNote = new AVMetadataIdentifierQuickTimeMetadata("LocationNoteValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationRole = new AVMetadataIdentifierQuickTimeMetadata("LocationRoleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationDate = new AVMetadataIdentifierQuickTimeMetadata("LocationDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DirectionFacing = new AVMetadataIdentifierQuickTimeMetadata("DirectionFacingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DirectionMotion = new AVMetadataIdentifierQuickTimeMetadata("DirectionMotionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata PreferredAffineTransform = new AVMetadataIdentifierQuickTimeMetadata("PreferredAffineTransformValue");
    
    private static AVMetadataIdentifierQuickTimeMetadata[] values = new AVMetadataIdentifierQuickTimeMetadata[] {Author, Comment, Copyright, CreationDate, Director, DisplayName, 
        Information, Keywords, Producer, Publisher, Album, Artist, Artwork, Description, Software, Year, Genre, iXML, LocationISO6709, Make, Model, Arranger, EncodedBy, 
        OriginalArtist, Performer, Composer, Credits, PhonogramRights, CameraIdentifier, CameraFrameReadoutTime, Title, CollectionUser, RatingUser, LocationName, LocationBody, 
        LocationNote, LocationRole, LocationDate, DirectionFacing, DirectionMotion, PreferredAffineTransform};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifierQuickTimeMetadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifierQuickTimeMetadata valueOf(NSString value) {
        for (AVMetadataIdentifierQuickTimeMetadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComment", optional=true)
    protected static native NSString CommentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDisplayName", optional=true)
    protected static native NSString DisplayNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataInformation", optional=true)
    protected static native NSString InformationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataKeywords", optional=true)
    protected static native NSString KeywordsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtwork", optional=true)
    protected static native NSString ArtworkValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataSoftware", optional=true)
    protected static native NSString SoftwareValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataYear", optional=true)
    protected static native NSString YearValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataiXML", optional=true)
    protected static native NSString iXMLValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationISO6709", optional=true)
    protected static native NSString LocationISO6709Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraIdentifier", optional=true)
    protected static native NSString CameraIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraFrameReadoutTime", optional=true)
    protected static native NSString CameraFrameReadoutTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCollectionUser", optional=true)
    protected static native NSString CollectionUserValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataRatingUser", optional=true)
    protected static native NSString RatingUserValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationName", optional=true)
    protected static native NSString LocationNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationBody", optional=true)
    protected static native NSString LocationBodyValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationNote", optional=true)
    protected static native NSString LocationNoteValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationRole", optional=true)
    protected static native NSString LocationRoleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationDate", optional=true)
    protected static native NSString LocationDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionFacing", optional=true)
    protected static native NSString DirectionFacingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionMotion", optional=true)
    protected static native NSString DirectionMotionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPreferredAffineTransform", optional=true)
    protected static native NSString PreferredAffineTransformValue();
    /*</methods>*/
}
