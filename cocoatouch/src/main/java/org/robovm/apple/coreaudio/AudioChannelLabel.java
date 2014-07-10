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
package org.robovm.apple.coreaudio;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AudioChannelLabel/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Unknown(-1L),
    Unused(0L),
    UseCoordinates(100L),
    Left(1L),
    Right(2L),
    Center(3L),
    LFEScreen(4L),
    LeftSurround(5L),
    RightSurround(6L),
    LeftCenter(7L),
    RightCenter(8L),
    CenterSurround(9L),
    LeftSurroundDirect(10L),
    RightSurroundDirect(11L),
    TopCenterSurround(12L),
    VerticalHeightLeft(13L),
    VerticalHeightCenter(14L),
    VerticalHeightRight(15L),
    TopBackLeft(16L),
    TopBackCenter(17L),
    TopBackRight(18L),
    RearSurroundLeft(33L),
    RearSurroundRight(34L),
    LeftWide(35L),
    RightWide(36L),
    LFE2(37L),
    LeftTotal(38L),
    RightTotal(39L),
    HearingImpaired(40L),
    Narration(41L),
    Mono(42L),
    DialogCentricMix(43L),
    CenterSurroundDirect(44L),
    Haptic(45L),
    Ambisonic_W(200L),
    Ambisonic_X(201L),
    Ambisonic_Y(202L),
    Ambisonic_Z(203L),
    MS_Mid(204L),
    MS_Side(205L),
    XY_X(206L),
    XY_Y(207L),
    HeadphonesLeft(301L),
    HeadphonesRight(302L),
    ClickTrack(304L),
    ForeignLanguage(305L),
    Discrete(400L),
    Discrete_0(65536L),
    Discrete_1(65537L),
    Discrete_2(65538L),
    Discrete_3(65539L),
    Discrete_4(65540L),
    Discrete_5(65541L),
    Discrete_6(65542L),
    Discrete_7(65543L),
    Discrete_8(65544L),
    Discrete_9(65545L),
    Discrete_10(65546L),
    Discrete_11(65547L),
    Discrete_12(65548L),
    Discrete_13(65549L),
    Discrete_14(65550L),
    Discrete_15(65551L),
    Discrete_65535(131071L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioChannelLabel/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioChannelLabel/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioChannelLabel/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioChannelLabel/*</name>*/.class.getName());
    }
}
