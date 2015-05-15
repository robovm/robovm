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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
@ForceLinkClass(CKError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/CKErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    InternalError(1L),
    PartialFailure(2L),
    NetworkUnavailable(3L),
    NetworkFailure(4L),
    BadContainer(5L),
    ServiceUnavailable(6L),
    RequestRateLimited(7L),
    MissingEntitlement(8L),
    NotAuthenticated(9L),
    PermissionFailure(10L),
    UnknownItem(11L),
    InvalidArguments(12L),
    ResultsTruncated(13L),
    ServerRecordChanged(14L),
    ServerRejectedRequest(15L),
    AssetFileNotFound(16L),
    AssetFileModified(17L),
    IncompatibleVersion(18L),
    ConstraintViolation(19L),
    OperationCancelled(20L),
    ChangeTokenExpired(21L),
    BatchRequestFailed(22L),
    ZoneBusy(23L),
    BadDatabase(24L),
    QuotaExceeded(25L),
    ZoneNotFound(26L),
    LimitExceeded(27L),
    UserDeletedZone(28L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/CKErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/CKErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/CKErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/CKErrorCode/*</name>*/.class.getName());
    }
}
