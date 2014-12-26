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
package org.robovm.apple.photos;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Photos") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHImageManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHImageManagerPtr extends Ptr<PHImageManager, PHImageManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHImageManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHImageManager() {}
    protected PHImageManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="PHImageManagerMaximumSize", optional=true)
    public static native @ByVal CGSize getMaximumSize();
    
    @Method(selector = "requestImageForAsset:targetSize:contentMode:options:resultHandler:")
    public native int requestImageForAsset(PHAsset asset, @ByVal CGSize targetSize, PHImageContentMode contentMode, PHImageRequestOptions options, @Block VoidBlock2<UIImage, NSDictionary<NSString, NSObject>> resultHandler);
    @Method(selector = "requestImageDataForAsset:options:resultHandler:")
    public native int requestImageDataForAsset(PHAsset asset, PHImageRequestOptions options, @Block VoidBlock4<NSData, String, UIImageOrientation, NSDictionary<NSString, NSObject>> resultHandler);
    @Method(selector = "cancelImageRequest:")
    public native void cancelImageRequest(int requestID);
    @Method(selector = "requestPlayerItemForVideo:options:resultHandler:")
    public native int requestPlayerItemForVideo(PHAsset asset, PHVideoRequestOptions options, @Block VoidBlock2<AVPlayerItem, NSDictionary<NSString, NSObject>> resultHandler);
    @Method(selector = "requestExportSessionForVideo:options:exportPreset:resultHandler:")
    public native int requestExportSessionForVideo(PHAsset asset, PHVideoRequestOptions options, String exportPreset, @Block VoidBlock2<AVAssetExportSession, NSDictionary<NSString, NSObject>> resultHandler);
    @Method(selector = "requestAVAssetForVideo:options:resultHandler:")
    public native int requestAVAssetForVideo(PHAsset asset, PHVideoRequestOptions options, @Block VoidBlock3<AVAsset, AVAudioMix, NSDictionary<NSString, NSObject>> resultHandler);
    @Method(selector = "defaultManager")
    public static native PHImageManager getDefaultManager();
    /*</methods>*/
}
