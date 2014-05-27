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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ALAsset/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ALAssetPtr extends Ptr<ALAsset, ALAssetPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(ALAsset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ALAsset() {}
    protected ALAsset(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "originalAsset")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native ALAsset getOriginalAsset();
    @Property(selector = "isEditable")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native boolean isEditable();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "valueForProperty:")
    public native NSObject getValue(NSString property);
    @Method(selector = "defaultRepresentation")
    public native ALAssetRepresentation getDefaultRepresentation();
    @Method(selector = "representationForUTI:")
    public native ALAssetRepresentation getRepresentation(String representationUTI);
    @Method(selector = "thumbnail")
    public native CGImage getThumbnail();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "aspectRatioThumbnail")
    public native CGImage getAspectRatioThumbnail();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "writeModifiedImageDataToSavedPhotosAlbum:metadata:completionBlock:")
    public native void writeModifiedImageData(NSData imageData, NSDictionary<NSString, ?> metadata, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "writeModifiedVideoAtPathToSavedPhotosAlbum:completionBlock:")
    public native void writeModifiedVideoData(NSURL videoPathURL, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setImageData:metadata:completionBlock:")
    public native void setImageData(NSData imageData, NSDictionary<NSString, ?> metadata, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setVideoAtPath:completionBlock:")
    public native void setVideo(NSURL videoPathURL, @Block VoidBlock2<NSURL, NSError> completionBlock);
    /*</methods>*/
}
