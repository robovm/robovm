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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKConstraint/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKConstraintPtr extends Ptr<SKConstraint, SKConstraintPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKConstraint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKConstraint() {}
    protected SKConstraint(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "enabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "referenceNode")
    public native SKNode getReferenceNode();
    @Property(selector = "setReferenceNode:")
    public native void setReferenceNode(SKNode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "positionX:")
    public static native SKConstraint positionX(SKRange range);
    @Method(selector = "positionY:")
    public static native SKConstraint positionY(SKRange range);
    @Method(selector = "positionX:Y:")
    public static native SKConstraint positionXY(SKRange xRange, SKRange yRange);
    @Method(selector = "distance:toNode:")
    public static native SKConstraint distanceToNode(SKRange range, SKNode node);
    @Method(selector = "distance:toPoint:")
    public static native SKConstraint distanceToPoint(SKRange range, @ByVal CGPoint point);
    @Method(selector = "distance:toPoint:inNode:")
    public static native SKConstraint distanceToPointInNode(SKRange range, @ByVal CGPoint point, SKNode node);
    @Method(selector = "zRotation:")
    public static native SKConstraint zRotation(SKRange zRange);
    @Method(selector = "orientToNode:offset:")
    public static native SKConstraint orientToNode(SKNode node, SKRange radians);
    @Method(selector = "orientToPoint:offset:")
    public static native SKConstraint orientToPoint(@ByVal CGPoint point, SKRange radians);
    @Method(selector = "orientToPoint:inNode:offset:")
    public static native SKConstraint orientToPointInNode(@ByVal CGPoint point, SKNode node, SKRange radians);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
