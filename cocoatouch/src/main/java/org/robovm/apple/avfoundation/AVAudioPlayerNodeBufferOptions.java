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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/ extends Bits</*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/> {
    /*<values>*/
    public static final AVAudioPlayerNodeBufferOptions None = new AVAudioPlayerNodeBufferOptions(0L);
    public static final AVAudioPlayerNodeBufferOptions Loops = new AVAudioPlayerNodeBufferOptions(1L);
    public static final AVAudioPlayerNodeBufferOptions Interrupts = new AVAudioPlayerNodeBufferOptions(2L);
    public static final AVAudioPlayerNodeBufferOptions InterruptsAtLoop = new AVAudioPlayerNodeBufferOptions(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/[] values = _values(/*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/.class);

    public /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AVAudioPlayerNodeBufferOptions/*</name>*/[] values() {
        return values.clone();
    }
}
