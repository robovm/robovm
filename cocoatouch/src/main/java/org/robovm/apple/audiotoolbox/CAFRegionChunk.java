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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFRegionChunk/*</name>*/ 
    extends /*<extends>*/Struct<CAFRegionChunk>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFRegionChunkPtr extends Ptr<CAFRegionChunk, CAFRegionChunkPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFRegionChunk() {}
    public CAFRegionChunk(CAFSMPTETimeType SMPTETimeType) {
        this.setSMPTETimeType(SMPTETimeType);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    public int getRegionCount() {
        return getNumberRegions();
    }
    
    public CAFRegion getRegion(int index) {
        if (index >= getRegionCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return getRegions0().next(index).get();
    }
    public CAFRegionChunk setRegion(int index, CAFRegion value) {
        if (index >= getRegionCount()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        getRegions0().next(index).set(value);
        return this;
    }
    public CAFRegion[] getRegions() {
        int count = getRegionCount();
        CAFRegion[] array = new CAFRegion[count];
        CAFRegion.CAFRegionPtr ptr = getRegions0();
        for (int i = 0; i < count; i++) {
            array[i] = ptr.next(i).get();
        }
        return array;
    }
    public CAFRegionChunk setRegions(CAFRegion[] regions) {
        this.setNumberRegions(regions.length);
        getRegions0().set(regions);
        return this;
    }
    /*<members>*/
    @StructMember(0) public native CAFSMPTETimeType getSMPTETimeType();
    @StructMember(0) public native CAFRegionChunk setSMPTETimeType(CAFSMPTETimeType SMPTETimeType);
    @StructMember(1) protected native int getNumberRegions();
    @StructMember(1) protected native CAFRegionChunk setNumberRegions(int numberRegions);
    @StructMember(2) protected native CAFRegion.CAFRegionPtr getRegions0();
    @StructMember(2) protected native CAFRegionChunk setRegions0(CAFRegion.CAFRegionPtr regions0);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
