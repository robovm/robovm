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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleTimingInfo/*</name>*/ 
    extends /*<extends>*/Struct<CMSampleTimingInfo>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMSampleTimingInfoPtr extends Ptr<CMSampleTimingInfo, CMSampleTimingInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMSampleTimingInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMSampleTimingInfo() {}
    public CMSampleTimingInfo(CMTime duration, CMTime presentationTimeStamp, CMTime decodeTimeStamp) {
        this.duration(duration);
        this.presentationTimeStamp(presentationTimeStamp);
        this.decodeTimeStamp(decodeTimeStamp);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CMTime duration();
    @StructMember(0) public native CMSampleTimingInfo duration(@ByVal CMTime duration);
    @StructMember(1) public native @ByVal CMTime presentationTimeStamp();
    @StructMember(1) public native CMSampleTimingInfo presentationTimeStamp(@ByVal CMTime presentationTimeStamp);
    @StructMember(2) public native @ByVal CMTime decodeTimeStamp();
    @StructMember(2) public native CMSampleTimingInfo decodeTimeStamp(@ByVal CMTime decodeTimeStamp);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
