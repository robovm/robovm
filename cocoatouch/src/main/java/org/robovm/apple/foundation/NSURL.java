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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/
import org.robovm.apple.foundation.NSError.NSErrorPtr;

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURL/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSURLPtr extends Ptr<NSURL, NSURLPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSURL.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public NSURL(NSURLScheme scheme, String host, String path) { super((SkipInit) null); initObject(initWithScheme$host$path$(scheme.value(), host, path)); }
    /*<constructors>*/
    public NSURL() {}
    protected NSURL(SkipInit skipInit) { super(skipInit); }
    public NSURL(String scheme, String host, String path) { super((SkipInit) null); initObject(initWithScheme$host$path$(scheme, host, path)); }
    public NSURL(String URLString) { super((SkipInit) null); initObject(initWithString$(URLString)); }
    public NSURL(String URLString, NSURL baseURL) { super((SkipInit) null); initObject(initWithString$relativeToURL$(URLString, baseURL)); }
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
    
    /**
     * 
     * @param bookmarkData
     * @param options
     * @param relativeURL
     * @param isStale
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSURL(NSData bookmarkData, NSURLBookmarkResolutionOptions options, NSURL relativeURL, BooleanPtr isStale) throws NSErrorException {
        super((SkipInit) null);
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        initObject(init(bookmarkData, options, relativeURL, isStale, err));
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }

    /*<properties>*/
    @Property(selector = "absoluteString")
    public native String getAbsoluteString();
    @Property(selector = "relativeString")
    public native String getRelativeString();
    @Property(selector = "baseURL")
    public native NSURL getBaseURL();
    @Property(selector = "absoluteURL")
    public native NSURL getAbsoluteURL();
    @Property(selector = "scheme")
    public native String getScheme();
    @Property(selector = "resourceSpecifier")
    public native String getResourceSpecifier();
    @Property(selector = "host")
    public native String getHost();
    @Property(selector = "port")
    public native NSNumber getPort();
    @Property(selector = "user")
    public native String getUser();
    @Property(selector = "password")
    public native String getPassword();
    @Property(selector = "path")
    public native String getPath();
    @Property(selector = "fragment")
    public native String getFragment();
    @Property(selector = "parameterString")
    public native String getParameterString();
    @Property(selector = "query")
    public native String getQuery();
    @Property(selector = "relativePath")
    public native String getRelativePath();
    @Property(selector = "isFileURL")
    public native boolean isFileURL();
    @Property(selector = "standardizedURL")
    public native NSURL getStandardizedURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "filePathURL")
    public native NSURL getFilePathURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "pathComponents")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPathComponents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "lastPathComponent")
    public native String getLastPathComponent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "pathExtension")
    public native String getPathExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "URLByDeletingLastPathComponent")
    public native NSURL getURLByDeletingLastPathComponent();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "URLByDeletingPathExtension")
    public native NSURL getURLByDeletingPathExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "URLByStandardizingPath")
    public native NSURL getURLByStandardizingPath();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "URLByResolvingSymlinksInPath")
    public native NSURL getURLByResolvingSymlinksInPath();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public java.net.URL toURL() throws java.net.MalformedURLException {
        return new java.net.URL(getAbsoluteString());
    }
    public java.net.URI toURI() throws java.net.URISyntaxException {
        return new java.net.URI(getAbsoluteString());
    }

    public static String encodeURLString(String urlString, NSStringEncoding encoding) {
        return new NSString(urlString).stringByAddingPercentEscapesUsingEncoding$(encoding);
    }
    public static String decodeURLString(String urlString, NSStringEncoding encoding) {
        return new NSString(urlString).stringByReplacingPercentEscapesUsingEncoding$(encoding);
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSObject getResourceValue(NSURLFileSystemProperty property) throws NSErrorException {
        return getResourceValue(property.value());
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSObject getResourceValue(NSURLFileProperty property) throws NSErrorException {
        return getResourceValue(property.value());
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSObject getResourceValue(NSURLVolumeProperty property) throws NSErrorException {
        return getResourceValue(property.value());
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSObject getResourceValue(NSURLUbiquitousItemProperty property) throws NSErrorException {
        return getResourceValue(property.value());
    }
    private NSObject getResourceValue(NSString key) {
        NSObjectPtr value = new NSObjectPtr();
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        
        getResourceValue$forKey$error$(value, key, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        
        return value.get();
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void removeCachedResourceValue(NSURLFileSystemProperty property) {
        removeCachedResourceValue(property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void removeCachedResourceValue(NSURLFileProperty property) {
        removeCachedResourceValue(property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void removeCachedResourceValue(NSURLVolumeProperty property) {
        removeCachedResourceValue(property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void removeCachedResourceValue(NSURLUbiquitousItemProperty property) {
        removeCachedResourceValue(property.value());
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public void setResourceValue(NSURLFileSystemProperty property, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        setResourceValue(value, property.value(), err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public void setResourceValue(NSURLFileProperty property, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        setResourceValue(value, property.value(), err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public void setResourceValue(NSURLVolumeProperty property, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        setResourceValue(value, property.value(), err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public void setResourceValue(NSURLUbiquitousItemProperty property, NSObject value) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        setResourceValue(value, property.value(), err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setTemporaryResourceValue(NSURLFileSystemProperty property, NSObject value) {
        setTemporaryResourceValue(value, property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setTemporaryResourceValue(NSURLFileProperty property, NSObject value) {
        setTemporaryResourceValue(value, property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setTemporaryResourceValue(NSURLVolumeProperty property, NSObject value) {
        setTemporaryResourceValue(value, property.value());
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setTemporaryResourceValue(NSURLUbiquitousItemProperty property, NSObject value) {
        setTemporaryResourceValue(value, property.value());
    }
    /**
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public NSObject getPromisedItemResourceValue(NSURLProperty key) throws NSErrorException {
        NSObject.NSObjectPtr ptr = new NSObject.NSObjectPtr();
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        if (getPromisedItemResourceValue(ptr, key.value(), err)) {
            return ptr.get();
        }
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return null;
    }

    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public boolean checkResourceIsReachable() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = checkResourceIsReachable(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSURLProperties getResourceValues(@org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSURLProperties result = getResourceValues(keys, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public boolean setResourceValues(NSURLProperties keyedValues) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = setResourceValues(keyedValues, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public NSData toBookmarkData(NSURLBookmarkCreationOptions options, @org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys, NSURL relativeURL) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSData result = toBookmarkData(options, keys, relativeURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public static boolean writeBookmarkData(NSData bookmarkData, NSURL bookmarkFileURL, NSURLBookmarkCreationOptions options) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = writeBookmarkData(bookmarkData, bookmarkFileURL, options, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public static NSData createBookmarkData(NSURL bookmarkFileURL) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSData result = createBookmarkData(bookmarkFileURL, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public NSURLProperties getPromisedItemResourceValues(@org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        NSURLProperties result = getPromisedItemResourceValues(keys, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public boolean isPromisedItemReachable() throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = isPromisedItemReachable(err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
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
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "checkResourceIsReachableAndReturnError:")
    protected native boolean checkResourceIsReachable(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "isFileReferenceURL")
    public native boolean isFileReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "fileReferenceURL")
    public native NSURL getFileReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "getResourceValue:forKey:error:")
    private native boolean getResourceValue$forKey$error$(NSObject.NSObjectPtr value, NSString key, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "resourceValuesForKeys:error:")
    protected native NSURLProperties getResourceValues(@org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setResourceValue:forKey:error:")
    protected native boolean setResourceValue(NSObject value, NSString key, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setResourceValues:error:")
    protected native boolean setResourceValues(NSURLProperties keyedValues, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeCachedResourceValueForKey:")
    protected native void removeCachedResourceValue(NSString key);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "removeAllCachedResourceValues")
    public native void removeAllCachedResourceValues();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setTemporaryResourceValue:forKey:")
    protected native void setTemporaryResourceValue(NSObject value, NSString key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "bookmarkDataWithOptions:includingResourceValuesForKeys:relativeToURL:error:")
    protected native NSData toBookmarkData(NSURLBookmarkCreationOptions options, @org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys, NSURL relativeURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "initByResolvingBookmarkData:options:relativeToURL:bookmarkDataIsStale:error:")
    protected native @Pointer long init(NSData bookmarkData, NSURLBookmarkResolutionOptions options, NSURL relativeURL, BooleanPtr isStale, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "startAccessingSecurityScopedResource")
    public native boolean startAccessingSecurityScopedResource();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "stopAccessingSecurityScopedResource")
    public native void stopAccessingSecurityScopedResource();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "resourceValuesForKeys:fromBookmarkData:")
    public static native NSURLProperties getResourceValuesFromBookmarkData(@org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys, NSData bookmarkData);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "writeBookmarkData:toURL:options:error:")
    protected static native boolean writeBookmarkData(NSData bookmarkData, NSURL bookmarkFileURL, NSURLBookmarkCreationOptions options, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "bookmarkDataWithContentsOfURL:error:")
    protected static native NSData createBookmarkData(NSURL bookmarkFileURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getPromisedItemResourceValue:forKey:error:")
    protected native boolean getPromisedItemResourceValue(NSObject.NSObjectPtr value, NSString key, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "promisedItemResourceValuesForKeys:error:")
    protected native NSURLProperties getPromisedItemResourceValues(@org.robovm.rt.bro.annotation.Marshaler(NSURLProperty.AsListMarshaler.class) List<NSURLProperty> keys, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "checkPromisedItemIsReachableAndReturnError:")
    protected native boolean isPromisedItemReachable(NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByAppendingPathComponent:")
    public native NSURL newURLByAppendingPathComponent(String pathComponent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLByAppendingPathComponent:isDirectory:")
    public native NSURL newURLByAppendingPathComponent(String pathComponent, boolean isDirectory);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLByAppendingPathExtension:")
    public native NSURL newURLByAppendingPathExtension(String pathExtension);
    /*</methods>*/
}
