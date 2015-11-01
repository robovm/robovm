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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLDrawIndexedPrimitivesIndirectArguments/*</name>*/ 
    extends /*<extends>*/Struct<MTLDrawIndexedPrimitivesIndirectArguments>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLDrawIndexedPrimitivesIndirectArgumentsPtr extends Ptr<MTLDrawIndexedPrimitivesIndirectArguments, MTLDrawIndexedPrimitivesIndirectArgumentsPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLDrawIndexedPrimitivesIndirectArguments() {}
    public MTLDrawIndexedPrimitivesIndirectArguments(int indexCount, int instanceCount, int indexStart, int baseVertex, int baseInstance) {
        this.setIndexCount(indexCount);
        this.setInstanceCount(instanceCount);
        this.setIndexStart(indexStart);
        this.setBaseVertex(baseVertex);
        this.setBaseInstance(baseInstance);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getIndexCount();
    @StructMember(0) public native MTLDrawIndexedPrimitivesIndirectArguments setIndexCount(int indexCount);
    @StructMember(1) public native int getInstanceCount();
    @StructMember(1) public native MTLDrawIndexedPrimitivesIndirectArguments setInstanceCount(int instanceCount);
    @StructMember(2) public native int getIndexStart();
    @StructMember(2) public native MTLDrawIndexedPrimitivesIndirectArguments setIndexStart(int indexStart);
    @StructMember(3) public native int getBaseVertex();
    @StructMember(3) public native MTLDrawIndexedPrimitivesIndirectArguments setBaseVertex(int baseVertex);
    @StructMember(4) public native int getBaseInstance();
    @StructMember(4) public native MTLDrawIndexedPrimitivesIndirectArguments setBaseInstance(int baseInstance);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
