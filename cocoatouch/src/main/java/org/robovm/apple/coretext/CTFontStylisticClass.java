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
/*<annotations>*//*</annotations>*/
public final class /*<name>*/CTFontStylisticClass/*</name>*/ extends Bits</*<name>*/CTFontStylisticClass/*</name>*/> {
    /*<values>*/
    public static final CTFontStylisticClass None = new CTFontStylisticClass(0L);
    public static final CTFontStylisticClass ClassUnknown = new CTFontStylisticClass(0L);
    public static final CTFontStylisticClass ClassOldStyleSerifs = new CTFontStylisticClass(268435456L);
    public static final CTFontStylisticClass ClassTransitionalSerifs = new CTFontStylisticClass(536870912L);
    public static final CTFontStylisticClass ClassModernSerifs = new CTFontStylisticClass(805306368L);
    public static final CTFontStylisticClass ClassClarendonSerifs = new CTFontStylisticClass(1073741824L);
    public static final CTFontStylisticClass ClassSlabSerifs = new CTFontStylisticClass(1342177280L);
    public static final CTFontStylisticClass ClassFreeformSerifs = new CTFontStylisticClass(1879048192L);
    public static final CTFontStylisticClass ClassSansSerif = new CTFontStylisticClass(-2147483648L);
    public static final CTFontStylisticClass ClassOrnamentals = new CTFontStylisticClass(-1879048192L);
    public static final CTFontStylisticClass ClassScripts = new CTFontStylisticClass(-1610612736L);
    public static final CTFontStylisticClass ClassSymbolic = new CTFontStylisticClass(-1073741824L);
    public static final CTFontStylisticClass UnknownClass = new CTFontStylisticClass(0L);
    public static final CTFontStylisticClass OldStyleSerifsClass = new CTFontStylisticClass(268435456L);
    public static final CTFontStylisticClass TransitionalSerifsClass = new CTFontStylisticClass(536870912L);
    public static final CTFontStylisticClass ModernSerifsClass = new CTFontStylisticClass(805306368L);
    public static final CTFontStylisticClass ClarendonSerifsClass = new CTFontStylisticClass(1073741824L);
    public static final CTFontStylisticClass SlabSerifsClass = new CTFontStylisticClass(1342177280L);
    public static final CTFontStylisticClass FreeformSerifsClass = new CTFontStylisticClass(1879048192L);
    public static final CTFontStylisticClass SansSerifClass = new CTFontStylisticClass(-2147483648L);
    public static final CTFontStylisticClass OrnamentalsClass = new CTFontStylisticClass(-1879048192L);
    public static final CTFontStylisticClass ScriptsClass = new CTFontStylisticClass(-1610612736L);
    public static final CTFontStylisticClass SymbolicClass = new CTFontStylisticClass(-1073741824L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTFontStylisticClass/*</name>*/[] values = _values(/*<name>*/CTFontStylisticClass/*</name>*/.class);

    public /*<name>*/CTFontStylisticClass/*</name>*/(long value) { super(value); }
    private /*<name>*/CTFontStylisticClass/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTFontStylisticClass/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTFontStylisticClass/*</name>*/(value, mask);
    }
    protected /*<name>*/CTFontStylisticClass/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTFontStylisticClass/*</name>*/[] values() {
        return values.clone();
    }
}
