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
package org.robovm.apple.audiounit;

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
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/AudioComponentFlags/*</name>*/ extends Bits</*<name>*/AudioComponentFlags/*</name>*/> {
    /*<values>*/
    public static final AudioComponentFlags None = new AudioComponentFlags(0L);
    public static final AudioComponentFlags Unsearchable = new AudioComponentFlags(1L);
    public static final AudioComponentFlags SandboxSafe = new AudioComponentFlags(2L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioComponentFlags/*</name>*/[] values = _values(/*<name>*/AudioComponentFlags/*</name>*/.class);

    public /*<name>*/AudioComponentFlags/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioComponentFlags/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioComponentFlags/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioComponentFlags/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioComponentFlags/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioComponentFlags/*</name>*/[] values() {
        return values.clone();
    }
}
