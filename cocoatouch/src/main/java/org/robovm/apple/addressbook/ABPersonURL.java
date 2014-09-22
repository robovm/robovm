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
public class ABPersonURL {
    static { Bro.bind(ABPersonURL.class); }
    
    private CFString url;
    private CFString label;
    
    public ABPersonURL(String url, String label) {
        this.url = new CFString(url);
        this.label = new CFString(label);
    }
    
    public ABPersonURL(String url, ABPersonURLLabel label) {
        this.url = new CFString(url);
        this.label = label.value();
    }
    
    protected ABPersonURL(CFString url, CFString label) {
        this.url = url;
        this.label = label;
    }
    
    public String getURL() {
        return url.toString();
    }
    public CFString getURL0() {
        return url;
    }
    
    public String getLabel() {
        return label.toString();
    }
    protected CFString getLabel0() {
        return label;
    }
}
