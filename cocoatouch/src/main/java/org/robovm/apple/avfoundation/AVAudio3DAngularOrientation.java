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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudio3DAngularOrientation/*</name>*/ 
    extends /*<extends>*/Struct<AVAudio3DAngularOrientation>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudio3DAngularOrientationPtr extends Ptr<AVAudio3DAngularOrientation, AVAudio3DAngularOrientationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudio3DAngularOrientation() {}
    public AVAudio3DAngularOrientation(float yaw, float pitch, float roll) {
        this.setYaw(yaw);
        this.setPitch(pitch);
        this.setRoll(roll);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getYaw();
    @StructMember(0) public native AVAudio3DAngularOrientation setYaw(float yaw);
    
    @Deprecated
    @StructMember(0) public native float yaw();
    @Deprecated
    @StructMember(0) public native AVAudio3DAngularOrientation yaw(float yaw);
    
    @StructMember(1) public native float getPitch();
    @StructMember(1) public native AVAudio3DAngularOrientation setPitch(float pitch);
    
    @Deprecated
    @StructMember(1) public native float pitch();
    @Deprecated
    @StructMember(1) public native AVAudio3DAngularOrientation pitch(float pitch);
    
    @StructMember(2) public native float getRoll();
    @StructMember(2) public native AVAudio3DAngularOrientation setRoll(float roll);
    
    @Deprecated
    @StructMember(2) public native float roll();
    @Deprecated
    @StructMember(2) public native AVAudio3DAngularOrientation roll(float roll);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
