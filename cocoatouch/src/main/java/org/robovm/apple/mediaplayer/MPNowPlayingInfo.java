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
/*</javadoc>*/
@Marshaler(MPNowPlayingInfo.Marshaler.class)
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPNowPlayingInfo/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static MPNowPlayingInfo toObject(Class<MPNowPlayingInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MPNowPlayingInfo(o);
        }
        @MarshalsPointer
        public static long toNative(MPNowPlayingInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    public MPNowPlayingInfo() {
        data = new NSMutableDictionary<>();
    }
    protected MPNowPlayingInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(MPNowPlayingInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    public long getPersistentID() {
        if (data.containsKey(MPMediaItemProperty.ItemPersistentIDValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.ItemPersistentIDValue());
            return val.longValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setPersistentID(long id) {
        data.put(MPMediaItemProperty.ItemPersistentIDValue(), NSNumber.valueOf(id));
        return this;
    }
    public String getTitle() {
        if (data.containsKey(MPMediaItemProperty.TitleValue())) {
            NSString val = (NSString) data.get(MPMediaItemProperty.TitleValue());
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setTitle(String title) {
        data.put(MPMediaItemProperty.TitleValue(), new NSString(title));
        return this;
    }
    public String getAlbumTitle() {
        if (data.containsKey(MPMediaItemProperty.AlbumTitleValue())) {
            NSString val = (NSString) data.get(MPMediaItemProperty.AlbumTitleValue());
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setAlbumTitle(String albumTitle) {
        data.put(MPMediaItemProperty.AlbumTitleValue(), new NSString(albumTitle));
        return this;
    }
    public String getArtist() {
        if (data.containsKey(MPMediaItemProperty.ArtistValue())) {
            NSString val = (NSString) data.get(MPMediaItemProperty.ArtistValue());
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setArtist(String artist) {
        data.put(MPMediaItemProperty.ArtistValue(), new NSString(artist));
        return this;
    }
    public String getGenre() {
        if (data.containsKey(MPMediaItemProperty.GenreValue())) {
            NSString val = (NSString) data.get(MPMediaItemProperty.GenreValue());
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setGenre(String genre) {
        data.put(MPMediaItemProperty.GenreValue(), new NSString(genre));
        return this;
    }
    public String getComposer() {
        if (data.containsKey(MPMediaItemProperty.ComposerValue())) {
            NSString val = (NSString) data.get(MPMediaItemProperty.ComposerValue());
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setComposer(String composer) {
        data.put(MPMediaItemProperty.ComposerValue(), new NSString(composer));
        return this;
    }
    public double getPlaybackDuration() {
        if (data.containsKey(MPMediaItemProperty.PlaybackDurationValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.PlaybackDurationValue());
            return val.doubleValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setPlaybackDuration(double playbackDuration) {
        data.put(MPMediaItemProperty.PlaybackDurationValue(), NSNumber.valueOf(playbackDuration));
        return this;
    }
    public int getAlbumTrackNumber() {
        if (data.containsKey(MPMediaItemProperty.AlbumTrackNumberValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.AlbumTrackNumberValue());
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setAlbumTrackNumber(int albumTrackNumber) {
        data.put(MPMediaItemProperty.AlbumTrackNumberValue(), NSNumber.valueOf(albumTrackNumber));
        return this;
    }
    public int getAlbumTrackCount() {
        if (data.containsKey(MPMediaItemProperty.AlbumTrackCountValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.AlbumTrackCountValue());
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setAlbumTrackCount(int albumTrackCount) {
        data.put(MPMediaItemProperty.AlbumTrackCountValue(), NSNumber.valueOf(albumTrackCount));
        return this;
    }
    public int getDiscNumber() {
        if (data.containsKey(MPMediaItemProperty.DiscNumberValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.DiscNumberValue());
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setDiscNumber(int discNumber) {
        data.put(MPMediaItemProperty.DiscNumberValue(), NSNumber.valueOf(discNumber));
        return this;
    }
    public int getDiscCount() {
        if (data.containsKey(MPMediaItemProperty.DiscCountValue())) {
            NSNumber val = (NSNumber) data.get(MPMediaItemProperty.DiscCountValue());
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setDiscCount(int discCount) {
        data.put(MPMediaItemProperty.DiscCountValue(), NSNumber.valueOf(discCount));
        return this;
    }
    public MPMediaItemArtwork getArtwork() {
        if (data.containsKey(MPMediaItemProperty.ArtworkValue())) {
            MPMediaItemArtwork val = (MPMediaItemArtwork) data.get(MPMediaItemProperty.ArtworkValue());
            return val;
        }
        return null;
    }
    public MPNowPlayingInfo setArtwork(MPMediaItemArtwork artwork) {
        data.put(MPMediaItemProperty.ArtworkValue(), artwork);
        return this;
    }
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getElapsedPlaybackTime() {
        if (data.containsKey(ElapsedPlaybackTime())) {
            NSNumber val = (NSNumber) data.get(ElapsedPlaybackTime());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setElapsedPlaybackTime(double elapsedPlaybackTime) {
        data.put(ElapsedPlaybackTime(), NSNumber.valueOf(elapsedPlaybackTime));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getPlaybackRate() {
        if (data.containsKey(PlaybackRate())) {
            NSNumber val = (NSNumber) data.get(PlaybackRate());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackRate(double playbackRate) {
        data.put(PlaybackRate(), NSNumber.valueOf(playbackRate));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getPlaybackQueueIndex() {
        if (data.containsKey(PlaybackQueueIndex())) {
            NSNumber val = (NSNumber) data.get(PlaybackQueueIndex());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackQueueIndex(int playbackQueueIndex) {
        data.put(PlaybackQueueIndex(), NSNumber.valueOf(playbackQueueIndex));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getPlaybackQueueCount() {
        if (data.containsKey(PlaybackQueueCount())) {
            NSNumber val = (NSNumber) data.get(PlaybackQueueCount());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackQueueCount(int playbackQueueCount) {
        data.put(PlaybackQueueCount(), NSNumber.valueOf(playbackQueueCount));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getChapterNumber() {
        if (data.containsKey(ChapterNumber())) {
            NSNumber val = (NSNumber) data.get(ChapterNumber());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setChapterNumber(int chapterNumber) {
        data.put(ChapterNumber(), NSNumber.valueOf(chapterNumber));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getChapterCount() {
        if (data.containsKey(ChapterCount())) {
            NSNumber val = (NSNumber) data.get(ChapterCount());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setChapterCount(int chapterCount) {
        data.put(ChapterCount(), NSNumber.valueOf(chapterCount));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getDefaultPlaybackRate() {
        if (data.containsKey(DefaultPlaybackRate())) {
            NSNumber val = (NSNumber) data.get(DefaultPlaybackRate());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public MPNowPlayingInfo setDefaultPlaybackRate(double playbackRate) {
        data.put(DefaultPlaybackRate(), NSNumber.valueOf(playbackRate));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyElapsedPlaybackTime", optional=true)
    protected static native NSString ElapsedPlaybackTime();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackRate", optional=true)
    protected static native NSString PlaybackRate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyDefaultPlaybackRate", optional=true)
    protected static native NSString DefaultPlaybackRate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueIndex", optional=true)
    protected static native NSString PlaybackQueueIndex();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueCount", optional=true)
    protected static native NSString PlaybackQueueCount();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterNumber", optional=true)
    protected static native NSString ChapterNumber();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterCount", optional=true)
    protected static native NSString ChapterCount();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
