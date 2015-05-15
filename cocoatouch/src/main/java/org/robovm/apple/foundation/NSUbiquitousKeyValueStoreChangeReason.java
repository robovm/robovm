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

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
public enum /*<name>*/NSUbiquitousKeyValueStoreChangeReason/*</name>*/ implements ValuedEnum {
    /*<values>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    ServerChange(0L),
    /**
     * @since Available in iOS 5.0 and later.
     */
    InitialSyncChange(1L),
    /**
     * @since Available in iOS 5.0 and later.
     */
    QuotaViolationChange(2L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    AccountChange(3L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSUbiquitousKeyValueStoreChangeReason/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSUbiquitousKeyValueStoreChangeReason/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSUbiquitousKeyValueStoreChangeReason/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSUbiquitousKeyValueStoreChangeReason/*</name>*/.class.getName());
    }
}
