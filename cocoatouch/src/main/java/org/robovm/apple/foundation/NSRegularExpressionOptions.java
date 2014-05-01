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

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSRegularExpressionOptions/*</name>*/ extends Bits</*<name>*/NSRegularExpressionOptions/*</name>*/> {
    /*<values>*/
    public static final NSRegularExpressionOptions CaseInsensitive = new NSRegularExpressionOptions(1L);
    public static final NSRegularExpressionOptions AllowCommentsAndWhitespace = new NSRegularExpressionOptions(2L);
    public static final NSRegularExpressionOptions IgnoreMetacharacters = new NSRegularExpressionOptions(4L);
    public static final NSRegularExpressionOptions DotMatchesLineSeparators = new NSRegularExpressionOptions(8L);
    public static final NSRegularExpressionOptions AnchorsMatchLines = new NSRegularExpressionOptions(16L);
    public static final NSRegularExpressionOptions UseUnixLineSeparators = new NSRegularExpressionOptions(32L);
    public static final NSRegularExpressionOptions UseUnicodeWordBoundaries = new NSRegularExpressionOptions(64L);
    /*</values>*/

    private static final /*<name>*/NSRegularExpressionOptions/*</name>*/[] values = _values(/*<name>*/NSRegularExpressionOptions/*</name>*/.class);

    public /*<name>*/NSRegularExpressionOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSRegularExpressionOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSRegularExpressionOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSRegularExpressionOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSRegularExpressionOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSRegularExpressionOptions/*</name>*/[] values() {
        return values.clone();
    }
}
