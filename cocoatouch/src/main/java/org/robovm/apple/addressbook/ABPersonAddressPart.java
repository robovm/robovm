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
package org.robovm.apple.addressbook;

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
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonAddressPart/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonAddressPart.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final ABPersonAddressPart Street = new ABPersonAddressPart("StreetValue");
    public static final ABPersonAddressPart City = new ABPersonAddressPart("CityValue");
    public static final ABPersonAddressPart State = new ABPersonAddressPart("StateValue");
    public static final ABPersonAddressPart ZIP = new ABPersonAddressPart("ZIPValue");
    public static final ABPersonAddressPart Country = new ABPersonAddressPart("CountryValue");
    public static final ABPersonAddressPart CountryCode = new ABPersonAddressPart("CountryCodeValue");
    private static ABPersonAddressPart[] values = new ABPersonAddressPart[] {Street, City, State, ZIP, Country, CountryCode};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ABPersonAddressPart(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ABPersonAddressPart valueOf(int value) {
        for (ABPersonAddressPart v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonAddressPart/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kABPersonAddressStreetKey", optional=true)
    protected static native CFString StreetValue();
    @GlobalValue(symbol="kABPersonAddressCityKey", optional=true)
    protected static native CFString CityValue();
    @GlobalValue(symbol="kABPersonAddressStateKey", optional=true)
    protected static native CFString StateValue();
    @GlobalValue(symbol="kABPersonAddressZIPKey", optional=true)
    protected static native CFString ZIPValue();
    @GlobalValue(symbol="kABPersonAddressCountryKey", optional=true)
    protected static native CFString CountryValue();
    @GlobalValue(symbol="kABPersonAddressCountryCodeKey", optional=true)
    protected static native CFString CountryCodeValue();
    /*</methods>*/
}
