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
package org.robovm.apple.networkextension;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("NetworkExtension") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NEVPNProtocolIKEv2/*</name>*/ 
    extends /*<extends>*/NEVPNProtocolIPSec/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NEVPNProtocolIKEv2Ptr extends Ptr<NEVPNProtocolIKEv2, NEVPNProtocolIKEv2Ptr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NEVPNProtocolIKEv2.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NEVPNProtocolIKEv2() {}
    protected NEVPNProtocolIKEv2(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "deadPeerDetectionRate")
    public native NEVPNIKEv2DeadPeerDetectionRate getDeadPeerDetectionRate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setDeadPeerDetectionRate:")
    public native void setDeadPeerDetectionRate(NEVPNIKEv2DeadPeerDetectionRate v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "serverCertificateIssuerCommonName")
    public native String getServerCertificateIssuerCommonName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setServerCertificateIssuerCommonName:")
    public native void setServerCertificateIssuerCommonName(String v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "serverCertificateCommonName")
    public native String getServerCertificateCommonName();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setServerCertificateCommonName:")
    public native void setServerCertificateCommonName(String v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "certificateType")
    public native NEVPNIKEv2CertificateType getCertificateType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCertificateType:")
    public native void setCertificateType(NEVPNIKEv2CertificateType v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "IKESecurityAssociationParameters")
    public native NEVPNIKEv2SecurityAssociationParameters getIKESecurityAssociationParameters();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "childSecurityAssociationParameters")
    public native NEVPNIKEv2SecurityAssociationParameters getChildSecurityAssociationParameters();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
