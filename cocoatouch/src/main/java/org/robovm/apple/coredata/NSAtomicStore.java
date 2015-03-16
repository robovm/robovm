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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/
import org.robovm.apple.foundation.NSError.NSErrorPtr;

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
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
    public NSAtomicStore(NSPersistentStoreCoordinator coordinator, String configurationName, NSURL url, NSPersistentStoreOptions options) { super((SkipInit) null); initObject(init(coordinator, configurationName, url, options)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public boolean load() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = load(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @return
     * @throws NSErrorException
     */
    public boolean save() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = save(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "initWithPersistentStoreCoordinator:configurationName:URL:options:")
    protected native @Pointer long init(NSPersistentStoreCoordinator coordinator, String configurationName, NSURL url, NSPersistentStoreOptions options);
    @Method(selector = "load:")
    protected native boolean load(NSError.NSErrorPtr error);
    @Method(selector = "save:")
    protected native boolean save(NSError.NSErrorPtr error);
    @Method(selector = "newCacheNodeForManagedObject:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSAtomicStoreCacheNode newCacheNode(NSManagedObject managedObject);
    @Method(selector = "updateCacheNode:fromManagedObject:")
    public native void updateCacheNode(NSAtomicStoreCacheNode node, NSManagedObject managedObject);
    @Method(selector = "cacheNodes")
    public native NSSet<NSAtomicStoreCacheNode> getCacheNodes();
    @Method(selector = "addCacheNodes:")
    public native void addCacheNodes(NSSet<NSAtomicStoreCacheNode> cacheNodes);
    @Method(selector = "willRemoveCacheNodes:")
    public native void willRemoveCacheNodes(NSSet<NSAtomicStoreCacheNode> cacheNodes);
    @Method(selector = "cacheNodeForObjectID:")
    public native NSAtomicStoreCacheNode getCacheNode(NSManagedObjectID objectID);
    @Method(selector = "objectIDForEntity:referenceObject:")
    public native NSManagedObjectID getObjectID(NSEntityDescription entity, NSObject data);
    @Method(selector = "newReferenceObjectForManagedObject:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSObject newReferenceObject(NSManagedObject managedObject);
    @Method(selector = "referenceObjectForObjectID:")
    public native NSObject getReferenceObject(NSManagedObjectID objectID);
    /*</methods>*/
}
