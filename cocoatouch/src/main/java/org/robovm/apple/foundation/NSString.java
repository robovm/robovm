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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSString/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSStringPtr extends Ptr<NSString, NSStringPtr> {}/*</ptr>*/

    public static class AsStringMarshaler {
        @MarshalsPointer
        public static String toObject(Class<?> cls, long handle, long flags) {
            NSString o = ObjCObject.toObjCObject(NSString.class, handle);
            try {
                return o != null ? o.toString() : null;
            } finally {
                o.dispose();
            }
        }
        @MarshalsPointer
        public static long toNative(String o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSString s = new NSString(o);
            try {
                // retainCount is now 1
                s.retain(); // Make sure the retainCount is 1 when we exit this try block
                // retainCount is now 2
                if ((flags & MarshalerFlags.CALL_TYPE_CALLBACK) > 0) {
                    // NSStrings returned by callbacks should be autoreleased
                    s.autorelease();
                }
                return s.getHandle(); // retainCount is 1 after the return
            } finally {
                s.dispose();
            }
        }
        @AfterBridgeCall
        public static void afterJavaToNative(String before, long after, long flags) {
            if (after != 0) {
                // after is the handle of the NSString returned by toNative().
                // We've already disposed the Java peer so we have to release the
                // ObjC NSString now.
                release(after);
            }
        }
    }
    
    private static String EMPTY_STRING = "";
    private static final long STRING_VALUE_OFFSET;    
    private static final long STRING_OFFSET_OFFSET;    
    /*<bind>*/static { ObjCRuntime.bind(NSString.class); }/*</bind>*/
    static {
        try {
            STRING_VALUE_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("value")));
            STRING_OFFSET_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(String.class.getDeclaredField("offset")));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
    /*<constants>*//*</constants>*/
    
    public NSString(String s) {
        this(copyChars(s), s.length(), true);
    }
    
    /*<constructors>*/
    public NSString() {}
    protected NSString(SkipInit skipInit) { super(skipInit); }
    public NSString(@Pointer long characters, @MachineSizedUInt long length, boolean freeBuffer) { super((SkipInit) null); initObject(initWithCharactersNoCopy$length$freeWhenDone$(characters, length, freeBuffer)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    private static long copyChars(String s) {
        int len = s.length();
        int offset = VM.getInt(VM.getObjectAddress(s) + STRING_OFFSET_OFFSET);
        char[] value = (char[]) VM.getObject(VM.getObjectAddress(s) + STRING_VALUE_OFFSET);
        long dest = VM.malloc(len << 1);
        VM.memcpy(dest, VM.getArrayValuesAddress(value) + (offset << 1), len << 1);
        return dest;
    }
    
    @Override
    public String toString() {
        int len = (int) length();
        if (len == 0) {
            return EMPTY_STRING;
        }
        char[] chars = new char[len];
        getCharacters$range$(VM.getArrayValuesAddress(chars), new NSRange(0, len));
        return VM.newStringNoCopy(chars, 0, len);
    }
    
    /*<methods>*/
    @Method(selector = "length")
    public native @MachineSizedUInt long length();
    @Method(selector = "characterAtIndex:")
    public native short characterAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "getCharacters:range:")
    private native void getCharacters$range$(@Pointer long buffer, @ByVal NSRange aRange);
    @Method(selector = "substringFromIndex:")
    public native String substringFromIndex$(@MachineSizedUInt long from);
    @Method(selector = "substringToIndex:")
    public native String substringToIndex$(@MachineSizedUInt long to);
    @Method(selector = "substringWithRange:")
    public native String substringWithRange$(@ByVal NSRange range);
    @Method(selector = "compare:")
    public native NSComparisonResult compare$(String string);
    @Method(selector = "compare:options:")
    public native NSComparisonResult compare$options$(String string, NSStringCompareOptions mask);
    @Method(selector = "compare:options:range:")
    public native NSComparisonResult compare$options$range$(String string, NSStringCompareOptions mask, @ByVal NSRange compareRange);
    @Method(selector = "compare:options:range:locale:")
    public native NSComparisonResult compare$options$range$locale$(String string, NSStringCompareOptions mask, @ByVal NSRange compareRange, NSObject locale);
    @Method(selector = "caseInsensitiveCompare:")
    public native NSComparisonResult caseInsensitiveCompare$(String string);
    @Method(selector = "localizedCompare:")
    public native NSComparisonResult localizedCompare$(String string);
    @Method(selector = "localizedCaseInsensitiveCompare:")
    public native NSComparisonResult localizedCaseInsensitiveCompare$(String string);
    @Method(selector = "localizedStandardCompare:")
    public native NSComparisonResult localizedStandardCompare$(String string);
    @Method(selector = "isEqualToString:")
    public native boolean isEqualToString$(String aString);
    @Method(selector = "hasPrefix:")
    public native boolean hasPrefix$(String aString);
    @Method(selector = "hasSuffix:")
    public native boolean hasSuffix$(String aString);
    @Method(selector = "rangeOfString:")
    public native @ByVal NSRange rangeOfString$(String aString);
    @Method(selector = "rangeOfString:options:")
    public native @ByVal NSRange rangeOfString$options$(String aString, NSStringCompareOptions mask);
    @Method(selector = "rangeOfString:options:range:")
    public native @ByVal NSRange rangeOfString$options$range$(String aString, NSStringCompareOptions mask, @ByVal NSRange searchRange);
    @Method(selector = "rangeOfString:options:range:locale:")
    public native @ByVal NSRange rangeOfString$options$range$locale$(String aString, NSStringCompareOptions mask, @ByVal NSRange searchRange, NSLocale locale);
    @Method(selector = "rangeOfCharacterFromSet:")
    public native @ByVal NSRange rangeOfCharacterFromSet$(NSCharacterSet aSet);
    @Method(selector = "rangeOfCharacterFromSet:options:")
    public native @ByVal NSRange rangeOfCharacterFromSet$options$(NSCharacterSet aSet, NSStringCompareOptions mask);
    @Method(selector = "rangeOfCharacterFromSet:options:range:")
    public native @ByVal NSRange rangeOfCharacterFromSet$options$range$(NSCharacterSet aSet, NSStringCompareOptions mask, @ByVal NSRange searchRange);
    @Method(selector = "rangeOfComposedCharacterSequenceAtIndex:")
    public native @ByVal NSRange rangeOfComposedCharacterSequenceAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "rangeOfComposedCharacterSequencesForRange:")
    public native @ByVal NSRange rangeOfComposedCharacterSequencesForRange$(@ByVal NSRange range);
    @Method(selector = "stringByAppendingString:")
    public native String stringByAppendingString$(String aString);
    @Method(selector = "doubleValue")
    public native double doubleValue();
    @Method(selector = "floatValue")
    public native float floatValue();
    @Method(selector = "intValue")
    public native int intValue();
    @Method(selector = "integerValue")
    public native @MachineSizedSInt long integerValue();
    @Method(selector = "longLongValue")
    public native long longLongValue();
    @Method(selector = "boolValue")
    public native boolean boolValue();
    @Method(selector = "componentsSeparatedByString:")
    public native NSArray<?> componentsSeparatedByString$(String separator);
    @Method(selector = "componentsSeparatedByCharactersInSet:")
    public native NSArray<?> componentsSeparatedByCharactersInSet$(NSCharacterSet separator);
    @Method(selector = "commonPrefixWithString:options:")
    public native String commonPrefixWithString$options$(String aString, NSStringCompareOptions mask);
    @Method(selector = "uppercaseString")
    public native String uppercaseString();
    @Method(selector = "lowercaseString")
    public native String lowercaseString();
    @Method(selector = "capitalizedString")
    public native String capitalizedString();
    @Method(selector = "uppercaseStringWithLocale:")
    public native String uppercaseStringWithLocale$(NSLocale locale);
    @Method(selector = "lowercaseStringWithLocale:")
    public native String lowercaseStringWithLocale$(NSLocale locale);
    @Method(selector = "capitalizedStringWithLocale:")
    public native String capitalizedStringWithLocale$(NSLocale locale);
    @Method(selector = "stringByTrimmingCharactersInSet:")
    public native String stringByTrimmingCharactersInSet$(NSCharacterSet set);
    @Method(selector = "stringByPaddingToLength:withString:startingAtIndex:")
    public native String stringByPaddingToLength$withString$startingAtIndex$(@MachineSizedUInt long newLength, String padString, @MachineSizedUInt long padIndex);
    @Method(selector = "getLineStart:end:contentsEnd:forRange:")
    public native void getLineStart$end$contentsEnd$forRange$(MachineSizedUIntPtr startPtr, MachineSizedUIntPtr lineEndPtr, MachineSizedUIntPtr contentsEndPtr, @ByVal NSRange range);
    @Method(selector = "lineRangeForRange:")
    public native @ByVal NSRange lineRangeForRange$(@ByVal NSRange range);
    @Method(selector = "getParagraphStart:end:contentsEnd:forRange:")
    public native void getParagraphStart$end$contentsEnd$forRange$(MachineSizedUIntPtr startPtr, MachineSizedUIntPtr parEndPtr, MachineSizedUIntPtr contentsEndPtr, @ByVal NSRange range);
    @Method(selector = "paragraphRangeForRange:")
    public native @ByVal NSRange paragraphRangeForRange$(@ByVal NSRange range);
    @Method(selector = "enumerateSubstringsInRange:options:usingBlock:")
    public native void enumerateSubstringsInRange$options$usingBlock$(@ByVal NSRange range, NSStringEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "enumerateLinesUsingBlock:")
    public native void enumerateLinesUsingBlock$(ObjCBlock block);
    @Method(selector = "description")
    public native String description();
    @Method(selector = "hash")
    public native @MachineSizedUInt long hash();
    @Method(selector = "fastestEncoding")
    public native @MachineSizedUInt long fastestEncoding();
    @Method(selector = "smallestEncoding")
    public native @MachineSizedUInt long smallestEncoding();
    @Method(selector = "dataUsingEncoding:allowLossyConversion:")
    public native NSData dataUsingEncoding$allowLossyConversion$(@MachineSizedUInt long encoding, boolean lossy);
    @Method(selector = "dataUsingEncoding:")
    public native NSData dataUsingEncoding$(@MachineSizedUInt long encoding);
    @Method(selector = "canBeConvertedToEncoding:")
    public native boolean canBeConvertedToEncoding$(@MachineSizedUInt long encoding);
    @Method(selector = "cStringUsingEncoding:")
    public native BytePtr cStringUsingEncoding$(@MachineSizedUInt long encoding);
    @Method(selector = "getCString:maxLength:encoding:")
    public native boolean getCString$maxLength$encoding$(BytePtr buffer, @MachineSizedUInt long maxBufferCount, @MachineSizedUInt long encoding);
    @Method(selector = "getBytes:maxLength:usedLength:encoding:options:range:remainingRange:")
    public native boolean getBytes$maxLength$usedLength$encoding$options$range$remainingRange$(VoidPtr buffer, @MachineSizedUInt long maxBufferCount, MachineSizedUIntPtr usedBufferCount, @MachineSizedUInt long encoding, NSStringEncodingConversionOptions options, @ByVal NSRange range, NSRange leftover);
    @Method(selector = "maximumLengthOfBytesUsingEncoding:")
    public native @MachineSizedUInt long maximumLengthOfBytesUsingEncoding$(@MachineSizedUInt long enc);
    @Method(selector = "lengthOfBytesUsingEncoding:")
    public native @MachineSizedUInt long lengthOfBytesUsingEncoding$(@MachineSizedUInt long enc);
    @Method(selector = "decomposedStringWithCanonicalMapping")
    public native String decomposedStringWithCanonicalMapping();
    @Method(selector = "precomposedStringWithCanonicalMapping")
    public native String precomposedStringWithCanonicalMapping();
    @Method(selector = "decomposedStringWithCompatibilityMapping")
    public native String decomposedStringWithCompatibilityMapping();
    @Method(selector = "precomposedStringWithCompatibilityMapping")
    public native String precomposedStringWithCompatibilityMapping();
    @Method(selector = "stringByFoldingWithOptions:locale:")
    public native String stringByFoldingWithOptions$locale$(NSStringCompareOptions options, NSLocale locale);
    @Method(selector = "stringByReplacingOccurrencesOfString:withString:options:range:")
    public native String stringByReplacingOccurrencesOfString$withString$options$range$(String target, String replacement, NSStringCompareOptions options, @ByVal NSRange searchRange);
    @Method(selector = "stringByReplacingOccurrencesOfString:withString:")
    public native String stringByReplacingOccurrencesOfString$withString$(String target, String replacement);
    @Method(selector = "stringByReplacingCharactersInRange:withString:")
    public native String stringByReplacingCharactersInRange$withString$(@ByVal NSRange range, String replacement);
    @Method(selector = "UTF8String")
    public native BytePtr UTF8String();
    @Method(selector = "initWithCharactersNoCopy:length:freeWhenDone:")
    private native @Pointer long initWithCharactersNoCopy$length$freeWhenDone$(@Pointer long characters, @MachineSizedUInt long length, boolean freeBuffer);
    @Method(selector = "writeToURL:atomically:encoding:error:")
    public native boolean writeToURL$atomically$encoding$error$(NSURL url, boolean useAuxiliaryFile, @MachineSizedUInt long enc, NSError.NSErrorPtr error);
    @Method(selector = "writeToFile:atomically:encoding:error:")
    public native boolean writeToFile$atomically$encoding$error$(String path, boolean useAuxiliaryFile, @MachineSizedUInt long enc, NSError.NSErrorPtr error);
    @Method(selector = "defaultCStringEncoding")
    public static native @MachineSizedUInt long defaultCStringEncoding();
    @Method(selector = "availableStringEncodings")
    public static native MachineSizedUIntPtr availableStringEncodings();
    @Method(selector = "localizedNameOfStringEncoding:")
    public static native String localizedNameOfStringEncoding$(@MachineSizedUInt long encoding);
    @Method(selector = "string")
    public static native NSString string();
    @Method(selector = "stringWithString:")
    public static native NSString stringWithString$(String string);
    @Method(selector = "stringWithCharacters:length:")
    public static native NSString stringWithCharacters$length$(ShortPtr characters, @MachineSizedUInt long length);
    @Method(selector = "stringWithUTF8String:")
    public static native NSString stringWithUTF8String$(BytePtr nullTerminatedCString);
    @Method(selector = "stringWithCString:encoding:")
    public static native NSString stringWithCString$encoding$(BytePtr cString, @MachineSizedUInt long enc);
    @Method(selector = "stringWithContentsOfURL:encoding:error:")
    public static native NSString stringWithContentsOfURL$encoding$error$(NSURL url, @MachineSizedUInt long enc, NSError.NSErrorPtr error);
    @Method(selector = "stringWithContentsOfFile:encoding:error:")
    public static native NSString stringWithContentsOfFile$encoding$error$(String path, @MachineSizedUInt long enc, NSError.NSErrorPtr error);
    @Method(selector = "stringWithContentsOfURL:usedEncoding:error:")
    public static native NSString stringWithContentsOfURL$usedEncoding$error$(NSURL url, MachineSizedUIntPtr enc, NSError.NSErrorPtr error);
    @Method(selector = "stringWithContentsOfFile:usedEncoding:error:")
    public static native NSString stringWithContentsOfFile$usedEncoding$error$(String path, MachineSizedUIntPtr enc, NSError.NSErrorPtr error);
    @Method(selector = "propertyList")
    public native NSObject propertyList();
    @Method(selector = "propertyListFromStringsFileFormat")
    public native NSDictionary<?, ?> propertyListFromStringsFileFormat();
    @Method(selector = "cString")
    public native BytePtr cString();
    @Method(selector = "lossyCString")
    public native BytePtr lossyCString();
    @Method(selector = "cStringLength")
    public native @MachineSizedUInt long cStringLength();
    @Method(selector = "getCString:")
    public native void getCString$(BytePtr bytes);
    @Method(selector = "getCString:maxLength:")
    public native void getCString$maxLength$(BytePtr bytes, @MachineSizedUInt long maxLength);
    @Method(selector = "getCString:maxLength:range:remainingRange:")
    public native void getCString$maxLength$range$remainingRange$(BytePtr bytes, @MachineSizedUInt long maxLength, @ByVal NSRange aRange, NSRange leftoverRange);
    @Method(selector = "writeToFile:atomically:")
    public native boolean writeToFile$atomically$(String path, boolean useAuxiliaryFile);
    @Method(selector = "writeToURL:atomically:")
    public native boolean writeToURL$atomically$(NSURL url, boolean atomically);
    @Method(selector = "getCharacters:")
    public native void getCharacters$(ShortPtr buffer);
    @Method(selector = "stringWithContentsOfFile:")
    public static native NSObject stringWithContentsOfFile$(String path);
    @Method(selector = "stringWithContentsOfURL:")
    public static native NSObject stringWithContentsOfURL$(NSURL url);
    @Method(selector = "stringWithCString:length:")
    public static native NSObject stringWithCString$length$(BytePtr bytes, @MachineSizedUInt long length);
    @Method(selector = "stringWithCString:")
    public static native NSObject stringWithCString$(BytePtr bytes);
    @Method(selector = "pathComponents")
    public native NSArray<?> pathComponents();
    @Method(selector = "isAbsolutePath")
    public native boolean isAbsolutePath();
    @Method(selector = "lastPathComponent")
    public native String lastPathComponent();
    @Method(selector = "stringByDeletingLastPathComponent")
    public native String stringByDeletingLastPathComponent();
    @Method(selector = "stringByAppendingPathComponent:")
    public native String stringByAppendingPathComponent$(String str);
    @Method(selector = "pathExtension")
    public native String pathExtension();
    @Method(selector = "stringByDeletingPathExtension")
    public native String stringByDeletingPathExtension();
    @Method(selector = "stringByAppendingPathExtension:")
    public native String stringByAppendingPathExtension$(String str);
    @Method(selector = "stringByAbbreviatingWithTildeInPath")
    public native String stringByAbbreviatingWithTildeInPath();
    @Method(selector = "stringByExpandingTildeInPath")
    public native String stringByExpandingTildeInPath();
    @Method(selector = "stringByStandardizingPath")
    public native String stringByStandardizingPath();
    @Method(selector = "stringByResolvingSymlinksInPath")
    public native String stringByResolvingSymlinksInPath();
    @Method(selector = "stringsByAppendingPaths:")
    public native NSArray<?> stringsByAppendingPaths$(NSArray<?> paths);
    @Method(selector = "completePathIntoString:caseSensitive:matchesIntoArray:filterTypes:")
    public native @MachineSizedUInt long completePathIntoString$caseSensitive$matchesIntoArray$filterTypes$(NSString.NSStringPtr outputName, boolean flag, NSArray.NSArrayPtr<?> outputArray, NSArray<?> filterTypes);
    @Method(selector = "fileSystemRepresentation")
    public native BytePtr fileSystemRepresentation();
    @Method(selector = "getFileSystemRepresentation:maxLength:")
    public native boolean getFileSystemRepresentation$maxLength$(BytePtr cname, @MachineSizedUInt long max);
    @Method(selector = "pathWithComponents:")
    public static native String pathWithComponents$(NSArray<?> components);
    @Method(selector = "stringByAddingPercentEncodingWithAllowedCharacters:")
    public native String stringByAddingPercentEncodingWithAllowedCharacters$(NSCharacterSet allowedCharacters);
    @Method(selector = "stringByRemovingPercentEncoding")
    public native String stringByRemovingPercentEncoding();
    @Method(selector = "stringByAddingPercentEscapesUsingEncoding:")
    public native String stringByAddingPercentEscapesUsingEncoding$(@MachineSizedUInt long enc);
    @Method(selector = "stringByReplacingPercentEscapesUsingEncoding:")
    public native String stringByReplacingPercentEscapesUsingEncoding$(@MachineSizedUInt long enc);
    @Method(selector = "linguisticTagsInRange:scheme:options:orthography:tokenRanges:")
    public native NSArray<?> linguisticTagsInRange$scheme$options$orthography$tokenRanges$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, NSOrthography orthography, NSArray.NSArrayPtr<?> tokenRanges);
    @Method(selector = "enumerateLinguisticTagsInRange:scheme:options:orthography:usingBlock:")
    public native void enumerateLinguisticTagsInRange$scheme$options$orthography$usingBlock$(@ByVal NSRange range, String tagScheme, NSLinguisticTaggerOptions opts, NSOrthography orthography, ObjCBlock block);
    /*</methods>*/
}
