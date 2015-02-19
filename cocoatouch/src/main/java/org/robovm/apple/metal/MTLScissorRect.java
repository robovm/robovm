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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLScissorRect/*</name>*/ 
    extends /*<extends>*/Struct<MTLScissorRect>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLScissorRectPtr extends Ptr<MTLScissorRect, MTLScissorRectPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLScissorRect() {}
    public MTLScissorRect(@MachineSizedUInt long x, @MachineSizedUInt long y, @MachineSizedUInt long width, @MachineSizedUInt long height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedUInt long getX();
    @StructMember(0) public native MTLScissorRect setX(@MachineSizedUInt long x);
    @StructMember(1) public native @MachineSizedUInt long getY();
    @StructMember(1) public native MTLScissorRect setY(@MachineSizedUInt long y);
    @StructMember(2) public native @MachineSizedUInt long getWidth();
    @StructMember(2) public native MTLScissorRect setWidth(@MachineSizedUInt long width);
    @StructMember(3) public native @MachineSizedUInt long getHeight();
    @StructMember(3) public native MTLScissorRect setHeight(@MachineSizedUInt long height);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
