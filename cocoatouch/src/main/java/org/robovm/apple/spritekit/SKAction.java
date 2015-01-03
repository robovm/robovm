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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKAction/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKActionPtr extends Ptr<SKAction, SKActionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKAction.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKAction() {}
    protected SKAction(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "setDuration:")
    public native void setDuration(double v);
    @Property(selector = "timingMode")
    public native SKActionTimingMode getTimingMode();
    @Property(selector = "setTimingMode:")
    public native void setTimingMode(SKActionTimingMode v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "timingFunction")
    public native @Block Block1<Float, Float> getTimingFunction();
    /**
     * @since Available in iOS 8.0 and later.
     */
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
    public native SKAction reversedAction();
    @Method(selector = "moveBy:duration:")
    public static native SKAction moveBy(@ByVal CGVector delta, double sec);
    @Method(selector = "moveByX:y:duration:")
    public static native SKAction moveBy(@MachineSizedFloat double deltaX, @MachineSizedFloat double deltaY, double sec);
    @Method(selector = "moveTo:duration:")
    public static native SKAction moveTo(@ByVal CGPoint location, double sec);
    @Method(selector = "moveToX:duration:")
    public static native SKAction moveToX(@MachineSizedFloat double x, double sec);
    @Method(selector = "moveToY:duration:")
    public static native SKAction moveToY(@MachineSizedFloat double y, double sec);
    @Method(selector = "rotateByAngle:duration:")
    public static native SKAction rotateBy(@MachineSizedFloat double radians, double sec);
    @Method(selector = "rotateToAngle:duration:")
    public static native SKAction rotateTo(@MachineSizedFloat double radians, double sec);
    @Method(selector = "rotateToAngle:duration:shortestUnitArc:")
    public static native SKAction rotateTo(@MachineSizedFloat double radians, double sec, boolean shortestUnitArc);
    @Method(selector = "resizeByWidth:height:duration:")
    public static native SKAction resizeBy(@MachineSizedFloat double width, @MachineSizedFloat double height, double duration);
    @Method(selector = "resizeToWidth:height:duration:")
    public static native SKAction resizeTo(@MachineSizedFloat double width, @MachineSizedFloat double height, double duration);
    @Method(selector = "resizeToWidth:duration:")
    public static native SKAction resizeToWidth(@MachineSizedFloat double width, double duration);
    @Method(selector = "resizeToHeight:duration:")
    public static native SKAction resizeToHeight(@MachineSizedFloat double height, double duration);
    @Method(selector = "scaleBy:duration:")
    public static native SKAction scaleBy(@MachineSizedFloat double scale, double sec);
    @Method(selector = "scaleXBy:y:duration:")
    public static native SKAction scaleBy(@MachineSizedFloat double xScale, @MachineSizedFloat double yScale, double sec);
    @Method(selector = "scaleTo:duration:")
    public static native SKAction scaleTo(@MachineSizedFloat double scale, double sec);
    @Method(selector = "scaleXTo:y:duration:")
    public static native SKAction scaleTo(@MachineSizedFloat double xScale, @MachineSizedFloat double yScale, double sec);
    @Method(selector = "scaleXTo:duration:")
    public static native SKAction scaleXTo(@MachineSizedFloat double scale, double sec);
    @Method(selector = "scaleYTo:duration:")
    public static native SKAction scaleYTo(@MachineSizedFloat double scale, double sec);
    @Method(selector = "sequence:")
    public static native SKAction sequence(NSArray<SKAction> actions);
    @Method(selector = "group:")
    public static native SKAction group(NSArray<SKAction> actions);
    @Method(selector = "repeatAction:count:")
    public static native SKAction repeat(SKAction action, @MachineSizedUInt long count);
    @Method(selector = "repeatActionForever:")
    public static native SKAction repeatForever(SKAction action);
    @Method(selector = "fadeInWithDuration:")
    public static native SKAction fadeIn(double sec);
    @Method(selector = "fadeOutWithDuration:")
    public static native SKAction fadeOut(double sec);
    @Method(selector = "fadeAlphaBy:duration:")
    public static native SKAction fadeAlphaBy(@MachineSizedFloat double factor, double sec);
    @Method(selector = "fadeAlphaTo:duration:")
    public static native SKAction fadeAlphaTo(@MachineSizedFloat double alpha, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "hide")
    public static native SKAction hide();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "unhide")
    public static native SKAction unhide();
    @Method(selector = "setTexture:")
    public static native SKAction setTexture(SKTexture texture);
    @Method(selector = "setTexture:resize:")
    public static native SKAction setTexture(SKTexture texture, boolean resize);
    @Method(selector = "animateWithTextures:timePerFrame:")
    public static native SKAction animate(NSArray<SKTexture> textures, double sec);
    @Method(selector = "animateWithTextures:timePerFrame:resize:restore:")
    public static native SKAction animate(NSArray<SKTexture> textures, double sec, boolean resize, boolean restore);
    @Method(selector = "playSoundFileNamed:waitForCompletion:")
    public static native SKAction playSound(String soundFile, boolean wait);
    @Method(selector = "colorizeWithColor:colorBlendFactor:duration:")
    public static native SKAction colorize(UIColor color, @MachineSizedFloat double colorBlendFactor, double sec);
    @Method(selector = "colorizeWithColorBlendFactor:duration:")
    public static native SKAction colorize(@MachineSizedFloat double colorBlendFactor, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "falloffTo:duration:")
    public static native SKAction falloffTo(float falloff, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "falloffBy:duration:")
    public static native SKAction falloffBy(float falloff, double sec);
    @Method(selector = "followPath:duration:")
    public static native SKAction followPath(CGPath path, double sec);
    @Method(selector = "followPath:asOffset:orientToPath:duration:")
    public static native SKAction followPath(CGPath path, boolean offset, boolean orient, double sec);
    @Method(selector = "followPath:speed:")
    public static native SKAction followPathWithSpeed(CGPath path, @MachineSizedFloat double speed);
    @Method(selector = "followPath:asOffset:orientToPath:speed:")
    public static native SKAction followPathWithSpeed(CGPath path, boolean offset, boolean orient, @MachineSizedFloat double speed);
    @Method(selector = "speedBy:duration:")
    public static native SKAction speedBy(@MachineSizedFloat double speed, double sec);
    @Method(selector = "speedTo:duration:")
    public static native SKAction speedTo(@MachineSizedFloat double speed, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "reachTo:rootNode:duration:")
    public static native SKAction reachTo(@ByVal CGPoint position, SKNode root, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "reachTo:rootNode:velocity:")
    public static native SKAction reachToWithVelocity(@ByVal CGPoint position, SKNode root, @MachineSizedFloat double velocity);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "reachToNode:rootNode:duration:")
    public static native SKAction reachToNode(SKNode node, SKNode root, double sec);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "reachToNode:rootNode:velocity:")
    public static native SKAction reachToNodeWithVelocity(SKNode node, SKNode root, @MachineSizedFloat double velocity);
    @Method(selector = "strengthTo:duration:")
    public static native SKAction strengthTo(float strength, double sec);
    @Method(selector = "strengthBy:duration:")
    public static native SKAction strengthBy(float strength, double sec);
    @Method(selector = "waitForDuration:")
    public static native SKAction wait(double sec);
    @Method(selector = "waitForDuration:withRange:")
    public static native SKAction wait(double sec, double durationRange);
    @Method(selector = "removeFromParent")
    public static native SKAction removeFromParent();
    @Method(selector = "runBlock:")
    public static native SKAction runBlock(@Block Runnable block);
    @Method(selector = "runBlock:queue:")
    public static native SKAction runBlock(@Block Runnable block, DispatchQueue queue);
    @Method(selector = "runAction:onChildWithName:")
    public static native SKAction runAction(SKAction action, String name);
    @Method(selector = "customActionWithDuration:actionBlock:")
    public static native SKAction customAction(double seconds, @Block("(,@MachineSizedFloat)") VoidBlock2<SKNode, Double> block);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
