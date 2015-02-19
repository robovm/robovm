/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
    /*<constants>*//*</constants>*/
    
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
        int len = (int) length();
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
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithPascalString(CFAllocator alloc, BytePtr pStr, CFStringEncodings encoding);
    @Bridge(symbol="CFStringCreateWithCString", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithCString(CFAllocator alloc, BytePtr cStr, CFStringEncodings encoding);
    @Bridge(symbol="CFStringCreateWithBytes", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithBytes(CFAllocator alloc, BytePtr bytes, @MachineSizedSInt long numBytes, CFStringEncodings encoding, boolean isExternalRepresentation);
    @Bridge(symbol="CFStringCreateWithCharacters", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithCharacters(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringCreateWithPascalStringNoCopy", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithPascalStringNoCopy(CFAllocator alloc, BytePtr pStr, CFStringEncodings encoding, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithCStringNoCopy", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithCStringNoCopy(CFAllocator alloc, BytePtr cStr, CFStringEncodings encoding, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithBytesNoCopy", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithBytesNoCopy(CFAllocator alloc, BytePtr bytes, @MachineSizedSInt long numBytes, CFStringEncodings encoding, boolean isExternalRepresentation, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithCharactersNoCopy", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithCharactersNoCopy(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars, CFAllocator contentsDeallocator);
    @Bridge(symbol="CFStringCreateWithSubstring", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithSubstring(CFAllocator alloc, String str, @ByVal CFRange range);
    @Bridge(symbol="CFStringCreateCopy", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createCopy(CFAllocator alloc, CFString theString);
    @Bridge(symbol="CFStringGetLength", optional=true)
    public native @MachineSizedSInt long length();
    @Bridge(symbol="CFStringGetCharacterAtIndex", optional=true)
    protected native short getCharacterAtIndex(@MachineSizedSInt long idx);
    @Bridge(symbol="CFStringGetCharacters", optional=true)
    protected native void getCharacters(@ByVal CFRange range, ShortPtr buffer);
    @Bridge(symbol="CFStringGetPascalString", optional=true)
    protected native boolean getPascalString(BytePtr buffer, @MachineSizedSInt long bufferSize, CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetCString", optional=true)
    protected native boolean getCString(BytePtr buffer, @MachineSizedSInt long bufferSize, CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetPascalStringPtr", optional=true)
    protected native BytePtr getPascalStringPtr(CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetCStringPtr", optional=true)
    protected native BytePtr getCStringPtr(CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetCharactersPtr", optional=true)
    protected native ShortPtr getCharactersPtr();
    @Bridge(symbol="CFStringGetBytes", optional=true)
    protected native @MachineSizedSInt long getBytes(@ByVal CFRange range, CFStringEncodings encoding, byte lossByte, boolean isExternalRepresentation, BytePtr buffer, @MachineSizedSInt long maxBufLen, MachineSizedSIntPtr usedBufLen);
    @Bridge(symbol="CFStringCreateFromExternalRepresentation", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createFromExternalRepresentation(CFAllocator alloc, CFData data, CFStringEncodings encoding);
    @Bridge(symbol="CFStringCreateExternalRepresentation", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createExternalRepresentation(CFAllocator alloc, CFString theString, CFStringEncodings encoding, byte lossByte);
    @Bridge(symbol="CFStringGetSmallestEncoding", optional=true)
    protected native CFStringEncodings getSmallestEncoding();
    @Bridge(symbol="CFStringGetFastestEncoding", optional=true)
    protected native CFStringEncodings getFastestEncoding();
    @Bridge(symbol="CFStringGetSystemEncoding", optional=true)
    protected static native CFStringEncodings getSystemEncoding();
    @Bridge(symbol="CFStringGetMaximumSizeForEncoding", optional=true)
    protected static native @MachineSizedSInt long getMaximumSizeForEncoding(@MachineSizedSInt long length, CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetFileSystemRepresentation", optional=true)
    protected static native boolean getFileSystemRepresentation(String string, BytePtr buffer, @MachineSizedSInt long maxBufLen);
    @Bridge(symbol="CFStringGetMaximumSizeOfFileSystemRepresentation", optional=true)
    protected static native @MachineSizedSInt long getMaximumSizeOfFileSystemRepresentation(String string);
    @Bridge(symbol="CFStringCreateWithFileSystemRepresentation", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createWithFileSystemRepresentation(CFAllocator alloc, BytePtr buffer);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFStringCompareWithOptionsAndLocale", optional=true)
    public static native CFComparisonResult compare(String theString1, String theString2, @ByVal CFRange rangeToCompare, CFStringCompareFlags compareOptions, CFLocale locale);
    @Bridge(symbol="CFStringCompareWithOptions", optional=true)
    public static native CFComparisonResult compare(String theString1, String theString2, @ByVal CFRange rangeToCompare, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringCompare", optional=true)
    public static native CFComparisonResult compare(String theString1, String theString2, CFStringCompareFlags compareOptions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFStringFindWithOptionsAndLocale", optional=true)
    public native boolean find(String stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFLocale locale, CFRange result);
    @Bridge(symbol="CFStringFindWithOptions", optional=true)
    public native boolean find(String stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFRange result);
    @Bridge(symbol="CFStringCreateArrayWithFindResults", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createArrayWithFindResults(CFAllocator alloc, CFString theString, String stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringFind", optional=true)
    public native @ByVal CFRange find(String stringToFind, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringHasPrefix", optional=true)
    public native boolean hasPrefix(String prefix);
    @Bridge(symbol="CFStringHasSuffix", optional=true)
    public native boolean hasSuffix(String suffix);
    @Bridge(symbol="CFStringGetRangeOfComposedCharactersAtIndex", optional=true)
    protected native @ByVal CFRange getRangeOfComposedCharactersAtIndex(@MachineSizedSInt long theIndex);
    @Bridge(symbol="CFStringFindCharacterFromSet", optional=true)
    public native boolean find(CFCharacterSet theSet, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFRange result);
    @Bridge(symbol="CFStringGetLineBounds", optional=true)
    protected native void getLineBounds(@ByVal CFRange range, MachineSizedSIntPtr lineBeginIndex, MachineSizedSIntPtr lineEndIndex, MachineSizedSIntPtr contentsEndIndex);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFStringGetParagraphBounds", optional=true)
    protected static native void getParagraphBounds(String string, @ByVal CFRange range, MachineSizedSIntPtr parBeginIndex, MachineSizedSIntPtr parEndIndex, MachineSizedSIntPtr contentsEndIndex);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="CFStringGetHyphenationLocationBeforeIndex", optional=true)
    protected native @MachineSizedSInt long getHyphenationLocationBeforeIndex(@MachineSizedSInt long location, @ByVal CFRange limitRange, @MachineSizedUInt long options, CFLocale locale, IntPtr character);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Bridge(symbol="CFStringIsHyphenationAvailableForLocale", optional=true)
    protected static native boolean isHyphenationAvailableForLocale(CFLocale locale);
    @Bridge(symbol="CFStringCreateByCombiningStrings", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createByCombiningStrings(CFAllocator alloc, CFArray theArray, String separatorString);
    @Bridge(symbol="CFStringCreateArrayBySeparatingStrings", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFString createArrayBySeparatingStrings(CFAllocator alloc, CFString theString, String separatorString);
    @Bridge(symbol="CFStringGetIntValue", optional=true)
    protected static native int getIntValue(String str);
    @Bridge(symbol="CFStringGetDoubleValue", optional=true)
    protected static native double getDoubleValue(String str);
    @Bridge(symbol="CFStringIsEncodingAvailable", optional=true)
    protected static native boolean isEncodingAvailable(CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetListOfAvailableEncodings", optional=true)
    protected static native MachineSizedUIntPtr getListOfAvailableEncodings();
    @Bridge(symbol="CFStringGetNameOfEncoding", optional=true)
    protected static native String getNameOfEncoding(CFStringEncodings encoding);
    @Bridge(symbol="CFStringConvertEncodingToNSStringEncoding", optional=true)
    protected static native @MachineSizedUInt long convertEncodingToNSStringEncoding(CFStringEncodings encoding);
    @Bridge(symbol="CFStringConvertNSStringEncodingToEncoding", optional=true)
    protected static native CFStringEncodings convertNSStringEncodingToEncoding(@MachineSizedUInt long encoding);
    @Bridge(symbol="CFStringConvertEncodingToWindowsCodepage", optional=true)
    protected static native int convertEncodingToWindowsCodepage(CFStringEncodings encoding);
    @Bridge(symbol="CFStringConvertWindowsCodepageToEncoding", optional=true)
    protected static native CFStringEncodings convertWindowsCodepageToEncoding(int codepage);
    @Bridge(symbol="CFStringConvertIANACharSetNameToEncoding", optional=true)
    protected native CFStringEncodings convertIANACharSetNameToEncoding();
    @Bridge(symbol="CFStringConvertEncodingToIANACharSetName", optional=true)
    protected static native String convertEncodingToIANACharSetName(CFStringEncodings encoding);
    @Bridge(symbol="CFStringGetMostCompatibleMacStringEncoding", optional=true)
    protected static native CFStringEncodings getMostCompatibleMacStringEncoding(CFStringEncodings encoding);
    @Bridge(symbol="CFShowStr", optional=true)
    public native void show();
    /*</methods>*/
}
