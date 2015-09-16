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
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ModelIO") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLVoxelArray/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLVoxelArrayPtr extends Ptr<MDLVoxelArray, MDLVoxelArrayPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLVoxelArray.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLVoxelArray() {}
    protected MDLVoxelArray(SkipInit skipInit) { super(skipInit); }
    public MDLVoxelArray(MDLAsset asset, int divisions, int interiorShells, int exteriorShells, float patchRadius) { super((SkipInit) null); initObject(init(asset, divisions, interiorShells, exteriorShells, patchRadius)); }
    public MDLVoxelArray(MDLAsset asset, int divisions, float interiorNBWidth, float exteriorNBWidth, float patchRadius) { super((SkipInit) null); initObject(init(asset, divisions, interiorNBWidth, exteriorNBWidth, patchRadius)); }
    public MDLVoxelArray(NSData voxelData, @ByVal MDLAxisAlignedBoundingBox boundingBox, float voxelExtent) { super((SkipInit) null); initObject(init(voxelData, boundingBox, voxelExtent)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "count")
    public native @MachineSizedUInt long getCount();
    @Property(selector = "voxelIndexExtent")
    public native @ByVal MDLVoxelIndexExtent getVoxelIndexExtent();
    @Property(selector = "boundingBox")
    public native @ByVal MDLAxisAlignedBoundingBox getBoundingBox();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAsset:divisions:interiorShells:exteriorShells:patchRadius:")
    protected native @Pointer long init(MDLAsset asset, int divisions, int interiorShells, int exteriorShells, float patchRadius);
    @Method(selector = "initWithAsset:divisions:interiorNBWidth:exteriorNBWidth:patchRadius:")
    protected native @Pointer long init(MDLAsset asset, int divisions, float interiorNBWidth, float exteriorNBWidth, float patchRadius);
    @Method(selector = "initWithData:boundingBox:voxelExtent:")
    protected native @Pointer long init(NSData voxelData, @ByVal MDLAxisAlignedBoundingBox boundingBox, float voxelExtent);
    @Method(selector = "meshUsingAllocator:")
    public native MDLMesh getMesh(MDLMeshBufferAllocator allocator);
    @Method(selector = "voxelExistsAtIndex:allowAnyX:allowAnyY:allowAnyZ:allowAnyShell:")
    public native boolean voxelExists(VectorInt4 index, boolean allowAnyX, boolean allowAnyY, boolean allowAnyZ, boolean allowAnyShell);
    @Method(selector = "setVoxelAtIndex:")
    public native void setVoxel(VectorInt4 index);
    @Method(selector = "setVoxelsForMesh:divisions:interiorShells:exteriorShells:patchRadius:")
    public native void setVoxels(MDLMesh mesh, int divisions, int interiorShells, int exteriorShells, float patchRadius);
    @Method(selector = "setVoxelsForMesh:divisions:interiorNBWidth:exteriorNBWidth:patchRadius:")
    public native void setVoxels(MDLMesh mesh, int divisions, float interiorNBWidth, float exteriorNBWidth, float patchRadius);
    @Method(selector = "voxelsWithinExtent:")
    public native NSData getVoxelsWithinExtent(@ByVal MDLVoxelIndexExtent extent);
    @Method(selector = "voxelIndices")
    public native NSData getVoxelIndices();
    @Method(selector = "unionWithVoxels:")
    public native void union(MDLVoxelArray voxels);
    @Method(selector = "differenceWithVoxels:")
    public native void difference(MDLVoxelArray voxels);
    @Method(selector = "intersectWithVoxels:")
    public native void intersect(MDLVoxelArray voxels);
    @Method(selector = "indexOfSpatialLocation:")
    public native VectorInt4 indexOfSpatialLocation(VectorFloat3 location);
    @Method(selector = "spatialLocationOfIndex:")
    public native VectorFloat3 getSpatialLocation(VectorInt4 index);
    @Method(selector = "voxelBoundingBoxAtIndex:")
    public native @ByVal MDLAxisAlignedBoundingBox getVoxelBoundingBox(VectorInt4 index);
    /*</methods>*/
}
