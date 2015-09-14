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
@Marshaler(/*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataIdentifierQuickTimeMetadata toObject(Class<AVMetadataIdentifierQuickTimeMetadata> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataIdentifierQuickTimeMetadata.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataIdentifierQuickTimeMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataIdentifierQuickTimeMetadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataIdentifierQuickTimeMetadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMetadataIdentifierQuickTimeMetadata.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataIdentifierQuickTimeMetadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataIdentifierQuickTimeMetadata o : l) {
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
    public static final AVMetadataIdentifierQuickTimeMetadata Author = new AVMetadataIdentifierQuickTimeMetadata("Author");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Comment = new AVMetadataIdentifierQuickTimeMetadata("Comment");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Copyright = new AVMetadataIdentifierQuickTimeMetadata("Copyright");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CreationDate = new AVMetadataIdentifierQuickTimeMetadata("CreationDate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Director = new AVMetadataIdentifierQuickTimeMetadata("Director");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DisplayName = new AVMetadataIdentifierQuickTimeMetadata("DisplayName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Information = new AVMetadataIdentifierQuickTimeMetadata("Information");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Keywords = new AVMetadataIdentifierQuickTimeMetadata("Keywords");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Producer = new AVMetadataIdentifierQuickTimeMetadata("Producer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Publisher = new AVMetadataIdentifierQuickTimeMetadata("Publisher");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Album = new AVMetadataIdentifierQuickTimeMetadata("Album");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Artist = new AVMetadataIdentifierQuickTimeMetadata("Artist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Artwork = new AVMetadataIdentifierQuickTimeMetadata("Artwork");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Description = new AVMetadataIdentifierQuickTimeMetadata("Description");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Software = new AVMetadataIdentifierQuickTimeMetadata("Software");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Year = new AVMetadataIdentifierQuickTimeMetadata("Year");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Genre = new AVMetadataIdentifierQuickTimeMetadata("Genre");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata iXML = new AVMetadataIdentifierQuickTimeMetadata("iXML");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationISO6709 = new AVMetadataIdentifierQuickTimeMetadata("LocationISO6709");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Make = new AVMetadataIdentifierQuickTimeMetadata("Make");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Model = new AVMetadataIdentifierQuickTimeMetadata("Model");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Arranger = new AVMetadataIdentifierQuickTimeMetadata("Arranger");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata EncodedBy = new AVMetadataIdentifierQuickTimeMetadata("EncodedBy");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata OriginalArtist = new AVMetadataIdentifierQuickTimeMetadata("OriginalArtist");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Performer = new AVMetadataIdentifierQuickTimeMetadata("Performer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Composer = new AVMetadataIdentifierQuickTimeMetadata("Composer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Credits = new AVMetadataIdentifierQuickTimeMetadata("Credits");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata PhonogramRights = new AVMetadataIdentifierQuickTimeMetadata("PhonogramRights");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CameraIdentifier = new AVMetadataIdentifierQuickTimeMetadata("CameraIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CameraFrameReadoutTime = new AVMetadataIdentifierQuickTimeMetadata("CameraFrameReadoutTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata Title = new AVMetadataIdentifierQuickTimeMetadata("Title");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata CollectionUser = new AVMetadataIdentifierQuickTimeMetadata("CollectionUser");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata RatingUser = new AVMetadataIdentifierQuickTimeMetadata("RatingUser");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationName = new AVMetadataIdentifierQuickTimeMetadata("LocationName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationBody = new AVMetadataIdentifierQuickTimeMetadata("LocationBody");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationNote = new AVMetadataIdentifierQuickTimeMetadata("LocationNote");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationRole = new AVMetadataIdentifierQuickTimeMetadata("LocationRole");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata LocationDate = new AVMetadataIdentifierQuickTimeMetadata("LocationDate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DirectionFacing = new AVMetadataIdentifierQuickTimeMetadata("DirectionFacing");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DirectionMotion = new AVMetadataIdentifierQuickTimeMetadata("DirectionMotion");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata PreferredAffineTransform = new AVMetadataIdentifierQuickTimeMetadata("PreferredAffineTransform");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata DetectedFace = new AVMetadataIdentifierQuickTimeMetadata("DetectedFace");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata VideoOrientation = new AVMetadataIdentifierQuickTimeMetadata("VideoOrientation");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVMetadataIdentifierQuickTimeMetadata ContentIdentifier = new AVMetadataIdentifierQuickTimeMetadata("ContentIdentifier");
    /*</constants>*/
    
    private static /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/[] values = new /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/[] {/*<value_list>*/Author, Comment, Copyright, CreationDate, Director, DisplayName, Information, Keywords, Producer, Publisher, Album, Artist, Artwork, Description, Software, Year, Genre, iXML, LocationISO6709, Make, Model, Arranger, EncodedBy, OriginalArtist, Performer, Composer, Credits, PhonogramRights, CameraIdentifier, CameraFrameReadoutTime, Title, CollectionUser, RatingUser, LocationName, LocationBody, LocationNote, LocationRole, LocationDate, DirectionFacing, DirectionMotion, PreferredAffineTransform, DetectedFace, VideoOrientation, ContentIdentifier/*</value_list>*/};
    
    /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataIdentifierQuickTimeMetadata/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAuthor", optional=true)
        public static native NSString Author();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComment", optional=true)
        public static native NSString Comment();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCopyright", optional=true)
        public static native NSString Copyright();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCreationDate", optional=true)
        public static native NSString CreationDate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirector", optional=true)
        public static native NSString Director();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDisplayName", optional=true)
        public static native NSString DisplayName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataInformation", optional=true)
        public static native NSString Information();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataKeywords", optional=true)
        public static native NSString Keywords();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataProducer", optional=true)
        public static native NSString Producer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPublisher", optional=true)
        public static native NSString Publisher();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataAlbum", optional=true)
        public static native NSString Album();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtist", optional=true)
        public static native NSString Artist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArtwork", optional=true)
        public static native NSString Artwork();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDescription", optional=true)
        public static native NSString Description();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataSoftware", optional=true)
        public static native NSString Software();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataYear", optional=true)
        public static native NSString Year();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataGenre", optional=true)
        public static native NSString Genre();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataiXML", optional=true)
        public static native NSString iXML();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationISO6709", optional=true)
        public static native NSString LocationISO6709();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataMake", optional=true)
        public static native NSString Make();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataModel", optional=true)
        public static native NSString Model();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataArranger", optional=true)
        public static native NSString Arranger();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataEncodedBy", optional=true)
        public static native NSString EncodedBy();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataOriginalArtist", optional=true)
        public static native NSString OriginalArtist();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPerformer", optional=true)
        public static native NSString Performer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataComposer", optional=true)
        public static native NSString Composer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCredits", optional=true)
        public static native NSString Credits();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPhonogramRights", optional=true)
        public static native NSString PhonogramRights();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraIdentifier", optional=true)
        public static native NSString CameraIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCameraFrameReadoutTime", optional=true)
        public static native NSString CameraFrameReadoutTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataTitle", optional=true)
        public static native NSString Title();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataCollectionUser", optional=true)
        public static native NSString CollectionUser();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataRatingUser", optional=true)
        public static native NSString RatingUser();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationName", optional=true)
        public static native NSString LocationName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationBody", optional=true)
        public static native NSString LocationBody();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationNote", optional=true)
        public static native NSString LocationNote();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationRole", optional=true)
        public static native NSString LocationRole();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataLocationDate", optional=true)
        public static native NSString LocationDate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionFacing", optional=true)
        public static native NSString DirectionFacing();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDirectionMotion", optional=true)
        public static native NSString DirectionMotion();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataPreferredAffineTransform", optional=true)
        public static native NSString PreferredAffineTransform();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataDetectedFace", optional=true)
        public static native NSString DetectedFace();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataVideoOrientation", optional=true)
        public static native NSString VideoOrientation();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVMetadataIdentifierQuickTimeMetadataContentIdentifier", optional=true)
        public static native NSString ContentIdentifier();
        /*</values>*/
    }
}
