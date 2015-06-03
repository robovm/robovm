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
@Marshaler(AVMetadataKeyQuickTimeUserData.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyQuickTimeUserData toObject(Class<AVMetadataKeyQuickTimeUserData> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyQuickTimeUserData.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyQuickTimeUserData o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeyQuickTimeUserData.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Album = new AVMetadataKeyQuickTimeUserData("AlbumValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Arranger = new AVMetadataKeyQuickTimeUserData("ArrangerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Artist = new AVMetadataKeyQuickTimeUserData("ArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Author = new AVMetadataKeyQuickTimeUserData("AuthorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Chapter = new AVMetadataKeyQuickTimeUserData("ChapterValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Comment = new AVMetadataKeyQuickTimeUserData("CommentValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Composer = new AVMetadataKeyQuickTimeUserData("ComposerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Copyright = new AVMetadataKeyQuickTimeUserData("CopyrightValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData CreationDate = new AVMetadataKeyQuickTimeUserData("CreationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Description = new AVMetadataKeyQuickTimeUserData("DescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Director = new AVMetadataKeyQuickTimeUserData("DirectorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Disclaimer = new AVMetadataKeyQuickTimeUserData("DisclaimerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData EncodedBy = new AVMetadataKeyQuickTimeUserData("EncodedByValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData FullName = new AVMetadataKeyQuickTimeUserData("FullNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Genre = new AVMetadataKeyQuickTimeUserData("GenreValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData HostComputer = new AVMetadataKeyQuickTimeUserData("HostComputerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Information = new AVMetadataKeyQuickTimeUserData("InformationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Keywords = new AVMetadataKeyQuickTimeUserData("KeywordsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Make = new AVMetadataKeyQuickTimeUserData("MakeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Model = new AVMetadataKeyQuickTimeUserData("ModelValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalArtist = new AVMetadataKeyQuickTimeUserData("OriginalArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalFormat = new AVMetadataKeyQuickTimeUserData("OriginalFormatValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalSource = new AVMetadataKeyQuickTimeUserData("OriginalSourceValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Performers = new AVMetadataKeyQuickTimeUserData("PerformersValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Producer = new AVMetadataKeyQuickTimeUserData("ProducerValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Publisher = new AVMetadataKeyQuickTimeUserData("PublisherValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Product = new AVMetadataKeyQuickTimeUserData("ProductValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Software = new AVMetadataKeyQuickTimeUserData("SoftwareValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData SpecialPlaybackRequirements = new AVMetadataKeyQuickTimeUserData("SpecialPlaybackRequirementsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Track = new AVMetadataKeyQuickTimeUserData("TrackValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Warning = new AVMetadataKeyQuickTimeUserData("WarningValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Writer = new AVMetadataKeyQuickTimeUserData("WriterValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData URLLink = new AVMetadataKeyQuickTimeUserData("URLLinkValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData LocationISO6709 = new AVMetadataKeyQuickTimeUserData("LocationISO6709Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData TrackName = new AVMetadataKeyQuickTimeUserData("TrackNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Credits = new AVMetadataKeyQuickTimeUserData("CreditsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData PhonogramRights = new AVMetadataKeyQuickTimeUserData("PhonogramRightsValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData TaggedCharacteristic = new AVMetadataKeyQuickTimeUserData("TaggedCharacteristicValue");
    
    private static AVMetadataKeyQuickTimeUserData[] values = new AVMetadataKeyQuickTimeUserData[] {Album, Arranger, Artist, Author, Chapter, 
        Comment, Composer, Copyright, CreationDate, Description, Director, Disclaimer, EncodedBy, FullName, Genre, HostComputer, 
        Information, Keywords, Make, Model, OriginalArtist, OriginalFormat, OriginalSource, Performers, Producer, Publisher, Product, 
        Software, SpecialPlaybackRequirements, Track, Warning, Writer, URLLink, LocationISO6709, TrackName, Credits, PhonogramRights, 
        TaggedCharacteristic};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKeyQuickTimeUserData(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeyQuickTimeUserData valueOf(NSString value) {
        for (AVMetadataKeyQuickTimeUserData v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAlbum", optional=true)
    protected static native NSString AlbumValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArranger", optional=true)
    protected static native NSString ArrangerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyChapter", optional=true)
    protected static native NSString ChapterValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComment", optional=true)
    protected static native NSString CommentValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComposer", optional=true)
    protected static native NSString ComposerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCopyright", optional=true)
    protected static native NSString CopyrightValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDirector", optional=true)
    protected static native NSString DirectorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDisclaimer", optional=true)
    protected static native NSString DisclaimerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyEncodedBy", optional=true)
    protected static native NSString EncodedByValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyFullName", optional=true)
    protected static native NSString FullNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyGenre", optional=true)
    protected static native NSString GenreValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyHostComputer", optional=true)
    protected static native NSString HostComputerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyInformation", optional=true)
    protected static native NSString InformationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyKeywords", optional=true)
    protected static native NSString KeywordsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalArtist", optional=true)
    protected static native NSString OriginalArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalFormat", optional=true)
    protected static native NSString OriginalFormatValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalSource", optional=true)
    protected static native NSString OriginalSourceValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPerformers", optional=true)
    protected static native NSString PerformersValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProducer", optional=true)
    protected static native NSString ProducerValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProduct", optional=true)
    protected static native NSString ProductValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySoftware", optional=true)
    protected static native NSString SoftwareValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements", optional=true)
    protected static native NSString SpecialPlaybackRequirementsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrack", optional=true)
    protected static native NSString TrackValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWarning", optional=true)
    protected static native NSString WarningValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWriter", optional=true)
    protected static native NSString WriterValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyURLLink", optional=true)
    protected static native NSString URLLinkValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyLocationISO6709", optional=true)
    protected static native NSString LocationISO6709Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrackName", optional=true)
    protected static native NSString TrackNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCredits", optional=true)
    protected static native NSString CreditsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPhonogramRights", optional=true)
    protected static native NSString PhonogramRightsValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTaggedCharacteristic", optional=true)
    protected static native NSString TaggedCharacteristicValue();
    /*</methods>*/
}
