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
