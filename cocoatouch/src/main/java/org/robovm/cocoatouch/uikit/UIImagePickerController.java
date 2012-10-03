/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIImagePickerController /*</name>*/ 
    extends /*<extends>*/ UINavigationController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIImagePickerController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIImagePickerController() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("allowsEditing") public native @Type("BOOL") boolean isAllowsEditing();
    @Bind("setAllowsEditing:") public native void setAllowsEditing(@Type("BOOL") boolean v);
    @Bind("cameraCaptureMode") public native @Type("UIImagePickerControllerCameraCaptureMode") UIImagePickerControllerCameraCaptureMode getCameraCaptureMode();
    @Bind("setCameraCaptureMode:") public native void setCameraCaptureMode(@Type("UIImagePickerControllerCameraCaptureMode") UIImagePickerControllerCameraCaptureMode v);
    @Bind("cameraDevice") public native @Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice getCameraDevice();
    @Bind("setCameraDevice:") public native void setCameraDevice(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice v);
    @Bind("cameraFlashMode") public native @Type("UIImagePickerControllerCameraFlashMode") UIImagePickerControllerCameraFlashMode getCameraFlashMode();
    @Bind("setCameraFlashMode:") public native void setCameraFlashMode(@Type("UIImagePickerControllerCameraFlashMode") UIImagePickerControllerCameraFlashMode v);
    @Bind("cameraOverlayView") public native @Type("UIView *") UIView getCameraOverlayView();
    @Bind("setCameraOverlayView:") public native void setCameraOverlayView(@Type("UIView *") UIView v);
    @Bind("cameraViewTransform") public native @Type("CGAffineTransform") CGAffineTransform getCameraViewTransform();
    @Bind("setCameraViewTransform:") public native void setCameraViewTransform(@Type("CGAffineTransform") CGAffineTransform v);
    @Bind("delegate") public native @Type("id<UINavigationControllerDelegate, UIImagePickerControllerDelegate>") UIImagePickerControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UINavigationControllerDelegate, UIImagePickerControllerDelegate>") UIImagePickerControllerDelegate v);
    @Bind("mediaTypes") public native @Type("NSArray *") NSArray getMediaTypes();
    @Bind("setMediaTypes:") public native void setMediaTypes(@Type("NSArray *") NSArray v);
    @Bind("showsCameraControls") public native @Type("BOOL") boolean isShowsCameraControls();
    @Bind("setShowsCameraControls:") public native void setShowsCameraControls(@Type("BOOL") boolean v);
    @Bind("sourceType") public native @Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType getSourceType();
    @Bind("setSourceType:") public native void setSourceType(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType v);
    @Bind("videoMaximumDuration") public native @Type("NSTimeInterval") double getVideoMaximumDuration();
    @Bind("setVideoMaximumDuration:") public native void setVideoMaximumDuration(@Type("NSTimeInterval") double v);
    @Bind("videoQuality") public native @Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType getVideoQuality();
    @Bind("setVideoQuality:") public native void setVideoQuality(@Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType v);
    /*</properties>*/
    /*<methods>*/
    @Bind("availableCaptureModesForCameraDevice:") public native static @Type("NSArray *") NSArray getAvailableCaptureModes(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    @Bind("availableMediaTypesForSourceType:") public native static @Type("NSArray *") NSArray getAvailableMediaTypes(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType sourceType);
    @Bind("isCameraDeviceAvailable:") public native static @Type("BOOL") boolean isCameraDeviceAvailable(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    @Bind("isFlashAvailableForCameraDevice:") public native static @Type("BOOL") boolean isFlashAvailableForCameraDevice(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    @Bind("isSourceTypeAvailable:") public native static @Type("BOOL") boolean isSourceTypeAvailable(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType sourceType);
    @Bind("startVideoCapture") public native @Type("BOOL") boolean startVideoCapture();
    @Bind("stopVideoCapture") public native @Type("void") void stopVideoCapture();
    @Bind("takePicture") public native @Type("void") void takePicture();
    /*</methods>*/

}
