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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMediaItem/*</name>*/ 
    extends /*<extends>*/MPMediaEntity/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPMediaItemPtr extends Ptr<MPMediaItem, MPMediaItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMediaItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMediaItem() {}
    protected MPMediaItem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "persistentID")
    public native long getPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "mediaType")
    public native MPMediaType getMediaType();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "title")
    public native String getTitle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "albumTitle")
    public native String getAlbumTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "albumPersistentID")
    public native long getAlbumPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "artist")
    public native String getArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "artistPersistentID")
    public native long getArtistPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "albumArtist")
    public native String getAlbumArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "albumArtistPersistentID")
    public native long getAlbumArtistPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "genre")
    public native String getGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "genrePersistentID")
    public native long getGenrePersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "composer")
    public native String getComposer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "composerPersistentID")
    public native long getComposerPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "playbackDuration")
    public native double getPlaybackDuration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "albumTrackNumber")
    public native @MachineSizedUInt long getAlbumTrackNumber();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "albumTrackCount")
    public native @MachineSizedUInt long getAlbumTrackCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "discNumber")
    public native @MachineSizedUInt long getDiscNumber();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "discCount")
    public native @MachineSizedUInt long getDiscCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "artwork")
    public native MPMediaItemArtwork getArtwork();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "lyrics")
    public native String getLyrics();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isCompilation")
    public native boolean isCompilation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "releaseDate")
    public native NSDate getReleaseDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "beatsPerMinute")
    public native @MachineSizedUInt long getBeatsPerMinute();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "comments")
    public native String getComments();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "assetURL")
    public native NSURL getAssetURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isCloudItem")
    public native boolean isCloudItem();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "podcastTitle")
    public native String getPodcastTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "podcastPersistentID")
    public native long getPodcastPersistentID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "playCount")
    public native @MachineSizedUInt long getPlayCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "skipCount")
    public native @MachineSizedUInt long getSkipCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "rating")
    public native @MachineSizedUInt long getRating();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "lastPlayedDate")
    public native NSDate getLastPlayedDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "userGrouping")
    public native String getUserGrouping();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "bookmarkTime")
    public native double getBookmarkTime();
    /*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public long getPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ItemPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getAlbumPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getArtistPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ArtistPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getAlbumArtistPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumArtistPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getGenrePersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.GenrePersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getComposerPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ComposerPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getPodcastPersistentIDLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PodcastPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    public MPMediaType getMediaTypeLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.MediaType);
        if (val != null) {
            return new MPMediaType(val.intValue());
        }
        return null;
    }
    public String getTitleLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Title);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getAlbumTitleLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.AlbumTitle);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getArtistLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Artist);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getAlbumArtistLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.AlbumArtist);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getGenreLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Genre);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getComposerLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Composer);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public double getPlaybackDurationLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PlaybackDuration);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    public int getAlbumTrackNumberLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumTrackNumber);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getAlbumTrackCountLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumTrackCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getDiscNumberLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.DiscNumber);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getDiscCountLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.DiscCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public MPMediaItemArtwork getArtworkLegacy() {
        MPMediaItemArtwork val = (MPMediaItemArtwork) getValue(MPMediaItemProperty.Artwork);
        if (val != null) {
            return val;
        }
        return null;
    }
    public String getLyricsLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Lyrics);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public boolean isCompilationLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.IsCompilation);
        if (val != null) {
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getReleaseDateLegacy() {
        NSDate val = (NSDate) getValue(MPMediaItemProperty.ReleaseDate);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBeatsPerMinuteLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.BeatsPerMinute);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getCommentsLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Comments);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getAssetURLLegacy() {
        NSURL val = (NSURL) getValue(MPMediaItemProperty.AssetURL);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isCloudItemLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.IsCloudItem);
        if (val != null) {
            return val.booleanValue();
        }
        return false;
    }
    public String getPodcastTitleLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.PodcastTitle);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public int getPlayCountLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PlayCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getSkipCountLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.SkipCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getRatingLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.Rating);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public NSDate getLastPlayedDateLegacy() {
        NSDate val = (NSDate) getValue(MPMediaItemProperty.LastPlayedDate);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getUserGroupingLegacy() {
        NSString val = (NSString) getValue(MPMediaItemProperty.UserGrouping);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getBookmarkTimeLegacy() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.BookmarkTime);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "persistentIDPropertyForGroupingType:")
    public static native MPMediaEntityProperty getPersistentIDProperty(MPMediaGrouping groupingType);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "titlePropertyForGroupingType:")
    public static native MPMediaEntityProperty getTitleProperty(MPMediaGrouping groupingType);
    /*</methods>*/
}
