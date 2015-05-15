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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CGGradientDrawingOptions/*</name>*/ extends Bits</*<name>*/CGGradientDrawingOptions/*</name>*/> {
    /*<values>*/
    public static final CGGradientDrawingOptions None = new CGGradientDrawingOptions(0L);
    public static final CGGradientDrawingOptions BeforeStartLocation = new CGGradientDrawingOptions(1L);
    public static final CGGradientDrawingOptions AfterEndLocation = new CGGradientDrawingOptions(2L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CGGradientDrawingOptions/*</name>*/[] values = _values(/*<name>*/CGGradientDrawingOptions/*</name>*/.class);

    public /*<name>*/CGGradientDrawingOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CGGradientDrawingOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CGGradientDrawingOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CGGradientDrawingOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CGGradientDrawingOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CGGradientDrawingOptions/*</name>*/[] values() {
        return values.clone();
    }
}
