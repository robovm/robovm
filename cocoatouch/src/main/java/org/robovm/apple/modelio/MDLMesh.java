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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLMesh/*</name>*/ 
    extends /*<extends>*/MDLObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MDLMeshPtr extends Ptr<MDLMesh, MDLMeshPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLMesh.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLMesh() {}
    protected MDLMesh(SkipInit skipInit) { super(skipInit); }
    public MDLMesh(MDLMeshBuffer vertexBuffer, @MachineSizedUInt long vertexCount, MDLVertexDescriptor descriptor, NSMutableArray<MDLSubmesh> submeshes) { super((SkipInit) null); initObject(init(vertexBuffer, vertexCount, descriptor, submeshes)); }
    public MDLMesh(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<MDLMeshBuffer> vertexBuffers, @MachineSizedUInt long vertexCount, MDLVertexDescriptor descriptor, NSMutableArray<MDLSubmesh> submeshes) { super((SkipInit) null); initObject(init(vertexBuffers, vertexCount, descriptor, submeshes)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "boundingBox")
    public native @ByVal MDLAxisAlignedBoundingBox getBoundingBox();
    @Property(selector = "vertexDescriptor")
    public native MDLVertexDescriptor getVertexDescriptor();
    @Property(selector = "setVertexDescriptor:")
    public native void setVertexDescriptor(MDLVertexDescriptor v);
    @Property(selector = "vertexCount")
    public native @MachineSizedUInt long getVertexCount();
    @Property(selector = "vertexBuffers")
    public native NSArray getVertexBuffers();
    @Property(selector = "submeshes")
    public native NSMutableArray<MDLSubmesh> getSubmeshes();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithVertexBuffer:vertexCount:descriptor:submeshes:")
    protected native @Pointer long init(MDLMeshBuffer vertexBuffer, @MachineSizedUInt long vertexCount, MDLVertexDescriptor descriptor, NSMutableArray<MDLSubmesh> submeshes);
    @Method(selector = "initWithVertexBuffers:vertexCount:descriptor:submeshes:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsListMarshaler.class) List<MDLMeshBuffer> vertexBuffers, @MachineSizedUInt long vertexCount, MDLVertexDescriptor descriptor, NSMutableArray<MDLSubmesh> submeshes);
    @Method(selector = "vertexAttributeDataForAttributeNamed:")
    public native MDLVertexAttributeData getVertexAttributeData(String name);
    @Method(selector = "addAttributeWithName:format:")
    public native void addAttribute(String name, MDLVertexFormat format);
    @Method(selector = "addNormalsWithAttributeNamed:creaseThreshold:")
    public native void addNormals(String attributeName, float creaseThreshold);
    @Method(selector = "addTangentBasisForTextureCoordinateAttributeNamed:tangentAttributeNamed:bitangentAttributeNamed:")
    public native void addTangentBasis(String textureCoordinateAttributeName, String tangentAttributeName, String bitangentAttributeName);
    @Method(selector = "addTangentBasisForTextureCoordinateAttributeNamed:normalAttributeNamed:tangentAttributeNamed:")
    public native void addTangentBasisWithNormals(String textureCoordinateAttributeName, String normalAttributeName, String tangentAttributeName);
    @Method(selector = "makeVerticesUnique")
    public native void makeVerticesUnique();
    @Method(selector = "newBoxWithDimensions:segments:geometryType:inwardNormals:allocator:")
    public static native MDLMesh newBox(VectorFloat3 dimensions, VectorInt3 segments, MDLGeometryType geometryType, boolean inwardNormals, MDLMeshBufferAllocator allocator);
    @Method(selector = "newEllipsoidWithRadii:radialSegments:verticalSegments:geometryType:inwardNormals:hemisphere:allocator:")
    public static native MDLMesh newEllipsoid(VectorFloat3 radii, @MachineSizedUInt long radialSegments, @MachineSizedUInt long verticalSegments, MDLGeometryType geometryType, boolean inwardNormals, boolean hemisphere, MDLMeshBufferAllocator allocator);
    @Method(selector = "newCylinderWithHeight:radii:radialSegments:verticalSegments:geometryType:inwardNormals:allocator:")
    public static native MDLMesh newCylinder(float height, VectorFloat2 radii, @MachineSizedUInt long radialSegments, @MachineSizedUInt long verticalSegments, MDLGeometryType geometryType, boolean inwardNormals, MDLMeshBufferAllocator allocator);
    @Method(selector = "newEllipticalConeWithHeight:radii:radialSegments:verticalSegments:geometryType:inwardNormals:allocator:")
    public static native MDLMesh newEllipticalCone(float height, VectorFloat2 radii, @MachineSizedUInt long radialSegments, @MachineSizedUInt long verticalSegments, MDLGeometryType geometryType, boolean inwardNormals, MDLMeshBufferAllocator allocator);
    @Method(selector = "newPlaneWithDimensions:segments:geometryType:allocator:")
    public static native MDLMesh newPlane(VectorFloat2 dimensions, VectorInt2 segments, MDLGeometryType geometryType, MDLMeshBufferAllocator allocator);
    @Method(selector = "newIcosahedronWithRadius:inwardNormals:allocator:")
    public static native MDLMesh newIcosahedron(float radius, boolean inwardNormals, MDLMeshBufferAllocator allocator);
    @Method(selector = "newSubdividedMesh:submeshIndex:subdivisionLevels:")
    public static native MDLMesh newSubdividedMesh(MDLMesh mesh, @MachineSizedUInt long submeshIndex, @MachineSizedUInt long subdivisionLevels);
    @Method(selector = "generateAmbientOcclusionTextureWithSize:raysPerSample:attenuationFactor:objectsToConsider:vertexAttributeNamed:materialPropertyNamed:")
    public native boolean generateAmbientOcclusionTexture(VectorInt2 textureSize, @MachineSizedSInt long raysPerSample, float attenuationFactor, NSArray<MDLObject> objectsToConsider, String vertexAttributeName, String materialPropertyName);
    @Method(selector = "generateAmbientOcclusionTextureWithQuality:attenuationFactor:objectsToConsider:vertexAttributeNamed:materialPropertyNamed:")
    public native boolean generateAmbientOcclusionTexture(float bakeQuality, float attenuationFactor, NSArray<MDLObject> objectsToConsider, String vertexAttributeName, String materialPropertyName);
    @Method(selector = "generateAmbientOcclusionVertexColorsWithRaysPerSample:attenuationFactor:objectsToConsider:vertexAttributeNamed:")
    public native boolean generateAmbientOcclusionVertexColors(@MachineSizedSInt long raysPerSample, float attenuationFactor, NSArray<MDLObject> objectsToConsider, String vertexAttributeName);
    @Method(selector = "generateAmbientOcclusionVertexColorsWithQuality:attenuationFactor:objectsToConsider:vertexAttributeNamed:")
    public native boolean generateAmbientOcclusionVertexColors(float bakeQuality, float attenuationFactor, NSArray<MDLObject> objectsToConsider, String vertexAttributeName);
    @Method(selector = "generateLightMapTextureWithTextureSize:lightsToConsider:objectsToConsider:vertexAttributeNamed:materialPropertyNamed:")
    public native boolean generateLightMapTexture(VectorInt2 textureSize, NSArray<MDLLight> lightsToConsider, NSArray<MDLObject> objectsToConsider, String vertexAttributeName, String materialPropertyName);
    @Method(selector = "generateLightMapTextureWithQuality:lightsToConsider:objectsToConsider:vertexAttributeNamed:materialPropertyNamed:")
    public native boolean generateLightMapTexture(float bakeQuality, NSArray<MDLLight> lightsToConsider, NSArray<MDLObject> objectsToConsider, String vertexAttributeName, String materialPropertyName);
    @Method(selector = "generateLightMapVertexColorsWithLightsToConsider:objectsToConsider:vertexAttributeNamed:")
    public native boolean generateLightMapVertexColors(NSArray<MDLLight> lightsToConsider, NSArray<MDLObject> objectsToConsider, String vertexAttributeName);
    /*</methods>*/
}
