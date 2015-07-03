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
package org.robovm.apple.imageio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyIPTC/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyIPTC/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyIPTC/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyIPTC> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyIPTC> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyIPTC.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyIPTC> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyIPTC o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectTypeReference = new CGImagePropertyIPTC("ObjectTypeReference");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectAttributeReference = new CGImagePropertyIPTC("ObjectAttributeReference");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectName = new CGImagePropertyIPTC("ObjectName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC EditStatus = new CGImagePropertyIPTC("EditStatus");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC EditorialUpdate = new CGImagePropertyIPTC("EditorialUpdate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Urgency = new CGImagePropertyIPTC("Urgency");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SubjectReference = new CGImagePropertyIPTC("SubjectReference");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Category = new CGImagePropertyIPTC("Category");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SupplementalCategory = new CGImagePropertyIPTC("SupplementalCategory");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC FixtureIdentifier = new CGImagePropertyIPTC("FixtureIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Keywords = new CGImagePropertyIPTC("Keywords");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ContentLocationCode = new CGImagePropertyIPTC("ContentLocationCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ContentLocationName = new CGImagePropertyIPTC("ContentLocationName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReleaseDate = new CGImagePropertyIPTC("ReleaseDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReleaseTime = new CGImagePropertyIPTC("ReleaseTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ExpirationDate = new CGImagePropertyIPTC("ExpirationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ExpirationTime = new CGImagePropertyIPTC("ExpirationTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SpecialInstructions = new CGImagePropertyIPTC("SpecialInstructions");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ActionAdvised = new CGImagePropertyIPTC("ActionAdvised");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceService = new CGImagePropertyIPTC("ReferenceService");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceDate = new CGImagePropertyIPTC("ReferenceDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ReferenceNumber = new CGImagePropertyIPTC("ReferenceNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DateCreated = new CGImagePropertyIPTC("DateCreated");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC TimeCreated = new CGImagePropertyIPTC("TimeCreated");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DigitalCreationDate = new CGImagePropertyIPTC("DigitalCreationDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC DigitalCreationTime = new CGImagePropertyIPTC("DigitalCreationTime");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC OriginatingProgram = new CGImagePropertyIPTC("OriginatingProgram");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ProgramVersion = new CGImagePropertyIPTC("ProgramVersion");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ObjectCycle = new CGImagePropertyIPTC("ObjectCycle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Byline = new CGImagePropertyIPTC("Byline");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC BylineTitle = new CGImagePropertyIPTC("BylineTitle");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC City = new CGImagePropertyIPTC("City");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC SubLocation = new CGImagePropertyIPTC("SubLocation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ProvinceState = new CGImagePropertyIPTC("ProvinceState");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CountryPrimaryLocationCode = new CGImagePropertyIPTC("CountryPrimaryLocationCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CountryPrimaryLocationName = new CGImagePropertyIPTC("CountryPrimaryLocationName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC OriginalTransmissionReference = new CGImagePropertyIPTC("OriginalTransmissionReference");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Headline = new CGImagePropertyIPTC("Headline");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Credit = new CGImagePropertyIPTC("Credit");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Source = new CGImagePropertyIPTC("Source");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CopyrightNotice = new CGImagePropertyIPTC("CopyrightNotice");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Contact = new CGImagePropertyIPTC("Contact");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CaptionAbstract = new CGImagePropertyIPTC("CaptionAbstract");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC WriterEditor = new CGImagePropertyIPTC("WriterEditor");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ImageType = new CGImagePropertyIPTC("ImageType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC ImageOrientation = new CGImagePropertyIPTC("ImageOrientation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC LanguageIdentifier = new CGImagePropertyIPTC("LanguageIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC StarRating = new CGImagePropertyIPTC("StarRating");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC CreatorContactInfo = new CGImagePropertyIPTC("CreatorContactInfo");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC RightsUsageTerms = new CGImagePropertyIPTC("RightsUsageTerms");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyIPTC Scene = new CGImagePropertyIPTC("Scene");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyIPTC/*</name>*/[] values = new /*<name>*/CGImagePropertyIPTC/*</name>*/[] {/*<value_list>*/ObjectTypeReference, ObjectAttributeReference, ObjectName, EditStatus, EditorialUpdate, Urgency, SubjectReference, Category, SupplementalCategory, FixtureIdentifier, Keywords, ContentLocationCode, ContentLocationName, ReleaseDate, ReleaseTime, ExpirationDate, ExpirationTime, SpecialInstructions, ActionAdvised, ReferenceService, ReferenceDate, ReferenceNumber, DateCreated, TimeCreated, DigitalCreationDate, DigitalCreationTime, OriginatingProgram, ProgramVersion, ObjectCycle, Byline, BylineTitle, City, SubLocation, ProvinceState, CountryPrimaryLocationCode, CountryPrimaryLocationName, OriginalTransmissionReference, Headline, Credit, Source, CopyrightNotice, Contact, CaptionAbstract, WriterEditor, ImageType, ImageOrientation, LanguageIdentifier, StarRating, CreatorContactInfo, RightsUsageTerms, Scene/*</value_list>*/};
    
    /*<name>*/CGImagePropertyIPTC/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyIPTC/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyIPTC/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyIPTC/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCObjectTypeReference", optional=true)
        public static native CFString ObjectTypeReference();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCObjectAttributeReference", optional=true)
        public static native CFString ObjectAttributeReference();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCObjectName", optional=true)
        public static native CFString ObjectName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCEditStatus", optional=true)
        public static native CFString EditStatus();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCEditorialUpdate", optional=true)
        public static native CFString EditorialUpdate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCUrgency", optional=true)
        public static native CFString Urgency();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCSubjectReference", optional=true)
        public static native CFString SubjectReference();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCategory", optional=true)
        public static native CFString Category();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCSupplementalCategory", optional=true)
        public static native CFString SupplementalCategory();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCFixtureIdentifier", optional=true)
        public static native CFString FixtureIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCKeywords", optional=true)
        public static native CFString Keywords();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContentLocationCode", optional=true)
        public static native CFString ContentLocationCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContentLocationName", optional=true)
        public static native CFString ContentLocationName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCReleaseDate", optional=true)
        public static native CFString ReleaseDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCReleaseTime", optional=true)
        public static native CFString ReleaseTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCExpirationDate", optional=true)
        public static native CFString ExpirationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCExpirationTime", optional=true)
        public static native CFString ExpirationTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCSpecialInstructions", optional=true)
        public static native CFString SpecialInstructions();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCActionAdvised", optional=true)
        public static native CFString ActionAdvised();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCReferenceService", optional=true)
        public static native CFString ReferenceService();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCReferenceDate", optional=true)
        public static native CFString ReferenceDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCReferenceNumber", optional=true)
        public static native CFString ReferenceNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCDateCreated", optional=true)
        public static native CFString DateCreated();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCTimeCreated", optional=true)
        public static native CFString TimeCreated();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCDigitalCreationDate", optional=true)
        public static native CFString DigitalCreationDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCDigitalCreationTime", optional=true)
        public static native CFString DigitalCreationTime();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCOriginatingProgram", optional=true)
        public static native CFString OriginatingProgram();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCProgramVersion", optional=true)
        public static native CFString ProgramVersion();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCObjectCycle", optional=true)
        public static native CFString ObjectCycle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCByline", optional=true)
        public static native CFString Byline();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCBylineTitle", optional=true)
        public static native CFString BylineTitle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCity", optional=true)
        public static native CFString City();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCSubLocation", optional=true)
        public static native CFString SubLocation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCProvinceState", optional=true)
        public static native CFString ProvinceState();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCountryPrimaryLocationCode", optional=true)
        public static native CFString CountryPrimaryLocationCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCountryPrimaryLocationName", optional=true)
        public static native CFString CountryPrimaryLocationName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCOriginalTransmissionReference", optional=true)
        public static native CFString OriginalTransmissionReference();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCHeadline", optional=true)
        public static native CFString Headline();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCredit", optional=true)
        public static native CFString Credit();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCSource", optional=true)
        public static native CFString Source();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCopyrightNotice", optional=true)
        public static native CFString CopyrightNotice();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCContact", optional=true)
        public static native CFString Contact();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCaptionAbstract", optional=true)
        public static native CFString CaptionAbstract();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCWriterEditor", optional=true)
        public static native CFString WriterEditor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCImageType", optional=true)
        public static native CFString ImageType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCImageOrientation", optional=true)
        public static native CFString ImageOrientation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCLanguageIdentifier", optional=true)
        public static native CFString LanguageIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCStarRating", optional=true)
        public static native CFString StarRating();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCCreatorContactInfo", optional=true)
        public static native CFString CreatorContactInfo();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCRightsUsageTerms", optional=true)
        public static native CFString RightsUsageTerms();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyIPTCScene", optional=true)
        public static native CFString Scene();
        /*</values>*/
    }
}
