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
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerCopyBestStringLanguage", optional=true)
    public static native CFString copyBestStringLanguage(CFString string, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerCreate", optional=true)
    public static native CFStringTokenizer create(CFAllocator alloc, CFString string, @ByVal CFRange range, CFStringTokenizerUnitOptions options, CFLocale locale);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerSetString", optional=true)
    public native void setString(CFString string, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGoToTokenAtIndex", optional=true)
    public native CFStringTokenizerTokenType goToTokenAtIndex(@MachineSizedSInt long index);
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
    public native CFType copyCurrentTokenAttribute(CFStringTokenizerUnitOptions attribute);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Bridge(symbol="CFStringTokenizerGetCurrentSubTokens", optional=true)
    public native @MachineSizedSInt long getCurrentSubTokens(CFRange ranges, @MachineSizedSInt long maxRangeLength, CFMutableArray derivedSubTokens);
    /*</methods>*/
}
