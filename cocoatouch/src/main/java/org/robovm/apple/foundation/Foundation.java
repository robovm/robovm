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
    public static final int FoundationVersionNumber10_9 = 1056;
    public static final int FoundationVersionNumber10_9_1 = 1056;
    public static final double FoundationVersionNumber10_9_2 = 1056.13;
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
    public static final double FoundationVersionNumber_iOS_6_0 = 992.00;
    public static final double FoundationVersionNumber_iOS_6_1 = 993.00;
    public static final double FoundationVersionNumber_iOS_7_0 = 1047.20;
    public static final double FoundationVersionNumber_iOS_7_1 = 1047.25;
    public static final double TimeIntervalSince1970 = 978307200.0;
    public static final int DecimalMaxSize = 8;
    public static final int DecimalNoScale = 32767;
    public static final int FoundationVersionWithFileManagerResourceForkSupport = 412;
    public static final int URLResponseUnknownLength = -1;
    public static final int NotFound = 2147483647;
    public static final int DateComponentUndefined = 2147483647;
    public static final int OpenStepUnicodeReservedBase = 62464;
    public static final int OperationQueueDefaultMaxConcurrentOperationCount = -1;
    public static final long ItemProviderUnknownError = -1L;
    public static final long ItemProviderItemUnavailableError = -1000L;
    public static final long ItemProviderUnexpectedValueClassError = -1100L;
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
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionSuggestedEncodingsKey", optional=true)
    public static native String StringEncodingDetectionSuggestedEncodingsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionDisallowedEncodingsKey", optional=true)
    public static native String StringEncodingDetectionDisallowedEncodingsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionUseOnlySuggestedEncodingsKey", optional=true)
    public static native String StringEncodingDetectionUseOnlySuggestedEncodingsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionAllowLossyKey", optional=true)
    public static native String StringEncodingDetectionAllowLossyKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionFromWindowsKey", optional=true)
    public static native String StringEncodingDetectionFromWindowsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionLossySubstitutionKey", optional=true)
    public static native String StringEncodingDetectionLossySubstitutionKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSStringEncodingDetectionLikelyLanguageKey", optional=true)
    public static native String StringEncodingDetectionLikelyLanguageKey();
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
    public static native String KeyValueChangeKindKey();
    @GlobalValue(symbol="NSKeyValueChangeNewKey", optional=true)
    public static native String KeyValueChangeNewKey();
    @GlobalValue(symbol="NSKeyValueChangeOldKey", optional=true)
    public static native String KeyValueChangeOldKey();
    @GlobalValue(symbol="NSKeyValueChangeIndexesKey", optional=true)
    public static native String KeyValueChangeIndexesKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSKeyValueChangeNotificationIsPriorKey", optional=true)
    public static native String KeyValueChangeNotificationIsPriorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSKeyedArchiveRootObjectKey", optional=true)
    public static native String KeyedArchiveRootObjectKey();
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
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
    public static native NSString URLKeysOfUnsetValuesKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSThumbnail1024x1024SizeKey", optional=true)
    public static native String Thumbnail1024x1024SizeKey();
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
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLErrorBackgroundTaskCancelledReasonKey", optional=true)
    public static native NSString URLErrorBackgroundTaskCancelledReasonKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSXMLParserErrorDomain", optional=true)
    public static native NSString XMLParserErrorDomain();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionItemsAndErrorsKey", optional=true)
    public static native String ExtensionItemsAndErrorsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionJavaScriptPreprocessingResultsKey", optional=true)
    public static native String ExtensionJavaScriptPreprocessingResultsKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionJavaScriptFinalizeArgumentKey", optional=true)
    public static native String ExtensionJavaScriptFinalizeArgumentKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSItemProviderErrorDomain", optional=true)
    public static native NSString ItemProviderErrorDomain();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionItemAttributedTitleKey", optional=true)
    public static native String ExtensionItemAttributedTitleKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionItemAttributedContentTextKey", optional=true)
    public static native String ExtensionItemAttributedContentTextKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSExtensionItemAttachmentsKey", optional=true)
    public static native String ExtensionItemAttachmentsKey();
    @GlobalValue(symbol="NSNetServicesErrorCode", optional=true)
    public static native NSString NetServicesErrorCode();
    @GlobalValue(symbol="NSNetServicesErrorDomain", optional=true)
    public static native NSString NetServicesErrorDomain();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionTaskPriorityDefault", optional=true)
    public static native float URLSessionTaskPriorityDefault();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionTaskPriorityLow", optional=true)
    public static native float URLSessionTaskPriorityLow();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionTaskPriorityHigh", optional=true)
    public static native float URLSessionTaskPriorityHigh();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLSessionDownloadTaskResumeData", optional=true)
    public static native String URLSessionDownloadTaskResumeData();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSUserActivityTypeBrowsingWeb", optional=true)
    public static native String UserActivityTypeBrowsingWeb();
    /*</methods>*/
}
