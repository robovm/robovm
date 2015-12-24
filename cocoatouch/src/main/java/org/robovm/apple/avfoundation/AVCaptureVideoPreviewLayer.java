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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureVideoPreviewLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureVideoPreviewLayerPtr extends Ptr<AVCaptureVideoPreviewLayer, AVCaptureVideoPreviewLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureVideoPreviewLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureVideoPreviewLayer() {}
    protected AVCaptureVideoPreviewLayer(SkipInit skipInit) { super(skipInit); }
    public AVCaptureVideoPreviewLayer(AVCaptureSession session) { super((SkipInit) null); initObject(init(session)); }
    /*</constructors>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public AVCaptureVideoPreviewLayer(AVCaptureSession session, boolean noConnection) {
        super((SkipInit) null);
        initObject(noConnection ? initWithNoConnection(session) : init(session));
    }
    /*<properties>*/
    @Property(selector = "session")
    public native AVCaptureSession getSession();
    @Property(selector = "setSession:")
    public native void setSession(AVCaptureSession v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "connection")
    public native AVCaptureConnection getConnection();
    @Property(selector = "videoGravity")
    public native AVLayerVideoGravity getVideoGravity();
    @Property(selector = "setVideoGravity:")
    public native void setVideoGravity(AVLayerVideoGravity v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSession:")
    protected native @Pointer long init(AVCaptureSession session);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "initWithSessionWithNoConnection:")
    protected native @Pointer long initWithNoConnection(AVCaptureSession session);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setSessionWithNoConnection:")
    public native void setSessionWithNoConnection(AVCaptureSession session);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "captureDevicePointOfInterestForPoint:")
    public native @ByVal CGPoint captureDevicePointOfInterest(@ByVal CGPoint pointInLayer);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "pointForCaptureDevicePointOfInterest:")
    public native @ByVal CGPoint getDevicePointOfInterest(@ByVal CGPoint captureDevicePointOfInterest);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "metadataOutputRectOfInterestForRect:")
    public native @ByVal CGRect getRectOfInterestInLayerCoordinates(@ByVal CGRect rectInLayerCoordinates);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "rectForMetadataOutputRectOfInterest:")
    public native @ByVal CGRect getRectOfInterestInMetadataOutputCoordinates(@ByVal CGRect rectInMetadataOutputCoordinates);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "transformedMetadataObjectForMetadataObject:")
    public native AVMetadataObject getTransformedMetadataObject(AVMetadataObject metadataObject);
    /*</methods>*/
}
