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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(AVError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/AVErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    Unknown(-11800L),
    OutOfMemory(-11801L),
    SessionNotRunning(-11803L),
    DeviceAlreadyUsedByAnotherSession(-11804L),
    NoDataCaptured(-11805L),
    SessionConfigurationChanged(-11806L),
    DiskFull(-11807L),
    DeviceWasDisconnected(-11808L),
    MediaChanged(-11809L),
    MaximumDurationReached(-11810L),
    MaximumFileSizeReached(-11811L),
    MediaDiscontinuity(-11812L),
    MaximumNumberOfSamplesForFileFormatReached(-11813L),
    DeviceNotConnected(-11814L),
    DeviceInUseByAnotherApplication(-11815L),
    DeviceLockedForConfigurationByAnotherProcess(-11817L),
    SessionWasInterrupted(-11818L),
    MediaServicesWereReset(-11819L),
    ExportFailed(-11820L),
    DecodeFailed(-11821L),
    InvalidSourceMedia(-11822L),
    FileAlreadyExists(-11823L),
    CompositionTrackSegmentsNotContiguous(-11824L),
    InvalidCompositionTrackSegmentDuration(-11825L),
    InvalidCompositionTrackSegmentSourceStartTime(-11826L),
    InvalidCompositionTrackSegmentSourceDuration(-11827L),
    FileFormatNotRecognized(-11828L),
    FileFailedToParse(-11829L),
    MaximumStillImageCaptureRequestsExceeded(-11830L),
    ContentIsProtected(-11831L),
    NoImageAtTime(-11832L),
    DecoderNotFound(-11833L),
    EncoderNotFound(-11834L),
    ContentIsNotAuthorized(-11835L),
    ApplicationIsNotAuthorized(-11836L),
    DeviceIsNotAvailableInBackground(-11837L),
    OperationNotSupportedForAsset(-11838L),
    DecoderTemporarilyUnavailable(-11839L),
    EncoderTemporarilyUnavailable(-11840L),
    InvalidVideoComposition(-11841L),
    ReferenceForbiddenByReferencePolicy(-11842L),
    InvalidOutputURLPathExtension(-11843L),
    ScreenCaptureFailed(-11844L),
    DisplayWasDisabled(-11845L),
    TorchLevelUnavailable(-11846L),
    OperationInterrupted(-11847L),
    IncompatibleAsset(-11848L),
    FailedToLoadMediaData(-11849L),
    ServerIncorrectlyConfigured(-11850L),
    ApplicationIsNotAuthorizedToUseDevice(-11852L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    FailedToParse(-11853L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    FileTypeDoesNotSupportSampleReferences(-11854L),
    /**
     * @since Available in iOS 8.0 and later.
     */
    UndecodableMediaData(-11855L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/AVErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/AVErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/AVErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/AVErrorCode/*</name>*/.class.getName());
    }
}
