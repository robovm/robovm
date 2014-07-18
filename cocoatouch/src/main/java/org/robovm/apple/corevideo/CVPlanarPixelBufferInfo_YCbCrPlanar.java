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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.coremedia.*;
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
        this.componentInfoY(componentInfoY);
        this.componentInfoCb(componentInfoCb);
        this.componentInfoCr(componentInfoCr);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal CVPlanarComponentInfo componentInfoY();
    @StructMember(0) public native CVPlanarPixelBufferInfo_YCbCrPlanar componentInfoY(@ByVal CVPlanarComponentInfo componentInfoY);
    @StructMember(1) public native @ByVal CVPlanarComponentInfo componentInfoCb();
    @StructMember(1) public native CVPlanarPixelBufferInfo_YCbCrPlanar componentInfoCb(@ByVal CVPlanarComponentInfo componentInfoCb);
    @StructMember(2) public native @ByVal CVPlanarComponentInfo componentInfoCr();
    @StructMember(2) public native CVPlanarPixelBufferInfo_YCbCrPlanar componentInfoCr(@ByVal CVPlanarComponentInfo componentInfoCr);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
