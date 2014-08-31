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
package org.robovm.apple.coretext;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CTUnderlineStyle/*</name>*/ extends Bits</*<name>*/CTUnderlineStyle/*</name>*/> {
    /*<values>*/
    public static final CTUnderlineStyle None = new CTUnderlineStyle(0L);
    public static final CTUnderlineStyle Single = new CTUnderlineStyle(1L);
    public static final CTUnderlineStyle Thick = new CTUnderlineStyle(2L);
    public static final CTUnderlineStyle Double = new CTUnderlineStyle(9L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTUnderlineStyle/*</name>*/[] values = _values(/*<name>*/CTUnderlineStyle/*</name>*/.class);

    public /*<name>*/CTUnderlineStyle/*</name>*/(long value) { super(value); }
    private /*<name>*/CTUnderlineStyle/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTUnderlineStyle/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTUnderlineStyle/*</name>*/(value, mask);
    }
    protected /*<name>*/CTUnderlineStyle/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTUnderlineStyle/*</name>*/[] values() {
        return values.clone();
    }
}
