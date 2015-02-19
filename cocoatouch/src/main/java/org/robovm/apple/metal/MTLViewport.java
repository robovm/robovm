/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.metal;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLViewport/*</name>*/ 
    extends /*<extends>*/Struct<MTLViewport>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLViewportPtr extends Ptr<MTLViewport, MTLViewportPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLViewport() {}
    public MTLViewport(double originX, double originY, double width, double height, double znear, double zfar) {
        this.setOriginX(originX);
        this.setOriginY(originY);
        this.setWidth(width);
        this.setHeight(height);
        this.setZnear(znear);
        this.setZfar(zfar);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getOriginX();
    @StructMember(0) public native MTLViewport setOriginX(double originX);
    @StructMember(1) public native double getOriginY();
    @StructMember(1) public native MTLViewport setOriginY(double originY);
    @StructMember(2) public native double getWidth();
    @StructMember(2) public native MTLViewport setWidth(double width);
    @StructMember(3) public native double getHeight();
    @StructMember(3) public native MTLViewport setHeight(double height);
    @StructMember(4) public native double getZnear();
    @StructMember(4) public native MTLViewport setZnear(double znear);
    @StructMember(5) public native double getZfar();
    @StructMember(5) public native MTLViewport setZfar(double zfar);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
