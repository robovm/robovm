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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTimeCodeFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMTimeCodeFormatDescriptionPtr extends Ptr<CMTimeCodeFormatDescription, CMTimeCodeFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMTimeCodeFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMTimeCodeFormatDescription create(CMTimeCodeFormatType timeCodeFormatType, @ByVal CMTime frameDuration, int frameQuanta, int tcFlags, NSDictionary<NSString, ?> extensions) {
        CMTimeCodeFormatDescriptionPtr ptr = new CMTimeCodeFormatDescriptionPtr();
        create(null, timeCodeFormatType, frameDuration, frameQuanta, tcFlags, extensions, ptr);
        return (CMTimeCodeFormatDescription)ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionCreate", optional=true)
    protected static native int create(CFAllocator allocator, CMTimeCodeFormatType timeCodeFormatType, @ByVal CMTime frameDuration, int frameQuanta, int tcFlags, NSDictionary<NSString, ?> extensions, CMTimeCodeFormatDescription.CMTimeCodeFormatDescriptionPtr descOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionGetFrameDuration", optional=true)
    public native @ByVal CMTime getFrameDuration();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionGetFrameQuanta", optional=true)
    public native int getFrameQuanta();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionGetTimeCodeFlags", optional=true)
    public native CMTimeCodeFlag getTimeCodeFlags();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionCreateFromBigEndianTimeCodeDescriptionData", optional=true)
    public static native int createFromBigEndianTimeCodeDescriptionData(CFAllocator allocator, BytePtr timeCodeDescriptionData, @MachineSizedUInt long timeCodeDescriptionSize, String timeCodeDescriptionFlavor, CMTimeCodeFormatDescription.CMTimeCodeFormatDescriptionPtr timeCodeFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionCreateFromBigEndianTimeCodeDescriptionBlockBuffer", optional=true)
    public static native int createFromBigEndianTimeCodeDescriptionBlockBuffer(CFAllocator allocator, CMBlockBuffer timeCodeDescriptionBlockBuffer, String timeCodeDescriptionFlavor, CMTimeCodeFormatDescription.CMTimeCodeFormatDescriptionPtr timeCodeFormatDescriptionOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CMTimeCodeFormatDescriptionCopyAsBigEndianTimeCodeDescriptionBlockBuffer", optional=true)
    public static native int copyAsBigEndianTimeCodeDescriptionBlockBuffer(CFAllocator allocator, CMTimeCodeFormatDescription timeCodeFormatDescription, String timeCodeDescriptionFlavor, CMBlockBuffer.CMBlockBufferPtr timeCodeDescriptionBlockBufferOut);
    /*</methods>*/
}
