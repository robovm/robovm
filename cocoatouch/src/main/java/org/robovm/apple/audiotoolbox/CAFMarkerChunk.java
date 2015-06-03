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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFMarkerChunk/*</name>*/ 
    extends /*<extends>*/Struct<CAFMarkerChunk>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFMarkerChunkPtr extends Ptr<CAFMarkerChunk, CAFMarkerChunkPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFMarkerChunk() {}
    public CAFMarkerChunk(CAFSMPTETimeType SMPTETimeType) {
        this.setSMPTETimeType(SMPTETimeType);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public int getMarkerCount() {
        return getNumberMarkers();
    }
    
    public CAFMarker getMarker(int index) {
        if (index >= getMarkerCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getMarkers0().next(index).get();
    }
    public CAFMarkerChunk setMarker(int index, CAFMarker value) {
        if (index >= getMarkerCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        getMarkers0().next(index).set(value);
        return this;
    }
    public CAFMarker[] getMarkers() {
        int count = getMarkerCount();
        CAFMarker[] array = new CAFMarker[count];
        CAFMarker.CAFMarkerPtr ptr = getMarkers0();
        for (int i = 0; i < count; i++) {
            array[i] = ptr.next(i).get();
        }
        return array;
    }
    public CAFMarkerChunk setMarkers(CAFMarker[] markers) {
        this.setNumberMarkers(markers.length);
        getMarkers0().set(markers);
        return this;
    }
    /*<members>*/
    @StructMember(0) public native CAFSMPTETimeType getSMPTETimeType();
    @StructMember(0) public native CAFMarkerChunk setSMPTETimeType(CAFSMPTETimeType SMPTETimeType);
    @StructMember(1) protected native int getNumberMarkers();
    @StructMember(1) protected native CAFMarkerChunk setNumberMarkers(int numberMarkers);
    @StructMember(2) protected native CAFMarker.CAFMarkerPtr getMarkers0();
    @StructMember(2) protected native CAFMarkerChunk setMarkers0(CAFMarker.CAFMarkerPtr markers0);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
