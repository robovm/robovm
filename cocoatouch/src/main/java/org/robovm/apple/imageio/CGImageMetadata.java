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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageMetadata/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGImageMetadataPtr extends Ptr<CGImageMetadata, CGImageMetadataPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImageMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGImageMetadata() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * 
     * @param xmlns
     * @param prefix
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    public boolean registerNamespaceForPrefix(String xmlns, String prefix) throws NSErrorException {
        CFError.CFErrorPtr err = new CFError.CFErrorPtr();
        boolean result = registerNamespaceForPrefix(xmlns, prefix, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get().as(NSError.class));
        }
        return result;
    }
    /**
     * 
     * @param xmlns
     * @param prefix
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    public boolean registerNamespaceForPrefix(CGImageMetadataNamespace xmlns, String prefix) throws NSErrorException {
        return registerNamespaceForPrefix(xmlns.value().toString(), prefix);
    }
    /**
     * 
     * @param xmlns
     * @param prefix
     * @return
     * @since Available in iOS 7.0 and later.
     * @throws NSErrorException
     */
    public boolean registerNamespaceForPrefix(String xmlns, CGImageMetadataPrefix prefix) throws NSErrorException {
        return registerNamespaceForPrefix(xmlns, prefix.value().toString());
    }
    /*<methods>*/
    @Bridge(symbol="CGImageMetadataGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCreateMutable", optional=true)
    public static native CGImageMetadata create();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCreateMutableCopy", optional=true)
    public static native CGImageMetadata createCopy(CGImageMetadata metadata);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCopyTags", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<CGImageMetadataTag> getTags();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCopyTagWithPath", optional=true)
    public native CGImageMetadataTag getTagAtPath(CGImageMetadataTag parent, String path);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCopyStringValueWithPath", optional=true)
    public native String getStringValueAtPath(CGImageMetadataTag parent, String path);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataRegisterNamespaceForPrefix", optional=true)
    protected native boolean registerNamespaceForPrefix(String xmlns, String prefix, CFError.CFErrorPtr err);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataSetTagWithPath", optional=true)
    public native boolean setTagAtPath(CGImageMetadataTag parent, String path, CGImageMetadataTag tag);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataSetValueWithPath", optional=true)
    public native boolean setValueAtPath(CGImageMetadataTag parent, String path, CFType value);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataRemoveTagWithPath", optional=true)
    public native boolean removeTagAtPath(CGImageMetadataTag parent, String path);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataEnumerateTagsUsingBlock", optional=true)
    public native void enumerateTags(String rootPath, CGImageMetadataEnumerationOptions options, @Block VoidBlock2<String, CGImageMetadataTag> block);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCreateXMPData", optional=true)
    public native NSData createXMPData(NSDictionary<?, ?> options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CGImageMetadataCreateFromXMPData", optional=true)
    public static native CGImageMetadata createFromXMPData(NSData data);
    /*</methods>*/
}
