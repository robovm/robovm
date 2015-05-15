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
public class ABPersonDate {
    static { Bro.bind(ABPersonDate.class); }
    
    private NSDate date;
    private CFString label;
    
    public ABPersonDate(NSDate date, String label) {
        this.date = date;
        this.label = new CFString(label);
    }
    public ABPersonDate(NSDate date, ABPersonDateLabel label) {
        this.date = date;
        this.label = label.value();
    }
    protected ABPersonDate(NSDate date, CFString label) {
        this.date = date;
        this.label = label;
    }
    
    public NSDate getDate() {
        return date;
    }
    
    public String getLabel() {
        return label.toString();
    }
    protected CFString getLabel0() {
        return label;
    }
}
