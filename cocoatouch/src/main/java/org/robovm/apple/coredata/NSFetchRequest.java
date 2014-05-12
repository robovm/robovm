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
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
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
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFetchRequest(String entityName) { super((SkipInit) null); initObject(initWithEntityName$(entityName)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithEntityName:")
    protected native @Pointer long initWithEntityName$(String entityName);
    @Method(selector = "entity")
    public native NSEntityDescription entity();
    @Method(selector = "setEntity:")
    public native void setEntity(NSEntityDescription entity);
    /**
     * @since Available in iOS 4.0 and later.
     */
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
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "resultType")
    public native NSFetchRequestResultType resultType();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setResultType:")
    public native void setResultType(NSFetchRequestResultType type);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "includesSubentities")
    public native boolean includesSubentities();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setIncludesSubentities:")
    public native void setIncludesSubentities(boolean yesNo);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "includesPropertyValues")
    public native boolean includesPropertyValues();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setIncludesPropertyValues:")
    public native void setIncludesPropertyValues(boolean yesNo);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "returnsObjectsAsFaults")
    public native boolean returnsObjectsAsFaults();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setReturnsObjectsAsFaults:")
    public native void setReturnsObjectsAsFaults(boolean yesNo);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "relationshipKeyPathsForPrefetching")
    public native NSArray<?> relationshipKeyPathsForPrefetching();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setRelationshipKeyPathsForPrefetching:")
    public native void setRelationshipKeyPathsForPrefetching(NSArray<?> keys);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "includesPendingChanges")
    public native boolean includesPendingChanges();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setIncludesPendingChanges:")
    public native void setIncludesPendingChanges(boolean flag);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "returnsDistinctResults")
    public native boolean returnsDistinctResults();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setReturnsDistinctResults:")
    public native void setReturnsDistinctResults(boolean flag);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "propertiesToFetch")
    public native NSArray<?> propertiesToFetch();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setPropertiesToFetch:")
    public native void setPropertiesToFetch(NSArray<?> values);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "fetchOffset")
    public native @MachineSizedUInt long fetchOffset();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setFetchOffset:")
    public native void setFetchOffset(@MachineSizedUInt long offset);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "fetchBatchSize")
    public native @MachineSizedUInt long fetchBatchSize();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setFetchBatchSize:")
    public native void setFetchBatchSize(@MachineSizedUInt long bsize);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shouldRefreshRefetchedObjects")
    public native boolean shouldRefreshRefetchedObjects();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setShouldRefreshRefetchedObjects:")
    public native void setShouldRefreshRefetchedObjects(boolean flag);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "propertiesToGroupBy")
    public native NSArray<?> propertiesToGroupBy();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setPropertiesToGroupBy:")
    public native void setPropertiesToGroupBy(NSArray<?> array);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "havingPredicate")
    public native NSPredicate havingPredicate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setHavingPredicate:")
    public native void setHavingPredicate(NSPredicate predicate);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fetchRequestWithEntityName:")
    public static native NSFetchRequest fetchRequestWithEntityName$(String entityName);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
