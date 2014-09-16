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
public final class /*<name>*/NSPointerFunctionsOptions/*</name>*/ extends Bits</*<name>*/NSPointerFunctionsOptions/*</name>*/> {
    /*<values>*/
    public static final NSPointerFunctionsOptions None = new NSPointerFunctionsOptions(0L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions StrongMemory = new NSPointerFunctionsOptions(0L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions OpaqueMemory = new NSPointerFunctionsOptions(2L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions MallocMemory = new NSPointerFunctionsOptions(3L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions MachVirtualMemory = new NSPointerFunctionsOptions(4L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions WeakMemory = new NSPointerFunctionsOptions(5L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions ObjectPersonality = new NSPointerFunctionsOptions(0L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions OpaquePersonality = new NSPointerFunctionsOptions(256L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions ObjectPointerPersonality = new NSPointerFunctionsOptions(512L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions CStringPersonality = new NSPointerFunctionsOptions(768L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions StructPersonality = new NSPointerFunctionsOptions(1024L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions IntegerPersonality = new NSPointerFunctionsOptions(1280L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSPointerFunctionsOptions CopyIn = new NSPointerFunctionsOptions(65536L);
    /*</values>*/

    private static final /*<name>*/NSPointerFunctionsOptions/*</name>*/[] values = _values(/*<name>*/NSPointerFunctionsOptions/*</name>*/.class);

    public /*<name>*/NSPointerFunctionsOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSPointerFunctionsOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSPointerFunctionsOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSPointerFunctionsOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSPointerFunctionsOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSPointerFunctionsOptions/*</name>*/[] values() {
        return values.clone();
    }
}
