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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMetadataIdentifierID3Metadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifierID3Metadata toObject(Class<AVMetadataIdentifierID3Metadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifierID3Metadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifierID3Metadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifierID3Metadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AudioEncryption = new AVMetadataIdentifierID3Metadata("AudioEncryptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AttachedPicture = new AVMetadataIdentifierID3Metadata("AttachedPictureValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AudioSeekPointIndex = new AVMetadataIdentifierID3Metadata("AudioSeekPointIndexValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Comments = new AVMetadataIdentifierID3Metadata("CommentsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Commerical = new AVMetadataIdentifierID3Metadata("CommericalValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Encryption = new AVMetadataIdentifierID3Metadata("EncryptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Equalization = new AVMetadataIdentifierID3Metadata("EqualizationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Equalization2 = new AVMetadataIdentifierID3Metadata("Equalization2Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EventTimingCodes = new AVMetadataIdentifierID3Metadata("EventTimingCodesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata GeneralEncapsulatedObject = new AVMetadataIdentifierID3Metadata("GeneralEncapsulatedObjectValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata GroupIdentifier = new AVMetadataIdentifierID3Metadata("GroupIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InvolvedPeopleList_v23 = new AVMetadataIdentifierID3Metadata("InvolvedPeopleList_v23Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Link = new AVMetadataIdentifierID3Metadata("LinkValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MusicCDIdentifier = new AVMetadataIdentifierID3Metadata("MusicCDIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MPEGLocationLookupTable = new AVMetadataIdentifierID3Metadata("MPEGLocationLookupTableValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Ownership = new AVMetadataIdentifierID3Metadata("OwnershipValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Private = new AVMetadataIdentifierID3Metadata("PrivateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PlayCounter = new AVMetadataIdentifierID3Metadata("PlayCounterValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Popularimeter = new AVMetadataIdentifierID3Metadata("PopularimeterValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PositionSynchronization = new AVMetadataIdentifierID3Metadata("PositionSynchronizationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecommendedBufferSize = new AVMetadataIdentifierID3Metadata("RecommendedBufferSizeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RelativeVolumeAdjustment = new AVMetadataIdentifierID3Metadata("RelativeVolumeAdjustmentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RelativeVolumeAdjustment2 = new AVMetadataIdentifierID3Metadata("RelativeVolumeAdjustment2Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Reverb = new AVMetadataIdentifierID3Metadata("ReverbValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Seek = new AVMetadataIdentifierID3Metadata("SeekValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Signature = new AVMetadataIdentifierID3Metadata("SignatureValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SynchronizedLyric = new AVMetadataIdentifierID3Metadata("SynchronizedLyricValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SynchronizedTempoCodes = new AVMetadataIdentifierID3Metadata("SynchronizedTempoCodesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AlbumTitle = new AVMetadataIdentifierID3Metadata("AlbumTitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata BeatsPerMinute = new AVMetadataIdentifierID3Metadata("BeatsPerMinuteValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Composer = new AVMetadataIdentifierID3Metadata("ComposerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ContentType = new AVMetadataIdentifierID3Metadata("ContentTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Copyright = new AVMetadataIdentifierID3Metadata("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Date = new AVMetadataIdentifierID3Metadata("DateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodingTime = new AVMetadataIdentifierID3Metadata("EncodingTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PlaylistDelay = new AVMetadataIdentifierID3Metadata("PlaylistDelayValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalReleaseTime = new AVMetadataIdentifierID3Metadata("OriginalReleaseTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecordingTime = new AVMetadataIdentifierID3Metadata("RecordingTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ReleaseTime = new AVMetadataIdentifierID3Metadata("ReleaseTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TaggingTime = new AVMetadataIdentifierID3Metadata("TaggingTimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodedBy = new AVMetadataIdentifierID3Metadata("EncodedByValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Lyricist = new AVMetadataIdentifierID3Metadata("LyricistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata FileType = new AVMetadataIdentifierID3Metadata("FileTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Time = new AVMetadataIdentifierID3Metadata("TimeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InvolvedPeopleList_v24 = new AVMetadataIdentifierID3Metadata("InvolvedPeopleList_v24Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ContentGroupDescription = new AVMetadataIdentifierID3Metadata("ContentGroupDescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TitleDescription = new AVMetadataIdentifierID3Metadata("TitleDescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SubTitle = new AVMetadataIdentifierID3Metadata("SubTitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InitialKey = new AVMetadataIdentifierID3Metadata("InitialKeyValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Language = new AVMetadataIdentifierID3Metadata("LanguageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Length = new AVMetadataIdentifierID3Metadata("LengthValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MusicianCreditsList = new AVMetadataIdentifierID3Metadata("MusicianCreditsListValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MediaType = new AVMetadataIdentifierID3Metadata("MediaTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Mood = new AVMetadataIdentifierID3Metadata("MoodValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalAlbumTitle = new AVMetadataIdentifierID3Metadata("OriginalAlbumTitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalFilename = new AVMetadataIdentifierID3Metadata("OriginalFilenameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalLyricist = new AVMetadataIdentifierID3Metadata("OriginalLyricistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalArtist = new AVMetadataIdentifierID3Metadata("OriginalArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalReleaseYear = new AVMetadataIdentifierID3Metadata("OriginalReleaseYearValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata FileOwner = new AVMetadataIdentifierID3Metadata("FileOwnerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata LeadPerformer = new AVMetadataIdentifierID3Metadata("LeadPerformerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Band = new AVMetadataIdentifierID3Metadata("BandValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Conductor = new AVMetadataIdentifierID3Metadata("ConductorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ModifiedBy = new AVMetadataIdentifierID3Metadata("ModifiedByValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PartOfASet = new AVMetadataIdentifierID3Metadata("PartOfASetValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ProducedNotice = new AVMetadataIdentifierID3Metadata("ProducedNoticeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Publisher = new AVMetadataIdentifierID3Metadata("PublisherValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TrackNumber = new AVMetadataIdentifierID3Metadata("TrackNumberValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecordingDates = new AVMetadataIdentifierID3Metadata("RecordingDatesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternetRadioStationName = new AVMetadataIdentifierID3Metadata("InternetRadioStationNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternetRadioStationOwner = new AVMetadataIdentifierID3Metadata("InternetRadioStationOwnerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Size = new AVMetadataIdentifierID3Metadata("SizeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AlbumSortOrder = new AVMetadataIdentifierID3Metadata("AlbumSortOrderValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PerformerSortOrder = new AVMetadataIdentifierID3Metadata("PerformerSortOrderValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TitleSortOrder = new AVMetadataIdentifierID3Metadata("TitleSortOrderValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternationalStandardRecordingCode = new AVMetadataIdentifierID3Metadata("InternationalStandardRecordingCodeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodedWith = new AVMetadataIdentifierID3Metadata("EncodedWithValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SetSubtitle = new AVMetadataIdentifierID3Metadata("SetSubtitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Year = new AVMetadataIdentifierID3Metadata("YearValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UserText = new AVMetadataIdentifierID3Metadata("UserTextValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UniqueFileIdentifier = new AVMetadataIdentifierID3Metadata("UniqueFileIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TermsOfUse = new AVMetadataIdentifierID3Metadata("TermsOfUseValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UnsynchronizedLyric = new AVMetadataIdentifierID3Metadata("UnsynchronizedLyricValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata CommercialInformation = new AVMetadataIdentifierID3Metadata("CommercialInformationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata CopyrightInformation = new AVMetadataIdentifierID3Metadata("CopyrightInformationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialAudioFileWebpage = new AVMetadataIdentifierID3Metadata("OfficialAudioFileWebpageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialArtistWebpage = new AVMetadataIdentifierID3Metadata("OfficialArtistWebpageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialAudioSourceWebpage = new AVMetadataIdentifierID3Metadata("OfficialAudioSourceWebpageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialInternetRadioStationHomepage = new AVMetadataIdentifierID3Metadata("OfficialInternetRadioStationHomepageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Payment = new AVMetadataIdentifierID3Metadata("PaymentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialPublisherWebpage = new AVMetadataIdentifierID3Metadata("OfficialPublisherWebpageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UserURL = new AVMetadataIdentifierID3Metadata("UserURLValue");
    
    private static AVMetadataIdentifierID3Metadata[] values = new AVMetadataIdentifierID3Metadata[] {AudioEncryption, AttachedPicture, AudioSeekPointIndex, Comments, Commerical, 
        Encryption, Equalization, Equalization2, EventTimingCodes, GeneralEncapsulatedObject, GroupIdentifier, InvolvedPeopleList_v23, Link, MusicCDIdentifier, MPEGLocationLookupTable, 
        Ownership, Private, PlayCounter, Popularimeter, PositionSynchronization, RecommendedBufferSize, RelativeVolumeAdjustment, RelativeVolumeAdjustment2, 
        Reverb, Seek, Signature, SynchronizedLyric, SynchronizedTempoCodes, AlbumTitle, BeatsPerMinute, Composer, ContentType, Copyright, Date, EncodingTime, PlaylistDelay, OriginalReleaseTime, 
        RecordingTime, ReleaseTime, TaggingTime, EncodedBy, Lyricist, FileType, Time, InvolvedPeopleList_v24, ContentGroupDescription, TitleDescription, SubTitle, InitialKey, 
        Language, Length, MusicianCreditsList, MediaType, Mood, OriginalAlbumTitle, OriginalFilename, OriginalLyricist, OriginalArtist, OriginalReleaseYear, FileOwner, LeadPerformer, 
        Band, Conductor, ModifiedBy, PartOfASet, ProducedNotice, Publisher, TrackNumber, RecordingDates, InternetRadioStationName, InternetRadioStationOwner, Size, AlbumSortOrder, 
        PerformerSortOrder, TitleSortOrder, InternationalStandardRecordingCode, EncodedWith, SetSubtitle, Year, UserText, UniqueFileIdentifier, TermsOfUse, UnsynchronizedLyric, 
        CommercialInformation, CopyrightInformation, OfficialAudioFileWebpage, OfficialArtistWebpage, OfficialAudioSourceWebpage, OfficialInternetRadioStationHomepage, Payment, 
        OfficialPublisherWebpage, UserURL};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifierID3Metadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifierID3Metadata valueOf(NSString value) {
        for (AVMetadataIdentifierID3Metadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioEncryption", optional=true)
    protected static native NSString AudioEncryptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAttachedPicture", optional=true)
    protected static native NSString AttachedPictureValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioSeekPointIndex", optional=true)
    protected static native NSString AudioSeekPointIndexValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComments", optional=true)
    protected static native NSString CommentsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommerical", optional=true)
    protected static native NSString CommericalValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncryption", optional=true)
    protected static native NSString EncryptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization", optional=true)
    protected static native NSString EqualizationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization2", optional=true)
    protected static native NSString Equalization2Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEventTimingCodes", optional=true)
    protected static native NSString EventTimingCodesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGeneralEncapsulatedObject", optional=true)
    protected static native NSString GeneralEncapsulatedObjectValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGroupIdentifier", optional=true)
    protected static native NSString GroupIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v23", optional=true)
    protected static native NSString InvolvedPeopleList_v23Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLink", optional=true)
    protected static native NSString LinkValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicCDIdentifier", optional=true)
    protected static native NSString MusicCDIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMPEGLocationLookupTable", optional=true)
    protected static native NSString MPEGLocationLookupTableValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOwnership", optional=true)
    protected static native NSString OwnershipValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPrivate", optional=true)
    protected static native NSString PrivateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlayCounter", optional=true)
    protected static native NSString PlayCounterValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPopularimeter", optional=true)
    protected static native NSString PopularimeterValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPositionSynchronization", optional=true)
    protected static native NSString PositionSynchronizationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecommendedBufferSize", optional=true)
    protected static native NSString RecommendedBufferSizeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment", optional=true)
    protected static native NSString RelativeVolumeAdjustmentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment2", optional=true)
    protected static native NSString RelativeVolumeAdjustment2Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReverb", optional=true)
    protected static native NSString ReverbValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSeek", optional=true)
    protected static native NSString SeekValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSignature", optional=true)
    protected static native NSString SignatureValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedLyric", optional=true)
    protected static native NSString SynchronizedLyricValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedTempoCodes", optional=true)
    protected static native NSString SynchronizedTempoCodesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumTitle", optional=true)
    protected static native NSString AlbumTitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBeatsPerMinute", optional=true)
    protected static native NSString BeatsPerMinuteValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentType", optional=true)
    protected static native NSString ContentTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataDate", optional=true)
    protected static native NSString DateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodingTime", optional=true)
    protected static native NSString EncodingTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlaylistDelay", optional=true)
    protected static native NSString PlaylistDelayValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseTime", optional=true)
    protected static native NSString OriginalReleaseTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingTime", optional=true)
    protected static native NSString RecordingTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReleaseTime", optional=true)
    protected static native NSString ReleaseTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTaggingTime", optional=true)
    protected static native NSString TaggingTimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLyricist", optional=true)
    protected static native NSString LyricistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileType", optional=true)
    protected static native NSString FileTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTime", optional=true)
    protected static native NSString TimeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v24", optional=true)
    protected static native NSString InvolvedPeopleList_v24Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentGroupDescription", optional=true)
    protected static native NSString ContentGroupDescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleDescription", optional=true)
    protected static native NSString TitleDescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSubTitle", optional=true)
    protected static native NSString SubTitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInitialKey", optional=true)
    protected static native NSString InitialKeyValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLanguage", optional=true)
    protected static native NSString LanguageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLength", optional=true)
    protected static native NSString LengthValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicianCreditsList", optional=true)
    protected static native NSString MusicianCreditsListValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMediaType", optional=true)
    protected static native NSString MediaTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMood", optional=true)
    protected static native NSString MoodValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalAlbumTitle", optional=true)
    protected static native NSString OriginalAlbumTitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalFilename", optional=true)
    protected static native NSString OriginalFilenameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalLyricist", optional=true)
    protected static native NSString OriginalLyricistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseYear", optional=true)
    protected static native NSString OriginalReleaseYearValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileOwner", optional=true)
    protected static native NSString FileOwnerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLeadPerformer", optional=true)
    protected static native NSString LeadPerformerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBand", optional=true)
    protected static native NSString BandValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataConductor", optional=true)
    protected static native NSString ConductorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataModifiedBy", optional=true)
    protected static native NSString ModifiedByValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPartOfASet", optional=true)
    protected static native NSString PartOfASetValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataProducedNotice", optional=true)
    protected static native NSString ProducedNoticeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTrackNumber", optional=true)
    protected static native NSString TrackNumberValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingDates", optional=true)
    protected static native NSString RecordingDatesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationName", optional=true)
    protected static native NSString InternetRadioStationNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationOwner", optional=true)
    protected static native NSString InternetRadioStationOwnerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSize", optional=true)
    protected static native NSString SizeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumSortOrder", optional=true)
    protected static native NSString AlbumSortOrderValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPerformerSortOrder", optional=true)
    protected static native NSString PerformerSortOrderValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleSortOrder", optional=true)
    protected static native NSString TitleSortOrderValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternationalStandardRecordingCode", optional=true)
    protected static native NSString InternationalStandardRecordingCodeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedWith", optional=true)
    protected static native NSString EncodedWithValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSetSubtitle", optional=true)
    protected static native NSString SetSubtitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataYear", optional=true)
    protected static native NSString YearValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserText", optional=true)
    protected static native NSString UserTextValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUniqueFileIdentifier", optional=true)
    protected static native NSString UniqueFileIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTermsOfUse", optional=true)
    protected static native NSString TermsOfUseValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUnsynchronizedLyric", optional=true)
    protected static native NSString UnsynchronizedLyricValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommercialInformation", optional=true)
    protected static native NSString CommercialInformationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyrightInformation", optional=true)
    protected static native NSString CopyrightInformationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioFileWebpage", optional=true)
    protected static native NSString OfficialAudioFileWebpageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialArtistWebpage", optional=true)
    protected static native NSString OfficialArtistWebpageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioSourceWebpage", optional=true)
    protected static native NSString OfficialAudioSourceWebpageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialInternetRadioStationHomepage", optional=true)
    protected static native NSString OfficialInternetRadioStationHomepageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPayment", optional=true)
    protected static native NSString PaymentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialPublisherWebpage", optional=true)
    protected static native NSString OfficialPublisherWebpageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserURL", optional=true)
    protected static native NSString UserURLValue();
    /*</methods>*/
}
