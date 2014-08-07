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
package org.robovm.apple.coremotion;

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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMRotationMatrix/*</name>*/ 
    extends /*<extends>*/Struct<CMRotationMatrix>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMRotationMatrixPtr extends Ptr<CMRotationMatrix, CMRotationMatrixPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMRotationMatrix() {}
    public CMRotationMatrix(double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
        this.m11(m11);
        this.m12(m12);
        this.m13(m13);
        this.m21(m21);
        this.m22(m22);
        this.m23(m23);
        this.m31(m31);
        this.m32(m32);
        this.m33(m33);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double m11();
    @StructMember(0) public native CMRotationMatrix m11(double m11);
    @StructMember(1) public native double m12();
    @StructMember(1) public native CMRotationMatrix m12(double m12);
    @StructMember(2) public native double m13();
    @StructMember(2) public native CMRotationMatrix m13(double m13);
    @StructMember(3) public native double m21();
    @StructMember(3) public native CMRotationMatrix m21(double m21);
    @StructMember(4) public native double m22();
    @StructMember(4) public native CMRotationMatrix m22(double m22);
    @StructMember(5) public native double m23();
    @StructMember(5) public native CMRotationMatrix m23(double m23);
    @StructMember(6) public native double m31();
    @StructMember(6) public native CMRotationMatrix m31(double m31);
    @StructMember(7) public native double m32();
    @StructMember(7) public native CMRotationMatrix m32(double m32);
    @StructMember(8) public native double m33();
    @StructMember(8) public native CMRotationMatrix m33(double m33);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
