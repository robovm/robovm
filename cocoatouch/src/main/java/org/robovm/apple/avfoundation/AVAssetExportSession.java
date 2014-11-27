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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetExportSession/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetExportSessionPtr extends Ptr<AVAssetExportSession, AVAssetExportSessionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetExportSession.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetExportSession() {}
    protected AVAssetExportSession(SkipInit skipInit) { super(skipInit); }
    public AVAssetExportSession(AVAsset asset, String presetName) { super((SkipInit) null); initObject(init(asset, presetName)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "presetName")
    public native String getPresetName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "asset")
    public native AVAsset getAsset();
    @Property(selector = "supportedFileTypes")
    public native NSArray<NSString> getSupportedFileTypes();
    @Property(selector = "outputFileType")
    public native NSString getOutputFileType();
    @Property(selector = "setOutputFileType:")
    public native void setOutputFileType(NSString v);
    @Property(selector = "outputURL")
    public native NSURL getOutputURL();
    @Property(selector = "setOutputURL:")
    public native void setOutputURL(NSURL v);
    @Property(selector = "status")
    public native AVAssetExportSessionStatus getStatus();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "progress")
    public native float getProgress();
    @Property(selector = "maxDuration")
    public native @ByVal CMTime getMaxDuration();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "estimatedOutputFileLength")
    public native long getEstimatedOutputFileLength();
    @Property(selector = "timeRange")
    public native @ByVal CMTimeRange getTimeRange();
    @Property(selector = "setTimeRange:")
    public native void setTimeRange(@ByVal CMTimeRange v);
    @Property(selector = "metadata")
    public native NSArray<AVMetadataItem> getMetadata();
    @Property(selector = "setMetadata:")
    public native void setMetadata(NSArray<AVMetadataItem> v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "metadataItemFilter")
    public native AVMetadataItemFilter getMetadataItemFilter();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setMetadataItemFilter:")
    public native void setMetadataItemFilter(AVMetadataItemFilter v);
    @Property(selector = "fileLengthLimit")
    public native long getFileLengthLimit();
    @Property(selector = "setFileLengthLimit:")
    public native void setFileLengthLimit(long v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "audioTimePitchAlgorithm")
    public native String getAudioTimePitchAlgorithm();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAudioTimePitchAlgorithm:")
    public native void setAudioTimePitchAlgorithm(String v);
    @Property(selector = "audioMix")
    public native AVAudioMix getAudioMix();
    @Property(selector = "setAudioMix:")
    public native void setAudioMix(AVAudioMix v);
    @Property(selector = "videoComposition")
    public native AVVideoComposition getVideoComposition();
    @Property(selector = "setVideoComposition:")
    public native void setVideoComposition(AVVideoComposition v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "customVideoCompositor")
    public native AVVideoCompositing getCustomVideoCompositor();
    @Property(selector = "shouldOptimizeForNetworkUse")
    public native boolean isShouldOptimizeForNetworkUse();
    @Property(selector = "setShouldOptimizeForNetworkUse:")
    public native void setShouldOptimizeForNetworkUse(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "canPerformMultiplePassesOverSourceMediaData")
    public native boolean isCanPerformMultiplePassesOverSourceMediaData();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCanPerformMultiplePassesOverSourceMediaData:")
    public native void setCanPerformMultiplePassesOverSourceMediaData(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "directoryForTemporaryFiles")
    public native NSURL getDirectoryForTemporaryFiles();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setDirectoryForTemporaryFiles:")
    public native void setDirectoryForTemporaryFiles(NSURL v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAsset:presetName:")
    protected native @Pointer long init(AVAsset asset, String presetName);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "determineCompatibleFileTypesWithCompletionHandler:")
    public native void determineCompatibleFileTypes(@Block VoidBlock1<NSArray<NSString>> handler);
    @Method(selector = "exportAsynchronouslyWithCompletionHandler:")
    public native void exportAsynchronously(@Block Runnable handler);
    @Method(selector = "cancelExport")
    public native void cancelExport();
    @Method(selector = "allExportPresets")
    public static native NSArray<NSString> getAllExportPresets();
    @Method(selector = "exportPresetsCompatibleWithAsset:")
    public static native NSArray<NSString> getCompatibleExportPresets(AVAsset asset);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "determineCompatibilityOfExportPreset:withAsset:outputFileType:completionHandler:")
    public static native void determineCompatibilityOfExportPreset(String presetName, AVAsset asset, String outputFileType, @Block VoidBooleanBlock handler);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Method(selector = "exportSessionWithAsset:presetName:")
    public static native AVAssetExportSession getExportSession(AVAsset asset, String presetName);
    /*</methods>*/
}
