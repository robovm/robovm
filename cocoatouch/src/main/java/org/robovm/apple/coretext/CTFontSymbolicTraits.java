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
public final class /*<name>*/CTFontSymbolicTraits/*</name>*/ extends Bits</*<name>*/CTFontSymbolicTraits/*</name>*/> {
    /*<values>*/
    public static final CTFontSymbolicTraits None = new CTFontSymbolicTraits(0L);
    public static final CTFontSymbolicTraits TraitItalic = new CTFontSymbolicTraits(1L);
    public static final CTFontSymbolicTraits TraitBold = new CTFontSymbolicTraits(2L);
    public static final CTFontSymbolicTraits TraitExpanded = new CTFontSymbolicTraits(32L);
    public static final CTFontSymbolicTraits TraitCondensed = new CTFontSymbolicTraits(64L);
    public static final CTFontSymbolicTraits TraitMonoSpace = new CTFontSymbolicTraits(1024L);
    public static final CTFontSymbolicTraits TraitVertical = new CTFontSymbolicTraits(2048L);
    public static final CTFontSymbolicTraits TraitUIOptimized = new CTFontSymbolicTraits(4096L);
    public static final CTFontSymbolicTraits TraitColorGlyphs = new CTFontSymbolicTraits(8192L);
    public static final CTFontSymbolicTraits TraitComposite = new CTFontSymbolicTraits(16384L);
    public static final CTFontSymbolicTraits TraitClassMask = new CTFontSymbolicTraits(-268435456L);
    public static final CTFontSymbolicTraits ItalicTrait = new CTFontSymbolicTraits(1L);
    public static final CTFontSymbolicTraits BoldTrait = new CTFontSymbolicTraits(2L);
    public static final CTFontSymbolicTraits ExpandedTrait = new CTFontSymbolicTraits(32L);
    public static final CTFontSymbolicTraits CondensedTrait = new CTFontSymbolicTraits(64L);
    public static final CTFontSymbolicTraits MonoSpaceTrait = new CTFontSymbolicTraits(1024L);
    public static final CTFontSymbolicTraits VerticalTrait = new CTFontSymbolicTraits(2048L);
    public static final CTFontSymbolicTraits UIOptimizedTrait = new CTFontSymbolicTraits(4096L);
    public static final CTFontSymbolicTraits ColorGlyphsTrait = new CTFontSymbolicTraits(8192L);
    public static final CTFontSymbolicTraits CompositeTrait = new CTFontSymbolicTraits(16384L);
    public static final CTFontSymbolicTraits ClassMaskTrait = new CTFontSymbolicTraits(-268435456L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTFontSymbolicTraits/*</name>*/[] values = _values(/*<name>*/CTFontSymbolicTraits/*</name>*/.class);

    public /*<name>*/CTFontSymbolicTraits/*</name>*/(long value) { super(value); }
    private /*<name>*/CTFontSymbolicTraits/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTFontSymbolicTraits/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTFontSymbolicTraits/*</name>*/(value, mask);
    }
    protected /*<name>*/CTFontSymbolicTraits/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTFontSymbolicTraits/*</name>*/[] values() {
        return values.clone();
    }
}
