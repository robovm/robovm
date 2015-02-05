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

@Library("AddressBook")
public class ABPersonPhoneNumber {
    static { Bro.bind(ABPersonPhoneNumber.class); }
    
    private CFString number;
    private CFString label;
    
    public ABPersonPhoneNumber(String number, String label) {
        this.number = new CFString(number);
        this.label = new CFString(label);
    }
    public ABPersonPhoneNumber(String number, ABPersonPhoneLabel label) {
        this.number = new CFString(number);
        this.label = label.value();
    }
    protected ABPersonPhoneNumber(CFString number, CFString label) {
        this.number = number;
        this.label = label;
    }
    
    public String getNumber() {
        return number.toString();
    }
    protected CFString getNumber0() {
        return number;
    }
    public String getLabel() {
        return label.toString();
    }
    protected CFString getLabel0() {
        return label;
    }
}
