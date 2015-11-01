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
@Marshaler(/*<name>*/AVMetadataKeyID3Metadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyID3Metadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataKeyID3Metadata/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataKeyID3Metadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataKeyID3Metadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataKeyID3Metadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataKeyID3Metadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataKeyID3Metadata o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AudioEncryption = new AVMetadataKeyID3Metadata("AudioEncryption");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AttachedPicture = new AVMetadataKeyID3Metadata("AttachedPicture");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AudioSeekPointIndex = new AVMetadataKeyID3Metadata("AudioSeekPointIndex");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Comments = new AVMetadataKeyID3Metadata("Comments");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Commercial = new AVMetadataKeyID3Metadata("Commercial");
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final AVMetadataKeyID3Metadata Commerical = new AVMetadataKeyID3Metadata("Commerical");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Encryption = new AVMetadataKeyID3Metadata("Encryption");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Equalization = new AVMetadataKeyID3Metadata("Equalization");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Equalization2 = new AVMetadataKeyID3Metadata("Equalization2");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EventTimingCodes = new AVMetadataKeyID3Metadata("EventTimingCodes");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata GeneralEncapsulatedObject = new AVMetadataKeyID3Metadata("GeneralEncapsulatedObject");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata GroupIdentifier = new AVMetadataKeyID3Metadata("GroupIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InvolvedPeopleList_v23 = new AVMetadataKeyID3Metadata("InvolvedPeopleList_v23");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Link = new AVMetadataKeyID3Metadata("Link");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MusicCDIdentifier = new AVMetadataKeyID3Metadata("MusicCDIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MPEGLocationLookupTable = new AVMetadataKeyID3Metadata("MPEGLocationLookupTable");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Ownership = new AVMetadataKeyID3Metadata("Ownership");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Private = new AVMetadataKeyID3Metadata("Private");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PlayCounter = new AVMetadataKeyID3Metadata("PlayCounter");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Popularimeter = new AVMetadataKeyID3Metadata("Popularimeter");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PositionSynchronization = new AVMetadataKeyID3Metadata("PositionSynchronization");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecommendedBufferSize = new AVMetadataKeyID3Metadata("RecommendedBufferSize");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RelativeVolumeAdjustment = new AVMetadataKeyID3Metadata("RelativeVolumeAdjustment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RelativeVolumeAdjustment2 = new AVMetadataKeyID3Metadata("RelativeVolumeAdjustment2");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Reverb = new AVMetadataKeyID3Metadata("Reverb");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Seek = new AVMetadataKeyID3Metadata("Seek");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Signature = new AVMetadataKeyID3Metadata("Signature");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SynchronizedLyric = new AVMetadataKeyID3Metadata("SynchronizedLyric");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SynchronizedTempoCodes = new AVMetadataKeyID3Metadata("SynchronizedTempoCodes");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AlbumTitle = new AVMetadataKeyID3Metadata("AlbumTitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata BeatsPerMinute = new AVMetadataKeyID3Metadata("BeatsPerMinute");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Composer = new AVMetadataKeyID3Metadata("Composer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ContentType = new AVMetadataKeyID3Metadata("ContentType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Copyright = new AVMetadataKeyID3Metadata("Copyright");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Date = new AVMetadataKeyID3Metadata("Date");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodingTime = new AVMetadataKeyID3Metadata("EncodingTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PlaylistDelay = new AVMetadataKeyID3Metadata("PlaylistDelay");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalReleaseTime = new AVMetadataKeyID3Metadata("OriginalReleaseTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecordingTime = new AVMetadataKeyID3Metadata("RecordingTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ReleaseTime = new AVMetadataKeyID3Metadata("ReleaseTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TaggingTime = new AVMetadataKeyID3Metadata("TaggingTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodedBy = new AVMetadataKeyID3Metadata("EncodedBy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Lyricist = new AVMetadataKeyID3Metadata("Lyricist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata FileType = new AVMetadataKeyID3Metadata("FileType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Time = new AVMetadataKeyID3Metadata("Time");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InvolvedPeopleList_v24 = new AVMetadataKeyID3Metadata("InvolvedPeopleList_v24");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ContentGroupDescription = new AVMetadataKeyID3Metadata("ContentGroupDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TitleDescription = new AVMetadataKeyID3Metadata("TitleDescription");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SubTitle = new AVMetadataKeyID3Metadata("SubTitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InitialKey = new AVMetadataKeyID3Metadata("InitialKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Language = new AVMetadataKeyID3Metadata("Language");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Length = new AVMetadataKeyID3Metadata("Length");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MusicianCreditsList = new AVMetadataKeyID3Metadata("MusicianCreditsList");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata MediaType = new AVMetadataKeyID3Metadata("MediaType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Mood = new AVMetadataKeyID3Metadata("Mood");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalAlbumTitle = new AVMetadataKeyID3Metadata("OriginalAlbumTitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalFilename = new AVMetadataKeyID3Metadata("OriginalFilename");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalLyricist = new AVMetadataKeyID3Metadata("OriginalLyricist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalArtist = new AVMetadataKeyID3Metadata("OriginalArtist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OriginalReleaseYear = new AVMetadataKeyID3Metadata("OriginalReleaseYear");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata FileOwner = new AVMetadataKeyID3Metadata("FileOwner");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata LeadPerformer = new AVMetadataKeyID3Metadata("LeadPerformer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Band = new AVMetadataKeyID3Metadata("Band");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Conductor = new AVMetadataKeyID3Metadata("Conductor");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ModifiedBy = new AVMetadataKeyID3Metadata("ModifiedBy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PartOfASet = new AVMetadataKeyID3Metadata("PartOfASet");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata ProducedNotice = new AVMetadataKeyID3Metadata("ProducedNotice");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Publisher = new AVMetadataKeyID3Metadata("Publisher");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TrackNumber = new AVMetadataKeyID3Metadata("TrackNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata RecordingDates = new AVMetadataKeyID3Metadata("RecordingDates");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternetRadioStationName = new AVMetadataKeyID3Metadata("InternetRadioStationName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternetRadioStationOwner = new AVMetadataKeyID3Metadata("InternetRadioStationOwner");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Size = new AVMetadataKeyID3Metadata("Size");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata AlbumSortOrder = new AVMetadataKeyID3Metadata("AlbumSortOrder");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata PerformerSortOrder = new AVMetadataKeyID3Metadata("PerformerSortOrder");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TitleSortOrder = new AVMetadataKeyID3Metadata("TitleSortOrder");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata InternationalStandardRecordingCode = new AVMetadataKeyID3Metadata("InternationalStandardRecordingCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata EncodedWith = new AVMetadataKeyID3Metadata("EncodedWith");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata SetSubtitle = new AVMetadataKeyID3Metadata("SetSubtitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Year = new AVMetadataKeyID3Metadata("Year");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UserText = new AVMetadataKeyID3Metadata("UserText");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UniqueFileIdentifier = new AVMetadataKeyID3Metadata("UniqueFileIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata TermsOfUse = new AVMetadataKeyID3Metadata("TermsOfUse");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UnsynchronizedLyric = new AVMetadataKeyID3Metadata("UnsynchronizedLyric");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata CommercialInformation = new AVMetadataKeyID3Metadata("CommercialInformation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata CopyrightInformation = new AVMetadataKeyID3Metadata("CopyrightInformation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialAudioFileWebpage = new AVMetadataKeyID3Metadata("OfficialAudioFileWebpage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialArtistWebpage = new AVMetadataKeyID3Metadata("OfficialArtistWebpage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialAudioSourceWebpage = new AVMetadataKeyID3Metadata("OfficialAudioSourceWebpage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialInternetRadioStationHomepage = new AVMetadataKeyID3Metadata("OfficialInternetRadioStationHomepage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata Payment = new AVMetadataKeyID3Metadata("Payment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata OfficialPublisherWebpage = new AVMetadataKeyID3Metadata("OfficialPublisherWebpage");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyID3Metadata UserURL = new AVMetadataKeyID3Metadata("UserURL");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataKeyID3Metadata/*</name>*/[] values = new /*<name>*/AVMetadataKeyID3Metadata/*</name>*/[] {/*<value_list>*/AudioEncryption, AttachedPicture, AudioSeekPointIndex, Comments, Commercial, Commerical, Encryption, Equalization, Equalization2, EventTimingCodes, GeneralEncapsulatedObject, GroupIdentifier, InvolvedPeopleList_v23, Link, MusicCDIdentifier, MPEGLocationLookupTable, Ownership, Private, PlayCounter, Popularimeter, PositionSynchronization, RecommendedBufferSize, RelativeVolumeAdjustment, RelativeVolumeAdjustment2, Reverb, Seek, Signature, SynchronizedLyric, SynchronizedTempoCodes, AlbumTitle, BeatsPerMinute, Composer, ContentType, Copyright, Date, EncodingTime, PlaylistDelay, OriginalReleaseTime, RecordingTime, ReleaseTime, TaggingTime, EncodedBy, Lyricist, FileType, Time, InvolvedPeopleList_v24, ContentGroupDescription, TitleDescription, SubTitle, InitialKey, Language, Length, MusicianCreditsList, MediaType, Mood, OriginalAlbumTitle, OriginalFilename, OriginalLyricist, OriginalArtist, OriginalReleaseYear, FileOwner, LeadPerformer, Band, Conductor, ModifiedBy, PartOfASet, ProducedNotice, Publisher, TrackNumber, RecordingDates, InternetRadioStationName, InternetRadioStationOwner, Size, AlbumSortOrder, PerformerSortOrder, TitleSortOrder, InternationalStandardRecordingCode, EncodedWith, SetSubtitle, Year, UserText, UniqueFileIdentifier, TermsOfUse, UnsynchronizedLyric, CommercialInformation, CopyrightInformation, OfficialAudioFileWebpage, OfficialArtistWebpage, OfficialAudioSourceWebpage, OfficialInternetRadioStationHomepage, Payment, OfficialPublisherWebpage, UserURL/*</value_list>*/};
    
    /*<name>*/AVMetadataKeyID3Metadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataKeyID3Metadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataKeyID3Metadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeyID3Metadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioEncryption", optional=true)
        public static native NSString AudioEncryption();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyAttachedPicture", optional=true)
        public static native NSString AttachedPicture();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioSeekPointIndex", optional=true)
        public static native NSString AudioSeekPointIndex();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyComments", optional=true)
        public static native NSString Comments();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyCommercial", optional=true)
        public static native NSString Commercial();
        /**
         * @since Available in iOS 4.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="AVMetadataID3MetadataKeyCommerical", optional=true)
        public static native NSString Commerical();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEncryption", optional=true)
        public static native NSString Encryption();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization", optional=true)
        public static native NSString Equalization();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization2", optional=true)
        public static native NSString Equalization2();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEventTimingCodes", optional=true)
        public static native NSString EventTimingCodes();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyGeneralEncapsulatedObject", optional=true)
        public static native NSString GeneralEncapsulatedObject();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyGroupIdentifier", optional=true)
        public static native NSString GroupIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v23", optional=true)
        public static native NSString InvolvedPeopleList_v23();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyLink", optional=true)
        public static native NSString Link();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicCDIdentifier", optional=true)
        public static native NSString MusicCDIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyMPEGLocationLookupTable", optional=true)
        public static native NSString MPEGLocationLookupTable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOwnership", optional=true)
        public static native NSString Ownership();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPrivate", optional=true)
        public static native NSString Private();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPlayCounter", optional=true)
        public static native NSString PlayCounter();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPopularimeter", optional=true)
        public static native NSString Popularimeter();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPositionSynchronization", optional=true)
        public static native NSString PositionSynchronization();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyRecommendedBufferSize", optional=true)
        public static native NSString RecommendedBufferSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment", optional=true)
        public static native NSString RelativeVolumeAdjustment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment2", optional=true)
        public static native NSString RelativeVolumeAdjustment2();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyReverb", optional=true)
        public static native NSString Reverb();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySeek", optional=true)
        public static native NSString Seek();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySignature", optional=true)
        public static native NSString Signature();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedLyric", optional=true)
        public static native NSString SynchronizedLyric();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedTempoCodes", optional=true)
        public static native NSString SynchronizedTempoCodes();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumTitle", optional=true)
        public static native NSString AlbumTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyBeatsPerMinute", optional=true)
        public static native NSString BeatsPerMinute();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyContentType", optional=true)
        public static native NSString ContentType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyDate", optional=true)
        public static native NSString Date();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodingTime", optional=true)
        public static native NSString EncodingTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPlaylistDelay", optional=true)
        public static native NSString PlaylistDelay();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseTime", optional=true)
        public static native NSString OriginalReleaseTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingTime", optional=true)
        public static native NSString RecordingTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyReleaseTime", optional=true)
        public static native NSString ReleaseTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTaggingTime", optional=true)
        public static native NSString TaggingTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyLyricist", optional=true)
        public static native NSString Lyricist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyFileType", optional=true)
        public static native NSString FileType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTime", optional=true)
        public static native NSString Time();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v24", optional=true)
        public static native NSString InvolvedPeopleList_v24();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyContentGroupDescription", optional=true)
        public static native NSString ContentGroupDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleDescription", optional=true)
        public static native NSString TitleDescription();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySubTitle", optional=true)
        public static native NSString SubTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInitialKey", optional=true)
        public static native NSString InitialKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyLanguage", optional=true)
        public static native NSString Language();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyLength", optional=true)
        public static native NSString Length();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicianCreditsList", optional=true)
        public static native NSString MusicianCreditsList();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyMediaType", optional=true)
        public static native NSString MediaType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyMood", optional=true)
        public static native NSString Mood();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalAlbumTitle", optional=true)
        public static native NSString OriginalAlbumTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalFilename", optional=true)
        public static native NSString OriginalFilename();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalLyricist", optional=true)
        public static native NSString OriginalLyricist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseYear", optional=true)
        public static native NSString OriginalReleaseYear();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyFileOwner", optional=true)
        public static native NSString FileOwner();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyLeadPerformer", optional=true)
        public static native NSString LeadPerformer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyBand", optional=true)
        public static native NSString Band();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyConductor", optional=true)
        public static native NSString Conductor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyModifiedBy", optional=true)
        public static native NSString ModifiedBy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPartOfASet", optional=true)
        public static native NSString PartOfASet();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyProducedNotice", optional=true)
        public static native NSString ProducedNotice();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTrackNumber", optional=true)
        public static native NSString TrackNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingDates", optional=true)
        public static native NSString RecordingDates();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationName", optional=true)
        public static native NSString InternetRadioStationName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationOwner", optional=true)
        public static native NSString InternetRadioStationOwner();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySize", optional=true)
        public static native NSString Size();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumSortOrder", optional=true)
        public static native NSString AlbumSortOrder();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPerformerSortOrder", optional=true)
        public static native NSString PerformerSortOrder();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleSortOrder", optional=true)
        public static native NSString TitleSortOrder();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyInternationalStandardRecordingCode", optional=true)
        public static native NSString InternationalStandardRecordingCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedWith", optional=true)
        public static native NSString EncodedWith();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeySetSubtitle", optional=true)
        public static native NSString SetSubtitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyYear", optional=true)
        public static native NSString Year();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyUserText", optional=true)
        public static native NSString UserText();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyUniqueFileIdentifier", optional=true)
        public static native NSString UniqueFileIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyTermsOfUse", optional=true)
        public static native NSString TermsOfUse();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyUnsynchronizedLyric", optional=true)
        public static native NSString UnsynchronizedLyric();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyCommercialInformation", optional=true)
        public static native NSString CommercialInformation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyrightInformation", optional=true)
        public static native NSString CopyrightInformation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioFileWebpage", optional=true)
        public static native NSString OfficialAudioFileWebpage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialArtistWebpage", optional=true)
        public static native NSString OfficialArtistWebpage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioSourceWebpage", optional=true)
        public static native NSString OfficialAudioSourceWebpage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage", optional=true)
        public static native NSString OfficialInternetRadioStationHomepage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyPayment", optional=true)
        public static native NSString Payment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialPublisherWebpage", optional=true)
        public static native NSString OfficialPublisherWebpage();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataID3MetadataKeyUserURL", optional=true)
        public static native NSString UserURL();
        /*</values>*/
    }
}
