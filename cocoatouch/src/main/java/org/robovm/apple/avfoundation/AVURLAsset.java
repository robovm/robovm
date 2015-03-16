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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVURLAsset/*</name>*/ 
    extends /*<extends>*/AVAsset/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVURLAssetPtr extends Ptr<AVURLAsset, AVURLAssetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVURLAsset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVURLAsset() {}
    protected AVURLAsset(SkipInit skipInit) { super(skipInit); }
    public AVURLAsset(NSURL URL, AVURLAssetOptions options) { super((SkipInit) null); initObject(init(URL, options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "URL")
    public native NSURL getURL();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "resourceLoader")
    public native AVAssetResourceLoader getResourceLoader();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithURL:options:")
    protected native @Pointer long init(NSURL URL, AVURLAssetOptions options);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "audiovisualTypes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAudiovisualTypes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "audiovisualMIMETypes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAudiovisualMIMETypes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isPlayableExtendedMIMEType:")
    public static native boolean isPlayableExtendedMIMEType(String extendedMIMEType);
    @Method(selector = "URLAssetWithURL:options:")
    public static native AVURLAsset create(NSURL URL, AVURLAssetOptions options);
    @Method(selector = "compatibleTrackForCompositionTrack:")
    public native AVAssetTrack getCompatibleTrack(AVCompositionTrack compositionTrack);
    /*</methods>*/
}
