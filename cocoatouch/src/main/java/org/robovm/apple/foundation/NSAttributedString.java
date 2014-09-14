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
import org.robovm.apple.uikit.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
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
    public NSAttributedString(String str, NSAttributedStringAttributes attrs) { super((SkipInit) null); initObject(initWithString$attributes$(str, attrs)); }
    public NSAttributedString(NSAttributedString attrStr) { super((SkipInit) null); initObject(initWithAttributedString$(attrStr)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NSAttributedString)) {
            return false;
        }
        return isEqualToAttributedString$((NSAttributedString) obj);
    }
    
    public NSObject getAttribute(String name, @MachineSizedUInt long location, NSRange range) {
        return getAttribute(new NSString(name), location, range);
    }
    public NSObject getAttribute(NSAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range) {
        return getAttribute(attribute.value(), location, range);
    }
    public NSObject getAttribute(String name, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        return getAttribute(new NSString(name), location, range, rangeLimit);
    }
    public NSObject getAttribute(NSAttributedStringAttribute attribute, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit) {
        return getAttribute(attribute.value(), location, range, rangeLimit);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttributes(@ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSAttributedStringAttributes, NSRange, Boolean> block) {
        enumerateAttributes0(enumerationRange, opts, new VoidBlock3<NSDictionary<NSString,NSObject>, NSRange, BytePtr>() {
            @Override
            public void invoke(NSDictionary<NSString, NSObject> a, NSRange b, BytePtr c) {
                block.invoke(new NSAttributedStringAttributes(a), b, c.get() != 0);
            }
        });
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(String name, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, Boolean> block) {
        enumerateAttribute0(new NSString(name), enumerationRange, opts, new VoidBlock3<NSObject, NSRange, BytePtr>() {
            @Override
            public void invoke(NSObject a, NSRange b, BytePtr c) {
                block.invoke(a, b, c.get() != 0);
            }
        });
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void enumerateAttribute(NSAttributedStringAttribute attribute, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, final VoidBlock3<NSObject, NSRange, Boolean> block) {
        enumerateAttribute0(attribute.value(), enumerationRange, opts, new VoidBlock3<NSObject, NSRange, BytePtr>() {
            @Override
            public void invoke(NSObject a, NSRange b, BytePtr c) {
                block.invoke(a, b, c.get() != 0);
            }
        });
    }
    /*<methods>*/
    @Method(selector = "string")
    public native String getString();
    @Method(selector = "attributesAtIndex:effectiveRange:")
    public native NSAttributedStringAttributes getAttributes(@MachineSizedUInt long location, NSRange range);
    @Method(selector = "length")
    public native @MachineSizedUInt long getLength();
    @Method(selector = "attribute:atIndex:effectiveRange:")
    protected native NSObject getAttribute(NSString attrName, @MachineSizedUInt long location, NSRange range);
    @Method(selector = "attributedSubstringFromRange:")
    public native NSAttributedString substring(@ByVal NSRange range);
    @Method(selector = "attributesAtIndex:longestEffectiveRange:inRange:")
    public native NSAttributedStringAttributes getAttributes(@MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "attribute:atIndex:longestEffectiveRange:inRange:")
    protected native NSObject getAttribute(NSString attrName, @MachineSizedUInt long location, NSRange range, @ByVal NSRange rangeLimit);
    @Method(selector = "isEqualToAttributedString:")
    protected native boolean isEqualToAttributedString$(NSAttributedString other);
    @Method(selector = "initWithString:")
    protected native @Pointer long initWithString$(String str);
    @Method(selector = "initWithString:attributes:")
    protected native @Pointer long initWithString$attributes$(String str, NSAttributedStringAttributes attrs);
    @Method(selector = "initWithAttributedString:")
    protected native @Pointer long initWithAttributedString$(NSAttributedString attrStr);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumerateAttributesInRange:options:usingBlock:")
    protected native void enumerateAttributes0(@ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, @Block("(,@ByVal,)") VoidBlock3<NSDictionary<NSString, NSObject>, NSRange, BytePtr> block);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "enumerateAttribute:inRange:options:usingBlock:")
    protected native void enumerateAttribute0(NSString attrName, @ByVal NSRange enumerationRange, NSAttributedStringEnumerationOptions opts, @Block("(,@ByVal,)") VoidBlock3<NSObject, NSRange, BytePtr> block);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
