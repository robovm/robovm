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
/*<annotations>*/@Library("Metal")/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/MTLDevice/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/public static class MTLDevicePtr extends Ptr<MTLDevice, MTLDevicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLDevice.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    /*</properties>*/
    /*<members>*//*</members>*/
    public MTLBuffer newBuffer(byte[] bytes, MTLResourceOptions options) {
        return newBuffer(VM.getArrayValuesAddress(bytes), bytes.length, options);
    }
    public MTLBuffer newBuffer(byte[] bytes, MTLResourceOptions options, final VoidBlock1<byte[]> deallocator) {
        return newBuffer(VM.getArrayValuesAddress(bytes), bytes.length, options, new VoidBlock2<Long, Long>() {
            @Override
            public void invoke(Long a, Long b) {
                byte[] bytes = VM.newByteArray(a, b.intValue());
                deallocator.invoke(bytes);
            }
        });
    }
    /**
     * 
     * @param filepath
     * @return
     * @throws NSErrorException
     */
    public MTLLibrary newLibraryWithFile(String filepath) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLLibrary result = newLibraryWithFile(filepath, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param data
     * @return
     * @throws NSErrorException
     */
    public MTLLibrary newLibraryWithData(DispatchData data) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLLibrary result = newLibraryWithData(data, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param source
     * @param options
     * @return
     * @throws NSErrorException
     */
    public MTLLibrary newLibraryWithSource(String source, MTLCompileOptions options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLLibrary result = newLibraryWithSource(source, options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param descriptor
     * @return
     * @throws NSErrorException
     */
    public MTLRenderPipelineState newRenderPipelineState(MTLRenderPipelineDescriptor descriptor) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLRenderPipelineState result = newRenderPipelineState(descriptor, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param descriptor
     * @param options
     * @return
     * @throws NSErrorException
     */
    public MTLRenderPipelineState newRenderPipelineState(MTLRenderPipelineDescriptor descriptor, MTLPipelineOption options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLRenderPipelineState result = newRenderPipelineState(descriptor, options, null, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param computeFunction
     * @return
     * @throws NSErrorException
     */
    public MTLComputePipelineState newComputePipelineState(MTLFunction computeFunction) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLComputePipelineState result = newComputePipelineState(computeFunction, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param computeFunction
     * @param options
     * @return
     */
    public MTLComputePipelineState newComputePipelineState(MTLFunction computeFunction, MTLPipelineOption options) {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        MTLComputePipelineState result = newComputePipelineState(computeFunction, options, null, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="MTLCreateSystemDefaultDevice", optional=true)
    public static native MTLDevice createSystemDefaultDevice();
    
    @Method(selector = "newCommandQueue")
    public native MTLCommandQueue newCommandQueue();
    @Method(selector = "newCommandQueueWithMaxCommandBufferCount:")
    public native MTLCommandQueue newCommandQueue(@MachineSizedUInt long maxCommandBufferCount);
    @Method(selector = "newBufferWithLength:options:")
    public native MTLBuffer newBuffer(@MachineSizedUInt long length, MTLResourceOptions options);
    @Method(selector = "newBufferWithBytes:length:options:")
    protected native MTLBuffer newBuffer(@Pointer long pointer, @MachineSizedUInt long length, MTLResourceOptions options);
    @Method(selector = "newBufferWithBytesNoCopy:length:options:deallocator:")
    protected native MTLBuffer newBuffer(@Pointer long pointer, @MachineSizedUInt long length, MTLResourceOptions options, @Block("(@Pointer,@MachineSizedUInt)") VoidBlock2<Long, Long> deallocator);
    @Method(selector = "newDepthStencilStateWithDescriptor:")
    public native MTLDepthStencilState newDepthStencilState(MTLDepthStencilDescriptor descriptor);
    @Method(selector = "newTextureWithDescriptor:")
    public native MTLTexture newTexture(MTLTextureDescriptor descriptor);
    @Method(selector = "newSamplerStateWithDescriptor:")
    public native MTLSamplerState newSamplerState(MTLSamplerDescriptor descriptor);
    @Method(selector = "newDefaultLibrary")
    public native MTLLibrary newDefaultLibrary();
    @Method(selector = "newLibraryWithFile:error:")
    protected native MTLLibrary newLibraryWithFile(String filepath, NSError.NSErrorPtr error);
    @Method(selector = "newLibraryWithData:error:")
    protected native MTLLibrary newLibraryWithData(DispatchData data, NSError.NSErrorPtr error);
    @Method(selector = "newLibraryWithSource:options:error:")
    protected native MTLLibrary newLibraryWithSource(String source, MTLCompileOptions options, NSError.NSErrorPtr error);
    @Method(selector = "newLibraryWithSource:options:completionHandler:")
    public native void newLibraryWithSource(String source, MTLCompileOptions options, @Block VoidBlock2<MTLLibrary, NSError> completionHandler);
    @Method(selector = "newRenderPipelineStateWithDescriptor:error:")
    protected native MTLRenderPipelineState newRenderPipelineState(MTLRenderPipelineDescriptor descriptor, NSError.NSErrorPtr error);
    @Method(selector = "newRenderPipelineStateWithDescriptor:options:reflection:error:")
    protected native MTLRenderPipelineState newRenderPipelineState(MTLRenderPipelineDescriptor descriptor, MTLPipelineOption options, MTLRenderPipelineReflection.MTLRenderPipelineReflectionPtr reflection, NSError.NSErrorPtr error);
    @Method(selector = "newRenderPipelineStateWithDescriptor:completionHandler:")
    public native void newRenderPipelineState(MTLRenderPipelineDescriptor descriptor, @Block VoidBlock2<MTLRenderPipelineState, NSError> completionHandler);
    @Method(selector = "newRenderPipelineStateWithDescriptor:options:completionHandler:")
    public native void newRenderPipelineState(MTLRenderPipelineDescriptor descriptor, MTLPipelineOption options, @Block VoidBlock3<MTLRenderPipelineState, MTLRenderPipelineReflection, NSError> completionHandler);
    @Method(selector = "newComputePipelineStateWithFunction:error:")
    protected native MTLComputePipelineState newComputePipelineState(MTLFunction computeFunction, NSError.NSErrorPtr error);
    @Method(selector = "newComputePipelineStateWithFunction:options:reflection:error:")
    protected native MTLComputePipelineState newComputePipelineState(MTLFunction computeFunction, MTLPipelineOption options, MTLComputePipelineReflection.MTLComputePipelineReflectionPtr reflection, NSError.NSErrorPtr error);
    @Method(selector = "newComputePipelineStateWithFunction:completionHandler:")
    public native void newComputePipelineState(MTLFunction computeFunction, @Block VoidBlock2<MTLComputePipelineState, NSError> completionHandler);
    @Method(selector = "newComputePipelineStateWithFunction:options:completionHandler:")
    public native void newComputePipelineState(MTLFunction computeFunction, MTLPipelineOption options, @Block VoidBlock3<MTLComputePipelineState, MTLComputePipelineReflection, NSError> completionHandler);
    @Method(selector = "supportsFeatureSet:")
    public native boolean supportsFeatureSet(MTLFeatureSet featureSet);
    /*</methods>*/
}
