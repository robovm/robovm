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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFMarker/*</name>*/ 
    extends /*<extends>*/Struct<CAFMarker>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFMarkerPtr extends Ptr<CAFMarker, CAFMarkerPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFMarker() {}
    public CAFMarker(int mType, double mFramePosition, int mMarkerID, CAFSMPTETime mSMPTETime, int mChannel) {
        this.setMType(mType);
        this.setMFramePosition(mFramePosition);
        this.setMMarkerID(mMarkerID);
        this.setMSMPTETime(mSMPTETime);
        this.setMChannel(mChannel);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMType();
    @StructMember(0) public native CAFMarker setMType(int mType);
    @StructMember(1) public native double getMFramePosition();
    @StructMember(1) public native CAFMarker setMFramePosition(double mFramePosition);
    @StructMember(2) public native int getMMarkerID();
    @StructMember(2) public native CAFMarker setMMarkerID(int mMarkerID);
    @StructMember(3) public native @ByVal CAFSMPTETime getMSMPTETime();
    @StructMember(3) public native CAFMarker setMSMPTETime(@ByVal CAFSMPTETime mSMPTETime);
    @StructMember(4) public native int getMChannel();
    @StructMember(4) public native CAFMarker setMChannel(int mChannel);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
