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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/ extends Bits</*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/> {
    /*<values>*/
    public static final NSPersonNameComponentsFormatterOptions None = new NSPersonNameComponentsFormatterOptions(0L);
    public static final NSPersonNameComponentsFormatterOptions Phonetic = new NSPersonNameComponentsFormatterOptions(2L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/[] values = _values(/*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/.class);

    public /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSPersonNameComponentsFormatterOptions/*</name>*/[] values() {
        return values.clone();
    }
}
