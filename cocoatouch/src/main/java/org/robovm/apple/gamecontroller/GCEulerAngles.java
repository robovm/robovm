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
package org.robovm.apple.gamecontroller;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCEulerAngles/*</name>*/ 
    extends /*<extends>*/Struct<GCEulerAngles>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCEulerAnglesPtr extends Ptr<GCEulerAngles, GCEulerAnglesPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCEulerAngles() {}
    public GCEulerAngles(double pitch, double yaw, double roll) {
        this.setPitch(pitch);
        this.setYaw(yaw);
        this.setRoll(roll);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getPitch();
    @StructMember(0) public native GCEulerAngles setPitch(double pitch);
    @StructMember(1) public native double getYaw();
    @StructMember(1) public native GCEulerAngles setYaw(double yaw);
    @StructMember(2) public native double getRoll();
    @StructMember(2) public native GCEulerAngles setRoll(double roll);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
