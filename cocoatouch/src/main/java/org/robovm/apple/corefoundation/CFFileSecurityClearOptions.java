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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFFileSecurityClearOptions/*</name>*/ extends Bits</*<name>*/CFFileSecurityClearOptions/*</name>*/> {
    /*<values>*/
    public static final CFFileSecurityClearOptions Owner = new CFFileSecurityClearOptions(1L);
    public static final CFFileSecurityClearOptions Group = new CFFileSecurityClearOptions(2L);
    public static final CFFileSecurityClearOptions Mode = new CFFileSecurityClearOptions(4L);
    public static final CFFileSecurityClearOptions OwnerUUID = new CFFileSecurityClearOptions(8L);
    public static final CFFileSecurityClearOptions GroupUUID = new CFFileSecurityClearOptions(16L);
    public static final CFFileSecurityClearOptions AccessControlList = new CFFileSecurityClearOptions(32L);
    /*</values>*/

    private static final /*<name>*/CFFileSecurityClearOptions/*</name>*/[] values = _values(/*<name>*/CFFileSecurityClearOptions/*</name>*/.class);

    public /*<name>*/CFFileSecurityClearOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CFFileSecurityClearOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFFileSecurityClearOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFFileSecurityClearOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CFFileSecurityClearOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFFileSecurityClearOptions/*</name>*/[] values() {
        return values.clone();
    }
}
