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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLRegion/*</name>*/ 
    extends /*<extends>*/Struct<MTLRegion>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLRegionPtr extends Ptr<MTLRegion, MTLRegionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLRegion() {}
    public MTLRegion(MTLOrigin origin, MTLSize size) {
        this.setOrigin(origin);
        this.setSize(size);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native @ByVal MTLOrigin getOrigin();
    @StructMember(0) public native MTLRegion setOrigin(@ByVal MTLOrigin origin);
    
    @Deprecated
    @StructMember(0) public native @ByVal MTLOrigin origin();
    @Deprecated
    @StructMember(0) public native MTLRegion origin(@ByVal MTLOrigin origin);
    
    @StructMember(1) public native @ByVal MTLSize getSize();
    @StructMember(1) public native MTLRegion setSize(@ByVal MTLSize size);
    
    @Deprecated
    @StructMember(1) public native @ByVal MTLSize size();
    @Deprecated
    @StructMember(1) public native MTLRegion size(@ByVal MTLSize size);
    
    /*</members>*/
    public static MTLRegion create1D(long x, long width) {
        MTLRegion region = new MTLRegion();
        region.origin(new MTLOrigin(x, 0, 0));
        region.size(new MTLSize(width, 1, 1));
        return region;
    }
    public static MTLRegion create2D(long x, long y, long width, long height) {
        MTLRegion region = new MTLRegion();
        region.origin(new MTLOrigin(x, y, 0));
        region.size(new MTLSize(width, height, 1));
        return region;
    }
    public static MTLRegion create3D(long x, long y, long z, long width, long height, long depth) {
        MTLRegion region = new MTLRegion();
        region.origin(new MTLOrigin(x, y, z));
        region.size(new MTLSize(width, height, depth));
        return region;
    }
    /*<methods>*//*</methods>*/
}
