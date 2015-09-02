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
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreData") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSConstraintConflict/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSConstraintConflictPtr extends Ptr<NSConstraintConflict, NSConstraintConflictPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSConstraintConflict.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSConstraintConflict() {}
    protected NSConstraintConflict(SkipInit skipInit) { super(skipInit); }
    public NSConstraintConflict(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> constraint, NSManagedObject databaseObject, NSDictionary<?, ?> databaseSnapshot, NSArray<NSManagedObject> conflictingObjects, NSArray<NSDictionary> conflictingSnapshots) { super((SkipInit) null); initObject(init(constraint, databaseObject, databaseSnapshot, conflictingObjects, conflictingSnapshots)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "constraint")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getConstraint();
    @Property(selector = "constraintValues")
    public native NSDictionary<?, ?> getConstraintValues();
    @Property(selector = "databaseObject")
    public native NSManagedObject getDatabaseObject();
    @Property(selector = "databaseSnapshot")
    public native NSDictionary<?, ?> getDatabaseSnapshot();
    @Property(selector = "conflictingObjects")
    public native NSArray<NSManagedObject> getConflictingObjects();
    @Property(selector = "conflictingSnapshots")
    public native NSArray<NSDictionary> getConflictingSnapshots();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithConstraint:databaseObject:databaseSnapshot:conflictingObjects:conflictingSnapshots:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> constraint, NSManagedObject databaseObject, NSDictionary<?, ?> databaseSnapshot, NSArray<NSManagedObject> conflictingObjects, NSArray<NSDictionary> conflictingSnapshots);
    /*</methods>*/
}
