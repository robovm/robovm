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
package org.robovm.apple.coretelephony;

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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CoreTelephony") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTCarrier/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTCarrierPtr extends Ptr<CTCarrier, CTCarrierPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CTCarrier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CTCarrier() {}
    protected CTCarrier(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "carrierName")
    public native String getCarrierName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "mobileCountryCode")
    public native String getMobileCountryCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "mobileNetworkCode")
    public native String getMobileNetworkCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "isoCountryCode")
    public native String getIsoCountryCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "allowsVOIP")
    public native boolean allowsVOIP();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
