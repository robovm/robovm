/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecCertificate/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecCertificatePtr extends Ptr<SecCertificate, SecCertificatePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecCertificate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kSecPropertyTypeTitle")
    public static native CFType PropertyTypeTitle();
    @GlobalValue(symbol="kSecPropertyTypeError")
    public static native CFType PropertyTypeError();
    
    @Bridge(symbol="SecCertificateGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="SecCertificateCreateWithData")
    public static native SecCertificate createWithData(CFAllocator allocator, CFData data);
    @Bridge(symbol="SecCertificateCopyData")
    public native CFData copyData();
    @Bridge(symbol="SecCertificateCopySubjectSummary")
    public native CFString copySubjectSummary();
    /*</methods>*/
}
