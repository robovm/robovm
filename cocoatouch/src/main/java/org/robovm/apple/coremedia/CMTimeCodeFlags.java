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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CMTimeCodeFlags/*</name>*/ extends Bits</*<name>*/CMTimeCodeFlags/*</name>*/> {
    /*<values>*/
    public static final CMTimeCodeFlags None = new CMTimeCodeFlags(0L);
    public static final CMTimeCodeFlags DropFrame = new CMTimeCodeFlags(1L);
    public static final CMTimeCodeFlags _24HourMax = new CMTimeCodeFlags(2L);
    public static final CMTimeCodeFlags NegTimesOK = new CMTimeCodeFlags(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CMTimeCodeFlags/*</name>*/[] values = _values(/*<name>*/CMTimeCodeFlags/*</name>*/.class);

    public /*<name>*/CMTimeCodeFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/CMTimeCodeFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CMTimeCodeFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CMTimeCodeFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/CMTimeCodeFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CMTimeCodeFlags/*</name>*/[] values() {
        return values.clone();
    }
}
