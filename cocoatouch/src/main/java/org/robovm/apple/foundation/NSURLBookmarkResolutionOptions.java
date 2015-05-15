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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/ extends Bits</*<name>*/NSURLBookmarkResolutionOptions/*</name>*/> {
    /*<values>*/
    public static final NSURLBookmarkResolutionOptions None = new NSURLBookmarkResolutionOptions(0L);
    public static final NSURLBookmarkResolutionOptions WithoutUI = new NSURLBookmarkResolutionOptions(256L);
    public static final NSURLBookmarkResolutionOptions WithoutMounting = new NSURLBookmarkResolutionOptions(512L);
    public static final NSURLBookmarkResolutionOptions WithSecurityScope = new NSURLBookmarkResolutionOptions(1024L);
    /*</values>*/

    private static final /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/[] values = _values(/*<name>*/NSURLBookmarkResolutionOptions/*</name>*/.class);

    public /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSURLBookmarkResolutionOptions/*</name>*/[] values() {
        return values.clone();
    }
}
