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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
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

    public static class Notifications {
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeDidStartGathering(NSMetadataQuery object, final VoidBlock2<NSMetadataQuery, NSMetadataQueryUpdatedItems> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidStartGatheringNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSMetadataQuery)a.getObject(), new NSMetadataQueryUpdatedItems(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeGatheringProgress(NSMetadataQuery object, final VoidBlock2<NSMetadataQuery, NSMetadataQueryUpdatedItems> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(GatheringProgressNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSMetadataQuery)a.getObject(), new NSMetadataQueryUpdatedItems(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeDidFinishGathering(NSMetadataQuery object, final VoidBlock2<NSMetadataQuery, NSMetadataQueryUpdatedItems> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidFinishGatheringNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSMetadataQuery)a.getObject(), new NSMetadataQueryUpdatedItems(a.getUserInfo()));
                }
            });
        }
        /**
         * @since Available in iOS 5.0 and later.
         */
        public static NSObject observeDidUpdate(NSMetadataQuery object, final VoidBlock2<NSMetadataQuery, NSMetadataQueryUpdatedItems> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidUpdateNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((NSMetadataQuery)a.getObject(), new NSMetadataQueryUpdatedItems(a.getUserInfo()));
                }
            });
        }
    }
    
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
    public void setSearchScopes(NSArray<NSURL> scopes) {
        setSearchScopes0(scopes);
    }
    public void setSearchScopes(List<String> scopes) {
        setSearchScopes0(NSArray.fromStrings(scopes));
    }
    public void setSearchScopes(NSMetadataQueryScope...scopes) {
        NSMutableArray<NSString> array = new NSMutableArray<>();
        for (NSMetadataQueryScope scope : scopes) {
            array.add(scope.value());
        }
        setSearchScopes0(array);
    }
    public void setSearchItems(NSArray<NSMetadataItem> items) {
        setSearchItems0(items);
    }
    public void setSearchItemURLs(NSArray<NSURL> urls) {
        setSearchItems0(urls);
    }
    public Map<NSMetadataItemAttribute, NSMetadataQueryAttributeValueTuple> getValueLists() {
        Map<NSMetadataItemAttribute, NSMetadataQueryAttributeValueTuple> values = new HashMap<>();
        NSDictionary<NSString, NSMetadataQueryAttributeValueTuple> valueLists = getValueLists0();
        for (Map.Entry<NSString, NSMetadataQueryAttributeValueTuple> entry : valueLists.entrySet()) {
            values.put(NSMetadataItemAttribute.valueOf(entry.getKey()), entry.getValue());
        }
        return values;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidStartGatheringNotification", optional=true)
    public static native NSString DidStartGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryGatheringProgressNotification", optional=true)
    public static native NSString GatheringProgressNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidFinishGatheringNotification", optional=true)
    public static native NSString DidFinishGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidUpdateNotification", optional=true)
    public static native NSString DidUpdateNotification();
    
    @Method(selector = "delegate")
    public native NSMetadataQueryDelegate getDelegate();
    @Method(selector = "setDelegate:")
    public native void setDelegate(NSMetadataQueryDelegate delegate);
    @Method(selector = "predicate")
    public native NSPredicate getPredicate();
    @Method(selector = "setPredicate:")
    public native void setPredicate(NSPredicate predicate);
    @Method(selector = "sortDescriptors")
    public native NSArray<NSSortDescriptor> getSortDescriptors();
    @Method(selector = "setSortDescriptors:")
    public native void setSortDescriptors(NSArray<NSSortDescriptor> descriptors);
    @Method(selector = "valueListAttributes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSMetadataItemAttribute.AsListMarshaler.class) List<NSMetadataItemAttribute> getValueListAttributes();
    @Method(selector = "setValueListAttributes:")
    public native void setValueListAttributes(@org.robovm.rt.bro.annotation.Marshaler(NSMetadataItemAttribute.AsListMarshaler.class) List<NSMetadataItemAttribute> attrs);
    @Method(selector = "groupingAttributes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSMetadataItemAttribute.AsListMarshaler.class) List<NSMetadataItemAttribute> getGroupingAttributes();
    @Method(selector = "setGroupingAttributes:")
    public native void setGroupingAttributes(@org.robovm.rt.bro.annotation.Marshaler(NSMetadataItemAttribute.AsListMarshaler.class) List<NSMetadataItemAttribute> attrs);
    @Method(selector = "notificationBatchingInterval")
    public native double getNotificationBatchingInterval();
    @Method(selector = "setNotificationBatchingInterval:")
    public native void setNotificationBatchingInterval(double ti);
    @Method(selector = "searchScopes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSearchScopes();
    @Method(selector = "setSearchScopes:")
    protected native void setSearchScopes0(NSArray<?> scopes);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "searchItems")
    public native NSArray<NSObject> getSearchItems();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setSearchItems:")
    protected native void setSearchItems0(NSArray<?> items);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "operationQueue")
    public native NSOperationQueue getOperationQueue();
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
    public native @MachineSizedUInt long getResultCount();
    @Method(selector = "resultAtIndex:")
    public native NSMetadataItem getResult(@MachineSizedUInt long idx);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "enumerateResultsUsingBlock:")
    public native void enumerateResults(@Block("(,@MachineSizedUInt,)") VoidBlock3<NSMetadataItem, Long, BooleanPtr> block);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "enumerateResultsWithOptions:usingBlock:")
    public native void enumerateResults(NSEnumerationOptions opts, @Block("(,@MachineSizedUInt,)") VoidBlock3<NSMetadataItem, Long, BooleanPtr> block);
    @Method(selector = "results")
    public native NSArray<NSMetadataItem> getResults();
    @Method(selector = "indexOfResult:")
    public native @MachineSizedUInt long indexOfResult(NSMetadataItem result);
    @Method(selector = "valueLists")
    protected native NSDictionary<NSString, NSMetadataQueryAttributeValueTuple> getValueLists0();
    @Method(selector = "groupedResults")
    public native NSArray<NSMetadataQueryResultGroup> getGroupedResults();
    @Method(selector = "valueOfAttribute:forResultAtIndex:")
    public native NSObject getValueForResult(NSMetadataItemAttribute attrName, @MachineSizedUInt long idx);
    /*</methods>*/
}
