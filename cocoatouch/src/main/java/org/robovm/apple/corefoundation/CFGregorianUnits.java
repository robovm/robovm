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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFGregorianUnits/*</name>*/ 
    extends /*<extends>*/Struct<CFGregorianUnits>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFGregorianUnitsPtr extends Ptr<CFGregorianUnits, CFGregorianUnitsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFGregorianUnits() {}
    public CFGregorianUnits(int years, int months, int days, int hours, int minutes, double seconds) {
        this.setYears(years);
        this.setMonths(months);
        this.setDays(days);
        this.setHours(hours);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getYears();
    @StructMember(0) public native CFGregorianUnits setYears(int years);
    @StructMember(1) public native int getMonths();
    @StructMember(1) public native CFGregorianUnits setMonths(int months);
    @StructMember(2) public native int getDays();
    @StructMember(2) public native CFGregorianUnits setDays(int days);
    @StructMember(3) public native int getHours();
    @StructMember(3) public native CFGregorianUnits setHours(int hours);
    @StructMember(4) public native int getMinutes();
    @StructMember(4) public native CFGregorianUnits setMinutes(int minutes);
    @StructMember(5) public native double getSeconds();
    @StructMember(5) public native CFGregorianUnits setSeconds(double seconds);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
