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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataIdentifierID3Metadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataIdentifierID3Metadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataIdentifierID3Metadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataIdentifierID3Metadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataIdentifierID3Metadata o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AudioEncryption = new AVMetadataIdentifierID3Metadata("AudioEncryption");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AttachedPicture = new AVMetadataIdentifierID3Metadata("AttachedPicture");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AudioSeekPointIndex = new AVMetadataIdentifierID3Metadata("AudioSeekPointIndex");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Comments = new AVMetadataIdentifierID3Metadata("Comments");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Commercial = new AVMetadataIdentifierID3Metadata("Commercial");
    /**
     * @since Available in iOS 8.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final AVMetadataIdentifierID3Metadata Commerical = new AVMetadataIdentifierID3Metadata("Commerical");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Encryption = new AVMetadataIdentifierID3Metadata("Encryption");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Equalization = new AVMetadataIdentifierID3Metadata("Equalization");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Equalization2 = new AVMetadataIdentifierID3Metadata("Equalization2");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EventTimingCodes = new AVMetadataIdentifierID3Metadata("EventTimingCodes");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata GeneralEncapsulatedObject = new AVMetadataIdentifierID3Metadata("GeneralEncapsulatedObject");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata GroupIdentifier = new AVMetadataIdentifierID3Metadata("GroupIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InvolvedPeopleList_v23 = new AVMetadataIdentifierID3Metadata("InvolvedPeopleList_v23");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Link = new AVMetadataIdentifierID3Metadata("Link");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MusicCDIdentifier = new AVMetadataIdentifierID3Metadata("MusicCDIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MPEGLocationLookupTable = new AVMetadataIdentifierID3Metadata("MPEGLocationLookupTable");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Ownership = new AVMetadataIdentifierID3Metadata("Ownership");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Private = new AVMetadataIdentifierID3Metadata("Private");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PlayCounter = new AVMetadataIdentifierID3Metadata("PlayCounter");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Popularimeter = new AVMetadataIdentifierID3Metadata("Popularimeter");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PositionSynchronization = new AVMetadataIdentifierID3Metadata("PositionSynchronization");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecommendedBufferSize = new AVMetadataIdentifierID3Metadata("RecommendedBufferSize");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RelativeVolumeAdjustment = new AVMetadataIdentifierID3Metadata("RelativeVolumeAdjustment");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RelativeVolumeAdjustment2 = new AVMetadataIdentifierID3Metadata("RelativeVolumeAdjustment2");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Reverb = new AVMetadataIdentifierID3Metadata("Reverb");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Seek = new AVMetadataIdentifierID3Metadata("Seek");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Signature = new AVMetadataIdentifierID3Metadata("Signature");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SynchronizedLyric = new AVMetadataIdentifierID3Metadata("SynchronizedLyric");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SynchronizedTempoCodes = new AVMetadataIdentifierID3Metadata("SynchronizedTempoCodes");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AlbumTitle = new AVMetadataIdentifierID3Metadata("AlbumTitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata BeatsPerMinute = new AVMetadataIdentifierID3Metadata("BeatsPerMinute");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Composer = new AVMetadataIdentifierID3Metadata("Composer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ContentType = new AVMetadataIdentifierID3Metadata("ContentType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Copyright = new AVMetadataIdentifierID3Metadata("Copyright");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Date = new AVMetadataIdentifierID3Metadata("Date");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodingTime = new AVMetadataIdentifierID3Metadata("EncodingTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PlaylistDelay = new AVMetadataIdentifierID3Metadata("PlaylistDelay");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalReleaseTime = new AVMetadataIdentifierID3Metadata("OriginalReleaseTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecordingTime = new AVMetadataIdentifierID3Metadata("RecordingTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ReleaseTime = new AVMetadataIdentifierID3Metadata("ReleaseTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TaggingTime = new AVMetadataIdentifierID3Metadata("TaggingTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodedBy = new AVMetadataIdentifierID3Metadata("EncodedBy");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Lyricist = new AVMetadataIdentifierID3Metadata("Lyricist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata FileType = new AVMetadataIdentifierID3Metadata("FileType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Time = new AVMetadataIdentifierID3Metadata("Time");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InvolvedPeopleList_v24 = new AVMetadataIdentifierID3Metadata("InvolvedPeopleList_v24");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ContentGroupDescription = new AVMetadataIdentifierID3Metadata("ContentGroupDescription");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TitleDescription = new AVMetadataIdentifierID3Metadata("TitleDescription");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SubTitle = new AVMetadataIdentifierID3Metadata("SubTitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InitialKey = new AVMetadataIdentifierID3Metadata("InitialKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Language = new AVMetadataIdentifierID3Metadata("Language");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Length = new AVMetadataIdentifierID3Metadata("Length");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MusicianCreditsList = new AVMetadataIdentifierID3Metadata("MusicianCreditsList");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata MediaType = new AVMetadataIdentifierID3Metadata("MediaType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Mood = new AVMetadataIdentifierID3Metadata("Mood");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalAlbumTitle = new AVMetadataIdentifierID3Metadata("OriginalAlbumTitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalFilename = new AVMetadataIdentifierID3Metadata("OriginalFilename");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalLyricist = new AVMetadataIdentifierID3Metadata("OriginalLyricist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalArtist = new AVMetadataIdentifierID3Metadata("OriginalArtist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OriginalReleaseYear = new AVMetadataIdentifierID3Metadata("OriginalReleaseYear");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata FileOwner = new AVMetadataIdentifierID3Metadata("FileOwner");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata LeadPerformer = new AVMetadataIdentifierID3Metadata("LeadPerformer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Band = new AVMetadataIdentifierID3Metadata("Band");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Conductor = new AVMetadataIdentifierID3Metadata("Conductor");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ModifiedBy = new AVMetadataIdentifierID3Metadata("ModifiedBy");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PartOfASet = new AVMetadataIdentifierID3Metadata("PartOfASet");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata ProducedNotice = new AVMetadataIdentifierID3Metadata("ProducedNotice");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Publisher = new AVMetadataIdentifierID3Metadata("Publisher");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TrackNumber = new AVMetadataIdentifierID3Metadata("TrackNumber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata RecordingDates = new AVMetadataIdentifierID3Metadata("RecordingDates");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternetRadioStationName = new AVMetadataIdentifierID3Metadata("InternetRadioStationName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternetRadioStationOwner = new AVMetadataIdentifierID3Metadata("InternetRadioStationOwner");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Size = new AVMetadataIdentifierID3Metadata("Size");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata AlbumSortOrder = new AVMetadataIdentifierID3Metadata("AlbumSortOrder");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata PerformerSortOrder = new AVMetadataIdentifierID3Metadata("PerformerSortOrder");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TitleSortOrder = new AVMetadataIdentifierID3Metadata("TitleSortOrder");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata InternationalStandardRecordingCode = new AVMetadataIdentifierID3Metadata("InternationalStandardRecordingCode");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata EncodedWith = new AVMetadataIdentifierID3Metadata("EncodedWith");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata SetSubtitle = new AVMetadataIdentifierID3Metadata("SetSubtitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Year = new AVMetadataIdentifierID3Metadata("Year");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UserText = new AVMetadataIdentifierID3Metadata("UserText");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UniqueFileIdentifier = new AVMetadataIdentifierID3Metadata("UniqueFileIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata TermsOfUse = new AVMetadataIdentifierID3Metadata("TermsOfUse");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UnsynchronizedLyric = new AVMetadataIdentifierID3Metadata("UnsynchronizedLyric");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata CommercialInformation = new AVMetadataIdentifierID3Metadata("CommercialInformation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata CopyrightInformation = new AVMetadataIdentifierID3Metadata("CopyrightInformation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialAudioFileWebpage = new AVMetadataIdentifierID3Metadata("OfficialAudioFileWebpage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialArtistWebpage = new AVMetadataIdentifierID3Metadata("OfficialArtistWebpage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialAudioSourceWebpage = new AVMetadataIdentifierID3Metadata("OfficialAudioSourceWebpage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialInternetRadioStationHomepage = new AVMetadataIdentifierID3Metadata("OfficialInternetRadioStationHomepage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata Payment = new AVMetadataIdentifierID3Metadata("Payment");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata OfficialPublisherWebpage = new AVMetadataIdentifierID3Metadata("OfficialPublisherWebpage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierID3Metadata UserURL = new AVMetadataIdentifierID3Metadata("UserURL");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/[] values = new /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/[] {/*<value_list>*/AudioEncryption, AttachedPicture, AudioSeekPointIndex, Comments, Commercial, Commerical, Encryption, Equalization, Equalization2, EventTimingCodes, GeneralEncapsulatedObject, GroupIdentifier, InvolvedPeopleList_v23, Link, MusicCDIdentifier, MPEGLocationLookupTable, Ownership, Private, PlayCounter, Popularimeter, PositionSynchronization, RecommendedBufferSize, RelativeVolumeAdjustment, RelativeVolumeAdjustment2, Reverb, Seek, Signature, SynchronizedLyric, SynchronizedTempoCodes, AlbumTitle, BeatsPerMinute, Composer, ContentType, Copyright, Date, EncodingTime, PlaylistDelay, OriginalReleaseTime, RecordingTime, ReleaseTime, TaggingTime, EncodedBy, Lyricist, FileType, Time, InvolvedPeopleList_v24, ContentGroupDescription, TitleDescription, SubTitle, InitialKey, Language, Length, MusicianCreditsList, MediaType, Mood, OriginalAlbumTitle, OriginalFilename, OriginalLyricist, OriginalArtist, OriginalReleaseYear, FileOwner, LeadPerformer, Band, Conductor, ModifiedBy, PartOfASet, ProducedNotice, Publisher, TrackNumber, RecordingDates, InternetRadioStationName, InternetRadioStationOwner, Size, AlbumSortOrder, PerformerSortOrder, TitleSortOrder, InternationalStandardRecordingCode, EncodedWith, SetSubtitle, Year, UserText, UniqueFileIdentifier, TermsOfUse, UnsynchronizedLyric, CommercialInformation, CopyrightInformation, OfficialAudioFileWebpage, OfficialArtistWebpage, OfficialAudioSourceWebpage, OfficialInternetRadioStationHomepage, Payment, OfficialPublisherWebpage, UserURL/*</value_list>*/};
    
    /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataIdentifierID3Metadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioEncryption", optional=true)
        public static native NSString AudioEncryption();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAttachedPicture", optional=true)
        public static native NSString AttachedPicture();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAudioSeekPointIndex", optional=true)
        public static native NSString AudioSeekPointIndex();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComments", optional=true)
        public static native NSString Comments();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommercial", optional=true)
        public static native NSString Commercial();
        /**
         * @since Available in iOS 8.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommerical", optional=true)
        public static native NSString Commerical();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncryption", optional=true)
        public static native NSString Encryption();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization", optional=true)
        public static native NSString Equalization();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEqualization2", optional=true)
        public static native NSString Equalization2();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEventTimingCodes", optional=true)
        public static native NSString EventTimingCodes();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGeneralEncapsulatedObject", optional=true)
        public static native NSString GeneralEncapsulatedObject();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataGroupIdentifier", optional=true)
        public static native NSString GroupIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v23", optional=true)
        public static native NSString InvolvedPeopleList_v23();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLink", optional=true)
        public static native NSString Link();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicCDIdentifier", optional=true)
        public static native NSString MusicCDIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMPEGLocationLookupTable", optional=true)
        public static native NSString MPEGLocationLookupTable();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOwnership", optional=true)
        public static native NSString Ownership();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPrivate", optional=true)
        public static native NSString Private();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlayCounter", optional=true)
        public static native NSString PlayCounter();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPopularimeter", optional=true)
        public static native NSString Popularimeter();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPositionSynchronization", optional=true)
        public static native NSString PositionSynchronization();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecommendedBufferSize", optional=true)
        public static native NSString RecommendedBufferSize();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment", optional=true)
        public static native NSString RelativeVolumeAdjustment();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRelativeVolumeAdjustment2", optional=true)
        public static native NSString RelativeVolumeAdjustment2();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReverb", optional=true)
        public static native NSString Reverb();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSeek", optional=true)
        public static native NSString Seek();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSignature", optional=true)
        public static native NSString Signature();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedLyric", optional=true)
        public static native NSString SynchronizedLyric();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSynchronizedTempoCodes", optional=true)
        public static native NSString SynchronizedTempoCodes();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumTitle", optional=true)
        public static native NSString AlbumTitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBeatsPerMinute", optional=true)
        public static native NSString BeatsPerMinute();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentType", optional=true)
        public static native NSString ContentType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataDate", optional=true)
        public static native NSString Date();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodingTime", optional=true)
        public static native NSString EncodingTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPlaylistDelay", optional=true)
        public static native NSString PlaylistDelay();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseTime", optional=true)
        public static native NSString OriginalReleaseTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingTime", optional=true)
        public static native NSString RecordingTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataReleaseTime", optional=true)
        public static native NSString ReleaseTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTaggingTime", optional=true)
        public static native NSString TaggingTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLyricist", optional=true)
        public static native NSString Lyricist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileType", optional=true)
        public static native NSString FileType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTime", optional=true)
        public static native NSString Time();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInvolvedPeopleList_v24", optional=true)
        public static native NSString InvolvedPeopleList_v24();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataContentGroupDescription", optional=true)
        public static native NSString ContentGroupDescription();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleDescription", optional=true)
        public static native NSString TitleDescription();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSubTitle", optional=true)
        public static native NSString SubTitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInitialKey", optional=true)
        public static native NSString InitialKey();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLanguage", optional=true)
        public static native NSString Language();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLength", optional=true)
        public static native NSString Length();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMusicianCreditsList", optional=true)
        public static native NSString MusicianCreditsList();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMediaType", optional=true)
        public static native NSString MediaType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataMood", optional=true)
        public static native NSString Mood();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalAlbumTitle", optional=true)
        public static native NSString OriginalAlbumTitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalFilename", optional=true)
        public static native NSString OriginalFilename();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalLyricist", optional=true)
        public static native NSString OriginalLyricist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOriginalReleaseYear", optional=true)
        public static native NSString OriginalReleaseYear();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataFileOwner", optional=true)
        public static native NSString FileOwner();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataLeadPerformer", optional=true)
        public static native NSString LeadPerformer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataBand", optional=true)
        public static native NSString Band();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataConductor", optional=true)
        public static native NSString Conductor();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataModifiedBy", optional=true)
        public static native NSString ModifiedBy();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPartOfASet", optional=true)
        public static native NSString PartOfASet();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataProducedNotice", optional=true)
        public static native NSString ProducedNotice();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTrackNumber", optional=true)
        public static native NSString TrackNumber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataRecordingDates", optional=true)
        public static native NSString RecordingDates();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationName", optional=true)
        public static native NSString InternetRadioStationName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternetRadioStationOwner", optional=true)
        public static native NSString InternetRadioStationOwner();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSize", optional=true)
        public static native NSString Size();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataAlbumSortOrder", optional=true)
        public static native NSString AlbumSortOrder();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPerformerSortOrder", optional=true)
        public static native NSString PerformerSortOrder();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTitleSortOrder", optional=true)
        public static native NSString TitleSortOrder();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataInternationalStandardRecordingCode", optional=true)
        public static native NSString InternationalStandardRecordingCode();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataEncodedWith", optional=true)
        public static native NSString EncodedWith();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataSetSubtitle", optional=true)
        public static native NSString SetSubtitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataYear", optional=true)
        public static native NSString Year();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserText", optional=true)
        public static native NSString UserText();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUniqueFileIdentifier", optional=true)
        public static native NSString UniqueFileIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataTermsOfUse", optional=true)
        public static native NSString TermsOfUse();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUnsynchronizedLyric", optional=true)
        public static native NSString UnsynchronizedLyric();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCommercialInformation", optional=true)
        public static native NSString CommercialInformation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataCopyrightInformation", optional=true)
        public static native NSString CopyrightInformation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioFileWebpage", optional=true)
        public static native NSString OfficialAudioFileWebpage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialArtistWebpage", optional=true)
        public static native NSString OfficialArtistWebpage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialAudioSourceWebpage", optional=true)
        public static native NSString OfficialAudioSourceWebpage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialInternetRadioStationHomepage", optional=true)
        public static native NSString OfficialInternetRadioStationHomepage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataPayment", optional=true)
        public static native NSString Payment();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataOfficialPublisherWebpage", optional=true)
        public static native NSString OfficialPublisherWebpage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierID3MetadataUserURL", optional=true)
        public static native NSString UserURL();
        /*</values>*/
    }
}
