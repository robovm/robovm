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
        this.setDpadx(dpadX);
        this.setDpady(dpadY);
        this.setButtona(buttonA);
        this.setButtonb(buttonB);
        this.setButtonx(buttonX);
        this.setButtony(buttonY);
        this.setLeftshoulder(leftShoulder);
        this.setRightshoulder(rightShoulder);
        this.setLeftthumbstickx(leftThumbstickX);
        this.setLeftthumbsticky(leftThumbstickY);
        this.setRightthumbstickx(rightThumbstickX);
        this.setRightthumbsticky(rightThumbstickY);
        this.setLefttrigger(leftTrigger);
        this.setRighttrigger(rightTrigger);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getVersion();
    @StructMember(0) public native GCExtendedGamepadSnapShotDataV100 setVersion(short version);
    
    @Deprecated
    @StructMember(0) public native short version();
    @Deprecated
    @StructMember(0) public native GCExtendedGamepadSnapShotDataV100 version(short version);
    
    @StructMember(1) public native short getSize();
    @StructMember(1) public native GCExtendedGamepadSnapShotDataV100 setSize(short size);
    
    @Deprecated
    @StructMember(1) public native short size();
    @Deprecated
    @StructMember(1) public native GCExtendedGamepadSnapShotDataV100 size(short size);
    
    @StructMember(2) public native float getDpadx();
    @StructMember(2) public native GCExtendedGamepadSnapShotDataV100 setDpadx(float dpadX);
    
    @Deprecated
    @StructMember(2) public native float dpadX();
    @Deprecated
    @StructMember(2) public native GCExtendedGamepadSnapShotDataV100 dpadX(float dpadX);
    
    @StructMember(3) public native float getDpady();
    @StructMember(3) public native GCExtendedGamepadSnapShotDataV100 setDpady(float dpadY);
    
    @Deprecated
    @StructMember(3) public native float dpadY();
    @Deprecated
    @StructMember(3) public native GCExtendedGamepadSnapShotDataV100 dpadY(float dpadY);
    
    @StructMember(4) public native float getButtona();
    @StructMember(4) public native GCExtendedGamepadSnapShotDataV100 setButtona(float buttonA);
    
    @Deprecated
    @StructMember(4) public native float buttonA();
    @Deprecated
    @StructMember(4) public native GCExtendedGamepadSnapShotDataV100 buttonA(float buttonA);
    
    @StructMember(5) public native float getButtonb();
    @StructMember(5) public native GCExtendedGamepadSnapShotDataV100 setButtonb(float buttonB);
    
    @Deprecated
    @StructMember(5) public native float buttonB();
    @Deprecated
    @StructMember(5) public native GCExtendedGamepadSnapShotDataV100 buttonB(float buttonB);
    
    @StructMember(6) public native float getButtonx();
    @StructMember(6) public native GCExtendedGamepadSnapShotDataV100 setButtonx(float buttonX);
    
    @Deprecated
    @StructMember(6) public native float buttonX();
    @Deprecated
    @StructMember(6) public native GCExtendedGamepadSnapShotDataV100 buttonX(float buttonX);
    
    @StructMember(7) public native float getButtony();
    @StructMember(7) public native GCExtendedGamepadSnapShotDataV100 setButtony(float buttonY);
    
    @Deprecated
    @StructMember(7) public native float buttonY();
    @Deprecated
    @StructMember(7) public native GCExtendedGamepadSnapShotDataV100 buttonY(float buttonY);
    
    @StructMember(8) public native float getLeftshoulder();
    @StructMember(8) public native GCExtendedGamepadSnapShotDataV100 setLeftshoulder(float leftShoulder);
    
    @Deprecated
    @StructMember(8) public native float leftShoulder();
    @Deprecated
    @StructMember(8) public native GCExtendedGamepadSnapShotDataV100 leftShoulder(float leftShoulder);
    
    @StructMember(9) public native float getRightshoulder();
    @StructMember(9) public native GCExtendedGamepadSnapShotDataV100 setRightshoulder(float rightShoulder);
    
    @Deprecated
    @StructMember(9) public native float rightShoulder();
    @Deprecated
    @StructMember(9) public native GCExtendedGamepadSnapShotDataV100 rightShoulder(float rightShoulder);
    
    @StructMember(10) public native float getLeftthumbstickx();
    @StructMember(10) public native GCExtendedGamepadSnapShotDataV100 setLeftthumbstickx(float leftThumbstickX);
    
    @Deprecated
    @StructMember(10) public native float leftThumbstickX();
    @Deprecated
    @StructMember(10) public native GCExtendedGamepadSnapShotDataV100 leftThumbstickX(float leftThumbstickX);
    
    @StructMember(11) public native float getLeftthumbsticky();
    @StructMember(11) public native GCExtendedGamepadSnapShotDataV100 setLeftthumbsticky(float leftThumbstickY);
    
    @Deprecated
    @StructMember(11) public native float leftThumbstickY();
    @Deprecated
    @StructMember(11) public native GCExtendedGamepadSnapShotDataV100 leftThumbstickY(float leftThumbstickY);
    
    @StructMember(12) public native float getRightthumbstickx();
    @StructMember(12) public native GCExtendedGamepadSnapShotDataV100 setRightthumbstickx(float rightThumbstickX);
    
    @Deprecated
    @StructMember(12) public native float rightThumbstickX();
    @Deprecated
    @StructMember(12) public native GCExtendedGamepadSnapShotDataV100 rightThumbstickX(float rightThumbstickX);
    
    @StructMember(13) public native float getRightthumbsticky();
    @StructMember(13) public native GCExtendedGamepadSnapShotDataV100 setRightthumbsticky(float rightThumbstickY);
    
    @Deprecated
    @StructMember(13) public native float rightThumbstickY();
    @Deprecated
    @StructMember(13) public native GCExtendedGamepadSnapShotDataV100 rightThumbstickY(float rightThumbstickY);
    
    @StructMember(14) public native float getLefttrigger();
    @StructMember(14) public native GCExtendedGamepadSnapShotDataV100 setLefttrigger(float leftTrigger);
    
    @Deprecated
    @StructMember(14) public native float leftTrigger();
    @Deprecated
    @StructMember(14) public native GCExtendedGamepadSnapShotDataV100 leftTrigger(float leftTrigger);
    
    @StructMember(15) public native float getRighttrigger();
    @StructMember(15) public native GCExtendedGamepadSnapShotDataV100 setRighttrigger(float rightTrigger);
    
    @Deprecated
    @StructMember(15) public native float rightTrigger();
    @Deprecated
    @StructMember(15) public native GCExtendedGamepadSnapShotDataV100 rightTrigger(float rightTrigger);
    
    /*</members>*/
    /*<methods>*/
    @Bridge(symbol="GCExtendedGamepadSnapShotDataV100FromNSData", optional=true)
    public native boolean setData(NSData data);
    @Bridge(symbol="NSDataFromGCExtendedGamepadSnapShotDataV100", optional=true)
    public native NSData getData();
    /*</methods>*/
}
