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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CFStringBuiltInEncodings/*</name>*/ implements ValuedEnum {
    /*<values>*/
    MacRoman(0L),
    WindowsLatin1(1280L),
    ISOLatin1(513L),
    NextStepLatin(2817L),
    ASCII(1536L),
    Unicode(256L),
    UTF8(134217984L),
    NonLossyASCII(3071L),
    UTF16(256L),
    UTF16BE(268435712L),
    UTF16LE(335544576L),
    UTF32(201326848L),
    UTF32BE(402653440L),
    UTF32LE(469762304L);
    /*</values>*/

    private final long n;

    private /*<name>*/CFStringBuiltInEncodings/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CFStringBuiltInEncodings/*</name>*/ valueOf(long n) {
        for (/*<name>*/CFStringBuiltInEncodings/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CFStringBuiltInEncodings/*</name>*/.class.getName());
    }
}
