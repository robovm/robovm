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
package org.robovm.apple.mediatoolbox;

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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/MTAudioProcessingTapFlag/*</name>*/ extends Bits</*<name>*/MTAudioProcessingTapFlag/*</name>*/> {
    /*<values>*/
    public static final MTAudioProcessingTapFlag StartOfStream = new MTAudioProcessingTapFlag(256L);
    public static final MTAudioProcessingTapFlag EndOfStream = new MTAudioProcessingTapFlag(512L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/MTAudioProcessingTapFlag/*</name>*/[] values = _values(/*<name>*/MTAudioProcessingTapFlag/*</name>*/.class);

    public /*<name>*/MTAudioProcessingTapFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/MTAudioProcessingTapFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MTAudioProcessingTapFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MTAudioProcessingTapFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/MTAudioProcessingTapFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MTAudioProcessingTapFlag/*</name>*/[] values() {
        return values.clone();
    }
}
