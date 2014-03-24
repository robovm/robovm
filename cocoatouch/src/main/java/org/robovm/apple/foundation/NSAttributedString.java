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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSAttributedString/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSAttributedStringPtr extends Ptr<NSAttributedString, NSAttributedStringPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSAttributedString.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSAttributedString() {}
    protected NSAttributedString(SkipInit skipInit) { super(skipInit); }
    public NSAttributedString(String str) { super((SkipInit) null); initObject(initWithString$(str)); }
    public NSAttributedString(String str, NSDictionary<?, ?> attrs) { super((SkipInit) null); initObject(initWithString$attributes$(str, attrs)); }
    public NSAttributedString(NSAttributedString attrStr) { super((SkipInit) null); initObject(initWithAttributedString$(attrStr)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "string")
    public native String string();
    @Method(selector = "attributesAtIndex:effectiveRange:")
    public native NSDictionary<?, ?> attributesAtIndex$effectiveRange$(@MachineSizedUInt long location, NSRange range);
    @Method(selector = "length")
    public native @MachineSizedUInt long length();
    @Method(selector = "attribute:atIndex:effectiveRange:")
    public native NSObject attribute$atIndex$effectiveRange$(String attrName, @MachineSizedUInt long location, NSRange range);
    @Method(selector = "attributedSubstringFromRange:")
    public native NSAttributedString attributedSubstringFromRange$(@ByVal NSRange range);
    @Method(selector = "attributesAtIndex:longestEffectiveRange:inRange:")
    public native NSDictionary<?, ?> attributesAtIndex$longestEffectiveRange$inRange$(@MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "attribute:atIndex:longestEffectiveRange:inRange:")
    public native NSObject attribute$atIndex$longestEffectiveRange$inRange$(String attrName, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "isEqualToAttributedString:")
    public native boolean isEqualToAttributedString$(NSAttributedString other);
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String str);
    @Method(selector = "initWithString:attributes:")
    protected native @Pointer long initWithString$attributes$(String str, NSDictionary<?, ?> attrs);
    @Method(selector = "initWithAttributedString:")
    protected native @Pointer long initWithAttributedString$(NSAttributedString attrStr);
    @Method(selector = "enumerateAttributesInRange:options:usingBlock:")
    public native void enumerateAttributesInRange$options$usingBlock$(@ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "enumerateAttribute:inRange:options:usingBlock:")
    public native void enumerateAttribute$inRange$options$usingBlock$(String attrName, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, ObjCBlock block);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
