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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFString/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFStringPtr extends Ptr<CFString, CFStringPtr> {}/*</ptr>*/
    
    public static class AsStringMarshaler {
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            try (CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags, true)) {
                return o != null ? o.toString() : null;
            }
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            if (o == null) {
                return 0L;
            }
            try (CFString s = new CFString(o)) {
                // retainCount is now 1
                s.retain(); // Make sure the retainCount is 1 when we exit this try block
                // retainCount is now 2
                return s.getHandle(); // retainCount is 1 after the return
            }
        }
        @AfterBridgeCall
        public static void afterJavaToNative(String before, long after, long flags) {
            if (after != 0) {
                // after is the handle of the CFString returned by toNative().
                // We've already disposed the Java peer so we have to release the
                // ObjC CFString now.
                release(after);
            }
        }
    }
    /**
     * Marshaler used for create and copy methods which have already retained
     * the CFString they return.
     */
    public static class AsStringNoRetainMarshaler {
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            try (CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags, false)) {
                return o != null ? o.toString() : null;
            }
        }
    }
    
    /*<bind>*/static { Bro.bind(CFString.class); }/*</bind>*/
    /*<constants>*/
    public static final float StringEncodingInvalidId = 0xffffffff;
    /*</constants>*/
    
    private static String EMPTY_STRING = "";
    private static final long STRING_VALUE_OFFSET;    
    private static final long STRING_OFFSET_OFFSET;
    
    static {
        try {
            STRING_VALUE_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("value")));
            STRING_OFFSET_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("offset")));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
    
    /*<constructors>*/
    protected CFString() {}
    /*</constructors>*/
    
    public CFString(String s) {
        int offset = VM.getInt(VM.getObjectAddress(s) + STRING_OFFSET_OFFSET);
        char[] value = (char[]) VM.getObject(VM.getObjectAddress(s) + STRING_VALUE_OFFSET);
        long content = VM.getArrayValuesAddress(value) + (offset << 1);
        setHandle(createWithCharacters(null, content, s.length()));
    }
    
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    
    @Override
    public String toString() {
        int len = (int) getLength();
        if (len == 0) {
            return EMPTY_STRING;
        }
        char[] chars = new char[len];
        getCharacters(getHandle(), new CFRange(0, len), VM.getArrayValuesAddress(chars));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    @Bridge(symbol="CFStringCreateWithCharacters")
    private static native @Pointer long createWithCharacters(CFAllocator alloc, @Pointer long buffer, @MachineSizedSInt long numChars);

    @Bridge(symbol="CFStringGetCharacters")
    private static native void getCharacters(@Pointer long str, @ByVal CFRange range, @Pointer long buffer);
    
    /*<methods>*/
    @Bridge(symbol="CFStringGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFStringCreateWithPascalString", optional=true)
    public static native CFString createWithPascalString(CFAllocator alloc, BytePtr pStr, int encoding);
    @Bridge(symbol="CFStringCreateWithCString", optional=true)
    public static native CFString createWithCString(CFAllocator alloc, BytePtr cStr, int encoding);
    @Bridge(symbol="CFStringCreateWithBytes", optional=true)
    public static native CFString createWithBytes(CFAllocator alloc, BytePtr bytes, @MachineSizedSInt long numBytes, int encoding, boolean isExternalRepresentation);
    @Bridge(symbol="CFStringCreateWithCharacters", optional=true)
    public static native CFString createWithCharacters(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringCreateWithPascalStringNoCopy", optional=true)
    public static native CFString createWithPascalStringNoCopy(CFAllocator alloc, BytePtr pStr, int encoding, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithCStringNoCopy", optional=true)
    public static native CFString createWithCStringNoCopy(CFAllocator alloc, BytePtr cStr, int encoding, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithBytesNoCopy", optional=true)
    public static native CFString createWithBytesNoCopy(CFAllocator alloc, BytePtr bytes, @MachineSizedSInt long numBytes, int encoding, boolean isExternalRepresentation, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithCharactersNoCopy", optional=true)
    public static native CFString createWithCharactersNoCopy(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithSubstring", optional=true)
    public static native CFString createWithSubstring(CFAllocator alloc, CFString str, @ByVal CFRange range);
    @Bridge(symbol="CFStringCreateCopy", optional=true)
    public static native CFString createCopy(CFAllocator alloc, CFString theString);
    @Bridge(symbol="CFStringGetLength", optional=true)
    public native @MachineSizedSInt long getLength();
    @Bridge(symbol="CFStringGetCharacterAtIndex", optional=true)
    public native short getCharacterAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFStringGetCharacters", optional=true)
    public native void getCharacters(@ByVal CFRange range, ShortPtr buffer);
    @Bridge(symbol="CFStringGetPascalString", optional=true)
    public native boolean getPascalString(BytePtr buffer, @MachineSizedSInt long bufferSize, int encoding);
    @Bridge(symbol="CFStringGetCString", optional=true)
    public native boolean getCString(BytePtr buffer, @MachineSizedSInt long bufferSize, int encoding);
    @Bridge(symbol="CFStringGetPascalStringPtr", optional=true)
    public native BytePtr getPascalStringPtr(int encoding);
    @Bridge(symbol="CFStringGetCStringPtr", optional=true)
    public native BytePtr getCStringPtr(int encoding);
    @Bridge(symbol="CFStringGetCharactersPtr", optional=true)
    public native ShortPtr getCharactersPtr();
    @Bridge(symbol="CFStringGetBytes", optional=true)
    public native @MachineSizedSInt long getBytes(@ByVal CFRange range, int encoding, byte lossByte, boolean isExternalRepresentation, BytePtr buffer, @MachineSizedSInt long maxBufLen, MachineSizedSIntPtr usedBufLen);
    @Bridge(symbol="CFStringCreateFromExternalRepresentation", optional=true)
    public static native CFString createFromExternalRepresentation(CFAllocator alloc, CFData data, int encoding);
    @Bridge(symbol="CFStringCreateExternalRepresentation", optional=true)
    public static native CFData createExternalRepresentation(CFAllocator alloc, CFString theString, int encoding, byte lossByte);
    @Bridge(symbol="CFStringGetSmallestEncoding", optional=true)
    public native int getSmallestEncoding();
    @Bridge(symbol="CFStringGetFastestEncoding", optional=true)
    public native int getFastestEncoding();
    @Bridge(symbol="CFStringGetSystemEncoding", optional=true)
    public static native int getSystemEncoding();
    @Bridge(symbol="CFStringGetMaximumSizeForEncoding", optional=true)
    public static native @MachineSizedSInt long getMaximumSizeForEncoding(@MachineSizedSInt long length, int encoding);
    @Bridge(symbol="CFStringGetFileSystemRepresentation", optional=true)
    public native boolean getFileSystemRepresentation(BytePtr buffer, @MachineSizedSInt long maxBufLen);
    @Bridge(symbol="CFStringGetMaximumSizeOfFileSystemRepresentation", optional=true)
    public native @MachineSizedSInt long getMaximumSizeOfFileSystemRepresentation();
    @Bridge(symbol="CFStringCreateWithFileSystemRepresentation", optional=true)
    public static native CFString createWithFileSystemRepresentation(CFAllocator alloc, BytePtr buffer);
    @Bridge(symbol="CFStringCompareWithOptionsAndLocale", optional=true)
    public native CFComparisonResult compareWithOptionsAndLocale(CFString theString2, @ByVal CFRange rangeToCompare, CFStringCompareFlags compareOptions, CFLocale locale);
    @Bridge(symbol="CFStringCompareWithOptions", optional=true)
    public native CFComparisonResult compareWithOptions(CFString theString2, @ByVal CFRange rangeToCompare, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringCompare", optional=true)
    public native CFComparisonResult compare(CFString theString2, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringCreateArrayWithFindResults", optional=true)
    public static native CFArray createArrayWithFindResults(CFAllocator alloc, CFString theString, CFString stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringHasPrefix", optional=true)
    public native boolean hasPrefix(CFString prefix);
    @Bridge(symbol="CFStringHasSuffix", optional=true)
    public native boolean hasSuffix(CFString suffix);
    @Bridge(symbol="CFStringGetRangeOfComposedCharactersAtIndex", optional=true)
    public native @ByVal CFRange getRangeOfComposedCharactersAtIndex(@MachineSizedSInt long theIndex);
    @Bridge(symbol="CFStringGetLineBounds", optional=true)
    public native void getLineBounds(@ByVal CFRange range, MachineSizedSIntPtr lineBeginIndex, MachineSizedSIntPtr lineEndIndex, MachineSizedSIntPtr contentsEndIndex);
    @Bridge(symbol="CFStringGetParagraphBounds", optional=true)
    public native void getParagraphBounds(@ByVal CFRange range, MachineSizedSIntPtr parBeginIndex, MachineSizedSIntPtr parEndIndex, MachineSizedSIntPtr contentsEndIndex);
    @Bridge(symbol="CFStringGetHyphenationLocationBeforeIndex", optional=true)
    public native @MachineSizedSInt long getHyphenationLocationBeforeIndex(@MachineSizedSInt long location, @ByVal CFRange limitRange, @MachineSizedUInt long options, CFLocale locale, IntPtr character);
    @Bridge(symbol="CFStringIsHyphenationAvailableForLocale", optional=true)
    public static native boolean isHyphenationAvailableForLocale(CFLocale locale);
    @Bridge(symbol="CFStringCreateByCombiningStrings", optional=true)
    public static native CFString createByCombiningStrings(CFAllocator alloc, CFArray theArray, CFString separatorString);
    @Bridge(symbol="CFStringCreateArrayBySeparatingStrings", optional=true)
    public static native CFArray createArrayBySeparatingStrings(CFAllocator alloc, CFString theString, CFString separatorString);
    @Bridge(symbol="CFStringGetIntValue", optional=true)
    public native int getIntValue();
    @Bridge(symbol="CFStringGetDoubleValue", optional=true)
    public native double getDoubleValue();
    @Bridge(symbol="CFStringIsEncodingAvailable", optional=true)
    public static native boolean isEncodingAvailable(int encoding);
    @Bridge(symbol="CFStringGetListOfAvailableEncodings", optional=true)
    public static native IntPtr getListOfAvailableEncodings();
    @Bridge(symbol="CFStringGetNameOfEncoding", optional=true)
    public static native CFString getNameOfEncoding(int encoding);
    @Bridge(symbol="CFStringConvertEncodingToNSStringEncoding", optional=true)
    public static native @MachineSizedUInt long convertEncodingToNSStringEncoding(int encoding);
    @Bridge(symbol="CFStringConvertNSStringEncodingToEncoding", optional=true)
    public static native int convertNSStringEncodingToEncoding(@MachineSizedUInt long encoding);
    @Bridge(symbol="CFStringConvertEncodingToWindowsCodepage", optional=true)
    public static native int convertEncodingToWindowsCodepage(int encoding);
    @Bridge(symbol="CFStringConvertWindowsCodepageToEncoding", optional=true)
    public static native int convertWindowsCodepageToEncoding(int codepage);
    @Bridge(symbol="CFStringConvertIANACharSetNameToEncoding", optional=true)
    public native int convertIANACharSetNameToEncoding();
    @Bridge(symbol="CFStringConvertEncodingToIANACharSetName", optional=true)
    public static native CFString convertEncodingToIANACharSetName(int encoding);
    @Bridge(symbol="CFStringGetMostCompatibleMacStringEncoding", optional=true)
    public static native int getMostCompatibleMacStringEncoding(int encoding);
    @Bridge(symbol="CFShowStr", optional=true)
    public native void showStr();
    /*</methods>*/
}
