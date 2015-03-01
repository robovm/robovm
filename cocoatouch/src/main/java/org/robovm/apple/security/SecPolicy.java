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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecPolicy/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecPolicyPtr extends Ptr<SecPolicy, SecPolicyPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecPolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecPolicy() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCopyProperties", optional=true)
    public native SecPolicyProperties getProperties();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateBasicX509", optional=true)
    public static native SecPolicy createBasicX509();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateSSL", optional=true)
    public static native SecPolicy createSSL(boolean server, String hostname);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateRevocation", optional=true)
    public static native SecPolicy createRevocation(SecRevocationPolicyFlags revocationFlags);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateWithProperties", optional=true)
    public static native SecPolicy create(SecPolicyIdentifier policyIdentifier, SecPolicyProperties properties);
    /*</methods>*/
}
