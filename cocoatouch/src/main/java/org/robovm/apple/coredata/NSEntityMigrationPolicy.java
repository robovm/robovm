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
    /*<methods>*/
    @GlobalValue(symbol="NSMigrationManagerKey")
    public static native NSString KeyManager();
    @GlobalValue(symbol="NSMigrationSourceObjectKey")
    public static native NSString KeySourceObject();
    @GlobalValue(symbol="NSMigrationDestinationObjectKey")
    public static native NSString KeyDestinationObject();
    @GlobalValue(symbol="NSMigrationEntityMappingKey")
    public static native NSString KeyEntityMapping();
    @GlobalValue(symbol="NSMigrationPropertyMappingKey")
    public static native NSString KeyPropertyMapping();
    @GlobalValue(symbol="NSMigrationEntityPolicyKey")
    public static native NSString KeyEntityPolicy();
    
    @Method(selector = "beginEntityMapping:manager:error:")
    public native boolean beginEntityMapping$manager$error$(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "createDestinationInstancesForSourceInstance:entityMapping:manager:error:")
    public native boolean createDestinationInstancesForSourceInstance$entityMapping$manager$error$(NSManagedObject sInstance, NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endInstanceCreationForEntityMapping:manager:error:")
    public native boolean endInstanceCreationForEntityMapping$manager$error$(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "createRelationshipsForDestinationInstance:entityMapping:manager:error:")
    public native boolean createRelationshipsForDestinationInstance$entityMapping$manager$error$(NSManagedObject dInstance, NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endRelationshipCreationForEntityMapping:manager:error:")
    public native boolean endRelationshipCreationForEntityMapping$manager$error$(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "performCustomValidationForEntityMapping:manager:error:")
    public native boolean performCustomValidationForEntityMapping$manager$error$(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    @Method(selector = "endEntityMapping:manager:error:")
    public native boolean endEntityMapping$manager$error$(NSEntityMapping mapping, NSMigrationManager manager, NSError.NSErrorPtr error);
    /*</methods>*/
}
