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
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSStringDrawingOptions/*</name>*/ extends Bits</*<name>*/NSStringDrawingOptions/*</name>*/> {
    /*<values>*/
    public static final NSStringDrawingOptions None = new NSStringDrawingOptions(0L);
    public static final NSStringDrawingOptions TruncatesLastVisibleLine = new NSStringDrawingOptions(32L);
    public static final NSStringDrawingOptions UsesLineFragmentOrigin = new NSStringDrawingOptions(1L);
    public static final NSStringDrawingOptions UsesFontLeading = new NSStringDrawingOptions(2L);
    public static final NSStringDrawingOptions UsesDeviceMetrics = new NSStringDrawingOptions(8L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private static final /*<name>*/NSStringDrawingOptions/*</name>*/[] values = _values(/*<name>*/NSStringDrawingOptions/*</name>*/.class);

    public /*<name>*/NSStringDrawingOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSStringDrawingOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSStringDrawingOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSStringDrawingOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSStringDrawingOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSStringDrawingOptions/*</name>*/[] values() {
        return values.clone();
    }
}
