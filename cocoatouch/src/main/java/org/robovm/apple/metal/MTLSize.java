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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLSize/*</name>*/ 
    extends /*<extends>*/Struct<MTLSize>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLSizePtr extends Ptr<MTLSize, MTLSizePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLSize() {}
    public MTLSize(@MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long depth) {
        this.setWidth(width);
        this.setHeight(height);
        this.setDepth(depth);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @MachineSizedUInt long getWidth();
    @StructMember(0) public native MTLSize setWidth(@MachineSizedUInt long width);
    @StructMember(1) public native @MachineSizedUInt long getHeight();
    @StructMember(1) public native MTLSize setHeight(@MachineSizedUInt long height);
    @StructMember(2) public native @MachineSizedUInt long getDepth();
    @StructMember(2) public native MTLSize setDepth(@MachineSizedUInt long depth);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
