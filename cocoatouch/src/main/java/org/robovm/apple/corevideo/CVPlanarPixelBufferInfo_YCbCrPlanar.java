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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPlanarPixelBufferInfo_YCbCrPlanar/*</name>*/ 
    extends /*<extends>*/Struct<CVPlanarPixelBufferInfo_YCbCrPlanar>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVPlanarPixelBufferInfo_YCbCrPlanarPtr extends Ptr<CVPlanarPixelBufferInfo_YCbCrPlanar, CVPlanarPixelBufferInfo_YCbCrPlanarPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CVPlanarPixelBufferInfo_YCbCrPlanar() {}
    public CVPlanarPixelBufferInfo_YCbCrPlanar(CVPlanarComponentInfo componentInfoY, CVPlanarComponentInfo componentInfoCb, CVPlanarComponentInfo componentInfoCr) {
        this.setComponentInfoY(componentInfoY);
        this.setComponentInfoCb(componentInfoCb);
        this.setComponentInfoCr(componentInfoCr);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CVPlanarComponentInfo getComponentInfoY();
    @StructMember(0) public native CVPlanarPixelBufferInfo_YCbCrPlanar setComponentInfoY(@ByVal CVPlanarComponentInfo componentInfoY);
    @StructMember(1) public native @ByVal CVPlanarComponentInfo getComponentInfoCb();
    @StructMember(1) public native CVPlanarPixelBufferInfo_YCbCrPlanar setComponentInfoCb(@ByVal CVPlanarComponentInfo componentInfoCb);
    @StructMember(2) public native @ByVal CVPlanarComponentInfo getComponentInfoCr();
    @StructMember(2) public native CVPlanarPixelBufferInfo_YCbCrPlanar setComponentInfoCr(@ByVal CVPlanarComponentInfo componentInfoCr);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
