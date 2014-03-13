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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSIncrementalStore/*</name>*/ 
    extends /*<extends>*/NSPersistentStore/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSIncrementalStorePtr extends Ptr<NSIncrementalStore, NSIncrementalStorePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSIncrementalStore.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSIncrementalStore() {}
    protected NSIncrementalStore(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "loadMetadata:")
    public native boolean loadMetadata$(NSError.NSErrorPtr error);
    @Method(selector = "executeRequest:withContext:error:")
    public native NSObject executeRequest$withContext$error$(NSPersistentStoreRequest request, NSManagedObjectContext context, NSError.NSErrorPtr error);
    @Method(selector = "newValuesForObjectWithID:withContext:error:")
    public native NSIncrementalStoreNode newValuesForObjectWithID$withContext$error$(NSManagedObjectID objectID, NSManagedObjectContext context, NSError.NSErrorPtr error);
    @Method(selector = "newValueForRelationship:forObjectWithID:withContext:error:")
    public native NSObject newValueForRelationship$forObjectWithID$withContext$error$(NSRelationshipDescription relationship, NSManagedObjectID objectID, NSManagedObjectContext context, NSError.NSErrorPtr error);
    @Method(selector = "obtainPermanentIDsForObjects:error:")
    public native NSArray<?> obtainPermanentIDsForObjects$error$(NSArray<?> array, NSError.NSErrorPtr error);
    @Method(selector = "managedObjectContextDidRegisterObjectsWithIDs:")
    public native void managedObjectContextDidRegisterObjectsWithIDs$(NSArray<?> objectIDs);
    @Method(selector = "managedObjectContextDidUnregisterObjectsWithIDs:")
    public native void managedObjectContextDidUnregisterObjectsWithIDs$(NSArray<?> objectIDs);
    @Method(selector = "newObjectIDForEntity:referenceObject:")
    public native NSManagedObjectID newObjectIDForEntity$referenceObject$(NSEntityDescription entity, NSObject data);
    @Method(selector = "referenceObjectForObjectID:")
    public native NSObject referenceObjectForObjectID$(NSManagedObjectID objectID);
    @Method(selector = "identifierForNewStoreAtURL:")
    public static native NSObject identifierForNewStoreAtURL$(NSURL storeURL);
    /*</methods>*/
}
