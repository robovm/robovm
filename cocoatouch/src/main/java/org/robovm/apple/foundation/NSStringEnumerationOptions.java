/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
public final class /*<name>*/NSStringEnumerationOptions/*</name>*/ extends Bits</*<name>*/NSStringEnumerationOptions/*</name>*/> {
    /*<values>*/
    public static final NSStringEnumerationOptions None = new NSStringEnumerationOptions(0L);
    public static final NSStringEnumerationOptions ByLines = new NSStringEnumerationOptions(0L);
    public static final NSStringEnumerationOptions ByParagraphs = new NSStringEnumerationOptions(1L);
    public static final NSStringEnumerationOptions ByComposedCharacterSequences = new NSStringEnumerationOptions(2L);
    public static final NSStringEnumerationOptions ByWords = new NSStringEnumerationOptions(3L);
    public static final NSStringEnumerationOptions BySentences = new NSStringEnumerationOptions(4L);
    public static final NSStringEnumerationOptions Reverse = new NSStringEnumerationOptions(256L);
    public static final NSStringEnumerationOptions SubstringNotRequired = new NSStringEnumerationOptions(512L);
    public static final NSStringEnumerationOptions Localized = new NSStringEnumerationOptions(1024L);
    /*</values>*/

    private static final /*<name>*/NSStringEnumerationOptions/*</name>*/[] values = _values(/*<name>*/NSStringEnumerationOptions/*</name>*/.class);

    public /*<name>*/NSStringEnumerationOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSStringEnumerationOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSStringEnumerationOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSStringEnumerationOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSStringEnumerationOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSStringEnumerationOptions/*</name>*/[] values() {
        return values.clone();
    }
}
