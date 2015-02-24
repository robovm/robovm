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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMClosedCaptionFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMClosedCaptionFormatDescriptionPtr extends Ptr<CMClosedCaptionFormatDescription, CMClosedCaptionFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMClosedCaptionFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMClosedCaptionFormatDescription create(CMMediaType mediaType, CMClosedCaptionFormatType formatType, NSDictionary<NSString, ?> extensions) {
        CMFormatDescriptionPtr ptr = new CMFormatDescriptionPtr();
        create(null, mediaType, (int)formatType.value(), extensions.as(CFDictionary.class), ptr);
        if (ptr.get() != null) {
            return ptr.get().as(CMClosedCaptionFormatDescription.class);
        }
        return null;
    }
    
    public CMClosedCaptionFormatType getFormatType() {
        return CMClosedCaptionFormatType.valueOf(getMediaSubType());
    }
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMClosedCaptionFormatDescription createFromBigEndianClosedCaptionDescriptionData(BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize, String closedCaptionDescriptionFlavor) {
        CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr ptr = new CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr();
        createFromBigEndianClosedCaptionDescriptionData(null, closedCaptionDescriptionData, closedCaptionDescriptionSize, closedCaptionDescriptionFlavor, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CMClosedCaptionFormatDescription createFromBigEndianClosedCaptionDescriptionBlockBuffer(CMBlockBuffer closedCaptionDescriptionBlockBuffer, String closedCaptionDescriptionFlavor) {
        CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr ptr = new CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr();
        createFromBigEndianClosedCaptionDescriptionBlockBuffer(null, closedCaptionDescriptionBlockBuffer, closedCaptionDescriptionFlavor, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CMBlockBuffer copyAsBigEndianClosedCaptionDescriptionBlockBuffer(String closedCaptionDescriptionFlavor) {
        CMBlockBuffer.CMBlockBufferPtr ptr = new CMBlockBuffer.CMBlockBufferPtr();
        copyAsBigEndianClosedCaptionDescriptionBlockBuffer(null, this, closedCaptionDescriptionFlavor, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionData", optional=true)
    private static native OSStatus createFromBigEndianClosedCaptionDescriptionData(CFAllocator allocator, BytePtr closedCaptionDescriptionData, @MachineSizedUInt long closedCaptionDescriptionSize, String closedCaptionDescriptionFlavor, CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCreateFromBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    private static native OSStatus createFromBigEndianClosedCaptionDescriptionBlockBuffer(CFAllocator allocator, CMBlockBuffer closedCaptionDescriptionBlockBuffer, String closedCaptionDescriptionFlavor, CMClosedCaptionFormatDescription.CMClosedCaptionFormatDescriptionPtr closedCaptionFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMClosedCaptionFormatDescriptionCopyAsBigEndianClosedCaptionDescriptionBlockBuffer", optional=true)
    private static native OSStatus copyAsBigEndianClosedCaptionDescriptionBlockBuffer(CFAllocator allocator, CMClosedCaptionFormatDescription closedCaptionFormatDescription, String closedCaptionDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr closedCaptionDescriptionBlockBufferOut);
    /*</methods>*/
}
