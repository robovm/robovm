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
    @GlobalValue(symbol="kCFURLKeysOfUnsetValuesKey", optional=true)
    public static native CFString KeyKeysOfUnsetValues();
    @GlobalValue(symbol="kCFURLNameKey", optional=true)
    public static native CFString KeyName();
    @GlobalValue(symbol="kCFURLLocalizedNameKey", optional=true)
    public static native CFString KeyLocalizedName();
    @GlobalValue(symbol="kCFURLIsRegularFileKey", optional=true)
    public static native CFString KeyIsRegularFile();
    @GlobalValue(symbol="kCFURLIsDirectoryKey", optional=true)
    public static native CFString KeyIsDirectory();
    @GlobalValue(symbol="kCFURLIsSymbolicLinkKey", optional=true)
    public static native CFString KeyIsSymbolicLink();
    @GlobalValue(symbol="kCFURLIsVolumeKey", optional=true)
    public static native CFString KeyIsVolume();
    @GlobalValue(symbol="kCFURLIsPackageKey", optional=true)
    public static native CFString KeyIsPackage();
    @GlobalValue(symbol="kCFURLIsSystemImmutableKey", optional=true)
    public static native CFString KeyIsSystemImmutable();
    @GlobalValue(symbol="kCFURLIsUserImmutableKey", optional=true)
    public static native CFString KeyIsUserImmutable();
    @GlobalValue(symbol="kCFURLIsHiddenKey", optional=true)
    public static native CFString KeyIsHidden();
    @GlobalValue(symbol="kCFURLHasHiddenExtensionKey", optional=true)
    public static native CFString KeyHasHiddenExtension();
    @GlobalValue(symbol="kCFURLCreationDateKey", optional=true)
    public static native CFString KeyCreationDate();
    @GlobalValue(symbol="kCFURLContentAccessDateKey", optional=true)
    public static native CFString KeyContentAccessDate();
    @GlobalValue(symbol="kCFURLContentModificationDateKey", optional=true)
    public static native CFString KeyContentModificationDate();
    @GlobalValue(symbol="kCFURLAttributeModificationDateKey", optional=true)
    public static native CFString KeyAttributeModificationDate();
    @GlobalValue(symbol="kCFURLLinkCountKey", optional=true)
    public static native CFString KeyLinkCount();
    @GlobalValue(symbol="kCFURLParentDirectoryURLKey", optional=true)
    public static native CFString KeyParentDirectoryURL();
    @GlobalValue(symbol="kCFURLVolumeURLKey", optional=true)
    public static native CFString KeyVolumeURL();
    @GlobalValue(symbol="kCFURLTypeIdentifierKey", optional=true)
    public static native CFString KeyTypeIdentifier();
    @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey", optional=true)
    public static native CFString KeyLocalizedTypeDescription();
    @GlobalValue(symbol="kCFURLLabelNumberKey", optional=true)
    public static native CFString KeyLabelNumber();
    @GlobalValue(symbol="kCFURLLabelColorKey", optional=true)
    public static native CFString KeyLabelColor();
    @GlobalValue(symbol="kCFURLLocalizedLabelKey", optional=true)
    public static native CFString KeyLocalizedLabel();
    @GlobalValue(symbol="kCFURLEffectiveIconKey", optional=true)
    public static native CFString KeyEffectiveIcon();
    @GlobalValue(symbol="kCFURLCustomIconKey", optional=true)
    public static native CFString KeyCustomIcon();
    @GlobalValue(symbol="kCFURLFileResourceIdentifierKey", optional=true)
    public static native CFString KeyFileResourceIdentifier();
    @GlobalValue(symbol="kCFURLVolumeIdentifierKey", optional=true)
    public static native CFString KeyVolumeIdentifier();
    @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey", optional=true)
    public static native CFString KeyPreferredIOBlockSize();
    @GlobalValue(symbol="kCFURLIsReadableKey", optional=true)
    public static native CFString KeyIsReadable();
    @GlobalValue(symbol="kCFURLIsWritableKey", optional=true)
    public static native CFString KeyIsWritable();
    @GlobalValue(symbol="kCFURLIsExecutableKey", optional=true)
    public static native CFString KeyIsExecutable();
    @GlobalValue(symbol="kCFURLFileSecurityKey", optional=true)
    public static native CFString KeyFileSecurity();
    @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey", optional=true)
    public static native CFString KeyIsExcludedFromBackup();
    @GlobalValue(symbol="kCFURLTagNamesKey", optional=true)
    public static native CFString KeyTagNames();
    @GlobalValue(symbol="kCFURLPathKey", optional=true)
    public static native CFString KeyPath();
    @GlobalValue(symbol="kCFURLIsMountTriggerKey", optional=true)
    public static native CFString KeyIsMountTrigger();
    @GlobalValue(symbol="kCFURLFileResourceTypeKey", optional=true)
    public static native CFString KeyFileResourceType();
    @GlobalValue(symbol="kCFURLFileResourceTypeNamedPipe", optional=true)
    public static native CFString FileResourceTypeNamedPipe();
    @GlobalValue(symbol="kCFURLFileResourceTypeCharacterSpecial", optional=true)
    public static native CFString FileResourceTypeCharacterSpecial();
    @GlobalValue(symbol="kCFURLFileResourceTypeDirectory", optional=true)
    public static native CFString FileResourceTypeDirectory();
    @GlobalValue(symbol="kCFURLFileResourceTypeBlockSpecial", optional=true)
    public static native CFString FileResourceTypeBlockSpecial();
    @GlobalValue(symbol="kCFURLFileResourceTypeRegular", optional=true)
    public static native CFString FileResourceTypeRegular();
    @GlobalValue(symbol="kCFURLFileResourceTypeSymbolicLink", optional=true)
    public static native CFString FileResourceTypeSymbolicLink();
    @GlobalValue(symbol="kCFURLFileResourceTypeSocket", optional=true)
    public static native CFString FileResourceTypeSocket();
    @GlobalValue(symbol="kCFURLFileResourceTypeUnknown", optional=true)
    public static native CFString FileResourceTypeUnknown();
    @GlobalValue(symbol="kCFURLFileSizeKey", optional=true)
    public static native CFString KeyFileSize();
    @GlobalValue(symbol="kCFURLFileAllocatedSizeKey", optional=true)
    public static native CFString KeyFileAllocatedSize();
    @GlobalValue(symbol="kCFURLTotalFileSizeKey", optional=true)
    public static native CFString KeyTotalFileSize();
    @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey", optional=true)
    public static native CFString KeyTotalFileAllocatedSize();
    @GlobalValue(symbol="kCFURLIsAliasFileKey", optional=true)
    public static native CFString KeyIsAliasFile();
    @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native CFString KeyVolumeLocalizedFormatDescription();
    @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey", optional=true)
    public static native CFString KeyVolumeTotalCapacity();
    @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey", optional=true)
    public static native CFString KeyVolumeAvailableCapacity();
    @GlobalValue(symbol="kCFURLVolumeResourceCountKey", optional=true)
    public static native CFString KeyVolumeResourceCount();
    @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native CFString KeyVolumeSupportsPersistentIDs();
    @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native CFString KeyVolumeSupportsSymbolicLinks();
    @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey", optional=true)
    public static native CFString KeyVolumeSupportsHardLinks();
    @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey", optional=true)
    public static native CFString KeyVolumeSupportsJournaling();
    @GlobalValue(symbol="kCFURLVolumeIsJournalingKey", optional=true)
    public static native CFString KeyVolumeIsJournaling();
    @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey", optional=true)
    public static native CFString KeyVolumeSupportsSparseFiles();
    @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey", optional=true)
    public static native CFString KeyVolumeSupportsZeroRuns();
    @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native CFString KeyVolumeSupportsCaseSensitiveNames();
    @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native CFString KeyVolumeSupportsCasePreservedNames();
    @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native CFString KeyVolumeSupportsRootDirectoryDates();
    @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native CFString KeyVolumeSupportsVolumeSizes();
    @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey", optional=true)
    public static native CFString KeyVolumeSupportsRenaming();
    @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native CFString KeyVolumeSupportsAdvisoryFileLocking();
    @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native CFString KeyVolumeSupportsExtendedSecurity();
    @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey", optional=true)
    public static native CFString KeyVolumeIsBrowsable();
    @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey", optional=true)
    public static native CFString KeyVolumeMaximumFileSize();
    @GlobalValue(symbol="kCFURLVolumeIsEjectableKey", optional=true)
    public static native CFString KeyVolumeIsEjectable();
    @GlobalValue(symbol="kCFURLVolumeIsRemovableKey", optional=true)
    public static native CFString KeyVolumeIsRemovable();
    @GlobalValue(symbol="kCFURLVolumeIsInternalKey", optional=true)
    public static native CFString KeyVolumeIsInternal();
    @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey", optional=true)
    public static native CFString KeyVolumeIsAutomounted();
    @GlobalValue(symbol="kCFURLVolumeIsLocalKey", optional=true)
    public static native CFString KeyVolumeIsLocal();
    @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey", optional=true)
    public static native CFString KeyVolumeIsReadOnly();
    @GlobalValue(symbol="kCFURLVolumeCreationDateKey", optional=true)
    public static native CFString KeyVolumeCreationDate();
    @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey", optional=true)
    public static native CFString KeyVolumeURLForRemounting();
    @GlobalValue(symbol="kCFURLVolumeUUIDStringKey", optional=true)
    public static native CFString KeyVolumeUUIDString();
    @GlobalValue(symbol="kCFURLVolumeNameKey", optional=true)
    public static native CFString KeyVolumeName();
    @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey", optional=true)
    public static native CFString KeyVolumeLocalizedName();
    @GlobalValue(symbol="kCFURLIsUbiquitousItemKey", optional=true)
    public static native CFString KeyIsUbiquitousItem();
    @GlobalValue(symbol="kCFURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native CFString KeyUbiquitousItemHasUnresolvedConflicts();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native CFString KeyUbiquitousItemIsDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native CFString KeyUbiquitousItemIsDownloading();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadedKey", optional=true)
    public static native CFString KeyUbiquitousItemIsUploaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadingKey", optional=true)
    public static native CFString KeyUbiquitousItemIsUploading();
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native CFString KeyUbiquitousItemPercentDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native CFString KeyUbiquitousItemPercentUploaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native CFString KeyUbiquitousItemDownloadingStatus();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native CFString KeyUbiquitousItemDownloadingError();
    @GlobalValue(symbol="kCFURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native CFString KeyUbiquitousItemUploadingError();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusNotDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusDownloaded();
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="kCFURLFileExists", optional=true)
    public static native CFString FileExists();
    @GlobalValue(symbol="kCFURLFileDirectoryContents", optional=true)
    public static native CFString FileDirectoryContents();
    @GlobalValue(symbol="kCFURLFileLength", optional=true)
    public static native CFString FileLength();
    @GlobalValue(symbol="kCFURLFileLastModificationTime", optional=true)
    public static native CFString FileLastModificationTime();
    @GlobalValue(symbol="kCFURLFilePOSIXMode", optional=true)
    public static native CFString FilePOSIXMode();
    @GlobalValue(symbol="kCFURLFileOwnerID", optional=true)
    public static native CFString FileOwnerID();
    @GlobalValue(symbol="kCFURLHTTPStatusCode", optional=true)
    public static native CFString HTTPStatusCode();
    @GlobalValue(symbol="kCFURLHTTPStatusLine", optional=true)
    public static native CFString HTTPStatusLine();
    
    @Bridge(symbol="CFURLGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFURLCreateWithBytes", optional=true)
    public static native CFURL createWithBytes(CFAllocator allocator, BytePtr URLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL);
    @Bridge(symbol="CFURLCreateData", optional=true)
    public static native CFData createData(CFAllocator allocator, CFURL url, int encoding, boolean escapeWhitespace);
    @Bridge(symbol="CFURLCreateWithString", optional=true)
    public static native CFURL createWithString(CFAllocator allocator, CFString URLString, CFURL baseURL);
    @Bridge(symbol="CFURLCreateAbsoluteURLWithBytes", optional=true)
    public static native CFURL createAbsoluteURLWithBytes(CFAllocator alloc, BytePtr relativeURLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL, boolean useCompatibilityMode);
    @Bridge(symbol="CFURLCreateWithFileSystemPath", optional=true)
    public static native CFURL createWithFileSystemPath(CFAllocator allocator, CFString filePath, CFURLPathStyle pathStyle, boolean isDirectory);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentation", optional=true)
    public static native CFURL createFromFileSystemRepresentation(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory);
    @Bridge(symbol="CFURLCreateWithFileSystemPathRelativeToBase", optional=true)
    public static native CFURL createWithFileSystemPathRelativeToBase(CFAllocator allocator, CFString filePath, CFURLPathStyle pathStyle, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentationRelativeToBase", optional=true)
    public static native CFURL createFromFileSystemRepresentationRelativeToBase(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLGetFileSystemRepresentation", optional=true)
    public native boolean getFileSystemRepresentation(boolean resolveAgainstBase, BytePtr buffer, @MachineSizedSInt long maxBufLen);
    @Bridge(symbol="CFURLCopyAbsoluteURL", optional=true)
    public native CFURL copyAbsoluteURL();
    @Bridge(symbol="CFURLGetString", optional=true)
    public native CFString getString();
    @Bridge(symbol="CFURLGetBaseURL", optional=true)
    public native CFURL getBaseURL();
    @Bridge(symbol="CFURLCanBeDecomposed", optional=true)
    public native boolean canBeDecomposed();
    @Bridge(symbol="CFURLCopyScheme", optional=true)
    public native CFString copyScheme();
    @Bridge(symbol="CFURLCopyNetLocation", optional=true)
    public native CFString copyNetLocation();
    @Bridge(symbol="CFURLCopyPath", optional=true)
    public native CFString copyPath();
    @Bridge(symbol="CFURLCopyStrictPath", optional=true)
    public native CFString copyStrictPath(BytePtr isAbsolute);
    @Bridge(symbol="CFURLCopyFileSystemPath", optional=true)
    public native CFString copyFileSystemPath(CFURLPathStyle pathStyle);
    @Bridge(symbol="CFURLHasDirectoryPath", optional=true)
    public native boolean hasDirectoryPath();
    @Bridge(symbol="CFURLCopyResourceSpecifier", optional=true)
    public native CFString copyResourceSpecifier();
    @Bridge(symbol="CFURLCopyHostName", optional=true)
    public native CFString copyHostName();
    @Bridge(symbol="CFURLGetPortNumber", optional=true)
    public native int getPortNumber();
    @Bridge(symbol="CFURLCopyUserName", optional=true)
    public native CFString copyUserName();
    @Bridge(symbol="CFURLCopyPassword", optional=true)
    public native CFString copyPassword();
    @Bridge(symbol="CFURLCopyParameterString", optional=true)
    public native CFString copyParameterString(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyQueryString", optional=true)
    public native CFString copyQueryString(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyFragment", optional=true)
    public native CFString copyFragment(CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyLastPathComponent", optional=true)
    public native CFString copyLastPathComponent();
    @Bridge(symbol="CFURLCopyPathExtension", optional=true)
    public native CFString copyPathExtension();
    @Bridge(symbol="CFURLCreateCopyAppendingPathComponent", optional=true)
    public static native CFURL createCopyAppendingPathComponent(CFAllocator allocator, CFURL url, CFString pathComponent, boolean isDirectory);
    @Bridge(symbol="CFURLCreateCopyDeletingLastPathComponent", optional=true)
    public static native CFURL createCopyDeletingLastPathComponent(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLCreateCopyAppendingPathExtension", optional=true)
    public static native CFURL createCopyAppendingPathExtension(CFAllocator allocator, CFURL url, CFString extension);
    @Bridge(symbol="CFURLCreateCopyDeletingPathExtension", optional=true)
    public static native CFURL createCopyDeletingPathExtension(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLGetBytes", optional=true)
    public native @MachineSizedSInt long getBytes(BytePtr buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFURLGetByteRangeForComponent", optional=true)
    public native @ByVal CFRange getByteRangeForComponent(CFURLComponentType component, CFRange rangeIncludingSeparators);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapes", optional=true)
    public static native CFString createStringByReplacingPercentEscapes(CFAllocator allocator, CFString originalString, CFString charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapesUsingEncoding", optional=true)
    public static native CFString createStringByReplacingPercentEscapesUsingEncoding(CFAllocator allocator, CFString origString, CFString charsToLeaveEscaped, int encoding);
    @Bridge(symbol="CFURLCreateStringByAddingPercentEscapes", optional=true)
    public static native CFString createStringByAddingPercentEscapes(CFAllocator allocator, CFString originalString, CFString charactersToLeaveUnescaped, CFString legalURLCharactersToBeEscaped, int encoding);
    @Bridge(symbol="CFURLIsFileReferenceURL", optional=true)
    public native boolean isFileReferenceURL();
    @Bridge(symbol="CFURLCreateFileReferenceURL", optional=true)
    public static native CFURL createFileReferenceURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateFilePathURL", optional=true)
    public static native CFURL createFilePathURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCopyResourcePropertyForKey", optional=true)
    public native boolean copyResourcePropertyForKey(CFString key, VoidPtr propertyValueTypeRefPtr, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCopyResourcePropertiesForKeys", optional=true)
    public native CFDictionary copyResourcePropertiesForKeys(CFArray keys, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLSetResourcePropertyForKey", optional=true)
    public native boolean setResourcePropertyForKey(CFString key, CFType propertyValue, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLSetResourcePropertiesForKeys", optional=true)
    public native boolean setResourcePropertiesForKeys(CFDictionary keyedPropertyValues, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLClearResourcePropertyCacheForKey", optional=true)
    public native void clearResourcePropertyCacheForKey(CFString key);
    @Bridge(symbol="CFURLClearResourcePropertyCache", optional=true)
    public native void clearResourcePropertyCache();
    @Bridge(symbol="CFURLSetTemporaryResourcePropertyForKey", optional=true)
    public native void setTemporaryResourcePropertyForKey(CFString key, CFType propertyValue);
    @Bridge(symbol="CFURLResourceIsReachable", optional=true)
    public native boolean resourceIsReachable(CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateBookmarkData", optional=true)
    public static native CFData createBookmarkData(CFAllocator allocator, CFURL url, CFURLBookmarkCreationOptions options, CFArray resourcePropertiesToInclude, CFURL relativeToURL, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateByResolvingBookmarkData", optional=true)
    public static native CFURL createByResolvingBookmarkData(CFAllocator allocator, CFData bookmark, CFURLBookmarkResolutionOptions options, CFURL relativeToURL, CFArray resourcePropertiesToInclude, BytePtr isStale, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLCreateResourcePropertiesForKeysFromBookmarkData", optional=true)
    public static native CFDictionary createResourcePropertiesForKeysFromBookmarkData(CFAllocator allocator, CFArray resourcePropertiesToReturn, CFData bookmark);
    @Bridge(symbol="CFURLCreateResourcePropertyForKeyFromBookmarkData", optional=true)
    public static native CFType createResourcePropertyForKeyFromBookmarkData(CFAllocator allocator, CFString resourcePropertyKey, CFData bookmark);
    @Bridge(symbol="CFURLCreateBookmarkDataFromFile", optional=true)
    public static native CFData createBookmarkDataFromFile(CFAllocator allocator, CFURL fileURL, CFError.CFErrorPtr errorRef);
    @Bridge(symbol="CFURLWriteBookmarkDataToFile", optional=true)
    public static native boolean writeBookmarkDataToFile(CFData bookmarkRef, CFURL fileURL, @MachineSizedUInt long options, CFError.CFErrorPtr errorRef);
    @Bridge(symbol="CFURLCreateBookmarkDataFromAliasRecord", optional=true)
    public static native CFData createBookmarkDataFromAliasRecord(CFAllocator allocatorRef, CFData aliasRecordDataRef);
    @Bridge(symbol="CFURLStartAccessingSecurityScopedResource", optional=true)
    public native boolean startAccessingSecurityScopedResource();
    @Bridge(symbol="CFURLStopAccessingSecurityScopedResource", optional=true)
    public native void stopAccessingSecurityScopedResource();
    @Bridge(symbol="CFURLCreateDataAndPropertiesFromResource", optional=true)
    public static native boolean createDataAndPropertiesFromResource(CFAllocator alloc, CFURL url, CFData.CFDataPtr resourceData, CFDictionary.CFDictionaryPtr properties, CFArray desiredProperties, IntPtr errorCode);
    @Bridge(symbol="CFURLWriteDataAndPropertiesToResource", optional=true)
    public native boolean writeDataAndPropertiesToResource(CFData dataToWrite, CFDictionary propertiesToWrite, IntPtr errorCode);
    @Bridge(symbol="CFURLDestroyResource", optional=true)
    public native boolean destroyResource(IntPtr errorCode);
    @Bridge(symbol="CFURLCreatePropertyFromResource", optional=true)
    public static native CFType createPropertyFromResource(CFAllocator alloc, CFURL url, CFString property, IntPtr errorCode);
    @Bridge(symbol="CFCopyHomeDirectoryURL", optional=true)
    public static native CFURL copyHomeDirectoryURL();
    @Bridge(symbol="CFURLEnumeratorCreateForDirectoryURL", optional=true)
    public static native CFURLEnumerator enumeratorCreateForDirectoryURL(CFAllocator alloc, CFURL directoryURL, CFURLEnumeratorOptions option, CFArray propertyKeys);
    @Bridge(symbol="CFURLEnumeratorCreateForMountedVolumes", optional=true)
    public static native CFURLEnumerator enumeratorCreateForMountedVolumes(CFAllocator alloc, CFURLEnumeratorOptions option, CFArray propertyKeys);
    @Bridge(symbol="CFURLEnumeratorGetNextURL", optional=true)
    public static native CFURLEnumeratorResult enumeratorGetNextURL(CFURLEnumerator enumerator, CFURL.CFURLPtr url, CFError.CFErrorPtr error);
    @Bridge(symbol="CFURLEnumeratorSkipDescendents", optional=true)
    public static native void enumeratorSkipDescendents(CFURLEnumerator enumerator);
    @Bridge(symbol="CFURLEnumeratorGetDescendentLevel", optional=true)
    public static native @MachineSizedSInt long enumeratorGetDescendentLevel(CFURLEnumerator enumerator);
    @Bridge(symbol="CFURLEnumeratorGetSourceDidChange", optional=true)
    public static native boolean enumeratorGetSourceDidChange(CFURLEnumerator enumerator);
    /*</methods>*/
}
