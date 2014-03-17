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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSFileManagerPtr extends Ptr<NSFileManager, NSFileManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSFileManager.class); }/*</bind>*/
    /*<constants>*/
    public static final int FoundationVersionWithFileManagerResourceForkSupport = 412;
    /*</constants>*/
    /*<constructors>*/
    public NSFileManager() {}
    protected NSFileManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="NSUbiquityIdentityDidChangeNotification")
    public static native String NotificationUbiquityIdentityDidChange();
    @GlobalValue(symbol="NSFileType")
    public static native NSString KeyType();
    @GlobalValue(symbol="NSFileTypeDirectory")
    public static native NSString FileTypeDirectory();
    @GlobalValue(symbol="NSFileTypeRegular")
    public static native NSString FileTypeRegular();
    @GlobalValue(symbol="NSFileTypeSymbolicLink")
    public static native NSString FileTypeSymbolicLink();
    @GlobalValue(symbol="NSFileTypeSocket")
    public static native NSString FileTypeSocket();
    @GlobalValue(symbol="NSFileTypeCharacterSpecial")
    public static native NSString FileTypeCharacterSpecial();
    @GlobalValue(symbol="NSFileTypeBlockSpecial")
    public static native NSString FileTypeBlockSpecial();
    @GlobalValue(symbol="NSFileTypeUnknown")
    public static native NSString FileTypeUnknown();
    @GlobalValue(symbol="NSFileSize")
    public static native NSString KeySize();
    @GlobalValue(symbol="NSFileModificationDate")
    public static native NSString KeyModificationDate();
    @GlobalValue(symbol="NSFileReferenceCount")
    public static native NSString KeyReferenceCount();
    @GlobalValue(symbol="NSFileDeviceIdentifier")
    public static native NSString KeyDeviceIdentifier();
    @GlobalValue(symbol="NSFileOwnerAccountName")
    public static native NSString KeyOwnerAccountName();
    @GlobalValue(symbol="NSFileGroupOwnerAccountName")
    public static native NSString KeyGroupOwnerAccountName();
    @GlobalValue(symbol="NSFilePosixPermissions")
    public static native NSString KeyPosixPermissions();
    @GlobalValue(symbol="NSFileSystemNumber")
    public static native NSString KeySystemNumber();
    @GlobalValue(symbol="NSFileSystemFileNumber")
    public static native NSString KeySystemFileNumber();
    @GlobalValue(symbol="NSFileExtensionHidden")
    public static native NSString KeyExtensionHidden();
    @GlobalValue(symbol="NSFileHFSCreatorCode")
    public static native NSString KeyHFSCreatorCode();
    @GlobalValue(symbol="NSFileHFSTypeCode")
    public static native NSString KeyHFSTypeCode();
    @GlobalValue(symbol="NSFileImmutable")
    public static native NSString KeyImmutable();
    @GlobalValue(symbol="NSFileAppendOnly")
    public static native NSString KeyAppendOnly();
    @GlobalValue(symbol="NSFileCreationDate")
    public static native NSString KeyCreationDate();
    @GlobalValue(symbol="NSFileOwnerAccountID")
    public static native NSString KeyOwnerAccountID();
    @GlobalValue(symbol="NSFileGroupOwnerAccountID")
    public static native NSString KeyGroupOwnerAccountID();
    @GlobalValue(symbol="NSFileBusy")
    public static native NSString KeyBusy();
    @GlobalValue(symbol="NSFileProtectionKey")
    public static native NSString KeyProtection();
    @GlobalValue(symbol="NSFileProtectionNone")
    public static native NSString FileProtectionNone();
    @GlobalValue(symbol="NSFileProtectionComplete")
    public static native NSString FileProtectionComplete();
    @GlobalValue(symbol="NSFileProtectionCompleteUnlessOpen")
    public static native NSString FileProtectionCompleteUnlessOpen();
    @GlobalValue(symbol="NSFileProtectionCompleteUntilFirstUserAuthentication")
    public static native NSString FileProtectionCompleteUntilFirstUserAuthentication();
    @GlobalValue(symbol="NSFileSystemSize")
    public static native NSString FileSystemSize();
    @GlobalValue(symbol="NSFileSystemFreeSize")
    public static native NSString FileSystemFreeSize();
    @GlobalValue(symbol="NSFileSystemNodes")
    public static native NSString FileSystemNodes();
    @GlobalValue(symbol="NSFileSystemFreeNodes")
    public static native NSString FileSystemFreeNodes();
    
    @Method(selector = "mountedVolumeURLsIncludingResourceValuesForKeys:options:")
    public native NSArray<?> mountedVolumeURLsIncludingResourceValuesForKeys$options$(NSArray<?> propertyKeys, NSVolumeEnumerationOptions options);
    @Method(selector = "contentsOfDirectoryAtURL:includingPropertiesForKeys:options:error:")
    public native NSArray<?> contentsOfDirectoryAtURL$includingPropertiesForKeys$options$error$(NSURL url, NSArray<?> keys, NSDirectoryEnumerationOptions mask, NSError.NSErrorPtr error);
    @Method(selector = "URLsForDirectory:inDomains:")
    public native NSArray<?> URLsForDirectory$inDomains$(NSSearchPathDirectory directory, NSSearchPathDomainMask domainMask);
    @Method(selector = "URLForDirectory:inDomain:appropriateForURL:create:error:")
    public native NSURL URLForDirectory$inDomain$appropriateForURL$create$error$(NSSearchPathDirectory directory, NSSearchPathDomainMask domain, NSURL url, boolean shouldCreate, NSError.NSErrorPtr error);
    @Method(selector = "createDirectoryAtURL:withIntermediateDirectories:attributes:error:")
    public native boolean createDirectoryAtURL$withIntermediateDirectories$attributes$error$(NSURL url, boolean createIntermediates, NSDictionary<?, ?> attributes, NSError.NSErrorPtr error);
    @Method(selector = "createSymbolicLinkAtURL:withDestinationURL:error:")
    public native boolean createSymbolicLinkAtURL$withDestinationURL$error$(NSURL url, NSURL destURL, NSError.NSErrorPtr error);
    @Method(selector = "setDelegate:")
    public native void setDelegate$(NSObject delegate);
    @Method(selector = "delegate")
    public native NSObject delegate();
    @Method(selector = "setAttributes:ofItemAtPath:error:")
    public native boolean setAttributes$ofItemAtPath$error$(NSDictionary<?, ?> attributes, String path, NSError.NSErrorPtr error);
    @Method(selector = "createDirectoryAtPath:withIntermediateDirectories:attributes:error:")
    public native boolean createDirectoryAtPath$withIntermediateDirectories$attributes$error$(String path, boolean createIntermediates, NSDictionary<?, ?> attributes, NSError.NSErrorPtr error);
    @Method(selector = "contentsOfDirectoryAtPath:error:")
    public native NSArray<?> contentsOfDirectoryAtPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "subpathsOfDirectoryAtPath:error:")
    public native NSArray<?> subpathsOfDirectoryAtPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "attributesOfItemAtPath:error:")
    public native NSDictionary<?, ?> attributesOfItemAtPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "attributesOfFileSystemForPath:error:")
    public native NSDictionary<?, ?> attributesOfFileSystemForPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "createSymbolicLinkAtPath:withDestinationPath:error:")
    public native boolean createSymbolicLinkAtPath$withDestinationPath$error$(String path, String destPath, NSError.NSErrorPtr error);
    @Method(selector = "destinationOfSymbolicLinkAtPath:error:")
    public native String destinationOfSymbolicLinkAtPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "copyItemAtPath:toPath:error:")
    public native boolean copyItemAtPath$toPath$error$(String srcPath, String dstPath, NSError.NSErrorPtr error);
    @Method(selector = "moveItemAtPath:toPath:error:")
    public native boolean moveItemAtPath$toPath$error$(String srcPath, String dstPath, NSError.NSErrorPtr error);
    @Method(selector = "linkItemAtPath:toPath:error:")
    public native boolean linkItemAtPath$toPath$error$(String srcPath, String dstPath, NSError.NSErrorPtr error);
    @Method(selector = "removeItemAtPath:error:")
    public native boolean removeItemAtPath$error$(String path, NSError.NSErrorPtr error);
    @Method(selector = "copyItemAtURL:toURL:error:")
    public native boolean copyItemAtURL$toURL$error$(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    @Method(selector = "moveItemAtURL:toURL:error:")
    public native boolean moveItemAtURL$toURL$error$(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    @Method(selector = "linkItemAtURL:toURL:error:")
    public native boolean linkItemAtURL$toURL$error$(NSURL srcURL, NSURL dstURL, NSError.NSErrorPtr error);
    @Method(selector = "removeItemAtURL:error:")
    public native boolean removeItemAtURL$error$(NSURL URL, NSError.NSErrorPtr error);
    @Method(selector = "fileAttributesAtPath:traverseLink:")
    public native NSDictionary<?, ?> fileAttributesAtPath$traverseLink$(String path, boolean yorn);
    @Method(selector = "changeFileAttributes:atPath:")
    public native boolean changeFileAttributes$atPath$(NSDictionary<?, ?> attributes, String path);
    @Method(selector = "directoryContentsAtPath:")
    public native NSArray<?> directoryContentsAtPath$(String path);
    @Method(selector = "fileSystemAttributesAtPath:")
    public native NSDictionary<?, ?> fileSystemAttributesAtPath$(String path);
    @Method(selector = "pathContentOfSymbolicLinkAtPath:")
    public native String pathContentOfSymbolicLinkAtPath$(String path);
    @Method(selector = "createSymbolicLinkAtPath:pathContent:")
    public native boolean createSymbolicLinkAtPath$pathContent$(String path, String otherpath);
    @Method(selector = "createDirectoryAtPath:attributes:")
    public native boolean createDirectoryAtPath$attributes$(String path, NSDictionary<?, ?> attributes);
    @Method(selector = "currentDirectoryPath")
    public native String currentDirectoryPath();
    @Method(selector = "changeCurrentDirectoryPath:")
    public native boolean changeCurrentDirectoryPath$(String path);
    @Method(selector = "fileExistsAtPath:")
    public native boolean fileExistsAtPath$(String path);
    @Method(selector = "fileExistsAtPath:isDirectory:")
    public native boolean fileExistsAtPath$isDirectory$(String path, BytePtr isDirectory);
    @Method(selector = "isReadableFileAtPath:")
    public native boolean isReadableFileAtPath$(String path);
    @Method(selector = "isWritableFileAtPath:")
    public native boolean isWritableFileAtPath$(String path);
    @Method(selector = "isExecutableFileAtPath:")
    public native boolean isExecutableFileAtPath$(String path);
    @Method(selector = "isDeletableFileAtPath:")
    public native boolean isDeletableFileAtPath$(String path);
    @Method(selector = "contentsEqualAtPath:andPath:")
    public native boolean contentsEqualAtPath$andPath$(String path1, String path2);
    @Method(selector = "displayNameAtPath:")
    public native String displayNameAtPath$(String path);
    @Method(selector = "componentsToDisplayForPath:")
    public native NSArray<?> componentsToDisplayForPath$(String path);
    @Method(selector = "enumeratorAtPath:")
    public native NSDirectoryEnumerator enumeratorAtPath$(String path);
    @Method(selector = "enumeratorAtURL:includingPropertiesForKeys:options:errorHandler:")
    public native NSDirectoryEnumerator enumeratorAtURL$includingPropertiesForKeys$options$errorHandler$(NSURL url, NSArray<?> keys, NSDirectoryEnumerationOptions mask, ObjCBlock handler);
    @Method(selector = "subpathsAtPath:")
    public native NSArray<?> subpathsAtPath$(String path);
    @Method(selector = "contentsAtPath:")
    public native NSData contentsAtPath$(String path);
    @Method(selector = "createFileAtPath:contents:attributes:")
    public native boolean createFileAtPath$contents$attributes$(String path, NSData data, NSDictionary<?, ?> attr);
    @Method(selector = "fileSystemRepresentationWithPath:")
    public native BytePtr fileSystemRepresentationWithPath$(String path);
    @Method(selector = "stringWithFileSystemRepresentation:length:")
    public native String stringWithFileSystemRepresentation$length$(BytePtr str, @MachineSizedUInt long len);
    @Method(selector = "replaceItemAtURL:withItemAtURL:backupItemName:options:resultingItemURL:error:")
    public native boolean replaceItemAtURL$withItemAtURL$backupItemName$options$resultingItemURL$error$(NSURL originalItemURL, NSURL newItemURL, String backupItemName, NSFileManagerItemReplacementOptions options, NSURL.NSURLPtr resultingURL, NSError.NSErrorPtr error);
    @Method(selector = "setUbiquitous:itemAtURL:destinationURL:error:")
    public native boolean setUbiquitous$itemAtURL$destinationURL$error$(boolean flag, NSURL url, NSURL destinationURL, NSError.NSErrorPtr error);
    @Method(selector = "isUbiquitousItemAtURL:")
    public native boolean isUbiquitousItemAtURL$(NSURL url);
    @Method(selector = "startDownloadingUbiquitousItemAtURL:error:")
    public native boolean startDownloadingUbiquitousItemAtURL$error$(NSURL url, NSError.NSErrorPtr error);
    @Method(selector = "evictUbiquitousItemAtURL:error:")
    public native boolean evictUbiquitousItemAtURL$error$(NSURL url, NSError.NSErrorPtr error);
    @Method(selector = "URLForUbiquityContainerIdentifier:")
    public native NSURL URLForUbiquityContainerIdentifier$(String containerIdentifier);
    @Method(selector = "URLForPublishingUbiquitousItemAtURL:expirationDate:error:")
    public native NSURL URLForPublishingUbiquitousItemAtURL$expirationDate$error$(NSURL url, NSDate.NSDatePtr outDate, NSError.NSErrorPtr error);
    @Method(selector = "ubiquityIdentityToken")
    public native NSObject ubiquityIdentityToken();
    @Method(selector = "containerURLForSecurityApplicationGroupIdentifier:")
    public native NSURL containerURLForSecurityApplicationGroupIdentifier$(String groupIdentifier);
    @Method(selector = "defaultManager")
    public static native NSFileManager defaultManager();
    /*</methods>*/
}
