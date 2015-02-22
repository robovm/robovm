/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
public final class /*<name>*/MTAudioProcessingTapFlags/*</name>*/ extends Bits</*<name>*/MTAudioProcessingTapFlags/*</name>*/> {
    /*<values>*/
    public static final MTAudioProcessingTapFlags None = new MTAudioProcessingTapFlags(0L);
    public static final MTAudioProcessingTapFlags StartOfStream = new MTAudioProcessingTapFlags(256L);
    public static final MTAudioProcessingTapFlags EndOfStream = new MTAudioProcessingTapFlags(512L);
    /*</values>*/

    public static class MTAudioProcessingTapFlagsPtr extends Struct<MTAudioProcessingTapFlagsPtr> {
        public MTAudioProcessingTapFlags get() {
            return new MTAudioProcessingTapFlags(getValue());
        }
        public void set(MTAudioProcessingTapFlags value) {
            setValue((int)value.value());
        }
        
        @StructMember(0)
        protected native int getValue();
        
        @StructMember(0)
        protected native void setValue(int value);
    };
    
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/MTAudioProcessingTapFlags/*</name>*/[] values = _values(/*<name>*/MTAudioProcessingTapFlags/*</name>*/.class);

    public /*<name>*/MTAudioProcessingTapFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/MTAudioProcessingTapFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MTAudioProcessingTapFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MTAudioProcessingTapFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/MTAudioProcessingTapFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MTAudioProcessingTapFlags/*</name>*/[] values() {
        return values.clone();
    }
}
