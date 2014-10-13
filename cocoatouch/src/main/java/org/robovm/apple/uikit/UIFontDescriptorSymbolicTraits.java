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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/ extends Bits</*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/> {
    /*<values>*/
    public static final UIFontDescriptorSymbolicTraits None = new UIFontDescriptorSymbolicTraits(0L);
    public static final UIFontDescriptorSymbolicTraits TraitItalic = new UIFontDescriptorSymbolicTraits(1L);
    public static final UIFontDescriptorSymbolicTraits TraitBold = new UIFontDescriptorSymbolicTraits(2L);
    public static final UIFontDescriptorSymbolicTraits TraitExpanded = new UIFontDescriptorSymbolicTraits(32L);
    public static final UIFontDescriptorSymbolicTraits TraitCondensed = new UIFontDescriptorSymbolicTraits(64L);
    public static final UIFontDescriptorSymbolicTraits TraitMonoSpace = new UIFontDescriptorSymbolicTraits(1024L);
    public static final UIFontDescriptorSymbolicTraits TraitVertical = new UIFontDescriptorSymbolicTraits(2048L);
    public static final UIFontDescriptorSymbolicTraits TraitUIOptimized = new UIFontDescriptorSymbolicTraits(4096L);
    public static final UIFontDescriptorSymbolicTraits TraitTightLeading = new UIFontDescriptorSymbolicTraits(32768L);
    public static final UIFontDescriptorSymbolicTraits TraitLooseLeading = new UIFontDescriptorSymbolicTraits(65536L);
    public static final UIFontDescriptorSymbolicTraits ClassMask = new UIFontDescriptorSymbolicTraits(-268435456L);
    public static final UIFontDescriptorSymbolicTraits ClassUnknown = new UIFontDescriptorSymbolicTraits(0L);
    public static final UIFontDescriptorSymbolicTraits ClassOldStyleSerifs = new UIFontDescriptorSymbolicTraits(268435456L);
    public static final UIFontDescriptorSymbolicTraits ClassTransitionalSerifs = new UIFontDescriptorSymbolicTraits(536870912L);
    public static final UIFontDescriptorSymbolicTraits ClassModernSerifs = new UIFontDescriptorSymbolicTraits(805306368L);
    public static final UIFontDescriptorSymbolicTraits ClassClarendonSerifs = new UIFontDescriptorSymbolicTraits(1073741824L);
    public static final UIFontDescriptorSymbolicTraits ClassSlabSerifs = new UIFontDescriptorSymbolicTraits(1342177280L);
    public static final UIFontDescriptorSymbolicTraits ClassFreeformSerifs = new UIFontDescriptorSymbolicTraits(1879048192L);
    public static final UIFontDescriptorSymbolicTraits ClassSansSerif = new UIFontDescriptorSymbolicTraits(-2147483648L);
    public static final UIFontDescriptorSymbolicTraits ClassOrnamentals = new UIFontDescriptorSymbolicTraits(-1879048192L);
    public static final UIFontDescriptorSymbolicTraits ClassScripts = new UIFontDescriptorSymbolicTraits(-1610612736L);
    public static final UIFontDescriptorSymbolicTraits ClassSymbolic = new UIFontDescriptorSymbolicTraits(-1073741824L);
    /*</values>*/

    private static final /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/[] values = _values(/*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/.class);

    public /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/(long value) { super(value); }
    private /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/(value, mask);
    }
    protected /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIFontDescriptorSymbolicTraits/*</name>*/[] values() {
        return values.clone();
    }
}
