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
import org.robovm.apple.foundation.CocoaUtility;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/Security/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(Security.class); }/*</bind>*/
    /*<constants>*/
    public static final long kSecAccessControlUserPresence = 1L;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecUseOperationPrompt", optional=true)
    public static native CFType kSecUseOperationPrompt();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecUseOperationPrompt", optional=true)
    public static native void kSecUseOperationPrompt(CFType v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecUseNoAuthenticationUI", optional=true)
    public static native CFType kSecUseNoAuthenticationUI();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecUseNoAuthenticationUI", optional=true)
    public static native void kSecUseNoAuthenticationUI(CFType v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecSharedPassword", optional=true)
    public static native CFType kSecSharedPassword();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kSecSharedPassword", optional=true)
    public static native void kSecSharedPassword(CFType v);
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecAccessControlGetTypeID", optional=true)
    public static native @MachineSizedUInt long secAccessControlGetTypeID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecAddSharedWebCredential", optional=true)
    public static native void secAddSharedWebCredential(CFString fqdn, CFString account, CFString password, ObjCBlock completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecRequestSharedWebCredential", optional=true)
    public static native void secRequestSharedWebCredential(CFString fqdn, CFString account, ObjCBlock completionHandler);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="SecCreateSharedWebCredentialPassword", optional=true)
    public static native CFString secCreateSharedWebCredentialPassword();
    /*</methods>*/
}
