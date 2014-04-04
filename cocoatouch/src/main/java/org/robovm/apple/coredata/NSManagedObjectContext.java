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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSManagedObjectContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, NSLocking/*</implements>*/ {

    /*<ptr>*/public static class NSManagedObjectContextPtr extends Ptr<NSManagedObjectContext, NSManagedObjectContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSManagedObjectContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSManagedObjectContext() {}
    protected NSManagedObjectContext(SkipInit skipInit) { super(skipInit); }
    public NSManagedObjectContext(NSManagedObjectContextConcurrencyType ct) { super((SkipInit) null); initObject(initWithConcurrencyType$(ct)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSManagedObjectContextWillSaveNotification", optional=true)
    public static native String NotificationWillSave();
    @GlobalValue(symbol="NSManagedObjectContextDidSaveNotification", optional=true)
    public static native String NotificationDidSave();
    @GlobalValue(symbol="NSManagedObjectContextObjectsDidChangeNotification", optional=true)
    public static native String NotificationObjectsDidChange();
    @GlobalValue(symbol="NSInsertedObjectsKey", optional=true)
    public static native NSString KeyInsertedObjects();
    @GlobalValue(symbol="NSUpdatedObjectsKey", optional=true)
    public static native NSString KeyUpdatedObjects();
    @GlobalValue(symbol="NSDeletedObjectsKey", optional=true)
    public static native NSString KeyDeletedObjects();
    @GlobalValue(symbol="NSRefreshedObjectsKey", optional=true)
    public static native NSString KeyRefreshedObjects();
    @GlobalValue(symbol="NSInvalidatedObjectsKey", optional=true)
    public static native NSString KeyInvalidatedObjects();
    @GlobalValue(symbol="NSInvalidatedAllObjectsKey", optional=true)
    public static native NSString KeyInvalidatedAllObjects();
    
    @Method(selector = "initWithConcurrencyType:")
    protected native @Pointer long initWithConcurrencyType$(NSManagedObjectContextConcurrencyType ct);
    @Method(selector = "performBlock:")
    public native void performBlock$(@Block Runnable block);
    @Method(selector = "performBlockAndWait:")
    public native void performBlockAndWait$(@Block Runnable block);
    @Method(selector = "setPersistentStoreCoordinator:")
    public native void setPersistentStoreCoordinator(NSPersistentStoreCoordinator coordinator);
    @Method(selector = "persistentStoreCoordinator")
    public native NSPersistentStoreCoordinator persistentStoreCoordinator();
    @Method(selector = "setParentContext:")
    public native void setParentContext(NSManagedObjectContext parent);
    @Method(selector = "parentContext")
    public native NSManagedObjectContext parentContext();
    @Method(selector = "setUndoManager:")
    public native void setUndoManager(NSUndoManager undoManager);
    @Method(selector = "undoManager")
    public native NSUndoManager undoManager();
    @Method(selector = "hasChanges")
    public native boolean hasChanges();
    @Method(selector = "userInfo")
    public native NSMutableDictionary<?, ?> userInfo();
    @Method(selector = "concurrencyType")
    public native NSManagedObjectContextConcurrencyType concurrencyType();
    @Method(selector = "objectRegisteredForID:")
    public native NSManagedObject objectRegisteredForID$(NSManagedObjectID objectID);
    @Method(selector = "objectWithID:")
    public native NSManagedObject objectWithID$(NSManagedObjectID objectID);
    @Method(selector = "existingObjectWithID:error:")
    public native NSManagedObject existingObjectWithID$error$(NSManagedObjectID objectID, NSError.NSErrorPtr error);
    @Method(selector = "executeFetchRequest:error:")
    public native NSArray<?> executeFetchRequest$error$(NSFetchRequest request, NSError.NSErrorPtr error);
    @Method(selector = "countForFetchRequest:error:")
    public native @MachineSizedUInt long countForFetchRequest$error$(NSFetchRequest request, NSError.NSErrorPtr error);
    @Method(selector = "insertObject:")
    public native void insertObject$(NSManagedObject object);
    @Method(selector = "deleteObject:")
    public native void deleteObject$(NSManagedObject object);
    @Method(selector = "refreshObject:mergeChanges:")
    public native void refreshObject$mergeChanges$(NSManagedObject object, boolean flag);
    @Method(selector = "detectConflictsForObject:")
    public native void detectConflictsForObject$(NSManagedObject object);
    @Method(selector = "observeValueForKeyPath:ofObject:change:context:")
    public native void observeValueForKeyPath$ofObject$change$context$(String keyPath, NSObject object, NSDictionary<?, ?> change, VoidPtr context);
    @Method(selector = "processPendingChanges")
    public native void processPendingChanges();
    @Method(selector = "assignObject:toPersistentStore:")
    public native void assignObject$toPersistentStore$(NSObject object, NSPersistentStore store);
    @Method(selector = "insertedObjects")
    public native NSSet<?> insertedObjects();
    @Method(selector = "updatedObjects")
    public native NSSet<?> updatedObjects();
    @Method(selector = "deletedObjects")
    public native NSSet<?> deletedObjects();
    @Method(selector = "registeredObjects")
    public native NSSet<?> registeredObjects();
    @Method(selector = "undo")
    public native void undo();
    @Method(selector = "redo")
    public native void redo();
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "rollback")
    public native void rollback();
    @Method(selector = "save:")
    public native boolean save$(NSError.NSErrorPtr error);
    @Method(selector = "lock")
    public native void lock();
    @Method(selector = "unlock")
    public native void unlock();
    @Method(selector = "tryLock")
    public native boolean tryLock();
    @Method(selector = "propagatesDeletesAtEndOfEvent")
    public native boolean propagatesDeletesAtEndOfEvent();
    @Method(selector = "setPropagatesDeletesAtEndOfEvent:")
    public native void setPropagatesDeletesAtEndOfEvent(boolean flag);
    @Method(selector = "retainsRegisteredObjects")
    public native boolean retainsRegisteredObjects();
    @Method(selector = "setRetainsRegisteredObjects:")
    public native void setRetainsRegisteredObjects(boolean flag);
    @Method(selector = "stalenessInterval")
    public native double stalenessInterval();
    @Method(selector = "setStalenessInterval:")
    public native void setStalenessInterval(double expiration);
    @Method(selector = "setMergePolicy:")
    public native void setMergePolicy(NSObject mergePolicy);
    @Method(selector = "mergePolicy")
    public native NSObject mergePolicy();
    @Method(selector = "obtainPermanentIDsForObjects:error:")
    public native boolean obtainPermanentIDsForObjects$error$(NSArray<?> objects, NSError.NSErrorPtr error);
    @Method(selector = "mergeChangesFromContextDidSaveNotification:")
    public native void mergeChangesFromContextDidSaveNotification$(NSNotification notification);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
