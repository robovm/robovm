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
        this.mLeftRightBalance(mLeftRightBalance);
        this.mBackFrontFade(mBackFrontFade);
        this.mType(mType);
        this.mChannelLayout(mChannelLayout);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float mLeftRightBalance();
    @StructMember(0) public native AudioBalanceFade mLeftRightBalance(float mLeftRightBalance);
    @StructMember(1) public native float mBackFrontFade();
    @StructMember(1) public native AudioBalanceFade mBackFrontFade(float mBackFrontFade);
    @StructMember(2) public native int mType();
    @StructMember(2) public native AudioBalanceFade mType(int mType);
    @StructMember(3) public native AudioChannelLayout mChannelLayout();
    @StructMember(3) public native AudioBalanceFade mChannelLayout(AudioChannelLayout mChannelLayout);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
