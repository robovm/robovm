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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSSearchPathDomainMask/*</name>*/ extends Bits</*<name>*/NSSearchPathDomainMask/*</name>*/> {
    /*<values>*/
    public static final NSSearchPathDomainMask None = new NSSearchPathDomainMask(0L);
    public static final NSSearchPathDomainMask UserDomainMask = new NSSearchPathDomainMask(1L);
    public static final NSSearchPathDomainMask LocalDomainMask = new NSSearchPathDomainMask(2L);
    public static final NSSearchPathDomainMask NetworkDomainMask = new NSSearchPathDomainMask(4L);
    public static final NSSearchPathDomainMask SystemDomainMask = new NSSearchPathDomainMask(8L);
    public static final NSSearchPathDomainMask AllDomainsMask = new NSSearchPathDomainMask(65535L);
    /*</values>*/

    private static final /*<name>*/NSSearchPathDomainMask/*</name>*/[] values = _values(/*<name>*/NSSearchPathDomainMask/*</name>*/.class);

    public /*<name>*/NSSearchPathDomainMask/*</name>*/(long value) { super(value); }
    private /*<name>*/NSSearchPathDomainMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSSearchPathDomainMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSSearchPathDomainMask/*</name>*/(value, mask);
    }
    protected /*<name>*/NSSearchPathDomainMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSSearchPathDomainMask/*</name>*/[] values() {
        return values.clone();
    }
}
