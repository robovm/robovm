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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSLinguisticTaggerOptions/*</name>*/ extends Bits</*<name>*/NSLinguisticTaggerOptions/*</name>*/> {
    /*<values>*/
    public static final NSLinguisticTaggerOptions OmitWords = new NSLinguisticTaggerOptions(1L);
    public static final NSLinguisticTaggerOptions OmitPunctuation = new NSLinguisticTaggerOptions(2L);
    public static final NSLinguisticTaggerOptions OmitWhitespace = new NSLinguisticTaggerOptions(4L);
    public static final NSLinguisticTaggerOptions OmitOther = new NSLinguisticTaggerOptions(8L);
    public static final NSLinguisticTaggerOptions JoinNames = new NSLinguisticTaggerOptions(16L);
    /*</values>*/

    private static final /*<name>*/NSLinguisticTaggerOptions/*</name>*/[] values = _values(/*<name>*/NSLinguisticTaggerOptions/*</name>*/.class);

    public /*<name>*/NSLinguisticTaggerOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSLinguisticTaggerOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSLinguisticTaggerOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSLinguisticTaggerOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSLinguisticTaggerOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSLinguisticTaggerOptions/*</name>*/[] values() {
        return values.clone();
    }
}
