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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFetchRequest/*</name>*/ 
    extends /*<extends>*/NSPersistentStoreRequest/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSFetchRequestPtr extends Ptr<NSFetchRequest, NSFetchRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFetchRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFetchRequest() {}
    protected NSFetchRequest(SkipInit skipInit) { super(skipInit); }
    public NSFetchRequest(String entityName) { super((SkipInit) null); initObject(initWithEntityName$(entityName)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithEntityName:")
    protected native @Pointer long initWithEntityName$(String entityName);
    @Method(selector = "entity")
    public native NSEntityDescription entity();
    @Method(selector = "setEntity:")
    public native void setEntity(NSEntityDescription entity);
    @Method(selector = "entityName")
    public native String entityName();
    @Method(selector = "predicate")
    public native NSPredicate predicate();
    @Method(selector = "setPredicate:")
    public native void setPredicate(NSPredicate predicate);
    @Method(selector = "sortDescriptors")
    public native NSArray<?> sortDescriptors();
    @Method(selector = "setSortDescriptors:")
    public native void setSortDescriptors(NSArray<?> sortDescriptors);
    @Method(selector = "fetchLimit")
    public native @MachineSizedUInt long fetchLimit();
    @Method(selector = "setFetchLimit:")
    public native void setFetchLimit(@MachineSizedUInt long limit);
    @Method(selector = "affectedStores")
    public native NSArray<?> affectedStores();
    @Method(selector = "setAffectedStores:")
    public native void setAffectedStores(NSArray<?> stores);
    @Method(selector = "resultType")
    public native NSFetchRequestResultType resultType();
    @Method(selector = "setResultType:")
    public native void setResultType(NSFetchRequestResultType type);
    @Method(selector = "includesSubentities")
    public native boolean includesSubentities();
    @Method(selector = "setIncludesSubentities:")
    public native void setIncludesSubentities(boolean yesNo);
    @Method(selector = "includesPropertyValues")
    public native boolean includesPropertyValues();
    @Method(selector = "setIncludesPropertyValues:")
    public native void setIncludesPropertyValues(boolean yesNo);
    @Method(selector = "returnsObjectsAsFaults")
    public native boolean returnsObjectsAsFaults();
    @Method(selector = "setReturnsObjectsAsFaults:")
    public native void setReturnsObjectsAsFaults(boolean yesNo);
    @Method(selector = "relationshipKeyPathsForPrefetching")
    public native NSArray<?> relationshipKeyPathsForPrefetching();
    @Method(selector = "setRelationshipKeyPathsForPrefetching:")
    public native void setRelationshipKeyPathsForPrefetching(NSArray<?> keys);
    @Method(selector = "includesPendingChanges")
    public native boolean includesPendingChanges();
    @Method(selector = "setIncludesPendingChanges:")
    public native void setIncludesPendingChanges(boolean flag);
    @Method(selector = "returnsDistinctResults")
    public native boolean returnsDistinctResults();
    @Method(selector = "setReturnsDistinctResults:")
    public native void setReturnsDistinctResults(boolean flag);
    @Method(selector = "propertiesToFetch")
    public native NSArray<?> propertiesToFetch();
    @Method(selector = "setPropertiesToFetch:")
    public native void setPropertiesToFetch(NSArray<?> values);
    @Method(selector = "fetchOffset")
    public native @MachineSizedUInt long fetchOffset();
    @Method(selector = "setFetchOffset:")
    public native void setFetchOffset(@MachineSizedUInt long offset);
    @Method(selector = "fetchBatchSize")
    public native @MachineSizedUInt long fetchBatchSize();
    @Method(selector = "setFetchBatchSize:")
    public native void setFetchBatchSize(@MachineSizedUInt long bsize);
    @Method(selector = "shouldRefreshRefetchedObjects")
    public native boolean shouldRefreshRefetchedObjects();
    @Method(selector = "setShouldRefreshRefetchedObjects:")
    public native void setShouldRefreshRefetchedObjects(boolean flag);
    @Method(selector = "propertiesToGroupBy")
    public native NSArray<?> propertiesToGroupBy();
    @Method(selector = "setPropertiesToGroupBy:")
    public native void setPropertiesToGroupBy(NSArray<?> array);
    @Method(selector = "havingPredicate")
    public native NSPredicate havingPredicate();
    @Method(selector = "setHavingPredicate:")
    public native void setHavingPredicate(NSPredicate predicate);
    @Method(selector = "fetchRequestWithEntityName:")
    public static native NSFetchRequest fetchRequestWithEntityName$(String entityName);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
