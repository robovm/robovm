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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureStillImageOutput/*</name>*/ 
    extends /*<extends>*/AVCaptureOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureStillImageOutputPtr extends Ptr<AVCaptureStillImageOutput, AVCaptureStillImageOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureStillImageOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureStillImageOutput() {}
    protected AVCaptureStillImageOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "outputSettings")
    public native NSDictionary<NSString, ?> getOutputSettings();
    @Property(selector = "setOutputSettings:")
    public native void setOutputSettings(NSDictionary<NSString, ?> v);
    @Property(selector = "availableImageDataCVPixelFormatTypes")
    public native NSArray<?> getAvailableImageDataCVPixelFormatTypes();
    @Property(selector = "availableImageDataCodecTypes")
    public native NSArray<?> getAvailableImageDataCodecTypes();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isStillImageStabilizationSupported")
    public native boolean isStillImageStabilizationSupported();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "automaticallyEnablesStillImageStabilizationWhenAvailable")
    public native boolean isAutomaticallyEnablesStillImageStabilizationWhenAvailable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAutomaticallyEnablesStillImageStabilizationWhenAvailable:")
    public native void setAutomaticallyEnablesStillImageStabilizationWhenAvailable(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isStillImageStabilizationActive")
    public native boolean isStillImageStabilizationActive();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isHighResolutionStillImageOutputEnabled")
    public native boolean isHighResolutionStillImageOutputEnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setHighResolutionStillImageOutputEnabled:")
    public native void setHighResolutionStillImageOutputEnabled(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isCapturingStillImage")
    public native boolean isCapturingStillImage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "maxBracketedCaptureStillImageCount")
    public native @MachineSizedUInt long getMaxBracketedCaptureStillImageCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "captureStillImageAsynchronouslyFromConnection:completionHandler:")
    public native void captureStillImageAsynchronously(AVCaptureConnection connection, @Block VoidBlock2<CMSampleBuffer, NSError> handler);
    @Method(selector = "jpegStillImageNSDataRepresentation:")
    public static native NSData getJPEGStillImageNSDataRepresentation(CMSampleBuffer jpegSampleBuffer);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "prepareToCaptureStillImageBracketFromConnection:withSettingsArray:completionHandler:")
    public native void prepareToCaptureStillImageBracketFromConnection$withSettingsArray$completionHandler$(AVCaptureConnection connection, NSArray<?> settings, ObjCBlock handler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "captureStillImageBracketAsynchronouslyFromConnection:withSettingsArray:completionHandler:")
    public native void captureStillImageBracketAsynchronouslyFromConnection$withSettingsArray$completionHandler$(AVCaptureConnection connection, NSArray<?> settings, ObjCBlock handler);
    /*</methods>*/
}
