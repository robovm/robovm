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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAEmitterLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAEmitterLayerPtr extends Ptr<CAEmitterLayer, CAEmitterLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAEmitterLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAEmitterLayer() {}
    protected CAEmitterLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "emitterCells")
    public native NSArray<?> getEmitterCells();
    @Property(selector = "setEmitterCells:")
    public native void setEmitterCells(NSArray<?> v);
    @Property(selector = "birthRate")
    public native float getBirthRate();
    @Property(selector = "setBirthRate:")
    public native void setBirthRate(float v);
    @Property(selector = "lifetime")
    public native float getLifetime();
    @Property(selector = "setLifetime:")
    public native void setLifetime(float v);
    @Property(selector = "emitterPosition")
    public native @ByVal CGPoint getEmitterPosition();
    @Property(selector = "setEmitterPosition:")
    public native void setEmitterPosition(@ByVal CGPoint v);
    @Property(selector = "emitterZPosition")
    public native @MachineSizedFloat double getEmitterZPosition();
    @Property(selector = "setEmitterZPosition:")
    public native void setEmitterZPosition(@MachineSizedFloat double v);
    @Property(selector = "emitterSize")
    public native @ByVal CGSize getEmitterSize();
    @Property(selector = "setEmitterSize:")
    public native void setEmitterSize(@ByVal CGSize v);
    @Property(selector = "emitterDepth")
    public native @MachineSizedFloat double getEmitterDepth();
    @Property(selector = "setEmitterDepth:")
    public native void setEmitterDepth(@MachineSizedFloat double v);
    @Property(selector = "emitterShape")
    public native String getEmitterShape();
    @Property(selector = "setEmitterShape:")
    public native void setEmitterShape(String v);
    @Property(selector = "emitterMode")
    public native String getEmitterMode();
    @Property(selector = "setEmitterMode:")
    public native void setEmitterMode(String v);
    @Property(selector = "renderMode")
    public native String getRenderMode();
    @Property(selector = "setRenderMode:")
    public native void setRenderMode(String v);
    @Property(selector = "preservesDepth")
    public native boolean isPreservesDepth();
    @Property(selector = "setPreservesDepth:")
    public native void setPreservesDepth(boolean v);
    @Property(selector = "velocity")
    public native float getVelocity();
    @Property(selector = "setVelocity:")
    public native void setVelocity(float v);
    @Property(selector = "scale")
    public native float getScale();
    @Property(selector = "setScale:")
    public native void setScale(float v);
    @Property(selector = "spin")
    public native float getSpin();
    @Property(selector = "setSpin:")
    public native void setSpin(float v);
    @Property(selector = "seed")
    public native int getSeed();
    @Property(selector = "setSeed:")
    public native void setSeed(int v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoint", optional=true)
    public static native String ShapePoint();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerLine", optional=true)
    public static native String ShapeLine();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerRectangle", optional=true)
    public static native String ShapeRectangle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCuboid", optional=true)
    public static native String ShapeCuboid();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerCircle", optional=true)
    public static native String ShapeCircle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSphere", optional=true)
    public static native String ShapeSphere();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerPoints", optional=true)
    public static native String ModePoints();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOutline", optional=true)
    public static native String ModeOutline();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerSurface", optional=true)
    public static native String ModeSurface();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerVolume", optional=true)
    public static native String ModeVolume();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerUnordered", optional=true)
    public static native String RenderUnordered();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestFirst", optional=true)
    public static native String RenderOldestFirst();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerOldestLast", optional=true)
    public static native String RenderOldestLast();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerBackToFront", optional=true)
    public static native String RenderBackToFront();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCAEmitterLayerAdditive", optional=true)
    public static native String RenderAdditive();
    
    
    /*</methods>*/
}
