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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSManagedObject/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSManagedObjectPtr extends Ptr<NSManagedObject, NSManagedObjectPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSManagedObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSManagedObject() {}
    protected NSManagedObject(SkipInit skipInit) { super(skipInit); }
    public NSManagedObject(NSEntityDescription entity, NSManagedObjectContext context) { super((SkipInit) null); initObject(initWithEntity$insertIntoManagedObjectContext$(entity, context)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithEntity:insertIntoManagedObjectContext:")
    protected native @Pointer long initWithEntity$insertIntoManagedObjectContext$(NSEntityDescription entity, NSManagedObjectContext context);
    @Method(selector = "managedObjectContext")
    public native NSManagedObjectContext managedObjectContext();
    @Method(selector = "entity")
    public native NSEntityDescription entity();
    @Method(selector = "objectID")
    public native NSManagedObjectID objectID();
    @Method(selector = "isInserted")
    public native boolean isInserted();
    @Method(selector = "isUpdated")
    public native boolean isUpdated();
    @Method(selector = "isDeleted")
    public native boolean isDeleted();
    @Method(selector = "hasChanges")
    public native boolean hasChanges();
    @Method(selector = "isFault")
    public native boolean isFault();
    @Method(selector = "hasFaultForRelationshipNamed:")
    public native boolean hasFaultForRelationshipNamed$(String key);
    @Method(selector = "faultingState")
    public native @MachineSizedUInt long faultingState();
    @Method(selector = "willAccessValueForKey:")
    public native void willAccessValueForKey$(String key);
    @Method(selector = "didAccessValueForKey:")
    public native void didAccessValueForKey$(String key);
    @Method(selector = "willChangeValueForKey:")
    public native void willChangeValueForKey$(String key);
    @Method(selector = "didChangeValueForKey:")
    public native void didChangeValueForKey$(String key);
    @Method(selector = "willChangeValueForKey:withSetMutation:usingObjects:")
    public native void willChangeValueForKey$withSetMutation$usingObjects$(String inKey, NSKeyValueSetMutationKind inMutationKind, NSSet<?> inObjects);
    @Method(selector = "didChangeValueForKey:withSetMutation:usingObjects:")
    public native void didChangeValueForKey$withSetMutation$usingObjects$(String inKey, NSKeyValueSetMutationKind inMutationKind, NSSet<?> inObjects);
    @Method(selector = "setObservationInfo:")
    public native void setObservationInfo$(NSObject inObservationInfo);
    @Method(selector = "observationInfo")
    public native NSObject observationInfo();
    @Method(selector = "awakeFromFetch")
    public native void awakeFromFetch();
    @Method(selector = "awakeFromInsert")
    public native void awakeFromInsert();
    @Method(selector = "awakeFromSnapshotEvents:")
    public native void awakeFromSnapshotEvents$(NSSnapshotEventType flags);
    @Method(selector = "prepareForDeletion")
    public native void prepareForDeletion();
    @Method(selector = "willSave")
    public native void willSave();
    @Method(selector = "didSave")
    public native void didSave();
    @Method(selector = "willTurnIntoFault")
    public native void willTurnIntoFault();
    @Method(selector = "didTurnIntoFault")
    public native void didTurnIntoFault();
    @Method(selector = "valueForKey:")
    public native NSObject valueForKey$(String key);
    @Method(selector = "setValue:forKey:")
    public native void setValue$forKey$(NSObject value, String key);
    @Method(selector = "primitiveValueForKey:")
    public native NSObject primitiveValueForKey$(String key);
    @Method(selector = "setPrimitiveValue:forKey:")
    public native void setPrimitiveValue$forKey$(NSObject value, String key);
    @Method(selector = "committedValuesForKeys:")
    public native NSDictionary<?, ?> committedValuesForKeys$(NSArray<?> keys);
    @Method(selector = "changedValues")
    public native NSDictionary<?, ?> changedValues();
    @Method(selector = "changedValuesForCurrentEvent")
    public native NSDictionary<?, ?> changedValuesForCurrentEvent();
    @Method(selector = "validateValue:forKey:error:")
    public native boolean validateValue$forKey$error$(NSObject value, String key, NSError.NSErrorPtr error);
    @Method(selector = "validateForDelete:")
    public native boolean validateForDelete$(NSError.NSErrorPtr error);
    @Method(selector = "validateForInsert:")
    public native boolean validateForInsert$(NSError.NSErrorPtr error);
    @Method(selector = "validateForUpdate:")
    public native boolean validateForUpdate$(NSError.NSErrorPtr error);
    @Method(selector = "contextShouldIgnoreUnmodeledPropertyChanges")
    public static native boolean contextShouldIgnoreUnmodeledPropertyChanges();
    /*</methods>*/
}
