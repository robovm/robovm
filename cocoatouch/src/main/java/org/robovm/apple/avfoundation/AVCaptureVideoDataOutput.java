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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureVideoDataOutput/*</name>*/ 
    extends /*<extends>*/AVCaptureOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureVideoDataOutputPtr extends Ptr<AVCaptureVideoDataOutput, AVCaptureVideoDataOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureVideoDataOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureVideoDataOutput() {}
    protected AVCaptureVideoDataOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public AVVideoSettings getVideoSettings() {
        return new AVVideoSettings(getVideoSettings0());
    }
    public void setVideoSettings(AVVideoSettings videoSettings) {
        setVideoSettings0(videoSettings.getDictionary());
    }
    public AVPixelBufferAttributes getPixelBufferSettings() {
        return new AVPixelBufferAttributes(getVideoSettings0().as(CFDictionary.class));
    }
    @SuppressWarnings("unchecked")
    public void setPixelBufferSettings(AVPixelBufferAttributes videoSettings) {
        setVideoSettings0(videoSettings.getDictionary().as(NSDictionary.class));
    }
    /*<properties>*/
    @Property(selector = "sampleBufferDelegate")
    public native AVCaptureVideoDataOutputSampleBufferDelegate getSampleBufferDelegate();
    @Property(selector = "sampleBufferCallbackQueue")
    public native DispatchQueue getSampleBufferCallbackQueue();
    @Property(selector = "videoSettings")
    protected native NSDictionary<NSString, NSObject> getVideoSettings0();
    @Property(selector = "setVideoSettings:")
    protected native void setVideoSettings0(NSDictionary<NSString, NSObject> v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "availableVideoCVPixelFormatTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(CVPixelFormatType.AsListMarshaler.class) List<CVPixelFormatType> getAvailableVideoCVPixelFormatTypes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "availableVideoCodecTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(CMVideoCodecType.AsListMarshaler.class) List<CMVideoCodecType> getAvailableVideoCodecTypes();
    @Property(selector = "alwaysDiscardsLateVideoFrames")
    public native boolean alwaysDiscardsLateVideoFrames();
    @Property(selector = "setAlwaysDiscardsLateVideoFrames:")
    public native void setAlwaysDiscardsLateVideoFrames(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoSettings getRecommendedVideoSettings(String outputFileType) {
        return new AVVideoSettings(getRecommendedVideoSettings0(outputFileType));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVPixelBufferAttributes getRecommendedPixelBufferSettings(String outputFileType) {
        return new AVPixelBufferAttributes(getRecommendedVideoSettings0(outputFileType).as(CFDictionary.class));
    }
    /*<methods>*/
    @Method(selector = "setSampleBufferDelegate:queue:")
    public native void setSampleBufferDelegate(AVCaptureVideoDataOutputSampleBufferDelegate sampleBufferDelegate, DispatchQueue sampleBufferCallbackQueue);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "recommendedVideoSettingsForAssetWriterWithOutputFileType:")
    protected native NSDictionary<NSString, NSObject> getRecommendedVideoSettings0(String outputFileType);
    /*</methods>*/
}
