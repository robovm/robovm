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
    public NSFetchRequest(String entityName) { super((SkipInit) null); initObject(init(entityName)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "entity")
    public native NSEntityDescription getEntity();
    @Property(selector = "setEntity:")
    public native void setEntity(NSEntityDescription v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "entityName")
    public native String getEntityName();
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    @Property(selector = "setPredicate:")
    public native void setPredicate(NSPredicate v);
    @Property(selector = "sortDescriptors")
    public native NSArray<NSSortDescriptor> getSortDescriptors();
    @Property(selector = "setSortDescriptors:")
    public native void setSortDescriptors(NSArray<NSSortDescriptor> v);
    @Property(selector = "fetchLimit")
    public native @MachineSizedUInt long getFetchLimit();
    @Property(selector = "setFetchLimit:")
    public native void setFetchLimit(@MachineSizedUInt long v);
    @Property(selector = "affectedStores")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAffectedStores();
    @Property(selector = "setAffectedStores:")
    public native void setAffectedStores(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "resultType")
    public native NSFetchRequestResultType getResultType();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setResultType:")
    public native void setResultType(NSFetchRequestResultType v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "includesSubentities")
    public native boolean includesSubentities();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setIncludesSubentities:")
    public native void setIncludesSubentities(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "includesPropertyValues")
    public native boolean includesPropertyValues();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setIncludesPropertyValues:")
    public native void setIncludesPropertyValues(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "returnsObjectsAsFaults")
    public native boolean returnsObjectsAsFaults();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setReturnsObjectsAsFaults:")
    public native void setReturnsObjectsAsFaults(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "relationshipKeyPathsForPrefetching")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getRelationshipKeyPathsForPrefetching();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setRelationshipKeyPathsForPrefetching:")
    public native void setRelationshipKeyPathsForPrefetching(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "includesPendingChanges")
    public native boolean includesPendingChanges();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setIncludesPendingChanges:")
    public native void setIncludesPendingChanges(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "returnsDistinctResults")
    public native boolean returnsDistinctResults();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setReturnsDistinctResults:")
    public native void setReturnsDistinctResults(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "propertiesToFetch")
    public native NSArray<NSPropertyDescription> getPropertiesToFetch();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setPropertiesToFetch:")
    public native void setPropertiesToFetch(NSArray<NSPropertyDescription> v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "fetchOffset")
    public native @MachineSizedUInt long getFetchOffset();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setFetchOffset:")
    public native void setFetchOffset(@MachineSizedUInt long v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "fetchBatchSize")
    public native @MachineSizedUInt long getFetchBatchSize();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setFetchBatchSize:")
    public native void setFetchBatchSize(@MachineSizedUInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shouldRefreshRefetchedObjects")
    public native boolean shouldRefreshRefetchedObjects();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setShouldRefreshRefetchedObjects:")
    public native void setShouldRefreshRefetchedObjects(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "propertiesToGroupBy")
    public native NSArray<NSPropertyDescription> getPropertiesToGroupBy();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setPropertiesToGroupBy:")
    public native void setPropertiesToGroupBy(NSArray<NSPropertyDescription> v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "havingPredicate")
    public native NSPredicate getHavingPredicate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setHavingPredicate:")
    public native void setHavingPredicate(NSPredicate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithEntityName:")
    protected native @Pointer long init(String entityName);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fetchRequestWithEntityName:")
    public static native NSFetchRequest create(String entityName);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
