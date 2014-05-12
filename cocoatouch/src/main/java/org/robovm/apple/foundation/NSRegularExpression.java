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
    /*<constructors>*/
    public NSRegularExpression() {}
    protected NSRegularExpression(SkipInit skipInit) { super(skipInit); }
    public NSRegularExpression(String pattern, NSRegularExpressionOptions options, NSError.NSErrorPtr error) { super((SkipInit) null); initObject(initWithPattern$options$error$(pattern, options, error)); }
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
    protected native @Pointer long initWithPattern$options$error$(String pattern, NSRegularExpressionOptions options, NSError.NSErrorPtr error);
    @Method(selector = "regularExpressionWithPattern:options:error:")
    public static native NSRegularExpression regularExpressionWithPattern$options$error$(String pattern, NSRegularExpressionOptions options, NSError.NSErrorPtr error);
    @Method(selector = "escapedPatternForString:")
    public static native String escapedPatternForString$(String string);
    @Method(selector = "enumerateMatchesInString:options:range:usingBlock:")
    public native void enumerateMatchesInString$options$range$usingBlock$(String string, NSMatchingOptions options, @ByVal NSRange range, @Block VoidBlock3<NSTextCheckingResult, NSMatchingFlags, BytePtr> block);
    @Method(selector = "matchesInString:options:range:")
    public native NSArray<?> matchesInString$options$range$(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "numberOfMatchesInString:options:range:")
    public native @MachineSizedUInt long numberOfMatchesInString$options$range$(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "firstMatchInString:options:range:")
    public native NSTextCheckingResult firstMatchInString$options$range$(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "rangeOfFirstMatchInString:options:range:")
    public native @ByVal NSRange rangeOfFirstMatchInString$options$range$(String string, NSMatchingOptions options, @ByVal NSRange range);
    @Method(selector = "stringByReplacingMatchesInString:options:range:withTemplate:")
    public native String stringByReplacingMatchesInString$options$range$withTemplate$(String string, NSMatchingOptions options, @ByVal NSRange range, String templ);
    @Method(selector = "replaceMatchesInString:options:range:withTemplate:")
    public native @MachineSizedUInt long replaceMatchesInString$options$range$withTemplate$(NSMutableString string, NSMatchingOptions options, @ByVal NSRange range, String templ);
    @Method(selector = "replacementStringForResult:inString:offset:template:")
    public native String replacementStringForResult$inString$offset$template$(NSTextCheckingResult result, String string, @MachineSizedSInt long offset, String templ);
    @Method(selector = "escapedTemplateForString:")
    public static native String escapedTemplateForString$(String string);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
