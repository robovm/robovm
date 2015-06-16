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
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/AudioChannelFlags/*</name>*/ extends Bits</*<name>*/AudioChannelFlags/*</name>*/> {
    /*<values>*/
    public static final AudioChannelFlags None = new AudioChannelFlags(0L);
    public static final AudioChannelFlags AllOff = new AudioChannelFlags(0L);
    public static final AudioChannelFlags RectangularCoordinates = new AudioChannelFlags(1L);
    public static final AudioChannelFlags SphericalCoordinates = new AudioChannelFlags(2L);
    public static final AudioChannelFlags Meters = new AudioChannelFlags(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioChannelFlags/*</name>*/[] values = _values(/*<name>*/AudioChannelFlags/*</name>*/.class);

    public /*<name>*/AudioChannelFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioChannelFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioChannelFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioChannelFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioChannelFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioChannelFlags/*</name>*/[] values() {
        return values.clone();
    }
}
