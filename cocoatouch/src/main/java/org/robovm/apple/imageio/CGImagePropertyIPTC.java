/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.imageio;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyIPTC.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyIPTC/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyIPTC toObject(Class<CGImagePropertyIPTC> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyIPTC.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyIPTC o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyIPTC.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectTypeReference = new CGImagePropertyIPTC("ObjectTypeReferenceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectAttributeReference = new CGImagePropertyIPTC("ObjectAttributeReferenceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectName = new CGImagePropertyIPTC("ObjectNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC EditStatus = new CGImagePropertyIPTC("EditStatusKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC EditorialUpdate = new CGImagePropertyIPTC("EditorialUpdateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Urgency = new CGImagePropertyIPTC("UrgencyKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SubjectReference = new CGImagePropertyIPTC("SubjectReferenceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Category = new CGImagePropertyIPTC("CategoryKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SupplementalCategory = new CGImagePropertyIPTC("SupplementalCategoryKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC FixtureIdentifier = new CGImagePropertyIPTC("FixtureIdentifierKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Keywords = new CGImagePropertyIPTC("KeywordsKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ContentLocationCode = new CGImagePropertyIPTC("ContentLocationCodeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ContentLocationName = new CGImagePropertyIPTC("ContentLocationNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReleaseDate = new CGImagePropertyIPTC("ReleaseDateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReleaseTime = new CGImagePropertyIPTC("ReleaseTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ExpirationDate = new CGImagePropertyIPTC("ExpirationDateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ExpirationTime = new CGImagePropertyIPTC("ExpirationTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SpecialInstructions = new CGImagePropertyIPTC("SpecialInstructionsKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ActionAdvised = new CGImagePropertyIPTC("ActionAdvisedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceService = new CGImagePropertyIPTC("ReferenceServiceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceDate = new CGImagePropertyIPTC("ReferenceDateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceNumber = new CGImagePropertyIPTC("ReferenceNumberKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DateCreated = new CGImagePropertyIPTC("DateCreatedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC TimeCreated = new CGImagePropertyIPTC("TimeCreatedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DigitalCreationDate = new CGImagePropertyIPTC("DigitalCreationDateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DigitalCreationTime = new CGImagePropertyIPTC("DigitalCreationTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC OriginatingProgram = new CGImagePropertyIPTC("OriginatingProgramKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ProgramVersion = new CGImagePropertyIPTC("ProgramVersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectCycle = new CGImagePropertyIPTC("ObjectCycleKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Byline = new CGImagePropertyIPTC("BylineKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC BylineTitle = new CGImagePropertyIPTC("BylineTitleKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC City = new CGImagePropertyIPTC("CityKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SubLocation = new CGImagePropertyIPTC("SubLocationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ProvinceState = new CGImagePropertyIPTC("ProvinceStateKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CountryPrimaryLocationCode = new CGImagePropertyIPTC("CountryPrimaryLocationCodeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CountryPrimaryLocationName = new CGImagePropertyIPTC("CountryPrimaryLocationNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC OriginalTransmissionReference = new CGImagePropertyIPTC("OriginalTransmissionReferenceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Headline = new CGImagePropertyIPTC("HeadlineKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Credit = new CGImagePropertyIPTC("CreditKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Source = new CGImagePropertyIPTC("SourceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CopyrightNotice = new CGImagePropertyIPTC("CopyrightNoticeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Contact = new CGImagePropertyIPTC("ContactKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CaptionAbstract = new CGImagePropertyIPTC("CaptionAbstractKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC WriterEditor = new CGImagePropertyIPTC("WriterEditorKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ImageType = new CGImagePropertyIPTC("ImageTypeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ImageOrientation = new CGImagePropertyIPTC("ImageOrientationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC LanguageIdentifier = new CGImagePropertyIPTC("LanguageIdentifierKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC StarRating = new CGImagePropertyIPTC("StarRatingKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CreatorContactInfo = new CGImagePropertyIPTC("CreatorContactInfoKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC RightsUsageTerms = new CGImagePropertyIPTC("RightsUsageTermsKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Scene = new CGImagePropertyIPTC("SceneKey");
    
    private static CGImagePropertyIPTC[] values = new CGImagePropertyIPTC[] {ObjectTypeReference, ObjectAttributeReference, 
        ObjectName, EditStatus, EditorialUpdate, Urgency, SubjectReference, Category, SupplementalCategory, FixtureIdentifier, 
        Keywords, ContentLocationCode, ContentLocationName, ReleaseDate, ReleaseTime, ExpirationDate, ExpirationTime, SpecialInstructions, 
        ActionAdvised, ReferenceService, ReferenceDate, ReferenceNumber, DateCreated, TimeCreated, DigitalCreationDate, DigitalCreationTime, 
        OriginatingProgram, ProgramVersion, ObjectCycle, Byline, BylineTitle, City, SubLocation, ProvinceState, CountryPrimaryLocationCode, 
        CountryPrimaryLocationName, OriginalTransmissionReference, Headline, Credit, Source, CopyrightNotice, Contact, CaptionAbstract, 
        WriterEditor, ImageType, ImageOrientation, LanguageIdentifier, StarRating, CreatorContactInfo, RightsUsageTerms, Scene};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyIPTC(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyIPTC valueOf(CFString value) {
        for (CGImagePropertyIPTC v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyIPTC/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCObjectTypeReference", optional=true)
    protected static native CFString ObjectTypeReferenceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCObjectAttributeReference", optional=true)
    protected static native CFString ObjectAttributeReferenceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCObjectName", optional=true)
    protected static native CFString ObjectNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCEditStatus", optional=true)
    protected static native CFString EditStatusKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCEditorialUpdate", optional=true)
    protected static native CFString EditorialUpdateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCUrgency", optional=true)
    protected static native CFString UrgencyKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCSubjectReference", optional=true)
    protected static native CFString SubjectReferenceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCategory", optional=true)
    protected static native CFString CategoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCSupplementalCategory", optional=true)
    protected static native CFString SupplementalCategoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCFixtureIdentifier", optional=true)
    protected static native CFString FixtureIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCKeywords", optional=true)
    protected static native CFString KeywordsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContentLocationCode", optional=true)
    protected static native CFString ContentLocationCodeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContentLocationName", optional=true)
    protected static native CFString ContentLocationNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCReleaseDate", optional=true)
    protected static native CFString ReleaseDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCReleaseTime", optional=true)
    protected static native CFString ReleaseTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCExpirationDate", optional=true)
    protected static native CFString ExpirationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCExpirationTime", optional=true)
    protected static native CFString ExpirationTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCSpecialInstructions", optional=true)
    protected static native CFString SpecialInstructionsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCActionAdvised", optional=true)
    protected static native CFString ActionAdvisedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCReferenceService", optional=true)
    protected static native CFString ReferenceServiceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCReferenceDate", optional=true)
    protected static native CFString ReferenceDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCReferenceNumber", optional=true)
    protected static native CFString ReferenceNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCDateCreated", optional=true)
    protected static native CFString DateCreatedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCTimeCreated", optional=true)
    protected static native CFString TimeCreatedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCDigitalCreationDate", optional=true)
    protected static native CFString DigitalCreationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCDigitalCreationTime", optional=true)
    protected static native CFString DigitalCreationTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCOriginatingProgram", optional=true)
    protected static native CFString OriginatingProgramKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCProgramVersion", optional=true)
    protected static native CFString ProgramVersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCObjectCycle", optional=true)
    protected static native CFString ObjectCycleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCByline", optional=true)
    protected static native CFString BylineKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCBylineTitle", optional=true)
    protected static native CFString BylineTitleKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCity", optional=true)
    protected static native CFString CityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCSubLocation", optional=true)
    protected static native CFString SubLocationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCProvinceState", optional=true)
    protected static native CFString ProvinceStateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCountryPrimaryLocationCode", optional=true)
    protected static native CFString CountryPrimaryLocationCodeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCountryPrimaryLocationName", optional=true)
    protected static native CFString CountryPrimaryLocationNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCOriginalTransmissionReference", optional=true)
    protected static native CFString OriginalTransmissionReferenceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCHeadline", optional=true)
    protected static native CFString HeadlineKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCredit", optional=true)
    protected static native CFString CreditKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCSource", optional=true)
    protected static native CFString SourceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCopyrightNotice", optional=true)
    protected static native CFString CopyrightNoticeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCContact", optional=true)
    protected static native CFString ContactKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCaptionAbstract", optional=true)
    protected static native CFString CaptionAbstractKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCWriterEditor", optional=true)
    protected static native CFString WriterEditorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCImageType", optional=true)
    protected static native CFString ImageTypeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCImageOrientation", optional=true)
    protected static native CFString ImageOrientationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCLanguageIdentifier", optional=true)
    protected static native CFString LanguageIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCStarRating", optional=true)
    protected static native CFString StarRatingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCCreatorContactInfo", optional=true)
    protected static native CFString CreatorContactInfoKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCRightsUsageTerms", optional=true)
    protected static native CFString RightsUsageTermsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCScene", optional=true)
    protected static native CFString SceneKey();
    /*</methods>*/
}
