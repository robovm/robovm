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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CMTimeFlag/*</name>*/ extends Bits</*<name>*/CMTimeFlag/*</name>*/> {
    /*<values>*/
    public static final CMTimeFlag None = new CMTimeFlag(0L);
    public static final CMTimeFlag Valid = new CMTimeFlag(1L);
    public static final CMTimeFlag HasBeenRounded = new CMTimeFlag(2L);
    public static final CMTimeFlag PositiveInfinity = new CMTimeFlag(4L);
    public static final CMTimeFlag NegativeInfinity = new CMTimeFlag(8L);
    public static final CMTimeFlag Indefinite = new CMTimeFlag(16L);
    public static final CMTimeFlag ImpliedValueFlagsMask = new CMTimeFlag(28L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CMTimeFlag/*</name>*/[] values = _values(/*<name>*/CMTimeFlag/*</name>*/.class);

    public /*<name>*/CMTimeFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/CMTimeFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CMTimeFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CMTimeFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/CMTimeFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CMTimeFlag/*</name>*/[] values() {
        return values.clone();
    }
}
