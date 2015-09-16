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
package org.robovm.apple.security;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/SecRevocationPolicyFlags/*</name>*/ extends Bits</*<name>*/SecRevocationPolicyFlags/*</name>*/> {
    /*<values>*/
    public static final SecRevocationPolicyFlags None = new SecRevocationPolicyFlags(0L);
    public static final SecRevocationPolicyFlags OCSPMethod = new SecRevocationPolicyFlags(1L);
    public static final SecRevocationPolicyFlags CRLMethod = new SecRevocationPolicyFlags(2L);
    public static final SecRevocationPolicyFlags PreferCRL = new SecRevocationPolicyFlags(4L);
    public static final SecRevocationPolicyFlags RequirePositiveResponse = new SecRevocationPolicyFlags(8L);
    public static final SecRevocationPolicyFlags NetworkAccessDisabled = new SecRevocationPolicyFlags(16L);
    public static final SecRevocationPolicyFlags UseAnyAvailableMethod = new SecRevocationPolicyFlags(3L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/SecRevocationPolicyFlags/*</name>*/[] values = _values(/*<name>*/SecRevocationPolicyFlags/*</name>*/.class);

    public /*<name>*/SecRevocationPolicyFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/SecRevocationPolicyFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/SecRevocationPolicyFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/SecRevocationPolicyFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/SecRevocationPolicyFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/SecRevocationPolicyFlags/*</name>*/[] values() {
        return values.clone();
    }
}
