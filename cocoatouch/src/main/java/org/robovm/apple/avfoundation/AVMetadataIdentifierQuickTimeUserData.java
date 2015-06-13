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
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataIdentifierQuickTimeUserData> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataIdentifierQuickTimeUserData> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataIdentifierQuickTimeUserData.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataIdentifierQuickTimeUserData> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataIdentifierQuickTimeUserData o : l) {
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
    public static final AVMetadataIdentifierQuickTimeUserData Album = new AVMetadataIdentifierQuickTimeUserData("Album");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Arranger = new AVMetadataIdentifierQuickTimeUserData("Arranger");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Artist = new AVMetadataIdentifierQuickTimeUserData("Artist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Author = new AVMetadataIdentifierQuickTimeUserData("Author");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Chapter = new AVMetadataIdentifierQuickTimeUserData("Chapter");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Comment = new AVMetadataIdentifierQuickTimeUserData("Comment");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Composer = new AVMetadataIdentifierQuickTimeUserData("Composer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Copyright = new AVMetadataIdentifierQuickTimeUserData("Copyright");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData CreationDate = new AVMetadataIdentifierQuickTimeUserData("CreationDate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Description = new AVMetadataIdentifierQuickTimeUserData("Description");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Director = new AVMetadataIdentifierQuickTimeUserData("Director");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Disclaimer = new AVMetadataIdentifierQuickTimeUserData("Disclaimer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData EncodedBy = new AVMetadataIdentifierQuickTimeUserData("EncodedBy");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData FullName = new AVMetadataIdentifierQuickTimeUserData("FullName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Genre = new AVMetadataIdentifierQuickTimeUserData("Genre");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData HostComputer = new AVMetadataIdentifierQuickTimeUserData("HostComputer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Information = new AVMetadataIdentifierQuickTimeUserData("Information");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Keywords = new AVMetadataIdentifierQuickTimeUserData("Keywords");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Make = new AVMetadataIdentifierQuickTimeUserData("Make");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Model = new AVMetadataIdentifierQuickTimeUserData("Model");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalArtist = new AVMetadataIdentifierQuickTimeUserData("OriginalArtist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalFormat = new AVMetadataIdentifierQuickTimeUserData("OriginalFormat");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData OriginalSource = new AVMetadataIdentifierQuickTimeUserData("OriginalSource");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Performers = new AVMetadataIdentifierQuickTimeUserData("Performers");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Producer = new AVMetadataIdentifierQuickTimeUserData("Producer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Publisher = new AVMetadataIdentifierQuickTimeUserData("Publisher");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Product = new AVMetadataIdentifierQuickTimeUserData("Product");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Software = new AVMetadataIdentifierQuickTimeUserData("Software");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData SpecialPlaybackRequirements = new AVMetadataIdentifierQuickTimeUserData("SpecialPlaybackRequirements");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Track = new AVMetadataIdentifierQuickTimeUserData("Track");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Warning = new AVMetadataIdentifierQuickTimeUserData("Warning");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Writer = new AVMetadataIdentifierQuickTimeUserData("Writer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData URLLink = new AVMetadataIdentifierQuickTimeUserData("URLLink");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData LocationISO6709 = new AVMetadataIdentifierQuickTimeUserData("LocationISO6709");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData TrackName = new AVMetadataIdentifierQuickTimeUserData("TrackName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData Credits = new AVMetadataIdentifierQuickTimeUserData("Credits");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData PhonogramRights = new AVMetadataIdentifierQuickTimeUserData("PhonogramRights");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeUserData TaggedCharacteristic = new AVMetadataIdentifierQuickTimeUserData("TaggedCharacteristic");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/[] values = new /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/[] {/*<value_list>*/Album, Arranger, Artist, Author, Chapter, Comment, Composer, Copyright, CreationDate, Description, Director, Disclaimer, EncodedBy, FullName, Genre, HostComputer, Information, Keywords, Make, Model, OriginalArtist, OriginalFormat, OriginalSource, Performers, Producer, Publisher, Product, Software, SpecialPlaybackRequirements, Track, Warning, Writer, URLLink, LocationISO6709, TrackName, Credits, PhonogramRights, TaggedCharacteristic/*</value_list>*/};
    
    /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataIdentifierQuickTimeUserData/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataChapter", optional=true)
        public static native NSString Chapter();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComment", optional=true)
        public static native NSString Comment();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCreationDate", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataDisclaimer", optional=true)
        public static native NSString Disclaimer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataFullName", optional=true)
        public static native NSString FullName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataGenre", optional=true)
        public static native NSString Genre();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataHostComputer", optional=true)
        public static native NSString HostComputer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataInformation", optional=true)
        public static native NSString Information();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataKeywords", optional=true)
        public static native NSString Keywords();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataMake", optional=true)
        public static native NSString Make();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataModel", optional=true)
        public static native NSString Model();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalFormat", optional=true)
        public static native NSString OriginalFormat();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataOriginalSource", optional=true)
        public static native NSString OriginalSource();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPerformers", optional=true)
        public static native NSString Performers();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataProduct", optional=true)
        public static native NSString Product();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSoftware", optional=true)
        public static native NSString Software();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataSpecialPlaybackRequirements", optional=true)
        public static native NSString SpecialPlaybackRequirements();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrack", optional=true)
        public static native NSString Track();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWarning", optional=true)
        public static native NSString Warning();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataWriter", optional=true)
        public static native NSString Writer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataURLLink", optional=true)
        public static native NSString URLLink();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataLocationISO6709", optional=true)
        public static native NSString LocationISO6709();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTrackName", optional=true)
        public static native NSString TrackName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeUserDataTaggedCharacteristic", optional=true)
        public static native NSString TaggedCharacteristic();
        /*</values>*/
    }
}
