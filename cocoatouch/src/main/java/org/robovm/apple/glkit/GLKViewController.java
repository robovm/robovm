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
package org.robovm.apple.glkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("GLKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*/implements NSCoding, GLKViewDelegate/*</implements>*/ {

    /*<ptr>*/public static class GLKViewControllerPtr extends Ptr<GLKViewController, GLKViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKViewController() {}
    protected GLKViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native GLKViewControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(GLKViewControllerDelegate v);
    @Property(selector = "preferredFramesPerSecond")
    public native @MachineSizedSInt long getPreferredFramesPerSecond();
    @Property(selector = "setPreferredFramesPerSecond:")
    public native void setPreferredFramesPerSecond(@MachineSizedSInt long v);
    @Property(selector = "framesPerSecond")
    public native @MachineSizedSInt long getFramesPerSecond();
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    @Property(selector = "framesDisplayed")
    public native @MachineSizedSInt long getFramesDisplayed();
    @Property(selector = "timeSinceFirstResume")
    public native double getTimeSinceFirstResume();
    @Property(selector = "timeSinceLastResume")
    public native double getTimeSinceLastResume();
    @Property(selector = "timeSinceLastUpdate")
    public native double getTimeSinceLastUpdate();
    @Property(selector = "timeSinceLastDraw")
    public native double getTimeSinceLastDraw();
    @Property(selector = "pauseOnWillResignActive")
    public native boolean isPauseOnWillResignActive();
    @Property(selector = "setPauseOnWillResignActive:")
    public native void setPauseOnWillResignActive(boolean v);
    @Property(selector = "resumeOnDidBecomeActive")
    public native boolean isResumeOnDidBecomeActive();
    @Property(selector = "setResumeOnDidBecomeActive:")
    public native void setResumeOnDidBecomeActive(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "glkView:drawInRect:")
    public native void draw(GLKView view, @ByVal CGRect rect);
    /*</methods>*/
}
