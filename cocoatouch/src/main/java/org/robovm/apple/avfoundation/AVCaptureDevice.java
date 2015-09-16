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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureDevice/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeWasConnected(final VoidBlock1<AVCaptureDevice> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WasConnectedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((AVCaptureDevice) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 4.0 and later.
         */
        public static NSObject observeWasDisconnected(final VoidBlock1<AVCaptureDevice> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WasDisconnectedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((AVCaptureDevice) a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeSubjectAreaDidChange(AVCaptureDevice object, final VoidBlock1<AVCaptureDevice> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(SubjectAreaDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((AVCaptureDevice) a.getObject());
                }
            });
        }
    }
    
    /*<ptr>*/public static class AVCaptureDevicePtr extends Ptr<AVCaptureDevice, AVCaptureDevicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVCaptureDevice.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureDevice() {}
    protected AVCaptureDevice(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "uniqueID")
    public native String getUniqueID();
    @Property(selector = "modelID")
    public native String getModelID();
    @Property(selector = "localizedName")
    public native String getLocalizedName();
    @Property(selector = "isConnected")
    public native boolean isConnected();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "formats")
    public native NSArray<AVCaptureDeviceFormat> getFormats();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "activeFormat")
    public native AVCaptureDeviceFormat getActiveFormat();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setActiveFormat:")
    public native void setActiveFormat(AVCaptureDeviceFormat v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "activeVideoMinFrameDuration")
    public native @ByVal CMTime getActiveVideoMinFrameDuration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setActiveVideoMinFrameDuration:")
    public native void setActiveVideoMinFrameDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "activeVideoMaxFrameDuration")
    public native @ByVal CMTime getActiveVideoMaxFrameDuration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setActiveVideoMaxFrameDuration:")
    public native void setActiveVideoMaxFrameDuration(@ByVal CMTime v);
    @Property(selector = "position")
    public native AVCaptureDevicePosition getPosition();
    @Property(selector = "hasFlash")
    public native boolean hasFlash();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isFlashAvailable")
    public native boolean isFlashAvailable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isFlashActive")
    public native boolean isFlashActive();
    @Property(selector = "flashMode")
    public native AVCaptureFlashMode getFlashMode();
    @Property(selector = "setFlashMode:")
    public native void setFlashMode(AVCaptureFlashMode v);
    @Property(selector = "hasTorch")
    public native boolean hasTorch();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isTorchAvailable")
    public native boolean isTorchAvailable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isTorchActive")
    public native boolean isTorchActive();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "torchLevel")
    public native float getTorchLevel();
    @Property(selector = "torchMode")
    public native AVCaptureTorchMode getTorchMode();
    @Property(selector = "setTorchMode:")
    public native void setTorchMode(AVCaptureTorchMode v);
    @Property(selector = "focusMode")
    public native AVCaptureFocusMode getFocusMode();
    @Property(selector = "setFocusMode:")
    public native void setFocusMode(AVCaptureFocusMode v);
    @Property(selector = "isFocusPointOfInterestSupported")
    public native boolean isFocusPointOfInterestSupported();
    @Property(selector = "focusPointOfInterest")
    public native @ByVal CGPoint getFocusPointOfInterest();
    @Property(selector = "setFocusPointOfInterest:")
    public native void setFocusPointOfInterest(@ByVal CGPoint v);
    @Property(selector = "isAdjustingFocus")
    public native boolean isAdjustingFocus();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isAutoFocusRangeRestrictionSupported")
    public native boolean isAutoFocusRangeRestrictionSupported();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "autoFocusRangeRestriction")
    public native AVCaptureAutoFocusRangeRestriction getAutoFocusRangeRestriction();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAutoFocusRangeRestriction:")
    public native void setAutoFocusRangeRestriction(AVCaptureAutoFocusRangeRestriction v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isSmoothAutoFocusSupported")
    public native boolean isSmoothAutoFocusSupported();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isSmoothAutoFocusEnabled")
    public native boolean isSmoothAutoFocusEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setSmoothAutoFocusEnabled:")
    public native void setSmoothAutoFocusEnabled(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "lensPosition")
    public native float getLensPosition();
    @Property(selector = "exposureMode")
    public native AVCaptureExposureMode getExposureMode();
    @Property(selector = "setExposureMode:")
    public native void setExposureMode(AVCaptureExposureMode v);
    @Property(selector = "isExposurePointOfInterestSupported")
    public native boolean isExposurePointOfInterestSupported();
    @Property(selector = "exposurePointOfInterest")
    public native @ByVal CGPoint getExposurePointOfInterest();
    @Property(selector = "setExposurePointOfInterest:")
    public native void setExposurePointOfInterest(@ByVal CGPoint v);
    @Property(selector = "isAdjustingExposure")
    public native boolean isAdjustingExposure();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "lensAperture")
    public native float getLensAperture();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "exposureDuration")
    public native @ByVal CMTime getExposureDuration();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "ISO")
    public native float getISO();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "exposureTargetOffset")
    public native float getExposureTargetOffset();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "exposureTargetBias")
    public native float getExposureTargetBias();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "minExposureTargetBias")
    public native float getMinExposureTargetBias();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "maxExposureTargetBias")
    public native float getMaxExposureTargetBias();
    @Property(selector = "whiteBalanceMode")
    public native AVCaptureWhiteBalanceMode getWhiteBalanceMode();
    @Property(selector = "setWhiteBalanceMode:")
    public native void setWhiteBalanceMode(AVCaptureWhiteBalanceMode v);
    @Property(selector = "isAdjustingWhiteBalance")
    public native boolean isAdjustingWhiteBalance();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "deviceWhiteBalanceGains")
    public native @ByVal AVCaptureWhiteBalanceGains getDeviceWhiteBalanceGains();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "grayWorldDeviceWhiteBalanceGains")
    public native @ByVal AVCaptureWhiteBalanceGains getGrayWorldDeviceWhiteBalanceGains();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "maxWhiteBalanceGain")
    public native float getMaxWhiteBalanceGain();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "isSubjectAreaChangeMonitoringEnabled")
    public native boolean isSubjectAreaChangeMonitoringEnabled();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setSubjectAreaChangeMonitoringEnabled:")
    public native void setSubjectAreaChangeMonitoringEnabled(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isLowLightBoostSupported")
    public native boolean isLowLightBoostSupported();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isLowLightBoostEnabled")
    public native boolean isLowLightBoostEnabled();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "automaticallyEnablesLowLightBoostWhenAvailable")
    public native boolean automaticallyEnablesLowLightBoostWhenAvailable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAutomaticallyEnablesLowLightBoostWhenAvailable:")
    public native void setAutomaticallyEnablesLowLightBoostWhenAvailable(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "videoZoomFactor")
    public native @MachineSizedFloat double getVideoZoomFactor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setVideoZoomFactor:")
    public native void setVideoZoomFactor(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isRampingVideoZoom")
    public native boolean isRampingVideoZoom();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "automaticallyAdjustsVideoHDREnabled")
    public native boolean automaticallyAdjustsVideoHDREnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setAutomaticallyAdjustsVideoHDREnabled:")
    public native void setAutomaticallyAdjustsVideoHDREnabled(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isVideoHDREnabled")
    public native boolean isVideoHDREnabled();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setVideoHDREnabled:")
    public native void setVideoHDREnabled(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceWasConnectedNotification", optional=true)
    public static native NSString WasConnectedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceWasDisconnectedNotification", optional=true)
    public static native NSString WasDisconnectedNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceSubjectAreaDidChangeNotification", optional=true)
    public static native NSString SubjectAreaDidChangeNotification();
    @GlobalValue(symbol="AVCaptureMaxAvailableTorchLevel", optional=true)
    public static native float getMaxAvailableTorchLevel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureLensPositionCurrent", optional=true)
    public static native float getLensPositionCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureExposureDurationCurrent", optional=true)
    public static native @ByVal CMTime getExposureDurationCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureISOCurrent", optional=true)
    public static native float getISOCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureExposureTargetBiasCurrent", optional=true)
    public static native float getExposureTargetBiasCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureWhiteBalanceGainsCurrent", optional=true)
    public static native @ByVal AVCaptureWhiteBalanceGains getWhiteBalanceGainsCurrent();
    
    @Method(selector = "hasMediaType:")
    public native boolean hasMediaType(AVMediaType mediaType);
    public boolean lockForConfiguration() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = lockForConfiguration(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "lockForConfiguration:")
    private native boolean lockForConfiguration(NSError.NSErrorPtr outError);
    @Method(selector = "unlockForConfiguration")
    public native void unlockForConfiguration();
    @Method(selector = "supportsAVCaptureSessionPreset:")
    public native boolean supportsAVCaptureSessionPreset(AVCaptureSessionPreset preset);
    @Method(selector = "devices")
    public static native NSArray<AVCaptureDevice> getDevices();
    @Method(selector = "devicesWithMediaType:")
    public static native NSArray<AVCaptureDevice> getDevicesForMediaType(AVMediaType mediaType);
    @Method(selector = "defaultDeviceWithMediaType:")
    public static native AVCaptureDevice getDefaultDeviceForMediaType(AVMediaType mediaType);
    @Method(selector = "deviceWithUniqueID:")
    public static native AVCaptureDevice getDeviceWithUniqueID(String deviceUniqueID);
    @Method(selector = "isFlashModeSupported:")
    public native boolean isFlashModeSupported(AVCaptureFlashMode flashMode);
    @Method(selector = "isTorchModeSupported:")
    public native boolean isTorchModeSupported(AVCaptureTorchMode torchMode);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean setTorchModeOn(float torchLevel) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setTorchModeOn(torchLevel, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setTorchModeOnWithLevel:error:")
    private native boolean setTorchModeOn(float torchLevel, NSError.NSErrorPtr outError);
    @Method(selector = "isFocusModeSupported:")
    public native boolean isFocusModeSupported(AVCaptureFocusMode focusMode);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setFocusModeLockedWithLensPosition:completionHandler:")
    public native void setFocusModeLocked(float lensPosition, @Block VoidBlock1<CMTime> handler);
    @Method(selector = "isExposureModeSupported:")
    public native boolean isExposureModeSupported(AVCaptureExposureMode exposureMode);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setExposureModeCustomWithDuration:ISO:completionHandler:")
    public native void setExposureModeCustom(@ByVal CMTime duration, float ISO, @Block VoidBlock1<CMTime> handler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setExposureTargetBias:completionHandler:")
    public native void setExposureTargetBias(float bias, @Block VoidBlock1<CMTime> handler);
    @Method(selector = "isWhiteBalanceModeSupported:")
    public native boolean isWhiteBalanceModeSupported(AVCaptureWhiteBalanceMode whiteBalanceMode);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setWhiteBalanceModeLockedWithDeviceWhiteBalanceGains:completionHandler:")
    public native void setWhiteBalanceModeLocked(@ByVal AVCaptureWhiteBalanceGains whiteBalanceGains, @Block VoidBlock1<CMTime> handler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "chromaticityValuesForDeviceWhiteBalanceGains:")
    public native @ByVal AVCaptureWhiteBalanceChromaticityValues convertDeviceWhiteBalanceGainsToChromaticityValues(@ByVal AVCaptureWhiteBalanceGains whiteBalanceGains);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "deviceWhiteBalanceGainsForChromaticityValues:")
    public native @ByVal AVCaptureWhiteBalanceGains convertChromaticityValuesToDeviceWhiteBalanceGains(@ByVal AVCaptureWhiteBalanceChromaticityValues chromaticityValues);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "temperatureAndTintValuesForDeviceWhiteBalanceGains:")
    public native @ByVal AVCaptureWhiteBalanceTemperatureAndTintValues convertDeviceWhiteBalanceGainsToTemperatureAndTintValues(@ByVal AVCaptureWhiteBalanceGains whiteBalanceGains);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "deviceWhiteBalanceGainsForTemperatureAndTintValues:")
    public native @ByVal AVCaptureWhiteBalanceGains convertTemperatureAndTintValuesToDeviceWhiteBalanceGains(@ByVal AVCaptureWhiteBalanceTemperatureAndTintValues tempAndTintValues);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "rampToVideoZoomFactor:withRate:")
    public native void rampToVideoZoomFactor(@MachineSizedFloat double factor, float rate);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "cancelVideoZoomRamp")
    public native void cancelVideoZoomRamp();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "authorizationStatusForMediaType:")
    public static native AVAuthorizationStatus getAuthorizationStatusForMediaType(AVMediaType mediaType);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "requestAccessForMediaType:completionHandler:")
    public static native void requestAccessForMediaType(AVMediaType mediaType, @Block VoidBooleanBlock handler);
    /*</methods>*/
}
