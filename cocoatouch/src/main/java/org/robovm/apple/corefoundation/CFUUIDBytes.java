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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFUUIDBytes/*</name>*/ 
    extends /*<extends>*/Struct<CFUUIDBytes>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFUUIDBytesPtr extends Ptr<CFUUIDBytes, CFUUIDBytesPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFUUIDBytes() {}
    public CFUUIDBytes(byte byte0, byte byte1, byte byte2, byte byte3, byte byte4, byte byte5, byte byte6, byte byte7, byte byte8, byte byte9, byte byte10, byte byte11, byte byte12, byte byte13, byte byte14, byte byte15) {
        this.setByte0(byte0);
        this.setByte1(byte1);
        this.setByte2(byte2);
        this.setByte3(byte3);
        this.setByte4(byte4);
        this.setByte5(byte5);
        this.setByte6(byte6);
        this.setByte7(byte7);
        this.setByte8(byte8);
        this.setByte9(byte9);
        this.setByte10(byte10);
        this.setByte11(byte11);
        this.setByte12(byte12);
        this.setByte13(byte13);
        this.setByte14(byte14);
        this.setByte15(byte15);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte getByte0();
    @StructMember(0) public native CFUUIDBytes setByte0(byte byte0);
    @StructMember(1) public native byte getByte1();
    @StructMember(1) public native CFUUIDBytes setByte1(byte byte1);
    @StructMember(2) public native byte getByte2();
    @StructMember(2) public native CFUUIDBytes setByte2(byte byte2);
    @StructMember(3) public native byte getByte3();
    @StructMember(3) public native CFUUIDBytes setByte3(byte byte3);
    @StructMember(4) public native byte getByte4();
    @StructMember(4) public native CFUUIDBytes setByte4(byte byte4);
    @StructMember(5) public native byte getByte5();
    @StructMember(5) public native CFUUIDBytes setByte5(byte byte5);
    @StructMember(6) public native byte getByte6();
    @StructMember(6) public native CFUUIDBytes setByte6(byte byte6);
    @StructMember(7) public native byte getByte7();
    @StructMember(7) public native CFUUIDBytes setByte7(byte byte7);
    @StructMember(8) public native byte getByte8();
    @StructMember(8) public native CFUUIDBytes setByte8(byte byte8);
    @StructMember(9) public native byte getByte9();
    @StructMember(9) public native CFUUIDBytes setByte9(byte byte9);
    @StructMember(10) public native byte getByte10();
    @StructMember(10) public native CFUUIDBytes setByte10(byte byte10);
    @StructMember(11) public native byte getByte11();
    @StructMember(11) public native CFUUIDBytes setByte11(byte byte11);
    @StructMember(12) public native byte getByte12();
    @StructMember(12) public native CFUUIDBytes setByte12(byte byte12);
    @StructMember(13) public native byte getByte13();
    @StructMember(13) public native CFUUIDBytes setByte13(byte byte13);
    @StructMember(14) public native byte getByte14();
    @StructMember(14) public native CFUUIDBytes setByte14(byte byte14);
    @StructMember(15) public native byte getByte15();
    @StructMember(15) public native CFUUIDBytes setByte15(byte byte15);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
