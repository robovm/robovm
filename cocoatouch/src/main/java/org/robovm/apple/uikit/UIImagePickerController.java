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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIImagePickerController/*</name>*/ 
    extends /*<extends>*/UINavigationController/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIImagePickerControllerPtr extends Ptr<UIImagePickerController, UIImagePickerControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIImagePickerController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIImagePickerController() {}
    protected UIImagePickerController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIImagePickerControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIImagePickerControllerDelegate v);
    @Property(selector = "sourceType")
    public native UIImagePickerControllerSourceType getSourceType();
    @Property(selector = "setSourceType:")
    public native void setSourceType(UIImagePickerControllerSourceType v);
    @Property(selector = "mediaTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getMediaTypes();
    @Property(selector = "setMediaTypes:")
    public native void setMediaTypes(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "allowsEditing")
    public native boolean allowsEditing();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setAllowsEditing:")
    public native void setAllowsEditing(boolean v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "videoMaximumDuration")
    public native double getVideoMaximumDuration();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setVideoMaximumDuration:")
    public native void setVideoMaximumDuration(double v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "videoQuality")
    public native UIImagePickerControllerQualityType getVideoQuality();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setVideoQuality:")
    public native void setVideoQuality(UIImagePickerControllerQualityType v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "showsCameraControls")
    public native boolean showsCameraControls();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setShowsCameraControls:")
    public native void setShowsCameraControls(boolean v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "cameraOverlayView")
    public native UIView getCameraOverlayView();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setCameraOverlayView:")
    public native void setCameraOverlayView(UIView v);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "cameraViewTransform")
    public native @ByVal CGAffineTransform getCameraViewTransform();
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Property(selector = "setCameraViewTransform:")
    public native void setCameraViewTransform(@ByVal CGAffineTransform v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "cameraCaptureMode")
    public native UIImagePickerControllerCameraCaptureMode getCameraCaptureMode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setCameraCaptureMode:")
    public native void setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "cameraDevice")
    public native UIImagePickerControllerCameraDevice getCameraDevice();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setCameraDevice:")
    public native void setCameraDevice(UIImagePickerControllerCameraDevice v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "cameraFlashMode")
    public native UIImagePickerControllerCameraFlashMode getCameraFlashMode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setCameraFlashMode:")
    public native void setCameraFlashMode(UIImagePickerControllerCameraFlashMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Method(selector = "takePicture")
    public native void takePicture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "startVideoCapture")
    public native boolean startVideoCapture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "stopVideoCapture")
    public native void stopVideoCapture();
    @Method(selector = "isSourceTypeAvailable:")
    public static native boolean isSourceTypeAvailable(UIImagePickerControllerSourceType sourceType);
    @Method(selector = "availableMediaTypesForSourceType:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAvailableMediaTypes(UIImagePickerControllerSourceType sourceType);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "isCameraDeviceAvailable:")
    public static native boolean isCameraDeviceAvailable(UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "isFlashAvailableForCameraDevice:")
    public static native boolean isFlashAvailableForCameraDevice(UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "availableCaptureModesForCameraDevice:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(UIImagePickerControllerCameraCaptureMode.AsListMarshaler.class) List<UIImagePickerControllerCameraCaptureMode> getAvailableCaptureModes(UIImagePickerControllerCameraDevice cameraDevice);
    /*</methods>*/
}
