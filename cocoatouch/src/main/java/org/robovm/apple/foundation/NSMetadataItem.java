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
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSMetadataItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSMetadataItemPtr extends Ptr<NSMetadataItem, NSMetadataItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSMetadataItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSMetadataItem() {}
    protected NSMetadataItem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSMetadataItemFSNameKey", optional=true)
    public static native NSString KeyFSName();
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey", optional=true)
    public static native NSString KeyDisplayName();
    @GlobalValue(symbol="NSMetadataItemURLKey", optional=true)
    public static native NSString KeyURL();
    @GlobalValue(symbol="NSMetadataItemPathKey", optional=true)
    public static native NSString KeyPath();
    @GlobalValue(symbol="NSMetadataItemFSSizeKey", optional=true)
    public static native NSString KeyFSSize();
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey", optional=true)
    public static native NSString KeyFSCreationDate();
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey", optional=true)
    public static native NSString KeyFSContentChangeDate();
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey", optional=true)
    public static native NSString KeyIsUbiquitous();
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString KeyUbiquitousHasUnresolvedConflicts();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousIsDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString KeyUbiquitousDownloadingStatus();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native NSString UbiquitousDownloadingStatusNotDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native NSString UbiquitousDownloadingStatusDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native NSString UbiquitousDownloadingStatusCurrent();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString KeyUbiquitousIsDownloading();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString KeyUbiquitousIsUploaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString KeyUbiquitousIsUploading();
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousPercentDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString KeyUbiquitousPercentUploaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousDownloadingError();
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousUploadingError();
    @GlobalValue(symbol="NSMetadataItemAttributeChangeDateKey", optional=true)
    public static native NSString KeyAttributeChangeDate();
    @GlobalValue(symbol="NSMetadataItemContentTypeKey", optional=true)
    public static native NSString KeyContentType();
    @GlobalValue(symbol="NSMetadataItemContentTypeTreeKey", optional=true)
    public static native NSString KeyContentTypeTree();
    @GlobalValue(symbol="NSMetadataItemKeywordsKey", optional=true)
    public static native NSString KeyKeywords();
    @GlobalValue(symbol="NSMetadataItemTitleKey", optional=true)
    public static native NSString KeyTitle();
    @GlobalValue(symbol="NSMetadataItemAuthorsKey", optional=true)
    public static native NSString KeyAuthors();
    @GlobalValue(symbol="NSMetadataItemEditorsKey", optional=true)
    public static native NSString KeyEditors();
    @GlobalValue(symbol="NSMetadataItemParticipantsKey", optional=true)
    public static native NSString KeyParticipants();
    @GlobalValue(symbol="NSMetadataItemProjectsKey", optional=true)
    public static native NSString KeyProjects();
    @GlobalValue(symbol="NSMetadataItemDownloadedDateKey", optional=true)
    public static native NSString KeyDownloadedDate();
    @GlobalValue(symbol="NSMetadataItemWhereFromsKey", optional=true)
    public static native NSString KeyWhereFroms();
    @GlobalValue(symbol="NSMetadataItemCommentKey", optional=true)
    public static native NSString KeyComment();
    @GlobalValue(symbol="NSMetadataItemCopyrightKey", optional=true)
    public static native NSString KeyCopyright();
    @GlobalValue(symbol="NSMetadataItemLastUsedDateKey", optional=true)
    public static native NSString KeyLastUsedDate();
    @GlobalValue(symbol="NSMetadataItemContentCreationDateKey", optional=true)
    public static native NSString KeyContentCreationDate();
    @GlobalValue(symbol="NSMetadataItemContentModificationDateKey", optional=true)
    public static native NSString KeyContentModificationDate();
    @GlobalValue(symbol="NSMetadataItemDateAddedKey", optional=true)
    public static native NSString KeyDateAdded();
    @GlobalValue(symbol="NSMetadataItemDurationSecondsKey", optional=true)
    public static native NSString KeyDurationSeconds();
    @GlobalValue(symbol="NSMetadataItemContactKeywordsKey", optional=true)
    public static native NSString KeyContactKeywords();
    @GlobalValue(symbol="NSMetadataItemVersionKey", optional=true)
    public static native NSString KeyVersion();
    @GlobalValue(symbol="NSMetadataItemPixelHeightKey", optional=true)
    public static native NSString KeyPixelHeight();
    @GlobalValue(symbol="NSMetadataItemPixelWidthKey", optional=true)
    public static native NSString KeyPixelWidth();
    @GlobalValue(symbol="NSMetadataItemPixelCountKey", optional=true)
    public static native NSString KeyPixelCount();
    @GlobalValue(symbol="NSMetadataItemColorSpaceKey", optional=true)
    public static native NSString KeyColorSpace();
    @GlobalValue(symbol="NSMetadataItemBitsPerSampleKey", optional=true)
    public static native NSString KeyBitsPerSample();
    @GlobalValue(symbol="NSMetadataItemFlashOnOffKey", optional=true)
    public static native NSString KeyFlashOnOff();
    @GlobalValue(symbol="NSMetadataItemFocalLengthKey", optional=true)
    public static native NSString KeyFocalLength();
    @GlobalValue(symbol="NSMetadataItemAcquisitionMakeKey", optional=true)
    public static native NSString KeyAcquisitionMake();
    @GlobalValue(symbol="NSMetadataItemAcquisitionModelKey", optional=true)
    public static native NSString KeyAcquisitionModel();
    @GlobalValue(symbol="NSMetadataItemISOSpeedKey", optional=true)
    public static native NSString KeyISOSpeed();
    @GlobalValue(symbol="NSMetadataItemOrientationKey", optional=true)
    public static native NSString KeyOrientation();
    @GlobalValue(symbol="NSMetadataItemLayerNamesKey", optional=true)
    public static native NSString KeyLayerNames();
    @GlobalValue(symbol="NSMetadataItemWhiteBalanceKey", optional=true)
    public static native NSString KeyWhiteBalance();
    @GlobalValue(symbol="NSMetadataItemApertureKey", optional=true)
    public static native NSString KeyAperture();
    @GlobalValue(symbol="NSMetadataItemProfileNameKey", optional=true)
    public static native NSString KeyProfileName();
    @GlobalValue(symbol="NSMetadataItemResolutionWidthDPIKey", optional=true)
    public static native NSString KeyResolutionWidthDPI();
    @GlobalValue(symbol="NSMetadataItemResolutionHeightDPIKey", optional=true)
    public static native NSString KeyResolutionHeightDPI();
    @GlobalValue(symbol="NSMetadataItemExposureModeKey", optional=true)
    public static native NSString KeyExposureMode();
    @GlobalValue(symbol="NSMetadataItemExposureTimeSecondsKey", optional=true)
    public static native NSString KeyExposureTimeSeconds();
    @GlobalValue(symbol="NSMetadataItemEXIFVersionKey", optional=true)
    public static native NSString KeyEXIFVersion();
    @GlobalValue(symbol="NSMetadataItemCameraOwnerKey", optional=true)
    public static native NSString KeyCameraOwner();
    @GlobalValue(symbol="NSMetadataItemFocalLength35mmKey", optional=true)
    public static native NSString KeyFocalLength35mm();
    @GlobalValue(symbol="NSMetadataItemLensModelKey", optional=true)
    public static native NSString KeyLensModel();
    @GlobalValue(symbol="NSMetadataItemEXIFGPSVersionKey", optional=true)
    public static native NSString KeyEXIFGPSVersion();
    @GlobalValue(symbol="NSMetadataItemAltitudeKey", optional=true)
    public static native NSString KeyAltitude();
    @GlobalValue(symbol="NSMetadataItemLatitudeKey", optional=true)
    public static native NSString KeyLatitude();
    @GlobalValue(symbol="NSMetadataItemLongitudeKey", optional=true)
    public static native NSString KeyLongitude();
    @GlobalValue(symbol="NSMetadataItemSpeedKey", optional=true)
    public static native NSString KeySpeed();
    @GlobalValue(symbol="NSMetadataItemTimestampKey", optional=true)
    public static native NSString KeyTimestamp();
    @GlobalValue(symbol="NSMetadataItemGPSTrackKey", optional=true)
    public static native NSString KeyGPSTrack();
    @GlobalValue(symbol="NSMetadataItemImageDirectionKey", optional=true)
    public static native NSString KeyImageDirection();
    @GlobalValue(symbol="NSMetadataItemNamedLocationKey", optional=true)
    public static native NSString KeyNamedLocation();
    @GlobalValue(symbol="NSMetadataItemGPSStatusKey", optional=true)
    public static native NSString KeyGPSStatus();
    @GlobalValue(symbol="NSMetadataItemGPSMeasureModeKey", optional=true)
    public static native NSString KeyGPSMeasureMode();
    @GlobalValue(symbol="NSMetadataItemGPSDOPKey", optional=true)
    public static native NSString KeyGPSDOP();
    @GlobalValue(symbol="NSMetadataItemGPSMapDatumKey", optional=true)
    public static native NSString KeyGPSMapDatum();
    @GlobalValue(symbol="NSMetadataItemGPSDestLatitudeKey", optional=true)
    public static native NSString KeyGPSDestLatitude();
    @GlobalValue(symbol="NSMetadataItemGPSDestLongitudeKey", optional=true)
    public static native NSString KeyGPSDestLongitude();
    @GlobalValue(symbol="NSMetadataItemGPSDestBearingKey", optional=true)
    public static native NSString KeyGPSDestBearing();
    @GlobalValue(symbol="NSMetadataItemGPSDestDistanceKey", optional=true)
    public static native NSString KeyGPSDestDistance();
    @GlobalValue(symbol="NSMetadataItemGPSProcessingMethodKey", optional=true)
    public static native NSString KeyGPSProcessingMethod();
    @GlobalValue(symbol="NSMetadataItemGPSAreaInformationKey", optional=true)
    public static native NSString KeyGPSAreaInformation();
    @GlobalValue(symbol="NSMetadataItemGPSDateStampKey", optional=true)
    public static native NSString KeyGPSDateStamp();
    @GlobalValue(symbol="NSMetadataItemGPSDifferentalKey", optional=true)
    public static native NSString KeyGPSDifferental();
    @GlobalValue(symbol="NSMetadataItemCodecsKey", optional=true)
    public static native NSString KeyCodecs();
    @GlobalValue(symbol="NSMetadataItemMediaTypesKey", optional=true)
    public static native NSString KeyMediaTypes();
    @GlobalValue(symbol="NSMetadataItemStreamableKey", optional=true)
    public static native NSString KeyStreamable();
    @GlobalValue(symbol="NSMetadataItemTotalBitRateKey", optional=true)
    public static native NSString KeyTotalBitRate();
    @GlobalValue(symbol="NSMetadataItemVideoBitRateKey", optional=true)
    public static native NSString KeyVideoBitRate();
    @GlobalValue(symbol="NSMetadataItemAudioBitRateKey", optional=true)
    public static native NSString KeyAudioBitRate();
    @GlobalValue(symbol="NSMetadataItemDeliveryTypeKey", optional=true)
    public static native NSString KeyDeliveryType();
    @GlobalValue(symbol="NSMetadataItemAlbumKey", optional=true)
    public static native NSString KeyAlbum();
    @GlobalValue(symbol="NSMetadataItemHasAlphaChannelKey", optional=true)
    public static native NSString KeyHasAlphaChannel();
    @GlobalValue(symbol="NSMetadataItemRedEyeOnOffKey", optional=true)
    public static native NSString KeyRedEyeOnOff();
    @GlobalValue(symbol="NSMetadataItemMeteringModeKey", optional=true)
    public static native NSString KeyMeteringMode();
    @GlobalValue(symbol="NSMetadataItemMaxApertureKey", optional=true)
    public static native NSString KeyMaxAperture();
    @GlobalValue(symbol="NSMetadataItemFNumberKey", optional=true)
    public static native NSString KeyFNumber();
    @GlobalValue(symbol="NSMetadataItemExposureProgramKey", optional=true)
    public static native NSString KeyExposureProgram();
    @GlobalValue(symbol="NSMetadataItemExposureTimeStringKey", optional=true)
    public static native NSString KeyExposureTimeString();
    @GlobalValue(symbol="NSMetadataItemHeadlineKey", optional=true)
    public static native NSString KeyHeadline();
    @GlobalValue(symbol="NSMetadataItemInstructionsKey", optional=true)
    public static native NSString KeyInstructions();
    @GlobalValue(symbol="NSMetadataItemCityKey", optional=true)
    public static native NSString KeyCity();
    @GlobalValue(symbol="NSMetadataItemStateOrProvinceKey", optional=true)
    public static native NSString KeyStateOrProvince();
    @GlobalValue(symbol="NSMetadataItemCountryKey", optional=true)
    public static native NSString KeyCountry();
    @GlobalValue(symbol="NSMetadataItemTextContentKey", optional=true)
    public static native NSString KeyTextContent();
    @GlobalValue(symbol="NSMetadataItemAudioSampleRateKey", optional=true)
    public static native NSString KeyAudioSampleRate();
    @GlobalValue(symbol="NSMetadataItemAudioChannelCountKey", optional=true)
    public static native NSString KeyAudioChannelCount();
    @GlobalValue(symbol="NSMetadataItemTempoKey", optional=true)
    public static native NSString KeyTempo();
    @GlobalValue(symbol="NSMetadataItemKeySignatureKey", optional=true)
    public static native NSString KeyKeySignature();
    @GlobalValue(symbol="NSMetadataItemTimeSignatureKey", optional=true)
    public static native NSString KeyTimeSignature();
    @GlobalValue(symbol="NSMetadataItemAudioEncodingApplicationKey", optional=true)
    public static native NSString KeyAudioEncodingApplication();
    @GlobalValue(symbol="NSMetadataItemComposerKey", optional=true)
    public static native NSString KeyComposer();
    @GlobalValue(symbol="NSMetadataItemLyricistKey", optional=true)
    public static native NSString KeyLyricist();
    @GlobalValue(symbol="NSMetadataItemAudioTrackNumberKey", optional=true)
    public static native NSString KeyAudioTrackNumber();
    @GlobalValue(symbol="NSMetadataItemRecordingDateKey", optional=true)
    public static native NSString KeyRecordingDate();
    @GlobalValue(symbol="NSMetadataItemMusicalGenreKey", optional=true)
    public static native NSString KeyMusicalGenre();
    @GlobalValue(symbol="NSMetadataItemIsGeneralMIDISequenceKey", optional=true)
    public static native NSString KeyIsGeneralMIDISequence();
    @GlobalValue(symbol="NSMetadataItemRecordingYearKey", optional=true)
    public static native NSString KeyRecordingYear();
    @GlobalValue(symbol="NSMetadataItemOrganizationsKey", optional=true)
    public static native NSString KeyOrganizations();
    @GlobalValue(symbol="NSMetadataItemLanguagesKey", optional=true)
    public static native NSString KeyLanguages();
    @GlobalValue(symbol="NSMetadataItemRightsKey", optional=true)
    public static native NSString KeyRights();
    @GlobalValue(symbol="NSMetadataItemPublishersKey", optional=true)
    public static native NSString KeyPublishers();
    @GlobalValue(symbol="NSMetadataItemContributorsKey", optional=true)
    public static native NSString KeyContributors();
    @GlobalValue(symbol="NSMetadataItemCoverageKey", optional=true)
    public static native NSString KeyCoverage();
    @GlobalValue(symbol="NSMetadataItemSubjectKey", optional=true)
    public static native NSString KeySubject();
    @GlobalValue(symbol="NSMetadataItemThemeKey", optional=true)
    public static native NSString KeyTheme();
    @GlobalValue(symbol="NSMetadataItemDescriptionKey", optional=true)
    public static native NSString KeyDescription();
    @GlobalValue(symbol="NSMetadataItemIdentifierKey", optional=true)
    public static native NSString KeyIdentifier();
    @GlobalValue(symbol="NSMetadataItemAudiencesKey", optional=true)
    public static native NSString KeyAudiences();
    @GlobalValue(symbol="NSMetadataItemNumberOfPagesKey", optional=true)
    public static native NSString KeyNumberOfPages();
    @GlobalValue(symbol="NSMetadataItemPageWidthKey", optional=true)
    public static native NSString KeyPageWidth();
    @GlobalValue(symbol="NSMetadataItemPageHeightKey", optional=true)
    public static native NSString KeyPageHeight();
    @GlobalValue(symbol="NSMetadataItemSecurityMethodKey", optional=true)
    public static native NSString KeySecurityMethod();
    @GlobalValue(symbol="NSMetadataItemCreatorKey", optional=true)
    public static native NSString KeyCreator();
    @GlobalValue(symbol="NSMetadataItemEncodingApplicationsKey", optional=true)
    public static native NSString KeyEncodingApplications();
    @GlobalValue(symbol="NSMetadataItemDueDateKey", optional=true)
    public static native NSString KeyDueDate();
    @GlobalValue(symbol="NSMetadataItemStarRatingKey", optional=true)
    public static native NSString KeyStarRating();
    @GlobalValue(symbol="NSMetadataItemPhoneNumbersKey", optional=true)
    public static native NSString KeyPhoneNumbers();
    @GlobalValue(symbol="NSMetadataItemEmailAddressesKey", optional=true)
    public static native NSString KeyEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemInstantMessageAddressesKey", optional=true)
    public static native NSString KeyInstantMessageAddresses();
    @GlobalValue(symbol="NSMetadataItemKindKey", optional=true)
    public static native NSString KeyKind();
    @GlobalValue(symbol="NSMetadataItemRecipientsKey", optional=true)
    public static native NSString KeyRecipients();
    @GlobalValue(symbol="NSMetadataItemFinderCommentKey", optional=true)
    public static native NSString KeyFinderComment();
    @GlobalValue(symbol="NSMetadataItemFontsKey", optional=true)
    public static native NSString KeyFonts();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsRootKeyKey", optional=true)
    public static native NSString KeyAppleLoopsRootKey();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsKeyFilterTypeKey", optional=true)
    public static native NSString KeyAppleLoopsKeyFilterType();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsLoopModeKey", optional=true)
    public static native NSString KeyAppleLoopsLoopMode();
    @GlobalValue(symbol="NSMetadataItemAppleLoopDescriptorsKey", optional=true)
    public static native NSString KeyAppleLoopDescriptors();
    @GlobalValue(symbol="NSMetadataItemMusicalInstrumentCategoryKey", optional=true)
    public static native NSString KeyMusicalInstrumentCategory();
    @GlobalValue(symbol="NSMetadataItemMusicalInstrumentNameKey", optional=true)
    public static native NSString KeyMusicalInstrumentName();
    @GlobalValue(symbol="NSMetadataItemCFBundleIdentifierKey", optional=true)
    public static native NSString KeyCFBundleIdentifier();
    @GlobalValue(symbol="NSMetadataItemInformationKey", optional=true)
    public static native NSString KeyInformation();
    @GlobalValue(symbol="NSMetadataItemDirectorKey", optional=true)
    public static native NSString KeyDirector();
    @GlobalValue(symbol="NSMetadataItemProducerKey", optional=true)
    public static native NSString KeyProducer();
    @GlobalValue(symbol="NSMetadataItemGenreKey", optional=true)
    public static native NSString KeyGenre();
    @GlobalValue(symbol="NSMetadataItemPerformersKey", optional=true)
    public static native NSString KeyPerformers();
    @GlobalValue(symbol="NSMetadataItemOriginalFormatKey", optional=true)
    public static native NSString KeyOriginalFormat();
    @GlobalValue(symbol="NSMetadataItemOriginalSourceKey", optional=true)
    public static native NSString KeyOriginalSource();
    @GlobalValue(symbol="NSMetadataItemAuthorEmailAddressesKey", optional=true)
    public static native NSString KeyAuthorEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemRecipientEmailAddressesKey", optional=true)
    public static native NSString KeyRecipientEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemAuthorAddressesKey", optional=true)
    public static native NSString KeyAuthorAddresses();
    @GlobalValue(symbol="NSMetadataItemRecipientAddressesKey", optional=true)
    public static native NSString KeyRecipientAddresses();
    @GlobalValue(symbol="NSMetadataItemIsLikelyJunkKey", optional=true)
    public static native NSString KeyIsLikelyJunk();
    @GlobalValue(symbol="NSMetadataItemExecutableArchitecturesKey", optional=true)
    public static native NSString KeyExecutableArchitectures();
    @GlobalValue(symbol="NSMetadataItemExecutablePlatformKey", optional=true)
    public static native NSString KeyExecutablePlatform();
    @GlobalValue(symbol="NSMetadataItemApplicationCategoriesKey", optional=true)
    public static native NSString KeyApplicationCategories();
    @GlobalValue(symbol="NSMetadataItemIsApplicationManagedKey", optional=true)
    public static native NSString KeyIsApplicationManaged();
    
    @Method(selector = "valueForAttribute:")
    public native NSObject valueForAttribute$(String key);
    @Method(selector = "valuesForAttributes:")
    public native NSDictionary<?, ?> valuesForAttributes$(NSArray<?> keys);
    @Method(selector = "attributes")
    public native NSArray<?> attributes();
    /*</methods>*/
}
