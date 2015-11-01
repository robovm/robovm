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
package org.robovm.apple.contacts;

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
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNMutablePostalAddress/*</name>*/ 
    extends /*<extends>*/CNPostalAddress/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CNMutablePostalAddressPtr extends Ptr<CNMutablePostalAddress, CNMutablePostalAddressPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CNMutablePostalAddress.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CNMutablePostalAddress() {}
    protected CNMutablePostalAddress(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "street")
    public native String getStreet();
    @Property(selector = "setStreet:")
    public native void setStreet(String v);
    @Property(selector = "city")
    public native String getCity();
    @Property(selector = "setCity:")
    public native void setCity(String v);
    @Property(selector = "state")
    public native String getState();
    @Property(selector = "setState:")
    public native void setState(String v);
    @Property(selector = "postalCode")
    public native String getPostalCode();
    @Property(selector = "setPostalCode:")
    public native void setPostalCode(String v);
    @Property(selector = "country")
    public native String getCountry();
    @Property(selector = "setCountry:")
    public native void setCountry(String v);
    @Property(selector = "ISOCountryCode")
    public native String getISOCountryCode();
    @Property(selector = "setISOCountryCode:")
    public native void setISOCountryCode(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
