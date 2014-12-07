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
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVFoundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVFoundation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioEngineConfigurationChangeNotification", optional=true)
    public static native String Value__AVAudioEngineConfigurationChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCoreAnimationBeginTimeAtZero", optional=true)
    public static native double AVCoreAnimationBeginTimeAtZero();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetLowQuality", optional=true)
    public static native String AVAssetExportPresetLowQuality();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetMediumQuality", optional=true)
    public static native String AVAssetExportPresetMediumQuality();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetHighestQuality", optional=true)
    public static native String AVAssetExportPresetHighestQuality();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset640x480", optional=true)
    public static native String AVAssetExportPreset640x480();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset960x540", optional=true)
    public static native String AVAssetExportPreset960x540();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset1280x720", optional=true)
    public static native String AVAssetExportPreset1280x720();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset1920x1080", optional=true)
    public static native String AVAssetExportPreset1920x1080();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetAppleM4A", optional=true)
    public static native String AVAssetExportPresetAppleM4A();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetPassthrough", optional=true)
    public static native String AVAssetExportPresetPassthrough();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeCleanAperture", optional=true)
    public static native String AVAssetImageGeneratorApertureModeCleanAperture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeProductionAperture", optional=true)
    public static native String AVAssetImageGeneratorApertureModeProductionAperture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeEncodedPixels", optional=true)
    public static native String AVAssetImageGeneratorApertureModeEncodedPixels();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeAudioFallback", optional=true)
    public static native String AVTrackAssociationTypeAudioFallback();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeChapterList", optional=true)
    public static native String AVTrackAssociationTypeChapterList();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeForcedSubtitlesOnly", optional=true)
    public static native String AVTrackAssociationTypeForcedSubtitlesOnly();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeSelectionFollower", optional=true)
    public static native String AVTrackAssociationTypeSelectionFollower();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeTimecode", optional=true)
    public static native String AVTrackAssociationTypeTimecode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVTrackAssociationTypeMetadataReferent", optional=true)
    public static native String AVTrackAssociationTypeMetadataReferent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceWasConnectedNotification", optional=true)
    public static native String AVCaptureDeviceWasConnectedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceWasDisconnectedNotification", optional=true)
    public static native String AVCaptureDeviceWasDisconnectedNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureDeviceSubjectAreaDidChangeNotification", optional=true)
    public static native String AVCaptureDeviceSubjectAreaDidChangeNotification();
    @GlobalValue(symbol="AVCaptureMaxAvailableTorchLevel", optional=true)
    public static native float AVCaptureMaxAvailableTorchLevel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureLensPositionCurrent", optional=true)
    public static native float Value__AVCaptureLensPositionCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureExposureDurationCurrent", optional=true)
    public static native @ByVal CMTime Value__AVCaptureExposureDurationCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureISOCurrent", optional=true)
    public static native float Value__AVCaptureISOCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureExposureTargetBiasCurrent", optional=true)
    public static native float Value__AVCaptureExposureTargetBiasCurrent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVCaptureWhiteBalanceGainsCurrent", optional=true)
    public static native @ByVal AVCaptureWhiteBalanceGains Value__AVCaptureWhiteBalanceGainsCurrent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureInputPortFormatDescriptionDidChangeNotification", optional=true)
    public static native String AVCaptureInputPortFormatDescriptionDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionRuntimeErrorNotification", optional=true)
    public static native String AVCaptureSessionRuntimeErrorNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionErrorKey", optional=true)
    public static native String AVCaptureSessionErrorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionDidStartRunningNotification", optional=true)
    public static native String AVCaptureSessionDidStartRunningNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionDidStopRunningNotification", optional=true)
    public static native String AVCaptureSessionDidStopRunningNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionWasInterruptedNotification", optional=true)
    public static native String AVCaptureSessionWasInterruptedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionInterruptionEndedNotification", optional=true)
    public static native String AVCaptureSessionInterruptionEndedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetPhoto", optional=true)
    public static native String AVCaptureSessionPresetPhoto();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetHigh", optional=true)
    public static native String AVCaptureSessionPresetHigh();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetMedium", optional=true)
    public static native String AVCaptureSessionPresetMedium();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetLow", optional=true)
    public static native String AVCaptureSessionPresetLow();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset352x288", optional=true)
    public static native String AVCaptureSessionPreset352x288();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset640x480", optional=true)
    public static native String AVCaptureSessionPreset640x480();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset1280x720", optional=true)
    public static native String AVCaptureSessionPreset1280x720();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset1920x1080", optional=true)
    public static native String AVCaptureSessionPreset1920x1080();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetiFrame960x540", optional=true)
    public static native String AVCaptureSessionPresetiFrame960x540();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetiFrame1280x720", optional=true)
    public static native String AVCaptureSessionPresetiFrame1280x720();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetInputPriority", optional=true)
    public static native String AVCaptureSessionPresetInputPriority();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorDeviceKey", optional=true)
    public static native String AVErrorDeviceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorTimeKey", optional=true)
    public static native String AVErrorTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorFileSizeKey", optional=true)
    public static native String AVErrorFileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorPIDKey", optional=true)
    public static native String AVErrorPIDKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVErrorRecordingSuccessfullyFinishedKey", optional=true)
    public static native String AVErrorRecordingSuccessfullyFinishedKey();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVErrorMediaTypeKey", optional=true)
    public static native String AVErrorMediaTypeKey();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVErrorMediaSubTypeKey", optional=true)
    public static native String AVErrorMediaSubTypeKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorPresentationTimeStampKey", optional=true)
    public static native String AVErrorPresentationTimeStampKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorPersistentTrackIDKey", optional=true)
    public static native String AVErrorPersistentTrackIDKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVErrorFileTypeKey", optional=true)
    public static native String AVErrorFileTypeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemTimeJumpedNotification", optional=true)
    public static native String AVPlayerItemTimeJumpedNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemDidPlayToEndTimeNotification", optional=true)
    public static native String AVPlayerItemDidPlayToEndTimeNotification();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVPlayerItemFailedToPlayToEndTimeNotification", optional=true)
    public static native String AVPlayerItemFailedToPlayToEndTimeNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemPlaybackStalledNotification", optional=true)
    public static native String AVPlayerItemPlaybackStalledNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemNewAccessLogEntryNotification", optional=true)
    public static native String AVPlayerItemNewAccessLogEntryNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemNewErrorLogEntryNotification", optional=true)
    public static native String AVPlayerItemNewErrorLogEntryNotification();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVPlayerItemFailedToPlayToEndTimeErrorKey", optional=true)
    public static native String AVPlayerItemFailedToPlayToEndTimeErrorKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVSampleBufferDisplayLayerFailedToDecodeNotification", optional=true)
    public static native String Value__AVSampleBufferDisplayLayerFailedToDecodeNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVSampleBufferDisplayLayerFailedToDecodeNotificationErrorKey", optional=true)
    public static native String Value__AVSampleBufferDisplayLayerFailedToDecodeNotificationErrorKey();
    @GlobalValue(symbol="AVSpeechUtteranceMinimumSpeechRate", optional=true)
    public static native float AVSpeechUtteranceMinimumSpeechRate();
    @GlobalValue(symbol="AVSpeechUtteranceMaximumSpeechRate", optional=true)
    public static native float AVSpeechUtteranceMaximumSpeechRate();
    @GlobalValue(symbol="AVSpeechUtteranceDefaultSpeechRate", optional=true)
    public static native float AVSpeechUtteranceDefaultSpeechRate();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="AVMakeRectWithAspectRatioInsideRect", optional=true)
    public static native @ByVal CGRect function__AVMakeRectWithAspectRatioInsideRect(@ByVal CGSize aspectRatio, @ByVal CGRect boundingRect);
    /*</methods>*/
}
