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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.addressbookui.ABAddressFormating;

@Library("AddressBook")
public class ABPersonAddress {
    static { Bro.bind(ABPersonAddress.class); }
    
    private CFDictionary data;
    private CFString label;
    
    public ABPersonAddress(String label) {
        this.data = CFMutableDictionary.create();
        this.label = new CFString(label);
    }
    public ABPersonAddress(ABPropertyLabel label) {
        this.data = CFMutableDictionary.create();
        this.label = label.value();
    }
    protected ABPersonAddress(CFDictionary data, CFString label) {
        this.data = data;
        this.label = label;
    }
    
    public String getAddressPart(ABPersonAddressPart part) {
        if (data.containsKey(part.value())) {
            CFString val = data.get(part.value(), CFString.class);
            return val.toString();
        }
        return null;
    }
    public void setAddressPart(ABPersonAddressPart part, String s) {
        data.put(part.value(), new CFString(s));
    }
    
    public String getLabel() {
        return label.toString();
    }
    protected CFString getLabel0() {
        return label;
    }
    public CFDictionary getDictionary() {
        return data;
    }
    
    public String getStreet() {
        return getAddressPart(ABPersonAddressPart.Street);
    }
    public void setStreet(String s) {
        setAddressPart(ABPersonAddressPart.Street, s);
    }
    public String getCity() {
        return getAddressPart(ABPersonAddressPart.City);
    }
    public void setCity(String s) {
        setAddressPart(ABPersonAddressPart.City, s);
    }
    public String getState() {
        return getAddressPart(ABPersonAddressPart.State);
    }
    public void setState(String s) {
        setAddressPart(ABPersonAddressPart.State, s);
    }
    public String getZIP() {
        return getAddressPart(ABPersonAddressPart.ZIP);
    }
    public void setZIP(String s) {
        setAddressPart(ABPersonAddressPart.ZIP, s);
    }
    public String getCountry() {
        return getAddressPart(ABPersonAddressPart.Country);
    }
    public void setCountry(String s) {
        setAddressPart(ABPersonAddressPart.Country, s);
    }
    public String getCountryCode() {
        return getAddressPart(ABPersonAddressPart.CountryCode);
    }
    public void setCountryCode(String s) {
        setAddressPart(ABPersonAddressPart.CountryCode, s);
    }
    
    @Override
    public String toString () {
        return toString(false);
    }
    public String toString(boolean addCountryName) {
        return ABAddressFormating.createString(data, addCountryName);
    }
}
