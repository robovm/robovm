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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMuxedFormatDescription/*</name>*/ 
    extends /*<extends>*/CMFormatDescription/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMMuxedFormatDescriptionPtr extends Ptr<CMMuxedFormatDescription, CMMuxedFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMMuxedFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CMMuxedFormatDescription create(CMMuxedStreamType muxType, CMVideoFormatDescriptionExtension extensions) {
        CMMuxedFormatDescription.CMMuxedFormatDescriptionPtr ptr = new CMMuxedFormatDescription.CMMuxedFormatDescriptionPtr();
        create(null, muxType, extensions, ptr);
        return ptr.get();
    }
    
    public CMMuxedStreamType getMuxedStreamType() {
        return CMMuxedStreamType.valueOf(getMediaSubType());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMMuxedFormatDescriptionCreate", optional=true)
    private static native int create(CFAllocator allocator, CMMuxedStreamType muxType, CMVideoFormatDescriptionExtension extensions, CMMuxedFormatDescription.CMMuxedFormatDescriptionPtr outDesc);
    /*</methods>*/
}
