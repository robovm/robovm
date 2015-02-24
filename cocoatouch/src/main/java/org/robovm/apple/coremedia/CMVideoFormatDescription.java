/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMVideoFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMVideoFormatDescriptionPtr extends Ptr<CMVideoFormatDescription, CMVideoFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMVideoFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CMVideoFormatDescription create(CMVideoCodecType codecType, int width, int height, CMVideoFormatDescriptionExtension extensions) {
        CMVideoFormatDescription.CMVideoFormatDescriptionPtr ptr = new CMVideoFormatDescription.CMVideoFormatDescriptionPtr();
        create(null, codecType, width, height, extensions, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CMVideoFormatDescription create(CVImageBuffer imageBuffer) {
        CMVideoFormatDescription.CMVideoFormatDescriptionPtr ptr = new CMVideoFormatDescription.CMVideoFormatDescriptionPtr();
        create(null, imageBuffer, ptr);
        return ptr.get();
    }

    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMVideoFormatDescription createFromBigEndianImageDescriptionData(BytePtr imageDescriptionData, @MachineSizedUInt long imageDescriptionSize, int imageDescriptionStringEncoding, String imageDescriptionFlavor) {
        CMVideoFormatDescription.CMVideoFormatDescriptionPtr ptr = new CMVideoFormatDescription.CMVideoFormatDescriptionPtr();
        createFromBigEndianImageDescriptionData(null, imageDescriptionData, imageDescriptionSize, imageDescriptionStringEncoding, imageDescriptionFlavor, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMVideoFormatDescription createFromBigEndianImageDescriptionBlockBuffer(CMBlockBuffer imageDescriptionBlockBuffer, int imageDescriptionStringEncoding, String imageDescriptionFlavor) {
        CMVideoFormatDescription.CMVideoFormatDescriptionPtr ptr = new CMVideoFormatDescription.CMVideoFormatDescriptionPtr();
        createFromBigEndianImageDescriptionBlockBuffer(null, imageDescriptionBlockBuffer, imageDescriptionStringEncoding, imageDescriptionFlavor, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMBlockBuffer copyAsBigEndianImageDescriptionBlockBuffer(CFAllocator allocator, CMVideoFormatDescription videoFormatDescription, int imageDescriptionStringEncoding, String imageDescriptionFlavor) {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        copyAsBigEndianImageDescriptionBlockBuffer(null, videoFormatDescription, imageDescriptionStringEncoding, imageDescriptionFlavor, ptr);
        return ptr.get();
    }

    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionCreate", optional=true)
    private static native OSStatus create(CFAllocator allocator, CMVideoCodecType codecType, int width, int height, CMVideoFormatDescriptionExtension extensions, CMVideoFormatDescription.CMVideoFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionCreateForImageBuffer", optional=true)
    private static native OSStatus create(CFAllocator allocator, CVImageBuffer imageBuffer, CMVideoFormatDescription.CMVideoFormatDescriptionPtr outDesc);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionGetDimensions", optional=true)
    public native @ByVal CMVideoDimensions getDimensions();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionGetPresentationDimensions", optional=true)
    public native @ByVal CGSize getPresentationDimensions(boolean usePixelAspectRatio, boolean useCleanAperture);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionGetCleanAperture", optional=true)
    public native @ByVal CGRect getCleanAperture(boolean originIsAtTopLeft);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionGetExtensionKeysCommonWithImageBuffers", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getExtensionKeysCommonWithImageBuffers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionMatchesImageBuffer", optional=true)
    public native boolean matchesImageBuffer(CVImageBuffer imageBuffer);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionCreateFromBigEndianImageDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianImageDescriptionData(CFAllocator allocator, BytePtr imageDescriptionData, @MachineSizedUInt long imageDescriptionSize, int imageDescriptionStringEncoding, String imageDescriptionFlavor, CMVideoFormatDescription.CMVideoFormatDescriptionPtr videoFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionCreateFromBigEndianImageDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianImageDescriptionBlockBuffer(CFAllocator allocator, CMBlockBuffer imageDescriptionBlockBuffer, int imageDescriptionStringEncoding, String imageDescriptionFlavor, CMVideoFormatDescription.CMVideoFormatDescriptionPtr videoFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMVideoFormatDescriptionCopyAsBigEndianImageDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianImageDescriptionBlockBuffer(CFAllocator allocator, CMVideoFormatDescription videoFormatDescription, int imageDescriptionStringEncoding, String imageDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr imageDescriptionBlockBufferOut);
    /*</methods>*/
}
