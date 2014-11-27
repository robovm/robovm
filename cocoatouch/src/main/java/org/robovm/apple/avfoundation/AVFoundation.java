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
    @GlobalValue(symbol="AVFormatIDKey", optional=true)
    public static native String AVFormatIDKey();
    @GlobalValue(symbol="AVSampleRateKey", optional=true)
    public static native String AVSampleRateKey();
    @GlobalValue(symbol="AVNumberOfChannelsKey", optional=true)
    public static native String NumberOfChannelsKey();
    @GlobalValue(symbol="AVLinearPCMBitDepthKey", optional=true)
    public static native String AVLinearPCMBitDepthKey();
    @GlobalValue(symbol="AVLinearPCMIsBigEndianKey", optional=true)
    public static native String AVLinearPCMIsBigEndianKey();
    @GlobalValue(symbol="AVLinearPCMIsFloatKey", optional=true)
    public static native String AVLinearPCMIsFloatKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVLinearPCMIsNonInterleaved", optional=true)
    public static native String AVLinearPCMIsNonInterleaved();
    @GlobalValue(symbol="AVEncoderAudioQualityKey", optional=true)
    public static native String AVEncoderAudioQualityKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVEncoderAudioQualityForVBRKey", optional=true)
    public static native String AVEncoderAudioQualityForVBRKey();
    @GlobalValue(symbol="AVEncoderBitRateKey", optional=true)
    public static native String AVEncoderBitRateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVEncoderBitRatePerChannelKey", optional=true)
    public static native String AVEncoderBitRatePerChannelKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVEncoderBitRateStrategyKey", optional=true)
    public static native String AVEncoderBitRateStrategyKey();
    @GlobalValue(symbol="AVEncoderBitDepthHintKey", optional=true)
    public static native String AVEncoderBitDepthHintKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVSampleRateConverterAlgorithmKey", optional=true)
    public static native String AVSampleRateConverterAlgorithmKey();
    @GlobalValue(symbol="AVSampleRateConverterAudioQualityKey", optional=true)
    public static native String AVSampleRateConverterAudioQualityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVChannelLayoutKey", optional=true)
    public static native String AVChannelLayoutKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioBitRateStrategy_Constant", optional=true)
    public static native String AVAudioBitRateStrategyConstant();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioBitRateStrategy_LongTermAverage", optional=true)
    public static native String AVAudioBitRateStrategyLongTermAverage();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioBitRateStrategy_VariableConstrained", optional=true)
    public static native String AVAudioBitRateStrategyVariableConstrained();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioBitRateStrategy_Variable", optional=true)
    public static native String AVAudioBitRateStrategyVariable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVSampleRateConverterAlgorithm_Normal", optional=true)
    public static native String AVSampleRateConverterAlgorithm_Normal();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVSampleRateConverterAlgorithm_Mastering", optional=true)
    public static native String AVSampleRateConverterAlgorithm_Mastering();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionNotification", optional=true)
    public static native String AVAudioSessionInterruptionNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionRouteChangeNotification", optional=true)
    public static native String AVAudioSessionRouteChangeNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionMediaServicesWereLostNotification", optional=true)
    public static native String AVAudioSessionMediaServicesWereLostNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionMediaServicesWereResetNotification", optional=true)
    public static native String AVAudioSessionMediaServicesWereResetNotification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionSilenceSecondaryAudioHintNotification", optional=true)
    public static native String AVAudioSessionSilenceSecondaryAudioHintNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionTypeKey", optional=true)
    public static native String AVAudioSessionInterruptionTypeKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionInterruptionOptionKey", optional=true)
    public static native String AVAudioSessionInterruptionOptionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionRouteChangeReasonKey", optional=true)
    public static native String AVAudioSessionRouteChangeReasonKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionRouteChangePreviousRouteKey", optional=true)
    public static native String AVAudioSessionRouteChangePreviousRouteKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionSilenceSecondaryAudioHintTypeKey", optional=true)
    public static native String AVAudioSessionSilenceSecondaryAudioHintTypeKey();
    @GlobalValue(symbol="AVAudioSessionCategoryAmbient", optional=true)
    public static native String AVAudioSessionCategoryAmbient();
    @GlobalValue(symbol="AVAudioSessionCategorySoloAmbient", optional=true)
    public static native String AVAudioSessionCategorySoloAmbient();
    @GlobalValue(symbol="AVAudioSessionCategoryPlayback", optional=true)
    public static native String AVAudioSessionCategoryPlayback();
    @GlobalValue(symbol="AVAudioSessionCategoryRecord", optional=true)
    public static native String AVAudioSessionCategoryRecord();
    @GlobalValue(symbol="AVAudioSessionCategoryPlayAndRecord", optional=true)
    public static native String AVAudioSessionCategoryPlayAndRecord();
    @GlobalValue(symbol="AVAudioSessionCategoryAudioProcessing", optional=true)
    public static native String AVAudioSessionCategoryAudioProcessing();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionCategoryMultiRoute", optional=true)
    public static native String AVAudioSessionCategoryMultiRoute();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeDefault", optional=true)
    public static native String AVAudioSessionModeDefault();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVoiceChat", optional=true)
    public static native String AVAudioSessionModeVoiceChat();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeGameChat", optional=true)
    public static native String AVAudioSessionModeGameChat();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVideoRecording", optional=true)
    public static native String AVAudioSessionModeVideoRecording();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeMeasurement", optional=true)
    public static native String AVAudioSessionModeMeasurement();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeMoviePlayback", optional=true)
    public static native String AVAudioSessionModeMoviePlayback();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVideoChat", optional=true)
    public static native String AVAudioSessionModeVideoChat();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortLineIn", optional=true)
    public static native String AVAudioSessionPortLineIn();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInMic", optional=true)
    public static native String AVAudioSessionPortBuiltInMic();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHeadsetMic", optional=true)
    public static native String AVAudioSessionPortHeadsetMic();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortLineOut", optional=true)
    public static native String AVAudioSessionPortLineOut();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHeadphones", optional=true)
    public static native String AVAudioSessionPortHeadphones();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothA2DP", optional=true)
    public static native String AVAudioSessionPortBluetoothA2DP();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInReceiver", optional=true)
    public static native String AVAudioSessionPortBuiltInReceiver();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInSpeaker", optional=true)
    public static native String AVAudioSessionPortBuiltInSpeaker();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHDMI", optional=true)
    public static native String AVAudioSessionPortHDMI();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortAirPlay", optional=true)
    public static native String AVAudioSessionPortAirPlay();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothLE", optional=true)
    public static native String AVAudioSessionPortBluetoothLE();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothHFP", optional=true)
    public static native String AVAudioSessionPortBluetoothHFP();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortUSBAudio", optional=true)
    public static native String AVAudioSessionPortUSBAudio();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortCarAudio", optional=true)
    public static native String AVAudioSessionPortCarAudio();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionLocationUpper", optional=true)
    public static native String AVAudioSessionLocationUpper();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionLocationLower", optional=true)
    public static native String AVAudioSessionLocationLower();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationTop", optional=true)
    public static native String AVAudioSessionOrientationTop();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationBottom", optional=true)
    public static native String AVAudioSessionOrientationBottom();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationFront", optional=true)
    public static native String AVAudioSessionOrientationFront();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationBack", optional=true)
    public static native String AVAudioSessionOrientationBack();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationLeft", optional=true)
    public static native String AVAudioSessionOrientationLeft();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionOrientationRight", optional=true)
    public static native String AVAudioSessionOrientationRight();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPolarPatternOmnidirectional", optional=true)
    public static native String AVAudioSessionPolarPatternOmnidirectional();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPolarPatternCardioid", optional=true)
    public static native String AVAudioSessionPolarPatternCardioid();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPolarPatternSubcardioid", optional=true)
    public static native String AVAudioSessionPolarPatternSubcardioid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCoreAnimationBeginTimeAtZero", optional=true)
    public static native double AVCoreAnimationBeginTimeAtZero();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetPreferPreciseDurationAndTimingKey", optional=true)
    public static native String AVURLAssetPreferPreciseDurationAndTimingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetReferenceRestrictionsKey", optional=true)
    public static native String AVURLAssetReferenceRestrictionsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVURLAssetHTTPCookiesKey", optional=true)
    public static native String AVURLAssetHTTPCookiesKey();
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
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmLowQualityZeroLatency", optional=true)
    public static native String AVAudioTimePitchAlgorithmLowQualityZeroLatency();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmTimeDomain", optional=true)
    public static native String AVAudioTimePitchAlgorithmTimeDomain();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmSpectral", optional=true)
    public static native String AVAudioTimePitchAlgorithmSpectral();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmVarispeed", optional=true)
    public static native String AVAudioTimePitchAlgorithmVarispeed();
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
    @GlobalValue(symbol="AVFoundationErrorDomain", optional=true)
    public static native String ErrorDomain();
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
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeVideo", optional=true)
    public static native String AVMediaTypeVideo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeAudio", optional=true)
    public static native String AVMediaTypeAudio();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeText", optional=true)
    public static native String AVMediaTypeText();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeClosedCaption", optional=true)
    public static native String AVMediaTypeClosedCaption();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeSubtitle", optional=true)
    public static native String AVMediaTypeSubtitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeTimecode", optional=true)
    public static native String AVMediaTypeTimecode();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeMetadata", optional=true)
    public static native String AVMediaTypeMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaTypeMuxed", optional=true)
    public static native String AVMediaTypeMuxed();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicVisual", optional=true)
    public static native String AVMediaCharacteristicVisual();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicAudible", optional=true)
    public static native String AVMediaCharacteristicAudible();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicLegible", optional=true)
    public static native String AVMediaCharacteristicLegible();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicFrameBased", optional=true)
    public static native String AVMediaCharacteristicFrameBased();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicIsMainProgramContent", optional=true)
    public static native String AVMediaCharacteristicIsMainProgramContent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicIsAuxiliaryContent", optional=true)
    public static native String AVMediaCharacteristicIsAuxiliaryContent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicContainsOnlyForcedSubtitles", optional=true)
    public static native String AVMediaCharacteristicContainsOnlyForcedSubtitles();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicTranscribesSpokenDialogForAccessibility", optional=true)
    public static native String AVMediaCharacteristicTranscribesSpokenDialogForAccessibility();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicDescribesMusicAndSoundForAccessibility", optional=true)
    public static native String AVMediaCharacteristicDescribesMusicAndSoundForAccessibility();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicEasyToRead", optional=true)
    public static native String AVMediaCharacteristicEasyToRead();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicDescribesVideoForAccessibility", optional=true)
    public static native String AVMediaCharacteristicDescribesVideoForAccessibility();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeQuickTimeMovie", optional=true)
    public static native String AVFileTypeQuickTimeMovie();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEG4", optional=true)
    public static native String AVFileTypeMPEG4();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4V", optional=true)
    public static native String AVFileTypeAppleM4V();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4A", optional=true)
    public static native String AVFileTypeAppleM4A();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileType3GPP", optional=true)
    public static native String AVFileType3GPP();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileType3GPP2", optional=true)
    public static native String AVFileType3GPP2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeCoreAudioFormat", optional=true)
    public static native String AVFileTypeCoreAudioFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeWAVE", optional=true)
    public static native String AVFileTypeWAVE();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFF", optional=true)
    public static native String AVFileTypeAIFF();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFC", optional=true)
    public static native String AVFileTypeAIFC();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAMR", optional=true)
    public static native String AVFileTypeAMR();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEGLayer3", optional=true)
    public static native String AVFileTypeMPEGLayer3();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeSunAU", optional=true)
    public static native String AVFileTypeSunAU();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAC3", optional=true)
    public static native String AVFileTypeAC3();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceCommon", optional=true)
    public static native String AVMetadataKeySpaceCommon();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyTitle", optional=true)
    public static native String AVMetadataCommonKeyTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCreator", optional=true)
    public static native String AVMetadataCommonKeyCreator();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySubject", optional=true)
    public static native String AVMetadataCommonKeySubject();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyDescription", optional=true)
    public static native String AVMetadataCommonKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyPublisher", optional=true)
    public static native String AVMetadataCommonKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyContributor", optional=true)
    public static native String AVMetadataCommonKeyContributor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCreationDate", optional=true)
    public static native String AVMetadataCommonKeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLastModifiedDate", optional=true)
    public static native String AVMetadataCommonKeyLastModifiedDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyType", optional=true)
    public static native String AVMetadataCommonKeyType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyFormat", optional=true)
    public static native String AVMetadataCommonKeyFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyIdentifier", optional=true)
    public static native String AVMetadataCommonKeyIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySource", optional=true)
    public static native String AVMetadataCommonKeySource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLanguage", optional=true)
    public static native String AVMetadataCommonKeyLanguage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyRelation", optional=true)
    public static native String AVMetadataCommonKeyRelation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLocation", optional=true)
    public static native String AVMetadataCommonKeyLocation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCopyrights", optional=true)
    public static native String AVMetadataCommonKeyCopyrights();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyAlbumName", optional=true)
    public static native String AVMetadataCommonKeyAlbumName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyAuthor", optional=true)
    public static native String AVMetadataCommonKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyArtist", optional=true)
    public static native String AVMetadataCommonKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyArtwork", optional=true)
    public static native String AVMetadataCommonKeyArtwork();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyMake", optional=true)
    public static native String AVMetadataCommonKeyMake();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyModel", optional=true)
    public static native String AVMetadataCommonKeyModel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySoftware", optional=true)
    public static native String AVMetadataCommonKeySoftware();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatQuickTimeUserData", optional=true)
    public static native String AVMetadataFormatQuickTimeUserData();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeUserData", optional=true)
    public static native String AVMetadataKeySpaceQuickTimeUserData();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAlbum", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArranger", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArtist", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAuthor", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyChapter", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyChapter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComment", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComposer", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCopyright", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCreationDate", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDescription", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDirector", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDisclaimer", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyDisclaimer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyEncodedBy", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyFullName", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyFullName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyGenre", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyHostComputer", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyHostComputer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyInformation", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyKeywords", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyKeywords();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyMake", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyMake();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyModel", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyModel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalArtist", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalFormat", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyOriginalFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalSource", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyOriginalSource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPerformers", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyPerformers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProducer", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPublisher", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProduct", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyProduct();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySoftware", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeySoftware();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrack", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyTrack();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWarning", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyWarning();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWriter", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyWriter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyURLLink", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyURLLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyLocationISO6709", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyLocationISO6709();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrackName", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyTrackName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCredits", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPhonogramRights", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyPhonogramRights();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTaggedCharacteristic", optional=true)
    public static native String AVMetadataQuickTimeUserDataKeyTaggedCharacteristic();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatISOUserData", optional=true)
    public static native String AVMetadataFormatISOUserData();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceISOUserData", optional=true)
    public static native String AVMetadataKeySpaceISOUserData();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataISOUserDataKeyCopyright", optional=true)
    public static native String AVMetadataISOUserDataKeyCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataISOUserDataKeyTaggedCharacteristic", optional=true)
    public static native String AVMetadataISOUserDataKeyTaggedCharacteristic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCopyright", optional=true)
    public static native String AVMetadata3GPUserDataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAuthor", optional=true)
    public static native String AVMetadata3GPUserDataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyPerformer", optional=true)
    public static native String AVMetadata3GPUserDataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyGenre", optional=true)
    public static native String AVMetadata3GPUserDataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyRecordingYear", optional=true)
    public static native String AVMetadata3GPUserDataKeyRecordingYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyLocation", optional=true)
    public static native String AVMetadata3GPUserDataKeyLocation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyTitle", optional=true)
    public static native String AVMetadata3GPUserDataKeyTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyDescription", optional=true)
    public static native String AVMetadata3GPUserDataKeyDescription();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCollection", optional=true)
    public static native String AVMetadata3GPUserDataKeyCollection();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyUserRating", optional=true)
    public static native String AVMetadata3GPUserDataKeyUserRating();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyThumbnail", optional=true)
    public static native String AVMetadata3GPUserDataKeyThumbnail();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAlbumAndTrack", optional=true)
    public static native String AVMetadata3GPUserDataKeyAlbumAndTrack();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyKeywordList", optional=true)
    public static native String AVMetadata3GPUserDataKeyKeywordList();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaClassification", optional=true)
    public static native String AVMetadata3GPUserDataKeyMediaClassification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaRating", optional=true)
    public static native String AVMetadata3GPUserDataKeyMediaRating();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatQuickTimeMetadata", optional=true)
    public static native String AVMetadataFormatQuickTimeMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceQuickTimeMetadata", optional=true)
    public static native String AVMetadataKeySpaceQuickTimeMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAuthor", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComment", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCopyright", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCreationDate", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirector", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDisplayName", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyDisplayName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyInformation", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyKeywords", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyKeywords();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyProducer", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPublisher", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAlbum", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtist", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtwork", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyArtwork();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDescription", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeySoftware", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeySoftware();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyYear", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyGenre", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyiXML", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyiXML();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationISO6709", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationISO6709();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyMake", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyMake();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyModel", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyModel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArranger", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyEncodedBy", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyOriginalArtist", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPerformer", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComposer", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCredits", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPhonogramRights", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyPhonogramRights();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraIdentifier", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCameraIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyTitle", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyTitle();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCollectionUser", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyCollectionUser();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyRatingUser", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyRatingUser();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationName", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationName();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationBody", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationBody();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationNote", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationNote();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationRole", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationRole();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationDate", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyLocationDate();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionFacing", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyDirectionFacing();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionMotion", optional=true)
    public static native String AVMetadataQuickTimeMetadataKeyDirectionMotion();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatiTunesMetadata", optional=true)
    public static native String AVMetadataFormatiTunesMetadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceiTunes", optional=true)
    public static native String AVMetadataKeySpaceiTunes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbum", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtist", optional=true)
    public static native String AVMetadataiTunesMetadataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserComment", optional=true)
    public static native String AVMetadataiTunesMetadataKeyUserComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCoverArt", optional=true)
    public static native String AVMetadataiTunesMetadataKeyCoverArt();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCopyright", optional=true)
    public static native String AVMetadataiTunesMetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyReleaseDate", optional=true)
    public static native String AVMetadataiTunesMetadataKeyReleaseDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodedBy", optional=true)
    public static native String AVMetadataiTunesMetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPredefinedGenre", optional=true)
    public static native String AVMetadataiTunesMetadataKeyPredefinedGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserGenre", optional=true)
    public static native String AVMetadataiTunesMetadataKeyUserGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongName", optional=true)
    public static native String AVMetadataiTunesMetadataKeySongName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackSubTitle", optional=true)
    public static native String AVMetadataiTunesMetadataKeyTrackSubTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodingTool", optional=true)
    public static native String AVMetadataiTunesMetadataKeyEncodingTool();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyComposer", optional=true)
    public static native String AVMetadataiTunesMetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbumArtist", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAlbumArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAccountKind", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAccountKind();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAppleID", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAppleID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtistID", optional=true)
    public static native String AVMetadataiTunesMetadataKeyArtistID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongID", optional=true)
    public static native String AVMetadataiTunesMetadataKeySongID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscCompilation", optional=true)
    public static native String AVMetadataiTunesMetadataKeyDiscCompilation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscNumber", optional=true)
    public static native String AVMetadataiTunesMetadataKeyDiscNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGenreID", optional=true)
    public static native String AVMetadataiTunesMetadataKeyGenreID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGrouping", optional=true)
    public static native String AVMetadataiTunesMetadataKeyGrouping();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPlaylistID", optional=true)
    public static native String AVMetadataiTunesMetadataKeyPlaylistID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyContentRating", optional=true)
    public static native String AVMetadataiTunesMetadataKeyContentRating();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyBeatsPerMin", optional=true)
    public static native String AVMetadataiTunesMetadataKeyBeatsPerMin();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackNumber", optional=true)
    public static native String AVMetadataiTunesMetadataKeyTrackNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtDirector", optional=true)
    public static native String AVMetadataiTunesMetadataKeyArtDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArranger", optional=true)
    public static native String AVMetadataiTunesMetadataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAuthor", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLyrics", optional=true)
    public static native String AVMetadataiTunesMetadataKeyLyrics();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAcknowledgement", optional=true)
    public static native String AVMetadataiTunesMetadataKeyAcknowledgement();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyConductor", optional=true)
    public static native String AVMetadataiTunesMetadataKeyConductor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDescription", optional=true)
    public static native String AVMetadataiTunesMetadataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDirector", optional=true)
    public static native String AVMetadataiTunesMetadataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEQ", optional=true)
    public static native String AVMetadataiTunesMetadataKeyEQ();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLinerNotes", optional=true)
    public static native String AVMetadataiTunesMetadataKeyLinerNotes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyRecordCompany", optional=true)
    public static native String AVMetadataiTunesMetadataKeyRecordCompany();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOriginalArtist", optional=true)
    public static native String AVMetadataiTunesMetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPhonogramRights", optional=true)
    public static native String AVMetadataiTunesMetadataKeyPhonogramRights();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyProducer", optional=true)
    public static native String AVMetadataiTunesMetadataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPerformer", optional=true)
    public static native String AVMetadataiTunesMetadataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPublisher", optional=true)
    public static native String AVMetadataiTunesMetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoundEngineer", optional=true)
    public static native String AVMetadataiTunesMetadataKeySoundEngineer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoloist", optional=true)
    public static native String AVMetadataiTunesMetadataKeySoloist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCredits", optional=true)
    public static native String AVMetadataiTunesMetadataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyThanks", optional=true)
    public static native String AVMetadataiTunesMetadataKeyThanks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOnlineExtras", optional=true)
    public static native String AVMetadataiTunesMetadataKeyOnlineExtras();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyExecProducer", optional=true)
    public static native String AVMetadataiTunesMetadataKeyExecProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatID3Metadata", optional=true)
    public static native String AVMetadataFormatID3Metadata();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceID3", optional=true)
    public static native String AVMetadataKeySpaceID3();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioEncryption", optional=true)
    public static native String AVMetadataID3MetadataKeyAudioEncryption();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAttachedPicture", optional=true)
    public static native String AVMetadataID3MetadataKeyAttachedPicture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioSeekPointIndex", optional=true)
    public static native String AVMetadataID3MetadataKeyAudioSeekPointIndex();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComments", optional=true)
    public static native String AVMetadataID3MetadataKeyComments();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommerical", optional=true)
    public static native String AVMetadataID3MetadataKeyCommerical();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncryption", optional=true)
    public static native String AVMetadataID3MetadataKeyEncryption();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization", optional=true)
    public static native String AVMetadataID3MetadataKeyEqualization();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization2", optional=true)
    public static native String AVMetadataID3MetadataKeyEqualization2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEventTimingCodes", optional=true)
    public static native String AVMetadataID3MetadataKeyEventTimingCodes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGeneralEncapsulatedObject", optional=true)
    public static native String AVMetadataID3MetadataKeyGeneralEncapsulatedObject();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGroupIdentifier", optional=true)
    public static native String AVMetadataID3MetadataKeyGroupIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v23", optional=true)
    public static native String AVMetadataID3MetadataKeyInvolvedPeopleList_v23();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLink", optional=true)
    public static native String AVMetadataID3MetadataKeyLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicCDIdentifier", optional=true)
    public static native String AVMetadataID3MetadataKeyMusicCDIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMPEGLocationLookupTable", optional=true)
    public static native String AVMetadataID3MetadataKeyMPEGLocationLookupTable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOwnership", optional=true)
    public static native String AVMetadataID3MetadataKeyOwnership();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPrivate", optional=true)
    public static native String AVMetadataID3MetadataKeyPrivate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlayCounter", optional=true)
    public static native String AVMetadataID3MetadataKeyPlayCounter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPopularimeter", optional=true)
    public static native String AVMetadataID3MetadataKeyPopularimeter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPositionSynchronization", optional=true)
    public static native String AVMetadataID3MetadataKeyPositionSynchronization();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecommendedBufferSize", optional=true)
    public static native String AVMetadataID3MetadataKeyRecommendedBufferSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment", optional=true)
    public static native String AVMetadataID3MetadataKeyRelativeVolumeAdjustment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment2", optional=true)
    public static native String AVMetadataID3MetadataKeyRelativeVolumeAdjustment2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReverb", optional=true)
    public static native String AVMetadataID3MetadataKeyReverb();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySeek", optional=true)
    public static native String AVMetadataID3MetadataKeySeek();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySignature", optional=true)
    public static native String AVMetadataID3MetadataKeySignature();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedLyric", optional=true)
    public static native String AVMetadataID3MetadataKeySynchronizedLyric();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedTempoCodes", optional=true)
    public static native String AVMetadataID3MetadataKeySynchronizedTempoCodes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumTitle", optional=true)
    public static native String AVMetadataID3MetadataKeyAlbumTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBeatsPerMinute", optional=true)
    public static native String AVMetadataID3MetadataKeyBeatsPerMinute();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComposer", optional=true)
    public static native String AVMetadataID3MetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentType", optional=true)
    public static native String AVMetadataID3MetadataKeyContentType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyright", optional=true)
    public static native String AVMetadataID3MetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyDate", optional=true)
    public static native String AVMetadataID3MetadataKeyDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodingTime", optional=true)
    public static native String AVMetadataID3MetadataKeyEncodingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlaylistDelay", optional=true)
    public static native String AVMetadataID3MetadataKeyPlaylistDelay();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseTime", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalReleaseTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingTime", optional=true)
    public static native String AVMetadataID3MetadataKeyRecordingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReleaseTime", optional=true)
    public static native String AVMetadataID3MetadataKeyReleaseTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTaggingTime", optional=true)
    public static native String AVMetadataID3MetadataKeyTaggingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedBy", optional=true)
    public static native String AVMetadataID3MetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLyricist", optional=true)
    public static native String AVMetadataID3MetadataKeyLyricist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileType", optional=true)
    public static native String AVMetadataID3MetadataKeyFileType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTime", optional=true)
    public static native String AVMetadataID3MetadataKeyTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v24", optional=true)
    public static native String AVMetadataID3MetadataKeyInvolvedPeopleList_v24();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentGroupDescription", optional=true)
    public static native String AVMetadataID3MetadataKeyContentGroupDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleDescription", optional=true)
    public static native String AVMetadataID3MetadataKeyTitleDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySubTitle", optional=true)
    public static native String AVMetadataID3MetadataKeySubTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInitialKey", optional=true)
    public static native String AVMetadataID3MetadataKeyInitialKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLanguage", optional=true)
    public static native String AVMetadataID3MetadataKeyLanguage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLength", optional=true)
    public static native String AVMetadataID3MetadataKeyLength();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicianCreditsList", optional=true)
    public static native String AVMetadataID3MetadataKeyMusicianCreditsList();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMediaType", optional=true)
    public static native String AVMetadataID3MetadataKeyMediaType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMood", optional=true)
    public static native String AVMetadataID3MetadataKeyMood();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalAlbumTitle", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalAlbumTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalFilename", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalFilename();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalLyricist", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalLyricist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalArtist", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseYear", optional=true)
    public static native String AVMetadataID3MetadataKeyOriginalReleaseYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileOwner", optional=true)
    public static native String AVMetadataID3MetadataKeyFileOwner();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLeadPerformer", optional=true)
    public static native String AVMetadataID3MetadataKeyLeadPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBand", optional=true)
    public static native String AVMetadataID3MetadataKeyBand();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyConductor", optional=true)
    public static native String AVMetadataID3MetadataKeyConductor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyModifiedBy", optional=true)
    public static native String AVMetadataID3MetadataKeyModifiedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPartOfASet", optional=true)
    public static native String AVMetadataID3MetadataKeyPartOfASet();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyProducedNotice", optional=true)
    public static native String AVMetadataID3MetadataKeyProducedNotice();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPublisher", optional=true)
    public static native String AVMetadataID3MetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTrackNumber", optional=true)
    public static native String AVMetadataID3MetadataKeyTrackNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingDates", optional=true)
    public static native String AVMetadataID3MetadataKeyRecordingDates();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationName", optional=true)
    public static native String AVMetadataID3MetadataKeyInternetRadioStationName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationOwner", optional=true)
    public static native String AVMetadataID3MetadataKeyInternetRadioStationOwner();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySize", optional=true)
    public static native String AVMetadataID3MetadataKeySize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumSortOrder", optional=true)
    public static native String AVMetadataID3MetadataKeyAlbumSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPerformerSortOrder", optional=true)
    public static native String AVMetadataID3MetadataKeyPerformerSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleSortOrder", optional=true)
    public static native String AVMetadataID3MetadataKeyTitleSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternationalStandardRecordingCode", optional=true)
    public static native String AVMetadataID3MetadataKeyInternationalStandardRecordingCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedWith", optional=true)
    public static native String AVMetadataID3MetadataKeyEncodedWith();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySetSubtitle", optional=true)
    public static native String AVMetadataID3MetadataKeySetSubtitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyYear", optional=true)
    public static native String AVMetadataID3MetadataKeyYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserText", optional=true)
    public static native String AVMetadataID3MetadataKeyUserText();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUniqueFileIdentifier", optional=true)
    public static native String AVMetadataID3MetadataKeyUniqueFileIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTermsOfUse", optional=true)
    public static native String AVMetadataID3MetadataKeyTermsOfUse();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUnsynchronizedLyric", optional=true)
    public static native String AVMetadataID3MetadataKeyUnsynchronizedLyric();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommercialInformation", optional=true)
    public static native String AVMetadataID3MetadataKeyCommercialInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyrightInformation", optional=true)
    public static native String AVMetadataID3MetadataKeyCopyrightInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioFileWebpage", optional=true)
    public static native String AVMetadataID3MetadataKeyOfficialAudioFileWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialArtistWebpage", optional=true)
    public static native String AVMetadataID3MetadataKeyOfficialArtistWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioSourceWebpage", optional=true)
    public static native String AVMetadataID3MetadataKeyOfficialAudioSourceWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage", optional=true)
    public static native String AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPayment", optional=true)
    public static native String AVMetadataID3MetadataKeyPayment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialPublisherWebpage", optional=true)
    public static native String AVMetadataID3MetadataKeyOfficialPublisherWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserURL", optional=true)
    public static native String AVMetadataID3MetadataKeyUserURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataKeySpaceIcy", optional=true)
    public static native String AVMetadataKeySpaceIcy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIcyMetadataKeyStreamTitle", optional=true)
    public static native String AVMetadataIcyMetadataKeyStreamTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIcyMetadataKeyStreamURL", optional=true)
    public static native String AVMetadataIcyMetadataKeyStreamURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataFormatHLSMetadata", optional=true)
    public static native String AVMetadataFormatHLSMetadata();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataExtraAttributeValueURIKey", optional=true)
    public static native String AVMetadataExtraAttributeValueURIKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataExtraAttributeBaseURIKey", optional=true)
    public static native String AVMetadataExtraAttributeBaseURIKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierTitle", optional=true)
    public static native String AVMetadataCommonIdentifierTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCreator", optional=true)
    public static native String AVMetadataCommonIdentifierCreator();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSubject", optional=true)
    public static native String AVMetadataCommonIdentifierSubject();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierDescription", optional=true)
    public static native String AVMetadataCommonIdentifierDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierPublisher", optional=true)
    public static native String AVMetadataCommonIdentifierPublisher();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierContributor", optional=true)
    public static native String AVMetadataCommonIdentifierContributor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCreationDate", optional=true)
    public static native String AVMetadataCommonIdentifierCreationDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLastModifiedDate", optional=true)
    public static native String AVMetadataCommonIdentifierLastModifiedDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierType", optional=true)
    public static native String AVMetadataCommonIdentifierType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierFormat", optional=true)
    public static native String AVMetadataCommonIdentifierFormat();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAssetIdentifier", optional=true)
    public static native String AVMetadataCommonIdentifierAssetIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSource", optional=true)
    public static native String AVMetadataCommonIdentifierSource();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLanguage", optional=true)
    public static native String AVMetadataCommonIdentifierLanguage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierRelation", optional=true)
    public static native String AVMetadataCommonIdentifierRelation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLocation", optional=true)
    public static native String AVMetadataCommonIdentifierLocation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCopyrights", optional=true)
    public static native String AVMetadataCommonIdentifierCopyrights();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAlbumName", optional=true)
    public static native String AVMetadataCommonIdentifierAlbumName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAuthor", optional=true)
    public static native String AVMetadataCommonIdentifierAuthor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierArtist", optional=true)
    public static native String AVMetadataCommonIdentifierArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierArtwork", optional=true)
    public static native String AVMetadataCommonIdentifierArtwork();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierMake", optional=true)
    public static native String AVMetadataCommonIdentifierMake();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierModel", optional=true)
    public static native String AVMetadataCommonIdentifierModel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSoftware", optional=true)
    public static native String AVMetadataCommonIdentifierSoftware();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAlbum", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataAlbum();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArranger", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataArranger();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArtist", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAuthor", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataAuthor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataChapter", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataChapter();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComment", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataComment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComposer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataComposer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCopyright", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCreationDate", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataCreationDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDescription", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDirector", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataDirector();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDisclaimer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataDisclaimer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataEncodedBy", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataEncodedBy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataFullName", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataFullName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataGenre", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataHostComputer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataHostComputer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataInformation", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataInformation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataKeywords", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataKeywords();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataMake", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataMake();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataModel", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataModel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalArtist", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataOriginalArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalFormat", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataOriginalFormat();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalSource", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataOriginalSource();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPerformers", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataPerformers();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProducer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataProducer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPublisher", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataPublisher();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProduct", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataProduct();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSoftware", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataSoftware();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSpecialPlaybackRequirements", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataSpecialPlaybackRequirements();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrack", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataTrack();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWarning", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataWarning();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWriter", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataWriter();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataURLLink", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataURLLink();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataLocationISO6709", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataLocationISO6709();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrackName", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataTrackName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCredits", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataCredits();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPhonogramRights", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataPhonogramRights();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTaggedCharacteristic", optional=true)
    public static native String AVMetadataIdentifierQuickTimeUserDataTaggedCharacteristic();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierISOUserDataCopyright", optional=true)
    public static native String AVMetadataIdentifierISOUserDataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierISOUserDataTaggedCharacteristic", optional=true)
    public static native String AVMetadataIdentifierISOUserDataTaggedCharacteristic();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataCopyright", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataAuthor", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataAuthor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataPerformer", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataPerformer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataGenre", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataRecordingYear", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataRecordingYear();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataLocation", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataLocation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataTitle", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataDescription", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataCollection", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataCollection();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataUserRating", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataUserRating();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataThumbnail", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataThumbnail();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataAlbumAndTrack", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataAlbumAndTrack();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataKeywordList", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataKeywordList();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataMediaClassification", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataMediaClassification();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifier3GPUserDataMediaRating", optional=true)
    public static native String AVMetadataIdentifier3GPUserDataMediaRating();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAuthor", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataAuthor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComment", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataComment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCopyright", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCreationDate", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCreationDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirector", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataDirector();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDisplayName", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataDisplayName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataInformation", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataInformation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataKeywords", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataKeywords();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataProducer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataProducer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPublisher", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataPublisher();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAlbum", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataAlbum();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtist", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtwork", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataArtwork();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDescription", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataSoftware", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataSoftware();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataYear", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataYear();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataGenre", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataiXML", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataiXML();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationISO6709", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationISO6709();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataMake", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataMake();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataModel", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataModel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArranger", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataArranger();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataEncodedBy", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataEncodedBy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataOriginalArtist", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataOriginalArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPerformer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataPerformer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComposer", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataComposer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCredits", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCredits();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPhonogramRights", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataPhonogramRights();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraIdentifier", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCameraIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraFrameReadoutTime", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCameraFrameReadoutTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataTitle", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCollectionUser", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataCollectionUser();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataRatingUser", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataRatingUser();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationName", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationBody", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationBody();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationNote", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationNote();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationRole", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationRole();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationDate", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataLocationDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionFacing", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataDirectionFacing();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionMotion", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataDirectionMotion();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPreferredAffineTransform", optional=true)
    public static native String AVMetadataIdentifierQuickTimeMetadataPreferredAffineTransform();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbum", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAlbum();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtist", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserComment", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataUserComment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCoverArt", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataCoverArt();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCopyright", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataReleaseDate", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataReleaseDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodedBy", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataEncodedBy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPredefinedGenre", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataPredefinedGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataUserGenre", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataUserGenre();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongName", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataSongName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackSubTitle", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataTrackSubTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEncodingTool", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataEncodingTool();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataComposer", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataComposer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAlbumArtist", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAlbumArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAccountKind", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAccountKind();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAppleID", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAppleID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtistID", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataArtistID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSongID", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataSongID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscCompilation", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataDiscCompilation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDiscNumber", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataDiscNumber();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGenreID", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataGenreID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataGrouping", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataGrouping();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPlaylistID", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataPlaylistID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataContentRating", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataContentRating();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataBeatsPerMin", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataBeatsPerMin();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataTrackNumber", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataTrackNumber();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArtDirector", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataArtDirector();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataArranger", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataArranger();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAuthor", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAuthor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLyrics", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataLyrics();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataAcknowledgement", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataAcknowledgement();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataConductor", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataConductor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDescription", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataDirector", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataDirector();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataEQ", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataEQ();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataLinerNotes", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataLinerNotes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataRecordCompany", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataRecordCompany();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOriginalArtist", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataOriginalArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPhonogramRights", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataPhonogramRights();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataProducer", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataProducer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPerformer", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataPerformer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataPublisher", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataPublisher();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoundEngineer", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataSoundEngineer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataSoloist", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataSoloist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataCredits", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataCredits();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataThanks", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataThanks();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataOnlineExtras", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataOnlineExtras();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifieriTunesMetadataExecProducer", optional=true)
    public static native String AVMetadataIdentifieriTunesMetadataExecProducer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioEncryption", optional=true)
    public static native String AVMetadataIdentifierID3MetadataAudioEncryption();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAttachedPicture", optional=true)
    public static native String AVMetadataIdentifierID3MetadataAttachedPicture();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioSeekPointIndex", optional=true)
    public static native String AVMetadataIdentifierID3MetadataAudioSeekPointIndex();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComments", optional=true)
    public static native String AVMetadataIdentifierID3MetadataComments();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommerical", optional=true)
    public static native String AVMetadataIdentifierID3MetadataCommerical();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncryption", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEncryption();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEqualization();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization2", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEqualization2();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEventTimingCodes", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEventTimingCodes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGeneralEncapsulatedObject", optional=true)
    public static native String AVMetadataIdentifierID3MetadataGeneralEncapsulatedObject();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGroupIdentifier", optional=true)
    public static native String AVMetadataIdentifierID3MetadataGroupIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v23", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInvolvedPeopleList_v23();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLink", optional=true)
    public static native String AVMetadataIdentifierID3MetadataLink();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicCDIdentifier", optional=true)
    public static native String AVMetadataIdentifierID3MetadataMusicCDIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMPEGLocationLookupTable", optional=true)
    public static native String AVMetadataIdentifierID3MetadataMPEGLocationLookupTable();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOwnership", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOwnership();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPrivate", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPrivate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlayCounter", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPlayCounter();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPopularimeter", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPopularimeter();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPositionSynchronization", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPositionSynchronization();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecommendedBufferSize", optional=true)
    public static native String AVMetadataIdentifierID3MetadataRecommendedBufferSize();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment", optional=true)
    public static native String AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment2", optional=true)
    public static native String AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment2();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReverb", optional=true)
    public static native String AVMetadataIdentifierID3MetadataReverb();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSeek", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSeek();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSignature", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSignature();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedLyric", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSynchronizedLyric();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedTempoCodes", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSynchronizedTempoCodes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumTitle", optional=true)
    public static native String AVMetadataIdentifierID3MetadataAlbumTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBeatsPerMinute", optional=true)
    public static native String AVMetadataIdentifierID3MetadataBeatsPerMinute();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComposer", optional=true)
    public static native String AVMetadataIdentifierID3MetadataComposer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentType", optional=true)
    public static native String AVMetadataIdentifierID3MetadataContentType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyright", optional=true)
    public static native String AVMetadataIdentifierID3MetadataCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataDate", optional=true)
    public static native String AVMetadataIdentifierID3MetadataDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodingTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEncodingTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlaylistDelay", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPlaylistDelay();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalReleaseTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataRecordingTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReleaseTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataReleaseTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTaggingTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTaggingTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedBy", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEncodedBy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLyricist", optional=true)
    public static native String AVMetadataIdentifierID3MetadataLyricist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileType", optional=true)
    public static native String AVMetadataIdentifierID3MetadataFileType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTime", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v24", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInvolvedPeopleList_v24();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentGroupDescription", optional=true)
    public static native String AVMetadataIdentifierID3MetadataContentGroupDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleDescription", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTitleDescription();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSubTitle", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSubTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInitialKey", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInitialKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLanguage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataLanguage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLength", optional=true)
    public static native String AVMetadataIdentifierID3MetadataLength();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicianCreditsList", optional=true)
    public static native String AVMetadataIdentifierID3MetadataMusicianCreditsList();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMediaType", optional=true)
    public static native String AVMetadataIdentifierID3MetadataMediaType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMood", optional=true)
    public static native String AVMetadataIdentifierID3MetadataMood();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalAlbumTitle", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalAlbumTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalFilename", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalFilename();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalLyricist", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalLyricist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalArtist", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalArtist();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseYear", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOriginalReleaseYear();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileOwner", optional=true)
    public static native String AVMetadataIdentifierID3MetadataFileOwner();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLeadPerformer", optional=true)
    public static native String AVMetadataIdentifierID3MetadataLeadPerformer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBand", optional=true)
    public static native String AVMetadataIdentifierID3MetadataBand();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataConductor", optional=true)
    public static native String AVMetadataIdentifierID3MetadataConductor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataModifiedBy", optional=true)
    public static native String AVMetadataIdentifierID3MetadataModifiedBy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPartOfASet", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPartOfASet();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataProducedNotice", optional=true)
    public static native String AVMetadataIdentifierID3MetadataProducedNotice();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPublisher", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPublisher();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTrackNumber", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTrackNumber();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingDates", optional=true)
    public static native String AVMetadataIdentifierID3MetadataRecordingDates();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationName", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInternetRadioStationName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationOwner", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInternetRadioStationOwner();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSize", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSize();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumSortOrder", optional=true)
    public static native String AVMetadataIdentifierID3MetadataAlbumSortOrder();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPerformerSortOrder", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPerformerSortOrder();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleSortOrder", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTitleSortOrder();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternationalStandardRecordingCode", optional=true)
    public static native String AVMetadataIdentifierID3MetadataInternationalStandardRecordingCode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedWith", optional=true)
    public static native String AVMetadataIdentifierID3MetadataEncodedWith();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSetSubtitle", optional=true)
    public static native String AVMetadataIdentifierID3MetadataSetSubtitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataYear", optional=true)
    public static native String AVMetadataIdentifierID3MetadataYear();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserText", optional=true)
    public static native String AVMetadataIdentifierID3MetadataUserText();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUniqueFileIdentifier", optional=true)
    public static native String AVMetadataIdentifierID3MetadataUniqueFileIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTermsOfUse", optional=true)
    public static native String AVMetadataIdentifierID3MetadataTermsOfUse();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUnsynchronizedLyric", optional=true)
    public static native String AVMetadataIdentifierID3MetadataUnsynchronizedLyric();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommercialInformation", optional=true)
    public static native String AVMetadataIdentifierID3MetadataCommercialInformation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyrightInformation", optional=true)
    public static native String AVMetadataIdentifierID3MetadataCopyrightInformation();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioFileWebpage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOfficialAudioFileWebpage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialArtistWebpage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOfficialArtistWebpage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioSourceWebpage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOfficialAudioSourceWebpage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialInternetRadioStationHomepage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOfficialInternetRadioStationHomepage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPayment", optional=true)
    public static native String AVMetadataIdentifierID3MetadataPayment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialPublisherWebpage", optional=true)
    public static native String AVMetadataIdentifierID3MetadataOfficialPublisherWebpage();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserURL", optional=true)
    public static native String AVMetadataIdentifierID3MetadataUserURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierIcyMetadataStreamTitle", optional=true)
    public static native String AVMetadataIdentifierIcyMetadataStreamTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierIcyMetadataStreamURL", optional=true)
    public static native String AVMetadataIdentifierIcyMetadataStreamURL();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeFace", optional=true)
    public static native String AVMetadataObjectTypeFace();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeUPCECode", optional=true)
    public static native String AVMetadataObjectTypeUPCECode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode39Code", optional=true)
    public static native String AVMetadataObjectTypeCode39Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode39Mod43Code", optional=true)
    public static native String AVMetadataObjectTypeCode39Mod43Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeEAN13Code", optional=true)
    public static native String AVMetadataObjectTypeEAN13Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeEAN8Code", optional=true)
    public static native String AVMetadataObjectTypeEAN8Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode93Code", optional=true)
    public static native String AVMetadataObjectTypeCode93Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode128Code", optional=true)
    public static native String AVMetadataObjectTypeCode128Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypePDF417Code", optional=true)
    public static native String AVMetadataObjectTypePDF417Code();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeQRCode", optional=true)
    public static native String AVMetadataObjectTypeQRCode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeAztecCode", optional=true)
    public static native String AVMetadataObjectTypeAztecCode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeInterleaved2of5Code", optional=true)
    public static native String AVMetadataObjectTypeInterleaved2of5Code();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeITF14Code", optional=true)
    public static native String AVMetadataObjectTypeITF14Code();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeDataMatrixCode", optional=true)
    public static native String AVMetadataObjectTypeDataMatrixCode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset640x480", optional=true)
    public static native String AVOutputSettingsPreset640x480();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset960x540", optional=true)
    public static native String AVOutputSettingsPreset960x540();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset1280x720", optional=true)
    public static native String AVOutputSettingsPreset1280x720();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVOutputSettingsPreset1920x1080", optional=true)
    public static native String AVOutputSettingsPreset1920x1080();
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
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemLegibleOutputTextStylingResolutionDefault", optional=true)
    public static native String AVPlayerItemLegibleOutputTextStylingResolutionDefault();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVPlayerItemLegibleOutputTextStylingResolutionSourceAndRulesOnly", optional=true)
    public static native String AVPlayerItemLegibleOutputTextStylingResolutionSourceAndRulesOnly();
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
    @GlobalValue(symbol="AVVideoCodecKey", optional=true)
    public static native String AVVideoCodecKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCodecH264", optional=true)
    public static native String AVVideoCodecH264();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCodecJPEG", optional=true)
    public static native String AVVideoCodecJPEG();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoWidthKey", optional=true)
    public static native String AVVideoWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoHeightKey", optional=true)
    public static native String AVVideoHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioKey", optional=true)
    public static native String AVVideoPixelAspectRatioKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioHorizontalSpacingKey", optional=true)
    public static native String AVVideoPixelAspectRatioHorizontalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioVerticalSpacingKey", optional=true)
    public static native String AVVideoPixelAspectRatioVerticalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureKey", optional=true)
    public static native String AVVideoCleanApertureKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureWidthKey", optional=true)
    public static native String AVVideoCleanApertureWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureHeightKey", optional=true)
    public static native String AVVideoCleanApertureHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureHorizontalOffsetKey", optional=true)
    public static native String AVVideoCleanApertureHorizontalOffsetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureVerticalOffsetKey", optional=true)
    public static native String AVVideoCleanApertureVerticalOffsetKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeKey", optional=true)
    public static native String AVVideoScalingModeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeFit", optional=true)
    public static native String AVVideoScalingModeFit();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeResize", optional=true)
    public static native String AVVideoScalingModeResize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeResizeAspect", optional=true)
    public static native String AVVideoScalingModeResizeAspect();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeResizeAspectFill", optional=true)
    public static native String AVVideoScalingModeResizeAspectFill();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCompressionPropertiesKey", optional=true)
    public static native String AVVideoCompressionPropertiesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoAverageBitRateKey", optional=true)
    public static native String AVVideoAverageBitRateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoQualityKey", optional=true)
    public static native String AVVideoQualityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalKey", optional=true)
    public static native String AVVideoMaxKeyFrameIntervalKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalDurationKey", optional=true)
    public static native String AVVideoMaxKeyFrameIntervalDurationKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoAllowFrameReorderingKey", optional=true)
    public static native String AVVideoAllowFrameReorderingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelKey", optional=true)
    public static native String AVVideoProfileLevelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline30", optional=true)
    public static native String AVVideoProfileLevelH264Baseline30();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline31", optional=true)
    public static native String AVVideoProfileLevelH264Baseline31();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Baseline41", optional=true)
    public static native String AVVideoProfileLevelH264Baseline41();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264BaselineAutoLevel", optional=true)
    public static native String AVVideoProfileLevelH264BaselineAutoLevel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main30", optional=true)
    public static native String AVVideoProfileLevelH264Main30();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main31", optional=true)
    public static native String AVVideoProfileLevelH264Main31();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main32", optional=true)
    public static native String AVVideoProfileLevelH264Main32();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264Main41", optional=true)
    public static native String AVVideoProfileLevelH264Main41();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264MainAutoLevel", optional=true)
    public static native String AVVideoProfileLevelH264MainAutoLevel();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264High40", optional=true)
    public static native String AVVideoProfileLevelH264High40();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264High41", optional=true)
    public static native String AVVideoProfileLevelH264High41();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelH264HighAutoLevel", optional=true)
    public static native String AVVideoProfileLevelH264HighAutoLevel();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoH264EntropyModeKey", optional=true)
    public static native String AVVideoH264EntropyModeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoH264EntropyModeCAVLC", optional=true)
    public static native String AVVideoH264EntropyModeCAVLC();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoH264EntropyModeCABAC", optional=true)
    public static native String AVVideoH264EntropyModeCABAC();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoExpectedSourceFrameRateKey", optional=true)
    public static native String AVVideoExpectedSourceFrameRateKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoAverageNonDroppableFrameRateKey", optional=true)
    public static native String AVVideoAverageNonDroppableFrameRateKey();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="AVMakeRectWithAspectRatioInsideRect", optional=true)
    public static native @ByVal CGRect function__AVMakeRectWithAspectRatioInsideRect(@ByVal CGSize aspectRatio, @ByVal CGRect boundingRect);
    /*</methods>*/
}
