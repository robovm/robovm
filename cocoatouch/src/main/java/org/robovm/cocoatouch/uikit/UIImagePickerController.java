/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html">UIImagePickerController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIImagePickerController /*</name>*/ 
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
    
    private static final Selector allowsEditing = Selector.register("allowsEditing");
    @Bridge private native static boolean objc_isAllowsEditing(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    public boolean isAllowsEditing() {
        if (customClass) { return objc_isAllowsEditingSuper(getSuper(), allowsEditing); } else { return objc_isAllowsEditing(this, allowsEditing); }
    }
    
    private static final Selector setAllowsEditing$ = Selector.register("setAllowsEditing:");
    @Bridge private native static void objc_setAllowsEditing(UIImagePickerController __self__, Selector __cmd__, boolean allowsEditing);
    @Bridge private native static void objc_setAllowsEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsEditing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/allowsEditing">@property (nonatomic) BOOL allowsEditing</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setAllowsEditing(boolean allowsEditing) {
        if (customClass) { objc_setAllowsEditingSuper(getSuper(), setAllowsEditing$, allowsEditing); } else { objc_setAllowsEditing(this, setAllowsEditing$, allowsEditing); }
    }
    
    private static final Selector cameraCaptureMode = Selector.register("cameraCaptureMode");
    @Bridge private native static UIImagePickerControllerCameraCaptureMode objc_getCameraCaptureMode(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerCameraCaptureMode objc_getCameraCaptureModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    public UIImagePickerControllerCameraCaptureMode getCameraCaptureMode() {
        if (customClass) { return objc_getCameraCaptureModeSuper(getSuper(), cameraCaptureMode); } else { return objc_getCameraCaptureMode(this, cameraCaptureMode); }
    }
    
    private static final Selector setCameraCaptureMode$ = Selector.register("setCameraCaptureMode:");
    @Bridge private native static void objc_setCameraCaptureMode(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraCaptureMode cameraCaptureMode);
    @Bridge private native static void objc_setCameraCaptureModeSuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerCameraCaptureMode cameraCaptureMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraCaptureMode">@property (nonatomic) UIImagePickerControllerCameraCaptureMode cameraCaptureMode</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setCameraCaptureMode(UIImagePickerControllerCameraCaptureMode cameraCaptureMode) {
        if (customClass) { objc_setCameraCaptureModeSuper(getSuper(), setCameraCaptureMode$, cameraCaptureMode); } else { objc_setCameraCaptureMode(this, setCameraCaptureMode$, cameraCaptureMode); }
    }
    
    private static final Selector cameraDevice = Selector.register("cameraDevice");
    @Bridge private native static UIImagePickerControllerCameraDevice objc_getCameraDevice(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerCameraDevice objc_getCameraDeviceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public UIImagePickerControllerCameraDevice getCameraDevice() {
        if (customClass) { return objc_getCameraDeviceSuper(getSuper(), cameraDevice); } else { return objc_getCameraDevice(this, cameraDevice); }
    }
    
    private static final Selector setCameraDevice$ = Selector.register("setCameraDevice:");
    @Bridge private native static void objc_setCameraDevice(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    @Bridge private native static void objc_setCameraDeviceSuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraDevice">@property (nonatomic) UIImagePickerControllerCameraDevice cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setCameraDevice(UIImagePickerControllerCameraDevice cameraDevice) {
        if (customClass) { objc_setCameraDeviceSuper(getSuper(), setCameraDevice$, cameraDevice); } else { objc_setCameraDevice(this, setCameraDevice$, cameraDevice); }
    }
    
    private static final Selector cameraFlashMode = Selector.register("cameraFlashMode");
    @Bridge private native static UIImagePickerControllerCameraFlashMode objc_getCameraFlashMode(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerCameraFlashMode objc_getCameraFlashModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    public UIImagePickerControllerCameraFlashMode getCameraFlashMode() {
        if (customClass) { return objc_getCameraFlashModeSuper(getSuper(), cameraFlashMode); } else { return objc_getCameraFlashMode(this, cameraFlashMode); }
    }
    
    private static final Selector setCameraFlashMode$ = Selector.register("setCameraFlashMode:");
    @Bridge private native static void objc_setCameraFlashMode(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraFlashMode cameraFlashMode);
    @Bridge private native static void objc_setCameraFlashModeSuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerCameraFlashMode cameraFlashMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraFlashMode">@property (nonatomic) UIImagePickerControllerCameraFlashMode cameraFlashMode</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setCameraFlashMode(UIImagePickerControllerCameraFlashMode cameraFlashMode) {
        if (customClass) { objc_setCameraFlashModeSuper(getSuper(), setCameraFlashMode$, cameraFlashMode); } else { objc_setCameraFlashMode(this, setCameraFlashMode$, cameraFlashMode); }
    }
    
    private static final Selector cameraOverlayView = Selector.register("cameraOverlayView");
    @Bridge private native static UIView objc_getCameraOverlayView(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getCameraOverlayViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIView getCameraOverlayView() {
        if (customClass) { return objc_getCameraOverlayViewSuper(getSuper(), cameraOverlayView); } else { return objc_getCameraOverlayView(this, cameraOverlayView); }
    }
    
    private static final Selector setCameraOverlayView$ = Selector.register("setCameraOverlayView:");
    @Bridge private native static void objc_setCameraOverlayView(UIImagePickerController __self__, Selector __cmd__, UIView cameraOverlayView);
    @Bridge private native static void objc_setCameraOverlayViewSuper(ObjCSuper __super__, Selector __cmd__, UIView cameraOverlayView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraOverlayView">@property (nonatomic, retain) UIView *cameraOverlayView</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setCameraOverlayView(UIView cameraOverlayView) {
        if (customClass) { objc_setCameraOverlayViewSuper(getSuper(), setCameraOverlayView$, cameraOverlayView); } else { objc_setCameraOverlayView(this, setCameraOverlayView$, cameraOverlayView); }
    }
    
    private static final Selector cameraViewTransform = Selector.register("cameraViewTransform");
    @Bridge private native static @ByVal CGAffineTransform objc_getCameraViewTransform(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGAffineTransform objc_getCameraViewTransformSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    public CGAffineTransform getCameraViewTransform() {
        if (customClass) { return objc_getCameraViewTransformSuper(getSuper(), cameraViewTransform); } else { return objc_getCameraViewTransform(this, cameraViewTransform); }
    }
    
    private static final Selector setCameraViewTransform$ = Selector.register("setCameraViewTransform:");
    @Bridge private native static void objc_setCameraViewTransform(UIImagePickerController __self__, Selector __cmd__, @ByVal CGAffineTransform cameraViewTransform);
    @Bridge private native static void objc_setCameraViewTransformSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGAffineTransform cameraViewTransform);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/cameraViewTransform">@property (nonatomic) CGAffineTransform cameraViewTransform</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setCameraViewTransform(CGAffineTransform cameraViewTransform) {
        if (customClass) { objc_setCameraViewTransformSuper(getSuper(), setCameraViewTransform$, cameraViewTransform); } else { objc_setCameraViewTransform(this, setCameraViewTransform$, cameraViewTransform); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIImagePickerControllerDelegate objc_getDelegate(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImagePickerControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/delegate">@property (nonatomic, assign) id&amp;lt;UINavigationControllerDelegate, UIImagePickerControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIImagePickerControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector mediaTypes = Selector.register("mediaTypes");
    @Bridge private native static NSArray objc_getMediaTypes(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getMediaTypesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getMediaTypes() {
        if (customClass) { return objc_getMediaTypesSuper(getSuper(), mediaTypes); } else { return objc_getMediaTypes(this, mediaTypes); }
    }
    
    private static final Selector setMediaTypes$ = Selector.register("setMediaTypes:");
    @Bridge private native static void objc_setMediaTypes(UIImagePickerController __self__, Selector __cmd__, NSArray mediaTypes);
    @Bridge private native static void objc_setMediaTypesSuper(ObjCSuper __super__, Selector __cmd__, NSArray mediaTypes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/mediaTypes">@property (nonatomic, copy) NSArray *mediaTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setMediaTypes(NSArray mediaTypes) {
        if (customClass) { objc_setMediaTypesSuper(getSuper(), setMediaTypes$, mediaTypes); } else { objc_setMediaTypes(this, setMediaTypes$, mediaTypes); }
    }
    
    private static final Selector showsCameraControls = Selector.register("showsCameraControls");
    @Bridge private native static boolean objc_isShowsCameraControls(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsCameraControlsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    public boolean isShowsCameraControls() {
        if (customClass) { return objc_isShowsCameraControlsSuper(getSuper(), showsCameraControls); } else { return objc_isShowsCameraControls(this, showsCameraControls); }
    }
    
    private static final Selector setShowsCameraControls$ = Selector.register("setShowsCameraControls:");
    @Bridge private native static void objc_setShowsCameraControls(UIImagePickerController __self__, Selector __cmd__, boolean showsCameraControls);
    @Bridge private native static void objc_setShowsCameraControlsSuper(ObjCSuper __super__, Selector __cmd__, boolean showsCameraControls);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/showsCameraControls">@property (nonatomic) BOOL showsCameraControls</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setShowsCameraControls(boolean showsCameraControls) {
        if (customClass) { objc_setShowsCameraControlsSuper(getSuper(), setShowsCameraControls$, showsCameraControls); } else { objc_setShowsCameraControls(this, setShowsCameraControls$, showsCameraControls); }
    }
    
    private static final Selector sourceType = Selector.register("sourceType");
    @Bridge private native static UIImagePickerControllerSourceType objc_getSourceType(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerSourceType objc_getSourceTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImagePickerControllerSourceType getSourceType() {
        if (customClass) { return objc_getSourceTypeSuper(getSuper(), sourceType); } else { return objc_getSourceType(this, sourceType); }
    }
    
    private static final Selector setSourceType$ = Selector.register("setSourceType:");
    @Bridge private native static void objc_setSourceType(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    @Bridge private native static void objc_setSourceTypeSuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/sourceType">@property (nonatomic) UIImagePickerControllerSourceType sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSourceType(UIImagePickerControllerSourceType sourceType) {
        if (customClass) { objc_setSourceTypeSuper(getSuper(), setSourceType$, sourceType); } else { objc_setSourceType(this, setSourceType$, sourceType); }
    }
    
    private static final Selector videoMaximumDuration = Selector.register("videoMaximumDuration");
    @Bridge private native static double objc_getVideoMaximumDuration(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static double objc_getVideoMaximumDurationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public double getVideoMaximumDuration() {
        if (customClass) { return objc_getVideoMaximumDurationSuper(getSuper(), videoMaximumDuration); } else { return objc_getVideoMaximumDuration(this, videoMaximumDuration); }
    }
    
    private static final Selector setVideoMaximumDuration$ = Selector.register("setVideoMaximumDuration:");
    @Bridge private native static void objc_setVideoMaximumDuration(UIImagePickerController __self__, Selector __cmd__, double videoMaximumDuration);
    @Bridge private native static void objc_setVideoMaximumDurationSuper(ObjCSuper __super__, Selector __cmd__, double videoMaximumDuration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoMaximumDuration">@property (nonatomic) NSTimeInterval videoMaximumDuration</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoMaximumDuration(double videoMaximumDuration) {
        if (customClass) { objc_setVideoMaximumDurationSuper(getSuper(), setVideoMaximumDuration$, videoMaximumDuration); } else { objc_setVideoMaximumDuration(this, setVideoMaximumDuration$, videoMaximumDuration); }
    }
    
    private static final Selector videoQuality = Selector.register("videoQuality");
    @Bridge private native static UIImagePickerControllerQualityType objc_getVideoQuality(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static UIImagePickerControllerQualityType objc_getVideoQualitySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public UIImagePickerControllerQualityType getVideoQuality() {
        if (customClass) { return objc_getVideoQualitySuper(getSuper(), videoQuality); } else { return objc_getVideoQuality(this, videoQuality); }
    }
    
    private static final Selector setVideoQuality$ = Selector.register("setVideoQuality:");
    @Bridge private native static void objc_setVideoQuality(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    @Bridge private native static void objc_setVideoQualitySuper(ObjCSuper __super__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instp/UIImagePickerController/videoQuality">@property (nonatomic) UIImagePickerControllerQualityType videoQuality</a>
     * @since Available in iOS 3.1 and later.
     */
    public void setVideoQuality(UIImagePickerControllerQualityType videoQuality) {
        if (customClass) { objc_setVideoQualitySuper(getSuper(), setVideoQuality$, videoQuality); } else { objc_setVideoQuality(this, setVideoQuality$, videoQuality); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector availableCaptureModesForCameraDevice$ = Selector.register("availableCaptureModesForCameraDevice:");
    @Bridge private native static NSArray objc_getAvailableCaptureModes(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableCaptureModesForCameraDevice:">+ (NSArray *)availableCaptureModesForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static NSArray getAvailableCaptureModes(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_getAvailableCaptureModes(objCClass, availableCaptureModesForCameraDevice$, cameraDevice);
    }
    
    private static final Selector availableMediaTypesForSourceType$ = Selector.register("availableMediaTypesForSourceType:");
    @Bridge private native static NSArray objc_getAvailableMediaTypes(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/availableMediaTypesForSourceType:">+ (NSArray *)availableMediaTypesForSourceType:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 3.0 and later.
     */
    public static NSArray getAvailableMediaTypes(UIImagePickerControllerSourceType sourceType) {
        return objc_getAvailableMediaTypes(objCClass, availableMediaTypesForSourceType$, sourceType);
    }
    
    private static final Selector isCameraDeviceAvailable$ = Selector.register("isCameraDeviceAvailable:");
    @Bridge private native static boolean objc_isCameraDeviceAvailable(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isCameraDeviceAvailable:">+ (BOOL)isCameraDeviceAvailable:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static boolean isCameraDeviceAvailable(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_isCameraDeviceAvailable(objCClass, isCameraDeviceAvailable$, cameraDevice);
    }
    
    private static final Selector isFlashAvailableForCameraDevice$ = Selector.register("isFlashAvailableForCameraDevice:");
    @Bridge private native static boolean objc_isFlashAvailableForCameraDevice(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isFlashAvailableForCameraDevice:">+ (BOOL)isFlashAvailableForCameraDevice:(UIImagePickerControllerCameraDevice)cameraDevice</a>
     * @since Available in iOS 4.0 and later.
     */
    public static boolean isFlashAvailableForCameraDevice(UIImagePickerControllerCameraDevice cameraDevice) {
        return objc_isFlashAvailableForCameraDevice(objCClass, isFlashAvailableForCameraDevice$, cameraDevice);
    }
    
    private static final Selector isSourceTypeAvailable$ = Selector.register("isSourceTypeAvailable:");
    @Bridge private native static boolean objc_isSourceTypeAvailable(ObjCClass __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/clm/UIImagePickerController/isSourceTypeAvailable:">+ (BOOL)isSourceTypeAvailable:(UIImagePickerControllerSourceType)sourceType</a>
     * @since Available in iOS 2.0 and later.
     */
    public static boolean isSourceTypeAvailable(UIImagePickerControllerSourceType sourceType) {
        return objc_isSourceTypeAvailable(objCClass, isSourceTypeAvailable$, sourceType);
    }
    
    private static final Selector startVideoCapture = Selector.register("startVideoCapture");
    @Bridge private native static boolean objc_startVideoCapture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_startVideoCaptureSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/startVideoCapture">- (BOOL)startVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean startVideoCapture() {
        if (customClass) { return objc_startVideoCaptureSuper(getSuper(), startVideoCapture); } else { return objc_startVideoCapture(this, startVideoCapture); }
    }
    
    private static final Selector stopVideoCapture = Selector.register("stopVideoCapture");
    @Bridge private native static void objc_stopVideoCapture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static void objc_stopVideoCaptureSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/stopVideoCapture">- (void)stopVideoCapture</a>
     * @since Available in iOS 4.0 and later.
     */
    public void stopVideoCapture() {
        if (customClass) { objc_stopVideoCaptureSuper(getSuper(), stopVideoCapture); } else { objc_stopVideoCapture(this, stopVideoCapture); }
    }
    
    private static final Selector takePicture = Selector.register("takePicture");
    @Bridge private native static void objc_takePicture(UIImagePickerController __self__, Selector __cmd__);
    @Bridge private native static void objc_takePictureSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIImagePickerController_Class/UIImagePickerController/UIImagePickerController.html#//apple_ref/occ/instm/UIImagePickerController/takePicture">- (void)takePicture</a>
     * @since Available in iOS 3.1 and later.
     */
    public void takePicture() {
        if (customClass) { objc_takePictureSuper(getSuper(), takePicture); } else { objc_takePicture(this, takePicture); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allowsEditing") public static boolean isAllowsEditing(UIImagePickerController __self__, Selector __cmd__) { return __self__.isAllowsEditing(); }
        @Callback @BindSelector("setAllowsEditing:") public static void setAllowsEditing(UIImagePickerController __self__, Selector __cmd__, boolean allowsEditing) { __self__.setAllowsEditing(allowsEditing); }
        @Callback @BindSelector("cameraCaptureMode") public static UIImagePickerControllerCameraCaptureMode getCameraCaptureMode(UIImagePickerController __self__, Selector __cmd__) { return __self__.getCameraCaptureMode(); }
        @Callback @BindSelector("setCameraCaptureMode:") public static void setCameraCaptureMode(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraCaptureMode cameraCaptureMode) { __self__.setCameraCaptureMode(cameraCaptureMode); }
        @Callback @BindSelector("cameraDevice") public static UIImagePickerControllerCameraDevice getCameraDevice(UIImagePickerController __self__, Selector __cmd__) { return __self__.getCameraDevice(); }
        @Callback @BindSelector("setCameraDevice:") public static void setCameraDevice(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraDevice cameraDevice) { __self__.setCameraDevice(cameraDevice); }
        @Callback @BindSelector("cameraFlashMode") public static UIImagePickerControllerCameraFlashMode getCameraFlashMode(UIImagePickerController __self__, Selector __cmd__) { return __self__.getCameraFlashMode(); }
        @Callback @BindSelector("setCameraFlashMode:") public static void setCameraFlashMode(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerCameraFlashMode cameraFlashMode) { __self__.setCameraFlashMode(cameraFlashMode); }
        @Callback @BindSelector("cameraOverlayView") public static UIView getCameraOverlayView(UIImagePickerController __self__, Selector __cmd__) { return __self__.getCameraOverlayView(); }
        @Callback @BindSelector("setCameraOverlayView:") public static void setCameraOverlayView(UIImagePickerController __self__, Selector __cmd__, UIView cameraOverlayView) { __self__.setCameraOverlayView(cameraOverlayView); }
        @Callback @BindSelector("cameraViewTransform") public static @ByVal CGAffineTransform getCameraViewTransform(UIImagePickerController __self__, Selector __cmd__) { return __self__.getCameraViewTransform(); }
        @Callback @BindSelector("setCameraViewTransform:") public static void setCameraViewTransform(UIImagePickerController __self__, Selector __cmd__, @ByVal CGAffineTransform cameraViewTransform) { __self__.setCameraViewTransform(cameraViewTransform); }
        @Callback @BindSelector("delegate") public static UIImagePickerControllerDelegate getDelegate(UIImagePickerController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("mediaTypes") public static NSArray getMediaTypes(UIImagePickerController __self__, Selector __cmd__) { return __self__.getMediaTypes(); }
        @Callback @BindSelector("setMediaTypes:") public static void setMediaTypes(UIImagePickerController __self__, Selector __cmd__, NSArray mediaTypes) { __self__.setMediaTypes(mediaTypes); }
        @Callback @BindSelector("showsCameraControls") public static boolean isShowsCameraControls(UIImagePickerController __self__, Selector __cmd__) { return __self__.isShowsCameraControls(); }
        @Callback @BindSelector("setShowsCameraControls:") public static void setShowsCameraControls(UIImagePickerController __self__, Selector __cmd__, boolean showsCameraControls) { __self__.setShowsCameraControls(showsCameraControls); }
        @Callback @BindSelector("sourceType") public static UIImagePickerControllerSourceType getSourceType(UIImagePickerController __self__, Selector __cmd__) { return __self__.getSourceType(); }
        @Callback @BindSelector("setSourceType:") public static void setSourceType(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerSourceType sourceType) { __self__.setSourceType(sourceType); }
        @Callback @BindSelector("videoMaximumDuration") public static double getVideoMaximumDuration(UIImagePickerController __self__, Selector __cmd__) { return __self__.getVideoMaximumDuration(); }
        @Callback @BindSelector("setVideoMaximumDuration:") public static void setVideoMaximumDuration(UIImagePickerController __self__, Selector __cmd__, double videoMaximumDuration) { __self__.setVideoMaximumDuration(videoMaximumDuration); }
        @Callback @BindSelector("videoQuality") public static UIImagePickerControllerQualityType getVideoQuality(UIImagePickerController __self__, Selector __cmd__) { return __self__.getVideoQuality(); }
        @Callback @BindSelector("setVideoQuality:") public static void setVideoQuality(UIImagePickerController __self__, Selector __cmd__, UIImagePickerControllerQualityType videoQuality) { __self__.setVideoQuality(videoQuality); }
        @Callback @BindSelector("startVideoCapture") public static boolean startVideoCapture(UIImagePickerController __self__, Selector __cmd__) { return __self__.startVideoCapture(); }
        @Callback @BindSelector("stopVideoCapture") public static void stopVideoCapture(UIImagePickerController __self__, Selector __cmd__) { __self__.stopVideoCapture(); }
        @Callback @BindSelector("takePicture") public static void takePicture(UIImagePickerController __self__, Selector __cmd__) { __self__.takePicture(); }
    }
    /*</callbacks>*/

}
