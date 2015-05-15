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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/MTLColorWriteMask/*</name>*/ extends Bits</*<name>*/MTLColorWriteMask/*</name>*/> {
    /*<values>*/
    public static final MTLColorWriteMask None = new MTLColorWriteMask(0L);
    public static final MTLColorWriteMask Red = new MTLColorWriteMask(8L);
    public static final MTLColorWriteMask Green = new MTLColorWriteMask(4L);
    public static final MTLColorWriteMask Blue = new MTLColorWriteMask(2L);
    public static final MTLColorWriteMask Alpha = new MTLColorWriteMask(1L);
    public static final MTLColorWriteMask All = new MTLColorWriteMask(15L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/MTLColorWriteMask/*</name>*/[] values = _values(/*<name>*/MTLColorWriteMask/*</name>*/.class);

    public /*<name>*/MTLColorWriteMask/*</name>*/(long value) { super(value); }
    private /*<name>*/MTLColorWriteMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MTLColorWriteMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MTLColorWriteMask/*</name>*/(value, mask);
    }
    protected /*<name>*/MTLColorWriteMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MTLColorWriteMask/*</name>*/[] values() {
        return values.clone();
    }
}
