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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMoviePlayerController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MPMediaPlayback/*</implements>*/ {

    /*<ptr>*/public static class MPMoviePlayerControllerPtr extends Ptr<MPMoviePlayerController, MPMoviePlayerControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMoviePlayerController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMoviePlayerController() {}
    protected MPMoviePlayerController(SkipInit skipInit) { super(skipInit); }
    public MPMoviePlayerController(NSURL url) { super((SkipInit) null); initObject(initWithContentURL$(url)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "contentURL")
    public native NSURL getContentURL();
    @Property(selector = "setContentURL:")
    public native void setContentURL(NSURL v);
    @Property(selector = "view")
    public native UIView getView();
    @Property(selector = "backgroundView")
    public native UIView getBackgroundView();
    @Property(selector = "playbackState")
    public native MPMoviePlaybackState getPlaybackState();
    @Property(selector = "loadState")
    public native MPMovieLoadState getLoadState();
    @Property(selector = "controlStyle")
    public native MPMovieControlStyle getControlStyle();
    @Property(selector = "setControlStyle:")
    public native void setControlStyle(MPMovieControlStyle v);
    @Property(selector = "repeatMode")
    public native MPMovieRepeatMode getRepeatMode();
    @Property(selector = "setRepeatMode:")
    public native void setRepeatMode(MPMovieRepeatMode v);
    @Property(selector = "shouldAutoplay")
    public native boolean isShouldAutoplay();
    @Property(selector = "setShouldAutoplay:")
    public native void setShouldAutoplay(boolean v);
    @Property(selector = "isFullscreen")
    public native boolean isFullscreen();
    @Property(selector = "setFullscreen:")
    public native void setFullscreen(boolean v);
    @Property(selector = "scalingMode")
    public native MPMovieScalingMode getScalingMode();
    @Property(selector = "setScalingMode:")
    public native void setScalingMode(MPMovieScalingMode v);
    @Property(selector = "readyForDisplay")
    public native boolean isReadyForDisplay();
    @Property(selector = "movieMediaTypes")
    public native MPMovieMediaTypeMask getMovieMediaTypes();
    @Property(selector = "movieSourceType")
    public native MPMovieSourceType getMovieSourceType();
    @Property(selector = "setMovieSourceType:")
    public native void setMovieSourceType(MPMovieSourceType v);
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "playableDuration")
    public native double getPlayableDuration();
    @Property(selector = "naturalSize")
    public native @ByVal CGSize getNaturalSize();
    @Property(selector = "initialPlaybackTime")
    public native double getInitialPlaybackTime();
    @Property(selector = "setInitialPlaybackTime:")
    public native void setInitialPlaybackTime(double v);
    @Property(selector = "endPlaybackTime")
    public native double getEndPlaybackTime();
    @Property(selector = "setEndPlaybackTime:")
    public native void setEndPlaybackTime(double v);
    @Property(selector = "allowsAirPlay")
    public native boolean isAllowsAirPlay();
    @Property(selector = "setAllowsAirPlay:")
    public native void setAllowsAirPlay(boolean v);
    @Property(selector = "isAirPlayVideoActive")
    public native boolean isAirPlayVideoActive();
    @Property(selector = "accessLog")
    public native MPMovieAccessLog getAccessLog();
    @Property(selector = "errorLog")
    public native MPMovieErrorLog getErrorLog();
    @Property(selector = "useApplicationAudioSession")
    public native boolean isUseApplicationAudioSession();
    @Property(selector = "setUseApplicationAudioSession:")
    public native void setUseApplicationAudioSession(boolean v);
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
    @Method(selector = "initWithContentURL:")
    protected native @Pointer long initWithContentURL$(NSURL url);
    @Method(selector = "setFullscreen:animated:")
    public native void setFullscreen(boolean fullscreen, boolean animated);
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "thumbnailImageAtTime:timeOption:")
    public native UIImage getThumbnailImage(double playbackTime, MPMovieTimeOption option);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "requestThumbnailImagesAtTimes:timeOption:")
    public native void requestThumbnailImages(NSArray<NSNumber> playbackTimes, MPMovieTimeOption option);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "cancelAllThumbnailImageRequests")
    public native void cancelAllThumbnailImageRequests();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "timedMetadata")
    public native NSArray<MPTimedMetadata> getTimedMetadata();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @Method(selector = "setBackgroundColor:")
    public native void setBackgroundColor(UIColor backgroundColor);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @Method(selector = "backgroundColor")
    public native UIColor getBackgroundColor();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @Method(selector = "setMovieControlMode:")
    public native void setMovieControlMode(MPMovieControlMode movieControlMode);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.2.
     */
    @Deprecated
    @Method(selector = "movieControlMode")
    public native MPMovieControlMode getMovieControlMode();
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
