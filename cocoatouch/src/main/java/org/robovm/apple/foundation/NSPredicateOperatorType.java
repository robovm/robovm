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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/NSPredicateOperatorType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    LessThan(0L),
    LessThanOrEqualTo(1L),
    GreaterThan(2L),
    GreaterThanOrEqualTo(3L),
    EqualTo(4L),
    NotEqualTo(5L),
    Matches(6L),
    Like(7L),
    BeginsWith(8L),
    EndsWith(9L),
    In(10L),
    CustomSelector(11L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    Contains(99L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    Between(100L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSPredicateOperatorType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSPredicateOperatorType/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSPredicateOperatorType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSPredicateOperatorType/*</name>*/.class.getName());
    }
}
