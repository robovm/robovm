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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFileRegion/*</name>*/ 
    extends /*<extends>*/Struct<AudioFileRegion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFileRegionPtr extends Ptr<AudioFileRegion, AudioFileRegionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioFileRegion() {}
    public AudioFileRegion(int regionID, String name, AudioFileRegionFlags flags) {
        this.setRegionID(regionID);
        this.setName(name);
        this.setFlags(flags);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public int getMarkerCount() {
        return getNumberMarkers();
    }
    
    public AudioFileMarker getMarker(int index) {
        if (index >= getMarkerCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getMarkers0().next(index).get();
    }
    public AudioFileRegion setMarker(int index, AudioFileMarker value) {
        if (index >= getMarkerCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        getMarkers0().next(index).set(value);
        return this;
    }
    public AudioFileMarker[] getMarkers() {
        int count = getMarkerCount();
        AudioFileMarker[] array = new AudioFileMarker[count];
        AudioFileMarker.AudioFileMarkerPtr ptr = getMarkers0();
        for (int i = 0; i < count; i++) {
            array[i] = ptr.next(i).get();
        }
        return array;
    }
    public AudioFileRegion setMarkers(AudioFileMarker[] markers) {
        this.setNumberMarkers(markers.length);
        getMarkers0().set(markers);
        return this;
    }
    /*<members>*/
    @StructMember(0) public native int getRegionID();
    @StructMember(0) public native AudioFileRegion setRegionID(int regionID);
    @StructMember(1) public native String getName();
    @StructMember(1) public native AudioFileRegion setName(String name);
    @StructMember(2) public native AudioFileRegionFlags getFlags();
    @StructMember(2) public native AudioFileRegion setFlags(AudioFileRegionFlags flags);
    @StructMember(3) protected native int getNumberMarkers();
    @StructMember(3) protected native AudioFileRegion setNumberMarkers(int numberMarkers);
    @StructMember(4) protected native AudioFileMarker.AudioFileMarkerPtr getMarkers0();
    @StructMember(4) protected native AudioFileRegion setMarkers0(AudioFileMarker.AudioFileMarkerPtr markers0);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
