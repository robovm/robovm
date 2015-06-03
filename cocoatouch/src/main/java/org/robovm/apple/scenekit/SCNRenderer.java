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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNRenderer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNSceneRenderer, SCNTechniqueSupport/*</implements>*/ {

    /*<ptr>*/public static class SCNRendererPtr extends Ptr<SCNRenderer, SCNRendererPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNRenderer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNRenderer() {}
    protected SCNRenderer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "scene")
    public native SCNScene getScene();
    @Property(selector = "setScene:")
    public native void setScene(SCNScene v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "nextFrameTime")
    public native double getNextFrameTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "sceneTime")
    public native double getSceneTime();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setSceneTime:")
    public native void setSceneTime(double v);
    @Property(selector = "delegate")
    public native SCNSceneRendererDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(SCNSceneRendererDelegate v);
    @Property(selector = "isPlaying")
    public native boolean isPlaying();
    @Property(selector = "setPlaying:")
    public native void setPlaying(boolean v);
    @Property(selector = "loops")
    public native boolean loops();
    @Property(selector = "setLoops:")
    public native void setLoops(boolean v);
    @Property(selector = "pointOfView")
    public native SCNNode getPointOfView();
    @Property(selector = "setPointOfView:")
    public native void setPointOfView(SCNNode v);
    @Property(selector = "autoenablesDefaultLighting")
    public native boolean autoenablesDefaultLighting();
    @Property(selector = "setAutoenablesDefaultLighting:")
    public native void setAutoenablesDefaultLighting(boolean v);
    @Property(selector = "isJitteringEnabled")
    public native boolean isJitteringEnabled();
    @Property(selector = "setJitteringEnabled:")
    public native void setJitteringEnabled(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "showsStatistics")
    public native boolean showsStatistics();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setShowsStatistics:")
    public native void setShowsStatistics(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Property(selector = "overlaySKScene")
    public native SKScene getOverlaySKScene();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @WeaklyLinked
    @Property(selector = "setOverlaySKScene:")
    public native void setOverlaySKScene(SKScene v);
    @WeaklyLinked
    @Property(selector = "context")
    public native EAGLContext getContext();
    @Property(selector = "technique")
    public native SCNTechnique getTechnique();
    @Property(selector = "setTechnique:")
    public native void setTechnique(SCNTechnique v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "render")
    public native void render();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "renderAtTime:")
    public native void renderAtTime(double time);
    @Method(selector = "rendererWithContext:options:")
    public static native SCNRenderer create(EAGLContext context, NSDictionary<?, ?> options);
    @Method(selector = "hitTest:options:")
    public native NSArray<SCNHitTestResult> hitTest(@ByVal CGPoint thePoint, SCNHitTestOptions options);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "isNodeInsideFrustum:withPointOfView:")
    public native boolean isNodeInsideFrustum(SCNNode node, SCNNode pointOfView);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "projectPoint:")
    public native @ByVal SCNVector3 projectPoint(@ByVal SCNVector3 point);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "unprojectPoint:")
    public native @ByVal SCNVector3 unprojectPoint(@ByVal SCNVector3 point);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "prepareObject:shouldAbortBlock:")
    public native boolean prepareObject(NSObject object, @Block Block0<Boolean> block);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "prepareObjects:withCompletionHandler:")
    public native void prepareObjects(NSArray<?> objects, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
