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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFileMarkerList/*</name>*/ 
    extends /*<extends>*/Struct<AudioFileMarkerList>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFileMarkerListPtr extends Ptr<AudioFileMarkerList, AudioFileMarkerListPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioFileMarkerList() {}
    public AudioFileMarkerList(int mSMPTE_TimeType, int mNumberMarkers, AudioFileMarker mMarkers) {
        this.setMSMPTE_TimeType(mSMPTE_TimeType);
        this.setMNumberMarkers(mNumberMarkers);
        this.setMMarkers(mMarkers);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMSMPTE_TimeType();
    @StructMember(0) public native AudioFileMarkerList setMSMPTE_TimeType(int mSMPTE_TimeType);
    @StructMember(1) public native int getMNumberMarkers();
    @StructMember(1) public native AudioFileMarkerList setMNumberMarkers(int mNumberMarkers);
    @StructMember(2) public native @Array({1}) AudioFileMarker getMMarkers();
    @StructMember(2) public native AudioFileMarkerList setMMarkers(@Array({1}) AudioFileMarker mMarkers);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
