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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioFileRegion/*</name>*/ 
    extends /*<extends>*/Struct<AudioFileRegion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioFileRegionPtr extends Ptr<AudioFileRegion, AudioFileRegionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioFileRegion() {}
    public AudioFileRegion(int mRegionID, CFString mName, int mFlags, int mNumberMarkers, AudioFileMarker mMarkers) {
        this.mRegionID(mRegionID);
        this.mName(mName);
        this.mFlags(mFlags);
        this.mNumberMarkers(mNumberMarkers);
        this.mMarkers(mMarkers);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int mRegionID();
    @StructMember(0) public native AudioFileRegion mRegionID(int mRegionID);
    @StructMember(1) public native CFString mName();
    @StructMember(1) public native AudioFileRegion mName(CFString mName);
    @StructMember(2) public native int mFlags();
    @StructMember(2) public native AudioFileRegion mFlags(int mFlags);
    @StructMember(3) public native int mNumberMarkers();
    @StructMember(3) public native AudioFileRegion mNumberMarkers(int mNumberMarkers);
    @StructMember(4) public native @Array({1}) AudioFileMarker mMarkers();
    @StructMember(4) public native AudioFileRegion mMarkers(@Array({1}) AudioFileMarker mMarkers);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
