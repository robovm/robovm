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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageSource/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGImageSourcePtr extends Ptr<CGImageSource, CGImageSourcePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImageSource.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGImageSource() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCopyTypeIdentifiers", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getTypeIdentifiers();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateWithDataProvider", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImageSource create(CGDataProvider provider, CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateWithData", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImageSource create(NSData data, CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateWithURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImageSource create(NSURL url, CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceGetType", optional=true)
    public native String getType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceGetCount", optional=true)
    public native @MachineSizedUInt long getCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCopyProperties", optional=true)
    public native CGImageProperties getProperties(CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCopyPropertiesAtIndex", optional=true)
    public native CGImageProperties getProperties(@MachineSizedUInt long index, CGImageSourceOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageSourceCopyMetadataAtIndex", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImageMetadata getMetadata(@MachineSizedUInt long index, CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateImageAtIndex", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createImage(@MachineSizedUInt long index, CGImageSourceOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageSourceRemoveCacheAtIndex", optional=true)
    public native void removeCacheAtIndex(@MachineSizedUInt long index);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateThumbnailAtIndex", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createThumbnail(@MachineSizedUInt long index, CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceCreateIncremental", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImageSource createIncremental(CGImageSourceOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceUpdateData", optional=true)
    public native void updateData(NSData data, boolean isFinal);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceUpdateDataProvider", optional=true)
    public native void updateDataProvider(CGDataProvider provider, boolean isFinal);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceGetStatus", optional=true)
    public native CGImageSourceStatus getStatus();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGImageSourceGetStatusAtIndex", optional=true)
    public native CGImageSourceStatus getStatusAtIndex(@MachineSizedUInt long index);
    /*</methods>*/
}
