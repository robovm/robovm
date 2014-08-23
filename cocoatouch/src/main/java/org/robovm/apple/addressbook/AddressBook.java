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
    @GlobalValue(symbol="kABPersonAnniversaryLabel", optional=true)
    public static native String ABPersonAnniversaryLabel();
    @GlobalValue(symbol="kABPersonKindPerson", optional=true)
    public static native CFNumber ABPersonKindPerson();
    @GlobalValue(symbol="kABPersonKindOrganization", optional=true)
    public static native CFNumber ABPersonKindOrganization();
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
    @GlobalValue(symbol="kABPersonHomePageLabel", optional=true)
    public static native String ABPersonHomePageLabel();
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
    /*</methods>*/
}
