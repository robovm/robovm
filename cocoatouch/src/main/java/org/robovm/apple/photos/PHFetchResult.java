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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHFetchResult/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration/*</implements>*/ {

    /*<ptr>*/public static class PHFetchResultPtr extends Ptr<PHFetchResult, PHFetchResultPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PHFetchResult.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PHFetchResult() {}
    protected PHFetchResult(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "count")
    public native @MachineSizedUInt long size();
    @Property(selector = "firstObject")
    public native PHObject first();
    @Property(selector = "lastObject")
    public native PHObject last();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "objectAtIndex:")
    public native PHObject get(@MachineSizedUInt long index);
    @Method(selector = "containsObject:")
    public native boolean contains(PHObject anObject);
    @Method(selector = "indexOfObject:")
    public native @MachineSizedUInt long indexOf(PHObject anObject);
    @Method(selector = "indexOfObject:inRange:")
    public native @MachineSizedUInt long indexOf(PHObject anObject, @ByVal NSRange range);
    @Method(selector = "objectsAtIndexes:")
    public native NSArray<PHObject> getAll(NSIndexSet indexes);
    @Method(selector = "enumerateObjectsUsingBlock:")
    public native void enumerateObjects(@Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "enumerateObjectsWithOptions:usingBlock:")
    public native void enumerateObjects(NSEnumerationOptions opts, @Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "enumerateObjectsAtIndexes:options:usingBlock:")
    public native void enumerateObjects(NSIndexSet s, NSEnumerationOptions opts, @Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "countOfAssetsWithMediaType:")
    public native @MachineSizedUInt long getCountOfAssetsWithMediaType(PHAssetMediaType mediaType);
    /*</methods>*/
}
