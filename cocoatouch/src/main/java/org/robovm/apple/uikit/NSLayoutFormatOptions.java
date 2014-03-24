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
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSLayoutFormatOptions/*</name>*/ extends Bits</*<name>*/NSLayoutFormatOptions/*</name>*/> {
    /*<values>*/
    public static final NSLayoutFormatOptions AlignAllLeft = new NSLayoutFormatOptions(2L);
    public static final NSLayoutFormatOptions AlignAllRight = new NSLayoutFormatOptions(4L);
    public static final NSLayoutFormatOptions AlignAllTop = new NSLayoutFormatOptions(8L);
    public static final NSLayoutFormatOptions AlignAllBottom = new NSLayoutFormatOptions(16L);
    public static final NSLayoutFormatOptions AlignAllLeading = new NSLayoutFormatOptions(32L);
    public static final NSLayoutFormatOptions AlignAllTrailing = new NSLayoutFormatOptions(64L);
    public static final NSLayoutFormatOptions AlignAllCenterX = new NSLayoutFormatOptions(512L);
    public static final NSLayoutFormatOptions AlignAllCenterY = new NSLayoutFormatOptions(1024L);
    public static final NSLayoutFormatOptions AlignAllBaseline = new NSLayoutFormatOptions(2048L);
    public static final NSLayoutFormatOptions AlignmentMask = new NSLayoutFormatOptions(65535L);
    public static final NSLayoutFormatOptions DirectionLeadingToTrailing = new NSLayoutFormatOptions(0L);
    public static final NSLayoutFormatOptions DirectionLeftToRight = new NSLayoutFormatOptions(65536L);
    public static final NSLayoutFormatOptions DirectionRightToLeft = new NSLayoutFormatOptions(131072L);
    public static final NSLayoutFormatOptions DirectionMask = new NSLayoutFormatOptions(196608L);
    /*</values>*/

    private static final /*<name>*/NSLayoutFormatOptions/*</name>*/[] values = _values(/*<name>*/NSLayoutFormatOptions/*</name>*/.class);

    public /*<name>*/NSLayoutFormatOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSLayoutFormatOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSLayoutFormatOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSLayoutFormatOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSLayoutFormatOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSLayoutFormatOptions/*</name>*/[] values() {
        return values.clone();
    }
}
