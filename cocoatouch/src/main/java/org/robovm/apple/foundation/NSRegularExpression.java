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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSRegularExpression/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSRegularExpressionPtr extends Ptr<NSRegularExpression, NSRegularExpressionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSRegularExpression.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * 
     * @param pattern
     * @param options
     * @throws NSErrorException
     */
    public NSRegularExpression(String pattern, NSRegularExpressionOptions options) throws NSErrorException {
        super((SkipInit) null);
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        initObject(init(pattern, options, err));
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
    }
    /*<constructors>*/
    protected NSRegularExpression(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "pattern")
    public native String getPattern();
    @Property(selector = "options")
    public native NSRegularExpressionOptions getOptions();
    @Property(selector = "numberOfCaptureGroups")
    public native @MachineSizedUInt long getNumberOfCaptureGroups();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPattern:options:error:")
    protected native @Pointer long init(String pattern, NSRegularExpressionOptions options, NSError.NSErrorPtr error);
    @Method(selector = "escapedPatternForString:")
    public static native String getEscapedPattern(String string);
    @Method(selector = "enumerateMatchesInString:options:range:usingBlock:")
    public native void enumerateMatches(String string, NSMatchingOptions options, @ByVal NSRange range, @Block VoidBlock3<NSTextCheckingResult, NSMatchingFlags, BooleanPtr> block);
    @Method(selector = "matchesInString:options:range:")
    public native NSArray<NSTextCheckingResult> getMatches(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "numberOfMatchesInString:options:range:")
    public native @MachineSizedUInt long getNumberOfMatches(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "firstMatchInString:options:range:")
    public native NSTextCheckingResult getFirstMatch(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "rangeOfFirstMatchInString:options:range:")
    public native @ByVal NSRange getRangeOfFirstMatch(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "stringByReplacingMatchesInString:options:range:withTemplate:")
    public native String newStringByReplacingMatches(String string, NSMatchingOptions options, @ByVal NSRange range, String templ);
    @Method(selector = "replaceMatchesInString:options:range:withTemplate:")
    public native @MachineSizedUInt long replaceMatches(NSMutableString string, NSMatchingOptions options, @ByVal NSRange range, String templ);
    @Method(selector = "replacementStringForResult:inString:offset:template:")
    public native String getReplacementString(NSTextCheckingResult result, String string, @MachineSizedSInt long offset, String templ);
    @Method(selector = "escapedTemplateForString:")
    public static native String getEscapedTemplate(String string);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
