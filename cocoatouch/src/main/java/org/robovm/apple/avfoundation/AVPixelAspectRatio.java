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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPixelAspectRatio/*</name>*/ 
    extends /*<extends>*/Struct<AVPixelAspectRatio>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVPixelAspectRatioPtr extends Ptr<AVPixelAspectRatio, AVPixelAspectRatioPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVPixelAspectRatio() {}
    public AVPixelAspectRatio(@MachineSizedSInt long horizontalSpacing, @MachineSizedSInt long verticalSpacing) {
        this.setHorizontalSpacing(horizontalSpacing);
        this.setVerticalSpacing(verticalSpacing);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedSInt long getHorizontalSpacing();
    @StructMember(0) public native AVPixelAspectRatio setHorizontalSpacing(@MachineSizedSInt long horizontalSpacing);
    @StructMember(1) public native @MachineSizedSInt long getVerticalSpacing();
    @StructMember(1) public native AVPixelAspectRatio setVerticalSpacing(@MachineSizedSInt long verticalSpacing);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
