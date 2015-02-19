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
package org.robovm.apple.passkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/PKAddressField/*</name>*/ extends Bits</*<name>*/PKAddressField/*</name>*/> {
    /*<values>*/
    public static final PKAddressField None = new PKAddressField(0L);
    public static final PKAddressField PostalAddress = new PKAddressField(1L);
    public static final PKAddressField Phone = new PKAddressField(2L);
    public static final PKAddressField Email = new PKAddressField(4L);
    public static final PKAddressField All = new PKAddressField(7L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/PKAddressField/*</name>*/[] values = _values(/*<name>*/PKAddressField/*</name>*/.class);

    public /*<name>*/PKAddressField/*</name>*/(long value) { super(value); }
    private /*<name>*/PKAddressField/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/PKAddressField/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/PKAddressField/*</name>*/(value, mask);
    }
    protected /*<name>*/PKAddressField/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/PKAddressField/*</name>*/[] values() {
        return values.clone();
    }
}
