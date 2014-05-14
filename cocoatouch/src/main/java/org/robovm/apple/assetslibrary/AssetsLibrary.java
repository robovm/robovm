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
/*</javadoc>*/
/*<annotations>*/@Library("AssetsLibrary")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AssetsLibrary/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AssetsLibrary.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsLibraryChangedNotification", optional=true)
    public static native NSString AssetsLibraryChangedNotification();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryUpdatedAssetsKey", optional=true)
    public static native NSString AssetLibraryUpdatedAssetsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryInsertedAssetGroupsKey", optional=true)
    public static native NSString AssetLibraryInsertedAssetGroupsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryUpdatedAssetGroupsKey", optional=true)
    public static native NSString AssetLibraryUpdatedAssetGroupsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetLibraryDeletedAssetGroupsKey", optional=true)
    public static native NSString AssetLibraryDeletedAssetGroupsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsLibraryErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALErrorInvalidProperty", optional=true)
    public static native NSString ErrorInvalidProperty();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyType", optional=true)
    public static native NSString AssetPropertyType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyLocation", optional=true)
    public static native NSString AssetPropertyLocation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyDuration", optional=true)
    public static native NSString AssetPropertyDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyOrientation", optional=true)
    public static native NSString AssetPropertyOrientation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyDate", optional=true)
    public static native NSString AssetPropertyDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyRepresentations", optional=true)
    public static native NSString AssetPropertyRepresentations();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyURLs", optional=true)
    public static native NSString AssetPropertyURLs();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ALAssetPropertyAssetURL", optional=true)
    public static native NSString AssetPropertyAssetURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypePhoto", optional=true)
    public static native NSString AssetTypePhoto();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypeVideo", optional=true)
    public static native NSString AssetTypeVideo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetTypeUnknown", optional=true)
    public static native NSString AssetTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyName", optional=true)
    public static native NSString AssetsGroupPropertyName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyType", optional=true)
    public static native NSString AssetsGroupPropertyType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyPersistentID", optional=true)
    public static native NSString AssetsGroupPropertyPersistentID();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ALAssetsGroupPropertyURL", optional=true)
    public static native NSString AssetsGroupPropertyURL();
    /*</methods>*/
}
