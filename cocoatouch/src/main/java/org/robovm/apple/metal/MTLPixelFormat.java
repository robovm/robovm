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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/MTLPixelFormat/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Invalid(0L),
    A8Unorm(1L),
    R8Unorm(10L),
    R8Unorm_sRGB(11L),
    R8Snorm(12L),
    R8Uint(13L),
    R8Sint(14L),
    R16Unorm(20L),
    R16Snorm(22L),
    R16Uint(23L),
    R16Sint(24L),
    R16Float(25L),
    RG8Unorm(30L),
    RG8Unorm_sRGB(31L),
    RG8Snorm(32L),
    RG8Uint(33L),
    RG8Sint(34L),
    B5G6R5Unorm(40L),
    A1BGR5Unorm(41L),
    ABGR4Unorm(42L),
    R32Uint(53L),
    R32Sint(54L),
    R32Float(55L),
    RG16Unorm(60L),
    RG16Snorm(62L),
    RG16Uint(63L),
    RG16Sint(64L),
    RG16Float(65L),
    RGBA8Unorm(70L),
    RGBA8Unorm_sRGB(71L),
    RGBA8Snorm(72L),
    RGBA8Uint(73L),
    RGBA8Sint(74L),
    BGRA8Unorm(80L),
    BGRA8Unorm_sRGB(81L),
    RGB10A2Unorm(90L),
    RGB10A2Uint(91L),
    RG11B10Float(92L),
    RGB9E5Float(93L),
    RG32Uint(103L),
    RG32Sint(104L),
    RG32Float(105L),
    RGBA16Unorm(110L),
    RGBA16Snorm(112L),
    RGBA16Uint(113L),
    RGBA16Sint(114L),
    RGBA16Float(115L),
    RGBA32Uint(123L),
    RGBA32Sint(124L),
    RGBA32Float(125L),
    PVRTC_RGB_2BPP(160L),
    PVRTC_RGB_2BPP_sRGB(161L),
    PVRTC_RGB_4BPP(162L),
    PVRTC_RGB_4BPP_sRGB(163L),
    PVRTC_RGBA_2BPP(164L),
    PVRTC_RGBA_2BPP_sRGB(165L),
    PVRTC_RGBA_4BPP(166L),
    PVRTC_RGBA_4BPP_sRGB(167L),
    EAC_R11Unorm(170L),
    EAC_R11Snorm(172L),
    EAC_RG11Unorm(174L),
    EAC_RG11Snorm(176L),
    EAC_RGBA8(178L),
    EAC_RGBA8_sRGB(179L),
    ETC2_RGB8(180L),
    ETC2_RGB8_sRGB(181L),
    ETC2_RGB8A1(182L),
    ETC2_RGB8A1_sRGB(183L),
    ASTC_4x4_sRGB(186L),
    ASTC_5x4_sRGB(187L),
    ASTC_5x5_sRGB(188L),
    ASTC_6x5_sRGB(189L),
    ASTC_6x6_sRGB(190L),
    ASTC_8x5_sRGB(192L),
    ASTC_8x6_sRGB(193L),
    ASTC_8x8_sRGB(194L),
    ASTC_10x5_sRGB(195L),
    ASTC_10x6_sRGB(196L),
    ASTC_10x8_sRGB(197L),
    ASTC_10x10_sRGB(198L),
    ASTC_12x10_sRGB(199L),
    ASTC_12x12_sRGB(200L),
    ASTC_4x4_LDR(204L),
    ASTC_5x4_LDR(205L),
    ASTC_5x5_LDR(206L),
    ASTC_6x5_LDR(207L),
    ASTC_6x6_LDR(208L),
    ASTC_8x5_LDR(210L),
    ASTC_8x6_LDR(211L),
    ASTC_8x8_LDR(212L),
    ASTC_10x5_LDR(213L),
    ASTC_10x6_LDR(214L),
    ASTC_10x8_LDR(215L),
    ASTC_10x10_LDR(216L),
    ASTC_12x10_LDR(217L),
    ASTC_12x12_LDR(218L),
    GBGR422(240L),
    BGRG422(241L),
    Depth32Float(252L),
    Stencil8(253L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/MTLPixelFormat/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/MTLPixelFormat/*</name>*/ valueOf(long n) {
        for (/*<name>*/MTLPixelFormat/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/MTLPixelFormat/*</name>*/.class.getName());
    }
}
