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
package org.robovm.apple.homekit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMEventTrigger/*</name>*/ 
    extends /*<extends>*/HMTrigger/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HMEventTriggerPtr extends Ptr<HMEventTrigger, HMEventTriggerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HMEventTrigger.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected HMEventTrigger(SkipInit skipInit) { super(skipInit); }
    public HMEventTrigger(String name, NSArray<HMEvent> events, NSPredicate predicate) { super((SkipInit) null); initObject(init(name, events, predicate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "events")
    public native NSArray<HMEvent> getEvents();
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithName:events:predicate:")
    protected native @Pointer long init(String name, NSArray<HMEvent> events, NSPredicate predicate);
    @Method(selector = "addEvent:completionHandler:")
    public native void addEvent(HMEvent event, @Block VoidBlock1<NSError> completion);
    @Method(selector = "removeEvent:completionHandler:")
    public native void removeEvent(HMEvent event, @Block VoidBlock1<NSError> completion);
    @Method(selector = "updatePredicate:completionHandler:")
    public native void updatePredicate(NSPredicate predicate, @Block VoidBlock1<NSError> completion);
    @Method(selector = "predicateForEvaluatingTriggerOccurringBeforeSignificantEvent:applyingOffset:")
    public static native NSPredicate getPredicateForEvaluatingTriggerOccurringBeforeSignificantEvent(HMSignificantEvent significantEvent, NSDateComponents offset);
    @Method(selector = "predicateForEvaluatingTriggerOccurringAfterSignificantEvent:applyingOffset:")
    public static native NSPredicate getPredicateForEvaluatingTriggerOccurringAfterSignificantEvent(HMSignificantEvent significantEvent, NSDateComponents offset);
    @Method(selector = "predicateForEvaluatingTriggerOccurringBeforeDateWithComponents:")
    public static native NSPredicate getPredicateForEvaluatingTriggerOccurringBeforeDate(NSDateComponents dateComponents);
    @Method(selector = "predicateForEvaluatingTriggerOccurringOnDateWithComponents:")
    public static native NSPredicate getPredicateForEvaluatingTriggerOccurringOnDate(NSDateComponents dateComponents);
    @Method(selector = "predicateForEvaluatingTriggerOccurringAfterDateWithComponents:")
    public static native NSPredicate getPredicateForEvaluatingTriggerOccurringAfterDate(NSDateComponents dateComponents);
    @Method(selector = "predicateForEvaluatingTriggerWithCharacteristic:relatedBy:toValue:")
    public static native NSPredicate getPredicateForEvaluatingTrigger(HMCharacteristic characteristic, NSPredicateOperatorType operatorType, NSObject value);
    /*</methods>*/
}
