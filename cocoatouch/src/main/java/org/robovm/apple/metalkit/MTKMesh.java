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
package org.robovm.apple.metalkit;

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
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MetalKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTKMesh/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTKMeshPtr extends Ptr<MTKMesh, MTKMeshPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTKMesh.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTKMesh() {}
    protected MTKMesh(SkipInit skipInit) { super(skipInit); }
    public MTKMesh(MDLMesh mesh, MTLDevice device) throws NSErrorException {
       super((SkipInit) null);
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long handle = init(mesh, device, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       initObject(handle);
    }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "vertexBuffers")
    public native NSArray<MTKMeshBuffer> getVertexBuffers();
    @Property(selector = "vertexDescriptor")
    public native MDLVertexDescriptor getVertexDescriptor();
    @Property(selector = "submeshes")
    public native NSArray<MTKSubmesh> getSubmeshes();
    @Property(selector = "vertexCount")
    public native @MachineSizedUInt long getVertexCount();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithMesh:device:error:")
    private native @Pointer long init(MDLMesh mesh, MTLDevice device, NSError.NSErrorPtr error);
    public static NSArray<MTKMesh> newMeshes(MDLAsset asset, MTLDevice device, NSArray.NSArrayPtr sourceMeshes) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<MTKMesh> result = newMeshes(asset, device, sourceMeshes, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "newMeshesFromAsset:device:sourceMeshes:error:")
    private static native NSArray<MTKMesh> newMeshes(MDLAsset asset, MTLDevice device, NSArray.NSArrayPtr sourceMeshes, NSError.NSErrorPtr error);
    /*</methods>*/
}
