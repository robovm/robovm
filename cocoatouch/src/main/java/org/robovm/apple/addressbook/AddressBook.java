/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AddressBook/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AddressBook.class); }/*</bind>*/
    /*<constants>*/
    public static final int ABRecordInvalidID = -1;
    public static final int ABPropertyInvalidID = -1;
    public static final int ABSourceTypeSearchableMask = 0x01000000;
    public static final int ABMultiValueInvalidIdentifier = -1;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="ABAddressBookErrorDomain", optional=true)
    public static native String ABAddressBookErrorDomain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kABSourceNameProperty", optional=true)
    public static native int ABSourceNameProperty();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kABSourceTypeProperty", optional=true)
    public static native int ABSourceTypeProperty();
    @GlobalValue(symbol="kABWorkLabel", optional=true)
    public static native String ABWorkLabel();
    @GlobalValue(symbol="kABHomeLabel", optional=true)
    public static native String ABHomeLabel();
    @GlobalValue(symbol="kABOtherLabel", optional=true)
    public static native String ABOtherLabel();
    @GlobalValue(symbol="kABPersonFirstNameProperty", optional=true)
    public static native int ABPersonFirstNameProperty();
    @GlobalValue(symbol="kABPersonLastNameProperty", optional=true)
    public static native int ABPersonLastNameProperty();
    @GlobalValue(symbol="kABPersonMiddleNameProperty", optional=true)
    public static native int ABPersonMiddleNameProperty();
    @GlobalValue(symbol="kABPersonPrefixProperty", optional=true)
    public static native int ABPersonPrefixProperty();
    @GlobalValue(symbol="kABPersonSuffixProperty", optional=true)
    public static native int ABPersonSuffixProperty();
    @GlobalValue(symbol="kABPersonNicknameProperty", optional=true)
    public static native int ABPersonNicknameProperty();
    @GlobalValue(symbol="kABPersonFirstNamePhoneticProperty", optional=true)
    public static native int ABPersonFirstNamePhoneticProperty();
    @GlobalValue(symbol="kABPersonLastNamePhoneticProperty", optional=true)
    public static native int ABPersonLastNamePhoneticProperty();
    @GlobalValue(symbol="kABPersonMiddleNamePhoneticProperty", optional=true)
    public static native int ABPersonMiddleNamePhoneticProperty();
    @GlobalValue(symbol="kABPersonOrganizationProperty", optional=true)
    public static native int ABPersonOrganizationProperty();
    @GlobalValue(symbol="kABPersonJobTitleProperty", optional=true)
    public static native int ABPersonJobTitleProperty();
    @GlobalValue(symbol="kABPersonDepartmentProperty", optional=true)
    public static native int ABPersonDepartmentProperty();
    @GlobalValue(symbol="kABPersonEmailProperty", optional=true)
    public static native int ABPersonEmailProperty();
    @GlobalValue(symbol="kABPersonBirthdayProperty", optional=true)
    public static native int ABPersonBirthdayProperty();
    @GlobalValue(symbol="kABPersonNoteProperty", optional=true)
    public static native int ABPersonNoteProperty();
    @GlobalValue(symbol="kABPersonCreationDateProperty", optional=true)
    public static native int ABPersonCreationDateProperty();
    @GlobalValue(symbol="kABPersonModificationDateProperty", optional=true)
    public static native int ABPersonModificationDateProperty();
    @GlobalValue(symbol="kABPersonAddressProperty", optional=true)
    public static native int ABPersonAddressProperty();
    @GlobalValue(symbol="kABPersonAddressStreetKey", optional=true)
    public static native String ABPersonAddressStreetKey();
    @GlobalValue(symbol="kABPersonAddressCityKey", optional=true)
    public static native String ABPersonAddressCityKey();
    @GlobalValue(symbol="kABPersonAddressStateKey", optional=true)
    public static native String ABPersonAddressStateKey();
    @GlobalValue(symbol="kABPersonAddressZIPKey", optional=true)
    public static native String ABPersonAddressZIPKey();
    @GlobalValue(symbol="kABPersonAddressCountryKey", optional=true)
    public static native String ABPersonAddressCountryKey();
    @GlobalValue(symbol="kABPersonAddressCountryCodeKey", optional=true)
    public static native String ABPersonAddressCountryCodeKey();
    @GlobalValue(symbol="kABPersonDateProperty", optional=true)
    public static native int ABPersonDateProperty();
    @GlobalValue(symbol="kABPersonAnniversaryLabel", optional=true)
    public static native String ABPersonAnniversaryLabel();
    @GlobalValue(symbol="kABPersonKindProperty", optional=true)
    public static native int ABPersonKindProperty();
    @GlobalValue(symbol="kABPersonKindPerson", optional=true)
    public static native CFNumber ABPersonKindPerson();
    @GlobalValue(symbol="kABPersonKindOrganization", optional=true)
    public static native CFNumber ABPersonKindOrganization();
    @GlobalValue(symbol="kABPersonPhoneProperty", optional=true)
    public static native int ABPersonPhoneProperty();
    @GlobalValue(symbol="kABPersonPhoneMobileLabel", optional=true)
    public static native String ABPersonPhoneMobileLabel();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kABPersonPhoneIPhoneLabel", optional=true)
    public static native String ABPersonPhoneIPhoneLabel();
    @GlobalValue(symbol="kABPersonPhoneMainLabel", optional=true)
    public static native String ABPersonPhoneMainLabel();
    @GlobalValue(symbol="kABPersonPhoneHomeFAXLabel", optional=true)
    public static native String ABPersonPhoneHomeFAXLabel();
    @GlobalValue(symbol="kABPersonPhoneWorkFAXLabel", optional=true)
    public static native String ABPersonPhoneWorkFAXLabel();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonPhoneOtherFAXLabel", optional=true)
    public static native String ABPersonPhoneOtherFAXLabel();
    @GlobalValue(symbol="kABPersonPhonePagerLabel", optional=true)
    public static native String ABPersonPhonePagerLabel();
    @GlobalValue(symbol="kABPersonInstantMessageProperty", optional=true)
    public static native int ABPersonInstantMessageProperty();
    @GlobalValue(symbol="kABPersonInstantMessageServiceKey", optional=true)
    public static native String ABPersonInstantMessageServiceKey();
    @GlobalValue(symbol="kABPersonInstantMessageServiceYahoo", optional=true)
    public static native String ABPersonInstantMessageServiceYahoo();
    @GlobalValue(symbol="kABPersonInstantMessageServiceJabber", optional=true)
    public static native String ABPersonInstantMessageServiceJabber();
    @GlobalValue(symbol="kABPersonInstantMessageServiceMSN", optional=true)
    public static native String ABPersonInstantMessageServiceMSN();
    @GlobalValue(symbol="kABPersonInstantMessageServiceICQ", optional=true)
    public static native String ABPersonInstantMessageServiceICQ();
    @GlobalValue(symbol="kABPersonInstantMessageServiceAIM", optional=true)
    public static native String ABPersonInstantMessageServiceAIM();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceQQ", optional=true)
    public static native String ABPersonInstantMessageServiceQQ();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceGoogleTalk", optional=true)
    public static native String ABPersonInstantMessageServiceGoogleTalk();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceSkype", optional=true)
    public static native String ABPersonInstantMessageServiceSkype();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceFacebook", optional=true)
    public static native String ABPersonInstantMessageServiceFacebook();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonInstantMessageServiceGaduGadu", optional=true)
    public static native String ABPersonInstantMessageServiceGaduGadu();
    @GlobalValue(symbol="kABPersonInstantMessageUsernameKey", optional=true)
    public static native String ABPersonInstantMessageUsernameKey();
    @GlobalValue(symbol="kABPersonURLProperty", optional=true)
    public static native int ABPersonURLProperty();
    @GlobalValue(symbol="kABPersonHomePageLabel", optional=true)
    public static native String ABPersonHomePageLabel();
    @GlobalValue(symbol="kABPersonRelatedNamesProperty", optional=true)
    public static native int ABPersonRelatedNamesProperty();
    @GlobalValue(symbol="kABPersonFatherLabel", optional=true)
    public static native String ABPersonFatherLabel();
    @GlobalValue(symbol="kABPersonMotherLabel", optional=true)
    public static native String ABPersonMotherLabel();
    @GlobalValue(symbol="kABPersonParentLabel", optional=true)
    public static native String ABPersonParentLabel();
    @GlobalValue(symbol="kABPersonBrotherLabel", optional=true)
    public static native String ABPersonBrotherLabel();
    @GlobalValue(symbol="kABPersonSisterLabel", optional=true)
    public static native String ABPersonSisterLabel();
    @GlobalValue(symbol="kABPersonChildLabel", optional=true)
    public static native String ABPersonChildLabel();
    @GlobalValue(symbol="kABPersonFriendLabel", optional=true)
    public static native String ABPersonFriendLabel();
    @GlobalValue(symbol="kABPersonSpouseLabel", optional=true)
    public static native String ABPersonSpouseLabel();
    @GlobalValue(symbol="kABPersonPartnerLabel", optional=true)
    public static native String ABPersonPartnerLabel();
    @GlobalValue(symbol="kABPersonAssistantLabel", optional=true)
    public static native String ABPersonAssistantLabel();
    @GlobalValue(symbol="kABPersonManagerLabel", optional=true)
    public static native String ABPersonManagerLabel();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileProperty", optional=true)
    public static native int ABPersonSocialProfileProperty();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileURLKey", optional=true)
    public static native String ABPersonSocialProfileURLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceKey", optional=true)
    public static native String ABPersonSocialProfileServiceKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileUsernameKey", optional=true)
    public static native String ABPersonSocialProfileUsernameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileUserIdentifierKey", optional=true)
    public static native String ABPersonSocialProfileUserIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceTwitter", optional=true)
    public static native String ABPersonSocialProfileServiceTwitter();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceSinaWeibo", optional=true)
    public static native String ABPersonSocialProfileServiceSinaWeibo();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceGameCenter", optional=true)
    public static native String ABPersonSocialProfileServiceGameCenter();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceFacebook", optional=true)
    public static native String ABPersonSocialProfileServiceFacebook();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceMyspace", optional=true)
    public static native String ABPersonSocialProfileServiceMyspace();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceLinkedIn", optional=true)
    public static native String ABPersonSocialProfileServiceLinkedIn();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceFlickr", optional=true)
    public static native String ABPersonSocialProfileServiceFlickr();
    @GlobalValue(symbol="kABGroupNameProperty", optional=true)
    public static native int ABGroupNameProperty();
    /*</methods>*/
}
