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

/*</javadoc>*/
@ForceLinkClass(NSError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/NSCoreDataErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    ManagedObjectValidation(1550L),
    ValidationMultipleErrors(1560L),
    ValidationMissingMandatoryProperty(1570L),
    ValidationRelationshipLacksMinimumCount(1580L),
    ValidationRelationshipExceedsMaximumCount(1590L),
    ValidationRelationshipDeniedDelete(1600L),
    ValidationNumberTooLarge(1610L),
    ValidationNumberTooSmall(1620L),
    ValidationDateTooLate(1630L),
    ValidationDateTooSoon(1640L),
    ValidationInvalidDate(1650L),
    ValidationStringTooLong(1660L),
    ValidationStringTooShort(1670L),
    ValidationStringPatternMatching(1680L),
    ManagedObjectContextLocking(132000L),
    PersistentStoreCoordinatorLocking(132010L),
    ManagedObjectReferentialIntegrity(133000L),
    ManagedObjectExternalRelationship(133010L),
    ManagedObjectMerge(133020L),
    PersistentStoreInvalidType(134000L),
    PersistentStoreTypeMismatch(134010L),
    PersistentStoreIncompatibleSchema(134020L),
    PersistentStoreSave(134030L),
    PersistentStoreIncompleteSave(134040L),
    PersistentStoreSaveConflicts(134050L),
    CoreData(134060L),
    PersistentStoreOperation(134070L),
    PersistentStoreOpen(134080L),
    PersistentStoreTimeout(134090L),
    PersistentStoreUnsupportedRequestType(134091L),
    PersistentStoreIncompatibleVersionHash(134100L),
    Migration(134110L),
    MigrationCancelled(134120L),
    MigrationMissingSourceModel(134130L),
    MigrationMissingMappingModel(134140L),
    MigrationManagerSourceStore(134150L),
    MigrationManagerDestinationStore(134160L),
    EntityMigrationPolicy(134170L),
    SQLite(134180L),
    InferredMappingModel(134190L),
    ExternalRecordImport(134200L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/NSCoreDataErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSCoreDataErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSCoreDataErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSCoreDataErrorCode/*</name>*/.class.getName());
    }
}
