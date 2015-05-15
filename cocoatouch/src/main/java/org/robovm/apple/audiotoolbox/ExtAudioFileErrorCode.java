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
/*<annotations>*//*</annotations>*/
public enum /*<name>*/ExtAudioFileErrorCode/*</name>*/ implements ValuedEnum {
    /*<values>*/
    CodecUnavailableInputConsumed(-66559L),
    CodecUnavailableInputNotConsumed(-66560L),
    InvalidProperty(-66561L),
    InvalidPropertySize(-66562L),
    NonPCMClientFormat(-66563L),
    InvalidChannelMap(-66564L),
    InvalidOperationOrder(-66565L),
    InvalidDataFormat(-66566L),
    MaxPacketSizeUnknown(-66567L),
    InvalidSeek(-66568L),
    AsyncWriteTooLarge(-66569L),
    AsyncWriteBufferOverflow(-66570L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/ExtAudioFileErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/ExtAudioFileErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/ExtAudioFileErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/ExtAudioFileErrorCode/*</name>*/.class.getName());
    }
}
