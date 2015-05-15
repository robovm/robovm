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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Metal") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLRenderPipelineColorAttachmentDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLRenderPipelineColorAttachmentDescriptorPtr extends Ptr<MTLRenderPipelineColorAttachmentDescriptor, MTLRenderPipelineColorAttachmentDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLRenderPipelineColorAttachmentDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLRenderPipelineColorAttachmentDescriptor() {}
    protected MTLRenderPipelineColorAttachmentDescriptor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "pixelFormat")
    public native MTLPixelFormat getPixelFormat();
    @Property(selector = "setPixelFormat:")
    public native void setPixelFormat(MTLPixelFormat v);
    @Property(selector = "isBlendingEnabled")
    public native boolean isBlendingEnabled();
    @Property(selector = "setBlendingEnabled:")
    public native void setBlendingEnabled(boolean v);
    @Property(selector = "sourceRGBBlendFactor")
    public native MTLBlendFactor getSourceRGBBlendFactor();
    @Property(selector = "setSourceRGBBlendFactor:")
    public native void setSourceRGBBlendFactor(MTLBlendFactor v);
    @Property(selector = "destinationRGBBlendFactor")
    public native MTLBlendFactor getDestinationRGBBlendFactor();
    @Property(selector = "setDestinationRGBBlendFactor:")
    public native void setDestinationRGBBlendFactor(MTLBlendFactor v);
    @Property(selector = "rgbBlendOperation")
    public native MTLBlendOperation getRgbBlendOperation();
    @Property(selector = "setRgbBlendOperation:")
    public native void setRgbBlendOperation(MTLBlendOperation v);
    @Property(selector = "sourceAlphaBlendFactor")
    public native MTLBlendFactor getSourceAlphaBlendFactor();
    @Property(selector = "setSourceAlphaBlendFactor:")
    public native void setSourceAlphaBlendFactor(MTLBlendFactor v);
    @Property(selector = "destinationAlphaBlendFactor")
    public native MTLBlendFactor getDestinationAlphaBlendFactor();
    @Property(selector = "setDestinationAlphaBlendFactor:")
    public native void setDestinationAlphaBlendFactor(MTLBlendFactor v);
    @Property(selector = "alphaBlendOperation")
    public native MTLBlendOperation getAlphaBlendOperation();
    @Property(selector = "setAlphaBlendOperation:")
    public native void setAlphaBlendOperation(MTLBlendOperation v);
    @Property(selector = "writeMask")
    public native MTLColorWriteMask getWriteMask();
    @Property(selector = "setWriteMask:")
    public native void setWriteMask(MTLColorWriteMask v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
