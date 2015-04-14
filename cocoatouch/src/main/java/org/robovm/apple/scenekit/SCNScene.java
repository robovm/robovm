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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNScene/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNScenePtr extends Ptr<SCNScene, SCNScenePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNScene.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNScene() {}
    protected SCNScene(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "rootNode")
    public native SCNNode getRootNode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "physicsWorld")
    public native SCNPhysicsWorld getPhysicsWorld();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "background")
    public native SCNMaterialProperty getBackground();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fogStartDistance")
    public native @MachineSizedFloat double getFogStartDistance();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFogStartDistance:")
    public native void setFogStartDistance(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fogEndDistance")
    public native @MachineSizedFloat double getFogEndDistance();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFogEndDistance:")
    public native void setFogEndDistance(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fogDensityExponent")
    public native @MachineSizedFloat double getFogDensityExponent();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFogDensityExponent:")
    public native void setFogDensityExponent(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fogColor")
    public native UIColor getFogColor();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFogColor:")
    public native void setFogColor(UIColor v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isPaused")
    public native boolean isPaused();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    @Property(selector = "particleSystems")
    public native NSArray<SCNParticleSystem> getParticleSystems();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setAttribute(SCNSceneAttribute key, NSObject attribute) {
        setAttribute(attribute, key);
    }
    /**
     * 
     * @param url
     * @param options
     * @return
     * @throws NSErrorException
     */
    public static SCNScene create(NSURL url, SCNSceneSourceOptions options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        SCNScene result = create(url, options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    
    /* Convenience methods */
    public double getStartTime() {
        NSNumber val = (NSNumber) getAttribute(SCNSceneAttribute.StartTime);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    public SCNScene setStartTime(double startTime) {
        setAttribute(NSNumber.valueOf(startTime), SCNSceneAttribute.StartTime);
        return this;
    }
    public double getEndTime() {
        NSNumber val = (NSNumber) getAttribute(SCNSceneAttribute.EndTime);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    public SCNScene setEndTime(double endTime) {
        setAttribute(NSNumber.valueOf(endTime), SCNSceneAttribute.EndTime);
        return this;
    }
    public double getFrameRate() {
        NSNumber val = (NSNumber) getAttribute(SCNSceneAttribute.FrameRate);
        if (val != null) {
            return val.doubleValue();
        }
        return 0;
    }
    public SCNScene setFrameRate(double frameRate) {
        setAttribute(NSNumber.valueOf(frameRate), SCNSceneAttribute.FrameRate);
        return this;
    }
    public SCNVector3 getUpAxis() {
        NSValue val = (NSValue) getAttribute(SCNSceneAttribute.UpAxis);
        if (val != null) {
            return val.SCNVector3Value();
        }
        return null;
    }
    public SCNScene setUpAxis(SCNVector3 upAxis) {
        setAttribute(NSValue.valueOf(upAxis), SCNSceneAttribute.UpAxis);
        return this;
    }
    /*<methods>*/
    @Method(selector = "attributeForKey:")
    public native NSObject getAttribute(SCNSceneAttribute key);
    @Method(selector = "setAttribute:forKey:")
    protected native void setAttribute(NSObject attribute, SCNSceneAttribute key);
    @Method(selector = "scene")
    public static native SCNScene create();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "sceneNamed:")
    public static native SCNScene create(String name);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "sceneNamed:inDirectory:options:")
    public static native SCNScene create(String name, String directory, SCNSceneSourceOptions options);
    @Method(selector = "sceneWithURL:options:error:")
    protected static native SCNScene create(NSURL url, SCNSceneSourceOptions options, NSError.NSErrorPtr error);
    @Method(selector = "addParticleSystem:withTransform:")
    public native void addParticleSystem(SCNParticleSystem system, @ByVal SCNMatrix4 transform);
    @Method(selector = "removeAllParticleSystems")
    public native void removeAllParticleSystems();
    @Method(selector = "removeParticleSystem:")
    public native void removeParticleSystem(SCNParticleSystem system);
    /*</methods>*/
}
