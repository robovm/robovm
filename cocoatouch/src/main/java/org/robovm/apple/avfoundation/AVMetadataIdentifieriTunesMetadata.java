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
@Marshaler(AVMetadataIdentifieriTunesMetadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifieriTunesMetadata toObject(Class<AVMetadataIdentifieriTunesMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifieriTunesMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifieriTunesMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifieriTunesMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Album = new AVMetadataIdentifieriTunesMetadata("AlbumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Artist = new AVMetadataIdentifieriTunesMetadata("ArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata UserComment = new AVMetadataIdentifieriTunesMetadata("UserCommentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata CoverArt = new AVMetadataIdentifieriTunesMetadata("CoverArtValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Copyright = new AVMetadataIdentifieriTunesMetadata("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ReleaseDate = new AVMetadataIdentifieriTunesMetadata("ReleaseDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EncodedBy = new AVMetadataIdentifieriTunesMetadata("EncodedByValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PredefinedGenre = new AVMetadataIdentifieriTunesMetadata("PredefinedGenreValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata UserGenre = new AVMetadataIdentifieriTunesMetadata("UserGenreValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SongName = new AVMetadataIdentifieriTunesMetadata("SongNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata TrackSubTitle = new AVMetadataIdentifieriTunesMetadata("TrackSubTitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EncodingTool = new AVMetadataIdentifieriTunesMetadata("EncodingToolValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Composer = new AVMetadataIdentifieriTunesMetadata("ComposerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AlbumArtist = new AVMetadataIdentifieriTunesMetadata("AlbumArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AccountKind = new AVMetadataIdentifieriTunesMetadata("AccountKindValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AppleID = new AVMetadataIdentifieriTunesMetadata("AppleIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ArtistID = new AVMetadataIdentifieriTunesMetadata("ArtistIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SongID = new AVMetadataIdentifieriTunesMetadata("SongIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata DiscCompilation = new AVMetadataIdentifieriTunesMetadata("DiscCompilationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata DiscNumber = new AVMetadataIdentifieriTunesMetadata("DiscNumberValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata GenreID = new AVMetadataIdentifieriTunesMetadata("GenreIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Grouping = new AVMetadataIdentifieriTunesMetadata("GroupingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PlaylistID = new AVMetadataIdentifieriTunesMetadata("PlaylistIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ContentRating = new AVMetadataIdentifieriTunesMetadata("ContentRatingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata BeatsPerMin = new AVMetadataIdentifieriTunesMetadata("BeatsPerMinValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata TrackNumber = new AVMetadataIdentifieriTunesMetadata("TrackNumberValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ArtDirector = new AVMetadataIdentifieriTunesMetadata("ArtDirectorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Arranger = new AVMetadataIdentifieriTunesMetadata("ArrangerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Author = new AVMetadataIdentifieriTunesMetadata("AuthorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Lyrics = new AVMetadataIdentifieriTunesMetadata("LyricsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Acknowledgement = new AVMetadataIdentifieriTunesMetadata("AcknowledgementValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Conductor = new AVMetadataIdentifieriTunesMetadata("ConductorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Description = new AVMetadataIdentifieriTunesMetadata("DescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Director = new AVMetadataIdentifieriTunesMetadata("DirectorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EQ = new AVMetadataIdentifieriTunesMetadata("EQValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata LinerNotes = new AVMetadataIdentifieriTunesMetadata("LinerNotesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata RecordCompany = new AVMetadataIdentifieriTunesMetadata("RecordCompanyValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata OriginalArtist = new AVMetadataIdentifieriTunesMetadata("OriginalArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PhonogramRights = new AVMetadataIdentifieriTunesMetadata("PhonogramRightsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Producer = new AVMetadataIdentifieriTunesMetadata("ProducerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Performer = new AVMetadataIdentifieriTunesMetadata("PerformerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Publisher = new AVMetadataIdentifieriTunesMetadata("PublisherValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SoundEngineer = new AVMetadataIdentifieriTunesMetadata("SoundEngineerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Soloist = new AVMetadataIdentifieriTunesMetadata("SoloistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Credits = new AVMetadataIdentifieriTunesMetadata("CreditsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Thanks = new AVMetadataIdentifieriTunesMetadata("ThanksValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata OnlineExtras = new AVMetadataIdentifieriTunesMetadata("OnlineExtrasValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ExecProducer = new AVMetadataIdentifieriTunesMetadata("ExecProducerValue");
    
    private static AVMetadataIdentifieriTunesMetadata[] values = new AVMetadataIdentifieriTunesMetadata[] {Album, Artist, UserComment, CoverArt, Copyright, 
        ReleaseDate, EncodedBy, PredefinedGenre, UserGenre, SongName, TrackSubTitle, EncodingTool, Composer, AlbumArtist, AccountKind, AppleID, ArtistID, 
        SongID, DiscCompilation, DiscNumber, GenreID, Grouping, PlaylistID, ContentRating, BeatsPerMin, TrackNumber, ArtDirector, Arranger, Author, 
        Lyrics, Acknowledgement, Conductor, Description, Director, EQ, LinerNotes, RecordCompany, OriginalArtist, PhonogramRights, Producer, Performer, 
        Publisher, SoundEngineer, Soloist, Credits, Thanks, OnlineExtras, ExecProducer};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifieriTunesMetadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifieriTunesMetadata valueOf(NSString value) {
        for (AVMetadataIdentifieriTunesMetadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserComment", optional=true)
    protected static native NSString UserCommentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCoverArt", optional=true)
    protected static native NSString CoverArtValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataReleaseDate", optional=true)
    protected static native NSString ReleaseDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPredefinedGenre", optional=true)
    protected static native NSString PredefinedGenreValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserGenre", optional=true)
    protected static native NSString UserGenreValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongName", optional=true)
    protected static native NSString SongNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackSubTitle", optional=true)
    protected static native NSString TrackSubTitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodingTool", optional=true)
    protected static native NSString EncodingToolValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbumArtist", optional=true)
    protected static native NSString AlbumArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAccountKind", optional=true)
    protected static native NSString AccountKindValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAppleID", optional=true)
    protected static native NSString AppleIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtistID", optional=true)
    protected static native NSString ArtistIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongID", optional=true)
    protected static native NSString SongIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscCompilation", optional=true)
    protected static native NSString DiscCompilationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscNumber", optional=true)
    protected static native NSString DiscNumberValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGenreID", optional=true)
    protected static native NSString GenreIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGrouping", optional=true)
    protected static native NSString GroupingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPlaylistID", optional=true)
    protected static native NSString PlaylistIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataContentRating", optional=true)
    protected static native NSString ContentRatingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataBeatsPerMin", optional=true)
    protected static native NSString BeatsPerMinValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackNumber", optional=true)
    protected static native NSString TrackNumberValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtDirector", optional=true)
    protected static native NSString ArtDirectorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLyrics", optional=true)
    protected static native NSString LyricsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAcknowledgement", optional=true)
    protected static native NSString AcknowledgementValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataConductor", optional=true)
    protected static native NSString ConductorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEQ", optional=true)
    protected static native NSString EQValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLinerNotes", optional=true)
    protected static native NSString LinerNotesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataRecordCompany", optional=true)
    protected static native NSString RecordCompanyValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPerformer", optional=true)
    protected static native NSString PerformerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoundEngineer", optional=true)
    protected static native NSString SoundEngineerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoloist", optional=true)
    protected static native NSString SoloistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataThanks", optional=true)
    protected static native NSString ThanksValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOnlineExtras", optional=true)
    protected static native NSString OnlineExtrasValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataExecProducer", optional=true)
    protected static native NSString ExecProducerValue();
    /*</methods>*/
}
