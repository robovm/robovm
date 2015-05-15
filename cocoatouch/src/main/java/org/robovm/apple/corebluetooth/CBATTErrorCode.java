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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(CBATTError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CBATTErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    Success(0L),
    InvalidHandle(1L),
    ReadNotPermitted(2L),
    WriteNotPermitted(3L),
    InvalidPdu(4L),
    InsufficientAuthentication(5L),
    RequestNotSupported(6L),
    InvalidOffset(7L),
    InsufficientAuthorization(8L),
    PrepareQueueFull(9L),
    AttributeNotFound(10L),
    AttributeNotLong(11L),
    InsufficientEncryptionKeySize(12L),
    InvalidAttributeValueLength(13L),
    UnlikelyError(14L),
    InsufficientEncryption(15L),
    UnsupportedGroupType(16L),
    InsufficientResources(17L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CBATTErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CBATTErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/CBATTErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CBATTErrorCode/*</name>*/.class.getName());
    }
}
