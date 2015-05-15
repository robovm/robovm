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
    public AudioChannelDescription(AudioChannelLabel channelLabel, AudioChannelFlags channelFlags, float[] coordinates) {
        this.setChannelLabel(channelLabel);
        this.setChannelFlags(channelFlags);
        this.setCoordinates(coordinates);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public float getCoordinate(AudioChannelCoordinate coordinate) {
        return getCoordinates()[(int)coordinate.value()];
    }
    public AudioChannelDescription setCoordinate(AudioChannelCoordinate coordinate, float value) {
        float[] coords = getCoordinates();
        coords[(int)coordinate.value()] = value;
        setCoordinates(coords);
        return this;
    }
    public AudioChannelDescription setCoordinates(float x, float y, float z) {
        float[] coords = new float[] {x, y, z};
        setCoordinates(coords);
        return this;
    }
    /*<members>*/
    @StructMember(0) public native AudioChannelLabel getChannelLabel();
    @StructMember(0) public native AudioChannelDescription setChannelLabel(AudioChannelLabel channelLabel);
    @StructMember(1) public native AudioChannelFlags getChannelFlags();
    @StructMember(1) public native AudioChannelDescription setChannelFlags(AudioChannelFlags channelFlags);
    @StructMember(2) public native @Array({3}) float[] getCoordinates();
    @StructMember(2) public native AudioChannelDescription setCoordinates(@Array({3}) float[] coordinates);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
