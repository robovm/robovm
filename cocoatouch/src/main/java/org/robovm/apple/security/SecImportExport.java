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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecImportExport/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(SecImportExport.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportExportPassphrase", optional=true)
    public static native CFString KeyPassphrase();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportItemLabel", optional=true)
    public static native CFString KeyItemLabel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportItemKeyID", optional=true)
    public static native CFString KeyItemKeyID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportItemTrust", optional=true)
    public static native CFString KeyItemTrust();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportItemCertChain", optional=true)
    public static native CFString KeyItemCertChain();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kSecImportItemIdentity", optional=true)
    public static native CFString KeyItemIdentity();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPKCS12Import", optional=true)
    public static native int importPKCS12(CFData pkcs12_data, CFDictionary options, CFArray.CFArrayPtr items);
    /*</methods>*/
}
