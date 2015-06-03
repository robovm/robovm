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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFDataSearchFlags/*</name>*/ extends Bits</*<name>*/CFDataSearchFlags/*</name>*/> {
    /*<values>*/
    public static final CFDataSearchFlags None = new CFDataSearchFlags(0L);
    public static final CFDataSearchFlags Backwards = new CFDataSearchFlags(1L);
    public static final CFDataSearchFlags Anchored = new CFDataSearchFlags(2L);
    /*</values>*/

    private static final /*<name>*/CFDataSearchFlags/*</name>*/[] values = _values(/*<name>*/CFDataSearchFlags/*</name>*/.class);

    public /*<name>*/CFDataSearchFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/CFDataSearchFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFDataSearchFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFDataSearchFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/CFDataSearchFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFDataSearchFlags/*</name>*/[] values() {
        return values.clone();
    }
}
