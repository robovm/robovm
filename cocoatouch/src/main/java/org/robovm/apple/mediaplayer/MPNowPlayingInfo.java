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
/*<annotations>*/@Library("MediaPlayer")/*</annotations>*/
@Marshaler(/*<name>*/MPNowPlayingInfo/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPNowPlayingInfo/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MPNowPlayingInfo toObject(Class<MPNowPlayingInfo> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<MPNowPlayingInfo> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MPNowPlayingInfo> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new MPNowPlayingInfo(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MPNowPlayingInfo> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (MPNowPlayingInfo i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    MPNowPlayingInfo(NSDictionary data) {
        super(data);
    }
    public MPNowPlayingInfo() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public MPNowPlayingInfo set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getElapsedPlaybackTime() {
        if (has(Keys.ElapsedPlaybackTime())) {
            NSNumber val = (NSNumber) get(Keys.ElapsedPlaybackTime());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setElapsedPlaybackTime(double elapsedPlaybackTime) {
        set(Keys.ElapsedPlaybackTime(), NSNumber.valueOf(elapsedPlaybackTime));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getPlaybackRate() {
        if (has(Keys.PlaybackRate())) {
            NSNumber val = (NSNumber) get(Keys.PlaybackRate());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackRate(double playbackRate) {
        set(Keys.PlaybackRate(), NSNumber.valueOf(playbackRate));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getDefaultPlaybackRate() {
        if (has(Keys.DefaultPlaybackRate())) {
            NSNumber val = (NSNumber) get(Keys.DefaultPlaybackRate());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public MPNowPlayingInfo setDefaultPlaybackRate(double defaultPlaybackRate) {
        set(Keys.DefaultPlaybackRate(), NSNumber.valueOf(defaultPlaybackRate));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getPlaybackQueueIndex() {
        if (has(Keys.PlaybackQueueIndex())) {
            NSNumber val = (NSNumber) get(Keys.PlaybackQueueIndex());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackQueueIndex(int playbackQueueIndex) {
        set(Keys.PlaybackQueueIndex(), NSNumber.valueOf(playbackQueueIndex));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getPlaybackQueueCount() {
        if (has(Keys.PlaybackQueueCount())) {
            NSNumber val = (NSNumber) get(Keys.PlaybackQueueCount());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setPlaybackQueueCount(int playbackQueueCount) {
        set(Keys.PlaybackQueueCount(), NSNumber.valueOf(playbackQueueCount));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getChapterNumber() {
        if (has(Keys.ChapterNumber())) {
            NSNumber val = (NSNumber) get(Keys.ChapterNumber());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setChapterNumber(int chapterNumber) {
        set(Keys.ChapterNumber(), NSNumber.valueOf(chapterNumber));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public int getChapterCount() {
        if (has(Keys.ChapterCount())) {
            NSNumber val = (NSNumber) get(Keys.ChapterCount());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public MPNowPlayingInfo setChapterCount(int chapterCount) {
        set(Keys.ChapterCount(), NSNumber.valueOf(chapterCount));
        return this;
    }
    /*</methods>*/
    public boolean has(MPMediaEntityProperty key) {
        return data.containsKey(key.value());
    }
    public NSObject get(MPMediaEntityProperty key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public MPNowPlayingInfo set(MPMediaEntityProperty key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    
    public long getPersistentID() {
        if (has(MPMediaItemProperty.ItemPersistentID)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.ItemPersistentID);
            return val.longValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setPersistentID(long id) {
        set(MPMediaItemProperty.ItemPersistentID, NSNumber.valueOf(id));
        return this;
    }
    public String getTitle() {
        if (has(MPMediaItemProperty.Title)) {
            NSString val = (NSString) get(MPMediaItemProperty.Title);
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setTitle(String title) {
        set(MPMediaItemProperty.Title, new NSString(title));
        return this;
    }
    public String getAlbumTitle() {
        if (has(MPMediaItemProperty.AlbumTitle)) {
            NSString val = (NSString) get(MPMediaItemProperty.AlbumTitle);
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setAlbumTitle(String albumTitle) {
        set(MPMediaItemProperty.AlbumTitle, new NSString(albumTitle));
        return this;
    }
    public String getArtist() {
        if (has(MPMediaItemProperty.Artist)) {
            NSString val = (NSString) get(MPMediaItemProperty.Artist);
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setArtist(String artist) {
        set(MPMediaItemProperty.Artist, new NSString(artist));
        return this;
    }
    public String getGenre() {
        if (has(MPMediaItemProperty.Genre)) {
            NSString val = (NSString) get(MPMediaItemProperty.Genre);
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setGenre(String genre) {
        set(MPMediaItemProperty.Genre, new NSString(genre));
        return this;
    }
    public String getComposer() {
        if (has(MPMediaItemProperty.Composer)) {
            NSString val = (NSString) get(MPMediaItemProperty.Composer);
            return val.toString();
        }
        return null;
    }
    public MPNowPlayingInfo setComposer(String composer) {
        set(MPMediaItemProperty.Composer, new NSString(composer));
        return this;
    }
    public double getPlaybackDuration() {
        if (has(MPMediaItemProperty.PlaybackDuration)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.PlaybackDuration);
            return val.doubleValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setPlaybackDuration(double playbackDuration) {
        set(MPMediaItemProperty.PlaybackDuration, NSNumber.valueOf(playbackDuration));
        return this;
    }
    public int getAlbumTrackNumber() {
        if (has(MPMediaItemProperty.AlbumTrackNumber)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.AlbumTrackNumber);
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setAlbumTrackNumber(int albumTrackNumber) {
        set(MPMediaItemProperty.AlbumTrackNumber, NSNumber.valueOf(albumTrackNumber));
        return this;
    }
    public int getAlbumTrackCount() {
        if (has(MPMediaItemProperty.AlbumTrackCount)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.AlbumTrackCount);
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setAlbumTrackCount(int albumTrackCount) {
        set(MPMediaItemProperty.AlbumTrackCount, NSNumber.valueOf(albumTrackCount));
        return this;
    }
    public int getDiscNumber() {
        if (has(MPMediaItemProperty.DiscNumber)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.DiscNumber);
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setDiscNumber(int discNumber) {
        set(MPMediaItemProperty.DiscNumber, NSNumber.valueOf(discNumber));
        return this;
    }
    public int getDiscCount() {
        if (has(MPMediaItemProperty.DiscCount)) {
            NSNumber val = (NSNumber) get(MPMediaItemProperty.DiscCount);
            return val.intValue();
        }
        return 0;
    }
    public MPNowPlayingInfo setDiscCount(int discCount) {
        set(MPMediaItemProperty.DiscCount, NSNumber.valueOf(discCount));
        return this;
    }
    public MPMediaItemArtwork getArtwork() {
        if (has(MPMediaItemProperty.Artwork)) {
            MPMediaItemArtwork val = (MPMediaItemArtwork) get(MPMediaItemProperty.Artwork);
            return val;
        }
        return null;
    }
    public MPNowPlayingInfo setArtwork(MPMediaItemArtwork artwork) {
        set(MPMediaItemProperty.Artwork, artwork);
        return this;
    }
    
    /*<keys>*/
    @Library("MediaPlayer")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyElapsedPlaybackTime", optional=true)
        public static native NSString ElapsedPlaybackTime();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackRate", optional=true)
        public static native NSString PlaybackRate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyDefaultPlaybackRate", optional=true)
        public static native NSString DefaultPlaybackRate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueIndex", optional=true)
        public static native NSString PlaybackQueueIndex();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueCount", optional=true)
        public static native NSString PlaybackQueueCount();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterNumber", optional=true)
        public static native NSString ChapterNumber();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterCount", optional=true)
        public static native NSString ChapterCount();
    }
    /*</keys>*/
}
