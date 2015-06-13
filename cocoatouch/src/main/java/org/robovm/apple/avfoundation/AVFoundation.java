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
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVFoundation/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVFoundation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCoreAnimationBeginTimeAtZero", optional=true)
    public static native double CoreAnimationBeginTimeAtZero();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAlbum", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArranger", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyArtist", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyAuthor", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyChapter", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyChapter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComment", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyComposer", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCopyright", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCreationDate", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDescription", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDirector", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyDisclaimer", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyDisclaimer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyEncodedBy", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyFullName", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyFullName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyGenre", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyHostComputer", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyHostComputer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyInformation", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyKeywords", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyKeywords();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyMake", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyMake();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyModel", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyModel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalArtist", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalFormat", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyOriginalFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyOriginalSource", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyOriginalSource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPerformers", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyPerformers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProducer", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPublisher", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyProduct", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyProduct();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySoftware", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeySoftware();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeySpecialPlaybackRequirements();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrack", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyTrack();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWarning", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyWarning();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyWriter", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyWriter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyURLLink", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyURLLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyLocationISO6709", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyLocationISO6709();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTrackName", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyTrackName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyCredits", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyPhonogramRights", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyPhonogramRights();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeUserDataKeyTaggedCharacteristic", optional=true)
    public static native String Value__AVMetadataQuickTimeUserDataKeyTaggedCharacteristic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataISOUserDataKeyCopyright", optional=true)
    public static native String Value__AVMetadataISOUserDataKeyCopyright();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataISOUserDataKeyTaggedCharacteristic", optional=true)
    public static native String Value__AVMetadataISOUserDataKeyTaggedCharacteristic();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCopyright", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAuthor", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyPerformer", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyGenre", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyRecordingYear", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyRecordingYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyLocation", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyLocation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyTitle", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyDescription", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyDescription();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyCollection", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyCollection();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyUserRating", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyUserRating();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyThumbnail", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyThumbnail();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyAlbumAndTrack", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyAlbumAndTrack();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyKeywordList", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyKeywordList();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaClassification", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyMediaClassification();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadata3GPUserDataKeyMediaRating", optional=true)
    public static native String Value__AVMetadata3GPUserDataKeyMediaRating();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAuthor", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComment", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCopyright", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCreationDate", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirector", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDisplayName", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyDisplayName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyInformation", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyKeywords", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyKeywords();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyProducer", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPublisher", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyAlbum", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtist", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArtwork", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyArtwork();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDescription", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeySoftware", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeySoftware();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyYear", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyGenre", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyiXML", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyiXML();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationISO6709", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationISO6709();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyMake", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyMake();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyModel", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyModel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyArranger", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyEncodedBy", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyOriginalArtist", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPerformer", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyComposer", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCredits", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyPhonogramRights", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyPhonogramRights();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraIdentifier", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCameraIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCameraFrameReadoutTime();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyTitle", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyTitle();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyCollectionUser", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyCollectionUser();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyRatingUser", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyRatingUser();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationName", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationName();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationBody", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationBody();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationNote", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationNote();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationRole", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationRole();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyLocationDate", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyLocationDate();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionFacing", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyDirectionFacing();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="AVMetadataQuickTimeMetadataKeyDirectionMotion", optional=true)
    public static native String Value__AVMetadataQuickTimeMetadataKeyDirectionMotion();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbum", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAlbum();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtist", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserComment", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyUserComment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCoverArt", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyCoverArt();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCopyright", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyReleaseDate", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyReleaseDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodedBy", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPredefinedGenre", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyPredefinedGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyUserGenre", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyUserGenre();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongName", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeySongName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackSubTitle", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyTrackSubTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEncodingTool", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyEncodingTool();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyComposer", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAlbumArtist", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAlbumArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAccountKind", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAccountKind();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAppleID", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAppleID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtistID", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyArtistID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySongID", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeySongID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscCompilation", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyDiscCompilation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDiscNumber", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyDiscNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGenreID", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyGenreID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyGrouping", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyGrouping();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPlaylistID", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyPlaylistID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyContentRating", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyContentRating();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyBeatsPerMin", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyBeatsPerMin();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyTrackNumber", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyTrackNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArtDirector", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyArtDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyArranger", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyArranger();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAuthor", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAuthor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLyrics", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyLyrics();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyAcknowledgement", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyAcknowledgement();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyConductor", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyConductor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDescription", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyDirector", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyDirector();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyEQ", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyEQ();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyLinerNotes", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyLinerNotes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyRecordCompany", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyRecordCompany();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOriginalArtist", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPhonogramRights", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyPhonogramRights();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyProducer", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPerformer", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyPublisher", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoundEngineer", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeySoundEngineer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeySoloist", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeySoloist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyCredits", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyCredits();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyThanks", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyThanks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyOnlineExtras", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyOnlineExtras();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataiTunesMetadataKeyExecProducer", optional=true)
    public static native String Value__AVMetadataiTunesMetadataKeyExecProducer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioEncryption", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyAudioEncryption();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAttachedPicture", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyAttachedPicture();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAudioSeekPointIndex", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyAudioSeekPointIndex();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComments", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyComments();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommerical", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyCommerical();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncryption", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEncryption();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEqualization();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEqualization2", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEqualization2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEventTimingCodes", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEventTimingCodes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGeneralEncapsulatedObject", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyGeneralEncapsulatedObject();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyGroupIdentifier", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyGroupIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v23", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInvolvedPeopleList_v23();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLink", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicCDIdentifier", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyMusicCDIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMPEGLocationLookupTable", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyMPEGLocationLookupTable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOwnership", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOwnership();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPrivate", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPrivate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlayCounter", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPlayCounter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPopularimeter", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPopularimeter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPositionSynchronization", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPositionSynchronization();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecommendedBufferSize", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyRecommendedBufferSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyRelativeVolumeAdjustment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRelativeVolumeAdjustment2", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyRelativeVolumeAdjustment2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReverb", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyReverb();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySeek", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySeek();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySignature", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySignature();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedLyric", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySynchronizedLyric();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySynchronizedTempoCodes", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySynchronizedTempoCodes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumTitle", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyAlbumTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBeatsPerMinute", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyBeatsPerMinute();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyComposer", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyComposer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentType", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyContentType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyright", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyCopyright();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyDate", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodingTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEncodingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPlaylistDelay", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPlaylistDelay();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalReleaseTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyRecordingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyReleaseTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyReleaseTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTaggingTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTaggingTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedBy", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEncodedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLyricist", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyLyricist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileType", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyFileType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTime", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTime();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInvolvedPeopleList_v24", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInvolvedPeopleList_v24();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyContentGroupDescription", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyContentGroupDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleDescription", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTitleDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySubTitle", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySubTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInitialKey", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInitialKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLanguage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyLanguage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLength", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyLength();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMusicianCreditsList", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyMusicianCreditsList();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMediaType", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyMediaType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyMood", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyMood();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalAlbumTitle", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalAlbumTitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalFilename", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalFilename();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalLyricist", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalLyricist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalArtist", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalArtist();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOriginalReleaseYear", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOriginalReleaseYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyFileOwner", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyFileOwner();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyLeadPerformer", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyLeadPerformer();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyBand", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyBand();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyConductor", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyConductor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyModifiedBy", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyModifiedBy();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPartOfASet", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPartOfASet();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyProducedNotice", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyProducedNotice();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPublisher", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPublisher();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTrackNumber", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTrackNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyRecordingDates", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyRecordingDates();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationName", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInternetRadioStationName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternetRadioStationOwner", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInternetRadioStationOwner();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySize", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyAlbumSortOrder", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyAlbumSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPerformerSortOrder", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPerformerSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTitleSortOrder", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTitleSortOrder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyInternationalStandardRecordingCode", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyInternationalStandardRecordingCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyEncodedWith", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyEncodedWith();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeySetSubtitle", optional=true)
    public static native String Value__AVMetadataID3MetadataKeySetSubtitle();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyYear", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyYear();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserText", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyUserText();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUniqueFileIdentifier", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyUniqueFileIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyTermsOfUse", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyTermsOfUse();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUnsynchronizedLyric", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyUnsynchronizedLyric();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCommercialInformation", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyCommercialInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyCopyrightInformation", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyCopyrightInformation();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioFileWebpage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOfficialAudioFileWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialArtistWebpage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOfficialArtistWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialAudioSourceWebpage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOfficialAudioSourceWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOfficialInternetRadioStationHomepage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyPayment", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyPayment();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyOfficialPublisherWebpage", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyOfficialPublisherWebpage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMetadataID3MetadataKeyUserURL", optional=true)
    public static native String Value__AVMetadataID3MetadataKeyUserURL();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIcyMetadataKeyStreamTitle", optional=true)
    public static native String Value__AVMetadataIcyMetadataKeyStreamTitle();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataIcyMetadataKeyStreamURL", optional=true)
    public static native String Value__AVMetadataIcyMetadataKeyStreamURL();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="AVMakeRectWithAspectRatioInsideRect", optional=true)
    public static native @ByVal CGRect createRectWithAspectRatioInsideRect(@ByVal CGSize aspectRatio, @ByVal CGRect boundingRect);
    /*</methods>*/
}
