/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    public NSAtomicStoreCacheNode(NSManagedObjectID moid) { super((SkipInit) null); initObject(initWithObjectID$(moid)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithObjectID:")
    protected native @Pointer long initWithObjectID$(NSManagedObjectID moid);
    @Method(selector = "objectID")
    public native NSManagedObjectID objectID();
    @Method(selector = "propertyCache")
    public native NSMutableDictionary<?, ?> propertyCache();
    @Method(selector = "setPropertyCache:")
    public native void setPropertyCache$(NSMutableDictionary<?, ?> propertyCache);
    @Method(selector = "valueForKey:")
    public native NSObject valueForKey$(String key);
    @Method(selector = "setValue:forKey:")
    public native void setValue$forKey$(NSObject value, String key);
    /*</methods>*/
}
