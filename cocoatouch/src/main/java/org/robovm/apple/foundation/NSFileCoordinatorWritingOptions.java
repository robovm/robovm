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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/ extends Bits</*<name>*/NSFileCoordinatorWritingOptions/*</name>*/> {
    /*<values>*/
    public static final NSFileCoordinatorWritingOptions None = new NSFileCoordinatorWritingOptions(0L);
    public static final NSFileCoordinatorWritingOptions Deleting = new NSFileCoordinatorWritingOptions(1L);
    public static final NSFileCoordinatorWritingOptions Moving = new NSFileCoordinatorWritingOptions(2L);
    public static final NSFileCoordinatorWritingOptions Merging = new NSFileCoordinatorWritingOptions(4L);
    public static final NSFileCoordinatorWritingOptions Replacing = new NSFileCoordinatorWritingOptions(8L);
    /*</values>*/

    private static final /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/[] values = _values(/*<name>*/NSFileCoordinatorWritingOptions/*</name>*/.class);

    public /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSFileCoordinatorWritingOptions/*</name>*/[] values() {
        return values.clone();
    }
}
