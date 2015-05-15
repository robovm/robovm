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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIUserNotificationType/*</name>*/ extends Bits</*<name>*/UIUserNotificationType/*</name>*/> {
    /*<values>*/
    public static final UIUserNotificationType None = new UIUserNotificationType(0L);
    public static final UIUserNotificationType Badge = new UIUserNotificationType(1L);
    public static final UIUserNotificationType Sound = new UIUserNotificationType(2L);
    public static final UIUserNotificationType Alert = new UIUserNotificationType(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/UIUserNotificationType/*</name>*/[] values = _values(/*<name>*/UIUserNotificationType/*</name>*/.class);

    public /*<name>*/UIUserNotificationType/*</name>*/(long value) { super(value); }
    private /*<name>*/UIUserNotificationType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIUserNotificationType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIUserNotificationType/*</name>*/(value, mask);
    }
    protected /*<name>*/UIUserNotificationType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIUserNotificationType/*</name>*/[] values() {
        return values.clone();
    }
}
