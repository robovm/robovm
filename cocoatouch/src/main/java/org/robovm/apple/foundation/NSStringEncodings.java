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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/
/*</annotations>*/
public enum /*<name>*/NSStringEncodings/*</name>*/ implements ValuedEnum {
    /*<values>*/
    ASCII(1L),
    NEXTSTEP(2L),
    JapaneseEUC(3L),
    UTF8(4L),
    ISOLatin1(5L),
    Symbol(6L),
    NonLossyASCII(7L),
    ShiftJIS(8L),
    ISOLatin2(9L),
    Unicode(10L),
    WindowsCP1251(11L),
    WindowsCP1252(12L),
    WindowsCP1253(13L),
    WindowsCP1254(14L),
    WindowsCP1250(15L),
    ISO2022JP(21L),
    MacOSRoman(30L),
    UTF16(10L),
    UTF16BigEndian(-1879047936L),
    UTF16LittleEndian(-1811939072L),
    UTF32(-1946156800L),
    UTF32BigEndian(-1744830208L),
    UTF32LittleEndian(-1677721344L),
    Proprietary(65536L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSStringEncodings/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSStringEncodings/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSStringEncodings/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSStringEncodings/*</name>*/.class.getName());
    }
}
