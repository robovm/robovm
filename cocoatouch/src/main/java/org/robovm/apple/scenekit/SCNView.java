/*
 * Copyright (C) 2014 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements SCNSceneRenderer, SCNTechniqueSupport/*</implements>*/ {

    /*<ptr>*/public static class SCNViewPtr extends Ptr<SCNView, SCNViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNView() {}
    protected SCNView(SkipInit skipInit) { super(skipInit); }
    public SCNView(@ByVal CGRect frame, NSDictionary<?, ?> options) { super((SkipInit) null); initObject(init(frame, options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "scene")
    public native SCNScene getScene();
    @Property(selector = "setScene:")
    public native void setScene(SCNScene v);
    @Property(selector = "allowsCameraControl")
    public native boolean isAllowsCameraControl();
    @Property(selector = "setAllowsCameraControl:")
    public native void setAllowsCameraControl(boolean v);
    @Property(selector = "eaglContext")
    public native EAGLContext getEaglContext();
    @Property(selector = "setEaglContext:")
    public native void setEaglContext(EAGLContext v);
    @Property(selector = "preferredFramesPerSecond")
    public native @MachineSizedSInt long getPreferredFramesPerSecond();
    @Property(selector = "setPreferredFramesPerSecond:")
    public native void setPreferredFramesPerSecond(@MachineSizedSInt long v);
    @Property(selector = "antialiasingMode")
    public native SCNAntialiasingMode getAntialiasingMode();
    @Property(selector = "setAntialiasingMode:")
    public native void setAntialiasingMode(SCNAntialiasingMode v);
    @Property(selector = "sceneTime")
    public native double getSceneTime();
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
    public native boolean isLoops();
    @Property(selector = "setLoops:")
    public native void setLoops(boolean v);
    @Property(selector = "pointOfView")
    public native SCNNode getPointOfView();
    @Property(selector = "setPointOfView:")
    public native void setPointOfView(SCNNode v);
    @Property(selector = "autoenablesDefaultLighting")
    public native boolean isAutoenablesDefaultLighting();
    @Property(selector = "setAutoenablesDefaultLighting:")
    public native void setAutoenablesDefaultLighting(boolean v);
    @Property(selector = "isJitteringEnabled")
    public native boolean isJitteringEnabled();
    @Property(selector = "setJitteringEnabled:")
    public native void setJitteringEnabled(boolean v);
    @Property(selector = "showsStatistics")
    public native boolean isShowsStatistics();
    @Property(selector = "setShowsStatistics:")
    public native void setShowsStatistics(boolean v);
    @Property(selector = "overlaySKScene")
    public native SKScene getOverlaySKScene();
    @Property(selector = "setOverlaySKScene:")
    public native void setOverlaySKScene(SKScene v);
    @Property(selector = "context")
    public native EAGLContext getContext();
    @Property(selector = "technique")
    public native SCNTechnique getTechnique();
    @Property(selector = "setTechnique:")
    public native void setTechnique(SCNTechnique v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:options:")
    protected native @Pointer long init(@ByVal CGRect frame, NSDictionary<?, ?> options);
    @Method(selector = "snapshot")
    public native UIImage snapshot();
    @Method(selector = "play:")
    public native void play(NSObject sender);
    @Method(selector = "pause:")
    public native void pause(NSObject sender);
    @Method(selector = "stop:")
    public native void stop(NSObject sender);
    @Method(selector = "hitTest:options:")
    public native NSArray<SCNHitTestResult> hitTest(@ByVal CGPoint thePoint, SCNHitTestOptions options);
    @Method(selector = "isNodeInsideFrustum:withPointOfView:")
    public native boolean isNodeInsideFrustum(SCNNode node, SCNNode pointOfView);
    @Method(selector = "projectPoint:")
    public native @ByVal SCNVector3 projectPoint(@ByVal SCNVector3 point);
    @Method(selector = "unprojectPoint:")
    public native @ByVal SCNVector3 unprojectPoint(@ByVal SCNVector3 point);
    @Method(selector = "prepareObject:shouldAbortBlock:")
    public native boolean prepareObject(NSObject object, @Block Block0<Boolean> block);
    @Method(selector = "prepareObjects:withCompletionHandler:")
    public native void prepareObjects(NSArray<?> objects, @Block VoidBooleanBlock completionHandler);
    /*</methods>*/
}
