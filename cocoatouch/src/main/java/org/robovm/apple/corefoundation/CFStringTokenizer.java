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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStringTokenizer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFStringTokenizerPtr extends Ptr<CFStringTokenizer, CFStringTokenizerPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFStringTokenizer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFStringTokenizer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static CFStringTokenizer create(String string, @ByVal CFRange range, CFStringTokenizerUnitOptions options, CFLocale locale) {
        return create(null, string, range, options, locale);
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public CFRange[] getCurrentSubTokens(long maxRanges, List<String> derivedSubTokens) {
        CFRange.CFRangePtr ptr = new CFRange.CFRangePtr();
        long length = getCurrentSubTokens(ptr, maxRanges, derivedSubTokens);
        return ptr.get().toArray((int)length);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerCopyBestStringLanguage", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getBestStringLanguage(String string, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFStringTokenizer create(CFAllocator alloc, String string, @ByVal CFRange range, CFStringTokenizerUnitOptions options, CFLocale locale);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerSetString", optional=true)
    public native void setString(String string, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGoToTokenAtIndex", optional=true)
    public native CFStringTokenizerTokenType goToToken(@MachineSizedSInt long index);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerAdvanceToNextToken", optional=true)
    public native CFStringTokenizerTokenType advanceToNextToken();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGetCurrentTokenRange", optional=true)
    public native @ByVal CFRange getCurrentTokenRange();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerCopyCurrentTokenAttribute", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getCurrentTokenAttribute(CFStringTokenizerUnitOptions attribute);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGetCurrentSubTokens", optional=true)
    protected native @MachineSizedSInt long getCurrentSubTokens(CFRange.CFRangePtr ranges, @MachineSizedSInt long maxRangeLength, @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> derivedSubTokens);
    /*</methods>*/
}
