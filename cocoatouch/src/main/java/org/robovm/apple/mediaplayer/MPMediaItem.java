/*
 * Copyright (C) 2014 Trillian Mobile AB
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
    
    /*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public long getPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ItemPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getAlbumPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getArtistPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ArtistPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getAlbumArtistPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumArtistPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getGenrePersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.GenrePersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getComposerPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.ComposerPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public long getPodcastPersistentID() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PodcastPersistentID);
        if (val != null) {
            return val.longValue();
        }
        return 0;
    }
    public MPMediaType getMediaType() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.MediaType);
        if (val != null) {
            return new MPMediaType(val.intValue());
        }
        return null;
    }
    public String getTitle() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Title);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getAlbumTitle() {
        NSString val = (NSString) getValue(MPMediaItemProperty.AlbumTitle);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getArtist() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Artist);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getAlbumArtist() {
        NSString val = (NSString) getValue(MPMediaItemProperty.AlbumArtist);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getGenre() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Genre);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public String getComposer() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Composer);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public double getPlaybackDuration() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PlaybackDuration);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    public int getAlbumTrackNumber() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumTrackNumber);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getAlbumTrackCount() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.AlbumTrackCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getDiscNumber() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.DiscNumber);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getDiscCount() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.DiscCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public MPMediaItemArtwork getArtwork() {
        MPMediaItemArtwork val = (MPMediaItemArtwork) getValue(MPMediaItemProperty.Artwork);
        if (val != null) {
            return val;
        }
        return null;
    }
    public String getLyrics() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Lyrics);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public boolean isCompilation() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.IsCompilation);
        if (val != null) {
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSDate getReleaseDate() {
        NSDate val = (NSDate) getValue(MPMediaItemProperty.ReleaseDate);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBeatsPerMinute() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.BeatsPerMinute);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getComments() {
        NSString val = (NSString) getValue(MPMediaItemProperty.Comments);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getAssetURL() {
        NSURL val = (NSURL) getValue(MPMediaItemProperty.AssetURL);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isCloudItem() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.IsCloudItem);
        if (val != null) {
            return val.booleanValue();
        }
        return false;
    }
    public String getPodcastTitle() {
        NSString val = (NSString) getValue(MPMediaItemProperty.PodcastTitle);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    public int getPlayCount() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.PlayCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getSkipCount() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.SkipCount);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public int getRating() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.Rating);
        if (val != null) {
            return val.intValue();
        }
        return 0;
    }
    public NSDate getLastPlayedDate() {
        NSDate val = (NSDate) getValue(MPMediaItemProperty.LastPlayedDate);
        if (val != null) {
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getUserGrouping() {
        NSString val = (NSString) getValue(MPMediaItemProperty.UserGrouping);
        if (val != null) {
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getBookmarkTime() {
        NSNumber val = (NSNumber) getValue(MPMediaItemProperty.BookmarkTime);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static MPMediaEntityProperty getPersistentIDProperty(MPMediaGrouping groupingType) {
        return MPMediaEntityProperty.valueOf(getPersistentIDProperty0(groupingType));
    }
    /**
     * @since Available in iOS 4.2 and later.
     */
    public static MPMediaEntityProperty getTitleProperty(MPMediaGrouping groupingType) {
        return MPMediaEntityProperty.valueOf(getTitleProperty0(groupingType));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "persistentIDPropertyForGroupingType:")
    protected static native NSString getPersistentIDProperty0(MPMediaGrouping groupingType);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Method(selector = "titlePropertyForGroupingType:")
    protected static native NSString getTitleProperty0(MPMediaGrouping groupingType);
    /*</methods>*/
}
