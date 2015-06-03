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
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedUIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CTParagraphStyleSpecifier/*</name>*/ implements ValuedEnum {
    /*<values>*/
    Alignment(0L),
    FirstLineHeadIndent(1L),
    HeadIndent(2L),
    TailIndent(3L),
    TabStops(4L),
    DefaultTabInterval(5L),
    LineBreakMode(6L),
    LineHeightMultiple(7L),
    MaximumLineHeight(8L),
    MinimumLineHeight(9L),
    LineSpacing(10L),
    ParagraphSpacing(11L),
    ParagraphSpacingBefore(12L),
    BaseWritingDirection(13L),
    MaximumLineSpacing(14L),
    MinimumLineSpacing(15L),
    LineSpacingAdjustment(16L),
    LineBoundsOptions(17L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CTParagraphStyleSpecifier/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CTParagraphStyleSpecifier/*</name>*/ valueOf(long n) {
        for (/*<name>*/CTParagraphStyleSpecifier/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CTParagraphStyleSpecifier/*</name>*/.class.getName());
    }
}
