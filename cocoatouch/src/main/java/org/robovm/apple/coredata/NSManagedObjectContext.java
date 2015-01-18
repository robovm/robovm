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

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSManagedObjectContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding, NSLocking/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 3.0 and later.
         */
        public static NSObject observeWillSave(NSManagedObject object, final VoidBlock1<NSManagedObject> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillSaveNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSManagedObject)a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 3.0 and later.
         */
        public static NSObject observeDidSave(NSManagedObject object, final VoidBlock2<NSManagedObject, NSManagedObjectContextNotificationInfo> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidSaveNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSManagedObject)a.getObject(), new NSManagedObjectContextNotificationInfo(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 3.0 and later.
         */
        public static NSObject observeObjectsDidChange(NSManagedObject object, final VoidBlock3<NSManagedObject, NSManagedObjectContextNotificationInfo, NSNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(ObjectsDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSManagedObject)a.getObject(), new NSManagedObjectContextNotificationInfo(a.getUserInfo()), a);
                }
            });
        }
        
    }
    
    /*<ptr>*/public static class NSManagedObjectContextPtr extends Ptr<NSManagedObjectContext, NSManagedObjectContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSManagedObjectContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSManagedObjectContext() {}
    protected NSManagedObjectContext(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSManagedObjectContext(NSManagedObjectContextConcurrencyType ct) { super((SkipInit) null); initObject(init(ct)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "persistentStoreCoordinator")
    public native NSPersistentStoreCoordinator getPersistentStoreCoordinator();
    @Property(selector = "setPersistentStoreCoordinator:")
    public native void setPersistentStoreCoordinator(NSPersistentStoreCoordinator v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "parentContext")
    public native NSManagedObjectContext getParentContext();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setParentContext:")
    public native void setParentContext(NSManagedObjectContext v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "name")
    public native String getName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "undoManager")
    public native NSUndoManager getUndoManager();
    @Property(selector = "setUndoManager:")
    public native void setUndoManager(NSUndoManager v);
    @Property(selector = "hasChanges")
    public native boolean hasChanges();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "userInfo")
    public native NSMutableDictionary<?, ?> getUserInfo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "concurrencyType")
    public native NSManagedObjectContextConcurrencyType getConcurrencyType();
    @Property(selector = "insertedObjects")
    public native NSSet<NSManagedObject> getInsertedObjects();
    @Property(selector = "updatedObjects")
    public native NSSet<NSManagedObject> getUpdatedObjects();
    @Property(selector = "deletedObjects")
    public native NSSet<NSManagedObject> getDeletedObjects();
    @Property(selector = "registeredObjects")
    public native NSSet<NSManagedObject> getRegisteredObjects();
    @Property(selector = "propagatesDeletesAtEndOfEvent")
    public native boolean propagatesDeletesAtEndOfEvent();
    @Property(selector = "setPropagatesDeletesAtEndOfEvent:")
    public native void setPropagatesDeletesAtEndOfEvent(boolean v);
    @Property(selector = "retainsRegisteredObjects")
    public native NSSet<NSManagedObject> getRetainsRegisteredObjects();
    @Property(selector = "setRetainsRegisteredObjects:")
    public native void setRetainsRegisteredObjects(NSSet<NSManagedObject> v);
    @Property(selector = "stalenessInterval")
    public native double getStalenessInterval();
    @Property(selector = "setStalenessInterval:")
    public native void setStalenessInterval(double v);
    @Property(selector = "mergePolicy")
    public native NSObject getMergePolicy();
    @Property(selector = "setMergePolicy:")
    public native void setMergePolicy(NSObject v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void observeValue(String keyPath, NSObject object, NSKeyValueChangeInfo change) {}
    
    @Method(selector = "observeValueForKeyPath:ofObject:change:context:")
    private void observeValueForKeyPath(String keyPath, NSObject object, NSKeyValueChangeInfo change, VoidPtr context) {
        observeValue(keyPath, object, change);
    }
    
    /**
     * 
     * @param objectID
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public NSManagedObject getExistingObjectWithID(NSManagedObjectID objectID) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSManagedObject result = getExistingObjectWithID(objectID, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param request
     * @return
     * @throws NSErrorException
     */
    public NSArray<NSManagedObject> executeFetchRequest(NSFetchRequest request) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSArray<NSManagedObject> result = executeFetchRequest(request, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param request
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public @MachineSizedUInt long getCountForFetchRequest(NSFetchRequest request) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        long result = getCountForFetchRequest(request, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param request
     * @return
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public NSPersistentStoreResult executeRequest(NSPersistentStoreRequest request) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSPersistentStoreResult result = executeRequest(request, err);
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
    /**
     * 
     * @param objects
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public boolean obtainPermanentIDsForObjects(NSArray<NSManagedObject> objects) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = obtainPermanentIDsForObjects(objects, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextWillSaveNotification", optional=true)
    protected static native NSString WillSaveNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextDidSaveNotification", optional=true)
    protected static native NSString DidSaveNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextObjectsDidChangeNotification", optional=true)
    protected static native NSString ObjectsDidChangeNotification();
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithConcurrencyType:")
    protected native @Pointer long init(NSManagedObjectContextConcurrencyType ct);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "performBlock:")
    public native void performBlock(@Block Runnable block);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "performBlockAndWait:")
    public native void performBlockAndWait(@Block Runnable block);
    @Method(selector = "objectRegisteredForID:")
    public native NSManagedObject getObjectRegisteredForID(NSManagedObjectID objectID);
    @Method(selector = "objectWithID:")
    public native NSManagedObject getObjectWithId(NSManagedObjectID objectID);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "existingObjectWithID:error:")
    protected native NSManagedObject getExistingObjectWithID(NSManagedObjectID objectID, NSError.NSErrorPtr error);
    @Method(selector = "executeFetchRequest:error:")
    protected native NSArray<NSManagedObject> executeFetchRequest(NSFetchRequest request, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "countForFetchRequest:error:")
    protected native @MachineSizedUInt long getCountForFetchRequest(NSFetchRequest request, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "executeRequest:error:")
    protected native NSPersistentStoreResult executeRequest(NSPersistentStoreRequest request, NSError.NSErrorPtr error);
    @Method(selector = "insertObject:")
    public native void insertObject(NSManagedObject object);
    @Method(selector = "deleteObject:")
    public native void deleteObject(NSManagedObject object);
    @Method(selector = "refreshObject:mergeChanges:")
    public native void refreshObject(NSManagedObject object, boolean flag);
    @Method(selector = "detectConflictsForObject:")
    public native void detectConflicts(NSManagedObject object);
    @Method(selector = "processPendingChanges")
    public native void processPendingChanges();
    @Method(selector = "assignObject:toPersistentStore:")
    public native void assignObjectToPersistentStore(NSObject object, NSPersistentStore store);
    @Method(selector = "undo")
    public native void undo();
    @Method(selector = "redo")
    public native void redo();
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "rollback")
    public native void rollback();
    @Method(selector = "save:")
    protected native boolean save(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "lock")
    public native void lock();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "unlock")
    public native void unlock();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "tryLock")
    public native boolean tryLock();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "obtainPermanentIDsForObjects:error:")
    protected native boolean obtainPermanentIDsForObjects(NSArray<NSManagedObject> objects, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "mergeChangesFromContextDidSaveNotification:")
    public native void mergeChangesFromContextDidSaveNotification(NSNotification notification);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
