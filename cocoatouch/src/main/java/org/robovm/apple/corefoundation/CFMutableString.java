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
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
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
    public static native void append(CFString theString, CFString appendedString);
    @Bridge(symbol="CFStringAppendCharacters")
    public static native void appendCharacters(CFString theString, ShortPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringAppendPascalString")
    public static native void appendPascalString(CFString theString, BytePtr pStr, int encoding);
    @Bridge(symbol="CFStringAppendCString")
    public static native void appendCString(CFString theString, BytePtr cStr, int encoding);
    @Bridge(symbol="CFStringInsert")
    public static native void insert(CFString str, @MachineSizedSInt long idx, CFString insertedStr);
    @Bridge(symbol="CFStringDelete")
    public static native void delete(CFString theString, @ByVal CFRange range);
    @Bridge(symbol="CFStringReplace")
    public static native void replace(CFString theString, @ByVal CFRange range, CFString replacement);
    @Bridge(symbol="CFStringReplaceAll")
    public static native void replaceAll(CFString theString, CFString replacement);
    @Bridge(symbol="CFStringFindAndReplace")
    public static native @MachineSizedSInt long findAndReplace(CFString theString, CFString stringToFind, CFString replacementString, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringSetExternalCharactersNoCopy")
    public static native void setExternalCharactersNoCopy(CFString theString, ShortPtr chars, @MachineSizedSInt long length, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFStringPad")
    public static native void pad(CFString theString, CFString padString, @MachineSizedSInt long length, @MachineSizedSInt long indexIntoPad);
    @Bridge(symbol="CFStringTrim")
    public static native void trim(CFString theString, CFString trimString);
    @Bridge(symbol="CFStringTrimWhitespace")
    public static native void trimWhitespace(CFString theString);
    @Bridge(symbol="CFStringLowercase")
    public static native void lowercase(CFString theString, CFLocale locale);
    @Bridge(symbol="CFStringUppercase")
    public static native void uppercase(CFString theString, CFLocale locale);
    @Bridge(symbol="CFStringCapitalize")
    public static native void capitalize(CFString theString, CFLocale locale);
    @Bridge(symbol="CFStringNormalize")
    public static native void normalize(CFString theString, CFStringNormalizationForm theForm);
    @Bridge(symbol="CFStringFold")
    public static native void fold(CFString theString, CFStringCompareFlags theFlags, CFLocale theLocale);
    @Bridge(symbol="CFStringTransform")
    public static native boolean transform(CFString string, CFRange range, CFString transform, boolean reverse);
    /*</methods>*/
}
