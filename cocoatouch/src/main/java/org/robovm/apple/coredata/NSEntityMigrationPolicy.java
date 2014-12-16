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
import org.robovm.apple.foundation.NSError.NSErrorPtr;

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSEntityMigrationPolicy/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSEntityMigrationPolicyPtr extends Ptr<NSEntityMigrationPolicy, NSEntityMigrationPolicyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSEntityMigrationPolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSEntityMigrationPolicy() {}
    protected NSEntityMigrationPolicy(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean beginEntityMapping(NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = beginEntityMapping(mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param sInstance
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean createDestinationInstances(NSManagedObject sInstance, NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = createDestinationInstances(sInstance, mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean endInstanceCreation(NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = endInstanceCreation(mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param dInstance
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean createRelationships(NSManagedObject dInstance, NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = createRelationships(dInstance, mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean endRelationshipCreation(NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = endRelationshipCreation(mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean performCustomValidation(NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = performCustomValidation(mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param mapping
     * @param manager
     * @return
     * @throws NSErrorException
     */
    public boolean endEntityMapping(NSEntityMapping mapping, NSMigrationManager manager) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = endEntityMapping(mapping, manager, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Method(selector = "beginEntityMapping:manager:error:")
    protected native boolean beginEntityMapping(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "createDestinationInstancesForSourceInstance:entityMapping:manager:error:")
    protected native boolean createDestinationInstances(NSManagedObject sInstance, NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endInstanceCreationForEntityMapping:manager:error:")
    protected native boolean endInstanceCreation(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "createRelationshipsForDestinationInstance:entityMapping:manager:error:")
    protected native boolean createRelationships(NSManagedObject dInstance, NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endRelationshipCreationForEntityMapping:manager:error:")
    protected native boolean endRelationshipCreation(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "performCustomValidationForEntityMapping:manager:error:")
    protected native boolean performCustomValidation(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endEntityMapping:manager:error:")
    protected native boolean endEntityMapping(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    /*</methods>*/
}
