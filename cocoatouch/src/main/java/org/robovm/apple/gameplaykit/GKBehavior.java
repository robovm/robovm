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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKBehavior/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSFastEnumeration/*</implements>*/, Iterable<GKGoal> {

    /*<ptr>*/public static class GKBehaviorPtr extends Ptr<GKBehavior, GKBehaviorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKBehavior.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKBehavior() {}
    protected GKBehavior(SkipInit skipInit) { super(skipInit); }
    public GKBehavior(GKGoal goal, float weight) { super(create(goal, weight)); retain(getHandle()); }
    public GKBehavior(NSArray<GKGoal> goals) { super(create(goals)); retain(getHandle()); }
    public GKBehavior(NSArray<GKGoal> goals, NSArray<NSNumber> weights) { super(create(goals, weights)); retain(getHandle()); }
    public GKBehavior(NSDictionary weightedGoals) { super(create(weightedGoals)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "goalCount")
    public native @MachineSizedSInt long getGoalCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public java.util.Iterator<GKGoal> iterator() {
        return new Iterator(this);
    }
    /*<methods>*/
    @Method(selector = "setWeight:forGoal:")
    private native void setGoalWeight(float weight, GKGoal goal);
    @Method(selector = "weightForGoal:")
    public native float getGoalWeight(GKGoal goal);
    @Method(selector = "removeGoal:")
    public native void removeGoal(GKGoal goal);
    @Method(selector = "removeAllGoals")
    public native void removeAllGoals();
    @Method(selector = "objectAtIndexedSubscript:")
    protected native GKGoal get(@MachineSizedUInt long idx);
    @Method(selector = "behaviorWithGoal:weight:")
    private static native @Pointer long create(GKGoal goal, float weight);
    @Method(selector = "behaviorWithGoals:")
    private static native @Pointer long create(NSArray<GKGoal> goals);
    @Method(selector = "behaviorWithGoals:andWeights:")
    private static native @Pointer long create(NSArray<GKGoal> goals, NSArray<NSNumber> weights);
    @Method(selector = "behaviorWithWeightedGoals:")
    private static native @Pointer long create(NSDictionary weightedGoals);
    /*</methods>*/
    
    private static class Iterator implements java.util.Iterator<GKGoal> {
        GKBehavior enumerator;
        int index;
        GKGoal current;
        GKGoal next;

        Iterator(GKBehavior enumerator) {
            this.enumerator = enumerator;
            index = -1;
            current = null;
            next = enumerator.get(0);
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public GKGoal next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            current = next;
            index++;
            next = enumerator.get(index + 1);
            return current;
        }

        void remove(int index, GKGoal o) {
            enumerator.removeGoal(o);
        }
        
        @Override
        public void remove() {
            if (current == null || next == null) {
                throw new IllegalStateException();
            }
            remove(index, current);
        }
    }
}
