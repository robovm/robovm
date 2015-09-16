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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHCollectionList/*</name>*/ 
    extends /*<extends>*/PHCollection/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHCollectionListPtr extends Ptr<PHCollectionList, PHCollectionListPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHCollectionList.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHCollectionList() {}
    protected PHCollectionList(SkipInit skipInit) { super(skipInit); }
    public PHCollectionList(NSArray<PHCollection> collections, String title) { super(createTransientCollectionList(collections, title)); retain(getHandle()); }
    public PHCollectionList(PHFetchResult<PHCollectionList> fetchResult, String title) { super(createTransientCollectionList(fetchResult, title)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "collectionListType")
    public native PHCollectionListType getCollectionListType();
    @Property(selector = "collectionListSubtype")
    public native PHCollectionListSubtype getCollectionListSubtype();
    @Property(selector = "startDate")
    public native NSDate getStartDate();
    @Property(selector = "endDate")
    public native NSDate getEndDate();
    @Property(selector = "localizedLocationNames")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLocalizedLocationNames();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "fetchCollectionListsContainingCollection:options:")
    public static native PHFetchResult<PHCollectionList> fetchCollectionListsContainingCollection(PHCollection collection, PHFetchOptions options);
    @Method(selector = "fetchCollectionListsWithLocalIdentifiers:options:")
    public static native PHFetchResult<PHCollectionList> fetchCollectionListsWithLocalIdentifiers(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> identifiers, PHFetchOptions options);
    @Method(selector = "fetchCollectionListsWithType:subtype:options:")
    public static native PHFetchResult<PHCollectionList> fetchCollectionListsWithType(PHCollectionListType collectionListType, PHCollectionListSubtype subtype, PHFetchOptions options);
    @Method(selector = "fetchMomentListsWithSubtype:containingMoment:options:")
    public static native PHFetchResult<PHCollectionList> fetchMomentListsWithSubtypeContainingMoment(PHCollectionListSubtype momentListSubtype, PHAssetCollection moment, PHFetchOptions options);
    @Method(selector = "fetchMomentListsWithSubtype:options:")
    public static native PHFetchResult<PHCollectionList> fetchMomentListsWithSubtype(PHCollectionListSubtype momentListSubtype, PHFetchOptions options);
    @Method(selector = "transientCollectionListWithCollections:title:")
    protected static native @Pointer long createTransientCollectionList(NSArray<PHCollection> collections, String title);
    @Method(selector = "transientCollectionListWithCollectionsFetchResult:title:")
    protected static native @Pointer long createTransientCollectionList(PHFetchResult<PHCollectionList> fetchResult, String title);
    /*</methods>*/
}
