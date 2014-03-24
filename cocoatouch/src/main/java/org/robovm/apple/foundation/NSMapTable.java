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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMapTable/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSMapTablePtr extends Ptr<NSMapTable, NSMapTablePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMapTable.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMapTable() {}
    protected NSMapTable(SkipInit skipInit) { super(skipInit); }
    public NSMapTable(NSPointerFunctionsOptions keyOptions, NSPointerFunctionsOptions valueOptions, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(initWithKeyOptions$valueOptions$capacity$(keyOptions, valueOptions, initialCapacity)); }
    public NSMapTable(NSPointerFunctions keyFunctions, NSPointerFunctions valueFunctions, @MachineSizedUInt long initialCapacity) { super((SkipInit) null); initObject(initWithKeyPointerFunctions$valuePointerFunctions$capacity$(keyFunctions, valueFunctions, initialCapacity)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithKeyOptions:valueOptions:capacity:")
    protected native @Pointer long initWithKeyOptions$valueOptions$capacity$(NSPointerFunctionsOptions keyOptions, NSPointerFunctionsOptions valueOptions, @MachineSizedUInt long initialCapacity);
    @Method(selector = "initWithKeyPointerFunctions:valuePointerFunctions:capacity:")
    protected native @Pointer long initWithKeyPointerFunctions$valuePointerFunctions$capacity$(NSPointerFunctions keyFunctions, NSPointerFunctions valueFunctions, @MachineSizedUInt long initialCapacity);
    @Method(selector = "keyPointerFunctions")
    public native NSPointerFunctions keyPointerFunctions();
    @Method(selector = "valuePointerFunctions")
    public native NSPointerFunctions valuePointerFunctions();
    @Method(selector = "objectForKey:")
    public native NSObject objectForKey$(NSObject aKey);
    @Method(selector = "removeObjectForKey:")
    public native void removeObjectForKey$(NSObject aKey);
    @Method(selector = "setObject:forKey:")
    public native void setObject$forKey$(NSObject anObject, NSObject aKey);
    @Method(selector = "count")
    public native @MachineSizedUInt long count();
    @Method(selector = "keyEnumerator")
    public native NSEnumerator<?> keyEnumerator();
    @Method(selector = "objectEnumerator")
    public native NSEnumerator<?> objectEnumerator();
    @Method(selector = "removeAllObjects")
    public native void removeAllObjects();
    @Method(selector = "dictionaryRepresentation")
    public native NSDictionary<?, ?> dictionaryRepresentation();
    @Method(selector = "mapTableWithKeyOptions:valueOptions:")
    public static native NSObject mapTableWithKeyOptions$valueOptions$(NSPointerFunctionsOptions keyOptions, NSPointerFunctionsOptions valueOptions);
    @Method(selector = "strongToStrongObjectsMapTable")
    public static native NSObject strongToStrongObjectsMapTable();
    @Method(selector = "weakToStrongObjectsMapTable")
    public static native NSObject weakToStrongObjectsMapTable();
    @Method(selector = "strongToWeakObjectsMapTable")
    public static native NSObject strongToWeakObjectsMapTable();
    @Method(selector = "weakToWeakObjectsMapTable")
    public static native NSObject weakToWeakObjectsMapTable();
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
