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
/*<annotations>*//*</annotations>*/
public enum /*<name>*/AudioChannelLayoutTag/*</name>*/ implements ValuedEnum {
    /*<values>*/
    UseChannelDescriptions(0L),
    UseChannelBitmap(65536L),
    Mono(6553601L),
    Stereo(6619138L),
    StereoHeadphones(6684674L),
    MatrixStereo(6750210L),
    MidSide(6815746L),
    XY(6881282L),
    Binaural(6946818L),
    Ambisonic_B_Format(7012356L),
    Quadraphonic(7077892L),
    Pentagonal(7143429L),
    Hexagonal(7208966L),
    Octagonal(7274504L),
    Cube(7340040L),
    MPEG_1_0(6553601L),
    MPEG_2_0(6619138L),
    MPEG_3_0_A(7405571L),
    MPEG_3_0_B(7471107L),
    MPEG_4_0_A(7536644L),
    MPEG_4_0_B(7602180L),
    MPEG_5_0_A(7667717L),
    MPEG_5_0_B(7733253L),
    MPEG_5_0_C(7798789L),
    MPEG_5_0_D(7864325L),
    MPEG_5_1_A(7929862L),
    MPEG_5_1_B(7995398L),
    MPEG_5_1_C(8060934L),
    MPEG_5_1_D(8126470L),
    MPEG_6_1_A(8192007L),
    MPEG_7_1_A(8257544L),
    MPEG_7_1_B(8323080L),
    MPEG_7_1_C(8388616L),
    Emagic_Default_7_1(8454152L),
    SMPTE_DTV(8519688L),
    ITU_1_0(6553601L),
    ITU_2_0(6619138L),
    ITU_2_1(8585219L),
    ITU_2_2(8650756L),
    ITU_3_0(7405571L),
    ITU_3_1(7536644L),
    ITU_3_2(7667717L),
    ITU_3_2_1(7929862L),
    ITU_3_4_1(8388616L),
    DVD_0(6553601L),
    DVD_1(6619138L),
    DVD_2(8585219L),
    DVD_3(8650756L),
    DVD_4(8716291L),
    DVD_5(8781828L),
    DVD_6(8847365L),
    DVD_7(7405571L),
    DVD_8(7536644L),
    DVD_9(7667717L),
    DVD_10(8912900L),
    DVD_11(8978437L),
    DVD_12(7929862L),
    DVD_13(7536644L),
    DVD_14(7667717L),
    DVD_15(8912900L),
    DVD_16(8978437L),
    DVD_17(7929862L),
    DVD_18(9043973L),
    DVD_19(7733253L),
    DVD_20(7995398L),
    AudioUnit_4(7077892L),
    AudioUnit_5(7143429L),
    AudioUnit_6(7208966L),
    AudioUnit_8(7274504L),
    AudioUnit_5_0(7733253L),
    AudioUnit_6_0(9109510L),
    AudioUnit_7_0(9175047L),
    AudioUnit_7_0_Front(9699335L),
    AudioUnit_5_1(7929862L),
    AudioUnit_6_1(8192007L),
    AudioUnit_7_1(8388616L),
    AudioUnit_7_1_Front(8257544L),
    AAC_3_0(7471107L),
    AAC_Quadraphonic(7077892L),
    AAC_4_0(7602180L),
    AAC_5_0(7864325L),
    AAC_5_1(8126470L),
    AAC_6_0(9240582L),
    AAC_6_1(9306119L),
    AAC_7_0(9371655L),
    AAC_7_1(8323080L),
    AAC_7_1_B(11993096L),
    AAC_7_1_C(12058632L),
    AAC_Octagonal(9437192L),
    TMH_10_2_std(9502736L),
    TMH_10_2_full(9568277L),
    AC3_1_0_1(9764866L),
    AC3_3_0(9830403L),
    AC3_3_1(9895940L),
    AC3_3_0_1(9961476L),
    AC3_2_1_1(10027012L),
    AC3_3_1_1(10092549L),
    EAC_6_0_A(10158086L),
    EAC_7_0_A(10223623L),
    EAC3_6_1_A(10289159L),
    EAC3_6_1_B(10354695L),
    EAC3_6_1_C(10420231L),
    EAC3_7_1_A(10485768L),
    EAC3_7_1_B(10551304L),
    EAC3_7_1_C(10616840L),
    EAC3_7_1_D(10682376L),
    EAC3_7_1_E(10747912L),
    EAC3_7_1_F(10813448L),
    EAC3_7_1_G(10878984L),
    EAC3_7_1_H(10944520L),
    DTS_3_1(11010052L),
    DTS_4_1(11075589L),
    DTS_6_0_A(11141126L),
    DTS_6_0_B(11206662L),
    DTS_6_0_C(11272198L),
    DTS_6_1_A(11337735L),
    DTS_6_1_B(11403271L),
    DTS_6_1_C(11468807L),
    DTS_7_0(11534343L),
    DTS_7_1(11599880L),
    DTS_8_0_A(11665416L),
    DTS_8_0_B(11730952L),
    DTS_8_1_A(11796489L),
    DTS_8_1_B(11862025L),
    DTS_6_1_D(11927559L),
    DiscreteInOrder(9633792L),
    Unknown(-65536L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioChannelLayoutTag/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioChannelLayoutTag/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioChannelLayoutTag/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioChannelLayoutTag/*</name>*/.class.getName());
    }
}
