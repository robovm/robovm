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
import org.robovm.rt.annotation.*;
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
/*<annotations>*/@Library("Metal") @NativeProtocolProxy/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/MTLComputeCommandEncoder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MTLCommandEncoder/*</implements>*/ {

    /*<ptr>*/public static class MTLComputeCommandEncoderPtr extends Ptr<MTLComputeCommandEncoder, MTLComputeCommandEncoderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLComputeCommandEncoder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "device")
    public native MTLDevice getDevice();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.3 and later.
     */
    public void setBytes(byte[] bytes, long index) {
        setBytes(VM.getArrayValuesAddress(bytes), bytes.length, index);
    }
    public void setBuffers(MTLBuffer[] buffers, long[] offsets, @ByVal NSRange range) {
        MTLBuffer.MTLBufferPtr bptr = new MTLBuffer.MTLBufferPtr();
        bptr.set(buffers);
        MachineSizedUIntPtr lptr = new MachineSizedUIntPtr();
        lptr.set(offsets);
        setBuffers(bptr, lptr, range);
    }
    public void setTextures(MTLTexture[] textures, @ByVal NSRange range) {
        MTLTexture.MTLTexturePtr ptr = new MTLTexture.MTLTexturePtr();
        ptr.set(textures);
        setTextures(ptr, range);
    }
    public void setSamplerStates(MTLSamplerState[] samplers, @ByVal NSRange range) {
        MTLSamplerState.MTLSamplerStatePtr ptr = new MTLSamplerState.MTLSamplerStatePtr();
        ptr.set(samplers);
        setSamplerStates(ptr, range);
    }
    public void setSamplerStates(MTLSamplerState[] samplers, float[] lodMinClamps, float[] lodMaxClamps, @ByVal NSRange range) {
        MTLSamplerState.MTLSamplerStatePtr sptr = new MTLSamplerState.MTLSamplerStatePtr();
        sptr.set(samplers);
        FloatPtr minptr = new FloatPtr();
        minptr.set(lodMinClamps);
        FloatPtr maxptr = new FloatPtr();
        maxptr.set(lodMaxClamps);
        setSamplerStates(sptr, minptr, maxptr, range);
    }
    
    /*<methods>*/
    @Method(selector = "setComputePipelineState:")
    public native void setComputePipelineState(MTLComputePipelineState state);
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Method(selector = "setBytes:length:atIndex:")
    protected native void setBytes(@Pointer long bytes, @MachineSizedUInt long length, @MachineSizedUInt long index);
    @Method(selector = "setBuffer:offset:atIndex:")
    public native void setBuffer(MTLBuffer buffer, @MachineSizedUInt long offset, @MachineSizedUInt long index);
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Method(selector = "setBufferOffset:atIndex:")
    public native void setBufferOffset(@MachineSizedUInt long offset, @MachineSizedUInt long index);
    @Method(selector = "setBuffers:offsets:withRange:")
    protected native void setBuffers(MTLBuffer.MTLBufferPtr buffers, MachineSizedUIntPtr offsets, @ByVal NSRange range);
    @Method(selector = "setTexture:atIndex:")
    public native void setTexture(MTLTexture texture, @MachineSizedUInt long index);
    @Method(selector = "setTextures:withRange:")
    protected native void setTextures(MTLTexture.MTLTexturePtr textures, @ByVal NSRange range);
    @Method(selector = "setSamplerState:atIndex:")
    public native void setSamplerState(MTLSamplerState sampler, @MachineSizedUInt long index);
    @Method(selector = "setSamplerStates:withRange:")
    protected native void setSamplerStates(MTLSamplerState.MTLSamplerStatePtr samplers, @ByVal NSRange range);
    @Method(selector = "setSamplerState:lodMinClamp:lodMaxClamp:atIndex:")
    public native void setSamplerState(MTLSamplerState sampler, float lodMinClamp, float lodMaxClamp, @MachineSizedUInt long index);
    @Method(selector = "setSamplerStates:lodMinClamps:lodMaxClamps:withRange:")
    protected native void setSamplerStates(MTLSamplerState.MTLSamplerStatePtr samplers, FloatPtr lodMinClamps, FloatPtr lodMaxClamps, @ByVal NSRange range);
    @Method(selector = "setThreadgroupMemoryLength:atIndex:")
    public native void setThreadgroupMemoryLength(@MachineSizedUInt long length, @MachineSizedUInt long index);
    @Method(selector = "dispatchThreadgroups:threadsPerThreadgroup:")
    public native void dispatchThreadgroups(@ByVal MTLSize threadgroupsPerGrid, @ByVal MTLSize threadsPerThreadgroup);
    @Method(selector = "endEncoding")
    public native void endEncoding();
    @Method(selector = "insertDebugSignpost:")
    public native void insertDebugSignpost(String string);
    @Method(selector = "pushDebugGroup:")
    public native void pushDebugGroup(String string);
    @Method(selector = "popDebugGroup")
    public native void popDebugGroup();
    /*</methods>*/
}
