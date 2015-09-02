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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
@ForceLinkClass(NSCocoaError.class)
public enum /*<name>*/NSCocoaErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    FileNoSuchFile(4L),
    FileLocking(255L),
    FileReadUnknown(256L),
    FileReadNoPermission(257L),
    FileReadInvalidFileName(258L),
    FileReadCorruptFile(259L),
    FileReadNoSuchFile(260L),
    FileReadInapplicableStringEncoding(261L),
    FileReadUnsupportedScheme(262L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    FileReadTooLarge(263L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    FileReadUnknownStringEncoding(264L),
    FileWriteUnknown(512L),
    FileWriteNoPermission(513L),
    FileWriteInvalidFileName(514L),
    /**
     * @since Available in iOS 5.0 and later.
     */
    FileWriteFileExists(516L),
    FileWriteInapplicableStringEncoding(517L),
    FileWriteUnsupportedScheme(518L),
    FileWriteOutOfSpace(640L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    FileWriteVolumeReadOnly(642L),
    FileManagerUnmountUnknown(768L),
    FileManagerUnmountBusy(769L),
    KeyValueValidation(1024L),
    Formatting(2048L),
    UserCancelled(3072L),
    /**
     * @since Available in iOS 6.0 and later.
     */
    FeatureUnsupported(3328L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableNotLoadable(3584L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableArchitectureMismatch(3585L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableRuntimeMismatch(3586L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableLoad(3587L),
    /**
     * @since Available in iOS 2.0 and later.
     */
    ExecutableLink(3588L),
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
    PropertyListReadCorrupt(3840L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListReadUnknownVersion(3841L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListReadStream(3842L),
    /**
     * @since Available in iOS 4.0 and later.
     */
    PropertyListWriteStream(3851L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    PropertyListWriteInvalid(3852L),
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
    UbiquitousFileUnavailable(4353L),
    /**
     * @since Available in iOS 7.0 and later.
     */
    UbiquitousFileNotUploadedDueToQuota(4354L),
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
    UserActivityHandoffFailed(4608L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityConnectionUnavailable(4609L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityRemoteApplicationTimedOut(4610L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityHandoffUserInfoTooLarge(4611L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityErrorMinimum(4608L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UserActivityErrorMaximum(4863L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    CoderReadCorrupt(4864L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    CoderValueNotFound(4865L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    CoderErrorMinimum(4864L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    CoderErrorMaximum(4991L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    BundleErrorMinimum(4992L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    BundleErrorMaximum(5119L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    BundleOnDemandResourceOutOfSpace(4992L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    BundleOnDemandResourceExceededMaximumSize(4993L),
    /**
     * @since Available in iOS 9.0 and later.
     */
    BundleOnDemandResourceInvalidTag(4994L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/NSCocoaErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    @WeaklyLinked
    public static NSErrorCode valueOf(long n) {
        for (/*<name>*/NSCocoaErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        // NSCoreDataError codes don't have their own domain. They are added to Cocoa error codes.
        for (NSCoreDataErrorCode v : NSCoreDataErrorCode.values()) {
            if (v.value() == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/NSCocoaErrorCode/*</name>*/.class.getName());
    }
}
