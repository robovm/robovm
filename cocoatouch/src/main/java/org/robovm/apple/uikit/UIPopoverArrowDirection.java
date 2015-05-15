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

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIPopoverArrowDirection/*</name>*/ extends Bits</*<name>*/UIPopoverArrowDirection/*</name>*/> {
    /*<values>*/
    public static final UIPopoverArrowDirection None = new UIPopoverArrowDirection(0L);
    public static final UIPopoverArrowDirection Up = new UIPopoverArrowDirection(1L);
    public static final UIPopoverArrowDirection Down = new UIPopoverArrowDirection(2L);
    public static final UIPopoverArrowDirection Left = new UIPopoverArrowDirection(4L);
    public static final UIPopoverArrowDirection Right = new UIPopoverArrowDirection(8L);
    public static final UIPopoverArrowDirection Any = new UIPopoverArrowDirection(15L);
    public static final UIPopoverArrowDirection Unknown = new UIPopoverArrowDirection(Bro.IS_32BIT ? 0xffffffffL : 0xffffffffffffffffL);
    /*</values>*/

    private static final /*<name>*/UIPopoverArrowDirection/*</name>*/[] values = _values(/*<name>*/UIPopoverArrowDirection/*</name>*/.class);

    public /*<name>*/UIPopoverArrowDirection/*</name>*/(long value) { super(value); }
    private /*<name>*/UIPopoverArrowDirection/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIPopoverArrowDirection/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIPopoverArrowDirection/*</name>*/(value, mask);
    }
    protected /*<name>*/UIPopoverArrowDirection/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIPopoverArrowDirection/*</name>*/[] values() {
        return values.clone();
    }
}
