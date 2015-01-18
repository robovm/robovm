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
    @Bridge(symbol="CFURLGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFURLCreateWithBytes", optional=true)
    public static native CFURL createWithBytes(CFAllocator allocator, BytePtr URLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL);
    @Bridge(symbol="CFURLCreateData", optional=true)
    public static native CFData createData(CFAllocator allocator, CFURL url, int encoding, boolean escapeWhitespace);
    @Bridge(symbol="CFURLCreateWithString", optional=true)
    public static native CFURL createWithString(CFAllocator allocator, String URLString, CFURL baseURL);
    @Bridge(symbol="CFURLCreateAbsoluteURLWithBytes", optional=true)
    public static native CFURL createAbsoluteURLWithBytes(CFAllocator alloc, BytePtr relativeURLBytes, @MachineSizedSInt long length, int encoding, CFURL baseURL, boolean useCompatibilityMode);
    @Bridge(symbol="CFURLCreateWithFileSystemPath", optional=true)
    public static native CFURL createWithFileSystemPath(CFAllocator allocator, String filePath, CFURLPathStyle pathStyle, boolean isDirectory);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentation", optional=true)
    public static native CFURL createFromFileSystemRepresentation(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory);
    @Bridge(symbol="CFURLCreateWithFileSystemPathRelativeToBase", optional=true)
    public static native CFURL createWithFileSystemPathRelativeToBase(CFAllocator allocator, String filePath, CFURLPathStyle pathStyle, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLCreateFromFileSystemRepresentationRelativeToBase", optional=true)
    public static native CFURL createFromFileSystemRepresentationRelativeToBase(CFAllocator allocator, BytePtr buffer, @MachineSizedSInt long bufLen, boolean isDirectory, CFURL baseURL);
    @Bridge(symbol="CFURLGetFileSystemRepresentation", optional=true)
    public native boolean getFileSystemRepresentation(boolean resolveAgainstBase, BytePtr buffer, @MachineSizedSInt long maxBufLen);
    @Bridge(symbol="CFURLCopyAbsoluteURL", optional=true)
    public native CFURL copyAbsoluteURL();
    @Bridge(symbol="CFURLGetString", optional=true)
    public native String getString();
    @Bridge(symbol="CFURLGetBaseURL", optional=true)
    public native CFURL getBaseURL();
    @Bridge(symbol="CFURLCanBeDecomposed", optional=true)
    public native boolean canBeDecomposed();
    @Bridge(symbol="CFURLCopyScheme", optional=true)
    public native String copyScheme();
    @Bridge(symbol="CFURLCopyNetLocation", optional=true)
    public native String copyNetLocation();
    @Bridge(symbol="CFURLCopyPath", optional=true)
    public native String copyPath();
    @Bridge(symbol="CFURLCopyStrictPath", optional=true)
    public native String copyStrictPath(BooleanPtr isAbsolute);
    @Bridge(symbol="CFURLCopyFileSystemPath", optional=true)
    public native String copyFileSystemPath(CFURLPathStyle pathStyle);
    @Bridge(symbol="CFURLHasDirectoryPath", optional=true)
    public native boolean hasDirectoryPath();
    @Bridge(symbol="CFURLCopyResourceSpecifier", optional=true)
    public native String copyResourceSpecifier();
    @Bridge(symbol="CFURLCopyHostName", optional=true)
    public native String copyHostName();
    @Bridge(symbol="CFURLGetPortNumber", optional=true)
    public native int getPortNumber();
    @Bridge(symbol="CFURLCopyUserName", optional=true)
    public native String copyUserName();
    @Bridge(symbol="CFURLCopyPassword", optional=true)
    public native String copyPassword();
    @Bridge(symbol="CFURLCopyParameterString", optional=true)
    public native String copyParameterString(String charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyQueryString", optional=true)
    public native String copyQueryString(String charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyFragment", optional=true)
    public native String copyFragment(String charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCopyLastPathComponent", optional=true)
    public native String copyLastPathComponent();
    @Bridge(symbol="CFURLCopyPathExtension", optional=true)
    public native String copyPathExtension();
    @Bridge(symbol="CFURLCreateCopyAppendingPathComponent", optional=true)
    public static native CFURL createCopyAppendingPathComponent(CFAllocator allocator, CFURL url, String pathComponent, boolean isDirectory);
    @Bridge(symbol="CFURLCreateCopyDeletingLastPathComponent", optional=true)
    public static native CFURL createCopyDeletingLastPathComponent(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLCreateCopyAppendingPathExtension", optional=true)
    public static native CFURL createCopyAppendingPathExtension(CFAllocator allocator, CFURL url, String extension);
    @Bridge(symbol="CFURLCreateCopyDeletingPathExtension", optional=true)
    public static native CFURL createCopyDeletingPathExtension(CFAllocator allocator, CFURL url);
    @Bridge(symbol="CFURLGetBytes", optional=true)
    public native @MachineSizedSInt long getBytes(BytePtr buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFURLGetByteRangeForComponent", optional=true)
    public native @ByVal CFRange getByteRangeForComponent(CFURLComponentType component, CFRange rangeIncludingSeparators);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapes", optional=true)
    public static native String createStringByReplacingPercentEscapes(CFAllocator allocator, String originalString, String charactersToLeaveEscaped);
    @Bridge(symbol="CFURLCreateStringByReplacingPercentEscapesUsingEncoding", optional=true)
    public static native String createStringByReplacingPercentEscapesUsingEncoding(CFAllocator allocator, String origString, String charsToLeaveEscaped, int encoding);
    @Bridge(symbol="CFURLCreateStringByAddingPercentEscapes", optional=true)
    public static native String createStringByAddingPercentEscapes(CFAllocator allocator, String originalString, String charactersToLeaveUnescaped, String legalURLCharactersToBeEscaped, int encoding);
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
    public native boolean copyResourcePropertyForKey(String key, VoidPtr propertyValueTypeRefPtr, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCopyResourcePropertiesForKeys", optional=true)
    public native CFDictionary copyResourcePropertiesForKeys(CFArray keys, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetResourcePropertyForKey", optional=true)
    public native boolean setResourcePropertyForKey(String key, CFType propertyValue, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetResourcePropertiesForKeys", optional=true)
    public native boolean setResourcePropertiesForKeys(CFDictionary keyedPropertyValues, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLClearResourcePropertyCacheForKey", optional=true)
    public native void clearResourcePropertyCacheForKey(String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLClearResourcePropertyCache", optional=true)
    public native void clearResourcePropertyCache();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLSetTemporaryResourcePropertyForKey", optional=true)
    public native void setTemporaryResourcePropertyForKey(String key, CFType propertyValue);
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
    public static native CFURL createByResolvingBookmarkData(CFAllocator allocator, CFData bookmark, CFURLBookmarkResolutionOptions options, CFURL relativeToURL, CFArray resourcePropertiesToInclude, BooleanPtr isStale, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateResourcePropertiesForKeysFromBookmarkData", optional=true)
    public static native CFDictionary createResourcePropertiesForKeysFromBookmarkData(CFAllocator allocator, CFArray resourcePropertiesToReturn, CFData bookmark);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLCreateResourcePropertyForKeyFromBookmarkData", optional=true)
    public static native CFType createResourcePropertyForKeyFromBookmarkData(CFAllocator allocator, String resourcePropertyKey, CFData bookmark);
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
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CFURLStartAccessingSecurityScopedResource", optional=true)
    public native boolean startAccessingSecurityScopedResource();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CFURLStopAccessingSecurityScopedResource", optional=true)
    public native void stopAccessingSecurityScopedResource();
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
    public static native CFType createPropertyFromResource(CFAllocator alloc, CFURL url, String property, IntPtr errorCode);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="CFCopyHomeDirectoryURL", optional=true)
    public static native CFURL copyHomeDirectoryURL();
    /*</methods>*/
}
