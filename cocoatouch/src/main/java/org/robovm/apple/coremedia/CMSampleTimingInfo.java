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
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMSampleTimingInfo/*</name>*/ 
    extends /*<extends>*/Struct<CMSampleTimingInfo>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMSampleTimingInfoPtr extends Ptr<CMSampleTimingInfo, CMSampleTimingInfoPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMSampleTimingInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMSampleTimingInfo() {}
    public CMSampleTimingInfo(CMTime duration, CMTime presentationTimeStamp, CMTime decodeTimeStamp) {
        this.setDuration(duration);
        this.setPresentationTimeStamp(presentationTimeStamp);
        this.setDecodeTimeStamp(decodeTimeStamp);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CMTime getDuration();
    @StructMember(0) public native CMSampleTimingInfo setDuration(@ByVal CMTime duration);
    @StructMember(1) public native @ByVal CMTime getPresentationTimeStamp();
    @StructMember(1) public native CMSampleTimingInfo setPresentationTimeStamp(@ByVal CMTime presentationTimeStamp);
    @StructMember(2) public native @ByVal CMTime getDecodeTimeStamp();
    @StructMember(2) public native CMSampleTimingInfo setDecodeTimeStamp(@ByVal CMTime decodeTimeStamp);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCMTimingInfoInvalid", optional=true)
    public static native @ByVal CMSampleTimingInfo Invalid();
    /*</methods>*/
}
