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
package org.robovm.apple.coreaudio;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioChannelLayout/*</name>*/ 
    extends /*<extends>*/Struct<AudioChannelLayout>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioChannelLayoutPtr extends Ptr<AudioChannelLayout, AudioChannelLayoutPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioChannelLayout() {}
    public AudioChannelLayout(AudioChannelLayoutTag mChannelLayoutTag, AudioChannelBit mChannelBitmap, int mNumberChannelDescriptions, AudioChannelDescription mChannelDescriptions) {
        this.setMChannelLayoutTag(mChannelLayoutTag);
        this.setMChannelBitmap(mChannelBitmap);
        this.setMNumberChannelDescriptions(mNumberChannelDescriptions);
        this.setMChannelDescriptions(mChannelDescriptions);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native AudioChannelLayoutTag getMChannelLayoutTag();
    @StructMember(0) public native AudioChannelLayout setMChannelLayoutTag(AudioChannelLayoutTag mChannelLayoutTag);
    @StructMember(1) public native AudioChannelBit getMChannelBitmap();
    @StructMember(1) public native AudioChannelLayout setMChannelBitmap(AudioChannelBit mChannelBitmap);
    @StructMember(2) public native int getMNumberChannelDescriptions();
    @StructMember(2) public native AudioChannelLayout setMNumberChannelDescriptions(int mNumberChannelDescriptions);
    @StructMember(3) public native @Array({1}) AudioChannelDescription getMChannelDescriptions();
    @StructMember(3) public native AudioChannelLayout setMChannelDescriptions(@Array({1}) AudioChannelDescription mChannelDescriptions);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
