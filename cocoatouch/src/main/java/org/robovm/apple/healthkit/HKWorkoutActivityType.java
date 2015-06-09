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
import org.robovm.rt.annotation.*;
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
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/HKWorkoutActivityType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    AmericanFootball(1L),
    Archery(2L),
    AustralianFootball(3L),
    Badminton(4L),
    Baseball(5L),
    Basketball(6L),
    Bowling(7L),
    Boxing(8L),
    Climbing(9L),
    Cricket(10L),
    CrossTraining(11L),
    Curling(12L),
    Cycling(13L),
    Dance(14L),
    DanceInspiredTraining(15L),
    Elliptical(16L),
    EquestrianSports(17L),
    Fencing(18L),
    Fishing(19L),
    FunctionalStrengthTraining(20L),
    Golf(21L),
    Gymnastics(22L),
    Handball(23L),
    Hiking(24L),
    Hockey(25L),
    Hunting(26L),
    Lacrosse(27L),
    MartialArts(28L),
    MindAndBody(29L),
    MixedMetabolicCardioTraining(30L),
    PaddleSports(31L),
    Play(32L),
    PreparationAndRecovery(33L),
    Racquetball(34L),
    Rowing(35L),
    Rugby(36L),
    Running(37L),
    Sailing(38L),
    SkatingSports(39L),
    SnowSports(40L),
    Soccer(41L),
    Softball(42L),
    Squash(43L),
    StairClimbing(44L),
    SurfingSports(45L),
    Swimming(46L),
    TableTennis(47L),
    Tennis(48L),
    TrackAndField(49L),
    TraditionalStrengthTraining(50L),
    Volleyball(51L),
    Walking(52L),
    WaterFitness(53L),
    WaterPolo(54L),
    WaterSports(55L),
    Wrestling(56L),
    Yoga(57L),
    Other(3000L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/HKWorkoutActivityType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/HKWorkoutActivityType/*</name>*/ valueOf(long n) {
        for (/*<name>*/HKWorkoutActivityType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/HKWorkoutActivityType/*</name>*/.class.getName());
    }
}
