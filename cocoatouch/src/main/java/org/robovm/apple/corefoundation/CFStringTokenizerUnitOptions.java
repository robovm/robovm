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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFStringTokenizerUnitOptions/*</name>*/ extends Bits</*<name>*/CFStringTokenizerUnitOptions/*</name>*/> {
    /*<values>*/
    public static final CFStringTokenizerUnitOptions UnitWord = new CFStringTokenizerUnitOptions(0L);
    public static final CFStringTokenizerUnitOptions UnitSentence = new CFStringTokenizerUnitOptions(1L);
    public static final CFStringTokenizerUnitOptions UnitParagraph = new CFStringTokenizerUnitOptions(2L);
    public static final CFStringTokenizerUnitOptions UnitLineBreak = new CFStringTokenizerUnitOptions(3L);
    public static final CFStringTokenizerUnitOptions UnitWordBoundary = new CFStringTokenizerUnitOptions(4L);
    public static final CFStringTokenizerUnitOptions AttributeLatinTranscription = new CFStringTokenizerUnitOptions(65536L);
    public static final CFStringTokenizerUnitOptions AttributeLanguage = new CFStringTokenizerUnitOptions(131072L);
    /*</values>*/

    private static final /*<name>*/CFStringTokenizerUnitOptions/*</name>*/[] values = _values(/*<name>*/CFStringTokenizerUnitOptions/*</name>*/.class);

    public /*<name>*/CFStringTokenizerUnitOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CFStringTokenizerUnitOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFStringTokenizerUnitOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFStringTokenizerUnitOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CFStringTokenizerUnitOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFStringTokenizerUnitOptions/*</name>*/[] values() {
        return values.clone();
    }
}
