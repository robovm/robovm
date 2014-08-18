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
package org.robovm.apple.coreservices;

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
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPAuthentication/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFHTTPAuthenticationPtr extends Ptr<CFHTTPAuthentication, CFHTTPAuthenticationPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFHTTPAuthentication.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFHTTPAuthentication() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthentication create(CFHTTPMessage response) {
        return create(null, response);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean isValid() {
        return isValid(null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationCreateFromResponse", optional=true)
    protected static native CFHTTPAuthentication create(CFAllocator alloc, CFHTTPMessage response);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationIsValid", optional=true)
    protected native boolean isValid(CFStreamError.CFStreamErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationAppliesToRequest", optional=true)
    public native boolean appliesToRequest(CFHTTPMessage request);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationRequiresOrderedRequests", optional=true)
    public native boolean requiresOrderedRequests();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationCopyRealm", optional=true)
    public native String getRealm();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationCopyDomains", optional=true)
    public native NSArray<NSURL> getDomains();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationCopyMethod", optional=true)
    public native String getMethod();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationRequiresUserNameAndPassword", optional=true)
    public native boolean requiresUserNameAndPassword();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFHTTPAuthenticationRequiresAccountDomain", optional=true)
    public native boolean requiresAccountDomain();
    /*</methods>*/
}
