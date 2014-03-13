/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURL/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFURLPtr extends Ptr<CFURL, CFURLPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFURL.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFURL() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFURLKeysOfUnsetValuesKey")
    public static native CFString KeyKeysOfUnsetValues();
    @GlobalValue(symbol="kCFURLNameKey")
    public static native CFString KeyName();
    @GlobalValue(symbol="kCFURLLocalizedNameKey")
    public static native CFString KeyLocalizedName();
    @GlobalValue(symbol="kCFURLIsRegularFileKey")
    public static native CFString KeyIsRegularFile();
    @GlobalValue(symbol="kCFURLIsDirectoryKey")
    public static native CFString KeyIsDirectory();
    @GlobalValue(symbol="kCFURLIsSymbolicLinkKey")
    public static native CFString KeyIsSymbolicLink();
    @GlobalValue(symbol="kCFURLIsVolumeKey")
    public static native CFString KeyIsVolume();
    @GlobalValue(symbol="kCFURLIsPackageKey")
    public static native CFString KeyIsPackage();
    @GlobalValue(symbol="kCFURLIsSystemImmutableKey")
    public static native CFString KeyIsSystemImmutable();
    @GlobalValue(symbol="kCFURLIsUserImmutableKey")
    public static native CFString KeyIsUserImmutable();
    @GlobalValue(symbol="kCFURLIsHiddenKey")
    public static native CFString KeyIsHidden();
    @GlobalValue(symbol="kCFURLHasHiddenExtensionKey")
    public static native CFString KeyHasHiddenExtension();
    @GlobalValue(symbol="kCFURLCreationDateKey")
    public static native CFString KeyCreationDate();
    @GlobalValue(symbol="kCFURLContentAccessDateKey")
    public static native CFString KeyContentAccessDate();
    @GlobalValue(symbol="kCFURLContentModificationDateKey")
    public static native CFString KeyContentModificationDate();
    @GlobalValue(symbol="kCFURLAttributeModificationDateKey")
    public static native CFString KeyAttributeModificationDate();
    @GlobalValue(symbol="kCFURLLinkCountKey")
    public static native CFString KeyLinkCount();
    @GlobalValue(symbol="kCFURLParentDirectoryURLKey")
    public static native CFString KeyParentDirectoryURL();
    @GlobalValue(symbol="kCFURLVolumeURLKey")
    public static native CFString KeyVolumeURL();
    @GlobalValue(symbol="kCFURLTypeIdentifierKey")
    public static native CFString KeyTypeIdentifier();
    @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey")
    public static native CFString KeyLocalizedTypeDescription();
    @GlobalValue(symbol="kCFURLLabelNumberKey")
    public static native CFString KeyLabelNumber();
    @GlobalValue(symbol="kCFURLLabelColorKey")
    public static native CFString KeyLabelColor();
    @GlobalValue(symbol="kCFURLLocalizedLabelKey")
    public static native CFString KeyLocalizedLabel();
    @GlobalValue(symbol="kCFURLEffectiveIconKey")
    public static native CFString KeyEffectiveIcon();
    @GlobalValue(symbol="kCFURLCustomIconKey")
    public static native CFString KeyCustomIcon();
    @GlobalValue(symbol="kCFURLFileResourceIdentifierKey")
    public static native CFString KeyFileResourceIdentifier();
    @GlobalValue(symbol="kCFURLVolumeIdentifierKey")
    public static native CFString KeyVolumeIdentifier();
    @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey")
    public static native CFString KeyPreferredIOBlockSize();
    @GlobalValue(symbol="kCFURLIsReadableKey")
    public static native CFString KeyIsReadable();
    @GlobalValue(symbol="kCFURLIsWritableKey")
    public static native CFString KeyIsWritable();
    @GlobalValue(symbol="kCFURLIsExecutableKey")
    public static native CFString KeyIsExecutable();
    @GlobalValue(symbol="kCFURLFileSecurityKey")
    public static native CFString KeyFileSecurity();
    @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey")
    public static native CFString KeyIsExcludedFromBackup();
    @GlobalValue(symbol="kCFURLTagNamesKey")
    public static native CFString KeyTagNames();
    @GlobalValue(symbol="kCFURLPathKey")
    public static native CFString KeyPath();
    @GlobalValue(symbol="kCFURLIsMountTriggerKey")
    public static native CFString KeyIsMountTrigger();
    @GlobalValue(symbol="kCFURLFileResourceTypeKey")
    public static native CFString KeyFileResourceType();
    @GlobalValue(symbol="kCFURLFileResourceTypeNamedPipe")
    public static native CFString FileResourceTypeNamedPipe();
    @GlobalValue(symbol="kCFURLFileResourceTypeCharacterSpecial")
    public static native CFString FileResourceTypeCharacterSpecial();
    @GlobalValue(symbol="kCFURLFileResourceTypeDirectory")
    public static native CFString FileResourceTypeDirectory();
    @GlobalValue(symbol="kCFURLFileResourceTypeBlockSpecial")
    public static native CFString FileResourceTypeBlockSpecial();
    @GlobalValue(symbol="kCFURLFileResourceTypeRegular")
    public static native CFString FileResourceTypeRegular();
    @GlobalValue(symbol="kCFURLFileResourceTypeSymbolicLink")
    public static native CFString FileResourceTypeSymbolicLink();
    @GlobalValue(symbol="kCFURLFileResourceTypeSocket")
    public static native CFString FileResourceTypeSocket();
    @GlobalValue(symbol="kCFURLFileResourceTypeUnknown")
    public static native CFString FileResourceTypeUnknown();
    @GlobalValue(symbol="kCFURLFileSizeKey")
    public static native CFString KeyFileSize();
    @GlobalValue(symbol="kCFURLFileAllocatedSizeKey")
    public static native CFString KeyFileAllocatedSize();
    @GlobalValue(symbol="kCFURLTotalFileSizeKey")
    public static native CFString KeyTotalFileSize();
    @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey")
    public static native CFString KeyTotalFileAllocatedSize();
    @GlobalValue(symbol="kCFURLIsAliasFileKey")
    public static native CFString KeyIsAliasFile();
    @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey")
    public static native CFString KeyVolumeLocalizedFormatDescription();
    @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey")
    public static native CFString KeyVolumeTotalCapacity();
    @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey")
    public static native CFString KeyVolumeAvailableCapacity();
    @GlobalValue(symbol="kCFURLVolumeResourceCountKey")
    public static native CFString KeyVolumeResourceCount();
    @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey")
    public static native CFString KeyVolumeSupportsPersistentIDs();
    @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey")
    public static native CFString KeyVolumeSupportsSymbolicLinks();
    @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey")
    public static native CFString KeyVolumeSupportsHardLinks();
    @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey")
    public static native CFString KeyVolumeSupportsJournaling();
    @GlobalValue(symbol="kCFURLVolumeIsJournalingKey")
    public static native CFString KeyVolumeIsJournaling();
    @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey")
    public static native CFString KeyVolumeSupportsSparseFiles();
    @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey")
    public static native CFString KeyVolumeSupportsZeroRuns();
    @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey")
    public static native CFString KeyVolumeSupportsCaseSensitiveNames();
    @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey")
    public static native CFString KeyVolumeSupportsCasePreservedNames();
    @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey")
    public static native CFString KeyVolumeSupportsRootDirectoryDates();
    @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey")
    public static native CFString KeyVolumeSupportsVolumeSizes();
    @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey")
    public static native CFString KeyVolumeSupportsRenaming();
    @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey")
    public static native CFString KeyVolumeSupportsAdvisoryFileLocking();
    @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey")
    public static native CFString KeyVolumeSupportsExtendedSecurity();
    @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey")
    public static native CFString KeyVolumeIsBrowsable();
    @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey")
    public static native CFString KeyVolumeMaximumFileSize();
    @GlobalValue(symbol="kCFURLVolumeIsEjectableKey")
    public static native CFString KeyVolumeIsEjectable();
    @GlobalValue(symbol="kCFURLVolumeIsRemovableKey")
    public static native CFString KeyVolumeIsRemovable();
    @GlobalValue(symbol="kCFURLVolumeIsInternalKey")
    public static native CFString KeyVolumeIsInternal();
    @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey")
    public static native CFString KeyVolumeIsAutomounted();
    @GlobalValue(symbol="kCFURLVolumeIsLocalKey")
    public static native CFString KeyVolumeIsLocal();
    @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey")
    public static native CFString KeyVolumeIsReadOnly();
    @GlobalValue(symbol="kCFURLVolumeCreationDateKey")
    public static native CFString KeyVolumeCreationDate();
    @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey")
    public static native CFString KeyVolumeURLForRemounting();
    @GlobalValue(symbol="kCFURLVolumeUUIDStringKey")
    public static native CFString KeyVolumeUUIDString();
    @GlobalValue(symbol="kCFURLVolumeNameKey")
    public static native CFString KeyVolumeName();
    @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey")
    public static native CFString KeyVolumeLocalizedName();
    @GlobalValue(symbol="kCFURLIsUbiquitousItemKey")
    public static native CFString KeyIsUbiquitousItem();
    @GlobalValue(symbol="kCFURLUbiquitousItemHasUnresolvedConflictsKey")
    public static native CFString KeyUbiquitousItemHasUnresolvedConflicts();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadedKey")
    public static native CFString KeyUbiquitousItemIsDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadingKey")
    public static native CFString KeyUbiquitousItemIsDownloading();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadedKey")
    public static native CFString KeyUbiquitousItemIsUploaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadingKey")
    public static native CFString KeyUbiquitousItemIsUploading();
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentDownloadedKey")
    public static native CFString KeyUbiquitousItemPercentDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentUploadedKey")
    public static native CFString KeyUbiquitousItemPercentUploaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusKey")
    public static native CFString KeyUbiquitousItemDownloadingStatus();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingErrorKey")
    public static native CFString KeyUbiquitousItemDownloadingError();
    @GlobalValue(symbol="kCFURLUbiquitousItemUploadingErrorKey")
    public static native CFString KeyUbiquitousItemUploadingError();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded")
    public static native CFString UbiquitousItemDownloadingStatusNotDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded")
    public static native CFString UbiquitousItemDownloadingStatusDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent")
    public static native CFString UbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="kCFURLFileExists")
    public static native CFString FileExists();
    @GlobalValue(symbol="kCFURLFileDirectoryContents")
    public static native CFString FileDirectoryContents();
    @GlobalValue(symbol="kCFURLFileLength")
    public static native CFString FileLength();
    @GlobalValue(symbol="kCFURLFileLastModificationTime")
    public static native CFString FileLastModificationTime();
    @GlobalValue(symbol="kCFURLFilePOSIXMode")
    public static native CFString FilePOSIXMode();
    @GlobalValue(symbol="kCFURLFileOwnerID")
    public static native CFString FileOwnerID();
    @GlobalValue(symbol="kCFURLHTTPStatusCode")
    public static native CFString HTTPStatusCode();
    @GlobalValue(symbol="kCFURLHTTPStatusLine")
    public static native CFString HTTPStatusLine();
    
    @Bridge(symbol="CFURLGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFURLCreateWithBytes")
    public static native CFURL createWithBytes(CFAllocator allocator, BytePtr URLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL);
    @Bridge(symbol="CFURLCreateData")
    public static native CFData createData(CFAllocator allocator, CFURL url, int encoding, boolean escapeWhitespace);
    @Bridge(symbol="CFURLCreateWithString")
    public static native CFURL createWithString(CFAllocator allocator, CFString URLString, CFURL baseURL);
    @Bridge(symbol="CFURLCreateAbsoluteURLWithBytes")
    public static native CFURL createAbsoluteURLWithBytes(CFAllocator alloc, BytePtr relativeURLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL, boolean useCompatibilityMode);
    @Bridge(symbol="CFURLCreateWithFileSystemPath")
    public static native CFURL createWithFileSystemPath(CFAllocator allocator, CFString filePath, CFURLPathStyle pathStyle, boolean isDirectory);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentation")
    public static native CFURL createFromFileSystemRepresentation(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory);
    @Bridge(symbol="CFURLCreateWithFileSystemPathRelativeToBase")
    public static native CFURL createWithFileSystemPathRelativeToBase(CFAllocator allocator, CFString filePath, CFURLPathStyle pathStyle, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentationRelativeToBase")
    public static native CFURL createFromFileSystemRepresentationRelativeToBase(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLGetFileSystemRepresentation")
    public native boolean getFileSystemRepresentation(boolean resolveAgainstBase, BytePtr buffer, @MachineSizedSInt long maxBufLen);
    @Bridge(symbol="CFURLCopyAbsoluteURL")
    public native CFURL copyAbsoluteURL();
    @Bridge(symbol="CFURLGetString")
    public native CFString getString();
    @Bridge(symbol="CFURLGetBaseURL")
    public native CFURL getBaseURL();
    @Bridge(symbol="CFURLCanBeDecomposed")
    public native boolean canBeDecomposed();
    @Bridge(symbol="CFURLCopyScheme")
    public native CFString copyScheme();
    @Bridge(symbol="CFURLCopyNetLocation")
    public native CFString copyNetLocation();
    @Bridge(symbol="CFURLCopyPath")
    public native CFString copyPath();
    @Bridge(symbol="CFURLCopyStrictPath")
    public native CFString copyStrictPath(BytePtr isAbsolute);
    @Bridge(symbol="CFURLCopyFileSystemPath")
    public native CFString copyFileSystemPath(CFURLPathStyle pathStyle);
    @Bridge(symbol="CFURLHasDirectoryPath")
    public native boolean hasDirectoryPath();
    @Bridge(symbol="CFURLCopyResourceSpecifier")
    public native CFString copyResourceSpecifier();
    @Bridge(symbol="CFURLCopyHostName")
    public native CFString copyHostName();
    @Bridge(symbol="CFURLGetPortNumber")
    public native int getPortNumber();
    @Bridge(symbol="CFURLCopyUserName")
    public native CFString copyUserName();
    @Bridge(symbol="CFURLCopyPassword")
    public native CFString copyPassword();
    @Bridge(symbol="CFURLCopyParameterString")
    public native CFString copyParameterString(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyQueryString")
    public native CFString copyQueryString(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyFragment")
    public native CFString copyFragment(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyLastPathComponent")
    public native CFString copyLastPathComponent();
    @Bridge(symbol="CFURLCopyPathExtension")
    public native CFString copyPathExtension();
    @Bridge(symbol="CFURLCreateCopyAppendingPathComponent")
    public static native CFURL createCopyAppendingPathComponent(CFAllocator allocator, CFURL url, CFString pathComponent, boolean isDirectory);
    @Bridge(symbol="CFURLCreateCopyDeletingLastPathComponent")
    public static native CFURL createCopyDeletingLastPathComponent(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLCreateCopyAppendingPathExtension")
    public static native CFURL createCopyAppendingPathExtension(CFAllocator allocator, CFURL url, CFString extension);
    @Bridge(symbol="CFURLCreateCopyDeletingPathExtension")
    public static native CFURL createCopyDeletingPathExtension(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLGetBytes")
    public native @MachineSizedSInt long getBytes(BytePtr buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFURLGetByteRangeForComponent")
    public native @ByVal CFRange getByteRangeForComponent(CFURLComponentType component, CFRange rangeIncludingSeparators);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapes")
    public static native CFString createStringByReplacingPercentEscapes(CFAllocator allocator, CFString originalString, CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapesUsingEncoding")
    public static native CFString createStringByReplacingPercentEscapesUsingEncoding(CFAllocator allocator, CFString origString, CFString charsToLeaveEscaped, int encoding);
    @Bridge(symbol="CFURLCreateStringByAddingPercentEscapes")
    public static native CFString createStringByAddingPercentEscapes(CFAllocator allocator, CFString originalString, CFString charactersToLeaveUnescaped, CFString legalURLCharactersToBeEscaped, int encoding);
    @Bridge(symbol="CFURLIsFileReferenceURL")
    public native boolean isFileReferenceURL();
    @Bridge(symbol="CFURLCreateFileReferenceURL")
    public static native CFURL createFileReferenceURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateFilePathURL")
    public static native CFURL createFilePathURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCopyResourcePropertyForKey")
    public native boolean copyResourcePropertyForKey(CFString key, VoidPtr propertyValueTypeRefPtr, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCopyResourcePropertiesForKeys")
    public native CFDictionary copyResourcePropertiesForKeys(CFArray keys, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLSetResourcePropertyForKey")
    public native boolean setResourcePropertyForKey(CFString key, CFType propertyValue, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLSetResourcePropertiesForKeys")
    public native boolean setResourcePropertiesForKeys(CFDictionary keyedPropertyValues, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLClearResourcePropertyCacheForKey")
    public native void clearResourcePropertyCacheForKey(CFString key);
    @Bridge(symbol="CFURLClearResourcePropertyCache")
    public native void clearResourcePropertyCache();
    @Bridge(symbol="CFURLSetTemporaryResourcePropertyForKey")
    public native void setTemporaryResourcePropertyForKey(CFString key, CFType propertyValue);
    @Bridge(symbol="CFURLResourceIsReachable")
    public native boolean resourceIsReachable(CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateBookmarkData")
    public static native CFData createBookmarkData(CFAllocator allocator, CFURL url, CFURLBookmarkCreationOptions options, CFArray resourcePropertiesToInclude, CFURL relativeToURL, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateByResolvingBookmarkData")
    public static native CFURL createByResolvingBookmarkData(CFAllocator allocator, CFData bookmark, CFURLBookmarkResolutionOptions options, CFURL relativeToURL, CFArray resourcePropertiesToInclude, BytePtr isStale, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateResourcePropertiesForKeysFromBookmarkData")
    public static native CFDictionary createResourcePropertiesForKeysFromBookmarkData(CFAllocator allocator, CFArray resourcePropertiesToReturn, CFData bookmark);
    @Bridge(symbol="CFURLCreateResourcePropertyForKeyFromBookmarkData")
    public static native CFType createResourcePropertyForKeyFromBookmarkData(CFAllocator allocator, CFString resourcePropertyKey, CFData bookmark);
    @Bridge(symbol="CFURLCreateBookmarkDataFromFile")
    public static native CFData createBookmarkDataFromFile(CFAllocator allocator, CFURL fileURL, CFError.CFErrorPtr errorRef);
    @Bridge(symbol="CFURLWriteBookmarkDataToFile")
    public static native boolean writeBookmarkDataToFile(CFData bookmarkRef, CFURL fileURL, @MachineSizedUInt long options, CFError.CFErrorPtr errorRef);
    @Bridge(symbol="CFURLCreateBookmarkDataFromAliasRecord")
    public static native CFData createBookmarkDataFromAliasRecord(CFAllocator allocatorRef, CFData aliasRecordDataRef);
    @Bridge(symbol="CFURLStartAccessingSecurityScopedResource")
    public native boolean startAccessingSecurityScopedResource();
    @Bridge(symbol="CFURLStopAccessingSecurityScopedResource")
    public native void stopAccessingSecurityScopedResource();
    @Bridge(symbol="CFURLCreateDataAndPropertiesFromResource")
    public static native boolean createDataAndPropertiesFromResource(CFAllocator alloc, CFURL url, CFData.CFDataPtr resourceData, CFDictionary.CFDictionaryPtr properties, CFArray desiredProperties, IntPtr errorCode);
    @Bridge(symbol="CFURLWriteDataAndPropertiesToResource")
    public native boolean writeDataAndPropertiesToResource(CFData dataToWrite, CFDictionary propertiesToWrite, IntPtr errorCode);
    @Bridge(symbol="CFURLDestroyResource")
    public native boolean destroyResource(IntPtr errorCode);
    @Bridge(symbol="CFURLCreatePropertyFromResource")
    public static native CFType createPropertyFromResource(CFAllocator alloc, CFURL url, CFString property, IntPtr errorCode);
    @Bridge(symbol="CFCopyHomeDirectoryURL")
    public static native CFURL copyHomeDirectoryURL();
    @Bridge(symbol="CFURLEnumeratorCreateForDirectoryURL")
    public static native CFURLEnumerator enumeratorCreateForDirectoryURL(CFAllocator alloc, CFURL directoryURL, CFURLEnumeratorOptions option, CFArray propertyKeys);
    @Bridge(symbol="CFURLEnumeratorCreateForMountedVolumes")
    public static native CFURLEnumerator enumeratorCreateForMountedVolumes(CFAllocator alloc, CFURLEnumeratorOptions option, CFArray propertyKeys);
    @Bridge(symbol="CFURLEnumeratorGetNextURL")
    public static native CFURLEnumeratorResult enumeratorGetNextURL(CFURLEnumerator enumerator, CFURL.CFURLPtr url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLEnumeratorSkipDescendents")
    public static native void enumeratorSkipDescendents(CFURLEnumerator enumerator);
    @Bridge(symbol="CFURLEnumeratorGetDescendentLevel")
    public static native @MachineSizedSInt long enumeratorGetDescendentLevel(CFURLEnumerator enumerator);
    @Bridge(symbol="CFURLEnumeratorGetSourceDidChange")
    public static native boolean enumeratorGetSourceDidChange(CFURLEnumerator enumerator);
    /*</methods>*/
}
