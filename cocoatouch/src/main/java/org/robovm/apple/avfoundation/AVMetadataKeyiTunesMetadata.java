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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMetadataKeyiTunesMetadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyiTunesMetadata toObject(Class<AVMetadataKeyiTunesMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyiTunesMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyiTunesMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeyiTunesMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Album = new AVMetadataKeyiTunesMetadata("AlbumValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Artist = new AVMetadataKeyiTunesMetadata("ArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata UserComment = new AVMetadataKeyiTunesMetadata("UserCommentValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata CoverArt = new AVMetadataKeyiTunesMetadata("CoverArtValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Copyright = new AVMetadataKeyiTunesMetadata("CopyrightValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ReleaseDate = new AVMetadataKeyiTunesMetadata("ReleaseDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EncodedBy = new AVMetadataKeyiTunesMetadata("EncodedByValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PredefinedGenre = new AVMetadataKeyiTunesMetadata("PredefinedGenreValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata UserGenre = new AVMetadataKeyiTunesMetadata("UserGenreValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SongName = new AVMetadataKeyiTunesMetadata("SongNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata TrackSubTitle = new AVMetadataKeyiTunesMetadata("TrackSubTitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EncodingTool = new AVMetadataKeyiTunesMetadata("EncodingToolValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Composer = new AVMetadataKeyiTunesMetadata("ComposerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AlbumArtist = new AVMetadataKeyiTunesMetadata("AlbumArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AccountKind = new AVMetadataKeyiTunesMetadata("AccountKindValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AppleID = new AVMetadataKeyiTunesMetadata("AppleIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ArtistID = new AVMetadataKeyiTunesMetadata("ArtistIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SongID = new AVMetadataKeyiTunesMetadata("SongIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata DiscCompilation = new AVMetadataKeyiTunesMetadata("DiscCompilationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata DiscNumber = new AVMetadataKeyiTunesMetadata("DiscNumberValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata GenreID = new AVMetadataKeyiTunesMetadata("GenreIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Grouping = new AVMetadataKeyiTunesMetadata("GroupingValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PlaylistID = new AVMetadataKeyiTunesMetadata("PlaylistIDValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ContentRating = new AVMetadataKeyiTunesMetadata("ContentRatingValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata BeatsPerMin = new AVMetadataKeyiTunesMetadata("BeatsPerMinValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata TrackNumber = new AVMetadataKeyiTunesMetadata("TrackNumberValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ArtDirector = new AVMetadataKeyiTunesMetadata("ArtDirectorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Arranger = new AVMetadataKeyiTunesMetadata("ArrangerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Author = new AVMetadataKeyiTunesMetadata("AuthorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Lyrics = new AVMetadataKeyiTunesMetadata("LyricsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Acknowledgement = new AVMetadataKeyiTunesMetadata("AcknowledgementValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Conductor = new AVMetadataKeyiTunesMetadata("ConductorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Description = new AVMetadataKeyiTunesMetadata("DescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Director = new AVMetadataKeyiTunesMetadata("DirectorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EQ = new AVMetadataKeyiTunesMetadata("EQValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata LinerNotes = new AVMetadataKeyiTunesMetadata("LinerNotesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata RecordCompany = new AVMetadataKeyiTunesMetadata("RecordCompanyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata OriginalArtist = new AVMetadataKeyiTunesMetadata("OriginalArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PhonogramRights = new AVMetadataKeyiTunesMetadata("PhonogramRightsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Producer = new AVMetadataKeyiTunesMetadata("ProducerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Performer = new AVMetadataKeyiTunesMetadata("PerformerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Publisher = new AVMetadataKeyiTunesMetadata("PublisherValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SoundEngineer = new AVMetadataKeyiTunesMetadata("SoundEngineerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Soloist = new AVMetadataKeyiTunesMetadata("SoloistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Credits = new AVMetadataKeyiTunesMetadata("CreditsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Thanks = new AVMetadataKeyiTunesMetadata("ThanksValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata OnlineExtras = new AVMetadataKeyiTunesMetadata("OnlineExtrasValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ExecProducer = new AVMetadataKeyiTunesMetadata("ExecProducerValue");
    
    private static AVMetadataKeyiTunesMetadata[] values = new AVMetadataKeyiTunesMetadata[] {Album, Artist, UserComment, CoverArt, Copyright, 
        ReleaseDate, EncodedBy, PredefinedGenre, UserGenre, SongName, TrackSubTitle, EncodingTool, Composer, AlbumArtist, AccountKind, AppleID, ArtistID, 
        SongID, DiscCompilation, DiscNumber, GenreID, Grouping, PlaylistID, ContentRating, BeatsPerMin, TrackNumber, ArtDirector, Arranger, Author, 
        Lyrics, Acknowledgement, Conductor, Description, Director, EQ, LinerNotes, RecordCompany, OriginalArtist, PhonogramRights, Producer, Performer, 
        Publisher, SoundEngineer, Soloist, Credits, Thanks, OnlineExtras, ExecProducer};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKeyiTunesMetadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeyiTunesMetadata valueOf(NSString value) {
        for (AVMetadataKeyiTunesMetadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserComment", optional=true)
    protected static native NSString UserCommentValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCoverArt", optional=true)
    protected static native NSString CoverArtValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyReleaseDate", optional=true)
    protected static native NSString ReleaseDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPredefinedGenre", optional=true)
    protected static native NSString PredefinedGenreValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserGenre", optional=true)
    protected static native NSString UserGenreValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongName", optional=true)
    protected static native NSString SongNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackSubTitle", optional=true)
    protected static native NSString TrackSubTitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodingTool", optional=true)
    protected static native NSString EncodingToolValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbumArtist", optional=true)
    protected static native NSString AlbumArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAccountKind", optional=true)
    protected static native NSString AccountKindValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAppleID", optional=true)
    protected static native NSString AppleIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtistID", optional=true)
    protected static native NSString ArtistIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongID", optional=true)
    protected static native NSString SongIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscCompilation", optional=true)
    protected static native NSString DiscCompilationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscNumber", optional=true)
    protected static native NSString DiscNumberValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGenreID", optional=true)
    protected static native NSString GenreIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGrouping", optional=true)
    protected static native NSString GroupingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPlaylistID", optional=true)
    protected static native NSString PlaylistIDValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyContentRating", optional=true)
    protected static native NSString ContentRatingValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyBeatsPerMin", optional=true)
    protected static native NSString BeatsPerMinValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackNumber", optional=true)
    protected static native NSString TrackNumberValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtDirector", optional=true)
    protected static native NSString ArtDirectorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLyrics", optional=true)
    protected static native NSString LyricsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAcknowledgement", optional=true)
    protected static native NSString AcknowledgementValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyConductor", optional=true)
    protected static native NSString ConductorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEQ", optional=true)
    protected static native NSString EQValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLinerNotes", optional=true)
    protected static native NSString LinerNotesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyRecordCompany", optional=true)
    protected static native NSString RecordCompanyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoundEngineer", optional=true)
    protected static native NSString SoundEngineerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoloist", optional=true)
    protected static native NSString SoloistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyThanks", optional=true)
    protected static native NSString ThanksValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOnlineExtras", optional=true)
    protected static native NSString OnlineExtrasValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyExecProducer", optional=true)
    protected static native NSString ExecProducerValue();
    /*</methods>*/
}
