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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioComponentDescription/*</name>*/ 
    extends /*<extends>*/Struct<AudioComponentDescription>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioComponentDescriptionPtr extends Ptr<AudioComponentDescription, AudioComponentDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioComponentDescription() {}
    public AudioComponentDescription(AUType componentType, AUSubType componentSubType, AUManufacturer componentManufacturer, AudioComponentFlags componentFlags, int componentFlagsMask) {
        this.setComponentType(componentType);
        this.setComponentSubType(componentSubType);
        this.setComponentManufacturer(componentManufacturer);
        this.setComponentFlags(componentFlags);
        this.setComponentFlagsMask(componentFlagsMask);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public static AudioComponentDescription create(AUType type) {
        return new AudioComponentDescription(type, null, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription create(AUType type, AUSubType subType) {
        return new AudioComponentDescription(type, subType, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createOutput(AUTypeOutput type) {
        return new AudioComponentDescription(AUType.Output, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createMusicDevice(AUTypeMusicDevice type) {
        return new AudioComponentDescription(AUType.MusicDevice, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createConverter(AUTypeConverter type) {
        return new AudioComponentDescription(AUType.FormatConverter, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createEffect(AUTypeEffect type) {
        return new AudioComponentDescription(AUType.Effect, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createMixer(AUTypeMixer type) {
        return new AudioComponentDescription(AUType.Mixer, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createPanner() {
        return new AudioComponentDescription(AUType.Mixer, null, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    public static AudioComponentDescription createGenerator(AUTypeGenerator type) {
        return new AudioComponentDescription(AUType.Generator, type, AUManufacturer.Apple, AudioComponentFlags.None, 0);
    }
    /*<members>*/
    @StructMember(0) public native AUType getComponentType();
    @StructMember(0) public native AudioComponentDescription setComponentType(AUType componentType);
    @StructMember(1) public native AUSubType getComponentSubType();
    @StructMember(1) public native AudioComponentDescription setComponentSubType(AUSubType componentSubType);
    @StructMember(2) public native AUManufacturer getComponentManufacturer();
    @StructMember(2) public native AudioComponentDescription setComponentManufacturer(AUManufacturer componentManufacturer);
    @StructMember(3) public native AudioComponentFlags getComponentFlags();
    @StructMember(3) public native AudioComponentDescription setComponentFlags(AudioComponentFlags componentFlags);
    @StructMember(4) public native int getComponentFlagsMask();
    @StructMember(4) public native AudioComponentDescription setComponentFlagsMask(int componentFlagsMask);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
