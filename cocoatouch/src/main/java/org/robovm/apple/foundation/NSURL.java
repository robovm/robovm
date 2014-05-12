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
    @GlobalValue(symbol="NSURLFileScheme", optional=true)
    public static native String FileScheme();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
    public static native NSString KeyKeysOfUnsetValues();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLNameKey", optional=true)
    public static native NSString KeyName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
    public static native NSString KeyLocalizedName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
    public static native NSString KeyIsRegularFile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
    public static native NSString KeyIsDirectory();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
    public static native NSString KeyIsSymbolicLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
    public static native NSString KeyIsVolume();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
    public static native NSString KeyIsPackage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
    public static native NSString KeyIsSystemImmutable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
    public static native NSString KeyIsUserImmutable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
    public static native NSString KeyIsHidden();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
    public static native NSString KeyHasHiddenExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
    public static native NSString KeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
    public static native NSString KeyContentAccessDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
    public static native NSString KeyContentModificationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
    public static native NSString KeyAttributeModificationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
    public static native NSString KeyLinkCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
    public static native NSString KeyParentDirectoryURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
    public static native NSString KeyVolumeURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
    public static native NSString KeyTypeIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
    public static native NSString KeyLocalizedTypeDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
    public static native NSString KeyLabelNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
    public static native NSString KeyLabelColor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
    public static native NSString KeyLocalizedLabel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
    public static native NSString KeyEffectiveIcon();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
    public static native NSString KeyCustomIcon();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
    public static native NSString KeyFileResourceIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
    public static native NSString KeyVolumeIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
    public static native NSString KeyPreferredIOBlockSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
    public static native NSString KeyIsReadable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
    public static native NSString KeyIsWritable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
    public static native NSString KeyIsExecutable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
    public static native NSString KeyFileSecurity();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
    public static native NSString KeyIsExcludedFromBackup();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="NSURLPathKey", optional=true)
    public static native NSString KeyPath();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
    public static native NSString KeyIsMountTrigger();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
    public static native NSString KeyFileResourceType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
    public static native String FileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
    public static native String FileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
    public static native String FileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
    public static native String FileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
    public static native String FileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
    public static native String FileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
    public static native String FileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
    public static native String FileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
    public static native NSString KeyFileSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
    public static native NSString KeyFileAllocatedSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
    public static native NSString KeyTotalFileSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
    public static native NSString KeyTotalFileAllocatedSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
    public static native NSString KeyIsAliasFile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native NSString KeyVolumeLocalizedFormatDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
    public static native NSString KeyVolumeTotalCapacity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
    public static native NSString KeyVolumeAvailableCapacity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
    public static native NSString KeyVolumeResourceCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native NSString KeyVolumeSupportsPersistentIDs();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native NSString KeyVolumeSupportsSymbolicLinks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
    public static native NSString KeyVolumeSupportsHardLinks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
    public static native NSString KeyVolumeSupportsJournaling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
    public static native NSString KeyVolumeIsJournaling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
    public static native NSString KeyVolumeSupportsSparseFiles();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
    public static native NSString KeyVolumeSupportsZeroRuns();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native NSString KeyVolumeSupportsCaseSensitiveNames();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native NSString KeyVolumeSupportsCasePreservedNames();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native NSString KeyVolumeSupportsRootDirectoryDates();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native NSString KeyVolumeSupportsVolumeSizes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
    public static native NSString KeyVolumeSupportsRenaming();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native NSString KeyVolumeSupportsAdvisoryFileLocking();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native NSString KeyVolumeSupportsExtendedSecurity();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
    public static native NSString KeyVolumeIsBrowsable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
    public static native NSString KeyVolumeMaximumFileSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
    public static native NSString KeyVolumeIsEjectable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
    public static native NSString KeyVolumeIsRemovable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
    public static native NSString KeyVolumeIsInternal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
    public static native NSString KeyVolumeIsAutomounted();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
    public static native NSString KeyVolumeIsLocal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
    public static native NSString KeyVolumeIsReadOnly();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
    public static native NSString KeyVolumeCreationDate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
    public static native NSString KeyVolumeURLForRemounting();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
    public static native NSString KeyVolumeUUIDString();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
    public static native NSString KeyVolumeName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
    public static native NSString KeyVolumeLocalizedName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLIsUbiquitousItemKey", optional=true)
    public static native NSString KeyIsUbiquitousItem();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString KeyUbiquitousItemHasUnresolvedConflicts();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousItemIsDownloaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString KeyUbiquitousItemIsDownloading();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString KeyUbiquitousItemIsUploaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString KeyUbiquitousItemIsUploading();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousItemPercentDownloaded();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString KeyUbiquitousItemPercentUploaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString KeyUbiquitousItemDownloadingStatus();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousItemDownloadingError();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousItemUploadingError();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native String UbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native String UbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native String UbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="NSURLErrorDomain", optional=true)
    public static native String ErrorDomainURL();
    
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
