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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    public native NSArray<?> getMediaTypes();
    @Property(selector = "setMediaTypes:")
    public native void setMediaTypes(NSArray<?> v);
    @Property(selector = "allowsEditing")
    public native boolean isAllowsEditing();
    @Property(selector = "setAllowsEditing:")
    public native void setAllowsEditing(boolean v);
    @Property(selector = "allowsImageEditing")
    public native boolean isAllowsImageEditing();
    @Property(selector = "setAllowsImageEditing:")
    public native void setAllowsImageEditing(boolean v);
    @Property(selector = "videoMaximumDuration")
    public native double getVideoMaximumDuration();
    @Property(selector = "setVideoMaximumDuration:")
    public native void setVideoMaximumDuration(double v);
    @Property(selector = "videoQuality")
    public native UIImagePickerControllerQualityType getVideoQuality();
    @Property(selector = "setVideoQuality:")
    public native void setVideoQuality(UIImagePickerControllerQualityType v);
    @Property(selector = "showsCameraControls")
    public native boolean isShowsCameraControls();
    @Property(selector = "setShowsCameraControls:")
    public native void setShowsCameraControls(boolean v);
    @Property(selector = "cameraOverlayView")
    public native UIView getCameraOverlayView();
    @Property(selector = "setCameraOverlayView:")
    public native void setCameraOverlayView(UIView v);
    @Property(selector = "cameraViewTransform")
    public native @ByVal CGAffineTransform getCameraViewTransform();
    @Property(selector = "setCameraViewTransform:")
    public native void setCameraViewTransform(@ByVal CGAffineTransform v);
    @Property(selector = "cameraCaptureMode")
    public native UIImagePickerControllerCameraCaptureMode getCameraCaptureMode();
    @Property(selector = "setCameraCaptureMode:")
    public native void setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode v);
    @Property(selector = "cameraDevice")
    public native UIImagePickerControllerCameraDevice getCameraDevice();
    @Property(selector = "setCameraDevice:")
    public native void setCameraDevice(UIImagePickerControllerCameraDevice v);
    @Property(selector = "cameraFlashMode")
    public native UIImagePickerControllerCameraFlashMode getCameraFlashMode();
    @Property(selector = "setCameraFlashMode:")
    public native void setCameraFlashMode(UIImagePickerControllerCameraFlashMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "takePicture")
    public native void takePicture();
    @Method(selector = "startVideoCapture")
    public native boolean startVideoCapture();
    @Method(selector = "stopVideoCapture")
    public native void stopVideoCapture();
    @Method(selector = "isSourceTypeAvailable:")
    public static native boolean isSourceTypeAvailable$(UIImagePickerControllerSourceType sourceType);
    @Method(selector = "availableMediaTypesForSourceType:")
    public static native NSArray<?> getAvailableMediaTypes(UIImagePickerControllerSourceType sourceType);
    @Method(selector = "isCameraDeviceAvailable:")
    public static native boolean isCameraDeviceAvailable$(UIImagePickerControllerCameraDevice cameraDevice);
    @Method(selector = "isFlashAvailableForCameraDevice:")
    public static native boolean isFlashAvailableForCameraDevice$(UIImagePickerControllerCameraDevice cameraDevice);
    @Method(selector = "availableCaptureModesForCameraDevice:")
    public static native NSArray<?> getAvailableCaptureModes(UIImagePickerControllerCameraDevice cameraDevice);
    /*</methods>*/
}
