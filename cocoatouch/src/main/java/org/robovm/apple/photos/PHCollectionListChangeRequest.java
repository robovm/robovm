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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHCollectionListChangeRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PHCollectionListChangeRequestPtr extends Ptr<PHCollectionListChangeRequest, PHCollectionListChangeRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHCollectionListChangeRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHCollectionListChangeRequest() {}
    protected PHCollectionListChangeRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "placeholderForCreatedCollectionList")
    public native PHObjectPlaceholder getPlaceholderForCreatedCollectionList();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addChildCollections:")
    public native void addChildCollections(NSFastEnumeration collections);
    @Method(selector = "insertChildCollections:atIndexes:")
    public native void insertChildCollections(NSFastEnumeration collections, NSIndexSet indexes);
    @Method(selector = "removeChildCollections:")
    public native void removeChildCollections(NSFastEnumeration collections);
    @Method(selector = "removeChildCollectionsAtIndexes:")
    public native void removeChildCollections(NSIndexSet indexes);
    @Method(selector = "replaceChildCollectionsAtIndexes:withChildCollections:")
    public native void replaceChildCollections(NSIndexSet indexes, NSFastEnumeration collections);
    @Method(selector = "moveChildCollectionsAtIndexes:toIndex:")
    public native void moveChildCollectionsTo(NSIndexSet indexes, @MachineSizedUInt long toIndex);
    @Method(selector = "creationRequestForCollectionListWithTitle:")
    public static native PHCollectionListChangeRequest createCollectionListCreationRequest(String title);
    @Method(selector = "deleteCollectionLists:")
    public static native void deleteCollectionLists(NSFastEnumeration collectionLists);
    @Method(selector = "changeRequestForCollectionList:")
    public static native PHCollectionListChangeRequest create(PHCollectionList collectionList);
    @Method(selector = "changeRequestForCollectionList:childCollections:")
    public static native PHCollectionListChangeRequest create(PHCollectionList collectionList, PHFetchResult childCollections);
    /*</methods>*/
}
