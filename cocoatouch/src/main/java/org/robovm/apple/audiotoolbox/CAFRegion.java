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
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFRegion/*</name>*/ 
    extends /*<extends>*/Struct<CAFRegion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFRegionPtr extends Ptr<CAFRegion, CAFRegionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFRegion() {}
    public CAFRegion(int mRegionID, int mFlags, int mNumberMarkers, CAFMarker mMarkers) {
        this.setMRegionID(mRegionID);
        this.setMFlags(mFlags);
        this.setMNumberMarkers(mNumberMarkers);
        this.setMMarkers(mMarkers);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMRegionID();
    @StructMember(0) public native CAFRegion setMRegionID(int mRegionID);
    @StructMember(1) public native int getMFlags();
    @StructMember(1) public native CAFRegion setMFlags(int mFlags);
    @StructMember(2) public native int getMNumberMarkers();
    @StructMember(2) public native CAFRegion setMNumberMarkers(int mNumberMarkers);
    @StructMember(3) public native @Array({1}) CAFMarker getMMarkers();
    @StructMember(3) public native CAFRegion setMMarkers(@Array({1}) CAFMarker mMarkers);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
