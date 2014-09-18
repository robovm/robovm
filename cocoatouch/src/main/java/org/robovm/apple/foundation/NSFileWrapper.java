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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileWrapper/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSFileWrapperPtr extends Ptr<NSFileWrapper, NSFileWrapperPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileWrapper.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileWrapper() {}
    protected NSFileWrapper(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFileWrapper(NSURL url, NSFileWrapperReadingOptions options, NSError.NSErrorPtr outError) { super((SkipInit) null); initObject(initWithURL$options$error$(url, options, outError)); }
    public NSFileWrapper(NSDictionary<?, ?> childrenByPreferredName) { super((SkipInit) null); initObject(initDirectoryWithFileWrappers$(childrenByPreferredName)); }
    public NSFileWrapper(NSData contents) { super((SkipInit) null); initObject(initRegularFileWithContents$(contents)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFileWrapper(NSURL url) { super((SkipInit) null); initObject(initSymbolicLinkWithDestinationURL$(url)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public static NSFileWrapper deserialize(NSData data) {
        NSFileWrapper wrapper = new NSFileWrapper((SkipInit) null);
        long handle = wrapper.initWithSerializedRepresentation$(data);
        if (handle == 0) {
            return null;
        }
        wrapper.initObject(handle);
        return wrapper;
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initWithURL:options:error:")
    protected native @Pointer long initWithURL$options$error$(NSURL url, NSFileWrapperReadingOptions options, NSError.NSErrorPtr outError);
    @Method(selector = "initDirectoryWithFileWrappers:")
    protected native @Pointer long initDirectoryWithFileWrappers$(NSDictionary<?, ?> childrenByPreferredName);
    @Method(selector = "initRegularFileWithContents:")
    protected native @Pointer long initRegularFileWithContents$(NSData contents);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initSymbolicLinkWithDestinationURL:")
    protected native @Pointer long initSymbolicLinkWithDestinationURL$(NSURL url);
    @Method(selector = "initWithSerializedRepresentation:")
    protected native @Pointer long initWithSerializedRepresentation$(NSData serializeRepresentation);
    @Method(selector = "isDirectory")
    public native boolean isDirectory();
    @Method(selector = "isRegularFile")
    public native boolean isRegularFile();
    @Method(selector = "isSymbolicLink")
    public native boolean isSymbolicLink();
    @Method(selector = "setPreferredFilename:")
    public native void setPreferredFilename(String fileName);
    @Method(selector = "preferredFilename")
    public native String preferredFilename();
    @Method(selector = "setFilename:")
    public native void setFilename(String fileName);
    @Method(selector = "filename")
    public native String filename();
    @Method(selector = "setFileAttributes:")
    public native void setFileAttributes(NSDictionary<?, ?> fileAttributes);
    @Method(selector = "fileAttributes")
    public native NSDictionary<?, ?> fileAttributes();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "matchesContentsOfURL:")
    public native boolean matchesContentsOfURL$(NSURL url);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "readFromURL:options:error:")
    public native boolean readFromURL$options$error$(NSURL url, NSFileWrapperReadingOptions options, NSError.NSErrorPtr outError);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "writeToURL:options:originalContentsURL:error:")
    public native boolean writeToURL$options$originalContentsURL$error$(NSURL url, NSFileWrapperWritingOptions options, NSURL originalContentsURL, NSError.NSErrorPtr outError);
    @Method(selector = "serializedRepresentation")
    public native NSData serialize();
    @Method(selector = "addFileWrapper:")
    public native String addFileWrapper$(NSFileWrapper child);
    @Method(selector = "addRegularFileWithContents:preferredFilename:")
    public native String addRegularFileWithContents$preferredFilename$(NSData data, String fileName);
    @Method(selector = "removeFileWrapper:")
    public native void removeFileWrapper$(NSFileWrapper child);
    @Method(selector = "fileWrappers")
    public native NSDictionary<?, ?> fileWrappers();
    @Method(selector = "keyForFileWrapper:")
    public native String keyForFileWrapper$(NSFileWrapper child);
    @Method(selector = "regularFileContents")
    public native NSData regularFileContents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "symbolicLinkDestinationURL")
    public native NSURL symbolicLinkDestinationURL();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
