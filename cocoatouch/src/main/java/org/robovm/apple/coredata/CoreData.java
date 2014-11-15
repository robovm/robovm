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
/*<annotations>*/@Library("CoreData") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreData/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSDetailedErrorsKey", optional=true)
    public static native String Value__NSDetailedErrorsKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationObjectErrorKey", optional=true)
    public static native String Value__NSValidationObjectErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationKeyErrorKey", optional=true)
    public static native String Value__NSValidationKeyErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationPredicateErrorKey", optional=true)
    public static native String Value__NSValidationPredicateErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSValidationValueErrorKey", optional=true)
    public static native String Value__NSValidationValueErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedStoresErrorKey", optional=true)
    public static native String Value__NSAffectedStoresErrorKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAffectedObjectsErrorKey", optional=true)
    public static native String Value__NSAffectedObjectsErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresWillChangeNotification", optional=true)
    public static native String Value__NSPersistentStoreCoordinatorStoresWillChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorStoresDidChangeNotification", optional=true)
    public static native String Value__NSPersistentStoreCoordinatorStoresDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreCoordinatorWillRemoveStoreNotification", optional=true)
    public static native String Value__NSPersistentStoreCoordinatorWillRemoveStoreNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAddedPersistentStoresKey", optional=true)
    public static native String Value__NSAddedPersistentStoresKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRemovedPersistentStoresKey", optional=true)
    public static native String Value__NSRemovedPersistentStoresKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUUIDChangedPersistentStoresKey", optional=true)
    public static native String Value__NSUUIDChangedPersistentStoresKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreDidImportUbiquitousContentChangesNotification", optional=true)
    public static native String Value__NSPersistentStoreDidImportUbiquitousContentChangesNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationManagerKey", optional=true)
    public static native String Value__NSMigrationManagerKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationSourceObjectKey", optional=true)
    public static native String Value__NSMigrationSourceObjectKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationDestinationObjectKey", optional=true)
    public static native String Value__NSMigrationDestinationObjectKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityMappingKey", optional=true)
    public static native String Value__NSMigrationEntityMappingKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationPropertyMappingKey", optional=true)
    public static native String Value__NSMigrationPropertyMappingKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityPolicyKey", optional=true)
    public static native String Value__NSMigrationEntityPolicyKey();
    /*</methods>*/
}
