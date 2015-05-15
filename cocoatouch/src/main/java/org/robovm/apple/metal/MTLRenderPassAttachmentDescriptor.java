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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLRenderPassAttachmentDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLRenderPassAttachmentDescriptorPtr extends Ptr<MTLRenderPassAttachmentDescriptor, MTLRenderPassAttachmentDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLRenderPassAttachmentDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLRenderPassAttachmentDescriptor() {}
    protected MTLRenderPassAttachmentDescriptor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "texture")
    public native MTLTexture getTexture();
    @Property(selector = "setTexture:")
    public native void setTexture(MTLTexture v);
    @Property(selector = "level")
    public native @MachineSizedUInt long getLevel();
    @Property(selector = "setLevel:")
    public native void setLevel(@MachineSizedUInt long v);
    @Property(selector = "slice")
    public native @MachineSizedUInt long getSlice();
    @Property(selector = "setSlice:")
    public native void setSlice(@MachineSizedUInt long v);
    @Property(selector = "depthPlane")
    public native @MachineSizedUInt long getDepthPlane();
    @Property(selector = "setDepthPlane:")
    public native void setDepthPlane(@MachineSizedUInt long v);
    @Property(selector = "resolveTexture")
    public native MTLTexture getResolveTexture();
    @Property(selector = "setResolveTexture:")
    public native void setResolveTexture(MTLTexture v);
    @Property(selector = "resolveLevel")
    public native @MachineSizedUInt long getResolveLevel();
    @Property(selector = "setResolveLevel:")
    public native void setResolveLevel(@MachineSizedUInt long v);
    @Property(selector = "resolveSlice")
    public native @MachineSizedUInt long getResolveSlice();
    @Property(selector = "setResolveSlice:")
    public native void setResolveSlice(@MachineSizedUInt long v);
    @Property(selector = "resolveDepthPlane")
    public native @MachineSizedUInt long getResolveDepthPlane();
    @Property(selector = "setResolveDepthPlane:")
    public native void setResolveDepthPlane(@MachineSizedUInt long v);
    @Property(selector = "loadAction")
    public native MTLLoadAction getLoadAction();
    @Property(selector = "setLoadAction:")
    public native void setLoadAction(MTLLoadAction v);
    @Property(selector = "storeAction")
    public native MTLStoreAction getStoreAction();
    @Property(selector = "setStoreAction:")
    public native void setStoreAction(MTLStoreAction v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
