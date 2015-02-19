/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.coremotion;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CMAttitudeReferenceFrame/*</name>*/ extends Bits</*<name>*/CMAttitudeReferenceFrame/*</name>*/> {
    /*<values>*/
    public static final CMAttitudeReferenceFrame None = new CMAttitudeReferenceFrame(0L);
    public static final CMAttitudeReferenceFrame ArbitraryZVertical = new CMAttitudeReferenceFrame(1L);
    public static final CMAttitudeReferenceFrame ArbitraryCorrectedZVertical = new CMAttitudeReferenceFrame(2L);
    public static final CMAttitudeReferenceFrame MagneticNorthZVertical = new CMAttitudeReferenceFrame(4L);
    public static final CMAttitudeReferenceFrame TrueNorthZVertical = new CMAttitudeReferenceFrame(8L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CMAttitudeReferenceFrame/*</name>*/[] values = _values(/*<name>*/CMAttitudeReferenceFrame/*</name>*/.class);

    public /*<name>*/CMAttitudeReferenceFrame/*</name>*/(long value) { super(value); }
    private /*<name>*/CMAttitudeReferenceFrame/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CMAttitudeReferenceFrame/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CMAttitudeReferenceFrame/*</name>*/(value, mask);
    }
    protected /*<name>*/CMAttitudeReferenceFrame/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CMAttitudeReferenceFrame/*</name>*/[] values() {
        return values.clone();
    }
}
