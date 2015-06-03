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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/UIInterfaceOrientationMask/*</name>*/ extends Bits</*<name>*/UIInterfaceOrientationMask/*</name>*/> {
    /*<values>*/
    public static final UIInterfaceOrientationMask None = new UIInterfaceOrientationMask(0L);
    public static final UIInterfaceOrientationMask Portrait = new UIInterfaceOrientationMask(2L);
    public static final UIInterfaceOrientationMask LandscapeLeft = new UIInterfaceOrientationMask(16L);
    public static final UIInterfaceOrientationMask LandscapeRight = new UIInterfaceOrientationMask(8L);
    public static final UIInterfaceOrientationMask PortraitUpsideDown = new UIInterfaceOrientationMask(4L);
    public static final UIInterfaceOrientationMask Landscape = new UIInterfaceOrientationMask(24L);
    public static final UIInterfaceOrientationMask All = new UIInterfaceOrientationMask(30L);
    public static final UIInterfaceOrientationMask AllButUpsideDown = new UIInterfaceOrientationMask(26L);
    /*</values>*/

    private static final /*<name>*/UIInterfaceOrientationMask/*</name>*/[] values = _values(/*<name>*/UIInterfaceOrientationMask/*</name>*/.class);

    public /*<name>*/UIInterfaceOrientationMask/*</name>*/(long value) { super(value); }
    private /*<name>*/UIInterfaceOrientationMask/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/UIInterfaceOrientationMask/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/UIInterfaceOrientationMask/*</name>*/(value, mask);
    }
    protected /*<name>*/UIInterfaceOrientationMask/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/UIInterfaceOrientationMask/*</name>*/[] values() {
        return values.clone();
    }
}
