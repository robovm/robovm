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
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFileRegionList/*</name>*/ 
    extends /*<extends>*/Struct<AudioFileRegionList>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFileRegionListPtr extends Ptr<AudioFileRegionList, AudioFileRegionListPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioFileRegionList() {}
    public AudioFileRegionList(CAFSMPTETimeType SMPTETimeType) {
        this.setSMPTETimeType(SMPTETimeType);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public int getRegionCount() {
        return getNumberRegions();
    }
    
    public AudioFileRegion getRegion(int index) {
        if (index >= getRegionCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getRegions0().next(index).get();
    }
    public AudioFileRegionList setRegion(int index, AudioFileRegion value) {
        if (index >= getRegionCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        getRegions0().next(index).set(value);
        return this;
    }
    public AudioFileRegion[] getRegions() {
        int count = getRegionCount();
        AudioFileRegion[] array = new AudioFileRegion[count];
        AudioFileRegion.AudioFileRegionPtr ptr = getRegions0();
        for (int i = 0; i < count; i++) {
            array[i] = ptr.next(i).get();
        }
        return array;
    }
    public AudioFileRegionList setRegions(AudioFileRegion[] regions) {
        this.setNumberRegions(regions.length);
        getRegions0().set(regions);
        return this;
    }
    /*<members>*/
    @StructMember(0) public native CAFSMPTETimeType getSMPTETimeType();
    @StructMember(0) public native AudioFileRegionList setSMPTETimeType(CAFSMPTETimeType SMPTETimeType);
    @StructMember(1) protected native int getNumberRegions();
    @StructMember(1) protected native AudioFileRegionList setNumberRegions(int numberRegions);
    @StructMember(2) protected native AudioFileRegion.AudioFileRegionPtr getRegions0();
    @StructMember(2) protected native AudioFileRegionList setRegions0(AudioFileRegion.AudioFileRegionPtr regions0);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
