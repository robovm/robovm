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
package org.robovm.apple.coreaudio;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public final class /*<name>*/AudioChannelBits/*</name>*/ extends Bits</*<name>*/AudioChannelBits/*</name>*/> {
    /*<values>*/
    public static final AudioChannelBits None = new AudioChannelBits(0L);
    public static final AudioChannelBits Left = new AudioChannelBits(1L);
    public static final AudioChannelBits Right = new AudioChannelBits(2L);
    public static final AudioChannelBits Center = new AudioChannelBits(4L);
    public static final AudioChannelBits LFEScreen = new AudioChannelBits(8L);
    public static final AudioChannelBits LeftSurround = new AudioChannelBits(16L);
    public static final AudioChannelBits RightSurround = new AudioChannelBits(32L);
    public static final AudioChannelBits LeftCenter = new AudioChannelBits(64L);
    public static final AudioChannelBits RightCenter = new AudioChannelBits(128L);
    public static final AudioChannelBits CenterSurround = new AudioChannelBits(256L);
    public static final AudioChannelBits LeftSurroundDirect = new AudioChannelBits(512L);
    public static final AudioChannelBits RightSurroundDirect = new AudioChannelBits(1024L);
    public static final AudioChannelBits TopCenterSurround = new AudioChannelBits(2048L);
    public static final AudioChannelBits VerticalHeightLeft = new AudioChannelBits(4096L);
    public static final AudioChannelBits VerticalHeightCenter = new AudioChannelBits(8192L);
    public static final AudioChannelBits VerticalHeightRight = new AudioChannelBits(16384L);
    public static final AudioChannelBits TopBackLeft = new AudioChannelBits(32768L);
    public static final AudioChannelBits TopBackCenter = new AudioChannelBits(65536L);
    public static final AudioChannelBits TopBackRight = new AudioChannelBits(131072L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioChannelBits/*</name>*/[] values = _values(/*<name>*/AudioChannelBits/*</name>*/.class);

    public /*<name>*/AudioChannelBits/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioChannelBits/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioChannelBits/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioChannelBits/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioChannelBits/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioChannelBits/*</name>*/[] values() {
        return values.clone();
    }
}
