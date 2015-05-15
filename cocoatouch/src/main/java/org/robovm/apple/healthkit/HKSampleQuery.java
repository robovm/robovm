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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKSampleQuery/*</name>*/ 
    extends /*<extends>*/HKQuery/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HKSampleQueryPtr extends Ptr<HKSampleQuery, HKSampleQueryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HKSampleQuery.class); }/*</bind>*/
    /*<constants>*/
    public static final int NoLimit = 0;
    /*</constants>*/
    /*<constructors>*/
    public HKSampleQuery() {}
    protected HKSampleQuery(SkipInit skipInit) { super(skipInit); }
    public HKSampleQuery(HKSampleType sampleType, NSPredicate predicate, @MachineSizedUInt long limit, NSArray<NSSortDescriptor> sortDescriptors, @Block VoidBlock3<HKSampleQuery, NSArray<HKSample>, NSError> resultsHandler) { super((SkipInit) null); initObject(init(sampleType, predicate, limit, sortDescriptors, resultsHandler)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "limit")
    public native @MachineSizedUInt long getLimit();
    @Property(selector = "sortDescriptors")
    public native NSArray<NSSortDescriptor> getSortDescriptors();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSampleType:predicate:limit:sortDescriptors:resultsHandler:")
    protected native @Pointer long init(HKSampleType sampleType, NSPredicate predicate, @MachineSizedUInt long limit, NSArray<NSSortDescriptor> sortDescriptors, @Block VoidBlock3<HKSampleQuery, NSArray<HKSample>, NSError> resultsHandler);
    /*</methods>*/
}
