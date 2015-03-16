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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioPanningInfo/*</name>*/ 
    extends /*<extends>*/Struct<AudioPanningInfo>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioPanningInfoPtr extends Ptr<AudioPanningInfo, AudioPanningInfoPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioPanningInfo() {}
    public AudioPanningInfo(int mPanningMode, int mCoordinateFlags, FloatBuffer mCoordinates, float mGainScale, AudioChannelLayout mOutputChannelMap) {
        this.setMPanningMode(mPanningMode);
        this.setMCoordinateFlags(mCoordinateFlags);
        this.setMCoordinates(mCoordinates);
        this.setMGainScale(mGainScale);
        this.setMOutputChannelMap(mOutputChannelMap);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMPanningMode();
    @StructMember(0) public native AudioPanningInfo setMPanningMode(int mPanningMode);
    @StructMember(1) public native int getMCoordinateFlags();
    @StructMember(1) public native AudioPanningInfo setMCoordinateFlags(int mCoordinateFlags);
    @StructMember(2) public native @Array({3}) FloatBuffer getMCoordinates();
    @StructMember(2) public native AudioPanningInfo setMCoordinates(@Array({3}) FloatBuffer mCoordinates);
    @StructMember(3) public native float getMGainScale();
    @StructMember(3) public native AudioPanningInfo setMGainScale(float mGainScale);
    @StructMember(4) public native AudioChannelLayout getMOutputChannelMap();
    @StructMember(4) public native AudioPanningInfo setMOutputChannelMap(AudioChannelLayout mOutputChannelMap);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
