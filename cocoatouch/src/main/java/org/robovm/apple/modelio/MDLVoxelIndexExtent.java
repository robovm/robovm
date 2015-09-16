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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVoxelIndexExtent/*</name>*/ 
    extends /*<extends>*/Struct<MDLVoxelIndexExtent>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLVoxelIndexExtentPtr extends Ptr<MDLVoxelIndexExtent, MDLVoxelIndexExtentPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLVoxelIndexExtent() {}
    public MDLVoxelIndexExtent(VectorInt4 minimumExtent, VectorInt4 maximumExtent) {
        this.setMinimumExtent(minimumExtent);
        this.setMaximumExtent(maximumExtent);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native VectorInt4 getMinimumExtent();
    @StructMember(0) public native MDLVoxelIndexExtent setMinimumExtent(VectorInt4 minimumExtent);
    @StructMember(1) public native VectorInt4 getMaximumExtent();
    @StructMember(1) public native MDLVoxelIndexExtent setMaximumExtent(VectorInt4 maximumExtent);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
