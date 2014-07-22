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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureConnection/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureConnectionPtr extends Ptr<AVCaptureConnection, AVCaptureConnectionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureConnection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureConnection() {}
    protected AVCaptureConnection(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "inputPorts")
    public native NSArray<AVCaptureInputPort> getInputPorts();
    @Property(selector = "output")
    public native AVCaptureOutput getOutput();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "videoPreviewLayer")
    public native AVCaptureVideoPreviewLayer getVideoPreviewLayer();
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "isActive")
    public native boolean isActive();
    @Property(selector = "audioChannels")
    public native NSArray<AVCaptureAudioChannel> getAudioChannels();
    @Property(selector = "isVideoMirroringSupported")
    public native boolean isSupportsVideoMirroring();
    @Property(selector = "isVideoMirrored")
    public native boolean isVideoMirrored();
    @Property(selector = "setVideoMirrored:")
    public native void setVideoMirrored(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "automaticallyAdjustsVideoMirroring")
    public native boolean isAutomaticallyAdjustsVideoMirroring();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAutomaticallyAdjustsVideoMirroring:")
    public native void setAutomaticallyAdjustsVideoMirroring(boolean v);
    @Property(selector = "isVideoOrientationSupported")
    public native boolean isSupportsVideoOrientation();
    @Property(selector = "videoOrientation")
    public native AVCaptureVideoOrientation getVideoOrientation();
    @Property(selector = "setVideoOrientation:")
    public native void setVideoOrientation(AVCaptureVideoOrientation v);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "isVideoMinFrameDurationSupported")
    public native boolean isSupportsVideoMinFrameDuration();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "videoMinFrameDuration")
    public native @ByVal CMTime getVideoMinFrameDuration();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setVideoMinFrameDuration:")
    public native void setVideoMinFrameDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "isVideoMaxFrameDurationSupported")
    public native boolean isSupportsVideoMaxFrameDuration();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "videoMaxFrameDuration")
    public native @ByVal CMTime getVideoMaxFrameDuration();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setVideoMaxFrameDuration:")
    public native void setVideoMaxFrameDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "videoMaxScaleAndCropFactor")
    public native @MachineSizedFloat double getVideoMaxScaleAndCropFactor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "videoScaleAndCropFactor")
    public native @MachineSizedFloat double getVideoScaleAndCropFactor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setVideoScaleAndCropFactor:")
    public native void setVideoScaleAndCropFactor(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isVideoStabilizationSupported")
    public native boolean isSupportsVideoStabilization();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isVideoStabilizationEnabled")
    public native boolean isVideoStabilizationEnabled();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "enablesVideoStabilizationWhenAvailable")
    public native boolean isEnablesVideoStabilizationWhenAvailable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setEnablesVideoStabilizationWhenAvailable:")
    public native void setEnablesVideoStabilizationWhenAvailable(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
