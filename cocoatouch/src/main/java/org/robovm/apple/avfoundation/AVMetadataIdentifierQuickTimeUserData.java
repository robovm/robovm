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
@Marshaler(AVMetadataIdentifierQuickTimeUserData.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class AVMetadataIdentifierQuickTimeUserData 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifierQuickTimeUserData toObject(Class<AVMetadataIdentifierQuickTimeUserData> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifierQuickTimeUserData.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifierQuickTimeUserData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifierQuickTimeUserData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Album = new AVMetadataIdentifierQuickTimeUserData("AlbumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Arranger = new AVMetadataIdentifierQuickTimeUserData("ArrangerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Artist = new AVMetadataIdentifierQuickTimeUserData("ArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Author = new AVMetadataIdentifierQuickTimeUserData("AuthorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Chapter = new AVMetadataIdentifierQuickTimeUserData("ChapterValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Comment = new AVMetadataIdentifierQuickTimeUserData("CommentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Composer = new AVMetadataIdentifierQuickTimeUserData("ComposerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Copyright = new AVMetadataIdentifierQuickTimeUserData("CopyrightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData CreationDate = new AVMetadataIdentifierQuickTimeUserData("CreationDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Description = new AVMetadataIdentifierQuickTimeUserData("DescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Director = new AVMetadataIdentifierQuickTimeUserData("DirectorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Disclaimer = new AVMetadataIdentifierQuickTimeUserData("DisclaimerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData EncodedBy = new AVMetadataIdentifierQuickTimeUserData("EncodedByValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData FullName = new AVMetadataIdentifierQuickTimeUserData("FullNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Genre = new AVMetadataIdentifierQuickTimeUserData("GenreValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData HostComputer = new AVMetadataIdentifierQuickTimeUserData("HostComputerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Information = new AVMetadataIdentifierQuickTimeUserData("InformationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Keywords = new AVMetadataIdentifierQuickTimeUserData("KeywordsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Make = new AVMetadataIdentifierQuickTimeUserData("MakeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Model = new AVMetadataIdentifierQuickTimeUserData("ModelValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalArtist = new AVMetadataIdentifierQuickTimeUserData("OriginalArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalFormat = new AVMetadataIdentifierQuickTimeUserData("OriginalFormatValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalSource = new AVMetadataIdentifierQuickTimeUserData("OriginalSourceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Performers = new AVMetadataIdentifierQuickTimeUserData("PerformersValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Producer = new AVMetadataIdentifierQuickTimeUserData("ProducerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Publisher = new AVMetadataIdentifierQuickTimeUserData("PublisherValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Product = new AVMetadataIdentifierQuickTimeUserData("ProductValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Software = new AVMetadataIdentifierQuickTimeUserData("SoftwareValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData SpecialPlaybackRequirements = new AVMetadataIdentifierQuickTimeUserData("SpecialPlaybackRequirementsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Track = new AVMetadataIdentifierQuickTimeUserData("TrackValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Warning = new AVMetadataIdentifierQuickTimeUserData("WarningValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Writer = new AVMetadataIdentifierQuickTimeUserData("WriterValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData URLLink = new AVMetadataIdentifierQuickTimeUserData("URLLinkValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData LocationISO6709 = new AVMetadataIdentifierQuickTimeUserData("LocationISO6709Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData TrackName = new AVMetadataIdentifierQuickTimeUserData("TrackNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Credits = new AVMetadataIdentifierQuickTimeUserData("CreditsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData PhonogramRights = new AVMetadataIdentifierQuickTimeUserData("PhonogramRightsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData TaggedCharacteristic = new AVMetadataIdentifierQuickTimeUserData("TaggedCharacteristicValue");
    
    private static AVMetadataIdentifierQuickTimeUserData[] values = new AVMetadataIdentifierQuickTimeUserData[] {Album, Arranger, Artist, Author, Chapter, 
        Comment, Composer, Copyright, CreationDate, Description, Director, Disclaimer, EncodedBy, FullName, Genre, HostComputer, 
        Information, Keywords, Make, Model, OriginalArtist, OriginalFormat, OriginalSource, Performers, Producer, Publisher, Product, 
        Software, SpecialPlaybackRequirements, Track, Warning, Writer, URLLink, LocationISO6709, TrackName, Credits, PhonogramRights, 
        TaggedCharacteristic};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifierQuickTimeUserData(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifierQuickTimeUserData valueOf(NSString value) {
        for (AVMetadataIdentifierQuickTimeUserData v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataChapter", optional=true)
    protected static native NSString ChapterValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComment", optional=true)
    protected static native NSString CommentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDisclaimer", optional=true)
    protected static native NSString DisclaimerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataFullName", optional=true)
    protected static native NSString FullNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataHostComputer", optional=true)
    protected static native NSString HostComputerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataInformation", optional=true)
    protected static native NSString InformationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataKeywords", optional=true)
    protected static native NSString KeywordsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalFormat", optional=true)
    protected static native NSString OriginalFormatValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalSource", optional=true)
    protected static native NSString OriginalSourceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPerformers", optional=true)
    protected static native NSString PerformersValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProduct", optional=true)
    protected static native NSString ProductValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSoftware", optional=true)
    protected static native NSString SoftwareValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSpecialPlaybackRequirements", optional=true)
    protected static native NSString SpecialPlaybackRequirementsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrack", optional=true)
    protected static native NSString TrackValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWarning", optional=true)
    protected static native NSString WarningValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWriter", optional=true)
    protected static native NSString WriterValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataURLLink", optional=true)
    protected static native NSString URLLinkValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataLocationISO6709", optional=true)
    protected static native NSString LocationISO6709Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrackName", optional=true)
    protected static native NSString TrackNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTaggedCharacteristic", optional=true)
    protected static native NSString TaggedCharacteristicValue();
    /*</methods>*/
}
