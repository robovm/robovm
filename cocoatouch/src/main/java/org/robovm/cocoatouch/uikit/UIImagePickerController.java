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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIImagePickerController /*</name>*/.class);

    /*<constructors>*/
    protected UIImagePickerController(SkipInit skipInit) { super(skipInit); }
    public UIImagePickerController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("allowsEditing") public native boolean isAllowsEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setAllowsEditing:") public native void setAllowsEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraCaptureMode") public native UIImagePickerControllerCameraCaptureMode getCameraCaptureMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraCaptureMode:") public native void setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraDevice") public native UIImagePickerControllerCameraDevice getCameraDevice();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraDevice:") public native void setCameraDevice(UIImagePickerControllerCameraDevice v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("cameraFlashMode") public native UIImagePickerControllerCameraFlashMode getCameraFlashMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("setCameraFlashMode:") public native void setCameraFlashMode(UIImagePickerControllerCameraFlashMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("cameraOverlayView") public native UIView getCameraOverlayView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setCameraOverlayView:") public native void setCameraOverlayView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("cameraViewTransform") public native CGAffineTransform getCameraViewTransform();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setCameraViewTransform:") public native void setCameraViewTransform(CGAffineTransform v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIImagePickerControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIImagePickerControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("mediaTypes") public native NSArray getMediaTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setMediaTypes:") public native void setMediaTypes(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("showsCameraControls") public native boolean isShowsCameraControls();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setShowsCameraControls:") public native void setShowsCameraControls(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("sourceType") public native UIImagePickerControllerSourceType getSourceType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSourceType:") public native void setSourceType(UIImagePickerControllerSourceType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoMaximumDuration") public native double getVideoMaximumDuration();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoMaximumDuration:") public native void setVideoMaximumDuration(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("videoQuality") public native UIImagePickerControllerQualityType getVideoQuality();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    @Bind("setVideoQuality:") public native void setVideoQuality(UIImagePickerControllerQualityType v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector availableCaptureModesForCameraDevice$ = Selector.register("availableCaptureModesForCameraDevice:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getAvailableCaptureModes(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableCaptureModesForCameraDevice:">+ (NSArray *)availableCaptureModesForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static NSArray getAvailableCaptureModes(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_getAvailableCaptureModes(objCClass, availableCaptureModesForCameraDevice$, cameraDevice);
    }
    
    private static final Selector availableMediaTypesForSourceType$ = Selector.register("availableMediaTypesForSourceType:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getAvailableMediaTypes(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableMediaTypesForSourceType:">+ (NSArray *)availableMediaTypesForSourceType:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 3.0 and later.
     */
    public static NSArray getAvailableMediaTypes(UIImagePickerControllerSourceType sourceType) {
        return objc_getAvailableMediaTypes(objCClass, availableMediaTypesForSourceType$, sourceType);
    }
    
    private static final Selector isCameraDeviceAvailable$ = Selector.register("isCameraDeviceAvailable:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isCameraDeviceAvailable(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isCameraDeviceAvailable:">+ (BOOL)isCameraDeviceAvailable:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static boolean isCameraDeviceAvailable(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_isCameraDeviceAvailable(objCClass, isCameraDeviceAvailable$, cameraDevice);
    }
    
    private static final Selector isFlashAvailableForCameraDevice$ = Selector.register("isFlashAvailableForCameraDevice:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isFlashAvailableForCameraDevice(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isFlashAvailableForCameraDevice:">+ (BOOL)isFlashAvailableForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static boolean isFlashAvailableForCameraDevice(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_isFlashAvailableForCameraDevice(objCClass, isFlashAvailableForCameraDevice$, cameraDevice);
    }
    
    private static final Selector isSourceTypeAvailable$ = Selector.register("isSourceTypeAvailable:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isSourceTypeAvailable(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isSourceTypeAvailable:">+ (BOOL)isSourceTypeAvailable:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    public static boolean isSourceTypeAvailable(UIImagePickerControllerSourceType sourceType) {
        return objc_isSourceTypeAvailable(objCClass, isSourceTypeAvailable$, sourceType);
    }
    
    private static final Selector startVideoCapture = Selector.register("startVideoCapture");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_startVideoCapture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_startVideoCaptureSuper(ObjCSuper __super__, UIImagePickerController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/startVideoCapture">- (BOOL)startVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean startVideoCapture() {
        if (customClass) { return objc_startVideoCaptureSuper(getSuper(), this, startVideoCapture); } else { return objc_startVideoCapture(this, startVideoCapture); }
    }
    
    private static final Selector stopVideoCapture = Selector.register("stopVideoCapture");
    @Bridge(symbol = "objc_msgSend") private native static void objc_stopVideoCapture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_stopVideoCaptureSuper(ObjCSuper __super__, UIImagePickerController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/stopVideoCapture">- (void)stopVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    public void stopVideoCapture() {
        if (customClass) { objc_stopVideoCaptureSuper(getSuper(), this, stopVideoCapture); } else { objc_stopVideoCapture(this, stopVideoCapture); }
    }
    
    private static final Selector takePicture = Selector.register("takePicture");
    @Bridge(symbol = "objc_msgSend") private native static void objc_takePicture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_takePictureSuper(ObjCSuper __super__, UIImagePickerController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/takePicture">- (void)takePicture</a>
     * @since Available in iOS 3.1 and later.
     */
    public void takePicture() {
        if (customClass) { objc_takePictureSuper(getSuper(), this, takePicture); } else { objc_takePicture(this, takePicture); }
    }
    /*</methods>*/

}
