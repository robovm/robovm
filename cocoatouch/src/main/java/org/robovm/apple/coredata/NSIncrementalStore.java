/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
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
    public NSObject executeRequest(NSPersistentStoreRequest request, NSManagedObjectContext context) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSObject result = executeRequest(request, context, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "executeRequest:withContext:error:")
    private native NSObject executeRequest(NSPersistentStoreRequest request, NSManagedObjectContext context, NSError.NSErrorPtr error);
    public NSIncrementalStoreNode newValuesForObjectID(NSManagedObjectID objectID, NSManagedObjectContext context) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSIncrementalStoreNode result = newValuesForObjectID(objectID, context, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "newValuesForObjectWithID:withContext:error:")
    private native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSIncrementalStoreNode newValuesForObjectID(NSManagedObjectID objectID, NSManagedObjectContext context, NSError.NSErrorPtr error);
    public NSObject newValueForRelationship(NSRelationshipDescription relationship, NSManagedObjectID objectID, NSManagedObjectContext context) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSObject result = newValueForRelationship(relationship, objectID, context, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "newValueForRelationship:forObjectWithID:withContext:error:")
    private native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSObject newValueForRelationship(NSRelationshipDescription relationship, NSManagedObjectID objectID, NSManagedObjectContext context, NSError.NSErrorPtr error);
    public NSArray<NSManagedObjectID> obtainPermanentIDsForObjects(NSArray<NSManagedObject> array) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<NSManagedObjectID> result = obtainPermanentIDsForObjects(array, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "obtainPermanentIDsForObjects:error:")
    private native NSArray<NSManagedObjectID> obtainPermanentIDsForObjects(NSArray<NSManagedObject> array, NSError.NSErrorPtr error);
    @Method(selector = "managedObjectContextDidRegisterObjectsWithIDs:")
    public native void didRegisterObjects(NSArray<NSManagedObjectID> objectIDs);
    @Method(selector = "managedObjectContextDidUnregisterObjectsWithIDs:")
    public native void didUnregisterObjects(NSArray<NSManagedObjectID> objectIDs);
    @Method(selector = "newObjectIDForEntity:referenceObject:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSManagedObjectID newObjectIDForEntity(NSEntityDescription entity, NSObject data);
    @Method(selector = "referenceObjectForObjectID:")
    public native NSObject getReferenceObjectForID(NSManagedObjectID objectID);
    @Method(selector = "identifierForNewStoreAtURL:")
    public static native NSObject getIdentifierForNewStore(NSURL storeURL);
    /*</methods>*/
}
