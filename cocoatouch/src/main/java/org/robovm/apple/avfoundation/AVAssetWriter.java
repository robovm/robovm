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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetWriter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetWriterPtr extends Ptr<AVAssetWriter, AVAssetWriterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetWriter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetWriter() {}
    protected AVAssetWriter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /**
     * 
     * @param outputURL
     * @param outputFileType
     * @throws NSErrorException
     */
    public AVAssetWriter(NSURL outputURL, String outputFileType) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        initObject(init(outputURL, outputFileType, err));
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /*<properties>*/
    @Property(selector = "outputURL")
    public native NSURL getOutputURL();
    @Property(selector = "outputFileType")
    public native String getOutputFileType();
    @Property(selector = "availableMediaTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(AVMediaType.AsListMarshaler.class) List<AVMediaType> getAvailableMediaTypes();
    @Property(selector = "status")
    public native AVAssetWriterStatus getStatus();
    @Property(selector = "error")
    public native NSError getError();
    @Property(selector = "metadata")
    public native NSArray<AVMetadataItem> getMetadata();
    @Property(selector = "setMetadata:")
    public native void setMetadata(NSArray<AVMetadataItem> v);
    @Property(selector = "shouldOptimizeForNetworkUse")
    public native boolean shouldOptimizeForNetworkUse();
    @Property(selector = "setShouldOptimizeForNetworkUse:")
    public native void setShouldOptimizeForNetworkUse(boolean v);
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
    @Property(selector = "inputs")
    public native NSArray<AVAssetWriterInput> getInputs();
    @Property(selector = "movieFragmentInterval")
    public native @ByVal CMTime getMovieFragmentInterval();
    @Property(selector = "setMovieFragmentInterval:")
    public native void setMovieFragmentInterval(@ByVal CMTime v);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "movieTimeScale")
    public native int getMovieTimeScale();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Property(selector = "setMovieTimeScale:")
    public native void setMovieTimeScale(int v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "inputGroups")
    public native NSArray<AVAssetWriterInputGroup> getInputGroups();
    /*</properties>*/
    /*<members>*//*</members>*/
    public boolean canApplyOutputSettings(AVAudioSettings outputSettings, AVMediaType mediaType) {
        return canApplyOutputSettings(outputSettings.getDictionary(), mediaType);
    }
    public boolean canApplyOutputSettings(AVVideoSettings outputSettings, AVMediaType mediaType) {
        return canApplyOutputSettings(outputSettings.getDictionary(), mediaType);
    }
    @SuppressWarnings("unchecked")
    public boolean canApplyOutputSettings(AVPixelBufferAttributes outputSettings, AVMediaType mediaType) {
        return canApplyOutputSettings(outputSettings.getDictionary().as(NSDictionary.class), mediaType);
    }
    /**
     * 
     * @param outputURL
     * @param outputFileType
     * @return
     * @throws NSErrorException
     */
    public AVAssetWriter create(NSURL outputURL, String outputFileType) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        AVAssetWriter result = create(outputURL, outputFileType, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
   
    /*<methods>*/
    @Method(selector = "initWithURL:fileType:error:")
    protected native @Pointer long init(NSURL outputURL, String outputFileType, NSError.NSErrorPtr outError);
    @Method(selector = "canApplyOutputSettings:forMediaType:")
    protected native boolean canApplyOutputSettings(NSDictionary<NSString, NSObject> outputSettings, AVMediaType mediaType);
    @Method(selector = "canAddInput:")
    public native boolean canAddInput(AVAssetWriterInput input);
    @Method(selector = "addInput:")
    public native void addInput(AVAssetWriterInput input);
    @Method(selector = "startWriting")
    public native boolean startWriting();
    @Method(selector = "startSessionAtSourceTime:")
    public native void startSession(@ByVal CMTime startTime);
    @Method(selector = "endSessionAtSourceTime:")
    public native void endSession(@ByVal CMTime endTime);
    @Method(selector = "cancelWriting")
    public native void cancelWriting();
    /**
     * @since Available in iOS 4.1 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "finishWriting")
    public native boolean finishWriting();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "finishWritingWithCompletionHandler:")
    public native void finishWriting(@Block Runnable handler);
    @Method(selector = "assetWriterWithURL:fileType:error:")
    protected static native AVAssetWriter create(NSURL outputURL, String outputFileType, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "canAddInputGroup:")
    public native boolean canAddInputGroup(AVAssetWriterInputGroup inputGroup);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "addInputGroup:")
    public native void addInputGroup(AVAssetWriterInputGroup inputGroup);
    /*</methods>*/
}
