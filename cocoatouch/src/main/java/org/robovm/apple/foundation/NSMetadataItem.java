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
    @GlobalValue(symbol="NSMetadataItemFSNameKey")
    public static native NSString KeyFSName();
    @GlobalValue(symbol="NSMetadataItemDisplayNameKey")
    public static native NSString KeyDisplayName();
    @GlobalValue(symbol="NSMetadataItemURLKey")
    public static native NSString KeyURL();
    @GlobalValue(symbol="NSMetadataItemPathKey")
    public static native NSString KeyPath();
    @GlobalValue(symbol="NSMetadataItemFSSizeKey")
    public static native NSString KeyFSSize();
    @GlobalValue(symbol="NSMetadataItemFSCreationDateKey")
    public static native NSString KeyFSCreationDate();
    @GlobalValue(symbol="NSMetadataItemFSContentChangeDateKey")
    public static native NSString KeyFSContentChangeDate();
    @GlobalValue(symbol="NSMetadataItemIsUbiquitousKey")
    public static native NSString KeyIsUbiquitous();
    @GlobalValue(symbol="NSMetadataUbiquitousItemHasUnresolvedConflictsKey")
    public static native NSString KeyUbiquitousHasUnresolvedConflicts();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadedKey")
    public static native NSString KeyUbiquitousIsDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusKey")
    public static native NSString KeyUbiquitousDownloadingStatus();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusNotDownloaded")
    public static native NSString UbiquitousDownloadingStatusNotDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusDownloaded")
    public static native NSString UbiquitousDownloadingStatusDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingStatusCurrent")
    public static native NSString UbiquitousDownloadingStatusCurrent();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsDownloadingKey")
    public static native NSString KeyUbiquitousIsDownloading();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadedKey")
    public static native NSString KeyUbiquitousIsUploaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemIsUploadingKey")
    public static native NSString KeyUbiquitousIsUploading();
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentDownloadedKey")
    public static native NSString KeyUbiquitousPercentDownloaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemPercentUploadedKey")
    public static native NSString KeyUbiquitousPercentUploaded();
    @GlobalValue(symbol="NSMetadataUbiquitousItemDownloadingErrorKey")
    public static native NSString KeyUbiquitousDownloadingError();
    @GlobalValue(symbol="NSMetadataUbiquitousItemUploadingErrorKey")
    public static native NSString KeyUbiquitousUploadingError();
    @GlobalValue(symbol="NSMetadataItemAttributeChangeDateKey")
    public static native NSString KeyAttributeChangeDate();
    @GlobalValue(symbol="NSMetadataItemContentTypeKey")
    public static native NSString KeyContentType();
    @GlobalValue(symbol="NSMetadataItemContentTypeTreeKey")
    public static native NSString KeyContentTypeTree();
    @GlobalValue(symbol="NSMetadataItemKeywordsKey")
    public static native NSString KeyKeywords();
    @GlobalValue(symbol="NSMetadataItemTitleKey")
    public static native NSString KeyTitle();
    @GlobalValue(symbol="NSMetadataItemAuthorsKey")
    public static native NSString KeyAuthors();
    @GlobalValue(symbol="NSMetadataItemEditorsKey")
    public static native NSString KeyEditors();
    @GlobalValue(symbol="NSMetadataItemParticipantsKey")
    public static native NSString KeyParticipants();
    @GlobalValue(symbol="NSMetadataItemProjectsKey")
    public static native NSString KeyProjects();
    @GlobalValue(symbol="NSMetadataItemDownloadedDateKey")
    public static native NSString KeyDownloadedDate();
    @GlobalValue(symbol="NSMetadataItemWhereFromsKey")
    public static native NSString KeyWhereFroms();
    @GlobalValue(symbol="NSMetadataItemCommentKey")
    public static native NSString KeyComment();
    @GlobalValue(symbol="NSMetadataItemCopyrightKey")
    public static native NSString KeyCopyright();
    @GlobalValue(symbol="NSMetadataItemLastUsedDateKey")
    public static native NSString KeyLastUsedDate();
    @GlobalValue(symbol="NSMetadataItemContentCreationDateKey")
    public static native NSString KeyContentCreationDate();
    @GlobalValue(symbol="NSMetadataItemContentModificationDateKey")
    public static native NSString KeyContentModificationDate();
    @GlobalValue(symbol="NSMetadataItemDateAddedKey")
    public static native NSString KeyDateAdded();
    @GlobalValue(symbol="NSMetadataItemDurationSecondsKey")
    public static native NSString KeyDurationSeconds();
    @GlobalValue(symbol="NSMetadataItemContactKeywordsKey")
    public static native NSString KeyContactKeywords();
    @GlobalValue(symbol="NSMetadataItemVersionKey")
    public static native NSString KeyVersion();
    @GlobalValue(symbol="NSMetadataItemPixelHeightKey")
    public static native NSString KeyPixelHeight();
    @GlobalValue(symbol="NSMetadataItemPixelWidthKey")
    public static native NSString KeyPixelWidth();
    @GlobalValue(symbol="NSMetadataItemPixelCountKey")
    public static native NSString KeyPixelCount();
    @GlobalValue(symbol="NSMetadataItemColorSpaceKey")
    public static native NSString KeyColorSpace();
    @GlobalValue(symbol="NSMetadataItemBitsPerSampleKey")
    public static native NSString KeyBitsPerSample();
    @GlobalValue(symbol="NSMetadataItemFlashOnOffKey")
    public static native NSString KeyFlashOnOff();
    @GlobalValue(symbol="NSMetadataItemFocalLengthKey")
    public static native NSString KeyFocalLength();
    @GlobalValue(symbol="NSMetadataItemAcquisitionMakeKey")
    public static native NSString KeyAcquisitionMake();
    @GlobalValue(symbol="NSMetadataItemAcquisitionModelKey")
    public static native NSString KeyAcquisitionModel();
    @GlobalValue(symbol="NSMetadataItemISOSpeedKey")
    public static native NSString KeyISOSpeed();
    @GlobalValue(symbol="NSMetadataItemOrientationKey")
    public static native NSString KeyOrientation();
    @GlobalValue(symbol="NSMetadataItemLayerNamesKey")
    public static native NSString KeyLayerNames();
    @GlobalValue(symbol="NSMetadataItemWhiteBalanceKey")
    public static native NSString KeyWhiteBalance();
    @GlobalValue(symbol="NSMetadataItemApertureKey")
    public static native NSString KeyAperture();
    @GlobalValue(symbol="NSMetadataItemProfileNameKey")
    public static native NSString KeyProfileName();
    @GlobalValue(symbol="NSMetadataItemResolutionWidthDPIKey")
    public static native NSString KeyResolutionWidthDPI();
    @GlobalValue(symbol="NSMetadataItemResolutionHeightDPIKey")
    public static native NSString KeyResolutionHeightDPI();
    @GlobalValue(symbol="NSMetadataItemExposureModeKey")
    public static native NSString KeyExposureMode();
    @GlobalValue(symbol="NSMetadataItemExposureTimeSecondsKey")
    public static native NSString KeyExposureTimeSeconds();
    @GlobalValue(symbol="NSMetadataItemEXIFVersionKey")
    public static native NSString KeyEXIFVersion();
    @GlobalValue(symbol="NSMetadataItemCameraOwnerKey")
    public static native NSString KeyCameraOwner();
    @GlobalValue(symbol="NSMetadataItemFocalLength35mmKey")
    public static native NSString KeyFocalLength35mm();
    @GlobalValue(symbol="NSMetadataItemLensModelKey")
    public static native NSString KeyLensModel();
    @GlobalValue(symbol="NSMetadataItemEXIFGPSVersionKey")
    public static native NSString KeyEXIFGPSVersion();
    @GlobalValue(symbol="NSMetadataItemAltitudeKey")
    public static native NSString KeyAltitude();
    @GlobalValue(symbol="NSMetadataItemLatitudeKey")
    public static native NSString KeyLatitude();
    @GlobalValue(symbol="NSMetadataItemLongitudeKey")
    public static native NSString KeyLongitude();
    @GlobalValue(symbol="NSMetadataItemSpeedKey")
    public static native NSString KeySpeed();
    @GlobalValue(symbol="NSMetadataItemTimestampKey")
    public static native NSString KeyTimestamp();
    @GlobalValue(symbol="NSMetadataItemGPSTrackKey")
    public static native NSString KeyGPSTrack();
    @GlobalValue(symbol="NSMetadataItemImageDirectionKey")
    public static native NSString KeyImageDirection();
    @GlobalValue(symbol="NSMetadataItemNamedLocationKey")
    public static native NSString KeyNamedLocation();
    @GlobalValue(symbol="NSMetadataItemGPSStatusKey")
    public static native NSString KeyGPSStatus();
    @GlobalValue(symbol="NSMetadataItemGPSMeasureModeKey")
    public static native NSString KeyGPSMeasureMode();
    @GlobalValue(symbol="NSMetadataItemGPSDOPKey")
    public static native NSString KeyGPSDOP();
    @GlobalValue(symbol="NSMetadataItemGPSMapDatumKey")
    public static native NSString KeyGPSMapDatum();
    @GlobalValue(symbol="NSMetadataItemGPSDestLatitudeKey")
    public static native NSString KeyGPSDestLatitude();
    @GlobalValue(symbol="NSMetadataItemGPSDestLongitudeKey")
    public static native NSString KeyGPSDestLongitude();
    @GlobalValue(symbol="NSMetadataItemGPSDestBearingKey")
    public static native NSString KeyGPSDestBearing();
    @GlobalValue(symbol="NSMetadataItemGPSDestDistanceKey")
    public static native NSString KeyGPSDestDistance();
    @GlobalValue(symbol="NSMetadataItemGPSProcessingMethodKey")
    public static native NSString KeyGPSProcessingMethod();
    @GlobalValue(symbol="NSMetadataItemGPSAreaInformationKey")
    public static native NSString KeyGPSAreaInformation();
    @GlobalValue(symbol="NSMetadataItemGPSDateStampKey")
    public static native NSString KeyGPSDateStamp();
    @GlobalValue(symbol="NSMetadataItemGPSDifferentalKey")
    public static native NSString KeyGPSDifferental();
    @GlobalValue(symbol="NSMetadataItemCodecsKey")
    public static native NSString KeyCodecs();
    @GlobalValue(symbol="NSMetadataItemMediaTypesKey")
    public static native NSString KeyMediaTypes();
    @GlobalValue(symbol="NSMetadataItemStreamableKey")
    public static native NSString KeyStreamable();
    @GlobalValue(symbol="NSMetadataItemTotalBitRateKey")
    public static native NSString KeyTotalBitRate();
    @GlobalValue(symbol="NSMetadataItemVideoBitRateKey")
    public static native NSString KeyVideoBitRate();
    @GlobalValue(symbol="NSMetadataItemAudioBitRateKey")
    public static native NSString KeyAudioBitRate();
    @GlobalValue(symbol="NSMetadataItemDeliveryTypeKey")
    public static native NSString KeyDeliveryType();
    @GlobalValue(symbol="NSMetadataItemAlbumKey")
    public static native NSString KeyAlbum();
    @GlobalValue(symbol="NSMetadataItemHasAlphaChannelKey")
    public static native NSString KeyHasAlphaChannel();
    @GlobalValue(symbol="NSMetadataItemRedEyeOnOffKey")
    public static native NSString KeyRedEyeOnOff();
    @GlobalValue(symbol="NSMetadataItemMeteringModeKey")
    public static native NSString KeyMeteringMode();
    @GlobalValue(symbol="NSMetadataItemMaxApertureKey")
    public static native NSString KeyMaxAperture();
    @GlobalValue(symbol="NSMetadataItemFNumberKey")
    public static native NSString KeyFNumber();
    @GlobalValue(symbol="NSMetadataItemExposureProgramKey")
    public static native NSString KeyExposureProgram();
    @GlobalValue(symbol="NSMetadataItemExposureTimeStringKey")
    public static native NSString KeyExposureTimeString();
    @GlobalValue(symbol="NSMetadataItemHeadlineKey")
    public static native NSString KeyHeadline();
    @GlobalValue(symbol="NSMetadataItemInstructionsKey")
    public static native NSString KeyInstructions();
    @GlobalValue(symbol="NSMetadataItemCityKey")
    public static native NSString KeyCity();
    @GlobalValue(symbol="NSMetadataItemStateOrProvinceKey")
    public static native NSString KeyStateOrProvince();
    @GlobalValue(symbol="NSMetadataItemCountryKey")
    public static native NSString KeyCountry();
    @GlobalValue(symbol="NSMetadataItemTextContentKey")
    public static native NSString KeyTextContent();
    @GlobalValue(symbol="NSMetadataItemAudioSampleRateKey")
    public static native NSString KeyAudioSampleRate();
    @GlobalValue(symbol="NSMetadataItemAudioChannelCountKey")
    public static native NSString KeyAudioChannelCount();
    @GlobalValue(symbol="NSMetadataItemTempoKey")
    public static native NSString KeyTempo();
    @GlobalValue(symbol="NSMetadataItemKeySignatureKey")
    public static native NSString KeyKeySignature();
    @GlobalValue(symbol="NSMetadataItemTimeSignatureKey")
    public static native NSString KeyTimeSignature();
    @GlobalValue(symbol="NSMetadataItemAudioEncodingApplicationKey")
    public static native NSString KeyAudioEncodingApplication();
    @GlobalValue(symbol="NSMetadataItemComposerKey")
    public static native NSString KeyComposer();
    @GlobalValue(symbol="NSMetadataItemLyricistKey")
    public static native NSString KeyLyricist();
    @GlobalValue(symbol="NSMetadataItemAudioTrackNumberKey")
    public static native NSString KeyAudioTrackNumber();
    @GlobalValue(symbol="NSMetadataItemRecordingDateKey")
    public static native NSString KeyRecordingDate();
    @GlobalValue(symbol="NSMetadataItemMusicalGenreKey")
    public static native NSString KeyMusicalGenre();
    @GlobalValue(symbol="NSMetadataItemIsGeneralMIDISequenceKey")
    public static native NSString KeyIsGeneralMIDISequence();
    @GlobalValue(symbol="NSMetadataItemRecordingYearKey")
    public static native NSString KeyRecordingYear();
    @GlobalValue(symbol="NSMetadataItemOrganizationsKey")
    public static native NSString KeyOrganizations();
    @GlobalValue(symbol="NSMetadataItemLanguagesKey")
    public static native NSString KeyLanguages();
    @GlobalValue(symbol="NSMetadataItemRightsKey")
    public static native NSString KeyRights();
    @GlobalValue(symbol="NSMetadataItemPublishersKey")
    public static native NSString KeyPublishers();
    @GlobalValue(symbol="NSMetadataItemContributorsKey")
    public static native NSString KeyContributors();
    @GlobalValue(symbol="NSMetadataItemCoverageKey")
    public static native NSString KeyCoverage();
    @GlobalValue(symbol="NSMetadataItemSubjectKey")
    public static native NSString KeySubject();
    @GlobalValue(symbol="NSMetadataItemThemeKey")
    public static native NSString KeyTheme();
    @GlobalValue(symbol="NSMetadataItemDescriptionKey")
    public static native NSString KeyDescription();
    @GlobalValue(symbol="NSMetadataItemIdentifierKey")
    public static native NSString KeyIdentifier();
    @GlobalValue(symbol="NSMetadataItemAudiencesKey")
    public static native NSString KeyAudiences();
    @GlobalValue(symbol="NSMetadataItemNumberOfPagesKey")
    public static native NSString KeyNumberOfPages();
    @GlobalValue(symbol="NSMetadataItemPageWidthKey")
    public static native NSString KeyPageWidth();
    @GlobalValue(symbol="NSMetadataItemPageHeightKey")
    public static native NSString KeyPageHeight();
    @GlobalValue(symbol="NSMetadataItemSecurityMethodKey")
    public static native NSString KeySecurityMethod();
    @GlobalValue(symbol="NSMetadataItemCreatorKey")
    public static native NSString KeyCreator();
    @GlobalValue(symbol="NSMetadataItemEncodingApplicationsKey")
    public static native NSString KeyEncodingApplications();
    @GlobalValue(symbol="NSMetadataItemDueDateKey")
    public static native NSString KeyDueDate();
    @GlobalValue(symbol="NSMetadataItemStarRatingKey")
    public static native NSString KeyStarRating();
    @GlobalValue(symbol="NSMetadataItemPhoneNumbersKey")
    public static native NSString KeyPhoneNumbers();
    @GlobalValue(symbol="NSMetadataItemEmailAddressesKey")
    public static native NSString KeyEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemInstantMessageAddressesKey")
    public static native NSString KeyInstantMessageAddresses();
    @GlobalValue(symbol="NSMetadataItemKindKey")
    public static native NSString KeyKind();
    @GlobalValue(symbol="NSMetadataItemRecipientsKey")
    public static native NSString KeyRecipients();
    @GlobalValue(symbol="NSMetadataItemFinderCommentKey")
    public static native NSString KeyFinderComment();
    @GlobalValue(symbol="NSMetadataItemFontsKey")
    public static native NSString KeyFonts();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsRootKeyKey")
    public static native NSString KeyAppleLoopsRootKey();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsKeyFilterTypeKey")
    public static native NSString KeyAppleLoopsKeyFilterType();
    @GlobalValue(symbol="NSMetadataItemAppleLoopsLoopModeKey")
    public static native NSString KeyAppleLoopsLoopMode();
    @GlobalValue(symbol="NSMetadataItemAppleLoopDescriptorsKey")
    public static native NSString KeyAppleLoopDescriptors();
    @GlobalValue(symbol="NSMetadataItemMusicalInstrumentCategoryKey")
    public static native NSString KeyMusicalInstrumentCategory();
    @GlobalValue(symbol="NSMetadataItemMusicalInstrumentNameKey")
    public static native NSString KeyMusicalInstrumentName();
    @GlobalValue(symbol="NSMetadataItemCFBundleIdentifierKey")
    public static native NSString KeyCFBundleIdentifier();
    @GlobalValue(symbol="NSMetadataItemInformationKey")
    public static native NSString KeyInformation();
    @GlobalValue(symbol="NSMetadataItemDirectorKey")
    public static native NSString KeyDirector();
    @GlobalValue(symbol="NSMetadataItemProducerKey")
    public static native NSString KeyProducer();
    @GlobalValue(symbol="NSMetadataItemGenreKey")
    public static native NSString KeyGenre();
    @GlobalValue(symbol="NSMetadataItemPerformersKey")
    public static native NSString KeyPerformers();
    @GlobalValue(symbol="NSMetadataItemOriginalFormatKey")
    public static native NSString KeyOriginalFormat();
    @GlobalValue(symbol="NSMetadataItemOriginalSourceKey")
    public static native NSString KeyOriginalSource();
    @GlobalValue(symbol="NSMetadataItemAuthorEmailAddressesKey")
    public static native NSString KeyAuthorEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemRecipientEmailAddressesKey")
    public static native NSString KeyRecipientEmailAddresses();
    @GlobalValue(symbol="NSMetadataItemAuthorAddressesKey")
    public static native NSString KeyAuthorAddresses();
    @GlobalValue(symbol="NSMetadataItemRecipientAddressesKey")
    public static native NSString KeyRecipientAddresses();
    @GlobalValue(symbol="NSMetadataItemIsLikelyJunkKey")
    public static native NSString KeyIsLikelyJunk();
    @GlobalValue(symbol="NSMetadataItemExecutableArchitecturesKey")
    public static native NSString KeyExecutableArchitectures();
    @GlobalValue(symbol="NSMetadataItemExecutablePlatformKey")
    public static native NSString KeyExecutablePlatform();
    @GlobalValue(symbol="NSMetadataItemApplicationCategoriesKey")
    public static native NSString KeyApplicationCategories();
    @GlobalValue(symbol="NSMetadataItemIsApplicationManagedKey")
    public static native NSString KeyIsApplicationManaged();
    
    @Method(selector = "valueForAttribute:")
    public native NSObject valueForAttribute$(String key);
    @Method(selector = "valuesForAttributes:")
    public native NSDictionary<?, ?> valuesForAttributes$(NSArray<?> keys);
    @Method(selector = "attributes")
    public native NSArray<?> attributes();
    /*</methods>*/
}
