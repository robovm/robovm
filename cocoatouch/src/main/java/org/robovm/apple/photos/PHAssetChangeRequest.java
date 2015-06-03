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
package org.robovm.apple.photos;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.corelocation.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Photos") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHAssetChangeRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHAssetChangeRequestPtr extends Ptr<PHAssetChangeRequest, PHAssetChangeRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHAssetChangeRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHAssetChangeRequest() {}
    protected PHAssetChangeRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "placeholderForCreatedAsset")
    public native PHObjectPlaceholder getPlaceholderForCreatedAsset();
    @Property(selector = "creationDate")
    public native NSDate getCreationDate();
    @Property(selector = "setCreationDate:")
    public native void setCreationDate(NSDate v);
    @WeaklyLinked
    @Property(selector = "location")
    public native CLLocation getLocation();
    @WeaklyLinked
    @Property(selector = "setLocation:")
    public native void setLocation(CLLocation v);
    @Property(selector = "isFavorite")
    public native boolean isFavorite();
    @Property(selector = "setFavorite:")
    public native void setFavorite(boolean v);
    @Property(selector = "isHidden")
    public native boolean isHidden();
    @Property(selector = "setHidden:")
    public native void setHidden(boolean v);
    @Property(selector = "contentEditingOutput")
    public native PHContentEditingOutput getContentEditingOutput();
    @Property(selector = "setContentEditingOutput:")
    public native void setContentEditingOutput(PHContentEditingOutput v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "revertAssetContentToOriginal")
    public native void revertAssetContentToOriginal();
    @WeaklyLinked
    @Method(selector = "creationRequestForAssetFromImage:")
    public static native PHAssetChangeRequest createImageAssetCreationRequest(UIImage image);
    @Method(selector = "creationRequestForAssetFromImageAtFileURL:")
    public static native PHAssetChangeRequest createImageAssetCreationRequest(NSURL fileURL);
    @Method(selector = "creationRequestForAssetFromVideoAtFileURL:")
    public static native PHAssetChangeRequest createVideoAssetCreationRequest(NSURL fileURL);
    @Method(selector = "deleteAssets:")
    public static native void deleteAssets(NSFastEnumeration assets);
    @Method(selector = "changeRequestForAsset:")
    public static native PHAssetChangeRequest create(PHAsset asset);
    /*</methods>*/
}
