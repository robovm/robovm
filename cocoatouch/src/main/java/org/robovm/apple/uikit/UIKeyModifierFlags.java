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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIKeyModifierFlags/*</name>*/ extends Bits</*<name>*/UIKeyModifierFlags/*</name>*/> {
    /*<values>*/
    public static final UIKeyModifierFlags None = new UIKeyModifierFlags(0L);
    public static final UIKeyModifierFlags AlphaShift = new UIKeyModifierFlags(65536L);
    public static final UIKeyModifierFlags Shift = new UIKeyModifierFlags(131072L);
    public static final UIKeyModifierFlags Control = new UIKeyModifierFlags(262144L);
    public static final UIKeyModifierFlags Alternate = new UIKeyModifierFlags(524288L);
    public static final UIKeyModifierFlags Command = new UIKeyModifierFlags(1048576L);
    public static final UIKeyModifierFlags NumericPad = new UIKeyModifierFlags(2097152L);
    /*</values>*/

    private static final /*<name>*/UIKeyModifierFlags/*</name>*/[] values = _values(/*<name>*/UIKeyModifierFlags/*</name>*/.class);

    public /*<name>*/UIKeyModifierFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/UIKeyModifierFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIKeyModifierFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIKeyModifierFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/UIKeyModifierFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIKeyModifierFlags/*</name>*/[] values() {
        return values.clone();
    }
}
