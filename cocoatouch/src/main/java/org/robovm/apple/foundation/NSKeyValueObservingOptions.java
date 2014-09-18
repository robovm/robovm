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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSKeyValueObservingOptions/*</name>*/ extends Bits</*<name>*/NSKeyValueObservingOptions/*</name>*/> {
    /*<values>*/
    public static final NSKeyValueObservingOptions None = new NSKeyValueObservingOptions(0L);
    public static final NSKeyValueObservingOptions New = new NSKeyValueObservingOptions(1L);
    public static final NSKeyValueObservingOptions Old = new NSKeyValueObservingOptions(2L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSKeyValueObservingOptions Initial = new NSKeyValueObservingOptions(4L);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSKeyValueObservingOptions Prior = new NSKeyValueObservingOptions(8L);
    /*</values>*/

    private static final /*<name>*/NSKeyValueObservingOptions/*</name>*/[] values = _values(/*<name>*/NSKeyValueObservingOptions/*</name>*/.class);

    public /*<name>*/NSKeyValueObservingOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSKeyValueObservingOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSKeyValueObservingOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSKeyValueObservingOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSKeyValueObservingOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSKeyValueObservingOptions/*</name>*/[] values() {
        return values.clone();
    }
}
