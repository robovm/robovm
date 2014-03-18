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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableString/*</name>*/ 
    extends /*<extends>*/CFString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableStringPtr extends Ptr<CFMutableString, CFMutableStringPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMutableString() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFStringTransformStripCombiningMarks")
    public static native CFString TransformStripCombiningMarks();
    @GlobalValue(symbol="kCFStringTransformToLatin")
    public static native CFString TransformToLatin();
    @GlobalValue(symbol="kCFStringTransformFullwidthHalfwidth")
    public static native CFString TransformFullwidthHalfwidth();
    @GlobalValue(symbol="kCFStringTransformLatinKatakana")
    public static native CFString TransformLatinKatakana();
    @GlobalValue(symbol="kCFStringTransformLatinHiragana")
    public static native CFString TransformLatinHiragana();
    @GlobalValue(symbol="kCFStringTransformHiraganaKatakana")
    public static native CFString TransformHiraganaKatakana();
    @GlobalValue(symbol="kCFStringTransformMandarinLatin")
    public static native CFString TransformMandarinLatin();
    @GlobalValue(symbol="kCFStringTransformLatinHangul")
    public static native CFString TransformLatinHangul();
    @GlobalValue(symbol="kCFStringTransformLatinArabic")
    public static native CFString TransformLatinArabic();
    @GlobalValue(symbol="kCFStringTransformLatinHebrew")
    public static native CFString TransformLatinHebrew();
    @GlobalValue(symbol="kCFStringTransformLatinThai")
    public static native CFString TransformLatinThai();
    @GlobalValue(symbol="kCFStringTransformLatinCyrillic")
    public static native CFString TransformLatinCyrillic();
    @GlobalValue(symbol="kCFStringTransformLatinGreek")
    public static native CFString TransformLatinGreek();
    @GlobalValue(symbol="kCFStringTransformToXMLHex")
    public static native CFString TransformToXMLHex();
    @GlobalValue(symbol="kCFStringTransformToUnicodeName")
    public static native CFString TransformToUnicodeName();
    @GlobalValue(symbol="kCFStringTransformStripDiacritics")
    public static native CFString TransformStripDiacritics();
    
    @Bridge(symbol="CFStringCreateMutable")
    public static native CFMutableString createMutable(CFAllocator alloc, @MachineSizedSInt long maxLength);
    @Bridge(symbol="CFStringCreateMutableCopy")
    public static native CFMutableString createMutableCopy(CFAllocator alloc, @MachineSizedSInt long maxLength, CFString theString);
    @Bridge(symbol="CFStringCreateMutableWithExternalCharactersNoCopy")
    public static native CFMutableString createMutableWithExternalCharactersNoCopy(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars, @MachineSizedSInt long capacity, CFAllocator externalCharactersAllocator);
    @Bridge(symbol="CFStringFindWithOptionsAndLocale")
    public static native boolean findWithOptionsAndLocale(CFString theString, CFString stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFLocale locale, CFRange result);
    @Bridge(symbol="CFStringFindWithOptions")
    public static native boolean findWithOptions(CFString theString, CFString stringToFind, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFRange result);
    @Bridge(symbol="CFStringFind")
    public static native @ByVal CFRange find(CFString theString, CFString stringToFind, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringFindCharacterFromSet")
    public static native boolean findCharacterFromSet(CFString theString, CFCharacterSet theSet, @ByVal CFRange rangeToSearch, CFStringCompareFlags searchOptions, CFRange result);
    @Bridge(symbol="CFStringAppend")
    public native void append(CFString appendedString);
    @Bridge(symbol="CFStringAppendCharacters")
    public native void appendCharacters(ShortPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringAppendPascalString")
    public native void appendPascalString(BytePtr pStr, int encoding);
    @Bridge(symbol="CFStringAppendCString")
    public native void appendCString(BytePtr cStr, int encoding);
    @Bridge(symbol="CFStringInsert")
    public native void insert(@MachineSizedSInt long idx, CFString insertedStr);
    @Bridge(symbol="CFStringDelete")
    public native void delete(@ByVal CFRange range);
    @Bridge(symbol="CFStringReplace")
    public native void replace(@ByVal CFRange range, CFString replacement);
    @Bridge(symbol="CFStringReplaceAll")
    public native void replaceAll(CFString replacement);
    @Bridge(symbol="CFStringFindAndReplace")
    public native @MachineSizedSInt long findAndReplace(CFString stringToFind, CFString replacementString, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringSetExternalCharactersNoCopy")
    public native void setExternalCharactersNoCopy(ShortPtr chars, @MachineSizedSInt long length, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFStringPad")
    public native void pad(CFString padString, @MachineSizedSInt long length, @MachineSizedSInt long indexIntoPad);
    @Bridge(symbol="CFStringTrim")
    public native void trim(CFString trimString);
    @Bridge(symbol="CFStringTrimWhitespace")
    public native void trimWhitespace();
    @Bridge(symbol="CFStringLowercase")
    public native void lowercase(CFLocale locale);
    @Bridge(symbol="CFStringUppercase")
    public native void uppercase(CFLocale locale);
    @Bridge(symbol="CFStringCapitalize")
    public native void capitalize(CFLocale locale);
    @Bridge(symbol="CFStringNormalize")
    public native void normalize(CFStringNormalizationForm theForm);
    @Bridge(symbol="CFStringFold")
    public native void fold(CFStringCompareFlags theFlags, CFLocale theLocale);
    @Bridge(symbol="CFStringTransform")
    public native boolean transform(CFRange range, CFString transform, boolean reverse);
    /*</methods>*/
}
