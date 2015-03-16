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

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
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
    public NSManagedObject(NSEntityDescription entity, NSManagedObjectContext context) { super((SkipInit) null); initObject(init(entity, context)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "managedObjectContext")
    public native NSManagedObjectContext getManagedObjectContext();
    @Property(selector = "entity")
    public native NSEntityDescription getEntity();
    @Property(selector = "objectID")
    public native NSManagedObjectID getObjectID();
    @Property(selector = "isInserted")
    public native boolean isInserted();
    @Property(selector = "isUpdated")
    public native boolean isUpdated();
    @Property(selector = "isDeleted")
    public native boolean isDeleted();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "hasChanges")
    public native boolean hasChanges();
    @Property(selector = "isFault")
    public native boolean isFault();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "faultingState")
    public native @MachineSizedUInt long getFaultingState();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setValue(String key, NSObject value) {
        setValue(value, key);
    }
    public void setPrimitiveValue(String key, NSObject value) {
        setPrimitiveValue(value, key);
    }
    /**
     * 
     * @param key
     * @param value
     * @return
     * @throws NSErrorException
     */
    public boolean validateValue(String key, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = validateValue(value, key, err);
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
    public boolean validateForDelete() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = validateForDelete(err);
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
    public boolean validateForInsert() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = validateForInsert(err);
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
    public boolean validateForUpdate() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = validateForUpdate(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "initWithEntity:insertIntoManagedObjectContext:")
    protected native @Pointer long init(NSEntityDescription entity, NSManagedObjectContext context);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "hasFaultForRelationshipNamed:")
    public native boolean hasFaultForRelationship(String key);
    @Method(selector = "willAccessValueForKey:")
    public native void willAccessValue(String key);
    @Method(selector = "didAccessValueForKey:")
    public native void didAccessValue(String key);
    @Method(selector = "willChangeValueForKey:")
    public native void willChangeValue(String key);
    @Method(selector = "didChangeValueForKey:")
    public native void didChangeValue(String key);
    @Method(selector = "willChangeValueForKey:withSetMutation:usingObjects:")
    public native void willChangeValue(String inKey, NSKeyValueSetMutationKind inMutationKind, NSSet<?> inObjects);
    @Method(selector = "didChangeValueForKey:withSetMutation:usingObjects:")
    public native void didChangeValue(String inKey, NSKeyValueSetMutationKind inMutationKind, NSSet<?> inObjects);
    @Method(selector = "awakeFromFetch")
    public native void awakeFromFetch();
    @Method(selector = "awakeFromInsert")
    public native void awakeFromInsert();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "awakeFromSnapshotEvents:")
    public native void awakeFromSnapshotEvents(NSSnapshotEventType flags);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "prepareForDeletion")
    public native void prepareForDeletion();
    @Method(selector = "willSave")
    public native void willSave();
    @Method(selector = "didSave")
    public native void didSave();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "willTurnIntoFault")
    public native void willTurnIntoFault();
    @Method(selector = "didTurnIntoFault")
    public native void didTurnIntoFault();
    @Method(selector = "valueForKey:")
    public native NSObject getValue(String key);
    @Method(selector = "setValue:forKey:")
    private native void setValue(NSObject value, String key);
    @Method(selector = "primitiveValueForKey:")
    public native NSObject getPrimitiveValue(String key);
    @Method(selector = "setPrimitiveValue:forKey:")
    private native void setPrimitiveValue(NSObject value, String key);
    @Method(selector = "committedValuesForKeys:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getCommittedValues(NSArray<?> keys);
    @Method(selector = "changedValues")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getChangedValues();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "changedValuesForCurrentEvent")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSDictionary.AsStringMapMarshaler.class) Map<String, NSObject> getChangedValuesForCurrentEvent();
    @Method(selector = "validateValue:forKey:error:")
    protected native boolean validateValue(NSObject value, String key, NSError.NSErrorPtr error);
    @Method(selector = "validateForDelete:")
    protected native boolean validateForDelete(NSError.NSErrorPtr error);
    @Method(selector = "validateForInsert:")
    protected native boolean validateForInsert(NSError.NSErrorPtr error);
    @Method(selector = "validateForUpdate:")
    protected native boolean validateForUpdate(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "contextShouldIgnoreUnmodeledPropertyChanges")
    public static native boolean shouldContextIgnoreUnmodeledPropertyChanges();
    /*</methods>*/
}
