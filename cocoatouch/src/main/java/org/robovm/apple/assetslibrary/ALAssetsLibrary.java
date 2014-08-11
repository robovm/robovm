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
package org.robovm.apple.assetslibrary;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AssetsLibrary") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAssetsLibrary/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ALAssetsLibraryPtr extends Ptr<ALAssetsLibrary, ALAssetsLibraryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(ALAssetsLibrary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ALAssetsLibrary() {}
    protected ALAssetsLibrary(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "enumerateGroupsWithTypes:usingBlock:failureBlock:")
    public native void enumerateGroups(ALAssetsGroupType types, @Block VoidBlock2<ALAssetsGroup, BytePtr> enumerationBlock, @Block VoidBlock1<NSError> failureBlock);
    @Method(selector = "assetForURL:resultBlock:failureBlock:")
    public native void getAsset(NSURL assetURL, @Block VoidBlock1<ALAsset> resultBlock, @Block VoidBlock1<NSError> failureBlock);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "groupForURL:resultBlock:failureBlock:")
    public native void getGroup(NSURL groupURL, @Block VoidBlock1<ALAssetsGroup> resultBlock, @Block VoidBlock1<NSError> failureBlock);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "addAssetsGroupAlbumWithName:resultBlock:failureBlock:")
    public native void addAssetsGroupAlbum(String name, @Block VoidBlock1<ALAssetsGroup> resultBlock, @Block VoidBlock1<NSError> failureBlock);
    @Method(selector = "writeImageToSavedPhotosAlbum:orientation:completionBlock:")
    public native void writeImageToSavedPhotosAlbum(CGImage imageRef, ALAssetOrientation orientation, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Method(selector = "writeImageToSavedPhotosAlbum:metadata:completionBlock:")
    public native void writeImageToSavedPhotosAlbum(CGImage imageRef, NSDictionary<NSString, ?> metadata, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /**
     * @since Available in iOS 4.1 and later.
     */
    @Method(selector = "writeImageDataToSavedPhotosAlbum:metadata:completionBlock:")
    public native void writeImageDataToSavedPhotosAlbum(NSData imageData, NSDictionary<NSString, ?> metadata, @Block VoidBlock2<NSURL, NSError> completionBlock);
    @Method(selector = "writeVideoAtPathToSavedPhotosAlbum:completionBlock:")
    public native void writeVideoToSavedPhotosAlbum(NSURL videoPathURL, @Block VoidBlock2<NSURL, NSError> completionBlock);
    @Method(selector = "videoAtPathIsCompatibleWithSavedPhotosAlbum:")
    public native boolean isVideoCompatibleWithSavedPhotosAlbum(NSURL videoPathURL);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "authorizationStatus")
    public static native ALAuthorizationStatus getAuthorizationStatus();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "disableSharedPhotoStreamsSupport")
    public static native void disableSharedPhotoStreamsSupport();
    /*</methods>*/
}
