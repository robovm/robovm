/*
 * Copyright (C) 2014 Trillian AB
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

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIRectCorner/*</name>*/ extends Bits</*<name>*/UIRectCorner/*</name>*/> {
    /*<values>*/
    public static final UIRectCorner TopLeft = new UIRectCorner(1L);
    public static final UIRectCorner TopRight = new UIRectCorner(2L);
    public static final UIRectCorner BottomLeft = new UIRectCorner(4L);
    public static final UIRectCorner BottomRight = new UIRectCorner(8L);
    public static final UIRectCorner AllCorners = new UIRectCorner(-1L);
    /*</values>*/

    private static final /*<name>*/UIRectCorner/*</name>*/[] values = _values(/*<name>*/UIRectCorner/*</name>*/.class);

    public /*<name>*/UIRectCorner/*</name>*/(long value) { super(value); }
    private /*<name>*/UIRectCorner/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIRectCorner/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIRectCorner/*</name>*/(value, mask);
    }
    protected /*<name>*/UIRectCorner/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIRectCorner/*</name>*/[] values() {
        return values.clone();
    }
}
