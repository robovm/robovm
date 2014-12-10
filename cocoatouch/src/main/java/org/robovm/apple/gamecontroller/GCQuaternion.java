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
package org.robovm.apple.gamecontroller;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCQuaternion/*</name>*/ 
    extends /*<extends>*/Struct<GCQuaternion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCQuaternionPtr extends Ptr<GCQuaternion, GCQuaternionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCQuaternion() {}
    public GCQuaternion(double x, double y, double z, double w) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setW(w);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getX();
    @StructMember(0) public native GCQuaternion setX(double x);
    
    @Deprecated
    @StructMember(0) public native double x();
    @Deprecated
    @StructMember(0) public native GCQuaternion x(double x);
    
    @StructMember(1) public native double getY();
    @StructMember(1) public native GCQuaternion setY(double y);
    
    @Deprecated
    @StructMember(1) public native double y();
    @Deprecated
    @StructMember(1) public native GCQuaternion y(double y);
    
    @StructMember(2) public native double getZ();
    @StructMember(2) public native GCQuaternion setZ(double z);
    
    @Deprecated
    @StructMember(2) public native double z();
    @Deprecated
    @StructMember(2) public native GCQuaternion z(double z);
    
    @StructMember(3) public native double getW();
    @StructMember(3) public native GCQuaternion setW(double w);
    
    @Deprecated
    @StructMember(3) public native double w();
    @Deprecated
    @StructMember(3) public native GCQuaternion w(double w);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
