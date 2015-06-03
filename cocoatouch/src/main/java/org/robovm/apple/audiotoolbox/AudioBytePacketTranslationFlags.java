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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/AudioBytePacketTranslationFlags/*</name>*/ extends Bits</*<name>*/AudioBytePacketTranslationFlags/*</name>*/> {
    /*<values>*/
    public static final AudioBytePacketTranslationFlags None = new AudioBytePacketTranslationFlags(0L);
    public static final AudioBytePacketTranslationFlags IsEstimate = new AudioBytePacketTranslationFlags(1L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioBytePacketTranslationFlags/*</name>*/[] values = _values(/*<name>*/AudioBytePacketTranslationFlags/*</name>*/.class);

    public /*<name>*/AudioBytePacketTranslationFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioBytePacketTranslationFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioBytePacketTranslationFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioBytePacketTranslationFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioBytePacketTranslationFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioBytePacketTranslationFlags/*</name>*/[] values() {
        return values.clone();
    }
}
