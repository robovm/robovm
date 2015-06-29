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
@Marshaler(/*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataKeyiTunesMetadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataKeyiTunesMetadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataKeyiTunesMetadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataKeyiTunesMetadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataKeyiTunesMetadata o : l) {
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
    public static final AVMetadataKeyiTunesMetadata Album = new AVMetadataKeyiTunesMetadata("Album");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Artist = new AVMetadataKeyiTunesMetadata("Artist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata UserComment = new AVMetadataKeyiTunesMetadata("UserComment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata CoverArt = new AVMetadataKeyiTunesMetadata("CoverArt");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Copyright = new AVMetadataKeyiTunesMetadata("Copyright");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ReleaseDate = new AVMetadataKeyiTunesMetadata("ReleaseDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EncodedBy = new AVMetadataKeyiTunesMetadata("EncodedBy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PredefinedGenre = new AVMetadataKeyiTunesMetadata("PredefinedGenre");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata UserGenre = new AVMetadataKeyiTunesMetadata("UserGenre");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SongName = new AVMetadataKeyiTunesMetadata("SongName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata TrackSubTitle = new AVMetadataKeyiTunesMetadata("TrackSubTitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EncodingTool = new AVMetadataKeyiTunesMetadata("EncodingTool");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Composer = new AVMetadataKeyiTunesMetadata("Composer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AlbumArtist = new AVMetadataKeyiTunesMetadata("AlbumArtist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AccountKind = new AVMetadataKeyiTunesMetadata("AccountKind");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata AppleID = new AVMetadataKeyiTunesMetadata("AppleID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ArtistID = new AVMetadataKeyiTunesMetadata("ArtistID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SongID = new AVMetadataKeyiTunesMetadata("SongID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata DiscCompilation = new AVMetadataKeyiTunesMetadata("DiscCompilation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata DiscNumber = new AVMetadataKeyiTunesMetadata("DiscNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata GenreID = new AVMetadataKeyiTunesMetadata("GenreID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Grouping = new AVMetadataKeyiTunesMetadata("Grouping");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PlaylistID = new AVMetadataKeyiTunesMetadata("PlaylistID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ContentRating = new AVMetadataKeyiTunesMetadata("ContentRating");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata BeatsPerMin = new AVMetadataKeyiTunesMetadata("BeatsPerMin");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata TrackNumber = new AVMetadataKeyiTunesMetadata("TrackNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ArtDirector = new AVMetadataKeyiTunesMetadata("ArtDirector");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Arranger = new AVMetadataKeyiTunesMetadata("Arranger");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Author = new AVMetadataKeyiTunesMetadata("Author");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Lyrics = new AVMetadataKeyiTunesMetadata("Lyrics");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Acknowledgement = new AVMetadataKeyiTunesMetadata("Acknowledgement");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Conductor = new AVMetadataKeyiTunesMetadata("Conductor");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Description = new AVMetadataKeyiTunesMetadata("Description");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Director = new AVMetadataKeyiTunesMetadata("Director");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata EQ = new AVMetadataKeyiTunesMetadata("EQ");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata LinerNotes = new AVMetadataKeyiTunesMetadata("LinerNotes");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata RecordCompany = new AVMetadataKeyiTunesMetadata("RecordCompany");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata OriginalArtist = new AVMetadataKeyiTunesMetadata("OriginalArtist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata PhonogramRights = new AVMetadataKeyiTunesMetadata("PhonogramRights");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Producer = new AVMetadataKeyiTunesMetadata("Producer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Performer = new AVMetadataKeyiTunesMetadata("Performer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Publisher = new AVMetadataKeyiTunesMetadata("Publisher");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata SoundEngineer = new AVMetadataKeyiTunesMetadata("SoundEngineer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Soloist = new AVMetadataKeyiTunesMetadata("Soloist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Credits = new AVMetadataKeyiTunesMetadata("Credits");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata Thanks = new AVMetadataKeyiTunesMetadata("Thanks");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata OnlineExtras = new AVMetadataKeyiTunesMetadata("OnlineExtras");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyiTunesMetadata ExecProducer = new AVMetadataKeyiTunesMetadata("ExecProducer");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/[] values = new /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/[] {/*<value_list>*/Album, Artist, UserComment, CoverArt, Copyright, ReleaseDate, EncodedBy, PredefinedGenre, UserGenre, SongName, TrackSubTitle, EncodingTool, Composer, AlbumArtist, AccountKind, AppleID, ArtistID, SongID, DiscCompilation, DiscNumber, GenreID, Grouping, PlaylistID, ContentRating, BeatsPerMin, TrackNumber, ArtDirector, Arranger, Author, Lyrics, Acknowledgement, Conductor, Description, Director, EQ, LinerNotes, RecordCompany, OriginalArtist, PhonogramRights, Producer, Performer, Publisher, SoundEngineer, Soloist, Credits, Thanks, OnlineExtras, ExecProducer/*</value_list>*/};
    
    /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeyiTunesMetadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserComment", optional=true)
        public static native NSString UserComment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCoverArt", optional=true)
        public static native NSString CoverArt();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyReleaseDate", optional=true)
        public static native NSString ReleaseDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPredefinedGenre", optional=true)
        public static native NSString PredefinedGenre();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserGenre", optional=true)
        public static native NSString UserGenre();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongName", optional=true)
        public static native NSString SongName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackSubTitle", optional=true)
        public static native NSString TrackSubTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodingTool", optional=true)
        public static native NSString EncodingTool();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbumArtist", optional=true)
        public static native NSString AlbumArtist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAccountKind", optional=true)
        public static native NSString AccountKind();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAppleID", optional=true)
        public static native NSString AppleID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtistID", optional=true)
        public static native NSString ArtistID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongID", optional=true)
        public static native NSString SongID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscCompilation", optional=true)
        public static native NSString DiscCompilation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscNumber", optional=true)
        public static native NSString DiscNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGenreID", optional=true)
        public static native NSString GenreID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGrouping", optional=true)
        public static native NSString Grouping();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPlaylistID", optional=true)
        public static native NSString PlaylistID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyContentRating", optional=true)
        public static native NSString ContentRating();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyBeatsPerMin", optional=true)
        public static native NSString BeatsPerMin();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackNumber", optional=true)
        public static native NSString TrackNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtDirector", optional=true)
        public static native NSString ArtDirector();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLyrics", optional=true)
        public static native NSString Lyrics();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAcknowledgement", optional=true)
        public static native NSString Acknowledgement();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyConductor", optional=true)
        public static native NSString Conductor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEQ", optional=true)
        public static native NSString EQ();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLinerNotes", optional=true)
        public static native NSString LinerNotes();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyRecordCompany", optional=true)
        public static native NSString RecordCompany();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPerformer", optional=true)
        public static native NSString Performer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoundEngineer", optional=true)
        public static native NSString SoundEngineer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoloist", optional=true)
        public static native NSString Soloist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyThanks", optional=true)
        public static native NSString Thanks();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOnlineExtras", optional=true)
        public static native NSString OnlineExtras();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataiTunesMetadataKeyExecProducer", optional=true)
        public static native NSString ExecProducer();
        /*</values>*/
    }
}
