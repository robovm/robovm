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
public final class /*<name>*/CTFontCollectionCopyOptions/*</name>*/ extends Bits</*<name>*/CTFontCollectionCopyOptions/*</name>*/> {
    /*<values>*/
    public static final CTFontCollectionCopyOptions DefaultOptions = new CTFontCollectionCopyOptions(0L);
    public static final CTFontCollectionCopyOptions Unique = new CTFontCollectionCopyOptions(1L);
    public static final CTFontCollectionCopyOptions StandardSort = new CTFontCollectionCopyOptions(2L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTFontCollectionCopyOptions/*</name>*/[] values = _values(/*<name>*/CTFontCollectionCopyOptions/*</name>*/.class);

    public /*<name>*/CTFontCollectionCopyOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CTFontCollectionCopyOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTFontCollectionCopyOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTFontCollectionCopyOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CTFontCollectionCopyOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTFontCollectionCopyOptions/*</name>*/[] values() {
        return values.clone();
    }
}
