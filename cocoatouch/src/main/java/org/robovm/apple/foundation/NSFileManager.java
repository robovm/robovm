/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 6.0 and later.
         */
        public static NSObject observeUbiquityIdentityDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(UbiquityIdentityDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSFileManagerPtr extends Ptr<NSFileManager, NSFileManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSFileManager() {}
    protected NSFileManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "delegate")
    public native NSFileManagerDelegate getDelegate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSFileManagerDelegate v);
    @Property(selector = "currentDirectoryPath")
    public native String getCurrentDirectoryPath();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "ubiquityIdentityToken")
    public native NSObject getUbiquityIdentityToken();
    /*</properties>*/
    /*<members>*//*</members>*/
    public boolean isDirectoryAtPath(String path) {
        BooleanPtr ptr = new BooleanPtr();
        fileExists(path, ptr);
        return ptr.get();
    }
    
    /**
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public NSURLRelationship getRelationshipOfDirectoryToItem(NSURL directoryURL, NSURL otherURL) throws NSErrorException {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        if (getRelationshipOfDirectoryToItem(ptr, directoryURL, otherURL, err)) {
            return NSURLRelationship.valueOf(ptr.get());
        }
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     * @throws NSErrorException
     */
    public NSURLRelationship getRelationshipOfDirectoryToItem(NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, NSURL url) throws NSErrorException {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        if (getRelationshipOfDirectoryToItem(ptr, directory, domainMask, url, err)) {
            return NSURLRelationship.valueOf(ptr.get());
        }
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSUbiquityIdentityDidChangeNotification", optional=true)
    public static native NSString UbiquityIdentityDidChangeNotification();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "mountedVolumeURLsIncludingResourceValuesForKeys:options:")
    protected native NSArray<NSURL> getMountedVolumeURLsIncludingResourceValues(@org.robovm.rt.bro.annotation.Marshaler(NSURLFileSystemProperty.AsListMarshaler.class) List<NSURLFileSystemProperty> propertyKeys, NSVolumeEnumerationOptions options);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSArray<NSURL> getContentsOfDirectoryAtURL(NSURL url, @org.robovm.rt.bro.annotation.Marshaler(NSURLFileSystemProperty.AsListMarshaler.class) List<NSURLFileSystemProperty> keys, NSDirectoryEnumerationOptions mask) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<NSURL> result = getContentsOfDirectoryAtURL(url, keys, mask, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "contentsOfDirectoryAtURL:includingPropertiesForKeys:options:error:")
    private native NSArray<NSURL> getContentsOfDirectoryAtURL(NSURL url, @org.robovm.rt.bro.annotation.Marshaler(NSURLFileSystemProperty.AsListMarshaler.class) List<NSURLFileSystemProperty> keys, NSDirectoryEnumerationOptions mask, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLsForDirectory:inDomains:")
    public native NSArray<NSURL> getURLsForDirectory(NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getURLForDirectory(NSSearchPathDirectory directory, NSSearchPathDomainMask domain, NSURL url, boolean shouldCreate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSURL result = getURLForDirectory(directory, domain, url, shouldCreate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForDirectory:inDomain:appropriateForURL:create:error:")
    private native NSURL getURLForDirectory(NSSearchPathDirectory directory, NSSearchPathDomainMask domain, NSURL url, boolean shouldCreate, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    protected boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSURL directoryURL, NSURL otherURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = getRelationshipOfDirectoryToItem(outRelationship, directoryURL, otherURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getRelationship:ofDirectoryAtURL:toItemAtURL:error:")
    private native boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSURL directoryURL, NSURL otherURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    protected boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = getRelationshipOfDirectoryToItem(outRelationship, directory, domainMask, url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getRelationship:ofDirectory:inDomain:toItemAtURL:error:")
    private native boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean createDirectoryAtURL(NSURL url, boolean createIntermediates, NSFileAttributes attributes) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = createDirectoryAtURL(url, createIntermediates, attributes, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "createDirectoryAtURL:withIntermediateDirectories:attributes:error:")
    private native boolean createDirectoryAtURL(NSURL url, boolean createIntermediates, NSFileAttributes attributes, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean createSymbolicLinkAtURL(NSURL url, NSURL destURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = createSymbolicLinkAtURL(url, destURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "createSymbolicLinkAtURL:withDestinationURL:error:")
    private native boolean createSymbolicLinkAtURL(NSURL url, NSURL destURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean setAttributesForItem(NSFileAttributes attributes, String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setAttributesForItem(attributes, path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setAttributes:ofItemAtPath:error:")
    private native boolean setAttributesForItem(NSFileAttributes attributes, String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean createDirectoryAtPath(String path, boolean createIntermediates, NSFileAttributes attributes) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = createDirectoryAtPath(path, createIntermediates, attributes, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "createDirectoryAtPath:withIntermediateDirectories:attributes:error:")
    private native boolean createDirectoryAtPath(String path, boolean createIntermediates, NSFileAttributes attributes, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSArray<NSURL> getContentsOfDirectoryAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSArray<NSURL> result = getContentsOfDirectoryAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "contentsOfDirectoryAtPath:error:")
    private native NSArray<NSURL> getContentsOfDirectoryAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubpathsOfDirectoryAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       List<String> result = getSubpathsOfDirectoryAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "subpathsOfDirectoryAtPath:error:")
    private native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubpathsOfDirectoryAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSFileAttributes getAttributesOfItemAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSFileAttributes result = getAttributesOfItemAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "attributesOfItemAtPath:error:")
    private native NSFileAttributes getAttributesOfItemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSFileSystemAttributes getAttributesOfFileSystemAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSFileSystemAttributes result = getAttributesOfFileSystemAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "attributesOfFileSystemForPath:error:")
    private native NSFileSystemAttributes getAttributesOfFileSystemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean createSymbolicLinkAtPath(String path, String destPath) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = createSymbolicLinkAtPath(path, destPath, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "createSymbolicLinkAtPath:withDestinationPath:error:")
    private native boolean createSymbolicLinkAtPath(String path, String destPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getDestinationOfSymbolicLinkAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       String result = getDestinationOfSymbolicLinkAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "destinationOfSymbolicLinkAtPath:error:")
    private native String getDestinationOfSymbolicLinkAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean copyItemAtPath(String srcPath, String dstPath) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = copyItemAtPath(srcPath, dstPath, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "copyItemAtPath:toPath:error:")
    private native boolean copyItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean moveItemAtPath(String srcPath, String dstPath) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = moveItemAtPath(srcPath, dstPath, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "moveItemAtPath:toPath:error:")
    private native boolean moveItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean linkItemAtPath(String srcPath, String dstPath) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = linkItemAtPath(srcPath, dstPath, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "linkItemAtPath:toPath:error:")
    private native boolean linkItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    public boolean removeItemAtPath(String path) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeItemAtPath(path, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "removeItemAtPath:error:")
    private native boolean removeItemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean copyItemAtURL(NSURL srcURL, NSURL dstURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = copyItemAtURL(srcURL, dstURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "copyItemAtURL:toURL:error:")
    private native boolean copyItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean moveItemAtURL(NSURL srcURL, NSURL dstURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = moveItemAtURL(srcURL, dstURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "moveItemAtURL:toURL:error:")
    private native boolean moveItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean linkItemAtURL(NSURL srcURL, NSURL dstURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = linkItemAtURL(srcURL, dstURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "linkItemAtURL:toURL:error:")
    private native boolean linkItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean removeItemAtURL(NSURL URL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeItemAtURL(URL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "removeItemAtURL:error:")
    private native boolean removeItemAtURL(NSURL URL, NSError.NSErrorPtr error);
    @Method(selector = "changeCurrentDirectoryPath:")
    public native boolean changeCurrentDirectoryPath(String path);
    @Method(selector = "fileExistsAtPath:")
    public native boolean fileExists(String path);
    @Method(selector = "fileExistsAtPath:isDirectory:")
    protected native boolean fileExists(String path, BooleanPtr isDirectory);
    @Method(selector = "isReadableFileAtPath:")
    public native boolean fileIsReadable(String path);
    @Method(selector = "isWritableFileAtPath:")
    public native boolean fileIsWritable(String path);
    @Method(selector = "isExecutableFileAtPath:")
    public native boolean fileIsExecutable(String path);
    @Method(selector = "isDeletableFileAtPath:")
    public native boolean fileIsDeletable(String path);
    @Method(selector = "contentsEqualAtPath:andPath:")
    public native boolean contentsAtPathEqual(String path1, String path2);
    @Method(selector = "displayNameAtPath:")
    public native String getDisplayNameAtPath(String path);
    @Method(selector = "componentsToDisplayForPath:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getComponentsToDisplayForPath(String path);
    @Method(selector = "enumeratorAtPath:")
    public native NSDirectoryEnumerator getEnumeratorAtPath(String path);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumeratorAtURL:includingPropertiesForKeys:options:errorHandler:")
    protected native NSDirectoryEnumerator getEnumeratorAtURL(NSURL url, @org.robovm.rt.bro.annotation.Marshaler(NSURLFileSystemProperty.AsListMarshaler.class) List<NSURLFileSystemProperty> keys, NSDirectoryEnumerationOptions mask, @Block Block2<NSURL, NSError, Boolean> handler);
    @Method(selector = "subpathsAtPath:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubpathsAtPath(String path);
    @Method(selector = "contentsAtPath:")
    public native NSData getContentsAtPath(String path);
    @Method(selector = "createFileAtPath:contents:attributes:")
    public native boolean createFileAtPath(String path, NSData data, NSFileAttributes attr);
    @Method(selector = "fileSystemRepresentationWithPath:")
    public native @org.robovm.rt.bro.annotation.Marshaler(StringMarshalers.AsDefaultCharsetZMarshaler.class) String getFileSystemRepresentationForPath(String path);
    @Method(selector = "stringWithFileSystemRepresentation:length:")
    public native String getPathForFileSystemRepresentation(BytePtr str, @MachineSizedUInt long len);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean replaceItemAtURL(NSURL originalItemURL, NSURL newItemURL, String backupItemName, NSFileManagerItemReplacementOptions options, NSURL.NSURLPtr resultingURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = replaceItemAtURL(originalItemURL, newItemURL, backupItemName, options, resultingURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "replaceItemAtURL:withItemAtURL:backupItemName:options:resultingItemURL:error:")
    private native boolean replaceItemAtURL(NSURL originalItemURL, NSURL newItemURL, String backupItemName, NSFileManagerItemReplacementOptions options, NSURL.NSURLPtr resultingURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean setUbiquitousItemAtURL(boolean flag, NSURL url, NSURL destinationURL) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setUbiquitousItemAtURL(flag, url, destinationURL, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setUbiquitous:itemAtURL:destinationURL:error:")
    private native boolean setUbiquitousItemAtURL(boolean flag, NSURL url, NSURL destinationURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isUbiquitousItemAtURL:")
    public native boolean isUbiquitousItemAtURL(NSURL url);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean startDownloadingUbiquitousItemAtURL(NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = startDownloadingUbiquitousItemAtURL(url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startDownloadingUbiquitousItemAtURL:error:")
    private native boolean startDownloadingUbiquitousItemAtURL(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean evictUbiquitousItemAtURL(NSURL url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = evictUbiquitousItemAtURL(url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "evictUbiquitousItemAtURL:error:")
    private native boolean evictUbiquitousItemAtURL(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLForUbiquityContainerIdentifier:")
    public native NSURL getURLForUbiquityContainerIdentifier(String containerIdentifier);
    /**
     * @since Available in iOS 5.0 and later.
     */
    public NSURL getURLForPublishingUbiquitousItemAtURL(NSURL url, NSDate.NSDatePtr outDate) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSURL result = getURLForPublishingUbiquitousItemAtURL(url, outDate, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLForPublishingUbiquitousItemAtURL:expirationDate:error:")
    private native NSURL getURLForPublishingUbiquitousItemAtURL(NSURL url, NSDate.NSDatePtr outDate, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "containerURLForSecurityApplicationGroupIdentifier:")
    public native NSURL getContainerURLForSecurityApplication(String groupIdentifier);
    @Method(selector = "defaultManager")
    public static native NSFileManager getDefaultManager();
    /*</methods>*/
}
