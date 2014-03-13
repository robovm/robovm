/*
 * Copyright (C) 2014 Trillian AB
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

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CFStringTokenizerTokenType/*</name>*/ extends Bits</*<name>*/CFStringTokenizerTokenType/*</name>*/> {
    /*<values>*/
    public static final CFStringTokenizerTokenType None = new CFStringTokenizerTokenType(0L);
    public static final CFStringTokenizerTokenType Normal = new CFStringTokenizerTokenType(1L);
    public static final CFStringTokenizerTokenType HasSubTokensMask = new CFStringTokenizerTokenType(2L);
    public static final CFStringTokenizerTokenType HasDerivedSubTokensMask = new CFStringTokenizerTokenType(4L);
    public static final CFStringTokenizerTokenType HasHasNumbersMask = new CFStringTokenizerTokenType(8L);
    public static final CFStringTokenizerTokenType HasNonLettersMask = new CFStringTokenizerTokenType(16L);
    public static final CFStringTokenizerTokenType IsCJWordMask = new CFStringTokenizerTokenType(32L);
    /*</values>*/

    private static final /*<name>*/CFStringTokenizerTokenType/*</name>*/[] values = _values(/*<name>*/CFStringTokenizerTokenType/*</name>*/.class);

    public /*<name>*/CFStringTokenizerTokenType/*</name>*/(long value) { super(value); }
    private /*<name>*/CFStringTokenizerTokenType/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CFStringTokenizerTokenType/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CFStringTokenizerTokenType/*</name>*/(value, mask);
    }
    protected /*<name>*/CFStringTokenizerTokenType/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CFStringTokenizerTokenType/*</name>*/[] values() {
        return values.clone();
    }
}
