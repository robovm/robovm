/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDINoteMessage/*</name>*/ 
    extends /*<extends>*/Struct<MIDINoteMessage>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDINoteMessagePtr extends Ptr<MIDINoteMessage, MIDINoteMessagePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDINoteMessage() {}
    public MIDINoteMessage(byte channel, byte note, byte velocity, byte releaseVelocity, float duration) {
        this.setChannel(channel);
        this.setNote(note);
        this.setVelocity(velocity);
        this.setReleaseVelocity(releaseVelocity);
        this.setDuration(duration);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte getChannel();
    @StructMember(0) public native MIDINoteMessage setChannel(byte channel);
    @StructMember(1) public native byte getNote();
    @StructMember(1) public native MIDINoteMessage setNote(byte note);
    @StructMember(2) public native byte getVelocity();
    @StructMember(2) public native MIDINoteMessage setVelocity(byte velocity);
    @StructMember(3) public native byte getReleaseVelocity();
    @StructMember(3) public native MIDINoteMessage setReleaseVelocity(byte releaseVelocity);
    @StructMember(4) public native float getDuration();
    @StructMember(4) public native MIDINoteMessage setDuration(float duration);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
