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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/ExtAudioFileProperty/*</name>*/ implements ValuedEnum {
    /*<values>*/
    FileDataFormat(1717988724L),
    FileChannelLayout(1717791855L),
    ClientDataFormat(1667657076L),
    ClientChannelLayout(1667460207L),
    CodecManufacturer(1668112750L),
    AudioConverter(1633906294L),
    AudioFile(1634101612L),
    FileMaxPacketSize(1718448243L),
    ClientMaxPacketSize(1668116595L),
    FileLengthFrames(593916525L),
    ConverterConfig(1633903462L),
    IOBufferSizeBytes(1768907379L),
    IOBuffer(1768907366L),
    PacketTable(2020635753L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/ExtAudioFileProperty/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/ExtAudioFileProperty/*</name>*/ valueOf(long n) {
        for (/*<name>*/ExtAudioFileProperty/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/ExtAudioFileProperty/*</name>*/.class.getName());
    }
}
