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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreFoundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreFoundation.class); }/*</bind>*/
    /*<constants>*/
    public static final float StringEncodingInvalidId = 0xffffffff;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFStringTransformStripCombiningMarks", optional=true)
    public static native String StringTransformStripCombiningMarks();
    @GlobalValue(symbol="kCFStringTransformToLatin", optional=true)
    public static native String StringTransformToLatin();
    @GlobalValue(symbol="kCFStringTransformFullwidthHalfwidth", optional=true)
    public static native String StringTransformFullwidthHalfwidth();
    @GlobalValue(symbol="kCFStringTransformLatinKatakana", optional=true)
    public static native String StringTransformLatinKatakana();
    @GlobalValue(symbol="kCFStringTransformLatinHiragana", optional=true)
    public static native String StringTransformLatinHiragana();
    @GlobalValue(symbol="kCFStringTransformHiraganaKatakana", optional=true)
    public static native String StringTransformHiraganaKatakana();
    @GlobalValue(symbol="kCFStringTransformMandarinLatin", optional=true)
    public static native String StringTransformMandarinLatin();
    @GlobalValue(symbol="kCFStringTransformLatinHangul", optional=true)
    public static native String StringTransformLatinHangul();
    @GlobalValue(symbol="kCFStringTransformLatinArabic", optional=true)
    public static native String StringTransformLatinArabic();
    @GlobalValue(symbol="kCFStringTransformLatinHebrew", optional=true)
    public static native String StringTransformLatinHebrew();
    @GlobalValue(symbol="kCFStringTransformLatinThai", optional=true)
    public static native String StringTransformLatinThai();
    @GlobalValue(symbol="kCFStringTransformLatinCyrillic", optional=true)
    public static native String StringTransformLatinCyrillic();
    @GlobalValue(symbol="kCFStringTransformLatinGreek", optional=true)
    public static native String StringTransformLatinGreek();
    @GlobalValue(symbol="kCFStringTransformToXMLHex", optional=true)
    public static native String StringTransformToXMLHex();
    @GlobalValue(symbol="kCFStringTransformToUnicodeName", optional=true)
    public static native String StringTransformToUnicodeName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStringTransformStripDiacritics", optional=true)
    public static native String StringTransformStripDiacritics();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLKeysOfUnsetValuesKey", optional=true)
    public static native String URLKeysOfUnsetValuesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLNameKey", optional=true)
    public static native String URLNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedNameKey", optional=true)
    public static native String URLLocalizedNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsRegularFileKey", optional=true)
    public static native String URLIsRegularFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsDirectoryKey", optional=true)
    public static native String URLIsDirectoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSymbolicLinkKey", optional=true)
    public static native String URLIsSymbolicLinkKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsVolumeKey", optional=true)
    public static native String URLIsVolumeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsPackageKey", optional=true)
    public static native String URLIsPackageKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSystemImmutableKey", optional=true)
    public static native String URLIsSystemImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUserImmutableKey", optional=true)
    public static native String URLIsUserImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsHiddenKey", optional=true)
    public static native String URLIsHiddenKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLHasHiddenExtensionKey", optional=true)
    public static native String URLHasHiddenExtensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCreationDateKey", optional=true)
    public static native String URLCreationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentAccessDateKey", optional=true)
    public static native String URLContentAccessDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentModificationDateKey", optional=true)
    public static native String URLContentModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLAttributeModificationDateKey", optional=true)
    public static native String URLAttributeModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLinkCountKey", optional=true)
    public static native String URLLinkCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLParentDirectoryURLKey", optional=true)
    public static native String URLParentDirectoryURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLKey", optional=true)
    public static native String URLVolumeURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLTypeIdentifierKey", optional=true)
    public static native String URLTypeIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey", optional=true)
    public static native String URLLocalizedTypeDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelNumberKey", optional=true)
    public static native String URLLabelNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelColorKey", optional=true)
    public static native String URLLabelColorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedLabelKey", optional=true)
    public static native String URLLocalizedLabelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLEffectiveIconKey", optional=true)
    public static native String URLEffectiveIconKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCustomIconKey", optional=true)
    public static native String URLCustomIconKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceIdentifierKey", optional=true)
    public static native String URLFileResourceIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIdentifierKey", optional=true)
    public static native String URLVolumeIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey", optional=true)
    public static native String URLPreferredIOBlockSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsReadableKey", optional=true)
    public static native String URLIsReadableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsWritableKey", optional=true)
    public static native String URLIsWritableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsExecutableKey", optional=true)
    public static native String URLIsExecutableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSecurityKey", optional=true)
    public static native String URLFileSecurityKey();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey", optional=true)
    public static native String URLIsExcludedFromBackupKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCFURLPathKey", optional=true)
    public static native String URLPathKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsMountTriggerKey", optional=true)
    public static native String URLIsMountTriggerKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCFURLGenerationIdentifierKey", optional=true)
    public static native String URLGenerationIdentifierKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCFURLDocumentIdentifierKey", optional=true)
    public static native String URLDocumentIdentifierKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCFURLAddedToDirectoryDateKey", optional=true)
    public static native String URLAddedToDirectoryDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeKey", optional=true)
    public static native String URLFileResourceTypeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeNamedPipe", optional=true)
    public static native String URLFileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeCharacterSpecial", optional=true)
    public static native String URLFileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeDirectory", optional=true)
    public static native String URLFileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeBlockSpecial", optional=true)
    public static native String URLFileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeRegular", optional=true)
    public static native String URLFileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSymbolicLink", optional=true)
    public static native String URLFileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSocket", optional=true)
    public static native String URLFileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeUnknown", optional=true)
    public static native String URLFileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSizeKey", optional=true)
    public static native String URLFileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileAllocatedSizeKey", optional=true)
    public static native String URLFileAllocatedSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileSizeKey", optional=true)
    public static native String URLTotalFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey", optional=true)
    public static native String URLTotalFileAllocatedSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsAliasFileKey", optional=true)
    public static native String URLIsAliasFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native String URLVolumeLocalizedFormatDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey", optional=true)
    public static native String URLVolumeTotalCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey", optional=true)
    public static native String URLVolumeAvailableCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeResourceCountKey", optional=true)
    public static native String URLVolumeResourceCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native String URLVolumeSupportsPersistentIDsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native String URLVolumeSupportsSymbolicLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey", optional=true)
    public static native String URLVolumeSupportsHardLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey", optional=true)
    public static native String URLVolumeSupportsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsJournalingKey", optional=true)
    public static native String URLVolumeIsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey", optional=true)
    public static native String URLVolumeSupportsSparseFilesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey", optional=true)
    public static native String URLVolumeSupportsZeroRunsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native String URLVolumeSupportsCaseSensitiveNamesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native String URLVolumeSupportsCasePreservedNamesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native String URLVolumeSupportsRootDirectoryDatesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native String URLVolumeSupportsVolumeSizesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey", optional=true)
    public static native String URLVolumeSupportsRenamingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native String URLVolumeSupportsAdvisoryFileLockingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native String URLVolumeSupportsExtendedSecurityKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey", optional=true)
    public static native String URLVolumeIsBrowsableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey", optional=true)
    public static native String URLVolumeMaximumFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsEjectableKey", optional=true)
    public static native String URLVolumeIsEjectableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsRemovableKey", optional=true)
    public static native String URLVolumeIsRemovableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsInternalKey", optional=true)
    public static native String URLVolumeIsInternalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey", optional=true)
    public static native String URLVolumeIsAutomountedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsLocalKey", optional=true)
    public static native String URLVolumeIsLocalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey", optional=true)
    public static native String URLVolumeIsReadOnlyKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeCreationDateKey", optional=true)
    public static native String URLVolumeCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey", optional=true)
    public static native String URLVolumeURLForRemountingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeUUIDStringKey", optional=true)
    public static native String URLVolumeUUIDStringKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeNameKey", optional=true)
    public static native String URLVolumeNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey", optional=true)
    public static native String URLVolumeLocalizedNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUbiquitousItemKey", optional=true)
    public static native String URLIsUbiquitousItemKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native String URLUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native String URLUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native String URLUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadedKey", optional=true)
    public static native String URLUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadingKey", optional=true)
    public static native String URLUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native String URLUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native String URLUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native String URLUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native String URLUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native String URLUbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="kCFSocketCommandKey", optional=true)
    public static native String Values_kCFSocketCommandKey();
    @GlobalValue(symbol="kCFSocketNameKey", optional=true)
    public static native String Values_kCFSocketNameKey();
    @GlobalValue(symbol="kCFSocketValueKey", optional=true)
    public static native String Values_kCFSocketValueKey();
    @GlobalValue(symbol="kCFSocketResultKey", optional=true)
    public static native String Values_kCFSocketResultKey();
    @GlobalValue(symbol="kCFSocketErrorKey", optional=true)
    public static native String Values_kCFSocketErrorKey();
    @GlobalValue(symbol="kCFSocketRegisterCommand", optional=true)
    public static native String SocketRegisterCommand();
    @GlobalValue(symbol="kCFSocketRetrieveCommand", optional=true)
    public static native String SocketRetrieveCommand();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileExists", optional=true)
    public static native String URLFileExists();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileDirectoryContents", optional=true)
    public static native String URLFileDirectoryContents();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLength", optional=true)
    public static native String URLFileLength();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLastModificationTime", optional=true)
    public static native String URLFileLastModificationTime();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFilePOSIXMode", optional=true)
    public static native String URLFilePOSIXMode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileOwnerID", optional=true)
    public static native String URLFileOwnerID();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLHTTPStatusCode", optional=true)
    public static native String URLHTTPStatusCode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLHTTPStatusLine", optional=true)
    public static native String URLHTTPStatusLine();
    @GlobalValue(symbol="kCFBundleInfoDictionaryVersionKey", optional=true)
    public static native String Values_kCFBundleInfoDictionaryVersionKey();
    @GlobalValue(symbol="kCFBundleExecutableKey", optional=true)
    public static native String Values_kCFBundleExecutableKey();
    @GlobalValue(symbol="kCFBundleIdentifierKey", optional=true)
    public static native String Values_kCFBundleIdentifierKey();
    @GlobalValue(symbol="kCFBundleVersionKey", optional=true)
    public static native String Values_kCFBundleVersionKey();
    @GlobalValue(symbol="kCFBundleDevelopmentRegionKey", optional=true)
    public static native String Values_kCFBundleDevelopmentRegionKey();
    @GlobalValue(symbol="kCFBundleNameKey", optional=true)
    public static native String Values_kCFBundleNameKey();
    @GlobalValue(symbol="kCFBundleLocalizationsKey", optional=true)
    public static native String Values_kCFBundleLocalizationsKey();
    /*</methods>*/
}
