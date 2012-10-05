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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html">UIImagePickerController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("allowsEditing") public native @Type("BOOL") boolean isAllowsEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setAllowsEditing:") public native void setAllowsEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraCaptureMode") public native @Type("UIImagePickerControllerCameraCaptureMode") UIImagePickerControllerCameraCaptureMode getCameraCaptureMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraCaptureMode:") public native void setCameraCaptureMode(@Type("UIImagePickerControllerCameraCaptureMode") UIImagePickerControllerCameraCaptureMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraDevice") public native @Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice getCameraDevice();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraDevice:") public native void setCameraDevice(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraFlashMode") public native @Type("UIImagePickerControllerCameraFlashMode") UIImagePickerControllerCameraFlashMode getCameraFlashMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraFlashMode:") public native void setCameraFlashMode(@Type("UIImagePickerControllerCameraFlashMode") UIImagePickerControllerCameraFlashMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("cameraOverlayView") public native @Type("UIView *") UIView getCameraOverlayView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setCameraOverlayView:") public native void setCameraOverlayView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("cameraViewTransform") public native @Type("CGAffineTransform") CGAffineTransform getCameraViewTransform();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setCameraViewTransform:") public native void setCameraViewTransform(@Type("CGAffineTransform") CGAffineTransform v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UINavigationControllerDelegate, UIImagePickerControllerDelegate>") UIImagePickerControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UINavigationControllerDelegate, UIImagePickerControllerDelegate>") UIImagePickerControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("mediaTypes") public native @Type("NSArray *") NSArray getMediaTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setMediaTypes:") public native void setMediaTypes(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("showsCameraControls") public native @Type("BOOL") boolean isShowsCameraControls();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setShowsCameraControls:") public native void setShowsCameraControls(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sourceType") public native @Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType getSourceType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSourceType:") public native void setSourceType(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoMaximumDuration") public native @Type("NSTimeInterval") double getVideoMaximumDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoMaximumDuration:") public native void setVideoMaximumDuration(@Type("NSTimeInterval") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoQuality") public native @Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType getVideoQuality();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoQuality:") public native void setVideoQuality(@Type("UIImagePickerControllerQualityType") UIImagePickerControllerQualityType v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableCaptureModesForCameraDevice:">+ (NSArray *)availableCaptureModesForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("availableCaptureModesForCameraDevice:") public native static @Type("NSArray *") NSArray getAvailableCaptureModes(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableMediaTypesForSourceType:">+ (NSArray *)availableMediaTypesForSourceType:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("availableMediaTypesForSourceType:") public native static @Type("NSArray *") NSArray getAvailableMediaTypes(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isCameraDeviceAvailable:">+ (BOOL)isCameraDeviceAvailable:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("isCameraDeviceAvailable:") public native static @Type("BOOL") boolean isCameraDeviceAvailable(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isFlashAvailableForCameraDevice:">+ (BOOL)isFlashAvailableForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("isFlashAvailableForCameraDevice:") public native static @Type("BOOL") boolean isFlashAvailableForCameraDevice(@Type("UIImagePickerControllerCameraDevice") UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isSourceTypeAvailable:">+ (BOOL)isSourceTypeAvailable:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSourceTypeAvailable:") public native static @Type("BOOL") boolean isSourceTypeAvailable(@Type("UIImagePickerControllerSourceType") UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/startVideoCapture">- (BOOL)startVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("startVideoCapture") public native @Type("BOOL") boolean startVideoCapture();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/stopVideoCapture">- (void)stopVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("stopVideoCapture") public native @Type("void") void stopVideoCapture();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/takePicture">- (void)takePicture</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("takePicture") public native @Type("void") void takePicture();
    /*</methods>*/

}
