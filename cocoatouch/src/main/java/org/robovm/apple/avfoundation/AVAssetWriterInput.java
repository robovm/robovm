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
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetWriterInput/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetWriterInputPtr extends Ptr<AVAssetWriterInput, AVAssetWriterInputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetWriterInput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetWriterInput() {}
    protected AVAssetWriterInput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public AVAssetWriterInput(AVMediaType mediaType, AVAudioSettings outputSettings) {
        super((SkipInit)null);
        initObject(init(mediaType, outputSettings.getDictionary()));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAssetWriterInput(AVMediaType mediaType, AVAudioSettings outputSettings, CMFormatDescription sourceFormatHint) {
        super((SkipInit)null);
        initObject(init(mediaType, outputSettings.getDictionary(), sourceFormatHint));
    }
    public AVAssetWriterInput(AVMediaType mediaType, AVVideoSettings outputSettings) {
        super((SkipInit)null);
        initObject(init(mediaType, outputSettings.getDictionary()));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public AVAssetWriterInput(AVMediaType mediaType, AVVideoSettings outputSettings, CMFormatDescription sourceFormatHint) {
        super((SkipInit)null);
        initObject(init(mediaType, outputSettings.getDictionary(), sourceFormatHint));
    }
    
    
    public AVAudioSettings getOutputAudioSettings() {
        return new AVAudioSettings(getOutputSettings());
    }
    public AVVideoSettings getOutputVideoSettings() {
        return new AVVideoSettings(getOutputSettings());
    }
    /*<properties>*/
    @Property(selector = "mediaType")
    public native AVMediaType getMediaType();
    @Property(selector = "outputSettings")
    protected native NSDictionary<NSString, NSObject> getOutputSettings();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "sourceFormatHint")
    public native CMFormatDescription getSourceFormatHint();
    @Property(selector = "metadata")
    public native NSArray<AVMetadataItem> getMetadata();
    @Property(selector = "setMetadata:")
    public native void setMetadata(NSArray<AVMetadataItem> v);
    @Property(selector = "isReadyForMoreMediaData")
    public native boolean isReadyForMoreMediaData();
    @Property(selector = "expectsMediaDataInRealTime")
    public native boolean expectsMediaDataInRealTime();
    @Property(selector = "setExpectsMediaDataInRealTime:")
    public native void setExpectsMediaDataInRealTime(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "languageCode")
    public native String getLanguageCode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setLanguageCode:")
    public native void setLanguageCode(String v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "extendedLanguageTag")
    public native String getExtendedLanguageTag();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setExtendedLanguageTag:")
    public native void setExtendedLanguageTag(String v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "naturalSize")
    public native @ByVal CGSize getNaturalSize();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setNaturalSize:")
    public native void setNaturalSize(@ByVal CGSize v);
    @Property(selector = "transform")
    public native @ByVal CGAffineTransform getTransform();
    @Property(selector = "setTransform:")
    public native void setTransform(@ByVal CGAffineTransform v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredVolume")
    public native float getPreferredVolume();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPreferredVolume:")
    public native void setPreferredVolume(float v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "marksOutputTrackAsEnabled")
    public native boolean marksOutputTrackAsEnabled();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setMarksOutputTrackAsEnabled:")
    public native void setMarksOutputTrackAsEnabled(boolean v);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "mediaTimeScale")
    public native int getMediaTimeScale();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "setMediaTimeScale:")
    public native void setMediaTimeScale(int v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "preferredMediaChunkDuration")
    public native @ByVal CMTime getPreferredMediaChunkDuration();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPreferredMediaChunkDuration:")
    public native void setPreferredMediaChunkDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "preferredMediaChunkAlignment")
    public native @MachineSizedSInt long getPreferredMediaChunkAlignment();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPreferredMediaChunkAlignment:")
    public native void setPreferredMediaChunkAlignment(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "sampleReferenceBaseURL")
    public native NSURL getSampleReferenceBaseURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setSampleReferenceBaseURL:")
    public native void setSampleReferenceBaseURL(NSURL v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "performsMultiPassEncodingIfSupported")
    public native boolean performsMultiPassEncodingIfSupported();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPerformsMultiPassEncodingIfSupported:")
    public native void setPerformsMultiPassEncodingIfSupported(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "canPerformMultiplePasses")
    public native boolean canPerformMultiplePasses();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "currentPassDescription")
    public native AVAssetWriterInputPassDescription getCurrentPassDescription();
    /*</properties>*/
    /*<members>*//*</members>*/
    public static AVAssetWriterInput create(AVMediaType mediaType, AVAudioSettings outputSettings) {
        return create(mediaType, outputSettings.getDictionary());
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static AVAssetWriterInput create(AVMediaType mediaType, AVAudioSettings outputSettings, CMFormatDescription sourceFormatHint) {
        return create(mediaType, outputSettings.getDictionary(), sourceFormatHint);
    }
    public static AVAssetWriterInput create(AVMediaType mediaType, AVVideoSettings outputSettings) {
        return create(mediaType, outputSettings.getDictionary());
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static AVAssetWriterInput create(AVMediaType mediaType, AVVideoSettings outputSettings, CMFormatDescription sourceFormatHint) {
        return create(mediaType, outputSettings.getDictionary(), sourceFormatHint);
    }
    /*<methods>*/
    @Method(selector = "initWithMediaType:outputSettings:")
    protected native @Pointer long init(AVMediaType mediaType, NSDictionary<NSString, NSObject> outputSettings);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "initWithMediaType:outputSettings:sourceFormatHint:")
    protected native @Pointer long init(AVMediaType mediaType, NSDictionary<NSString, NSObject> outputSettings, CMFormatDescription sourceFormatHint);
    @Method(selector = "requestMediaDataWhenReadyOnQueue:usingBlock:")
    public native void requestMediaDataWhenReady(DispatchQueue queue, @Block Runnable block);
    @Method(selector = "appendSampleBuffer:")
    public native boolean appendSampleBuffer(CMSampleBuffer sampleBuffer);
    @Method(selector = "markAsFinished")
    public native void markAsFinished();
    @Method(selector = "assetWriterInputWithMediaType:outputSettings:")
    protected static native AVAssetWriterInput create(AVMediaType mediaType, NSDictionary<NSString, NSObject> outputSettings);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "assetWriterInputWithMediaType:outputSettings:sourceFormatHint:")
    protected static native AVAssetWriterInput create(AVMediaType mediaType, NSDictionary<NSString, NSObject> outputSettings, CMFormatDescription sourceFormatHint);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "canAddTrackAssociationWithTrackOfInput:type:")
    public native boolean canAddTrackAssociation(AVAssetWriterInput input, AVTrackAssociationType trackAssociationType);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addTrackAssociationWithTrackOfInput:type:")
    public native void addTrackAssociation(AVAssetWriterInput input, AVTrackAssociationType trackAssociationType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "respondToEachPassDescriptionOnQueue:usingBlock:")
    public native void respondToEachPassDescriptionOnQueue(DispatchQueue queue, @Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "markCurrentPassAsFinished")
    public native void markCurrentPassAsFinished();
    /*</methods>*/
}
