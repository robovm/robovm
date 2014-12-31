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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreMedia/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreMedia.class); }/*</bind>*/
    /*<constants>*/
    public static final int PersistentTrackIDInvalid = 0;
    public static final int Constant__kCMAttachmentMode_ShouldNotPropagate = 0;
    public static final int Constant__kCMAttachmentMode_ShouldPropagate = 1;
    public static final int Constant__kCMFormatDescriptionBridgeError_InvalidParameter = -12712;
    public static final int Constant__kCMFormatDescriptionBridgeError_AllocationFailed = -12713;
    public static final int Constant__kCMFormatDescriptionBridgeError_InvalidSerializedSampleDescription = -12714;
    public static final int Constant__kCMFormatDescriptionBridgeError_InvalidFormatDescription = -12715;
    public static final int Constant__kCMFormatDescriptionBridgeError_IncompatibleFormatDescription = -12716;
    public static final int Constant__kCMFormatDescriptionBridgeError_UnsupportedSampleDescriptionFlavor = -12717;
    public static final int Constant__kCMFormatDescriptionBridgeError_InvalidSlice = -12719;
    public static final int Constant__kCMMetadataIdentifierError_AllocationFailed = -16300;
    public static final int Constant__kCMMetadataIdentifierError_RequiredParameterMissing = -16301;
    public static final int Constant__kCMMetadataIdentifierError_BadKey = -16302;
    public static final int Constant__kCMMetadataIdentifierError_BadKeyLength = -16303;
    public static final int Constant__kCMMetadataIdentifierError_BadKeyType = -16304;
    public static final int Constant__kCMMetadataIdentifierError_BadNumberKey = -16305;
    public static final int Constant__kCMMetadataIdentifierError_BadKeySpace = -16306;
    public static final int Constant__kCMMetadataIdentifierError_BadIdentifier = -16307;
    public static final int Constant__kCMMetadataIdentifierError_NoKeyValueAvailable = -16308;
    public static final int Constant__kCMMetadataDataTypeRegistryError_AllocationFailed = -16310;
    public static final int Constant__kCMMetadataDataTypeRegistryError_RequiredParameterMissing = -16311;
    public static final int Constant__kCMMetadataDataTypeRegistryError_BadDataTypeIdentifier = -16312;
    public static final int Constant__kCMMetadataDataTypeRegistryError_DataTypeAlreadyRegistered = -16313;
    public static final int Constant__kCMMetadataDataTypeRegistryError_RequiresConformingBaseType = -16314;
    public static final int Constant__kCMMetadataDataTypeRegistryError_MultipleConformingBaseTypes = -16315;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeInvalid", optional=true)
    public static native @ByVal CMTime CMTimeInvalid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeIndefinite", optional=true)
    public static native @ByVal CMTime CMTimeIndefinite();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimePositiveInfinity", optional=true)
    public static native @ByVal CMTime CMTimePositiveInfinity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeNegativeInfinity", optional=true)
    public static native @ByVal CMTime CMTimeNegativeInfinity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeZero", optional=true)
    public static native @ByVal CMTime CMTimeZero();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeValueKey", optional=true)
    public static native String CMTimeValueKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeScaleKey", optional=true)
    public static native String CMTimeScaleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeEpochKey", optional=true)
    public static native String CMTimeEpochKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeFlagsKey", optional=true)
    public static native String CMTimeFlagsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeRangeZero", optional=true)
    public static native @ByVal CMTimeRange CMTimeRangeZero();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeRangeInvalid", optional=true)
    public static native @ByVal CMTimeRange CMTimeRangeInvalid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeRangeStartKey", optional=true)
    public static native String CMTimeRangeStartKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeRangeDurationKey", optional=true)
    public static native String CMTimeRangeDurationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_OriginalCompressionSettings", optional=true)
    public static native String CMFormatDescriptionExtensionOriginalCompressionSettings();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_SampleDescriptionExtensionAtoms", optional=true)
    public static native String CMFormatDescriptionExtensionSampleDescriptionExtensionAtoms();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_VerbatimSampleDescription", optional=true)
    public static native String CMFormatDescriptionExtensionVerbatimSampleDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_VerbatimISOSampleEntry", optional=true)
    public static native String CMFormatDescriptionExtensionVerbatimISOSampleEntry();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_FormatName", optional=true)
    public static native String CMFormatDescriptionExtensionFormatName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_Depth", optional=true)
    public static native String CMFormatDescriptionExtensionDepth();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureWidthRational", optional=true)
    public static native String CMFormatDescriptionKeyCleanApertureWidthRational();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureHeightRational", optional=true)
    public static native String CMFormatDescriptionKeyCleanApertureHeightRational();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureHorizontalOffsetRational", optional=true)
    public static native String CMFormatDescriptionKeyCleanApertureHorizontalOffsetRational();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionKey_CleanApertureVerticalOffsetRational", optional=true)
    public static native String CMFormatDescriptionKeyCleanApertureVerticalOffsetRational();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionColorPrimaries_P22", optional=true)
    public static native String CMFormatDescriptionColorPrimariesP22();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_FullRangeVideo", optional=true)
    public static native String CMFormatDescriptionExtensionFullRangeVideo();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_ICCProfile", optional=true)
    public static native String CMFormatDescriptionExtensionICCProfile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_BytesPerRow", optional=true)
    public static native String CMFormatDescriptionExtensionBytesPerRow();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionConformsToMPEG2VideoProfile", optional=true)
    public static native String CMMetadataFormatDescriptionConformsToMPEG2VideoProfile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_TemporalQuality", optional=true)
    public static native String CMFormatDescriptionExtensionTemporalQuality();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_SpatialQuality", optional=true)
    public static native String CMFormatDescriptionExtensionSpatialQuality();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_Version", optional=true)
    public static native String CMFormatDescriptionExtensionVersion();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_RevisionLevel", optional=true)
    public static native String CMFormatDescriptionExtensionRevisionLevel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtension_Vendor", optional=true)
    public static native String CMFormatDescriptionExtensionVendor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionVendor_Apple", optional=true)
    public static native String CMFormatDescriptionVendorApple();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DisplayFlags", optional=true)
    public static native String CMTextFormatDescriptionExtensionDisplayFlags();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_BackgroundColor", optional=true)
    public static native String CMTextFormatDescriptionExtensionBackgroundColor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionColor_Red", optional=true)
    public static native String CMTextFormatDescriptionColorRed();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionColor_Green", optional=true)
    public static native String CMTextFormatDescriptionColorGreen();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionColor_Blue", optional=true)
    public static native String CMTextFormatDescriptionColorBlue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionColor_Alpha", optional=true)
    public static native String CMTextFormatDescriptionColorAlpha();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultTextBox", optional=true)
    public static native String CMTextFormatDescriptionExtensionDefaultTextBox();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionRect_Top", optional=true)
    public static native String CMTextFormatDescriptionRectTop();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionRect_Left", optional=true)
    public static native String CMTextFormatDescriptionRectLeft();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionRect_Bottom", optional=true)
    public static native String CMTextFormatDescriptionRectBottom();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionRect_Right", optional=true)
    public static native String CMTextFormatDescriptionRectRight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultStyle", optional=true)
    public static native String CMTextFormatDescriptionExtensionDefaultStyle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_StartChar", optional=true)
    public static native String CMTextFormatDescriptionStyleStartChar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Font", optional=true)
    public static native String CMTextFormatDescriptionStyleFont();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_FontFace", optional=true)
    public static native String CMTextFormatDescriptionStyleFontFace();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_ForegroundColor", optional=true)
    public static native String CMTextFormatDescriptionStyleForegroundColor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_FontSize", optional=true)
    public static native String CMTextFormatDescriptionStyleFontSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_HorizontalJustification", optional=true)
    public static native String CMTextFormatDescriptionExtensionHorizontalJustification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_VerticalJustification", optional=true)
    public static native String CMTextFormatDescriptionExtensionVerticalJustification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_EndChar", optional=true)
    public static native String CMTextFormatDescriptionStyleEndChar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_FontTable", optional=true)
    public static native String CMTextFormatDescriptionExtensionFontTable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_TextJustification", optional=true)
    public static native String CMTextFormatDescriptionExtensionTextJustification();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Height", optional=true)
    public static native String CMTextFormatDescriptionStyleHeight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Ascent", optional=true)
    public static native String CMTextFormatDescriptionStyleAscent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultFontName", optional=true)
    public static native String CMTextFormatDescriptionExtensionDefaultFontName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeCodeFormatDescriptionExtension_SourceReferenceName", optional=true)
    public static native String CMTimeCodeFormatDescriptionExtension_SourceReferenceName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeCodeFormatDescriptionKey_Value", optional=true)
    public static native String CMTimeCodeFormatDescriptionKey_Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimeCodeFormatDescriptionKey_LangCode", optional=true)
    public static native String CMTimeCodeFormatDescriptionKey_LangCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMFormatDescriptionExtensionKey_MetadataKeyTable", optional=true)
    public static native String CMFormatDescriptionExtensionKeyMetadataKeyTable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_Namespace", optional=true)
    public static native String CMMetadataFormatDescriptionKeyNamespace();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_Value", optional=true)
    public static native String CMMetadataFormatDescriptionKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_LocalID", optional=true)
    public static native String CMMetadataFormatDescriptionKeyLocalID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_DataType", optional=true)
    public static native String CMMetadataFormatDescriptionKeyDataType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_DataTypeNamespace", optional=true)
    public static native String CMMetadataFormatDescriptionKeyDataTypeNamespace();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_ConformingDataTypes", optional=true)
    public static native String CMMetadataFormatDescriptionKeyConformingDataTypes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionKey_LanguageTag", optional=true)
    public static native String CMMetadataFormatDescriptionKeyLanguageTag();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_Identifier", optional=true)
    public static native String Value__kCMMetadataFormatDescriptionMetadataSpecificationKey_Identifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_DataType", optional=true)
    public static native String Value__kCMMetadataFormatDescriptionMetadataSpecificationKey_DataType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataFormatDescriptionMetadataSpecificationKey_ExtendedLanguageTag", optional=true)
    public static native String Value__kCMMetadataFormatDescriptionMetadataSpecificationKey_ExtendedLanguageTag();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMImageDescriptionFlavor_QuickTimeMovie", optional=true)
    public static native String Value__kCMImageDescriptionFlavor_QuickTimeMovie();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMImageDescriptionFlavor_ISOFamily", optional=true)
    public static native String Value__kCMImageDescriptionFlavor_ISOFamily();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMImageDescriptionFlavor_3GPFamily", optional=true)
    public static native String Value__kCMImageDescriptionFlavor_3GPFamily();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSoundDescriptionFlavor_QuickTimeMovie", optional=true)
    public static native String Value__kCMSoundDescriptionFlavor_QuickTimeMovie();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSoundDescriptionFlavor_QuickTimeMovieV2", optional=true)
    public static native String Value__kCMSoundDescriptionFlavor_QuickTimeMovieV2();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSoundDescriptionFlavor_ISOFamily", optional=true)
    public static native String Value__kCMSoundDescriptionFlavor_ISOFamily();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSoundDescriptionFlavor_3GPFamily", optional=true)
    public static native String Value__kCMSoundDescriptionFlavor_3GPFamily();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimingInfoInvalid", optional=true)
    public static native @ByVal CMSampleTimingInfo CMSampleTimingInfoTimingInfoInvalid();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotification_DataBecameReady", optional=true)
    public static native String CMSampleBufferNotificationDataBecameReady();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotification_DataFailed", optional=true)
    public static native String CMSampleBufferNotificationDataFailed();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferNotificationParameter_OSStatus", optional=true)
    public static native String Value__kCMSampleBufferNotificationParameter_OSStatus();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_InhibitOutputUntil", optional=true)
    public static native String CMSampleBufferConduitNotificationInhibitOutputUntil();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_ResumeTag", optional=true)
    public static native String CMSampleBufferConduitNotificationParameterResumeTag();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_ResetOutput", optional=true)
    public static native String CMSampleBufferConduitNotificationResetOutput();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotification_UpcomingOutputPTSRangeChanged", optional=true)
    public static native String CMSampleBufferConduitNotificationUpcomingOutputPTSRangeChanged();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_UpcomingOutputPTSRangeMayOverlapQueuedOutputPTSRange", optional=true)
    public static native String CMSampleBufferConduitNotificationParameterUpcomingOutputPTSRangeMayOverlapQueuedOutputPTSRange();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_MinUpcomingOutputPTS", optional=true)
    public static native String CMSampleBufferConduitNotificationParameterMinUpcomingOutputPTS();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConduitNotificationParameter_MaxUpcomingOutputPTS", optional=true)
    public static native String CMSampleBufferConduitNotificationParameterMaxUpcomingOutputPTS();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferConsumerNotification_BufferConsumed", optional=true)
    public static native String CMSampleBufferConsumerNotificationBufferConsumed();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_NotSync", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyNotSync();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_PartialSync", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyPartialSync();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_HasRedundantCoding", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyHasRedundantCoding();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_IsDependedOnByOthers", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyIsDependedOnByOthers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_DependsOnOthers", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyDependsOnOthers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_EarlierDisplayTimesAllowed", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyEarlierDisplayTimesAllowed();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_DisplayImmediately", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyDisplayImmediately();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleAttachmentKey_DoNotDisplay", optional=true)
    public static native String CMSampleBufferSampleAttachmentKeyDoNotDisplay();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ResetDecoderBeforeDecoding", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyResetDecoderBeforeDecoding();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DrainAfterDecoding", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyDrainAfterDecoding();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_PostNotificationWhenConsumed", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyPostNotificationWhenConsumed();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ResumeOutput", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyResumeOutput();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TransitionID", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyTransitionID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TrimDurationAtStart", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyTrimDurationAtStart();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_TrimDurationAtEnd", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyTrimDurationAtEnd();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SpeedMultiplier", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeySpeedMultiplier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_Reverse", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyReverse();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_FillDiscontinuitiesWithSilence", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyFillDiscontinuitiesWithSilence();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_EmptyMedia", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyEmptyMedia();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_PermanentEmptyMedia", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyPermanentEmptyMedia();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DisplayEmptyMediaImmediately", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyDisplayEmptyMediaImmediately();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_EndsPreviousSampleDuration", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyEndsPreviousSampleDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SampleReferenceURL", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeySampleReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_SampleReferenceByteOffset", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeySampleReferenceByteOffset();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_GradualDecoderRefresh", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyGradualDecoderRefresh();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DroppedFrameReason", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyDroppedFrameReason();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferDroppedFrameReason_FrameWasLate", optional=true)
    public static native String CMSampleBufferDroppedFrameReasonFrameWasLate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferDroppedFrameReason_OutOfBuffers", optional=true)
    public static native String CMSampleBufferDroppedFrameReasonOutOfBuffers();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferDroppedFrameReason_Discontinuity", optional=true)
    public static native String CMSampleBufferDroppedFrameReasonDiscontinuity();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_DroppedFrameReasonInfo", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyDroppedFrameReasonInfo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferDroppedFrameReasonInfo_CameraModeSwitch", optional=true)
    public static native String CMSampleBufferDroppedFrameReasonInfoCameraModeSwitch();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMSampleBufferAttachmentKey_ForceKeyFrame", optional=true)
    public static native String CMSampleBufferSampleBufferAttachmentKeyForceKeyFrame();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMMemoryPoolOption_AgeOutPeriod", optional=true)
    public static native String CMMemoryPoolOptionAgeOutPeriod();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTimebaseNotification_EffectiveRateChanged", optional=true)
    public static native String CMTimebaseNotificationEffectiveRateChanged();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTimebaseNotification_TimeJumped", optional=true)
    public static native String CMTimebaseNotificationTimeJumped();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTimebaseNotificationKey_EventTime", optional=true)
    public static native String CMTimebaseNotificationKeyEventTime();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_ForegroundColorARGB", optional=true)
    public static native String CMTextMarkupAttributeForegroundColorARGB();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_BackgroundColorARGB", optional=true)
    public static native String CMTextMarkupAttributeBackgroundColorARGB();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_CharacterBackgroundColorARGB", optional=true)
    public static native String CMTextMarkupAttributeCharacterBackgroundColorARGB();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_BoldStyle", optional=true)
    public static native String CMTextMarkupAttributeBoldStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_ItalicStyle", optional=true)
    public static native String CMTextMarkupAttributeItalicStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_UnderlineStyle", optional=true)
    public static native String CMTextMarkupAttributeUnderlineStyle();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_FontFamilyName", optional=true)
    public static native String CMTextMarkupAttributeFontFamilyName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_GenericFontFamilyName", optional=true)
    public static native String CMTextMarkupAttributeGenericFontFamilyName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Default", optional=true)
    public static native String CMTextMarkupGenericFontNameDefault();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Serif", optional=true)
    public static native String CMTextMarkupGenericFontNameSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_SansSerif", optional=true)
    public static native String CMTextMarkupGenericFontNameSansSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Monospace", optional=true)
    public static native String CMTextMarkupGenericFontNameMonospace();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSerif", optional=true)
    public static native String CMTextMarkupGenericFontNameProportionalSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_ProportionalSansSerif", optional=true)
    public static native String CMTextMarkupGenericFontNameProportionalSansSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSerif", optional=true)
    public static native String CMTextMarkupGenericFontNameMonospaceSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_MonospaceSansSerif", optional=true)
    public static native String CMTextMarkupGenericFontNameMonospaceSansSerif();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Casual", optional=true)
    public static native String CMTextMarkupGenericFontNameCasual();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Cursive", optional=true)
    public static native String CMTextMarkupGenericFontNameCursive();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_Fantasy", optional=true)
    public static native String CMTextMarkupGenericFontNameFantasy();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupGenericFontName_SmallCapital", optional=true)
    public static native String CMTextMarkupGenericFontNameSmallCapital();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_BaseFontSizePercentageRelativeToVideoHeight", optional=true)
    public static native String CMTextMarkupAttributeBaseFontSizePercentageRelativeToVideoHeight();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_RelativeFontSize", optional=true)
    public static native String CMTextMarkupAttributeRelativeFontSize();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_VerticalLayout", optional=true)
    public static native String CMTextMarkupAttributeVerticalLayout();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextVerticalLayout_LeftToRight", optional=true)
    public static native String CMTextMarkupVerticalLayoutLeftToRight();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextVerticalLayout_RightToLeft", optional=true)
    public static native String CMTextMarkupVerticalLayoutRightToLeft();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_Alignment", optional=true)
    public static native String CMTextMarkupAttributeAlignment();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Start", optional=true)
    public static native String CMTextMarkupAlignmentTypeStart();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Middle", optional=true)
    public static native String CMTextMarkupAlignmentTypeMiddle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_End", optional=true)
    public static native String CMTextMarkupAlignmentTypeEnd();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Left", optional=true)
    public static native String CMTextMarkupAlignmentTypeLeft();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAlignmentType_Right", optional=true)
    public static native String CMTextMarkupAlignmentTypeRight();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_TextPositionPercentageRelativeToWritingDirection", optional=true)
    public static native String CMTextMarkupAttributeTextPositionPercentageRelativeToWritingDirection();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_OrthogonalLinePositionPercentageRelativeToWritingDirection", optional=true)
    public static native String CMTextMarkupAttributeOrthogonalLinePositionPercentageRelativeToWritingDirection();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_WritingDirectionSizePercentage", optional=true)
    public static native String CMTextMarkupAttributeWritingDirectionSizePercentage();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupAttribute_CharacterEdgeStyle", optional=true)
    public static native String CMTextMarkupAttributeCharacterEdgeStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_None", optional=true)
    public static native String CMTextMarkupCharacterEdgeStyleNone();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Raised", optional=true)
    public static native String CMTextMarkupCharacterEdgeStyleRaised();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Depressed", optional=true)
    public static native String CMTextMarkupCharacterEdgeStyleDepressed();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_Uniform", optional=true)
    public static native String CMTextMarkupCharacterEdgeStyleUniform();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCMTextMarkupCharacterEdgeStyle_DropShadow", optional=true)
    public static native String CMTextMarkupCharacterEdgeStyleDropShadow();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeUserData", optional=true)
    public static native String Value__kCMMetadataKeySpace_QuickTimeUserData();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_ISOUserData", optional=true)
    public static native String Value__kCMMetadataKeySpace_ISOUserData();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_QuickTimeMetadata", optional=true)
    public static native String Value__kCMMetadataKeySpace_QuickTimeMetadata();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_iTunes", optional=true)
    public static native String Value__kCMMetadataKeySpace_iTunes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_ID3", optional=true)
    public static native String Value__kCMMetadataKeySpace_ID3();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataKeySpace_Icy", optional=true)
    public static native String Value__kCMMetadataKeySpace_Icy();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataLocation_ISO6709", optional=true)
    public static native String Value__kCMMetadataIdentifier_QuickTimeMetadataLocation_ISO6709();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataDirection_Facing", optional=true)
    public static native String Value__kCMMetadataIdentifier_QuickTimeMetadataDirection_Facing();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataIdentifier_QuickTimeMetadataPreferredAffineTransform", optional=true)
    public static native String Value__kCMMetadataIdentifier_QuickTimeMetadataPreferredAffineTransform();
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianImageDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianImageDescriptionToHost(BytePtr imageDescriptionData, @MachineSizedUInt long imageDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianImageDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianImageDescriptionToBig(BytePtr imageDescriptionData, @MachineSizedUInt long imageDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMDoesBigEndianSoundDescriptionRequireLegacyCBRSampleTableLayout", optional=true)
    public static native boolean function__CMDoesBigEndianSoundDescriptionRequireLegacyCBRSampleTableLayout(CMBlockBuffer soundDescriptionBlockBuffer, String soundDescriptionFlavor);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianSoundDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianSoundDescriptionToHost(BytePtr soundDescriptionData, @MachineSizedUInt long soundDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianSoundDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianSoundDescriptionToBig(BytePtr soundDescriptionData, @MachineSizedUInt long soundDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianTextDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianTextDescriptionToHost(BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianTextDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianTextDescriptionToBig(BytePtr textDescriptionData, @MachineSizedUInt long textDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionData", optional=true)
    public static native int function__CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionData(CFAllocator allocator, BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize, String closedCaptionDescriptionFlavor, CMFormatDescription.CMFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    public static native int function__CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionBlockBuffer(CFAllocator allocator, CMBlockBuffer closedCaptionDescriptionBlockBuffer, String closedCaptionDescriptionFlavor, CMFormatDescription.CMFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCopyAsBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    public static native int function__CMClosedCaptionFormatDescriptionCopyAsBigEndianClosedCaptionDescriptionBlockBuffer(CFAllocator allocator, CMFormatDescription closedCaptionFormatDescription, String closedCaptionDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr closedCaptionDescriptionBlockBufferOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianClosedCaptionDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianClosedCaptionDescriptionToHost(BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianClosedCaptionDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianClosedCaptionDescriptionToBig(BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianTimeCodeDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianTimeCodeDescriptionToHost(BytePtr timeCodeDescriptionData, @MachineSizedUInt long timeCodeDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianTimeCodeDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianTimeCodeDescriptionToBig(BytePtr timeCodeDescriptionData, @MachineSizedUInt long timeCodeDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapBigEndianMetadataDescriptionToHost", optional=true)
    public static native int function__CMSwapBigEndianMetadataDescriptionToHost(BytePtr metadataDescriptionData, @MachineSizedUInt long metadataDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMSwapHostEndianMetadataDescriptionToBig", optional=true)
    public static native int function__CMSwapHostEndianMetadataDescriptionToBig(BytePtr metadataDescriptionData, @MachineSizedUInt long metadataDescriptionSize);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMAudioSampleBufferCreateReadyWithPacketDescriptions", optional=true)
    public static native int function__CMAudioSampleBufferCreateReadyWithPacketDescriptions(CFAllocator allocator, CMBlockBuffer dataBuffer, CMFormatDescription formatDescription, @MachineSizedSInt long numSamples, @ByVal CMTime sbufPTS, AudioStreamPacketDescription packetDescriptions, CMSampleBuffer.CMSampleBufferPtr sBufOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateKeyFromIdentifier", optional=true)
    public static native int function__CMMetadataCreateKeyFromIdentifier(CFAllocator allocator, String identifier, CFType.CFTypePtr keyOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataCreateKeyFromIdentifierAsCFData", optional=true)
    public static native int function__CMMetadataCreateKeyFromIdentifierAsCFData(CFAllocator allocator, String identifier, NSData keyOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryRegisterDataType", optional=true)
    public static native int function__CMMetadataDataTypeRegistryRegisterDataType(String dataType, String description, NSArray<?> conformingDataTypes);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeIsRegistered", optional=true)
    public static native boolean function__CMMetadataDataTypeRegistryDataTypeIsRegistered(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetDataTypeDescription", optional=true)
    public static native String function__CMMetadataDataTypeRegistryGetDataTypeDescription(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetConformingDataTypes", optional=true)
    public static native NSArray<?> function__CMMetadataDataTypeRegistryGetConformingDataTypes(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeConformsToDataType", optional=true)
    public static native boolean function__CMMetadataDataTypeRegistryDataTypeConformsToDataType(String dataType, String conformsToDataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetBaseDataTypes", optional=true)
    public static native NSArray<?> function__CMMetadataDataTypeRegistryGetBaseDataTypes();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryDataTypeIsBaseDataType", optional=true)
    public static native boolean function__CMMetadataDataTypeRegistryDataTypeIsBaseDataType(String dataType);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataDataTypeRegistryGetBaseDataTypeForConformingDataType", optional=true)
    public static native String function__CMMetadataDataTypeRegistryGetBaseDataTypeForConformingDataType(String dataType);
    /*</methods>*/
}
