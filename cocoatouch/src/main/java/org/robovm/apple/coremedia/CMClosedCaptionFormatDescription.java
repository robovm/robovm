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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMClosedCaptionFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMClosedCaptionFormatDescriptionPtr extends Ptr<CMClosedCaptionFormatDescription, CMClosedCaptionFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMClosedCaptionFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMClosedCaptionFormatDescription create(CMMediaType mediaType, CMClosedCaptionFormatType formatType, NSDictionary<NSString, ?> extensions) throws OSStatusException {
        CMFormatDescriptionPtr ptr = new CMFormatDescriptionPtr();
        OSStatus status = create0(null, mediaType, (int)formatType.value(), extensions.as(CFDictionary.class), ptr);
        if (OSStatusException.throwIfNecessary(status)) {
            return ptr.get().as(CMClosedCaptionFormatDescription.class);
        }
        return null;
    }
    public CMClosedCaptionFormatType getFormatType() {
        return CMClosedCaptionFormatType.valueOf(getMediaSubType());
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMClosedCaptionFormatDescription createFromBigEndianClosedCaptionDescriptionData(BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize, String closedCaptionDescriptionFlavor) throws OSStatusException {
        CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr ptr = new CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr();
        OSStatus status = createFromBigEndianClosedCaptionDescriptionData0(null, closedCaptionDescriptionData, closedCaptionDescriptionSize, closedCaptionDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public static CMClosedCaptionFormatDescription createFromBigEndianClosedCaptionDescriptionBlockBuffer(CMBlockBuffer closedCaptionDescriptionBlockBuffer, String closedCaptionDescriptionFlavor) throws OSStatusException {
        CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr ptr = new CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr();
        OSStatus status = createFromBigEndianClosedCaptionDescriptionBlockBuffer0(null, closedCaptionDescriptionBlockBuffer, closedCaptionDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 8.0 and later.
     */
    public CMBlockBuffer copyAsBigEndianClosedCaptionDescriptionBlockBuffer(String closedCaptionDescriptionFlavor) throws OSStatusException {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        OSStatus status = copyAsBigEndianClosedCaptionDescriptionBlockBuffer0(null, this, closedCaptionDescriptionFlavor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianClosedCaptionDescriptionData0(CFAllocator allocator, BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize, String closedCaptionDescriptionFlavor, CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianClosedCaptionDescriptionBlockBuffer0(CFAllocator allocator, CMBlockBuffer closedCaptionDescriptionBlockBuffer, String closedCaptionDescriptionFlavor, CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCopyAsBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianClosedCaptionDescriptionBlockBuffer0(CFAllocator allocator, CMClosedCaptionFormatDescription closedCaptionFormatDescription, String closedCaptionDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr closedCaptionDescriptionBlockBufferOut);
    /*</methods>*/
}
