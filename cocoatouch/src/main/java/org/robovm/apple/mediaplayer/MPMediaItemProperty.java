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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaItemProperty/*</name>*/ 
    extends /*<extends>*/MPMediaEntityProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MPMediaItemProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final MPMediaItemProperty ItemPersistentID = new MPMediaItemProperty("ItemPersistentIDValue", true);
    public static final MPMediaItemProperty MediaType = new MPMediaItemProperty("MediaTypeValue", true);
    public static final MPMediaItemProperty Title = new MPMediaItemProperty("TitleValue", true);
    public static final MPMediaItemProperty AlbumTitle = new MPMediaItemProperty("AlbumTitleValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty AlbumPersistentID = new MPMediaItemProperty("AlbumPersistentIDValue", true);
    public static final MPMediaItemProperty Artist = new MPMediaItemProperty("ArtistValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty ArtistPersistentID = new MPMediaItemProperty("ArtistPersistentIDValue", true);
    public static final MPMediaItemProperty AlbumArtist = new MPMediaItemProperty("AlbumArtistValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty AlbumArtistPersistentID = new MPMediaItemProperty("AlbumArtistPersistentIDValue", true);
    public static final MPMediaItemProperty Genre = new MPMediaItemProperty("GenreValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty GenrePersistentID = new MPMediaItemProperty("GenrePersistentIDValue", true);
    public static final MPMediaItemProperty Composer = new MPMediaItemProperty("ComposerValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty ComposerPersistentID = new MPMediaItemProperty("ComposerPersistentIDValue", true);
    public static final MPMediaItemProperty PlaybackDuration = new MPMediaItemProperty("PlaybackDurationValue", false);
    public static final MPMediaItemProperty AlbumTrackNumber = new MPMediaItemProperty("AlbumTrackNumberValue", false);
    public static final MPMediaItemProperty AlbumTrackCount = new MPMediaItemProperty("AlbumTrackCountValue", false);
    public static final MPMediaItemProperty DiscNumber = new MPMediaItemProperty("DiscNumberValue", false);
    public static final MPMediaItemProperty DiscCount = new MPMediaItemProperty("DiscCountValue", false);
    public static final MPMediaItemProperty Artwork = new MPMediaItemProperty("ArtworkValue", false);
    public static final MPMediaItemProperty Lyrics = new MPMediaItemProperty("LyricsValue", false);
    public static final MPMediaItemProperty IsCompilation = new MPMediaItemProperty("IsCompilationValue", true);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty ReleaseDate = new MPMediaItemProperty("ReleaseDateValue", false);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty BeatsPerMinute = new MPMediaItemProperty("BeatsPerMinuteValue", false);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty Comments = new MPMediaItemProperty("CommentsValue", false);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty AssetURL = new MPMediaItemProperty("AssetURLValue", false);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MPMediaItemProperty IsCloudItem = new MPMediaItemProperty("IsCloudItemValue", true);
    public static final MPMediaItemProperty PodcastTitle = new MPMediaItemProperty("PodcastTitleValue", true);
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static final MPMediaItemProperty PodcastPersistentID = new MPMediaItemProperty("PodcastPersistentIDValue", true);
    public static final MPMediaItemProperty PlayCount = new MPMediaItemProperty("PlayCountValue", false);
    public static final MPMediaItemProperty SkipCount = new MPMediaItemProperty("SkipCountValue", false);
    public static final MPMediaItemProperty Rating = new MPMediaItemProperty("RatingValue", false);
    public static final MPMediaItemProperty LastPlayedDate = new MPMediaItemProperty("LastPlayedDateValue", false);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final MPMediaItemProperty UserGrouping = new MPMediaItemProperty("UserGroupingValue", false);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final MPMediaItemProperty BookmarkTime = new MPMediaItemProperty("BookmarkTimeValue", false);
    private static MPMediaItemProperty[] values = new MPMediaItemProperty[] {ReleaseDate, BeatsPerMinute, Comments, AssetURL, UserGrouping, AlbumPersistentID, ArtistPersistentID, AlbumArtistPersistentID, 
        GenrePersistentID, ComposerPersistentID, PodcastPersistentID, IsCloudItem, BookmarkTime, ItemPersistentID, MediaType, Title, AlbumTitle, Artist, AlbumArtist, Genre, Composer, PlaybackDuration, 
        AlbumTrackNumber, AlbumTrackCount, DiscNumber, DiscCount, Artwork, Lyrics, IsCompilation, PodcastTitle, PlayCount, SkipCount, Rating, LastPlayedDate};
    
    private MPMediaItemProperty(String getterName, boolean filterable) {
        super(getterName, filterable);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    protected static MPMediaItemProperty findValue(NSString value) {
        for (MPMediaItemProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyPersistentID", optional=true)
    protected static native NSString ItemPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyMediaType", optional=true)
    protected static native NSString MediaTypeValue();
    @GlobalValue(symbol="MPMediaItemPropertyTitle", optional=true)
    protected static native NSString TitleValue();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTitle", optional=true)
    protected static native NSString AlbumTitleValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAlbumPersistentID", optional=true)
    protected static native NSString AlbumPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyArtistPersistentID", optional=true)
    protected static native NSString ArtistPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtist", optional=true)
    protected static native NSString AlbumArtistValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtistPersistentID", optional=true)
    protected static native NSString AlbumArtistPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyGenrePersistentID", optional=true)
    protected static native NSString GenrePersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyComposerPersistentID", optional=true)
    protected static native NSString ComposerPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyPlaybackDuration", optional=true)
    protected static native NSString PlaybackDurationValue();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackNumber", optional=true)
    protected static native NSString AlbumTrackNumberValue();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackCount", optional=true)
    protected static native NSString AlbumTrackCountValue();
    @GlobalValue(symbol="MPMediaItemPropertyDiscNumber", optional=true)
    protected static native NSString DiscNumberValue();
    @GlobalValue(symbol="MPMediaItemPropertyDiscCount", optional=true)
    protected static native NSString DiscCountValue();
    @GlobalValue(symbol="MPMediaItemPropertyArtwork", optional=true)
    protected static native NSString ArtworkValue();
    @GlobalValue(symbol="MPMediaItemPropertyLyrics", optional=true)
    protected static native NSString LyricsValue();
    @GlobalValue(symbol="MPMediaItemPropertyIsCompilation", optional=true)
    protected static native NSString IsCompilationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyReleaseDate", optional=true)
    protected static native NSString ReleaseDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyBeatsPerMinute", optional=true)
    protected static native NSString BeatsPerMinuteValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyComments", optional=true)
    protected static native NSString CommentsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAssetURL", optional=true)
    protected static native NSString AssetURLValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyIsCloudItem", optional=true)
    protected static native NSString IsCloudItemValue();
    @GlobalValue(symbol="MPMediaItemPropertyPodcastTitle", optional=true)
    protected static native NSString PodcastTitleValue();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyPodcastPersistentID", optional=true)
    protected static native NSString PodcastPersistentIDValue();
    @GlobalValue(symbol="MPMediaItemPropertyPlayCount", optional=true)
    protected static native NSString PlayCountValue();
    @GlobalValue(symbol="MPMediaItemPropertySkipCount", optional=true)
    protected static native NSString SkipCountValue();
    @GlobalValue(symbol="MPMediaItemPropertyRating", optional=true)
    protected static native NSString RatingValue();
    @GlobalValue(symbol="MPMediaItemPropertyLastPlayedDate", optional=true)
    protected static native NSString LastPlayedDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyUserGrouping", optional=true)
    protected static native NSString UserGroupingValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyBookmarkTime", optional=true)
    protected static native NSString BookmarkTimeValue();
    /*</methods>*/
}
