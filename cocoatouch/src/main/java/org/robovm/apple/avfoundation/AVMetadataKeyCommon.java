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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMetadataKeyCommon.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataKeyCommon/*</name>*/ 
    extends /*<extends>*/AVMetadataKey/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataKeyCommon toObject(Class<AVMetadataKeyCommon> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataKeyCommon.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataKeyCommon o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataKeyCommon.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Title = new AVMetadataKeyCommon("TitleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Creator = new AVMetadataKeyCommon("CreatorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Subject = new AVMetadataKeyCommon("SubjectValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Description = new AVMetadataKeyCommon("DescriptionValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Publisher = new AVMetadataKeyCommon("PublisherValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Contributor = new AVMetadataKeyCommon("ContributorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon CreationDate = new AVMetadataKeyCommon("CreationDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon LastModifiedDate = new AVMetadataKeyCommon("LastModifiedDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Type = new AVMetadataKeyCommon("TypeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Format = new AVMetadataKeyCommon("FormatValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Identifier = new AVMetadataKeyCommon("IdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Source = new AVMetadataKeyCommon("SourceValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Language = new AVMetadataKeyCommon("LanguageValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Relation = new AVMetadataKeyCommon("RelationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Location = new AVMetadataKeyCommon("LocationValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Copyrights = new AVMetadataKeyCommon("CopyrightsValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon AlbumName = new AVMetadataKeyCommon("AlbumNameValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Author = new AVMetadataKeyCommon("AuthorValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Artist = new AVMetadataKeyCommon("ArtistValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Artwork = new AVMetadataKeyCommon("ArtworkValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Make = new AVMetadataKeyCommon("MakeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Model = new AVMetadataKeyCommon("ModelValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMetadataKeyCommon Software = new AVMetadataKeyCommon("SoftwareValue");
    
    private static AVMetadataKeyCommon[] values = new AVMetadataKeyCommon[] {Title, Creator, Subject, Description, Publisher, 
        Contributor, CreationDate, LastModifiedDate, Type, Format, Identifier, Source, Language, Relation, Location, Copyrights, 
        AlbumName, Author, Artist, Artwork, Make, Model, Software};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataKeyCommon(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataKeyCommon valueOf(NSString value) {
        for (AVMetadataKeyCommon v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
//        throw new IllegalArgumentException("No constant with value " + value + " found in " 
//            + /*<name>*/AVMetadataKeyCommon/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyTitle", optional=true)
    protected static native NSString TitleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCreator", optional=true)
    protected static native NSString CreatorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySubject", optional=true)
    protected static native NSString SubjectValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyDescription", optional=true)
    protected static native NSString DescriptionValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyPublisher", optional=true)
    protected static native NSString PublisherValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyContributor", optional=true)
    protected static native NSString ContributorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCreationDate", optional=true)
    protected static native NSString CreationDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLastModifiedDate", optional=true)
    protected static native NSString LastModifiedDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyType", optional=true)
    protected static native NSString TypeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyFormat", optional=true)
    protected static native NSString FormatValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyIdentifier", optional=true)
    protected static native NSString IdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySource", optional=true)
    protected static native NSString SourceValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLanguage", optional=true)
    protected static native NSString LanguageValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyRelation", optional=true)
    protected static native NSString RelationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyLocation", optional=true)
    protected static native NSString LocationValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyCopyrights", optional=true)
    protected static native NSString CopyrightsValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyAlbumName", optional=true)
    protected static native NSString AlbumNameValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyAuthor", optional=true)
    protected static native NSString AuthorValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyArtist", optional=true)
    protected static native NSString ArtistValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyArtwork", optional=true)
    protected static native NSString ArtworkValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyMake", optional=true)
    protected static native NSString MakeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeyModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataCommonKeySoftware", optional=true)
    protected static native NSString SoftwareValue();
    /*</methods>*/
}
