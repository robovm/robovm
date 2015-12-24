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
@Marshaler(/*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataKeyQuickTimeUserData> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataKeyQuickTimeUserData> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataKeyQuickTimeUserData.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataKeyQuickTimeUserData> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataKeyQuickTimeUserData o : l) {
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
    public static final AVMetadataKeyQuickTimeUserData Album = new AVMetadataKeyQuickTimeUserData("Album");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Arranger = new AVMetadataKeyQuickTimeUserData("Arranger");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Artist = new AVMetadataKeyQuickTimeUserData("Artist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Author = new AVMetadataKeyQuickTimeUserData("Author");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Chapter = new AVMetadataKeyQuickTimeUserData("Chapter");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Comment = new AVMetadataKeyQuickTimeUserData("Comment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Composer = new AVMetadataKeyQuickTimeUserData("Composer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Copyright = new AVMetadataKeyQuickTimeUserData("Copyright");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData CreationDate = new AVMetadataKeyQuickTimeUserData("CreationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Description = new AVMetadataKeyQuickTimeUserData("Description");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Director = new AVMetadataKeyQuickTimeUserData("Director");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Disclaimer = new AVMetadataKeyQuickTimeUserData("Disclaimer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData EncodedBy = new AVMetadataKeyQuickTimeUserData("EncodedBy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData FullName = new AVMetadataKeyQuickTimeUserData("FullName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Genre = new AVMetadataKeyQuickTimeUserData("Genre");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData HostComputer = new AVMetadataKeyQuickTimeUserData("HostComputer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Information = new AVMetadataKeyQuickTimeUserData("Information");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Keywords = new AVMetadataKeyQuickTimeUserData("Keywords");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Make = new AVMetadataKeyQuickTimeUserData("Make");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Model = new AVMetadataKeyQuickTimeUserData("Model");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalArtist = new AVMetadataKeyQuickTimeUserData("OriginalArtist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalFormat = new AVMetadataKeyQuickTimeUserData("OriginalFormat");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData OriginalSource = new AVMetadataKeyQuickTimeUserData("OriginalSource");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Performers = new AVMetadataKeyQuickTimeUserData("Performers");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Producer = new AVMetadataKeyQuickTimeUserData("Producer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Publisher = new AVMetadataKeyQuickTimeUserData("Publisher");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Product = new AVMetadataKeyQuickTimeUserData("Product");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Software = new AVMetadataKeyQuickTimeUserData("Software");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData SpecialPlaybackRequirements = new AVMetadataKeyQuickTimeUserData("SpecialPlaybackRequirements");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Track = new AVMetadataKeyQuickTimeUserData("Track");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Warning = new AVMetadataKeyQuickTimeUserData("Warning");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Writer = new AVMetadataKeyQuickTimeUserData("Writer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData URLLink = new AVMetadataKeyQuickTimeUserData("URLLink");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData LocationISO6709 = new AVMetadataKeyQuickTimeUserData("LocationISO6709");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData TrackName = new AVMetadataKeyQuickTimeUserData("TrackName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData Credits = new AVMetadataKeyQuickTimeUserData("Credits");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData PhonogramRights = new AVMetadataKeyQuickTimeUserData("PhonogramRights");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMetadataKeyQuickTimeUserData TaggedCharacteristic = new AVMetadataKeyQuickTimeUserData("TaggedCharacteristic");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/[] values = new /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/[] {/*<value_list>*/Album, Arranger, Artist, Author, Chapter, Comment, Composer, Copyright, CreationDate, Description, Director, Disclaimer, EncodedBy, FullName, Genre, HostComputer, Information, Keywords, Make, Model, OriginalArtist, OriginalFormat, OriginalSource, Performers, Producer, Publisher, Product, Software, SpecialPlaybackRequirements, Track, Warning, Writer, URLLink, LocationISO6709, TrackName, Credits, PhonogramRights, TaggedCharacteristic/*</value_list>*/};
    
    /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeyQuickTimeUserData/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyChapter", optional=true)
        public static native NSString Chapter();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComment", optional=true)
        public static native NSString Comment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCreationDate", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDisclaimer", optional=true)
        public static native NSString Disclaimer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyFullName", optional=true)
        public static native NSString FullName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyGenre", optional=true)
        public static native NSString Genre();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyHostComputer", optional=true)
        public static native NSString HostComputer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyInformation", optional=true)
        public static native NSString Information();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyKeywords", optional=true)
        public static native NSString Keywords();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyMake", optional=true)
        public static native NSString Make();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyModel", optional=true)
        public static native NSString Model();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalFormat", optional=true)
        public static native NSString OriginalFormat();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalSource", optional=true)
        public static native NSString OriginalSource();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPerformers", optional=true)
        public static native NSString Performers();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProduct", optional=true)
        public static native NSString Product();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySoftware", optional=true)
        public static native NSString Software();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements", optional=true)
        public static native NSString SpecialPlaybackRequirements();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrack", optional=true)
        public static native NSString Track();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWarning", optional=true)
        public static native NSString Warning();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWriter", optional=true)
        public static native NSString Writer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyURLLink", optional=true)
        public static native NSString URLLink();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyLocationISO6709", optional=true)
        public static native NSString LocationISO6709();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrackName", optional=true)
        public static native NSString TrackName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTaggedCharacteristic", optional=true)
        public static native NSString TaggedCharacteristic();
        /*</values>*/
    }
}
