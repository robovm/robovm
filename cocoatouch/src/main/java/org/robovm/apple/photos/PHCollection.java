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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHCollection/*</name>*/ 
    extends /*<extends>*/PHObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHCollectionPtr extends Ptr<PHCollection, PHCollectionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHCollection.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHCollection() {}
    protected PHCollection(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "canContainAssets")
    public native boolean canContainAssets();
    @Property(selector = "canContainCollections")
    public native boolean canContainCollections();
    @Property(selector = "localizedTitle")
    public native String getLocalizedTitle();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "canPerformEditOperation:")
    public native boolean canPerformEditOperation(PHCollectionEditOperation anOperation);
    @Method(selector = "fetchCollectionsInCollectionList:options:")
    public static native PHFetchResult fetchCollectionsInCollectionList(PHCollectionList collectionList, PHFetchOptions options);
    @Method(selector = "fetchTopLevelUserCollectionsWithOptions:")
    public static native PHFetchResult fetchTopLevelUserCollections(PHFetchOptions options);
    /*</methods>*/
}
