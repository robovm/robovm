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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSStringCompareOptions/*</name>*/ extends Bits</*<name>*/NSStringCompareOptions/*</name>*/> {
    /*<values>*/
    public static final NSStringCompareOptions CaseInsensitive = new NSStringCompareOptions(1L);
    public static final NSStringCompareOptions Literal = new NSStringCompareOptions(2L);
    public static final NSStringCompareOptions Backwards = new NSStringCompareOptions(4L);
    public static final NSStringCompareOptions Anchored = new NSStringCompareOptions(8L);
    public static final NSStringCompareOptions Numeric = new NSStringCompareOptions(64L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStringCompareOptions DiacriticInsensitive = new NSStringCompareOptions(128L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStringCompareOptions WidthInsensitive = new NSStringCompareOptions(256L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStringCompareOptions ForcedOrdering = new NSStringCompareOptions(512L);
    /**
     * @since Available in iOS 3.2 and later.
     */
    public static final NSStringCompareOptions RegularExpression = new NSStringCompareOptions(1024L);
    /*</values>*/

    private static final /*<name>*/NSStringCompareOptions/*</name>*/[] values = _values(/*<name>*/NSStringCompareOptions/*</name>*/.class);

    public /*<name>*/NSStringCompareOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSStringCompareOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSStringCompareOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSStringCompareOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSStringCompareOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSStringCompareOptions/*</name>*/[] values() {
        return values.clone();
    }
}
