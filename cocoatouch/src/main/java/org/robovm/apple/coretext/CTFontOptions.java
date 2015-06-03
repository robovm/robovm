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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CTFontOptions/*</name>*/ extends Bits</*<name>*/CTFontOptions/*</name>*/> {
    /*<values>*/
    public static final CTFontOptions None = new CTFontOptions(0L);
    public static final CTFontOptions Default = new CTFontOptions(0L);
    public static final CTFontOptions PreventAutoActivation = new CTFontOptions(1L);
    public static final CTFontOptions PreferSystemFont = new CTFontOptions(4L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTFontOptions/*</name>*/[] values = _values(/*<name>*/CTFontOptions/*</name>*/.class);

    public /*<name>*/CTFontOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CTFontOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTFontOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTFontOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CTFontOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTFontOptions/*</name>*/[] values() {
        return values.clone();
    }
}
