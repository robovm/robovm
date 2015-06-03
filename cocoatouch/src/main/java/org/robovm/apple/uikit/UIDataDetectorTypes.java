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

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIDataDetectorTypes/*</name>*/ extends Bits</*<name>*/UIDataDetectorTypes/*</name>*/> {
    /*<values>*/
    public static final UIDataDetectorTypes PhoneNumber = new UIDataDetectorTypes(1L);
    public static final UIDataDetectorTypes Link = new UIDataDetectorTypes(2L);
    public static final UIDataDetectorTypes Address = new UIDataDetectorTypes(4L);
    public static final UIDataDetectorTypes CalendarEvent = new UIDataDetectorTypes(8L);
    public static final UIDataDetectorTypes None = new UIDataDetectorTypes(0L);
    public static final UIDataDetectorTypes All = new UIDataDetectorTypes(Bro.IS_32BIT ? 0xffffffffL : 0xffffffffffffffffL);
    /*</values>*/

    private static final /*<name>*/UIDataDetectorTypes/*</name>*/[] values = _values(/*<name>*/UIDataDetectorTypes/*</name>*/.class);

    public /*<name>*/UIDataDetectorTypes/*</name>*/(long value) { super(value); }
    private /*<name>*/UIDataDetectorTypes/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIDataDetectorTypes/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIDataDetectorTypes/*</name>*/(value, mask);
    }
    protected /*<name>*/UIDataDetectorTypes/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIDataDetectorTypes/*</name>*/[] values() {
        return values.clone();
    }
}
