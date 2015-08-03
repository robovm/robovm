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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreCoordinator/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSLocking/*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeStoresWillChange(NSPersistentStoreCoordinator object, final VoidBlock2<NSPersistentStoreCoordinator, NSPersistentStoreCoordinatorChangeNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(StoresWillChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> userInfo = a.getUserInfo();
                    NSPersistentStoreCoordinatorChangeNotification data = null;
                    if (userInfo != null) {
                        data = new NSPersistentStoreCoordinatorChangeNotification(userInfo);
                    }
                    block.invoke((NSPersistentStoreCoordinator)a.getObject(), data);
                }
            });
        }
        /**
         * @since Available in iOS 3.0 and later.
         */
        public static NSObject observeStoresDidChange(NSPersistentStoreCoordinator object, final VoidBlock2<NSPersistentStoreCoordinator, NSPersistentStoreCoordinatorChangeNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(StoresDidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    NSDictionary<NSString, NSObject> userInfo = a.getUserInfo();
                    NSPersistentStoreCoordinatorChangeNotification data = null;
                    if (userInfo != null) {
                        data = new NSPersistentStoreCoordinatorChangeNotification(userInfo);
                    }
                    block.invoke((NSPersistentStoreCoordinator)a.getObject(), data);
                }
            });
        }
        /**
         * @since Available in iOS 3.0 and later.
         */
        public static NSObject observeWillRemoveStore(NSPersistentStoreCoordinator object, final VoidBlock1<NSPersistentStoreCoordinator> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillRemoveStoreNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSPersistentStoreCoordinator)a.getObject());
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeDidImportUbiquitousContentChanges(NSPersistentStoreCoordinator object, final VoidBlock2<NSPersistentStoreCoordinator, NSNotification> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(WillRemoveStoreNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSPersistentStoreCoordinator)a.getObject(), a);
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSPersistentStoreCoordinatorPtr extends Ptr<NSPersistentStoreCoordinator, NSPersistentStoreCoordinatorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPersistentStoreCoordinator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPersistentStoreCoordinator() {}
    protected NSPersistentStoreCoordinator(SkipInit skipInit) { super(skipInit); }
    public NSPersistentStoreCoordinator(NSManagedObjectModel model) { super((SkipInit) null); initObject(init(model)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "managedObjectModel")
    public native NSManagedObjectModel getManagedObjectModel();
    @Property(selector = "persistentStores")
    public native NSArray<NSPersistentStore> getPersistentStores();
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
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param storeType
     * @param configuration
     * @param storeURL
     * @param options
     * @return
     * @throws NSErrorException
     */
    public NSPersistentStore addPersistentStore(NSPersistentStoreType storeType, String configuration, NSURL storeURL, NSPersistentStoreOptions options) throws NSErrorException {
        return addPersistentStore(storeType.value().toString(), configuration, storeURL, options);
    }
    /**
     * 
     * @param store
     * @param URL
     * @param options
     * @param storeType
     * @return
     * @throws NSErrorException
     */
    public NSPersistentStore migratePersistentStore(NSPersistentStore store, NSURL URL, NSPersistentStoreOptions options, NSPersistentStoreType storeType) throws NSErrorException {
        return migratePersistentStore(store, URL, options, storeType.value().toString());
    }
    /**
     * 
     * @param storeClass
     * @param storeType
     * @since Available in iOS 3.0 and later.
     */
    public static void registerStoreClassForType(Class<? extends NSPersistentStore> storeClass, NSPersistentStoreType storeType) {
        registerStoreClassForType(storeClass, storeType.value().toString());
    }
    /**
     * 
     * @param storeType
     * @param url
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public static NSPersistentStoreMetadata getMetadataForPersistentStoreType(NSPersistentStoreType storeType, NSURL url) throws NSErrorException {
        return getMetadataForPersistentStoreType(storeType.value().toString(), url);
    }
    /**
     * 
     * @param metadata
     * @param storeType
     * @param url
     * @return
     * @since Available in iOS 3.0 and later.
     * @throws NSErrorException
     */
    public static boolean setMetadataForPersistentStoreType(NSPersistentStoreMetadata metadata, NSPersistentStoreType storeType, NSURL url) throws NSErrorException {
        return setMetadataForPersistentStoreType(metadata, storeType.value().toString(), url);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresWillChangeNotification", optional=true)
    public static native NSString StoresWillChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresDidChangeNotification", optional=true)
    public static native NSString StoresDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorWillRemoveStoreNotification", optional=true)
    public static native NSString WillRemoveStoreNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreDidImportUbiquitousContentChangesNotification", optional=true)
    public static native NSString DidImportUbiquitousContentChangesNotification();
    
    @Method(selector = "initWithManagedObjectModel:")
    protected native @Pointer long init(NSManagedObjectModel model);
    @Method(selector = "persistentStoreForURL:")
    public native NSPersistentStore getPersistentStoreForURL(NSURL URL);
    @Method(selector = "URLForPersistentStore:")
    public native NSURL getURLForPersistentStore(NSPersistentStore store);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setURL:forPersistentStore:")
    public native boolean setURLForPersistentStore(NSURL url, NSPersistentStore store);
    public NSPersistentStore addPersistentStore(String storeType, String configuration, NSURL storeURL, NSPersistentStoreOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPersistentStore result = addPersistentStore(storeType, configuration, storeURL, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "addPersistentStoreWithType:configuration:URL:options:error:")
    private native NSPersistentStore addPersistentStore(String storeType, String configuration, NSURL storeURL, NSPersistentStoreOptions options, NSError.NSErrorPtr error);
    public boolean removePersistentStore(NSPersistentStore store) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removePersistentStore(store, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "removePersistentStore:error:")
    private native boolean removePersistentStore(NSPersistentStore store, NSError.NSErrorPtr error);
    @Method(selector = "setMetadata:forPersistentStore:")
    public native void setMetadataForPersistentStore(NSPersistentStoreMetadata metadata, NSPersistentStore store);
    @Method(selector = "metadataForPersistentStore:")
    public native NSDictionary getMetadataForPersistentStore(NSPersistentStore store);
    @Method(selector = "managedObjectIDForURIRepresentation:")
    public native NSManagedObjectID getManagedObjectIDForURIRepresentation(NSURL url);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSObject executeRequest(NSPersistentStoreRequest request, NSManagedObjectContext context) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSObject result = executeRequest(request, context, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "executeRequest:withContext:error:")
    private native NSObject executeRequest(NSPersistentStoreRequest request, NSManagedObjectContext context, NSError.NSErrorPtr error);
    public NSPersistentStore migratePersistentStore(NSPersistentStore store, NSURL URL, NSPersistentStoreOptions options, String storeType) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPersistentStore result = migratePersistentStore(store, URL, options, storeType, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "migratePersistentStore:toURL:options:withType:error:")
    private native NSPersistentStore migratePersistentStore(NSPersistentStore store, NSURL URL, NSPersistentStoreOptions options, String storeType, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "performBlock:")
    public native void performBlock(@Block Runnable block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "performBlockAndWait:")
    public native void performBlockAndWait(@Block Runnable block);
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
    @Method(selector = "registeredStoreTypes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSPersistentStore> getRegisteredStoreTypes();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "registerStoreClass:forStoreType:")
    public static native void registerStoreClassForType(Class<? extends NSPersistentStore> storeClass, String storeType);
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static NSPersistentStoreMetadata getMetadataForPersistentStoreType(String storeType, NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPersistentStoreMetadata result = getMetadataForPersistentStoreType(storeType, url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "metadataForPersistentStoreOfType:URL:error:")
    private static native NSPersistentStoreMetadata getMetadataForPersistentStoreType(String storeType, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static boolean setMetadataForPersistentStoreType(NSPersistentStoreMetadata metadata, String storeType, NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setMetadataForPersistentStoreType(metadata, storeType, url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setMetadata:forPersistentStoreOfType:URL:error:")
    private static native boolean setMetadataForPersistentStoreType(NSPersistentStoreMetadata metadata, String storeType, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static boolean removeUbiquitousContentAndPersistentStore(NSURL storeURL, NSPersistentStoreOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeUbiquitousContentAndPersistentStore(storeURL, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeUbiquitousContentAndPersistentStoreAtURL:options:error:")
    private static native boolean removeUbiquitousContentAndPersistentStore(NSURL storeURL, NSPersistentStoreOptions options, NSError.NSErrorPtr error);
    /*</methods>*/
}
