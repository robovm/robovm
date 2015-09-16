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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNContactPropertyKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContactPropertyKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNContactPropertyKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNContactPropertyKey toObject(Class<CNContactPropertyKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNContactPropertyKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNContactPropertyKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNContactPropertyKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNContactPropertyKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNContactPropertyKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNContactPropertyKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNContactPropertyKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Identifier = new CNContactPropertyKey("Identifier");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey NamePrefix = new CNContactPropertyKey("NamePrefix");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey GivenName = new CNContactPropertyKey("GivenName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey MiddleName = new CNContactPropertyKey("MiddleName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey FamilyName = new CNContactPropertyKey("FamilyName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PreviousFamilyName = new CNContactPropertyKey("PreviousFamilyName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey NameSuffix = new CNContactPropertyKey("NameSuffix");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Nickname = new CNContactPropertyKey("Nickname");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PhoneticGivenName = new CNContactPropertyKey("PhoneticGivenName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PhoneticMiddleName = new CNContactPropertyKey("PhoneticMiddleName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PhoneticFamilyName = new CNContactPropertyKey("PhoneticFamilyName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey OrganizationName = new CNContactPropertyKey("OrganizationName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey DepartmentName = new CNContactPropertyKey("DepartmentName");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey JobTitle = new CNContactPropertyKey("JobTitle");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Birthday = new CNContactPropertyKey("Birthday");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey NonGregorianBirthday = new CNContactPropertyKey("NonGregorianBirthday");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Note = new CNContactPropertyKey("Note");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey ImageData = new CNContactPropertyKey("ImageData");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey ThumbnailImageData = new CNContactPropertyKey("ThumbnailImageData");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey ImageDataAvailable = new CNContactPropertyKey("ImageDataAvailable");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Type = new CNContactPropertyKey("Type");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PhoneNumbers = new CNContactPropertyKey("PhoneNumbers");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey EmailAddresses = new CNContactPropertyKey("EmailAddresses");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey PostalAddresses = new CNContactPropertyKey("PostalAddresses");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Dates = new CNContactPropertyKey("Dates");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey UrlAddresses = new CNContactPropertyKey("UrlAddresses");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey Relations = new CNContactPropertyKey("Relations");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey SocialProfiles = new CNContactPropertyKey("SocialProfiles");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContactPropertyKey InstantMessageAddresses = new CNContactPropertyKey("InstantMessageAddresses");
    /*</constants>*/
    
    private static /*<name>*/CNContactPropertyKey/*</name>*/[] values = new /*<name>*/CNContactPropertyKey/*</name>*/[] {/*<value_list>*/Identifier, NamePrefix, GivenName, MiddleName, FamilyName, PreviousFamilyName, NameSuffix, Nickname, PhoneticGivenName, PhoneticMiddleName, PhoneticFamilyName, OrganizationName, DepartmentName, JobTitle, Birthday, NonGregorianBirthday, Note, ImageData, ThumbnailImageData, ImageDataAvailable, Type, PhoneNumbers, EmailAddresses, PostalAddresses, Dates, UrlAddresses, Relations, SocialProfiles, InstantMessageAddresses/*</value_list>*/};
    
    /*<name>*/CNContactPropertyKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNContactPropertyKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNContactPropertyKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNContactPropertyKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactIdentifierKey", optional=true)
        public static native NSString Identifier();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactNamePrefixKey", optional=true)
        public static native NSString NamePrefix();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactGivenNameKey", optional=true)
        public static native NSString GivenName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactMiddleNameKey", optional=true)
        public static native NSString MiddleName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactFamilyNameKey", optional=true)
        public static native NSString FamilyName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPreviousFamilyNameKey", optional=true)
        public static native NSString PreviousFamilyName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactNameSuffixKey", optional=true)
        public static native NSString NameSuffix();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactNicknameKey", optional=true)
        public static native NSString Nickname();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPhoneticGivenNameKey", optional=true)
        public static native NSString PhoneticGivenName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPhoneticMiddleNameKey", optional=true)
        public static native NSString PhoneticMiddleName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPhoneticFamilyNameKey", optional=true)
        public static native NSString PhoneticFamilyName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactOrganizationNameKey", optional=true)
        public static native NSString OrganizationName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactDepartmentNameKey", optional=true)
        public static native NSString DepartmentName();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactJobTitleKey", optional=true)
        public static native NSString JobTitle();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactBirthdayKey", optional=true)
        public static native NSString Birthday();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactNonGregorianBirthdayKey", optional=true)
        public static native NSString NonGregorianBirthday();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactNoteKey", optional=true)
        public static native NSString Note();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactImageDataKey", optional=true)
        public static native NSString ImageData();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactThumbnailImageDataKey", optional=true)
        public static native NSString ThumbnailImageData();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactImageDataAvailableKey", optional=true)
        public static native NSString ImageDataAvailable();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactTypeKey", optional=true)
        public static native NSString Type();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPhoneNumbersKey", optional=true)
        public static native NSString PhoneNumbers();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactEmailAddressesKey", optional=true)
        public static native NSString EmailAddresses();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactPostalAddressesKey", optional=true)
        public static native NSString PostalAddresses();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactDatesKey", optional=true)
        public static native NSString Dates();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactUrlAddressesKey", optional=true)
        public static native NSString UrlAddresses();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactRelationsKey", optional=true)
        public static native NSString Relations();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactSocialProfilesKey", optional=true)
        public static native NSString SocialProfiles();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContactInstantMessageAddressesKey", optional=true)
        public static native NSString InstantMessageAddresses();
        /*</values>*/
    }
}
