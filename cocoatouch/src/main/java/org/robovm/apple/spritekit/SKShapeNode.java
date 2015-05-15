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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKShapeNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKShapeNodePtr extends Ptr<SKShapeNode, SKShapeNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKShapeNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKShapeNode() {}
    protected SKShapeNode(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "path")
    public native CGPath getPath();
    @Property(selector = "setPath:")
    public native void setPath(CGPath v);
    @Property(selector = "strokeColor")
    public native UIColor getStrokeColor();
    @Property(selector = "setStrokeColor:")
    public native void setStrokeColor(UIColor v);
    @Property(selector = "fillColor")
    public native UIColor getFillColor();
    @Property(selector = "setFillColor:")
    public native void setFillColor(UIColor v);
    @Property(selector = "blendMode")
    public native SKBlendMode getBlendMode();
    @Property(selector = "setBlendMode:")
    public native void setBlendMode(SKBlendMode v);
    @Property(selector = "isAntialiased")
    public native boolean isAntialiased();
    @Property(selector = "setAntialiased:")
    public native void setAntialiased(boolean v);
    @Property(selector = "lineWidth")
    public native @MachineSizedFloat double getLineWidth();
    @Property(selector = "setLineWidth:")
    public native void setLineWidth(@MachineSizedFloat double v);
    @Property(selector = "glowWidth")
    public native @MachineSizedFloat double getGlowWidth();
    @Property(selector = "setGlowWidth:")
    public native void setGlowWidth(@MachineSizedFloat double v);
    @Property(selector = "lineCap")
    public native CGLineCap getLineCap();
    @Property(selector = "setLineCap:")
    public native void setLineCap(CGLineCap v);
    @Property(selector = "lineJoin")
    public native CGLineJoin getLineJoin();
    @Property(selector = "setLineJoin:")
    public native void setLineJoin(CGLineJoin v);
    @Property(selector = "miterLimit")
    public native @MachineSizedFloat double getMiterLimit();
    @Property(selector = "setMiterLimit:")
    public native void setMiterLimit(@MachineSizedFloat double v);
    @Property(selector = "lineLength")
    public native @MachineSizedFloat double getLineLength();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fillTexture")
    public native SKTexture getFillTexture();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFillTexture:")
    public native void setFillTexture(SKTexture v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "fillShader")
    public native SKShader getFillShader();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFillShader:")
    public native void setFillShader(SKShader v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "strokeTexture")
    public native SKTexture getStrokeTexture();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setStrokeTexture:")
    public native void setStrokeTexture(SKTexture v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "strokeShader")
    public native SKShader getStrokeShader();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setStrokeShader:")
    public native void setStrokeShader(SKShader v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithPath:")
    public static native SKShapeNode createPath(CGPath path);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithPath:centered:")
    public static native SKShapeNode createPath(CGPath path, boolean centered);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithRect:")
    public static native SKShapeNode createRect(@ByVal CGRect rect);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithRectOfSize:")
    public static native SKShapeNode createRect(@ByVal CGSize size);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithRect:cornerRadius:")
    public static native SKShapeNode createRect(@ByVal CGRect rect, @MachineSizedFloat double cornerRadius);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithRectOfSize:cornerRadius:")
    public static native SKShapeNode createRect(@ByVal CGSize size, @MachineSizedFloat double cornerRadius);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithCircleOfRadius:")
    public static native SKShapeNode createCircle(@MachineSizedFloat double radius);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithEllipseInRect:")
    public static native SKShapeNode createEllipse(@ByVal CGRect rect);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithEllipseOfSize:")
    public static native SKShapeNode createEllipse(@ByVal CGSize size);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithPoints:count:")
    public static native SKShapeNode createWithPoints(CGPoint points, @MachineSizedUInt long numPoints);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "shapeNodeWithSplinePoints:count:")
    public static native SKShapeNode createWithSplinePoints(CGPoint points, @MachineSizedUInt long numPoints);
    /*</methods>*/
}
