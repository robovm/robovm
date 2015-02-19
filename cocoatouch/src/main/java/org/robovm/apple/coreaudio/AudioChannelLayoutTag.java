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
public final class /*<name>*/AudioChannelLayoutTag/*</name>*/ extends Bits</*<name>*/AudioChannelLayoutTag/*</name>*/> {
    /*<values>*/
    public static final AudioChannelLayoutTag None = new AudioChannelLayoutTag(0L);
    public static final AudioChannelLayoutTag UseChannelDescriptions = new AudioChannelLayoutTag(0L);
    public static final AudioChannelLayoutTag UseChannelBitmap = new AudioChannelLayoutTag(65536L);
    public static final AudioChannelLayoutTag Mono = new AudioChannelLayoutTag(6553601L);
    public static final AudioChannelLayoutTag Stereo = new AudioChannelLayoutTag(6619138L);
    public static final AudioChannelLayoutTag StereoHeadphones = new AudioChannelLayoutTag(6684674L);
    public static final AudioChannelLayoutTag MatrixStereo = new AudioChannelLayoutTag(6750210L);
    public static final AudioChannelLayoutTag MidSide = new AudioChannelLayoutTag(6815746L);
    public static final AudioChannelLayoutTag XY = new AudioChannelLayoutTag(6881282L);
    public static final AudioChannelLayoutTag Binaural = new AudioChannelLayoutTag(6946818L);
    public static final AudioChannelLayoutTag Ambisonic_B_Format = new AudioChannelLayoutTag(7012356L);
    public static final AudioChannelLayoutTag Quadraphonic = new AudioChannelLayoutTag(7077892L);
    public static final AudioChannelLayoutTag Pentagonal = new AudioChannelLayoutTag(7143429L);
    public static final AudioChannelLayoutTag Hexagonal = new AudioChannelLayoutTag(7208966L);
    public static final AudioChannelLayoutTag Octagonal = new AudioChannelLayoutTag(7274504L);
    public static final AudioChannelLayoutTag Cube = new AudioChannelLayoutTag(7340040L);
    public static final AudioChannelLayoutTag MPEG_1_0 = new AudioChannelLayoutTag(6553601L);
    public static final AudioChannelLayoutTag MPEG_2_0 = new AudioChannelLayoutTag(6619138L);
    public static final AudioChannelLayoutTag MPEG_3_0_A = new AudioChannelLayoutTag(7405571L);
    public static final AudioChannelLayoutTag MPEG_3_0_B = new AudioChannelLayoutTag(7471107L);
    public static final AudioChannelLayoutTag MPEG_4_0_A = new AudioChannelLayoutTag(7536644L);
    public static final AudioChannelLayoutTag MPEG_4_0_B = new AudioChannelLayoutTag(7602180L);
    public static final AudioChannelLayoutTag MPEG_5_0_A = new AudioChannelLayoutTag(7667717L);
    public static final AudioChannelLayoutTag MPEG_5_0_B = new AudioChannelLayoutTag(7733253L);
    public static final AudioChannelLayoutTag MPEG_5_0_C = new AudioChannelLayoutTag(7798789L);
    public static final AudioChannelLayoutTag MPEG_5_0_D = new AudioChannelLayoutTag(7864325L);
    public static final AudioChannelLayoutTag MPEG_5_1_A = new AudioChannelLayoutTag(7929862L);
    public static final AudioChannelLayoutTag MPEG_5_1_B = new AudioChannelLayoutTag(7995398L);
    public static final AudioChannelLayoutTag MPEG_5_1_C = new AudioChannelLayoutTag(8060934L);
    public static final AudioChannelLayoutTag MPEG_5_1_D = new AudioChannelLayoutTag(8126470L);
    public static final AudioChannelLayoutTag MPEG_6_1_A = new AudioChannelLayoutTag(8192007L);
    public static final AudioChannelLayoutTag MPEG_7_1_A = new AudioChannelLayoutTag(8257544L);
    public static final AudioChannelLayoutTag MPEG_7_1_B = new AudioChannelLayoutTag(8323080L);
    public static final AudioChannelLayoutTag MPEG_7_1_C = new AudioChannelLayoutTag(8388616L);
    public static final AudioChannelLayoutTag Emagic_Default_7_1 = new AudioChannelLayoutTag(8454152L);
    public static final AudioChannelLayoutTag SMPTE_DTV = new AudioChannelLayoutTag(8519688L);
    public static final AudioChannelLayoutTag ITU_1_0 = new AudioChannelLayoutTag(6553601L);
    public static final AudioChannelLayoutTag ITU_2_0 = new AudioChannelLayoutTag(6619138L);
    public static final AudioChannelLayoutTag ITU_2_1 = new AudioChannelLayoutTag(8585219L);
    public static final AudioChannelLayoutTag ITU_2_2 = new AudioChannelLayoutTag(8650756L);
    public static final AudioChannelLayoutTag ITU_3_0 = new AudioChannelLayoutTag(7405571L);
    public static final AudioChannelLayoutTag ITU_3_1 = new AudioChannelLayoutTag(7536644L);
    public static final AudioChannelLayoutTag ITU_3_2 = new AudioChannelLayoutTag(7667717L);
    public static final AudioChannelLayoutTag ITU_3_2_1 = new AudioChannelLayoutTag(7929862L);
    public static final AudioChannelLayoutTag ITU_3_4_1 = new AudioChannelLayoutTag(8388616L);
    public static final AudioChannelLayoutTag DVD_0 = new AudioChannelLayoutTag(6553601L);
    public static final AudioChannelLayoutTag DVD_1 = new AudioChannelLayoutTag(6619138L);
    public static final AudioChannelLayoutTag DVD_2 = new AudioChannelLayoutTag(8585219L);
    public static final AudioChannelLayoutTag DVD_3 = new AudioChannelLayoutTag(8650756L);
    public static final AudioChannelLayoutTag DVD_4 = new AudioChannelLayoutTag(8716291L);
    public static final AudioChannelLayoutTag DVD_5 = new AudioChannelLayoutTag(8781828L);
    public static final AudioChannelLayoutTag DVD_6 = new AudioChannelLayoutTag(8847365L);
    public static final AudioChannelLayoutTag DVD_7 = new AudioChannelLayoutTag(7405571L);
    public static final AudioChannelLayoutTag DVD_8 = new AudioChannelLayoutTag(7536644L);
    public static final AudioChannelLayoutTag DVD_9 = new AudioChannelLayoutTag(7667717L);
    public static final AudioChannelLayoutTag DVD_10 = new AudioChannelLayoutTag(8912900L);
    public static final AudioChannelLayoutTag DVD_11 = new AudioChannelLayoutTag(8978437L);
    public static final AudioChannelLayoutTag DVD_12 = new AudioChannelLayoutTag(7929862L);
    public static final AudioChannelLayoutTag DVD_13 = new AudioChannelLayoutTag(7536644L);
    public static final AudioChannelLayoutTag DVD_14 = new AudioChannelLayoutTag(7667717L);
    public static final AudioChannelLayoutTag DVD_15 = new AudioChannelLayoutTag(8912900L);
    public static final AudioChannelLayoutTag DVD_16 = new AudioChannelLayoutTag(8978437L);
    public static final AudioChannelLayoutTag DVD_17 = new AudioChannelLayoutTag(7929862L);
    public static final AudioChannelLayoutTag DVD_18 = new AudioChannelLayoutTag(9043973L);
    public static final AudioChannelLayoutTag DVD_19 = new AudioChannelLayoutTag(7733253L);
    public static final AudioChannelLayoutTag DVD_20 = new AudioChannelLayoutTag(7995398L);
    public static final AudioChannelLayoutTag AudioUnit_4 = new AudioChannelLayoutTag(7077892L);
    public static final AudioChannelLayoutTag AudioUnit_5 = new AudioChannelLayoutTag(7143429L);
    public static final AudioChannelLayoutTag AudioUnit_6 = new AudioChannelLayoutTag(7208966L);
    public static final AudioChannelLayoutTag AudioUnit_8 = new AudioChannelLayoutTag(7274504L);
    public static final AudioChannelLayoutTag AudioUnit_5_0 = new AudioChannelLayoutTag(7733253L);
    public static final AudioChannelLayoutTag AudioUnit_6_0 = new AudioChannelLayoutTag(9109510L);
    public static final AudioChannelLayoutTag AudioUnit_7_0 = new AudioChannelLayoutTag(9175047L);
    public static final AudioChannelLayoutTag AudioUnit_7_0_Front = new AudioChannelLayoutTag(9699335L);
    public static final AudioChannelLayoutTag AudioUnit_5_1 = new AudioChannelLayoutTag(7929862L);
    public static final AudioChannelLayoutTag AudioUnit_6_1 = new AudioChannelLayoutTag(8192007L);
    public static final AudioChannelLayoutTag AudioUnit_7_1 = new AudioChannelLayoutTag(8388616L);
    public static final AudioChannelLayoutTag AudioUnit_7_1_Front = new AudioChannelLayoutTag(8257544L);
    public static final AudioChannelLayoutTag AAC_3_0 = new AudioChannelLayoutTag(7471107L);
    public static final AudioChannelLayoutTag AAC_Quadraphonic = new AudioChannelLayoutTag(7077892L);
    public static final AudioChannelLayoutTag AAC_4_0 = new AudioChannelLayoutTag(7602180L);
    public static final AudioChannelLayoutTag AAC_5_0 = new AudioChannelLayoutTag(7864325L);
    public static final AudioChannelLayoutTag AAC_5_1 = new AudioChannelLayoutTag(8126470L);
    public static final AudioChannelLayoutTag AAC_6_0 = new AudioChannelLayoutTag(9240582L);
    public static final AudioChannelLayoutTag AAC_6_1 = new AudioChannelLayoutTag(9306119L);
    public static final AudioChannelLayoutTag AAC_7_0 = new AudioChannelLayoutTag(9371655L);
    public static final AudioChannelLayoutTag AAC_7_1 = new AudioChannelLayoutTag(8323080L);
    public static final AudioChannelLayoutTag AAC_7_1_B = new AudioChannelLayoutTag(11993096L);
    public static final AudioChannelLayoutTag AAC_7_1_C = new AudioChannelLayoutTag(12058632L);
    public static final AudioChannelLayoutTag AAC_Octagonal = new AudioChannelLayoutTag(9437192L);
    public static final AudioChannelLayoutTag TMH_10_2_std = new AudioChannelLayoutTag(9502736L);
    public static final AudioChannelLayoutTag TMH_10_2_full = new AudioChannelLayoutTag(9568277L);
    public static final AudioChannelLayoutTag AC3_1_0_1 = new AudioChannelLayoutTag(9764866L);
    public static final AudioChannelLayoutTag AC3_3_0 = new AudioChannelLayoutTag(9830403L);
    public static final AudioChannelLayoutTag AC3_3_1 = new AudioChannelLayoutTag(9895940L);
    public static final AudioChannelLayoutTag AC3_3_0_1 = new AudioChannelLayoutTag(9961476L);
    public static final AudioChannelLayoutTag AC3_2_1_1 = new AudioChannelLayoutTag(10027012L);
    public static final AudioChannelLayoutTag AC3_3_1_1 = new AudioChannelLayoutTag(10092549L);
    public static final AudioChannelLayoutTag EAC_6_0_A = new AudioChannelLayoutTag(10158086L);
    public static final AudioChannelLayoutTag EAC_7_0_A = new AudioChannelLayoutTag(10223623L);
    public static final AudioChannelLayoutTag EAC3_6_1_A = new AudioChannelLayoutTag(10289159L);
    public static final AudioChannelLayoutTag EAC3_6_1_B = new AudioChannelLayoutTag(10354695L);
    public static final AudioChannelLayoutTag EAC3_6_1_C = new AudioChannelLayoutTag(10420231L);
    public static final AudioChannelLayoutTag EAC3_7_1_A = new AudioChannelLayoutTag(10485768L);
    public static final AudioChannelLayoutTag EAC3_7_1_B = new AudioChannelLayoutTag(10551304L);
    public static final AudioChannelLayoutTag EAC3_7_1_C = new AudioChannelLayoutTag(10616840L);
    public static final AudioChannelLayoutTag EAC3_7_1_D = new AudioChannelLayoutTag(10682376L);
    public static final AudioChannelLayoutTag EAC3_7_1_E = new AudioChannelLayoutTag(10747912L);
    public static final AudioChannelLayoutTag EAC3_7_1_F = new AudioChannelLayoutTag(10813448L);
    public static final AudioChannelLayoutTag EAC3_7_1_G = new AudioChannelLayoutTag(10878984L);
    public static final AudioChannelLayoutTag EAC3_7_1_H = new AudioChannelLayoutTag(10944520L);
    public static final AudioChannelLayoutTag DTS_3_1 = new AudioChannelLayoutTag(11010052L);
    public static final AudioChannelLayoutTag DTS_4_1 = new AudioChannelLayoutTag(11075589L);
    public static final AudioChannelLayoutTag DTS_6_0_A = new AudioChannelLayoutTag(11141126L);
    public static final AudioChannelLayoutTag DTS_6_0_B = new AudioChannelLayoutTag(11206662L);
    public static final AudioChannelLayoutTag DTS_6_0_C = new AudioChannelLayoutTag(11272198L);
    public static final AudioChannelLayoutTag DTS_6_1_A = new AudioChannelLayoutTag(11337735L);
    public static final AudioChannelLayoutTag DTS_6_1_B = new AudioChannelLayoutTag(11403271L);
    public static final AudioChannelLayoutTag DTS_6_1_C = new AudioChannelLayoutTag(11468807L);
    public static final AudioChannelLayoutTag DTS_7_0 = new AudioChannelLayoutTag(11534343L);
    public static final AudioChannelLayoutTag DTS_7_1 = new AudioChannelLayoutTag(11599880L);
    public static final AudioChannelLayoutTag DTS_8_0_A = new AudioChannelLayoutTag(11665416L);
    public static final AudioChannelLayoutTag DTS_8_0_B = new AudioChannelLayoutTag(11730952L);
    public static final AudioChannelLayoutTag DTS_8_1_A = new AudioChannelLayoutTag(11796489L);
    public static final AudioChannelLayoutTag DTS_8_1_B = new AudioChannelLayoutTag(11862025L);
    public static final AudioChannelLayoutTag DTS_6_1_D = new AudioChannelLayoutTag(11927559L);
    public static final AudioChannelLayoutTag DiscreteInOrder = new AudioChannelLayoutTag(9633792L);
    public static final AudioChannelLayoutTag Unknown = new AudioChannelLayoutTag(-65536L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioChannelLayoutTag/*</name>*/[] values = _values(/*<name>*/AudioChannelLayoutTag/*</name>*/.class);

    public /*<name>*/AudioChannelLayoutTag/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioChannelLayoutTag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioChannelLayoutTag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioChannelLayoutTag/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioChannelLayoutTag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioChannelLayoutTag/*</name>*/[] values() {
        return values.clone();
    }
}
