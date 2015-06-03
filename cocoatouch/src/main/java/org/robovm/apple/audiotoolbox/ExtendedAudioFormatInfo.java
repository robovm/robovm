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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ExtendedAudioFormatInfo/*</name>*/ 
    extends /*<extends>*/Struct<ExtendedAudioFormatInfo>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class ExtendedAudioFormatInfoPtr extends Ptr<ExtendedAudioFormatInfo, ExtendedAudioFormatInfoPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public ExtendedAudioFormatInfo() {}
    public ExtendedAudioFormatInfo(AudioStreamBasicDescription ASBD, VoidPtr magicCookie, int magicCookieSize, AudioClassDescription classDescription) {
        this.setASBD(ASBD);
        this.setMagicCookie(magicCookie);
        this.setMagicCookieSize(magicCookieSize);
        this.setClassDescription(classDescription);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal AudioStreamBasicDescription getASBD();
    @StructMember(0) public native ExtendedAudioFormatInfo setASBD(@ByVal AudioStreamBasicDescription ASBD);
    @StructMember(1) public native VoidPtr getMagicCookie();
    @StructMember(1) public native ExtendedAudioFormatInfo setMagicCookie(VoidPtr magicCookie);
    @StructMember(2) public native int getMagicCookieSize();
    @StructMember(2) public native ExtendedAudioFormatInfo setMagicCookieSize(int magicCookieSize);
    @StructMember(3) public native @ByVal AudioClassDescription getClassDescription();
    @StructMember(3) public native ExtendedAudioFormatInfo setClassDescription(@ByVal AudioClassDescription classDescription);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
