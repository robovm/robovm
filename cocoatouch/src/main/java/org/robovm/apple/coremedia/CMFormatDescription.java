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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMFormatDescription/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMFormatDescriptionPtr extends Ptr<CMFormatDescription, CMFormatDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CMFormatDescription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CMFormatDescription() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CMFormatDescription create(CMMediaType mediaType, int mediaSubtype, NSDictionary<?, ?> extensions) {
        CMFormatDescriptionPtr ptr = new CMFormatDescriptionPtr();
        create(null, mediaType, mediaSubtype, extensions, ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionCreate", optional=true)
    protected static native int create(CFAllocator allocator, CMMediaType mediaType, int mediaSubtype, NSDictionary<?, ?> extensions, CMFormatDescription.CMFormatDescriptionPtr descOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionEqual", optional=true)
    public native boolean equals(CMFormatDescription desc2);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Bridge(symbol="CMFormatDescriptionEqualIgnoringExtensionKeys", optional=true)
    public native boolean equals(CMFormatDescription desc2, CFType formatDescriptionExtensionKeysToIgnore, CFType sampleDescriptionExtensionAtomKeysToIgnore);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionGetMediaType", optional=true)
    public native CMMediaType getMediaType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionGetMediaSubType", optional=true)
    public native int getMediaSubType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionGetExtensions", optional=true)
    public native NSDictionary<?, ?> getExtensions();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CMFormatDescriptionGetExtension", optional=true)
    public native CFType getExtension(String extensionKey);
    /*</methods>*/
}
