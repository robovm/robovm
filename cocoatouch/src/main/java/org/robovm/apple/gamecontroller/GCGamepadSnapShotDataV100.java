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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameController")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCGamepadSnapShotDataV100/*</name>*/ 
    extends /*<extends>*/Struct<GCGamepadSnapShotDataV100>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCGamepadSnapShotDataV100Ptr extends Ptr<GCGamepadSnapShotDataV100, GCGamepadSnapShotDataV100Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GCGamepadSnapShotDataV100.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCGamepadSnapShotDataV100() {}
    public GCGamepadSnapShotDataV100(short version, short size, float dpadX, float dpadY, float buttonA, float buttonB, float buttonX, float buttonY, float leftShoulder, float rightShoulder) {
        this.setVersion(version);
        this.setSize(size);
        this.setDpadX(dpadX);
        this.setDpadY(dpadY);
        this.setButtonA(buttonA);
        this.setButtonB(buttonB);
        this.setButtonX(buttonX);
        this.setButtonY(buttonY);
        this.setLeftShoulder(leftShoulder);
        this.setRightShoulder(rightShoulder);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getVersion();
    @StructMember(0) public native GCGamepadSnapShotDataV100 setVersion(short version);
    @StructMember(1) public native short getSize();
    @StructMember(1) public native GCGamepadSnapShotDataV100 setSize(short size);
    @StructMember(2) public native float getDpadX();
    @StructMember(2) public native GCGamepadSnapShotDataV100 setDpadX(float dpadX);
    @StructMember(3) public native float getDpadY();
    @StructMember(3) public native GCGamepadSnapShotDataV100 setDpadY(float dpadY);
    @StructMember(4) public native float getButtonA();
    @StructMember(4) public native GCGamepadSnapShotDataV100 setButtonA(float buttonA);
    @StructMember(5) public native float getButtonB();
    @StructMember(5) public native GCGamepadSnapShotDataV100 setButtonB(float buttonB);
    @StructMember(6) public native float getButtonX();
    @StructMember(6) public native GCGamepadSnapShotDataV100 setButtonX(float buttonX);
    @StructMember(7) public native float getButtonY();
    @StructMember(7) public native GCGamepadSnapShotDataV100 setButtonY(float buttonY);
    @StructMember(8) public native float getLeftShoulder();
    @StructMember(8) public native GCGamepadSnapShotDataV100 setLeftShoulder(float leftShoulder);
    @StructMember(9) public native float getRightShoulder();
    @StructMember(9) public native GCGamepadSnapShotDataV100 setRightShoulder(float rightShoulder);
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="GCGamepadSnapShotDataV100FromNSData", optional=true)
    public native boolean setData(NSData data);
    @Bridge(symbol="NSDataFromGCGamepadSnapShotDataV100", optional=true)
    public native NSData getData();
    /*</methods>*/
}
