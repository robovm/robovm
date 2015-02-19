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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNNode/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNAnimatable, SCNActionable, SCNBoundingVolume/*</implements>*/ {

    /*<ptr>*/public static class SCNNodePtr extends Ptr<SCNNode, SCNNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNNode() {}
    protected SCNNode(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "light")
    public native SCNLight getLight();
    @Property(selector = "setLight:")
    public native void setLight(SCNLight v);
    @Property(selector = "camera")
    public native SCNCamera getCamera();
    @Property(selector = "setCamera:")
    public native void setCamera(SCNCamera v);
    @Property(selector = "geometry")
    public native SCNGeometry getGeometry();
    @Property(selector = "setGeometry:")
    public native void setGeometry(SCNGeometry v);
    @Property(selector = "skinner")
    public native SCNSkinner getSkinner();
    @Property(selector = "setSkinner:")
    public native void setSkinner(SCNSkinner v);
    @Property(selector = "morpher")
    public native SCNMorpher getMorpher();
    @Property(selector = "setMorpher:")
    public native void setMorpher(SCNMorpher v);
    @Property(selector = "transform")
    public native @ByVal SCNMatrix4 getTransform();
    @Property(selector = "setTransform:")
    public native void setTransform(@ByVal SCNMatrix4 v);
    @Property(selector = "position")
    public native @ByVal SCNVector3 getPosition();
    @Property(selector = "setPosition:")
    public native void setPosition(@ByVal SCNVector3 v);
    @Property(selector = "rotation")
    public native @ByVal SCNVector4 getRotation();
    @Property(selector = "setRotation:")
    public native void setRotation(@ByVal SCNVector4 v);
    @Property(selector = "orientation")
    public native @ByVal SCNVector4 getOrientation();
    @Property(selector = "setOrientation:")
    public native void setOrientation(@ByVal SCNVector4 v);
    @Property(selector = "eulerAngles")
    public native @ByVal SCNVector3 getEulerAngles();
    @Property(selector = "setEulerAngles:")
    public native void setEulerAngles(@ByVal SCNVector3 v);
    @Property(selector = "scale")
    public native @ByVal SCNVector3 getScale();
    @Property(selector = "setScale:")
    public native void setScale(@ByVal SCNVector3 v);
    @Property(selector = "pivot")
    public native @ByVal SCNMatrix4 getPivot();
    @Property(selector = "setPivot:")
    public native void setPivot(@ByVal SCNMatrix4 v);
    @Property(selector = "worldTransform")
    public native @ByVal SCNMatrix4 getWorldTransform();
    @Property(selector = "isHidden")
    public native boolean isHidden();
    @Property(selector = "setHidden:")
    public native void setHidden(boolean v);
    @Property(selector = "opacity")
    public native @MachineSizedFloat double getOpacity();
    @Property(selector = "setOpacity:")
    public native void setOpacity(@MachineSizedFloat double v);
    @Property(selector = "renderingOrder")
    public native @MachineSizedSInt long getRenderingOrder();
    @Property(selector = "setRenderingOrder:")
    public native void setRenderingOrder(@MachineSizedSInt long v);
    @Property(selector = "castsShadow")
    public native boolean castsShadow();
    @Property(selector = "setCastsShadow:")
    public native void setCastsShadow(boolean v);
    @Property(selector = "parentNode")
    public native SCNNode getParentNode();
    @Property(selector = "childNodes")
    public native NSArray<SCNNode> getChildNodes();
    @Property(selector = "physicsBody")
    public native SCNPhysicsBody getPhysicsBody();
    @Property(selector = "setPhysicsBody:")
    public native void setPhysicsBody(SCNPhysicsBody v);
    @Property(selector = "physicsField")
    public native SCNPhysicsField getPhysicsField();
    @Property(selector = "setPhysicsField:")
    public native void setPhysicsField(SCNPhysicsField v);
    @Property(selector = "constraints")
    public native NSArray<SCNConstraint> getConstraints();
    @Property(selector = "setConstraints:")
    public native void setConstraints(NSArray<SCNConstraint> v);
    @Property(selector = "filters")
    public native NSArray<org.robovm.apple.coreimage.CIFilter> getFilters();
    @Property(selector = "setFilters:")
    public native void setFilters(NSArray<org.robovm.apple.coreimage.CIFilter> v);
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    @Property(selector = "rendererDelegate")
    public native SCNNodeRendererDelegate getRendererDelegate();
    @Property(selector = "setRendererDelegate:", strongRef = true)
    public native void setRendererDelegate(SCNNodeRendererDelegate v);
    @Property(selector = "categoryBitMask")
    public native @MachineSizedUInt long getCategoryBitMask();
    @Property(selector = "setCategoryBitMask:")
    public native void setCategoryBitMask(@MachineSizedUInt long v);
    @Property(selector = "particleSystems")
    public native NSArray<SCNParticleSystem> getParticleSystems();
    /*</properties>*/
    /*<members>*//*</members>*/
    public SCNVector3 getBoundingBoxMin() {
        SCNVector3.SCNVector3Ptr ptr = new SCNVector3.SCNVector3Ptr();
        boolean result = getBoundingBox(ptr, null);
        if (result) {
            return ptr.get();
        }
        return null;
    }
    public SCNVector3 getBoundingBoxMax() {
        SCNVector3.SCNVector3Ptr ptr = new SCNVector3.SCNVector3Ptr();
        boolean result = getBoundingBox(null, ptr);
        if (result) {
            return ptr.get();
        }
        return null;
    }
    public SCNVector3 getBoundingSphereCenter() {
        SCNVector3.SCNVector3Ptr ptr = new SCNVector3.SCNVector3Ptr();
        boolean result = getBoundingSphere(ptr, null);
        if (result) {
            return ptr.get();
        }
        return null;
    }
    public double getBoundingSphereRadius() {
        MachineSizedFloatPtr ptr = new MachineSizedFloatPtr();
        boolean result = getBoundingSphere(null, ptr);
        if (result) {
            return ptr.get();
        }
        return 0;
    }
    public void setBoundingBox(SCNVector3 min, SCNVector3 max) {
        SCNVector3.SCNVector3Ptr minptr = new SCNVector3.SCNVector3Ptr();
        minptr.set(min);
        SCNVector3.SCNVector3Ptr maxptr = new SCNVector3.SCNVector3Ptr();
        maxptr.set(max);
        setBoundingBox(minptr, maxptr);
    }
    /*<methods>*/
    @Method(selector = "addChildNode:")
    public native void addChildNode(SCNNode child);
    @Method(selector = "insertChildNode:atIndex:")
    public native void insertChildNode(SCNNode child, @MachineSizedUInt long index);
    @Method(selector = "removeFromParentNode")
    public native void removeFromParentNode();
    @Method(selector = "replaceChildNode:with:")
    public native void replaceChildNode(SCNNode oldChild, SCNNode newChild);
    @Method(selector = "childNodeWithName:recursively:")
    public native SCNNode findChildNodeWithName(String name, boolean recursively);
    @Method(selector = "childNodesPassingTest:")
    public native NSArray<SCNNode> getChildNodesPassingTest(@Block Block2<SCNNode, BooleanPtr, Boolean> predicate);
    @Method(selector = "enumerateChildNodesUsingBlock:")
    public native void enumerateChildNodes(@Block VoidBlock2<SCNNode, BooleanPtr> block);
    @Method(selector = "convertPosition:toNode:")
    public native @ByVal SCNVector3 convertPositionToNode(@ByVal SCNVector3 position, SCNNode node);
    @Method(selector = "convertPosition:fromNode:")
    public native @ByVal SCNVector3 convertPositionFromNode(@ByVal SCNVector3 position, SCNNode node);
    @Method(selector = "convertTransform:toNode:")
    public native @ByVal SCNMatrix4 convertTransformToNode(@ByVal SCNMatrix4 transform, SCNNode node);
    @Method(selector = "convertTransform:fromNode:")
    public native @ByVal SCNMatrix4 convertTransformFromNode(@ByVal SCNMatrix4 transform, SCNNode node);
    @Method(selector = "presentationNode")
    public native SCNNode getPresentationNode();
    @Method(selector = "hitTestWithSegmentFromPoint:toPoint:options:")
    public native NSArray<SCNHitTestResult> hitTestWithSegment(@ByVal SCNVector3 pointA, @ByVal SCNVector3 pointB, SCNHitTestOptions options);
    @Method(selector = "node")
    public static native SCNNode create();
    @Method(selector = "nodeWithGeometry:")
    public static native SCNNode create(SCNGeometry geometry);
    @Method(selector = "addParticleSystem:")
    public native void addParticleSystem(SCNParticleSystem system);
    @Method(selector = "removeAllParticleSystems")
    public native void removeAllParticleSystems();
    @Method(selector = "removeParticleSystem:")
    public native void removeParticleSystem(SCNParticleSystem system);
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
    @Method(selector = "pauseAnimationForKey:")
    public native void pauseAnimation(String key);
    @Method(selector = "resumeAnimationForKey:")
    public native void resumeAnimation(String key);
    @Method(selector = "isAnimationForKeyPaused:")
    public native boolean isAnimationPaused(String key);
    @Method(selector = "removeAnimationForKey:fadeOutDuration:")
    public native void removeAnimation(String key, @MachineSizedFloat double duration);
    @Method(selector = "runAction:")
    public native void runAction(SCNAction action);
    @Method(selector = "runAction:completionHandler:")
    public native void runAction(SCNAction action, @Block Runnable block);
    @Method(selector = "runAction:forKey:")
    public native void runAction(SCNAction action, String key);
    @Method(selector = "runAction:forKey:completionHandler:")
    public native void runAction(SCNAction action, String key, @Block Runnable block);
    @Method(selector = "hasActions")
    public native boolean hasActions();
    @Method(selector = "actionForKey:")
    public native SCNAction getAction(String key);
    @Method(selector = "removeActionForKey:")
    public native void removeAction(String key);
    @Method(selector = "removeAllActions")
    public native void removeAllActions();
    @Method(selector = "getBoundingBoxMin:max:")
    public native boolean getBoundingBox(SCNVector3.SCNVector3Ptr min, SCNVector3.SCNVector3Ptr max);
    @Method(selector = "getBoundingSphereCenter:radius:")
    public native boolean getBoundingSphere(SCNVector3.SCNVector3Ptr center, MachineSizedFloatPtr radius);
    @Method(selector = "setBoundingBoxMin:max:")
    public native void setBoundingBox(SCNVector3.SCNVector3Ptr min, SCNVector3.SCNVector3Ptr max);
    /*</methods>*/
}
