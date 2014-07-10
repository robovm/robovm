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
public enum /*<name>*/AudioFormat/*</name>*/ implements ValuedEnum {
    /*<values>*/
    LinearPCM(1819304813L),
    AC3(1633889587L),
    _60958AC3(1667326771L),
    AppleIMA4(1768775988L),
    MPEG4AAC(1633772320L),
    MPEG4CELP(1667591280L),
    MPEG4HVXC(1752594531L),
    MPEG4TwinVQ(1953986161L),
    MACE3(1296122675L),
    MACE6(1296122678L),
    ULaw(1970037111L),
    ALaw(1634492791L),
    QDesign(1363430723L),
    QDesign2(1363430706L),
    QUALCOMM(1365470320L),
    MPEGLayer1(778924081L),
    MPEGLayer2(778924082L),
    MPEGLayer3(778924083L),
    TimeCode(1953066341L),
    MIDIStream(1835623529L),
    ParameterValueStream(1634760307L),
    AppleLossless(1634492771L),
    MPEG4AAC_HE(1633772392L),
    MPEG4AAC_LD(1633772396L),
    MPEG4AAC_ELD(1633772389L),
    MPEG4AAC_ELD_SBR(1633772390L),
    MPEG4AAC_ELD_V2(1633772391L),
    MPEG4AAC_HE_V2(1633772400L),
    MPEG4AAC_Spatial(1633772403L),
    AMR(1935764850L),
    Audible(1096107074L),
    iLBC(1768710755L),
    DVIIntelIMA(1836253201L),
    MicrosoftGSM(1836253233L),
    AES3(1634038579L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AudioFormat/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AudioFormat/*</name>*/ valueOf(long n) {
        for (/*<name>*/AudioFormat/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AudioFormat/*</name>*/.class.getName());
    }
}
