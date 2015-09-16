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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.iad.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPictureInPictureController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPictureInPictureControllerPtr extends Ptr<AVPictureInPictureController, AVPictureInPictureControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVPictureInPictureController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPictureInPictureController() {}
    protected AVPictureInPictureController(SkipInit skipInit) { super(skipInit); }
    public AVPictureInPictureController(AVPlayerLayer playerLayer) { super((SkipInit) null); initObject(init(playerLayer)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "playerLayer")
    public native AVPlayerLayer getPlayerLayer();
    @Property(selector = "delegate")
    public native AVPictureInPictureControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(AVPictureInPictureControllerDelegate v);
    @Property(selector = "isPictureInPicturePossible")
    public native boolean isPictureInPicturePossible();
    @Property(selector = "isPictureInPictureActive")
    public native boolean isPictureInPictureActive();
    @Property(selector = "isPictureInPictureSuspended")
    public native boolean isPictureInPictureSuspended();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPlayerLayer:")
    protected native @Pointer long init(AVPlayerLayer playerLayer);
    @Method(selector = "startPictureInPicture")
    public native void startPictureInPicture();
    @Method(selector = "stopPictureInPicture")
    public native void stopPictureInPicture();
    @Method(selector = "isPictureInPictureSupported")
    public static native boolean isPictureInPictureSupported();
    @Method(selector = "pictureInPictureButtonStartImageCompatibleWithTraitCollection:")
    public static native UIImage getPictureInPictureButtonStartImage(UITraitCollection traitCollection);
    @Method(selector = "pictureInPictureButtonStopImageCompatibleWithTraitCollection:")
    public static native UIImage getPictureInPictureButtonStopImage(UITraitCollection traitCollection);
    /*</methods>*/
}
