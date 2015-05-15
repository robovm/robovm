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
package org.robovm.apple.audiounit;

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
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AUMeterClipping/*</name>*/ 
    extends /*<extends>*/Struct<AUMeterClipping>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AUMeterClippingPtr extends Ptr<AUMeterClipping, AUMeterClippingPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AUMeterClipping() {}
    public AUMeterClipping(float peakValueSinceLastCall, boolean sawInfinity, boolean sawNotANumber) {
        this.setPeakValueSinceLastCall(peakValueSinceLastCall);
        this.setSawInfinity(sawInfinity);
        this.setSawNotANumber(sawNotANumber);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getPeakValueSinceLastCall();
    @StructMember(0) public native AUMeterClipping setPeakValueSinceLastCall(float peakValueSinceLastCall);
    @StructMember(1) public native boolean isSawInfinity();
    @StructMember(1) public native AUMeterClipping setSawInfinity(boolean sawInfinity);
    @StructMember(2) public native boolean isSawNotANumber();
    @StructMember(2) public native AUMeterClipping setSawNotANumber(boolean sawNotANumber);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
