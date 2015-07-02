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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/MPMediaItemProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaItemProperty/*</name>*/ 
    extends /*<extends>*/MPMediaEntityProperty/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/MPMediaItemProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MPMediaItemProperty toObject(Class<MPMediaItemProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return MPMediaItemProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(MPMediaItemProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<MPMediaItemProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MPMediaItemProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(MPMediaItemProperty.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MPMediaItemProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (MPMediaItemProperty o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty ItemPersistentID = new MPMediaItemProperty("ItemPersistentID");
    public static final MPMediaItemProperty MediaType = new MPMediaItemProperty("MediaType");
    public static final MPMediaItemProperty Title = new MPMediaItemProperty("Title");
    public static final MPMediaItemProperty AlbumTitle = new MPMediaItemProperty("AlbumTitle");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty AlbumPersistentID = new MPMediaItemProperty("AlbumPersistentID");
    public static final MPMediaItemProperty Artist = new MPMediaItemProperty("Artist");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty ArtistPersistentID = new MPMediaItemProperty("ArtistPersistentID");
    public static final MPMediaItemProperty AlbumArtist = new MPMediaItemProperty("AlbumArtist");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty AlbumArtistPersistentID = new MPMediaItemProperty("AlbumArtistPersistentID");
    public static final MPMediaItemProperty Genre = new MPMediaItemProperty("Genre");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty GenrePersistentID = new MPMediaItemProperty("GenrePersistentID");
    public static final MPMediaItemProperty Composer = new MPMediaItemProperty("Composer");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty ComposerPersistentID = new MPMediaItemProperty("ComposerPersistentID");
    public static final MPMediaItemProperty PlaybackDuration = new MPMediaItemProperty("PlaybackDuration");
    public static final MPMediaItemProperty AlbumTrackNumber = new MPMediaItemProperty("AlbumTrackNumber");
    public static final MPMediaItemProperty AlbumTrackCount = new MPMediaItemProperty("AlbumTrackCount");
    public static final MPMediaItemProperty DiscNumber = new MPMediaItemProperty("DiscNumber");
    public static final MPMediaItemProperty DiscCount = new MPMediaItemProperty("DiscCount");
    public static final MPMediaItemProperty Artwork = new MPMediaItemProperty("Artwork");
    public static final MPMediaItemProperty Lyrics = new MPMediaItemProperty("Lyrics");
    public static final MPMediaItemProperty IsCompilation = new MPMediaItemProperty("IsCompilation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty ReleaseDate = new MPMediaItemProperty("ReleaseDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty BeatsPerMinute = new MPMediaItemProperty("BeatsPerMinute");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty Comments = new MPMediaItemProperty("Comments");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty AssetURL = new MPMediaItemProperty("AssetURL");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MPMediaItemProperty IsCloudItem = new MPMediaItemProperty("IsCloudItem");
    public static final MPMediaItemProperty PodcastTitle = new MPMediaItemProperty("PodcastTitle");
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty PodcastPersistentID = new MPMediaItemProperty("PodcastPersistentID");
    public static final MPMediaItemProperty PlayCount = new MPMediaItemProperty("PlayCount");
    public static final MPMediaItemProperty SkipCount = new MPMediaItemProperty("SkipCount");
    public static final MPMediaItemProperty Rating = new MPMediaItemProperty("Rating");
    public static final MPMediaItemProperty LastPlayedDate = new MPMediaItemProperty("LastPlayedDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty UserGrouping = new MPMediaItemProperty("UserGrouping");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MPMediaItemProperty BookmarkTime = new MPMediaItemProperty("BookmarkTime");
    /*</constants>*/
    
    private static /*<name>*/MPMediaItemProperty/*</name>*/[] values = new /*<name>*/MPMediaItemProperty/*</name>*/[] {/*<value_list>*/ItemPersistentID, MediaType, Title, AlbumTitle, AlbumPersistentID, Artist, ArtistPersistentID, AlbumArtist, AlbumArtistPersistentID, Genre, GenrePersistentID, Composer, ComposerPersistentID, PlaybackDuration, AlbumTrackNumber, AlbumTrackCount, DiscNumber, DiscCount, Artwork, Lyrics, IsCompilation, ReleaseDate, BeatsPerMinute, Comments, AssetURL, IsCloudItem, PodcastTitle, PodcastPersistentID, PlayCount, SkipCount, Rating, LastPlayedDate, UserGrouping, BookmarkTime/*</value_list>*/};
    
    /*<name>*/MPMediaItemProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/MPMediaItemProperty/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/MPMediaItemProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/MPMediaItemProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("MediaPlayer") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyPersistentID", optional=true)
        public static native NSString ItemPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyMediaType", optional=true)
        public static native NSString MediaType();
        @GlobalValue(symbol="MPMediaItemPropertyTitle", optional=true)
        public static native NSString Title();
        @GlobalValue(symbol="MPMediaItemPropertyAlbumTitle", optional=true)
        public static native NSString AlbumTitle();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyAlbumPersistentID", optional=true)
        public static native NSString AlbumPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyArtistPersistentID", optional=true)
        public static native NSString ArtistPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyAlbumArtist", optional=true)
        public static native NSString AlbumArtist();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyAlbumArtistPersistentID", optional=true)
        public static native NSString AlbumArtistPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyGenre", optional=true)
        public static native NSString Genre();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyGenrePersistentID", optional=true)
        public static native NSString GenrePersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyComposerPersistentID", optional=true)
        public static native NSString ComposerPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyPlaybackDuration", optional=true)
        public static native NSString PlaybackDuration();
        @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackNumber", optional=true)
        public static native NSString AlbumTrackNumber();
        @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackCount", optional=true)
        public static native NSString AlbumTrackCount();
        @GlobalValue(symbol="MPMediaItemPropertyDiscNumber", optional=true)
        public static native NSString DiscNumber();
        @GlobalValue(symbol="MPMediaItemPropertyDiscCount", optional=true)
        public static native NSString DiscCount();
        @GlobalValue(symbol="MPMediaItemPropertyArtwork", optional=true)
        public static native NSString Artwork();
        @GlobalValue(symbol="MPMediaItemPropertyLyrics", optional=true)
        public static native NSString Lyrics();
        @GlobalValue(symbol="MPMediaItemPropertyIsCompilation", optional=true)
        public static native NSString IsCompilation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyReleaseDate", optional=true)
        public static native NSString ReleaseDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyBeatsPerMinute", optional=true)
        public static native NSString BeatsPerMinute();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyComments", optional=true)
        public static native NSString Comments();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyAssetURL", optional=true)
        public static native NSString AssetURL();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyIsCloudItem", optional=true)
        public static native NSString IsCloudItem();
        @GlobalValue(symbol="MPMediaItemPropertyPodcastTitle", optional=true)
        public static native NSString PodcastTitle();
        /**
         * @since Available in iOS 4.2 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyPodcastPersistentID", optional=true)
        public static native NSString PodcastPersistentID();
        @GlobalValue(symbol="MPMediaItemPropertyPlayCount", optional=true)
        public static native NSString PlayCount();
        @GlobalValue(symbol="MPMediaItemPropertySkipCount", optional=true)
        public static native NSString SkipCount();
        @GlobalValue(symbol="MPMediaItemPropertyRating", optional=true)
        public static native NSString Rating();
        @GlobalValue(symbol="MPMediaItemPropertyLastPlayedDate", optional=true)
        public static native NSString LastPlayedDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyUserGrouping", optional=true)
        public static native NSString UserGrouping();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="MPMediaItemPropertyBookmarkTime", optional=true)
        public static native NSString BookmarkTime();
        /*</values>*/
    }
}
