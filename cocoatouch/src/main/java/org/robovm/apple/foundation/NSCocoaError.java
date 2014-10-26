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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
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
    /**
     * @since Available in iOS 2.0 and later.
     */
    FileReadTooLargeError(263L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    FileReadUnknownStringEncodingError(264L),
    FileWriteUnknownError(512L),
    FileWriteNoPermissionError(513L),
    FileWriteInvalidFileNameError(514L),
    /**
     * @since Available in iOS 5.0 and later.
     */
    FileWriteFileExistsError(516L),
    FileWriteInapplicableStringEncodingError(517L),
    FileWriteUnsupportedSchemeError(518L),
    FileWriteOutOfSpaceError(640L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    FileWriteVolumeReadOnlyError(642L),
    KeyValueValidationError(1024L),
    FormattingError(2048L),
    UserCancelledError(3072L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    FeatureUnsupportedError(3328L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableNotLoadableError(3584L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableArchitectureMismatchError(3585L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableRuntimeMismatchError(3586L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableLoadError(3587L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableLinkError(3588L),
    FileErrorMinimum(0L),
    FileErrorMaximum(1023L),
    ValidationErrorMinimum(1024L),
    ValidationErrorMaximum(2047L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableErrorMinimum(3584L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableErrorMaximum(3839L),
    FormattingErrorMinimum(2048L),
    FormattingErrorMaximum(2559L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListReadCorruptError(3840L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListReadUnknownVersionError(3841L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListReadStreamError(3842L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListWriteStreamError(3851L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    PropertyListWriteInvalidError(3852L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListErrorMinimum(3840L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListErrorMaximum(4095L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    XPCConnectionInterrupted(4097L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    XPCConnectionInvalid(4099L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    XPCConnectionReplyInvalid(4101L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    XPCConnectionErrorMinimum(4096L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    XPCConnectionErrorMaximum(4224L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileUnavailableError(4353L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileNotUploadedDueToQuotaError(4354L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileUbiquityServerNotAvailable(4355L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileErrorMinimum(4352L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileErrorMaximum(4607L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityHandoffFailedError(4608L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityConnectionUnavailableError(4609L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityRemoteApplicationTimedOutError(4610L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityHandoffUserInfoTooLargeError(4611L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityErrorMinimum(4608L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityErrorMaximum(4863L);
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
