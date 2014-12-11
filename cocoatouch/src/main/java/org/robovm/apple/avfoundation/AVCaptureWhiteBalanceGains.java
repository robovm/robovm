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
        this.setRedgain(redGain);
        this.setGreengain(greenGain);
        this.setBluegain(blueGain);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getRedgain();
    @StructMember(0) public native AVCaptureWhiteBalanceGains setRedgain(float redGain);
    
    @Deprecated
    @StructMember(0) public native float redGain();
    @Deprecated
    @StructMember(0) public native AVCaptureWhiteBalanceGains redGain(float redGain);
    
    @StructMember(1) public native float getGreengain();
    @StructMember(1) public native AVCaptureWhiteBalanceGains setGreengain(float greenGain);
    
    @Deprecated
    @StructMember(1) public native float greenGain();
    @Deprecated
    @StructMember(1) public native AVCaptureWhiteBalanceGains greenGain(float greenGain);
    
    @StructMember(2) public native float getBluegain();
    @StructMember(2) public native AVCaptureWhiteBalanceGains setBluegain(float blueGain);
    
    @Deprecated
    @StructMember(2) public native float blueGain();
    @Deprecated
    @StructMember(2) public native AVCaptureWhiteBalanceGains blueGain(float blueGain);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
