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
        this.byte0(byte0);
        this.byte1(byte1);
        this.byte2(byte2);
        this.byte3(byte3);
        this.byte4(byte4);
        this.byte5(byte5);
        this.byte6(byte6);
        this.byte7(byte7);
        this.byte8(byte8);
        this.byte9(byte9);
        this.byte10(byte10);
        this.byte11(byte11);
        this.byte12(byte12);
        this.byte13(byte13);
        this.byte14(byte14);
        this.byte15(byte15);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte byte0();
    @StructMember(0) public native CFUUIDBytes byte0(byte byte0);
    @StructMember(1) public native byte byte1();
    @StructMember(1) public native CFUUIDBytes byte1(byte byte1);
    @StructMember(2) public native byte byte2();
    @StructMember(2) public native CFUUIDBytes byte2(byte byte2);
    @StructMember(3) public native byte byte3();
    @StructMember(3) public native CFUUIDBytes byte3(byte byte3);
    @StructMember(4) public native byte byte4();
    @StructMember(4) public native CFUUIDBytes byte4(byte byte4);
    @StructMember(5) public native byte byte5();
    @StructMember(5) public native CFUUIDBytes byte5(byte byte5);
    @StructMember(6) public native byte byte6();
    @StructMember(6) public native CFUUIDBytes byte6(byte byte6);
    @StructMember(7) public native byte byte7();
    @StructMember(7) public native CFUUIDBytes byte7(byte byte7);
    @StructMember(8) public native byte byte8();
    @StructMember(8) public native CFUUIDBytes byte8(byte byte8);
    @StructMember(9) public native byte byte9();
    @StructMember(9) public native CFUUIDBytes byte9(byte byte9);
    @StructMember(10) public native byte byte10();
    @StructMember(10) public native CFUUIDBytes byte10(byte byte10);
    @StructMember(11) public native byte byte11();
    @StructMember(11) public native CFUUIDBytes byte11(byte byte11);
    @StructMember(12) public native byte byte12();
    @StructMember(12) public native CFUUIDBytes byte12(byte byte12);
    @StructMember(13) public native byte byte13();
    @StructMember(13) public native CFUUIDBytes byte13(byte byte13);
    @StructMember(14) public native byte byte14();
    @StructMember(14) public native CFUUIDBytes byte14(byte byte14);
    @StructMember(15) public native byte byte15();
    @StructMember(15) public native CFUUIDBytes byte15(byte byte15);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
