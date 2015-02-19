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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/ABPropertyType/*</name>*/ extends Bits</*<name>*/ABPropertyType/*</name>*/> {
    /*<values>*/
    public static final ABPropertyType None = new ABPropertyType(0L);
    public static final ABPropertyType Invalid = new ABPropertyType(0L);
    public static final ABPropertyType String = new ABPropertyType(1L);
    public static final ABPropertyType Integer = new ABPropertyType(2L);
    public static final ABPropertyType Real = new ABPropertyType(3L);
    public static final ABPropertyType DateTime = new ABPropertyType(4L);
    public static final ABPropertyType Dictionary = new ABPropertyType(5L);
    public static final ABPropertyType MultiString = new ABPropertyType(257L);
    public static final ABPropertyType MultiInteger = new ABPropertyType(258L);
    public static final ABPropertyType MultiReal = new ABPropertyType(259L);
    public static final ABPropertyType MultiDateTime = new ABPropertyType(260L);
    public static final ABPropertyType MultiDictionary = new ABPropertyType(261L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/ABPropertyType/*</name>*/[] values = _values(/*<name>*/ABPropertyType/*</name>*/.class);

    public /*<name>*/ABPropertyType/*</name>*/(long value) { super(value); }
    private /*<name>*/ABPropertyType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/ABPropertyType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/ABPropertyType/*</name>*/(value, mask);
    }
    protected /*<name>*/ABPropertyType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/ABPropertyType/*</name>*/[] values() {
        return values.clone();
    }
}
