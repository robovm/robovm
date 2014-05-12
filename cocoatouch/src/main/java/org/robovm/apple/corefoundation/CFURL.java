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

/*<javadoc>*/
/*</javadoc>*/
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
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLKeysOfUnsetValuesKey", optional=true)
    public static native CFString KeyKeysOfUnsetValues();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLNameKey", optional=true)
    public static native CFString KeyName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedNameKey", optional=true)
    public static native CFString KeyLocalizedName();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsRegularFileKey", optional=true)
    public static native CFString KeyIsRegularFile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsDirectoryKey", optional=true)
    public static native CFString KeyIsDirectory();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSymbolicLinkKey", optional=true)
    public static native CFString KeyIsSymbolicLink();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsVolumeKey", optional=true)
    public static native CFString KeyIsVolume();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsPackageKey", optional=true)
    public static native CFString KeyIsPackage();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSystemImmutableKey", optional=true)
    public static native CFString KeyIsSystemImmutable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUserImmutableKey", optional=true)
    public static native CFString KeyIsUserImmutable();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsHiddenKey", optional=true)
    public static native CFString KeyIsHidden();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLHasHiddenExtensionKey", optional=true)
    public static native CFString KeyHasHiddenExtension();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCreationDateKey", optional=true)
    public static native CFString KeyCreationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentAccessDateKey", optional=true)
    public static native CFString KeyContentAccessDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentModificationDateKey", optional=true)
    public static native CFString KeyContentModificationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLAttributeModificationDateKey", optional=true)
    public static native CFString KeyAttributeModificationDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLinkCountKey", optional=true)
    public static native CFString KeyLinkCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLParentDirectoryURLKey", optional=true)
    public static native CFString KeyParentDirectoryURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLKey", optional=true)
    public static native CFString KeyVolumeURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLTypeIdentifierKey", optional=true)
    public static native CFString KeyTypeIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey", optional=true)
    public static native CFString KeyLocalizedTypeDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelNumberKey", optional=true)
    public static native CFString KeyLabelNumber();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelColorKey", optional=true)
    public static native CFString KeyLabelColor();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedLabelKey", optional=true)
    public static native CFString KeyLocalizedLabel();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLEffectiveIconKey", optional=true)
    public static native CFString KeyEffectiveIcon();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCustomIconKey", optional=true)
    public static native CFString KeyCustomIcon();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceIdentifierKey", optional=true)
    public static native CFString KeyFileResourceIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIdentifierKey", optional=true)
    public static native CFString KeyVolumeIdentifier();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey", optional=true)
    public static native CFString KeyPreferredIOBlockSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsReadableKey", optional=true)
    public static native CFString KeyIsReadable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsWritableKey", optional=true)
    public static native CFString KeyIsWritable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsExecutableKey", optional=true)
    public static native CFString KeyIsExecutable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSecurityKey", optional=true)
    public static native CFString KeyFileSecurity();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey", optional=true)
    public static native CFString KeyIsExcludedFromBackup();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCFURLPathKey", optional=true)
    public static native CFString KeyPath();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsMountTriggerKey", optional=true)
    public static native CFString KeyIsMountTrigger();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeKey", optional=true)
    public static native CFString KeyFileResourceType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeNamedPipe", optional=true)
    public static native CFString FileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeCharacterSpecial", optional=true)
    public static native CFString FileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeDirectory", optional=true)
    public static native CFString FileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeBlockSpecial", optional=true)
    public static native CFString FileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeRegular", optional=true)
    public static native CFString FileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSymbolicLink", optional=true)
    public static native CFString FileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSocket", optional=true)
    public static native CFString FileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeUnknown", optional=true)
    public static native CFString FileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSizeKey", optional=true)
    public static native CFString KeyFileSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileAllocatedSizeKey", optional=true)
    public static native CFString KeyFileAllocatedSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileSizeKey", optional=true)
    public static native CFString KeyTotalFileSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey", optional=true)
    public static native CFString KeyTotalFileAllocatedSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsAliasFileKey", optional=true)
    public static native CFString KeyIsAliasFile();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native CFString KeyVolumeLocalizedFormatDescription();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey", optional=true)
    public static native CFString KeyVolumeTotalCapacity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey", optional=true)
    public static native CFString KeyVolumeAvailableCapacity();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeResourceCountKey", optional=true)
    public static native CFString KeyVolumeResourceCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native CFString KeyVolumeSupportsPersistentIDs();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native CFString KeyVolumeSupportsSymbolicLinks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey", optional=true)
    public static native CFString KeyVolumeSupportsHardLinks();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey", optional=true)
    public static native CFString KeyVolumeSupportsJournaling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsJournalingKey", optional=true)
    public static native CFString KeyVolumeIsJournaling();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey", optional=true)
    public static native CFString KeyVolumeSupportsSparseFiles();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey", optional=true)
    public static native CFString KeyVolumeSupportsZeroRuns();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native CFString KeyVolumeSupportsCaseSensitiveNames();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native CFString KeyVolumeSupportsCasePreservedNames();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native CFString KeyVolumeSupportsRootDirectoryDates();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native CFString KeyVolumeSupportsVolumeSizes();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey", optional=true)
    public static native CFString KeyVolumeSupportsRenaming();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native CFString KeyVolumeSupportsAdvisoryFileLocking();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native CFString KeyVolumeSupportsExtendedSecurity();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey", optional=true)
    public static native CFString KeyVolumeIsBrowsable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey", optional=true)
    public static native CFString KeyVolumeMaximumFileSize();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsEjectableKey", optional=true)
    public static native CFString KeyVolumeIsEjectable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsRemovableKey", optional=true)
    public static native CFString KeyVolumeIsRemovable();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsInternalKey", optional=true)
    public static native CFString KeyVolumeIsInternal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey", optional=true)
    public static native CFString KeyVolumeIsAutomounted();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsLocalKey", optional=true)
    public static native CFString KeyVolumeIsLocal();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey", optional=true)
    public static native CFString KeyVolumeIsReadOnly();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeCreationDateKey", optional=true)
    public static native CFString KeyVolumeCreationDate();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey", optional=true)
    public static native CFString KeyVolumeURLForRemounting();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeUUIDStringKey", optional=true)
    public static native CFString KeyVolumeUUIDString();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeNameKey", optional=true)
    public static native CFString KeyVolumeName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey", optional=true)
    public static native CFString KeyVolumeLocalizedName();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUbiquitousItemKey", optional=true)
    public static native CFString KeyIsUbiquitousItem();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native CFString KeyUbiquitousItemHasUnresolvedConflicts();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native CFString KeyUbiquitousItemIsDownloaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native CFString KeyUbiquitousItemIsDownloading();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadedKey", optional=true)
    public static native CFString KeyUbiquitousItemIsUploaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadingKey", optional=true)
    public static native CFString KeyUbiquitousItemIsUploading();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native CFString KeyUbiquitousItemPercentDownloaded();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native CFString KeyUbiquitousItemPercentUploaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native CFString KeyUbiquitousItemDownloadingStatus();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native CFString KeyUbiquitousItemDownloadingError();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native CFString KeyUbiquitousItemUploadingError();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native CFString UbiquitousItemDownloadingStatusCurrent();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileExists", optional=true)
    public static native CFString FileExists();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileDirectoryContents", optional=true)
    public static native CFString FileDirectoryContents();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLength", optional=true)
    public static native CFString FileLength();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLastModificationTime", optional=true)
    public static native CFString FileLastModificationTime();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFilePOSIXMode", optional=true)
    public static native CFString FilePOSIXMode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileOwnerID", optional=true)
    public static native CFString FileOwnerID();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLHTTPStatusCode", optional=true)
    public static native CFString HTTPStatusCode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
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
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFURLIsFileReferenceURL", optional=true)
    public native boolean isFileReferenceURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateFileReferenceURL", optional=true)
    public static native CFURL createFileReferenceURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateFilePathURL", optional=true)
    public static native CFURL createFilePathURL(CFAllocator allocator, CFURL url, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCopyResourcePropertyForKey", optional=true)
    public native boolean copyResourcePropertyForKey(CFString key, VoidPtr propertyValueTypeRefPtr, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCopyResourcePropertiesForKeys", optional=true)
    public native CFDictionary copyResourcePropertiesForKeys(CFArray keys, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetResourcePropertyForKey", optional=true)
    public native boolean setResourcePropertyForKey(CFString key, CFType propertyValue, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetResourcePropertiesForKeys", optional=true)
    public native boolean setResourcePropertiesForKeys(CFDictionary keyedPropertyValues, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLClearResourcePropertyCacheForKey", optional=true)
    public native void clearResourcePropertyCacheForKey(CFString key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLClearResourcePropertyCache", optional=true)
    public native void clearResourcePropertyCache();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetTemporaryResourcePropertyForKey", optional=true)
    public native void setTemporaryResourcePropertyForKey(CFString key, CFType propertyValue);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLResourceIsReachable", optional=true)
    public native boolean resourceIsReachable(CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateBookmarkData", optional=true)
    public static native CFData createBookmarkData(CFAllocator allocator, CFURL url, CFURLBookmarkCreationOptions options, CFArray resourcePropertiesToInclude, CFURL relativeToURL, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateByResolvingBookmarkData", optional=true)
    public static native CFURL createByResolvingBookmarkData(CFAllocator allocator, CFData bookmark, CFURLBookmarkResolutionOptions options, CFURL relativeToURL, CFArray resourcePropertiesToInclude, BytePtr isStale, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateResourcePropertiesForKeysFromBookmarkData", optional=true)
    public static native CFDictionary createResourcePropertiesForKeysFromBookmarkData(CFAllocator allocator, CFArray resourcePropertiesToReturn, CFData bookmark);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateResourcePropertyForKeyFromBookmarkData", optional=true)
    public static native CFType createResourcePropertyForKeyFromBookmarkData(CFAllocator allocator, CFString resourcePropertyKey, CFData bookmark);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFURLCreateBookmarkDataFromFile", optional=true)
    public static native CFData createBookmarkDataFromFile(CFAllocator allocator, CFURL fileURL, CFError.CFErrorPtr errorRef);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFURLWriteBookmarkDataToFile", optional=true)
    public static native boolean writeBookmarkDataToFile(CFData bookmarkRef, CFURL fileURL, @MachineSizedUInt long options, CFError.CFErrorPtr errorRef);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFURLCreateDataAndPropertiesFromResource", optional=true)
    public static native boolean createDataAndPropertiesFromResource(CFAllocator alloc, CFURL url, CFData.CFDataPtr resourceData, CFDictionary.CFDictionaryPtr properties, CFArray desiredProperties, IntPtr errorCode);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFURLWriteDataAndPropertiesToResource", optional=true)
    public native boolean writeDataAndPropertiesToResource(CFData dataToWrite, CFDictionary propertiesToWrite, IntPtr errorCode);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFURLDestroyResource", optional=true)
    public native boolean destroyResource(IntPtr errorCode);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Bridge(symbol="CFURLCreatePropertyFromResource", optional=true)
    public static native CFType createPropertyFromResource(CFAllocator alloc, CFURL url, CFString property, IntPtr errorCode);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFCopyHomeDirectoryURL", optional=true)
    public static native CFURL copyHomeDirectoryURL();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorCreateForDirectoryURL", optional=true)
    public static native CFURLEnumerator enumeratorCreateForDirectoryURL(CFAllocator alloc, CFURL directoryURL, CFURLEnumeratorOptions option, CFArray propertyKeys);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorCreateForMountedVolumes", optional=true)
    public static native CFURLEnumerator enumeratorCreateForMountedVolumes(CFAllocator alloc, CFURLEnumeratorOptions option, CFArray propertyKeys);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorGetNextURL", optional=true)
    public static native CFURLEnumeratorResult enumeratorGetNextURL(CFURLEnumerator enumerator, CFURL.CFURLPtr url, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorSkipDescendents", optional=true)
    public static native void enumeratorSkipDescendents(CFURLEnumerator enumerator);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorGetDescendentLevel", optional=true)
    public static native @MachineSizedSInt long enumeratorGetDescendentLevel(CFURLEnumerator enumerator);
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Bridge(symbol="CFURLEnumeratorGetSourceDidChange", optional=true)
    public static native boolean enumeratorGetSourceDidChange(CFURLEnumerator enumerator);
    /*</methods>*/
}
