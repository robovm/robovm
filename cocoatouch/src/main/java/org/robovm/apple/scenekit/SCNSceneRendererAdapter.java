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
    public SCNScene getScene() { return null; }
    @NotImplemented("setScene:")
    public void setScene(SCNScene v) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("sceneTime")
    public double getSceneTime() { return 0; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("setSceneTime:")
    public void setSceneTime(double v) {}
    @NotImplemented("delegate")
    public SCNSceneRendererDelegate getDelegate() { return null; }
    @NotImplemented("setDelegate:")
    public void setDelegate(SCNSceneRendererDelegate v) {}
    @NotImplemented("isPlaying")
    public boolean isPlaying() { return false; }
    @NotImplemented("setPlaying:")
    public void setPlaying(boolean v) {}
    @NotImplemented("loops")
    public boolean loops() { return false; }
    @NotImplemented("setLoops:")
    public void setLoops(boolean v) {}
    @NotImplemented("pointOfView")
    public SCNNode getPointOfView() { return null; }
    @NotImplemented("setPointOfView:")
    public void setPointOfView(SCNNode v) {}
    @NotImplemented("autoenablesDefaultLighting")
    public boolean autoenablesDefaultLighting() { return false; }
    @NotImplemented("setAutoenablesDefaultLighting:")
    public void setAutoenablesDefaultLighting(boolean v) {}
    @NotImplemented("isJitteringEnabled")
    public boolean isJitteringEnabled() { return false; }
    @NotImplemented("setJitteringEnabled:")
    public void setJitteringEnabled(boolean v) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("showsStatistics")
    public boolean showsStatistics() { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("setShowsStatistics:")
    public void setShowsStatistics(boolean v) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("overlaySKScene")
    public SKScene getOverlaySKScene() { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("setOverlaySKScene:")
    public void setOverlaySKScene(SKScene v) {}
    @NotImplemented("context")
    public EAGLContext getContext() { return null; }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("hitTest:options:")
    public NSArray<SCNHitTestResult> hitTest(@ByVal CGPoint thePoint, SCNHitTestOptions options) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("isNodeInsideFrustum:withPointOfView:")
    public boolean isNodeInsideFrustum(SCNNode node, SCNNode pointOfView) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("projectPoint:")
    public @ByVal SCNVector3 projectPoint(@ByVal SCNVector3 point) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("unprojectPoint:")
    public @ByVal SCNVector3 unprojectPoint(@ByVal SCNVector3 point) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("prepareObject:shouldAbortBlock:")
    public boolean prepareObject(NSObject object, @Block Block0<Boolean> block) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("prepareObjects:withCompletionHandler:")
    public void prepareObjects(NSArray<?> objects, @Block VoidBooleanBlock completionHandler) {}
    /*</methods>*/
}
