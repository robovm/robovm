/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPlayerPtr extends Ptr<AVPlayer, AVPlayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPlayer() {}
    protected AVPlayer(SkipInit skipInit) { super(skipInit); }
    public AVPlayer(NSURL URL) { super((SkipInit) null); initObject(init(URL)); }
    public AVPlayer(AVPlayerItem item) { super((SkipInit) null); initObject(init(item)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "status")
    public native AVPlayerStatus getStatus();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "rate")
    public native float getRate();
    @Property(selector = "setRate:")
    public native void setRate(float v);
    @Property(selector = "currentItem")
    public native AVPlayerItem getCurrentItem();
    @Property(selector = "actionAtItemEnd")
    public native AVPlayerActionAtItemEnd getActionAtItemEnd();
    @Property(selector = "setActionAtItemEnd:")
    public native void setActionAtItemEnd(AVPlayerActionAtItemEnd v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "masterClock")
    public native CMClock getMasterClock();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setMasterClock:")
    public native void setMasterClock(CMClock v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "volume")
    public native float getVolume();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setVolume:")
    public native void setVolume(float v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isMuted")
    public native boolean isMuted();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setMuted:")
    public native void setMuted(boolean v);
    @Property(selector = "isClosedCaptionDisplayEnabled")
    public native boolean isClosedCaptionDisplayEnabled();
    @Property(selector = "setClosedCaptionDisplayEnabled:")
    public native void setClosedCaptionDisplayEnabled(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "appliesMediaSelectionCriteriaAutomatically")
    public native boolean appliesMediaSelectionCriteriaAutomatically();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAppliesMediaSelectionCriteriaAutomatically:")
    public native void setAppliesMediaSelectionCriteriaAutomatically(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "allowsExternalPlayback")
    public native boolean allowsExternalPlayback();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAllowsExternalPlayback:")
    public native void setAllowsExternalPlayback(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isExternalPlaybackActive")
    public native boolean isExternalPlaybackActive();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "usesExternalPlaybackWhileExternalScreenIsActive")
    public native boolean usesExternalPlaybackWhileExternalScreenIsActive();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setUsesExternalPlaybackWhileExternalScreenIsActive:")
    public native void setUsesExternalPlaybackWhileExternalScreenIsActive(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "externalPlaybackVideoGravity")
    public native String getExternalPlaybackVideoGravity();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setExternalPlaybackVideoGravity:")
    public native void setExternalPlaybackVideoGravity(String v);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "allowsAirPlayVideo")
    public native boolean allowsAirPlayVideo();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setAllowsAirPlayVideo:")
    public native void setAllowsAirPlayVideo(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "isAirPlayVideoActive")
    public native boolean isAirPlayVideoActive();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "usesAirPlayVideoWhileAirPlayScreenIsActive")
    public native boolean usesAirPlayVideoWhileAirPlayScreenIsActive();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "setUsesAirPlayVideoWhileAirPlayScreenIsActive:")
    public native void setUsesAirPlayVideoWhileAirPlayScreenIsActive(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "outputObscuredDueToInsufficientExternalProtection")
    public native boolean outputObscuredDueToInsufficientExternalProtection();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:")
    protected native @Pointer long init(NSURL URL);
    @Method(selector = "initWithPlayerItem:")
    protected native @Pointer long init(AVPlayerItem item);
    @Method(selector = "playerWithURL:")
    public static native AVPlayer create(NSURL URL);
    @Method(selector = "playerWithPlayerItem:")
    public static native AVPlayer create(AVPlayerItem item);
    @Method(selector = "play")
    public native void play();
    @Method(selector = "pause")
    public native void pause();
    @Method(selector = "replaceCurrentItemWithPlayerItem:")
    public native void replaceCurrentItem(AVPlayerItem item);
    @Method(selector = "currentTime")
    public native @ByVal CMTime getCurrentTime();
    @Method(selector = "seekToDate:")
    public native void seekToDate(NSDate date);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "seekToDate:completionHandler:")
    public native void seekToDate(NSDate date, @Block VoidBooleanBlock completionHandler);
    @Method(selector = "seekToTime:")
    public native void seekToTime(@ByVal CMTime time);
    @Method(selector = "seekToTime:toleranceBefore:toleranceAfter:")
    public native void seekToTime(@ByVal CMTime time, @ByVal CMTime toleranceBefore, @ByVal CMTime toleranceAfter);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "seekToTime:completionHandler:")
    public native void seekToTime(@ByVal CMTime time, @Block VoidBooleanBlock completionHandler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "seekToTime:toleranceBefore:toleranceAfter:completionHandler:")
    public native void seekToTime(@ByVal CMTime time, @ByVal CMTime toleranceBefore, @ByVal CMTime toleranceAfter, @Block VoidBooleanBlock completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setRate:time:atHostTime:")
    public native void setRate(float rate, @ByVal CMTime itemTime, @ByVal CMTime hostClockTime);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "prerollAtRate:completionHandler:")
    public native void prerollAtRate(float rate, @Block VoidBooleanBlock completionHandler);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "cancelPendingPrerolls")
    public native void cancelPendingPrerolls();
    @Method(selector = "addPeriodicTimeObserverForInterval:queue:usingBlock:")
    public native NSObject addPeriodicTimeObserver(@ByVal CMTime interval, DispatchQueue queue, @Block VoidBlock1<CMTime> block);
    @Method(selector = "addBoundaryTimeObserverForTimes:queue:usingBlock:")
    public native NSObject addBoundaryTimeObserver(@org.robovm.rt.bro.annotation.Marshaler(CMTime.AsValuedListMarshaler.class) List<CMTime> times, DispatchQueue queue, @Block Runnable block);
    @Method(selector = "removeTimeObserver:")
    public native void removeTimeObserver(NSObject observer);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setMediaSelectionCriteria:forMediaCharacteristic:")
    public native void setMediaSelectionCriteria(AVPlayerMediaSelectionCriteria criteria, String mediaCharacteristic);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "mediaSelectionCriteriaForMediaCharacteristic:")
    public native AVPlayerMediaSelectionCriteria getMediaSelectionCriteria(String mediaCharacteristic);
    /*</methods>*/
}
