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
/**
 * @since Available in iOS 8.0 and later.
 */
@Marshaler(AVMetadataIdentifierCommon.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataIdentifierCommon/*</name>*/ 
    extends /*<extends>*/AVMetadataIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSMetadataItemAttribute toObject(Class<NSMetadataItemAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSMetadataItemAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSMetadataItemAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataIdentifierCommon.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Title = new AVMetadataIdentifierCommon("TitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Creator = new AVMetadataIdentifierCommon("CreatorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Subject = new AVMetadataIdentifierCommon("SubjectValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Description = new AVMetadataIdentifierCommon("DescriptionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Publisher = new AVMetadataIdentifierCommon("PublisherValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Contributor = new AVMetadataIdentifierCommon("ContributorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon CreationDate = new AVMetadataIdentifierCommon("CreationDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon LastModifiedDate = new AVMetadataIdentifierCommon("LastModifiedDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Type = new AVMetadataIdentifierCommon("TypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Format = new AVMetadataIdentifierCommon("FormatValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon AssetIdentifier = new AVMetadataIdentifierCommon("AssetIdentifierValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Source = new AVMetadataIdentifierCommon("SourceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Language = new AVMetadataIdentifierCommon("LanguageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Relation = new AVMetadataIdentifierCommon("RelationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Location = new AVMetadataIdentifierCommon("LocationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Copyrights = new AVMetadataIdentifierCommon("CopyrightsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon AlbumName = new AVMetadataIdentifierCommon("AlbumNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Author = new AVMetadataIdentifierCommon("AuthorValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Artist = new AVMetadataIdentifierCommon("ArtistValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Artwork = new AVMetadataIdentifierCommon("ArtworkValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Make = new AVMetadataIdentifierCommon("MakeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Model = new AVMetadataIdentifierCommon("ModelValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataIdentifierCommon Software = new AVMetadataIdentifierCommon("SoftwareValue");
    
    private static AVMetadataIdentifierCommon[] values = new AVMetadataIdentifierCommon[] {Title, Creator, Subject, Description, 
        Publisher, Contributor, CreationDate, LastModifiedDate, Format, AssetIdentifier, Source, Language, Relation, Location, 
        Copyrights, AlbumName, Author, Artist, Artwork, Make, Model, Software};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataIdentifierCommon(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataIdentifierCommon valueOf(NSString value) {
        for (AVMetadataIdentifierCommon v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataIdentifierCommon/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCreator", optional=true)
    protected static native NSString CreatorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSubject", optional=true)
    protected static native NSString SubjectValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierContributor", optional=true)
    protected static native NSString ContributorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLastModifiedDate", optional=true)
    protected static native NSString LastModifiedDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierType", optional=true)
    protected static native NSString TypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierFormat", optional=true)
    protected static native NSString FormatValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAssetIdentifier", optional=true)
    protected static native NSString AssetIdentifierValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSource", optional=true)
    protected static native NSString SourceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLanguage", optional=true)
    protected static native NSString LanguageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierRelation", optional=true)
    protected static native NSString RelationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierLocation", optional=true)
    protected static native NSString LocationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierCopyrights", optional=true)
    protected static native NSString CopyrightsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAlbumName", optional=true)
    protected static native NSString AlbumNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierArtwork", optional=true)
    protected static native NSString ArtworkValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonIdentifierSoftware", optional=true)
    protected static native NSString SoftwareValue();
    /*</methods>*/
}
