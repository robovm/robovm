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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMusicPlayerController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MPMediaPlayback/*</implements>*/ {

    /*<ptr>*/public static class MPMusicPlayerControllerPtr extends Ptr<MPMusicPlayerController, MPMusicPlayerControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMusicPlayerController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMusicPlayerController() {}
    protected MPMusicPlayerController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "playbackState")
    public native MPMusicPlaybackState getPlaybackState();
    @Property(selector = "repeatMode")
    public native MPMusicRepeatMode getRepeatMode();
    @Property(selector = "setRepeatMode:")
    public native void setRepeatMode(MPMusicRepeatMode v);
    @Property(selector = "shuffleMode")
    public native MPMusicShuffleMode getShuffleMode();
    @Property(selector = "setShuffleMode:")
    public native void setShuffleMode(MPMusicShuffleMode v);
    @Property(selector = "volume")
    public native float getVolume();
    @Property(selector = "setVolume:")
    public native void setVolume(float v);
    @Property(selector = "nowPlayingItem")
    public native MPMediaItem getNowPlayingItem();
    @Property(selector = "setNowPlayingItem:")
    public native void setNowPlayingItem(MPMediaItem v);
    @Property(selector = "indexOfNowPlayingItem")
    public native @MachineSizedUInt long getIndexOfNowPlayingItem();
    @Property(selector = "isPreparedToPlay")
    public native boolean isIsPreparedToPlay();
    @Property(selector = "currentPlaybackTime")
    public native double getCurrentPlaybackTime();
    @Property(selector = "setCurrentPlaybackTime:")
    public native void setCurrentPlaybackTime(double v);
    @Property(selector = "currentPlaybackRate")
    public native float getCurrentPlaybackRate();
    @Property(selector = "setCurrentPlaybackRate:")
    public native void setCurrentPlaybackRate(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="MPMusicPlayerControllerPlaybackStateDidChangeNotification", optional=true)
    public static native String NotificationPlaybackStateDidChange();
    @GlobalValue(symbol="MPMusicPlayerControllerNowPlayingItemDidChangeNotification", optional=true)
    public static native String NotificationNowPlayingItemDidChange();
    @GlobalValue(symbol="MPMusicPlayerControllerVolumeDidChangeNotification", optional=true)
    public static native String NotificationVolumeDidChange();
    
    @Method(selector = "applicationMusicPlayer")
    public static native MPMusicPlayerController getApplicationMusicPlayer();
    @Method(selector = "iPodMusicPlayer")
    public static native MPMusicPlayerController getIPodMusicPlayer();
    @Method(selector = "setQueueWithQuery:")
    public native void setQueue(MPMediaQuery query);
    @Method(selector = "setQueueWithItemCollection:")
    public native void setQueue(MPMediaItemCollection itemCollection);
    @Method(selector = "skipToNextItem")
    public native void skipToNextItem();
    @Method(selector = "skipToBeginning")
    public native void skipToBeginning();
    @Method(selector = "skipToPreviousItem")
    public native void skipToPreviousItem();
    @Method(selector = "beginGeneratingPlaybackNotifications")
    public native void beginGeneratingPlaybackNotifications();
    @Method(selector = "endGeneratingPlaybackNotifications")
    public native void endGeneratingPlaybackNotifications();
    @Method(selector = "prepareToPlay")
    public native void prepareToPlay();
    @Method(selector = "play")
    public native void play();
    @Method(selector = "pause")
    public native void pause();
    @Method(selector = "stop")
    public native void stop();
    @Method(selector = "beginSeekingForward")
    public native void beginSeekingForward();
    @Method(selector = "beginSeekingBackward")
    public native void beginSeekingBackward();
    @Method(selector = "endSeeking")
    public native void endSeeking();
    /*</methods>*/
}
