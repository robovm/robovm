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
package org.robovm.apple.gameplaykit;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKRandomDistribution/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GKRandom/*</implements>*/ {

    /*<ptr>*/public static class GKRandomDistributionPtr extends Ptr<GKRandomDistribution, GKRandomDistributionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKRandomDistribution.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKRandomDistribution() {}
    protected GKRandomDistribution(SkipInit skipInit) { super(skipInit); }
    public GKRandomDistribution(GKRandom source, @MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive) { super((SkipInit) null); initObject(init(source, lowestInclusive, highestInclusive)); }
    public GKRandomDistribution(@MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive) { super(create(lowestInclusive, highestInclusive)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "lowestValue")
    public native @MachineSizedSInt long getLowestValue();
    @Property(selector = "highestValue")
    public native @MachineSizedSInt long getHighestValue();
    @Property(selector = "numberOfPossibleOutcomes")
    public native @MachineSizedUInt long getNumberOfPossibleOutcomes();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRandomSource:lowestValue:highestValue:")
    protected native @Pointer long init(GKRandom source, @MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive);
    @Method(selector = "nextInt")
    public native @MachineSizedSInt long nextInt();
    @Method(selector = "nextIntWithUpperBound:")
    public native @MachineSizedUInt long nextInt(@MachineSizedUInt long upperBound);
    @Method(selector = "nextUniform")
    public native float nextUniform();
    @Method(selector = "nextBool")
    public native boolean nextBool();
    @Method(selector = "distributionWithLowestValue:highestValue:")
    private static native @Pointer long create(@MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive);
    @Method(selector = "distributionForDieWithSideCount:")
    public static native GKRandomDistribution die(@MachineSizedSInt long sideCount);
    @Method(selector = "d6")
    public static native GKRandomDistribution d6();
    @Method(selector = "d20")
    public static native GKRandomDistribution d20();
    /*</methods>*/
}
