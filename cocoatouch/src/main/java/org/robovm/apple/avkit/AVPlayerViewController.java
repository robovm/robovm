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
package org.robovm.apple.avkit;

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
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.iad.*;
/*</imports>*/
import org.robovm.rt.annotation.WeaklyLinked;

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPlayerViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPlayerViewControllerPtr extends Ptr<AVPlayerViewController, AVPlayerViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPlayerViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPlayerViewController() {}
    protected AVPlayerViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "player")
    public native AVPlayer getPlayer();
    @Property(selector = "setPlayer:")
    public native void setPlayer(AVPlayer v);
    @Property(selector = "showsPlaybackControls")
    public native boolean showsPlaybackControls();
    @Property(selector = "setShowsPlaybackControls:")
    public native void setShowsPlaybackControls(boolean v);
    @Property(selector = "videoGravity")
    public native AVLayerVideoGravity getVideoGravity();
    @Property(selector = "setVideoGravity:")
    public native void setVideoGravity(AVLayerVideoGravity v);
    @Property(selector = "isReadyForDisplay")
    public native boolean isReadyForDisplay();
    @Property(selector = "videoBounds")
    public native @ByVal CGRect getVideoBounds();
    @Property(selector = "contentOverlayView")
    public native UIView getContentOverlayView();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public void playPrerollAd(@Block VoidBlock1<NSError> completionHandler) {
        AVPlayerViewControllerExtensions.playPrerollAd(this, completionHandler);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public void cancelPreroll() {
        AVPlayerViewControllerExtensions.cancelPreroll(this);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    public static void preparePrerollAds() {
        AVPlayerViewControllerExtensions.preparePrerollAds();
    }
    /*<methods>*/
    
    /*</methods>*/
}
