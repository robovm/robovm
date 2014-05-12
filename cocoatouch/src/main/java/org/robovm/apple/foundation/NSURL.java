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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURL/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLPtr extends Ptr<NSURL, NSURLPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURL.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSURL() {}
    protected NSURL(SkipInit skipInit) { super(skipInit); }
    public NSURL(String scheme, String host, String path) { super((SkipInit) null); initObject(initWithScheme$host$path$(scheme, host, path)); }
    public NSURL(String URLString) { super((SkipInit) null); initObject(initWithString$(URLString)); }
    public NSURL(String URLString, NSURL baseURL) { super((SkipInit) null); initObject(initWithString$relativeToURL$(URLString, baseURL)); }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL(NSData bookmarkData, NSURLBookmarkResolutionOptions options, NSURL relativeURL, BytePtr isStale, NSError.NSErrorPtr error) { super((SkipInit) null); initObject(initByResolvingBookmarkData$options$relativeToURL$bookmarkDataIsStale$error$(bookmarkData, options, relativeURL, isStale, error)); }
    /*</constructors>*/
    
    public NSURL(File file) {
        super((SkipInit) null);
        initObject(initFileURLWithPath$(file.getAbsolutePath()));
    }

    public NSURL(File file, boolean isDir) {
        super((SkipInit) null);
        initObject(initFileURLWithPath$isDirectory$(file.getAbsolutePath(), isDir));
    }
    
    public NSURL(java.net.URL url) throws java.net.URISyntaxException {
        this(url.toURI());
    }

    public NSURL(java.net.URI uri) {
        this(uri.toString());
    }

    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public java.net.URL toURL() throws java.net.MalformedURLException {
        return new java.net.URL(absoluteString());
    }

    public java.net.URI toURI() throws java.net.URISyntaxException {
        return new java.net.URI(absoluteString());
    }

    /*<methods>*/
    @Method(selector = "initWithScheme:host:path:")
    protected native @Pointer long initWithScheme$host$path$(String scheme, String host, String path);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "initFileURLWithPath:isDirectory:")
    protected native @Pointer long initFileURLWithPath$isDirectory$(String path, boolean isDir);
    @Method(selector = "initFileURLWithPath:")
    protected native @Pointer long initFileURLWithPath$(String path);
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String URLString);
    @Method(selector = "initWithString:relativeToURL:")
    protected native @Pointer long initWithString$relativeToURL$(String URLString, NSURL baseURL);
    @Method(selector = "absoluteString")
    public native String absoluteString();
    @Method(selector = "relativeString")
    public native String relativeString();
    @Method(selector = "baseURL")
    public native NSURL baseURL();
    @Method(selector = "absoluteURL")
    public native NSURL absoluteURL();
    @Method(selector = "scheme")
    public native String scheme();
    @Method(selector = "resourceSpecifier")
    public native String resourceSpecifier();
    @Method(selector = "host")
    public native String host();
    @Method(selector = "port")
    public native NSNumber port();
    @Method(selector = "user")
    public native String user();
    @Method(selector = "password")
    public native String password();
    @Method(selector = "path")
    public native String path();
    @Method(selector = "fragment")
    public native String fragment();
    @Method(selector = "parameterString")
    public native String parameterString();
    @Method(selector = "query")
    public native String query();
    @Method(selector = "relativePath")
    public native String relativePath();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "getFileSystemRepresentation:maxLength:")
    public native boolean getFileSystemRepresentation$maxLength$(BytePtr buffer, @MachineSizedUInt long maxBufferLength);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "fileSystemRepresentation")
    public native BytePtr fileSystemRepresentation();
    @Method(selector = "isFileURL")
    public native boolean isFileURL();
    @Method(selector = "standardizedURL")
    public native NSURL standardizedURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "checkResourceIsReachableAndReturnError:")
    public native boolean checkResourceIsReachableAndReturnError$(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "isFileReferenceURL")
    public native boolean isFileReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileReferenceURL")
    public native NSURL fileReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "filePathURL")
    public native NSURL filePathURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "getResourceValue:forKey:error:")
    public native boolean getResourceValue$forKey$error$(NSObject value, String key, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "resourceValuesForKeys:error:")
    public native NSDictionary<?, ?> resourceValuesForKeys$error$(NSArray<?> keys, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setResourceValue:forKey:error:")
    public native boolean setResourceValue$forKey$error$(NSObject value, String key, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setResourceValues:error:")
    public native boolean setResourceValues$error$(NSDictionary<?, ?> keyedValues, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeCachedResourceValueForKey:")
    public native void removeCachedResourceValueForKey$(String key);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeAllCachedResourceValues")
    public native void removeAllCachedResourceValues();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setTemporaryResourceValue:forKey:")
    public native void setTemporaryResourceValue$forKey$(NSObject value, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "bookmarkDataWithOptions:includingResourceValuesForKeys:relativeToURL:error:")
    public native NSData bookmarkDataWithOptions$includingResourceValuesForKeys$relativeToURL$error$(NSURLBookmarkCreationOptions options, NSArray<?> keys, NSURL relativeURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initByResolvingBookmarkData:options:relativeToURL:bookmarkDataIsStale:error:")
    protected native @Pointer long initByResolvingBookmarkData$options$relativeToURL$bookmarkDataIsStale$error$(NSData bookmarkData, NSURLBookmarkResolutionOptions options, NSURL relativeURL, BytePtr isStale, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "resourceValuesForKeys:fromBookmarkData:")
    public static native NSDictionary<?, ?> resourceValuesForKeys$fromBookmarkData$(NSArray<?> keys, NSData bookmarkData);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "writeBookmarkData:toURL:options:error:")
    public static native boolean writeBookmarkData$toURL$options$error$(NSData bookmarkData, NSURL bookmarkFileURL, @MachineSizedUInt long options, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "bookmarkDataWithContentsOfURL:error:")
    public static native NSData bookmarkDataWithContentsOfURL$error$(NSURL bookmarkFileURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "pathComponents")
    public native NSArray<?> pathComponents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "lastPathComponent")
    public native String lastPathComponent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "pathExtension")
    public native String pathExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByAppendingPathComponent:")
    public native NSURL URLByAppendingPathComponent$(String pathComponent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLByAppendingPathComponent:isDirectory:")
    public native NSURL URLByAppendingPathComponent$isDirectory$(String pathComponent, boolean isDirectory);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByDeletingLastPathComponent")
    public native NSURL URLByDeletingLastPathComponent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByAppendingPathExtension:")
    public native NSURL URLByAppendingPathExtension$(String pathExtension);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByDeletingPathExtension")
    public native NSURL URLByDeletingPathExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByStandardizingPath")
    public native NSURL URLByStandardizingPath();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByResolvingSymlinksInPath")
    public native NSURL URLByResolvingSymlinksInPath();
    /*</methods>*/
}
