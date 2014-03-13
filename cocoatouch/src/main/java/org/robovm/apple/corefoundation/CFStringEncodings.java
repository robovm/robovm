/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CFStringEncodings/*</name>*/ implements ValuedEnum {
    /*<values>*/
    MacJapanese(1L),
    MacChineseTrad(2L),
    MacKorean(3L),
    MacArabic(4L),
    MacHebrew(5L),
    MacGreek(6L),
    MacCyrillic(7L),
    MacDevanagari(9L),
    MacGurmukhi(10L),
    MacGujarati(11L),
    MacOriya(12L),
    MacBengali(13L),
    MacTamil(14L),
    MacTelugu(15L),
    MacKannada(16L),
    MacMalayalam(17L),
    MacSinhalese(18L),
    MacBurmese(19L),
    MacKhmer(20L),
    MacThai(21L),
    MacLaotian(22L),
    MacGeorgian(23L),
    MacArmenian(24L),
    MacChineseSimp(25L),
    MacTibetan(26L),
    MacMongolian(27L),
    MacEthiopic(28L),
    MacCentralEurRoman(29L),
    MacVietnamese(30L),
    MacExtArabic(31L),
    MacSymbol(33L),
    MacDingbats(34L),
    MacTurkish(35L),
    MacCroatian(36L),
    MacIcelandic(37L),
    MacRomanian(38L),
    MacCeltic(39L),
    MacGaelic(40L),
    MacFarsi(140L),
    MacUkrainian(152L),
    MacInuit(236L),
    MacVT100(252L),
    MacHFS(255L),
    ISOLatin2(514L),
    ISOLatin3(515L),
    ISOLatin4(516L),
    ISOLatinCyrillic(517L),
    ISOLatinArabic(518L),
    ISOLatinGreek(519L),
    ISOLatinHebrew(520L),
    ISOLatin5(521L),
    ISOLatin6(522L),
    ISOLatinThai(523L),
    ISOLatin7(525L),
    ISOLatin8(526L),
    ISOLatin9(527L),
    ISOLatin10(528L),
    DOSLatinUS(1024L),
    DOSGreek(1029L),
    DOSBalticRim(1030L),
    DOSLatin1(1040L),
    DOSGreek1(1041L),
    DOSLatin2(1042L),
    DOSCyrillic(1043L),
    DOSTurkish(1044L),
    DOSPortuguese(1045L),
    DOSIcelandic(1046L),
    DOSHebrew(1047L),
    DOSCanadianFrench(1048L),
    DOSArabic(1049L),
    DOSNordic(1050L),
    DOSRussian(1051L),
    DOSGreek2(1052L),
    DOSThai(1053L),
    DOSJapanese(1056L),
    DOSChineseSimplif(1057L),
    DOSKorean(1058L),
    DOSChineseTrad(1059L),
    WindowsLatin2(1281L),
    WindowsCyrillic(1282L),
    WindowsGreek(1283L),
    WindowsLatin5(1284L),
    WindowsHebrew(1285L),
    WindowsArabic(1286L),
    WindowsBalticRim(1287L),
    WindowsVietnamese(1288L),
    WindowsKoreanJohab(1296L),
    ANSEL(1537L),
    JIS_X0201_76(1568L),
    JIS_X0208_83(1569L),
    JIS_X0208_90(1570L),
    JIS_X0212_90(1571L),
    JIS_C6226_78(1572L),
    ShiftJIS_X0213(1576L),
    ShiftJIS_X0213_MenKuTen(1577L),
    GB_2312_80(1584L),
    GBK_95(1585L),
    GB_18030_2000(1586L),
    KSC_5601_87(1600L),
    KSC_5601_92_Johab(1601L),
    CNS_11643_92_P1(1617L),
    CNS_11643_92_P2(1618L),
    CNS_11643_92_P3(1619L),
    ISO_2022_JP(2080L),
    ISO_2022_JP_2(2081L),
    ISO_2022_JP_1(2082L),
    ISO_2022_JP_3(2083L),
    ISO_2022_CN(2096L),
    ISO_2022_CN_EXT(2097L),
    ISO_2022_KR(2112L),
    EUC_JP(2336L),
    EUC_CN(2352L),
    EUC_TW(2353L),
    EUC_KR(2368L),
    ShiftJIS(2561L),
    KOI8_R(2562L),
    Big5(2563L),
    MacRomanLatin1(2564L),
    HZ_GB_2312(2565L),
    Big5_HKSCS_1999(2566L),
    VISCII(2567L),
    KOI8_U(2568L),
    Big5_E(2569L),
    NextStepJapanese(2818L),
    EBCDIC_US(3073L),
    EBCDIC_CP037(3074L),
    UTF7(67109120L),
    UTF7_IMAP(2576L),
    ShiftJIS_X0213_00(1576L);
    /*</values>*/

    private final long n;

    private /*<name>*/CFStringEncodings/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CFStringEncodings/*</name>*/ valueOf(long n) {
        for (/*<name>*/CFStringEncodings/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CFStringEncodings/*</name>*/.class.getName());
    }
}
