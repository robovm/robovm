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
@Marshaler(/*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyQuickTimeMetadata toObject(Class<AVMetadataKeyQuickTimeMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyQuickTimeMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyQuickTimeMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataKeyQuickTimeMetadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataKeyQuickTimeMetadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataKeyQuickTimeMetadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataKeyQuickTimeMetadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataKeyQuickTimeMetadata o : l) {
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
    public static final AVMetadataKeyQuickTimeMetadata Author = new AVMetadataKeyQuickTimeMetadata("Author");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Comment = new AVMetadataKeyQuickTimeMetadata("Comment");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Copyright = new AVMetadataKeyQuickTimeMetadata("Copyright");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CreationDate = new AVMetadataKeyQuickTimeMetadata("CreationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Director = new AVMetadataKeyQuickTimeMetadata("Director");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DisplayName = new AVMetadataKeyQuickTimeMetadata("DisplayName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Information = new AVMetadataKeyQuickTimeMetadata("Information");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Keywords = new AVMetadataKeyQuickTimeMetadata("Keywords");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Producer = new AVMetadataKeyQuickTimeMetadata("Producer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Publisher = new AVMetadataKeyQuickTimeMetadata("Publisher");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Album = new AVMetadataKeyQuickTimeMetadata("Album");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Artist = new AVMetadataKeyQuickTimeMetadata("Artist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Artwork = new AVMetadataKeyQuickTimeMetadata("Artwork");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Description = new AVMetadataKeyQuickTimeMetadata("Description");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Software = new AVMetadataKeyQuickTimeMetadata("Software");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Year = new AVMetadataKeyQuickTimeMetadata("Year");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Genre = new AVMetadataKeyQuickTimeMetadata("Genre");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata iXML = new AVMetadataKeyQuickTimeMetadata("iXML");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationISO6709 = new AVMetadataKeyQuickTimeMetadata("LocationISO6709");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Make = new AVMetadataKeyQuickTimeMetadata("Make");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Model = new AVMetadataKeyQuickTimeMetadata("Model");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Arranger = new AVMetadataKeyQuickTimeMetadata("Arranger");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata EncodedBy = new AVMetadataKeyQuickTimeMetadata("EncodedBy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata OriginalArtist = new AVMetadataKeyQuickTimeMetadata("OriginalArtist");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Performer = new AVMetadataKeyQuickTimeMetadata("Performer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Composer = new AVMetadataKeyQuickTimeMetadata("Composer");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Credits = new AVMetadataKeyQuickTimeMetadata("Credits");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata PhonogramRights = new AVMetadataKeyQuickTimeMetadata("PhonogramRights");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CameraIdentifier = new AVMetadataKeyQuickTimeMetadata("CameraIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CameraFrameReadoutTime = new AVMetadataKeyQuickTimeMetadata("CameraFrameReadoutTime");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata Title = new AVMetadataKeyQuickTimeMetadata("Title");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata CollectionUser = new AVMetadataKeyQuickTimeMetadata("CollectionUser");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata RatingUser = new AVMetadataKeyQuickTimeMetadata("RatingUser");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationName = new AVMetadataKeyQuickTimeMetadata("LocationName");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationBody = new AVMetadataKeyQuickTimeMetadata("LocationBody");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationNote = new AVMetadataKeyQuickTimeMetadata("LocationNote");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationRole = new AVMetadataKeyQuickTimeMetadata("LocationRole");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata LocationDate = new AVMetadataKeyQuickTimeMetadata("LocationDate");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DirectionFacing = new AVMetadataKeyQuickTimeMetadata("DirectionFacing");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final AVMetadataKeyQuickTimeMetadata DirectionMotion = new AVMetadataKeyQuickTimeMetadata("DirectionMotion");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/[] values = new /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/[] {/*<value_list>*/Author, Comment, Copyright, CreationDate, Director, DisplayName, Information, Keywords, Producer, Publisher, Album, Artist, Artwork, Description, Software, Year, Genre, iXML, LocationISO6709, Make, Model, Arranger, EncodedBy, OriginalArtist, Performer, Composer, Credits, PhonogramRights, CameraIdentifier, CameraFrameReadoutTime, Title, CollectionUser, RatingUser, LocationName, LocationBody, LocationNote, LocationRole, LocationDate, DirectionFacing, DirectionMotion/*</value_list>*/};
    
    /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataKeyQuickTimeMetadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComment", optional=true)
        public static native NSString Comment();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCreationDate", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDisplayName", optional=true)
        public static native NSString DisplayName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyInformation", optional=true)
        public static native NSString Information();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyKeywords", optional=true)
        public static native NSString Keywords();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtwork", optional=true)
        public static native NSString Artwork();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeySoftware", optional=true)
        public static native NSString Software();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyYear", optional=true)
        public static native NSString Year();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyGenre", optional=true)
        public static native NSString Genre();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyiXML", optional=true)
        public static native NSString iXML();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationISO6709", optional=true)
        public static native NSString LocationISO6709();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyMake", optional=true)
        public static native NSString Make();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyModel", optional=true)
        public static native NSString Model();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPerformer", optional=true)
        public static native NSString Performer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraIdentifier", optional=true)
        public static native NSString CameraIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime", optional=true)
        public static native NSString CameraFrameReadoutTime();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyTitle", optional=true)
        public static native NSString Title();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCollectionUser", optional=true)
        public static native NSString CollectionUser();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyRatingUser", optional=true)
        public static native NSString RatingUser();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationName", optional=true)
        public static native NSString LocationName();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationBody", optional=true)
        public static native NSString LocationBody();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationNote", optional=true)
        public static native NSString LocationNote();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationRole", optional=true)
        public static native NSString LocationRole();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationDate", optional=true)
        public static native NSString LocationDate();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionFacing", optional=true)
        public static native NSString DirectionFacing();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionMotion", optional=true)
        public static native NSString DirectionMotion();
        /*</values>*/
    }
}
