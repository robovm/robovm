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
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/ extends Bits</*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/> {
    /*<values>*/
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsFloat = new AudioStreamBasicDescriptionFlag(1L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsBigEndian = new AudioStreamBasicDescriptionFlag(2L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsSignedInteger = new AudioStreamBasicDescriptionFlag(4L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsPacked = new AudioStreamBasicDescriptionFlag(8L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsAlignedHigh = new AudioStreamBasicDescriptionFlag(16L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsNonInterleaved = new AudioStreamBasicDescriptionFlag(32L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagIsNonMixable = new AudioStreamBasicDescriptionFlag(64L);
    public static final AudioStreamBasicDescriptionFlag AudioFormatFlagsAreAllClear = new AudioStreamBasicDescriptionFlag(-2147483648L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsFloat = new AudioStreamBasicDescriptionFlag(1L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsBigEndian = new AudioStreamBasicDescriptionFlag(2L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsSignedInteger = new AudioStreamBasicDescriptionFlag(4L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsPacked = new AudioStreamBasicDescriptionFlag(8L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsAlignedHigh = new AudioStreamBasicDescriptionFlag(16L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsNonInterleaved = new AudioStreamBasicDescriptionFlag(32L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagIsNonMixable = new AudioStreamBasicDescriptionFlag(64L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagsSampleFractionShift = new AudioStreamBasicDescriptionFlag(7L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagsSampleFractionMask = new AudioStreamBasicDescriptionFlag(8064L);
    public static final AudioStreamBasicDescriptionFlag LinearPCMFormatFlagsAreAllClear = new AudioStreamBasicDescriptionFlag(-2147483648L);
    public static final AudioStreamBasicDescriptionFlag AppleLosslessFormatFlag_16BitSourceData = new AudioStreamBasicDescriptionFlag(1L);
    public static final AudioStreamBasicDescriptionFlag AppleLosslessFormatFlag_20BitSourceData = new AudioStreamBasicDescriptionFlag(2L);
    public static final AudioStreamBasicDescriptionFlag AppleLosslessFormatFlag_24BitSourceData = new AudioStreamBasicDescriptionFlag(3L);
    public static final AudioStreamBasicDescriptionFlag AppleLosslessFormatFlag_32BitSourceData = new AudioStreamBasicDescriptionFlag(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/[] values = _values(/*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/.class);

    public /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioStreamBasicDescriptionFlag/*</name>*/[] values() {
        return values.clone();
    }
}
