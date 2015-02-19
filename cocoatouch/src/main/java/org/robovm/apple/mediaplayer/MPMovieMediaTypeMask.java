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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/MPMovieMediaTypeMask/*</name>*/ extends Bits</*<name>*/MPMovieMediaTypeMask/*</name>*/> {
    /*<values>*/
    public static final MPMovieMediaTypeMask None = new MPMovieMediaTypeMask(0L);
    public static final MPMovieMediaTypeMask Video = new MPMovieMediaTypeMask(1L);
    public static final MPMovieMediaTypeMask Audio = new MPMovieMediaTypeMask(2L);
    /*</values>*/

    private static final /*<name>*/MPMovieMediaTypeMask/*</name>*/[] values = _values(/*<name>*/MPMovieMediaTypeMask/*</name>*/.class);

    public /*<name>*/MPMovieMediaTypeMask/*</name>*/(long value) { super(value); }
    private /*<name>*/MPMovieMediaTypeMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/MPMovieMediaTypeMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/MPMovieMediaTypeMask/*</name>*/(value, mask);
    }
    protected /*<name>*/MPMovieMediaTypeMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/MPMovieMediaTypeMask/*</name>*/[] values() {
        return values.clone();
    }
}
