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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSceneRendererAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNSceneRenderer/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("scene")
    public SCNScene getScene() { throw new UnsupportedOperationException(); }
    @NotImplemented("setScene:")
    public void setScene(SCNScene v) { throw new UnsupportedOperationException(); }
    @NotImplemented("sceneTime")
    public double getSceneTime() { throw new UnsupportedOperationException(); }
    @NotImplemented("setSceneTime:")
    public void setSceneTime(double v) { throw new UnsupportedOperationException(); }
    @NotImplemented("delegate")
    public SCNSceneRendererDelegate getDelegate() { throw new UnsupportedOperationException(); }
    @NotImplemented("setDelegate:")
    public void setDelegate(SCNSceneRendererDelegate v) { throw new UnsupportedOperationException(); }
    @NotImplemented("isPlaying")
    public boolean isPlaying() { throw new UnsupportedOperationException(); }
    @NotImplemented("setPlaying:")
    public void setPlaying(boolean v) { throw new UnsupportedOperationException(); }
    @NotImplemented("loops")
    public boolean loops() { throw new UnsupportedOperationException(); }
    @NotImplemented("setLoops:")
    public void setLoops(boolean v) { throw new UnsupportedOperationException(); }
    @NotImplemented("pointOfView")
    public SCNNode getPointOfView() { throw new UnsupportedOperationException(); }
    @NotImplemented("setPointOfView:")
    public void setPointOfView(SCNNode v) { throw new UnsupportedOperationException(); }
    @NotImplemented("autoenablesDefaultLighting")
    public boolean autoenablesDefaultLighting() { throw new UnsupportedOperationException(); }
    @NotImplemented("setAutoenablesDefaultLighting:")
    public void setAutoenablesDefaultLighting(boolean v) { throw new UnsupportedOperationException(); }
    @NotImplemented("isJitteringEnabled")
    public boolean isJitteringEnabled() { throw new UnsupportedOperationException(); }
    @NotImplemented("setJitteringEnabled:")
    public void setJitteringEnabled(boolean v) { throw new UnsupportedOperationException(); }
    @NotImplemented("showsStatistics")
    public boolean showsStatistics() { throw new UnsupportedOperationException(); }
    @NotImplemented("setShowsStatistics:")
    public void setShowsStatistics(boolean v) { throw new UnsupportedOperationException(); }
    @NotImplemented("overlaySKScene")
    public SKScene getOverlaySKScene() { throw new UnsupportedOperationException(); }
    @NotImplemented("setOverlaySKScene:")
    public void setOverlaySKScene(SKScene v) { throw new UnsupportedOperationException(); }
    @NotImplemented("context")
    public EAGLContext getContext() { throw new UnsupportedOperationException(); }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("hitTest:options:")
    public NSArray<SCNHitTestResult> hitTest(@ByVal CGPoint thePoint, SCNHitTestOptions options) { throw new UnsupportedOperationException(); }
    @NotImplemented("isNodeInsideFrustum:withPointOfView:")
    public boolean isNodeInsideFrustum(SCNNode node, SCNNode pointOfView) { throw new UnsupportedOperationException(); }
    @NotImplemented("projectPoint:")
    public @ByVal SCNVector3 projectPoint(@ByVal SCNVector3 point) { throw new UnsupportedOperationException(); }
    @NotImplemented("unprojectPoint:")
    public @ByVal SCNVector3 unprojectPoint(@ByVal SCNVector3 point) { throw new UnsupportedOperationException(); }
    @NotImplemented("prepareObject:shouldAbortBlock:")
    public boolean prepareObject(NSObject object, @Block Block0<Boolean> block) { throw new UnsupportedOperationException(); }
    @NotImplemented("prepareObjects:withCompletionHandler:")
    public void prepareObjects(NSArray<?> objects, @Block VoidBooleanBlock completionHandler) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
