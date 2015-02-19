/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHAssetCollection/*</name>*/ 
    extends /*<extends>*/PHCollection/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHAssetCollectionPtr extends Ptr<PHAssetCollection, PHAssetCollectionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHAssetCollection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHAssetCollection() {}
    protected PHAssetCollection(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "assetCollectionType")
    public native PHAssetCollectionType getAssetCollectionType();
    @Property(selector = "assetCollectionSubtype")
    public native PHAssetCollectionSubtype getAssetCollectionSubtype();
    @Property(selector = "estimatedAssetCount")
    public native @MachineSizedUInt long getEstimatedAssetCount();
    @Property(selector = "startDate")
    public native NSDate getStartDate();
    @Property(selector = "endDate")
    public native NSDate getEndDate();
    @Property(selector = "approximateLocation")
    public native CLLocation getApproximateLocation();
    @Property(selector = "localizedLocationNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLocalizedLocationNames();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "fetchAssetCollectionsWithLocalIdentifiers:options:")
    public static native PHFetchResult fetchAssetCollectionsWithLocalIdentifiers(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> identifiers, PHFetchOptions options);
    @Method(selector = "fetchAssetCollectionsWithType:subtype:options:")
    public static native PHFetchResult fetchAssetCollectionsWithType(PHAssetCollectionType type, PHAssetCollectionSubtype subtype, PHFetchOptions options);
    @Method(selector = "fetchAssetCollectionsContainingAsset:withType:options:")
    public static native PHFetchResult fetchAssetCollectionsContainingAsset(PHAsset asset, PHAssetCollectionType type, PHFetchOptions options);
    @Method(selector = "fetchAssetCollectionsWithALAssetGroupURLs:options:")
    public static native PHFetchResult fetchAssetCollectionsWithALAssetGroupURLs(NSArray<NSURL> assetGroupURLs, PHFetchOptions options);
    @Method(selector = "fetchMomentsInMomentList:options:")
    public static native PHFetchResult fetchMomentsInMomentList(PHCollectionList momentList, PHFetchOptions options);
    @Method(selector = "fetchMomentsWithOptions:")
    public static native PHFetchResult fetchMoments(PHFetchOptions options);
    @Method(selector = "transientAssetCollectionWithAssets:title:")
    public static native PHAssetCollection createTransientAssetCollection(NSArray<PHAsset> assets, String title);
    @Method(selector = "transientAssetCollectionWithAssetFetchResult:title:")
    public static native PHAssetCollection createTransientAssetCollection(PHFetchResult fetchResult, String title);
    /*</methods>*/
}
