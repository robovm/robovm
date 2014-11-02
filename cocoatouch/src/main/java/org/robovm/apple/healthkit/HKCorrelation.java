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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKCorrelation/*</name>*/ 
    extends /*<extends>*/HKSample/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class HKCorrelationPtr extends Ptr<HKCorrelation, HKCorrelationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(HKCorrelation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public HKCorrelation() {}
    protected HKCorrelation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "correlationType")
    public native HKCorrelationType getCorrelationType();
    @Property(selector = "objects")
    public native NSSet<HKSample> getObjects();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "objectsForType:")
    public native NSSet<HKSample> getObjectsForType(HKObjectType objectType);
    @Method(selector = "correlationWithType:startDate:endDate:objects:")
    public static native HKCorrelation create(HKCorrelationType correlationType, NSDate startDate, NSDate endDate, NSSet<HKSample> objects);
    @Method(selector = "correlationWithType:startDate:endDate:objects:metadata:")
    public static native HKCorrelation create(HKCorrelationType correlationType, NSDate startDate, NSDate endDate, NSSet<HKSample> objects, HKMetadata metadata);
    /*</methods>*/
}
