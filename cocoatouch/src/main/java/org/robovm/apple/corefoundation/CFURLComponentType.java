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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CFURLComponentType/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Scheme(1L),
    NetLocation(2L),
    Path(3L),
    ResourceSpecifier(4L),
    User(5L),
    Password(6L),
    UserInfo(7L),
    Host(8L),
    Port(9L),
    ParameterString(10L),
    Query(11L),
    Fragment(12L);
    /*</values>*/

    private final long n;

    private /*<name>*/CFURLComponentType/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CFURLComponentType/*</name>*/ valueOf(long n) {
        for (/*<name>*/CFURLComponentType/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CFURLComponentType/*</name>*/.class.getName());
    }
}
