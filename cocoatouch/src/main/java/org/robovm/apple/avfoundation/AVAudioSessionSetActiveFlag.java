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
 * @since Available in iOS 4.0 and later.
 * @deprecated Deprecated in iOS 6.0.
 */
@Deprecated
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/ extends Bits</*<name>*/AVAudioSessionSetActiveFlag/*</name>*/> {
    /*<values>*/
    public static final AVAudioSessionSetActiveFlag None = new AVAudioSessionSetActiveFlag(0L);
    public static final AVAudioSessionSetActiveFlag NotifyOthersOnDeactivation = new AVAudioSessionSetActiveFlag(1L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/[] values = _values(/*<name>*/AVAudioSessionSetActiveFlag/*</name>*/.class);

    public /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AVAudioSessionSetActiveFlag/*</name>*/[] values() {
        return values.clone();
    }
}
