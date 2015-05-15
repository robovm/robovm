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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMBlockBuffer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMBlockBufferPtr extends Ptr<CMBlockBuffer, CMBlockBufferPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMBlockBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMBlockBuffer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMBlockBuffer create(int subBlockCapacity, CMBlockBufferFlags flags) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = create0(null, subBlockCapacity, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMBlockBuffer create(VoidPtr memoryBlock, @MachineSizedUInt long blockLength, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = create0(null, memoryBlock, blockLength, blockAllocator, customBlockSource, offsetToData, dataLength, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMBlockBuffer create(CMBlockBuffer targetBuffer, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = create0(null, targetBuffer, offsetToData, dataLength, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMBlockBuffer create(CMBlockBuffer sourceBuffer, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = create0(null, sourceBuffer, blockAllocator, customBlockSource, offsetToData, dataLength, flags, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void appendMemoryBlock(VoidPtr memoryBlock, long blockLength, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, long offsetToData, long dataLength, CMBlockBufferFlags flags) throws OSStatusException {
        OSStatus status = appendMemoryBlock0(memoryBlock, blockLength, blockAllocator, customBlockSource, offsetToData, dataLength, flags);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void appendBufferReference(CMBlockBuffer targetBBuf, long offsetToData, long dataLength, CMBlockBufferFlags flags) throws OSStatusException {
        OSStatus status = appendBufferReference0(targetBBuf, offsetToData, dataLength, flags);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void assureBlockMemory() throws OSStatusException {
        OSStatus status = assureBlockMemory0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public BytePtr accessDataBytes(long offset, @MachineSizedUInt long length, VoidPtr temporaryBlock) throws OSStatusException {
        BytePtr.BytePtrPtr ptr = new BytePtr.BytePtrPtr();
        OSStatus status = accessDataBytes0(this, offset, length, temporaryBlock, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void copyDataBytes(long offsetToData, long dataLength, VoidPtr destination) throws OSStatusException {
        OSStatus status = copyDataBytes0(offsetToData, dataLength, destination);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void replaceDataBytes(VoidPtr sourceBytes, long offsetIntoDestination, long dataLength) throws OSStatusException {
        OSStatus status = replaceDataBytes0(sourceBytes, this, offsetIntoDestination, dataLength);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public void fillDataBytes(byte fillByte, long offsetIntoDestination, long dataLength) throws OSStatusException {
        OSStatus status = fillDataBytes0(fillByte, this, offsetIntoDestination, dataLength);
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferCreateEmpty", optional=true)
    protected static native OSStatus create0(CFAllocator structureAllocator, int subBlockCapacity, CMBlockBufferFlags flags, CMBlockBuffer.CMBlockBufferPtr newBBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferCreateWithMemoryBlock", optional=true)
    protected static native OSStatus create0(CFAllocator structureAllocator, VoidPtr memoryBlock, @MachineSizedUInt long blockLength, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags, CMBlockBuffer.CMBlockBufferPtr newBBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferCreateWithBufferReference", optional=true)
    protected static native OSStatus create0(CFAllocator structureAllocator, CMBlockBuffer targetBuffer, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags, CMBlockBuffer.CMBlockBufferPtr newBBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferCreateContiguous", optional=true)
    protected static native OSStatus create0(CFAllocator structureAllocator, CMBlockBuffer sourceBuffer, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags, CMBlockBuffer.CMBlockBufferPtr newBBufOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferAppendMemoryBlock", optional=true)
    protected native OSStatus appendMemoryBlock0(VoidPtr memoryBlock, @MachineSizedUInt long blockLength, CFAllocator blockAllocator, CMBlockBufferCustomBlockSource customBlockSource, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferAppendBufferReference", optional=true)
    protected native OSStatus appendBufferReference0(CMBlockBuffer targetBBuf, @MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, CMBlockBufferFlags flags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferAssureBlockMemory", optional=true)
    protected native OSStatus assureBlockMemory0();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferAccessDataBytes", optional=true)
    protected static native OSStatus accessDataBytes0(CMBlockBuffer theBuffer, @MachineSizedUInt long offset, @MachineSizedUInt long length, VoidPtr temporaryBlock, BytePtr.BytePtrPtr returnedPointer);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferCopyDataBytes", optional=true)
    protected native OSStatus copyDataBytes0(@MachineSizedUInt long offsetToData, @MachineSizedUInt long dataLength, VoidPtr destination);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferReplaceDataBytes", optional=true)
    protected static native OSStatus replaceDataBytes0(VoidPtr sourceBytes, CMBlockBuffer destinationBuffer, @MachineSizedUInt long offsetIntoDestination, @MachineSizedUInt long dataLength);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferFillDataBytes", optional=true)
    protected static native OSStatus fillDataBytes0(byte fillByte, CMBlockBuffer destinationBuffer, @MachineSizedUInt long offsetIntoDestination, @MachineSizedUInt long dataLength);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferGetDataPointer", optional=true)
    protected native OSStatus getDataPointer0(@MachineSizedUInt long offset, MachineSizedUIntPtr lengthAtOffset, MachineSizedUIntPtr totalLength, BytePtr.BytePtrPtr dataPointer);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferGetDataLength", optional=true)
    public native @MachineSizedUInt long getDataLength();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferIsRangeContiguous", optional=true)
    public native boolean isRangeContiguous(@MachineSizedUInt long offset, @MachineSizedUInt long length);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMBlockBufferIsEmpty", optional=true)
    public native boolean isEmpty();
    /*</methods>*/
}
