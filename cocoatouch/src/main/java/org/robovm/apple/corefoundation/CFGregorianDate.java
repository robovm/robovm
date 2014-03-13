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
package org.robovm.apple.corefoundation;

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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFGregorianDate/*</name>*/ 
    extends /*<extends>*/Struct<CFGregorianDate>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFGregorianDatePtr extends Ptr<CFGregorianDate, CFGregorianDatePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFGregorianDate() {}
    public CFGregorianDate(int year, byte month, byte day, byte hour, byte minute, double second) {
        this.year(year);
        this.month(month);
        this.day(day);
        this.hour(hour);
        this.minute(minute);
        this.second(second);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int year();
    @StructMember(0) public native CFGregorianDate year(int year);
    @StructMember(1) public native byte month();
    @StructMember(1) public native CFGregorianDate month(byte month);
    @StructMember(2) public native byte day();
    @StructMember(2) public native CFGregorianDate day(byte day);
    @StructMember(3) public native byte hour();
    @StructMember(3) public native CFGregorianDate hour(byte hour);
    @StructMember(4) public native byte minute();
    @StructMember(4) public native CFGregorianDate minute(byte minute);
    @StructMember(5) public native double second();
    @StructMember(5) public native CFGregorianDate second(double second);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
