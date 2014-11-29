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
public final class /*<name>*/AVAudioSessionRecordPermission/*</name>*/ extends Bits</*<name>*/AVAudioSessionRecordPermission/*</name>*/> {
    /*<values>*/
    public static final AVAudioSessionRecordPermission None = new AVAudioSessionRecordPermission(0L);
    public static final AVAudioSessionRecordPermission Undetermined = new AVAudioSessionRecordPermission(1970168948L);
    public static final AVAudioSessionRecordPermission Denied = new AVAudioSessionRecordPermission(1684369017L);
    public static final AVAudioSessionRecordPermission Granted = new AVAudioSessionRecordPermission(1735552628L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AVAudioSessionRecordPermission/*</name>*/[] values = _values(/*<name>*/AVAudioSessionRecordPermission/*</name>*/.class);

    public /*<name>*/AVAudioSessionRecordPermission/*</name>*/(long value) { super(value); }
    private /*<name>*/AVAudioSessionRecordPermission/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AVAudioSessionRecordPermission/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AVAudioSessionRecordPermission/*</name>*/(value, mask);
    }
    protected /*<name>*/AVAudioSessionRecordPermission/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AVAudioSessionRecordPermission/*</name>*/[] values() {
        return values.clone();
    }
}
