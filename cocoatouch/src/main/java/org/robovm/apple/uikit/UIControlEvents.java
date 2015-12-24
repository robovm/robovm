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
public final class /*<name>*/UIControlEvents/*</name>*/ extends Bits</*<name>*/UIControlEvents/*</name>*/> {
    /*<values>*/
    public static final UIControlEvents None = new UIControlEvents(0L);
    public static final UIControlEvents TouchDown = new UIControlEvents(1L);
    public static final UIControlEvents TouchDownRepeat = new UIControlEvents(2L);
    public static final UIControlEvents TouchDragInside = new UIControlEvents(4L);
    public static final UIControlEvents TouchDragOutside = new UIControlEvents(8L);
    public static final UIControlEvents TouchDragEnter = new UIControlEvents(16L);
    public static final UIControlEvents TouchDragExit = new UIControlEvents(32L);
    public static final UIControlEvents TouchUpInside = new UIControlEvents(64L);
    public static final UIControlEvents TouchUpOutside = new UIControlEvents(128L);
    public static final UIControlEvents TouchCancel = new UIControlEvents(256L);
    public static final UIControlEvents ValueChanged = new UIControlEvents(4096L);
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final UIControlEvents PrimaryActionTriggered = new UIControlEvents(8192L);
    public static final UIControlEvents EditingDidBegin = new UIControlEvents(65536L);
    public static final UIControlEvents EditingChanged = new UIControlEvents(131072L);
    public static final UIControlEvents EditingDidEnd = new UIControlEvents(262144L);
    public static final UIControlEvents EditingDidEndOnExit = new UIControlEvents(524288L);
    public static final UIControlEvents AllTouchEvents = new UIControlEvents(4095L);
    public static final UIControlEvents AllEditingEvents = new UIControlEvents(983040L);
    public static final UIControlEvents ApplicationReserved = new UIControlEvents(251658240L);
    public static final UIControlEvents SystemReserved = new UIControlEvents(4026531840L);
    public static final UIControlEvents AllEvents = new UIControlEvents(4294967295L);
    /*</values>*/

    private static final /*<name>*/UIControlEvents/*</name>*/[] values = _values(/*<name>*/UIControlEvents/*</name>*/.class);

    public /*<name>*/UIControlEvents/*</name>*/(long value) { super(value); }
    private /*<name>*/UIControlEvents/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIControlEvents/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIControlEvents/*</name>*/(value, mask);
    }
    protected /*<name>*/UIControlEvents/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIControlEvents/*</name>*/[] values() {
        return values.clone();
    }
}
