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
/*<annotations>*/@Library("MediaPlayer") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MediaPlayer/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MediaPlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaEntityPropertyPersistentID", optional=true)
    public static native NSString MediaEntityPropertyPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyPersistentID", optional=true)
    public static native NSString MediaItemPropertyPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyMediaType", optional=true)
    public static native NSString MediaItemPropertyMediaType();
    @GlobalValue(symbol="MPMediaItemPropertyTitle", optional=true)
    public static native NSString MediaItemPropertyTitle();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTitle", optional=true)
    public static native NSString MediaItemPropertyAlbumTitle();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAlbumPersistentID", optional=true)
    public static native NSString MediaItemPropertyAlbumPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyArtist", optional=true)
    public static native NSString MediaItemPropertyArtist();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyArtistPersistentID", optional=true)
    public static native NSString MediaItemPropertyArtistPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtist", optional=true)
    public static native NSString MediaItemPropertyAlbumArtist();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtistPersistentID", optional=true)
    public static native NSString MediaItemPropertyAlbumArtistPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyGenre", optional=true)
    public static native NSString MediaItemPropertyGenre();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyGenrePersistentID", optional=true)
    public static native NSString MediaItemPropertyGenrePersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyComposer", optional=true)
    public static native NSString MediaItemPropertyComposer();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyComposerPersistentID", optional=true)
    public static native NSString MediaItemPropertyComposerPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyPlaybackDuration", optional=true)
    public static native NSString MediaItemPropertyPlaybackDuration();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackNumber", optional=true)
    public static native NSString MediaItemPropertyAlbumTrackNumber();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackCount", optional=true)
    public static native NSString MediaItemPropertyAlbumTrackCount();
    @GlobalValue(symbol="MPMediaItemPropertyDiscNumber", optional=true)
    public static native NSString MediaItemPropertyDiscNumber();
    @GlobalValue(symbol="MPMediaItemPropertyDiscCount", optional=true)
    public static native NSString MediaItemPropertyDiscCount();
    @GlobalValue(symbol="MPMediaItemPropertyArtwork", optional=true)
    public static native NSString MediaItemPropertyArtwork();
    @GlobalValue(symbol="MPMediaItemPropertyLyrics", optional=true)
    public static native NSString MediaItemPropertyLyrics();
    @GlobalValue(symbol="MPMediaItemPropertyIsCompilation", optional=true)
    public static native NSString MediaItemPropertyIsCompilation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyReleaseDate", optional=true)
    public static native NSString MediaItemPropertyReleaseDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyBeatsPerMinute", optional=true)
    public static native NSString MediaItemPropertyBeatsPerMinute();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyComments", optional=true)
    public static native NSString MediaItemPropertyComments();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyAssetURL", optional=true)
    public static native NSString MediaItemPropertyAssetURL();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyIsCloudItem", optional=true)
    public static native NSString MediaItemPropertyIsCloudItem();
    @GlobalValue(symbol="MPMediaItemPropertyPodcastTitle", optional=true)
    public static native NSString MediaItemPropertyPodcastTitle();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyPodcastPersistentID", optional=true)
    public static native NSString MediaItemPropertyPodcastPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyPlayCount", optional=true)
    public static native NSString MediaItemPropertyPlayCount();
    @GlobalValue(symbol="MPMediaItemPropertySkipCount", optional=true)
    public static native NSString MediaItemPropertySkipCount();
    @GlobalValue(symbol="MPMediaItemPropertyRating", optional=true)
    public static native NSString MediaItemPropertyRating();
    @GlobalValue(symbol="MPMediaItemPropertyLastPlayedDate", optional=true)
    public static native NSString MediaItemPropertyLastPlayedDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyUserGrouping", optional=true)
    public static native NSString MediaItemPropertyUserGrouping();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMediaItemPropertyBookmarkTime", optional=true)
    public static native NSString MediaItemPropertyBookmarkTime();
    @GlobalValue(symbol="MPMediaLibraryDidChangeNotification", optional=true)
    public static native NSString MediaLibraryDidChangeNotification();
    @GlobalValue(symbol="MPMediaPlaylistPropertyPersistentID", optional=true)
    public static native NSString MediaPlaylistPropertyPersistentID();
    @GlobalValue(symbol="MPMediaPlaylistPropertyName", optional=true)
    public static native NSString MediaPlaylistPropertyName();
    @GlobalValue(symbol="MPMediaPlaylistPropertyPlaylistAttributes", optional=true)
    public static native NSString MediaPlaylistPropertyPlaylistAttributes();
    @GlobalValue(symbol="MPMediaPlaylistPropertySeedItems", optional=true)
    public static native NSString MediaPlaylistPropertySeedItems();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMediaPlaybackIsPreparedToPlayDidChangeNotification", optional=true)
    public static native NSString MediaPlaybackIsPreparedToPlayDidChangeNotification();
    @GlobalValue(symbol="MPMoviePlayerScalingModeDidChangeNotification", optional=true)
    public static native NSString MoviePlayerScalingModeDidChangeNotification();
    @GlobalValue(symbol="MPMoviePlayerPlaybackDidFinishNotification", optional=true)
    public static native NSString MoviePlayerPlaybackDidFinishNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerPlaybackDidFinishReasonUserInfoKey", optional=true)
    public static native NSString MoviePlayerPlaybackDidFinishReasonUserInfoKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerPlaybackStateDidChangeNotification", optional=true)
    public static native NSString MoviePlayerPlaybackStateDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerLoadStateDidChangeNotification", optional=true)
    public static native NSString MoviePlayerLoadStateDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerNowPlayingMovieDidChangeNotification", optional=true)
    public static native NSString MoviePlayerNowPlayingMovieDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerWillEnterFullscreenNotification", optional=true)
    public static native NSString MoviePlayerWillEnterFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerDidEnterFullscreenNotification", optional=true)
    public static native NSString MoviePlayerDidEnterFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerWillExitFullscreenNotification", optional=true)
    public static native NSString MoviePlayerWillExitFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerDidExitFullscreenNotification", optional=true)
    public static native NSString MoviePlayerDidExitFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationDurationUserInfoKey", optional=true)
    public static native NSString MoviePlayerFullscreenAnimationDurationUserInfoKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerFullscreenAnimationCurveUserInfoKey", optional=true)
    public static native NSString MoviePlayerFullscreenAnimationCurveUserInfoKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerIsAirPlayVideoActiveDidChangeNotification", optional=true)
    public static native NSString MoviePlayerIsAirPlayVideoActiveDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerReadyForDisplayDidChangeNotification", optional=true)
    public static native NSString MoviePlayerReadyForDisplayDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieMediaTypesAvailableNotification", optional=true)
    public static native NSString MovieMediaTypesAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieSourceTypeAvailableNotification", optional=true)
    public static native NSString MovieSourceTypeAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieDurationAvailableNotification", optional=true)
    public static native NSString MovieDurationAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieNaturalSizeAvailableNotification", optional=true)
    public static native NSString MovieNaturalSizeAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailImageRequestDidFinishNotification", optional=true)
    public static native NSString MoviePlayerThumbnailImageRequestDidFinishNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailImageKey", optional=true)
    public static native NSString MoviePlayerThumbnailImageKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailTimeKey", optional=true)
    public static native NSString MoviePlayerThumbnailTimeKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailErrorKey", optional=true)
    public static native NSString MoviePlayerThumbnailErrorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataUpdatedNotification", optional=true)
    public static native NSString MoviePlayerTimedMetadataUpdatedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataUserInfoKey", optional=true)
    public static native NSString MoviePlayerTimedMetadataUserInfoKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyName", optional=true)
    public static native NSString MoviePlayerTimedMetadataKeyName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyInfo", optional=true)
    public static native NSString MoviePlayerTimedMetadataKeyInfo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyMIMEType", optional=true)
    public static native NSString MoviePlayerTimedMetadataKeyMIMEType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyDataType", optional=true)
    public static native NSString MoviePlayerTimedMetadataKeyDataType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataKeyLanguageCode", optional=true)
    public static native NSString MoviePlayerTimedMetadataKeyLanguageCode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @GlobalValue(symbol="MPMoviePlayerContentPreloadDidFinishNotification", optional=true)
    public static native NSString MoviePlayerContentPreloadDidFinishNotification();
    @GlobalValue(symbol="MPMusicPlayerControllerPlaybackStateDidChangeNotification", optional=true)
    public static native NSString MusicPlayerControllerPlaybackStateDidChangeNotification();
    @GlobalValue(symbol="MPMusicPlayerControllerNowPlayingItemDidChangeNotification", optional=true)
    public static native NSString MusicPlayerControllerNowPlayingItemDidChangeNotification();
    @GlobalValue(symbol="MPMusicPlayerControllerVolumeDidChangeNotification", optional=true)
    public static native NSString MusicPlayerControllerVolumeDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyElapsedPlaybackTime", optional=true)
    public static native NSString NowPlayingInfoPropertyElapsedPlaybackTime();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackRate", optional=true)
    public static native NSString NowPlayingInfoPropertyPlaybackRate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueIndex", optional=true)
    public static native NSString NowPlayingInfoPropertyPlaybackQueueIndex();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyPlaybackQueueCount", optional=true)
    public static native NSString NowPlayingInfoPropertyPlaybackQueueCount();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterNumber", optional=true)
    public static native NSString NowPlayingInfoPropertyChapterNumber();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPNowPlayingInfoPropertyChapterCount", optional=true)
    public static native NSString NowPlayingInfoPropertyChapterCount();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="MPVolumeViewWirelessRoutesAvailableDidChangeNotification", optional=true)
    public static native NSString VolumeViewWirelessRoutesAvailableDidChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="MPVolumeViewWirelessRouteActiveDidChangeNotification", optional=true)
    public static native NSString VolumeViewWirelessRouteActiveDidChangeNotification();
    /*</methods>*/
}
