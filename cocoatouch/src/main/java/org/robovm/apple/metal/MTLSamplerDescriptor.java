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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLSamplerDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLSamplerDescriptorPtr extends Ptr<MTLSamplerDescriptor, MTLSamplerDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLSamplerDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLSamplerDescriptor() {}
    protected MTLSamplerDescriptor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minFilter")
    public native MTLSamplerMinMagFilter getMinFilter();
    @Property(selector = "setMinFilter:")
    public native void setMinFilter(MTLSamplerMinMagFilter v);
    @Property(selector = "magFilter")
    public native MTLSamplerMinMagFilter getMagFilter();
    @Property(selector = "setMagFilter:")
    public native void setMagFilter(MTLSamplerMinMagFilter v);
    @Property(selector = "mipFilter")
    public native MTLSamplerMipFilter getMipFilter();
    @Property(selector = "setMipFilter:")
    public native void setMipFilter(MTLSamplerMipFilter v);
    @Property(selector = "maxAnisotropy")
    public native @MachineSizedUInt long getMaxAnisotropy();
    @Property(selector = "setMaxAnisotropy:")
    public native void setMaxAnisotropy(@MachineSizedUInt long v);
    @Property(selector = "sAddressMode")
    public native MTLSamplerAddressMode getSAddressMode();
    @Property(selector = "setSAddressMode:")
    public native void setSAddressMode(MTLSamplerAddressMode v);
    @Property(selector = "tAddressMode")
    public native MTLSamplerAddressMode getTAddressMode();
    @Property(selector = "setTAddressMode:")
    public native void setTAddressMode(MTLSamplerAddressMode v);
    @Property(selector = "rAddressMode")
    public native MTLSamplerAddressMode getRAddressMode();
    @Property(selector = "setRAddressMode:")
    public native void setRAddressMode(MTLSamplerAddressMode v);
    @Property(selector = "normalizedCoordinates")
    public native boolean normalizesCoordinates();
    @Property(selector = "setNormalizedCoordinates:")
    public native void setNormalizesCoordinates(boolean v);
    @Property(selector = "lodMinClamp")
    public native float getLodMinClamp();
    @Property(selector = "setLodMinClamp:")
    public native void setLodMinClamp(float v);
    @Property(selector = "lodMaxClamp")
    public native float getLodMaxClamp();
    @Property(selector = "setLodMaxClamp:")
    public native void setLodMaxClamp(float v);
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
