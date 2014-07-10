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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioChannelDescription/*</name>*/ 
    extends /*<extends>*/Struct<AudioChannelDescription>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioChannelDescriptionPtr extends Ptr<AudioChannelDescription, AudioChannelDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioChannelDescription() {}
    public AudioChannelDescription(AudioChannelLabel mChannelLabel, int mChannelFlags, FloatBuffer mCoordinates) {
        this.mChannelLabel(mChannelLabel);
        this.mChannelFlags(mChannelFlags);
        this.mCoordinates(mCoordinates);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native AudioChannelLabel mChannelLabel();
    @StructMember(0) public native AudioChannelDescription mChannelLabel(AudioChannelLabel mChannelLabel);
    @StructMember(1) public native int mChannelFlags();
    @StructMember(1) public native AudioChannelDescription mChannelFlags(int mChannelFlags);
    @StructMember(2) public native @Array({3}) FloatBuffer mCoordinates();
    @StructMember(2) public native AudioChannelDescription mCoordinates(@Array({3}) FloatBuffer mCoordinates);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
