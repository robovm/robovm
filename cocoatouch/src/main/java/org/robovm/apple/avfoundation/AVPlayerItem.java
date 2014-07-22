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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayerItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPlayerItemPtr extends Ptr<AVPlayerItem, AVPlayerItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPlayerItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPlayerItem() {}
    protected AVPlayerItem(SkipInit skipInit) { super(skipInit); }
    public AVPlayerItem(NSURL URL) { super((SkipInit) null); initObject(init(URL)); }
    public AVPlayerItem(AVAsset asset) { super((SkipInit) null); initObject(init(asset)); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVPlayerItem(AVAsset asset, NSArray<NSString> automaticallyLoadedAssetKeys) { super((SkipInit) null); initObject(init(asset, automaticallyLoadedAssetKeys)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "status")
    public native AVPlayerItemStatus getStatus();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "asset")
    public native AVAsset getAsset();
    @Property(selector = "tracks")
    public native NSArray<AVPlayerItemTrack> getTracks();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "duration")
    public native @ByVal CMTime getDuration();
    @Property(selector = "presentationSize")
    public native @ByVal CGSize getPresentationSize();
    @Property(selector = "timedMetadata")
    public native NSArray<AVMetadataItem> getTimedMetadata();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "automaticallyLoadedAssetKeys")
    public native NSArray<NSString> getAutomaticallyLoadedAssetKeys();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "canPlayFastForward")
    public native boolean isCanPlayFastForward();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "canPlaySlowForward")
    public native boolean isCanPlaySlowForward();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "canPlayReverse")
    public native boolean isCanPlayReverse();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "canPlaySlowReverse")
    public native boolean isCanPlaySlowReverse();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "canPlayFastReverse")
    public native boolean isCanPlayFastReverse();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "canStepForward")
    public native boolean isCanStepForward();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "canStepBackward")
    public native boolean isCanStepBackward();
    @Property(selector = "forwardPlaybackEndTime")
    public native @ByVal CMTime getForwardPlaybackEndTime();
    @Property(selector = "setForwardPlaybackEndTime:")
    public native void setForwardPlaybackEndTime(@ByVal CMTime v);
    @Property(selector = "reversePlaybackEndTime")
    public native @ByVal CMTime getReversePlaybackEndTime();
    @Property(selector = "setReversePlaybackEndTime:")
    public native void setReversePlaybackEndTime(@ByVal CMTime v);
    @Property(selector = "seekableTimeRanges")
    public native NSArray<NSValue> getSeekableTimeRanges();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "timebase")
    public native CMTimebase getTimebase();
    @Property(selector = "videoComposition")
    public native AVVideoComposition getVideoComposition();
    @Property(selector = "setVideoComposition:")
    public native void setVideoComposition(AVVideoComposition v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "customVideoCompositor")
    public native AVVideoCompositing getCustomVideoCompositor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "seekingWaitsForVideoCompositionRendering")
    public native boolean isSeekingWaitsForVideoCompositionRendering();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setSeekingWaitsForVideoCompositionRendering:")
    public native void setSeekingWaitsForVideoCompositionRendering(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "textStyleRules")
    public native NSArray<AVTextStyleRule> getTextStyleRules();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setTextStyleRules:")
    public native void setTextStyleRules(NSArray<AVTextStyleRule> v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "audioTimePitchAlgorithm")
    public native String getAudioTimePitchAlgorithm();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAudioTimePitchAlgorithm:")
    public native void setAudioTimePitchAlgorithm(String v);
    @Property(selector = "audioMix")
    public native AVAudioMix getAudioMix();
    @Property(selector = "setAudioMix:")
    public native void setAudioMix(AVAudioMix v);
    @Property(selector = "loadedTimeRanges")
    public native NSArray<NSValue> getLoadedTimeRanges();
    @Property(selector = "isPlaybackLikelyToKeepUp")
    public native boolean isPlaybackLikelyToKeepUp();
    @Property(selector = "isPlaybackBufferFull")
    public native boolean isPlaybackBufferFull();
    @Property(selector = "isPlaybackBufferEmpty")
    public native boolean isPlaybackBufferEmpty();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputs")
    public native NSArray<AVPlayerItemOutput> getOutputs();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:")
    protected native @Pointer long init(NSURL URL);
    @Method(selector = "initWithAsset:")
    protected native @Pointer long init(AVAsset asset);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithAsset:automaticallyLoadedAssetKeys:")
    protected native @Pointer long init(AVAsset asset, NSArray<NSString> automaticallyLoadedAssetKeys);
    @Method(selector = "playerItemWithURL:")
    public static native AVPlayerItem create(NSURL URL);
    @Method(selector = "playerItemWithAsset:")
    public static native AVPlayerItem create(AVAsset asset);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "playerItemWithAsset:automaticallyLoadedAssetKeys:")
    public static native AVPlayerItem create(AVAsset asset, NSArray<NSString> automaticallyLoadedAssetKeys);
    @Method(selector = "currentTime")
    public native @ByVal CMTime getCurrentTime();
    @Method(selector = "seekToTime:")
    public native void seekToTime(@ByVal CMTime time);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "seekToTime:completionHandler:")
    public native void seekToTime(@ByVal CMTime time, @Block VoidBooleanBlock completionHandler);
    @Method(selector = "seekToTime:toleranceBefore:toleranceAfter:")
    public native void seekToTime(@ByVal CMTime time, @ByVal CMTime toleranceBefore, @ByVal CMTime toleranceAfter);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "seekToTime:toleranceBefore:toleranceAfter:completionHandler:")
    public native void seekToTime(@ByVal CMTime time, @ByVal CMTime toleranceBefore, @ByVal CMTime toleranceAfter, @Block VoidBooleanBlock completionHandler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "cancelPendingSeeks")
    public native void cancelPendingSeeks();
    @Method(selector = "currentDate")
    public native NSDate getCurrentDate();
    @Method(selector = "seekToDate:")
    public native boolean seekToDate(NSDate date);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "seekToDate:completionHandler:")
    public native boolean seekToDate(NSDate date, @Block VoidBooleanBlock completionHandler);
    @Method(selector = "stepByCount:")
    public native void stepByCount(@MachineSizedSInt long stepCount);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "selectMediaOption:inMediaSelectionGroup:")
    public native void selectMediaOption(AVMediaSelectionOption mediaSelectionOption, AVMediaSelectionGroup mediaSelectionGroup);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "selectMediaOptionAutomaticallyInMediaSelectionGroup:")
    public native void selectMediaOptionAutomatically(AVMediaSelectionGroup mediaSelectionGroup);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "selectedMediaOptionInMediaSelectionGroup:")
    public native AVMediaSelectionOption getSelectedMediaOption(AVMediaSelectionGroup mediaSelectionGroup);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Method(selector = "accessLog")
    public native AVPlayerItemAccessLog getAccessLog();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Method(selector = "errorLog")
    public native AVPlayerItemErrorLog getErrorLog();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "addOutput:")
    public native void addOutput(AVPlayerItemOutput output);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "removeOutput:")
    public native void removeOutput(AVPlayerItemOutput output);
    /*</methods>*/
}
