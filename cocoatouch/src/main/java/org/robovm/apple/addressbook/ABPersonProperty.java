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
package org.robovm.apple.addressbook;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@StronglyLinked
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonProperty/*</name>*/ 
    extends /*<extends>*/ABProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final ABPersonProperty FirstName = new ABPersonProperty("FirstNameValue");
    public static final ABPersonProperty LastName = new ABPersonProperty("LastNameValue");
    public static final ABPersonProperty MiddleName = new ABPersonProperty("MiddleNameValue");
    public static final ABPersonProperty Prefix = new ABPersonProperty("PrefixValue");
    public static final ABPersonProperty Suffix = new ABPersonProperty("SuffixValue");
    public static final ABPersonProperty Nickname = new ABPersonProperty("NicknameValue");
    public static final ABPersonProperty FirstNamePhonetic = new ABPersonProperty("FirstNamePhoneticValue");
    public static final ABPersonProperty LastNamePhonetic = new ABPersonProperty("LastNamePhoneticValue");
    public static final ABPersonProperty MiddleNamePhonetic = new ABPersonProperty("MiddleNamePhoneticValue");
    public static final ABPersonProperty Organization = new ABPersonProperty("OrganizationValue");
    public static final ABPersonProperty JobTitle = new ABPersonProperty("JobTitleValue");
    public static final ABPersonProperty Department = new ABPersonProperty("DepartmentValue");
    public static final ABPersonProperty Email = new ABPersonProperty("EmailValue");
    public static final ABPersonProperty Birthday = new ABPersonProperty("BirthdayValue");
    public static final ABPersonProperty Note = new ABPersonProperty("NoteValue");
    public static final ABPersonProperty CreationDate = new ABPersonProperty("CreationDateValue");
    public static final ABPersonProperty ModificationDate = new ABPersonProperty("ModificationDateValue");
    public static final ABPersonProperty Address = new ABPersonProperty("AddressValue");
    public static final ABPersonProperty Date = new ABPersonProperty("DateValue");
    public static final ABPersonProperty Kind = new ABPersonProperty("KindValue");
    public static final ABPersonProperty Phone = new ABPersonProperty("PhoneValue");
    public static final ABPersonProperty InstantMessage = new ABPersonProperty("InstantMessageValue");
    public static final ABPersonProperty URL = new ABPersonProperty("URLValue");
    public static final ABPersonProperty RelatedNames = new ABPersonProperty("RelatedNamesValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonProperty SocialProfile = new ABPersonProperty("SocialProfileValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final ABPersonProperty AlternateBirthday = new ABPersonProperty("AlternateBirthdayValue");
    
    private static ABPersonProperty[] values = new ABPersonProperty[] {FirstName, LastName, Prefix, Suffix, Nickname, 
        FirstNamePhonetic, LastNamePhonetic, MiddleNamePhonetic, Organization, JobTitle, Department, Email, Birthday, 
        Note, CreationDate, ModificationDate, Address, Date, Kind, Phone, InstantMessage, URL, RelatedNames, 
        SocialProfile, AlternateBirthday};

    private ABPersonProperty(String getterName) {
        super(ABPersonProperty.class, getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static ABPersonProperty valueOf(int value) {
        for (ABPersonProperty v : values) {
            if (v.value() == value) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonProperty/*</name>*/.class.getName());
    }
    
    public ABPropertyType getType() {
        return getType(value());
    }
    public String getLocalizedName() {
        return getLocalizedPropertyName(value());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonFirstNameProperty", optional=true)
    protected static native int FirstNameValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonLastNameProperty", optional=true)
    protected static native int LastNameValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonMiddleNameProperty", optional=true)
    protected static native int MiddleNameValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonPrefixProperty", optional=true)
    protected static native int PrefixValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonSuffixProperty", optional=true)
    protected static native int SuffixValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonNicknameProperty", optional=true)
    protected static native int NicknameValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonFirstNamePhoneticProperty", optional=true)
    protected static native int FirstNamePhoneticValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonLastNamePhoneticProperty", optional=true)
    protected static native int LastNamePhoneticValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonMiddleNamePhoneticProperty", optional=true)
    protected static native int MiddleNamePhoneticValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonOrganizationProperty", optional=true)
    protected static native int OrganizationValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonDepartmentProperty", optional=true)
    protected static native int DepartmentValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonJobTitleProperty", optional=true)
    protected static native int JobTitleValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonEmailProperty", optional=true)
    protected static native int EmailValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonBirthdayProperty", optional=true)
    protected static native int BirthdayValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonNoteProperty", optional=true)
    protected static native int NoteValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonCreationDateProperty", optional=true)
    protected static native int CreationDateValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonModificationDateProperty", optional=true)
    protected static native int ModificationDateValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonAddressProperty", optional=true)
    protected static native int AddressValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonDateProperty", optional=true)
    protected static native int DateValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonKindProperty", optional=true)
    protected static native int KindValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonPhoneProperty", optional=true)
    protected static native int PhoneValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonInstantMessageProperty", optional=true)
    protected static native int InstantMessageValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonURLProperty", optional=true)
    protected static native int URLValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonRelatedNamesProperty", optional=true)
    protected static native int RelatedNamesValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonSocialProfileProperty", optional=true)
    protected static native int SocialProfileValue();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @GlobalValue(symbol="kABPersonAlternateBirthdayProperty", optional=true)
    protected static native int AlternateBirthdayValue();
    
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Bridge(symbol="ABPersonGetTypeOfProperty", optional=true)
    protected static native ABPropertyType getType(int property);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Bridge(symbol="ABPersonCopyLocalizedPropertyName", optional=true)
    protected static native String getLocalizedPropertyName(int property);
    /*</methods>*/
}
