/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSKeyValueSetMutationKind/*</name>*/ extends Bits</*<name>*/NSKeyValueSetMutationKind/*</name>*/> {
    /*<values>*/
    public static final NSKeyValueSetMutationKind Union = new NSKeyValueSetMutationKind(1L);
    public static final NSKeyValueSetMutationKind Minus = new NSKeyValueSetMutationKind(2L);
    public static final NSKeyValueSetMutationKind Intersect = new NSKeyValueSetMutationKind(3L);
    public static final NSKeyValueSetMutationKind Set = new NSKeyValueSetMutationKind(4L);
    /*</values>*/

    private static final /*<name>*/NSKeyValueSetMutationKind/*</name>*/[] values = _values(/*<name>*/NSKeyValueSetMutationKind/*</name>*/.class);

    public /*<name>*/NSKeyValueSetMutationKind/*</name>*/(long value) { super(value); }
    private /*<name>*/NSKeyValueSetMutationKind/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSKeyValueSetMutationKind/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSKeyValueSetMutationKind/*</name>*/(value, mask);
    }
    protected /*<name>*/NSKeyValueSetMutationKind/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSKeyValueSetMutationKind/*</name>*/[] values() {
        return values.clone();
    }
}
