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
package org.robovm.apple.addressbookui;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ABPersonPredicateKeyPath.Marshaler.class)
/*<annotations>*/@Library("AddressBookUI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonPredicateKeyPath/*</name>*/ 
    extends /*<extends>*/NSPredicateKeyPath/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonPredicateKeyPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath NamePrefix = new ABPersonPredicateKeyPath("NamePrefixValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath GivenName = new ABPersonPredicateKeyPath("GivenNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath MiddleName = new ABPersonPredicateKeyPath("MiddleNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath FamilyName = new ABPersonPredicateKeyPath("FamilyNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath NameSuffix = new ABPersonPredicateKeyPath("NameSuffixValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PreviousFamilyName = new ABPersonPredicateKeyPath("PreviousFamilyNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Nickname = new ABPersonPredicateKeyPath("NicknameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticGivenName = new ABPersonPredicateKeyPath("PhoneticGivenNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticMiddleName = new ABPersonPredicateKeyPath("PhoneticMiddleNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneticFamilyName = new ABPersonPredicateKeyPath("PhoneticFamilyNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath OrganizationName = new ABPersonPredicateKeyPath("OrganizationNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath DepartmentName = new ABPersonPredicateKeyPath("DepartmentNameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath JobTitle = new ABPersonPredicateKeyPath("JobTitleValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Birthday = new ABPersonPredicateKeyPath("BirthdayValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Note = new ABPersonPredicateKeyPath("NoteValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PhoneNumbers = new ABPersonPredicateKeyPath("PhoneNumbersValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath EmailAddresses = new ABPersonPredicateKeyPath("EmailAddressesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath UrlAddresses = new ABPersonPredicateKeyPath("UrlAddressesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath Dates = new ABPersonPredicateKeyPath("DatesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath InstantMessageAddresses = new ABPersonPredicateKeyPath("InstantMessageAddressesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath RelatedNames = new ABPersonPredicateKeyPath("RelatedNamesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath SocialProfiles = new ABPersonPredicateKeyPath("SocialProfilesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonPredicateKeyPath PostalAddresses = new ABPersonPredicateKeyPath("PostalAddressesValue");
    
    private static ABPersonPredicateKeyPath[] values = new ABPersonPredicateKeyPath[] {NamePrefix, GivenName, MiddleName, FamilyName, 
        NameSuffix, PreviousFamilyName, Nickname, PhoneticGivenName, PhoneticMiddleName, PhoneticFamilyName, OrganizationName, 
        DepartmentName, JobTitle, Birthday, Note, PhoneNumbers, EmailAddresses, UrlAddresses, Dates, InstantMessageAddresses, 
        RelatedNames, SocialProfiles, PostalAddresses};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ABPersonPredicateKeyPath(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ABPersonPredicateKeyPath valueOf(NSString value) {
        for (ABPersonPredicateKeyPath v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonPredicateKeyPath/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonNamePrefixProperty", optional=true)
    protected static native NSString NamePrefixValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonGivenNameProperty", optional=true)
    protected static native NSString GivenNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonMiddleNameProperty", optional=true)
    protected static native NSString MiddleNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonFamilyNameProperty", optional=true)
    protected static native NSString FamilyNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonNameSuffixProperty", optional=true)
    protected static native NSString NameSuffixValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPreviousFamilyNameProperty", optional=true)
    protected static native NSString PreviousFamilyNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonNicknameProperty", optional=true)
    protected static native NSString NicknameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPhoneticGivenNameProperty", optional=true)
    protected static native NSString PhoneticGivenNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPhoneticMiddleNameProperty", optional=true)
    protected static native NSString PhoneticMiddleNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPhoneticFamilyNameProperty", optional=true)
    protected static native NSString PhoneticFamilyNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonOrganizationNameProperty", optional=true)
    protected static native NSString OrganizationNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonDepartmentNameProperty", optional=true)
    protected static native NSString DepartmentNameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonJobTitleProperty", optional=true)
    protected static native NSString JobTitleValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonBirthdayProperty", optional=true)
    protected static native NSString BirthdayValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonNoteProperty", optional=true)
    protected static native NSString NoteValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPhoneNumbersProperty", optional=true)
    protected static native NSString PhoneNumbersValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonEmailAddressesProperty", optional=true)
    protected static native NSString EmailAddressesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonUrlAddressesProperty", optional=true)
    protected static native NSString UrlAddressesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonDatesProperty", optional=true)
    protected static native NSString DatesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonInstantMessageAddressesProperty", optional=true)
    protected static native NSString InstantMessageAddressesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonRelatedNamesProperty", optional=true)
    protected static native NSString RelatedNamesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonSocialProfilesProperty", optional=true)
    protected static native NSString SocialProfilesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="ABPersonPostalAddressesProperty", optional=true)
    protected static native NSString PostalAddressesValue();
    /*</methods>*/
}
