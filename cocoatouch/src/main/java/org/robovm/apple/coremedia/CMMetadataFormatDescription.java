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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMMetadataFormatDescriptionPtr extends Ptr<CMMetadataFormatDescription, CMMetadataFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMMetadataFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 4.0 and later.
     */
    public static CMMetadataFormatDescription createWithKeys(CMMetadataFormatType metadataType, List<CMMetadataFormatDescriptionKey> keys) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = createWithKeys0(null, metadataType, keys, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMMetadataFormatDescription createWithMetadataSpecifications(CMMetadataFormatType metadataType, List<CMMetadataFormatDescriptionMetadataSpecification> metadataSpecifications) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = createWithMetadataSpecifications0(null, metadataType, metadataSpecifications, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMMetadataFormatDescription create(CMMetadataFormatDescription srcDesc, List<CMMetadataFormatDescriptionMetadataSpecification> metadataSpecifications) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = create0(null, srcDesc, metadataSpecifications, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMMetadataFormatDescription createByMergingFormatDescriptions(CMMetadataFormatDescription srcDesc1, CMMetadataFormatDescription srcDesc2) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = createByMergingFormatDescriptions0(null, srcDesc1, srcDesc2, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }

    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMMetadataFormatDescription createFromBigEndianMetadataDescriptionData(BytePtr metadataDescriptionData, @MachineSizedUInt long metadataDescriptionSize, String metadataDescriptionFlavor) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = createFromBigEndianMetadataDescriptionData0(null, metadataDescriptionData, metadataDescriptionSize, metadataDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMMetadataFormatDescription createFromBigEndianMetadataDescriptionBlockBuffer(CMBlockBuffer metadataDescriptionBlockBuffer, String metadataDescriptionFlavor) throws OSStatusException {
        CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr ptr = new CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr();
        OSStatus status = createFromBigEndianMetadataDescriptionBlockBuffer0(null, metadataDescriptionBlockBuffer, metadataDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public CMBlockBuffer copyAsBigEndianMetadataDescriptionBlockBuffer(String metadataDescriptionFlavor) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = copyAsBigEndianMetadataDescriptionBlockBuffer0(null, this, metadataDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateWithKeys", optional=true)
    private static native OSStatus createWithKeys0(CFAllocator allocator, CMMetadataFormatType metadataType, @org.robovm.rt.bro.annotation.Marshaler(CMMetadataFormatDescriptionKey.AsListMarshaler.class) List<CMMetadataFormatDescriptionKey> keys, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateWithMetadataSpecifications", optional=true)
    private static native OSStatus createWithMetadataSpecifications0(CFAllocator allocator, CMMetadataFormatType metadataType, @org.robovm.rt.bro.annotation.Marshaler(CMMetadataFormatDescriptionMetadataSpecification.AsListMarshaler.class) List<CMMetadataFormatDescriptionMetadataSpecification> metadataSpecifications, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateWithMetadataFormatDescriptionAndMetadataSpecifications", optional=true)
    private static native OSStatus create0(CFAllocator allocator, CMMetadataFormatDescription srcDesc, @org.robovm.rt.bro.annotation.Marshaler(CMMetadataFormatDescriptionMetadataSpecification.AsListMarshaler.class) List<CMMetadataFormatDescriptionMetadataSpecification> metadataSpecifications, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateByMergingMetadataFormatDescriptions", optional=true)
    private static native OSStatus createByMergingFormatDescriptions0(CFAllocator allocator, CMMetadataFormatDescription srcDesc1, CMMetadataFormatDescription srcDesc2, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionGetKeyWithLocalID", optional=true)
    public native CMMetadataFormatDescriptionKey getKey(int localKeyID);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionGetIdentifiers", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getIdentifiers();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateFromBigEndianMetadataDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianMetadataDescriptionData0(CFAllocator allocator, BytePtr metadataDescriptionData, @MachineSizedUInt long metadataDescriptionSize, String metadataDescriptionFlavor, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr metadataFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCreateFromBigEndianMetadataDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianMetadataDescriptionBlockBuffer0(CFAllocator allocator, CMBlockBuffer metadataDescriptionBlockBuffer, String metadataDescriptionFlavor, CMMetadataFormatDescription.CMMetadataFormatDescriptionPtr metadataFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMMetadataFormatDescriptionCopyAsBigEndianMetadataDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianMetadataDescriptionBlockBuffer0(CFAllocator allocator, CMFormatDescription metadataFormatDescription, String metadataDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr metadataDescriptionBlockBufferOut);
    /*</methods>*/
}
