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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIControlState/*</name>*/ extends Bits</*<name>*/UIControlState/*</name>*/> {
    /*<values>*/
    public static final UIControlState Normal = new UIControlState(0L);
    public static final UIControlState Highlighted = new UIControlState(1L);
    public static final UIControlState Disabled = new UIControlState(2L);
    public static final UIControlState Selected = new UIControlState(4L);
    public static final UIControlState Application = new UIControlState(16711680L);
    public static final UIControlState Reserved = new UIControlState(-16777216L);
    /*</values>*/

    private static final /*<name>*/UIControlState/*</name>*/[] values = _values(/*<name>*/UIControlState/*</name>*/.class);

    public /*<name>*/UIControlState/*</name>*/(long value) { super(value); }
    private /*<name>*/UIControlState/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIControlState/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIControlState/*</name>*/(value, mask);
    }
    protected /*<name>*/UIControlState/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIControlState/*</name>*/[] values() {
        return values.clone();
    }
}
