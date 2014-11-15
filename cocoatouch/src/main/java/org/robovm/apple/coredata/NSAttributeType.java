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
package org.robovm.apple.coredata;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/NSAttributeType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Undefined(0L),
    Integer16(100L),
    Integer32(200L),
    Integer64(300L),
    Decimal(400L),
    Double(500L),
    Float(600L),
    String(700L),
    Boolean(800L),
    Date(900L),
    BinaryData(1000L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    Transformable(1800L),
    /**
     * @since Available in iOS 3.0 and later.
     */
    ObjectID(2000L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSAttributeType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSAttributeType/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSAttributeType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSAttributeType/*</name>*/.class.getName());
    }
}
