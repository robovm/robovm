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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AudioConverterProperty/*</name>*/ implements ValuedEnum {
    /*<values>*/
    PropertyMinimumInputBufferSize(1835623027L),
    PropertyMinimumOutputBufferSize(1836016243L),
    PropertyMaximumInputBufferSize(2020172403L),
    PropertyMaximumInputPacketSize(2020175987L),
    PropertyMaximumOutputPacketSize(2020569203L),
    PropertyCalculateInputBufferSize(1667850867L),
    PropertyCalculateOutputBufferSize(1668244083L),
    PropertyInputCodecParameters(1768121456L),
    PropertyOutputCodecParameters(1868784752L),
    SampleRateConverterAlgorithm(1936876393L),
    SampleRateConverterComplexity(1936876385L),
    SampleRateConverterQuality(1936876401L),
    SampleRateConverterInitialPhase(1936876400L),
    CodecQuality(1667527029L),
    PrimeMethod(1886547309L),
    PrimeInfo(1886546285L),
    ChannelMap(1667788144L),
    DecompressionMagicCookie(1684891491L),
    CompressionMagicCookie(1668114275L),
    EncodeBitRate(1651663220L),
    EncodeAdjustableSampleRate(1634366322L),
    InputChannelLayout(1768123424L),
    OutputChannelLayout(1868786720L),
    ApplicableEncodeBitRates(1634034290L),
    AvailableEncodeBitRates(1986355826L),
    ApplicableEncodeSampleRates(1634038642L),
    AvailableEncodeSampleRates(1986360178L),
    AvailableEncodeChannelLayoutTags(1634034540L),
    CurrentOutputStreamDescription(1633906532L),
    CurrentInputStreamDescription(1633904996L),
    PropertySettings(1633906803L),
    PropertyBitDepthHint(1633903204L),
    PropertyFormatList(1718383476L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioConverterProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioConverterProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioConverterProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioConverterProperty/*</name>*/.class.getName());
    }
}
