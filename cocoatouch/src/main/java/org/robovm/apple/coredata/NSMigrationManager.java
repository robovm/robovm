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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMigrationManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMigrationManagerPtr extends Ptr<NSMigrationManager, NSMigrationManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMigrationManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMigrationManager() {}
    protected NSMigrationManager(SkipInit skipInit) { super(skipInit); }
    public NSMigrationManager(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel) { super((SkipInit) null); initObject(init(sourceModel, destinationModel)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "usesStoreSpecificMigrationManager")
    public native boolean usesStoreSpecificMigrationManager();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setUsesStoreSpecificMigrationManager:")
    public native void setUsesStoreSpecificMigrationManager(boolean v);
    @Property(selector = "mappingModel")
    public native NSMappingModel getMappingModel();
    @Property(selector = "sourceModel")
    public native NSManagedObjectModel getSourceModel();
    @Property(selector = "destinationModel")
    public native NSManagedObjectModel getDestinationModel();
    @Property(selector = "sourceContext")
    public native NSManagedObjectContext getSourceContext();
    @Property(selector = "destinationContext")
    public native NSManagedObjectContext getDestinationContext();
    @Property(selector = "currentEntityMapping")
    public native NSEntityMapping getCurrentEntityMapping();
    @Property(selector = "migrationProgress")
    public native float getMigrationProgress();
    @Property(selector = "userInfo")
    public native NSDictionary<?, ?> getUserInfo();
    @Property(selector = "setUserInfo:")
    public native void setUserInfo(NSDictionary<?, ?> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param sourceURL
     * @param sStoreType
     * @param sOptions
     * @param mappings
     * @param dURL
     * @param dStoreType
     * @param dOptions
     * @return
     * @throws NSErrorException
     */
    public boolean migrateStore(NSURL sourceURL, NSPersistentStoreType sStoreType, NSPersistentStoreOptions sOptions, NSMappingModel mappings, NSURL dURL, NSPersistentStoreType dStoreType, NSPersistentStoreOptions dOptions) throws NSErrorException {
        return migrateStore(sourceURL, sStoreType.value(), sOptions, mappings, dURL, dStoreType.value(), dOptions);
    }
    /**
     * 
     * @param sourceURL
     * @param sStoreType
     * @param sOptions
     * @param mappings
     * @param dURL
     * @param dStoreType
     * @param dOptions
     * @return
     * @throws NSErrorException
     */
    public boolean migrateStore(NSURL sourceURL, String sStoreType, NSPersistentStoreOptions sOptions, NSMappingModel mappings, NSURL dURL, String dStoreType, NSPersistentStoreOptions dOptions) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = migrateStore(sourceURL, sStoreType, sOptions, mappings, dURL, dStoreType, dOptions, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "initWithSourceModel:destinationModel:")
    protected native @Pointer long init(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel);
    @Method(selector = "migrateStoreFromURL:type:options:withMappingModel:toDestinationURL:destinationType:destinationOptions:error:")
    protected native boolean migrateStore(NSURL sourceURL, String sStoreType, NSPersistentStoreOptions sOptions, NSMappingModel mappings, NSURL dURL, String dStoreType, NSPersistentStoreOptions dOptions, NSError.NSErrorPtr error);
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "sourceEntityForEntityMapping:")
    public native NSEntityDescription getSourceEntity(NSEntityMapping mEntity);
    @Method(selector = "destinationEntityForEntityMapping:")
    public native NSEntityDescription getDestinationEntity(NSEntityMapping mEntity);
    @Method(selector = "associateSourceInstance:withDestinationInstance:forEntityMapping:")
    public native void associateInstances(NSManagedObject sourceInstance, NSManagedObject destinationInstance, NSEntityMapping entityMapping);
    @Method(selector = "destinationInstancesForEntityMappingNamed:sourceInstances:")
    public native NSArray<NSEntityMapping> getDestinationInstances(String mappingName, NSArray<NSEntityMapping> sourceInstances);
    @Method(selector = "sourceInstancesForEntityMappingNamed:destinationInstances:")
    public native NSArray<NSEntityMapping> getSourceInstances(String mappingName, NSArray<NSEntityMapping> destinationInstances);
    @Method(selector = "cancelMigrationWithError:")
    public native void cancelMigration(NSError error);
    /*</methods>*/
}
