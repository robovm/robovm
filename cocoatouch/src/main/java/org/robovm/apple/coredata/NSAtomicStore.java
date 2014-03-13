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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAtomicStore/*</name>*/ 
    extends /*<extends>*/NSPersistentStore/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSAtomicStorePtr extends Ptr<NSAtomicStore, NSAtomicStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAtomicStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSAtomicStore() {}
    protected NSAtomicStore(SkipInit skipInit) { super(skipInit); }
    public NSAtomicStore(NSPersistentStoreCoordinator coordinator, String configurationName, NSURL url, NSDictionary<?, ?> options) { super((SkipInit) null); initObject(initWithPersistentStoreCoordinator$configurationName$URL$options$(coordinator, configurationName, url, options)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPersistentStoreCoordinator:configurationName:URL:options:")
    protected native @Pointer long initWithPersistentStoreCoordinator$configurationName$URL$options$(NSPersistentStoreCoordinator coordinator, String configurationName, NSURL url, NSDictionary<?, ?> options);
    @Method(selector = "load:")
    public native boolean load$(NSError.NSErrorPtr error);
    @Method(selector = "save:")
    public native boolean save$(NSError.NSErrorPtr error);
    @Method(selector = "newCacheNodeForManagedObject:")
    public native NSAtomicStoreCacheNode newCacheNodeForManagedObject$(NSManagedObject managedObject);
    @Method(selector = "updateCacheNode:fromManagedObject:")
    public native void updateCacheNode$fromManagedObject$(NSAtomicStoreCacheNode node, NSManagedObject managedObject);
    @Method(selector = "cacheNodes")
    public native NSSet<?> cacheNodes();
    @Method(selector = "addCacheNodes:")
    public native void addCacheNodes$(NSSet<?> cacheNodes);
    @Method(selector = "willRemoveCacheNodes:")
    public native void willRemoveCacheNodes$(NSSet<?> cacheNodes);
    @Method(selector = "cacheNodeForObjectID:")
    public native NSAtomicStoreCacheNode cacheNodeForObjectID$(NSManagedObjectID objectID);
    @Method(selector = "objectIDForEntity:referenceObject:")
    public native NSManagedObjectID objectIDForEntity$referenceObject$(NSEntityDescription entity, NSObject data);
    @Method(selector = "newReferenceObjectForManagedObject:")
    public native NSObject newReferenceObjectForManagedObject$(NSManagedObject managedObject);
    @Method(selector = "referenceObjectForObjectID:")
    public native NSObject referenceObjectForObjectID$(NSManagedObjectID objectID);
    /*</methods>*/
}
