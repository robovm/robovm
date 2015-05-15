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
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Accounts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACAccountCredential/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ACAccountCredentialPtr extends Ptr<ACAccountCredential, ACAccountCredentialPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(ACAccountCredential.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ACAccountCredential() {}
    protected ACAccountCredential(SkipInit skipInit) { super(skipInit); }
    public ACAccountCredential(String token, String secret) { super((SkipInit) null); initObject(init(token, secret)); }
    public ACAccountCredential(String token, String refreshToken, NSDate expiryDate) { super((SkipInit) null); initObject(init(token, refreshToken, expiryDate)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "oauthToken")
    public native String getOauthToken();
    @Property(selector = "setOauthToken:")
    public native void setOauthToken(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithOAuthToken:tokenSecret:")
    protected native @Pointer long init(String token, String secret);
    @Method(selector = "initWithOAuth2Token:refreshToken:expiryDate:")
    protected native @Pointer long init(String token, String refreshToken, NSDate expiryDate);
    /*</methods>*/
}
