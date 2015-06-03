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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNGeometry/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNAnimatable, SCNBoundingVolume, SCNShadable/*</implements>*/ {

    /*<ptr>*/public static class SCNGeometryPtr extends Ptr<SCNGeometry, SCNGeometryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNGeometry.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNGeometry() {}
    protected SCNGeometry(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "materials")
    public native NSArray<SCNMaterial> getMaterials();
    @Property(selector = "setMaterials:")
    public native void setMaterials(NSArray<SCNMaterial> v);
    @Property(selector = "firstMaterial")
    public native SCNMaterial getFirstMaterial();
    @Property(selector = "setFirstMaterial:")
    public native void setFirstMaterial(SCNMaterial v);
    @Property(selector = "geometryElementCount")
    public native @MachineSizedSInt long getGeometryElementCount();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "levelsOfDetail")
    public native NSArray<SCNLevelOfDetail> getLevelsOfDetail();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setLevelsOfDetail:")
    public native void setLevelsOfDetail(NSArray<SCNLevelOfDetail> v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "subdivisionLevel")
    public native @MachineSizedUInt long getSubdivisionLevel();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setSubdivisionLevel:")
    public native void setSubdivisionLevel(@MachineSizedUInt long v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "edgeCreasesElement")
    public native SCNGeometryElement getEdgeCreasesElement();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setEdgeCreasesElement:")
    public native void setEdgeCreasesElement(SCNGeometryElement v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "edgeCreasesSource")
    public native SCNGeometrySource getEdgeCreasesSource();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setEdgeCreasesSource:")
    public native void setEdgeCreasesSource(SCNGeometrySource v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "shaderModifiers")
    public native @org.robovm.rt.bro.annotation.Marshaler(SCNShaderModifierEntryPoint.AsStringMapMarshaler.class) Map<SCNShaderModifierEntryPoint, String> getShaderModifiers();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setShaderModifiers:")
    public native void setShaderModifiers(@org.robovm.rt.bro.annotation.Marshaler(SCNShaderModifierEntryPoint.AsStringMapMarshaler.class) Map<SCNShaderModifierEntryPoint, String> v);
    @Property(selector = "program")
    public native SCNProgram getProgram();
    @Property(selector = "setProgram:")
    public native void setProgram(SCNProgram v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "insertMaterial:atIndex:")
    public native void insertMaterial(SCNMaterial material, @MachineSizedUInt long index);
    @Method(selector = "removeMaterialAtIndex:")
    public native void removeMaterial(@MachineSizedUInt long index);
    @Method(selector = "replaceMaterialAtIndex:withMaterial:")
    public native void replaceMaterial(@MachineSizedUInt long index, SCNMaterial material);
    @Method(selector = "materialWithName:")
    public native SCNMaterial getMaterial(String name);
    @Method(selector = "geometrySourcesForSemantic:")
    public native NSArray<SCNGeometrySource> getGeometrySourcesForSemantic(SCNGeometrySourceSemantic semantic);
    @Method(selector = "geometryElementAtIndex:")
    public native SCNGeometryElement getGeometryElement(@MachineSizedSInt long elementIndex);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "geometry")
    public static native SCNGeometry create();
    @Method(selector = "geometryWithSources:elements:")
    public static native SCNGeometry create(NSArray<SCNGeometrySource> sources, NSArray<SCNGeometryElement> elements);
    @Method(selector = "addAnimation:forKey:")
    public native void addAnimation(CAAnimation animation, String key);
    @Method(selector = "removeAllAnimations")
    public native void removeAllAnimations();
    @Method(selector = "removeAnimationForKey:")
    public native void removeAnimation(String key);
    @Method(selector = "animationKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAnimationKeys();
    @Method(selector = "animationForKey:")
    public native CAAnimation getAnimation(String key);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "pauseAnimationForKey:")
    public native void pauseAnimation(String key);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "resumeAnimationForKey:")
    public native void resumeAnimation(String key);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "isAnimationForKeyPaused:")
    public native boolean isAnimationPaused(String key);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "removeAnimationForKey:fadeOutDuration:")
    public native void removeAnimation(String key, @MachineSizedFloat double duration);
    @Method(selector = "getBoundingBoxMin:max:")
    public native boolean getBoundingBox(SCNVector3.SCNVector3Ptr min, SCNVector3.SCNVector3Ptr max);
    @Method(selector = "getBoundingSphereCenter:radius:")
    public native boolean getBoundingSphere(SCNVector3.SCNVector3Ptr center, MachineSizedFloatPtr radius);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setBoundingBoxMin:max:")
    public native void setBoundingBox(SCNVector3.SCNVector3Ptr min, SCNVector3.SCNVector3Ptr max);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "handleBindingOfSymbol:usingBlock:")
    public native void handleBindingOfSymbol(String symbol, @Block VoidBlock4<Integer, Integer, SCNNode, SCNRenderer> block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "handleUnbindingOfSymbol:usingBlock:")
    public native void handleUnbindingOfSymbol(String symbol, @Block VoidBlock4<Integer, Integer, SCNNode, SCNRenderer> block);
    /*</methods>*/
}
