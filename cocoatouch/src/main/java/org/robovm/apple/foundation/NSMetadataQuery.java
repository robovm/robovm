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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataQuery/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMetadataQueryPtr extends Ptr<NSMetadataQuery, NSMetadataQueryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMetadataQuery.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMetadataQuery() {}
    protected NSMetadataQuery(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "delegate")
    public native NSMetadataQueryDelegate delegate();
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSMetadataQueryDelegate delegate);
    @Method(selector = "predicate")
    public native NSPredicate predicate();
    @Method(selector = "setPredicate:")
    public native void setPredicate(NSPredicate predicate);
    @Method(selector = "sortDescriptors")
    public native NSArray<?> sortDescriptors();
    @Method(selector = "setSortDescriptors:")
    public native void setSortDescriptors(NSArray<?> descriptors);
    @Method(selector = "valueListAttributes")
    public native NSArray<?> valueListAttributes();
    @Method(selector = "setValueListAttributes:")
    public native void setValueListAttributes(NSArray<?> attrs);
    @Method(selector = "groupingAttributes")
    public native NSArray<?> groupingAttributes();
    @Method(selector = "setGroupingAttributes:")
    public native void setGroupingAttributes(NSArray<?> attrs);
    @Method(selector = "notificationBatchingInterval")
    public native double notificationBatchingInterval();
    @Method(selector = "setNotificationBatchingInterval:")
    public native void setNotificationBatchingInterval(double ti);
    @Method(selector = "searchScopes")
    public native NSArray<?> searchScopes();
    @Method(selector = "setSearchScopes:")
    public native void setSearchScopes(NSArray<?> scopes);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "searchItems")
    public native NSArray<?> searchItems();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setSearchItems:")
    public native void setSearchItems(NSArray<?> items);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "operationQueue")
    public native NSOperationQueue operationQueue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setOperationQueue:")
    public native void setOperationQueue(NSOperationQueue operationQueue);
    @Method(selector = "startQuery")
    public native boolean startQuery();
    @Method(selector = "stopQuery")
    public native void stopQuery();
    @Method(selector = "isStarted")
    public native boolean isStarted();
    @Method(selector = "isGathering")
    public native boolean isGathering();
    @Method(selector = "isStopped")
    public native boolean isStopped();
    @Method(selector = "disableUpdates")
    public native void disableUpdates();
    @Method(selector = "enableUpdates")
    public native void enableUpdates();
    @Method(selector = "resultCount")
    public native @MachineSizedUInt long resultCount();
    @Method(selector = "resultAtIndex:")
    public native NSObject resultAtIndex$(@MachineSizedUInt long idx);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "enumerateResultsUsingBlock:")
    public native void enumerateResultsUsingBlock$(ObjCBlock block);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "enumerateResultsWithOptions:usingBlock:")
    public native void enumerateResultsWithOptions$usingBlock$(NSEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "results")
    public native NSArray<?> results();
    @Method(selector = "indexOfResult:")
    public native @MachineSizedUInt long indexOfResult$(NSObject result);
    @Method(selector = "valueLists")
    public native NSDictionary<?, ?> valueLists();
    @Method(selector = "groupedResults")
    public native NSArray<?> groupedResults();
    @Method(selector = "valueOfAttribute:forResultAtIndex:")
    public native NSObject valueOfAttribute$forResultAtIndex$(String attrName, @MachineSizedUInt long idx);
    /*</methods>*/
}
