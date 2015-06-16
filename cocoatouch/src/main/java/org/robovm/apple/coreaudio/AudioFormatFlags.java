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
package org.robovm.apple.coreaudio;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/AudioFormatFlags/*</name>*/ extends Bits</*<name>*/AudioFormatFlags/*</name>*/> {
    /*<values>*/
    public static final AudioFormatFlags None = new AudioFormatFlags(0L);
    public static final AudioFormatFlags AudioFormatFlagIsFloat = new AudioFormatFlags(1L);
    public static final AudioFormatFlags AudioFormatFlagIsBigEndian = new AudioFormatFlags(2L);
    public static final AudioFormatFlags AudioFormatFlagIsSignedInteger = new AudioFormatFlags(4L);
    public static final AudioFormatFlags AudioFormatFlagIsPacked = new AudioFormatFlags(8L);
    public static final AudioFormatFlags AudioFormatFlagIsAlignedHigh = new AudioFormatFlags(16L);
    public static final AudioFormatFlags AudioFormatFlagIsNonInterleaved = new AudioFormatFlags(32L);
    public static final AudioFormatFlags AudioFormatFlagIsNonMixable = new AudioFormatFlags(64L);
    public static final AudioFormatFlags AreAllClear = new AudioFormatFlags(-2147483648L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsFloat = new AudioFormatFlags(1L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsBigEndian = new AudioFormatFlags(2L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsSignedInteger = new AudioFormatFlags(4L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsPacked = new AudioFormatFlags(8L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsAlignedHigh = new AudioFormatFlags(16L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsNonInterleaved = new AudioFormatFlags(32L);
    public static final AudioFormatFlags LinearPCMFormatFlagIsNonMixable = new AudioFormatFlags(64L);
    public static final AudioFormatFlags LinearPCMSampleFractionShift = new AudioFormatFlags(7L);
    public static final AudioFormatFlags LinearPCMSampleFractionMask = new AudioFormatFlags(8064L);
    public static final AudioFormatFlags LinearPCMAreAllClear = new AudioFormatFlags(-2147483648L);
    public static final AudioFormatFlags AppleLossless16BitSourceData = new AudioFormatFlags(1L);
    public static final AudioFormatFlags AppleLossless20BitSourceData = new AudioFormatFlags(2L);
    public static final AudioFormatFlags AppleLossless24BitSourceData = new AudioFormatFlags(3L);
    public static final AudioFormatFlags AppleLossless32BitSourceData = new AudioFormatFlags(4L);
    public static final AudioFormatFlags NativeEndian = new AudioFormatFlags(0L);
    public static final AudioFormatFlags Canonical = new AudioFormatFlags(12L);
    public static final AudioFormatFlags AudioUnitCanonical = new AudioFormatFlags(3116L);
    public static final AudioFormatFlags NativeFloatPacked = new AudioFormatFlags(9L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioFormatFlags/*</name>*/[] values = _values(/*<name>*/AudioFormatFlags/*</name>*/.class);

    public /*<name>*/AudioFormatFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioFormatFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioFormatFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioFormatFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioFormatFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioFormatFlags/*</name>*/[] values() {
        return values.clone();
    }
}
