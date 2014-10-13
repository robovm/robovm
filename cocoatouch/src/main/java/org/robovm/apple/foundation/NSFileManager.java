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
     */
    public NSURLRelationship getRelationshipOfDirectoryToItem(NSURL directoryURL, NSURL otherURL) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        NSError.NSErrorPtr error = new NSError.NSErrorPtr();
        if (getRelationshipOfDirectoryToItem(ptr, directoryURL, otherURL, error)) {
            return NSURLRelationship.valueOf(ptr.get());
        }
        return null; // TODO exception
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSURLRelationship getRelationshipOfDirectoryToItem(NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, NSURL url) {
        MachineSizedSIntPtr ptr = new MachineSizedSIntPtr();
        NSError.NSErrorPtr error = new NSError.NSErrorPtr();
        if (getRelationshipOfDirectoryToItem(ptr, directory, domainMask, url, error)) {
            return NSURLRelationship.valueOf(ptr.get());
        }
        return null; // TODO exception
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
    @Method(selector = "contentsOfDirectoryAtURL:includingPropertiesForKeys:options:error:")
    protected native NSArray<NSURL> getContentsOfDirectoryAtURL(NSURL url, @org.robovm.rt.bro.annotation.Marshaler(NSURLFileSystemProperty.AsListMarshaler.class) List<NSURLFileSystemProperty> keys, NSDirectoryEnumerationOptions mask, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLsForDirectory:inDomains:")
    public native NSArray<NSURL> getURLsForDirectory(NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "URLForDirectory:inDomain:appropriateForURL:create:error:")
    public native NSURL getURLForDirectory(NSSearchPathDirectory directory, NSSearchPathDomainMask domain, NSURL url, boolean shouldCreate, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getRelationship:ofDirectoryAtURL:toItemAtURL:error:")
    protected native boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSURL directoryURL, NSURL otherURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "getRelationship:ofDirectory:inDomain:toItemAtURL:error:")
    protected native boolean getRelationshipOfDirectoryToItem(MachineSizedSIntPtr outRelationship, NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask, NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "createDirectoryAtURL:withIntermediateDirectories:attributes:error:")
    public native boolean createDirectoryAtURL(NSURL url, boolean createIntermediates, NSFileAttributes attributes, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "createSymbolicLinkAtURL:withDestinationURL:error:")
    public native boolean createSymbolicLinkAtURL(NSURL url, NSURL destURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setAttributes:ofItemAtPath:error:")
    public native boolean setAttributesForItem(NSFileAttributes attributes, String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "createDirectoryAtPath:withIntermediateDirectories:attributes:error:")
    public native boolean createDirectoryAtPath(String path, boolean createIntermediates, NSFileAttributes attributes, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "contentsOfDirectoryAtPath:error:")
    public native NSArray<NSURL> getContentsOfDirectoryAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "subpathsOfDirectoryAtPath:error:")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubpathsOfDirectoryAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "attributesOfItemAtPath:error:")
    public native NSFileAttributes getAttributesOfItemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "attributesOfFileSystemForPath:error:")
    public native NSFileSystemAttributes getAttributesOfFileSystemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "createSymbolicLinkAtPath:withDestinationPath:error:")
    public native boolean createSymbolicLinkAtPath(String path, String destPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "destinationOfSymbolicLinkAtPath:error:")
    public native String getDestinationOfSymbolicLinkAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "copyItemAtPath:toPath:error:")
    public native boolean copyItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "moveItemAtPath:toPath:error:")
    public native boolean moveItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "linkItemAtPath:toPath:error:")
    public native boolean linkItemAtPath(String srcPath, String dstPath, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "removeItemAtPath:error:")
    public native boolean removeItemAtPath(String path, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "copyItemAtURL:toURL:error:")
    public native boolean copyItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "moveItemAtURL:toURL:error:")
    public native boolean moveItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "linkItemAtURL:toURL:error:")
    public native boolean linkItemAtURL(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "removeItemAtURL:error:")
    public native boolean removeItemAtURL(NSURL URL, NSError.NSErrorPtr error);
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
    @Method(selector = "replaceItemAtURL:withItemAtURL:backupItemName:options:resultingItemURL:error:")
    public native boolean replaceItemAtURL(NSURL originalItemURL, NSURL newItemURL, String backupItemName, NSFileManagerItemReplacementOptions options, NSURL.NSURLPtr resultingURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setUbiquitous:itemAtURL:destinationURL:error:")
    public native boolean setUbiquitousItemAtURL(boolean flag, NSURL url, NSURL destinationURL, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isUbiquitousItemAtURL:")
    public native boolean isUbiquitousItemAtURL(NSURL url);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "startDownloadingUbiquitousItemAtURL:error:")
    public native boolean startDownloadingUbiquitousItemAtURL(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "evictUbiquitousItemAtURL:error:")
    public native boolean evictUbiquitousItemAtURL(NSURL url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLForUbiquityContainerIdentifier:")
    public native NSURL getURLForUbiquityContainerIdentifier(String containerIdentifier);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "URLForPublishingUbiquitousItemAtURL:expirationDate:error:")
    public native NSURL getURLForPublishingUbiquitousItemAtURL(NSURL url, NSDate.NSDatePtr outDate, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "containerURLForSecurityApplicationGroupIdentifier:")
    public native NSURL getContainerURLForSecurityApplication(String groupIdentifier);
    @Method(selector = "defaultManager")
    public static native NSFileManager getDefaultManager();
    /*</methods>*/
}
