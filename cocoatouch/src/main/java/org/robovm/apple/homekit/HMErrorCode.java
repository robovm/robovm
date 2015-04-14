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
package org.robovm.apple.homekit;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
@ForceLinkClass(HMError.class)
/*<annotations>*/@Marshaler(ValuedEnum.AsMachineSizedSIntMarshaler.class)/*</annotations>*/
public enum /*<name>*/HMErrorCode/*</name>*/ implements NSErrorCode {
    /*<values>*/
    AlreadyExists(1L),
    NotFound(2L),
    InvalidParameter(3L),
    AccessoryNotReachable(4L),
    ReadOnlyCharacteristic(5L),
    WriteOnlyCharacteristic(6L),
    NotificationNotSupported(7L),
    OperationTimedOut(8L),
    AccessoryPoweredOff(9L),
    AccessDenied(10L),
    ObjectAssociatedToAnotherHome(11L),
    ObjectNotAssociatedToAnyHome(12L),
    ObjectAlreadyAssociatedToHome(13L),
    AccessoryIsBusy(14L),
    OperationInProgress(15L),
    AccessoryOutOfResources(16L),
    InsufficientPrivileges(17L),
    AccessoryPairingFailed(18L),
    InvalidDataFormatSpecified(19L),
    NilParameter(20L),
    UnconfiguredParameter(21L),
    InvalidClass(22L),
    OperationCancelled(23L),
    RoomForHomeCannotBeInZone(24L),
    NoActionsInActionSet(25L),
    NoRegisteredActionSets(26L),
    MissingParameter(27L),
    FireDateInPast(28L),
    RoomForHomeCannotBeUpdated(29L),
    ActionInAnotherActionSet(30L),
    ObjectWithSimilarNameExistsInHome(31L),
    HomeWithSimilarNameExists(32L),
    RenameWithSimilarName(33L),
    CannotRemoveNonBridgeAccessory(34L),
    NameContainsProhibitedCharacters(35L),
    NameDoesNotStartWithValidCharacters(36L),
    UserIDNotEmailAddress(37L),
    UserDeclinedAddingUser(38L),
    UserDeclinedRemovingUser(39L),
    UserDeclinedInvite(40L),
    UserManagementFailed(41L),
    RecurrenceTooSmall(42L),
    InvalidValueType(43L),
    ValueLowerThanMinimum(44L),
    ValueHigherThanMaximum(45L),
    StringLongerThanMaximum(46L),
    HomeAccessNotAuthorized(47L),
    OperationNotSupported(48L),
    MaximumObjectLimitReached(49L),
    AccessorySentInvalidResponse(50L),
    StringShorterThanMinimum(51L),
    GenericError(52L),
    SecurityFailure(53L),
    CommunicationFailure(54L),
    MessageAuthenticationFailed(55L),
    InvalidMessageSize(56L),
    AccessoryDiscoveryFailed(57L),
    ClientRequestError(58L),
    AccessoryResponseError(59L),
    NameDoesNotEndWithValidCharacters(60L),
    AccessoryIsBlocked(61L),
    InvalidAssociatedServiceType(62L),
    ActionSetExecutionFailed(63L),
    ActionSetExecutionPartialSuccess(64L),
    ActionSetExecutionInProgress(65L),
    AccessoryOutOfCompliance(66L),
    DataResetFailure(67L),
    NotificationAlreadyEnabled(68L),
    RecurrenceMustBeOnSpecifiedBoundaries(69L),
    DateMustBeOnSpecifiedBoundaries(70L),
    CannotActivateTriggerTooFarInFuture(71L),
    RecurrenceTooLarge(72L),
    ReadWritePartialSuccess(73L),
    ReadWriteFailure(74L),
    NotSignedIntoiCloud(75L),
    KeychainSyncNotEnabled(76L),
    CloudDataSyncInProgress(77L),
    NetworkUnavailable(78L),
    AddAccessoryFailed(79L),
    MissingEntitlement(80L),
    CannotUnblockNonBridgeAccessory(81L),
    DeviceLocked(82L);
    /*</values>*/

    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<methods>*//*</methods>*/

    private final long n;

    private /*<name>*/HMErrorCode/*</name>*/(long n) { this.n = n; }
    public long value() { return n; }
    public static /*<name>*/HMErrorCode/*</name>*/ valueOf(long n) {
        for (/*<name>*/HMErrorCode/*</name>*/ v : values()) {
            if (v.n == n) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + n + " found in " 
            + /*<name>*/HMErrorCode/*</name>*/.class.getName());
    }
}
