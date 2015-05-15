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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSByteCountFormatterUnits/*</name>*/ extends Bits</*<name>*/NSByteCountFormatterUnits/*</name>*/> {
    /*<values>*/
    public static final NSByteCountFormatterUnits Default = new NSByteCountFormatterUnits(0L);
    public static final NSByteCountFormatterUnits Bytes = new NSByteCountFormatterUnits(1L);
    public static final NSByteCountFormatterUnits KB = new NSByteCountFormatterUnits(2L);
    public static final NSByteCountFormatterUnits MB = new NSByteCountFormatterUnits(4L);
    public static final NSByteCountFormatterUnits GB = new NSByteCountFormatterUnits(8L);
    public static final NSByteCountFormatterUnits TB = new NSByteCountFormatterUnits(16L);
    public static final NSByteCountFormatterUnits PB = new NSByteCountFormatterUnits(32L);
    public static final NSByteCountFormatterUnits EB = new NSByteCountFormatterUnits(64L);
    public static final NSByteCountFormatterUnits ZB = new NSByteCountFormatterUnits(128L);
    public static final NSByteCountFormatterUnits YBOrHigher = new NSByteCountFormatterUnits(65280L);
    public static final NSByteCountFormatterUnits All = new NSByteCountFormatterUnits(65535L);
    /*</values>*/

    private static final /*<name>*/NSByteCountFormatterUnits/*</name>*/[] values = _values(/*<name>*/NSByteCountFormatterUnits/*</name>*/.class);

    public /*<name>*/NSByteCountFormatterUnits/*</name>*/(long value) { super(value); }
    private /*<name>*/NSByteCountFormatterUnits/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSByteCountFormatterUnits/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSByteCountFormatterUnits/*</name>*/(value, mask);
    }
    protected /*<name>*/NSByteCountFormatterUnits/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSByteCountFormatterUnits/*</name>*/[] values() {
        return values.clone();
    }
}
