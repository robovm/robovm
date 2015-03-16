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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureWhiteBalanceGains/*</name>*/ 
    extends /*<extends>*/Struct<AVCaptureWhiteBalanceGains>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVCaptureWhiteBalanceGainsPtr extends Ptr<AVCaptureWhiteBalanceGains, AVCaptureWhiteBalanceGainsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVCaptureWhiteBalanceGains() {}
    public AVCaptureWhiteBalanceGains(float redGain, float greenGain, float blueGain) {
        this.setRedGain(redGain);
        this.setGreenGain(greenGain);
        this.setBlueGain(blueGain);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getRedGain();
    @StructMember(0) public native AVCaptureWhiteBalanceGains setRedGain(float redGain);
    @StructMember(1) public native float getGreenGain();
    @StructMember(1) public native AVCaptureWhiteBalanceGains setGreenGain(float greenGain);
    @StructMember(2) public native float getBlueGain();
    @StructMember(2) public native AVCaptureWhiteBalanceGains setBlueGain(float blueGain);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
