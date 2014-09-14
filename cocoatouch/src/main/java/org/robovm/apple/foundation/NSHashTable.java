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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSHashTable/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSHashTablePtr extends Ptr<NSHashTable, NSHashTablePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSHashTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSHashTable() {}
    protected NSHashTable(SkipInit skipInit) { super(skipInit); }
    public NSHashTable(NSPointerFunctionsOptions options, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(initWithOptions$capacity$(options, initialCapacity)); }
    public NSHashTable(NSPointerFunctions functions, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(initWithPointerFunctions$capacity$(functions, initialCapacity)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithOptions:capacity:")
    protected native @Pointer long initWithOptions$capacity$(NSPointerFunctionsOptions options, @MachineSizedUInt long initialCapacity);
    @Method(selector = "initWithPointerFunctions:capacity:")
    protected native @Pointer long initWithPointerFunctions$capacity$(NSPointerFunctions functions, @MachineSizedUInt long initialCapacity);
    @Method(selector = "pointerFunctions")
    public native NSPointerFunctions pointerFunctions();
    @Method(selector = "count")
    public native @MachineSizedUInt long count();
    @Method(selector = "member:")
    public native NSObject member$(NSObject object);
    @Method(selector = "objectEnumerator")
    public native NSEnumerator<?> objectEnumerator();
    @Method(selector = "addObject:")
    public native void addObject$(NSObject object);
    @Method(selector = "removeObject:")
    public native void removeObject$(NSObject object);
    @Method(selector = "removeAllObjects")
    public native void removeAllObjects();
    @Method(selector = "allObjects")
    public native NSArray<?> allObjects();
    @Method(selector = "anyObject")
    public native NSObject anyObject();
    @Method(selector = "containsObject:")
    public native boolean containsObject$(NSObject anObject);
    @Method(selector = "intersectsHashTable:")
    public native boolean intersectsHashTable$(NSHashTable other);
    @Method(selector = "isEqualToHashTable:")
    public native boolean isEqualToHashTable$(NSHashTable other);
    @Method(selector = "isSubsetOfHashTable:")
    public native boolean isSubsetOfHashTable$(NSHashTable other);
    @Method(selector = "intersectHashTable:")
    public native void intersectHashTable$(NSHashTable other);
    @Method(selector = "unionHashTable:")
    public native void unionHashTable$(NSHashTable other);
    @Method(selector = "minusHashTable:")
    public native void minusHashTable$(NSHashTable other);
    @Method(selector = "setRepresentation")
    public native NSSet<?> setRepresentation();
    @Method(selector = "hashTableWithOptions:")
    public static native NSObject hashTableWithOptions$(NSPointerFunctionsOptions options);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "weakObjectsHashTable")
    public static native NSObject weakObjectsHashTable();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
