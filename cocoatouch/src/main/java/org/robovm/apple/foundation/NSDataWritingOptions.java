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

/*</javadoc>*/
/*<annotations>*/@Marshaler(Bits.AsMachineSizedIntMarshaler.class)/*</annotations>*/
public final class /*<name>*/NSDataWritingOptions/*</name>*/ extends Bits</*<name>*/NSDataWritingOptions/*</name>*/> {
    /*<values>*/
    public static final NSDataWritingOptions None = new NSDataWritingOptions(0L);
    public static final NSDataWritingOptions DataWritingAtomic = new NSDataWritingOptions(1L);
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final NSDataWritingOptions DataWritingWithoutOverwriting = new NSDataWritingOptions(2L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSDataWritingOptions DataWritingFileProtectionNone = new NSDataWritingOptions(268435456L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSDataWritingOptions DataWritingFileProtectionComplete = new NSDataWritingOptions(536870912L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSDataWritingOptions DataWritingFileProtectionCompleteUnlessOpen = new NSDataWritingOptions(805306368L);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final NSDataWritingOptions DataWritingFileProtectionCompleteUntilFirstUserAuthentication = new NSDataWritingOptions(1073741824L);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSDataWritingOptions DataWritingFileProtectionMask = new NSDataWritingOptions(-268435456L);
    public static final NSDataWritingOptions AtomicWrite = new NSDataWritingOptions(1L);
    /*</values>*/

    private static final /*<name>*/NSDataWritingOptions/*</name>*/[] values = _values(/*<name>*/NSDataWritingOptions/*</name>*/.class);

    public /*<name>*/NSDataWritingOptions/*</name>*/(long value) { super(value); }
    private /*<name>*/NSDataWritingOptions/*</name>*/(long value, long mask) { super(value, mask); }
    protected /*<name>*/NSDataWritingOptions/*</name>*/ wrap(long value, long mask) {
        return new /*<name>*/NSDataWritingOptions/*</name>*/(value, mask);
    }
    protected /*<name>*/NSDataWritingOptions/*</name>*/[] _values() {
        return values;
    }
    public static /*<name>*/NSDataWritingOptions/*</name>*/[] values() {
        return values.clone();
    }
}
