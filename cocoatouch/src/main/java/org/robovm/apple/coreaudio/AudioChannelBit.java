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
package org.robovm.apple.coreaudio;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/AudioChannelBit/*</name>*/ extends Bits</*<name>*/AudioChannelBit/*</name>*/> {
    /*<values>*/
    public static final AudioChannelBit None = new AudioChannelBit(0L);
    public static final AudioChannelBit Left = new AudioChannelBit(1L);
    public static final AudioChannelBit Right = new AudioChannelBit(2L);
    public static final AudioChannelBit Center = new AudioChannelBit(4L);
    public static final AudioChannelBit LFEScreen = new AudioChannelBit(8L);
    public static final AudioChannelBit LeftSurround = new AudioChannelBit(16L);
    public static final AudioChannelBit RightSurround = new AudioChannelBit(32L);
    public static final AudioChannelBit LeftCenter = new AudioChannelBit(64L);
    public static final AudioChannelBit RightCenter = new AudioChannelBit(128L);
    public static final AudioChannelBit CenterSurround = new AudioChannelBit(256L);
    public static final AudioChannelBit LeftSurroundDirect = new AudioChannelBit(512L);
    public static final AudioChannelBit RightSurroundDirect = new AudioChannelBit(1024L);
    public static final AudioChannelBit TopCenterSurround = new AudioChannelBit(2048L);
    public static final AudioChannelBit VerticalHeightLeft = new AudioChannelBit(4096L);
    public static final AudioChannelBit VerticalHeightCenter = new AudioChannelBit(8192L);
    public static final AudioChannelBit VerticalHeightRight = new AudioChannelBit(16384L);
    public static final AudioChannelBit TopBackLeft = new AudioChannelBit(32768L);
    public static final AudioChannelBit TopBackCenter = new AudioChannelBit(65536L);
    public static final AudioChannelBit TopBackRight = new AudioChannelBit(131072L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/AudioChannelBit/*</name>*/[] values = _values(/*<name>*/AudioChannelBit/*</name>*/.class);

    public /*<name>*/AudioChannelBit/*</name>*/(long value) { super(value); }
    private /*<name>*/AudioChannelBit/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/AudioChannelBit/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/AudioChannelBit/*</name>*/(value, mask);
    }
    protected /*<name>*/AudioChannelBit/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/AudioChannelBit/*</name>*/[] values() {
        return values.clone();
    }
}
