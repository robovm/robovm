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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMigrationManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMigrationManagerPtr extends Ptr<NSMigrationManager, NSMigrationManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMigrationManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMigrationManager() {}
    protected NSMigrationManager(SkipInit skipInit) { super(skipInit); }
    public NSMigrationManager(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel) { super((SkipInit) null); initObject(initWithSourceModel$destinationModel$(sourceModel, destinationModel)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSourceModel:destinationModel:")
    protected native @Pointer long initWithSourceModel$destinationModel$(NSManagedObjectModel sourceModel, NSManagedObjectModel destinationModel);
    @Method(selector = "migrateStoreFromURL:type:options:withMappingModel:toDestinationURL:destinationType:destinationOptions:error:")
    public native boolean migrateStoreFromURL$type$options$withMappingModel$toDestinationURL$destinationType$destinationOptions$error$(NSURL sourceURL, String sStoreType, NSDictionary<?, ?> sOptions, NSMappingModel mappings, NSURL dURL, String dStoreType, NSDictionary<?, ?> dOptions, NSError.NSErrorPtr error);
    @Method(selector = "setUsesStoreSpecificMigrationManager:")
    public native void setUsesStoreSpecificMigrationManager$(boolean flag);
    @Method(selector = "usesStoreSpecificMigrationManager")
    public native boolean usesStoreSpecificMigrationManager();
    @Method(selector = "reset")
    public native void reset();
    @Method(selector = "mappingModel")
    public native NSMappingModel mappingModel();
    @Method(selector = "sourceModel")
    public native NSManagedObjectModel sourceModel();
    @Method(selector = "destinationModel")
    public native NSManagedObjectModel destinationModel();
    @Method(selector = "sourceContext")
    public native NSManagedObjectContext sourceContext();
    @Method(selector = "destinationContext")
    public native NSManagedObjectContext destinationContext();
    @Method(selector = "sourceEntityForEntityMapping:")
    public native NSEntityDescription sourceEntityForEntityMapping$(NSEntityMapping mEntity);
    @Method(selector = "destinationEntityForEntityMapping:")
    public native NSEntityDescription destinationEntityForEntityMapping$(NSEntityMapping mEntity);
    @Method(selector = "associateSourceInstance:withDestinationInstance:forEntityMapping:")
    public native void associateSourceInstance$withDestinationInstance$forEntityMapping$(NSManagedObject sourceInstance, NSManagedObject destinationInstance, NSEntityMapping entityMapping);
    @Method(selector = "destinationInstancesForEntityMappingNamed:sourceInstances:")
    public native NSArray<?> destinationInstancesForEntityMappingNamed$sourceInstances$(String mappingName, NSArray<?> sourceInstances);
    @Method(selector = "sourceInstancesForEntityMappingNamed:destinationInstances:")
    public native NSArray<?> sourceInstancesForEntityMappingNamed$destinationInstances$(String mappingName, NSArray<?> destinationInstances);
    @Method(selector = "currentEntityMapping")
    public native NSEntityMapping currentEntityMapping();
    @Method(selector = "migrationProgress")
    public native float migrationProgress();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "setUserInfo:")
    public native void setUserInfo$(NSDictionary<?, ?> dict);
    @Method(selector = "cancelMigrationWithError:")
    public native void cancelMigrationWithError$(NSError error);
    /*</methods>*/
}
