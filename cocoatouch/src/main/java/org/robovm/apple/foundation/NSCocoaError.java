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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
public enum /*<name>*/NSCocoaError/*</name>*/ implements ValuedEnum {
    /*<values>*/
    FileNoSuchFileError(4L),
    FileLockingError(255L),
    FileReadUnknownError(256L),
    FileReadNoPermissionError(257L),
    FileReadInvalidFileNameError(258L),
    FileReadCorruptFileError(259L),
    FileReadNoSuchFileError(260L),
    FileReadInapplicableStringEncodingError(261L),
    FileReadUnsupportedSchemeError(262L),
    FileReadTooLargeError(263L),
    FileReadUnknownStringEncodingError(264L),
    FileWriteUnknownError(512L),
    FileWriteNoPermissionError(513L),
    FileWriteInvalidFileNameError(514L),
    FileWriteFileExistsError(516L),
    FileWriteInapplicableStringEncodingError(517L),
    FileWriteUnsupportedSchemeError(518L),
    FileWriteOutOfSpaceError(640L),
    FileWriteVolumeReadOnlyError(642L),
    KeyValueValidationError(1024L),
    FormattingError(2048L),
    UserCancelledError(3072L),
    FeatureUnsupportedError(3328L),
    ExecutableNotLoadableError(3584L),
    ExecutableArchitectureMismatchError(3585L),
    ExecutableRuntimeMismatchError(3586L),
    ExecutableLoadError(3587L),
    ExecutableLinkError(3588L),
    FileErrorMinimum(0L),
    FileErrorMaximum(1023L),
    ValidationErrorMinimum(1024L),
    ValidationErrorMaximum(2047L),
    ExecutableErrorMinimum(3584L),
    ExecutableErrorMaximum(3839L),
    FormattingErrorMinimum(2048L),
    FormattingErrorMaximum(2559L),
    PropertyListReadCorruptError(3840L),
    PropertyListReadUnknownVersionError(3841L),
    PropertyListReadStreamError(3842L),
    PropertyListWriteStreamError(3851L),
    PropertyListErrorMinimum(3840L),
    PropertyListErrorMaximum(4095L),
    XPCConnectionInterrupted(4097L),
    XPCConnectionInvalid(4099L),
    XPCConnectionReplyInvalid(4101L),
    XPCConnectionErrorMinimum(4096L),
    XPCConnectionErrorMaximum(4224L),
    UbiquitousFileUnavailableError(4353L),
    UbiquitousFileNotUploadedDueToQuotaError(4354L),
    UbiquitousFileUbiquityServerNotAvailable(4355L),
    UbiquitousFileErrorMinimum(4352L),
    UbiquitousFileErrorMaximum(4607L);
    /*</values>*/

    private final long n;

    private /*<name>*/NSCocoaError/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/NSCocoaError/*</name>*/ valueOf(long n) {
        for (/*<name>*/NSCocoaError/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSCocoaError/*</name>*/.class.getName());
    }
}
