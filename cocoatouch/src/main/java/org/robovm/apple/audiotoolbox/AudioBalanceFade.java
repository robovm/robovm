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
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioBalanceFade/*</name>*/ 
    extends /*<extends>*/Struct<AudioBalanceFade>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioBalanceFadePtr extends Ptr<AudioBalanceFade, AudioBalanceFadePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioBalanceFade() {}
    public AudioBalanceFade(float mLeftRightBalance, float mBackFrontFade, int mType, AudioChannelLayout mChannelLayout) {
        this.setMLeftRightBalance(mLeftRightBalance);
        this.setMBackFrontFade(mBackFrontFade);
        this.setMType(mType);
        this.setMChannelLayout(mChannelLayout);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getMLeftRightBalance();
    @StructMember(0) public native AudioBalanceFade setMLeftRightBalance(float mLeftRightBalance);
    @StructMember(1) public native float getMBackFrontFade();
    @StructMember(1) public native AudioBalanceFade setMBackFrontFade(float mBackFrontFade);
    @StructMember(2) public native int getMType();
    @StructMember(2) public native AudioBalanceFade setMType(int mType);
    @StructMember(3) public native AudioChannelLayout getMChannelLayout();
    @StructMember(3) public native AudioBalanceFade setMChannelLayout(AudioChannelLayout mChannelLayout);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
