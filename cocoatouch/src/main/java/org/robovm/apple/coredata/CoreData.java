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
/*</javadoc>*/
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreData/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreData.class); }/*</bind>*/
    /*<constants>*/
    public static final double CoreDataVersionNumber10_4 = 46.0;
    public static final double CoreDataVersionNumber10_4_3 = 77.0;
    public static final double CoreDataVersionNumber10_5 = 185.0;
    public static final double CoreDataVersionNumber10_5_3 = 186.0;
    public static final double CoreDataVersionNumber10_6 = 246.0;
    public static final double CoreDataVersionNumber10_6_2 = 250.0;
    public static final double CoreDataVersionNumber10_6_3 = 251.0;
    public static final double CoreDataVersionNumber10_7 = 358.4;
    public static final double CoreDataVersionNumber10_7_2 = 358.12;
    public static final double CoreDataVersionNumber10_7_3 = 358.13;
    public static final double CoreDataVersionNumber10_7_4 = 358.14;
    public static final double CoreDataVersionNumber10_8 = 407.5;
    public static final double CoreDataVersionNumber10_8_2 = 407.7;
    public static final double CoreDataVersionNumber_iPhoneOS_3_0 = 241.0;
    public static final double CoreDataVersionNumber_iPhoneOS_3_1 = 248.0;
    public static final double CoreDataVersionNumber_iPhoneOS_3_2 = 310.2;
    public static final double CoreDataVersionNumber_iPhoneOS_4_0 = 320.5;
    public static final double CoreDataVersionNumber_iPhoneOS_4_1 = 320.11;
    public static final double CoreDataVersionNumber_iPhoneOS_4_2 = 320.15;
    public static final double CoreDataVersionNumber_iPhoneOS_4_3 = 320.17;
    public static final double CoreDataVersionNumber_iPhoneOS_5_0 = 386.1;
    public static final double CoreDataVersionNumber_iPhoneOS_5_1 = 386.5;
    public static final double CoreDataVersionNumber_iPhoneOS_6_0 = 419.0;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSCoreDataVersionNumber", optional=true)
    public static native double CoreDataVersionNumber();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSDetailedErrorsKey", optional=true)
    public static native NSString DetailedErrorsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationObjectErrorKey", optional=true)
    public static native NSString ValidationObjectErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationKeyErrorKey", optional=true)
    public static native NSString ValidationKeyErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationPredicateErrorKey", optional=true)
    public static native NSString ValidationPredicateErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationValueErrorKey", optional=true)
    public static native NSString ValidationValueErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedStoresErrorKey", optional=true)
    public static native NSString AffectedStoresErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedObjectsErrorKey", optional=true)
    public static native NSString AffectedObjectsErrorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreSaveConflictsErrorKey", optional=true)
    public static native NSString PersistentStoreSaveConflictsErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteErrorDomain", optional=true)
    public static native NSString SQLiteErrorDomain();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextWillSaveNotification", optional=true)
    public static native NSString ManagedObjectContextWillSaveNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextDidSaveNotification", optional=true)
    public static native NSString ManagedObjectContextDidSaveNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSManagedObjectContextObjectsDidChangeNotification", optional=true)
    public static native NSString ManagedObjectContextObjectsDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInsertedObjectsKey", optional=true)
    public static native NSString InsertedObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUpdatedObjectsKey", optional=true)
    public static native NSString UpdatedObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSDeletedObjectsKey", optional=true)
    public static native NSString DeletedObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRefreshedObjectsKey", optional=true)
    public static native NSString RefreshedObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInvalidatedObjectsKey", optional=true)
    public static native NSString InvalidatedObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInvalidatedAllObjectsKey", optional=true)
    public static native NSString InvalidatedAllObjectsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSErrorMergePolicy", optional=true)
    public static native NSObject ErrorMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMergeByPropertyStoreTrumpMergePolicy", optional=true)
    public static native NSObject MergeByPropertyStoreTrumpMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMergeByPropertyObjectTrumpMergePolicy", optional=true)
    public static native NSObject MergeByPropertyObjectTrumpMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSOverwriteMergePolicy", optional=true)
    public static native NSObject OverwriteMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRollbackMergePolicy", optional=true)
    public static native NSObject RollbackMergePolicy();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteStoreType", optional=true)
    public static native String SQLiteStoreType();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSBinaryStoreType", optional=true)
    public static native String BinaryStoreType();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInMemoryStoreType", optional=true)
    public static native String InMemoryStoreType();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreTypeKey", optional=true)
    public static native NSString StoreTypeKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreUUIDKey", optional=true)
    public static native NSString StoreUUIDKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresWillChangeNotification", optional=true)
    public static native NSString PersistentStoreCoordinatorStoresWillChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresDidChangeNotification", optional=true)
    public static native NSString PersistentStoreCoordinatorStoresDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorWillRemoveStoreNotification", optional=true)
    public static native NSString PersistentStoreCoordinatorWillRemoveStoreNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAddedPersistentStoresKey", optional=true)
    public static native NSString AddedPersistentStoresKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRemovedPersistentStoresKey", optional=true)
    public static native NSString RemovedPersistentStoresKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUUIDChangedPersistentStoresKey", optional=true)
    public static native NSString UUIDChangedPersistentStoresKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSReadOnlyPersistentStoreOption", optional=true)
    public static native NSString ReadOnlyPersistentStoreOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreTimeoutOption", optional=true)
    public static native NSString PersistentStoreTimeoutOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLitePragmasOption", optional=true)
    public static native NSString SQLitePragmasOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteAnalyzeOption", optional=true)
    public static native NSString SQLiteAnalyzeOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteManualVacuumOption", optional=true)
    public static native NSString SQLiteManualVacuumOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIgnorePersistentStoreVersioningOption", optional=true)
    public static native NSString IgnorePersistentStoreVersioningOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigratePersistentStoresAutomaticallyOption", optional=true)
    public static native NSString MigratePersistentStoresAutomaticallyOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInferMappingModelAutomaticallyOption", optional=true)
    public static native NSString InferMappingModelAutomaticallyOption();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreModelVersionHashesKey", optional=true)
    public static native NSString StoreModelVersionHashesKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreModelVersionIdentifiersKey", optional=true)
    public static native NSString StoreModelVersionIdentifiersKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreOSCompatibility", optional=true)
    public static native NSString PersistentStoreOSCompatibility();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContentNameKey", optional=true)
    public static native NSString PersistentStoreUbiquitousContentNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContentURLKey", optional=true)
    public static native NSString PersistentStoreUbiquitousContentURLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreDidImportUbiquitousContentChangesNotification", optional=true)
    public static native NSString PersistentStoreDidImportUbiquitousContentChangesNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousTransitionTypeKey", optional=true)
    public static native NSString PersistentStoreUbiquitousTransitionTypeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousPeerTokenOption", optional=true)
    public static native NSString PersistentStoreUbiquitousPeerTokenOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreRemoveUbiquitousMetadataOption", optional=true)
    public static native NSString PersistentStoreRemoveUbiquitousMetadataOption();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousContainerIdentifierKey", optional=true)
    public static native NSString PersistentStoreUbiquitousContainerIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreRebuildFromUbiquitousContentOption", optional=true)
    public static native NSString PersistentStoreRebuildFromUbiquitousContentOption();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreFileProtectionKey", optional=true)
    public static native NSString PersistentStoreFileProtectionKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationManagerKey", optional=true)
    public static native NSString MigrationManagerKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationSourceObjectKey", optional=true)
    public static native NSString MigrationSourceObjectKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationDestinationObjectKey", optional=true)
    public static native NSString MigrationDestinationObjectKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityMappingKey", optional=true)
    public static native NSString MigrationEntityMappingKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationPropertyMappingKey", optional=true)
    public static native NSString MigrationPropertyMappingKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityPolicyKey", optional=true)
    public static native NSString MigrationEntityPolicyKey();
    /*</methods>*/
}
