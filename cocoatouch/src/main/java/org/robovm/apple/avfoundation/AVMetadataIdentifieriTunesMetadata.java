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
@Marshaler(/*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataIdentifieriTunesMetadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataIdentifieriTunesMetadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataIdentifieriTunesMetadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataIdentifieriTunesMetadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataIdentifieriTunesMetadata o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Album = new AVMetadataIdentifieriTunesMetadata("Album");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Artist = new AVMetadataIdentifieriTunesMetadata("Artist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata UserComment = new AVMetadataIdentifieriTunesMetadata("UserComment");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata CoverArt = new AVMetadataIdentifieriTunesMetadata("CoverArt");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Copyright = new AVMetadataIdentifieriTunesMetadata("Copyright");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ReleaseDate = new AVMetadataIdentifieriTunesMetadata("ReleaseDate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EncodedBy = new AVMetadataIdentifieriTunesMetadata("EncodedBy");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PredefinedGenre = new AVMetadataIdentifieriTunesMetadata("PredefinedGenre");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata UserGenre = new AVMetadataIdentifieriTunesMetadata("UserGenre");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SongName = new AVMetadataIdentifieriTunesMetadata("SongName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata TrackSubTitle = new AVMetadataIdentifieriTunesMetadata("TrackSubTitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EncodingTool = new AVMetadataIdentifieriTunesMetadata("EncodingTool");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Composer = new AVMetadataIdentifieriTunesMetadata("Composer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AlbumArtist = new AVMetadataIdentifieriTunesMetadata("AlbumArtist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AccountKind = new AVMetadataIdentifieriTunesMetadata("AccountKind");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata AppleID = new AVMetadataIdentifieriTunesMetadata("AppleID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ArtistID = new AVMetadataIdentifieriTunesMetadata("ArtistID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SongID = new AVMetadataIdentifieriTunesMetadata("SongID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata DiscCompilation = new AVMetadataIdentifieriTunesMetadata("DiscCompilation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata DiscNumber = new AVMetadataIdentifieriTunesMetadata("DiscNumber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata GenreID = new AVMetadataIdentifieriTunesMetadata("GenreID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Grouping = new AVMetadataIdentifieriTunesMetadata("Grouping");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PlaylistID = new AVMetadataIdentifieriTunesMetadata("PlaylistID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ContentRating = new AVMetadataIdentifieriTunesMetadata("ContentRating");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata BeatsPerMin = new AVMetadataIdentifieriTunesMetadata("BeatsPerMin");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata TrackNumber = new AVMetadataIdentifieriTunesMetadata("TrackNumber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ArtDirector = new AVMetadataIdentifieriTunesMetadata("ArtDirector");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Arranger = new AVMetadataIdentifieriTunesMetadata("Arranger");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Author = new AVMetadataIdentifieriTunesMetadata("Author");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Lyrics = new AVMetadataIdentifieriTunesMetadata("Lyrics");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Acknowledgement = new AVMetadataIdentifieriTunesMetadata("Acknowledgement");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Conductor = new AVMetadataIdentifieriTunesMetadata("Conductor");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Description = new AVMetadataIdentifieriTunesMetadata("Description");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Director = new AVMetadataIdentifieriTunesMetadata("Director");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata EQ = new AVMetadataIdentifieriTunesMetadata("EQ");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata LinerNotes = new AVMetadataIdentifieriTunesMetadata("LinerNotes");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata RecordCompany = new AVMetadataIdentifieriTunesMetadata("RecordCompany");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata OriginalArtist = new AVMetadataIdentifieriTunesMetadata("OriginalArtist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata PhonogramRights = new AVMetadataIdentifieriTunesMetadata("PhonogramRights");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Producer = new AVMetadataIdentifieriTunesMetadata("Producer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Performer = new AVMetadataIdentifieriTunesMetadata("Performer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Publisher = new AVMetadataIdentifieriTunesMetadata("Publisher");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata SoundEngineer = new AVMetadataIdentifieriTunesMetadata("SoundEngineer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Soloist = new AVMetadataIdentifieriTunesMetadata("Soloist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Credits = new AVMetadataIdentifieriTunesMetadata("Credits");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata Thanks = new AVMetadataIdentifieriTunesMetadata("Thanks");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata OnlineExtras = new AVMetadataIdentifieriTunesMetadata("OnlineExtras");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifieriTunesMetadata ExecProducer = new AVMetadataIdentifieriTunesMetadata("ExecProducer");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/[] values = new /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/[] {/*<value_list>*/Album, Artist, UserComment, CoverArt, Copyright, ReleaseDate, EncodedBy, PredefinedGenre, UserGenre, SongName, TrackSubTitle, EncodingTool, Composer, AlbumArtist, AccountKind, AppleID, ArtistID, SongID, DiscCompilation, DiscNumber, GenreID, Grouping, PlaylistID, ContentRating, BeatsPerMin, TrackNumber, ArtDirector, Arranger, Author, Lyrics, Acknowledgement, Conductor, Description, Director, EQ, LinerNotes, RecordCompany, OriginalArtist, PhonogramRights, Producer, Performer, Publisher, SoundEngineer, Soloist, Credits, Thanks, OnlineExtras, ExecProducer/*</value_list>*/};
    
    /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataIdentifieriTunesMetadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserComment", optional=true)
        public static native NSString UserComment();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCoverArt", optional=true)
        public static native NSString CoverArt();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataReleaseDate", optional=true)
        public static native NSString ReleaseDate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPredefinedGenre", optional=true)
        public static native NSString PredefinedGenre();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserGenre", optional=true)
        public static native NSString UserGenre();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongName", optional=true)
        public static native NSString SongName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackSubTitle", optional=true)
        public static native NSString TrackSubTitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodingTool", optional=true)
        public static native NSString EncodingTool();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbumArtist", optional=true)
        public static native NSString AlbumArtist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAccountKind", optional=true)
        public static native NSString AccountKind();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAppleID", optional=true)
        public static native NSString AppleID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtistID", optional=true)
        public static native NSString ArtistID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongID", optional=true)
        public static native NSString SongID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscCompilation", optional=true)
        public static native NSString DiscCompilation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscNumber", optional=true)
        public static native NSString DiscNumber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGenreID", optional=true)
        public static native NSString GenreID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGrouping", optional=true)
        public static native NSString Grouping();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPlaylistID", optional=true)
        public static native NSString PlaylistID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataContentRating", optional=true)
        public static native NSString ContentRating();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataBeatsPerMin", optional=true)
        public static native NSString BeatsPerMin();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackNumber", optional=true)
        public static native NSString TrackNumber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtDirector", optional=true)
        public static native NSString ArtDirector();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLyrics", optional=true)
        public static native NSString Lyrics();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAcknowledgement", optional=true)
        public static native NSString Acknowledgement();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataConductor", optional=true)
        public static native NSString Conductor();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEQ", optional=true)
        public static native NSString EQ();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLinerNotes", optional=true)
        public static native NSString LinerNotes();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataRecordCompany", optional=true)
        public static native NSString RecordCompany();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPerformer", optional=true)
        public static native NSString Performer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoundEngineer", optional=true)
        public static native NSString SoundEngineer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoloist", optional=true)
        public static native NSString Soloist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataThanks", optional=true)
        public static native NSString Thanks();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOnlineExtras", optional=true)
        public static native NSString OnlineExtras();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataExecProducer", optional=true)
        public static native NSString ExecProducer();
        /*</values>*/
    }
}
