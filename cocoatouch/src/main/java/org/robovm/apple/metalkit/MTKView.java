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
package org.robovm.apple.metalkit;

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
import org.robovm.apple.metal.*;
import org.robovm.apple.modelio.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MetalKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTKView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class MTKViewPtr extends Ptr<MTKView, MTKViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTKView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTKView() {}
    protected MTKView(SkipInit skipInit) { super(skipInit); }
    public MTKView(@ByVal CGRect frameRect, MTLDevice device) { super((SkipInit) null); initObject(init(frameRect, device)); }
    public MTKView(NSCoder coder) { super((SkipInit) null); initObject(init(coder)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native MTKViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(MTKViewDelegate v);
    @Property(selector = "device")
    public native MTLDevice getDevice();
    @Property(selector = "setDevice:")
    public native void setDevice(MTLDevice v);
    @Property(selector = "currentDrawable")
    public native CAMetalDrawable getCurrentDrawable();
    @Property(selector = "framebufferOnly")
    public native boolean isFramebufferOnly();
    @Property(selector = "setFramebufferOnly:")
    public native void setFramebufferOnly(boolean v);
    @Property(selector = "presentsWithTransaction")
    public native boolean presentsWithTransaction();
    @Property(selector = "setPresentsWithTransaction:")
    public native void setPresentsWithTransaction(boolean v);
    @Property(selector = "colorPixelFormat")
    public native MTLPixelFormat getColorPixelFormat();
    @Property(selector = "setColorPixelFormat:")
    public native void setColorPixelFormat(MTLPixelFormat v);
    @Property(selector = "depthStencilPixelFormat")
    public native MTLPixelFormat getDepthStencilPixelFormat();
    @Property(selector = "setDepthStencilPixelFormat:")
    public native void setDepthStencilPixelFormat(MTLPixelFormat v);
    @Property(selector = "sampleCount")
    public native @MachineSizedUInt long getSampleCount();
    @Property(selector = "setSampleCount:")
    public native void setSampleCount(@MachineSizedUInt long v);
    @Property(selector = "clearColor")
    public native @ByVal MTLClearColor getClearColor();
    @Property(selector = "setClearColor:")
    public native void setClearColor(@ByVal MTLClearColor v);
    @Property(selector = "clearDepth")
    public native double getClearDepth();
    @Property(selector = "setClearDepth:")
    public native void setClearDepth(double v);
    @Property(selector = "clearStencil")
    public native int getClearStencil();
    @Property(selector = "setClearStencil:")
    public native void setClearStencil(int v);
    @Property(selector = "depthStencilTexture")
    public native MTLTexture getDepthStencilTexture();
    @Property(selector = "multisampleColorTexture")
    public native MTLTexture getMultisampleColorTexture();
    @Property(selector = "currentRenderPassDescriptor")
    public native MTLRenderPassDescriptor getCurrentRenderPassDescriptor();
    @Property(selector = "preferredFramesPerSecond")
    public native @MachineSizedSInt long getPreferredFramesPerSecond();
    @Property(selector = "setPreferredFramesPerSecond:")
    public native void setPreferredFramesPerSecond(@MachineSizedSInt long v);
    @Property(selector = "enableSetNeedsDisplay")
    public native boolean isNeedsDisplayEnabled();
    @Property(selector = "setEnableSetNeedsDisplay:")
    public native void setNeedsDisplayEnabled(boolean v);
    @Property(selector = "autoResizeDrawable")
    public native boolean isAutoResizeDrawable();
    @Property(selector = "setAutoResizeDrawable:")
    public native void setAutoResizeDrawable(boolean v);
    @Property(selector = "drawableSize")
    public native @ByVal CGSize getDrawableSize();
    @Property(selector = "setDrawableSize:")
    public native void setDrawableSize(@ByVal CGSize v);
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:device:")
    protected native @Pointer long init(@ByVal CGRect frameRect, MTLDevice device);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder coder);
    @Method(selector = "releaseDrawables")
    public native void releaseDrawables();
    @Method(selector = "draw")
    public native void draw();
    /*</methods>*/
}
