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
package org.robovm.apple.imageio;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageDestination/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGImageDestinationPtr extends Ptr<CGImageDestination, CGImageDestinationPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImageDestination.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGImageDestination() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param isrc
     * @param options
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    public boolean copyImageSource(CGImageSource isrc, CGImageDestinationCopySourceOptions options) throws NSErrorException {
        CFError.CFErrorPtr err = new CFError.CFErrorPtr();
        boolean result = copyImageSource(isrc, options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get().as(NSError.class));
        }
        return result;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationCopyTypeIdentifiers", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getTypeIdentifiers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationCreateWithDataConsumer", optional=true)
    public static native CGImageDestination create(CGDataConsumer consumer, String type, @MachineSizedUInt long count, NSDictionary<?, ?> options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationCreateWithData", optional=true)
    public static native CGImageDestination create(NSData data, String type, @MachineSizedUInt long count, NSDictionary<?, ?> options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationCreateWithURL", optional=true)
    public static native CGImageDestination create(NSURL url, String type, @MachineSizedUInt long count, NSDictionary<?, ?> options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationSetProperties", optional=true)
    public native void setProperties(CGImageDestinationProperties properties);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationAddImage", optional=true)
    public native void addImage(CGImage image, CGImageDestinationProperties properties);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageDestinationAddImageFromSource", optional=true)
    public native void addImageFromSource(CGImageSource isrc, @MachineSizedUInt long index, CGImageDestinationProperties properties);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageDestinationAddImageAndMetadata", optional=true)
    public native void addImageAndMetadata(CGImage image, CGImageMetadata metadata, NSDictionary<?, ?> options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageDestinationCopyImageSource", optional=true)
    protected native boolean copyImageSource(CGImageSource isrc, CGImageDestinationCopySourceOptions options, CFError.CFErrorPtr err);
    /*</methods>*/
}
