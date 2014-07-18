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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVBuffer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CVBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFType getAttachment(String key) {
        return getAttachment(key, null);
    }
    
    public CVAttachmentMode getAttachmentMode(String key) {
        IntPtr ptr = new IntPtr();
        getAttachment(key, ptr);
        return CVAttachmentMode.valueOf(ptr.get());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferRetain", optional=true)
    public native CVBuffer retain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferRelease", optional=true)
    public native void release();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferSetAttachment", optional=true)
    public native void setAttachment(String key, CFType value, CVAttachmentMode attachmentMode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferGetAttachment", optional=true)
    protected native CFType getAttachment(String key, IntPtr attachmentMode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferRemoveAttachment", optional=true)
    public native void removeAttachment(String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferRemoveAllAttachments", optional=true)
    public native void removeAllAttachments();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferGetAttachments", optional=true)
    public native NSDictionary<NSString, ?> getAttachments(CVAttachmentMode attachmentMode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferSetAttachments", optional=true)
    public native void setAttachments(NSDictionary<NSString, ?> theAttachments, CVAttachmentMode attachmentMode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVBufferPropagateAttachments", optional=true)
    public native void propagateAttachments(CVBuffer destinationBuffer);
    /*</methods>*/
}
