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
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNAction/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNActionPtr extends Ptr<SCNAction, SCNActionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNAction.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNAction() {}
    protected SCNAction(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "setDuration:")
    public native void setDuration(double v);
    @Property(selector = "timingMode")
    public native SCNActionTimingMode getTimingMode();
    @Property(selector = "setTimingMode:")
    public native void setTimingMode(SCNActionTimingMode v);
    @Property(selector = "timingFunction")
    public native @Block Block1<Float, Float> getTimingFunction();
    @Property(selector = "setTimingFunction:")
    public native void setTimingFunction(@Block Block1<Float, Float> v);
    @Property(selector = "speed")
    public native @MachineSizedFloat double getSpeed();
    @Property(selector = "setSpeed:")
    public native void setSpeed(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "reversedAction")
    public native SCNAction getReversedAction();
    @Method(selector = "moveByX:y:z:duration:")
    public static native SCNAction moveBy(@MachineSizedFloat double deltaX, @MachineSizedFloat double deltaY, @MachineSizedFloat double deltaZ, double duration);
    @Method(selector = "moveBy:duration:")
    public static native SCNAction moveBy(@ByVal SCNVector3 delta, double duration);
    @Method(selector = "moveTo:duration:")
    public static native SCNAction moveTo(@ByVal SCNVector3 location, double duration);
    @Method(selector = "rotateByX:y:z:duration:")
    public static native SCNAction rotateBy(@MachineSizedFloat double xAngle, @MachineSizedFloat double yAngle, @MachineSizedFloat double zAngle, double duration);
    @Method(selector = "rotateToX:y:z:duration:")
    public static native SCNAction rotateTo(@MachineSizedFloat double xAngle, @MachineSizedFloat double yAngle, @MachineSizedFloat double zAngle, double duration);
    @Method(selector = "rotateToX:y:z:duration:shortestUnitArc:")
    public static native SCNAction rotateTo(@MachineSizedFloat double xAngle, @MachineSizedFloat double yAngle, @MachineSizedFloat double zAngle, double duration, boolean shortestUnitArc);
    @Method(selector = "rotateByAngle:aroundAxis:duration:")
    public static native SCNAction rotateBy(@MachineSizedFloat double angle, @ByVal SCNVector3 axis, double duration);
    @Method(selector = "rotateToAxisAngle:duration:")
    public static native SCNAction rotateTo(@ByVal SCNVector4 axisAngle, double duration);
    @Method(selector = "scaleBy:duration:")
    public static native SCNAction scaleBy(@MachineSizedFloat double scale, double sec);
    @Method(selector = "scaleTo:duration:")
    public static native SCNAction scaleTo(@MachineSizedFloat double scale, double sec);
    @Method(selector = "sequence:")
    public static native SCNAction sequence(NSArray<SCNAction> actions);
    @Method(selector = "group:")
    public static native SCNAction group(NSArray<SCNAction> actions);
    @Method(selector = "repeatAction:count:")
    public static native SCNAction repeat(SCNAction action, @MachineSizedUInt long count);
    @Method(selector = "repeatActionForever:")
    public static native SCNAction repeatForever(SCNAction action);
    @Method(selector = "fadeInWithDuration:")
    public static native SCNAction fadeIn(double sec);
    @Method(selector = "fadeOutWithDuration:")
    public static native SCNAction fadeOut(double sec);
    @Method(selector = "fadeOpacityBy:duration:")
    public static native SCNAction fadeOpacityBy(@MachineSizedFloat double factor, double sec);
    @Method(selector = "fadeOpacityTo:duration:")
    public static native SCNAction fadeOpacityTo(@MachineSizedFloat double opacity, double sec);
    @Method(selector = "waitForDuration:")
    public static native SCNAction wait(double sec);
    @Method(selector = "waitForDuration:withRange:")
    public static native SCNAction wait(double sec, double durationRange);
    @Method(selector = "removeFromParentNode")
    public static native SCNAction removeFromParentNode();
    @Method(selector = "runBlock:")
    public static native SCNAction run(@Block VoidBlock1<SCNNode> block);
    @Method(selector = "runBlock:queue:")
    public static native SCNAction run(@Block VoidBlock1<SCNNode> block, DispatchQueue queue);
    @Method(selector = "javaScriptActionWithScript:duration:")
    public static native SCNAction javaScript(String script, double seconds);
    @Method(selector = "customActionWithDuration:actionBlock:")
    public static native SCNAction custom(double seconds, @Block("(,@MachineSizedFloat)") VoidBlock2<SCNNode, Double> block);
    /*</methods>*/
}
