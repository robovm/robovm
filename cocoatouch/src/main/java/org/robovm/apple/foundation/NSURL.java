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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="NSURLKeysOfUnsetValuesKey", optional=true)
    public static native NSString KeyKeysOfUnsetValues();
    @GlobalValue(symbol="NSURLNameKey", optional=true)
    public static native NSString KeyName();
    @GlobalValue(symbol="NSURLLocalizedNameKey", optional=true)
    public static native NSString KeyLocalizedName();
    @GlobalValue(symbol="NSURLIsRegularFileKey", optional=true)
    public static native NSString KeyIsRegularFile();
    @GlobalValue(symbol="NSURLIsDirectoryKey", optional=true)
    public static native NSString KeyIsDirectory();
    @GlobalValue(symbol="NSURLIsSymbolicLinkKey", optional=true)
    public static native NSString KeyIsSymbolicLink();
    @GlobalValue(symbol="NSURLIsVolumeKey", optional=true)
    public static native NSString KeyIsVolume();
    @GlobalValue(symbol="NSURLIsPackageKey", optional=true)
    public static native NSString KeyIsPackage();
    @GlobalValue(symbol="NSURLIsSystemImmutableKey", optional=true)
    public static native NSString KeyIsSystemImmutable();
    @GlobalValue(symbol="NSURLIsUserImmutableKey", optional=true)
    public static native NSString KeyIsUserImmutable();
    @GlobalValue(symbol="NSURLIsHiddenKey", optional=true)
    public static native NSString KeyIsHidden();
    @GlobalValue(symbol="NSURLHasHiddenExtensionKey", optional=true)
    public static native NSString KeyHasHiddenExtension();
    @GlobalValue(symbol="NSURLCreationDateKey", optional=true)
    public static native NSString KeyCreationDate();
    @GlobalValue(symbol="NSURLContentAccessDateKey", optional=true)
    public static native NSString KeyContentAccessDate();
    @GlobalValue(symbol="NSURLContentModificationDateKey", optional=true)
    public static native NSString KeyContentModificationDate();
    @GlobalValue(symbol="NSURLAttributeModificationDateKey", optional=true)
    public static native NSString KeyAttributeModificationDate();
    @GlobalValue(symbol="NSURLLinkCountKey", optional=true)
    public static native NSString KeyLinkCount();
    @GlobalValue(symbol="NSURLParentDirectoryURLKey", optional=true)
    public static native NSString KeyParentDirectoryURL();
    @GlobalValue(symbol="NSURLVolumeURLKey", optional=true)
    public static native NSString KeyVolumeURL();
    @GlobalValue(symbol="NSURLTypeIdentifierKey", optional=true)
    public static native NSString KeyTypeIdentifier();
    @GlobalValue(symbol="NSURLLocalizedTypeDescriptionKey", optional=true)
    public static native NSString KeyLocalizedTypeDescription();
    @GlobalValue(symbol="NSURLLabelNumberKey", optional=true)
    public static native NSString KeyLabelNumber();
    @GlobalValue(symbol="NSURLLabelColorKey", optional=true)
    public static native NSString KeyLabelColor();
    @GlobalValue(symbol="NSURLLocalizedLabelKey", optional=true)
    public static native NSString KeyLocalizedLabel();
    @GlobalValue(symbol="NSURLEffectiveIconKey", optional=true)
    public static native NSString KeyEffectiveIcon();
    @GlobalValue(symbol="NSURLCustomIconKey", optional=true)
    public static native NSString KeyCustomIcon();
    @GlobalValue(symbol="NSURLFileResourceIdentifierKey", optional=true)
    public static native NSString KeyFileResourceIdentifier();
    @GlobalValue(symbol="NSURLVolumeIdentifierKey", optional=true)
    public static native NSString KeyVolumeIdentifier();
    @GlobalValue(symbol="NSURLPreferredIOBlockSizeKey", optional=true)
    public static native NSString KeyPreferredIOBlockSize();
    @GlobalValue(symbol="NSURLIsReadableKey", optional=true)
    public static native NSString KeyIsReadable();
    @GlobalValue(symbol="NSURLIsWritableKey", optional=true)
    public static native NSString KeyIsWritable();
    @GlobalValue(symbol="NSURLIsExecutableKey", optional=true)
    public static native NSString KeyIsExecutable();
    @GlobalValue(symbol="NSURLFileSecurityKey", optional=true)
    public static native NSString KeyFileSecurity();
    @GlobalValue(symbol="NSURLIsExcludedFromBackupKey", optional=true)
    public static native NSString KeyIsExcludedFromBackup();
    @GlobalValue(symbol="NSURLTagNamesKey", optional=true)
    public static native NSString KeyTagNames();
    @GlobalValue(symbol="NSURLPathKey", optional=true)
    public static native NSString KeyPath();
    @GlobalValue(symbol="NSURLIsMountTriggerKey", optional=true)
    public static native NSString KeyIsMountTrigger();
    @GlobalValue(symbol="NSURLFileResourceTypeKey", optional=true)
    public static native NSString KeyFileResourceType();
    @GlobalValue(symbol="NSURLFileResourceTypeNamedPipe", optional=true)
    public static native String FileResourceTypeNamedPipe();
    @GlobalValue(symbol="NSURLFileResourceTypeCharacterSpecial", optional=true)
    public static native String FileResourceTypeCharacterSpecial();
    @GlobalValue(symbol="NSURLFileResourceTypeDirectory", optional=true)
    public static native String FileResourceTypeDirectory();
    @GlobalValue(symbol="NSURLFileResourceTypeBlockSpecial", optional=true)
    public static native String FileResourceTypeBlockSpecial();
    @GlobalValue(symbol="NSURLFileResourceTypeRegular", optional=true)
    public static native String FileResourceTypeRegular();
    @GlobalValue(symbol="NSURLFileResourceTypeSymbolicLink", optional=true)
    public static native String FileResourceTypeSymbolicLink();
    @GlobalValue(symbol="NSURLFileResourceTypeSocket", optional=true)
    public static native String FileResourceTypeSocket();
    @GlobalValue(symbol="NSURLFileResourceTypeUnknown", optional=true)
    public static native String FileResourceTypeUnknown();
    @GlobalValue(symbol="NSURLFileSizeKey", optional=true)
    public static native NSString KeyFileSize();
    @GlobalValue(symbol="NSURLFileAllocatedSizeKey", optional=true)
    public static native NSString KeyFileAllocatedSize();
    @GlobalValue(symbol="NSURLTotalFileSizeKey", optional=true)
    public static native NSString KeyTotalFileSize();
    @GlobalValue(symbol="NSURLTotalFileAllocatedSizeKey", optional=true)
    public static native NSString KeyTotalFileAllocatedSize();
    @GlobalValue(symbol="NSURLIsAliasFileKey", optional=true)
    public static native NSString KeyIsAliasFile();
    @GlobalValue(symbol="NSURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native NSString KeyVolumeLocalizedFormatDescription();
    @GlobalValue(symbol="NSURLVolumeTotalCapacityKey", optional=true)
    public static native NSString KeyVolumeTotalCapacity();
    @GlobalValue(symbol="NSURLVolumeAvailableCapacityKey", optional=true)
    public static native NSString KeyVolumeAvailableCapacity();
    @GlobalValue(symbol="NSURLVolumeResourceCountKey", optional=true)
    public static native NSString KeyVolumeResourceCount();
    @GlobalValue(symbol="NSURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native NSString KeyVolumeSupportsPersistentIDs();
    @GlobalValue(symbol="NSURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native NSString KeyVolumeSupportsSymbolicLinks();
    @GlobalValue(symbol="NSURLVolumeSupportsHardLinksKey", optional=true)
    public static native NSString KeyVolumeSupportsHardLinks();
    @GlobalValue(symbol="NSURLVolumeSupportsJournalingKey", optional=true)
    public static native NSString KeyVolumeSupportsJournaling();
    @GlobalValue(symbol="NSURLVolumeIsJournalingKey", optional=true)
    public static native NSString KeyVolumeIsJournaling();
    @GlobalValue(symbol="NSURLVolumeSupportsSparseFilesKey", optional=true)
    public static native NSString KeyVolumeSupportsSparseFiles();
    @GlobalValue(symbol="NSURLVolumeSupportsZeroRunsKey", optional=true)
    public static native NSString KeyVolumeSupportsZeroRuns();
    @GlobalValue(symbol="NSURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native NSString KeyVolumeSupportsCaseSensitiveNames();
    @GlobalValue(symbol="NSURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native NSString KeyVolumeSupportsCasePreservedNames();
    @GlobalValue(symbol="NSURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native NSString KeyVolumeSupportsRootDirectoryDates();
    @GlobalValue(symbol="NSURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native NSString KeyVolumeSupportsVolumeSizes();
    @GlobalValue(symbol="NSURLVolumeSupportsRenamingKey", optional=true)
    public static native NSString KeyVolumeSupportsRenaming();
    @GlobalValue(symbol="NSURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native NSString KeyVolumeSupportsAdvisoryFileLocking();
    @GlobalValue(symbol="NSURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native NSString KeyVolumeSupportsExtendedSecurity();
    @GlobalValue(symbol="NSURLVolumeIsBrowsableKey", optional=true)
    public static native NSString KeyVolumeIsBrowsable();
    @GlobalValue(symbol="NSURLVolumeMaximumFileSizeKey", optional=true)
    public static native NSString KeyVolumeMaximumFileSize();
    @GlobalValue(symbol="NSURLVolumeIsEjectableKey", optional=true)
    public static native NSString KeyVolumeIsEjectable();
    @GlobalValue(symbol="NSURLVolumeIsRemovableKey", optional=true)
    public static native NSString KeyVolumeIsRemovable();
    @GlobalValue(symbol="NSURLVolumeIsInternalKey", optional=true)
    public static native NSString KeyVolumeIsInternal();
    @GlobalValue(symbol="NSURLVolumeIsAutomountedKey", optional=true)
    public static native NSString KeyVolumeIsAutomounted();
    @GlobalValue(symbol="NSURLVolumeIsLocalKey", optional=true)
    public static native NSString KeyVolumeIsLocal();
    @GlobalValue(symbol="NSURLVolumeIsReadOnlyKey", optional=true)
    public static native NSString KeyVolumeIsReadOnly();
    @GlobalValue(symbol="NSURLVolumeCreationDateKey", optional=true)
    public static native NSString KeyVolumeCreationDate();
    @GlobalValue(symbol="NSURLVolumeURLForRemountingKey", optional=true)
    public static native NSString KeyVolumeURLForRemounting();
    @GlobalValue(symbol="NSURLVolumeUUIDStringKey", optional=true)
    public static native NSString KeyVolumeUUIDString();
    @GlobalValue(symbol="NSURLVolumeNameKey", optional=true)
    public static native NSString KeyVolumeName();
    @GlobalValue(symbol="NSURLVolumeLocalizedNameKey", optional=true)
    public static native NSString KeyVolumeLocalizedName();
    @GlobalValue(symbol="NSURLIsUbiquitousItemKey", optional=true)
    public static native NSString KeyIsUbiquitousItem();
    @GlobalValue(symbol="NSURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native NSString KeyUbiquitousItemHasUnresolvedConflicts();
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousItemIsDownloaded();
    @GlobalValue(symbol="NSURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native NSString KeyUbiquitousItemIsDownloading();
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadedKey", optional=true)
    public static native NSString KeyUbiquitousItemIsUploaded();
    @GlobalValue(symbol="NSURLUbiquitousItemIsUploadingKey", optional=true)
    public static native NSString KeyUbiquitousItemIsUploading();
    @GlobalValue(symbol="NSURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native NSString KeyUbiquitousItemPercentDownloaded();
    @GlobalValue(symbol="NSURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native NSString KeyUbiquitousItemPercentUploaded();
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native NSString KeyUbiquitousItemDownloadingStatus();
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousItemDownloadingError();
    @GlobalValue(symbol="NSURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native NSString KeyUbiquitousItemUploadingError();
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native String UbiquitousItemDownloadingStatusNotDownloaded();
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native String UbiquitousItemDownloadingStatusDownloaded();
    @GlobalValue(symbol="NSURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native String UbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="NSURLErrorDomain", optional=true)
    public static native String ErrorDomainURL();
    
    @Method(selector = "initWithScheme:host:path:")
    protected native @Pointer long initWithScheme$host$path$(String scheme, String host, String path);
    @Method(selector = "initFileURLWithPath:isDirectory:")
    protected native @Pointer long initFileURLWithPath$isDirectory$(String path, boolean isDir);
    @Method(selector = "initFileURLWithPath:")
    protected native @Pointer long initFileURLWithPath$(String path);
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String URLString);
    @Method(selector = "absoluteString")
    public native String absoluteString();
    @Method(selector = "relativeString")
    public native String relativeString();
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
    @Method(selector = "getFileSystemRepresentation:maxLength:")
    public native boolean getFileSystemRepresentation$maxLength$(BytePtr buffer, @MachineSizedUInt long maxBufferLength);
    @Method(selector = "fileSystemRepresentation")
    public native BytePtr fileSystemRepresentation();
    @Method(selector = "checkResourceIsReachableAndReturnError:")
    public native boolean checkResourceIsReachableAndReturnError$(NSError.NSErrorPtr error);
    @Method(selector = "getResourceValue:forKey:error:")
    public native boolean getResourceValue$forKey$error$(NSObject value, String key, NSError.NSErrorPtr error);
    @Method(selector = "resourceValuesForKeys:error:")
    public native NSDictionary<?, ?> resourceValuesForKeys$error$(NSArray<?> keys, NSError.NSErrorPtr error);
    @Method(selector = "setResourceValue:forKey:error:")
    public native boolean setResourceValue$forKey$error$(NSObject value, String key, NSError.NSErrorPtr error);
    @Method(selector = "setResourceValues:error:")
    public native boolean setResourceValues$error$(NSDictionary<?, ?> keyedValues, NSError.NSErrorPtr error);
    @Method(selector = "removeCachedResourceValueForKey:")
    public native void removeCachedResourceValueForKey$(String key);
    @Method(selector = "removeAllCachedResourceValues")
    public native void removeAllCachedResourceValues();
    @Method(selector = "setTemporaryResourceValue:forKey:")
    public native void setTemporaryResourceValue$forKey$(NSObject value, String key);
    @Method(selector = "resourceValuesForKeys:fromBookmarkData:")
    public static native NSDictionary<?, ?> resourceValuesForKeys$fromBookmarkData$(NSArray<?> keys, NSData bookmarkData);
    @Method(selector = "pathComponents")
    public native NSArray<?> pathComponents();
    @Method(selector = "lastPathComponent")
    public native String lastPathComponent();
    @Method(selector = "pathExtension")
    public native String pathExtension();
    /*</methods>*/
}
