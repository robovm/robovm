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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PHFetchResult/*</name>*/ <T extends PHObject>
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
    public native T first();
    @Property(selector = "lastObject")
    public native T last();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void enumerateObjects(final Block2<T, Long, Boolean> block) {
        enumerateObjects0(new VoidBlock3<NSObject, Long, BooleanPtr>() {
            @Override
            public void invoke(NSObject a, Long b, BooleanPtr c) {
                c.set(block.invoke((T)a, b));
            }
        });
    }
    public void enumerateObjects(NSEnumerationOptions opts, final Block2<T, Long, Boolean> block) {
        enumerateObjects0(opts, new VoidBlock3<NSObject, Long, BooleanPtr>() {
            @Override
            public void invoke(NSObject a, Long b, BooleanPtr c) {
                c.set(block.invoke((T)a, b));
            }
        });
    }
    public void enumerateObjects(NSIndexSet s, NSEnumerationOptions opts, final Block2<T, Long, Boolean> block) {
        enumerateObjects0(s, opts, new VoidBlock3<NSObject, Long, BooleanPtr>() {
            @Override
            public void invoke(NSObject a, Long b, BooleanPtr c) {
                c.set(block.invoke((T)a, b));
            }
        });
    }
    /*<methods>*/
    @Method(selector = "objectAtIndex:")
    public native T get(@MachineSizedUInt long index);
    @Method(selector = "containsObject:")
    public native boolean contains(T anObject);
    @Method(selector = "indexOfObject:")
    public native @MachineSizedUInt long indexOf(T anObject);
    @Method(selector = "indexOfObject:inRange:")
    public native @MachineSizedUInt long indexOf(T anObject, @ByVal NSRange range);
    @Method(selector = "objectsAtIndexes:")
    public native NSArray<T> getAll(NSIndexSet indexes);
    @Method(selector = "enumerateObjectsUsingBlock:")
    protected native void enumerateObjects0(@Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "enumerateObjectsWithOptions:usingBlock:")
    protected native void enumerateObjects0(NSEnumerationOptions opts, @Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "enumerateObjectsAtIndexes:options:usingBlock:")
    protected native void enumerateObjects0(NSIndexSet s, NSEnumerationOptions opts, @Block("(,@MachineSizedUInt,)") VoidBlock3<NSObject, Long, BooleanPtr> block);
    @Method(selector = "countOfAssetsWithMediaType:")
    public native @MachineSizedUInt long getCountOfAssetsWithMediaType(PHAssetMediaType mediaType);
    /*</methods>*/
}
