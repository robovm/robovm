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
public final class /*<name>*/CTLineBoundsOptions/*</name>*/ extends Bits</*<name>*/CTLineBoundsOptions/*</name>*/> {
    /*<values>*/
    public static final CTLineBoundsOptions None = new CTLineBoundsOptions(0L);
    public static final CTLineBoundsOptions ExcludeTypographicLeading = new CTLineBoundsOptions(1L);
    public static final CTLineBoundsOptions ExcludeTypographicShifts = new CTLineBoundsOptions(2L);
    public static final CTLineBoundsOptions UseHangingPunctuation = new CTLineBoundsOptions(4L);
    public static final CTLineBoundsOptions UseGlyphPathBounds = new CTLineBoundsOptions(8L);
    public static final CTLineBoundsOptions UseOpticalBounds = new CTLineBoundsOptions(16L);
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CTLineBoundsOptions IncludeLanguageExtents = new CTLineBoundsOptions(32L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CTLineBoundsOptions/*</name>*/[] values = _values(/*<name>*/CTLineBoundsOptions/*</name>*/.class);

    public /*<name>*/CTLineBoundsOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/CTLineBoundsOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CTLineBoundsOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CTLineBoundsOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/CTLineBoundsOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CTLineBoundsOptions/*</name>*/[] values() {
        return values.clone();
    }
}
