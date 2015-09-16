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
import org.robovm.rt.annotation.WeaklyLinked;

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPMoviePlayerController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MPMediaPlayback/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeIsPreparedToPlayDidChange(MPMoviePlayerController object, final VoidBlock1<MPMediaPlayback> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(IsPreparedToPlayDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMediaPlayback) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeDurationAvailable(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DurationAvailableNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeMediaTypesAvailable(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(MediaTypesAvailableNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeNaturalSizeAvailable(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(NaturalSizeAvailableNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeDidEnterFullscreen(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidEnterFullscreenNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeDidExitFullscreen(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidExitFullscreenNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeIsAirPlayVideoActiveDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(IsAirPlayVideoActiveDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeLoadStateDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(LoadStateDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeNowPlayingMovieDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(NowPlayingMovieDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        public static NSObject observePlaybackDidFinish(MPMoviePlayerController object, final VoidBlock3<MPMoviePlayerController, MPMovieFinishReason, NSError> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(PlaybackDidFinishNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    NSDictionary<?, ?> data = a.getUserInfo();
                    NSNumber val = (NSNumber) data.get(PlaybackDidFinishReasonUserInfoKey());
                    NSError error = (NSError) data.get("error");
                    block.invoke((MPMoviePlayerController) a.getObject(), MPMovieFinishReason.valueOf(val.intValue()), error);
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observePlaybackStateDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(PlaybackStateDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeScalingModeDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ScalingModeDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeThumbnailImageRequestDidFinish(MPMoviePlayerController object, final VoidBlock2<MPMoviePlayerController, MPMoviePlayerThumbnailRequest> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ThumbnailImageRequestDidFinishNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject(), new MPMoviePlayerThumbnailRequest(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeWillEnterFullscreen(MPMoviePlayerController object, final VoidBlock2<MPMoviePlayerController, MPMoviePlayerFullscreenAnimation> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillEnterFullscreenNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject(), new MPMoviePlayerFullscreenAnimation(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeWillExitFullscreen(MPMoviePlayerController object, final VoidBlock2<MPMoviePlayerController, MPMoviePlayerFullscreenAnimation> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillExitFullscreenNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject(), new MPMoviePlayerFullscreenAnimation(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 3.2 and later.
         */
        public static NSObject observeNewSourceTypeAvailable(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(SourceTypeAvailableNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeReadyForDisplayDidChange(MPMoviePlayerController object, final VoidBlock1<MPMoviePlayerController> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ReadyForDisplayDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke (NSNotification a) {
                    block.invoke((MPMoviePlayerController) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeTimedMetadataUpdated(MPMoviePlayerController object, final VoidBlock2<MPMoviePlayerController, NSArray<MPTimedMetadata>> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(TimedMetadataUpdatedNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @SuppressWarnings("unchecked")
                @Override
                public void invoke (NSNotification a) {
                    NSDictionary<?, ?> userInfo = a.getUserInfo();
                    NSArray<MPTimedMetadata> arr = (NSArray<MPTimedMetadata>) userInfo.get(TimedMetadataUserInfoKey());
                    block.invoke((MPMoviePlayerController) a.getObject(), arr);
                }
            });
        }
    }
    
    /*<ptr>*/public static class MPMoviePlayerControllerPtr extends Ptr<MPMoviePlayerController, MPMoviePlayerControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPMoviePlayerController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPMoviePlayerController() {}
    protected MPMoviePlayerController(SkipInit skipInit) { super(skipInit); }
    public MPMoviePlayerController(NSURL url) { super((SkipInit) null); initObject(init(url)); }
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
    public native boolean shouldAutoplay();
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
    /**
     * @since Available in iOS 6.0 and later.
     */
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
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "allowsAirPlay")
    public native boolean allowsAirPlay();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "setAllowsAirPlay:")
    public native void setAllowsAirPlay(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isAirPlayVideoActive")
    public native boolean isAirPlayVideoActive();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "timedMetadata")
    public native NSArray<MPTimedMetadata> getTimedMetadata();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "accessLog")
    public native MPMovieAccessLog getAccessLog();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "errorLog")
    public native MPMovieErrorLog getErrorLog();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "useApplicationAudioSession")
    public native boolean usesApplicationAudioSession();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setUseApplicationAudioSession:")
    public native void setUsesApplicationAudioSession(boolean v);
    @Property(selector = "isPreparedToPlay")
    public native boolean isPreparedToPlay();
    @Property(selector = "currentPlaybackTime")
    public native double getCurrentPlaybackTime();
    @Property(selector = "setCurrentPlaybackTime:")
    public native void setCurrentPlaybackTime(double v);
    @Property(selector = "currentPlaybackRate")
    public native float getCurrentPlaybackRate();
    @Property(selector = "setCurrentPlaybackRate:")
    public native void setCurrentPlaybackRate(float v);
    /*</properties>*/
    
    /* iAd extensions */
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public void playPrerollAd(@Block VoidBlock1<NSError> completionHandler) {
        org.robovm.apple.iad.MPMoviePlayerControllerExtensions.playPrerollAd(this, completionHandler);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @WeaklyLinked
    public static void preparePrerollAds() {
        org.robovm.apple.iad.MPMoviePlayerControllerExtensions.preparePrerollAds();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public void cancelPreroll() {
        org.robovm.apple.iad.MPMoviePlayerControllerExtensions.cancelPreroll(this);
    }
    
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMediaPlaybackIsPreparedToPlayDidChangeNotification", optional=true)
    public static native NSString IsPreparedToPlayDidChangeNotification();
    /*<methods>*/
    @GlobalValue(symbol="MPMoviePlayerScalingModeDidChangeNotification", optional=true)
    public static native NSString ScalingModeDidChangeNotification();
    @GlobalValue(symbol="MPMoviePlayerPlaybackDidFinishNotification", optional=true)
    public static native NSString PlaybackDidFinishNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerPlaybackDidFinishReasonUserInfoKey", optional=true)
    protected static native NSString PlaybackDidFinishReasonUserInfoKey();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerPlaybackStateDidChangeNotification", optional=true)
    public static native NSString PlaybackStateDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerLoadStateDidChangeNotification", optional=true)
    public static native NSString LoadStateDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerNowPlayingMovieDidChangeNotification", optional=true)
    public static native NSString NowPlayingMovieDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerWillEnterFullscreenNotification", optional=true)
    public static native NSString WillEnterFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerDidEnterFullscreenNotification", optional=true)
    public static native NSString DidEnterFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerWillExitFullscreenNotification", optional=true)
    public static native NSString WillExitFullscreenNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerDidExitFullscreenNotification", optional=true)
    public static native NSString DidExitFullscreenNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerIsAirPlayVideoActiveDidChangeNotification", optional=true)
    public static native NSString IsAirPlayVideoActiveDidChangeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerReadyForDisplayDidChangeNotification", optional=true)
    public static native NSString ReadyForDisplayDidChangeNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieMediaTypesAvailableNotification", optional=true)
    public static native NSString MediaTypesAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieSourceTypeAvailableNotification", optional=true)
    public static native NSString SourceTypeAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieDurationAvailableNotification", optional=true)
    public static native NSString DurationAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMovieNaturalSizeAvailableNotification", optional=true)
    public static native NSString NaturalSizeAvailableNotification();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerThumbnailImageRequestDidFinishNotification", optional=true)
    public static native NSString ThumbnailImageRequestDidFinishNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataUpdatedNotification", optional=true)
    public static native NSString TimedMetadataUpdatedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="MPMoviePlayerTimedMetadataUserInfoKey", optional=true)
    protected static native NSString TimedMetadataUserInfoKey();
    
    @Method(selector = "initWithContentURL:")
    protected native @Pointer long init(NSURL url);
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
