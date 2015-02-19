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
package org.robovm.apple.healthkit;

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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HealthKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKStatisticsCollectionQuery/*</name>*/ 
    extends /*<extends>*/HKQuery/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HKStatisticsCollectionQueryPtr extends Ptr<HKStatisticsCollectionQuery, HKStatisticsCollectionQueryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HKStatisticsCollectionQuery.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HKStatisticsCollectionQuery() {}
    protected HKStatisticsCollectionQuery(SkipInit skipInit) { super(skipInit); }
    public HKStatisticsCollectionQuery(HKQuantityType quantityType, NSPredicate quantitySamplePredicate, HKStatisticsOptions options, NSDate anchorDate, NSDateComponents intervalComponents) { super((SkipInit) null); initObject(init(quantityType, quantitySamplePredicate, options, anchorDate, intervalComponents)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "anchorDate")
    public native NSDate getAnchorDate();
    @Property(selector = "options")
    public native HKStatisticsOptions getOptions();
    @Property(selector = "intervalComponents")
    public native NSDateComponents getIntervalComponents();
    @Property(selector = "initialResultsHandler")
    public native @Block VoidBlock3<HKStatisticsCollectionQuery, HKStatisticsCollection, NSError> getInitialResultsHandler();
    @Property(selector = "setInitialResultsHandler:")
    public native void setInitialResultsHandler(@Block VoidBlock3<HKStatisticsCollectionQuery, HKStatisticsCollection, NSError> v);
    @Property(selector = "statisticsUpdateHandler")
    public native @Block VoidBlock4<HKStatisticsCollectionQuery, HKStatistics, HKStatisticsCollection, NSError> getStatisticsUpdateHandler();
    @Property(selector = "setStatisticsUpdateHandler:")
    public native void setStatisticsUpdateHandler(@Block VoidBlock4<HKStatisticsCollectionQuery, HKStatistics, HKStatisticsCollection, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithQuantityType:quantitySamplePredicate:options:anchorDate:intervalComponents:")
    protected native @Pointer long init(HKQuantityType quantityType, NSPredicate quantitySamplePredicate, HKStatisticsOptions options, NSDate anchorDate, NSDateComponents intervalComponents);
    /*</methods>*/
}
