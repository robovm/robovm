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
package org.robovm.apple.addressbookui;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBookUI") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ABPersonPredicateKeyPath/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonPredicateKeyPath/*</name>*/ 
    extends /*<extends>*/NSPredicateKeyPath/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ABPersonPredicateKeyPath/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ABPersonPredicateKeyPath toObject(Class<ABPersonPredicateKeyPath> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ABPersonPredicateKeyPath.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ABPersonPredicateKeyPath o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<ABPersonPredicateKeyPath> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABPersonPredicateKeyPath> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ABPersonPredicateKeyPath.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABPersonPredicateKeyPath> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (ABPersonPredicateKeyPath o : l) {
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
    public static final ABPersonPredicateKeyPath NamePrefix = new ABPersonPredicateKeyPath("NamePrefix");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath GivenName = new ABPersonPredicateKeyPath("GivenName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath MiddleName = new ABPersonPredicateKeyPath("MiddleName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath FamilyName = new ABPersonPredicateKeyPath("FamilyName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath NameSuffix = new ABPersonPredicateKeyPath("NameSuffix");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PreviousFamilyName = new ABPersonPredicateKeyPath("PreviousFamilyName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Nickname = new ABPersonPredicateKeyPath("Nickname");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticGivenName = new ABPersonPredicateKeyPath("PhoneticGivenName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticMiddleName = new ABPersonPredicateKeyPath("PhoneticMiddleName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticFamilyName = new ABPersonPredicateKeyPath("PhoneticFamilyName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath OrganizationName = new ABPersonPredicateKeyPath("OrganizationName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath DepartmentName = new ABPersonPredicateKeyPath("DepartmentName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath JobTitle = new ABPersonPredicateKeyPath("JobTitle");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Birthday = new ABPersonPredicateKeyPath("Birthday");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Note = new ABPersonPredicateKeyPath("Note");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneNumbers = new ABPersonPredicateKeyPath("PhoneNumbers");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath EmailAddresses = new ABPersonPredicateKeyPath("EmailAddresses");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath UrlAddresses = new ABPersonPredicateKeyPath("UrlAddresses");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Dates = new ABPersonPredicateKeyPath("Dates");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath InstantMessageAddresses = new ABPersonPredicateKeyPath("InstantMessageAddresses");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath RelatedNames = new ABPersonPredicateKeyPath("RelatedNames");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath SocialProfiles = new ABPersonPredicateKeyPath("SocialProfiles");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PostalAddresses = new ABPersonPredicateKeyPath("PostalAddresses");
    /*</constants>*/
    
    private static /*<name>*/ABPersonPredicateKeyPath/*</name>*/[] values = new /*<name>*/ABPersonPredicateKeyPath/*</name>*/[] {/*<value_list>*/NamePrefix, GivenName, MiddleName, FamilyName, NameSuffix, PreviousFamilyName, Nickname, PhoneticGivenName, PhoneticMiddleName, PhoneticFamilyName, OrganizationName, DepartmentName, JobTitle, Birthday, Note, PhoneNumbers, EmailAddresses, UrlAddresses, Dates, InstantMessageAddresses, RelatedNames, SocialProfiles, PostalAddresses/*</value_list>*/};
    
    /*<name>*/ABPersonPredicateKeyPath/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ABPersonPredicateKeyPath/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/ABPersonPredicateKeyPath/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonPredicateKeyPath/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AddressBookUI") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonNamePrefixProperty", optional=true)
        public static native NSString NamePrefix();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonGivenNameProperty", optional=true)
        public static native NSString GivenName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonMiddleNameProperty", optional=true)
        public static native NSString MiddleName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonFamilyNameProperty", optional=true)
        public static native NSString FamilyName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonNameSuffixProperty", optional=true)
        public static native NSString NameSuffix();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPreviousFamilyNameProperty", optional=true)
        public static native NSString PreviousFamilyName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonNicknameProperty", optional=true)
        public static native NSString Nickname();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPhoneticGivenNameProperty", optional=true)
        public static native NSString PhoneticGivenName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPhoneticMiddleNameProperty", optional=true)
        public static native NSString PhoneticMiddleName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPhoneticFamilyNameProperty", optional=true)
        public static native NSString PhoneticFamilyName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonOrganizationNameProperty", optional=true)
        public static native NSString OrganizationName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonDepartmentNameProperty", optional=true)
        public static native NSString DepartmentName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonJobTitleProperty", optional=true)
        public static native NSString JobTitle();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonBirthdayProperty", optional=true)
        public static native NSString Birthday();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonNoteProperty", optional=true)
        public static native NSString Note();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPhoneNumbersProperty", optional=true)
        public static native NSString PhoneNumbers();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonEmailAddressesProperty", optional=true)
        public static native NSString EmailAddresses();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonUrlAddressesProperty", optional=true)
        public static native NSString UrlAddresses();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonDatesProperty", optional=true)
        public static native NSString Dates();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonInstantMessageAddressesProperty", optional=true)
        public static native NSString InstantMessageAddresses();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonRelatedNamesProperty", optional=true)
        public static native NSString RelatedNames();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonSocialProfilesProperty", optional=true)
        public static native NSString SocialProfiles();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="ABPersonPostalAddressesProperty", optional=true)
        public static native NSString PostalAddresses();
        /*</values>*/
    }
}
