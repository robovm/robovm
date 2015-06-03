/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
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
    public static CFMutableString create(@MachineSizedSInt long maxLength) {
        return create((CFAllocator)null, maxLength);
    }
    public static CFMutableString createCopy(@MachineSizedSInt long maxLength, CFMutableString theString) {
        return createCopy(null, maxLength, theString);
    }
    private void appendCharacters(char[] chars) {
        CharPtr ptr = new CharPtr();
        ptr.set(chars);
        appendCharacters(ptr, chars.length);
    }
    /*<methods>*/
    @Bridge(symbol="CFStringCreateMutable", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableString create(CFAllocator alloc, @MachineSizedSInt long maxLength);
    @Bridge(symbol="CFStringCreateMutableCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFMutableString createCopy(CFAllocator alloc, @MachineSizedSInt long maxLength, CFMutableString theString);
    @Bridge(symbol="CFStringAppend", optional=true)
    public native void append(String appendedString);
    @Bridge(symbol="CFStringAppendCharacters", optional=true)
    private native void appendCharacters(CharPtr chars, @MachineSizedSInt long numChars);
    @Bridge(symbol="CFStringAppendPascalString", optional=true)
    public native void appendPascalString(BytePtr pStr, CFStringEncodings encoding);
    @Bridge(symbol="CFStringAppendCString", optional=true)
    public native void appendCString(BytePtr cStr, CFStringEncodings encoding);
    @Bridge(symbol="CFStringInsert", optional=true)
    public native void insert(@MachineSizedSInt long idx, String insertedStr);
    @Bridge(symbol="CFStringDelete", optional=true)
    public native void delete(@ByVal CFRange range);
    @Bridge(symbol="CFStringReplace", optional=true)
    public native void replace(@ByVal CFRange range, String replacement);
    @Bridge(symbol="CFStringReplaceAll", optional=true)
    public native void replaceAll(String replacement);
    @Bridge(symbol="CFStringFindAndReplace", optional=true)
    public native @MachineSizedSInt long findAndReplace(String stringToFind, String replacementString, @ByVal CFRange rangeToSearch, CFStringCompareFlags compareOptions);
    @Bridge(symbol="CFStringPad", optional=true)
    public native void pad(String padString, @MachineSizedSInt long length, @MachineSizedSInt long indexIntoPad);
    @Bridge(symbol="CFStringTrim", optional=true)
    public native void trim(String trimString);
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
    public native boolean transform(CFRange range, CFStringTransform transform, boolean reverse);
    /*</methods>*/
}
