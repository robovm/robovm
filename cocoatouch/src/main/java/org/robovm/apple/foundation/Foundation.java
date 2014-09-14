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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Foundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Foundation.class); }/*</bind>*/
    /*<constants>*/
    public static final double FoundationVersionNumber10_0 = 397.40;
    public static final double FoundationVersionNumber10_1 = 425.00;
    public static final double FoundationVersionNumber10_1_1 = 425.00;
    public static final double FoundationVersionNumber10_1_2 = 425.00;
    public static final double FoundationVersionNumber10_1_3 = 425.00;
    public static final double FoundationVersionNumber10_1_4 = 425.00;
    public static final double FoundationVersionNumber10_2 = 462.00;
    public static final double FoundationVersionNumber10_2_1 = 462.00;
    public static final double FoundationVersionNumber10_2_2 = 462.00;
    public static final double FoundationVersionNumber10_2_3 = 462.00;
    public static final double FoundationVersionNumber10_2_4 = 462.00;
    public static final double FoundationVersionNumber10_2_5 = 462.00;
    public static final double FoundationVersionNumber10_2_6 = 462.00;
    public static final double FoundationVersionNumber10_2_7 = 462.70;
    public static final double FoundationVersionNumber10_2_8 = 462.70;
    public static final double FoundationVersionNumber10_3 = 500.00;
    public static final double FoundationVersionNumber10_3_1 = 500.00;
    public static final double FoundationVersionNumber10_3_2 = 500.30;
    public static final double FoundationVersionNumber10_3_3 = 500.54;
    public static final double FoundationVersionNumber10_3_4 = 500.56;
    public static final double FoundationVersionNumber10_3_5 = 500.56;
    public static final double FoundationVersionNumber10_3_6 = 500.56;
    public static final double FoundationVersionNumber10_3_7 = 500.56;
    public static final double FoundationVersionNumber10_3_8 = 500.56;
    public static final double FoundationVersionNumber10_3_9 = 500.58;
    public static final double FoundationVersionNumber10_4 = 567.00;
    public static final double FoundationVersionNumber10_4_1 = 567.00;
    public static final double FoundationVersionNumber10_4_2 = 567.12;
    public static final double FoundationVersionNumber10_4_3 = 567.21;
    public static final double FoundationVersionNumber10_4_4_Intel = 567.23;
    public static final double FoundationVersionNumber10_4_4_PowerPC = 567.21;
    public static final double FoundationVersionNumber10_4_5 = 567.25;
    public static final double FoundationVersionNumber10_4_6 = 567.26;
    public static final double FoundationVersionNumber10_4_7 = 567.27;
    public static final double FoundationVersionNumber10_4_8 = 567.28;
    public static final double FoundationVersionNumber10_4_9 = 567.29;
    public static final double FoundationVersionNumber10_4_10 = 567.29;
    public static final double FoundationVersionNumber10_4_11 = 567.36;
    public static final double FoundationVersionNumber10_5 = 677.00;
    public static final double FoundationVersionNumber10_5_1 = 677.10;
    public static final double FoundationVersionNumber10_5_2 = 677.15;
    public static final double FoundationVersionNumber10_5_3 = 677.19;
    public static final double FoundationVersionNumber10_5_4 = 677.19;
    public static final double FoundationVersionNumber10_5_5 = 677.21;
    public static final double FoundationVersionNumber10_5_6 = 677.22;
    public static final double FoundationVersionNumber10_5_7 = 677.24;
    public static final double FoundationVersionNumber10_5_8 = 677.26;
    public static final double FoundationVersionNumber10_6 = 751.00;
    public static final double FoundationVersionNumber10_6_1 = 751.00;
    public static final double FoundationVersionNumber10_6_2 = 751.14;
    public static final double FoundationVersionNumber10_6_3 = 751.21;
    public static final double FoundationVersionNumber10_6_4 = 751.29;
    public static final double FoundationVersionNumber10_6_5 = 751.42;
    public static final double FoundationVersionNumber10_6_6 = 751.53;
    public static final double FoundationVersionNumber10_6_7 = 751.53;
    public static final double FoundationVersionNumber10_6_8 = 751.62;
    public static final double FoundationVersionNumber10_7 = 833.10;
    public static final double FoundationVersionNumber10_7_1 = 833.10;
    public static final double FoundationVersionNumber10_7_2 = 833.20;
    public static final double FoundationVersionNumber10_7_3 = 833.24;
    public static final double FoundationVersionNumber10_7_4 = 833.25;
    public static final double FoundationVersionNumber10_8 = 945.00;
    public static final double FoundationVersionNumber10_8_1 = 945.00;
    public static final double FoundationVersionNumber10_8_2 = 945.11;
    public static final double FoundationVersionNumber10_8_3 = 945.16;
    public static final double FoundationVersionNumber10_8_4 = 945.18;
    public static final double FoundationVersionNumber_iPhoneOS_2_0 = 678.24;
    public static final double FoundationVersionNumber_iPhoneOS_2_1 = 678.26;
    public static final double FoundationVersionNumber_iPhoneOS_2_2 = 678.29;
    public static final double FoundationVersionNumber_iPhoneOS_3_0 = 678.47;
    public static final double FoundationVersionNumber_iPhoneOS_3_1 = 678.51;
    public static final double FoundationVersionNumber_iPhoneOS_3_2 = 678.60;
    public static final double FoundationVersionNumber_iOS_4_0 = 751.32;
    public static final double FoundationVersionNumber_iOS_4_1 = 751.37;
    public static final double FoundationVersionNumber_iOS_4_2 = 751.49;
    public static final double FoundationVersionNumber_iOS_4_3 = 751.49;
    public static final double FoundationVersionNumber_iOS_5_0 = 881.00;
    public static final double FoundationVersionNumber_iOS_5_1 = 890.10;
    public static final double FoundationVersionNumber_iOS_6_0 = 993.00;
    public static final double FoundationVersionNumber_iOS_6_1 = 993.00;
    public static final double TimeIntervalSince1970 = 978307200.0;
    public static final int DecimalMaxSize = 8;
    public static final int DecimalNoScale = 32767;
    public static final int FoundationVersionWithFileManagerResourceForkSupport = 412;
    public static final int URLResponseUnknownLength = -1;
    public static final int NotFound = 2147483647;
    public static final int DateComponentUndefined = 2147483647;
    public static final int OpenStepUnicodeReservedBase = 62464;
    public static final int OperationQueueDefaultMaxConcurrentOperationCount = -1;
    public static final int UndoCloseGroupingRunLoopOrdering = 350000;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    /**
     * Prints the specified message to the system log facility.
     * 
     * @param message the message to print to the log.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String message);
    
    /**
     * Formats a log message using the specified format string and argument and
     * prints it to the system log facility. Primitive value arguments must be 
     * wrapped in {@link NSNumber} instances.
     * 
     * @param format the format string.
     * @param arg the argument to be used in the format string.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String format, NSObject arg);

    /**
     * Formats a log message using the specified format string and arguments and
     * prints it to the system log facility. Primitive value arguments must be 
     * wrapped in {@link NSNumber} instances.
     * 
     * @param format the format string.
     * @param arg1 the first argument to be used in the format string.
     * @param arg2 the second argument to be used in the format string.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String format, NSObject arg1, NSObject arg2);

    /**
     * Formats a log message using the specified format string and arguments and
     * prints it to the system log facility. Primitive value arguments must be 
     * wrapped in {@link NSNumber} instances.
     * 
     * @param format the format string.
     * @param arg1 the first argument to be used in the format string.
     * @param arg2 the second argument to be used in the format string.
     * @param arg3 the third argument to be used in the format string.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String format, NSObject arg1, NSObject arg2, NSObject arg3);

    /**
     * Formats a log message using the specified format string and arguments and
     * prints it to the system log facility. Primitive value arguments must be 
     * wrapped in {@link NSNumber} instances.
     * 
     * @param format the format string.
     * @param arg1 the first argument to be used in the format string.
     * @param arg2 the second argument to be used in the format string.
     * @param arg3 the third argument to be used in the format string.
     * @param arg4 the forth argument to be used in the format string.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String format, NSObject arg1, NSObject arg2, NSObject arg3, NSObject arg4);

    /**
     * Formats a log message using the specified format string and arguments and
     * prints it to the system log facility. Primitive value arguments must be 
     * wrapped in {@link NSNumber} instances.
     * 
     * @param format the format string.
     * @param arg1 the first argument to be used in the format string.
     * @param arg2 the second argument to be used in the format string.
     * @param arg3 the third argument to be used in the format string.
     * @param arg4 the forth argument to be used in the format string.
     * @param arg5 the fifth argument to be used in the format string.
     */
    @Bridge(symbol = "NSLog")
    public native static void log(String format, NSObject arg1, NSObject arg2, NSObject arg3, NSObject arg4, NSObject arg5);
    
    /*<methods>*/
    @GlobalValue(symbol="NSFoundationVersionNumber", optional=true)
    public static native double FoundationVersionNumber();
    @GlobalValue(symbol="NSBundleDidLoadNotification", optional=true)
    public static native NSString BundleDidLoadNotification();
    @GlobalValue(symbol="NSLoadedClasses", optional=true)
    public static native NSString LoadedClasses();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSSystemClockDidChangeNotification", optional=true)
    public static native NSString SystemClockDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierGregorian", optional=true)
    public static native String CalendarIdentifierGregorian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierBuddhist", optional=true)
    public static native String CalendarIdentifierBuddhist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierChinese", optional=true)
    public static native String CalendarIdentifierChinese();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierCoptic", optional=true)
    public static native String CalendarIdentifierCoptic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteMihret", optional=true)
    public static native String CalendarIdentifierEthiopicAmeteMihret();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteAlem", optional=true)
    public static native String CalendarIdentifierEthiopicAmeteAlem();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierHebrew", optional=true)
    public static native String CalendarIdentifierHebrew();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierISO8601", optional=true)
    public static native String CalendarIdentifierISO8601();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIndian", optional=true)
    public static native String CalendarIdentifierIndian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamic", optional=true)
    public static native String CalendarIdentifierIslamic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamicCivil", optional=true)
    public static native String CalendarIdentifierIslamicCivil();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierJapanese", optional=true)
    public static native String CalendarIdentifierJapanese();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierPersian", optional=true)
    public static native String CalendarIdentifierPersian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierRepublicOfChina", optional=true)
    public static native String CalendarIdentifierRepublicOfChina();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCalendarDayChangedNotification", optional=true)
    public static native NSString CalendarDayChangedNotification();
    @GlobalValue(symbol="NSCocoaErrorDomain", optional=true)
    public static native NSString CocoaErrorDomain();
    @GlobalValue(symbol="NSPOSIXErrorDomain", optional=true)
    public static native NSString POSIXErrorDomain();
    @GlobalValue(symbol="NSOSStatusErrorDomain", optional=true)
    public static native NSString OSStatusErrorDomain();
    @GlobalValue(symbol="NSMachErrorDomain", optional=true)
    public static native NSString MachErrorDomain();
    @GlobalValue(symbol="NSUnderlyingErrorKey", optional=true)
    public static native NSString UnderlyingErrorKey();
    @GlobalValue(symbol="NSLocalizedDescriptionKey", optional=true)
    public static native NSString LocalizedDescriptionKey();
    @GlobalValue(symbol="NSLocalizedFailureReasonErrorKey", optional=true)
    public static native NSString LocalizedFailureReasonErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoverySuggestionErrorKey", optional=true)
    public static native NSString LocalizedRecoverySuggestionErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoveryOptionsErrorKey", optional=true)
    public static native NSString LocalizedRecoveryOptionsErrorKey();
    @GlobalValue(symbol="NSRecoveryAttempterErrorKey", optional=true)
    public static native NSString RecoveryAttempterErrorKey();
    @GlobalValue(symbol="NSHelpAnchorErrorKey", optional=true)
    public static native NSString HelpAnchorErrorKey();
    @GlobalValue(symbol="NSStringEncodingErrorKey", optional=true)
    public static native NSString StringEncodingErrorKey();
    @GlobalValue(symbol="NSURLErrorKey", optional=true)
    public static native NSString URLErrorKey();
    @GlobalValue(symbol="NSFilePathErrorKey", optional=true)
    public static native NSString FilePathErrorKey();
    @GlobalValue(symbol="NSFileHandleReadCompletionNotification", optional=true)
    public static native NSString FileHandleReadCompletionNotification();
    @GlobalValue(symbol="NSFileHandleReadToEndOfFileCompletionNotification", optional=true)
    public static native NSString FileHandleReadToEndOfFileCompletionNotification();
    @GlobalValue(symbol="NSFileHandleConnectionAcceptedNotification", optional=true)
    public static native NSString FileHandleConnectionAcceptedNotification();
    @GlobalValue(symbol="NSFileHandleDataAvailableNotification", optional=true)
    public static native NSString FileHandleDataAvailableNotification();
    @GlobalValue(symbol="NSFileHandleNotificationDataItem", optional=true)
    public static native String FileHandleNotificationDataItem();
    @GlobalValue(symbol="NSFileHandleNotificationFileHandleItem", optional=true)
    public static native String FileHandleNotificationFileHandleItem();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSFileHandleNotificationMonitorModes", optional=true)
    public static native String FileHandleNotificationMonitorModes();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUbiquityIdentityDidChangeNotification", optional=true)
    public static native NSString UbiquityIdentityDidChangeNotification();
    @GlobalValue(symbol="NSFileType", optional=true)
    public static native NSString FileType();
    @GlobalValue(symbol="NSFileTypeDirectory", optional=true)
    public static native NSString FileTypeDirectory();
    @GlobalValue(symbol="NSFileTypeRegular", optional=true)
    public static native NSString FileTypeRegular();
    @GlobalValue(symbol="NSFileTypeSymbolicLink", optional=true)
    public static native NSString FileTypeSymbolicLink();
    @GlobalValue(symbol="NSFileTypeSocket", optional=true)
    public static native NSString FileTypeSocket();
    @GlobalValue(symbol="NSFileTypeCharacterSpecial", optional=true)
    public static native NSString FileTypeCharacterSpecial();
    @GlobalValue(symbol="NSFileTypeBlockSpecial", optional=true)
    public static native NSString FileTypeBlockSpecial();
    @GlobalValue(symbol="NSFileTypeUnknown", optional=true)
    public static native NSString FileTypeUnknown();
    @GlobalValue(symbol="NSFileSize", optional=true)
    public static native NSString FileSize();
    @GlobalValue(symbol="NSFileModificationDate", optional=true)
    public static native NSString FileModificationDate();
    @GlobalValue(symbol="NSFileReferenceCount", optional=true)
    public static native NSString FileReferenceCount();
    @GlobalValue(symbol="NSFileDeviceIdentifier", optional=true)
    public static native NSString FileDeviceIdentifier();
    @GlobalValue(symbol="NSFileOwnerAccountName", optional=true)
    public static native NSString FileOwnerAccountName();
    @GlobalValue(symbol="NSFileGroupOwnerAccountName", optional=true)
    public static native NSString FileGroupOwnerAccountName();
    @GlobalValue(symbol="NSFilePosixPermissions", optional=true)
    public static native NSString FilePosixPermissions();
    @GlobalValue(symbol="NSFileSystemNumber", optional=true)
    public static native NSString FileSystemNumber();
    @GlobalValue(symbol="NSFileSystemFileNumber", optional=true)
    public static native NSString FileSystemFileNumber();
    @GlobalValue(symbol="NSFileExtensionHidden", optional=true)
    public static native NSString FileExtensionHidden();
    @GlobalValue(symbol="NSFileHFSCreatorCode", optional=true)
    public static native NSString FileHFSCreatorCode();
    @GlobalValue(symbol="NSFileHFSTypeCode", optional=true)
    public static native NSString FileHFSTypeCode();
    @GlobalValue(symbol="NSFileImmutable", optional=true)
    public static native NSString FileImmutable();
    @GlobalValue(symbol="NSFileAppendOnly", optional=true)
    public static native NSString FileAppendOnly();
    @GlobalValue(symbol="NSFileCreationDate", optional=true)
    public static native NSString FileCreationDate();
    @GlobalValue(symbol="NSFileOwnerAccountID", optional=true)
    public static native NSString FileOwnerAccountID();
    @GlobalValue(symbol="NSFileGroupOwnerAccountID", optional=true)
    public static native NSString FileGroupOwnerAccountID();
    @GlobalValue(symbol="NSFileBusy", optional=true)
    public static native NSString FileBusy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionKey", optional=true)
    public static native NSString FileProtectionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionNone", optional=true)
    public static native NSString FileProtectionNone();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionComplete", optional=true)
    public static native NSString FileProtectionComplete();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionCompleteUnlessOpen", optional=true)
    public static native NSString FileProtectionCompleteUnlessOpen();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionCompleteUntilFirstUserAuthentication", optional=true)
    public static native NSString FileProtectionCompleteUntilFirstUserAuthentication();
    @GlobalValue(symbol="NSFileSystemSize", optional=true)
    public static native NSString FileSystemSize();
    @GlobalValue(symbol="NSFileSystemFreeSize", optional=true)
    public static native NSString FileSystemFreeSize();
    @GlobalValue(symbol="NSFileSystemNodes", optional=true)
    public static native NSString FileSystemNodes();
    @GlobalValue(symbol="NSFileSystemFreeNodes", optional=true)
    public static native NSString FileSystemFreeNodes();
    @GlobalValue(symbol="NSHTTPCookieName", optional=true)
    public static native NSString HTTPCookieName();
    @GlobalValue(symbol="NSHTTPCookieValue", optional=true)
    public static native NSString HTTPCookieValue();
    @GlobalValue(symbol="NSHTTPCookieOriginURL", optional=true)
    public static native NSString HTTPCookieOriginURL();
    @GlobalValue(symbol="NSHTTPCookieVersion", optional=true)
    public static native NSString HTTPCookieVersion();
    @GlobalValue(symbol="NSHTTPCookieDomain", optional=true)
    public static native NSString HTTPCookieDomain();
    @GlobalValue(symbol="NSHTTPCookiePath", optional=true)
    public static native NSString HTTPCookiePath();
    @GlobalValue(symbol="NSHTTPCookieSecure", optional=true)
    public static native NSString HTTPCookieSecure();
    @GlobalValue(symbol="NSHTTPCookieExpires", optional=true)
    public static native NSString HTTPCookieExpires();
    @GlobalValue(symbol="NSHTTPCookieComment", optional=true)
    public static native NSString HTTPCookieComment();
    @GlobalValue(symbol="NSHTTPCookieCommentURL", optional=true)
    public static native NSString HTTPCookieCommentURL();
    @GlobalValue(symbol="NSHTTPCookieDiscard", optional=true)
    public static native NSString HTTPCookieDiscard();
    @GlobalValue(symbol="NSHTTPCookieMaximumAge", optional=true)
    public static native NSString HTTPCookieMaximumAge();
    @GlobalValue(symbol="NSHTTPCookiePort", optional=true)
    public static native NSString HTTPCookiePort();
    @GlobalValue(symbol="NSHTTPCookieManagerAcceptPolicyChangedNotification", optional=true)
    public static native NSString HTTPCookieManagerAcceptPolicyChangedNotification();
    @GlobalValue(symbol="NSHTTPCookieManagerCookiesChangedNotification", optional=true)
    public static native NSString HTTPCookieManagerCookiesChangedNotification();
    @GlobalValue(symbol="NSAverageKeyValueOperator", optional=true)
    public static native String AverageKeyValueOperator();
    @GlobalValue(symbol="NSCountKeyValueOperator", optional=true)
    public static native String CountKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfArraysKeyValueOperator", optional=true)
    public static native String DistinctUnionOfArraysKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfObjectsKeyValueOperator", optional=true)
    public static native String DistinctUnionOfObjectsKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfSetsKeyValueOperator", optional=true)
    public static native String DistinctUnionOfSetsKeyValueOperator();
    @GlobalValue(symbol="NSMaximumKeyValueOperator", optional=true)
    public static native String MaximumKeyValueOperator();
    @GlobalValue(symbol="NSMinimumKeyValueOperator", optional=true)
    public static native String MinimumKeyValueOperator();
    @GlobalValue(symbol="NSSumKeyValueOperator", optional=true)
    public static native String SumKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfArraysKeyValueOperator", optional=true)
    public static native String UnionOfArraysKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfObjectsKeyValueOperator", optional=true)
    public static native String UnionOfObjectsKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfSetsKeyValueOperator", optional=true)
    public static native String UnionOfSetsKeyValueOperator();
    @GlobalValue(symbol="NSKeyValueChangeKindKey", optional=true)
    public static native NSString KeyValueChangeKindKey();
    @GlobalValue(symbol="NSKeyValueChangeNewKey", optional=true)
    public static native NSString KeyValueChangeNewKey();
    @GlobalValue(symbol="NSKeyValueChangeOldKey", optional=true)
    public static native NSString KeyValueChangeOldKey();
    @GlobalValue(symbol="NSKeyValueChangeIndexesKey", optional=true)
    public static native NSString KeyValueChangeIndexesKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSKeyValueChangeNotificationIsPriorKey", optional=true)
    public static native NSString KeyValueChangeNotificationIsPriorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSKeyedArchiveRootObjectKey", optional=true)
    public static native NSString KeyedArchiveRootObjectKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSCurrentLocaleDidChangeNotification", optional=true)
    public static native NSString CurrentLocaleDidChangeNotification();
    @GlobalValue(symbol="NSLocaleIdentifier", optional=true)
    public static native NSString LocaleIdentifier();
    @GlobalValue(symbol="NSLocaleLanguageCode", optional=true)
    public static native NSString LocaleLanguageCode();
    @GlobalValue(symbol="NSLocaleCountryCode", optional=true)
    public static native NSString LocaleCountryCode();
    @GlobalValue(symbol="NSLocaleScriptCode", optional=true)
    public static native NSString LocaleScriptCode();
    @GlobalValue(symbol="NSLocaleVariantCode", optional=true)
    public static native NSString LocaleVariantCode();
    @GlobalValue(symbol="NSLocaleExemplarCharacterSet", optional=true)
    public static native NSString LocaleExemplarCharacterSet();
    @GlobalValue(symbol="NSLocaleCalendar", optional=true)
    public static native NSString LocaleCalendar();
    @GlobalValue(symbol="NSLocaleCollationIdentifier", optional=true)
    public static native NSString LocaleCollationIdentifier();
    @GlobalValue(symbol="NSLocaleUsesMetricSystem", optional=true)
    public static native NSString LocaleUsesMetricSystem();
    @GlobalValue(symbol="NSLocaleMeasurementSystem", optional=true)
    public static native NSString LocaleMeasurementSystem();
    @GlobalValue(symbol="NSLocaleDecimalSeparator", optional=true)
    public static native NSString LocaleDecimalSeparator();
    @GlobalValue(symbol="NSLocaleGroupingSeparator", optional=true)
    public static native NSString LocaleGroupingSeparator();
    @GlobalValue(symbol="NSLocaleCurrencySymbol", optional=true)
    public static native NSString LocaleCurrencySymbol();
    @GlobalValue(symbol="NSLocaleCurrencyCode", optional=true)
    public static native NSString LocaleCurrencyCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleCollatorIdentifier", optional=true)
    public static native NSString LocaleCollatorIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey", optional=true)
    public static native NSString LocaleQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey", optional=true)
    public static native NSString LocaleQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native NSString LocaleAlternateQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native NSString LocaleAlternateQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSGregorianCalendar", optional=true)
    public static native String GregorianCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSBuddhistCalendar", optional=true)
    public static native String BuddhistCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSChineseCalendar", optional=true)
    public static native String ChineseCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSHebrewCalendar", optional=true)
    public static native String HebrewCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCalendar", optional=true)
    public static native String IslamicCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCivilCalendar", optional=true)
    public static native String IslamicCivilCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSJapaneseCalendar", optional=true)
    public static native String JapaneseCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSRepublicOfChinaCalendar", optional=true)
    public static native String RepublicOfChinaCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSPersianCalendar", optional=true)
    public static native String PersianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIndianCalendar", optional=true)
    public static native String IndianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSISO8601Calendar", optional=true)
    public static native String ISO8601Calendar();
    @GlobalValue(symbol="NSPortDidBecomeInvalidNotification", optional=true)
    public static native NSString PortDidBecomeInvalidNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingNameKey", optional=true)
    public static native NSString TextCheckingNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingJobTitleKey", optional=true)
    public static native NSString TextCheckingJobTitleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingOrganizationKey", optional=true)
    public static native NSString TextCheckingOrganizationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingStreetKey", optional=true)
    public static native NSString TextCheckingStreetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingCityKey", optional=true)
    public static native NSString TextCheckingCityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingStateKey", optional=true)
    public static native NSString TextCheckingStateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingZIPKey", optional=true)
    public static native NSString TextCheckingZIPKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingCountryKey", optional=true)
    public static native NSString TextCheckingCountryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingPhoneKey", optional=true)
    public static native NSString TextCheckingPhoneKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingAirlineKey", optional=true)
    public static native NSString TextCheckingAirlineKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingFlightKey", optional=true)
    public static native NSString TextCheckingFlightKey();
    @GlobalValue(symbol="NSDefaultRunLoopMode", optional=true)
    public static native String DefaultRunLoopMode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSRunLoopCommonModes", optional=true)
    public static native String RunLoopCommonModes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelKey", optional=true)
    public static native NSString StreamSocketSecurityLevelKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNone", optional=true)
    public static native NSString StreamSocketSecurityLevelNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv2", optional=true)
    public static native NSString StreamSocketSecurityLevelSSLv2();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv3", optional=true)
    public static native NSString StreamSocketSecurityLevelSSLv3();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelTLSv1", optional=true)
    public static native NSString StreamSocketSecurityLevelTLSv1();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNegotiatedSSL", optional=true)
    public static native NSString StreamSocketSecurityLevelNegotiatedSSL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyConfigurationKey", optional=true)
    public static native NSString StreamSOCKSProxyConfigurationKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyHostKey", optional=true)
    public static native NSString StreamSOCKSProxyHostKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPortKey", optional=true)
    public static native NSString StreamSOCKSProxyPortKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey", optional=true)
    public static native NSString StreamSOCKSProxyVersionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyUserKey", optional=true)
    public static native NSString StreamSOCKSProxyUserKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey", optional=true)
    public static native NSString StreamSOCKSProxyPasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion4", optional=true)
    public static native NSString StreamSOCKSProxyVersion4();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion5", optional=true)
    public static native NSString StreamSOCKSProxyVersion5();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamDataWrittenToMemoryStreamKey", optional=true)
    public static native NSString StreamDataWrittenToMemoryStreamKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamFileCurrentOffsetKey", optional=true)
    public static native NSString StreamFileCurrentOffsetKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSSLErrorDomain", optional=true)
    public static native NSString StreamSocketSSLErrorDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSErrorDomain", optional=true)
    public static native NSString StreamSOCKSErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceType", optional=true)
    public static native NSString StreamNetworkServiceType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP", optional=true)
    public static native NSString StreamNetworkServiceTypeVoIP();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo", optional=true)
    public static native NSString StreamNetworkServiceTypeVideo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground", optional=true)
    public static native NSString StreamNetworkServiceTypeBackground();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice", optional=true)
    public static native NSString StreamNetworkServiceTypeVoice();
    @GlobalValue(symbol="NSWillBecomeMultiThreadedNotification", optional=true)
    public static native NSString WillBecomeMultiThreadedNotification();
    @GlobalValue(symbol="NSDidBecomeSingleThreadedNotification", optional=true)
    public static native NSString DidBecomeSingleThreadedNotification();
    @GlobalValue(symbol="NSThreadWillExitNotification", optional=true)
    public static native NSString ThreadWillExitNotification();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSSystemTimeZoneDidChangeNotification", optional=true)
    public static native NSString SystemTimeZoneDidChangeNotification();
    @GlobalValue(symbol="NSURLFileScheme", optional=true)
    public static native String URLFileScheme();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
    public static native NSString URLKeysOfUnsetValuesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLNameKey", optional=true)
    public static native NSString URLNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
    public static native NSString URLLocalizedNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
    public static native NSString URLIsRegularFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
    public static native NSString URLIsDirectoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
    public static native NSString URLIsSymbolicLinkKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
    public static native NSString URLIsVolumeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
    public static native NSString URLIsPackageKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
    public static native NSString URLIsSystemImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
    public static native NSString URLIsUserImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
    public static native NSString URLIsHiddenKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
    public static native NSString URLHasHiddenExtensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
    public static native NSString URLCreationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
    public static native NSString URLContentAccessDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
    public static native NSString URLContentModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
    public static native NSString URLAttributeModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
    public static native NSString URLLinkCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
    public static native NSString URLParentDirectoryURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
    public static native NSString URLVolumeURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
    public static native NSString URLTypeIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
    public static native NSString URLLocalizedTypeDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
    public static native NSString URLLabelNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
    public static native NSString URLLabelColorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
    public static native NSString URLLocalizedLabelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
    public static native NSString URLEffectiveIconKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
    public static native NSString URLCustomIconKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
    public static native NSString URLFileResourceIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
    public static native NSString URLVolumeIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
    public static native NSString URLPreferredIOBlockSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
    public static native NSString URLIsReadableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
    public static native NSString URLIsWritableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
    public static native NSString URLIsExecutableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
    public static native NSString URLFileSecurityKey();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
    public static native NSString URLIsExcludedFromBackupKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSURLPathKey", optional=true)
    public static native NSString URLPathKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
    public static native NSString URLIsMountTriggerKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
    public static native NSString URLFileResourceTypeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
    public static native String URLFileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
    public static native String URLFileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
    public static native String URLFileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
    public static native String URLFileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
    public static native String URLFileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
    public static native String URLFileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
    public static native String URLFileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
    public static native String URLFileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
    public static native NSString URLFileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
    public static native NSString URLFileAllocatedSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
    public static native NSString URLTotalFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
    public static native NSString URLTotalFileAllocatedSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
    public static native NSString URLIsAliasFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native NSString URLVolumeLocalizedFormatDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
    public static native NSString URLVolumeTotalCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
    public static native NSString URLVolumeAvailableCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
    public static native NSString URLVolumeResourceCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native NSString URLVolumeSupportsPersistentIDsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native NSString URLVolumeSupportsSymbolicLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
    public static native NSString URLVolumeSupportsHardLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
    public static native NSString URLVolumeSupportsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
    public static native NSString URLVolumeIsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
    public static native NSString URLVolumeSupportsSparseFilesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
    public static native NSString URLVolumeSupportsZeroRunsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native NSString URLVolumeSupportsCaseSensitiveNamesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native NSString URLVolumeSupportsCasePreservedNamesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native NSString URLVolumeSupportsRootDirectoryDatesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native NSString URLVolumeSupportsVolumeSizesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
    public static native NSString URLVolumeSupportsRenamingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native NSString URLVolumeSupportsAdvisoryFileLockingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native NSString URLVolumeSupportsExtendedSecurityKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
    public static native NSString URLVolumeIsBrowsableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
    public static native NSString URLVolumeMaximumFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
    public static native NSString URLVolumeIsEjectableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
    public static native NSString URLVolumeIsRemovableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
    public static native NSString URLVolumeIsInternalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
    public static native NSString URLVolumeIsAutomountedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
    public static native NSString URLVolumeIsLocalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
    public static native NSString URLVolumeIsReadOnlyKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
    public static native NSString URLVolumeCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
    public static native NSString URLVolumeURLForRemountingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
    public static native NSString URLVolumeUUIDStringKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
    public static native NSString URLVolumeNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
    public static native NSString URLVolumeLocalizedNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUbiquitousItemKey", optional=true)
    public static native NSString URLIsUbiquitousItemKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString URLUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString URLUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString URLUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString URLUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString URLUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString URLUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString URLUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString URLUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString URLUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString URLUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="NSURLCredentialStorageChangedNotification", optional=true)
    public static native NSString URLCredentialStorageChangedNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLCredentialStorageRemoveSynchronizableCredentials", optional=true)
    public static native NSString URLCredentialStorageRemoveSynchronizableCredentials();
    @GlobalValue(symbol="NSURLErrorDomain", optional=true)
    public static native NSString URLErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLErrorKey", optional=true)
    public static native NSString URLErrorFailingURLErrorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLStringErrorKey", optional=true)
    public static native NSString URLErrorFailingURLStringErrorKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 4.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSErrorFailingURLStringKey", optional=true)
    public static native NSString ErrorFailingURLStringKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLPeerTrustErrorKey", optional=true)
    public static native NSString URLErrorFailingURLPeerTrustErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorBackgroundTaskCancelledReasonKey", optional=true)
    public static native NSString URLErrorBackgroundTaskCancelledReasonKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTP", optional=true)
    public static native String URLProtectionSpaceHTTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPS", optional=true)
    public static native String URLProtectionSpaceHTTPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceFTP", optional=true)
    public static native String URLProtectionSpaceFTP();
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPProxy", optional=true)
    public static native String URLProtectionSpaceHTTPProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPSProxy", optional=true)
    public static native String URLProtectionSpaceHTTPSProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceFTPProxy", optional=true)
    public static native String URLProtectionSpaceFTPProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceSOCKSProxy", optional=true)
    public static native String URLProtectionSpaceSOCKSProxy();
    @GlobalValue(symbol="NSURLAuthenticationMethodDefault", optional=true)
    public static native String URLAuthenticationMethodDefault();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPBasic", optional=true)
    public static native String URLAuthenticationMethodHTTPBasic();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPDigest", optional=true)
    public static native String URLAuthenticationMethodHTTPDigest();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTMLForm", optional=true)
    public static native String URLAuthenticationMethodHTMLForm();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNTLM", optional=true)
    public static native String URLAuthenticationMethodNTLM();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNegotiate", optional=true)
    public static native String URLAuthenticationMethodNegotiate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodClientCertificate", optional=true)
    public static native String URLAuthenticationMethodClientCertificate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodServerTrust", optional=true)
    public static native String URLAuthenticationMethodServerTrust();
    @GlobalValue(symbol="NSGlobalDomain", optional=true)
    public static native String GlobalDomain();
    @GlobalValue(symbol="NSArgumentDomain", optional=true)
    public static native String ArgumentDomain();
    @GlobalValue(symbol="NSRegistrationDomain", optional=true)
    public static native String RegistrationDomain();
    @GlobalValue(symbol="NSUserDefaultsDidChangeNotification", optional=true)
    public static native NSString UserDefaultsDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSNegateBooleanTransformerName", optional=true)
    public static native String NegateBooleanTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNilTransformerName", optional=true)
    public static native String IsNilTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNotNilTransformerName", optional=true)
    public static native String IsNotNilTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUnarchiveFromDataTransformerName", optional=true)
    public static native String UnarchiveFromDataTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSKeyedUnarchiveFromDataTransformerName", optional=true)
    public static native String KeyedUnarchiveFromDataTransformerName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSXMLParserErrorDomain", optional=true)
    public static native NSString XMLParserErrorDomain();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeTokenType", optional=true)
    public static native NSString LinguisticTagSchemeTokenType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLexicalClass", optional=true)
    public static native NSString LinguisticTagSchemeLexicalClass();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameType", optional=true)
    public static native NSString LinguisticTagSchemeNameType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameTypeOrLexicalClass", optional=true)
    public static native NSString LinguisticTagSchemeNameTypeOrLexicalClass();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLemma", optional=true)
    public static native NSString LinguisticTagSchemeLemma();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLanguage", optional=true)
    public static native NSString LinguisticTagSchemeLanguage();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeScript", optional=true)
    public static native NSString LinguisticTagSchemeScript();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWord", optional=true)
    public static native NSString LinguisticTagWord();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPunctuation", optional=true)
    public static native NSString LinguisticTagPunctuation();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWhitespace", optional=true)
    public static native NSString LinguisticTagWhitespace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOther", optional=true)
    public static native NSString LinguisticTagOther();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNoun", optional=true)
    public static native NSString LinguisticTagNoun();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagVerb", optional=true)
    public static native NSString LinguisticTagVerb();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdjective", optional=true)
    public static native NSString LinguisticTagAdjective();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdverb", optional=true)
    public static native NSString LinguisticTagAdverb();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPronoun", optional=true)
    public static native NSString LinguisticTagPronoun();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDeterminer", optional=true)
    public static native NSString LinguisticTagDeterminer();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParticle", optional=true)
    public static native NSString LinguisticTagParticle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPreposition", optional=true)
    public static native NSString LinguisticTagPreposition();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNumber", optional=true)
    public static native NSString LinguisticTagNumber();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagConjunction", optional=true)
    public static native NSString LinguisticTagConjunction();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagInterjection", optional=true)
    public static native NSString LinguisticTagInterjection();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagClassifier", optional=true)
    public static native NSString LinguisticTagClassifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagIdiom", optional=true)
    public static native NSString LinguisticTagIdiom();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWord", optional=true)
    public static native NSString LinguisticTagOtherWord();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSentenceTerminator", optional=true)
    public static native NSString LinguisticTagSentenceTerminator();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenQuote", optional=true)
    public static native NSString LinguisticTagOpenQuote();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseQuote", optional=true)
    public static native NSString LinguisticTagCloseQuote();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenParenthesis", optional=true)
    public static native NSString LinguisticTagOpenParenthesis();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseParenthesis", optional=true)
    public static native NSString LinguisticTagCloseParenthesis();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWordJoiner", optional=true)
    public static native NSString LinguisticTagWordJoiner();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDash", optional=true)
    public static native NSString LinguisticTagDash();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherPunctuation", optional=true)
    public static native NSString LinguisticTagOtherPunctuation();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParagraphBreak", optional=true)
    public static native NSString LinguisticTagParagraphBreak();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWhitespace", optional=true)
    public static native NSString LinguisticTagOtherWhitespace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPersonalName", optional=true)
    public static native NSString LinguisticTagPersonalName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPlaceName", optional=true)
    public static native NSString LinguisticTagPlaceName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOrganizationName", optional=true)
    public static native NSString LinguisticTagOrganizationName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
    public static native NSString MetadataItemFSNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
    public static native NSString MetadataItemDisplayNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
    public static native NSString MetadataItemURLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
    public static native NSString MetadataItemPathKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
    public static native NSString MetadataItemFSSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
    public static native NSString MetadataItemFSCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
    public static native NSString MetadataItemFSContentChangeDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
    public static native NSString MetadataItemIsUbiquitousKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString MetadataUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString MetadataUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString MetadataUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native NSString MetadataUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native NSString MetadataUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native NSString MetadataUbiquitousItemDownloadingStatusCurrent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString MetadataUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString MetadataUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString MetadataUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString MetadataUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString MetadataUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString MetadataUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString MetadataUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidStartGatheringNotification", optional=true)
    public static native NSString MetadataQueryDidStartGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryGatheringProgressNotification", optional=true)
    public static native NSString MetadataQueryGatheringProgressNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidFinishGatheringNotification", optional=true)
    public static native NSString MetadataQueryDidFinishGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidUpdateNotification", optional=true)
    public static native NSString MetadataQueryDidUpdateNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateAddedItemsKey", optional=true)
    public static native NSString MetadataQueryUpdateAddedItemsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateChangedItemsKey", optional=true)
    public static native NSString MetadataQueryUpdateChangedItemsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateRemovedItemsKey", optional=true)
    public static native NSString MetadataQueryUpdateRemovedItemsKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryResultContentRelevanceAttribute", optional=true)
    public static native NSString MetadataQueryResultContentRelevanceAttribute();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUbiquitousDocumentsScope", optional=true)
    public static native NSString MetadataQueryUbiquitousDocumentsScope();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUbiquitousDataScope", optional=true)
    public static native NSString MetadataQueryUbiquitousDataScope();
    @GlobalValue(symbol="NSNetServicesErrorCode", optional=true)
    public static native NSString NetServicesErrorCode();
    @GlobalValue(symbol="NSNetServicesErrorDomain", optional=true)
    public static native NSString NetServicesErrorDomain();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressEstimatedTimeRemainingKey", optional=true)
    public static native NSString ProgressEstimatedTimeRemainingKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressThroughputKey", optional=true)
    public static native NSString ProgressThroughputKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressKindFile", optional=true)
    public static native String ProgressKindFile();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindKey", optional=true)
    public static native NSString ProgressFileOperationKindKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindDownloading", optional=true)
    public static native NSString ProgressFileOperationKindDownloading();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindDecompressingAfterDownloading", optional=true)
    public static native NSString ProgressFileOperationKindDecompressingAfterDownloading();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindReceiving", optional=true)
    public static native NSString ProgressFileOperationKindReceiving();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindCopying", optional=true)
    public static native NSString ProgressFileOperationKindCopying();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileURLKey", optional=true)
    public static native NSString ProgressFileURLKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileTotalCountKey", optional=true)
    public static native NSString ProgressFileTotalCountKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileCompletedCountKey", optional=true)
    public static native NSString ProgressFileCompletedCountKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreDidChangeExternallyNotification", optional=true)
    public static native NSString UbiquitousKeyValueStoreDidChangeExternallyNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangeReasonKey", optional=true)
    public static native NSString UbiquitousKeyValueStoreChangeReasonKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangedKeysKey", optional=true)
    public static native NSString UbiquitousKeyValueStoreChangedKeysKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerGroupIsDiscardableKey", optional=true)
    public static native NSString UndoManagerGroupIsDiscardableKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerCheckpointNotification", optional=true)
    public static native NSString UndoManagerCheckpointNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillUndoChangeNotification", optional=true)
    public static native NSString UndoManagerWillUndoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillRedoChangeNotification", optional=true)
    public static native NSString UndoManagerWillRedoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidUndoChangeNotification", optional=true)
    public static native NSString UndoManagerDidUndoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidRedoChangeNotification", optional=true)
    public static native NSString UndoManagerDidRedoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidOpenUndoGroupNotification", optional=true)
    public static native NSString UndoManagerDidOpenUndoGroupNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillCloseUndoGroupNotification", optional=true)
    public static native NSString UndoManagerWillCloseUndoGroupNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidCloseUndoGroupNotification", optional=true)
    public static native NSString UndoManagerDidCloseUndoGroupNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionTransferSizeUnknown", optional=true)
    public static native long URLSessionTransferSizeUnknown();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionDownloadTaskResumeData", optional=true)
    public static native NSString URLSessionDownloadTaskResumeData();
    /*</methods>*/
}
