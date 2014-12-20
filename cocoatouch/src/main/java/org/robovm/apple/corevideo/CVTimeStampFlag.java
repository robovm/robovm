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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CVTimeStampFlag/*</name>*/ extends Bits</*<name>*/CVTimeStampFlag/*</name>*/> {
    /*<values>*/
    public static final CVTimeStampFlag None = new CVTimeStampFlag(0L);
    public static final CVTimeStampFlag VideoTimeValid = new CVTimeStampFlag(1L);
    public static final CVTimeStampFlag HostTimeValid = new CVTimeStampFlag(2L);
    public static final CVTimeStampFlag SMPTETimeValid = new CVTimeStampFlag(4L);
    public static final CVTimeStampFlag VideoRefreshPeriodValid = new CVTimeStampFlag(8L);
    public static final CVTimeStampFlag RateScalarValid = new CVTimeStampFlag(16L);
    public static final CVTimeStampFlag TopField = new CVTimeStampFlag(65536L);
    public static final CVTimeStampFlag BottomField = new CVTimeStampFlag(131072L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CVTimeStampFlag/*</name>*/[] values = _values(/*<name>*/CVTimeStampFlag/*</name>*/.class);

    public /*<name>*/CVTimeStampFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/CVTimeStampFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CVTimeStampFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CVTimeStampFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/CVTimeStampFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CVTimeStampFlag/*</name>*/[] values() {
        return values.clone();
    }
}
