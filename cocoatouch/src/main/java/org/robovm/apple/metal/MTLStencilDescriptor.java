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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLStencilDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLStencilDescriptorPtr extends Ptr<MTLStencilDescriptor, MTLStencilDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLStencilDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLStencilDescriptor() {}
    protected MTLStencilDescriptor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "stencilCompareFunction")
    public native MTLCompareFunction getStencilCompareFunction();
    @Property(selector = "setStencilCompareFunction:")
    public native void setStencilCompareFunction(MTLCompareFunction v);
    @Property(selector = "stencilFailureOperation")
    public native MTLStencilOperation getStencilFailureOperation();
    @Property(selector = "setStencilFailureOperation:")
    public native void setStencilFailureOperation(MTLStencilOperation v);
    @Property(selector = "depthFailureOperation")
    public native MTLStencilOperation getDepthFailureOperation();
    @Property(selector = "setDepthFailureOperation:")
    public native void setDepthFailureOperation(MTLStencilOperation v);
    @Property(selector = "depthStencilPassOperation")
    public native MTLStencilOperation getDepthStencilPassOperation();
    @Property(selector = "setDepthStencilPassOperation:")
    public native void setDepthStencilPassOperation(MTLStencilOperation v);
    @Property(selector = "readMask")
    public native int getReadMask();
    @Property(selector = "setReadMask:")
    public native void setReadMask(int v);
    @Property(selector = "writeMask")
    public native int getWriteMask();
    @Property(selector = "setWriteMask:")
    public native void setWriteMask(int v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
