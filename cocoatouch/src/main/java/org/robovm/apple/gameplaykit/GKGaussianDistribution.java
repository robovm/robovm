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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKGaussianDistribution/*</name>*/ 
    extends /*<extends>*/GKRandomDistribution/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKGaussianDistributionPtr extends Ptr<GKGaussianDistribution, GKGaussianDistributionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKGaussianDistribution.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKGaussianDistribution() {}
    protected GKGaussianDistribution(SkipInit skipInit) { super(skipInit); }
    public GKGaussianDistribution(GKRandom source, @MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive) { super((SkipInit) null); initObject(init(source, lowestInclusive, highestInclusive)); }
    public GKGaussianDistribution(GKRandom source, float mean, float deviation) { super((SkipInit) null); initObject(init(source, mean, deviation)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "mean")
    public native float getMean();
    @Property(selector = "deviation")
    public native float getDeviation();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRandomSource:lowestValue:highestValue:")
    protected native @Pointer long init(GKRandom source, @MachineSizedSInt long lowestInclusive, @MachineSizedSInt long highestInclusive);
    @Method(selector = "initWithRandomSource:mean:deviation:")
    protected native @Pointer long init(GKRandom source, float mean, float deviation);
    /*</methods>*/
}
