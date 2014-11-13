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

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/UIModalPresentationStyle/*</name>*/ implements ValuedEnum {
    /*<values>*/
    FullScreen(0L),
    /**
     * @since Available in iOS 3.2 and later.
     */
    PageSheet(1L),
    /**
     * @since Available in iOS 3.2 and later.
     */
    FormSheet(2L),
    /**
     * @since Available in iOS 3.2 and later.
     */
    CurrentContext(3L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    Custom(4L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    OverFullScreen(5L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    OverCurrentContext(6L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    Popover(7L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    None(-1L);
    /*</values>*/

    private final long n;

    private /*<name>*/UIModalPresentationStyle/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/UIModalPresentationStyle/*</name>*/ valueOf(long n) {
        for (/*<name>*/UIModalPresentationStyle/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/UIModalPresentationStyle/*</name>*/.class.getName());
    }
}
