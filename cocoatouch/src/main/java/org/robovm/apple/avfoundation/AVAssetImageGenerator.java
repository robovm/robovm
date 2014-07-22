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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetImageGenerator/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetImageGeneratorPtr extends Ptr<AVAssetImageGenerator, AVAssetImageGeneratorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetImageGenerator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetImageGenerator() {}
    protected AVAssetImageGenerator(SkipInit skipInit) { super(skipInit); }
    public AVAssetImageGenerator(AVAsset asset) { super((SkipInit) null); initObject(init(asset)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "asset")
    public native AVAsset getAsset();
    @Property(selector = "appliesPreferredTrackTransform")
    public native boolean isAppliesPreferredTrackTransform();
    @Property(selector = "setAppliesPreferredTrackTransform:")
    public native void setAppliesPreferredTrackTransform(boolean v);
    @Property(selector = "maximumSize")
    public native @ByVal CGSize getMaximumSize();
    @Property(selector = "setMaximumSize:")
    public native void setMaximumSize(@ByVal CGSize v);
    @Property(selector = "apertureMode")
    public native String getApertureMode();
    @Property(selector = "setApertureMode:")
    public native void setApertureMode(String v);
    @Property(selector = "videoComposition")
    public native AVVideoComposition getVideoComposition();
    @Property(selector = "setVideoComposition:")
    public native void setVideoComposition(AVVideoComposition v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "customVideoCompositor")
    public native AVVideoCompositing getCustomVideoCompositor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "requestedTimeToleranceBefore")
    public native @ByVal CMTime getRequestedTimeToleranceBefore();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setRequestedTimeToleranceBefore:")
    public native void setRequestedTimeToleranceBefore(@ByVal CMTime v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "requestedTimeToleranceAfter")
    public native @ByVal CMTime getRequestedTimeToleranceAfter();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setRequestedTimeToleranceAfter:")
    public native void setRequestedTimeToleranceAfter(@ByVal CMTime v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAsset:")
    protected native @Pointer long init(AVAsset asset);
    @Method(selector = "copyCGImageAtTime:actualTime:error:")
    public native CGImage copyCGImage(@ByVal CMTime requestedTime, CMTime actualTime, NSError.NSErrorPtr outError);
    @Method(selector = "generateCGImagesAsynchronouslyForTimes:completionHandler:")
    public native void generateCGImagesAsynchronously(NSArray<NSValue> requestedTimes, @Block VoidBlock5<CMTime, CGImage, CMTime, AVAssetImageGeneratorResult, NSError> handler);
    @Method(selector = "cancelAllCGImageGeneration")
    public native void cancelAllCGImageGeneration();
    @Method(selector = "assetImageGeneratorWithAsset:")
    public static native AVAssetImageGenerator create(AVAsset asset);
    /*</methods>*/
}
