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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMutableString/*</name>*/ 
    extends /*<extends>*/CFString/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMutableStringPtr extends Ptr<CFMutableString, CFMutableStringPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMutableString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    protected CFMutableString() {}
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFStringCreateMutable", optional=true)
    public static native CFMutableString createMutable(CFAllocator alloc, @MachineSizedSInt long maxLength);
    @Bridge(symbol="CFStringCreateMutableCopy", optional=true)
    public static native CFMutableString createMutableCopy(CFAllocator alloc, @MachineSizedSInt long maxLength, CFString theString);
    @Bridge(symbol="CFStringCreateMutableWithExternalCharactersNoCopy", optional=true)
    public static native CFMutableString createMutableWithExternalCharactersNoCopy(CFAllocator alloc, ShortPtr chars, @MachineSizedSInt long numChars, @MachineSizedSInt long capacity, CFAllocator externalCharactersAllocator);
    @Bridge(symbol="CFStringAppend", optional=true)
    public native void append(CFString appendedString);
    @Bridge(symbol="CFStringAppendCharacters", optional=true)
    public native void appendCharacters(ShortPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringAppendPascalString", optional=true)
    public native void appendPascalString(BytePtr pStr, int encoding);
    @Bridge(symbol="CFStringAppendCString", optional=true)
    public native void appendCString(BytePtr cStr, int encoding);
    @Bridge(symbol="CFStringInsert", optional=true)
    public native void insert(@MachineSizedSInt long idx, CFString insertedStr);
    @Bridge(symbol="CFStringDelete", optional=true)
    public native void delete(@ByVal CFRange range);
    @Bridge(symbol="CFStringReplace", optional=true)
    public native void replace(@ByVal CFRange range, CFString replacement);
    @Bridge(symbol="CFStringReplaceAll", optional=true)
    public native void replaceAll(CFString replacement);
    @Bridge(symbol="CFStringFindAndReplace", optional=true)
    public native @MachineSizedSInt long findAndReplace(CFString stringToFind, CFString replacementString, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringSetExternalCharactersNoCopy", optional=true)
    public native void setExternalCharactersNoCopy(ShortPtr chars, @MachineSizedSInt long length, @MachineSizedSInt long capacity);
    @Bridge(symbol="CFStringPad", optional=true)
    public native void pad(CFString padString, @MachineSizedSInt long length, @MachineSizedSInt long indexIntoPad);
    @Bridge(symbol="CFStringTrim", optional=true)
    public native void trim(CFString trimString);
    @Bridge(symbol="CFStringTrimWhitespace", optional=true)
    public native void trimWhitespace();
    @Bridge(symbol="CFStringLowercase", optional=true)
    public native void lowercase(CFLocale locale);
    @Bridge(symbol="CFStringUppercase", optional=true)
    public native void uppercase(CFLocale locale);
    @Bridge(symbol="CFStringCapitalize", optional=true)
    public native void capitalize(CFLocale locale);
    @Bridge(symbol="CFStringNormalize", optional=true)
    public native void normalize(CFStringNormalizationForm theForm);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFStringFold", optional=true)
    public native void fold(CFStringCompareFlags theFlags, CFLocale theLocale);
    @Bridge(symbol="CFStringTransform", optional=true)
    public native boolean transform(CFRange range, CFString transform, boolean reverse);
    /*</methods>*/
}
