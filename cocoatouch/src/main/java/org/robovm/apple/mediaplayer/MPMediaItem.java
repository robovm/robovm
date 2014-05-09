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

/**
 *
 * <div class="javadoc"></div>
 */
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
    /*<methods>*/
    @GlobalValue(symbol="MPMediaItemPropertyPersistentID", optional=true)
    public static native NSString PropertyPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyMediaType", optional=true)
    public static native NSString PropertyMediaType();
    @GlobalValue(symbol="MPMediaItemPropertyTitle", optional=true)
    public static native NSString PropertyTitle();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTitle", optional=true)
    public static native NSString PropertyAlbumTitle();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumPersistentID", optional=true)
    public static native NSString PropertyAlbumPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyArtist", optional=true)
    public static native NSString PropertyArtist();
    @GlobalValue(symbol="MPMediaItemPropertyArtistPersistentID", optional=true)
    public static native NSString PropertyArtistPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtist", optional=true)
    public static native NSString PropertyAlbumArtist();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumArtistPersistentID", optional=true)
    public static native NSString PropertyAlbumArtistPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyGenre", optional=true)
    public static native NSString PropertyGenre();
    @GlobalValue(symbol="MPMediaItemPropertyGenrePersistentID", optional=true)
    public static native NSString PropertyGenrePersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyComposer", optional=true)
    public static native NSString PropertyComposer();
    @GlobalValue(symbol="MPMediaItemPropertyComposerPersistentID", optional=true)
    public static native NSString PropertyComposerPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyPlaybackDuration", optional=true)
    public static native NSString PropertyPlaybackDuration();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackNumber", optional=true)
    public static native NSString PropertyAlbumTrackNumber();
    @GlobalValue(symbol="MPMediaItemPropertyAlbumTrackCount", optional=true)
    public static native NSString PropertyAlbumTrackCount();
    @GlobalValue(symbol="MPMediaItemPropertyDiscNumber", optional=true)
    public static native NSString PropertyDiscNumber();
    @GlobalValue(symbol="MPMediaItemPropertyDiscCount", optional=true)
    public static native NSString PropertyDiscCount();
    @GlobalValue(symbol="MPMediaItemPropertyArtwork", optional=true)
    public static native NSString PropertyArtwork();
    @GlobalValue(symbol="MPMediaItemPropertyLyrics", optional=true)
    public static native NSString PropertyLyrics();
    @GlobalValue(symbol="MPMediaItemPropertyIsCompilation", optional=true)
    public static native NSString PropertyIsCompilation();
    @GlobalValue(symbol="MPMediaItemPropertyReleaseDate", optional=true)
    public static native NSString PropertyReleaseDate();
    @GlobalValue(symbol="MPMediaItemPropertyBeatsPerMinute", optional=true)
    public static native NSString PropertyBeatsPerMinute();
    @GlobalValue(symbol="MPMediaItemPropertyComments", optional=true)
    public static native NSString PropertyComments();
    @GlobalValue(symbol="MPMediaItemPropertyAssetURL", optional=true)
    public static native NSString PropertyAssetURL();
    @GlobalValue(symbol="MPMediaItemPropertyIsCloudItem", optional=true)
    public static native NSString PropertyIsCloudItem();
    @GlobalValue(symbol="MPMediaItemPropertyPodcastTitle", optional=true)
    public static native NSString PropertyPodcastTitle();
    @GlobalValue(symbol="MPMediaItemPropertyPodcastPersistentID", optional=true)
    public static native NSString PropertyPodcastPersistentID();
    @GlobalValue(symbol="MPMediaItemPropertyPlayCount", optional=true)
    public static native NSString PropertyPlayCount();
    @GlobalValue(symbol="MPMediaItemPropertySkipCount", optional=true)
    public static native NSString PropertySkipCount();
    @GlobalValue(symbol="MPMediaItemPropertyRating", optional=true)
    public static native NSString PropertyRating();
    @GlobalValue(symbol="MPMediaItemPropertyLastPlayedDate", optional=true)
    public static native NSString PropertyLastPlayedDate();
    @GlobalValue(symbol="MPMediaItemPropertyUserGrouping", optional=true)
    public static native NSString PropertyUserGrouping();
    @GlobalValue(symbol="MPMediaItemPropertyBookmarkTime", optional=true)
    public static native NSString PropertyBookmarkTime();
    
    @Method(selector = "persistentIDPropertyForGroupingType:")
    public static native String getPersistentIDProperty(MPMediaGrouping groupingType);
    @Method(selector = "titlePropertyForGroupingType:")
    public static native String getTitleProperty(MPMediaGrouping groupingType);
    /*</methods>*/
}
