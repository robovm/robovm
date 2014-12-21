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
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAMetalLayer/*</name>*/ 
    extends /*<extends>*/CALayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAMetalLayerPtr extends Ptr<CAMetalLayer, CAMetalLayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CAMetalLayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAMetalLayer() {}
    protected CAMetalLayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "device")
    public native MTLDevice getDevice();
    @Property(selector = "setDevice:")
    public native void setDevice(MTLDevice v);
    @Property(selector = "pixelFormat")
    public native MTLPixelFormat getPixelFormat();
    @Property(selector = "setPixelFormat:")
    public native void setPixelFormat(MTLPixelFormat v);
    @Property(selector = "framebufferOnly")
    public native boolean isFramebufferOnly();
    @Property(selector = "setFramebufferOnly:")
    public native void setFramebufferOnly(boolean v);
    @Property(selector = "drawableSize")
    public native @ByVal CGSize getDrawableSize();
    @Property(selector = "setDrawableSize:")
    public native void setDrawableSize(@ByVal CGSize v);
    @Property(selector = "presentsWithTransaction")
    public native boolean isPresentsWithTransaction();
    @Property(selector = "setPresentsWithTransaction:")
    public native void setPresentsWithTransaction(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "nextDrawable")
    public native CAMetalDrawable nextDrawable();
    @Method(selector = "newDrawable")
    public native CAMetalDrawable newDrawable();
    /*</methods>*/
}
