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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(CBError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CBErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    Unknown(0L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    InvalidParameters(1L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    InvalidHandle(2L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    NotConnected(3L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    OutOfSpace(4L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    OperationCancelled(5L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    ConnectionTimeout(6L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    PeripheralDisconnected(7L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    UUIDNotAllowed(8L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    AlreadyAdvertising(9L),
    /**
     * @since Available in iOS 7.1 and later.
     */
    ConnectionFailed(10L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    ConnectionLimitReached(11L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CBErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CBErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/CBErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CBErrorCode/*</name>*/.class.getName());
    }
}
