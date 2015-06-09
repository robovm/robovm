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
package org.robovm.apple.coredata;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAtomicStoreCacheNode/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSAtomicStoreCacheNodePtr extends Ptr<NSAtomicStoreCacheNode, NSAtomicStoreCacheNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAtomicStoreCacheNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSAtomicStoreCacheNode() {}
    protected NSAtomicStoreCacheNode(SkipInit skipInit) { super(skipInit); }
    public NSAtomicStoreCacheNode(NSManagedObjectID moid) { super((SkipInit) null); initObject(init(moid)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "objectID")
    public native NSManagedObjectID getObjectID();
    @Property(selector = "propertyCache")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getPropertyCache();
    @Property(selector = "setPropertyCache:")
    public native void setPropertyCache(@org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setValue(String key, NSObject value) {
        setValue(value, key);
    }
    /*<methods>*/
    @Method(selector = "initWithObjectID:")
    protected native @Pointer long init(NSManagedObjectID moid);
    @Method(selector = "valueForKey:")
    public native NSObject getValue(String key);
    @Method(selector = "setValue:forKey:")
    private native void setValue(NSObject value, String key);
    /*</methods>*/
}
