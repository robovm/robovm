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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Foundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Foundation.class); }/*</bind>*/
    /*<constants>*/
    public static final double NSFoundationVersionNumber10_0 = 397.40;
    public static final double NSFoundationVersionNumber10_1 = 425.00;
    public static final double NSFoundationVersionNumber10_1_1 = 425.00;
    public static final double NSFoundationVersionNumber10_1_2 = 425.00;
    public static final double NSFoundationVersionNumber10_1_3 = 425.00;
    public static final double NSFoundationVersionNumber10_1_4 = 425.00;
    public static final double NSFoundationVersionNumber10_2 = 462.00;
    public static final double NSFoundationVersionNumber10_2_1 = 462.00;
    public static final double NSFoundationVersionNumber10_2_2 = 462.00;
    public static final double NSFoundationVersionNumber10_2_3 = 462.00;
    public static final double NSFoundationVersionNumber10_2_4 = 462.00;
    public static final double NSFoundationVersionNumber10_2_5 = 462.00;
    public static final double NSFoundationVersionNumber10_2_6 = 462.00;
    public static final double NSFoundationVersionNumber10_2_7 = 462.70;
    public static final double NSFoundationVersionNumber10_2_8 = 462.70;
    public static final double NSFoundationVersionNumber10_3 = 500.00;
    public static final double NSFoundationVersionNumber10_3_1 = 500.00;
    public static final double NSFoundationVersionNumber10_3_2 = 500.30;
    public static final double NSFoundationVersionNumber10_3_3 = 500.54;
    public static final double NSFoundationVersionNumber10_3_4 = 500.56;
    public static final double NSFoundationVersionNumber10_3_5 = 500.56;
    public static final double NSFoundationVersionNumber10_3_6 = 500.56;
    public static final double NSFoundationVersionNumber10_3_7 = 500.56;
    public static final double NSFoundationVersionNumber10_3_8 = 500.56;
    public static final double NSFoundationVersionNumber10_3_9 = 500.58;
    public static final double NSFoundationVersionNumber10_4 = 567.00;
    public static final double NSFoundationVersionNumber10_4_1 = 567.00;
    public static final double NSFoundationVersionNumber10_4_2 = 567.12;
    public static final double NSFoundationVersionNumber10_4_3 = 567.21;
    public static final double NSFoundationVersionNumber10_4_4_Intel = 567.23;
    public static final double NSFoundationVersionNumber10_4_4_PowerPC = 567.21;
    public static final double NSFoundationVersionNumber10_4_5 = 567.25;
    public static final double NSFoundationVersionNumber10_4_6 = 567.26;
    public static final double NSFoundationVersionNumber10_4_7 = 567.27;
    public static final double NSFoundationVersionNumber10_4_8 = 567.28;
    public static final double NSFoundationVersionNumber10_4_9 = 567.29;
    public static final double NSFoundationVersionNumber10_4_10 = 567.29;
    public static final double NSFoundationVersionNumber10_4_11 = 567.36;
    public static final double NSFoundationVersionNumber10_5 = 677.00;
    public static final double NSFoundationVersionNumber10_5_1 = 677.10;
    public static final double NSFoundationVersionNumber10_5_2 = 677.15;
    public static final double NSFoundationVersionNumber10_5_3 = 677.19;
    public static final double NSFoundationVersionNumber10_5_4 = 677.19;
    public static final double NSFoundationVersionNumber10_5_5 = 677.21;
    public static final double NSFoundationVersionNumber10_5_6 = 677.22;
    public static final double NSFoundationVersionNumber10_5_7 = 677.24;
    public static final double NSFoundationVersionNumber10_5_8 = 677.26;
    public static final double NSFoundationVersionNumber10_6 = 751.00;
    public static final double NSFoundationVersionNumber10_6_1 = 751.00;
    public static final double NSFoundationVersionNumber10_6_2 = 751.14;
    public static final double NSFoundationVersionNumber10_6_3 = 751.21;
    public static final double NSFoundationVersionNumber10_6_4 = 751.29;
    public static final double NSFoundationVersionNumber10_6_5 = 751.42;
    public static final double NSFoundationVersionNumber10_6_6 = 751.53;
    public static final double NSFoundationVersionNumber10_6_7 = 751.53;
    public static final double NSFoundationVersionNumber10_6_8 = 751.62;
    public static final double NSFoundationVersionNumber10_7 = 833.10;
    public static final double NSFoundationVersionNumber10_7_1 = 833.10;
    public static final double NSFoundationVersionNumber10_7_2 = 833.20;
    public static final double NSFoundationVersionNumber10_7_3 = 833.24;
    public static final double NSFoundationVersionNumber10_7_4 = 833.25;
    public static final double NSFoundationVersionNumber10_8 = 945.00;
    public static final double NSFoundationVersionNumber10_8_1 = 945.00;
    public static final double NSFoundationVersionNumber10_8_2 = 945.11;
    public static final double NSFoundationVersionNumber10_8_3 = 945.16;
    public static final double NSFoundationVersionNumber10_8_4 = 945.18;
    public static final double NSFoundationVersionNumber_iPhoneOS_2_0 = 678.24;
    public static final double NSFoundationVersionNumber_iPhoneOS_2_1 = 678.26;
    public static final double NSFoundationVersionNumber_iPhoneOS_2_2 = 678.29;
    public static final double NSFoundationVersionNumber_iPhoneOS_3_0 = 678.47;
    public static final double NSFoundationVersionNumber_iPhoneOS_3_1 = 678.51;
    public static final double NSFoundationVersionNumber_iPhoneOS_3_2 = 678.60;
    public static final double NSFoundationVersionNumber_iOS_4_0 = 751.32;
    public static final double NSFoundationVersionNumber_iOS_4_1 = 751.37;
    public static final double NSFoundationVersionNumber_iOS_4_2 = 751.49;
    public static final double NSFoundationVersionNumber_iOS_4_3 = 751.49;
    public static final double NSFoundationVersionNumber_iOS_5_0 = 881.00;
    public static final double NSFoundationVersionNumber_iOS_5_1 = 890.10;
    public static final double NSFoundationVersionNumber_iOS_6_0 = 993.00;
    public static final double NSFoundationVersionNumber_iOS_6_1 = 993.00;
    public static final double NSTimeIntervalSince1970 = 978307200.0;
    public static final int NSDecimalMaxSize = 8;
    public static final int NSDecimalNoScale = 32767;
    public static final int NSFoundationVersionWithFileManagerResourceForkSupport = 412;
    public static final int NSURLResponseUnknownLength = -1;
    public static final int NSNotFound = 2147483647;
    public static final int NSDateComponentUndefined = 2147483647;
    public static final int NSOpenStepUnicodeReservedBase = 62464;
    public static final int NSOperationQueueDefaultMaxConcurrentOperationCount = -1;
    public static final int NSUndoCloseGroupingRunLoopOrdering = 350000;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSFoundationVersionNumber", optional=true)
    public static native double NSFoundationVersionNumber();
    @GlobalValue(symbol="NSBundleDidLoadNotification", optional=true)
    public static native NSString NSBundleDidLoadNotification();
    @GlobalValue(symbol="NSLoadedClasses", optional=true)
    public static native NSString NSLoadedClasses();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSSystemClockDidChangeNotification", optional=true)
    public static native NSString NSSystemClockDidChangeNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierGregorian", optional=true)
    public static native String NSCalendarIdentifierGregorian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierBuddhist", optional=true)
    public static native String NSCalendarIdentifierBuddhist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierChinese", optional=true)
    public static native String NSCalendarIdentifierChinese();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierCoptic", optional=true)
    public static native String NSCalendarIdentifierCoptic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteMihret", optional=true)
    public static native String NSCalendarIdentifierEthiopicAmeteMihret();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteAlem", optional=true)
    public static native String NSCalendarIdentifierEthiopicAmeteAlem();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierHebrew", optional=true)
    public static native String NSCalendarIdentifierHebrew();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierISO8601", optional=true)
    public static native String NSCalendarIdentifierISO8601();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIndian", optional=true)
    public static native String NSCalendarIdentifierIndian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamic", optional=true)
    public static native String NSCalendarIdentifierIslamic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierIslamicCivil", optional=true)
    public static native String NSCalendarIdentifierIslamicCivil();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierJapanese", optional=true)
    public static native String NSCalendarIdentifierJapanese();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierPersian", optional=true)
    public static native String NSCalendarIdentifierPersian();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSCalendarIdentifierRepublicOfChina", optional=true)
    public static native String NSCalendarIdentifierRepublicOfChina();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCalendarDayChangedNotification", optional=true)
    public static native NSString NSCalendarDayChangedNotification();
    @GlobalValue(symbol="NSCocoaErrorDomain", optional=true)
    public static native NSString NSCocoaErrorDomain();
    @GlobalValue(symbol="NSPOSIXErrorDomain", optional=true)
    public static native NSString NSPOSIXErrorDomain();
    @GlobalValue(symbol="NSOSStatusErrorDomain", optional=true)
    public static native NSString NSOSStatusErrorDomain();
    @GlobalValue(symbol="NSMachErrorDomain", optional=true)
    public static native NSString NSMachErrorDomain();
    @GlobalValue(symbol="NSUnderlyingErrorKey", optional=true)
    public static native NSString NSUnderlyingErrorKey();
    @GlobalValue(symbol="NSLocalizedDescriptionKey", optional=true)
    public static native NSString NSLocalizedDescriptionKey();
    @GlobalValue(symbol="NSLocalizedFailureReasonErrorKey", optional=true)
    public static native NSString NSLocalizedFailureReasonErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoverySuggestionErrorKey", optional=true)
    public static native NSString NSLocalizedRecoverySuggestionErrorKey();
    @GlobalValue(symbol="NSLocalizedRecoveryOptionsErrorKey", optional=true)
    public static native NSString NSLocalizedRecoveryOptionsErrorKey();
    @GlobalValue(symbol="NSRecoveryAttempterErrorKey", optional=true)
    public static native NSString NSRecoveryAttempterErrorKey();
    @GlobalValue(symbol="NSHelpAnchorErrorKey", optional=true)
    public static native NSString NSHelpAnchorErrorKey();
    @GlobalValue(symbol="NSStringEncodingErrorKey", optional=true)
    public static native NSString NSStringEncodingErrorKey();
    @GlobalValue(symbol="NSURLErrorKey", optional=true)
    public static native NSString NSURLErrorKey();
    @GlobalValue(symbol="NSFilePathErrorKey", optional=true)
    public static native NSString NSFilePathErrorKey();
    @GlobalValue(symbol="NSFileHandleReadCompletionNotification", optional=true)
    public static native NSString NSFileHandleReadCompletionNotification();
    @GlobalValue(symbol="NSFileHandleReadToEndOfFileCompletionNotification", optional=true)
    public static native NSString NSFileHandleReadToEndOfFileCompletionNotification();
    @GlobalValue(symbol="NSFileHandleConnectionAcceptedNotification", optional=true)
    public static native NSString NSFileHandleConnectionAcceptedNotification();
    @GlobalValue(symbol="NSFileHandleDataAvailableNotification", optional=true)
    public static native NSString NSFileHandleDataAvailableNotification();
    @GlobalValue(symbol="NSFileHandleNotificationDataItem", optional=true)
    public static native String NSFileHandleNotificationDataItem();
    @GlobalValue(symbol="NSFileHandleNotificationFileHandleItem", optional=true)
    public static native String NSFileHandleNotificationFileHandleItem();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSFileHandleNotificationMonitorModes", optional=true)
    public static native String NSFileHandleNotificationMonitorModes();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUbiquityIdentityDidChangeNotification", optional=true)
    public static native NSString NSUbiquityIdentityDidChangeNotification();
    @GlobalValue(symbol="NSFileType", optional=true)
    public static native NSString NSFileType();
    @GlobalValue(symbol="NSFileTypeDirectory", optional=true)
    public static native NSString NSFileTypeDirectory();
    @GlobalValue(symbol="NSFileTypeRegular", optional=true)
    public static native NSString NSFileTypeRegular();
    @GlobalValue(symbol="NSFileTypeSymbolicLink", optional=true)
    public static native NSString NSFileTypeSymbolicLink();
    @GlobalValue(symbol="NSFileTypeSocket", optional=true)
    public static native NSString NSFileTypeSocket();
    @GlobalValue(symbol="NSFileTypeCharacterSpecial", optional=true)
    public static native NSString NSFileTypeCharacterSpecial();
    @GlobalValue(symbol="NSFileTypeBlockSpecial", optional=true)
    public static native NSString NSFileTypeBlockSpecial();
    @GlobalValue(symbol="NSFileTypeUnknown", optional=true)
    public static native NSString NSFileTypeUnknown();
    @GlobalValue(symbol="NSFileSize", optional=true)
    public static native NSString NSFileSize();
    @GlobalValue(symbol="NSFileModificationDate", optional=true)
    public static native NSString NSFileModificationDate();
    @GlobalValue(symbol="NSFileReferenceCount", optional=true)
    public static native NSString NSFileReferenceCount();
    @GlobalValue(symbol="NSFileDeviceIdentifier", optional=true)
    public static native NSString NSFileDeviceIdentifier();
    @GlobalValue(symbol="NSFileOwnerAccountName", optional=true)
    public static native NSString NSFileOwnerAccountName();
    @GlobalValue(symbol="NSFileGroupOwnerAccountName", optional=true)
    public static native NSString NSFileGroupOwnerAccountName();
    @GlobalValue(symbol="NSFilePosixPermissions", optional=true)
    public static native NSString NSFilePosixPermissions();
    @GlobalValue(symbol="NSFileSystemNumber", optional=true)
    public static native NSString NSFileSystemNumber();
    @GlobalValue(symbol="NSFileSystemFileNumber", optional=true)
    public static native NSString NSFileSystemFileNumber();
    @GlobalValue(symbol="NSFileExtensionHidden", optional=true)
    public static native NSString NSFileExtensionHidden();
    @GlobalValue(symbol="NSFileHFSCreatorCode", optional=true)
    public static native NSString NSFileHFSCreatorCode();
    @GlobalValue(symbol="NSFileHFSTypeCode", optional=true)
    public static native NSString NSFileHFSTypeCode();
    @GlobalValue(symbol="NSFileImmutable", optional=true)
    public static native NSString NSFileImmutable();
    @GlobalValue(symbol="NSFileAppendOnly", optional=true)
    public static native NSString NSFileAppendOnly();
    @GlobalValue(symbol="NSFileCreationDate", optional=true)
    public static native NSString NSFileCreationDate();
    @GlobalValue(symbol="NSFileOwnerAccountID", optional=true)
    public static native NSString NSFileOwnerAccountID();
    @GlobalValue(symbol="NSFileGroupOwnerAccountID", optional=true)
    public static native NSString NSFileGroupOwnerAccountID();
    @GlobalValue(symbol="NSFileBusy", optional=true)
    public static native NSString NSFileBusy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionKey", optional=true)
    public static native NSString NSFileProtectionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionNone", optional=true)
    public static native NSString NSFileProtectionNone();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionComplete", optional=true)
    public static native NSString NSFileProtectionComplete();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionCompleteUnlessOpen", optional=true)
    public static native NSString NSFileProtectionCompleteUnlessOpen();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionCompleteUntilFirstUserAuthentication", optional=true)
    public static native NSString NSFileProtectionCompleteUntilFirstUserAuthentication();
    @GlobalValue(symbol="NSFileSystemSize", optional=true)
    public static native NSString NSFileSystemSize();
    @GlobalValue(symbol="NSFileSystemFreeSize", optional=true)
    public static native NSString NSFileSystemFreeSize();
    @GlobalValue(symbol="NSFileSystemNodes", optional=true)
    public static native NSString NSFileSystemNodes();
    @GlobalValue(symbol="NSFileSystemFreeNodes", optional=true)
    public static native NSString NSFileSystemFreeNodes();
    @GlobalValue(symbol="NSHTTPCookieName", optional=true)
    public static native NSString NSHTTPCookieName();
    @GlobalValue(symbol="NSHTTPCookieValue", optional=true)
    public static native NSString NSHTTPCookieValue();
    @GlobalValue(symbol="NSHTTPCookieOriginURL", optional=true)
    public static native NSString NSHTTPCookieOriginURL();
    @GlobalValue(symbol="NSHTTPCookieVersion", optional=true)
    public static native NSString NSHTTPCookieVersion();
    @GlobalValue(symbol="NSHTTPCookieDomain", optional=true)
    public static native NSString NSHTTPCookieDomain();
    @GlobalValue(symbol="NSHTTPCookiePath", optional=true)
    public static native NSString NSHTTPCookiePath();
    @GlobalValue(symbol="NSHTTPCookieSecure", optional=true)
    public static native NSString NSHTTPCookieSecure();
    @GlobalValue(symbol="NSHTTPCookieExpires", optional=true)
    public static native NSString NSHTTPCookieExpires();
    @GlobalValue(symbol="NSHTTPCookieComment", optional=true)
    public static native NSString NSHTTPCookieComment();
    @GlobalValue(symbol="NSHTTPCookieCommentURL", optional=true)
    public static native NSString NSHTTPCookieCommentURL();
    @GlobalValue(symbol="NSHTTPCookieDiscard", optional=true)
    public static native NSString NSHTTPCookieDiscard();
    @GlobalValue(symbol="NSHTTPCookieMaximumAge", optional=true)
    public static native NSString NSHTTPCookieMaximumAge();
    @GlobalValue(symbol="NSHTTPCookiePort", optional=true)
    public static native NSString NSHTTPCookiePort();
    @GlobalValue(symbol="NSHTTPCookieManagerAcceptPolicyChangedNotification", optional=true)
    public static native NSString NSHTTPCookieManagerAcceptPolicyChangedNotification();
    @GlobalValue(symbol="NSHTTPCookieManagerCookiesChangedNotification", optional=true)
    public static native NSString NSHTTPCookieManagerCookiesChangedNotification();
    @GlobalValue(symbol="NSAverageKeyValueOperator", optional=true)
    public static native String NSAverageKeyValueOperator();
    @GlobalValue(symbol="NSCountKeyValueOperator", optional=true)
    public static native String NSCountKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfArraysKeyValueOperator", optional=true)
    public static native String NSDistinctUnionOfArraysKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfObjectsKeyValueOperator", optional=true)
    public static native String NSDistinctUnionOfObjectsKeyValueOperator();
    @GlobalValue(symbol="NSDistinctUnionOfSetsKeyValueOperator", optional=true)
    public static native String NSDistinctUnionOfSetsKeyValueOperator();
    @GlobalValue(symbol="NSMaximumKeyValueOperator", optional=true)
    public static native String NSMaximumKeyValueOperator();
    @GlobalValue(symbol="NSMinimumKeyValueOperator", optional=true)
    public static native String NSMinimumKeyValueOperator();
    @GlobalValue(symbol="NSSumKeyValueOperator", optional=true)
    public static native String NSSumKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfArraysKeyValueOperator", optional=true)
    public static native String NSUnionOfArraysKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfObjectsKeyValueOperator", optional=true)
    public static native String NSUnionOfObjectsKeyValueOperator();
    @GlobalValue(symbol="NSUnionOfSetsKeyValueOperator", optional=true)
    public static native String NSUnionOfSetsKeyValueOperator();
    @GlobalValue(symbol="NSKeyValueChangeKindKey", optional=true)
    public static native NSString NSKeyValueChangeKindKey();
    @GlobalValue(symbol="NSKeyValueChangeNewKey", optional=true)
    public static native NSString NSKeyValueChangeNewKey();
    @GlobalValue(symbol="NSKeyValueChangeOldKey", optional=true)
    public static native NSString NSKeyValueChangeOldKey();
    @GlobalValue(symbol="NSKeyValueChangeIndexesKey", optional=true)
    public static native NSString NSKeyValueChangeIndexesKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSKeyValueChangeNotificationIsPriorKey", optional=true)
    public static native NSString NSKeyValueChangeNotificationIsPriorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSKeyedArchiveRootObjectKey", optional=true)
    public static native NSString NSKeyedArchiveRootObjectKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSCurrentLocaleDidChangeNotification", optional=true)
    public static native NSString NSCurrentLocaleDidChangeNotification();
    @GlobalValue(symbol="NSLocaleIdentifier", optional=true)
    public static native NSString NSLocaleIdentifier();
    @GlobalValue(symbol="NSLocaleLanguageCode", optional=true)
    public static native NSString NSLocaleLanguageCode();
    @GlobalValue(symbol="NSLocaleCountryCode", optional=true)
    public static native NSString NSLocaleCountryCode();
    @GlobalValue(symbol="NSLocaleScriptCode", optional=true)
    public static native NSString NSLocaleScriptCode();
    @GlobalValue(symbol="NSLocaleVariantCode", optional=true)
    public static native NSString NSLocaleVariantCode();
    @GlobalValue(symbol="NSLocaleExemplarCharacterSet", optional=true)
    public static native NSString NSLocaleExemplarCharacterSet();
    @GlobalValue(symbol="NSLocaleCalendar", optional=true)
    public static native NSString NSLocaleCalendar();
    @GlobalValue(symbol="NSLocaleCollationIdentifier", optional=true)
    public static native NSString NSLocaleCollationIdentifier();
    @GlobalValue(symbol="NSLocaleUsesMetricSystem", optional=true)
    public static native NSString NSLocaleUsesMetricSystem();
    @GlobalValue(symbol="NSLocaleMeasurementSystem", optional=true)
    public static native NSString NSLocaleMeasurementSystem();
    @GlobalValue(symbol="NSLocaleDecimalSeparator", optional=true)
    public static native NSString NSLocaleDecimalSeparator();
    @GlobalValue(symbol="NSLocaleGroupingSeparator", optional=true)
    public static native NSString NSLocaleGroupingSeparator();
    @GlobalValue(symbol="NSLocaleCurrencySymbol", optional=true)
    public static native NSString NSLocaleCurrencySymbol();
    @GlobalValue(symbol="NSLocaleCurrencyCode", optional=true)
    public static native NSString NSLocaleCurrencyCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleCollatorIdentifier", optional=true)
    public static native NSString NSLocaleCollatorIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey", optional=true)
    public static native NSString NSLocaleQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey", optional=true)
    public static native NSString NSLocaleQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native NSString NSLocaleAlternateQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native NSString NSLocaleAlternateQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSGregorianCalendar", optional=true)
    public static native String NSGregorianCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSBuddhistCalendar", optional=true)
    public static native String NSBuddhistCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSChineseCalendar", optional=true)
    public static native String NSChineseCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSHebrewCalendar", optional=true)
    public static native String NSHebrewCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCalendar", optional=true)
    public static native String NSIslamicCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCivilCalendar", optional=true)
    public static native String NSIslamicCivilCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSJapaneseCalendar", optional=true)
    public static native String NSJapaneseCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSRepublicOfChinaCalendar", optional=true)
    public static native String NSRepublicOfChinaCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSPersianCalendar", optional=true)
    public static native String NSPersianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIndianCalendar", optional=true)
    public static native String NSIndianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSISO8601Calendar", optional=true)
    public static native String NSISO8601Calendar();
    @GlobalValue(symbol="NSPortDidBecomeInvalidNotification", optional=true)
    public static native NSString NSPortDidBecomeInvalidNotification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingNameKey", optional=true)
    public static native NSString NSTextCheckingNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingJobTitleKey", optional=true)
    public static native NSString NSTextCheckingJobTitleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingOrganizationKey", optional=true)
    public static native NSString NSTextCheckingOrganizationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingStreetKey", optional=true)
    public static native NSString NSTextCheckingStreetKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingCityKey", optional=true)
    public static native NSString NSTextCheckingCityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingStateKey", optional=true)
    public static native NSString NSTextCheckingStateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingZIPKey", optional=true)
    public static native NSString NSTextCheckingZIPKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingCountryKey", optional=true)
    public static native NSString NSTextCheckingCountryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingPhoneKey", optional=true)
    public static native NSString NSTextCheckingPhoneKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingAirlineKey", optional=true)
    public static native NSString NSTextCheckingAirlineKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSTextCheckingFlightKey", optional=true)
    public static native NSString NSTextCheckingFlightKey();
    @GlobalValue(symbol="NSDefaultRunLoopMode", optional=true)
    public static native String NSDefaultRunLoopMode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSRunLoopCommonModes", optional=true)
    public static native String NSRunLoopCommonModes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelKey", optional=true)
    public static native NSString NSStreamSocketSecurityLevelKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNone", optional=true)
    public static native NSString NSStreamSocketSecurityLevelNone();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv2", optional=true)
    public static native NSString NSStreamSocketSecurityLevelSSLv2();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv3", optional=true)
    public static native NSString NSStreamSocketSecurityLevelSSLv3();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelTLSv1", optional=true)
    public static native NSString NSStreamSocketSecurityLevelTLSv1();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSecurityLevelNegotiatedSSL", optional=true)
    public static native NSString NSStreamSocketSecurityLevelNegotiatedSSL();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyConfigurationKey", optional=true)
    public static native NSString NSStreamSOCKSProxyConfigurationKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyHostKey", optional=true)
    public static native NSString NSStreamSOCKSProxyHostKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPortKey", optional=true)
    public static native NSString NSStreamSOCKSProxyPortKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersionKey", optional=true)
    public static native NSString NSStreamSOCKSProxyVersionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyUserKey", optional=true)
    public static native NSString NSStreamSOCKSProxyUserKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyPasswordKey", optional=true)
    public static native NSString NSStreamSOCKSProxyPasswordKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion4", optional=true)
    public static native NSString NSStreamSOCKSProxyVersion4();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSProxyVersion5", optional=true)
    public static native NSString NSStreamSOCKSProxyVersion5();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamDataWrittenToMemoryStreamKey", optional=true)
    public static native NSString NSStreamDataWrittenToMemoryStreamKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamFileCurrentOffsetKey", optional=true)
    public static native NSString NSStreamFileCurrentOffsetKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSocketSSLErrorDomain", optional=true)
    public static native NSString NSStreamSocketSSLErrorDomain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSStreamSOCKSErrorDomain", optional=true)
    public static native NSString NSStreamSOCKSErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceType", optional=true)
    public static native NSString NSStreamNetworkServiceType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoIP", optional=true)
    public static native NSString NSStreamNetworkServiceTypeVoIP();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVideo", optional=true)
    public static native NSString NSStreamNetworkServiceTypeVideo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeBackground", optional=true)
    public static native NSString NSStreamNetworkServiceTypeBackground();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSStreamNetworkServiceTypeVoice", optional=true)
    public static native NSString NSStreamNetworkServiceTypeVoice();
    @GlobalValue(symbol="NSWillBecomeMultiThreadedNotification", optional=true)
    public static native NSString NSWillBecomeMultiThreadedNotification();
    @GlobalValue(symbol="NSDidBecomeSingleThreadedNotification", optional=true)
    public static native NSString NSDidBecomeSingleThreadedNotification();
    @GlobalValue(symbol="NSThreadWillExitNotification", optional=true)
    public static native NSString NSThreadWillExitNotification();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSSystemTimeZoneDidChangeNotification", optional=true)
    public static native NSString NSSystemTimeZoneDidChangeNotification();
    @GlobalValue(symbol="NSURLFileScheme", optional=true)
    public static native String NSURLFileScheme();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
    public static native NSString NSURLKeysOfUnsetValuesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLNameKey", optional=true)
    public static native NSString NSURLNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
    public static native NSString NSURLLocalizedNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
    public static native NSString NSURLIsRegularFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
    public static native NSString NSURLIsDirectoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
    public static native NSString NSURLIsSymbolicLinkKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
    public static native NSString NSURLIsVolumeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
    public static native NSString NSURLIsPackageKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
    public static native NSString NSURLIsSystemImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
    public static native NSString NSURLIsUserImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
    public static native NSString NSURLIsHiddenKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
    public static native NSString NSURLHasHiddenExtensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
    public static native NSString NSURLCreationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
    public static native NSString NSURLContentAccessDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
    public static native NSString NSURLContentModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
    public static native NSString NSURLAttributeModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
    public static native NSString NSURLLinkCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
    public static native NSString NSURLParentDirectoryURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
    public static native NSString NSURLVolumeURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
    public static native NSString NSURLTypeIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
    public static native NSString NSURLLocalizedTypeDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
    public static native NSString NSURLLabelNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
    public static native NSString NSURLLabelColorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
    public static native NSString NSURLLocalizedLabelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
    public static native NSString NSURLEffectiveIconKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
    public static native NSString NSURLCustomIconKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
    public static native NSString NSURLFileResourceIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
    public static native NSString NSURLVolumeIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
    public static native NSString NSURLPreferredIOBlockSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
    public static native NSString NSURLIsReadableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
    public static native NSString NSURLIsWritableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
    public static native NSString NSURLIsExecutableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
    public static native NSString NSURLFileSecurityKey();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
    public static native NSString NSURLIsExcludedFromBackupKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSURLPathKey", optional=true)
    public static native NSString NSURLPathKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
    public static native NSString NSURLIsMountTriggerKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
    public static native NSString NSURLFileResourceTypeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
    public static native String NSURLFileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
    public static native String NSURLFileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
    public static native String NSURLFileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
    public static native String NSURLFileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
    public static native String NSURLFileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
    public static native String NSURLFileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
    public static native String NSURLFileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
    public static native String NSURLFileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
    public static native NSString NSURLFileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
    public static native NSString NSURLFileAllocatedSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
    public static native NSString NSURLTotalFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
    public static native NSString NSURLTotalFileAllocatedSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
    public static native NSString NSURLIsAliasFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native NSString NSURLVolumeLocalizedFormatDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
    public static native NSString NSURLVolumeTotalCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
    public static native NSString NSURLVolumeAvailableCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
    public static native NSString NSURLVolumeResourceCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native NSString NSURLVolumeSupportsPersistentIDsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native NSString NSURLVolumeSupportsSymbolicLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
    public static native NSString NSURLVolumeSupportsHardLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
    public static native NSString NSURLVolumeSupportsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
    public static native NSString NSURLVolumeIsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
    public static native NSString NSURLVolumeSupportsSparseFilesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
    public static native NSString NSURLVolumeSupportsZeroRunsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native NSString NSURLVolumeSupportsCaseSensitiveNamesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native NSString NSURLVolumeSupportsCasePreservedNamesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native NSString NSURLVolumeSupportsRootDirectoryDatesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native NSString NSURLVolumeSupportsVolumeSizesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
    public static native NSString NSURLVolumeSupportsRenamingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native NSString NSURLVolumeSupportsAdvisoryFileLockingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native NSString NSURLVolumeSupportsExtendedSecurityKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
    public static native NSString NSURLVolumeIsBrowsableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
    public static native NSString NSURLVolumeMaximumFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
    public static native NSString NSURLVolumeIsEjectableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
    public static native NSString NSURLVolumeIsRemovableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
    public static native NSString NSURLVolumeIsInternalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
    public static native NSString NSURLVolumeIsAutomountedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
    public static native NSString NSURLVolumeIsLocalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
    public static native NSString NSURLVolumeIsReadOnlyKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
    public static native NSString NSURLVolumeCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
    public static native NSString NSURLVolumeURLForRemountingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
    public static native NSString NSURLVolumeUUIDStringKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
    public static native NSString NSURLVolumeNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
    public static native NSString NSURLVolumeLocalizedNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUbiquitousItemKey", optional=true)
    public static native NSString NSURLIsUbiquitousItemKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString NSURLUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString NSURLUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString NSURLUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString NSURLUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString NSURLUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString NSURLUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString NSURLUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString NSURLUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString NSURLUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString NSURLUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native String NSURLUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native String NSURLUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native String NSURLUbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="NSURLCredentialStorageChangedNotification", optional=true)
    public static native NSString NSURLCredentialStorageChangedNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLCredentialStorageRemoveSynchronizableCredentials", optional=true)
    public static native NSString NSURLCredentialStorageRemoveSynchronizableCredentials();
    @GlobalValue(symbol="NSURLErrorDomain", optional=true)
    public static native NSString NSURLErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLErrorKey", optional=true)
    public static native NSString NSURLErrorFailingURLErrorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLStringErrorKey", optional=true)
    public static native NSString NSURLErrorFailingURLStringErrorKey();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 4.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSErrorFailingURLStringKey", optional=true)
    public static native NSString NSErrorFailingURLStringKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorFailingURLPeerTrustErrorKey", optional=true)
    public static native NSString NSURLErrorFailingURLPeerTrustErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorBackgroundTaskCancelledReasonKey", optional=true)
    public static native NSString NSURLErrorBackgroundTaskCancelledReasonKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTP", optional=true)
    public static native String NSURLProtectionSpaceHTTP();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPS", optional=true)
    public static native String NSURLProtectionSpaceHTTPS();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLProtectionSpaceFTP", optional=true)
    public static native String NSURLProtectionSpaceFTP();
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPProxy", optional=true)
    public static native String NSURLProtectionSpaceHTTPProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceHTTPSProxy", optional=true)
    public static native String NSURLProtectionSpaceHTTPSProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceFTPProxy", optional=true)
    public static native String NSURLProtectionSpaceFTPProxy();
    @GlobalValue(symbol="NSURLProtectionSpaceSOCKSProxy", optional=true)
    public static native String NSURLProtectionSpaceSOCKSProxy();
    @GlobalValue(symbol="NSURLAuthenticationMethodDefault", optional=true)
    public static native String NSURLAuthenticationMethodDefault();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPBasic", optional=true)
    public static native String NSURLAuthenticationMethodHTTPBasic();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPDigest", optional=true)
    public static native String NSURLAuthenticationMethodHTTPDigest();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTMLForm", optional=true)
    public static native String NSURLAuthenticationMethodHTMLForm();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNTLM", optional=true)
    public static native String NSURLAuthenticationMethodNTLM();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNegotiate", optional=true)
    public static native String NSURLAuthenticationMethodNegotiate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodClientCertificate", optional=true)
    public static native String NSURLAuthenticationMethodClientCertificate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodServerTrust", optional=true)
    public static native String NSURLAuthenticationMethodServerTrust();
    @GlobalValue(symbol="NSGlobalDomain", optional=true)
    public static native String NSGlobalDomain();
    @GlobalValue(symbol="NSArgumentDomain", optional=true)
    public static native String NSArgumentDomain();
    @GlobalValue(symbol="NSRegistrationDomain", optional=true)
    public static native String NSRegistrationDomain();
    @GlobalValue(symbol="NSUserDefaultsDidChangeNotification", optional=true)
    public static native NSString NSUserDefaultsDidChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSNegateBooleanTransformerName", optional=true)
    public static native String NSNegateBooleanTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNilTransformerName", optional=true)
    public static native String NSIsNilTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSIsNotNilTransformerName", optional=true)
    public static native String NSIsNotNilTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUnarchiveFromDataTransformerName", optional=true)
    public static native String NSUnarchiveFromDataTransformerName();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSKeyedUnarchiveFromDataTransformerName", optional=true)
    public static native String NSKeyedUnarchiveFromDataTransformerName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSXMLParserErrorDomain", optional=true)
    public static native NSString NSXMLParserErrorDomain();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeTokenType", optional=true)
    public static native NSString NSLinguisticTagSchemeTokenType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLexicalClass", optional=true)
    public static native NSString NSLinguisticTagSchemeLexicalClass();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameType", optional=true)
    public static native NSString NSLinguisticTagSchemeNameType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeNameTypeOrLexicalClass", optional=true)
    public static native NSString NSLinguisticTagSchemeNameTypeOrLexicalClass();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLemma", optional=true)
    public static native NSString NSLinguisticTagSchemeLemma();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeLanguage", optional=true)
    public static native NSString NSLinguisticTagSchemeLanguage();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSchemeScript", optional=true)
    public static native NSString NSLinguisticTagSchemeScript();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWord", optional=true)
    public static native NSString NSLinguisticTagWord();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPunctuation", optional=true)
    public static native NSString NSLinguisticTagPunctuation();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWhitespace", optional=true)
    public static native NSString NSLinguisticTagWhitespace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOther", optional=true)
    public static native NSString NSLinguisticTagOther();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNoun", optional=true)
    public static native NSString NSLinguisticTagNoun();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagVerb", optional=true)
    public static native NSString NSLinguisticTagVerb();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdjective", optional=true)
    public static native NSString NSLinguisticTagAdjective();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagAdverb", optional=true)
    public static native NSString NSLinguisticTagAdverb();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPronoun", optional=true)
    public static native NSString NSLinguisticTagPronoun();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDeterminer", optional=true)
    public static native NSString NSLinguisticTagDeterminer();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParticle", optional=true)
    public static native NSString NSLinguisticTagParticle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPreposition", optional=true)
    public static native NSString NSLinguisticTagPreposition();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagNumber", optional=true)
    public static native NSString NSLinguisticTagNumber();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagConjunction", optional=true)
    public static native NSString NSLinguisticTagConjunction();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagInterjection", optional=true)
    public static native NSString NSLinguisticTagInterjection();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagClassifier", optional=true)
    public static native NSString NSLinguisticTagClassifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagIdiom", optional=true)
    public static native NSString NSLinguisticTagIdiom();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWord", optional=true)
    public static native NSString NSLinguisticTagOtherWord();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagSentenceTerminator", optional=true)
    public static native NSString NSLinguisticTagSentenceTerminator();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenQuote", optional=true)
    public static native NSString NSLinguisticTagOpenQuote();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseQuote", optional=true)
    public static native NSString NSLinguisticTagCloseQuote();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOpenParenthesis", optional=true)
    public static native NSString NSLinguisticTagOpenParenthesis();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagCloseParenthesis", optional=true)
    public static native NSString NSLinguisticTagCloseParenthesis();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagWordJoiner", optional=true)
    public static native NSString NSLinguisticTagWordJoiner();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagDash", optional=true)
    public static native NSString NSLinguisticTagDash();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherPunctuation", optional=true)
    public static native NSString NSLinguisticTagOtherPunctuation();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagParagraphBreak", optional=true)
    public static native NSString NSLinguisticTagParagraphBreak();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOtherWhitespace", optional=true)
    public static native NSString NSLinguisticTagOtherWhitespace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPersonalName", optional=true)
    public static native NSString NSLinguisticTagPersonalName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagPlaceName", optional=true)
    public static native NSString NSLinguisticTagPlaceName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSLinguisticTagOrganizationName", optional=true)
    public static native NSString NSLinguisticTagOrganizationName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
    public static native NSString NSMetadataItemFSNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
    public static native NSString NSMetadataItemDisplayNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
    public static native NSString NSMetadataItemURLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
    public static native NSString NSMetadataItemPathKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
    public static native NSString NSMetadataItemFSSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
    public static native NSString NSMetadataItemFSCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
    public static native NSString NSMetadataItemFSContentChangeDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
    public static native NSString NSMetadataItemIsUbiquitousKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native NSString NSMetadataUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native NSString NSMetadataUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native NSString NSMetadataUbiquitousItemDownloadingStatusCurrent();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString NSMetadataUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidStartGatheringNotification", optional=true)
    public static native NSString NSMetadataQueryDidStartGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryGatheringProgressNotification", optional=true)
    public static native NSString NSMetadataQueryGatheringProgressNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidFinishGatheringNotification", optional=true)
    public static native NSString NSMetadataQueryDidFinishGatheringNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryDidUpdateNotification", optional=true)
    public static native NSString NSMetadataQueryDidUpdateNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateAddedItemsKey", optional=true)
    public static native NSString NSMetadataQueryUpdateAddedItemsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateChangedItemsKey", optional=true)
    public static native NSString NSMetadataQueryUpdateChangedItemsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUpdateRemovedItemsKey", optional=true)
    public static native NSString NSMetadataQueryUpdateRemovedItemsKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryResultContentRelevanceAttribute", optional=true)
    public static native NSString NSMetadataQueryResultContentRelevanceAttribute();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUbiquitousDocumentsScope", optional=true)
    public static native NSString NSMetadataQueryUbiquitousDocumentsScope();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSMetadataQueryUbiquitousDataScope", optional=true)
    public static native NSString NSMetadataQueryUbiquitousDataScope();
    @GlobalValue(symbol="NSNetServicesErrorCode", optional=true)
    public static native NSString NSNetServicesErrorCode();
    @GlobalValue(symbol="NSNetServicesErrorDomain", optional=true)
    public static native NSString NSNetServicesErrorDomain();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressEstimatedTimeRemainingKey", optional=true)
    public static native NSString NSProgressEstimatedTimeRemainingKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressThroughputKey", optional=true)
    public static native NSString NSProgressThroughputKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressKindFile", optional=true)
    public static native String NSProgressKindFile();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindKey", optional=true)
    public static native NSString NSProgressFileOperationKindKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindDownloading", optional=true)
    public static native NSString NSProgressFileOperationKindDownloading();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindDecompressingAfterDownloading", optional=true)
    public static native NSString NSProgressFileOperationKindDecompressingAfterDownloading();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindReceiving", optional=true)
    public static native NSString NSProgressFileOperationKindReceiving();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileOperationKindCopying", optional=true)
    public static native NSString NSProgressFileOperationKindCopying();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileURLKey", optional=true)
    public static native NSString NSProgressFileURLKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileTotalCountKey", optional=true)
    public static native NSString NSProgressFileTotalCountKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSProgressFileCompletedCountKey", optional=true)
    public static native NSString NSProgressFileCompletedCountKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreDidChangeExternallyNotification", optional=true)
    public static native NSString NSUbiquitousKeyValueStoreDidChangeExternallyNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangeReasonKey", optional=true)
    public static native NSString NSUbiquitousKeyValueStoreChangeReasonKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUbiquitousKeyValueStoreChangedKeysKey", optional=true)
    public static native NSString NSUbiquitousKeyValueStoreChangedKeysKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerGroupIsDiscardableKey", optional=true)
    public static native NSString NSUndoManagerGroupIsDiscardableKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerCheckpointNotification", optional=true)
    public static native NSString NSUndoManagerCheckpointNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillUndoChangeNotification", optional=true)
    public static native NSString NSUndoManagerWillUndoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillRedoChangeNotification", optional=true)
    public static native NSString NSUndoManagerWillRedoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidUndoChangeNotification", optional=true)
    public static native NSString NSUndoManagerDidUndoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidRedoChangeNotification", optional=true)
    public static native NSString NSUndoManagerDidRedoChangeNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidOpenUndoGroupNotification", optional=true)
    public static native NSString NSUndoManagerDidOpenUndoGroupNotification();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerWillCloseUndoGroupNotification", optional=true)
    public static native NSString NSUndoManagerWillCloseUndoGroupNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSUndoManagerDidCloseUndoGroupNotification", optional=true)
    public static native NSString NSUndoManagerDidCloseUndoGroupNotification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionTransferSizeUnknown", optional=true)
    public static native long NSURLSessionTransferSizeUnknown();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionDownloadTaskResumeData", optional=true)
    public static native NSString NSURLSessionDownloadTaskResumeData();
    /*</methods>*/
}
