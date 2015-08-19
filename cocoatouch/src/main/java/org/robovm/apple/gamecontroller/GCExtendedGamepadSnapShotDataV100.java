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
/*<annotations>*/@Library("GameController")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCExtendedGamepadSnapShotDataV100/*</name>*/ 
    extends /*<extends>*/Struct<GCExtendedGamepadSnapShotDataV100>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCExtendedGamepadSnapShotDataV100Ptr extends Ptr<GCExtendedGamepadSnapShotDataV100, GCExtendedGamepadSnapShotDataV100Ptr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(GCExtendedGamepadSnapShotDataV100.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCExtendedGamepadSnapShotDataV100() {}
    public GCExtendedGamepadSnapShotDataV100(short version, short size, float dpadX, float dpadY, float buttonA, float buttonB, float buttonX, float buttonY, float leftShoulder, float rightShoulder, float leftThumbstickX, float leftThumbstickY, float rightThumbstickX, float rightThumbstickY, float leftTrigger, float rightTrigger) {
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
        this.setLeftThumbstickX(leftThumbstickX);
        this.setLeftThumbstickY(leftThumbstickY);
        this.setRightThumbstickX(rightThumbstickX);
        this.setRightThumbstickY(rightThumbstickY);
        this.setLeftTrigger(leftTrigger);
        this.setRightTrigger(rightTrigger);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getVersion();
    @StructMember(0) public native GCExtendedGamepadSnapShotDataV100 setVersion(short version);
    @StructMember(1) public native short getSize();
    @StructMember(1) public native GCExtendedGamepadSnapShotDataV100 setSize(short size);
    @StructMember(2) public native float getDpadX();
    @StructMember(2) public native GCExtendedGamepadSnapShotDataV100 setDpadX(float dpadX);
    @StructMember(3) public native float getDpadY();
    @StructMember(3) public native GCExtendedGamepadSnapShotDataV100 setDpadY(float dpadY);
    @StructMember(4) public native float getButtonA();
    @StructMember(4) public native GCExtendedGamepadSnapShotDataV100 setButtonA(float buttonA);
    @StructMember(5) public native float getButtonB();
    @StructMember(5) public native GCExtendedGamepadSnapShotDataV100 setButtonB(float buttonB);
    @StructMember(6) public native float getButtonX();
    @StructMember(6) public native GCExtendedGamepadSnapShotDataV100 setButtonX(float buttonX);
    @StructMember(7) public native float getButtonY();
    @StructMember(7) public native GCExtendedGamepadSnapShotDataV100 setButtonY(float buttonY);
    @StructMember(8) public native float getLeftShoulder();
    @StructMember(8) public native GCExtendedGamepadSnapShotDataV100 setLeftShoulder(float leftShoulder);
    @StructMember(9) public native float getRightShoulder();
    @StructMember(9) public native GCExtendedGamepadSnapShotDataV100 setRightShoulder(float rightShoulder);
    @StructMember(10) public native float getLeftThumbstickX();
    @StructMember(10) public native GCExtendedGamepadSnapShotDataV100 setLeftThumbstickX(float leftThumbstickX);
    @StructMember(11) public native float getLeftThumbstickY();
    @StructMember(11) public native GCExtendedGamepadSnapShotDataV100 setLeftThumbstickY(float leftThumbstickY);
    @StructMember(12) public native float getRightThumbstickX();
    @StructMember(12) public native GCExtendedGamepadSnapShotDataV100 setRightThumbstickX(float rightThumbstickX);
    @StructMember(13) public native float getRightThumbstickY();
    @StructMember(13) public native GCExtendedGamepadSnapShotDataV100 setRightThumbstickY(float rightThumbstickY);
    @StructMember(14) public native float getLeftTrigger();
    @StructMember(14) public native GCExtendedGamepadSnapShotDataV100 setLeftTrigger(float leftTrigger);
    @StructMember(15) public native float getRightTrigger();
    @StructMember(15) public native GCExtendedGamepadSnapShotDataV100 setRightTrigger(float rightTrigger);
    /*</members>*/
    /*<methods>*/
    public boolean setData(NSData data) { return setData(this, data); }
    @Bridge(symbol="GCExtendedGamepadSnapShotDataV100FromNSData", optional=true)
    private static native boolean setData(@ByVal GCExtendedGamepadSnapShotDataV100 snapshotData, NSData data);
    public NSData getData() { return getData(this); }
    @Bridge(symbol="NSDataFromGCExtendedGamepadSnapShotDataV100", optional=true)
    private static native NSData getData(@ByVal GCExtendedGamepadSnapShotDataV100 snapshotData);
    /*</methods>*/
}
