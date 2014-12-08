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
@Marshaler(AVMetadataKeyID3Metadata.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyID3Metadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyID3Metadata toObject(Class<AVMetadataKeyID3Metadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyID3Metadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyID3Metadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeyID3Metadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AudioEncryption = new AVMetadataKeyID3Metadata("AudioEncryptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AttachedPicture = new AVMetadataKeyID3Metadata("AttachedPictureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AudioSeekPointIndex = new AVMetadataKeyID3Metadata("AudioSeekPointIndexValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Comments = new AVMetadataKeyID3Metadata("CommentsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Commerical = new AVMetadataKeyID3Metadata("CommericalValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Encryption = new AVMetadataKeyID3Metadata("EncryptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Equalization = new AVMetadataKeyID3Metadata("EqualizationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Equalization2 = new AVMetadataKeyID3Metadata("Equalization2Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EventTimingCodes = new AVMetadataKeyID3Metadata("EventTimingCodesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata GeneralEncapsulatedObject = new AVMetadataKeyID3Metadata("GeneralEncapsulatedObjectValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata GroupIdentifier = new AVMetadataKeyID3Metadata("GroupIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InvolvedPeopleList_v23 = new AVMetadataKeyID3Metadata("InvolvedPeopleList_v23Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Link = new AVMetadataKeyID3Metadata("LinkValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MusicCDIdentifier = new AVMetadataKeyID3Metadata("MusicCDIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MPEGLocationLookupTable = new AVMetadataKeyID3Metadata("MPEGLocationLookupTableValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Ownership = new AVMetadataKeyID3Metadata("OwnershipValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Private = new AVMetadataKeyID3Metadata("PrivateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PlayCounter = new AVMetadataKeyID3Metadata("PlayCounterValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Popularimeter = new AVMetadataKeyID3Metadata("PopularimeterValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PositionSynchronization = new AVMetadataKeyID3Metadata("PositionSynchronizationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecommendedBufferSize = new AVMetadataKeyID3Metadata("RecommendedBufferSizeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RelativeVolumeAdjustment = new AVMetadataKeyID3Metadata("RelativeVolumeAdjustmentValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RelativeVolumeAdjustment2 = new AVMetadataKeyID3Metadata("RelativeVolumeAdjustment2Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Reverb = new AVMetadataKeyID3Metadata("ReverbValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Seek = new AVMetadataKeyID3Metadata("SeekValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Signature = new AVMetadataKeyID3Metadata("SignatureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SynchronizedLyric = new AVMetadataKeyID3Metadata("SynchronizedLyricValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SynchronizedTempoCodes = new AVMetadataKeyID3Metadata("SynchronizedTempoCodesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AlbumTitle = new AVMetadataKeyID3Metadata("AlbumTitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata BeatsPerMinute = new AVMetadataKeyID3Metadata("BeatsPerMinuteValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Composer = new AVMetadataKeyID3Metadata("ComposerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ContentType = new AVMetadataKeyID3Metadata("ContentTypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Copyright = new AVMetadataKeyID3Metadata("CopyrightValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Date = new AVMetadataKeyID3Metadata("DateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodingTime = new AVMetadataKeyID3Metadata("EncodingTimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PlaylistDelay = new AVMetadataKeyID3Metadata("PlaylistDelayValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalReleaseTime = new AVMetadataKeyID3Metadata("OriginalReleaseTimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecordingTime = new AVMetadataKeyID3Metadata("RecordingTimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ReleaseTime = new AVMetadataKeyID3Metadata("ReleaseTimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TaggingTime = new AVMetadataKeyID3Metadata("TaggingTimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodedBy = new AVMetadataKeyID3Metadata("EncodedByValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Lyricist = new AVMetadataKeyID3Metadata("LyricistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata FileType = new AVMetadataKeyID3Metadata("FileTypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Time = new AVMetadataKeyID3Metadata("TimeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InvolvedPeopleList_v24 = new AVMetadataKeyID3Metadata("InvolvedPeopleList_v24Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ContentGroupDescription = new AVMetadataKeyID3Metadata("ContentGroupDescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TitleDescription = new AVMetadataKeyID3Metadata("TitleDescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SubTitle = new AVMetadataKeyID3Metadata("SubTitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InitialKey = new AVMetadataKeyID3Metadata("InitialKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Language = new AVMetadataKeyID3Metadata("LanguageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Length = new AVMetadataKeyID3Metadata("LengthValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MusicianCreditsList = new AVMetadataKeyID3Metadata("MusicianCreditsListValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MediaType = new AVMetadataKeyID3Metadata("MediaTypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Mood = new AVMetadataKeyID3Metadata("MoodValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalAlbumTitle = new AVMetadataKeyID3Metadata("OriginalAlbumTitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalFilename = new AVMetadataKeyID3Metadata("OriginalFilenameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalLyricist = new AVMetadataKeyID3Metadata("OriginalLyricistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalArtist = new AVMetadataKeyID3Metadata("OriginalArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalReleaseYear = new AVMetadataKeyID3Metadata("OriginalReleaseYearValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata FileOwner = new AVMetadataKeyID3Metadata("FileOwnerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata LeadPerformer = new AVMetadataKeyID3Metadata("LeadPerformerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Band = new AVMetadataKeyID3Metadata("BandValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Conductor = new AVMetadataKeyID3Metadata("ConductorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ModifiedBy = new AVMetadataKeyID3Metadata("ModifiedByValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PartOfASet = new AVMetadataKeyID3Metadata("PartOfASetValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ProducedNotice = new AVMetadataKeyID3Metadata("ProducedNoticeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Publisher = new AVMetadataKeyID3Metadata("PublisherValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TrackNumber = new AVMetadataKeyID3Metadata("TrackNumberValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecordingDates = new AVMetadataKeyID3Metadata("RecordingDatesValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternetRadioStationName = new AVMetadataKeyID3Metadata("InternetRadioStationNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternetRadioStationOwner = new AVMetadataKeyID3Metadata("InternetRadioStationOwnerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Size = new AVMetadataKeyID3Metadata("SizeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AlbumSortOrder = new AVMetadataKeyID3Metadata("AlbumSortOrderValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PerformerSortOrder = new AVMetadataKeyID3Metadata("PerformerSortOrderValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TitleSortOrder = new AVMetadataKeyID3Metadata("TitleSortOrderValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternationalStandardRecordingCode = new AVMetadataKeyID3Metadata("InternationalStandardRecordingCodeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodedWith = new AVMetadataKeyID3Metadata("EncodedWithValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SetSubtitle = new AVMetadataKeyID3Metadata("SetSubtitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Year = new AVMetadataKeyID3Metadata("YearValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UserText = new AVMetadataKeyID3Metadata("UserTextValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UniqueFileIdentifier = new AVMetadataKeyID3Metadata("UniqueFileIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TermsOfUse = new AVMetadataKeyID3Metadata("TermsOfUseValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UnsynchronizedLyric = new AVMetadataKeyID3Metadata("UnsynchronizedLyricValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata CommercialInformation = new AVMetadataKeyID3Metadata("CommercialInformationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata CopyrightInformation = new AVMetadataKeyID3Metadata("CopyrightInformationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialAudioFileWebpage = new AVMetadataKeyID3Metadata("OfficialAudioFileWebpageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialArtistWebpage = new AVMetadataKeyID3Metadata("OfficialArtistWebpageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialAudioSourceWebpage = new AVMetadataKeyID3Metadata("OfficialAudioSourceWebpageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialInternetRadioStationHomepage = new AVMetadataKeyID3Metadata("OfficialInternetRadioStationHomepageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Payment = new AVMetadataKeyID3Metadata("PaymentValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialPublisherWebpage = new AVMetadataKeyID3Metadata("OfficialPublisherWebpageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UserURL = new AVMetadataKeyID3Metadata("UserURLValue");
    
    private static AVMetadataKeyID3Metadata[] values = new AVMetadataKeyID3Metadata[] {AudioEncryption, AttachedPicture, AudioSeekPointIndex, Comments, Commerical, 
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
    
    private AVMetadataKeyID3Metadata(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeyID3Metadata valueOf(NSString value) {
        for (AVMetadataKeyID3Metadata v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKeyID3Metadata/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioEncryption", optional=true)
    protected static native NSString AudioEncryptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAttachedPicture", optional=true)
    protected static native NSString AttachedPictureValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioSeekPointIndex", optional=true)
    protected static native NSString AudioSeekPointIndexValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComments", optional=true)
    protected static native NSString CommentsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommerical", optional=true)
    protected static native NSString CommericalValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncryption", optional=true)
    protected static native NSString EncryptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization", optional=true)
    protected static native NSString EqualizationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization2", optional=true)
    protected static native NSString Equalization2Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEventTimingCodes", optional=true)
    protected static native NSString EventTimingCodesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGeneralEncapsulatedObject", optional=true)
    protected static native NSString GeneralEncapsulatedObjectValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGroupIdentifier", optional=true)
    protected static native NSString GroupIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v23", optional=true)
    protected static native NSString InvolvedPeopleList_v23Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLink", optional=true)
    protected static native NSString LinkValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicCDIdentifier", optional=true)
    protected static native NSString MusicCDIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMPEGLocationLookupTable", optional=true)
    protected static native NSString MPEGLocationLookupTableValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOwnership", optional=true)
    protected static native NSString OwnershipValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPrivate", optional=true)
    protected static native NSString PrivateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlayCounter", optional=true)
    protected static native NSString PlayCounterValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPopularimeter", optional=true)
    protected static native NSString PopularimeterValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPositionSynchronization", optional=true)
    protected static native NSString PositionSynchronizationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecommendedBufferSize", optional=true)
    protected static native NSString RecommendedBufferSizeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment", optional=true)
    protected static native NSString RelativeVolumeAdjustmentValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment2", optional=true)
    protected static native NSString RelativeVolumeAdjustment2Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReverb", optional=true)
    protected static native NSString ReverbValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySeek", optional=true)
    protected static native NSString SeekValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySignature", optional=true)
    protected static native NSString SignatureValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedLyric", optional=true)
    protected static native NSString SynchronizedLyricValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedTempoCodes", optional=true)
    protected static native NSString SynchronizedTempoCodesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumTitle", optional=true)
    protected static native NSString AlbumTitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBeatsPerMinute", optional=true)
    protected static native NSString BeatsPerMinuteValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentType", optional=true)
    protected static native NSString ContentTypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyDate", optional=true)
    protected static native NSString DateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodingTime", optional=true)
    protected static native NSString EncodingTimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlaylistDelay", optional=true)
    protected static native NSString PlaylistDelayValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseTime", optional=true)
    protected static native NSString OriginalReleaseTimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingTime", optional=true)
    protected static native NSString RecordingTimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReleaseTime", optional=true)
    protected static native NSString ReleaseTimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTaggingTime", optional=true)
    protected static native NSString TaggingTimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLyricist", optional=true)
    protected static native NSString LyricistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileType", optional=true)
    protected static native NSString FileTypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTime", optional=true)
    protected static native NSString TimeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v24", optional=true)
    protected static native NSString InvolvedPeopleList_v24Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentGroupDescription", optional=true)
    protected static native NSString ContentGroupDescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleDescription", optional=true)
    protected static native NSString TitleDescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySubTitle", optional=true)
    protected static native NSString SubTitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInitialKey", optional=true)
    protected static native NSString InitialKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLanguage", optional=true)
    protected static native NSString LanguageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLength", optional=true)
    protected static native NSString LengthValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicianCreditsList", optional=true)
    protected static native NSString MusicianCreditsListValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMediaType", optional=true)
    protected static native NSString MediaTypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMood", optional=true)
    protected static native NSString MoodValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalAlbumTitle", optional=true)
    protected static native NSString OriginalAlbumTitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalFilename", optional=true)
    protected static native NSString OriginalFilenameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalLyricist", optional=true)
    protected static native NSString OriginalLyricistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseYear", optional=true)
    protected static native NSString OriginalReleaseYearValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileOwner", optional=true)
    protected static native NSString FileOwnerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLeadPerformer", optional=true)
    protected static native NSString LeadPerformerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBand", optional=true)
    protected static native NSString BandValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyConductor", optional=true)
    protected static native NSString ConductorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyModifiedBy", optional=true)
    protected static native NSString ModifiedByValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPartOfASet", optional=true)
    protected static native NSString PartOfASetValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyProducedNotice", optional=true)
    protected static native NSString ProducedNoticeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTrackNumber", optional=true)
    protected static native NSString TrackNumberValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingDates", optional=true)
    protected static native NSString RecordingDatesValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationName", optional=true)
    protected static native NSString InternetRadioStationNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationOwner", optional=true)
    protected static native NSString InternetRadioStationOwnerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySize", optional=true)
    protected static native NSString SizeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumSortOrder", optional=true)
    protected static native NSString AlbumSortOrderValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPerformerSortOrder", optional=true)
    protected static native NSString PerformerSortOrderValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleSortOrder", optional=true)
    protected static native NSString TitleSortOrderValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternationalStandardRecordingCode", optional=true)
    protected static native NSString InternationalStandardRecordingCodeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedWith", optional=true)
    protected static native NSString EncodedWithValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySetSubtitle", optional=true)
    protected static native NSString SetSubtitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyYear", optional=true)
    protected static native NSString YearValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserText", optional=true)
    protected static native NSString UserTextValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUniqueFileIdentifier", optional=true)
    protected static native NSString UniqueFileIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTermsOfUse", optional=true)
    protected static native NSString TermsOfUseValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUnsynchronizedLyric", optional=true)
    protected static native NSString UnsynchronizedLyricValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommercialInformation", optional=true)
    protected static native NSString CommercialInformationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyrightInformation", optional=true)
    protected static native NSString CopyrightInformationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioFileWebpage", optional=true)
    protected static native NSString OfficialAudioFileWebpageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialArtistWebpage", optional=true)
    protected static native NSString OfficialArtistWebpageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioSourceWebpage", optional=true)
    protected static native NSString OfficialAudioSourceWebpageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage", optional=true)
    protected static native NSString OfficialInternetRadioStationHomepageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPayment", optional=true)
    protected static native NSString PaymentValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialPublisherWebpage", optional=true)
    protected static native NSString OfficialPublisherWebpageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserURL", optional=true)
    protected static native NSString UserURLValue();
    /*</methods>*/
}
