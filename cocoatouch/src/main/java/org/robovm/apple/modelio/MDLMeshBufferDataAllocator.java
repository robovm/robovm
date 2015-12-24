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
/*<annotations>*/@Library("ModelIO") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLMeshBufferDataAllocator/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MDLMeshBufferAllocator/*</implements>*/ {

    /*<ptr>*/public static class MDLMeshBufferDataAllocatorPtr extends Ptr<MDLMeshBufferDataAllocator, MDLMeshBufferDataAllocatorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLMeshBufferDataAllocator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLMeshBufferDataAllocator() {}
    protected MDLMeshBufferDataAllocator(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "newZone:")
    public native MDLMeshBufferZone newZone(@MachineSizedUInt long capacity);
    @Method(selector = "newZoneForBuffersWithSize:andType:")
    public native MDLMeshBufferZone newZone(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsIntegerListMarshaler.class) List<Integer> sizes, @org.robovm.rt.bro.annotation.Marshaler(MDLMeshBufferType.AsListMarshaler.class) List<MDLMeshBufferType> types);
    @Method(selector = "newBuffer:type:")
    public native MDLMeshBuffer newBuffer(@MachineSizedUInt long length, MDLMeshBufferType type);
    @Method(selector = "newBufferWithData:type:")
    public native MDLMeshBuffer newBuffer(NSData data, MDLMeshBufferType type);
    @Method(selector = "newBufferFromZone:length:type:")
    public native MDLMeshBuffer newBuffer(MDLMeshBufferZone zone, @MachineSizedUInt long length, MDLMeshBufferType type);
    @Method(selector = "newBufferFromZone:data:type:")
    public native MDLMeshBuffer newBuffer(MDLMeshBufferZone zone, NSData data, MDLMeshBufferType type);
    /*</methods>*/
}
