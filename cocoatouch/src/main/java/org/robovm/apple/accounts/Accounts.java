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
package org.robovm.apple.accounts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Accounts")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Accounts/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Accounts.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierTwitter", optional=true)
    public static native NSString AccountTypeIdentifierTwitter();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierFacebook", optional=true)
    public static native NSString AccountTypeIdentifierFacebook();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierSinaWeibo", optional=true)
    public static native NSString AccountTypeIdentifierSinaWeibo();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="ACAccountTypeIdentifierTencentWeibo", optional=true)
    public static native NSString AccountTypeIdentifierTencentWeibo();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAppIdKey", optional=true)
    public static native NSString FacebookAppIdKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookPermissionsKey", optional=true)
    public static native NSString FacebookPermissionsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceKey", optional=true)
    public static native NSString FacebookAudienceKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceEveryone", optional=true)
    public static native NSString FacebookAudienceEveryone();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceFriends", optional=true)
    public static native NSString FacebookAudienceFriends();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceOnlyMe", optional=true)
    public static native NSString FacebookAudienceOnlyMe();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="ACTencentWeiboAppIdKey", optional=true)
    public static native NSString TencentWeiboAppIdKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ACAccountStoreDidChangeNotification", optional=true)
    public static native NSString AccountStoreDidChangeNotification();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="ACErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    /*</methods>*/
}
