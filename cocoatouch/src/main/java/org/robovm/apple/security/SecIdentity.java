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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecIdentity/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecIdentityPtr extends Ptr<SecIdentity, SecIdentityPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecIdentity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecIdentity() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecCertificate getCertificate() {
        SecCertificate.SecCertificatePtr ptr = new SecCertificate.SecCertificatePtr();
        getCertificate(ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public SecKey getPrivateKey() {
        SecKey.SecKeyPtr ptr = new SecKey.SecKeyPtr();
        getPrivateKey(ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecIdentityGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecIdentityCopyCertificate", optional=true)
    protected native OSStatus getCertificate(SecCertificate.SecCertificatePtr certificateRef);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecIdentityCopyPrivateKey", optional=true)
    protected native OSStatus getPrivateKey(SecKey.SecKeyPtr privateKeyRef);
    /*</methods>*/
}
