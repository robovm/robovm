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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CMTextDisplayFlags/*</name>*/ extends Bits</*<name>*/CMTextDisplayFlags/*</name>*/> {
    /*<values>*/
    public static final CMTextDisplayFlags None = new CMTextDisplayFlags(0L);
    public static final CMTextDisplayFlags scrollIn = new CMTextDisplayFlags(32L);
    public static final CMTextDisplayFlags scrollOut = new CMTextDisplayFlags(64L);
    public static final CMTextDisplayFlags scrollDirectionMask = new CMTextDisplayFlags(384L);
    public static final CMTextDisplayFlags scrollDirection_bottomToTop = new CMTextDisplayFlags(0L);
    public static final CMTextDisplayFlags scrollDirection_rightToLeft = new CMTextDisplayFlags(128L);
    public static final CMTextDisplayFlags scrollDirection_topToBottom = new CMTextDisplayFlags(256L);
    public static final CMTextDisplayFlags scrollDirection_leftToRight = new CMTextDisplayFlags(384L);
    public static final CMTextDisplayFlags continuousKaraoke = new CMTextDisplayFlags(2048L);
    public static final CMTextDisplayFlags writeTextVertically = new CMTextDisplayFlags(131072L);
    public static final CMTextDisplayFlags fillTextRegion = new CMTextDisplayFlags(262144L);
    public static final CMTextDisplayFlags obeySubtitleFormatting = new CMTextDisplayFlags(536870912L);
    public static final CMTextDisplayFlags forcedSubtitlesPresent = new CMTextDisplayFlags(1073741824L);
    public static final CMTextDisplayFlags allSubtitlesForced = new CMTextDisplayFlags(-2147483648L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CMTextDisplayFlags/*</name>*/[] values = _values(/*<name>*/CMTextDisplayFlags/*</name>*/.class);

    public /*<name>*/CMTextDisplayFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/CMTextDisplayFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CMTextDisplayFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CMTextDisplayFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/CMTextDisplayFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CMTextDisplayFlags/*</name>*/[] values() {
        return values.clone();
    }
}
