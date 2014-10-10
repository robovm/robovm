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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLCredential/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLCredentialPtr extends Ptr<NSURLCredential, NSURLCredentialPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURLCredential.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURLCredential() {}
    protected NSURLCredential(SkipInit skipInit) { super(skipInit); }
    public NSURLCredential(String user, String password, NSURLCredentialPersistence persistence) { super((SkipInit) null); initObject(initWithUser$password$persistence$(user, password, persistence)); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSURLCredential(SecIdentity identity, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecCertificate> certArray, NSURLCredentialPersistence persistence) { super((SkipInit) null); initObject(init(identity, certArray, persistence)); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSURLCredential(SecTrust trust) { super((SkipInit) null); initObject(initWithTrust$(trust)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "persistence")
    public native NSURLCredentialPersistence getPersistence();
    @Property(selector = "user")
    public native String getUser();
    @Property(selector = "password")
    public native String getPassword();
    @Property(selector = "hasPassword")
    public native boolean isHasPassword();
    @Property(selector = "identity")
    public native SecIdentity getIdentity();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "certificates")
    public native NSArray<?> getCertificates();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithUser:password:persistence:")
    protected native @Pointer long initWithUser$password$persistence$(String user, String password, NSURLCredentialPersistence persistence);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "initWithIdentity:certificates:persistence:")
    protected native @Pointer long init(SecIdentity identity, @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<SecCertificate> certArray, NSURLCredentialPersistence persistence);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "initWithTrust:")
    protected native @Pointer long initWithTrust$(SecTrust trust);
    /*</methods>*/
}
