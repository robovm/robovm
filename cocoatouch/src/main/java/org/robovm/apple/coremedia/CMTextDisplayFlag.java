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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/CMTextDisplayFlag/*</name>*/ extends Bits</*<name>*/CMTextDisplayFlag/*</name>*/> {
    /*<values>*/
    public static final CMTextDisplayFlag scrollIn = new CMTextDisplayFlag(32L);
    public static final CMTextDisplayFlag scrollOut = new CMTextDisplayFlag(64L);
    public static final CMTextDisplayFlag scrollDirectionMask = new CMTextDisplayFlag(384L);
    public static final CMTextDisplayFlag scrollDirection_bottomToTop = new CMTextDisplayFlag(0L);
    public static final CMTextDisplayFlag scrollDirection_rightToLeft = new CMTextDisplayFlag(128L);
    public static final CMTextDisplayFlag scrollDirection_topToBottom = new CMTextDisplayFlag(256L);
    public static final CMTextDisplayFlag scrollDirection_leftToRight = new CMTextDisplayFlag(384L);
    public static final CMTextDisplayFlag continuousKaraoke = new CMTextDisplayFlag(2048L);
    public static final CMTextDisplayFlag writeTextVertically = new CMTextDisplayFlag(131072L);
    public static final CMTextDisplayFlag fillTextRegion = new CMTextDisplayFlag(262144L);
    public static final CMTextDisplayFlag obeySubtitleFormatting = new CMTextDisplayFlag(536870912L);
    public static final CMTextDisplayFlag forcedSubtitlesPresent = new CMTextDisplayFlag(1073741824L);
    public static final CMTextDisplayFlag allSubtitlesForced = new CMTextDisplayFlag(-2147483648L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/CMTextDisplayFlag/*</name>*/[] values = _values(/*<name>*/CMTextDisplayFlag/*</name>*/.class);

    public /*<name>*/CMTextDisplayFlag/*</name>*/(long value) { super(value); }
    private /*<name>*/CMTextDisplayFlag/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/CMTextDisplayFlag/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/CMTextDisplayFlag/*</name>*/(value, mask);
    }
    protected /*<name>*/CMTextDisplayFlag/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/CMTextDisplayFlag/*</name>*/[] values() {
        return values.clone();
    }
}
